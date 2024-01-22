package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.epos.eposdatamodel.Distribution;
import org.epos.eposdatamodel.Operation;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.State;
import org.epos.eposdatamodel.WebService;
import org.epos.handler.dbapi.DBAPIClient;
import org.epos.handler.dbapi.DBAPIClient.DeleteQuery;
import org.epos.handler.dbapi.DBAPIClient.SaveQuery;

public class OperationManager {

	protected static DBAPIClient dbapi = new DBAPIClient();

	public static ApiResponseMessage getOperation(String meta_id, String instance_id) {
		//dbapi.setMetadataMode(false);
		if (meta_id == null)
			return new ApiResponseMessage(1, "The [meta_id] field can't be left blank");
		if(instance_id == null) {
			instance_id = "all";
		}

		System.out.println(meta_id+" "+instance_id);

		List<Operation> list;
		if (meta_id.equals("all")) {
			list = dbapi.retrieve(Operation.class, new DBAPIClient.GetQuery());	
		} else {
			if(instance_id.equals("all")) {
				list = dbapi.retrieve(Operation.class, new DBAPIClient.GetQuery());	
				list = list.stream()
						.filter(
								elem -> elem.getMetaId().equals(meta_id)
								)
						.collect(Collectors.toList());

			}else {
				list = dbapi.retrieve(Operation.class, new DBAPIClient.GetQuery().instanceId(instance_id));
			}
		}

		list = list.stream()
				.collect(Collectors.toList());

		List<Operation> revertedList = new ArrayList<>();
		list.forEach(e -> revertedList.add(0, e));

		if (list.isEmpty())
			return new ApiResponseMessage(ApiResponseMessage.OK, new ArrayList<Operation>());

		return new ApiResponseMessage(ApiResponseMessage.OK, list);
	}

	/**
	 * 
	 * @param Operation
	 * @param user
	 * @return
	 */
	public static ApiResponseMessage createOperation(Operation operation, boolean parents, boolean sons) {
		/** ID MANAGEMENT 
		 * if UID == NULL --> Generate a new UID
		 * Brand new Operation? --> InstanceId = null && InstanceChangeId == null
		 * New Operation from existing one? --> InstanceChangeId == OLD InstanceId
		 * 
		 **/
		if(operation.getUid()==null) {
			System.err.println("UID undefined, generating a new one");
			operation.setUid(operation.getClass().getSimpleName().toLowerCase()+"/"+UUID.randomUUID());
		}
		operation.setInstanceId(null);
		operation.setInstanceChangedId(null);

		// Check if exists a version PUBLISHED or ARCHIVED if MetaId!=null
		if (operation.getMetaId() != null) {
			List<Operation> retrieved = dbapi.retrieve(Operation.class, new DBAPIClient.GetQuery().state(State.PUBLISHED).metaId(operation.getMetaId()));
			if(retrieved.isEmpty())
				retrieved = dbapi.retrieve(Operation.class, new DBAPIClient.GetQuery().state(State.ARCHIVED).metaId(operation.getMetaId()));
			if(retrieved.isEmpty())
				retrieved = dbapi.retrieve(Operation.class, new DBAPIClient.GetQuery().state(State.SUBMITTED).metaId(operation.getMetaId()));
			if(!retrieved.isEmpty()) {
				operation.setInstanceChangedId(retrieved.get(0).getInstanceId());
			}	
		}

		operation.setState(State.DRAFT);
		operation.setEditorId("backoffice");
		operation.setFileProvenance("instance created with the backoffice");

			dbapi.setTransactionModeAuto(true);
		dbapi.startTransaction();

		LinkedEntity reference;
		try {

			// save the entity and get the reference to it
			reference = dbapi.create(operation);
			operation.setInstanceId(reference.getInstanceId());
			operation.setMetaId(reference.getMetaId());
			//TODO: Parents?
		} catch (Exception e) {
			e.printStackTrace();
			dbapi.rollbackTransaction();
			return new ApiResponseMessage(ApiResponseMessage.ERROR, "Something went wrong during the persisting of the new instance: "+e.getMessage());
		}

		dbapi.closeTransaction(true);
		dbapi.setTransactionModeAuto(true);

		manageRelations(operation, reference, parents, sons);

		return new ApiResponseMessage(ApiResponseMessage.OK, reference);
	}

	/**
	 * 
	 * @param operation
	 * @param user
	 * @return
	 */
	public static ApiResponseMessage updateOperation(Operation operation, boolean parents, boolean sons) {

		if(operation.getState()!=null && (operation.getState().equals(State.ARCHIVED) || operation.getState().equals(State.PUBLISHED))) {
			return new ApiResponseMessage(ApiResponseMessage.ERROR, "Unable to update a ARCHIVED or PUBLISHED instance");
		}
		if (operation.getInstanceId() == null) {
			return new ApiResponseMessage(ApiResponseMessage.ERROR, "InstanceId required");
		}
		if(operation.getInstanceChangedId() == null || operation.getInstanceChangedId().isEmpty())
			operation.setInstanceChangedId(null);

		operation.setEditorId("backoffice");
		operation.setFileProvenance("instance created with the backoffice");

		dbapi.setTransactionModeAuto(true);
		dbapi.startTransaction();
		LinkedEntity reference = null;
		try {
			reference = dbapi.createUpdate(operation, new SaveQuery().setInstanceId(operation.getInstanceId()));//dbapi.hardUpdate(operation);
		} catch (Exception e) {
			dbapi.rollbackTransaction();
			return new ApiResponseMessage(ApiResponseMessage.ERROR, "Something went wrong during the persisting of the new instance: "+e.getMessage());
		}

		dbapi.closeTransaction(true);
		dbapi.setTransactionModeAuto(true);

		manageRelations(operation, reference, parents, sons);

		return new ApiResponseMessage(ApiResponseMessage.OK, reference);
	}

	/**
	 * 
	 * @param operation
	 * @return
	 */
	public static boolean deleteOperation(String instance_id) {
		List<Operation> list = dbapi.retrieve(Operation.class, new DBAPIClient.GetQuery().instanceId(instance_id));

		if (list.isEmpty()) return false;
		Operation instance = list.get(0);
		dbapi.setTransactionModeAuto(true);
		dbapi.startTransaction();

		List<Distribution> distributions = (List<Distribution>) DistributionManager.getDistribution("all", null);
		for(Distribution distr : distributions) {
			if(distr.getAccessURL()!=null) {
				if(distr.getAccessURL().removeIf(url -> url.getUid().equals(instance.getUid())
						&& url.getMetaId().equals(instance.getMetaId())
						&& url.getInstanceId().equals(instance.getInstanceId())))
					DistributionManager.updateDistribution(distr, false, false);
			}
		}

		dbapi.delete(Operation.class, new DeleteQuery().instanceId(instance.getInstanceId()));

		dbapi.closeTransaction(true);
		dbapi.setTransactionModeAuto(true);

		return true;
	}

	private static void manageRelations(Operation operation, LinkedEntity relation, boolean parents, boolean sons) {

		System.out.println("*************\nManaging relation of: "+operation);
		System.out.println("WebServices: "+operation.getWebservice());
		if(parents) {
			if(operation.getWebservice()!=null)
				for(LinkedEntity le : operation.getWebservice()) {
					WebService webService = (WebService) WebServiceManager.getWebService(le.getMetaId(), le.getInstanceId()).get(0);
					webService.getSupportedOperation().add(relation);
					WebServiceManager.createWebService(webService, true, false);
				}
		}
	}


}

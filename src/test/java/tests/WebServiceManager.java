package tests;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.epos.eposdatamodel.DataProduct;
import org.epos.eposdatamodel.Distribution;
import org.epos.eposdatamodel.WebService;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.DBAPIClient;
import org.epos.handler.dbapi.DBAPIClient.DeleteQuery;
import org.epos.handler.dbapi.DBAPIClient.SaveQuery;
import org.epos.handler.dbapi.DBAPIClient.UpdateQuery;

public class WebServiceManager {

	protected static DBAPIClient dbapi = new DBAPIClient();

	public static List<WebService> getWebService(String meta_id, String instance_id) {
		dbapi.setMetadataMode(false);
		List<WebService> list;
		if (meta_id.equals("all")) {
			list = dbapi.retrieve(WebService.class, new DBAPIClient.GetQuery());	
		} else {
			if(instance_id.equals("all")) {
				list = dbapi.retrieve(WebService.class, new DBAPIClient.GetQuery());	
				list = list.stream()
						.filter(
								elem -> elem.getMetaId().equals(meta_id)
								)
						.collect(Collectors.toList());

			}else {
				list = dbapi.retrieve(WebService.class, new DBAPIClient.GetQuery().instanceId(instance_id));
			}
		}

		return list;
	}

	/**
	 * 
	 * @param WebService
	 * @param user
	 * @return
	 */
	public static ApiResponseMessage createWebService(WebService webservice, boolean parents, boolean sons) {
		/** ID MANAGEMENT 
		 * if UID == NULL --> Generate a new UID
		 * Brand new WebService? --> InstanceId = null && InstanceChangeId == null
		 * New WebService from existing one? --> InstanceChangeId == OLD InstanceId
		 * 
		 **/
		if(webservice.getUid()==null) {
			System.err.println("UID undefined, generating a new one");
			webservice.setUid(webservice.getClass().getSimpleName().toLowerCase()+"/"+UUID.randomUUID());
		}
		webservice.setInstanceId(null);
		webservice.setInstanceChangedId(null);

		// Check if exists a version PUBLISHED or ARCHIVED if MetaId!=null
		if (webservice.getMetaId() != null) {
			List<WebService> retrieved = dbapi.retrieve(WebService.class, new DBAPIClient.GetQuery().state(State.PUBLISHED).metaId(webservice.getMetaId()));
			if(retrieved.isEmpty())
				retrieved = dbapi.retrieve(WebService.class, new DBAPIClient.GetQuery().state(State.ARCHIVED).metaId(webservice.getMetaId()));
			if(retrieved.isEmpty())
				retrieved = dbapi.retrieve(WebService.class, new DBAPIClient.GetQuery().state(State.SUBMITTED).metaId(webservice.getMetaId()));
			if(!retrieved.isEmpty()) {
				webservice.setInstanceChangedId(retrieved.get(0).getInstanceId());
			}	
		}

		webservice.setState(State.DRAFT);
		webservice.setEditorId("backoffice");
		webservice.setFileProvenance("instance created with the backoffice");

		dbapi.setTransactionModeAuto(true);
		dbapi.startTransaction();

		LinkedEntity reference;
		try {

			// save the entity and get the reference to it
			reference = dbapi.create(webservice);
			webservice.setInstanceId(reference.getInstanceId());
			webservice.setMetaId(reference.getMetaId());
			//TODO: Parents?
		} catch (Exception e) {
			e.printStackTrace();
			dbapi.rollbackTransaction();
			return new ApiResponseMessage(ApiResponseMessage.ERROR, "Something went wrong during the persisting of the new instance: "+e.getMessage());
		}

		dbapi.closeTransaction(true);
		dbapi.setTransactionModeAuto(true);

		manageRelations(webservice, reference, parents, sons);

		return new ApiResponseMessage(ApiResponseMessage.OK, reference);
	}

	/**
	 * 
	 * @param webservice
	 * @param user
	 * @return
	 */
	public static ApiResponseMessage updateWebService(WebService webservice, boolean parents, boolean sons) {

		if(webservice.getState()!=null && (webservice.getState().equals(State.ARCHIVED) || webservice.getState().equals(State.PUBLISHED))) {
			return new ApiResponseMessage(ApiResponseMessage.ERROR, "Unable to update a ARCHIVED or PUBLISHED instance");
		}
		if (webservice.getInstanceId() == null) {
			return new ApiResponseMessage(ApiResponseMessage.ERROR, "InstanceId required");
		}
		if(webservice.getInstanceChangedId() == null || webservice.getInstanceChangedId().isEmpty())
			webservice.setInstanceChangedId(null);

		webservice.setEditorId("backoffice");
		webservice.setFileProvenance("instance created with the backoffice");

		dbapi.setTransactionModeAuto(true);
		dbapi.startTransaction();
		
		LinkedEntity reference = null;
		
		try {
			reference = dbapi.createUpdate(webservice,  new SaveQuery().setInstanceId(webservice.getInstanceId()));
		} catch (Exception e) {
			e.printStackTrace();
			dbapi.rollbackTransaction();
			return new ApiResponseMessage(ApiResponseMessage.ERROR, "Something went wrong during the persisting of the new instance: "+e.getMessage());
		}

		dbapi.closeTransaction(true);
		dbapi.setTransactionModeAuto(true);

		manageRelations(webservice, reference, parents, sons);

		return new ApiResponseMessage(ApiResponseMessage.OK, reference);
	}

	/**
	 * 
	 * @param webservice
	 * @return
	 */
	public static boolean deleteWebService(String instance_id) {
		List<WebService> list = dbapi.retrieve(WebService.class, new DBAPIClient.GetQuery().instanceId(instance_id));

		if (list.isEmpty()) return false;
		WebService instance = list.get(0);
		dbapi.setTransactionModeAuto(true);
		dbapi.startTransaction();

		dbapi.delete(WebService.class, new DeleteQuery().instanceId(instance.getInstanceId()));

		dbapi.closeTransaction(true);
		dbapi.setTransactionModeAuto(true);

		return true;
	}

	private static void manageRelations(WebService webservice, LinkedEntity relation, boolean parents, boolean sons) {

		System.out.println("*************\nManaging relation of: "+webservice);
		System.out.println("Distribution: "+webservice.getDistribution());
		if(parents) {
			for(LinkedEntity le : webservice.getDistribution()) {
				Distribution distribution = DistributionManager.getDistribution(le.getMetaId(), le.getInstanceId()).get(0);
				distribution.setAccessService(relation);
				DistributionManager.updateDistribution(distribution, false, false);
			}
		}

	}


}

package org.epos.handler.dbapi;

import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.sessions.UnitOfWork;
import org.epos.eposdatamodel.*;
import org.epos.handler.dbapi.dbapiimplementation.*;
import org.epos.handler.dbapi.service.DBService;


import javax.persistence.EntityManager;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class DBAPIClient implements DBAPIClientInterface {
    public static final Map<Class<? extends EPOSDataModelEntity>, EPOSDataModel<? extends EPOSDataModelEntity>> DBAPI;

    static {
        HashMap<Class<? extends EPOSDataModelEntity>, EPOSDataModel<? extends EPOSDataModelEntity>> dbapiImplementationMapToBeBuilt = new HashMap<>();
        dbapiImplementationMapToBeBuilt.put(ContactPoint.class, new ContactPointDBAPI());
        dbapiImplementationMapToBeBuilt.put(DataProduct.class, new DataProductDBAPI());
        dbapiImplementationMapToBeBuilt.put(Distribution.class, new DistributionDBAPI());
        dbapiImplementationMapToBeBuilt.put(Equipment.class, new EquipmentDBAPI());
        dbapiImplementationMapToBeBuilt.put(Facility.class, new FacilityDBAPI());
        dbapiImplementationMapToBeBuilt.put(Operation.class, new OperationDBAPI());
        dbapiImplementationMapToBeBuilt.put(Organization.class, new OrganizationDBAPI());
        dbapiImplementationMapToBeBuilt.put(Person.class, new PersonDBAPI());
        dbapiImplementationMapToBeBuilt.put(Service.class, new ServiceDBAPI());
        dbapiImplementationMapToBeBuilt.put(SoftwareApplication.class, new SoftwareApplicationDBAPI());
        dbapiImplementationMapToBeBuilt.put(SoftwareSourceCode.class, new SoftwareSourceCodeDBAPI());
        dbapiImplementationMapToBeBuilt.put(WebService.class, new WebServiceDBAPI());
        //dbapiImplementationMapToBeBuilt.put("contract", new ContractDBAPI());
        //dbapiImplementationMapToBeBuilt.put("publication", new PublicationDBAPI());
        dbapiImplementationMapToBeBuilt.put(DataProductImplementationStatus.class, new DataProductImplementationStatusDBAPI());
        //dbapiImplementationMapToBeBuilt.put("serviceimplementationstatus", new ServiceImplementationStatusDBAPI());

        DBAPI = Collections.unmodifiableMap(dbapiImplementationMapToBeBuilt);
    }

    private boolean transactionModeAuto = true;
    private boolean metadataMode = true;
    private EntityManager em;
    private EntityManagerHandler entityManagerHandler = new DBService();

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public DBAPIClient(boolean transactionModeAuto) {
        this.transactionModeAuto = transactionModeAuto;
    }

    public DBAPIClient(boolean transactionModeAuto, boolean metadataMode) {
        this.transactionModeAuto = transactionModeAuto;
        this.metadataMode = metadataMode;
    }

    public EntityManagerHandler getEntityManagerHandler() {
        return entityManagerHandler;
    }

    public void setEntityManagerHandler(EntityManagerHandler entityManagerHandler) {
        this.entityManagerHandler = entityManagerHandler;
    }

    public DBAPIClient entityManagerHandler(EntityManagerHandler entityManagerHandler) {
        this.entityManagerHandler = entityManagerHandler;
        return this;
    }

    public DBAPIClient() {
    }

    public boolean isMetadataMode() {
        return metadataMode;
    }

    public DBAPIClient metadataMode(boolean metadataMode) {
        this.metadataMode = metadataMode;
        DBAPI.values().forEach(dbapi -> dbapi.setMetadataMode(metadataMode));
        return this;
    }

    public void setMetadataMode(boolean metadataMode){
        this.metadataMode = metadataMode;
        DBAPI.values().forEach(dbapi -> dbapi.setMetadataMode(metadataMode));
    }

    public void flush(){
        this.em.flush();
    }

    public void rollbackTransaction() {
        em.getTransaction().rollback();
        em.close();
    }

    public void startTransaction() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        em = this.entityManagerHandler.getEntityManager();
        em.getTransaction().begin();
    }

    public void closeTransaction(boolean toBeSaved) {
        if (em != null && em.isOpen()) {

            if (toBeSaved && em.getTransaction().isActive()) {
                em.getTransaction().commit();
                em.getEntityManagerFactory().getCache().evictAll();
            }
            em.close();
        }
    }

    public boolean isTransactionModeAuto() {
        return transactionModeAuto;
    }

    public void setTransactionModeAuto(boolean transactionModeAuto) {
        this.transactionModeAuto = transactionModeAuto;
    }

    public <T extends EPOSDataModelEntity> List<T> retrieve(Class<T> clazz, GetQuery query) {
        EPOSDataModel<T> dbapi = (EPOSDataModel<T>) DBAPI.get(clazz);

        if (transactionModeAuto) startTransaction();

        List<T> results;

        if (query.getInstanceId() != null) {
            T byInstanceId = dbapi.getByInstanceId(query.getInstanceId(), em);
            results = byInstanceId == null ? new ArrayList<>() : List.of(byInstanceId);
        } else if (query.getUid() != null && query.getState() != null) {
            results = dbapi.getByUid(query.getUid(), em)
                    .stream()
                    .filter(i -> Objects.equals(i.getState(), query.getState()))
                    .collect(Collectors.toList());
        } else if (query.getUid() != null) {
            results = dbapi.getByUid(query.getUid(), em);
        } else if (query.getMetaId() != null && query.getState() != null) {
            results = dbapi.getByMetaId(query.getMetaId(), em)
                    .stream()
                    .filter(i -> i.getState().equals(query.getState()))
                    .collect(Collectors.toList());
        } else if (query.getMetaId() != null) {
            results = dbapi.getByMetaId(query.getMetaId(), em);
        } else if (query.getState() != null) {
            results = dbapi.getAllByState(query.getState(), em);
        } else {
            results = dbapi.getAll(em);
        }

        if (transactionModeAuto) closeTransaction(false);

        if(query.getSpecificParameters() != null) {
            for (Map.Entry<String, Object> entry : query.getSpecificParameters().entrySet()){
                try {

                    List<T> tmp = new LinkedList<>();
                    for ( T result : results){
                        Object f = new PropertyDescriptor(entry.getKey(), clazz).getReadMethod().invoke(result);
                        if (entry.getValue().equals(f)) tmp.add(result);
                    }
                    results = tmp;

                } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                    throw new IllegalArgumentException("no getter for the attribute name: " + entry.getKey() + ".");
                }
            }
        }


        System.out.println("theqyert: "+results);
        return results;
    }

    public <T extends EPOSDataModelEntity> LinkedEntity create(EPOSDataModelEntity instance) {
        return create(instance, new SaveQuery());
    }

    public <T extends EPOSDataModelEntity> LinkedEntity create(EPOSDataModelEntity instance, SaveQuery query) {
        LinkedEntity savedEntityReference;

        EPOSDataModel<T> dbapi = (EPOSDataModel<T>) DBAPI.get(instance.getClass());

        if (transactionModeAuto) startTransaction();

        if (query.instanceId != null) {
            if (query.instanceId.isBlank()) throw new IllegalArgumentException("the new instanceId can't be blank");
            savedEntityReference = dbapi.save((T) instance, em, query.instanceId);
        } else {
            savedEntityReference = dbapi.save((T) instance, em);
        }

        if (transactionModeAuto) closeTransaction(true);

        return savedEntityReference;
    }

    public <T extends EPOSDataModelEntity> void update(EPOSDataModelEntity instance) {
        update(instance, new UpdateQuery());
    }

    public <T extends EPOSDataModelEntity> void update(EPOSDataModelEntity instance, UpdateQuery query) {
        EPOSDataModel<T> dbapi = (EPOSDataModel<T>) DBAPI.get(instance.getClass());

        if (transactionModeAuto) startTransaction();

        if (query.hardUpdate) {
            //dbapi.hardUpdate(instance.getInstanceId(), (T) instance, em);
        	dbapi.save((T) instance, em);
        }

        if (query.state != null){
        	dbapi.save((T) instance, em);
            //dbapi.updateStatus(instance.getInstanceId(), query.state, em);
        }

        if (transactionModeAuto) closeTransaction(true);
    }


    public <T extends EPOSDataModelEntity> void delete(Class<T> clazz, DeleteQuery query) {
        if (transactionModeAuto) startTransaction();

        if (query.instanceId == null || query.instanceId.isBlank())
            throw new IllegalArgumentException("the new instanceId can't be blank");

        EPOSDataModel<T> dbapi = (EPOSDataModel<T>) DBAPI.get(clazz);

        dbapi.delete(query.getInstanceId(), em);

        if (transactionModeAuto) closeTransaction(true);
    }


    public static class DeleteQuery {
        private String instanceId;

        public String getInstanceId() {
            return instanceId;
        }

        public DeleteQuery instanceId(String instanceId) {
            this.instanceId = instanceId;
            return this;
        }
    }

    public static class UpdateQuery {
        public boolean hardUpdate;

        private State state;

        public boolean isHardUpdate() {
            return hardUpdate;
        }

        public UpdateQuery hardUpdate(boolean hardUpdate) {
            this.hardUpdate = hardUpdate;
            return this;
        }

        public State getState() {
            return state;
        }

        public UpdateQuery state(State state) {
            this.state = state;
            return this;
        }
    }

    public static class SaveQuery {

        public String instanceId;

        public String getInstanceId() {
            return instanceId;
        }

        public SaveQuery setInstanceId(String instanceId) {
            this.instanceId = instanceId;
            return this;
        }
    }

    public static class GetQuery {
        private String uid;
        private String metaId;
        private String instanceId;
        private State state;
        private Map<String, Object> specificParameters;

        public String getUid() {
            return uid;
        }

        public GetQuery uid(String uid) {
            this.uid = uid;
            return this;
        }

        public String getMetaId() {
            return metaId;
        }

        public GetQuery metaId(String metaId) {
            this.metaId = metaId;
            return this;
        }

        public String getInstanceId() {
            return instanceId;
        }

        public GetQuery instanceId(String instanceId) {
            this.instanceId = instanceId;
            return this;
        }

        public State getState() {
            return state;
        }

        public GetQuery state(State state) {
            this.state = state;
            return this;
        }

        public Map<String, Object> getSpecificParameters() {
            return specificParameters;
        }

        public GetQuery specificParameters(Map<String, Object> specificParameters) {
            this.specificParameters = specificParameters;
            return this;
        }
    }

}

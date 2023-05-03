package org.epos.handler.dbapi.dbapiimplementation;

import org.epos.eposdatamodel.EPOSDataModelEntity;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.EPOSDataModel;
import org.epos.handler.dbapi.service.DBService;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.epos.handler.dbapi.util.DBUtil.getFromDB;
import static org.epos.handler.dbapi.util.DBUtil.getOneFromDB;

public abstract class AbstractDBAPI<T extends EPOSDataModelEntity> implements EPOSDataModel<T> {

    protected String entityString;
    protected String namedQuery_getByUid;
    protected String namedQuery_getByMetaId;
    protected String namedQuery_getAllByState;
    protected String namedQuery_getAll;
    protected String namedQuery_getByUidAndState;
    protected String namedQuery_getByInstanceId;
    protected String namedQuery_getByFileProvenance;
    protected Class<?> edmClass;

    protected boolean metadataMode = true;

    public AbstractDBAPI(String entityString, Class<?> edmClass) {
        this.entityString = entityString;
        this.namedQuery_getByUid = entityString + ".findByUid";
        this.namedQuery_getAll = entityString + ".findAll";
        this.namedQuery_getByUidAndState = entityString + ".findByUidAndState";
        this.namedQuery_getByMetaId = entityString + ".findAllByMetaId";
        this.namedQuery_getAllByState = entityString + ".findAllByState";
        this.namedQuery_getByFileProvenance = entityString + ".findByFileProvenance";
        this.namedQuery_getByInstanceId = entityString + ".findByInstanceId";

        this.edmClass = edmClass;
    }

    public boolean isMetadataMode() {
        return metadataMode;
    }

    public void setMetadataMode(boolean metadataMode) {
        this.metadataMode = metadataMode;
    }

    public AbstractDBAPI<T> metadataMode(boolean metadataMode) {
        this.metadataMode = metadataMode;
        return this;
    }

    @Override
    public List<T> getAllByFileProvenance(String fileProvenance, EntityManager em) {
        return getList(namedQuery_getByFileProvenance, edmClass, "FPROV", fileProvenance, em);
    }

    @Override
    public List<T> getByMetaId(String metaId, EntityManager em) {
        return getList(namedQuery_getByMetaId, edmClass, "METAID", metaId, em);
    }


    @Override
    public List<T> getAllByState(State state, EntityManager em) {
        return getList(namedQuery_getAllByState, edmClass, "STATE", state.toString(), em);
    }

    @Override
    public List<T> getByUid(String uid, EntityManager em) {
        return getByUidHelper(edmClass, namedQuery_getByUid, em, uid);
    }

    @Override
    public void save(T eposDataModelObject) {
        EntityManager em = new DBService().getEntityManager();
        em.getTransaction().begin();

        save(eposDataModelObject, em);

        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
            em.getEntityManagerFactory().getCache().evictAll();
        }
        em.close();

    }

    @Override
    public LinkedEntity save(T eposDataModelObject, EntityManager em) {
        String instanceId = UUID.randomUUID().toString();
        return save(eposDataModelObject, em, instanceId);
    }

    @Override
    public void hardUpdate(String instanceId, T eposDataModelObject) {
        EntityManager em = new DBService().getEntityManager();
        em.getTransaction().begin();
        hardUpdate(instanceId, eposDataModelObject, em);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void hardUpdate(String instanceId, T eposDataModelObject, EntityManager em) {
        delete(instanceId, em);
        em.flush();
        //System.out.println(getByInstanceId(instanceId, em));
        save(eposDataModelObject, em, instanceId);
    }

    @Override
    public void delete(String instanceId) {
        EntityManager em = new DBService().getEntityManager();
        em.getTransaction().begin();

        delete(instanceId, em);

        em.getTransaction().commit();
        em.close();
    }

    public T getByUidPublished(String uid, EntityManager em) {

        List<T> list = getList(namedQuery_getByUidAndState, edmClass, "UID", uid, "STATE", State.PUBLISHED.toString(), em);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public T getByUidPublished(String uid) {
        EntityManager em = new DBService().getEntityManager();
        em.getTransaction().begin();

        T byUidPublished = getByUidPublished(uid, em);

        em.getTransaction().commit();
        em.close();

        return byUidPublished;
    }

    public List<T> getAllByGroup(List<String> id) {
        return null;
    }

    @Override
    public void updateStatus(String instanceId, State state) {
        EntityManager em = new DBService().getEntityManager();
        em.getTransaction().begin();

        updateStatus(instanceId, state, em);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public T getByInstanceId(String instanceId) {
        EntityManager em = new DBService().getEntityManager();
        em.getTransaction().begin();

        T instance = getByInstanceId(instanceId, em);

        em.getTransaction().commit();
        em.close();

        return instance;
    }

    protected List<T> getList(String namedQueryName, Class<?> jpaClass, EntityManager em) {
        return getFromDB(em, jpaClass, namedQueryName).stream()
                .map(this::mapFromDB)
                .collect(Collectors.toList());
    }

    protected List<T> getList(String namedQueryName, Class<?> jpaClass, String paramName, String param, EntityManager em) {
        return getFromDB(em, jpaClass, namedQueryName, paramName, param).stream()
                .map(this::mapFromDB)
                .collect(Collectors.toList());
    }

    protected List<T> getList(String namedQueryName, Class<?> jpaClass, String paramName, String param, String paramName1, String param1, EntityManager em) {
        return getFromDB(em, jpaClass, namedQueryName, paramName, param, paramName1, param1).stream()
                .map(this::mapFromDB)
                .collect(Collectors.toList());
    }

    protected List<T> getList(String namedQueryName, Class<?> jpaClass) {
        EntityManager em = new DBService().getEntityManager();

        List<T> list = getFromDB(em, jpaClass, namedQueryName).stream()
                .map(this::mapFromDB)
                .collect(Collectors.toList());

        em.close();
        return list;
    }

    protected List<T> getList(String namedQueryName, Class<?> jpaClass, String paramName, String param) {
        EntityManager em = new DBService().getEntityManager();

        List<T> list = getFromDB(em, jpaClass, namedQueryName, paramName, param).stream()
                .map(this::mapFromDB)
                .collect(Collectors.toList());

        em.close();
        return list;
    }

    protected abstract T mapFromDB(Object edmObject);

    protected <D> List<T> getByUidHelper(Class<D> clazz, String namedQuery, EntityManager em, String uid) {
        List<D> publishedList = getFromDB(em, clazz, namedQuery,
                "UID", uid);
        return publishedList.stream().map(this::mapFromDB).collect(Collectors.toList());
    }

    protected <D> boolean isAlreadyPublished(Class<D> clazz, String namedQuery, EntityManager em, T eposDataModelObject) {
        //if the instace need to be public check if is correct (duplicate)
        D published = getOneFromDB(em, clazz, namedQuery,
                "UID", eposDataModelObject.getUid(),
                "STATE", State.PUBLISHED.toString());

        if (published != null) {
            try {

                String methodFileProvenanceName = "getFileprovenance";
                Method methodFileProvenance = published.getClass().getMethod(methodFileProvenanceName);
                String fileProvenance = (String) methodFileProvenance.invoke(published);

                String methodMetaIdName = "getMetaId";
                Method methodMetaId = published.getClass().getMethod(methodMetaIdName);
                String metaId = (String) methodMetaId.invoke(published);

                System.err.println("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: "
                        + eposDataModelObject.getUid() + " and metaid: " + metaId + ", is already published" +
                        (fileProvenance != null ? " (found in file: " + fileProvenance + ")." : "."));
                return true;
            } catch (NoSuchMethodException | NullPointerException | IllegalAccessException | InvocationTargetException ignored) {
            }
            em.getTransaction().rollback();
            return true;
        }
        return false;
    }

    @Override
    public void updateStatus(String instanceId, State state, EntityManager em) {
        Object fromDB = getOneFromDB(em, edmClass, namedQuery_getByInstanceId, "INSTANCEID", instanceId);

        if (fromDB == null) return;
        try {
            Method method = fromDB.getClass().getMethod("setState", String.class);
            method.invoke(fromDB, state.toString());
        } catch (NoSuchMethodException | NullPointerException | IllegalAccessException | InvocationTargetException ignored) {
            em.getTransaction().rollback();

        }
        em.merge(fromDB);
    }

    @Override
    public void delete(String instanceId, EntityManager em) {
        Object fromDB = getOneFromDB(em, edmClass, namedQuery_getByInstanceId, "INSTANCEID", instanceId);
        if (fromDB != null) {
            em.remove(fromDB);
        }
    }


    @Override
    public List<T> getByMetaId(String metaId) {
        return getList(namedQuery_getByMetaId, edmClass, "METAID", metaId);
    }

    @Override
    public List<T> getByUid(String uid) {
        EntityManager em = new DBService().getEntityManager();
        List<T> list = getByUidHelper(edmClass, namedQuery_getByUid, em, uid);
        em.close();
        return list;
    }

    @Override
    public T getByInstanceId(String instanceId, EntityManager em) {
        Object fromDB = getOneFromDB(em, edmClass, namedQuery_getByInstanceId, "INSTANCEID", instanceId);
        if (fromDB != null) return mapFromDB(fromDB);
        return null;
    }

    @Override
    public List<T> getAll() {
        EntityManager em = new DBService().getEntityManager();

        List<T> list = getAll(em);

        em.close();
        return list;
    }

    @Override
    public List<T> getAll(EntityManager em) {
        return getFromDB(em, edmClass, namedQuery_getAll).stream()
                .map(this::mapFromDB)
                .collect(Collectors.toList());
    }


    @Override
    public List<T> getAllByState(State state) {
        EntityManager em = new DBService().getEntityManager();

        List<T> list = getFromDB(em, edmClass, namedQuery_getAllByState, "STATE", state.toString()).stream()
                .map(this::mapFromDB)
                .collect(Collectors.toList());

        em.close();
        return list;
    }

}

package dao;

import model.Versioningstatus;
import org.epos.handler.dbapi.service.DBService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class EposDataModelDAO<T> {
	
	private final DBService entityManager;

    public  EposDataModelDAO(){
        this.entityManager = new DBService();
    }

    public void createObject(T entity) {
    	EntityManager em = entityManager.getEntityManager();
    	em.getTransaction().begin();
        em.persist(entity);
    	em.getTransaction().commit();
    }

    public List<T> getOneFromDBByInstanceId(String instanceId, Class<T> obj){
        EntityManager em = entityManager.getEntityManager();
        em.getTransaction().begin();
        List resultList = em.createQuery(
                        "SELECT c FROM "+obj.getSimpleName()+" c WHERE c.instanceId LIKE :instanceId")
                .setParameter("instanceId", instanceId)
                .getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    public List<T> getOneFromDBByMetaId(String metaId, Class<T> obj){
        EntityManager em = entityManager.getEntityManager();
        em.getTransaction().begin();
        List resultList = em.createQuery(
                        "SELECT c FROM "+obj.getSimpleName()+" c WHERE c.metaId LIKE :metaId")
                .setParameter("metaId", metaId)
                .getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    public List<T> getOneFromDBByUID(String uid, Class<T> obj){
        EntityManager em = entityManager.getEntityManager();
        em.getTransaction().begin();
        System.out.println(uid);
        List resultList = em.createQuery(
                        "SELECT c FROM "+obj.getSimpleName()+" c WHERE c.uid LIKE :uid")
                .setParameter("uid", uid)
                .getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    public List<T> getOneFromDBByVersionID(String versionId, Class<T> obj){
        EntityManager em = entityManager.getEntityManager();
        em.getTransaction().begin();
        List resultList = em.createQuery(
                        "SELECT c FROM "+obj.getSimpleName()+" c WHERE c.versionId LIKE :versionId")
                .setParameter("versionId", versionId)
                .getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    public List<T> getOneFromDB(String instanceId, String metaId, String uid, String versionId, Class<T> obj){
        List resultList = new ArrayList<T>();
        if(instanceId!=null) resultList.addAll(getOneFromDBByInstanceId(instanceId,obj));
        if(metaId!=null) resultList.addAll(getOneFromDBByMetaId(metaId,obj));
        if(uid!=null) resultList.addAll(getOneFromDBByUID(uid,obj));
        if(versionId!=null) resultList.addAll(getOneFromDBByVersionID(versionId,obj));

        return resultList;
    }

    public List<Versioningstatus> getVersionsFromDBByVersionId(String versionId){
        Versioningstatus objReturn = entityManager.getEntityManager().find(Versioningstatus.class, versionId);
        return objReturn==null? List.of() : List.of(objReturn);
    }

    public List<T> getAllFromDB(Class<T> obj){
        EntityManager em = entityManager.getEntityManager();
        em.getTransaction().begin();
        List resultList = em.createQuery(
                        "SELECT c FROM "+obj.getSimpleName()+" c")
                .getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    public void updateObject(T obj) {
        if (obj == null) return;
    	EntityManager em = entityManager.getEntityManager();
    	em.getTransaction().begin();
        em.merge(obj);
    	em.getTransaction().commit();
    }

    public void deleteObject(T obj) {
        EntityManager em = entityManager.getEntityManager();
        if (!em.contains(obj)) {
            em.getTransaction().begin();
            T target = em.merge(obj);
            em.remove(target);
            em.getTransaction().commit();
        }
    }
}
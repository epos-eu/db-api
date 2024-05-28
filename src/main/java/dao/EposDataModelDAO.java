package dao;

import model.Versioningstatus;
import org.epos.handler.dbapi.service.DBService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
        T objReturn = entityManager.getEntityManager().find(obj, instanceId);
        return objReturn==null? List.of() : List.of(objReturn);
    }

    public List<Versioningstatus> getVersionsFromDBByVersionId(String versionId){
        Versioningstatus objReturn = entityManager.getEntityManager().find(Versioningstatus.class, versionId);
        return objReturn==null? List.of() : List.of(objReturn);
    }

    public List<T> getAllFromDB(Class<T> obj){
        EntityManager em = entityManager.getEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("select c from "+obj.getName()+" c ");
        List resultList = q.getResultList();
        em.getTransaction().commit();
        return resultList;
    }

    public List<T> getOneFromDBByMetaId(String metaId, Class<T> obj){
        EntityManager em = entityManager.getEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("select c from "+obj.getName()+" c where c.meta_id = '"+metaId+"'");
        List resultList = q.getResultList();
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
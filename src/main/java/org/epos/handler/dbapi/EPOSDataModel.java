package org.epos.handler.dbapi;

import org.epos.eposdatamodel.EPOSDataModelEntity;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.dbapiimplementation.AbstractDBAPI;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * This interface consists exclusively of the declaration of all the methods which a need to be implemented for each
 * main EPOS Data Model Bean (which are a representation of the EPOS Data Model Entities though Java).
 * The methods allow this component to expose all the CRUD operation for each EPOS Data Model Bean.
 *
 * <p>The interface deals with abstract type of data, so every class which implements it needs to put as parameter the
 * right EPOS Data Model Bean which the class want deals with.
 *
 * <p>Each EPOS Data Model Bean has its own structure. To be able to have an abstract and common interface
 * some compromise have been made. Some EPOS Data Model Entities, as Service, can be identified through the identifier,
 * meanwhile others, like Distribution or DataProduct, can be identified with the UID. To overcome this issue,
 * in this interface is only used the word id, it's demanded to the implementing class to handle this heterogeneity.
 *
 * <p>This interface expose two main group of method: the first group have the method to do the all the different crud
 * CRUD operation, the second one instead
 *
 * <p>As implementation note of the first group: All the method are meant to be a single transaction. To allow the correct behaviour each
 * concrete implementation create a new Entity Manager at the beginning of the method and ensure that this is closed at
 * the end of the method.
 *
 * @param <T> The EPOS Data Model Bean
 */
public interface EPOSDataModel<T extends EPOSDataModelEntity> {

    /**
     * This method take care of an EPOS Data Model Bean and make sure that the object is persisted on the db.
     *
     * @param eposDataModelObject The EPOS Data Model Bean to be persisted
     */
    void save(T eposDataModelObject);

    /**
     * This method query the database and resemble an EPOS Data Model Bean starting from the business identifier.
     *
     * @param instanceId The Identifier of the EPOS Data Model Bean to be retrived
     * @return The EPOS Data Model Bean retrived.
     */
    T getByInstanceId(String instanceId);

    /**
     * This method return the whole collection of EPOS Data Model Beans for the specific implemented type.
     *
     * @return A List Collection of all the EPOS Data Model Beans retrived.
     */
    List<T> getAll();

    /**
     * This update method is meant to be a hard update, it substitutes a specific EPOS Data Model Bean with another
     * into the database.
     *
     * @param instanceId          The Identifier of the EPOS Data Model Bean to be updated
     * @param eposDataModelObject The EPOS Data Model Bean to be persisted
     */
    void hardUpdate(String instanceId, T eposDataModelObject);

    /**
     * This method remove from the database the whole EPOS Data Model Bean with the specified identifier
     *
     * @param instanceId The Identifier of the EPOS Data Model Bean to be deleted
     */
    void delete(String instanceId);


    //------------

    LinkedEntity save(T eposDataModelObject, EntityManager em);

    LinkedEntity save(T eposDataModelObject, EntityManager em, String instanceId);

    List<T> getByUid(String uid);

    List<T> getByUid(String uid, EntityManager em);

    T getByUidPublished(String uid);

    void updateStatus(String instanceId, State state);

    void updateStatus(String instanceId, State state, EntityManager em);

    void delete(String instanceId, EntityManager em);

    List<T> getByMetaId(String metaId);

    List<T> getByMetaId(String metaId, EntityManager em);

    List<T> getAllByState(State state);

    List<T> getAllByState(State state, EntityManager em);

    List<T> getAllByGroup(List<String> id);

    List<T> getAllByFileProvenance(String fileProvenance, EntityManager em);

    void hardUpdate(String instanceId, T eposDataModelObject, EntityManager em);

    T getByInstanceId(String instanceId, EntityManager em);
    void setMetadataMode(boolean metadataMode);

    List<T> getAll(EntityManager em);

}

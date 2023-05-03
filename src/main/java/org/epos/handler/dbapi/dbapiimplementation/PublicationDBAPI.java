package org.epos.handler.dbapi.dbapiimplementation;

import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.Publication;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.model.EDMPublication;

import javax.persistence.EntityManager;
import java.util.List;

public class PublicationDBAPI extends AbstractDBAPI<Publication> {
    public PublicationDBAPI() {
        super("publication", EDMPublication.class);
    }

    @Override
    public void save(Publication eposDataModelObject) {

    }

    @Override
    public LinkedEntity save(Publication eposDataModelObject, EntityManager em, String edmInstanceId) {
        return null;
    }

    @Override
    protected Publication mapFromDB(Object edmObject) {
        return null;
    }

    @Override
    public List<Publication> getByMetaId(String metaId) {
        return null;
    }

    @Override
    public List<Publication> getByUid(String uid) {
        return null;
    }

    @Override
    public Publication getByInstanceId(String instanceId, EntityManager em) {
        return null;
    }

    @Override
    public List<Publication> getAll() {
        return null;
    }

    @Override
    public List<Publication> getAllByState(State state) {
        return null;
    }


    @Override
    public void delete(String instanceId) {

    }

    @Override
    public void delete(String instanceId, EntityManager em) {

    }

    @Override
    public void updateStatus(String instanceId, State state, EntityManager em) {

    }
}

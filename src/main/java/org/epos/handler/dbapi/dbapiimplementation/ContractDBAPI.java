package org.epos.handler.dbapi.dbapiimplementation;

import org.epos.eposdatamodel.CategoryScheme;
import org.epos.eposdatamodel.Contract;
import org.epos.eposdatamodel.Group;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.model.*;
import org.epos.handler.dbapi.util.EDMUtil;
import org.epos.handler.dbapi.util.LoggerFormat;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.epos.handler.dbapi.util.DBUtil.getOneFromDB;

public class ContractDBAPI extends AbstractDBAPI<Contract> {


    public ContractDBAPI() {
        super("contract", EDMContract.class);
    }

    @Override
    public LinkedEntity save(Contract eposDataModelObject, EntityManager em, String edmInstanceId) {

        if (eposDataModelObject.getState().equals(State.PUBLISHED)
                && isAlreadyPublished(EDMContract.class, "contract.findByIdentifierAsUidAndState", em, eposDataModelObject))
            return new LinkedEntity();

        //search for a existing instance placeholder to be populated
        EDMContract edmObject = getOneFromDB(em, EDMContract.class,
                "contract.findByIdentifierAndState",
                "IDENTIFIER", eposDataModelObject.getIdentifier(),
                "STATE", State.PLACEHOLDER.toString());
        
        if(edmObject==null) {
			edmObject = getOneFromDB(em, EDMContract.class,
					"contract.findByInstanceId",
					"INSTANCEID", eposDataModelObject.getInstanceId());
		}

        //if there's a placeholder for the entity check if is passed a specific metaid
        //only if the metaid is the same of the placeholder merge the two (the placeholder and the passed entity)
        EDMEdmEntityId edmMetaId;
        if (edmObject != null &&
                (eposDataModelObject.getMetaId() == null || (eposDataModelObject.getMetaId() != null && eposDataModelObject.getMetaId().equals(edmObject.getMetaId())))) {
            em.merge(edmObject);
        } else {
            edmObject = new EDMContract();
            edmObject.setInstanceId(edmInstanceId);
            em.persist(edmObject);

            if (eposDataModelObject.getMetaId() == null) {
                edmMetaId = new EDMEdmEntityId();
                edmMetaId.setMetaId(UUID.randomUUID().toString());
                em.persist(edmMetaId);
            } else {
                edmMetaId = getOneFromDB(em, EDMEdmEntityId.class,
                        "edmentityid.findByMetaId",
                        "METAID", eposDataModelObject.getMetaId());
                if (edmMetaId == null) {
                    edmMetaId = new EDMEdmEntityId();
                    edmMetaId.setMetaId(eposDataModelObject.getMetaId());
                    em.persist(edmMetaId);
                }
            }
            edmObject.setEdmEntityIdByMetaId(edmMetaId);

        }
        edmObject.setIdentifier(eposDataModelObject.getIdentifier());
        edmObject.setStatus(eposDataModelObject.getStatus());
        edmObject.setName(eposDataModelObject.getName());
        edmObject.setType(eposDataModelObject.getType());

        if (Objects.nonNull(eposDataModelObject.getGroups())){
            for (Group group : eposDataModelObject.getGroups()){

                EDMGroup edmGroup =  getOneFromDB(em, EDMGroup.class, "group.findById",
                        "ID", group.getId());

                if (Objects.isNull(edmGroup)){
                    em.getTransaction().rollback();
                    throw new IllegalArgumentException(LoggerFormat.log(eposDataModelObject, "is involved in a non existing group"));
                }

                EDMAuthorization edmAuthorization = getOneFromDB(em, EDMAuthorization.class, "authorization.findByMetaIdAndGroupId",
                        "GROUPID", group.getId(),
                        "METAID", edmObject.getEdmEntityIdByMetaId().getMetaId());

                if (Objects.isNull(edmAuthorization)){
                    edmAuthorization = new EDMAuthorization();
                    edmAuthorization.setEdmEntityIdByMetaId(edmObject.getEdmEntityIdByMetaId());
                    edmAuthorization.setGroupByGroupId(edmGroup);
                    em.persist(edmAuthorization);
                }
            }
        }

        if (eposDataModelObject.getInstanceChangedId() != null) {
            EDMContract changedInstance = getOneFromDB(em, EDMContract.class, "contract.findByInstanceId",
                    "INSTANCEID", eposDataModelObject.getInstanceChangedId());
            if (changedInstance == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with identifier: " + edmObject.getIdentifier() + ", state: " + edmObject.getState()
                        + " and instanceId: " + edmObject.getInstanceId() + ", have an invalid 'InstanceChangedId'.");
            }
            edmObject.setContractByInstanceChangedId(changedInstance);
        }

        if (eposDataModelObject.getEditorId() == null) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with identifier: " + edmObject.getIdentifier() + ", state: " + edmObject.getState()
                    + " and instanceId: " + edmObject.getInstanceId() + ", doesn't have the editorid.");
        }
        EDMEdmEntityId edmMetaIdEditor = getOneFromDB(em, EDMEdmEntityId.class,
                "edmentityid.findByMetaId",
                "METAID", eposDataModelObject.getEditorId());

        if (edmMetaIdEditor == null) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with identifier: " + edmObject.getIdentifier() + ", state: " + eposDataModelObject.getState()
                    + " and instanceId: " + edmObject.getInstanceId() + ", the editor doesn't exist.");
        }

        edmObject.setChangeTimestamp(new Timestamp(System.currentTimeMillis()));
        edmObject.setOperation(eposDataModelObject.getOperation());
        edmObject.setChangeComment(eposDataModelObject.getChangeComment());
        edmObject.setVersion(eposDataModelObject.getVersion());
        edmObject.setState(eposDataModelObject.getState().toString());
        edmObject.setToBeDeleted(Boolean.valueOf(eposDataModelObject.getToBeDelete()));

        return new LinkedEntity().entityType(entityString)
                .instanceId(edmInstanceId)
                .metaId(edmObject.getEdmEntityIdByMetaId().getMetaId())
                .uid(eposDataModelObject.getUid());

    }

    @Override
    protected Contract mapFromDB(Object edmObject) {
        Contract o = new Contract();

        EDMContract edm = (EDMContract) edmObject;


        o.setInstanceId(edm.getInstanceId());
        o.setMetaId(edm.getMetaId());
        o.setState(State.valueOf(edm.getState()));
        o.setOperation(edm.getOperation());
        if (edm.getEdmEntityIdByEditorMetaId() != null ) {
            o.setEditorId(edm.getEdmEntityIdByEditorMetaId().getMetaId());
        }
        o.setVersion(edm.getVersion());
        o.setChangeTimestamp(
                edm.getChangeTimestamp() != null ? edm.getChangeTimestamp().toLocalDateTime() : null
        );
        o.setToBeDelete(edm.getToBeDeleted() != null ? edm.getToBeDeleted().toString() : "false");
        o.setInstanceChangedId(edm.getInstanceChangedId());
        o.setGroups(
                edm.getEdmEntityIdByMetaId() != null && edm.getEdmEntityIdByMetaId().getAuthorizationsByMetaId() != null ?
                        edm.getEdmEntityIdByMetaId().getAuthorizationsByMetaId().stream()
                                .map(EDMAuthorization::getGroupByGroupId)
                                .map(e -> {
                                    Group group = new Group();
                                    group.setName(e.getName());
                                    group.setDescription(e.getDescription());
                                    return group;
                                })
                                .collect(Collectors.toList())
                        : null
        );

        o.setIdentifier(edm.getIdentifier());
        o.setStatus(edm.getStatus());
        o.setName(edm.getName());
        o.setType(edm.getType());

        return o;
    }

}

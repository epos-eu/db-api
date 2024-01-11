package org.epos.handler.dbapi.dbapiimplementation;

import org.epos.eposdatamodel.*;
import org.epos.handler.dbapi.model.*;
import org.epos.handler.dbapi.util.EDMUtil;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.epos.handler.dbapi.util.DBUtil.getOneFromDB;

public class OperationDBAPI extends AbstractDBAPI<Operation> {

    public OperationDBAPI() {
        super("operation", EDMOperation.class);
    }

    @Override
    public LinkedEntity save(Operation eposDataModelObject, EntityManager em, String edmInstanceId) {
        if (eposDataModelObject.getState().equals(State.PUBLISHED)
                && isAlreadyPublished(EDMOperation.class, "operation.findByUidAndState", em, eposDataModelObject))
            return new LinkedEntity();

        //search for a existing instance placeholder to be populated
        EDMOperation edmObject = getOneFromDB(em, EDMOperation.class,
                "operation.findByUidAndState",
                "UID", eposDataModelObject.getUid(),
                "STATE", State.PLACEHOLDER.toString());
        
        if(edmObject==null) {
			edmObject = getOneFromDB(em, EDMOperation.class,
					"operation.findByInstanceId",
					"INSTANCEID", eposDataModelObject.getInstanceId());
		}

        //if there's a placeholder for the entity check if is passed a specific metaid
        //only if the metaid is the same of the placeholder merge the two (the placeholder and the passed entity)
        EDMEdmEntityId edmMetaId;

        boolean merged = false;

        if (edmObject != null &&
                (eposDataModelObject.getMetaId() == null || (eposDataModelObject.getMetaId() != null && eposDataModelObject.getMetaId().equals(edmObject.getMetaId())))) {
            //em.merge(edmObject);
            merged = true;
        } else {
            edmObject = new EDMOperation();
            edmObject.setInstanceId(edmInstanceId);
            //em.persist(edmObject);

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

        edmObject.setUid(eposDataModelObject.getUid() != null ? eposDataModelObject.getUid().replace("file:///app/", "") : null);

        if (eposDataModelObject.getInstanceChangedId() != null) {
            EDMOperation changedInstance = getOneFromDB(em, EDMOperation.class, "operation.findByInstanceId",
                    "INSTANCEID", eposDataModelObject.getInstanceChangedId());
            if (changedInstance == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + edmObject.getState()
                        + " and instanceId: " + edmObject.getInstanceId() + ", have an invalid 'InstanceChangedId'.");
            }
            edmObject.setOperationByInstanceChangedId(changedInstance);
        }

        if (eposDataModelObject.getEditorId() == null) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + edmObject.getState()
                    + " and instanceId: " + edmObject.getInstanceId() + ", doesn't have the editorid.");
        }
        EDMEdmEntityId edmMetaIdEditor = getOneFromDB(em, EDMEdmEntityId.class,
                "edmentityid.findByMetaId",
                "METAID", eposDataModelObject.getEditorId());

        if (edmMetaIdEditor == null) {
            em.getTransaction().rollback();
            throw new IllegalArgumentException("Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: " + edmObject.getUid() + ", state: " + eposDataModelObject.getState()
                    + " and instanceId: " + edmObject.getInstanceId() + ", the editor doesn't exist.");
        } else {
            edmObject.setEdmEntityIdByEditorMetaId(edmMetaIdEditor);
        }

        edmObject.setFileprovenance(eposDataModelObject.getFileProvenance());
        edmObject.setChangeTimestamp(new Timestamp(System.currentTimeMillis()));
        edmObject.setOperation(eposDataModelObject.getOperation());
        edmObject.setChangeComment(eposDataModelObject.getChangeComment());
        edmObject.setVersion(eposDataModelObject.getVersion());
        edmObject.setState(eposDataModelObject.getState().toString());
        edmObject.setToBeDeleted(Boolean.valueOf(eposDataModelObject.getToBeDelete()));

        if (!merged)
            em.persist(edmObject);


        if (eposDataModelObject.getMapping() != null) {
            edmObject.setMappingsByInstanceId(new ArrayList<>());
            for (Mapping mapping : eposDataModelObject.getMapping()) {
                EDMMapping edmMapping = new EDMMapping();

                edmMapping.setId(UUID.randomUUID().toString());

                edmMapping.setDefaultvalue(mapping.getDefaultValue());
                edmMapping.setLabel(mapping.getLabel());
                edmMapping.setMaxvalue(mapping.getMaxValue());
                edmMapping.setMinvalue(mapping.getMinValue());

                edmMapping.setMappingParamvaluesById(new LinkedList<>());

                if (mapping.getParamValue() != null) {
                    for (String param : mapping.getParamValue()) {
                        EDMMappingParamvalue edmMappingParamvalue = new EDMMappingParamvalue();
                        edmMappingParamvalue.setParamvalue(param);
                        edmMappingParamvalue.setId(UUID.randomUUID().toString());
                        edmMappingParamvalue.setMappingByInstanceMappingId(edmMapping);
                        edmMapping.getMappingParamvaluesById().add(edmMappingParamvalue);
                    }
                }

                edmMapping.setProperty(mapping.getProperty());
                edmMapping.setRange(mapping.getRange());
                edmMapping.setRequired(Boolean.valueOf(mapping.getRequired()));
                edmMapping.setValuepattern(mapping.getValuePattern());
                edmMapping.setVariable(mapping.getVariable());
                edmMapping.setReadOnlyValue(mapping.getReadOnlyValue());
                edmMapping.setMultipleValues(mapping.getMultipleValues());

                edmMapping.setOperationByIsmappingof(edmObject);

                edmObject.getMappingsByInstanceId().add(edmMapping);

            }
        }

        edmObject.setMethod(eposDataModelObject.getMethod());

        if (eposDataModelObject.getReturns() != null) {
            edmObject.setOperationReturnsByInstanceId(new ArrayList<>());
            for (String returnString : eposDataModelObject.getReturns()) {
                EDMOperationReturns edmReturns = new EDMOperationReturns();

                edmReturns.setId(UUID.randomUUID().toString());
                edmReturns.setReturns(returnString);
                edmReturns.setOperationByInstanceOperationId(edmObject);

                edmObject.getOperationReturnsByInstanceId().add(edmReturns);
            }
        }

        edmObject.setTemplate(eposDataModelObject.getTemplate());

        return new LinkedEntity().entityType(entityString)
                .instanceId(edmInstanceId)
                .metaId(edmObject.getEdmEntityIdByMetaId().getMetaId())
                .uid(eposDataModelObject.getUid());

    }

    @Override
    protected Operation mapFromDB(Object edmObject) {
        Operation o = new Operation();

        EDMOperation edm = (EDMOperation) edmObject;

        if (!metadataMode) {
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
            o.setChangeComment(edm.getChangeComment());
            o.setToBeDelete(edm.getToBeDeleted() != null ? edm.getToBeDeleted().toString() : "false");
            o.setInstanceChangedId(edm.getInstanceChangedId());
            o.setFileProvenance(edm.getFileprovenance());

            o.setGroups(
                    edm.getEdmEntityIdByMetaId() != null && edm.getEdmEntityIdByMetaId().getAuthorizationsByMetaId() != null ?
                            edm.getEdmEntityIdByMetaId().getAuthorizationsByMetaId().stream()
                                    .map(EDMAuthorization::getGroupByGroupId)
                                    .map(e -> {
                                        Group group = new Group();
                                        group.setName(e.getName());
                                        group.setDescription(e.getDescription());
                                        group.setId(e.getId());
                                        return group;
                                    })
                                    .collect(Collectors.toList())
                            : null
            );

            o.setWebservice(
                    edm.getSupportedOperationsByInstanceId() != null ?
                            edm.getSupportedOperationsByInstanceId().stream()
                                    .map(EDMSupportedOperation::getWebserviceByInstanceWebserviceId)
                                    .map(d -> new LinkedEntity()
                                            .uid(d.getUid())
                                            .instanceId(d.getInstanceId())
                                            .entityType("WebService")
                                            .metaId(d.getMetaId()))
                                    .collect(Collectors.toList()) : null
            );
        }

        o.setUid(edm.getUid());
        o.setMethod(edm.getMethod());

        o.setReturns(
                edm.getOperationReturnsByInstanceId() != null ?
                        edm.getOperationReturnsByInstanceId().stream()
                                .map(EDMOperationReturns::getReturns).collect(Collectors.toList())
                        : new ArrayList<>()
        );

        o.setTemplate(edm.getTemplate());

        o.setMapping(
                edm.getMappingsByInstanceId() != null ?
                        edm.getMappingsByInstanceId().stream().map(e -> {
                            Mapping mapping = new Mapping();

                            mapping.setLabel(e.getLabel());
                            mapping.setVariable(e.getVariable());
                            mapping.setRequired(String.valueOf(e.getRequired()));
                            mapping.setRange(e.getRange());
                            mapping.setDefaultValue(e.getDefaultvalue());
                            mapping.setMinValue(e.getMinvalue());
                            mapping.setMaxValue(e.getMaxvalue());
                            mapping.setReadOnlyValue(e.getReadOnlyValue());
                            mapping.setMultipleValues(e.getMultipleValues());

                            mapping.setParamValue(
                                    e.getMappingParamvaluesById() != null ?
                                            e.getMappingParamvaluesById().stream()
                                                    .map(EDMMappingParamvalue::getParamvalue).collect(Collectors.toList())
                                            : new ArrayList<>()
                            );

                            mapping.setProperty(e.getProperty());
                            mapping.setValuePattern(e.getValuepattern());

                            return mapping;
                        }).collect(Collectors.toList())
                        : new ArrayList<>()
        );


        return o;
    }
}

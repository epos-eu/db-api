package commonapis;

import dao.EposDataModelDAO;
import model.*;
import org.epos.eposdatamodel.EPOSDataModelEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

public class VersioningStatusAPI {

    public static Boolean updateStatus(String instanceId, StatusType status){
        List<Versioningstatus> returnList = getDbaccess().getOneFromDBByInstanceId(instanceId,Versioningstatus.class);
        returnList.get(0).setStatus(status);
        return getDbaccess().updateObject(returnList.get(0));
    }

    public static EPOSDataModelEntity checkVersion(EPOSDataModelEntity obj) {

        List<Versioningstatus> returnList = getDbaccess().getOneFromDB(
                Optional.ofNullable(obj.getInstanceId()).orElse(null),
                Optional.ofNullable(obj.getMetaId()).orElse(null),
                Optional.ofNullable(obj.getUid()).orElse(null),
                Optional.ofNullable(obj.getVersionId()).orElse(null),
                Versioningstatus.class
        );

        if(!returnList.isEmpty()){

            Versioningstatus edmobj = returnList.get(0);

            if(obj.getStatus()==null) obj.setStatus(StatusType.DRAFT);

            switch (obj.getStatus()){
                case DRAFT:
                    if(!edmobj.getStatus().equals(StatusType.DRAFT)) {
                        /**
                         * CREATING A NEW DRAFT FROM A NON-DRAFT SAVED ENTITY
                         *
                         * STATUS --> NEW STATUS
                         * InstanceChangeId --> OLD InstanceId
                         * InstanceId --> NEW InstanceId
                         * VersionId --> NEW VersionId
                         */
                        edmobj.setStatus(obj.getStatus());

                        edmobj.setInstanceChangeId(edmobj.getInstanceId());
                        obj.setInstanceChangedId(edmobj.getInstanceId());

                        edmobj.setInstanceId(UUID.randomUUID().toString());
                        obj.setInstanceId(edmobj.getInstanceId());

                        edmobj.setVersionId(UUID.randomUUID().toString());
                        obj.setVersionId(edmobj.getVersionId());
                    } else{
                        /**
                         * UPDATING A DRAFT FROM A DRAFT SAVED ENTITY
                         *
                         * STATUS --> OLD STATUS
                         * InstanceChangeId --> OLD InstanceChangeId if not null
                         * InstanceId --> OLD InstanceId
                         * VersionId --> OLD VersionId
                         */
                        edmobj.setStatus(obj.getStatus());

                        obj.setInstanceChangedId(Optional.ofNullable(edmobj.getInstanceChangeId()).orElse(null));

                        obj.setInstanceId(edmobj.getInstanceId());

                        obj.setVersionId(edmobj.getVersionId());
                    }
                    break;
                case ARCHIVED:
                    if(edmobj.getStatus().equals(StatusType.DRAFT))
                        edmobj.setStatus(obj.getStatus());
                    if(edmobj.getStatus().equals(StatusType.PUBLISHED))
                        edmobj.setStatus(obj.getStatus());
                    if(edmobj.getStatus().equals(StatusType.SUBMITTED))
                        edmobj.setStatus(obj.getStatus());
                    if(edmobj.getStatus().equals(StatusType.DISCARDED))
                        edmobj.setStatus(obj.getStatus());
                case DISCARDED:
                    if(edmobj.getStatus().equals(StatusType.DRAFT))
                        edmobj.setStatus(obj.getStatus());
                    if(edmobj.getStatus().equals(StatusType.PUBLISHED))
                        edmobj.setStatus(obj.getStatus());
                    if(edmobj.getStatus().equals(StatusType.SUBMITTED))
                        edmobj.setStatus(obj.getStatus());
                    if(edmobj.getStatus().equals(StatusType.ARCHIVED))
                        edmobj.setStatus(obj.getStatus());
                case PUBLISHED:
                    if(edmobj.getStatus().equals(StatusType.SUBMITTED) ) {
                        edmobj.setStatus(obj.getStatus());
                    }
                    break;
                case SUBMITTED:
                    if(edmobj.getStatus().equals(StatusType.DRAFT))
                        edmobj.setStatus(obj.getStatus());
                    if(edmobj.getStatus().equals(StatusType.PUBLISHED))
                        edmobj.setStatus(obj.getStatus());
                    if(edmobj.getStatus().equals(StatusType.DISCARDED))
                        edmobj.setStatus(obj.getStatus());
                    if(edmobj.getStatus().equals(StatusType.ARCHIVED))
                        edmobj.setStatus(obj.getStatus());
                    break;
            }

            getDbaccess().updateObject(edmobj);

            return obj;
        } else {
            /**
             *
             * CREATING A NEW VERSIONING STATUS ENTITY
             *
             */
            Versioningstatus edmobj = new Versioningstatus();
            if(obj.getStatus()!=null) edmobj.setStatus(obj.getStatus());
            else edmobj.setStatus(StatusType.DRAFT);
            edmobj.setInstanceId(UUID.randomUUID().toString());
            obj.setInstanceId(edmobj.getInstanceId());
            edmobj.setMetaId(UUID.randomUUID().toString());
            obj.setMetaId(edmobj.getMetaId());
            edmobj.setVersionId(UUID.randomUUID().toString());
            obj.setVersionId(edmobj.getVersionId());
            edmobj.setUid(obj.getUid());
            edmobj.setInstanceChangeId(null);
            edmobj.setChangeTimestamp(Timestamp.from(Instant.now()));
            edmobj.setChangeComment(Optional.ofNullable(obj.getChangeComment()).orElse(null));
            edmobj.setEditorId(Optional.ofNullable(obj.getEditorId()).orElse(null));
            edmobj.setProvenance(Optional.ofNullable(obj.getFileProvenance()).orElse(null));
            edmobj.setVersion(Optional.ofNullable(obj.getVersion()).orElse(null));

            getDbaccess().updateObject(edmobj);

            return obj;
        }
    }

    private static EposDataModelDAO<Versioningstatus> getDbaccess() {
        return new EposDataModelDAO();
    }

    public static EPOSDataModelEntity updateVersion(EPOSDataModelEntity obj, Versioningstatus vs) {

        vs.setChangeComment(obj.getChangeComment());
        vs.setChangeTimestamp(Timestamp.from(Instant.now()));
        vs.setChangeComment(Optional.ofNullable(obj.getChangeComment()).orElse(null));
        vs.setEditorId(Optional.ofNullable(obj.getEditorId()).orElse(null));
        vs.setProvenance(Optional.ofNullable(obj.getFileProvenance()).orElse(null));
        vs.setVersion(Optional.ofNullable(obj.getVersion()).orElse(null));

        getDbaccess().updateObject(vs);

        return obj;
    }

    public static EPOSDataModelEntity retrieveVersion(EPOSDataModelEntity obj) {

        List<Versioningstatus> returnList = getDbaccess().getOneFromDB(
                Optional.ofNullable(obj.getInstanceId()).orElse(null),
                Optional.ofNullable(obj.getMetaId()).orElse(null),
                Optional.ofNullable(obj.getUid()).orElse(null),
                Optional.ofNullable(obj.getVersionId()).orElse(null),
                Versioningstatus.class
        );

        if(returnList.isEmpty()) return null;

        Versioningstatus vs = returnList.get(0);

        obj.setChangeComment(vs.getChangeComment());
        obj.setChangeTimestamp(vs.getChangeTimestamp().toLocalDateTime());
        obj.setChangeComment(Optional.ofNullable(vs.getChangeComment()).orElse(null));
        obj.setEditorId(Optional.ofNullable(vs.getEditorId()).orElse(null));
        obj.setFileProvenance(Optional.ofNullable(vs.getProvenance()).orElse(null));
        obj.setVersion(Optional.ofNullable(vs.getVersion()).orElse(null));
        obj.setStatus(vs.getStatus());

        getDbaccess().updateObject(vs);

        return obj;
    }

}

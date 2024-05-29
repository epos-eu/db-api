package apis;

import dao.EposDataModelDAO;
import model.*;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.epos.eposdatamodel.EPOSDataModelEntity;
import org.epos.eposdatamodel.LinkedEntity;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

public class VersioningStatusAPI {

    public static void updateStatus(String instanceId, StatusType status){
        List<Versioningstatus> returnList = getDbaccess().getOneFromDBByInstanceId(instanceId,Versioningstatus.class);
        returnList.get(0).setStatus(status);
        getDbaccess().updateObject(returnList.get(0));
    }

    public static EPOSDataModelEntity checkVersion(EPOSDataModelEntity obj) {

        List<Versioningstatus> returnList = getDbaccess().getOneFromDB(
                Optional.ofNullable(obj.getInstanceId()).orElse(null),
                Optional.ofNullable(obj.getMetaId()).orElse(null),
                null,
                Optional.ofNullable(obj.getVersionId()).orElse(null),
                Versioningstatus.class
        );

        if(!returnList.isEmpty()){
            Versioningstatus edmobj = returnList.get(0);
            if(obj.getStatus()==null) obj.setStatus(StatusType.DRAFT);
            switch (edmobj.getStatus()){
                case DRAFT:
                    if(!obj.getStatus().equals(StatusType.DRAFT)) {
                        edmobj.setStatus(obj.getStatus());
                        edmobj.setInstanceChangeId(edmobj.getInstanceId());
                        obj.setInstanceChangedId(edmobj.getInstanceId());
                        edmobj.setInstanceId(UUID.randomUUID().toString());
                        obj.setInstanceId(edmobj.getInstanceId());
                        edmobj.setVersionId(UUID.randomUUID().toString());
                        obj.setVersionId(edmobj.getVersionId());
                    }
                    break;
                case ARCHIVED:
                case DISCARDED:
                case PUBLISHED:
                    if(obj.getStatus().equals(StatusType.DRAFT)) {
                        edmobj.setStatus(obj.getStatus());
                        edmobj.setInstanceChangeId(edmobj.getInstanceId());
                        obj.setInstanceChangedId(edmobj.getInstanceId());
                        edmobj.setInstanceId(UUID.randomUUID().toString());
                        obj.setInstanceId(edmobj.getInstanceId());
                        edmobj.setVersionId(UUID.randomUUID().toString());
                        obj.setVersionId(edmobj.getVersionId());
                    }
                    break;
                case SUBMITTED:
                    if(obj.getStatus().equals(StatusType.DRAFT))
                        edmobj.setStatus(obj.getStatus());
                    if(obj.getStatus().equals(StatusType.PUBLISHED))
                        edmobj.setStatus(obj.getStatus());
                    if(obj.getStatus().equals(StatusType.DISCARDED))
                        edmobj.setStatus(obj.getStatus());
                    if(obj.getStatus().equals(StatusType.ARCHIVED))
                        edmobj.setStatus(obj.getStatus());
                    break;
            }
            return updateVersion(obj, edmobj);
        } else {
            Versioningstatus edmobj = new Versioningstatus();
            if(obj.getStatus()!=null) edmobj.setStatus(obj.getStatus());
            else edmobj.setStatus(StatusType.DRAFT);
            edmobj.setInstanceId(UUID.randomUUID().toString());
            obj.setInstanceId(edmobj.getInstanceId());
            edmobj.setMetaId(UUID.randomUUID().toString());
            obj.setMetaId(edmobj.getMetaId());
            edmobj.setVersionId(UUID.randomUUID().toString());
            obj.setVersionId(edmobj.getVersionId());
            edmobj.setInstanceChangeId(null);
            edmobj.setChangeTimestamp(Timestamp.from(Instant.now()));
            edmobj.setChangeComment(Optional.ofNullable(obj.getChangeComment()).orElse(null));
            edmobj.setEditorId(Optional.ofNullable(obj.getEditorId()).orElse(null));
            edmobj.setProvenance(Optional.ofNullable(obj.getFileProvenance()).orElse(null));
            edmobj.setVersion(Optional.ofNullable(obj.getVersion()).orElse(null));

            getDbaccess().createObject(edmobj);

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

}

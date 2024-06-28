package commonapis;

import dao.EposDataModelDAO;
import model.EdmEntityId;
import model.StatusType;
import model.Versioningstatus;
import org.epos.eposdatamodel.EPOSDataModelEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EposDataModelEntityIDAPI {

    private static EposDataModelDAO<EdmEntityId> getDbaccess() {
        return new EposDataModelDAO();
    }

    public static Boolean addEntityToEDMEntityID(String metaId, String entityName){
        EdmEntityId edmEntityId = new EdmEntityId();
        edmEntityId.setMetaId(metaId);
        edmEntityId.setTableName(entityName);

        return getDbaccess().updateObject(edmEntityId);
    }

    public static Boolean removeEntityToEDMEntityID(String metaId, String entityName){
        
        EdmEntityId edmEntityId = new EdmEntityId();
        edmEntityId.setMetaId(metaId);
        edmEntityId.setTableName(entityName);

        return getDbaccess().deleteObject(edmEntityId);
    }



}

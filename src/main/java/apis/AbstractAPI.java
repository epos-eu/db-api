package apis;

import dao.EposDataModelDAO;
import model.StatusType;
import model.Versioningstatus;
import org.epos.eposdatamodel.EPOSDataModelEntity;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.List;

public abstract class AbstractAPI<T> {

    protected Class<?> edmClass;
    protected String entityName;

    protected EposDataModelDAO dbaccess = new EposDataModelDAO();

    public AbstractAPI(String entityName, Class<?> edmClass){
        this.edmClass = edmClass;
        this.entityName = entityName;
    }

    public EposDataModelDAO getDbaccess(){
        return dbaccess;
    }

    public void setEdmClass(Class<?> edmClass){
        this.edmClass = edmClass;
    }

    public Class<?> getEdmClass(){
        return edmClass;
    }

    public void setEntityName(String entityName){
        this.entityName = entityName;
    }

    public  String getEntityName() {
        return entityName;
    }

    public abstract LinkedEntity create(T obj);

    public abstract T retrieve(String instanceId);

    public void checkVersioningStatus(EPOSDataModelEntity obj){

    }

    public void updateVersioningStatus(String versionInfo, StatusType status){
        List<Versioningstatus> returnList = getDbaccess().getVersionsFromDBByVersionId(versionInfo);
    }


}

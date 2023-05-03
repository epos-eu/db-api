package org.epos.handler.dbapi.dbapiimplementation;

import org.epos.eposdatamodel.EPOSDataModelEntity;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.EPOSDataModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Deprecated(forRemoval = true)
@SuppressWarnings("unchecked")
public class EPOSDataModelDBAPI {

    private EPOSDataModelDBAPI() {
    }

    public static void save(EPOSDataModelEntity eposDataModel) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        String objectName = eposDataModel.getClass().getSimpleName();
        EPOSDataModel implementationDBAPI = getImplementationDBAPI(objectName);
        implementationDBAPI.save(eposDataModel);

    }

    public static <T extends EPOSDataModelEntity> T get(String uid, Class<T> classObject) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        EPOSDataModel implementationDBAPI = getImplementationDBAPI(classObject.getSimpleName());

        List<T> collect = ((List<T>) implementationDBAPI.getByUid(uid)).stream()
                .filter(i -> i.getState().equals(State.PUBLISHED))
                .collect(Collectors.toList());

        return !collect.isEmpty() ? collect.get(0) : null;
    }

    public static <T> List<T> getAll(Class<T> classObject) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        EPOSDataModel implementationDBAPI = getImplementationDBAPI(classObject.getSimpleName());

        return (List<T>) implementationDBAPI.getAllByState(State.PUBLISHED);
    }


    public static void saveAll(Collection<EPOSDataModelEntity> eposDataModelList) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (EPOSDataModelEntity o : eposDataModelList) {
            save(o);
        }
    }

    public static <T> void delete(String uid, Class<T> classObject) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        EPOSDataModel implementationDBAPI = getImplementationDBAPI(classObject.getSimpleName());

        implementationDBAPI.delete(uid);

    }

    public static <T extends EPOSDataModelEntity> void update(String id, Object eposDataModel) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        String objectName = eposDataModel.getClass().getSimpleName();
        EPOSDataModel implementationDBAPI = getImplementationDBAPI(objectName);

        implementationDBAPI.hardUpdate(id, (EPOSDataModelEntity) eposDataModel);
    }


    private static EPOSDataModel<? extends EPOSDataModelEntity> getImplementationDBAPI(String simpleName) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String classNameDBAPI = "org.epos.handler.dbapi.dbapiimplementation." + simpleName + "DBAPI";

        Class<?> classDBAPI = Class.forName(classNameDBAPI);
        Constructor<?> ctorDBAPI = classDBAPI.getConstructor();
        return (EPOSDataModel<? extends EPOSDataModelEntity>) ctorDBAPI.newInstance();
    }
}

package org.epos.handler.dbapi.util;

import org.epos.eposdatamodel.EPOSDataModelEntity;

public class LoggerFormat {
    public static String log(EPOSDataModelEntity eposDataModelObject, String message) {
        return "Entity [" + eposDataModelObject.getClass().getSimpleName() + "] with uid: "
                + eposDataModelObject.getUid() + " and instanceId: " + eposDataModelObject.getInstanceId() + ", " + message + ".";
    }
}

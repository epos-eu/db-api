package org.epos.handler.dbapi.util;

import org.epos.handler.dbapi.DBAPIClient;
import org.epos.handler.dbapi.EPOSDataModel;

public class LoadCache {

    public static void loadCache() {
        DBAPIClient.DBAPI.values().forEach(EPOSDataModel::getAll);
    }

}

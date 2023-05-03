package org.epos.handler.dbapi;

import org.epos.eposdatamodel.EPOSDataModelEntity;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.List;

public interface DBAPIClientInterface {

    void rollbackTransaction();

    void startTransaction();

    void closeTransaction(boolean toBeSaved);

    <T extends EPOSDataModelEntity> List<T> retrieve(Class<T> clazz, DBAPIClient.GetQuery query);

    <T extends EPOSDataModelEntity> LinkedEntity create(EPOSDataModelEntity instance);

    <T extends EPOSDataModelEntity> void update(EPOSDataModelEntity instance, DBAPIClient.UpdateQuery query);
}

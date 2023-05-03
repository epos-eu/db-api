package org.epos.handler.dbapi.service;


import org.epos.handler.dbapi.EntityManagerHandler;

import javax.persistence.EntityManager;

public class DBService implements EntityManagerHandler {
    public DBService() {
    }

    public EntityManager getEntityManager() {
        return EntityManagerFactoryProvider.getInstance().createEntityManager();
    }

}

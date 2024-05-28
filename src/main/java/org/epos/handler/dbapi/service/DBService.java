package org.epos.handler.dbapi.service;


import jakarta.persistence.EntityManager;

public class DBService implements EntityManagerHandler {
    public DBService() {
    }

    public EntityManager getEntityManager() {
        return EntityManagerFactoryProvider.getInstance().createEntityManager();
    }

}

package org.epos.handler.dbapi.util;

import com.zaxxer.hikari.pool.HikariPool;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.epos.handler.dbapi.service.DBService;
import org.postgresql.util.PSQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.net.ConnectException;

public class HealtCheck {
    public static boolean isConnectedToDatabase() {
        try {
            throwing();
            EntityManager em = new DBService().getEntityManager();
            Query query = em.createNativeQuery("SELECT 1;");
            try {
                throwing();
                query.getResultList();
            } catch (PSQLException | DatabaseException | ConnectException | PersistenceException | HikariPool.PoolInitializationException e) {
                return false;
            } finally {
                em.close();
            }
        } catch (PSQLException | DatabaseException | ConnectException | PersistenceException | HikariPool.PoolInitializationException e) {
            return false;
        }
        return true;
    }

    private static void throwing() throws PSQLException, ConnectException {
    }
}

package org.epos.handler.dbapi.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

public class DBUtil {

    public static <T> List<T> getFromDB(EntityManager em, Class<T> clazz, String queryName, String parameterName1, Object item1,
                                        String parameterName2, Object item2) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put(parameterName1, item1);
        parameter.put(parameterName2, item2);
        return getFromDB(em, clazz, queryName, parameter);
    }

    public static <T> List<T> getFromDB(EntityManager em, Class<T> clazz, String queryName, String parameterName1, Object item1,
                                        String parameterName2, Object item2, String parameterName3, Object item3) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put(parameterName1, item1);
        parameter.put(parameterName2, item2);
        parameter.put(parameterName3, item3);
        return getFromDB(em, clazz, queryName, parameter);
    }

    public static <T> List<T> getFromDB(EntityManager em, Class<T> clazz, String queryName, String parameterName, Object item) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put(parameterName, item);
        return getFromDB(em, clazz, queryName, parameter);
    }

    public static <T> List<T> getFromDB(EntityManager em, Class<T> clazz, String queryName) {
        HashMap<String, Object> parameter = new HashMap<>();
        return getFromDB(em, clazz, queryName, parameter);
    }

    public static <T> T getOneFromDB(EntityManager em, Class<T> clazz, String namedQuery, String paramName, Object param) {
        List<T> list = getFromDB(em, clazz, namedQuery, paramName, param);
        return list.isEmpty() ? null : list.get(0);
    }

    public static <T> T getOneFromDB(EntityManager em, Class<T> clazz, String namedQuery, String paramName1, Object param1,
                                     String paramName2, Object param2) {
        List<T> list = getFromDB(em, clazz, namedQuery, paramName1, param1, paramName2, param2);
        return list.isEmpty() ? null : list.get(0);
    }

    public static <T> T getOneFromDB(EntityManager em, Class<T> clazz, String namedQuery, String paramName1, Object param1,
                                     String paramName2, Object param2, String paramName3, Object param3) {
        List<T> list = getFromDB(em, clazz, namedQuery, paramName1, param1, paramName2, param2, paramName3, param3);
        return list.isEmpty() ? null : list.get(0);
    }

    @SuppressWarnings("unchecked")
    private static <T> List<T> getFromDB(EntityManager em, Class<T> clazz, String queryName, HashMap<String, Object> parameter) {

        Query qry = em.createNamedQuery(queryName);
        for (String key : parameter.keySet()) {
            qry.setParameter(key, parameter.get(key));
        }

        return (List<T>) qry.getResultList();
    }
}

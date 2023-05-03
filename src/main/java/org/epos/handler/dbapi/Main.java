package org.epos.handler.dbapi;

import org.epos.eposdatamodel.Operation;
import org.epos.eposdatamodel.Person;
import org.epos.handler.dbapi.dbapiimplementation.AbstractDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.OperationDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.PersonDBAPI;

public class Main {

    public static void main(String[] args) {
        AbstractDBAPI<Operation> dbapi1 = new OperationDBAPI();
        AbstractDBAPI<Person> dbapi2 = new PersonDBAPI();
        //dbapi1.setMetadataMode(false);
        //dbapi2.setMetadataMode(false);
        long startTime = System.currentTimeMillis();

        //loadCache();
        //System.out.println(dbapi1.getAll());
        dbapi1.getAll();//.forEach(System.out::println);

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println(duration);

        long startTime1 = System.currentTimeMillis();

        //loadCache();
        dbapi1.getAll();

        long endTime1 = System.currentTimeMillis();
        long duration1 = (endTime1 - startTime1);
        System.out.println(duration1);
    }
}

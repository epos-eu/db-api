package org.epos.handler.dbapi.mastertable;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.epos.eposdatamodel.*;
import org.epos.handler.dbapi.DBAPIClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] argc) throws IOException {
        //load master table excel file
        FileInputStream file = new FileInputStream("/Users/andreaorfino/Documents/dbapiTest/db-api/src/main/resources/DDSS_master_table_v6.03_20220818.xlsx");
        Workbook workbook = new XSSFWorkbook(file);

        //get the first sheet DDSS_master_table
        String sheetName = workbook.getSheetName(0);
        Sheet sheet = workbook.getSheet(sheetName);

        //iterate on every line (it exit with an exception when the line is empty)
        //it start from the 5th line to jump the headert
        for (int j = 4 ; j < sheet.getLastRowNum() ; j++) {
            Row row = sheet.getRow(j);

            //create a list to store all the entities which need to be saved
            List<EPOSDataModelEntity> allRow = new ArrayList<>();

            //start with the dataProduct
            DataProduct dataProduct = new DataProduct();
            allRow.add(dataProduct);

            Identifier identifier = new Identifier();
            identifier.setType("DDSS-ID");
            String ddssid = getStringCellValue(row, 0);
            identifier.setIdentifier(ddssid);
            dataProduct.setIdentifier(List.of(identifier));

            dataProduct.setTitle(List.of(getStringCellValue(row, 6)));
            dataProduct.setType(getStringCellValue(row, 7));
            dataProduct.setDocumentation(getStringCellValue(row, 60));
            dataProduct.setQualityAssurance(getStringCellValue(row, 62));
            dataProduct.setUid(ddssid);
            dataProduct.setContactPoint(new ArrayList<>());


            String stringCellValueContactPoint1 = getStringCellValue(row, 3);
            dataProduct.getContactPoint().add(new LinkedEntity().uid(parseContactPoint(allRow, stringCellValueContactPoint1).getUid()));

            String contactPointsName = getStringCellValue(row, 50);
            String contactPointsEmail = getStringCellValue(row, 51);
            if (Objects.nonNull(contactPointsName) && Objects.nonNull(contactPointsEmail)
                && !contactPointsEmail.isBlank() && !contactPointsName.isBlank()){
                List<String> contactPointsNameEntries;
                List<String> contactPointsEmailEntries;
                if(contactPointsName.contains("/") && !contactPointsName.contains(",")){
                    contactPointsNameEntries = List.of(contactPointsName.split("/"));
                    contactPointsEmailEntries = List.of(contactPointsEmail.split("/"));
                } else {
                    contactPointsNameEntries = List.of(contactPointsName.split(", "));
                    contactPointsEmailEntries = List.of(contactPointsEmail.split(", "));
                }

                for (int i = 0 ; i < contactPointsEmailEntries.size() && i < contactPointsNameEntries.size() ; i++ ){
                    String buildedContactPointString = contactPointsNameEntries.get(i).trim() + " <" + contactPointsEmailEntries.get(i).trim() + ">";
                    ContactPoint contactPoint = parseContactPoint(allRow, buildedContactPointString);
                    if (contactPoint!= null) {
                        dataProduct.getContactPoint().add(new LinkedEntity().uid(contactPoint.getUid()));
                    }
                }
            }


            Organization organization = new Organization();
            String acronym = getStringCellValue(row, 2);
            organization.setAcronym(acronym);
            organization.setUid(acronym);
            dataProduct.setPublisher(new ArrayList<>());
            allRow.add(organization);

            for (int i = 0 ; i < 13 ; i++) {
                String orgString = getStringCellValue(row, i * 3 + 11);
                if (orgString != null && !orgString.isBlank()){

                Organization subOrganization = new Organization();
                allRow.add(subOrganization);

                subOrganization.setUid(orgString);
                subOrganization.setAcronym(orgString);

                String orgCountry = getStringCellValue(row, i * 3 + 12);
                subOrganization.setAddress(new Address().countryCode(orgCountry));

                String orgUrl = getStringCellValue(row, i * 3 + 13);
                subOrganization.setURL(orgUrl);

                dataProduct.getPublisher().add(
                        new LinkedEntity()
                                .uid(subOrganization.getUid())
                                .entityType("Organization")
                );
                //subOrganization.setMemberOf(List.of(new LinkedEntity().uid(organization.getUid())));
                }

            }

            String statusDataProductImplementationStatus = getStringCellValue(row, 56);
            if(statusDataProductImplementationStatus != null && !statusDataProductImplementationStatus.isBlank()){
                DataProductImplementationStatus dataProductImplementationStatus = new DataProductImplementationStatus();
                allRow.add(dataProductImplementationStatus);

                dataProductImplementationStatus.setStatus(statusDataProductImplementationStatus);
                dataProductImplementationStatus.setDataProduct(dataProduct.getUid());
                dataProductImplementationStatus.setDataProvider(organization.getUid());

            }

            Distribution distribution = new Distribution();
            allRow.add(distribution);

            distribution.setUid(dataProduct.getUid() + "_distribution");
            distribution.setConformsTo(getStringCellValue(row, 58));
            distribution.setFormat(getStringCellValue(row, 59));
            distribution.setDataPolicy(getStringCellValue(row, 61));
            distribution.setLicence(getStringCellValue(row, 63));

            dataProduct.setDistribution(List.of(new LinkedEntity().uid(distribution.getUid())));


            WebService webService = new WebService();
            allRow.add(webService);

            webService.setUid(dataProduct.getUid() + "_webservice");

            webService.setAaaiTypes(getStringCellValue(row, 66));
            ArrayList<Documentation> documentations = new ArrayList<>();
            documentations.add(new Documentation().URI(getStringCellValue(row, 64)));
            webService.setDocumentation(documentations);

            distribution.setAccessService(new LinkedEntity().uid(webService.getUid()));

            DBAPIClient dbapi = new DBAPIClient();

            allRow.forEach( e -> {
                e.setEditorId("backoffice");
                e.setState(State.PUBLISHED);
                e.setFileProvenance("Master Table");
                dbapi.create(e);
            });

            System.out.println(j);

        }



    }

    private static ContactPoint parseContactPoint(List<EPOSDataModelEntity> allRow, String stringCellValueContactPoint) {
        ContactPoint contactPoint = new ContactPoint();
        allRow.add(contactPoint);

        if (stringCellValueContactPoint != null && !stringCellValueContactPoint.isBlank()) {
            Person person = new Person();
            allRow.add(person);
            person.setFamilyName("");
            List<String> s = List.of(stringCellValueContactPoint.split(" "));
            for (int i = 0; i < s.size(); i++) {
                String elem = s.get(i);
                if (elem.contains("<") && elem.contains(">")) {
                    String email = elem.replace("<", "").replace(">", "");
                    person.setEmail(List.of(email));
                    contactPoint.setEmail(List.of(email));
                    person.setUid(email);
                    contactPoint.setUid(email);
                } else if (i > 0) {
                    person.setFamilyName(person.getFamilyName() + " " + elem);
                }
            }
            if(contactPoint.getUid() != null){
                contactPoint.setUid(UUID.randomUUID().toString());
            }
            person.setGivenName(s.get(0));
            contactPoint.setPerson(new LinkedEntity().uid(person.getUid()));
            return contactPoint;
        } else { return null; }
    }

    private static String getStringCellValue(Row row, int i) {
        try{
            return row.getCell(i).getStringCellValue();
        } catch (IllegalStateException ignored){
            return String.valueOf(row.getCell(i).getBooleanCellValue());
        }
    }


}

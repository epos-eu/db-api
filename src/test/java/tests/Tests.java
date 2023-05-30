package tests;

import org.epos.eposdatamodel.Category;
import org.epos.eposdatamodel.CategoryScheme;
import org.epos.eposdatamodel.DataProduct;
import org.epos.eposdatamodel.Identifier;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.dbapiimplementation.CategoryDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.CategorySchemeDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.DataProductDBAPI;


public class Tests {
	
	public static void main(String[] args) {
		DataProduct dataproductTest = new DataProduct();
		dataproductTest.setUid("TEST");
		dataproductTest.setState(State.DRAFT);
		dataproductTest.setEditorId("test");
		Identifier identifier = new Identifier();
		identifier.setIdentifier("test");
		identifier.setType("test");
		dataproductTest.addIdentifier(identifier);
		DataProductDBAPI dbapi = new DataProductDBAPI();
		
		CategoryScheme scheme = new CategoryScheme();
		scheme.setTitle("updatetest");
		scheme.setDescription("test");
		scheme.setUid("test");
		
		CategorySchemeDBAPI cs = new CategorySchemeDBAPI();
		cs.save(scheme);
		
		Category cat = new Category();
		cat.setName("test");
		cat.setDescription("test");
		cat.setInScheme("21fbf1d5-738a-4199-9235-1cfa7bfbeaf4");
		cat.setUid("test");
		CategoryDBAPI cats = new CategoryDBAPI();
		cats.save(cat);
		
		dataproductTest.addCategory("21fbf1d5-738a-4199-9235-1cfa7bfbeaf4");

		dbapi.save(dataproductTest);

	}

}

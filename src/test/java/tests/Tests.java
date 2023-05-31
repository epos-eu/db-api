package tests;

import java.util.ArrayList;

import org.epos.eposdatamodel.Category;
import org.epos.eposdatamodel.CategoryScheme;
import org.epos.eposdatamodel.DataProduct;
import org.epos.eposdatamodel.Identifier;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.dbapiimplementation.CategoryDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.CategorySchemeDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.DataProductDBAPI;
import org.epos.handler.dbapi.model.EDMIspartofCategory;


public class Tests {
	
	public static void main(String[] args) {
		
		CategoryScheme seismologyScheme = new CategoryScheme();
		seismologyScheme.setTitle("Seismology");
		seismologyScheme.setDescription("It contains the concepts of Seismology");
		seismologyScheme.setUid("epos:Seismology");
		
		Category products = new Category();
		products.setUid("epos:Seismologicalproducts");
		
		
		/*DataProduct dataproductTest = new DataProduct();
		dataproductTest.setUid("TEST");
		dataproductTest.setState(State.DRAFT);
		dataproductTest.setEditorId("test");
		Identifier identifier = new Identifier();
		identifier.setIdentifier("test");
		identifier.setType("test");
		dataproductTest.addIdentifier(identifier);
		DataProductDBAPI dbapi = new DataProductDBAPI();*/
		
		Category cat = new Category();
		cat.setName("ciao");
		cat.setDescription("ciao");
		cat.setInScheme("test");
		cat.setUid("ciao");
		Category cat2 = new Category();
		cat2.setName("ciao2");
		cat2.setDescription("ciao2");
		cat2.setInScheme("test");
		cat2.setUid("ciao2");
		ArrayList<String> narrowers = new ArrayList<>();
		narrowers.add("ciao2");
		cat.setNarrower(narrowers);
		cat2.setBroader("ciao");
		CategoryDBAPI cats = new CategoryDBAPI();
		cats.save(cat);
		cats.save(cat2);
		
		Category cat3 = new Category();
		cat3.setName("ciao3");
		cat3.setDescription("ciao3");
		cat3.setInScheme("test");
		cat3.setUid("ciao");
		cats.save(cat3);
		
		
		CategoryScheme scheme = new CategoryScheme();
		scheme.setTitle("updatetest");
		scheme.setDescription("test");
		scheme.setUid("test");
		
		CategorySchemeDBAPI cs = new CategorySchemeDBAPI();
		cs.save(scheme);
		
		//dataproductTest.addCategory("21fbf1d5-738a-4199-9235-1cfa7bfbeaf4");

		//dbapi.save(dataproductTest);

	}

}

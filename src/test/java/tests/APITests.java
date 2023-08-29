package tests;

import static org.epos.eposdatamodel.State.DRAFT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.epos.eposdatamodel.Category;
import org.epos.eposdatamodel.CategoryScheme;
import org.epos.eposdatamodel.ContactPoint;
import org.epos.eposdatamodel.DataProduct;
import org.epos.eposdatamodel.Distribution;
import org.epos.eposdatamodel.Identifier;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.Person;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.DBAPIClient;
import org.epos.handler.dbapi.dbapiimplementation.CategoryDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.CategorySchemeDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.ContactPointDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.DataProductDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.DistributionDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.PersonDBAPI;

public class APITests {


	public static void main(String[] args) {
		
		CategoryDBAPI catAPI = new CategoryDBAPI();
		CategorySchemeDBAPI schemeCatAPI = new CategorySchemeDBAPI();
		
		CategoryScheme scheme = new CategoryScheme();
		scheme.setTitle("Scheme");
		scheme.setUid("scheme");
		scheme.setDescription("scheme test");
		
		schemeCatAPI.save(scheme);
		
		List<String> narrowers1 = new ArrayList<String>();
		narrowers1.add("son1");
		narrowers1.add("son3");
		List<String> narrowers2 = new ArrayList<String>();
		narrowers2.add("son2");
		narrowers2.add("son3");
		
		Category catFather1 = new Category();
		catFather1.setUid("father1");
		catFather1.setName("Father 1");
		catFather1.setDescription("father 1 test");
		catFather1.setInScheme("scheme");
		catFather1.setNarrower(narrowers1);
		
		catAPI.save(catFather1);
		
		
		Category catSon1 = new Category();
		catSon1.setUid("son1");
		catSon1.setName("Son 1");
		catSon1.setDescription("son 1 test");
		catSon1.setInScheme("scheme");
		catSon1.setBroader("father1");
		
		catAPI.save(catSon1);
		
		Category catSon3 = new Category();
		catSon3.setUid("son3");
		catSon3.setName("Son 3");
		catSon3.setDescription("son 3 test");
		catSon3.setInScheme("scheme");
		catSon3.setBroader("father1");
		
		catAPI.save(catSon3);
		
		
		Category catFather2 = new Category();
		catFather2.setUid("father2");
		catFather2.setName("Father 2");
		catFather2.setDescription("father 2 test");
		catFather2.setInScheme("scheme");
		catFather2.setNarrower(narrowers2);
		
		catAPI.save(catFather2);
		
		Category catSon2 = new Category();
		catSon2.setUid("son2");
		catSon2.setName("Son 2");
		catSon2.setDescription("son 2 test");
		catSon2.setInScheme("scheme");
		catSon2.setBroader("father2");
		
		catAPI.save(catSon2);
		
		catSon3 = new Category();
		catSon3.setUid("son3");
		catSon3.setName("Son 3");
		catSon3.setDescription("son 3 test");
		catSon3.setInScheme("scheme");
		catSon3.setBroader("father2");
		
		catAPI.save(catSon3);
		
		
		//System.out.println(getPersonUsingDBAPIClient());
	}
	
	public static Person getPersonUsingDBAPIClient() {
		DBAPIClient client = new DBAPIClient();
		LinkedEntity l = new LinkedEntity();
		l.setEntityType("Person");
		l.setInstanceId("870f1195-1391-4416-957c-747c8c7867fb");
		l.setMetaId("8ad830f8-946d-43b1-bceb-84910aae506e");
		l.setUid("http://orcid.org/0000-0001-8626-2703");
		
		List<Person> d = client.retrieve(Person.class,
				new DBAPIClient.GetQuery()
				.metaId(l.getMetaId()));
		
		return d.get(0);
	}
	
	public static void createContactPoint() {
		ContactPoint cp = new ContactPoint();
		cp.setUid("testprova");
		cp.setRole("legalContact");
		LinkedEntity l = new LinkedEntity();
		l.setEntityType("Person");
		l.setInstanceId("870f1195-1391-4416-957c-747c8c7867fb");
		l.setMetaId("8ad830f8-946d-43b1-bceb-84910aae506e");
		l.setUid("http://orcid.org/0000-0001-8626-2703");
		System.out.println(l);
		cp.setPerson(l);
		cp.setState(State.DRAFT);
		cp.setEditorId("test");

		System.out.println(cp);
		
		ContactPointDBAPI api = new ContactPointDBAPI();
		api.save(cp);
	}

	public static void createDataproduct() {
		if(getDataproduct()==null) {
			DataProduct dp = new DataProduct();
			dp.setUid("test/dataproduct");
			dp.setTitle(new ArrayList<String>());
			dp.getTitle().add("test title");
			dp.setDescription(new ArrayList<String>());
			dp.getDescription().add("test description");
			dp.setIdentifier(new ArrayList<Identifier>());
			Identifier identifier = new Identifier();
			identifier.setIdentifier("test");
			identifier.setType("DDSS");
			dp.getIdentifier().add(identifier);
			dp.setState(State.DRAFT);
			dp.setEditorId("test");

			System.out.println(dp);
			
			DataProductDBAPI api = new DataProductDBAPI();
			api.save(dp);
		}


	}

	public static void createDistribution() {
		if(getDistribution()==null) {
			Distribution distr = new Distribution();
			distr.setUid("test/distribution");
			distr.setTitle(new ArrayList<String>());
			distr.getTitle().add("test title");
			distr.setDescription(new ArrayList<String>());
			distr.getDescription().add("test description");
			distr.setState(State.DRAFT);
			distr.setEditorId("test");

			System.out.println(distr);

			DistributionDBAPI api = new DistributionDBAPI();
			api.save(distr);
		}
	}

	public static DataProduct getDataproduct() {
		DataProductDBAPI api = new DataProductDBAPI();
		List<DataProduct> products = api.getByUid("test/dataproduct");
		return products.size()>0? products.get(0) : null;
	}

	public static Distribution getDistribution() {
		DistributionDBAPI api = new DistributionDBAPI();
		List<Distribution> products = api.getByUid("test/distribution");
		return products.size()>0? products.get(0) : null;
	}

	public static void updateDataproductWithDistribution(DataProduct dp, Distribution distr) {
		LinkedEntity le = new LinkedEntity();
		le.setEntityType("distribution");
		le.setInstanceId(distr.getInstanceId());
		le.setMetaId(distr.getMetaId());
		le.setUid(distr.getUid());
		dp.addDistribution(le);
		dp.setState(State.DRAFT);
		dp.setEditorId("test");
		DataProductDBAPI api = new DataProductDBAPI();
		api.save(dp);
	}

}

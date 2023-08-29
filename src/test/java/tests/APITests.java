package tests;

import static org.epos.eposdatamodel.State.DRAFT;

import java.util.ArrayList;
import java.util.Arrays;
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
		
		CategoryScheme tsunami = new CategoryScheme();
		tsunami.setTitle("Tsunami");
		tsunami.setUid("category:tsunami");
		tsunami.setDescription("TCS Domain");
		
		schemeCatAPI.save(tsunami);
		
		Category tsunamidata = new Category();
		tsunamidata.setUid("category:tsunamidata");
		tsunamidata.setName("Tsunami Data");
		tsunamidata.setDescription("TCS Subdomain");
		tsunamidata.setInScheme("category:tsunami");
		tsunamidata.setNarrower(Arrays.asList("category:sealevelstatimitgfacilitydatavliz"));
		catAPI.save(tsunamidata);
		
		Category sealevelstatimitgfacilitydatavliz = new Category();
		sealevelstatimitgfacilitydatavliz.setUid("category:sealevelstatimitgfacilitydatavliz");
		sealevelstatimitgfacilitydatavliz.setName("Tsunami Data");
		sealevelstatimitgfacilitydatavliz.setDescription("TCS Subdomain");
		sealevelstatimitgfacilitydatavliz.setInScheme("category:tsunami");
		sealevelstatimitgfacilitydatavliz.setBroader(Arrays.asList("category:tsunamidata"));
		catAPI.save(sealevelstatimitgfacilitydatavliz);
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

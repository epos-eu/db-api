package tests;

import static org.epos.eposdatamodel.State.DRAFT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.epos.eposdatamodel.ContactPoint;
import org.epos.eposdatamodel.DataProduct;
import org.epos.eposdatamodel.Distribution;
import org.epos.eposdatamodel.Identifier;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.Person;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.DBAPIClient;
import org.epos.handler.dbapi.dbapiimplementation.ContactPointDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.DataProductDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.DistributionDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.PersonDBAPI;

public class APITestsX {


	public static void main(String[] args) {

		createDataproduct();
		System.out.println(getDataproduct());
	}

	public static void createDataproduct() {
		if(getDataproduct()==null) {
			DataProduct dp = new DataProduct();
			dp.setUid("test/dataproductttt");
			dp.setTitle(new ArrayList<String>());
			dp.getTitle().add("test title");
			dp.setDescription(new ArrayList<String>());
			dp.getDescription().add("test description");
			dp.setIdentifier(new ArrayList<Identifier>());
			ArrayList<String> categories = new ArrayList<>();
			categories.add("conventional hydrocarbon extraction");
			dp.setCategory(categories);
			Identifier identifier = new Identifier();
			identifier.setIdentifier("test");
			identifier.setType("DDSS");
			dp.getIdentifier().add(identifier);
			dp.setState(State.DRAFT);
			dp.setEditorId("test");

			DataProductDBAPI api = new DataProductDBAPI();
			api.save(dp);
		}


	}
	
	public static DataProduct getDataproduct() {
		DataProductDBAPI api = new DataProductDBAPI();
		List<DataProduct> products = api.getByUid("test/dataproductttt");
		return products.size()>0? products.get(0) : null;
	}

}

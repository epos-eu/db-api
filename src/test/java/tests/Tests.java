package tests;

import java.util.List;

import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.Organization;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.DBAPIClient;
import org.epos.handler.dbapi.DBAPIClient.SaveQuery;



public class Tests {

	public static void main(String[] args) {
		
		Organization organization = new Organization();
		organization.setUid("organization/test/1");
		organization.setEditorId("ingestor");
		organization.setLegalName(List.of("Test 1"));
		organization.setState(State.PUBLISHED);
		LinkedEntity le = new LinkedEntity();
		le.setUid("facility/test/1");
		organization.setOwns(List.of(le));
		
		
		DBAPIClient dbapi = new DBAPIClient();
		dbapi.create(organization, new SaveQuery());
		
		
	}

}

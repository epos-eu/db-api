package integrationtests;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.epos.eposdatamodel.DataProduct;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.DBAPIClient;
import org.epos.handler.dbapi.DBAPIClient.GetQuery;
import org.epos.handler.dbapi.dbapiimplementation.DataProductDBAPI;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

class DBAPITest {

	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
			DockerImageName.parse("epos/metadata-database-deploy:latest").asCompatibleSubstituteFor("postgres")
			).withPassword("inmemory").withUsername("inmemory").withDatabaseName("cerif").withReuse(true);

	public static void setEnv(String key, String value) { 
		try { 
			Map<String, String> env = System.getenv(); 
			Class<?> cl = env.getClass(); 
			Field field = cl.getDeclaredField("m"); 
			field.setAccessible(true); 
			Map<String, String> writableEnv = (Map<String, String>) field.get(env); 
			writableEnv.put(key, value); 
		} catch (Exception e) { 
			throw new IllegalStateException("Failed to set environment variable", e); 
		} 
	} 

	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}

	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

	@BeforeEach
	void setUp() {
		boolean isExternal = false;
		
		try {
			setEnv("PERSISTENCE_NAME", "EPOSDataModel");
			setEnv("POSTGRESQL_CONNECTION_STRING", isExternal? "jdbc:postgresql://localhost:5432/cerif?&user=postgres&password=changeme" : postgres.getJdbcUrl()+"&user=inmemory&password=inmemory");
			setEnv("POSTGRES_USER", isExternal? "postgres" : "inmemory");
			setEnv("POSTGRESQL_PASSWORD", isExternal? "changeme" : "inmemory");
			setEnv("POSTGRES_DB", "cerif");
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}

	}

	@Test
	void shouldPrintSomeInformation() {
		System.out.println(System.getenv("POSTGRESQL_CONNECTION_STRING"));
		System.out.println(System.getenv("PERSISTENCE_NAME"));
		System.out.println(System.getenv("POSTGRES_USER"));
		System.out.println(System.getenv("POSTGRESQL_PASSWORD"));
		System.out.println(System.getenv("POSTGRES_DB"));
	}
	
	///----- Test create new empty data product, test update and hard update ----///
	
	@Test
	void createEmptyDataProductAndUpdateIts() {
		DataProduct dp = new DataProduct();
		dp.setUid("unittestnew");
		dp.setEditorId("test");
		dp.setState(State.DRAFT);
		DBAPIClient api = new DBAPIClient();
		api.setEm(null);
		api.startTransaction();
		LinkedEntity storedDataProduct = api.create(dp, new DBAPIClient.SaveQuery());
		api.closeTransaction(true);
		api.setTransactionModeAuto(true);
		
		assertNotNull(storedDataProduct);
		
		System.out.println(storedDataProduct);
		dp.setInstanceId(storedDataProduct.getInstanceId());
		dp.setMetaId(storedDataProduct.getMetaId());
		
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("Test");
		dp.setTitle(titles);
		
		api.update(dp, new DBAPIClient.UpdateQuery().hardUpdate(true));

		api.startTransaction();
		List<DataProduct> dps = api.retrieve(DataProduct.class, new DBAPIClient.GetQuery());
		api.closeTransaction(true);
		api.setTransactionModeAuto(true);
		System.out.println(dps.get(0));
		assertEquals(dps.size(), 1);
		assertEquals(dps.get(0).getInstanceId(), storedDataProduct.getInstanceId());
		assertEquals(dps.get(0).getMetaId(), storedDataProduct.getMetaId());
		assertEquals(dps.get(0).getUid(), storedDataProduct.getUid());
		
	}
	
}
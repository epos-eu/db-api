package integrationtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dao.EposDataModelDAO;
import model.Address;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class DAOJUnitTest {

    private static EposDataModelDAO<Address> objDAO;
    
    @BeforeAll
    public static void setUp() {
        objDAO = new EposDataModelDAO<Address>();
    }

    @AfterAll
    public static void tearDown() {
    }

    @Test
    @Order(1)
    public void testCreateAndGetAddress() {
        Address address = new Address();
        address.setInstanceId(UUID.randomUUID().toString());
        address.setMetaId(UUID.randomUUID().toString());
        address.setUid(UUID.randomUUID().toString());
        address.setCountry("Italy");
        address.setCountrycode("IT");
        address.setStreet("Via Roma");
        address.setPostalCode("00100");
        address.setLocality("Rome");

        objDAO.createObject(address);

        List<Address> retrievedAddress = objDAO.getOneFromDBByInstanceId(address.getInstanceId(), Address.class);

        assertNotNull(retrievedAddress);
        assertEquals(List.of(address), retrievedAddress);
    }

    @Test
    @Order(2)
    public void testUpdateAddress() {
        Address address = new Address();
        address.setInstanceId(UUID.randomUUID().toString());
        address.setMetaId(UUID.randomUUID().toString());
        address.setUid(UUID.randomUUID().toString());
        address.setCountry("France");
        address.setCountrycode("FR");
        address.setStreet("Rue de la Paix");
        address.setPostalCode("75002");
        address.setLocality("Paris");

        objDAO.createObject(address);

        address.setCountry("Spain");
        address.setPostalCode("28001");
        address.setLocality("Madrid");

        objDAO.updateObject(address);

        List<Address> retrievedAddress = objDAO.getOneFromDBByInstanceId(address.getInstanceId(), Address.class);

        assertNotNull(retrievedAddress);
        assertEquals(List.of(address), retrievedAddress);
    }

    @Test
    @Order(3)
    public void testDeleteAddress() {
        Address address = new Address();
        address.setInstanceId(UUID.randomUUID().toString());
        address.setMetaId(UUID.randomUUID().toString());
        address.setUid(UUID.randomUUID().toString());
        address.setCountry("Germany");
        address.setCountrycode("DE");
        address.setStreet("Unter den Linden");
        address.setPostalCode("10117");
        address.setLocality("Berlin");

        objDAO.createObject(address);

        objDAO.deleteObject(address);

        List<Address> retrievedAddress = objDAO.getOneFromDBByInstanceId(address.getInstanceId(), Address.class);

        assertEquals(List.of(),retrievedAddress);
    }
}

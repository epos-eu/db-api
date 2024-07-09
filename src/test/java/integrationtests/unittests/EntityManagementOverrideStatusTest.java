package integrationtests.unittests;

import abstractapis.AbstractAPI;
import integrationtests.TestcontainersLifecycle;
import metadataapis.EntityNames;
import model.StatusType;
import org.epos.eposdatamodel.Address;
import org.epos.eposdatamodel.LinkedEntity;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EntityManagementOverrideStatusTest extends TestcontainersLifecycle {

    static Address address;
    @Test
    @Order(1)
    public void testCreateAndGetAddress() {

        AbstractAPI api = AbstractAPI.retrieveAPI(EntityNames.ADDRESS.name());

        address = new Address();
        address.setInstanceId(UUID.randomUUID().toString());
        address.setMetaId(UUID.randomUUID().toString());
        address.setUid(UUID.randomUUID().toString());
        address.setCountry("Italy");
        address.setCountryCode("IT");
        address.setStreet("Via Roma");
        address.setPostalCode("00100");
        address.setLocality("Rome");
        address.setStatus(StatusType.DRAFT);

        api.create(address, null);

        Address retrievedAddress = (Address) api.retrieve(address.getInstanceId());

        assertNotNull(retrievedAddress);
        assertEquals(address, retrievedAddress);
        assertEquals(StatusType.DRAFT, retrievedAddress.getStatus());
    }


    @Test
    @Order(2)
    public void testUpdateAddress() {
        AbstractAPI api = AbstractAPI.retrieveAPI(EntityNames.ADDRESS.name());

        LinkedEntity le = api.create(address, StatusType.PUBLISHED);

        List<Address> retrievedAddress = api.retrieveAll();

        assertEquals(1, retrievedAddress.size());
        assertEquals(StatusType.PUBLISHED, retrievedAddress.get(0).getStatus());
    }

}

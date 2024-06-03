package integrationtests;

import dao.EposDataModelDAO;
import model.Address;
import org.epos.eposdatamodel.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import usermanagementapis.UserGroupManagementAPI;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserGroupTest {

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
    public void testCreateUser() {
        User user = new User("testid", "familyname", "givenname", "email@email.email");
        UserGroupManagementAPI.createUser(user);

        model.User retrieveUser = UserGroupManagementAPI.retrieveUser(user);

        assertNotNull(retrieveUser);
        assertEquals(user.getAuthIdentifier(), retrieveUser.getAuthIdentifier());
        assertEquals(user.getFamilyname(), retrieveUser.getFamilyname());
        assertEquals(user.getGivenname(), retrieveUser.getGivenname());
        assertEquals(user.getEmail(), retrieveUser.getEmail());
    }

    @Test
    @Order(2)
    public void testUpdateUser() {
        User user = new User("testid", "familyname", "givenname", "email@email.email");
        UserGroupManagementAPI.createUser(user);

        user.setEmail("newemail@email.email");
        user.setFamilyname("newfamilyname");
        user.setGivenname("newgivenname");

        UserGroupManagementAPI.createUser(user);

        model.User retrieveUser = UserGroupManagementAPI.retrieveUser(user);

        assertNotNull(retrieveUser);
        assertEquals(user.getAuthIdentifier(), retrieveUser.getAuthIdentifier());
        assertEquals(user.getFamilyname(), retrieveUser.getFamilyname());
        assertEquals(user.getGivenname(), retrieveUser.getGivenname());
        assertEquals(user.getEmail(), retrieveUser.getEmail());
    }

    @Test
    @Order(3)
    public void testDeleteUser() {
        User user = new User("testid", "familyname", "givenname", "email@email.email");
        UserGroupManagementAPI.createUser(user);

        UserGroupManagementAPI.deleteUser(user.getAuthIdentifier());

        model.User retrieveUser = UserGroupManagementAPI.retrieveUser(user);

        assertNull(retrieveUser);
    }
}

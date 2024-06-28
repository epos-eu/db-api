package integrationtests.unittests;

import integrationtests.TestcontainersLifecycle;
import model.RequestStatusType;
import model.RoleType;
import org.epos.eposdatamodel.Group;
import org.epos.eposdatamodel.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import usermanagementapis.UserGroupManagementAPI;

import java.util.UUID;
import java.util.zip.ZipEntry;

import static org.junit.jupiter.api.Assertions.*;

public class UserGroupManagementTest extends TestcontainersLifecycle {

    static User user;
    static Group group;

    @Test
    @Order(1)
    public void testCreateUser() {
        user = new User("testid", "familyname", "givenname", "email@email.email", false);
        UserGroupManagementAPI.createUser(user);

        User retrieveUser = UserGroupManagementAPI.retrieveUser(user);

        assertNotNull(retrieveUser);
        assertEquals(user.getAuthIdentifier(), retrieveUser.getAuthIdentifier());
        assertEquals(user.getLastName(), retrieveUser.getLastName());
        assertEquals(user.getFirstName(), retrieveUser.getFirstName());
        assertEquals(user.getEmail(), retrieveUser.getEmail());
    }

    @Test
    @Order(2)
    public void testUpdateUser() {

        user.setEmail("newemail@email.email");
        user.setLastName("newfamilyname");
        user.setFirstName("newgivenname");

        UserGroupManagementAPI.createUser(user);

        User retrieveUser = UserGroupManagementAPI.retrieveUser(user);

        assertNotNull(retrieveUser);
        assertEquals(user.getAuthIdentifier(), retrieveUser.getAuthIdentifier());
        assertEquals(user.getLastName(), retrieveUser.getLastName());
        assertEquals(user.getFirstName(), retrieveUser.getFirstName());
        assertEquals(user.getEmail(), retrieveUser.getEmail());
    }

    @Test
    @Order(3)
    public void testCreateGroup() {
        group = new Group(UUID.randomUUID().toString(), "Test Group", "Test Decription");
        UserGroupManagementAPI.createGroup(group);

        Group retrieveGroup = UserGroupManagementAPI.retrieveGroupById(group.getId());

        assertNotNull(retrieveGroup);
        assertEquals(group.getId(), retrieveGroup.getId());
        assertEquals(group.getName(), retrieveGroup.getName());
        assertEquals(group.getDescription(), retrieveGroup.getDescription());
    }

    @Test
    @Order(4)
    public void testUpdateGroup() {

        group.setDescription("Test updated description");

        UserGroupManagementAPI.createGroup(group);

        Group retrieveGroup = UserGroupManagementAPI.retrieveGroupById(group.getId());

        assertNotNull(retrieveGroup);
        assertEquals(group.getId(), retrieveGroup.getId());
        assertEquals(group.getName(), retrieveGroup.getName());
        assertEquals(group.getDescription(), retrieveGroup.getDescription());
    }

    @Test
    @Order(5)
    public void testAddUserToGroup() {

        UserGroupManagementAPI.addUserToGroup(group.getId(),user.getAuthIdentifier(), RoleType.EDITOR, RequestStatusType.PENDING);

        Group retrieveGroup = UserGroupManagementAPI.retrieveGroupById(group.getId());
        User retrieveUser = UserGroupManagementAPI.retrieveUser(user);

        System.out.println(retrieveGroup);
        System.out.println(retrieveUser);

        assertAll(
                () -> assertNotNull(retrieveGroup),
                () -> assertEquals(1, retrieveGroup.getUsers().size()),
                () -> assertEquals(retrieveGroup.getUsers().get(0), retrieveUser.getAuthIdentifier()),
                () -> assertEquals(1, retrieveUser.getGroups().size()),
                () -> assertEquals(retrieveUser.getGroups().get(0).getGroupId(), retrieveGroup.getId()),
                () -> assertEquals(retrieveUser.getGroups().get(0).getRole(), RoleType.EDITOR)
        );
    }

    @Test
    @Order(6)
    public void testDeleteUser() {
        UserGroupManagementAPI.deleteUser(user.getAuthIdentifier());

        User retrieveUser = UserGroupManagementAPI.retrieveUser(user);

        assertNull(retrieveUser);
    }

    @Test
    @Order(7)
    public void testDeleteGroup() {

        UserGroupManagementAPI.deleteGroup(group.getId());

        Group retrieveGroup = UserGroupManagementAPI.retrieveGroup(group);

        assertNull(retrieveGroup);
    }


    @Test
    @Order(8)
    public void testCreateGroupWithoutName() {
        Group group = new Group(UUID.randomUUID().toString(), null, "Test Decription");
        UserGroupManagementAPI.createGroup(group);

        Group retrieveGroup = UserGroupManagementAPI.retrieveGroupById(group.getId());

        assertNotNull(retrieveGroup);
        assertEquals(group.getId(), retrieveGroup.getId());
        assertEquals(group.getName(), retrieveGroup.getName());
        assertEquals(group.getDescription(), retrieveGroup.getDescription());
    }
}

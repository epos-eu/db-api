package usermanagementapis;

import dao.EposDataModelDAO;
import model.*;
import org.epos.eposdatamodel.UserGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserGroupManagementAPI {

    /**
     *
     * USER OPERATIONS
     *
     */

    public static Boolean createUser(org.epos.eposdatamodel.User user){
        User user1 = new User();
        user1.setAuthIdentifier(user.getAuthIdentifier());
        user1.setEmail(user.getEmail());
        user1.setGivenname(user.getFirstName());
        user1.setFamilyname(user.getLastName());
        user1.setIsadmin(Boolean.toString(user.getIsAdmin().booleanValue()));

        return getDbaccess().createObject(user1);
    }

    public static org.epos.eposdatamodel.User retrieveUser(org.epos.eposdatamodel.User user){
        List<User> userList = getDbaccess().getOneFromDBBySpecificKey("authIdentifier",user.getAuthIdentifier(), User.class);
        if(userList.isEmpty()) return null;

        User retrievedUser = userList.get(0);

        org.epos.eposdatamodel.User user1 = new org.epos.eposdatamodel.User(
                retrievedUser.getAuthIdentifier(),
                retrievedUser.getFamilyname(),
                retrievedUser.getGivenname(),
                retrievedUser.getEmail(),
                Boolean.parseBoolean(retrievedUser.getIsadmin())
        );

        retrievedUser.getMetadataGroupUsersByAuthIdentifier().forEach(item->{
            UserGroup userGroup = new UserGroup(item.getRole(),item.getGroupId());
            user1.getGroups().add(userGroup);
        });


        return user1 ;
    }

    public static org.epos.eposdatamodel.User retrieveUserById(String userId){
        List<User> userList = getDbaccess().getOneFromDBBySpecificKey("authIdentifier",userId, User.class);
        if(userList.isEmpty()) return null;

        User retrievedUser = userList.get(0);
        org.epos.eposdatamodel.User user1 = new org.epos.eposdatamodel.User(
                retrievedUser.getAuthIdentifier(),
                retrievedUser.getFamilyname(),
                retrievedUser.getGivenname(),
                retrievedUser.getEmail(),
                Boolean.parseBoolean(retrievedUser.getIsadmin())
        );

        return user1 ;
    }

    public static List<org.epos.eposdatamodel.User> retrieveAllUsers(){
        List<User> userList = getDbaccess().getAllFromDB(User.class);
        if(userList.isEmpty()) return null;

        List<org.epos.eposdatamodel.User> returnList = new ArrayList<>();
        for(User user : userList){
            org.epos.eposdatamodel.User user1 = new org.epos.eposdatamodel.User(
                    user.getAuthIdentifier(),
                    user.getFamilyname(),
                    user.getGivenname(),
                    user.getEmail(),
                    Boolean.parseBoolean(user.getIsadmin())
            );
            returnList.add(user1);
        }

        return returnList ;
    }

    public static Boolean deleteUser(String authIdentfier){
        List<User> user1 = getDbaccess().getOneFromDBBySpecificKey("authIdentifier",authIdentfier,User.class);
        return getDbaccess().deleteObject(user1.get(0));
    }


    /**
     *
     * GROUPS OPERATIONS
     *
     */

    public static Boolean createGroup(org.epos.eposdatamodel.Group group){
        MetadataGroup group1 = new MetadataGroup();
        group1.setId(UUID.randomUUID().toString());
        group1.setName(group.getName());
        group1.setDescription(group.getDescription());
        return getDbaccess().createObject(group1);
    }

    public static MetadataGroup retrieveGroup(org.epos.eposdatamodel.Group group){
        return (MetadataGroup) getDbaccess().getOneFromDBBySpecificKey("id",group.getId(), MetadataGroup.class).get(0);
    }

    public static MetadataGroup retrieveGroupById(String groupId){
        return (MetadataGroup) getDbaccess().getOneFromDBBySpecificKey("id",groupId, MetadataGroup.class).get(0);
    }

    public static List<MetadataGroup> retrieveAllGroups(){
        return getDbaccess().getAllFromDB(MetadataGroup.class);
    }

    public static Boolean deleteGroup(String groupId){
        List<MetadataGroup> group = getDbaccess().getOneFromDBBySpecificKey("id",groupId, MetadataGroup.class);
        return getDbaccess().deleteObject(group.get(0));
    }

    /**
     *
     * ASSIGN USER TO GROUP WITH AUTHORIZATIONS
     *
     */

    public static Boolean addUserToGroup(String groupId, String userId, RoleType role, RequestStatusType requestStatusType){

        MetadataGroup metadataGroup = retrieveGroupById(groupId);

        List<User> userList = getDbaccess().getOneFromDBBySpecificKey("authIdentifier",userId, User.class);
        if(userList.isEmpty()) return null;

        User retrievedUser = userList.get(0);

        if(retrievedUser!=null && metadataGroup!=null) {
            MetadataGroupUser metadataGroupUser = new MetadataGroupUser();
            metadataGroupUser.setId(UUID.randomUUID().toString());
            metadataGroupUser.setMetadataGroupByGroupId(metadataGroup);
            metadataGroupUser.setGroupId(metadataGroup.getId());
            metadataGroupUser.setUserByAuthIdentifier(retrievedUser);
            metadataGroupUser.setAuthIdentifier(retrievedUser.getAuthIdentifier());
            metadataGroupUser.setRequestStatus(requestStatusType);
            metadataGroupUser.setRole(role);

            return getDbaccess().createObject(metadataGroupUser);
        }
        return false;
    }

    public static void addMetadataElementToGroup(String metaId, String groupId){

        AuthorizationGroup authorizationGroup = new AuthorizationGroup();
        authorizationGroup.setGroupId(groupId);
        authorizationGroup.setMetaId(metaId);
        authorizationGroup.setId(UUID.randomUUID().toString());
        getDbaccess().createObject(authorizationGroup);
    }

    private static EposDataModelDAO getDbaccess() {
        return new EposDataModelDAO();
    }


}

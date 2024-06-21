package usermanagementapis;

import dao.EposDataModelDAO;
import model.*;
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
        user1.setGivenname(user.getGivenname());
        user1.setFamilyname(user.getFamilyname());

        return getDbaccess().createObject(user1);
    }

    public static org.epos.eposdatamodel.User retrieveUser(org.epos.eposdatamodel.User user){
        List<User> userList = getDbaccess().getOneFromDBByInstanceId(user.getAuthIdentifier(), User.class);
        if(userList.isEmpty()) return null;

        User retrievedUser = userList.get(0);
        org.epos.eposdatamodel.User user1 = new org.epos.eposdatamodel.User(
                retrievedUser.getAuthIdentifier(),
                retrievedUser.getFamilyname(),
                retrievedUser.getGivenname(),
                retrievedUser.getEmail()
        );

        return user1 ;
    }

    public static Boolean deleteUser(String authIdentfier){
        List<User> user1 = getDbaccess().getOneFromDBByInstanceId(authIdentfier,User.class);
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

    public static MetadataGroup retrieveGroup(org.epos.eposdatamodel.Group user){
        return (MetadataGroup) getDbaccess().getOneFromDBByInstanceId(user.getId(), MetadataGroup.class).get(0);
    }

    public static void deleteGroup(String groupId){
        List<MetadataGroup> group = getDbaccess().getOneFromDBByInstanceId(groupId, MetadataGroup.class);
        getDbaccess().deleteObject(group.get(0));
    }

    /**
     *
     * ASSIGN USER TO GROUP WITH AUTHORIZATIONS
     *
     */

    public static Boolean createUserGroup(org.epos.eposdatamodel.Group group, org.epos.eposdatamodel.User user, RoleType role, RequestStatusType requestStatusType){

        MetadataGroup metadataGroup = retrieveGroup(group);
        List<User> userList = getDbaccess().getOneFromDBByInstanceId(user.getAuthIdentifier(), User.class);
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

    public static void addMetadataToGroup(String metaId, String groupId){

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

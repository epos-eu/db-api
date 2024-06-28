package usermanagementapis;

import dao.EposDataModelDAO;
import model.*;
import org.epos.eposdatamodel.Group;
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

        return getDbaccess().updateObject(user1);
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

        retrievedUser.getMetadataGroupUsersByAuthIdentifier().forEach(item->{
            UserGroup userGroup = new UserGroup(item.getRole(),item.getGroupId());
            user1.getGroups().add(userGroup);
        });

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

            user.getMetadataGroupUsersByAuthIdentifier().forEach(item->{
                UserGroup userGroup = new UserGroup(item.getRole(),item.getGroupId());
                user1.getGroups().add(userGroup);
            });
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
        group1.setId(group.getId()==null? UUID.randomUUID().toString() : group.getId());
        group1.setName(group.getName());
        group1.setDescription(group.getDescription());
        return getDbaccess().updateObject(group1);
    }

    public static Group retrieveGroup(Group group){
        List<MetadataGroup> metadataGroupList = getDbaccess().getOneFromDBBySpecificKey("id",group.getId(), MetadataGroup.class);
        if(metadataGroupList.isEmpty()) return null;

        MetadataGroup retrievedGroup = metadataGroupList.get(0);
        org.epos.eposdatamodel.Group group1 = new org.epos.eposdatamodel.Group(
                retrievedGroup.getId(),
                retrievedGroup.getName(),
                retrievedGroup.getDescription()
        );

        retrievedGroup.getAuthorizationGroupsById().forEach(item->{
            group1.getEntities().add(item.getMetaId());
        });

        retrievedGroup.getMetadataGroupUsersById().forEach(item->{
            group1.getUsers().add(item.getAuthIdentifier());
        });

        return group1;
    }

    public static Group retrieveGroupById(String groupId){
        List<MetadataGroup> metadataGroupList = getDbaccess().getOneFromDBBySpecificKey("id",groupId, MetadataGroup.class);
        if(metadataGroupList.isEmpty()) return null;

        MetadataGroup retrievedGroup = metadataGroupList.get(0);
        org.epos.eposdatamodel.Group group1 = new org.epos.eposdatamodel.Group(
                retrievedGroup.getId(),
                retrievedGroup.getName(),
                retrievedGroup.getDescription()
        );

        retrievedGroup.getAuthorizationGroupsById().forEach(item->{
            group1.getEntities().add(item.getMetaId());
        });

        retrievedGroup.getMetadataGroupUsersById().forEach(item->{
            group1.getUsers().add(item.getAuthIdentifier());
        });

        return group1;
    }

    public static List<Group> retrieveAllGroups(){
        List<MetadataGroup> metadataGroupList = getDbaccess().getAllFromDB(MetadataGroup.class);
        if(metadataGroupList.isEmpty()) return null;

        List<Group> returnList = new ArrayList<>();
        for(MetadataGroup group : metadataGroupList){
            MetadataGroup retrievedGroup = metadataGroupList.get(0);
            org.epos.eposdatamodel.Group group1 = new org.epos.eposdatamodel.Group(
                    retrievedGroup.getId(),
                    retrievedGroup.getName(),
                    retrievedGroup.getDescription()
            );

            retrievedGroup.getAuthorizationGroupsById().forEach(item->{
                group1.getEntities().add(item.getMetaId());
            });

            retrievedGroup.getMetadataGroupUsersById().forEach(item->{
                group1.getUsers().add(item.getAuthIdentifier());
            });
            returnList.add(group1);
        }


        return returnList;
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

        List<MetadataGroup> metadataGroupList = getDbaccess().getOneFromDBBySpecificKey("id",groupId, MetadataGroup.class);
        if(metadataGroupList.isEmpty()) return null;

        List<User> userList = getDbaccess().getOneFromDBBySpecificKey("authIdentifier",userId, User.class);
        if(userList.isEmpty()) return null;

        MetadataGroup retrievedGroup = metadataGroupList.get(0);
        User retrievedUser = userList.get(0);

        if(retrievedUser!=null && retrievedGroup!=null) {
            MetadataGroupUser metadataGroupUser = new MetadataGroupUser();
            metadataGroupUser.setId(UUID.randomUUID().toString());
            metadataGroupUser.setMetadataGroupByGroupId(retrievedGroup);
            metadataGroupUser.setGroupId(retrievedGroup.getId());
            metadataGroupUser.setUserByAuthIdentifier(retrievedUser);
            metadataGroupUser.setAuthIdentifier(retrievedUser.getAuthIdentifier());
            metadataGroupUser.setRequestStatus(requestStatusType);
            metadataGroupUser.setRole(role);

            return getDbaccess().createObject(metadataGroupUser);
        }
        return false;
    }

    public static Boolean addMetadataElementToGroup(String metaId, String groupId){

        AuthorizationGroup authorizationGroup = new AuthorizationGroup();
        authorizationGroup.setGroupId(groupId);
        authorizationGroup.setMetaId(metaId);
        authorizationGroup.setId(UUID.randomUUID().toString());
        return getDbaccess().createObject(authorizationGroup);
    }

    private static EposDataModelDAO getDbaccess() {
        return new EposDataModelDAO();
    }


}

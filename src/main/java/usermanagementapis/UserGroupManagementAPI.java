package usermanagementapis;

import dao.EposDataModelDAO;
import model.*;
import org.epos.eposdatamodel.EPOSDataModelEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserGroupManagementAPI {

    public static void createUser(org.epos.eposdatamodel.User user){
        User user1 = new User();
        user1.setAuthIdentifier(user.getAuthIdentifier());
        user1.setEmail(user.getEmail());
        user1.setGivenname(user.getGivenname());
        user1.setFamilyname(user.getFamilyname());
        getDbaccess().createObject(user1);
    }

    public static User retrieveUser(org.epos.eposdatamodel.User user){
        return (User) getDbaccess().getOneFromDBByInstanceId(user.getAuthIdentifier(), User.class).get(0);
    }

    public static void deleteUser(String authIdentfier){
        List<User> user1 = getDbaccess().getOneFromDBByInstanceId(authIdentfier,User.class);
        getDbaccess().deleteObject(user1.get(0));
    }

    public static void createGroup(org.epos.eposdatamodel.Group group){
        MetadataGroup group1 = new MetadataGroup();
        group1.setId(UUID.randomUUID().toString());
        group1.setName(group.getName());
        group1.setDescription(group.getDescription());
        getDbaccess().createObject(group1);
    }

    public static MetadataGroup retrieveGroup(org.epos.eposdatamodel.Group user){
        return (MetadataGroup) getDbaccess().getOneFromDBByInstanceId(user.getId(), MetadataGroup.class).get(0);
    }

    public static void deleteGroup(String groupId){
        List<MetadataGroup> group = getDbaccess().getOneFromDBByInstanceId(groupId, MetadataGroup.class);
        getDbaccess().deleteObject(group.get(0));
    }

    public static void createUserGroup(org.epos.eposdatamodel.Group group, org.epos.eposdatamodel.User user, RoleType role, RequestStatusType requestStatusType){

        MetadataGroup metadataGroup = retrieveGroup(group);
        User user1 = retrieveUser(user);

        if(user1!=null && metadataGroup!=null) {
            MetadataGroupUser metadataGroupUser = new MetadataGroupUser();
            metadataGroupUser.setId(UUID.randomUUID().toString());
            metadataGroupUser.setMetadataGroupByGroupId(metadataGroup);
            metadataGroupUser.setGroupId(metadataGroup.getId());
            metadataGroupUser.setUserByAuthIdentifier(user1);
            metadataGroupUser.setAuthIdentifier(user1.getAuthIdentifier());
            metadataGroupUser.setRequestStatus(requestStatusType);
            metadataGroupUser.setRole(role);

            getDbaccess().createObject(metadataGroupUser);
        }
    }


    private static EposDataModelDAO getDbaccess() {
        return new EposDataModelDAO();
    }


}

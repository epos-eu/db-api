package metadataapis;

import abstractapis.AbstractAPI;
import commonapis.AddressAPI;
import commonapis.ElementAPI;
import commonapis.IdentifierAPI;
import commonapis.VersioningStatusAPI;
import model.*;
import org.epos.eposdatamodel.ContactPoint;
import org.epos.eposdatamodel.LegalName;
import org.epos.eposdatamodel.LinkedEntity;

import java.util.*;
import java.util.stream.Collectors;

public class OrganizationAPI extends AbstractAPI<org.epos.eposdatamodel.Organization> {

    public OrganizationAPI(String entityName, Class<?> edmClass) {
        super(entityName, edmClass);
    }

    @Override
    public LinkedEntity create(org.epos.eposdatamodel.Organization obj) {

        List<Organization> returnList = getDbaccess().getOneFromDB(
                obj.getInstanceId(),
                obj.getMetaId(),
                obj.getUid(),
                obj.getVersionId(),
                getEdmClass());

        if(!returnList.isEmpty()){
            obj.setInstanceId(returnList.get(0).getInstanceId());
            obj.setMetaId(returnList.get(0).getMetaId());
            obj.setUid(returnList.get(0).getUid());
            obj.setVersionId(returnList.get(0).getVersionId());
        }

        obj = (org.epos.eposdatamodel.Organization) VersioningStatusAPI.checkVersion(obj);

        Organization edmobj = new Organization();

        edmobj.setVersionId(obj.getVersionId());
        edmobj.setInstanceId(obj.getInstanceId());
        edmobj.setMetaId(obj.getMetaId());
        edmobj.setUid(Optional.ofNullable(obj.getUid()).orElse(getEdmClass().getSimpleName()+"/"+UUID.randomUUID().toString()));
        edmobj.setLogo(obj.getLogo());
        edmobj.setType(obj.getType());
        edmobj.setAcronym(obj.getAcronym());
        edmobj.setLeicode(obj.getLeiCode());
        edmobj.setUrl(obj.getURL());
        edmobj.setMaturity(obj.getMaturity());

        /** ADDRESS **/
        if (obj.getAddress() != null) {
            List<Address> identifierList = getDbaccess().getAllFromDB(Address.class);
            for(Address item : identifierList){
                if(item.getInstanceId().equals(obj.getAddress().getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            AddressAPI addressAPI = new AddressAPI("Address", Address.class);
            LinkedEntity le = addressAPI.create(obj.getAddress());
            edmobj.setAddressId(le.getInstanceId());
        }

        if(returnList.isEmpty()) getDbaccess().createObject(edmobj);
        else getDbaccess().updateObject(edmobj);

        /** CONTACTPOINT **/
        if (obj.getContactPoint() != null && !obj.getContactPoint().isEmpty()) {
            List<OrganizationContactpoint> identifierList = getDbaccess().getAllFromDB(OrganizationContactpoint.class);
            for(OrganizationContactpoint item : identifierList){
                if(item.getOrganizationInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            IdentifierAPI identifierAPI = new IdentifierAPI("Identifier", Identifier.class);
            for(org.epos.eposdatamodel.ContactPoint contactPoint : obj.getContactPoint()){
                OrganizationContactpoint pi = new OrganizationContactpoint();
                pi.setOrganizationByOrganizationInstanceId(edmobj);
                pi.setOrganizationInstanceId(edmobj.getInstanceId());
                pi.setContactpointInstanceId(contactPoint.getInstanceId());
                pi.setContactpointByContactpointInstanceId((Contactpoint) dbaccess.getOneFromDBByInstanceId(contactPoint.getInstanceId(),Contactpoint.class).get(0));
            }
        }

        /** IDENTIFIER **/
        if (obj.getIdentifier() != null && !obj.getIdentifier().isEmpty()) {
            List<OrganizationIdentifier> identifierList = getDbaccess().getAllFromDB(OrganizationIdentifier.class);
            for(OrganizationIdentifier item : identifierList){
                if(item.getOrganizationInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                    List<Identifier> list2 = getDbaccess().getOneFromDBByInstanceId(item.getIdentifierInstanceId(), Identifier.class);
                    getDbaccess().deleteObject(list2.get(0));
                }
            }
            IdentifierAPI identifierAPI = new IdentifierAPI("Identifier", Identifier.class);
            for(org.epos.eposdatamodel.Identifier identifier : obj.getIdentifier()){
                LinkedEntity le = identifierAPI.create(identifier);
                OrganizationIdentifier pi = new OrganizationIdentifier();
                pi.setOrganizationByOrganizationInstanceId(edmobj);
                pi.setOrganizationInstanceId(edmobj.getInstanceId());
                pi.setIdentifierInstanceId(le.getInstanceId());
                pi.setIdentifierByIdentifierInstanceId((Identifier) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(),Identifier.class).get(0));
            }
        }

        List<OrganizationElement> elementslist = getDbaccess().getAllFromDB(OrganizationElement.class);
        for(OrganizationElement item : elementslist){
            if(item.getOrganizationInstanceId().equals(obj.getInstanceId())){
                getDbaccess().deleteObject(item);
                List<Element> list2 = getDbaccess().getOneFromDBByInstanceId(item.getElementInstanceId(), Element.class);
                getDbaccess().deleteObject(list2.get(0));
            }
        }
        /* TELEPHONE */
        if(!obj.getTelephone().isEmpty()){
            for(String tel : obj.getTelephone()) {
                createInnerElement(ElementType.TELEPHONE, tel, edmobj);
            }
        }

        /* EMAIL */
        if(!obj.getEmail().isEmpty()){
            for(String email : obj.getEmail()) {
                createInnerElement(ElementType.EMAIL, email, edmobj);
            }
        }

        /** LEGALNAME **/
        if (obj.getLegalName() != null && !obj.getLegalName().isEmpty()) {
            List<OrganizationLegalname> legalnameList = getDbaccess().getAllFromDB(OrganizationLegalname.class);
            for(OrganizationLegalname item : legalnameList){
                if(item.getOrganizationInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                    List<OrganizationLegalname> list2 = getDbaccess().getOneFromDBByInstanceId(item.getInstanceId(), OrganizationLegalname.class);
                    getDbaccess().deleteObject(list2.get(0));
                }
            }

            for(LegalName legalname : obj.getLegalName()) {
                legalname = (org.epos.eposdatamodel.LegalName) VersioningStatusAPI.checkVersion(legalname);
                OrganizationLegalname pi = new OrganizationLegalname();
                pi.setInstanceId(legalname.getInstanceId());
                pi.setMetaId(legalname.getMetaId());
                pi.setUid(legalname.getUid());
                pi.setVersionId(legalname.getVersionId());
                pi.setOrganizationByOrganizationInstanceId(edmobj);
                pi.setOrganizationInstanceId(edmobj.getInstanceId());
                pi.setLanguage(null);
                pi.setLegalname(legalname.getLegalname());
                dbaccess.createObject(pi);
            }
        }

        /** MEMBER OF **/
        if (obj.getMemberOf() != null && !obj.getMemberOf().isEmpty()) {
            List<OrganizationMemberof> organizationMemberofs = getDbaccess().getAllFromDB(OrganizationMemberof.class);
            for(OrganizationMemberof item : organizationMemberofs){
                if(item.getOrganization1InstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }

            for(org.epos.eposdatamodel.Organization organization : obj.getMemberOf()) {
                organization = (org.epos.eposdatamodel.Organization) VersioningStatusAPI.checkVersion(organization);
                LinkedEntity le = create(organization);

                List<Organization> list2 = getDbaccess().getOneFromDBByInstanceId(le.getInstanceId(), Organization.class);

                OrganizationMemberof pi = new OrganizationMemberof();
                pi.setOrganization1InstanceId(edmobj.getInstanceId());
                pi.setOrganizationByOrganization1InstanceId(edmobj);
                pi.setOrganizationByOrganization2InstanceId(list2.get(0));
                pi.setOrganization2InstanceId(list2.get(0).getInstanceId());
                dbaccess.createObject(pi);
            }
        }

        /** OWNSFACILITIES **/
        if (obj.getOwnedFacilities() != null && !obj.getOwnedFacilities().isEmpty()) {
            List<OrganizationOwns> organizationOwns = getDbaccess().getAllFromDB(OrganizationOwns.class);
            organizationOwns = organizationOwns.stream().filter(item -> item.getResourceEntity().equals("Facility")).collect(Collectors.toList());
            for(OrganizationOwns item : organizationOwns){
                if(item.getOrganizationInstanceId().equals(obj.getInstanceId())){
                    getDbaccess().deleteObject(item);
                }
            }
            FacilityAPI facilityAPI = new FacilityAPI("Facility", Facility.class);
            for(org.epos.eposdatamodel.Facility facility : obj.getOwnedFacilities()){
                List<Facility> list = dbaccess.getOneFromDBByInstanceId(facility.getInstanceId(),Facility.class);
                Facility facility1 = null;
                if(list.isEmpty()){
                    LinkedEntity le = facilityAPI.create(facility);
                    facility1 = (Facility) dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Facility.class).get(0);
                } else {
                    facility1 = list.get(0);
                }
                OrganizationOwns pi = new OrganizationOwns();
                pi.setOrganizationByOrganizationInstanceId(edmobj);
                pi.setOrganizationInstanceId(edmobj.getInstanceId());
                pi.setResourceEntity("Facility");
                pi.setEntityInstanceId(facility1.getInstanceId());
            }
        }

        /** TODO: FINISH OWNS EQUIPMENT **/
        if (obj.getOwnedEquipments() != null && !obj.getOwnedFacilities().isEmpty()) {
            List<OrganizationOwns> organizationMemberofs = getDbaccess().getAllFromDB(OrganizationOwns.class);
            for(OrganizationOwns item : organizationMemberofs) {
                if (item.getOrganizationInstanceId().equals(obj.getInstanceId())) {
                    getDbaccess().deleteObject(item);
                }
            }

            for(org.epos.eposdatamodel.Equipment owns : obj.getOwnedEquipments()) {

                List<Equipment> equipments = getDbaccess().getOneFromDBByInstanceId(owns.getInstanceId(), Equipment.class);

                if(equipments.isEmpty()) {/**TODO: CREATE**/}

                /** TODO: CREATE EQUIPMENT USING API**/
                OrganizationOwns pi = new OrganizationOwns();
                pi.setOrganizationByOrganizationInstanceId(edmobj);
                pi.setOrganizationInstanceId(edmobj.getInstanceId());
                pi.setEntityInstanceId("facilityinstanceid");
                pi.setResourceEntity("Facility");
                dbaccess.createObject(pi);
            }
        }


        return new LinkedEntity().entityType(entityName)
                    .instanceId(edmobj.getInstanceId())
                    .metaId(edmobj.getMetaId())
                    .uid(edmobj.getUid());

    }

    private void createInnerElement(ElementType elementType, String value, Organization edmobj){
        org.epos.eposdatamodel.Element element = new org.epos.eposdatamodel.Element();
        element.setType(elementType);
        element.setValue(value);
        ElementAPI api = new ElementAPI("Element", Element.class);
        LinkedEntity le = api.create(element);
        List<Element> el = dbaccess.getOneFromDBByInstanceId(le.getInstanceId(), Element.class);
        OrganizationElement ce = new OrganizationElement();
        ce.setOrganizationByOrganizationInstanceId(edmobj);
        ce.setOrganizationInstanceId(edmobj.getInstanceId());
        ce.setElementByElementInstanceId(el.get(0));
        ce.setElementInstanceId(el.get(0).getInstanceId());
    }


    @Override
    public org.epos.eposdatamodel.Organization retrieve(String instanceId) {
        Organization edmobj = (Organization) getDbaccess().getOneFromDBByInstanceId(instanceId, Organization.class).get(0);

        org.epos.eposdatamodel.Organization o = new org.epos.eposdatamodel.Organization();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setAcronym(edmobj.getAcronym());
        o.setLeiCode(edmobj.getLeicode());
        o.setLogo(edmobj.getLogo());
        o.setURL(edmobj.getUrl());
        o.setType(edmobj.getType());
        o.setMaturity(edmobj.getMaturity());

        if(edmobj.getOrganizationIdentifiersByInstanceId().size()>0) {
            for(OrganizationIdentifier ed : edmobj.getOrganizationIdentifiersByInstanceId()) {
                Identifier el = ed.getIdentifierByIdentifierInstanceId();
                org.epos.eposdatamodel.Identifier id = new org.epos.eposdatamodel.Identifier();
                id.setIdentifier(el.getValue());
                id.setType(el.getType());
                o.addIdentifier(id);
            }
        }

        if(edmobj.getAddressByAddressId()!=null) {
            Address address = edmobj.getAddressByAddressId();
            org.epos.eposdatamodel.Address address1 = new org.epos.eposdatamodel.Address();
            address1.setLocality(address.getLocality());
            address1.setCountryCode(address.getCountrycode());
            address1.setCountry(address.getCountry());
            address1.setPostalCode(address.getPostalCode());
            address1.setStreet(address.getStreet());
            o.setAddress(address1);
        }

        if(edmobj.getOrganizationContactpointsByInstanceId().size()>0) {
            for(OrganizationContactpoint ed : edmobj.getOrganizationContactpointsByInstanceId()) {
                ContactPointAPI api = new ContactPointAPI("ContactPoint", Contactpoint.class);
                ContactPoint cp = api.retrieve(ed.getContactpointInstanceId());
                o.addContactPoint(cp);
            }
        }

        if(edmobj.getOrganizationElementsByInstanceId().size()>0) {
            for(OrganizationElement ed : edmobj.getOrganizationElementsByInstanceId()) {
                Element el = ed.getElementByElementInstanceId();
                if(el.getType().equals(ElementType.TELEPHONE)) o.addTelephone(el.getValue());
                if(el.getType().equals(ElementType.EMAIL)) o.addEmail(el.getValue());
            }
        }

        if(edmobj.getOrganizationLegalnamesByInstanceId().size()>0) {
            for(OrganizationLegalname ed : edmobj.getOrganizationLegalnamesByInstanceId()) {
                LegalName ln = new LegalName();
                ln.setLanguage(ed.getLanguage());
                ln.setLegalname(ed.getLegalname());
                o.addLegalName(ln);
            }
        }

        List<OrganizationOwns> organizationOwnsList =dbaccess.getOneFromDBByInstanceId(edmobj.getInstanceId(),OrganizationOwns.class);
        if(organizationOwnsList.size()>0) {
            for(OrganizationOwns ed : organizationOwnsList) {
                if(ed.getResourceEntity().equals("Facility")){
                    FacilityAPI api = new FacilityAPI("Facility", Facility.class);
                    o.addOwnedFacilities(api.retrieve(ed.getEntityInstanceId()));
                }
                if(ed.getResourceEntity().equals("Equipment")){
                    EquipmentAPI api = new EquipmentAPI("Equipment", Equipment.class);
                    o.addOwnedEquipments(api.retrieve(ed.getEntityInstanceId()));
                }
            }
        }

        if(edmobj.getOrganizationMemberofsByInstanceId().size()>0) {
            for(OrganizationMemberof ed : edmobj.getOrganizationMemberofsByInstanceId()) {
                org.epos.eposdatamodel.Organization cp = retrieve(ed.getOrganization2InstanceId());
                o.addMemberOf(cp);
            }
        }

        return o;
    }

    @Override
    public LinkedEntity retrieveLinkedEntity(String instanceId) {
        Organization edmobj = (Organization) getDbaccess().getOneFromDBByInstanceId(instanceId, Organization.class).get(0);

        LinkedEntity o = new LinkedEntity();
        o.setInstanceId(edmobj.getInstanceId());
        o.setMetaId(edmobj.getMetaId());
        o.setUid(edmobj.getUid());
        o.setEntityType(EntityNames.ORGANIZATION.name());

        return o;
    }

}

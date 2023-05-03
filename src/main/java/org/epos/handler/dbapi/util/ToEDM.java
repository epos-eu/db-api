package org.epos.handler.dbapi.util;

import org.epos.eposdatamodel.Address;
import org.epos.handler.dbapi.model.EDMAddress;

import java.util.UUID;

public class ToEDM {

    public static EDMAddress generateEdmAddress(Address address) {
        EDMAddress edmAddres = new EDMAddress();
        edmAddres.setCountry(address.getCountry());
        edmAddres.setLocality(address.getLocality());
        edmAddres.setPostalCode(address.getPostalCode());
        edmAddres.setStreet(address.getStreet());
        edmAddres.setId(UUID.randomUUID().toString());
        return edmAddres;
    }

}

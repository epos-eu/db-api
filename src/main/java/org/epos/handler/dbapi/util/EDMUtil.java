package org.epos.handler.dbapi.util;

import org.epos.handler.dbapi.model.*;

import java.util.Map;

public class EDMUtil {

    public static final Map<String, Integer> stateValueMap;

    static {

        stateValueMap = Map.of(
                "PUBLISHED", 6,
                "SUBMITTED", 5,
                "DRAFT", 4,
                "DISCARDED", 1,
                "PLACEHOLDER", 2,
                "ARCHIVED", 3
        );
    }

    public static int compareEntityVersion(EDMOrganization o1, EDMOrganization o2) {
        return stateValueMap.get(o2.getState()) - stateValueMap.get(o1.getState());
    }

    public static int compareEntityVersion(EDMPerson o1, EDMPerson o2) {
        return stateValueMap.get(o2.getState()) - stateValueMap.get(o1.getState());
    }

    public static int compareEntityVersion(EDMPersonLite o1, EDMPersonLite o2) {
        return stateValueMap.get(o2.getState()) - stateValueMap.get(o1.getState());
    }

    public static int compareEntityVersion(EDMContactpoint o1, EDMContactpoint o2) {
        return stateValueMap.get(o2.getState()) - stateValueMap.get(o1.getState());
    }

    public static int compareEntityVersion(EDMDistribution o1, EDMDistribution o2) {
        return stateValueMap.get(o2.getState()) - stateValueMap.get(o1.getState());
    }

    public static int compareEntityVersion(EDMDataproduct o1, EDMDataproduct o2) {
        return stateValueMap.get(o2.getState()) - stateValueMap.get(o1.getState());
    }

    public static int compareEntityVersion(EDMWebservice o1, EDMWebservice o2) {
        return stateValueMap.get(o2.getState()) - stateValueMap.get(o1.getState());
    }

    public static int compareEntityVersion(EDMOperation o1, EDMOperation o2) {
        return stateValueMap.get(o2.getState()) - stateValueMap.get(o1.getState());
    }

    public static int compareEntityVersion(EDMService o1, EDMService o2) {
        return stateValueMap.get(o2.getState()) - stateValueMap.get(o1.getState());
    }

    public static int compareEntityVersion(EDMFacility o1, EDMFacility o2) {
        return stateValueMap.get(o2.getState()) - stateValueMap.get(o1.getState());
    }

    public static int compareEntityVersion(EDMEquipment o1, EDMEquipment o2) {
        return stateValueMap.get(o2.getState()) - stateValueMap.get(o1.getState());
    }
}

package org.epos.handler.dbapi.util;

import java.sql.Date;
import java.time.LocalDateTime;

public class DataUtil {

    public static Date convertFromLocalDataTimeToSqlDate(LocalDateTime dateToConvert) {
        return (dateToConvert == null) ? null : Date.valueOf(dateToConvert.toLocalDate());
    }

    public static LocalDateTime convertFromSqlDateToLocalDataTime(Date dateToConvert) {
        return (dateToConvert == null) ? null : dateToConvert.toLocalDate().atStartOfDay();
    }
}

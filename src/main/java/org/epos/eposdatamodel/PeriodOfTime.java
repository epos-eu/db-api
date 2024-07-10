package org.epos.eposdatamodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Objects;

/**
 * An interval of time that is named or defined by its start and end dates.
 */
public class PeriodOfTime extends EPOSDataModelEntity{

    /**
     * This property contains the END of the period
     **/
    @Schema(name = "startDate", description = "This property contains the END of the period", example = "2024-07-03T00:00:00", required = false)
    private LocalDateTime startDate;

    /**
     * This property contains the END of the period
     **/
    @Schema(name = "endDate", description = "This property contains the END of the period", example = "2024-07-03T00:00:00", required = false)
    private LocalDateTime endDate;

    public PeriodOfTime startDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Get startDate
     *
     * @return startDate
     **/

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate){
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
        this.startDate = LocalDateTime.parse(startDate,formatter);
    }

    public PeriodOfTime endDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Get endDate
     *
     * @return endDate
     **/

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(String endDate){
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
        this.endDate = LocalDateTime.parse(endDate,formatter);
    }

    @Override
    public String toString() {
        return "PeriodOfTime{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}'+ super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodOfTime that = (PeriodOfTime) o;
        return Objects.equals(getStartDate(), that.getStartDate()) && Objects.equals(getEndDate(), that.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDate(), getEndDate());
    }
}

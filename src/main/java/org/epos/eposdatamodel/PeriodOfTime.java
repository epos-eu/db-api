package org.epos.eposdatamodel;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * An interval of time that is named or defined by its start and end dates.
 */
public class PeriodOfTime {

    /**
     * This property contains the END of the period
     **/
    private LocalDateTime startDate;

    /**
     * This property contains the END of the period
     **/
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

    @Override
    public String toString() {
        return "PeriodOfTime{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
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

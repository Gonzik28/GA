package com.example.test;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "statistics")
public class Statistic {
    @Id
    private Timestamp timeSave;
    private long sum;
    private long increment;

    @PrePersist
    public void prePersist() {
        timeSave = new Timestamp(System.currentTimeMillis());
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public void setIncrement(long increment) {
        this.increment = increment;
    }

    public long getIncrement() {
        return increment;
    }
}

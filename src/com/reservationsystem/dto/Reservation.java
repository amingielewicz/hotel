package com.reservationsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reservation {
    private int id = 1;
    private int customerId;
    private LocalDate startReservationDate;
    private LocalDate endReservationDate;
    private BigDecimal sum;
    private BigDecimal deposit;
    private boolean isFullPaid;
    private int employerId;

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDate getStartReservationDate() {
        return startReservationDate;
    }

    public LocalDate getEndReservationDate() {
        return endReservationDate;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public boolean isFullPaid() {
        return isFullPaid;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", startReservationDate=" + startReservationDate +
                ", endReservationDate=" + endReservationDate +
                ", sum=" + sum +
                ", deposit=" + deposit +
                ", isFullPaid=" + isFullPaid +
                ", employerId=" + employerId +
                '}';
    }

    public Reservation(int customerId, LocalDate startReservationDate, LocalDate endReservationDate,
                       BigDecimal sum, BigDecimal deposit, boolean isFullPaid, int employerId) {
        this.customerId = customerId;
        this.startReservationDate = startReservationDate;
        this.endReservationDate = endReservationDate;
        this.sum = sum;
        this.deposit = deposit;
        this.isFullPaid = isFullPaid;
        this.employerId = employerId;
    }
    public static ReservationBuilder builder(){
        return new ReservationBuilder();
    }
    public static class ReservationBuilder {
        private int customerId;
        private LocalDate startReservationDate;
        private LocalDate endReservationDate;
        private BigDecimal sum;
        private BigDecimal deposit;
        private boolean isFullPaid;
        private int employerId;

        public ReservationBuilder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }
        public ReservationBuilder startReservationDate(LocalDate startReservationDate) {
            this.startReservationDate = startReservationDate;
            return this;
        }
        public ReservationBuilder endReservationDate(LocalDate endReservationDate) {
            this.endReservationDate = endReservationDate;
            return this;
        }
        public ReservationBuilder sum(BigDecimal sum) {
            this.sum = sum;
            return  this;
        }
        public ReservationBuilder deposit(BigDecimal deposit) {
            this.deposit = deposit;
            return this;
        }
        public ReservationBuilder isFullPaid(boolean isFullPaid) {
            this.isFullPaid = isFullPaid;
            return this;
        }
        public  ReservationBuilder employerId(int employerId) {
            this.employerId = employerId;
            return this;
        }
        public Reservation build() {
            return new Reservation(customerId, startReservationDate, endReservationDate, sum, deposit, isFullPaid, employerId);
        }
    }
}

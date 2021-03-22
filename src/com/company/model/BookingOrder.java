package com.company.model;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class BookingOrder implements Comparable<BookingOrder> {
    //regionPROPERTIES
    private Customer customer;
    private Room room;
    private LocalDate fromDate;
    private LocalDate toDate;
    //endregion

    //region CONSTRUCTOR
    public BookingOrder() {
    }

    public BookingOrder(Customer customer, Room room, LocalDate fromDate, LocalDate toDate) {
        this.customer = customer;
        this.room = room;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    //endregion

    //region SETTER_AND_GETTER
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    //endregion

    //region TO_STRING

    @Override
    public String toString() {
        return customer + "," + room + "," + fromDate + "," + toDate;
    }

    //endregion

    //regionMETHOD
    public double getBill() {
        double bill = DAYS.between(fromDate, toDate) * room.getPrice();
        return bill;
    }

    @Override
    public int compareTo(BookingOrder bookingOrder) {
        if (this.getFromDate().isAfter(bookingOrder.getFromDate())) {
            return 1;
        } else if(this.getFromDate().isBefore(bookingOrder.getFromDate())){
            return -1;
        } else return 0;
    }
    //endregion
}


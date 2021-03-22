package com.company.controller;

import com.company.model.BookingOrder;
import com.company.model.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingOrderManager {
    public static List<Room> roomList;
    public static List<BookingOrder> bookingOrderList;

    static {
        roomList = new ArrayList<>();
        roomList.add(new Room("201", "Phòng VIP 1", 200000.00));
        roomList.add(new Room("202", "Phòng VIP 1", 200000.00));
        roomList.add(new Room("203", "Phòng VIP 2", 300000.00));
        roomList.add(new Room("204", "Phòng VIP 2", 300000.00));
        roomList.add(new Room("205", "Phòng VIP 3", 500000.00));
        roomList.add(new Room("206", "Phòng VIP 3", 500000.00));
        roomList.add(new Room("207", "Phòng VIP 3", 500000.00));
        roomList.add(new Room("208", "Phòng VIP 3", 500000.00));
        roomList.add(new Room("209", "Phòng VIP 3", 500000.00));
        roomList.add(new Room("210", "Phòng VIP 1", 200000.00));

        bookingOrderList = new ArrayList<>();
    }

    public Room getRoomByName(String nameRoom) {
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getNameRoom().equals(nameRoom)) {
                return roomList.get(i);
            }
        }
        return null;
    }

    public boolean isEmptyRoom(Room room, LocalDate fromDate, LocalDate toDate) {
        boolean addAble = true;
        boolean beforeNow = fromDate.isBefore(LocalDate.now());
        boolean fromBeforeTo = fromDate.isAfter(toDate);
        if (beforeNow || fromBeforeTo) {
            addAble = false;
        }

        for (BookingOrder bo : bookingOrderList) {
            if (room.getNameRoom().equals(bo.getRoom().getNameRoom())) {
                boolean conflictTime = !(fromDate.isAfter(bo.getToDate()) || toDate.isBefore(bo.getFromDate()));
                if (conflictTime) {
                    addAble = false;
                    break;
                }
            }
        }
        return addAble;
    }

    public List<Room> findEmptyRoomsByDate(LocalDate fromDate, LocalDate toDate) {
        List<Room> emptyRoomList = new ArrayList<>();
        for (Room room : roomList) {
            if (isEmptyRoom(room, fromDate, toDate)) {
                emptyRoomList.add(room);
            }
        }
        return emptyRoomList;
    }

    public void displayRoomList(List<Room> roomList) {
        System.out.println("Số phòng,Loại phòng,Đơn giá");
        for (Room room : roomList) {
            System.out.println(room);
        }
    }

    public boolean addOrder(BookingOrder bookingOrder) {
        boolean addAble = isEmptyRoom(bookingOrder.getRoom(), bookingOrder.getFromDate(), bookingOrder.getToDate());
        if (addAble) {
            bookingOrderList.add(bookingOrder);
        }
        return addAble;
    }

    public BookingOrder findOrderByRoom(String nameRoom) {
        for (BookingOrder bookingOrder : bookingOrderList) {
            if (bookingOrder.getRoom().getNameRoom().equals(nameRoom)) {
                return bookingOrder;
            }
        }
        return null;
    }

    public boolean deleteOrder(String nameRoom) {
        if (findOrderByRoom(nameRoom) != null) {
            bookingOrderList.remove(findOrderByRoom(nameRoom));
            return true;
        }
        return false;
    }

    public void sortOrderByFromDate(List<BookingOrder> list) {
        Collections.sort(list);
    }

    public void displayOderList(List<BookingOrder> bookingOrderList) {
        System.out.println("Tên khách hàng,Địa chỉ,Số điện thoại,Số CMND,Số Phòng,Loại Phòng,Đơn giá,Ngày thuê,Ngày trả");
        for (BookingOrder bookingOrder : bookingOrderList) {
            System.out.println(bookingOrder);
        }
    }
}





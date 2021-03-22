package com.company.io;

import com.company.controller.BookingOrderManager;
import com.company.model.BookingOrder;
import com.company.model.Customer;
import com.company.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputOutput {

    static Scanner scanner = new Scanner(System.in);
    BookingOrderManager bookingOrderManager = new BookingOrderManager();

    //region INPUT_CUSTOMER
    public static String inputName() {
        System.out.println("Nhập Họ và tên:");
        String name = scanner.nextLine();
        return name;
    }

    public static String inputAddress() {
        System.out.println("Nhập địa chỉ:");
        String address = scanner.nextLine();
        return address;
    }

    public static String inputIdCard() {
        final String ID_CARD_REGEX = "[0-9]{9}";
        String idCard;
        do {
            System.out.println("Nhập số CMND:");
            idCard = scanner.nextLine();
        } while (!Pattern.matches(ID_CARD_REGEX, idCard));
        return idCard;
    }

    public static String inputPhoneNumber() {
        final String PHONE_NUMBER_REGEX = "(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}";
        String phoneNumber;
        do {
            System.out.println("Nhập số điện thoại:");
            phoneNumber = scanner.nextLine();
        } while (!Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber));
        return phoneNumber;
    }

    public static Customer inputCustomer() {
        Customer customer = new Customer(inputName(), inputAddress(), inputPhoneNumber(), inputIdCard());
        return customer;
    }

    //endregion

    //regionINPUT_DATE
    public static LocalDate inputFromDate() {
        System.out.println("Nhập ngày thuê phòng:");
        return getDate();
    }

    public static LocalDate inputToDate() {
        System.out.println("Nhập ngày trả phòng:");
        return getDate();
    }

    private static LocalDate getDate() {
        while (true) {
            try {
                System.out.println("Nhập ngày");
                int day = Integer.parseInt(scanner.nextLine());

                System.out.println("Nhập tháng");
                int month = Integer.parseInt(scanner.nextLine());

                System.out.println("Nhập năm");
                int year = Integer.parseInt(scanner.nextLine());
                if (year < 2021) throw new Exception();

                return LocalDate.of(year, month, day);
            } catch (Exception e) {
                System.out.println("Nhập sai, Mời nhập lại");
            }
        }
    }
    //endregion

    //regionINPUT_ROOM

    public static String inputNameRoom() {
        final String NAME_ROOM_REGEX = "[2][0|1][0-9]";
        String nameRoom;
        do {
            System.out.println("Nhập số phòng từ 201 đến 210:");
            nameRoom = scanner.nextLine();
        } while (!Pattern.matches(NAME_ROOM_REGEX, nameRoom));
        return nameRoom;
    }

    public static String inputTypeRoom() {
        System.out.println("Nhập loại phòng:");
        String typeRoom = scanner.nextLine();
        return typeRoom;
    }

    public static double inputPriceRoom() {
        while (true) {
            try {
                System.out.println("Nhập giá phòng:");
                double price = Double.parseDouble(scanner.nextLine());
                return price;
            } catch (NumberFormatException e) {
                System.out.println("Nhập lại giá phòng");
            }
        }
    }
    //  endregion

    //regionOUTPUT
    public void displayBookingOrder() {
        bookingOrderManager.sortOrderByFromDate(bookingOrderManager.bookingOrderList);
        bookingOrderManager.displayOderList(bookingOrderManager.bookingOrderList);
    }

    public void displayEmptyRooms() {
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.of(10000, 12, 1);
        List<Room> emptyRooms = bookingOrderManager.findEmptyRoomsByDate(fromDate, toDate);
        if (!emptyRooms.isEmpty()) {
            System.out.println("Danh sánh phòng trống:");
            bookingOrderManager.displayRoomList(emptyRooms);
        } else {
            System.out.println("Hiện tại không có phòng nào trống");
        }
    }

    public void displayEmptyRoomsByDate() {
        LocalDate fromDate = InputOutput.inputFromDate();
        LocalDate toDate = LocalDate.of(10000, 12, 1);
        List<Room> emptyRooms = bookingOrderManager.findEmptyRoomsByDate(fromDate, toDate);
        if (!emptyRooms.isEmpty()) {
            System.out.println("Danh sánh phòng trống:");
            bookingOrderManager.displayRoomList(emptyRooms);
        } else {
            System.out.println("Không tìm thấy phòng nào trống");
        }
    }

    public void addBookingOrder(){
        String nameRoom = inputNameRoom();
        Room room = bookingOrderManager.getRoomByName(nameRoom);

        Customer customer = inputCustomer();

        LocalDate fromDate = inputFromDate();
        LocalDate toDate = inputToDate();

        BookingOrder bookingOrder = new BookingOrder(customer, room, fromDate, toDate);

        if (bookingOrderManager.addOrder(bookingOrder)) {
            System.out.println("Đã đặt phòng thành công.");
        } else {
            System.out.println("Vui lòng chọn phòng khác hoặc ngày khác");
        }
    }

    public void deleteBookingOrder(){
        String nameRoom = inputNameRoom();
        if (bookingOrderManager.deleteOrder(nameRoom)) {
            System.out.println("Đã hủy thành công");
        } else {
            System.out.println("Không tìm thấy phòng này");
        }
    }

    public void showBill(){
        String nameRoom = inputNameRoom();
        double bill = bookingOrderManager.findOrderByRoom(nameRoom).getBill();
        if (bookingOrderManager.deleteOrder(nameRoom)) {
            System.out.println("Số tiền cần thanh toán: " + bill + "vnđ");
        } else {
            System.out.println("Không tìm thấy phòng này");
        }
    }
    //endregion

}

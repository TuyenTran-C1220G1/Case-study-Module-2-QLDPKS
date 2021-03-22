package com.company;

import com.company.controller.BookingOrderManager;
import com.company.io.IOFile;
import com.company.io.InputOutput;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingOrderManager bookingOrderManager = new BookingOrderManager();
        bookingOrderManager.bookingOrderList.clear();
        bookingOrderManager.bookingOrderList.addAll(IOFile.ReadFromFile());
        InputOutput inputOutput = new InputOutput();

        int selector = 0;
        do {
            showMenu();
            try {
                selector = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                selector = 10;
            }

            switch (selector) {
                case 1:
                    inputOutput.displayBookingOrder();
                    break;
                case 2:
                    inputOutput.displayEmptyRooms();
                    break;
                case 3:
                    inputOutput.displayEmptyRoomsByDate();
                    break;
                case 4:
                    inputOutput.addBookingOrder();
                    break;
                case 5:
                    inputOutput.deleteBookingOrder();
                    break;
                case 6:
                    inputOutput.showBill();
                    break;
                case 0:
                    IOFile.writeToFile(bookingOrderManager.bookingOrderList);
                    System.exit(0);
                default:
                    System.out.println("Nhập sai. Mời nhập lại:");
            }
        } while (selector != 0);

    }

    private static void showMenu() {
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("-----CHƯƠNG TRÌNH QUẢN LÝ ĐẶT PHÒNG KHÁCH SẠN-----");
        System.out.println("Chọn chức năng theo số để tiếp tục chương trình:");
        System.out.println("1. Hiện thị danh sách khách hàng đang thuê");
        System.out.println("2. Hiện thị danh sách phòng trống");
        System.out.println("3. Tìm kiếm phòng trống theo ngày");
        System.out.println("4. Đặt phòng");
        System.out.println("5. Hủy đặt phòng");
        System.out.println("6. Thanh toán");
        System.out.println("0. Thoát");
        System.out.println("Chọn chức năng:");
    }
}

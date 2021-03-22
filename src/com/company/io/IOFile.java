package com.company.io;

import com.company.model.BookingOrder;
import com.company.model.Customer;
import com.company.model.Room;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    static final String PATH = "booking_oder.csv";
    //region WRITE_TO_FILE
    public static void writeToFile(List<BookingOrder> bookingOrderList) {
        File file = new File(PATH);
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (BookingOrder bookingOrder : bookingOrderList) {
                bufferedWriter.write(bookingOrder.toString() + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region READ_FROM_FILE
    public static List<BookingOrder> ReadFromFile() {
        File file = new File(PATH);
        List<BookingOrder> bookingOrderList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arrBookingOrder = line.split(",");

                String name = arrBookingOrder[0];
                String address = arrBookingOrder[1];
                String phoneNumber = arrBookingOrder[2];
                String idCard = arrBookingOrder[3];
                Customer customer = new Customer(name, address, phoneNumber, idCard);

                String nameRoom = arrBookingOrder[4];
                String typeRoom = arrBookingOrder[5];
                Double price = Double.parseDouble(arrBookingOrder[6]);
                Room room = new Room(nameRoom, typeRoom, price);

                String[] arrFromDate = arrBookingOrder[7].split("-");
                LocalDate fromDate = LocalDate.of(Integer.parseInt(arrFromDate[0]), Integer.parseInt(arrFromDate[1]), Integer.parseInt(arrFromDate[2]));

                String[] arrToDate = arrBookingOrder[8].split("-");
                LocalDate toDate = LocalDate.of(Integer.parseInt(arrToDate[0]), Integer.parseInt(arrToDate[1]), Integer.parseInt(arrToDate[2]));

                BookingOrder bookingOrder = new BookingOrder(customer, room, fromDate, toDate);

                bookingOrderList.add(bookingOrder);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookingOrderList;
    }
    //endregion
}


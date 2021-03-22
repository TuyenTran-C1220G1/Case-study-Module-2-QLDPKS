package com.company.model;

public class Room {

    //regionPROPERTIES
    private String nameRoom;
    private String type;
    private double price;
    //endregion

    //region CONSTRUCTOR
    public Room() {
    }

    public Room(String nameRoom, String type, double price) {
        this.nameRoom = nameRoom;
        this.type = type;
        this.price = price;
    }
    //endregion

    //region SETTER AND GETTER

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //endregion

    //region TO_STRING

    @Override
    public String toString() {
        return nameRoom + "," + type + "," + price;
    }
    //endregion
}

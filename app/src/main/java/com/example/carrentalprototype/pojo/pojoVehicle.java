package com.example.carrentalprototype.pojo;

public class pojoVehicle {
    private String vehicleId;
    private String title;
    private String hourRate;
    private String freeCancelation;
    private  String numberPlate;
    private String clas;
    private String seats;
    private String type;
    private String doors;
    private String ac;
    private String autoTransmision;
    private int pickPlaceId;
    private String millage;
    private String model;
    private String meterReading;
    private String vehicleImage;

    public pojoVehicle() {
    }

    public pojoVehicle(String title, String seats, String clas, String vehicleId, String doors, String millage, String model,String hourRate,String vehicleImage) {
        this.title = title;
        this.seats = seats;
        this.clas = clas;
        this.vehicleId=vehicleId;
        this.doors=doors;
        this.millage=millage;
        this.model=model;
        this.hourRate=hourRate;
        this.vehicleImage =vehicleImage;

    }



    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(String vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHourRate() {
        return hourRate;
    }

    public void setHourRate(String hourRate) {
        this.hourRate = hourRate;
    }

    public String getFreeCancelation() {
        return freeCancelation;
    }

    public void setFreeCancelation(String freeCancelation) {
        this.freeCancelation = freeCancelation;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getAutoTransmision() {
        return autoTransmision;
    }

    public void setAutoTransmision(String autoTransmision) {
        this.autoTransmision = autoTransmision;
    }

    public int getPickPlaceId() {
        return pickPlaceId;
    }

    public void setPickPlaceId(int pickPlaceId) {
        this.pickPlaceId = pickPlaceId;
    }

    public String getMillage() {
        return millage;
    }

    public void setMillage(String millage) {
        this.millage = millage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(String meterReading) {
        this.meterReading = meterReading;
    }
}





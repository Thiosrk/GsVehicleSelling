package com.gsvehicleselling.Model;

import java.util.List;

public class VehicleVO {

    private int id;

    private String model;//汽车型号

    private Engine engine;//发动机

    private DrivingModel drivingmode;//驱动方式

    private Tyre tyre;//轮胎

    private Gearbox gearbox;//变速箱

    private double price;//汽车价格

    private String image;//图片地址

    public VehicleVO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.model = vehicle.getModel();
        this.price = vehicle.getPrice();
        this.image = vehicle.getImage();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public DrivingModel getDrivingmode() {
        return drivingmode;
    }

    public void setDrivingmode(DrivingModel drivingmode) {
        this.drivingmode = drivingmode;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

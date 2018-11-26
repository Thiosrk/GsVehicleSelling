package com.gsvehicleselling.Model;

public class GoodsVO {

    Vehicle vehicle;

    Engine engine;

    Gearbox gearbox;

    Tyre tyre;

    Components components;

    String type;//物品类型（五选一）

    public GoodsVO(){}

    public GoodsVO(Vehicle vehicle){
        this.vehicle = vehicle;
        this.type = "vehicle";
    }
    public GoodsVO(Engine engine){
        this.engine = engine;
        this.type = "engine";
    }
    public GoodsVO(Gearbox gearbox){
        this.gearbox = gearbox;
        this.type = "gearbox";
    }
    public GoodsVO(Tyre tyre){
        this.tyre = tyre;
        this.type = "tyre";
    }
    public GoodsVO(Components components){
        this.components = components;
        this.type = "components";
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

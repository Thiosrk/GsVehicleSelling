package com.gsvehicleselling.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {

  @Id
  @GeneratedValue
  private int id;

  private String model;//汽车型号

  private int engineid;//发动机

  private int drivingmodeid;//驱动方式

  private int tyreid;//轮胎

  private int gearboxid;//变速箱

  private double price;//汽车价格

  private String image;//图片地址

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

  public int getEngineid() {
    return engineid;
  }

  public void setEngineid(int engineid) {
    this.engineid = engineid;
  }

  public int getDrivingmodeid() {
    return drivingmodeid;
  }

  public void setDrivingmodeid(int drivingmodeid) {
    this.drivingmodeid = drivingmodeid;
  }

  public int getTyreid() {
    return tyreid;
  }

  public void setTyreid(int tyreid) {
    this.tyreid = tyreid;
  }

  public int getGearboxid() {
    return gearboxid;
  }

  public void setGearboxid(int gearboxid) {
    this.gearboxid = gearboxid;
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

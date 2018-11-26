package com.gsvehicleselling.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "engine")
public class Engine {

  @Id
  @GeneratedValue
  private int id;

  private String type;

  private double displacement;

  private String intake;

  private String material;

  private String image;

  private double price;

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public double getDisplacement() {
    return displacement;
  }

  public void setDisplacement(double displacement) {
    this.displacement = displacement;
  }


  public String getIntake() {
    return intake;
  }

  public void setIntake(String intake) {
    this.intake = intake;
  }


  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

}

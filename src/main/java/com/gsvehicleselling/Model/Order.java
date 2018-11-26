package com.gsvehicleselling.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue
  private int id;

  private Date date;//订单日期

  private int userid;//用户id

  private int goodsid;//物品id

  private String goodstype;//物品种类

  private String goodsname;//物品名称

  private double price;//订单价格

  private int ispay;//是否已经付款 0：未付，1：已付

  private String consigneename;//收件人姓名

  private String consigneetel;//收件人电话

  private String consigneeaddress;//收件人地址

  public Order(String date, int userid, int goodsid, String goodstype, String goodsname, double price, int ispay, String consigneename, String consigneetel, String consigneeaddress) {

    this.date = stringTodate(date);
    this.userid = userid;
    this.goodsid = goodsid;
    this.goodstype = goodstype;
    this.goodsname = goodsname;
    this.price = price;
    this.ispay = ispay;
    this.consigneename = consigneename;
    this.consigneetel = consigneetel;
    this.consigneeaddress = consigneeaddress;


  }

  public Order(){}

  public String getGoodsname() {
    return goodsname;
  }

  public void setGoodsname(String goodsname) {
    this.goodsname = goodsname;
  }

  public String getGoodstype() {
    return goodstype;
  }

  public void setGoodstype(String goodstype) {
    this.goodstype = goodstype;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getDate() {
      return date.toString();
  }

  public void setDate(String date) {
    this.date = stringTodate(date);
  }


  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }


  public int getGoodsid() {
    return goodsid;
  }

  public void setGoodsid(int goodsid) {
    this.goodsid = goodsid;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public int getIspay() {
    return ispay;
  }

  public void setIspay(int ispay) {
    this.ispay = ispay;
  }


  public String getConsigneename() {
    return consigneename;
  }

  public void setConsigneename(String consigneename) {
    this.consigneename = consigneename;
  }


  public String getConsigneetel() {
    return consigneetel;
  }

  public void setConsigneetel(String consigneetel) {
    this.consigneetel = consigneetel;
  }


  public String getConsigneeaddress() {
    return consigneeaddress;
  }

  public void setConsigneeaddress(String consigneeaddress) {
    this.consigneeaddress = consigneeaddress;
  }

  private Date stringTodate(String strdate){

    String str = strdate;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date d = null;
    try {
      d = format.parse(str);
    } catch (Exception e) {
      e.printStackTrace();
    }
    Date date = new java.sql.Date(d.getTime());
    return date;
  }
}

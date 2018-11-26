package com.gsvehicleselling.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue
  private int id;

  private String nickname;//昵称

  private String tel;//电话

  private String password;//密码

  private Date birthday;//生日

  private int authority;//权限 0：用户，1：管理员

  private double money;//账户余额

    public User(){}

    public User(String nickname,String tel,String password,String date,int authority){
        this.nickname = nickname;
        this.tel = tel;
        this.password = password;
        this.birthday = stringTodate(date);
        this.authority = authority;
        this.money = 0.0;
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


  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getBirthday() {
    return birthday.toString();
  }

  public void setBirthday(String birthday) {
    this.birthday = stringTodate(birthday);
  }


  public long getAuthority() {
    return authority;
  }

  public void setAuthority(int authority) {
    this.authority = authority;
  }


  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }

}

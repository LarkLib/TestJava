package com.company.postgres;


public class Persion {

  private String id;
  private long seq;
  private long age;
  private String name;
  private String wage;
  private java.sql.Date hiredate;
  private java.sql.Timestamp lastupdatetime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public long getSeq() {
    return seq;
  }

  public void setSeq(long seq) {
    this.seq = seq;
  }


  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getWage() {
    return wage;
  }

  public void setWage(String wage) {
    this.wage = wage;
  }


  public java.sql.Date getHiredate() {
    return hiredate;
  }

  public void setHiredate(java.sql.Date hiredate) {
    this.hiredate = hiredate;
  }


  public java.sql.Timestamp getLastupdatetime() {
    return lastupdatetime;
  }

  public void setLastupdatetime(java.sql.Timestamp lastupdatetime) {
    this.lastupdatetime = lastupdatetime;
  }

}

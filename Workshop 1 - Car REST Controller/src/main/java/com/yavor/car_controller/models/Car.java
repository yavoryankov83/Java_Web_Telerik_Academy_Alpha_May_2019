package com.yavor.car_controller.models;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class Car {

  @PositiveOrZero
  private int id;

  @Size(min = 2, max = 8, message = "Model/Type should be between 2 and 8 symbols.")
  private String model, type;

  public Car() {
    this(0, "", "");
  }

  public Car(int id, String model, String type) {
    this.id = id;
    this.model = model;
    this.type = type;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}

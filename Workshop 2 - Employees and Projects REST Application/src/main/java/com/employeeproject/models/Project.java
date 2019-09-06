package com.employeeproject.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Component
public class Project {

  @PositiveOrZero
  private int id;

  @Size(min = 3, max = 15, message = "Name should be between 3 and 15 symbols.")
  private String name;

  public Project() {
    this(0, "");
  }

  public Project(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

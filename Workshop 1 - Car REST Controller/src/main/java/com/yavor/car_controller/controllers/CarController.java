package com.yavor.car_controller.controllers;

import com.yavor.car_controller.models.Car;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/cars")
public class CarController {

  private static final String CAR_NOT_EXIST_EXCEPTION =
          "Car with ID %d does not exist";

  private List<Car> cars;

  public CarController() {
    cars = new ArrayList<>();
    cars.add(new Car(1, "Audi", "Hatchback"));
    cars.add(new Car(2, "WV", "Combi"));
    cars.add(new Car(3, "Porche", "Sport"));
    cars.add(new Car(4, "Porche", "Carera"));
  }

  @GetMapping
  public List<Car> getAllCars() {
    return cars;
  }

  @GetMapping(path = "{id}")
  public Car getCarById(@Valid @PathVariable(name = "id") int carId) {
    if (!isCarExists(carId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(CAR_NOT_EXIST_EXCEPTION, carId));
    }
    return cars
            .stream()
            .filter(car -> car.getId() == carId)
            .findFirst()
            .get();
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  public void createCar(@Valid @RequestBody Car car) {
    cars.add(car);
  }

  @PutMapping(path = "{id}")
  public void updateCar(@Valid @PathVariable(name = "id") int carId, @Valid @RequestBody Car car) {
    if (!isCarExists(carId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(CAR_NOT_EXIST_EXCEPTION, carId));
    }
    Car carToChange = getCarById(carId);
    if (car.getModel() != null){
      carToChange.setModel(car.getModel());
    }
    if (car.getType() != null){
      carToChange.setType(car.getType());
    }
  }

  @DeleteMapping(path = "{id}")
  public void deleteCar(@Valid @PathVariable(name = "id") int carId) {
    if (!isCarExists(carId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(CAR_NOT_EXIST_EXCEPTION, carId));
    }
    Car carToRemove = getCarById(carId);
    cars.remove(carToRemove);
  }

  @GetMapping(path = "id_filter")
  public List<Car> filterAllCarsByMultipleId(@Valid @RequestParam(required = false, name = "id") List<Integer> carId) {
    return cars
            .stream()
            .filter(car -> carId.contains(car.getId()))
            .collect(Collectors.toList());
  }

  @GetMapping(path = "filter")
  public List<Car> carFilter(@Valid @RequestParam(required = false) String model,
                             @Valid @RequestParam(required = false) String type) {
    if (model != null && type != null) {
      return filterCarsByModelAndType(model, type);
    }
    if (model != null) {
      return filterCarsByModel(model);
    }
    if (type != null) {
      return filterCarsByType(type);
    } else {
      return Collections.emptyList();
    }
  }

  private List<Car> filterCarsByModelAndType(@RequestParam(required = false) @Valid String model, @RequestParam(required = false) @Valid String type) {
    return getAllCars()
            .stream()
            .filter(car -> car.getModel().equals(model) && car.getType().equals(type))
            .collect(Collectors.toList());
  }

  private List<Car> filterCarsByType(@RequestParam(required = false) @Valid String type) {
    return getAllCars()
            .stream()
            .filter(car -> car.getType().equals(type))
            .collect(Collectors.toList());
  }

  private List<Car> filterCarsByModel(@RequestParam(required = false) @Valid String model) {
    return getAllCars()
            .stream()
            .filter(car -> car.getModel().equals(model))
            .collect(Collectors.toList());
  }

  private boolean isCarExists(int carId){
    return getAllCars()
            .stream()
            .anyMatch(car -> car.getId() == carId);
  }
}

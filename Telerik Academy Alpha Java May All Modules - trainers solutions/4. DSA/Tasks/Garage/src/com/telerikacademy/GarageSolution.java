package com.telerikacademy;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class GarageSolution {

    private static void fakeInput() {
        String test = "add AlfaRomeo147 Hatchback 120\n" +
                "add SubaruForester Combi 163\n" +
                "add VolvoV40 Combi 110\n" +
                "add VolkswagenPassat Sedan 180\n" +
                "add MercedesSLK Hatchback 280\n" +
                "add SkodaOktavia Sedan 200\n" +
                "add SkodaSuperb Combi 300\n" +
                "add VolkswagenPassat Sedan 180\n" +
                "power 3\n" +
                "find Combi\n" +
                "find Hatchback\n" +
                "remove AudiQ7\n" +
                "remove MercedesSLK\n" +
                "power 3\n" +
                "find AudiQ7\n" +
                "find Hatchback\n" +
                "end";

        System.setIn(new ByteArrayInputStream(test.getBytes()));
    }

    private static Map<String, Car> cars = new HashMap<>();
    private static Set<Car> sortedCars = new TreeSet<>();
    private static Map<String, Set<Car>> types = new HashMap<>();

    public static void main(String[] args) throws IOException {
        fakeInput();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input;

        read:
        while (true) {

            input = reader.readLine().split(" ");

            if (input[0].equalsIgnoreCase("end")) {
                break;
            }

            switch (input[0]) {
                case "end": {
                    break read;
                }
                case "add": {
                    String model = input[1];
                    String type = input[2];
                    int hp = Integer.parseInt(input[3]);

                    if (cars.containsKey(model)) {
                        System.out.printf("FAIL: %s already exists!%n", model);
                        break;
                    }

                    if (!types.containsKey(type)) {
                        Set<Car> carTypes = new TreeSet<>();
                        types.put(type, carTypes);
                    }

                    Car newCar = new Car(model, type, hp);

                    cars.put(model, newCar);
                    sortedCars.add(newCar);
                    types.get(type).add(newCar);

                    System.out.printf("SUCCESS: %s added!%n", model);
                    break;
                }
                case "remove": {
                    String model = input[1];
                    if (cars.containsKey(model)) {

                        Car carToBeRemoved = cars.remove(model);
                        sortedCars.remove(carToBeRemoved);
                        types.get(carToBeRemoved.getType()).remove(carToBeRemoved);

                        System.out.printf("SUCCESS: %s removed!%n", carToBeRemoved.getModel());

                    } else {
                        System.out.printf("FAIL: %s could not be found!%n", model);
                    }
                    break;
                }
                case "find": {
                    String type = input[1];

                    StringBuilder builder = new StringBuilder();
                    System.out.print("RESULT: ");

                    if (types.containsKey(type)) {
                        types.get(type)
                                .stream()
                                .limit(10)
                                .forEach(car -> builder.append(car.printForFind()).append(", "));
                    }

                    if (builder.length() > 0) {
                        System.out.print(builder.deleteCharAt(builder.length() - 2));
                    }

                    System.out.println();
                    break;
                }
                case "power": {
                    int wantedCount = Integer.parseInt(input[1]);

                    StringBuilder builder = new StringBuilder();
                    System.out.print("RESULT: ");

                    sortedCars.stream()
                            .limit(wantedCount)
                            .forEach(car -> builder.append(car.printForFind()).append(", "));

                    if (builder.length() > 0) {
                        System.out.print(builder.deleteCharAt(builder.length() - 2));
                    }

                    System.out.println();
                    break;
                }
            }

        }
    }

    static class Car implements Comparable<Car> {
        String model;
        String type;
        int horsePower;

        public Car(String model, String type, int horsePower) {
            setModel(model);
            setType(type);
            setHorsePower(horsePower);
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

        public int getHorsePower() {
            return horsePower;
        }

        public void setHorsePower(int horsePower) {
            this.horsePower = horsePower;
        }

        public String printForFind() {
            return String.format("%s[%s](%d)",
                    this.getModel(), this.getType(), this.getHorsePower());
        }

        @Override
        public int compareTo(Car carToCompare) {

            int result = Integer.compare(carToCompare.horsePower, this.horsePower);
            if (result == 0) {
                result = this.model.compareTo(carToCompare.model);
            }
            return result;

        }
    }
}


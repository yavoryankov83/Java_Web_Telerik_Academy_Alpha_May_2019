package com.telerikacademy;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class GarageSolutionRefactored {

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

        while (true) {
            input = reader.readLine().split(" ");
            if ("end".equals(input[0])) {
                break;
            } else if ("add".equals(input[0])) {
                String model = input[1];
                String type = input[2];
                int horsePower = Integer.parseInt(input[3]);

                System.out.println(addNewCarCommand(model, type, horsePower));
            } else if ("remove".equals(input[0])) {
                String model = input[1];

                System.out.println(removeCarCommand(model));
            } else if ("find".equals(input[0])) {
                String type = input[1];

                System.out.println(findCarCommand(type));
            } else if ("power".equals(input[0])) {
                int wantedCount = Integer.parseInt(input[1]);

                System.out.println(getMostPowerfulCarsCommand(wantedCount));
            }

        }
    }

    private static String getMostPowerfulCarsCommand(int wantedCount) {
        StringBuilder builder = new StringBuilder("RESULT: ");

        builder.append(sortedCars
                .stream()
                .limit(wantedCount)
                .map(Car::printForFind)
                .collect(Collectors.joining(", ")));

        return builder.toString();
    }

    private static String findCarCommand(String type) {
        StringBuilder builder = new StringBuilder("RESULT: ");

        if (types.containsKey(type)) {
            builder.append(types
                    .get(type)
                    .stream()
                    .limit(10)
                    .map(Car::printForFind)
                    .collect(Collectors.joining(", ")));
        }

        return builder.toString();
    }

    private static String removeCarCommand(String model) {
        if (cars.containsKey(model)) {

            Car carToBeRemoved = cars.remove(model);
            sortedCars.remove(carToBeRemoved);
            types.get(carToBeRemoved.getType()).remove(carToBeRemoved);

            return String.format("SUCCESS: %s removed!", carToBeRemoved.getModel());

        } else {
            return String.format("FAIL: %s could not be found!", model);
        }
    }

    private static String addNewCarCommand(String model, String type, int horsePower) {

        if (cars.containsKey(model)) {
            return String.format("FAIL: %s already exists!", model);
        }

        if (!types.containsKey(type)) {
            Set<Car> carTypes = new TreeSet<>();
            types.put(type, carTypes);
        }

        Car newCar = new Car(model, type, horsePower);

        sortedCars.add(newCar);
        cars.put(model, newCar);
        types.get(type).add(newCar);

        return String.format("SUCCESS: %s added!", model);
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


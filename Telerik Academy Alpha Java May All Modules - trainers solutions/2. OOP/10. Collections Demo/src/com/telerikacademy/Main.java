package com.telerikacademy;

public class Main {
    public static <E extends Comparable> void sort(E[] array) {
        //sort array
    }

    public static void main(String[] args) {
        MyList<Integer> list = new MyArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        list.set(1, 20);

        System.out.println(list.get(1));

        for (int element : list) {
            System.out.print(element + " ");
        }
        System.out.println();

        System.out.println(list.find(4));
        System.out.println(list.find(7));

        int x = list.get(10);

        Integer[] intarray = {1, 2, 3};
        sort(intarray);

//        MyArrayList[] myArrayListArray = new MyArrayList[5];
//        sort(myArrayListArray);
    }
}

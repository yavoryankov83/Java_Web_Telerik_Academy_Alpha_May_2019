package com.telerikacademy;

import com.telerikacademy.abstractClasses.AbstractClass;
import com.telerikacademy.abstractClasses.ConcreteClass;
import com.telerikacademy.abstractClasses.ConcreteClass2;
import com.telerikacademy.interfaces.CClass;
import com.telerikacademy.interfaces.Iterfacable;
import com.telerikacademy.interfaces.MyInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        ConcreteClass cc = new ConcreteClass();
//        AbstractClass ab = new ConcreteClass();
//        AbstractClass ab2 = new ConcreteClass2();

//        cc.method1();
//        cc.method2();
//        cc.method3();
//        cc.method4();

        List<AbstractClass> items = new ArrayList<>();
//        items.add(ab);
//        items.add(ab2);
//
//        printNames(items);

        MyInterface i = new CClass();
        CClass cc = new CClass();
//        i.method();
//        i.method1();
        //getIterfacable(cc);
        //getMyinterface(cc);
        cc.method2();
    }

    public static void getIterfacable(Iterfacable iter){
        iter.method();
    }

    public static void getMyinterface(MyInterface mi){
        mi.method();
    }

    public  static void printNames(List<AbstractClass> items){
        for (AbstractClass item : items){
            System.out.println(item.getName());
        }
    }
}

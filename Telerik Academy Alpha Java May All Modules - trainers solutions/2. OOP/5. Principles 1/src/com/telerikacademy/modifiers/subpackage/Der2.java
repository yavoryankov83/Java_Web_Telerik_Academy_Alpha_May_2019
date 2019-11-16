package com.telerikacademy.modifiers.subpackage;

import com.telerikacademy.modifiers.Base;

public class Der2 extends Base {
    public Der2() {
        super("foo");
    }

    public void method4() {
      //  method1(); // compilation error
        method2();
    }
}

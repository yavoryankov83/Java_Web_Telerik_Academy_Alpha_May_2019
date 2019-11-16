package com.telerikacademy.furniture.tests.models;


import com.telerikacademy.furniture.models.ChairImpl;
import com.telerikacademy.furniture.models.contracts.Chair;
import com.telerikacademy.furniture.models.enums.MaterialType;
import org.junit.Test;

public class ChairImpl_Tests {
    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throwError_when_numberOfLegsIsLessThanZero() {
        //Arrange, Act, Assert
        Chair chair = new ChairImpl("aads", MaterialType.LEATHER, 2, 2, -4);
    }

}

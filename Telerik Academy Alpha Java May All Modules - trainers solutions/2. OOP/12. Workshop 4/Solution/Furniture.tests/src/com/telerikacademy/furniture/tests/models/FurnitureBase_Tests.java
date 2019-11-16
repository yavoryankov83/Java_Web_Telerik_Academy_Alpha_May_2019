package com.telerikacademy.furniture.tests.models;

import com.telerikacademy.furniture.models.ChairImpl;
import com.telerikacademy.furniture.models.contracts.Chair;
import com.telerikacademy.furniture.models.enums.MaterialType;
import org.junit.Test;

public class FurnitureBase_Tests {
    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throwError_when_modelIsNull() {
        //Arrange, Act, Assert
        Chair chair = new ChairImpl(null, MaterialType.LEATHER, 2, 2, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throwError_when_modelLengthLessThanMinimum() {
        //Arrange, Act, Assert
        Chair chair = new ChairImpl("a", MaterialType.LEATHER, 2, 2, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throwError_when_heightIsLessThanZero() {
        //Arrange, Act, Assert
        Chair chair = new ChairImpl("aads", MaterialType.LEATHER, 2, -2, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throwError_when_priceIsLessThanZero() {
        //Arrange, Act, Assert
        Chair chair = new ChairImpl("aads", MaterialType.LEATHER, -2, 2, 4);
    }


}

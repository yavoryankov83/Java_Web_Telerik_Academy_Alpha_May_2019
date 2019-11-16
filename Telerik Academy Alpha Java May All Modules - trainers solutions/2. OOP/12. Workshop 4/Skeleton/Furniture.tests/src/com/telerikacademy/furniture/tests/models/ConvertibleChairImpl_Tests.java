package com.telerikacademy.furniture.tests.models;

import com.telerikacademy.furniture.models.ConvertibleChairImpl;
import com.telerikacademy.furniture.models.contracts.ConvertibleChair;
import com.telerikacademy.furniture.models.enums.MaterialType;
import org.junit.Assert;
import org.junit.Test;

public class ConvertibleChairImpl_Tests {

    @Test
    public void convert_should_convertFalseToTrue() {
        // Arrange
        ConvertibleChair convChair = new ConvertibleChairImpl("model", MaterialType.LEATHER, 3, 3, 4);

        // Act
        convChair.convert();

        // Assert
        Assert.assertEquals(true, convChair.getConverted());
    }

    @Test
    public void convert_should_convertTrueToFalse() {
        // Arrange
        ConvertibleChair convChair = new ConvertibleChairImpl("model", MaterialType.LEATHER, 3, 3, 4);
        convChair.convert();

        // Act
        convChair.convert();

        // Assert
        Assert.assertEquals(false, convChair.getConverted());
    }

    @Test
    public void convert_should_convertHeightCorrectly() {
        // Arrange
        ConvertibleChair convChair = new ConvertibleChairImpl("model", MaterialType.LEATHER, 3, 3, 4);


        // Act
        convChair.convert();

        // Assert
        double expected = 0.1;
        Assert.assertEquals(expected, convChair.getHeight(), 0.01);
    }

    @Test
    public void convert_should_restoreOriginalHeight() {
        // Arrange
        ConvertibleChair convChair = new ConvertibleChairImpl("model", MaterialType.LEATHER, 3, 3, 4);
        convChair.convert();

        // Act
        convChair.convert();

        // Assert
        Assert.assertEquals(3, convChair.getHeight(), 0.01);
    }
}

package com.telerikacademy.furniture.tests.models;

import com.telerikacademy.furniture.models.AdjustableChairImpl;
import com.telerikacademy.furniture.models.contracts.AdjustableChair;
import com.telerikacademy.furniture.models.enums.MaterialType;
import org.junit.Assert;
import org.junit.Test;

public class AdjustableChair_Tests {
    @Test
    public void setHeight_should_adjustChairHeight() {
        // Arrange
        AdjustableChair adjChair = new AdjustableChairImpl("model", MaterialType.LEATHER, 3, 3, 4);

        // Act
        adjChair.setHeight(7);

        // Assert
        Assert.assertEquals(7, adjChair.getHeight(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setHeight_should_throwError_when_heightLessThanZero() {
        // Arrange
        AdjustableChair adjChair = new AdjustableChairImpl("aads", MaterialType.LEATHER, 2, -2, 4);

    }
}

package com.telerikacademy.furniture.tests.core.factories;

import com.telerikacademy.furniture.core.contracts.FurnitureFactory;
import com.telerikacademy.furniture.core.factories.FurnitureFactoryImpl;
import com.telerikacademy.furniture.models.contracts.Table;
import com.telerikacademy.furniture.models.enums.MaterialType;
import org.junit.Assert;
import org.junit.Test;

public class FurnitureFactoryImpl_Tests {


    @Test
    public void createTable_should_createNewTable_when_withValidMaterialType() {
        // Arrange & Act
        FurnitureFactory factory = new FurnitureFactoryImpl();
        Table table = factory.createTable("model", "leather", 4, 4, 4, 4);

        // Assert
        Assert.assertEquals(MaterialType.LEATHER, table.getMaterialType());
    }

}

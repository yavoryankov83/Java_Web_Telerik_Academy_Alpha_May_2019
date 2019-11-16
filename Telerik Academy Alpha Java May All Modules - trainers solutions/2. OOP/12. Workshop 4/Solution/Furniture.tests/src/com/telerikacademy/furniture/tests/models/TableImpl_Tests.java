package com.telerikacademy.furniture.tests.models;

import com.telerikacademy.furniture.models.TableImpl;
import com.telerikacademy.furniture.models.contracts.Table;
import com.telerikacademy.furniture.models.enums.MaterialType;
import org.junit.Test;

public class TableImpl_Tests {
    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throwError_when_lengthNotPositive() {
        // Arrange, Act, Assert
        Table table = new TableImpl("aads", MaterialType.LEATHER, 2, 2, 0, 4);
        Table table1 = new TableImpl("aads", MaterialType.LEATHER, 2, 2, -1, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throwError_when_widthNotPositive() {
        // Arrange, Act, Assert
        Table table = new TableImpl("aads", MaterialType.LEATHER, 2, 2, 4, 0);
        Table table1 = new TableImpl("aads", MaterialType.LEATHER, 2, 2, 4, -4);
    }

}

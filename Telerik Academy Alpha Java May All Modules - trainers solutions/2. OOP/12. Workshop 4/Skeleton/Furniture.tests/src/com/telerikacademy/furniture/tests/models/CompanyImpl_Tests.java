package com.telerikacademy.furniture.tests.models;


import com.telerikacademy.furniture.models.ChairImpl;
import com.telerikacademy.furniture.models.CompanyImpl;
import com.telerikacademy.furniture.models.contracts.Company;
import com.telerikacademy.furniture.models.contracts.Furniture;
import com.telerikacademy.furniture.models.enums.MaterialType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CompanyImpl_Tests {
    private Furniture testFurniture;

    @Before
    public void before() {
        testFurniture = new ChairImpl("model", MaterialType.LEATHER, 3, 10, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throwError_when_NameIsNull() {
        // Arrange, Act, Assert
        Company company = new CompanyImpl(null, "1231231232");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throwError_when_registrationNumberIsInvalidLength() {
        // Arrange, Act, Assert
        Company company = new CompanyImpl("LLLasdLdasd", "12322123121232");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throwError_when_registrationNumberHasInvalidChars() {
        // Arrange, Acr, Assert
        Company company = new CompanyImpl("LLLasdLdasd", "f1251512545");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_should_throwError_when_nameIsWithInvalidLength() {
        // Arrange, Act, Assert
        Company company = new CompanyImpl("asd", "123123121232");
    }

    @Test
    public void constructor_should_returnCompany_when_valuesAreValid() {
        // Arrange, Act
        Company company = new CompanyImpl("Telerik", "1556565654");

        // Assert
        Assert.assertEquals(company.getName(), "Telerik");
    }

    @Test
    public void getFurnitures_should_returnShallowCopy() {
        // Arrange
        Company company = new CompanyImpl("Telerik", "1556565654");

        // Act
        List<Furniture> supposedShallowCopy = company.getFurnitures();
        company.add(testFurniture);

        // Assert
        Assert.assertEquals(0, supposedShallowCopy.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void find_should_throwException_when_passedNull() {
        // Arrange
        Company company = new CompanyImpl("Telerik", "1556565654");

        // Act & Assert
        company.find(null);

    }

    @Test
    public void find_should_returnFurniture_when_passedValidModel() {
        // Arrange
        Company company = new CompanyImpl("Telerik", "1556565654");
        company.add(testFurniture);
        // Act
        Furniture found = company.find("model");

        // Assert
        Assert.assertEquals(testFurniture.getModel(), found.getModel());
    }

    @Test
    public void find_should_returnNull_when_passedNonPresentModel() {
        // Arrange
        Company company = new CompanyImpl("Telerik", "1556565654");
        company.add(testFurniture);
        // Act
        Furniture found = company.find("model123");

        // Assert
        Assert.assertEquals(null, found);
    }

    @Test
    public void remove_should_removeFurniture_when_passedPresentFurniture() {
        // Arrange
        Company company = new CompanyImpl("Telerik", "1556565654");
        company.add(testFurniture);
        // Act
        company.remove(testFurniture);

        // Assert
        Assert.assertEquals(0, company.getFurnitures().size());
    }


}

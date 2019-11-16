# Databases

## General Description

Use the provided SQL script to generate the Northwind database and write the following queries against it:

1. Write a SQL query to find the top 5 most recently shipped orders. Display their ship name and shipped date. Expected result:

|           ShipName           |        ShippedDate         |
| :--------------------------: | :------------------------: |
|     Tortuga Restaurante      | 1998-05-06 00:00:00.000000 |
|   Drachenblut Delikatessen   | 1998-05-06 00:00:00.000000 |
| Hungry Owl All-Night Grocers | 1998-05-06 00:00:00.000000 |
|       HILARION-Abastos       | 1998-05-05 00:00:00.000000 |
|        Folk och fä HB        | 1998-05-05 00:00:00.000000 |

2. Write a SQL query to find the top 3 (ordered alphabetically by product name) products along with their category name. Expected result:

|     Product      |   Category   |
| :--------------: | :----------: |
|   Alice Mutton   | Meat/Poultry |
|  Aniseed Syrup   |  Condiments  |
| Boston Crab Meat |   Seafood    |

3. Write a SQL query to find all suppliers that have more than 3 products (ordered by SupplierID). Display product's supplier company name. Expected result:


|   CompanyName |
|:--------:|
| New Orleans Cajun Delights |
| Pavlova, Ltd. |
| Specialty Biscuits, Ltd. |
| Plutzer Lebensmittelgroxmärkte AG |

4. Write a SQL query to find top 5 customer companies arranged by their order total. Order total is the sum of all ordered products' quantity multiplied by their unit price. Display the order id, company name, number of products in the order and order total. Expected result:

| OrderID |    CompanyName     | ProductCount | OrderTotal |
| :-----: | :----------------: | :----------: | :--------: |
|  10865  |     QUICK-Stop     |      2       | 17250.0000 |
|  11030  | Save-a-lot Markets |      4       | 16321.9000 |
|  10981  |   Hanari Carnes    |      1       | 15810.0000 |
|  10372  |   Queen Cozinha    |      4       | 12281.2000 |
|  10424  |   Mère Paillarde   |      3       | 11493.2000 |

5. Write a SQL query to display regions that have both customers and employees along with how many employees and how many customers are there. *Note: There is column "Region" in both customers and employees tables. Use them.*
Expected result:

| Region | Customers | Employees |
| :----: | :-------: | :-------: |
|   WA   |     3     |     5     |

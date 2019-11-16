# Databases

## General Description

Use the provided SQL script to generate the Northwind database and write the following queries against it:

1. Write a SQL query to find the top 3 most expensive products. Display their product name and price. Expected result:

|       ProductName       | UnitPrice |
| :---------------------: | :-------: |
|      Côte de Blaye      |  263.50   |
| Thüringer Rostbratwurst |  123.79   |
|     Mishi Kobe Niku     |   97.00   |

2. Write a SQL query to find the top 5 (ordered alphabetically by product name) products along with their supplier company name. Expected result:

|    ProductName    |    Supplier company name    |
| :---------------: | :-------------------------: |
|   Alice Mutton    |        Pavlova, Ltd.        |
|   Aniseed Syrup   |       Exotic Liquids        |
| Boston Crab Meat  | New England Seafood Cannery |
| Camembert Pierrot |        Gai pâturage         |
| Carnarvon Tigers  |        Pavlova, Ltd.        |

3. Write a SQL query to find all managers that have more than 2 employees. Display their first name and last name. Expected result:

| FirstName | LastName |
| :-------: | :------: |
|  Andrew   |  Fuller  |
|  Steven   | Buchanan |

4. Write a SQL query to find the product name, unit price and category name of the products (ordered by unit price) that have the max unit price in their category. Expected result:

|       ProductName       | UnitPrice |  CategoryName  |
| :---------------------: | :-------: | :------------: |
| Gnocchi di nonna Alice  |   38.00   | Grains/Cereals |
|      Vegie-spread       |   43.90   |   Condiments   |
|  Manjimup Dried Apples  |   53.00   |    Produce     |
|  Raclette Courdavault   |   55.00   | Dairy Products |
|    Carnarvon Tigers     |   62.50   |    Seafood     |
| Sir Rodney's Marmalade  |   81.00   |  Confections   |
| Thüringer Rostbratwurst |  123.79   |  Meat/Poultry  |
|      Côte de Blaye      |  263.50   |   Beverages    |

5. Write a SQL query to display the shipper's company name with minimum orders. Expected result:

|   CompanyName     |  
|:--------:|
| Speedy Express  |

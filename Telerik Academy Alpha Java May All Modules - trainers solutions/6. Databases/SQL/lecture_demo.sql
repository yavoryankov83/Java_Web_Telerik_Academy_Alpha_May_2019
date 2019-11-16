use northwind;

/*SELECT*/

-- select all employees
select * from northwind.employees;

-- select all employee names; use alias for both tables and columns
select first_name Nadya from employees e;

-- select all job titles; get them uniquely
select
    -- distinct
                job_title from employees;

-- select all employees that work as coordinators, 'Sales Coordinator'
select * from employees
where job_title like '_ales%';

-- select all employees that do not work as coordinators
select * from employees
where job_title not like '%Coordinator%';

-- select all order details that have quantity bigger than 50;
-- select just unique quantity
select distinct *
                -- product_id, quantity
from order_details
where quantity > 50;

select 'Nadya';

-- select employees with notes / without notes
select * from employees
where notes is not null;

select * from employees
where notes is null;

-- select all unique categories from the products
select distinct category from products;

-- select all sauces from the products
select * from products
where category like 'sauce%';

-- select all beverages from products that have minimum_reorder_quantity
select * from products
where category like '%beverage%' and minimum_reorder_quantity is not null ;

-- select all beverages from products that have minimum_reorder_quantity or target level above 60
select * from products
where category like '%beverage%' and (minimum_reorder_quantity is not null or target_level > 60);

-- select products with price between 20 and 100
select * from products
where list_price between 22 and 100
order by list_price desc ;

-- select products with product name between names 'Northwind Traders Clam Chowder' and 'Northwind Traders Marmalade';
select * from products
where product_name between 'Northwind Traders Clam Chowder' and 'Northwind Traders Hot Cereal'
order by product_name;

-- select employees that are either from Seattle or Redmond
select * from employees
where city in ('Seattle', 'Redmond');

-- select all products that were purchased
select product_name from products
where id in
      (select distinct product_id from purchase_order_details
      where unit_cost > 10)
and list_price > 50;



-- add several order by-s


/*GROUP BY*/

select city, count(id) Count
from employees
group by city
having Count > 1;

select id, first_name, city
from employees;

SELECT @@GLOBAL.sql_mode;

select p.product_name, pod.unit_cost
from
     purchase_order_details pod
right join products p on p.id = pod.product_id;

select distinct * from (
select city from employees emp
union all
select city from customers cus) tmp;

select id, city,last_name from employees
union
select id, city, country_region from customers;


-- aggregate functions - count the customers. Add where
select job_title, count(*) customer_number from employees
where job_title like '%sales%'
group by job_title
having customer_number > 1;

-- find how many products are purchased at all
select sum(profits.money) as profit
from
         (select product_id, p.product_name, count(*) * unit_cost as money
from purchase_order_details
join products p on purchase_order_details.product_id = p.id
group by product_id, product_name) profits;



-- find also the product names


 -- how many customers are from Seattle?

select count(*) count from customers
where city = 'Seattle';

 
-- what are the cities with more than 1 customers?
select
      city,
      count(*) count
from customers
group by city
having count > 1;


/*JOIN*/

-- inner join customers and orders -  c.id as customer_id, c.first_name, c.company, c.city, o.id as order_id, o.order_date, o.shipped_date 

select  c.first_name customer_name, c.company, c.city, o.order_date, os.status_name, invs.invoice_date
from customers c
join orders o on c.id = o.customer_id
join orders_status os on os.id = o.status_id
join
         (select i.order_id as id, i.invoice_date
          from invoices i
          where i.tax > 5) invs
on invs.id = o.id;

select * from invoices;

insert into invoices(id, order_id, invoice_date, tax)
values(43, 90000, '2006-03-23 17:15:29', 15);

select * from invoices
where id = 31;

update invoices
set order_id = 34
where order_id = 31;

insert into cities(city_name)
select city from customers
union
select city from employees;

select * from cities;



-- left join customers and orders


-- right join customers and orders


-- select all products that were purchased with name, quantity, cost and date - show join


-- find out which employees have what priviledges; test left and right joins


-- find customers and suppliers that are not from USA

-- list the employees that have registered more than 10 orders - join + aggregation


/*INSERT, UPDATE, DELETE*/

-- add new privilege - select, insert all, by columns. 'Send Newsletter', 'Order Approvals'


-- insert new customers from suppliers - first_name, last_name, city, country_region


-- update new customers' company (look up the id)


-- update customers with id between 30 and 36 with note that tey're contrasctors.


-- remove customers without city






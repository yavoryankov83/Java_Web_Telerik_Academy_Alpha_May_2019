use northwind;

/*SELECT*/

-- select all employees
select * 
from employees;

-- select all employee names; use alias
select employees.first_name as 'name', employees.last_name as 'family'
from employees;

select e.first_name as 'name', e.last_name as 'family'
from employees e;

-- select all job titles; get them uniquely
select distinct employees.job_title
from employees;

-- select all employees that work as coordinators
select * 
from employees
where employees.job_title = 'Sales Coordinator';

-- select all employees that do not work as coordinators
select * 
from employees
where employees.job_title <> 'Sales Coordinator';

-- select all order details that have quantity bigger than 50; select just unique quantity
select *
from order_details
where order_details.quantity >= 50;

select distinct quantity
from order_details
where order_details.quantity >= 50;

-- select employees with notes / without notes
select * 
from employees
where employees.notes is NULL;

select * 
from employees
where employees.notes is not NULL;

-- select all unique categories from the products
select distinct products.category
from products;

-- select all sauces from the products
select *
from products
where products.product_name like '%sauce%';

-- select all beverages from products that have minimum reorder quantity
select *
from products
where products.category like '%beverage%' and products.minimum_reorder_quantity is not NULL;

-- select all beverages from products that have minimum reorder quantity or target level above 60
select *
from products
where products.category like '%beverage%' and (products.minimum_reorder_quantity is not NULL or products.target_level < 60);

-- select products with price between 20 and 100
select *
from products
where products.list_price between 20 and 100;

-- select products with product name between names
select *
from products
where products.product_name between 'Northwind Traders Clam Chowder' and 'Northwind Traders Marmalade';

-- select employees that are either from Seattle or Redmond
select *
from employees
where employees.city in ('Seattle', 'Redmond');

-- select all products that were purchased
select *
from products
where products.id in
(select distinct purchase_order_details.product_id
from purchase_order_details)
order by products.id;

-- add several order by-s

/*GROUP BY*/

-- aggregate functions
select count(*)
from customers
where notes is not null;

-- find the cheapest and most expensive products. What is the average price of a product?
select max(pp.list_price)
from products as pp;

select min(pp.list_price)
from products as pp;

select avg(pp.list_price)
from products as pp;

-- find how many products are purchased at all
select sum(pp.quantity)
from purchase_order_details as pp;


-- find also the product names
select p.product_name, p.list_price
from products p
where p.list_price in 
(select min(list_price)
 from products);
 
 -- how many customers are from Seattle? How many customers are from each city?
 select city, count(*) as people_count
 from customers
 group by city
 order by people_count desc;
 
-- what are the cities with more then 1 customers?
select city, count(*) as people_count
from customers
group by city
having people_count >= 2
order by people_count desc;

/*JOIN*/

-- inner join 
select c.id as customer_id, c.first_name, c.company, c.city, o.id as order_id, o.order_date, o.shipped_date 
from customers c
join orders o on c.id=o.customer_id;

-- left join 
select c.id as customer_id, c.first_name, c.company, c.city, o.id as order_id, o.order_date, o.shipped_date 
from customers c
left join orders o on c.id=o.customer_id;

-- right join 
select c.id as customer_id, c.first_name, c.company, c.city, o.id as order_id, o.order_date, o.shipped_date 
from customers c
right join orders o on c.id=o.customer_id;

-- select all products that were purchased with name, quantity, cost and date - show join
select pod.id, p.product_name, pod.quantity, pod.unit_cost, pod.date_received
from purchase_order_details as pod
join products as p on pod.product_id = p.id;

select pod.id, p.product_name, pod.quantity, pod.unit_cost, pod.date_received
from products as p
join purchase_order_details as pod on pod.product_id = p.id;


-- find out which employees have what priviledges; test left and right joins
select e.first_name, e.last_name, p.privilege_name
from employee_privileges ep
join privileges p on ep.privilege_id = p.id
join employees e on ep.employee_id = e.id;

-- find customers and suppliers that are not from USA
select customers.city
from customers
where customers.country_region <> 'USA'
union
select suppliers.city
from suppliers
where customers.country_region <> 'USA'
-- where city is not null
order by city;

-- list the employees that have registered more than 10 orders - join + aggregation
select employees.last_name, count(orders.id) as number_of_orders
from (orders
join employees on orders.employee_id = employees.id)
group by last_name
having count(orders.id) > 10;



/*INSERT, UPDATE, DELETE*/

-- add new privilege
select * from privileges;

insert into privileges values(3,'Order Approvals');

insert into privileges (privilege_name) values('Send Newsletter');

-- insert new customers from suppliers
insert into customers (first_name, last_name, city, country_region)
select first_name, last_name, city, country_region from suppliers;

-- update new customers' company
update customers
set company='Company YY'
where id >= 30;

-- update customers with id between 30 and 36 with note that tey're contrasctors.
update customers
set notes='Works as contractor'
where id between 30 and 35;

-- remove customers without city
delete
from customers
where city is null;



use telerikacademy;

-- 1. Write a SQL query to find all information about all departments (use "TelerikAcademy" database).
select *
from departments;

-- 2. Write a SQL query to find all department names.
select name
from departments;

-- 3. Write a SQL query to find the salary of each employee.
select FirstName, LastName, Salary
from employees;

-- 4. Write a SQL to find the full name of each employee.
select CONCAT(FirstName, ' ', IFNULL(MiddleName, ''), ' ', LastName)
from employees;

select concat_ws(' ', e.FirstName, e.middlename, e.LastName) as full_name
from employees e;

-- 5. Write a SQL query to find the email addresses of each employee (by his first and last name). Consider that the mail domain is telerik.com. Emails should look like “John.Doe@telerik.com". The produced column should be named "Full Email Addresses".
select CONCAT(FirstName, '.', LastName, '@telerik.com') as 'Full Email Address'
from employees;

-- 6. Write a SQL query to find all different employee salaries.
select distinct salary
from employees;

-- 7. Write a SQL query to find all information about the employees whose job title is “Sales Representative“.
select *
from employees
where JobTitle = 'Sales Representative';

-- 8. Write an SQL query to find all employees whose salary is bigger than their manager's.
select e.FirstName, e.LastName, e.ManagerID, e.Salary
from employees e,
     employees e2
where e.ManagerID = e2.employeeID
  and e.Salary > e2.Salary;

-- 9. Write a SQL query to find the names of all employees whose first name starts with "SA".
select FirstName
from employees
where FirstName like 'sa%';

-- 10. Write a SQL query to find the names of all employees whose last name contains "ei".
select FirstName, LastName
from employees
where LastName like '%ei%';

select e.firstname, e.lastname
from employees e
where locate('ei', e.lastname);

-- 11. Write a SQL query to find the salary of all employees whose salary is in the range [20000…30000].
select FirstName, LastName
from employees
where Salary between 20000 and 30000;

-- 12. Write a SQL query to find the names of all employees whose salary is 25000, 14000, 12500 or 23600.
select FirstName, LastName
from employees
where Salary in (25000, 14000, 12500, 23600);

-- 13. Write a SQL query to find all employees that do not have manager.
select FirstName, LastName
from employees
where ManagerID is null;

-- 14. Write a SQL query to find the names of all employees who are hired before their managers.
select e.EmployeeID, e.FirstName, e.LastName, e.ManagerID, e.HireDate
from employees e,
     employees m
where e.ManagerID = m.employeeID
  and e.HireDate < m.HireDate;

-- 15. Write a SQL query to find all employees that have salary more than 50000. Order them in decreasing order by salary.
select FirstName, LastName, Salary
from employees
where Salary > 50000
order by Salary desc;

-- 16. Write a SQL query to find the top 5 best paid employees.
select FirstName, LastName, Salary
from employees
order by Salary desc
limit 5;

-- 17. Write a SQL query to find all employees and their address.
select e.FirstName, e.LastName, a.AddressText
from employees e
         join addresses a on a.AddressID = e.AddressID;

select e.firstname, e.lastname, e.salary, a.addresstext, t.name
from employees e
         join addresses a on a.addressid = e.addressid
         join towns t on t.townid = a.townid;

-- 18. Write a SQL query to find all employees whose MiddleName is the same as the first letter of their town.
select *
from employees;
select e.MiddleName, t.Name
from employees e
         join addresses a on e.AddressID = a.AddressID
         join towns t on a.TownID = t.TownID
where substr(e.MiddleName, 1, 1) = substr(t.Name, 1, 1);

select *
from employees;
select e.MiddleName, t.Name
from employees e
         join addresses a on e.AddressID = a.AddressID
         join towns t on a.TownID = t.TownID
where left(e.MiddleName, 1) = left(t.Name, 1);

-- 19. Write a SQL query to find all employees that have manager, along with their manager.
select e.FirstName, e.LastName, m.FirstName as ManagerFirstName, m.LastName as ManagerLastName
from employees e
         join employees m on m.EmployeeID = e.ManagerID;

-- 20. Write a SQL query to find all employees that have manager, along with their manager and their address.
SELECT concat(e.FirstName, ' ', e.LastName) AS Employee,
       concat(m.FirstName, ' ', m.LastName) AS Manager,
       t.name                               AS Town,
       a.addresstext                        AS Address
FROM employees e,
     employees m,
     addresses a,
     towns t
WHERE e.managerid = m.employeeid
  AND e.addressid = a.addressid
  AND a.townid = t.townid;

select e.FirstName   as EmplName,
       m.FirstName   as ManagerName,
       a.AddressText as ManagerAddressText,
       t.Name        as ManagerTown
from employees e
         join employees m on e.ManagerID = m.EmployeeID
         join addresses a on m.AddressID = a.AddressID
         join towns t on a.TownID = t.TownID
where e.ManagerID = m.EmployeeID;

-- 21. Write a SQL query to find all departments and all town names as a single list.
select name
from departments
union
select name
from towns;

-- 22. Write a SQL query to find all the employees and the manager for each of them along with the employees that do not have manager.
select e.FirstName,
       e.LastName,
       concat(IFNULL(m.FirstName, 'n/a'), ' ', IFNULL(m.LastName, '')) as Manager
from employees e
         left join employees m on m.EmployeeID = e.ManagerID;

-- 23. Write a SQL query to find the names of all employees from the departments "Sales" and "Finance" whose hire year is between 1995 and 2005.
select e.FirstName, e.LastName, d.Name, e.HireDate
from employees e
         join departments d on d.DepartmentID = e.DepartmentID
where d.Name in ('Sales', 'Finance')
  and (YEAR(e.HireDate) between 2002 and 2004);

select *
from employees
where DepartmentID in
      (
          select DepartmentID
          from departments
          where departments.Name in ('Sales', 'Finance')
      )
  and hiredate between '1995-01-01' and '2005-12-31';

select e.firstname, e.hiredate, t.name
from employees e
         join (
    select departmentid, name
    from departments
    where name in ('Finance', 'Sales')
) t on t.departmentid = e.departmentid
where year(e.hiredate) between 1995 and 2005;

select FirstName, year(HireDate)
from employees e
         join departments d on e.DepartmentID = d.DepartmentID
where d.Name in ('Sales', 'Finance')
  and year(e.HireDate) between 1995 and 2005;
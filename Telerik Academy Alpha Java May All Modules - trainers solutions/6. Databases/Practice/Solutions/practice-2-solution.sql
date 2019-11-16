# Advanced SQL

use "telerikacademy";

# Write a SQL query to find the average salary of employees who have been hired before year 2000 incl. Round it down to the nearest integer.
select floor(avg(Salary))
from employees
where year(HireDate) <= 2000;
# Write a SQL query to find all town names that end with a vowel (a,o,u,e,i).
select Name
from towns
where right(Name, 1) in ('a', 'o', 'u', 'e', 'i');

# Write a SQL query to find all town names that start with a vowel (a,o,u,e,i).
select Name
from towns
where lower(left(Name, 1)) in ('a', 'o', 'u', 'e', 'i');

# Write a SQL query to find the name and the length of the name of the town with the longest name.
select Name, length(Name)
from towns
order by length(Name) desc
limit 1;

select Name, length(Name)
from towns
where length(Name) = (
    select max(length(Name))
    from towns);

# Write a SQL query to find the name and the length of the name of the town with the shortest name.
select Name, length(Name)
from towns
where length(Name) = (
    select min(length(Name))
    from towns);

# Write a SQL query to find all employees with even IDs.
select *
from employees
where mod(EmployeeID, 2) = 0;

select *
from employees
where (employees.EmployeeID % 2) = 0;

# Write a SQL query to find the names and salaries of the employees that take the minimal salary in the company.
select FirstName, Salary
from employees
where Salary = (
    select min(Salary)
    from employees);

# Write a SQL query to find the names and salaries of the employees that have a salary that is up to 10% higher than the minimal salary for the company.
select FirstName, Salary
from employees
where Salary <= 1.1 * (select min(Salary) from employees);

# Write a SQL query to find the full name, salary and department of the employees that take the minimal salary in their department.
select concat_ws(' ', e.FirstName, e.middlename, e.LastName), e.Salary, d.Name
from employees e
join departments d on e.DepartmentID = d.DepartmentID
where e.Salary in (
    select min(Salary) from employees t
    where t.DepartmentID = d.DepartmentID);

# Write a SQL query to find the average salary in the department #1.
select avg(Salary) from
employees where DepartmentID = 1;

# Write a SQL query to find the average salary  in the "Sales" department.
select avg(Salary) from
employees e
join departments d on e.DepartmentID = d.DepartmentID
where d.Name like 'Sales';

# Write a SQL query to find the number of employees in the "Sales" department.
select count(*) from
employees e
join departments d on e.DepartmentID = d.DepartmentID
where d.Name like 'Sales';

# Write a SQL query to find the number of all employees that have manager.
select count(*) from employees e
where ManagerID is not null;

# Write a SQL query to find the number of all employees that have no manager.
select count(*) from employees e
where ManagerID is null;

# Write a SQL query to find all departments and the average salary for each of them.
select d.name, avg(Salary) from departments d
join employees e on d.DepartmentID = e.DepartmentID
group by d.Name;

# Write a SQL query to find all project that took less than a month (30 days) to complete.
select * from projects
where datediff(EndDate, StartDate) < 30;

# Write a SQL query to find the count of all employees in each department and for each town.
select d.Name, t.Name, count(*) from employees e
join departments d on e.DepartmentID = d.DepartmentID
join addresses a on e.AddressID = a.AddressID
join towns t on a.TownID = t.TownID
group by d.Name, t.Name;

# Write a SQL query to find all managers that have exactly 5 employees. Display their first name and last name.
select m.EmployeeID ,concat_ws(' ', e.FirstName, e.middlename, e.LastName), count(e.ManagerID)
from employees e
join employees m on e.ManagerID = m.EmployeeID
group by e.ManagerID
having count(e.ManagerID) = 5;

# Write a SQL query to find all employees along with their managers. For employees that do not have manager display the value "(no manager)".
select e.FirstName, e.LastName,
       IFNULL(m.FirstName, '{no manager}') from employees e
left join employees m on e.ManagerID = m.EmployeeID;

# Write a SQL query to find the names of all employees whose last name is exactly 5 characters long.
select LastName from employees e
where length(e.LastName) = 5;

# Write a SQL query to display the current date and time in the following format "`day.month.year hour:minutes:seconds:milliseconds`".
select DATE_FORMAT(NOW(), '%e.%m.%Y %H:%i:%s:%f');

# Write a SQL query to display the average employee salary by department and job title.
select d.Name, e.JobTitle, avg(Salary) from employees e
join departments d on e.DepartmentID = d.DepartmentID
group by d.Name, e.JobTitle;

# Write a SQL query to display the town where maximal number of employees work.
select t.Name, count(e.EmployeeID) from employees e
join addresses a on e.AddressID = a.AddressID
join towns t on a.TownID = t.TownID
group by t.Name
order by count(e.EmployeeID) desc
limit 1;

select tt.name, sortedtable.EmployeesCount
from(
	select t.townid, count(e.EmployeeID) as EmployeesCount
	from towns t
	join addresses a on a.TownID = t.TownID
	join employees e on e.AddressID = a.AddressID
	group by t.townid
	having EmployeesCount =
	(select max(EmployeesCount)
		from(select t.townid, count(e.EmployeeID) as EmployeesCount
			from towns t
			join addresses a on a.TownID = t.TownID
			join employees e on e.AddressID = a.AddressID
			group by t.townid
		) as maxtable
	)
) as sortedtable
join towns tt on tt.townid = sortedtable.townid;

# Write a SQL query to display the number of managers from each town.
select t.Name, count(e.EmployeeID) ManagersCount
from employees e
join addresses a on a.AddressID = e.EmployeeID
join towns t on t.TownID = a.TownID
where e.EmployeeID in (select distinct ManagerID from employees where ManagerID is not null)
group by t.Name;

# Write a SQL query to find the manager who is in charge of the most employees and their average salary.
select round(avg(Salary),0) from employees
where ManagerID =
(select ManagerID from employees
group by ManagerID
order by count(*) desc
limit 1);

# Create a table `Users`. Users should have username, password, full name and last login time.
# - Choose appropriate data types for the table fields. Define a primary key column with a primary key constraint.
# - Define the primary key column as auto-increment to facilitate inserting records.
# - Define unique constraint to avoid repeating usernames.
# - Define a check constraint to ensure the password is at least 5 characters long.
create table Users (
	UserID int primary key auto_increment,
	Username varchar(50) unique,
	Password varchar(50),
	FullName varchar(50),
	LastLoginTime timestamp,
	check(length(Password) > 4)
);

# Write SQL statements to insert in the `Users` table the names of all employees from the `Employees` table.
# - Combine the first and last names as a full name.
# - For username use the first 3 letters of the first name + the last name (in lowercase).
# - Use the same for the password.
# - Use HireDate for last login time.
insert into Users (Username, Password, FullName, LastLoginTime)
select
	concat(lower(substr(e.FirstName, 1, 3)), lower(e.LastName)) as Username,
	concat(lower(e.FirstName), lower(e.LastName)) as Password,
	concat(e.FirstName, ' ', e.LastName) as FullName,
	e.HireDate
from employees e;

# Write a SQL statement that changes the password to `NULL` for all users that have not been in the system since year 1999.
update Users
set Password = null
where year(LastLoginTime) < 2000;

# Write a SQL statement that deletes all users without passwords (`NULL` password).
delete from Users
where Password is null;

# Write a SQL statement finds the names of the employees who have worked on the most projects.
select concat(FirstName, ' ',  LastName) from employees
where EmployeeID in (
    select distinct EmployeeID from employeesprojects 
    group by EmployeeID
    having count(EmployeeID) = (
        select distinct count(*) from employeesprojects
        group by EmployeeID
        order by count(EmployeeID) desc
        limit 1
    )
);



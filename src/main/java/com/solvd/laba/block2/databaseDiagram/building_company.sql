CREATE DATABASE IF NOT EXISTS building_company;
USE building_company;

DROP TABLE payments;
DROP TABLE employees;
DROP TABLE salaries;
DROP TABLE positions;

DROP TABLE building_approvals;
DROP TABLE material_buildings;
DROP TABLE materials;
DROP TABLE material_types;
DROP TABLE cost_estimates;
DROP TABLE buildings;
DROP TABLE building_types;
DROP TABLE salaries;
DROP TABLE customers;

DROP TABLE companies;

CREATE TABLE IF NOT EXISTS companies (
	id SERIAL PRIMARY KEY,
    company_name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS building_types (
	id SERIAL PRIMARY KEY,
	building_type VARCHAR(50) NOT NULL UNIQUE,
    base_cost DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS payments (
	id SERIAL PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS customers (
	id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    company_id BIGINT UNSIGNED,
    payment_id BIGINT UNSIGNED,
    CONSTRAINT fk_company_customers FOREIGN KEY(company_id) REFERENCES companies(id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_customer_payments FOREIGN KEY(payment_id) REFERENCES payments(id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS buildings (
    id SERIAL PRIMARY KEY,
    building_type VARCHAR(50) NOT NULL,
    building_description VARCHAR(150) NULL,
    company_id BIGINT UNSIGNED,
    customer_id BIGINT UNSIGNED,
    CONSTRAINT fk_company_buildings FOREIGN KEY(company_id) REFERENCES companies(id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_type_buildings FOREIGN KEY(building_type) REFERENCES building_types(building_type) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_customer_buildings FOREIGN KEY(customer_id) REFERENCES customers(id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS building_approvals (
	id SERIAL PRIMARY KEY,
    time_needed VARCHAR(45) NOT NULL,
    approved_by VARCHAR(45) NOT NULL,
    building_id BIGINT UNSIGNED,
    FOREIGN KEY (building_id) REFERENCES buildings(id)
);

CREATE TABLE IF NOT EXISTS positions (
	id SERIAL PRIMARY KEY,
    position VARCHAR(50) NOT NULL UNIQUE,
    has_car BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS salaries (
	id SERIAL PRIMARY KEY,
    position VARCHAR(50) NOT NULL,
    experiance VARCHAR(50) NULL,
    amount DECIMAL(10, 2),
    CONSTRAINT fk_position_salaries FOREIGN KEY (position) REFERENCES positions(position) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS employees (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    position VARCHAR(50) NOT NULL,
    company_id BIGINT UNSIGNED,
    salary_id BIGINT UNSIGNED,
    CONSTRAINT fk_company_employees FOREIGN KEY(company_id) REFERENCES companies(id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_position_employees FOREIGN KEY (position) REFERENCES positions(position) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_salary_employees FOREIGN KEY (salary_id) REFERENCES salaries(id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS cost_estimates (
	id SERIAL PRIMARY KEY,
    cost DECIMAL(10, 2) NOT NULL,
    building_id BIGINT UNSIGNED,
    CONSTRAINT fk_building_cost_estimates FOREIGN KEY(building_id) REFERENCES buildings(id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS material_types (
	id SERIAL PRIMARY KEY,
    material_type VARCHAR(50) UNIQUE
);

CREATE TABLE IF NOT EXISTS materials (
	id SERIAL PRIMARY KEY,
    material_name VARCHAR(50) NOT NULL,
    amount BIGINT NOT NULL,
    price DECIMAL NOT NULL,
    material_type VARCHAR(50) NOT NULL,
    CONSTRAINT fk_type_materials FOREIGN KEY (material_type) REFERENCES material_types(material_type) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS material_buildings (
	material_id BIGINT UNSIGNED,
    building_id BIGINT UNSIGNED,
    CONSTRAINT fk_material_buildings_material_id FOREIGN KEY(material_id) REFERENCES materials(id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_material_buildings_building_id FOREIGN KEY(building_id) REFERENCES buildings(id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO companies(company_name) value ("Integral Bud");
INSERT INTO companies(company_name) value ("KyivMiskBud");
INSERT INTO companies(company_name) value ("LargeBud");

UPDATE companies SET company_name = 'Integral Builders' WHERE company_name = "Integral Bud";
UPDATE companies SET company_name = 'KyivMiskBud Ltd.' WHERE company_name = "KyivMiskBud";
UPDATE companies SET company_name = 'Large Construction' WHERE id = "LargeBud";

DELETE FROM companies WHERE company_name = 'Integral Builders';
DELETE FROM companies WHERE company_name = 'KyivMiskBud Ltd.';
DELETE FROM companies WHERE company_name = 'Large Construction';

INSERT INTO building_types (building_type, base_cost) VALUES ('Residential', 100000.00);
INSERT INTO building_types (building_type, base_cost) VALUES ('Commercial', 150000.00);
INSERT INTO building_types (building_type, base_cost) VALUES ('Industrial', 200000.00);

UPDATE building_types SET base_cost = 120000.00 WHERE building_type = 'Residential';
UPDATE building_types SET base_cost = 180000.00 WHERE building_type = 'Commercial';
UPDATE building_types SET base_cost = 250000.00 WHERE building_type = 'Industrial';

DELETE FROM building_types WHERE building_type = 'Residential';
DELETE FROM building_types WHERE building_type = 'Commercial';
DELETE FROM building_types WHERE building_type = 'Industrial';

INSERT INTO payments (amount, payment_date) VALUES (5000.00, '2023-01-15');
INSERT INTO payments (amount, payment_date) VALUES (8000.00, '2023-02-20');
INSERT INTO payments (amount, payment_date) VALUES (12000.00, '2023-03-25');

UPDATE payments SET amount = 5500.00 WHERE amount = 5000.00;
UPDATE payments SET amount = 8200.00 WHERE amount = 8000.00;
UPDATE payments SET amount = 12500.00 WHERE amount = 12000.00;

DELETE FROM payments WHERE amount = 5500.00;
DELETE FROM payments WHERE amount = 8200.00;
DELETE FROM payments WHERE amount = 12500.00;

select * from companies;

INSERT INTO customers (first_name, last_name, company_id, payment_id) VALUES ('John', 'Doe', 3, 1);
INSERT INTO customers (first_name, last_name, company_id, payment_id) VALUES ('Jane', 'Smith', 4, 2);
INSERT INTO customers (first_name, last_name, company_id, payment_id) VALUES ('Bob', 'Johnson', 5, 3);

UPDATE customers SET first_name = 'Johnny' WHERE id = 2;
UPDATE customers SET last_name = 'Smith' WHERE id = 3;
UPDATE customers SET first_name = 'Sam' WHERE id = 4;

DELETE FROM customers WHERE first_name = 'John' AND last_name = 'Doe';
DELETE FROM customers WHERE first_name = 'Jane' AND last_name = 'Smith';
DELETE FROM customers WHERE first_name = 'Bob' AND last_name = 'Johnson';

select * from customers;

INSERT INTO buildings (building_type, building_description, company_id, customer_id) VALUES ('Residential', 'Two-story house', 3, 2);
INSERT INTO buildings (building_type, building_description, company_id, customer_id) VALUES ('Commercial', 'Office building', 4, 3);
INSERT INTO buildings (building_type, building_description, company_id, customer_id) VALUES ('Industrial', 'Factory', 5, 4);

UPDATE buildings SET building_description = 'Three-story house' WHERE id = 2;
UPDATE buildings SET building_description = 'Shopping Mall' WHERE id = 3;
UPDATE buildings SET building_description = 'Warehouse' WHERE id = 4;

DELETE FROM buildings WHERE building_type = 'Residential' AND building_description = 'Three-story house';
DELETE FROM buildings WHERE building_type = 'Commercial' AND building_description = 'Shopping Mall';
DELETE FROM buildings WHERE building_type = 'Industrial' AND building_description = 'Factory';

select * from buildings;

INSERT INTO building_approvals (time_needed, approved_by, building_id) VALUES ('2 weeks', 'City Inspector', 2);
INSERT INTO building_approvals (time_needed, approved_by, building_id) VALUES ('1 month', 'County Engineer', 3);
INSERT INTO building_approvals (time_needed, approved_by, building_id) VALUES ('3 weeks', 'State Architect', 4);

UPDATE building_approvals SET time_needed = '1 month' WHERE id = 1;
UPDATE building_approvals SET approved_by = 'City Planning Department' WHERE id = 2;
UPDATE building_approvals SET building_id = 2 WHERE id = 3;

DELETE FROM building_approvals WHERE time_needed = '2 weeks' AND approved_by = 'City Inspector';
DELETE FROM building_approvals WHERE time_needed = '1 month' AND approved_by = 'County Engineer';
DELETE FROM building_approvals WHERE time_needed = '3 weeks' AND approved_by = 'State Architect';

INSERT INTO positions (position, has_car) VALUES ('Architect', true);
INSERT INTO positions (position, has_car) VALUES ('Project Manager', true);
INSERT INTO positions (position, has_car) VALUES ('Construction Worker', false);

UPDATE positions SET has_car = false WHERE position = 'Architect';
UPDATE positions SET position = 'Senior Project Manager' WHERE position = 'Project Manager';
UPDATE positions SET position = 'Skilled Construction Worker' WHERE position = 'Construction Worker';

DELETE FROM positions WHERE position = 'Architect';
DELETE FROM positions WHERE position = 'Project Manager';
DELETE FROM positions WHERE position = 'Construction Worker';

INSERT INTO salaries (position, experiance, amount) VALUES ('Architect', '5 years', 80000.00);
INSERT INTO salaries (position, experiance, amount) VALUES ('Project Manager', '7 years', 100000.00);
INSERT INTO salaries (position, experiance, amount) VALUES ('Construction Worker', '2 years', 50000.00);

UPDATE salaries SET amount = 85000.00 WHERE position = 'Architect';
UPDATE salaries SET experiance = '8 years' WHERE position = 'Senior Project Manager';
UPDATE salaries SET position = 'Senior Project Manager' WHERE position = 'Architect';

DELETE FROM salaries WHERE position = 'Architect' AND experiance = '5 years' AND amount = 80000.00;
DELETE FROM salaries WHERE position = 'Project Manager' AND experiance = '7 years' AND amount = 100000.00;
DELETE FROM salaries WHERE position = 'Construction Worker' AND experiance = '2 years' AND amount = 50000.00;

select * from companies;
select * from salaries;

INSERT INTO employees (first_name, last_name, position, company_id, salary_id) VALUES ('Alice', 'Johnson', 'Architect', 3, 1);
INSERT INTO employees (first_name, last_name, position, company_id, salary_id) VALUES ('Bob', 'Smith', 'Project Manager', 4, 2);
INSERT INTO employees (first_name, last_name, position, company_id, salary_id) VALUES ('Charlie', 'Doe', 'Construction Worker', 5, 3);

UPDATE employees SET company_id = 2 WHERE id = 1;
UPDATE employees SET first_name = 'Robert' WHERE id = 2;
UPDATE employees SET last_name = 'Smith' WHERE id = 3;

DELETE FROM employees WHERE first_name = 'Alice' AND last_name = 'Johnson' AND position = 'Architect';
DELETE FROM employees WHERE first_name = 'Bob' AND last_name = 'Smith' AND position = 'Project Manager';
DELETE FROM employees WHERE first_name = 'Charlie' AND last_name = 'Doe' AND position = 'Construction Worker';

select * from buildings;

INSERT INTO cost_estimates (cost, building_id) VALUES (120000.00, 2);
INSERT INTO cost_estimates (cost, building_id) VALUES (180000.00, 3);
INSERT INTO cost_estimates (cost, building_id) VALUES (250000.00, 4);

UPDATE cost_estimates SET cost = 125000.00 WHERE id = 1;
UPDATE cost_estimates SET building_id = 1 WHERE id = 2;
UPDATE cost_estimates SET cost = 260000.00 WHERE id = 3;

DELETE FROM cost_estimates WHERE cost = 120000.00;
DELETE FROM cost_estimates WHERE cost = 180000.00;
DELETE FROM cost_estimates WHERE cost = 250000.00;

INSERT INTO material_types (material_type) VALUES ('Concrete');
INSERT INTO material_types (material_type) VALUES ('Steel');
INSERT INTO material_types (material_type) VALUES ('Wood');

UPDATE material_types SET material_type = 'Cement Products' WHERE material_type = 'Concrete';
UPDATE material_types SET material_type = 'Structural Steel' WHERE material_type = 'Steel';
UPDATE material_types SET material_type = 'Hardwood' WHERE material_type = 'Wood';

INSERT INTO materials (material_name, amount, price, material_type) VALUES ('Cement', 100, 500.00, 'Concrete');
INSERT INTO materials (material_name, amount, price, material_type) VALUES ('Steel Beams', 50, 1200.00, 'Steel');
INSERT INTO materials (material_name, amount, price, material_type) VALUES ('Wood Planks', 200, 150.00, 'Wood');

SELECT * FROM materials;
SELECT * FROM buildings;

INSERT INTO material_buildings (material_id, building_id) VALUES (1, 2);
INSERT INTO material_buildings (material_id, building_id) VALUES (2, 3);
INSERT INTO material_buildings (material_id, building_id) VALUES (3, 4);

UPDATE material_buildings SET material_id = 4 WHERE material_id = 1 AND building_id = 1;
UPDATE material_buildings SET building_id = 2 WHERE material_id = 2 AND building_id = 2;
UPDATE material_buildings SET material_id = 5 WHERE material_id = 3 AND building_id = 3;

DELETE FROM material_buildings WHERE material_id = 1 AND building_id = 2;
DELETE FROM material_buildings WHERE material_id = 2 AND building_id = 2;
DELETE FROM material_buildings WHERE material_id = 3 AND building_id = 3;

-- Add a New Column
ALTER TABLE employees
ADD COLUMN new_column VARCHAR(50);

-- Modify Column Data Type
ALTER TABLE salaries
MODIFY COLUMN amount DECIMAL(12, 2);

-- Drop a Column
ALTER TABLE buildings
DROP COLUMN building_description;

-- Rename a Table
ALTER TABLE material_types
RENAME TO new_material_types;

-- Add Foreign Key Constraint
ALTER TABLE material_buildings
ADD CONSTRAINT fk_employee_material_buildings FOREIGN KEY (employee_id) REFERENCES employees(id);

SELECT
    *
FROM companies AS c
LEFT JOIN buildings AS b ON b.company_id = c.id
LEFT JOIN building_types AS bt ON b.building_type = bt.building_type
LEFT JOIN building_approvals AS ba ON b.id = ba.building_id
LEFT JOIN employees AS e ON e.company_id = c.id
LEFT JOIN positions AS p ON e.position = p.position
LEFT JOIN salaries AS s ON e.salary_id = s.id
LEFT JOIN cost_estimates AS ce ON b.id = ce.building_id
LEFT JOIN material_buildings AS mb ON b.id = mb.building_id
LEFT JOIN materials AS m ON mb.material_id = m.id
LEFT JOIN material_types AS et ON m.material_type = et.material_type
LEFT JOIN customers AS cu ON b.customer_id = cu.id;

SELECT
    companies.company_name,
    buildings.building_description
FROM companies
INNER JOIN buildings ON companies.id = buildings.company_id;

SELECT
    companies.company_name,
    buildings.building_description
FROM companies
LEFT JOIN buildings ON companies.id = buildings.company_id;

SELECT
    companies.company_name,
    buildings.building_description
FROM companies
RIGHT JOIN buildings ON companies.id = buildings.company_id;

-- Using UNION to combine the results of LEFT JOIN and RIGHT JOIN
SELECT
    companies.id AS company_id,
    companies.company_name,
    buildings.id AS building_id,
    buildings.building_description
FROM companies
LEFT JOIN buildings ON companies.id = buildings.company_id
UNION
SELECT
    companies.id AS company_id,
    companies.company_name,
    buildings.id AS building_id,
    buildings.building_description
FROM companies
RIGHT JOIN buildings ON companies.id = buildings.company_id
WHERE companies.id IS NULL;

SELECT
	buildings.id as building_id,
    buildings.building_description,
    materials.id as material_id,
    materials.material_name,
    material_types.material_type
FROM buildings
INNER JOIN material_buildings ON buildings.id = material_buildings.building_id
INNER JOIN materials ON material_buildings.material_id = materials.id
INNER JOIN material_types ON materials.material_type = material_types.material_type;

-- 7 statements with aggregate functions and group by and without having.
-- Count of Buildings for Each Building Type
SELECT
    building_type,
    COUNT(*) AS building_count
FROM buildings
GROUP BY building_type;

-- Average Salary for Each Position
SELECT
    position,
    AVG(amount) AS average_salary
FROM salaries
GROUP BY position;

-- Total Cost Estimate for Each Company
SELECT
    c.company_name,
    SUM(ce.cost) AS total_cost_estimate
FROM companies c
LEFT JOIN buildings b ON c.id = b.company_id
LEFT JOIN cost_estimates ce ON b.id = ce.building_id
GROUP BY c.company_name;

-- Maximum Amount Paid by Each Customer
SELECT
    cu.first_name,
    cu.last_name,
    MAX(p.amount) AS max_payment_amount
FROM customers cu
LEFT JOIN payments p ON cu.payment_id = p.id
GROUP BY cu.first_name, cu.last_name;

-- Total Material Amount for Each Building
SELECT
    b.building_description,
    SUM(m.amount) AS total_material_amount
FROM buildings b
LEFT JOIN material_buildings mb ON b.id = mb.building_id
LEFT JOIN materials m ON mb.material_id = m.id
GROUP BY b.building_description;

-- Count of Employees in Each Company
SELECT
    c.company_name,
    COUNT(e.id) AS employee_count
FROM companies c
LEFT JOIN employees e ON c.id = e.company_id
GROUP BY c.company_name;

-- Total Base Cost for Each Building Type
SELECT
    bt.building_type,
    SUM(bt.base_cost) AS total_base_cost
FROM building_types bt
LEFT JOIN buildings b ON bt.building_type = b.building_type
GROUP BY bt.building_type;

-- 7 statements with aggregate functions and group by and with having.
-- Count of Buildings for Each Building Type with More than 3 Buildings
SELECT
    building_type,
    COUNT(*) AS building_count
FROM buildings
GROUP BY building_type
HAVING COUNT(*) > 3;

-- Average Salary for Each Position with Average Salary Greater than 50000
SELECT
    position,
    AVG(amount) AS average_salary
FROM salaries
GROUP BY position
HAVING AVG(amount) > 50000;

-- Total Cost Estimate for Each Company with Total Cost Estimate Greater than 100000
SELECT
    c.company_name,
    SUM(ce.cost) AS total_cost_estimate
FROM companies c
LEFT JOIN buildings b ON c.id = b.company_id
LEFT JOIN cost_estimates ce ON b.id = ce.building_id
GROUP BY c.company_name
HAVING SUM(ce.cost) > 100000;

-- Maximum Amount Paid by Each Customer with Maximum Amount Greater than 5000
SELECT
    cu.first_name,
    cu.last_name,
    MAX(p.amount) AS max_payment_amount
FROM customers cu
LEFT JOIN payments p ON cu.payment_id = p.id
GROUP BY cu.first_name, cu.last_name
HAVING MAX(p.amount) > 5000;

-- Total Material Amount for Each Building with Total Amount Greater than 1000
SELECT
    b.building_description,
    SUM(m.amount) AS total_material_amount
FROM buildings b
LEFT JOIN material_buildings mb ON b.id = mb.building_id
LEFT JOIN materials m ON mb.material_id = m.id
GROUP BY b.building_description
HAVING SUM(m.amount) > 1000;

-- Count of Employees in Each Company with More than 5 Employees
SELECT
    c.company_name,
    COUNT(e.id) AS employee_count
FROM companies c
LEFT JOIN employees e ON c.id = e.company_id
GROUP BY c.company_name
HAVING COUNT(e.id) > 5;

-- Total Base Cost for Each Building Type with Total Base Cost Greater than 50000
SELECT
    bt.building_type,
    SUM(bt.base_cost) AS total_base_cost
FROM building_types bt
LEFT JOIN buildings b ON bt.building_type = b.building_type
GROUP BY bt.building_type
HAVING SUM(bt.base_cost) > 50000;



























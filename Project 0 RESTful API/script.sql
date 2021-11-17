DROP TABLE IF EXISTS clients;

CREATE TABLE clients(
	clients_id SERIAL PRIMARY KEY,
	clients_first_name VARCHAR(255) NOT NULL,
	clients_last_name VARCHAR(255) NOT NULL	
);

DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts(
	accounts_id SERIAL PRIMARY KEY,
	clients_id INTEGER NOT NULL,
	accounts_balance INTEGER NOT NULL,
	accounts_age INTEGER NOT NULL,	
	
	CONSTRAINT fk_clients FOREIGN KEY(clients_id)
		REFERENCES clients(clients_id)
);

INSERT INTO clients (clients_first_name, clients_last_name)
VALUES
	('Thomas', 'Hill'),
	('James', 'Simpson'),
	('Tina', 'Smith'),
	('Bryson', 'Williams'),
	('Jessica', 'Thomas'),
	('Henry', 'Caldwell'),
	('Frank', 'Keller'),
	('Jameson', 'Johnson'),
	('Harold', 'Jenkins'),
	('Gina', 'Sampson');

SELECT *
FROM clients;


INSERT INTO accounts (clients_id, accounts_balance, accounts_age)
VALUES
	(1, 1000, 8),
	(1, 450, 6),
	(2, 200, 6),
	(2, 150, 10),
	(2, 2000, 4),
	(3, 4000, 9),
	(3, 800, 5),
	(4, 875, 1),
	(5, 600, 3),
	(5, 5000, 2),
	(4, 1050, 8),
	(6, 457, 6),
	(6, 200, 6),
	(7, 150, 10),
	(7, 2200, 4),
	(7, 4000, 9),
	(8, 800, 5),
	(8, 850, 1),
	(9, 650, 3),
	(9, 5300, 2),
	(8, 850, 1),
	(9, 600, 3);

SELECT *
FROM accounts;

SELECT clients.clients_first_name, clients.clients_last_name, accounts.accounts_balance, accounts.accounts_age
FROM clients, accounts
WHERE accounts.clients_id = clients.clients_id;

SELECT * FROM clients WHERE clients_id = 4;

-- Query specific columns
-- student_id, student_first_name, student_last_name
--SELECT student_id, student_first_name, student_last_name
--FROM students;

-- Querying a specific student from students
--SELECT *
--FROM clients, accounts
--WHERE accounts.clients_id = '1' AND clients.clients_id = '1'

-- Data Maniupulatoin language (INSERT, SELECT, UPDATE, DELETE)

--UPDATE clients
--SET clients_first_name = 'Megan',
--	clients_last_name = 'Do' 	
--WHERE clients.clients_id = '1';

--DELETE students
--	SET student_first_name = 'Megan',
--	 	student_last_name = 'Do',
--	 	student_classification = 'Sophomore',
--	 	student_age = '19'
--where 
--	student_id = '5';

-- grades table
--DROP TABLE IF EXISTS grades;

--CREATE TABLE grades (
--	grade_id SERIAL PRIMARY KEY,
--	grade INTEGER NOT NULL,
--	assignment_name VARCHAR(100) NOT NULL,
--	student_id INTEGER NOT NULL,
	--
--	CONSTRAINT fk_student FOREIGN KEY(student_id)
--		REFERENCES students(student_id)
--);

--INSERT INTO grades (grade, assignment_name, student_id)
--VALUES 
--(85, 'homework 1.1', 1),
--(90, 'homework 1.1', 2),
--(93, 'homework 1.2', 1),
--(92, 'homework 1.2', 2);

--SELECT *
--FROM grades

/*
 * Refential Integrity:
 * 
 * Whenver we create relationships between tables, such as by having a foreign key in one table link to a primary key in another table,
 * SQL ensures that orphan records can never occur. SQL preserves this idea of having integrity with respect to references
 */

--DROP TABLE students;
-- I cannot drop the students table, because the grades table has a foreign key that references a primary key in the students table
-- so, grades is dependent on students
-- If I wanted to drop the students table, I would need to drop the grades table first, and then drop the students table 

--DELETE FROM grades
--WHERE student_id =1;

--DELETE FROM students 
--WHERE student_id =1;
-- I cannot delete student 1, because there are two grades that belong to student 1
-- I would need to delete grades that belong to student 1, before I can delete student 1

/*
 * ACID properties: this is an acronym for the properties of transactions
 * 
 * Atomicity: The transaction entirely succeeds or not at all
 * Consistency: Constraints and referential integrity must be upheld by a transaction. A transaction cannot violate the constraints
 * Isolation: Two transactions should not be interfering with each other as they are happening in a concurrent situation
 * Durability: Once a transaction has been committed, those changes are permanently sotred in a databases' storage memory instaed of RAM
 * 
 * Transaction: a grouping of DML statements that we would like to treat a single operation
 * - Transactions are either
 *  1. committed using the COMMIT statement
 *  2. rolled back using the ROLLBACK statement
 * 
 * However, by default, we don't need to do this whenever we work with DML statements
 *  BECAUSE AUTOCOMMIT is turned on by default
 * 
 * So, to demonstrate transactions in more detail, we will turn off autocommit
 * 
 * We can do this using the setting in DBeaver
 */

--SELECT *
--FROM students

--SELECT *
--FROM grades

-- DML Operations: INSERT, SELECT, UPDATE, DELETE
-- However, we don't really need to commit or rollback SELECT, because SELECT doesn't really make any changes to our database

--DELETE FROM grades
--WHERE grade_id = 3;

--ROLLBACK; -- Can only be used before you commit a transactions' changes. Rollback will go back to the state before the transaction began

--DELETE FROM grades
--WHERE grade_id = 4;

--INSERT INTO grades (grade, asignment_name, student_id)
--VALUES (100, 'Midterm 1', 1);

---CHECKPOINT my_checkpoint;

--COMMIT; -- Once you run the COMMIT command, changes made in a particular transaction will become permanent

/*
 * Scalar and Aggreate functions
 * 
 * Scalar function: a function that acts on individual rows of data
 * Aggregate function: a function that acts on many rows and gives a single value as output
 *
 */

-- LENGTH is a scalar function 
--SELECT student_first_name, LENGTH(student_first_name), student_last_name, LENGTH(student_last_name)
--FROM students;

-- AVE is an aggregate function
--SELECT AVE(student_age)
--FROM students;

--SELECT *
--FROM grades;

--INSERT INTO grades (grade, assignment_name, student_id)
--VALUES
--(70, 'Homework 1.2', 1),
--(75, 'Homework 1.2', 2),
--(70, 'Midterm 2', 1),
--(80, 'Midterm 2', 2);

-- This will average together all of the grade values from the grades table
-- But, because we have homework 1.1 and Midterm 1, it probably doesn't make too much to sense to average these two different
-- types of assignments together
--SELECT AVG(grade)
--FROM grades;

-- what instead?
--SELECT assignment_name, AVG(grade)
--FROM grades
--GROUP BY assignment_name; -- GROUP BY will differnt groups base on that column having the same values
l-- So, we have Group 1: homework 1.1
-- And Group 2: Midterm 1
-- It will find the average within those individual groups instead of finding the entire average

-- Let's say we only wanted to display the assignments where the average grade is greater than 90
--SELECT assignment_name, AVG(grade)
--FROM grades
--WHERE--
--GROUP BY assignment_name
--HAVING AVG(grade) > 80;

-- WHERE v. having
-- WHERE: filters out individual rows of data based on a condition not being method
-- 		ex. WHERE grades > 90: filters out everything 90 or less
-- HAVING: filter out the conditions when we have already grouped toegether data 

-- For WHERE, compare
--SELECT *
--FROM grades 

--  And 
--SELECT *
--FROM grades
--WHERE grade > 80;

/*
 * Joines
 *  1. INNER JOIN
 *  2. OUTER JOIN
 *  3. LEFT JOIN
 *  4. RIGHT JOIN
 */

--SELECT s.student_id, s.student_first_name, g.grade, g.assignment_name
--FROM students s
--INNER JOIN grades g
--ON s.student_id = g.student_id;

--SELECT s.student_id AS sid, s.student_first_name AS first_name, s.student_last_name AS last_name, g.assignment_name AS assignment, g.grade
--FROM students s
--INNER JOIN grades g
--ON s.student_id = g.student_id;

--SELECT s.student_first_name, g.assignment_name, AVG(grade)
--FROM students s 
--ON s.student_id = g.student_id
--GROUP BY s.student_first_name, g.assignment_name;

/*
 * SQL SELECT command clauses
 * 
 * there are many optional clauses that can be used with SELECT statement
 *	- SELECT 
 *	- FROM
 *	- () JOIN...ON...=...
 *	- WHERE
 *	- GROUP BY
 *  - HAVING
 * 	- ORDER BY
 * */

--SELECT *
--FROM grades
--ORDER BY grade; -- ORDER by is ascending by default 

--SELECT *
--FROM grades
--ORDER BY grade DESC;
 --*/





SET NOCOUNT ON;
SET DATEFORMAT DMY;

------------------------------------------------------------------
------------------------------------------------------------------
--                                                              --
-- Anwendung VERLEIH                                            --
--                                                              --
------------------------------------------------------------------
------------------------------------------------------------------

------------------------------------
-- DROP tables ---------------------
------------------------------------

IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'Sals') > 0 DROP TABLE Sals;
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'Items') > 0 DROP TABLE Items;
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'Ords') > 0 DROP TABLE Ords;
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'Custs') > 0 DROP TABLE Custs;
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'Emps') > 0 DROP TABLE Emps;
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'EmpH') > 0 DROP TABLE EmpH;
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'Depts') > 0 DROP TABLE Depts;
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'Prices') > 0 DROP TABLE Prices;
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'Prods') > 0 DROP TABLE Prods;



------------------------------------
-- CREATE tables -------------------
------------------------------------

CREATE TABLE Custs (
  id INT IDENTITY NOT NULL,
  name varchar(45) NULL,
  address varchar(40) NULL,
  city varchar(30) NULL,
  state varchar(2) NULL,
  zip varchar(9)  NULL,
  area smallint NULL,
  phone varchar(9) NULL ,
  creditlimit decimal(9, 2) NULL,
  comments varchar(1000) NULL,
  Emps_id INT NOT NULL
) ON [PRIMARY];

CREATE TABLE Depts (
  id INT IDENTITY NOT NULL,
  deptno smallint NOT NULL,
  name varchar(14) NULL,
  loc varchar(13) NULL
) ON [PRIMARY];

CREATE TABLE EmpH (
  id INT IDENTITY NOT NULL,
  empno INT NOT NULL,
  name varchar(10) NULL,
  title varchar(9) NULL,
  dateout date NOT NULL,
  Depts_id INT NOT NULL
) ON [PRIMARY];

CREATE TABLE Emps (
  id INT NOT NULL,
  empno INT NOT NULL,
  name varchar(10) NULL,
  title varchar(9) NULL,
  hiredate date NULL,
  sal decimal(7,2) NULL,
  comm decimal(7,2) NULL,
  Emps_id INT NULL,
  Depts_id INT NOT NULL
) ON [PRIMARY];

CREATE TABLE Items (
  id INT IDENTITY NOT NULL,
  itemid INT NOT NULL,
  actualprice decimal(8,2) NULL,
  quantity int NULL,
  itemtot decimal(8,2) NULL,
  Prods_id INT NULL,
  Ords_id INT NOT NULL
) ON [PRIMARY];

CREATE TABLE Ords (
  id INT NOT NULL,
  orderdate date NULL,
  shipdate date NULL,
  total decimal(8,2) NULL,
  Custs_id INT NOT NULL
) ON [PRIMARY];

CREATE TABLE Prices (
  id INT IDENTITY NOT NULL,
  stdprice decimal(8,2) NULL,
  minprice decimal(8,2) NULL,
  startdate date NULL,
  enddate date NULL,
  Prods_id INT NOT NULL
) ON [PRIMARY];

CREATE TABLE Prods (
  id INT NOT NULL,
  description varchar(30) NULL
) ON [PRIMARY];

CREATE TABLE Sals (
  id INT IDENTITY NOT NULL,
  grade INT NULL,
  lowsal INT NULL,
  highsal INT NULL
) ON [PRIMARY];


------------------------------------
-- CONSTRAINTs ---------------------
------------------------------------

ALTER TABLE [dbo].[Custs] WITH NOCHECK ADD 
	CONSTRAINT [PK_Custs] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 

ALTER TABLE [dbo].[Depts] WITH NOCHECK ADD 
	CONSTRAINT [PK_Depts] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 

ALTER TABLE [dbo].[EmpH] WITH NOCHECK ADD 
	CONSTRAINT [PK_EmpH] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 

ALTER TABLE [dbo].[Emps] WITH NOCHECK ADD 
	CONSTRAINT [PK_Emps] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 

ALTER TABLE [dbo].[Items] WITH NOCHECK ADD 
	CONSTRAINT [PK_Items] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 

ALTER TABLE [dbo].[Ords] WITH NOCHECK ADD 
	CONSTRAINT [PK_Ords] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 

ALTER TABLE [dbo].[Prices] WITH NOCHECK ADD 
	CONSTRAINT [PK_Prices] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 

ALTER TABLE [dbo].[Prods] WITH NOCHECK ADD 
	CONSTRAINT [PK_Prods] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 

ALTER TABLE [dbo].[Sals] WITH NOCHECK ADD 
	CONSTRAINT [PK_Sals] PRIMARY KEY  CLUSTERED 
	(
		[id]
	)  ON [PRIMARY] 

ALTER TABLE [dbo].[EmpH] ADD 
	CONSTRAINT [FK_EmpH_Depts] FOREIGN KEY 
	(
		[Depts_id]
	) REFERENCES [dbo].[Depts] (
		[id]
	)

ALTER TABLE [dbo].[Emps] ADD 
	CONSTRAINT [FK_Emps_Depts] FOREIGN KEY 
	(
		[Depts_id]
	) REFERENCES [dbo].[Depts] (
		[id]
	),
	CONSTRAINT [FK_Emps_Emps] FOREIGN KEY 
	(
		[Emps_id]
	) REFERENCES [dbo].[Emps] (
		[id]
	)

ALTER TABLE [dbo].[Items] ADD 
	CONSTRAINT [FK_Items_Ords] FOREIGN KEY 
	(
		[Ords_id]
	) REFERENCES [dbo].[Ords] (
		[id]
	),
	CONSTRAINT [FK_Items_Prods] FOREIGN KEY 
	(
		[Prods_id]
	) REFERENCES [dbo].[Prods] (
		[id]
	)

ALTER TABLE [dbo].[Custs] ADD 
	CONSTRAINT [FK_Custs_Emps] FOREIGN KEY 
	(
		[Emps_id]
	) REFERENCES [dbo].[Emps] (
		[id]
	)

ALTER TABLE [dbo].[Ords] ADD 
	CONSTRAINT [FK_Ords_Custs] FOREIGN KEY 
	(
		[Custs_id]
	) REFERENCES [dbo].[Custs] (
		[id]
	)

ALTER TABLE [dbo].[Prices] ADD 
	CONSTRAINT [FK_Prices_Prods] FOREIGN KEY 
	(
		[Prods_id]
	) REFERENCES [dbo].[Prods] (
		[id]
	)



------------------------------------
-- INSERT INTO tables --------------
------------------------------------

INSERT INTO Prods (id, description)
VALUES (100860, 'ACE Tennis Racket I');

INSERT INTO Prods (id, description)
VALUES (100861, 'ACE Tennis Racket II');

INSERT INTO Prods (id, description)
VALUES (100870, 'ACE Tennis Balls-3 Pack');

INSERT INTO Prods (id, description)
VALUES (100871, 'ACE Tennis Balls-6 Pack');

INSERT INTO Prods (id, description)
VALUES (100890, 'ACE Tennis Net');

INSERT INTO Prods (id, description)
VALUES (101860, 'SP Tennis Racket');

INSERT INTO Prods (id, description)
VALUES (101863, 'SP Junior Racket');

INSERT INTO Prods (id, description)
VALUES (102130, 'RH Guide to Tennis');

INSERT INTO Prods (id, description)
VALUES (200376, 'SB Energy Bar-6 Pack');

INSERT INTO Prods (id, description)
VALUES (200380, 'SB Vita Snack-6 Pack');

-- Prices
INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (100871, 4.8, 3.2, '01-01-85', '01-12-85');

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (100890, 58, 46.4, '01-01-85', NULL);

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (100890, 54, 40.5, '01-06-84', '31-05-84');

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (100860, 35, 28, '01-01-86', NULL);;

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (100860, 32, 25.6, '01-01-86', '31-05-86');

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (100860, 30, 24, '01-01-85', '31-12-85');

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (100861, 45, 36, '01-06-86', NULL);;

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (100861, 42, 33.6, '01-01-86', '31-05-86');

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (100861, 39, 31.2, '01-01-85', '31-12-85');

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (100870, 2.8, 2.4, '01-01-86', NULL);;

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (100870, 2.4, 1.9, '01-01-85', '01-12-85');

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (100871, 5.6, 4.8, '01-01-86', NULL);

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (101860, 24, 18, '15-02-85', NULL);

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (101863, 12.5, 9.4, '15-02-85', NULL);

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (102130, 3.4, 2.8, '18-08-85', NULL);

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (200376, 2.4, 1.75, '15-11-86', NULL);

INSERT INTO Prices (Prods_id, stdprice, minprice, startdate, enddate)
VALUES (200380, 4, 3.2, '15-11-86', NULL);

-- Depts
INSERT INTO Depts (deptno, name, loc)
VALUES (10, 'Accounting', 'New York');

INSERT INTO Depts (deptno, name, loc)
VALUES (20, 'Research', 'Dallas');

INSERT INTO Depts (deptno, name, loc)
VALUES (30, 'Sales', 'Chicago');

INSERT INTO Depts (deptno, name, loc)
VALUES (40, 'Operations', 'Boston');

-- EmpH
INSERT INTO EmpH (empno, name, title, dateout, Depts_id)
VALUES (6087, 'Spencer' , 'Operator', '27-11-81', 2);

INSERT INTO EmpH (empno, name, title, dateout, Depts_id)
VALUES (6185, 'Vandyke' , 'Manager', '17-01-81', 1);

INSERT INTO EmpH (empno, name, title, dateout, Depts_id)
VALUES (6235, 'Balford' , 'Clerk', '22-02-81', 2);

INSERT INTO EmpH (empno, name, title, dateout, Depts_id)
VALUES (7788, 'Scott' , NULL, '05-05-81', 2);

INSERT INTO EmpH (empno, name, title, dateout, Depts_id)
VALUES (7001, 'Jewell' , 'Analyst', '10-06-81', 3);

INSERT INTO EmpH (empno, name, title, dateout, Depts_id)
VALUES (7499, 'Allen' , 'Salesman', '01-08-81', 2);

INSERT INTO EmpH (empno, name, title, dateout, Depts_id)
VALUES (7225, 'Briggs' , 'Pay Clerk', '27-11-81', 1);

INSERT INTO EmpH (empno, name, title, dateout, Depts_id)
VALUES (7782, 'Clark' , 'Manager', '12-02-81', 1);

INSERT INTO EmpH (empno, name, title, dateout, Depts_id)
VALUES (7356, 'Wild' , 'Director', '01-11-81', 1);

-- Emps
INSERT INTO Emps (id, empno, name, title, Emps_id, hiredate, sal, comm, Depts_id)
VALUES (1, 7839, 'King', 'President', NULL, '17-11-81', 5000, NULL, 1);

INSERT INTO Emps (id, empno, name, title, Emps_id, hiredate, sal, comm, Depts_id)
VALUES (2, 7698, 'Blake', 'Manager', 1, '01-05-81', 2850, NULL, 3);

INSERT INTO Emps (id, empno, name, title, Emps_id, hiredate, sal, comm, Depts_id)
VALUES (3, 7782, 'Clark', 'Manager', 1, '09-06-81', 2450, NULL, 1);

INSERT INTO Emps (id, empno, name, title, Emps_id, hiredate, sal, comm, Depts_id)
VALUES (4, 7566, 'Jones', 'Manager', 1, '02-04-81', 2975, NULL, 2);

INSERT INTO Emps (id, empno, name, title, Emps_id, hiredate, sal, comm, Depts_id)
VALUES (5, 7654, 'Martin', 'Salesman', 2, '28-09-81', 1250, 1400, 3);

INSERT INTO Emps (id, empno, name, title, Emps_id, hiredate, sal, comm, Depts_id)
VALUES (6, 7499, 'Allen', 'Salesman', 2, '20-02-81', 1600, 300, 3);

INSERT INTO Emps (id, empno, name, title, Emps_id, hiredate, sal, comm, Depts_id)
VALUES (7, 7844, 'Turner', 'Salesman', 2, '08-09-81', 1500, 0, 3);

INSERT INTO Emps (id, empno, name, title, Emps_id, hiredate, sal, comm, Depts_id)
VALUES (8, 7900, 'James', 'Clerk', 2, '03-12-81', 950, NULL, 3);

INSERT INTO Emps (id, empno, name, title, Emps_id, hiredate, sal, comm, Depts_id)
VALUES (9, 7521, 'Ward', 'Salesman', 2, '22-02-81', 1250, 500, 3);

INSERT INTO Emps (id, empno, name, title, Emps_id, hiredate, sal, comm, Depts_id)
VALUES (10, 7902, 'Ford', 'Analyst', 4, '03-12-81', 3000, NULL, 2);

INSERT INTO Emps (id, empno, name, title, Emps_id, hiredate, sal, comm, Depts_id)
VALUES (11, 7369, 'Smith', 'Clerk', 10, '17-12-80', 800, NULL, 2);

INSERT INTO Emps (id, empno, name, title, Emps_id, hiredate, sal, comm, Depts_id)
VALUES (12, 7788, 'Scott', 'Analyst', 4, '09-12-82', 3000, NULL, 2);

INSERT INTO Emps (id, empno, name, title, Emps_id, hiredate, sal, comm, Depts_id)
VALUES (13, 7876, 'Adams', 'Clerk', 12, '12-01-83', 1100, NULL, 2);

INSERT INTO Emps (id, empno, name, title, Emps_id, hiredate, sal, comm, Depts_id)
VALUES (14, 7934, 'Miller', 'Clerk', 3, '23-01-82', 1300, NULL, 1);

-- Custs
INSERT INTO Custs
(name, address, city, state, zip, area, phone, Emps_id, creditlimit, comments)
VALUES
('Jocksports', '345 Viewridge', 'Belmont', 'CA', '96711', 415, '598-6609', 7, 5000, 'Very friendly people to work with -- sales rep likes to be called Mike.');

INSERT INTO Custs
(name, address, city, state, zip, area, phone, Emps_id, creditlimit, comments)
VALUES
('TKB Sport Shop', '490 Boli Rd.', 'Redwood City', 'CA', '94061', 415, '368-1223', 9, 10000, 'Rep called 5/8 about change in order - contact shipping.');

INSERT INTO Custs
(name, address, city, state, zip, area, phone, Emps_id, creditlimit, comments)
VALUES
('Vollyrite', '9722 Hamilton', 'Burlingame', 'CA', '95133', 415, '644-3341', 5, 7000, 'Company doing heavy promotion beginning 10/89. Prepare for large Ords during Ords during winter');

INSERT INTO Custs
(name, address, city, state, zip, area, phone, Emps_id, creditlimit, comments)
VALUES
('Just Tennis', 'Hillview Mall', 'Burlingame', 'CA', '97544', 415, '677-9312', 9, 3000, 'Contact rep about new line of tennis rackets.');

INSERT INTO Custs
(name, address, city, state, zip, area, phone, Emps_id, creditlimit, comments)
VALUES
('Every Mountain', '574 Surry Rd.', 'Cupertino', 'CA', '93301', 408, '996-2323', 6, 10000, 'Customer with high market share (23%) due to aggressive advertising.');

INSERT INTO Custs
(name, address, city, state, zip, area, phone, Emps_id, creditlimit, comments)
VALUES
('K + T Sports', '3476 El Paso', 'Santa Clara', 'CA', '91003', 408, '376-9966', 7, 5000, 'Tends to order large amounts of merchandise at once. Accounting is considering raising their credit limit');

INSERT INTO Custs
(name, address, city, state, zip, area, phone, Emps_id, creditlimit, comments)
VALUES
('Shape Up', '908 Sequoia', 'Palo Alto', 'CA', '94301', 415, '364-9777', 9, 6000, 'Support intensive. Ords small amounts (< 800) of merchandise at a time.');

INSERT INTO Custs
(name, address, city, state, zip, area, phone, Emps_id, creditlimit, comments)
VALUES
('Womens Sports', 'Valco Village', 'Sunnyvale', 'CA', '93301', 408, '967-4398', 6, 10000, 'First sporting goods store geared exclusively towards women. Unusual promotional style ');

INSERT INTO Custs
(name, address, city, state, zip, area, phone, Emps_id, creditlimit, comments)
VALUES
('North Woods Health and Fitness Supply Center', '98 Lone Pine Way', 'Hibbing', 'MN', '55649', 612, '566-9123', 7, 8000, NULL);

-- Ords
INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (610, '07-01-87', 2, '08-01-87', 101.4);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (611, '11-01-87', 3, '11-01-87', 45);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (612, '15-01-87', 5, '20-01-87', 5860);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (601, '01-05-86', 7, '30-05-86', 2.4);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (602, '05-06-86', 3, '20-06-86', 56);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (604, '15-06-86', 7, '30-06-86', 698);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (605, '14-07-86', 7, '30-07-86', 8324);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (606, '14-07-86', 1, '30-07-86', 3.4);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (609, '01-08-86', 1, '15-08-86', 97.5);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (607, '18-07-86', 5, '18-07-86', 5.6);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (608, '25-07-86', 5, '25-07-86', 35.2);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (603, '05-06-86', 3, '05-06-86', 224);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (620, '12-03-87', 1, '12-03-87', 4450);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (613, '01-02-87', 9, '01-02-87', 6400);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (614, '01-02-87', 3, '05-02-87', 23940);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (616, '03-02-87', 4, '10-02-87', 764);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (619, '22-02-87', 5, '04-02-87', 1260);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (617, '05-02-87', 6, '03-03-87', 46370);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (615, '01-02-87', 8, '06-02-87', 710);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (618, '15-02-87', 3, '06-03-87', 3510.5);

INSERT INTO Ords (id, orderdate, Custs_id, shipdate, total)
VALUES (621, '15-03-87', 1, '01-01-87', 730);

-- Items
INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (610, 3, 100890, 58, 1, 58);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (611, 1, 100861, 45, 1, 45);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (612, 1, 100860, 30, 100, 3000);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (601, 1, 200376, 2.4, 1, 2.4);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (602, 1, 100870, 2.8, 20, 56);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (604, 1, 100890, 58, 3, 174);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (604, 2, 100861, 42, 2, 84);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (604, 3, 100860, 44, 10, 440);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (610, 3, 100890, 58, 1, 58);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (603, 2, 100860, 56, 4, 224);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (610, 1, 100860, 35, 1, 35);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (610, 2, 100870, 2.8, 3, 8.4);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (613, 4, 200376, 2.2, 200, 440);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (614, 1, 100860, 35, 444, 15540);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (614, 2, 100870, 2.8, 1000, 2800);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (612, 2, 100861, 40.5, 20, 810);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (612, 3, 101863, 10, 150, 1500);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (620, 1, 100860, 35, 10, 350);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (620, 2, 200376, 2.4, 1000, 2400);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (620, 3, 102130, 3.4, 500, 1700);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (613, 1, 100871, 5.6, 100, 560);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (613, 2, 101860, 24, 200, 4800);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (613, 3, 200380, 4, 150, 600);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (619, 3, 102130, 3.4, 100, 340);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (617, 1, 100860, 35, 50, 1750);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (617, 2, 100861, 45, 100, 4500);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (614, 3, 100871, 5.6, 1000, 5600);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (616, 1, 100861, 45, 10, 450);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (616, 2, 100870, 2.8, 50, 140);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (616, 3, 100890, 58, 2, 116);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (616, 4, 102130, 3.4, 10, 34);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (616, 5, 200376, 2.4, 10, 24);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (619, 1, 200380, 4, 100, 400);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (619, 2, 200376, 2.4, 100, 240);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (615, 1, 100861, 45, 4, 180);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (607, 1, 100871, 5.6, 1, 5.6);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (615, 2, 100870, 2.8, 100, 280);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (617, 3, 100870, 2.8, 500, 1400);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (617, 4, 100871, 5.6, 500, 2800);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (617, 5, 100890, 58, 500, 29000);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (617, 6, 101860, 24, 100, 2400);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (617, 7, 101863, 12.5, 200, 2500);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (617, 8, 102130, 3.4, 100, 340);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (617, 9, 200376, 2.4, 200, 480);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (617, 10, 200380, 4, 300, 1200);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (609, 2, 100870, 2.5, 5, 12.5);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (609, 3, 100890, 50, 1, 50);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (618, 1, 100860, 35, 23, 805);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (618, 2, 100861, 45.11, 50, 2255.5);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (618, 3, 100870, 45, 10, 450);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (621, 1, 100861, 45, 10, 450);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (621, 2, 100870, 2.8, 100, 280);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (615, 3, 100871, 5, 50, 250);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (608, 1, 101860, 24, 1, 24);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (608, 2, 100871, 5.6, 2, 11.2);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (609, 1, 100861, 35, 1, 35);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (606, 1, 102130, 3.4, 1, 3.4);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (605, 1, 100861, 45, 100, 4500);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (605, 2, 100870, 2.8, 500, 1400);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (605, 3, 100890, 58, 5, 290);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (605, 4, 101860, 24, 50, 1200);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (605, 5, 101863, 9, 100, 900);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (605, 6, 102130, 3.4, 10, 34);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (612, 4, 100871, 5.5, 100, 550);

INSERT INTO Items (Ords_id, itemid, Prods_id, actualprice, quantity, itemtot)
VALUES (619, 4, 100871, 5.6, 50, 280);

-- Sals
INSERT INTO Sals (grade, lowsal, highsal)
VALUES (1, 700, 1200);

INSERT INTO Sals (grade, lowsal, highsal)
VALUES (2, 1202, 1400);

INSERT INTO Sals (grade, lowsal, highsal)
VALUES (3, 1401, 2000);

INSERT INTO Sals (grade, lowsal, highsal)
VALUES (4, 2001, 3000);

INSERT INTO Sals (grade, lowsal, highsal)
VALUES (5, 3001, 9999);






---------------------------------
---------------------------------
--                             --
-- MAKLERFIRMA                 --
--                             --
---------------------------------
---------------------------------
SET DATEFORMAT DMY

IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'Viewing') > 0 DROP TABLE Viewing;
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'PropertyForRent') > 0 DROP TABLE PropertyForRent;
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'Staff') > 0 DROP TABLE Staff;
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'Branch') > 0 DROP TABLE Branch;
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'Client') > 0 DROP TABLE Client;

CREATE TABLE Branch (
  branchNo	CHAR(4) NOT NULL PRIMARY KEY,
  city		VARCHAR(15)
);

CREATE TABLE Staff (
  staffNo	VARCHAR(5) NOT NULL PRIMARY KEY,
  fName		VARCHAR(10),
  lName		VARCHAR(15),
  position	VARCHAR(15),
  sex		CHAR(1),
  salary	INT,
  branchNo	CHAR(4)
);

ALTER TABLE Staff ADD 
	CONSTRAINT [FK_Staff_Branch_BranchNo] FOREIGN KEY 
	(
		branchNo
	) REFERENCES Branch (
		branchNo
	);

CREATE TABLE PropertyForRent (
  propertyNo	VARCHAR(5) NOT NULL PRIMARY KEY,
  street		VARCHAR(15),
  city			VARCHAR(10),
  rooms			INT,
  staffNo		VARCHAR(5),
  branchNo		CHAR(4)
);

ALTER TABLE PropertyForRent ADD 
	CONSTRAINT [FK_PropForRent_Branch_BranchNo] FOREIGN KEY 
	(
		branchNo
	) REFERENCES Branch (
		branchNo
	);

ALTER TABLE PropertyForRent ADD 
	CONSTRAINT [FK_Staff_StaffNo] FOREIGN KEY 
	(
		staffNo
	) REFERENCES Staff (
		staffNo
	);

CREATE TABLE Client (
  clientNo	CHAR(4) NOT NULL PRIMARY KEY,
  fName		VARCHAR(10),
  lName		VARCHAR(15)
);

CREATE TABLE Viewing (
  clientNo		CHAR(4) NOT NULL,
  propertyNo	VARCHAR(5) NOT NULL,
  viewDate		DATE NOT NULL,
  comment		VARCHAR(50)
);

ALTER TABLE Viewing WITH NOCHECK ADD 
	CONSTRAINT [PK_Viewing] PRIMARY KEY  CLUSTERED 
	(
		clientNo, propertyNo, viewDate
	);


-- Branches
INSERT INTO Branch (branchNo, city)
VALUES ('B003', 'Glasgow');

INSERT INTO Branch (branchNo, city)
VALUES ('B004', 'London');

INSERT INTO Branch (branchNo, city)
VALUES ('B005', 'St. Andrews');

-- Staff
INSERT INTO Staff (staffNo, fName, lName, position, sex, salary, branchNo)
VALUES ('SL21', 'John', 'White', 'Manager', 'M', 30000, 'B005');

INSERT INTO Staff (staffNo, fName, lName, position, sex, salary, branchNo)
VALUES ('SG37', 'Ann', 'Beech', 'Assistant', 'F', 12000, 'B003');

INSERT INTO Staff (staffNo, fName, lName, position, sex, salary, branchNo)
VALUES ('SG14', 'David', 'Ford', 'Supervisor', 'M', 18000, 'B003');

INSERT INTO Staff (staffNo, fName, lName, position, sex, salary, branchNo)
VALUES ('SG5', 'Susan', 'Brand', 'Manager', 'F', 24000, 'B003');

INSERT INTO Staff (staffNo, fName, lName, position, sex, salary, branchNo)
VALUES ('SA9', 'Mary', 'Howe', 'Assistant', 'F', 9000, 'B005');

INSERT INTO Staff (staffNo, fName, lName, position, sex, salary, branchNo)
VALUES ('SL41', 'Julie', 'Lee', 'Assistant', 'F', 9000, 'B004');

-- PropertyForRents
INSERT INTO PropertyForRent (propertyNo, street, city, rooms, staffNo, branchno)
VALUES ('PA14', '16 Holhead', 'Aberdeen', 4, 'SL21', 'B005');

INSERT INTO PropertyForRent (propertyNo, street, city, rooms, staffNo, branchno)
VALUES ('PL94', '6 Argyll St', 'London', 2, 'SG5', 'B003');

INSERT INTO PropertyForRent (propertyNo, street, city, rooms, staffNo, branchno)
VALUES ('PG4', '6 Lawrence St', 'Glasgow', 3, 'SL21', 'B005');

INSERT INTO PropertyForRent (propertyNo, street, city, rooms, staffNo, branchno)
VALUES ('PG36', '2 Manor Rd', 'Glasgow', 3, 'SL21', 'B005');

INSERT INTO PropertyForRent (propertyNo, street, city, rooms, staffNo, branchno)
VALUES ('PG21', '18 Dale Rd', 'Glasgow', 8, 'SG14', 'B003');

INSERT INTO PropertyForRent (propertyNo, street, city, rooms, staffNo, branchno)
VALUES ('PG16', '5 Novar Dr', 'Glasgow', 5, 'SG5', 'B003');

-- Clients
INSERT INTO Client (clientNo, fName, lName)
VALUES ('CR76', 'John', 'Kay');

INSERT INTO Client (clientNo, fName, lName)
VALUES ('CR56', 'Aline', 'Stewart');

INSERT INTO Client (clientNo, fName, lName)
VALUES ('CR74', 'Mike', 'Ritchie');

INSERT INTO Client (clientNo, fName, lName)
VALUES ('CR62', 'Mary', 'Tregear');

-- Viewings
INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR56', 'PA14', '24-05-09', 'too small');

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR76', 'PG4', '08-08-11', 'too remote');

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR56', 'PG4', '26-05-10', NULL);

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR62', 'PA14', '14-05-11', 'no dining room');

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR56', 'PG36', '28-04-11', NULL);

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR56', 'PA14', '09-08-11', 'too small');

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR76', 'PG4', '20-04-10', 'too remote');

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR56', 'PG4', '08-11-11', NULL);

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR62', 'PA14', '02-02-12', 'no dining room');

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR56', 'PG36', '02-01-12', NULL);

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR56', 'PA14', '05-12-12', 'too small');

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR76', 'PG4', '26-01-09', 'too remote');

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR56', 'PG4', '01-03-12', NULL);

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR62', 'PA14', '18-07-11', 'no dining room');

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR56', 'PG36', '14-04-11', NULL);

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR56', 'PA14', '18-02-12', 'too small');

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR76', 'PG4', '27-02-12', 'too remote');

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR56', 'PG4', '19-09-11', NULL);

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR62', 'PA14', '14-03-12', 'no dining room');

INSERT INTO Viewing (clientNo, propertyNo, viewDate, comment)
VALUES ('CR56', 'PG36', '23-12-11', NULL);







------------------------------------------------------------------
------------------------------------------------------------------
--                                                              --
-- Tabellen fuer Uebungen                                       --
--                                                              --
------------------------------------------------------------------
------------------------------------------------------------------

------------------- leere Tabelle
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'U_Autoren') > 0 DROP TABLE U_Autoren;
CREATE TABLE U_Autoren (id INT IDENTITY PRIMARY KEY, name VARCHAR(50), geb DATE);



------------------- Flugzeuge/Piloten
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'U_Piloten') > 0 DROP TABLE U_Piloten;
CREATE TABLE U_Piloten (id INT IDENTITY NOT NULL PRIMARY KEY, name VARCHAR(15))

IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'U_Flugzeuge') > 0 DROP TABLE U_Flugzeuge;
CREATE TABLE U_Flugzeuge (id INT IDENTITY NOT NULL PRIMARY KEY, bez INT)

IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'U_PilotenFlugzeuge') > 0 DROP TABLE U_PilotenFlugzeuge;
CREATE TABLE U_PilotenFlugzeuge (id INT IDENTITY NOT NULL PRIMARY KEY, Piloten_id INT, Flugzeuge_id INT)

INSERT INTO U_Piloten (name)
SELECT	'Snoopy'
UNION SELECT 'Meyer'
UNION SELECT 'Müller'
UNION SELECT 'Lüdenscheid'

INSERT INTO U_Flugzeuge (bez)
SELECT	707
UNION SELECT 727
UNION SELECT 747
UNION SELECT 777

INSERT INTO U_PilotenFlugzeuge (Piloten_id, Flugzeuge_id)
SELECT	1, 1
UNION SELECT 1, 2
UNION SELECT 1, 3
UNION SELECT 2, 1
UNION SELECT 2, 2
UNION SELECT 3, 1
UNION SELECT 3, 2
UNION SELECT 3, 3
UNION SELECT 3, 4
UNION SELECT 4, 2





------------------- ZeitDifferenz
IF (SELECT COUNT(*) FROM tempdb.dbo.sysobjects WHERE name LIKE '#tmp_ZeitDiff_%') > 0 DROP TABLE #tmp_ZeitDiff;
CREATE TABLE #tmp_ZeitDiff (
        id INT IDENTITY PRIMARY KEY,
        creationDate INT NOT NULL,
        creationTime INT NOT NULL,
        updateDatetime DATETIME NULL
);
 
SET DATEFORMAT YMD
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120130, 81015, 'Jan 30 2012  8:17AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120130, 143515, 'Jan 30 2012  2:48PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120130, 143722, 'Jan 30 2012  2:48PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120130, 143640, 'Jan 30 2012  2:45PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120130, 143608, 'Jan 30 2012  2:45PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120130, 143705, 'Jan 30 2012  2:45PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120130, 143737, 'Jan 30 2012  2:45PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120130, 143725, 'Jan 30 2012  2:46PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120131, 112147, 'Jan 31 2012 11:27AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120131, 143629, 'Jan 31 2012  3:55PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120131, 143914, 'Jan 31 2012  3:53PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120131, 144050, 'Jan 31 2012  3:59PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120131, 144010, 'Jan 31 2012  5:06PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120131, 144026, 'Jan 31 2012  3:55PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120131, 144026, 'Feb  1 2012  9:45AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120131, 143921, 'Jan 31 2012  6:50PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 94240, 'Feb  1 2012 10:44AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 94420, 'Feb  1 2012 10:17AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 94444, 'Feb  1 2012 10:40AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 94502, 'Feb  1 2012 10:20AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 94203, 'Feb  1 2012 10:17AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 94534, 'Feb  1 2012 10:38AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 94316, 'Feb  1 2012 10:19AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 94603, 'Feb  1 2012 10:18AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 95247, 'Feb  1 2012 11:55AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 95325, 'Feb  1 2012 11:45AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 95156, 'Feb  1 2012  9:59AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 95258, 'Feb  1 2012 11:17AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 95416, 'Feb  1 2012 11:15AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 95310, 'Feb  1 2012 11:38AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 95340, 'Feb  1 2012 11:46AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 95425, 'Feb  1 2012 11:45AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 95433, 'Feb  1 2012 11:16AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 95530, 'Feb  1 2012 11:47AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 95421, 'Feb  1 2012 11:17AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 95518, 'Feb  1 2012 11:16AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 103202, 'Feb  1 2012  1:02PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 105155, 'Feb  1 2012 12:06PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 121758, 'Feb  1 2012  1:32PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 132412, 'Feb  1 2012  1:38PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 150717, 'Feb  1 2012  3:25PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 150959, 'Feb  1 2012  3:24PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 151217, 'Feb  1 2012  3:23PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 160230, 'Feb  1 2012  4:08PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120201, 201745, 'Feb  1 2012  8:51PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120202, 94148, 'Feb  2 2012  9:51AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120202, 170944, 'Feb  2 2012  6:15PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120202, 170849, 'Feb  2 2012  6:12PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120202, 171040, 'Feb  2 2012  6:12PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120202, 171729, 'Feb  2 2012  6:21PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120202, 175451, 'Feb  2 2012  7:02PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120202, 180404, 'Feb  2 2012  6:14PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120202, 181014, 'Feb  2 2012  7:19PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120202, 181047, 'Feb  2 2012  7:14PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120202, 182544, 'Feb  2 2012  6:59PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120202, 203107, 'Feb  2 2012  8:40PM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80235, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80239, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80122, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80320, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80352, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80418, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80427, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80436, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80418, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80444, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80329, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80515, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80502, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80610, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80415, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80543, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80649, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80754, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80727, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80823, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 80849, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 81015, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 81056, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 81106, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 81050, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 81254, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 81034, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 81428, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 81603, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 81605, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 81517, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 81730, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 81817, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 81921, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 82157, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 82230, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 82316, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 82518, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 82432, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 82610, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 82732, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 82748, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 82807, 'Feb  4 2012 12:00AM');
INSERT INTO #tmp_ZeitDiff (creationDate, creationTime, updateDatetime) VALUES (20120203, 82728, 'Feb  4 2012 12:00AM');






-------------------- ChangeDate
IF (SELECT COUNT(*) FROM tempdb.dbo.sysobjects WHERE name LIKE '#tmp_ChangeDate_%') > 0 DROP TABLE #tmp_ChangeDate;
CREATE TABLE #tmp_ChangeDate (id INT IDENTITY, itemID INT, name VARCHAR(50), status INT, changeDate DATETIME)
 
 
 
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22358870, 'stadtkämmerei erfurt', 1, '2012-02-03 15:33:18.993')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22395884, 'metzger schneider gmbh', 1, '2012-02-06 11:37:33.650')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22406269, 'bewag', 1, '2012-02-06 17:06:34.440')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22409830, '424,19 Euro', 1, '2012-02-06 21:28:09.163')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22435678, 'sen.heim fries.wohnpark', 1, '2012-02-07 19:52:56.420')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22445519, 'enrike kiekenbusch', 1, '2012-02-08 11:55:17.900')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22462499, 'stadt füssen', 1, '2012-02-09 13:42:50.357')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22479479, 'unterwegs', 1, '2012-02-10 15:16:45.407')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22498025, 'kath. kist-amt muc', 1, '2012-02-12 13:26:11.727')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22522144, 'Bayrischer Club e. V.', 1, '2012-02-14 09:18:29.350')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22602828, 'albert meyn', 1, '2012-02-20 12:04:59.867')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22661302, 'christian krümel', 1, '2012-02-24 17:07:25.843')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22662513, 'easy tours gmbh', 1, '2012-02-24 09:49:40.470')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22687688, 'stefa gefer', 1, '2012-02-27 10:37:53.827')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692177, 'christoph gerlach', 1, '2012-02-27 15:20:23.590')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692320, 'thea harrer', 1, '2012-02-27 15:10:33.427')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692331, 'touristik service center', 1, '2012-02-27 16:48:19.050')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692354, 'energie hoch drei', 1, '2012-02-27 19:14:28.610')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692363, 'florian bütterich', 1, '2012-02-27 20:06:48.447')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692394, 'touristikservicecenter gmbh', 1, '2012-02-27 16:46:20.103')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692560, 'dr. geier andreas', 1, '2012-02-27 16:55:08.820')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692689, 'ac höhenfried', 1, '2012-02-27 20:07:40.883')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22719459, 'bekannt', 1, '2012-02-28 16:35:16.777')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22724860, 'bay. ärzteversorgung', 1, '2012-02-28 19:27:21.697')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22736267, 'hansekontor wismar gmbh', 1, '2012-02-29 11:31:20.133')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22775909, 'dak', 1, '2012-03-02 13:34:02.210')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22809660, 'dr hammerl pas', 1, '2012-03-05 14:11:47.173')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22884316, 'bernhard metz', 1, '2012-03-08 08:53:11.923')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22897526, 're.v. 07.03.12', 1, '2012-03-08 16:18:13.703')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22935027, 'fa. wissmar', 1, '2012-03-12 10:14:53.613')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22358870, 'stadtkämmerei erfurt', 2, '2012-02-03 10:36:33.197')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22406269, 'Vattenfall Europe', 2, '2012-02-06 16:48:59.900')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22409830, '', 2, '2012-02-06 19:50:18.033')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22602828, 'albert meyn', 2, '2012-02-20 09:37:42.323')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22661302, 'christian krümel', 2, '2012-02-24 08:29:33.040')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22687688, 'stefa gefer', 2, '2012-02-26 12:11:49.717')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692177, 'christoph gerlach', 2, '2012-02-27 10:52:59.893')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692320, 'thea harrer', 2, '2012-02-27 10:57:20.863')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692331, 'touristik service center', 2, '2012-02-27 10:57:36.017')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692354, 'energie hoch drei', 2, '2012-02-27 10:57:47.670')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692363, 'florian bütterich', 2, '2012-02-27 10:59:08.603')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692394, 'touristikservicecenter gmbh', 2, '2012-02-27 10:59:32.363')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692560, 'dr. geier andreas', 2, '2012-02-27 11:04:10.963')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22692689, 'ac höhenfried', 2, '2012-02-27 11:08:08.227')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22719459, 'bitte ergaenzen', 2, '2012-02-28 15:07:50.503')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22775909, 'dak', 2, '2012-03-02 11:29:47.083')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22809660, 'dr hammerl', 2, '2012-03-05 10:06:44.280')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22884316, 'bernhard metz', 2, '2012-03-08 08:15:18.350')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22897526, 're.v. 07.03.12', 2, '2012-03-08 15:58:11.327')
INSERT INTO #tmp_ChangeDate (itemID, name, status, changeDate) VALUES (22935027, 'fa. wissmar', 2, '2012-03-11 16:49:29.513')







IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%MyEmps%') > 0
EXEC('DROP TABLE MyEmps')
GO
CREATE TABLE [dbo].[MyEmps](
	[id] [int] NOT NULL,
	[last_name] [varchar](25) NULL,
	[first_name] [varchar](25) NULL,
	[user_id] [varchar](8) NULL,
	[salary] [decimal](9, 2) NULL
)

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%DeptsNew%') > 0
EXEC('DROP TABLE DeptsNew')
GO
CREATE TABLE [dbo].[DeptsNew](
	[id] [int] NULL,
	[deptno] [smallint] NULL,
	[name] [varchar](14) NULL,
	[loc] [varchar](13) NULL
)

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%EmpsNew%') > 0
EXEC('DROP TABLE EmpsNew')
GO
CREATE TABLE [dbo].[EmpsNew](
	[id] [int] NULL,
	[empno] [int] NULL,
	[name] [varchar](10) NULL,
	[title] [varchar](9) NULL,
	[hiredate] [smalldatetime] NULL,
	[sal] [decimal](7, 2) NULL,
	[comm] [decimal](7, 2) NULL,
	[DeptsNew_id] [int] NULL
)

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%Managers%') > 0
EXEC('DROP TABLE Managers')
GO
CREATE TABLE [dbo].[Managers](
	[id] [int] NOT NULL,
	[name] [varchar](10) NULL,
	[salary] [decimal](7, 2) NULL,
	[hiredate] [smalldatetime] NULL
)

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%Table1%') > 0
EXEC('DROP TABLE Table1')
GO
CREATE TABLE [dbo].[Table1](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[col1] [int] NULL
)

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%Table2%') > 0
EXEC('DROP TABLE Table2')
GO
CREATE TABLE [dbo].[Table2](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[col1] [int] NULL
)

INSERT INTO Table1 (col1)
VALUES (1)
INSERT INTO Table1 (col1)
VALUES (5)
INSERT INTO Table1 (col1)
VALUES (5)

INSERT INTO Table2 (col1)
VALUES (2)
INSERT INTO Table2 (col1)
VALUES (5)
INSERT INTO Table2 (col1)
VALUES (5)





------------------------------------------------------------------
------------------------------------------------------------------
--                                                              --
-- Views                                                        --
--                                                              --
------------------------------------------------------------------
------------------------------------------------------------------

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%descr%') > 0
EXEC('DROP PROCEDURE descr')
GO

CREATE PROCEDURE descr
		@p_viewName       	VARCHAR(25)
AS 
DECLARE	@v_viewDefinition	VARCHAR(MAX),
		@v_firstChar      	INT
BEGIN
	SET NOCOUNT ON

	SELECT  @v_viewDefinition = [text]
	FROM    syscomments 
	WHERE   OBJECTPROPERTY(id, 'IsMsShipped') = 0
	AND             OBJECTPROPERTY(id, 'IsView') = 1 
	AND             [text] LIKE '%' + @p_viewName + '%'

	SET @v_viewDefinition = REPLACE(REPLACE(REPLACE(@v_viewDefinition, 'create view ', ''),@p_viewName,''),'as','')

	SET @v_firstChar = ASCII(SUBSTRING(@v_viewDefinition,1,1))
	WHILE (@v_firstChar = 10 OR @v_firstChar = 13)
	BEGIN
		SET @v_viewDefinition = SUBSTRING(@v_viewDefinition,2,LEN(@v_viewDefinition))
		SET @v_firstChar = ASCII(SUBSTRING(@v_viewDefinition,1,1))
	END     

	PRINT @v_viewDefinition
END
GO


IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v101%') > 0
EXEC('DROP VIEW v101')
GO
CREATE VIEW v101
AS
SELECT 	*  
FROM  	Depts
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v102%') > 0
EXEC('DROP VIEW v102')
GO
CREATE VIEW v102
AS
SELECT 	deptno, name, loc 
FROM  	Depts
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v103%') > 0
EXEC('DROP VIEW v103')
GO
CREATE VIEW v103
AS
SELECT	deptno, loc
FROM	Depts
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v106%') > 0
EXEC('DROP VIEW v106')
GO
CREATE VIEW v106
AS
SELECT	Depts_id
FROM	Emps
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v107%') > 0
EXEC('DROP VIEW v107')
GO
CREATE VIEW v107
AS
SELECT	DISTINCT Depts_id
FROM	Emps
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v108%') > 0
EXEC('DROP VIEW v108')
GO
CREATE VIEW v108
AS
SELECT	Emps.name AS eName,
		Depts.name AS dName
FROM	Emps,
		Depts
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v109%') > 0
EXEC('DROP VIEW v109')
GO
CREATE VIEW v109
AS
SELECT 	*  
FROM  	Staff  
WHERE  	salary > 10000
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v150%') > 0
EXEC('DROP VIEW v150')
GO
CREATE VIEW v150
AS
SELECT	Client.clientNo AS cClientNo, Client.fName, Client.lName,  	    
		Viewing.clientNo AS vClientNo, Viewing.propertyNo, Viewing.comment  
FROM	Client,  
		Viewing  
WHERE	Client.clientNo = Viewing.clientNo 
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v151%') > 0
EXEC('DROP VIEW v151')
GO
CREATE VIEW v151
AS
SELECT	name,	          	
		title,	                         		  	
		sal,		
		comm
FROM	Emps
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v152%') > 0
EXEC('DROP VIEW v152')
GO
CREATE VIEW v152
AS
SELECT	name,
		12*sal + comm AS salComm
FROM	Emps
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v160%') > 0
EXEC('DROP VIEW v160')
GO
CREATE VIEW v160
AS
SELECT	a.name, 
  		a.sal, 
  		a.Depts_id, 
  		b.salavg 	   
FROM	Emps a, 
  		( 
  		SELECT		Depts_id, 
  	  				AVG(sal) salavg 
  		FROM		Emps 
  		GROUP BY	Depts_id 
  		) b 	   
WHERE	a.Depts_id = b.Depts_id 
AND		a.sal > b.salavg
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v170%') > 0
EXEC('DROP VIEW v170')
GO
CREATE VIEW v170
AS
SELECT	name, Depts_id, sal, comm 
FROM	Emps 
WHERE	sal IN 
		( 
		SELECT	sal 
		FROM	Emps 
		WHERE	Depts_id = 3 
   		) 
AND		ISNULL(comm,-1) IN 
  		( 
  	  	SELECT	ISNULL(comm,-1) 
  	  	FROM	Emps 
  	   	WHERE	Depts_id = 3 
  	  	) 
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v180%') > 0
EXEC('DROP VIEW v180')
GO
CREATE VIEW v180
AS
SELECT	e.name 
FROM	Emps e 
WHERE	e.id NOT IN 
  		( 
  		SELECT 	 mgr.Emps_id 
  		FROM 	 Emps mgr 
  		)
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v190%') > 0
EXEC('DROP VIEW v190')
GO
CREATE VIEW v190
AS
SELECT	e.name 
FROM	Emps e 
WHERE	e.id NOT IN 
  		( 
  		SELECT	mgr.Emps_id 
  		FROM	Emps mgr 
  		)
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v200%') > 0
EXEC('DROP VIEW v200')
GO
CREATE VIEW v200
AS
SELECT	Client.clientNo AS cClientNo, Client.fName, Client.lName,  
		Viewing.clientNo AS vClientNo, Viewing.propertyNo, Viewing.comment  
FROM	Client,  
		Viewing  
WHERE	Client.clientNo = Viewing.clientNo  
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v202%') > 0
EXEC('DROP VIEW v202')
GO
CREATE VIEW v202
AS
SELECT	name AS Employee,  
		sal "Annual Salary",  
		Depts_id Department,  
		hiredate [Einstellungsdatum]  
FROM	Emps
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v204%') > 0
EXEC('DROP VIEW v204')
GO
CREATE VIEW v204
AS
SELECT	Client.clientNo AS cClientNo, Client.fName, Client.lName,  
		Viewing.clientNo AS vClientNo, Viewing.propertyNo, Viewing.comment  
FROM	Client,  
		Viewing
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v205%') > 0
EXEC('DROP VIEW v205')
GO
CREATE VIEW v205
AS
SELECT	Client.clientNo AS cClientNo, Client.fName, Client.lName,  	    
		Viewing.clientNo AS vClientNo, Viewing.propertyNo, Viewing.comment  
FROM	Client, Viewing  
WHERE	Client.clientNo = Viewing.clientNo 
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v210%') > 0
EXEC('DROP VIEW v210')
GO
CREATE VIEW v210
AS
SELECT	Table1.* 
FROM	Table1, Table2 
WHERE	Table1.col1 = Table2.col1 
AND		Table2.col1 = 5 
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v211%') > 0
EXEC('DROP VIEW v211')
GO
CREATE VIEW v211
AS 
SELECT	* 
FROM	Table1 
WHERE	Table1.col1 IN  
  		( 
  		SELECT	Table2.col1 
  		FROM	Table2 
  		WHERE	Table2.col1 = 5 
  		)
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v213%') > 0
EXEC('DROP VIEW v213')
GO
CREATE VIEW v213
AS
SELECT	city AS stadt  
FROM	Branch  
UNION  
SELECT	city  
FROM	PropertyForRent  
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v220%') > 0
EXEC('DROP VIEW v220')
GO
CREATE VIEW v220
AS
SELECT	name 
FROM	Emps 
WHERE	sal > 
  		( 
  		SELECT	sal 
  		FROM	Emps 
  		WHERE	empno = 7566 	    -- Jones 
  		) 
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v221%') > 0
EXEC('DROP VIEW v221')
GO
CREATE VIEW v221
AS
/*
SELECT	empno, 
  		name 
FROM	Emps 
WHERE	empno > 
  		( 
  		SELECT	empno 
  		FROM	Emps 
  		WHERE	Depts_id = 1 
  		) 
AND		Depts_id != 1
*/
SELECT 'Fehler' AS Ausgabe
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v298%') > 0
EXEC('DROP VIEW v298')
GO
CREATE VIEW v298
AS
SELECT		city  
FROM  		Branch  
INTERSECT  
SELECT		city  
FROM  		PropertyForRent  
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v301%') > 0
EXEC('DROP VIEW v301')
GO
CREATE VIEW v301
AS
SELECT	name,  
		title,  
		Depts_id  
FROM	Emps  
WHERE	Depts_id = 1
GO
  
IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v305%') > 0
EXEC('DROP VIEW v305')
GO
CREATE VIEW v305
AS
SELECT	id, empno, name, CONVERT(VARCHAR,hiredate,104) AS hiredate  
FROM	Emps  
UNION  
SELECT	id, deptno, name, loc -- oder *  
FROM	Depts  
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v306%') > 0
EXEC('DROP VIEW v306')
GO
CREATE VIEW v306
AS
SELECT	id, empno, name, hiredate  
FROM	Emps  
UNION  
SELECT	*  
FROM	Depts
GO


IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v307%') > 0
EXEC('DROP VIEW v307')
GO
CREATE VIEW v307
AS
/*
SELECT	*  
FROM	Emps  
UNION  
SELECT	*  
FROM	Depts
*/
SELECT 'Fehler' AS Ausgabe
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v310%') > 0
EXEC('DROP VIEW v310')
GO
CREATE VIEW v310
AS
SELECT		e.name AS eName,  
			d.deptno,  
			d.name AS dName  
FROM  		Emps e
RIGHT JOIN	Depts d  
ON  		e.Depts_id = d.id  
--ORDER BY	e.Depts_id
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v320%') > 0
EXEC('DROP VIEW v320')
GO
CREATE VIEW v320
AS
SELECT	name, 
  		title 
FROM	Emps 
WHERE	title = 
  		( 
  		SELECT 	 title 
  		FROM 	 Emps 
  		WHERE 	 empno = 7369 
  		) 
AND		sal > 
  		( 
  		SELECT 	 sal 
  		FROM 	 Emps 
  		WHERE 	 empno = 7876 
  		) 
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v325%') > 0
EXEC('DROP VIEW v325')
GO
CREATE VIEW v325
AS
SELECT	empno, 
  		name,
  		sal
FROM	Emps 
WHERE	sal = 
  		( 
  		SELECT 		MIN(sal)
  		FROM 		Emps 
  		GROUP BY	Depts_id 
  		) 
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v326%') > 0
EXEC('DROP VIEW v326')
GO
CREATE VIEW v326
AS
SELECT	empno, 
  		name,
  		sal
FROM	Emps 
WHERE	sal IN 
  		( 
  		SELECT 		MIN(sal)
  		FROM 		Emps 
  		GROUP BY	Depts_id 
  		) 
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v330%') > 0
EXEC('DROP VIEW v330')
GO
CREATE VIEW v330
AS
SELECT	name,  	              	    
		title,  	    	    	    
		Depts_id  	    	    	    
FROM	Emps  	    	  
WHERE	name = 'James'  	    	  
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v331%') > 0
EXEC('DROP VIEW v331')
GO
CREATE VIEW v331
AS
SELECT	name,
		empno,
		title
FROM	Emps 
WHERE	title = 'Clerk'
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v340%') > 0
EXEC('DROP VIEW v340')
GO
CREATE VIEW v340
AS
SELECT		Depts_id, 
  			MAX(sal) AS salMax
FROM 		Emps 
GROUP BY	Depts_id 
HAVING 		MAX(sal) > 2900
GO
		
IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v341%') > 0
EXEC('DROP VIEW v341')
GO
CREATE VIEW v341
AS      	
SELECT		title, 
  			SUM(sal) Payroll 
FROM 		Emps 
WHERE 		title NOT LIKE 'Sales%' 
GROUP BY	title 
HAVING 		SUM(sal) > 5000 
--ORDER BY	SUM(sal) 
GO
 
IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v352%') > 0
EXEC('DROP VIEW v352')
GO
CREATE VIEW v352
AS
SELECT	Staff.*  
FROM	Staff,  
		Branch  
WHERE	Staff.branchNo = Branch.branchNo  
AND		Branch.city = 'Glasgow'
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v360%') > 0
EXEC('DROP VIEW v360')
GO
CREATE VIEW v360
AS
SELECT	worker.name + ' works for ' + manager.name AS Info
FROM	Emps worker,  
		Emps manager  
WHERE	worker.Emps_id = manager.id
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v370%') > 0
EXEC('DROP VIEW v370')
GO
CREATE VIEW v370
AS
SELECT	AVG(sal) AS Avg_SAL, MAX(sal) AS Max_SAL, MIN(sal) AS Min_SAL, SUM(sal) AS Sum_SAL 
FROM	Emps 
WHERE	title LIKE 'Sales%'
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v375%') > 0
EXEC('DROP VIEW v375')
GO
CREATE VIEW v375
AS
SELECT	name, 
  		title 
FROM	Emps 
WHERE	title = 
  		( 
  		SELECT 	 title 
  		FROM 	 Emps 
  		WHERE 	 name = 'Smythe' 
  		)
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v376%') > 0
EXEC('DROP VIEW v376')
GO
CREATE VIEW v376
AS
SELECT	MIN(hiredate) AS hMin, MAX(hiredate) AS hMax
FROM	Emps
GO 

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v380%') > 0
EXEC('DROP VIEW v380')
GO
CREATE VIEW v380
AS
SELECT 	 name, title, sal 
FROM 	 Emps 
WHERE 	 sal = (SELECT MIN(sal) FROM Emps) 
GO 

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v381%') > 0
EXEC('DROP VIEW v381')
GO
CREATE VIEW v381
AS     	
SELECT 		Depts_id, MIN(sal) AS Min_SAL 
FROM 		Emps 
GROUP BY	Depts_id 
HAVING 		MIN(sal) > 
  			(SELECT MIN(sal) FROM Emps WHERE Depts_id = 2)
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v390%') > 0
EXEC('DROP VIEW v390')
GO
CREATE VIEW v390
AS     	
SELECT 	 AVG(comm) AS commAvg
FROM 	 Emps
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v391%') > 0
EXEC('DROP VIEW v391')
GO
CREATE VIEW v391
AS     	
SELECT	AVG(ISNULL(comm,0)) AS commAvg
FROM	Emps
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v401%') > 0
EXEC('DROP VIEW v401')
GO
CREATE VIEW v401
AS
SELECT	city  
FROM	Branch  
EXCEPT  
SELECT	city  
FROM	PropertyForRent  
GO
 
IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v402%') > 0
EXEC('DROP VIEW v402')
GO
CREATE VIEW v402
AS
SELECT	staffNo, fName, lName, salary  
FROM	Staff  
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v410%') > 0
EXEC('DROP VIEW v410')
GO
CREATE VIEW v410
AS
SELECT	name,  	         	    
		sal,  	    	    	    
		comm  	    	    
FROM	Emps  	    	    
WHERE	sal <= comm  	    
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v411%') > 0
EXEC('DROP VIEW v411')
GO
CREATE VIEW v411
AS 
SELECT	name,
		sal
FROM	Emps
WHERE	sal BETWEEN 1000 AND 1500
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v412%') > 0
EXEC('DROP VIEW v412')
GO
CREATE VIEW v412
AS
SELECT	empno,  
		name,  
		sal,  
		Emps_id  
FROM	Emps  
WHERE	Emps_id IN (3, 4)
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v430%') > 0
EXEC('DROP VIEW v430')
GO
CREATE VIEW v430
AS
SELECT		Depts_id, 
  			title, 
  			SUM(sal) AS Sum_SAL 
FROM 		Emps 
GROUP BY 	Depts_id,
  			title
GO  	 

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v450%') > 0
EXEC('DROP VIEW v450')
GO
CREATE VIEW v450
AS
SELECT 	Emps.empno, 
  		Emps.name, 
  		Emps.Depts_id, 
  		Depts.deptno, 
  		Depts.loc 
FROM	Emps, 
  		Depts 
WHERE	Emps.Depts_id = Depts.id
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v451%') > 0
EXEC('DROP VIEW v451')
GO
CREATE VIEW v451
AS
SELECT	Emps.empno, 
  		Emps.name, 
  		Emps.Depts_id, 
  		Depts.deptno, 
  		Depts.loc 
FROM	Emps INNER JOIN Depts 
ON 		Emps.Depts_id = Depts.id 
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v452%') > 0
EXEC('DROP VIEW v452')
GO
CREATE VIEW v452
AS
SELECT	Emps.empno, 
  		Emps.name, 
  		Emps.Depts_id, 
  		Depts.loc 
FROM	Emps, 
  		Depts 
WHERE	Emps.Depts_id = Depts.id 
AND		Emps.name = 'King' 
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v453%') > 0
EXEC('DROP VIEW v453')
GO
CREATE VIEW v453
AS  	 
SELECT	e.empno, 
  		e.name, 
  		e.Depts_id, 
  		d.deptno, 
  		d.loc 
FROM	Emps 	 e, 	  
  		Depts 	 d 	  
WHERE	e.Depts_id = d.id
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v460%') > 0
EXEC('DROP VIEW v460')
GO
CREATE VIEW v460
AS
SELECT	e.name,  
		e.sal,  
		s.grade  
FROM	Emps e,  
		Sals s  
WHERE	e.sal BETWEEN s.lowsal AND s.highsal
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v470%') > 0
EXEC('DROP VIEW v470')
GO
CREATE VIEW v470
AS
SELECT	c.name,  
		o.id AS oId,  
		o.total,  
		i.id AS iId,
		i.itemtot  
FROM	Custs c,  
		Ords o,  
		Items i  
WHERE	c.id = o.Custs_id  
AND		o.id = i.Ords_id  
AND		c.name = 'TKB Sport Shop'
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v500%') > 0
EXEC('DROP VIEW v500')
GO
CREATE VIEW v500
AS
SELECT	PropertyForRent.propertyNo, PropertyForRent.street, PropertyForRent.city,  
		Viewing.clientNo, Viewing.viewDate, Viewing.comment  
FROM	PropertyForRent  
		LEFT OUTER JOIN Viewing ON PropertyForRent.propertyNo = Viewing.propertyNo  
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v510%') > 0
EXEC('DROP VIEW v510')
GO
CREATE VIEW v510
AS
SELECT	COUNT(*) AS c
FROM	Emps  
WHERE	Depts_id = 3
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v511%') > 0
EXEC('DROP VIEW v511')
GO
CREATE VIEW v511
AS
SELECT 	 COUNT(DISTINCT Emps_id) AS c
FROM 	 Emps 
GO


IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v600%') > 0
EXEC('DROP VIEW v600')
GO
CREATE VIEW v600
AS
SELECT	name  
FROM	Emps  
WHERE	name LIKE '_a%'
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v601%') > 0
EXEC('DROP VIEW v601')
GO
CREATE VIEW v601
AS
SELECT	name,  
		Emps_id  
FROM	Emps  
WHERE	Emps_id IS NULL
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v621%') > 0
EXEC('DROP VIEW v621')
GO
CREATE VIEW v621
AS
SELECT	name,  	     	    
		title,  	    	    	    
		sal  	    	    	    
FROM	Emps  	    	    
WHERE	sal >= 1100  	    	    
AND		title = 'Clerk'
GO  	    	    

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v622%') > 0
EXEC('DROP VIEW v622')
GO
CREATE VIEW v622
AS
SELECT	name, 
		title,
		sal
FROM	Emps
WHERE	sal >= 1100
OR		title = 'Clerk'
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v623%') > 0
EXEC('DROP VIEW v623')
GO
CREATE VIEW v623
AS   
SELECT	name,  
		title  
FROM	Emps  
WHERE	title NOT IN ('Clerk', 'Manager', 'Analyst') 
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v650%') > 0
EXEC('DROP VIEW v650')
GO
CREATE VIEW v650
AS
SELECT		name,  	              	    
			title,  	    	    	    
			Depts_id,  	    	    	    
			hiredate  	    	    	    
FROM		Emps  	    	    
-- ORDER BY	hiredate  	    	    
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v651%') > 0
EXEC('DROP VIEW v651')
GO
CREATE VIEW v651
AS
SELECT		name,
			title,
			Depts_id,
			hiredate
FROM		Emps
-- ORDER BY	hiredate DESC
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v652%') > 0
EXEC('DROP VIEW v652')
GO
CREATE VIEW v652
AS
SELECT		name,  
    		sal*12 Annsal  
FROM  		Emps  
-- ORDER BY	Annsal
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v660%') > 0
EXEC('DROP VIEW v660')
GO
CREATE VIEW v660
AS
SELECT		Depts_id, 
  			AVG(sal) AS salAvg 
FROM		Emps 
GROUP BY	Depts_id 
-- ORDER BY	AVG(sal)
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v670%') > 0
EXEC('DROP VIEW v670')
GO
CREATE VIEW v670
AS
SELECT		Depts_id, 
  			title, 
  			SUM(sal) AS salSum 
FROM 		Emps 
GROUP BY	Depts_id, 
  			title
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v671%') > 0
EXEC('DROP VIEW v671')
GO
CREATE VIEW v671
AS
SELECT 		Depts_id, 
  			COUNT(name) AS nameCount 
FROM 		Emps 
GROUP BY	Depts_id
GO 

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v680%') > 0
EXEC('DROP VIEW v680')
GO
CREATE VIEW v680
AS
SELECT	empno, name, sal 
FROM	Emps 
WHERE	sal != 
  		( 
  		SELECT 		AVG(sal) AS avgSal
  		FROM 		Emps
  		GROUP BY	Depts_id
  		) 
GO
  	 
IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v681%') > 0
EXEC('DROP VIEW v681')
GO
CREATE VIEW v681
AS
SELECT	id, empno, name, sal 
FROM	Emps 
WHERE	sal = ANY 
  		( 
  		SELECT	AVG(sal) 
  		FROM	Emps 
  		)
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v682%') > 0
EXEC('DROP VIEW v682')
GO
CREATE VIEW v682
AS
SELECT	id, empno, name, sal 
FROM	Emps 
WHERE	sal = ANY 
  		( 
  		SELECT	sal 
  		FROM	Emps 
  		)
GO	

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v701%') > 0
EXEC('DROP VIEW v701')
GO
CREATE VIEW v701
AS
SELECT	city  
FROM	Branch  
UNION  
SELECT	city  
FROM	PropertyForRent  
GO

IF (SELECT COUNT(*) FROM sysobjects WHERE name LIKE '%v702%') > 0
EXEC('DROP VIEW v702')
GO
CREATE VIEW v702
AS
SELECT		name,  
			Depts_id,  
    		sal  
FROM  		Emps  
--ORDER BY	Depts_id, sal DESC
GO



/*
IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'Komponenten') > 0 DROP TABLE Komponenten;
CREATE TABLE Komponenten
(  
	id  					INT IDENTITY(1,1)	NOT NULL	PRIMARY KEY CLUSTERED,  
	bez  					VARCHAR(250)		NOT NULL	UNIQUE  
)  

IF (SELECT COUNT(*) FROM sysobjects WHERE name = 'Fertigungsregeln') > 0 DROP TABLE Fertigungsregeln;
CREATE TABLE Fertigungsregeln
(
	id  					INT IDENTITY(1,1)	NOT NULL	PRIMARY KEY CLUSTERED,  
	menge  					INT					NULL,  
	Komponenten_id_oben		INT					NULL		FOREIGN KEY REFERENCES Komponenten(id),  
	Komponenten_id_unten	INT					NULL		FOREIGN KEY REFERENCES Komponenten(id)  
)

INSERT INTO Komponenten (bez)
SELECT	'Auto'
UNION	SELECT	'Karosserie'
UNION	SELECT	'Fahrgestell'
UNION	SELECT	'Kotflügel'
UNION	SELECT	'Autotür'  
UNION	SELECT	'Türgriff'
UNION	SELECT	'Schloss'
UNION	SELECT	'Heckklappe'
UNION	SELECT	'Autorad'
UNION	SELECT	'Autoreifen'
UNION	SELECT	'Felge'  

INSERT INTO Fertigungsregeln (menge, Komponenten_id_oben, Komponenten_id_unten)  
SELECT 1, o.id, u.id FROM Komponenten o, Komponenten u WHERE o.bez = 'Auto' AND u.bez = 'Karosserie'  
INSERT INTO Fertigungsregeln (menge, Komponenten_id_oben, Komponenten_id_unten)  
SELECT 1, o.id, u.id FROM Komponenten o, Komponenten u WHERE o.bez = 'Karosserie' AND u.bez = 'Fahrgestell'  
INSERT INTO Fertigungsregeln (menge, Komponenten_id_oben, Komponenten_id_unten)  
SELECT 2, o.id, u.id FROM Komponenten o, Komponenten u WHERE o.bez = 'Karosserie' AND u.bez = 'Kotflügel'  
INSERT INTO Fertigungsregeln (menge, Komponenten_id_oben, Komponenten_id_unten)  
SELECT 2, o.id, u.id FROM Komponenten o, Komponenten u WHERE o.bez = 'Karosserie' AND u.bez = 'Autotür'  
INSERT INTO Fertigungsregeln (menge, Komponenten_id_oben, Komponenten_id_unten)  
SELECT 1, o.id, u.id FROM Komponenten o, Komponenten u WHERE o.bez = 'Autotür' AND u.bez = 'Türgriff'  
INSERT INTO Fertigungsregeln (menge, Komponenten_id_oben, Komponenten_id_unten)  
SELECT 1, o.id, u.id FROM Komponenten o, Komponenten u WHERE o.bez = 'Autotür' AND u.bez = 'Schloss'  
INSERT INTO Fertigungsregeln (menge, Komponenten_id_oben, Komponenten_id_unten)  
SELECT 1, o.id, u.id FROM Komponenten o, Komponenten u WHERE o.bez = 'Karosserie' AND u.bez = 'Heckklappe'  
INSERT INTO Fertigungsregeln (menge, Komponenten_id_oben, Komponenten_id_unten)  
SELECT 1, o.id, u.id FROM Komponenten o, Komponenten u WHERE o.bez = 'Heckklappe' AND u.bez = 'Schloss'  
INSERT INTO Fertigungsregeln (menge, Komponenten_id_oben, Komponenten_id_unten)  
SELECT 5, o.id, u.id FROM Komponenten o, Komponenten u WHERE o.bez = 'Auto' AND u.bez = 'Autorad'  
INSERT INTO Fertigungsregeln (menge, Komponenten_id_oben, Komponenten_id_unten)  
SELECT 1, o.id, u.id FROM Komponenten o, Komponenten u WHERE o.bez = 'Autorad' AND u.bez = 'Autoreifen'  
INSERT INTO Fertigungsregeln (menge, Komponenten_id_oben, Komponenten_id_unten)  
SELECT 1, o.id, u.id FROM Komponenten o, Komponenten u WHERE o.bez = 'Autorad' AND u.bez = 'Felge'  
*/

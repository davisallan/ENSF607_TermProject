DROP DATABASE IF EXISTS toolshop;
CREATE DATABASE toolshop; 
USE toolshop;

DROP TABLE IF EXISTS supplier;
CREATE TABLE supplier (
supplierId	integer not null,
name		varchar(25),
type		varchar(15),
address		varchar(50),
cName		varchar(25),
phone		varchar(12),
primary key (supplierId)
);

DROP TABLE IF EXISTS tool;
CREATE TABLE tool (
toolId		integer not null,
name		varchar(25),
type		varchar(15), 
quantity	integer, 
price		float, 
supplierId	integer,
primary key (toolId),
foreign key (supplierId) references supplier(supplierId)
);

DROP TABLE IF EXISTS electrical;
CREATE TABLE electrical (
toolId		integer,
powerType	varchar(5), 
primary key (toolId),
foreign key (toolId) references tool(toolId)
);

DROP TABLE IF EXISTS international;
CREATE TABLE international (
supplierId		integer,
importTax		float,
primary key (supplierId),
foreign key (supplierId) references supplier(supplierId)
);

DROP TABLE IF EXISTS toolOrder;
CREATE TABLE toolOrder (
orderId		integer not null,
orderDate	date, 
primary key (orderId)
);

DROP TABLE IF EXISTS orderLine;
CREATE TABLE orderLine (
orderId		integer, 
toolId		integer, 
supplierId	integer,
quantity	integer, 
primary key (orderId,toolId),
foreign key (toolId) references tool(toolId),
foreign key (supplierId) references supplier(supplierId),
foreign key (orderId) references toolOrder(orderId)
);

DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
customerId	integer not null,
lName		varchar(20),
fName		varchar(20),
cType		char,
phoneNum	varchar(12),
address		varchar(50),
postalCode	varchar(7),
primary key (customerId)
);

DROP TABLE IF EXISTS purchases;
CREATE TABLE purchases (
customerId	integer, 
toolId		integer,
pDate		date,
primary key (customerId,toolId,pDate),
foreign key (customerId) references customer(customerId),
foreign key (toolId) references tool(toolId)
);
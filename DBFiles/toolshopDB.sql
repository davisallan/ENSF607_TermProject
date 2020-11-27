DROP DATABASE IF EXISTS toolshop;
CREATE DATABASE toolshop; 
USE toolshop;

DROP TABLE IF EXISTS supplier;
CREATE TABLE supplier (
supplierId	INTEGER NOT NULL,
sName		VARCHAR(25),
sType		VARCHAR(15),
address		VARCHAR(50),
cName		VARCHAR(25),
phone		VARCHAR(12),
PRIMARY KEY (supplierId)
);

DROP TABLE IF EXISTS international;
CREATE TABLE international (
supplierId		INTEGER,
importTax		FLOAT,
PRIMARY KEY (supplierId),
FOREIGN KEY (supplierId) REFERENCES supplier(supplierId)
	ON DELETE CASCADE		ON UPDATE CASCADE
);

DROP TABLE IF EXISTS tool;
CREATE TABLE tool (
toolId		INTEGER NOT NULL,
tName		VARCHAR(25),
tType		VARCHAR(15), 
quantity	INTEGER CHECK (quantity > 0), 
price		FLOAT, 
supplierId	INTEGER,
sName		VARCHAR(25),
PRIMARY KEY (toolId),
FOREIGN KEY (supplierId) REFERENCES supplier(supplierId)
	ON DELETE SET NULL 		ON UPDATE CASCADE
);

DROP TABLE IF EXISTS electrical;
CREATE TABLE electrical (
toolId		INTEGER,
powerType	VARCHAR(8), 
PRIMARY KEY (toolId),
FOREIGN KEY (toolId) REFERENCES tool(toolId)
	ON DELETE CASCADE		ON UPDATE CASCADE
);


DROP TABLE IF EXISTS toolOrder;
CREATE TABLE toolOrder (
orderId		INTEGER NOT NULL,
orderDate	DATE, 
PRIMARY KEY (orderId) 
);

DROP TABLE IF EXISTS orderLine;
CREATE TABLE orderLine (
orderId		INTEGER, 
toolId		INTEGER, 
supplierId	INTEGER,
quantity	INTEGER CHECK (quantity > 0), 
PRIMARY KEY (orderId, toolId),
FOREIGN KEY (toolId) REFERENCES tool(toolId) 
	ON DELETE CASCADE 		ON UPDATE CASCADE, 
FOREIGN KEY (supplierId) REFERENCES supplier(supplierId) 
	ON DELETE CASCADE		ON UPDATE CASCADE,
FOREIGN KEY (orderId) REFERENCES toolOrder(orderId) 
	ON DELETE RESTRICT		ON UPDATE CASCADE
);

DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
customerId	INTEGER NOT NULL,
lName		VARCHAR(20),
fName		VARCHAR(20),
cType		CHAR,
phoneNum	VARCHAR(12),
address		VARCHAR(50),
postalCode	VARCHAR(7),
PRIMARY KEY (customerId)
);

DROP TABLE IF EXISTS purchases;
CREATE TABLE purchases (
customerId	INTEGER, 
toolId		INTEGER,
pDate		DATE,
PRIMARY KEY (customerId, toolId, pDate),
FOREIGN KEY (customerId) REFERENCES customer(customerId) 
	ON DELETE CASCADE		ON UPDATE CASCADE,
FOREIGN KEY (toolId) REFERENCES tool(toolId) 
	ON DELETE CASCADE		ON UPDATE CASCADE
);
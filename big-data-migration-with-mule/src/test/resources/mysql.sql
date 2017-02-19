CREATE DATABASE IF NOT EXISTS etl;

DROP TABLE IF EXISTS etl.`order_item`;
DROP TABLE IF EXISTS etl.`product`;
DROP TABLE IF EXISTS etl.`order`;

CREATE TABLE etl.`order` (
  `orderID` INT NOT NULL PRIMARY KEY,
  `customerID` INT NOT NULL,
  `orderDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `orderStatus` VARCHAR(50)
) ENGINE=InnoDB;

CREATE TABLE etl.`product` (
  `productID` INT NOT NULL PRIMARY KEY,
  `shortName` VARCHAR(100) NOT NULL,
  `category` VARCHAR(50),
  `size` VARCHAR(20),
  `createDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE etl.`order_item` (
  `orderID` INT NOT NULL,
  `productID` int NOT NULL,
  `itemNumber` INT NOT NULL,
  `quantity` INT,
  PRIMARY KEY (`orderID`, `productID`, `itemNumber`),
  CONSTRAINT `fkItemOrderId` FOREIGN KEY (`orderID`) REFERENCES etl.`order` (`orderID`),
  CONSTRAINT `fkItemProductId` FOREIGN KEY (`productID`) REFERENCES etl.`product` (`productID`)
) ENGINE=InnoDB;

-- insert some test products
INSERT INTO etl.product(productID, shortName, size, category) VALUES (5006501, 'Lockets',	'10 lozenges',	'Throat and Lozenges');
INSERT INTO etl.product(productID, shortName, size, category) VALUES (5006502, 'Optrex Refreshing Eye Drops',	'8 ml',	'Eye Clearers');
INSERT INTO etl.product(productID, shortName, size, category) VALUES (5006503, 'Karvol Vaporiser Refill Pack',	'3 Refills',	'Easy Breathing');
INSERT INTO etl.product(productID, shortName, size, category) VALUES (5006504, 'Optrex Eyedew Sparkling',	'10ml',	'Eye Clearers');
INSERT INTO etl.product(productID, shortName, size, category) VALUES (5006505, 'Optrex Infected Eyes Eye Ointment',	'4g',	'Eye Infections');
INSERT INTO etl.product(productID, shortName, size, category) VALUES (5006506, 'Sure for Women Anti-perspirant Roll-on',	'50 ml',	'Roll On');
INSERT INTO etl.product(productID, shortName, size, category) VALUES (5006507, 'Nicotinell Mint',	'12 pieces',	'Oral');
INSERT INTO etl.product(productID, shortName, size, category) VALUES (5006508, 'Nicotinell 2 mg Nicotine Gum (Fruit)',	'12 pieces',	'Oral');
INSERT INTO etl.product(productID, shortName, size, category) VALUES (5006509, 'Clikpak Nelsons Belladonna',	'1 pack',	'Homeopathic Remedies');
INSERT INTO etl.product(productID, shortName, size, category) VALUES (5006510, 'Gaviscon Tablets', '20 Tablets',	'Heartburn');

-- couple of orders to play with
INSERT INTO etl.order(orderID, customerID, orderStatus) VALUES (101, 786, 'New Order'); 
INSERT INTO etl.order(orderID, customerID, orderStatus) VALUES (102, 720, 'New Order'); 
INSERT INTO etl.order(orderID, customerID, orderStatus) VALUES (103, 786, 'New Order'); 
INSERT INTO etl.order(orderID, customerID, orderStatus) VALUES (104, 628, 'New Order'); 

-- and finally the order details for the above orders
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (101, 5006502, 1, 1); 
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (101, 5006504, 2, 1); 
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (101, 5006505, 3, 1); 
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (101, 5006509, 4, 2); 
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (102, 5006501, 1, 1); 
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (103, 5006507, 1, 1);
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (103, 5006501, 2, 1);
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (103, 5006504, 3, 1);
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (103, 5006505, 4, 2);
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (103, 5006509, 5, 1);
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (103, 5006510, 6, 3);
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (104, 5006501, 1, 1);
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (104, 5006502, 2, 1);
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (104, 5006503, 3, 1);
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (104, 5006504, 4, 5);
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (104, 5006505, 5, 1);
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (104, 5006508, 6, 1);
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (104, 5006509, 7, 2);
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (104, 5006507, 8, 1);
INSERT INTO etl.order_item(orderID, productID, itemNumber, quantity) VALUES (104, 5006504, 9, 1);

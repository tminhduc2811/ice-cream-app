/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for icecream
CREATE DATABASE IF NOT EXISTS `icecream` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `icecream`;
-- Dumping structure for table icecream.admin
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id`			bigint(20) 		NOT NULL AUTO_INCREMENT,
	`user_name` 	varchar(100) 	NOT NULL,
	`password` 		varchar(1024) 	NOT NULL,
	`fullname`		varchar(100) 	NOT NULL,
	`status`		tinyint(1) 		NOT NULL DEFAULT 1,
	`avatar`		varchar(100) 	DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- Dumping data for table icecream.user: ~1 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_name`, `password`, `fullname`, `status`, `avatar`) VALUES
	('admin', '$2a$12$W46dM0Hi73mgICg3L1kzXO4Vu5i8AYAfb1xEJ7qh5ifsvYkb6.AaG', 'Duc Ta Minh', 1, '/images/ducta.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
	`user_id`		bigint(20) 		NOT NULL,	
	`role_id`		bigint(20) 		NOT NULL,
    PRIMARY KEY	(`user_id`, `role_id`),
    
    KEY `FK_USER_idx` (`user_id`),
	CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) 
	REFERENCES `user` (`id`) 
	ON DELETE NO ACTION ON UPDATE NO ACTION,
  
	CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
	REFERENCES `role` (`id`) 
	ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table icecream.user_role: ~2 rows (approximately)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, role_id) VALUES
	(1, 1),
    (1, 2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;


DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
	`id`			bigint(20) 		NOT NULL AUTO_INCREMENT,
    `role`			varchar(100) 	NOT NULL,
    PRIMARY KEY	(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- Dumping data for table icecream.role: ~2 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`role`) VALUES
	('ROLE_ADMIN'),
    ('ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id`				bigint(20) 		NOT NULL AUTO_INCREMENT,
  `user_name` 		varchar(100) 	NOT NULL,
  `password` 		varchar(1024) 	NOT NULL,
  `first_name` 		varchar(100) 	NOT NULL,
  `last_name`		varchar(100) 	NOT NULL,
  `address` 		varchar(100) 	DEFAULT NULL,
  `phone_number` 	varchar(50) 	DEFAULT NULL,
  `email` 			varchar(100) 	DEFAULT NULL,
  `gender` 			tinyint(1) 		DEFAULT NULL,
  `birthday`  		date 			DEFAULT NULL,
  `avatar` 			varchar(100) 	DEFAULT NULL,
  `expired_date` 	date 			NOT NULL,
  `status` 			tinyint(1) 		NOT NULL DEFAULT 1,
  `num_of_login_failed`	tinyint(2)	NOT NULL DEFAULT 0,	 
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
-- Dumping data for table icecream.customer: ~10 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`id`, `user_name`, `password`, `first_name`, `last_name`, `address`, `phone_number`, `email`, `gender`, `birthday`, `avatar`, `expired_date`, `status`, `num_of_login_failed`) VALUES
	(1, 'user1', '8l9Dp3HQloE=', 'Tran', 'Van Minh', '123 Pasteur', '0901234567', 'vanminhtran@icecream.com', 1, '1987-01-01', 'images/uploaded/noavatar.gif', '2012-12-25', 1, 0),
	(2, 'user3', '8l9Dp3HQloE=', 'Nguyen', 'Van Binh', '456 Alexandre de Rhodes', '0911234567', 'name2@icecream.com', 1, '1991-01-01', 'images/uploaded/noavatar.gif', '2012-12-12', 1, 0),
	(3, 'user2', '8l9Dp3HQloE=', 'Duong', 'Nguyen Anh Thu', '789 Yersin', '0921234567', 'name3@icecream.com', 1, '1990-01-01', 'images/uploaded/noavatar.gif', '2012-12-12', 1, 0),
	(4, 'user5', 'Cuwus/jwiqVUDOdgDW8Lgw==', 'Im Yoona', 'hihi', 'Seoul - Korea', '091234567', 'yoona90@gmail.com', 0, '1990-10-05', 'images/uploaded/yoona.gif', '2012-12-12', 1, 0),
	(5, 'lovely', 'Cuwus/jwiqVUDOdgDW8Lgw==', 'NoName', 'Zeroname', 'Seoul - Korea', '0912345678', 'noname@gmail.com', 0, '1990-10-10', 'images/uploaded/noname.gif', '2013-02-11', 1, 0),
	(6, 'ILoveVietNam', 'Cuwus/jwiqVUDOdgDW8Lgw==', 'Name 6', '6 name', 'Ho Chi Minh', '0912345679', 'ilovevn@gmail.com', 1, '1990-10-11', 'images/uploaded/noname.gif', '2013-02-11', 1, 0),
	(7, 'ILoveHoChiMinh', 'Cuwus/jwiqVUDOdgDW8Lgw==', 'Name 7', 'Name 7', 'Ho Chi Minh', '0932345659', 'ilovehochiminh@gmail.com', 1, '1985-10-11', 'images/uploaded/yoona.jpg', '2014-02-11', 1, 0),
	(8, 'ILoveHue', 'Cuwus/jwiqVUDOdgDW8Lgw==', 'Name 8', 'Name 8', 'Ho Chi Minh', '0912345659', 'ilovehue@gmail.com', 1, '1991-10-11', 'images/uploaded/0.jpg', '2014-02-11', 1, 0),
	(9, 'ILoveYou', 'Cuwus/jwiqVUDOdgDW8Lgw==', 'Need Some One', 'to love', 'Ho Chi Minh', '0912345609', 'iloveyou@gmail.com', 1, '1990-10-11', 'images/uploaded/yoona.gif', '2014-02-11', 1, 0),
	(10, 'loveyouloveme', 'Cuwus/jwiqVUDOdgDW8Lgw==', 'Need a love', 'real love', 'Ho Chi Minh', '0913345659', 'needalove@gmail.com', 1, '1999-10-11', 'images/uploaded/yoona.gif.jpg', '2012-02-02', 1, 0);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` 				bigint(20) 		NOT NULL AUTO_INCREMENT,
  `customer_id` 	bigint(20) 		NOT NULL,
  `order_id` 		bigint(20) 		NOT NULL,
  `details`			text			NOT NULL,
  `created_date`	datetime		NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_ORDER` FOREIGN KEY (`order_id`) 
  REFERENCES `recipe_order` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CUSTOMER2` FOREIGN KEY (`customer_id`)
  REFERENCES `customer` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `recipe_order`;
CREATE TABLE `recipe_order` (
  `id` 				bigint(20) 		NOT NULL AUTO_INCREMENT,
  `customer_id`		bigint(20) 		NOT NULL,
  `payment_id`		bigint(20) 		NOT NULL,
  `payment_option`	varchar(100) 	NOT NULL,
  `created_date`	datetime		NOT NULL,
  `delivery_detail`	varchar(1024) 	NOT NULL,
  `notes`			varchar(1024) 	NOT NULL,
  `status`			tinyint(1) 		NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_CUSTOMER` FOREIGN KEY (`customer_id`) 
  REFERENCES `customer`(`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PAYMENT` FOREIGN KEY (`payment_id`) 
  REFERENCES `payment` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
-- Dumping data for table icecream.recipe_order: ~2 rows (approximately)
/*!40000 ALTER TABLE `recipe_order` DISABLE KEYS */;
INSERT INTO `recipe_order` (`id`, `customer_id`, `payment_id`, `payment_option`, `created_date`, `delivery_detail`, `notes`, `status`) VALUES
	(1, 1, 1, 'credit', '2004-05-23T14:25:10.487', 'home', 'note example', 'status ex'),
    (2, 1, 1, 'credit', '2004-05-23T14:25:10.487', 'home', 'note example2', 'status ex');
/*!40000 ALTER TABLE `recipe_order` ENABLE KEYS */;

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id` 				bigint(20) 		NOT NULL AUTO_INCREMENT,
  `card_type`		varchar(100)	NOT NULL,
  `card_number`		varchar(1024)	NOT NULL,
  `cvv`				varchar(1024)	NOT NULL,
  `name`			varchar(50)		NOT NULL,
  `expired_date`	date			NOT NULL,
  `date_of_birth`	date			NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table icecream.recipe_order: ~2 rows (approximately)
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` (`id`, `card_type`, `card_number`, `cvv`, `name`, `expired_date`, `date_of_birth`) VALUES
	(1, 'VISA', '*********1234', '9812', 'TA MINH DUC', '2028-05-23T14', '1997-11-28T14');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;


DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id`				bigint(20)		NOT NULL AUTO_INCREMENT,
  `order_id` 		bigint(20) 		NOT NULL,
  `recipe_id` 		bigint(20) 		NOT NULL,
  `quantity`		int(10)			NOT NULL DEFAULT 0,
  `price`			float 			NOT NULL,
  `notes`			varchar(1024) 	NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_RECIPE` FOREIGN KEY (`recipe_id`) 
  REFERENCES `recipe` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ORDER2` FOREIGN KEY (`order_id`)
  REFERENCES `recipe_order` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `recipe`;
CREATE TABLE `recipe` (
  `id` 				bigint(20) 		NOT NULL AUTO_INCREMENT,
  `user_id` 		bigint(20) 		NOT NULL,
  `icecream_id` 	bigint(20) 		NOT NULL,
  `title`			varchar(1024) 	NOT NULL,
  `description`		varchar(1024) 	NOT NULL,
  `price`			float 			NOT NULL,
  `status` 			tinyint(1) 		NOT NULL DEFAULT 1,
  `view_count`		int				NOT NULL DEFAULT 0,
  `image` 			varchar(100) 	NOT NULL,
  `details`			varchar(1024) 	NOT NULL,
  `uploaded_date`	datetime		NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_USER2` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ICECREAM` FOREIGN KEY (`icecream_id`) 
  REFERENCES `icecream` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `icecream`;
CREATE TABLE `icecream` (
  `id` 				bigint(20) 		NOT NULL AUTO_INCREMENT,
  `name`			varchar(100) 	NOT NULL,
  `description`		varchar(1024) 	NOT NULL,
  UNIQUE KEY `icecream` (`name`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
-- Dumping data for table icecream.icecream: ~1 rows (approximately)
/*!40000 ALTER TABLE `icecream` DISABLE KEYS */;
INSERT INTO `icecream` (`id`, `name`, `description`) VALUES
	(1, 'Fun Chocolate Icecream', 'An icecream made with fresh chocolate can make you happy'),
    (2, 'Vanila Chocolate Icecream', 'An icecream made with both fresh chocolate and vanila. Better taste, better mood'),
    (3, 'Dark Chocolate Icecream', 'Dancing in the dark - Joji');
/*!40000 ALTER TABLE `icecream` ENABLE KEYS */;

DROP TABLE IF EXISTS `faq`;
CREATE TABLE `faq` (
  `id` 				bigint(20) 		NOT NULL AUTO_INCREMENT,
  `question` 		text 			NOT NULL,
  `answer` 			text 			NOT NULL,
  `status` 			tinyint(1) 		NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- Dumping data for table icecream.faq: ~5 rows (approximately)
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT INTO `faq` (`id`, `question`, `answer`) VALUES
	(1, 'Where can I get coupons for Parlor products?', 'Occasionally we offer coupons and samples on our website and Facebook page.'),
	(2, 'Do your products contain allergens or gluten?', 'Some products do contain allergens, including gluten. However, most of our ice creams and frozen dairy desserts are naturally gluten free. We are currently updating our packaging to make it easy for you to identify all of our naturally gluten free variants. It is our policy that when any of the eight most common allergens (milk, eggs, fish, wheat, tree nuts, peanuts, soybeans and crustaceans) occur in any of our products they will be listed inside the ingredient statement in plain language.'),
	(3, 'How Can I Buy Book Online?', 'You Should Login to The Member Page and Leave your Personal infomation there .Our deliver service base on this.'),
	(4, 'Is my daughter safe on Parlor Icecream? ', 'User safety is our most important concern. Always Icecream is using a wide range of methods and processes to ensure user safety and privacy. The site is content is continuously scanned for inappropriate content using both software and manual reviews. Users can also report any concerns or problems they encounter to Always Icecream staff. Furthermore, self-policing mechanisms are in place to encourage proper behavior and etiquette. Lastly, parents receive regular email reports about the activities and learning progress of their daughter. '),
	(5, 'If my recipe is Prized what way i receive my prize money?', 'You can go direct to Parlor Shop or your banking...');
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;

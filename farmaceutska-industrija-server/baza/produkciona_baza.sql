/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - seminarski_projekat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`seminarski_projekat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `seminarski_projekat`;

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `zip_code` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`zip_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `city` */

insert  into `city`(`zip_code`,`name`) values 
(11000,'Beograd'),
(12000,'Pozarevac'),
(18000,'Nis'),
(21000,'Novi Sad'),
(34000,'Kragujevac');

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id_po` bigint(20) NOT NULL,
  `order_number` bigint(20) NOT NULL,
  `quantity` bigint(20) DEFAULT NULL,
  `amount` bigint(20) DEFAULT NULL,
  `substance` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_po`,`order_number`),
  KEY `substance` (`substance`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`id_po`) REFERENCES `purchase_order` (`code`),
  CONSTRAINT `item_ibfk_2` FOREIGN KEY (`substance`) REFERENCES `substance` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `item` */

insert  into `item`(`id_po`,`order_number`,`quantity`,`amount`,`substance`) values 
(1,1,5,50,100),
(2,1,10,200,200),
(2,2,10,300,300),
(3,1,10,400,400),
(3,2,10,500,500),
(123,1,10,100,100),
(11111,1,10,100,100),
(12345,1,50,3500,700),
(121212,1,10,100,100);

/*Table structure for table `medicine` */

DROP TABLE IF EXISTS `medicine`;

CREATE TABLE `medicine` (
  `serial_number` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `dosage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `medicine` */

insert  into `medicine`(`serial_number`,`name`,`dosage`) values 
(1,'Lek 1','Na kasiku 123'),
(2,'Lek 2','Doziranje Leka 2');

/*Table structure for table `purchase_order` */

DROP TABLE IF EXISTS `purchase_order`;

CREATE TABLE `purchase_order` (
  `code` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `total_amount` bigint(20) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  `supplier` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `user` (`user`),
  KEY `supplier` (`supplier`),
  CONSTRAINT `purchase_order_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  CONSTRAINT `purchase_order_ibfk_2` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `purchase_order` */

insert  into `purchase_order`(`code`,`date`,`total_amount`,`user`,`supplier`) values 
(1,'2026-02-26',50,1,1),
(2,'2026-02-26',500,1,2),
(3,'2026-02-26',900,1,2),
(123,'2026-02-27',100,1,2),
(11111,'2026-03-11',1000,1,1),
(12345,'2026-03-06',3500,2,4),
(121212,'2026-03-11',1000,1,1);

/*Table structure for table `substance` */

DROP TABLE IF EXISTS `substance`;

CREATE TABLE `substance` (
  `code` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `quantity` bigint(20) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `substance` */

insert  into `substance`(`code`,`name`,`quantity`,`price`) values 
(100,'Kalcijum',110,10),
(200,'Kalijum',120,20),
(300,'Selen',100,30),
(400,'Magnezijum',100,40),
(500,'Nijacin',100,50),
(600,'Folna kiselina',100,60),
(700,'Biotin',120,70);

/*Table structure for table `substance_medicine` */

DROP TABLE IF EXISTS `substance_medicine`;

CREATE TABLE `substance_medicine` (
  `id_medicine` bigint(20) NOT NULL,
  `id_substance` bigint(20) NOT NULL,
  `quantity_used` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_medicine`,`id_substance`),
  KEY `id_substance` (`id_substance`),
  CONSTRAINT `substance_medicine_ibfk_1` FOREIGN KEY (`id_medicine`) REFERENCES `medicine` (`serial_number`),
  CONSTRAINT `substance_medicine_ibfk_2` FOREIGN KEY (`id_substance`) REFERENCES `substance` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `substance_medicine` */

insert  into `substance_medicine`(`id_medicine`,`id_substance`,`quantity_used`) values 
(1,100,50),
(1,200,30),
(2,100,50),
(2,200,30);

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `city` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `city` (`city`),
  CONSTRAINT `supplier_ibfk_1` FOREIGN KEY (`city`) REFERENCES `city` (`zip_code`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `supplier` */

insert  into `supplier`(`id`,`first_name`,`last_name`,`city`) values 
(1,'Pera','Peric',21000),
(2,'Mika','Mikic',12000),
(4,'Zika','Zikic',18000),
(34,'dsadas','adssada',11000);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`first_name`,`last_name`,`username`,`password`) values 
(1,'Milos','Korda','admin','admin'),
(2,'Marko','Markovic','mare','mare'),
(3,'Andjela','Andjic','andja','andja');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

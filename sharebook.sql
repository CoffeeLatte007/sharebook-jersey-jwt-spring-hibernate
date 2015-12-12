/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.20 : Database - sharebook
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sharebook` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `sharebook`;

/*Table structure for table `annotation` */

DROP TABLE IF EXISTS `annotation`;

CREATE TABLE `annotation` (
  `annotationId` varchar(32) NOT NULL,
  `content` longtext NOT NULL,
  `digest` varchar(100) DEFAULT NULL,
  `digestPhoto` varchar(200) DEFAULT NULL,
  `page_No` int(11) NOT NULL,
  `ubId` varchar(32) NOT NULL,
  PRIMARY KEY (`annotationId`),
  KEY `ubId` (`ubId`),
  CONSTRAINT `annotation_ibfk_1` FOREIGN KEY (`ubId`) REFERENCES `user_book` (`ubId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `annotation` */

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bookId` varchar(32) NOT NULL,
  `author` varchar(20) DEFAULT NULL,
  `bookName` varchar(30) NOT NULL,
  `coverUrl` varchar(255) DEFAULT NULL,
  `iSBN` varchar(20) DEFAULT NULL,
  `publisher` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `book` */

insert  into `book`(`bookId`,`author`,`bookName`,`coverUrl`,`iSBN`,`publisher`) values ('2bd8cda05170925a0151709266af0000',NULL,'java编程思想1',NULL,'232173892173892',NULL),('2bd8d910515d7e4101515d7e4f880000',NULL,'java编程思想',NULL,'23217389217389',NULL),('40289f0d516280510151628107710002',NULL,'javarest实战',NULL,'12345678999','机械工业'),('40289f0d5162dbf7015162dd194a0000',NULL,'javarest实战2',NULL,'12345678998','机械工业');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userID` varchar(32) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `headUrl` varchar(100) DEFAULT NULL,
  `nickName` varchar(32) DEFAULT NULL,
  `password` varchar(30) NOT NULL,
  `regDate` date NOT NULL,
  `telphone` varchar(30) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `user` */

/*Table structure for table `user_book` */

DROP TABLE IF EXISTS `user_book`;

CREATE TABLE `user_book` (
  `ubId` varchar(32) NOT NULL,
  `bookId` varchar(32) NOT NULL,
  `date` date NOT NULL,
  `userId` varchar(32) NOT NULL,
  `status` varchar(10) DEFAULT NULL,
  `comment` varchar(350) DEFAULT NULL,
  `privcy` varchar(10) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`ubId`),
  UNIQUE KEY `userId` (`userId`,`bookId`),
  KEY `bookId` (`bookId`),
  CONSTRAINT `user_book_ibfk_1` FOREIGN KEY (`bookId`) REFERENCES `book` (`bookId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_book_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `user_book` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

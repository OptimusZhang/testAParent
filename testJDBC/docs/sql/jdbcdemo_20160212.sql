-- --------------------------------------------------------
-- ホスト:                          127.0.0.1
-- サーバのバージョン:                    5.6.15 - MySQL Community Server (GPL)
-- サーバー OS:                      Win64
-- HeidiSQL バージョン:               9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for jdbcdemo
DROP DATABASE IF EXISTS `jdbcdemo`;
CREATE DATABASE IF NOT EXISTS `jdbcdemo` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `jdbcdemo`;


-- Dumping structure for テーブル jdbcdemo.product
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `productName` varchar(50) DEFAULT NULL,
  `dirId` bigint(11) DEFAULT NULL,
  `salePrice` decimal(10,2) DEFAULT NULL,
  `supplier` varchar(50) DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `cutoff` double(2,2) DEFAULT NULL,
  `costPrice` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table jdbcdemo.product: ~9 rows (approximately)
DELETE FROM `product`;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `productName`, `dirId`, `salePrice`, `supplier`, `brand`, `cutoff`, `costPrice`) VALUES
	(1, '罗技M90', 3, 90.00, '罗技', '罗技', 0.50, 35.00),
	(2, '罗技M100', 3, 49.00, '罗技', '罗技', 0.90, 39.00),
	(3, '罗技M115', 3, 99.00, '罗技', '罗技', 0.60, 38.00),
	(4, '罗技M125', 3, 80.00, '罗技', '罗技', 0.90, 39.00),
	(5, '罗技星球轨迹', 3, 182.00, '罗技', '罗技', 0.80, 80.00),
	(6, '罗技星球轨迹', 3, 460.68, '罗技', '罗技', 0.87, 290.00),
	(7, 'iphone7S', 5, 7000.00, '苹果公司', 'Apple', 0.90, 2000.00),
	(9, 'iphone5S', 5, 5000.00, '苹果公司', 'Apple', 0.50, 800.00),
	(11, 'iphone8S', 5, 8000.00, '苹果公司', 'Apple', 0.90, 3000.00);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;


-- Dumping structure for テーブル jdbcdemo.t_user
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table jdbcdemo.t_user: ~0 rows (approximately)
DELETE FROM `t_user`;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

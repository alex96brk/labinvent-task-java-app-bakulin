-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 10, 2021 at 11:50 PM
-- Server version: 5.6.41-log
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `invent_lab_test_task_bakulin`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(18);

-- --------------------------------------------------------

--
-- Table structure for table `sensors`
--

CREATE TABLE `sensors` (
  `id` bigint(20) NOT NULL,
  `sensor_id` varchar(255) DEFAULT NULL,
  `sensor_name` varchar(255) NOT NULL,
  `sensor_model` varchar(255) NOT NULL,
  `sensor_type` varchar(255) DEFAULT NULL,
  `sensor_unit` varchar(255) DEFAULT NULL,
  `left_range` decimal(15,2) DEFAULT NULL,
  `right_range` decimal(15,2) DEFAULT NULL,
  `sensor_descr` varchar(255) DEFAULT NULL,
  `sensor_location` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sensors`
--

INSERT INTO `sensors` (`id`, `sensor_id`, `sensor_name`, `sensor_model`, `sensor_type`, `sensor_unit`, `left_range`, `right_range`, `sensor_descr`, `sensor_location`) VALUES
(23, '85519815', 'Sensor1', 'SR-P-b-31', 'Temperature', '°С', '20.10', '31.00', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Eaque similique, maiores cumque dolores voluptatem consequatur labore fugit officia vel magni quis laborum, earum voluptas, sequi expedita natu', 'Minsk'),
(46, '71951874', 'Sensor2', 'SR-P-b-155', 'Pressure', 'bar', '14.40', '155.50', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Eaque similique, maiores cumque dolores voluptatem consequatur labore fugit officia vel magni quis laborum, earum voluptas, sequi expedita natu', 'Vitebsk'),
(48, '13442410', 'Sensor3', 'SR-V-v-25', 'Voltage', 'voltage', '15.20', '25.50', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Eaque similique, maiores cumque dolores voluptatem consequatur labore fugit officia vel magni quis laborum, earum voluptas, sequi expedita natu', 'Minsk'),
(50, '43919057', 'Sensor5', 'SR-H-perc-99', 'Humidity', '%', '45.00', '99.00', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Eaque similique, maiores cumque dolores voluptatem consequatur labore fugit officia vel magni quis laborum, earum voluptas, sequi expedita natu', 'Minsk'),
(51, '28398332', 'Sensor4', 'MD-V-volt-1.5', 'Voltage', 'voltage', '0.10', '1.50', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Eaque similique, maiores cumque dolores voluptatem consequatur labore fugit officia vel magni quis laborum, earum voluptas, sequi expedita natu', 'Vitebsk'),
(52, '91466609', 'Sensor7', 'MD-V-0.5', 'Voltage', 'voltage', '0.30', '0.50', 'Voltage Sensor', 'Gomel'),
(53, '38995816', 'Sensor8', 'MD-H-h-38', 'Humidity', '%', '0.00', '38.00', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Eaque similique, maiores cumque dolores voluptatem consequatur labore fugit officia vel magni quis laborum, earum voluptas, sequi expedita natu', 'Vitebsk'),
(55, '19819112', 'Sensor9', 'Model9', 'Pressure', 'bar', '1.00', '50.00', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Eaque similique, maiores cumque dolores voluptatem consequatur labore fugit officia vel magni quis laborum, earum voluptas, sequi expedita natu', 'Minsk');

-- --------------------------------------------------------

--
-- Table structure for table `sensor_types`
--

CREATE TABLE `sensor_types` (
  `sensor_type_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sensor_types`
--

INSERT INTO `sensor_types` (`sensor_type_name`) VALUES
('Humidity'),
('Pressure'),
('Temperature'),
('Voltage');

-- --------------------------------------------------------

--
-- Table structure for table `sensor_units`
--

CREATE TABLE `sensor_units` (
  `sensor_unit_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sensor_units`
--

INSERT INTO `sensor_units` (`sensor_unit_name`) VALUES
('%'),
('bar'),
('voltage'),
('°С');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `authorities` tinyblob,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `is_not_locked` bit(1) NOT NULL,
  `join_date` datetime DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `last_login_date_display` datetime DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_profile_image_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `authorities`, `email`, `first_name`, `is_active`, `is_not_locked`, `join_date`, `last_login_date`, `last_login_date_display`, `last_name`, `password`, `role`, `user_id`, `user_name`, `user_profile_image_url`) VALUES
(11, 0xaced0005757200135b4c6a6176612e6c616e672e537472696e673badd256e7e91d7b47020000787000000001740009757365723a72656164, 'fictiveuser1@mail.com', 'Alice', b'0', b'1', '2021-08-10 14:59:32', NULL, NULL, 'Cooper', '$2a$10$NNtWApEwv3fPmTV2DmLCve/bRaVM.SArbelO6LBEN.so7IlYKCzby', 'ROLE_USER', '8333153865', 'fictiveuser1', 'http://localhost:8081/user/image/profile/fictiveuser1'),
(12, 0xaced0005757200135b4c6a6176612e6c616e672e537472696e673badd256e7e91d7b47020000787000000001740009757365723a72656164, 'fictiveuser2@mai.com', 'Paul', b'0', b'1', '2021-08-10 14:59:26', NULL, NULL, 'Anderson', '$2a$10$PHQGpRBi0t2Wqw6OsPcZGO7T0PAGau7vLnseYIagttta4YZb0Omsm', 'ROLE_USER', '2994894147', 'fictiveuser2', 'http://localhost:8081/user/image/profile/fictiveuser2'),
(13, 0xaced0005757200135b4c6a6176612e6c616e672e537472696e673badd256e7e91d7b47020000787000000001740009757365723a72656164, 'fictiveuser3@mail.com', 'David', b'0', b'1', '2021-08-10 14:59:21', NULL, NULL, 'Raw', '$2a$10$XwPY2LD2/R7RHekoRjgTO.WuqO8VXJPAVGDS1thfsSa/wn/aKv/tu', 'ROLE_USER', '5354683361', 'fictiveuser3', 'http://localhost:8081/user/image/profile/fictiveuser3'),
(14, 0xaced0005757200135b4c6a6176612e6c616e672e537472696e673badd256e7e91d7b47020000787000000001740009757365723a72656164, 'fictiveuser4@mail.com', 'Sam', b'0', b'1', '2021-08-10 14:59:13', NULL, NULL, 'Smith', '$2a$10$qqZ8hg.LUdQ3dRiBvZVXn.O.17Ma29.tmwe6qVBJS6GHoDscfbPaG', 'ROLE_USER', '8523857237', 'fictiveuser4', 'http://localhost:8081/user/image/profile/fictiveuser4'),
(15, 0xaced0005757200135b4c6a6176612e6c616e672e537472696e673badd256e7e91d7b47020000787000000001740009757365723a72656164, 'fictiveuser5@mail.com', 'Andrew', b'0', b'1', '2021-08-10 14:59:10', NULL, NULL, 'Borson', '$2a$10$c2H2ZtMW99gjwUW19iZ.jOWqPmzyT0wycUQNnu1Lz.JVOsEBkf2DC', 'ROLE_USER', '0221924625', 'fictiveuser5', 'http://localhost:8081/user/image/profile/fictiveuser5'),
(16, 0xaced0005757200135b4c6a6176612e6c616e672e537472696e673badd256e7e91d7b47020000787000000004740009757365723a7265616474000b757365723a75706461746574000b757365723a63726561746574000b757365723a64656c657465, 'morthy.smith007@gmail.com', 'Morty', b'1', b'1', '2021-08-10 14:52:55', '2021-08-10 14:52:47', '2021-08-10 14:51:58', 'Smith', '$2a$10$GCRA43/9WpCj8VC3P3m02OFzMc7dY086.LsEJKZGy99reFYktRuWa', 'ROLE_ADMIN', '8671885159', 'mortysmith', 'http://localhost:8081/user/image/profile/mortysmith'),
(17, 0xaced0005757200135b4c6a6176612e6c616e672e537472696e673badd256e7e91d7b47020000787000000001740009757365723a72656164, 'summer.smith.spring5@gmail.com', 'Summer', b'1', b'1', '2021-08-10 20:48:05', '2021-08-10 20:48:35', NULL, 'Smith', '$2a$10$IviKuhMugzc2h/v5XytgguxozUjvLmCdThKZ601Q7K8HaljrBuMt2', 'ROLE_USER', '4824730863', 'summersmith', 'http://localhost:8081/user/image/profile/summersmith');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sensors`
--
ALTER TABLE `sensors`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK684duacohyn7u2i39qllnb31g` (`sensor_type`),
  ADD KEY `FKnm48i6lex5ss2bshyvg0qebai` (`sensor_unit`);

--
-- Indexes for table `sensor_types`
--
ALTER TABLE `sensor_types`
  ADD PRIMARY KEY (`sensor_type_name`);

--
-- Indexes for table `sensor_units`
--
ALTER TABLE `sensor_units`
  ADD PRIMARY KEY (`sensor_unit_name`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sensors`
--
ALTER TABLE `sensors`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sensors`
--
ALTER TABLE `sensors`
  ADD CONSTRAINT `FK684duacohyn7u2i39qllnb31g` FOREIGN KEY (`sensor_type`) REFERENCES `sensor_types` (`sensor_type_name`),
  ADD CONSTRAINT `FKnm48i6lex5ss2bshyvg0qebai` FOREIGN KEY (`sensor_unit`) REFERENCES `sensor_units` (`sensor_unit_name`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

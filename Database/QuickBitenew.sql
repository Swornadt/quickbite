-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 13, 2026 at 05:43 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `QuickBite`
--

-- --------------------------------------------------------

--
-- Table structure for table `favourite`
--

CREATE TABLE `favourite` (
  `user_id` smallint(5) UNSIGNED NOT NULL,
  `outlet_id` tinyint(3) UNSIGNED NOT NULL,
  `item_id` smallint(5) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedback_id` smallint(5) UNSIGNED NOT NULL,
  `rating_value` tinyint(3) UNSIGNED NOT NULL,
  `rating_date` datetime NOT NULL,
  `feedback_description` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `item_id` smallint(5) UNSIGNED NOT NULL,
  `item_name` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `item_type` varchar(10) NOT NULL,
  `item_description` varchar(1000) DEFAULT NULL,
  `item_status` varchar(50) NOT NULL,
  `item_ingredient` varchar(1000) NOT NULL,
  `item_allergy` varchar(200) DEFAULT NULL,
  `item_image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `order_id` smallint(5) UNSIGNED NOT NULL,
  `order_date` datetime DEFAULT NULL,
  `order_status` tinyint(3) UNSIGNED NOT NULL,
  `order_note` varchar(1000) DEFAULT NULL,
  `preferred_date` datetime DEFAULT NULL,
  `payment_id` smallint(5) UNSIGNED DEFAULT NULL,
  `user_id` smallint(5) UNSIGNED DEFAULT NULL,
  `feedback_id` smallint(5) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_outlet_item`
--

CREATE TABLE `order_outlet_item` (
  `order_id` smallint(5) UNSIGNED NOT NULL,
  `outlet_id` tinyint(3) UNSIGNED NOT NULL,
  `item_id` smallint(5) UNSIGNED NOT NULL,
  `item_qty` tinyint(3) UNSIGNED NOT NULL,
  `order_subtotal` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `outlet`
--

CREATE TABLE `outlet` (
  `outlet_id` tinyint(3) UNSIGNED NOT NULL,
  `outlet_name` varchar(100) NOT NULL,
  `outlet_status` varchar(15) NOT NULL,
  `outlet_image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `outlet_item`
--

CREATE TABLE `outlet_item` (
  `outlet_id` tinyint(3) UNSIGNED NOT NULL,
  `item_id` smallint(5) UNSIGNED NOT NULL,
  `outlet_item_price` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payment_id` smallint(5) UNSIGNED NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `payment_status` varchar(15) NOT NULL,
  `payment_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` smallint(5) UNSIGNED NOT NULL,
  `f_name` varchar(50) NOT NULL,
  `l_name` varchar(50) NOT NULL,
  `number` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `image` longtext DEFAULT NULL,
  `reset_pwd` varchar(255) DEFAULT NULL,
  `outlet_id` tinyint(3) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `favourite`
--
ALTER TABLE `favourite`
  ADD PRIMARY KEY (`user_id`,`outlet_id`,`item_id`),
  ADD KEY `fk_favourite_outlet_item` (`outlet_id`,`item_id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedback_id`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `fk_order_payment` (`payment_id`),
  ADD KEY `fk_order_user` (`user_id`),
  ADD KEY `fk_order_feedback` (`feedback_id`);

--
-- Indexes for table `order_outlet_item`
--
ALTER TABLE `order_outlet_item`
  ADD PRIMARY KEY (`order_id`,`outlet_id`,`item_id`),
  ADD KEY `fk_ooi_outlet_item` (`outlet_id`,`item_id`);

--
-- Indexes for table `outlet`
--
ALTER TABLE `outlet`
  ADD PRIMARY KEY (`outlet_id`);

--
-- Indexes for table `outlet_item`
--
ALTER TABLE `outlet_item`
  ADD PRIMARY KEY (`outlet_id`,`item_id`),
  ADD KEY `fk_outlet_item_item` (`item_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `fk_user_outlet` (`outlet_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedback_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `item_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `order_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `favourite`
--
ALTER TABLE `favourite`
  ADD CONSTRAINT `fk_favourite_outlet_item` FOREIGN KEY (`outlet_id`,`item_id`) REFERENCES `outlet_item` (`outlet_id`, `item_id`),
  ADD CONSTRAINT `fk_favourite_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `fk_order_feedback` FOREIGN KEY (`feedback_id`) REFERENCES `feedback` (`feedback_id`),
  ADD CONSTRAINT `fk_order_payment` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`),
  ADD CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `order_outlet_item`
--
ALTER TABLE `order_outlet_item`
  ADD CONSTRAINT `fk_ooi_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  ADD CONSTRAINT `fk_ooi_outlet_item` FOREIGN KEY (`outlet_id`,`item_id`) REFERENCES `outlet_item` (`outlet_id`, `item_id`);

--
-- Constraints for table `outlet_item`
--
ALTER TABLE `outlet_item`
  ADD CONSTRAINT `fk_outlet_item_item` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  ADD CONSTRAINT `fk_outlet_item_outlet` FOREIGN KEY (`outlet_id`) REFERENCES `outlet` (`outlet_id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_outlet` FOREIGN KEY (`outlet_id`) REFERENCES `outlet` (`outlet_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

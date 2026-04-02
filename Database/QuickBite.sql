-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 02, 2026 at 04:27 PM
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
-- Table structure for table `Favorite`
--

CREATE TABLE `Favorite` (
  `user_id` smallint(5) UNSIGNED NOT NULL,
  `item_id` smallint(5) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Feedback`
--

CREATE TABLE `Feedback` (
  `feedback_id` smallint(5) UNSIGNED NOT NULL,
  `user_id` smallint(5) UNSIGNED NOT NULL,
  `rating_value` tinyint(3) UNSIGNED NOT NULL,
  `rating_date` datetime NOT NULL,
  `feedback_description` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Item`
--

CREATE TABLE `Item` (
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
-- Table structure for table `Order`
--

CREATE TABLE `Order` (
  `order_id` smallint(5) UNSIGNED NOT NULL,
  `user_id` smallint(5) UNSIGNED NOT NULL,
  `order_date` datetime DEFAULT NULL,
  `order_status` tinyint(3) UNSIGNED NOT NULL,
  `order_note` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Order_Item`
--

CREATE TABLE `Order_Item` (
  `order_id` smallint(5) UNSIGNED NOT NULL,
  `item_id` smallint(5) UNSIGNED NOT NULL,
  `item_qty` tinyint(3) UNSIGNED NOT NULL,
  `order_subtotal` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Outlet`
--

CREATE TABLE `Outlet` (
  `outlet_id` tinyint(3) UNSIGNED NOT NULL,
  `outlet_name` varchar(100) NOT NULL,
  `outlet_status` varchar(15) NOT NULL,
  `outlet_image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Outlet_Item`
--

CREATE TABLE `Outlet_Item` (
  `outlet_id` tinyint(3) UNSIGNED NOT NULL,
  `item_id` smallint(5) UNSIGNED NOT NULL,
  `outlet_item_price` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Payment`
--

CREATE TABLE `Payment` (
  `payment_id` smallint(5) UNSIGNED NOT NULL,
  `order_id` smallint(5) UNSIGNED NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `payment_status` varchar(15) NOT NULL,
  `payment_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `user_id` smallint(5) UNSIGNED NOT NULL,
  `user_name` varchar(100) NOT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_phone_number` varchar(15) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_role` tinyint(3) UNSIGNED NOT NULL,
  `user_status` tinyint(3) UNSIGNED NOT NULL,
  `user_image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `User_Outlet`
--

CREATE TABLE `User_Outlet` (
  `user_id` smallint(5) UNSIGNED NOT NULL,
  `outlet_id` tinyint(3) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Favorite`
--
ALTER TABLE `Favorite`
  ADD PRIMARY KEY (`user_id`,`item_id`),
  ADD KEY `Favorite_item` (`item_id`);

--
-- Indexes for table `Feedback`
--
ALTER TABLE `Feedback`
  ADD PRIMARY KEY (`feedback_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `Item`
--
ALTER TABLE `Item`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `Order`
--
ALTER TABLE `Order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `Order_user` (`user_id`);

--
-- Indexes for table `Order_Item`
--
ALTER TABLE `Order_Item`
  ADD PRIMARY KEY (`order_id`,`item_id`),
  ADD KEY `Order_Item_2` (`item_id`);

--
-- Indexes for table `Outlet`
--
ALTER TABLE `Outlet`
  ADD PRIMARY KEY (`outlet_id`);

--
-- Indexes for table `Outlet_Item`
--
ALTER TABLE `Outlet_Item`
  ADD PRIMARY KEY (`outlet_id`,`item_id`),
  ADD KEY `Outlet_Item_2` (`item_id`);

--
-- Indexes for table `Payment`
--
ALTER TABLE `Payment`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `User_Outlet`
--
ALTER TABLE `User_Outlet`
  ADD PRIMARY KEY (`user_id`,`outlet_id`),
  ADD KEY `User_Outlet_2` (`outlet_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Feedback`
--
ALTER TABLE `Feedback`
  MODIFY `feedback_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Item`
--
ALTER TABLE `Item`
  MODIFY `item_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Order`
--
ALTER TABLE `Order`
  MODIFY `order_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Payment`
--
ALTER TABLE `Payment`
  MODIFY `payment_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `user_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Favorite`
--
ALTER TABLE `Favorite`
  ADD CONSTRAINT `Favorite_item` FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`),
  ADD CONSTRAINT `Favorite_user` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`);

--
-- Constraints for table `Feedback`
--
ALTER TABLE `Feedback`
  ADD CONSTRAINT `Feedback_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`);

--
-- Constraints for table `Order`
--
ALTER TABLE `Order`
  ADD CONSTRAINT `Order_user` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`);

--
-- Constraints for table `Order_Item`
--
ALTER TABLE `Order_Item`
  ADD CONSTRAINT `Order_Item_1` FOREIGN KEY (`order_id`) REFERENCES `Order` (`order_id`),
  ADD CONSTRAINT `Order_Item_2` FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`);

--
-- Constraints for table `Outlet_Item`
--
ALTER TABLE `Outlet_Item`
  ADD CONSTRAINT `Outlet_Item_1` FOREIGN KEY (`outlet_id`) REFERENCES `Outlet` (`outlet_id`),
  ADD CONSTRAINT `Outlet_Item_2` FOREIGN KEY (`item_id`) REFERENCES `Item` (`item_id`);

--
-- Constraints for table `Payment`
--
ALTER TABLE `Payment`
  ADD CONSTRAINT `Payment_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `Order` (`order_id`);

--
-- Constraints for table `User_Outlet`
--
ALTER TABLE `User_Outlet`
  ADD CONSTRAINT `User_Outlet_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`),
  ADD CONSTRAINT `User_Outlet_2` FOREIGN KEY (`outlet_id`) REFERENCES `Outlet` (`outlet_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 19, 2026 at 06:35 PM
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
-- Table structure for table `favorite`
--

CREATE TABLE `favorite` (
  `user_id` smallint(5) UNSIGNED NOT NULL,
  `outlet_id` tinyint(3) UNSIGNED NOT NULL,
  `item_id` smallint(5) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `favorite`
--

INSERT INTO `favorite` (`user_id`, `outlet_id`, `item_id`) VALUES
(11, 1, 5),
(11, 5, 10),
(11, 5, 11),
(11, 6, 10),
(15, 1, 5),
(15, 3, 15),
(15, 4, 5),
(16, 1, 5),
(17, 4, 19);

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

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`feedback_id`, `rating_value`, `rating_date`, `feedback_description`) VALUES
(1, 5, '2026-05-15 16:23:02', 'test'),
(2, 5, '2026-05-15 16:31:17', 'test 2'),
(3, 3, '2026-05-15 16:40:56', 'test 3'),
(4, 5, '2026-05-15 17:04:51', 'test 5');

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

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`item_id`, `item_name`, `category`, `item_type`, `item_description`, `item_status`, `item_ingredient`, `item_allergy`, `item_image`) VALUES
(4, 'Breakfast Set', 'Breakfast', 'Non Veg', 'Complete breakfast set', 'Available', 'Egg, Bread, Sausage', 'Egg', '/assets/images/BreakfastSet.png'),
(5, 'Buff Chowmein', 'Lunch', 'Non Veg', 'Buff chowmein noodles', 'Available', 'Noodles, Buff, Vegetables', 'Gluten', '/assets/images/BuffChowmein.png'),
(6, 'Chatpate', 'Snack', 'Veg', 'Spicy Nepali street snack', 'Available', 'Puffed rice, spices, onion', '', '/assets/images/Chatpate.png'),
(7, 'Chicken Fried Momo', 'Snack', 'Non Veg', 'Fried chicken dumplings', 'Available', 'Chicken, Flour, Oil', 'Gluten', '/assets/images/ChickenFriedMomo.png'),
(8, 'Chicken Jhol Momo', 'Lunch', 'Non Veg', 'Chicken momo in soup', 'Available', 'Chicken, Flour, Soup broth', NULL, '/assets/images/ChickenJholMomo.png'),
(9, 'Chicken Tandoori With Naan', 'Lunch', 'Non Veg', 'Tandoori chicken with naan bread', 'Available', 'Chicken, Spices, Wheat', NULL, '/assets/images/ChickenTandooriWithNaan.png'),
(10, 'Club Sandwich', 'Snack', 'Non Veg', 'Layered club sandwich', 'Available', 'Bread, Chicken, Egg, Veggies', 'Egg', '/assets/images/ClubSandwich.png'),
(11, 'Fruit Bowl', 'Breakfast', 'Veg', 'Mixed fresh fruit bowl', 'Available', 'Seasonal fruits', NULL, '/assets/images/FruitBowl.png'),
(12, 'Non Veg Thakali', 'Lunch', 'Non Veg', 'Traditional non-veg Nepali set', 'Available', 'Rice, Meat, Curry, Vegetables', NULL, '/assets/images/NonVegThakali.png'),
(13, 'Potato Wedges', 'Snack', 'Veg', 'Crispy potato wedges', 'Available', 'Potato, Oil, Salt', NULL, '/assets/images/PotatoWedges.png'),
(14, 'Samosa', 'Snack', 'Veg', 'Fried stuffed pastry', 'Available', 'Flour, Potato, Spices', 'Gluten', '/assets/images/Samosa.png'),
(15, 'Steamed Buff Momo', 'Snack', 'Non Veg', 'Steamed buffalo dumplings', 'Available', 'Buff, Flour, Spices', NULL, '/assets/images/SteamedBuffMomo.png'),
(16, 'Steamed Chicken Momo', 'Snack', 'Non Veg', 'Steamed chicken dumplings', 'Available', 'Chicken, Flour, Spices', NULL, '/assets/images/SteamedChickenMomo.png'),
(17, 'Veg Thukpa', 'Lunch', 'Veg', 'Vegetable noodle soup', 'Available', 'Noodles, Vegetables, Broth', NULL, '/assets/images/VegThukpa.png'),
(19, 'Special Chowmein', 'Lunch', 'Non-Veg', '', 'Available', 'special', '', 'uploads/items/momo1_1779109271960.png');

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

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`order_id`, `order_date`, `order_status`, `order_note`, `preferred_date`, `payment_id`, `user_id`, `feedback_id`) VALUES
(13, '2026-05-13 12:47:40', 2, '', NULL, NULL, 11, 2),
(14, '2026-05-13 15:49:42', 2, '', NULL, NULL, 11, 3),
(15, '2026-05-13 15:49:54', 0, '[ASAP]', NULL, NULL, 11, NULL),
(16, '2026-05-13 15:50:07', 2, '', NULL, NULL, 11, 4),
(17, '2026-05-13 15:50:21', 2, '', '2026-05-06 15:30:00', NULL, 11, 1),
(18, '2026-05-13 17:20:22', 0, '[ASAP]', NULL, 1, 11, NULL),
(19, '2026-05-16 08:20:47', 0, '[ASAP]', NULL, 2, 15, NULL),
(20, '2026-05-16 12:29:40', 0, '', '2026-05-18 11:00:00', 3, 15, NULL),
(21, '2026-05-16 12:45:06', 0, '[ASAP]', NULL, 4, 15, NULL),
(22, '2026-05-16 12:45:32', 0, '', '2026-05-17 09:30:00', 5, 15, NULL),
(23, '2026-05-17 12:13:30', 0, '', '2026-05-17 14:00:00', 6, 16, NULL),
(24, '2026-05-18 17:51:13', 0, '[ASAP]', NULL, 7, 16, NULL),
(25, '2026-05-18 19:08:43', 0, '[ASAP]', NULL, 8, 17, NULL),
(26, '2026-05-19 22:08:16', 2, '[ASAP]', NULL, 9, 11, NULL),
(27, '2026-05-19 22:08:35', 1, '[ASAP]', NULL, 10, 11, NULL),
(28, '2026-05-19 22:08:48', 0, '[ASAP]', NULL, 11, 11, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `order_outlet_item`
--

CREATE TABLE `order_outlet_item` (
  `order_id` smallint(5) UNSIGNED NOT NULL,
  `outlet_id` tinyint(3) UNSIGNED NOT NULL,
  `item_id` smallint(5) UNSIGNED NOT NULL,
  `item_qty` tinyint(3) UNSIGNED NOT NULL,
  `order_subtotal` decimal(10,2) NOT NULL,
  `item_status` tinyint(4) DEFAULT 0,
  `outlet_order_status` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_outlet_item`
--

INSERT INTO `order_outlet_item` (`order_id`, `outlet_id`, `item_id`, `item_qty`, `order_subtotal`, `item_status`, `outlet_order_status`) VALUES
(13, 1, 4, 1, 175.00, 0, 0),
(14, 1, 4, 1, 175.00, 1, 0),
(15, 1, 5, 1, 150.00, 0, 0),
(16, 1, 4, 1, 175.00, 1, 0),
(17, 1, 4, 1, 175.00, 1, 0),
(18, 1, 4, 3, 525.00, 0, 0),
(19, 1, 4, 2, 350.00, 0, 0),
(20, 1, 4, 2, 350.00, 0, 0),
(21, 3, 7, 1, 120.00, 0, 0),
(21, 3, 15, 1, 140.00, 0, 0),
(21, 4, 5, 1, 150.00, 0, 0),
(21, 5, 11, 1, 160.00, 0, 0),
(22, 5, 10, 1, 350.00, 0, 0),
(22, 6, 14, 2, 60.00, 0, 0),
(23, 1, 4, 2, 350.00, 0, 0),
(23, 3, 7, 1, 120.00, 0, 0),
(23, 3, 15, 1, 140.00, 0, 0),
(23, 3, 16, 1, 150.00, 0, 0),
(23, 4, 5, 1, 150.00, 0, 0),
(24, 1, 5, 1, 150.00, 0, 0),
(25, 4, 6, 1, 85.00, 0, 0),
(25, 4, 19, 1, 180.00, 0, 0),
(25, 5, 10, 1, 350.00, 0, 0),
(26, 1, 4, 1, 175.00, 1, 2),
(26, 1, 6, 1, 85.00, 1, 2),
(27, 1, 5, 1, 150.00, 0, 1),
(27, 1, 6, 1, 85.00, 0, 1),
(28, 1, 4, 1, 175.00, 0, 0);

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

--
-- Dumping data for table `outlet`
--

INSERT INTO `outlet` (`outlet_id`, `outlet_name`, `outlet_status`, `outlet_image`) VALUES
(1, 'Canteen', 'Active', 'assets/outlets/canteen.png'),
(2, 'Coffee Station', 'Active', 'assets/outlets/coffee.png'),
(3, 'Momo Station', 'Active', 'assets/outlets/momostation.png'),
(4, 'Chautari', 'Active', 'assets/outlets/chautari.png'),
(5, 'Brit Cafe', 'Active', 'assets/outlets/brit.png'),
(6, 'Kumari', 'Active', 'assets/outlets/kumari.png');

-- --------------------------------------------------------

--
-- Table structure for table `outlet_item`
--

CREATE TABLE `outlet_item` (
  `outlet_id` tinyint(3) UNSIGNED NOT NULL,
  `item_id` smallint(5) UNSIGNED NOT NULL,
  `outlet_item_price` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `outlet_item`
--

INSERT INTO `outlet_item` (`outlet_id`, `item_id`, `outlet_item_price`) VALUES
(1, 4, 175.00),
(1, 5, 150.00),
(1, 6, 85.00),
(1, 7, 120.00),
(1, 8, 150.00),
(1, 9, 300.00),
(1, 10, 350.00),
(1, 11, 160.00),
(1, 12, 210.00),
(1, 13, 100.00),
(1, 14, 30.00),
(1, 15, 140.00),
(1, 16, 150.00),
(1, 17, 100.00),
(3, 7, 120.00),
(3, 15, 140.00),
(3, 16, 150.00),
(4, 5, 150.00),
(4, 6, 85.00),
(4, 8, 150.00),
(4, 9, 300.00),
(4, 10, 350.00),
(4, 11, 160.00),
(4, 12, 210.00),
(4, 13, 100.00),
(4, 14, 30.00),
(4, 17, 100.00),
(4, 19, 180.00),
(5, 6, 85.00),
(5, 10, 350.00),
(5, 11, 160.00),
(5, 13, 100.00),
(5, 14, 30.00),
(6, 6, 85.00),
(6, 10, 350.00),
(6, 11, 160.00),
(6, 13, 100.00),
(6, 14, 30.00);

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

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payment_id`, `amount`, `payment_status`, `payment_date`) VALUES
(1, 525.00, 'Completed', '2026-05-13 17:20:22'),
(2, 350.00, 'Completed', '2026-05-16 08:20:47'),
(3, 350.00, 'Completed', '2026-05-16 12:29:40'),
(4, 570.00, 'Completed', '2026-05-16 12:45:06'),
(5, 410.00, 'Completed', '2026-05-16 12:45:32'),
(6, 910.00, 'Completed', '2026-05-17 12:13:29'),
(7, 150.00, 'Completed', '2026-05-18 17:51:13'),
(8, 615.00, 'Completed', '2026-05-18 19:08:43'),
(9, 260.00, 'Completed', '2026-05-19 22:08:16'),
(10, 235.00, 'Completed', '2026-05-19 22:08:35'),
(11, 175.00, 'Completed', '2026-05-19 22:08:48');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` smallint(5) UNSIGNED NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
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
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `fname`, `lname`, `number`, `email`, `gender`, `dob`, `password`, `role`, `status`, `image`, `reset_pwd`, `outlet_id`) VALUES
(11, 'Sahil', 'Shrestha', '9846714966', 'shresthasahil65@gmail.com', 'Male', '2005-12-17', '$2a$10$JDl2yzRC60kpNolOe5slQ.mgWRU8g9emGm2WTIpGinsF5.ItmqHH.', 'customer', 'active', 'uploads/default.png', NULL, 1),
(12, 'Canteen', 'Staff', '9846714900', 'email@gmail.com', 'Male', '2023-01-01', '$2a$10$BaVyDoMEgPOIJMW4ckTM4.EXuBbGb/LUDc/ut3mzhVxY67qhjNeqy', 'staff', 'active', 'uploads/default.png', NULL, 1),
(15, 'Admin', 'Admin', '9812345678', 'admin@gmail.com', 'Male', '2006-03-26', '$2a$10$M53jKdZzBqiO.MKR9cj.aeDInZtn/1qwH80mIXY6s/RAVyV4N9vPS', 'admin', 'active', 'uploads/github-profile_1778898662018.jpeg', NULL, NULL),
(16, 'Resha', 'Koju', '9812345679', 'resha@gmail.com', 'Female', '2006-03-26', '$2a$10$14j.7fEEbK3k9UITSZz1eOHVB9VIadwkCIRB5NdApCfTH5fB2nxgi', 'customer', 'active', 'uploads/mou-photo_1778985126914.jpeg', '0', NULL),
(17, 'Sworna', 'Tuladhar', '9840259002', 'sworna@gmail.com', 'Male', '2006-03-26', '$2a$10$girxrtFmUlWBx3e0T.FJGOTvXMS7cyOn6M1Js.rTj3T1kIw45krPS', 'customer', 'active', 'uploads/me_1779104683962.png', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `favorite`
--
ALTER TABLE `favorite`
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
  MODIFY `feedback_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `item_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `order_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `favorite`
--
ALTER TABLE `favorite`
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

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 06, 2026 at 07:31 AM
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
-- Database: `quickbite`
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

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedback_id` smallint(5) UNSIGNED NOT NULL,
  `user_id` smallint(5) UNSIGNED NOT NULL,
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

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`item_id`, `item_name`, `category`, `item_type`, `item_description`, `item_status`, `item_ingredient`, `item_allergy`, `item_image`) VALUES
(4, 'Breakfast Set', 'Breakfast', 'Non Veg', 'Complete breakfast set', 'Available', 'Egg, Bread, Sausage', 'Egg', 'BreakfastSet.png'),
(5, 'Buff Chowmein', 'Lunch', 'Non Veg', 'Buff chowmein noodles', 'Available', 'Noodles, Buff, Vegetables', 'Gluten', 'BuffChowmein.png'),
(6, 'Chatpate', 'Snack', 'Veg', 'Spicy Nepali street snack', 'Available', 'Puffed rice, spices, onion', NULL, 'Chatpate.png'),
(7, 'Chicken Fried Momo', 'Snack', 'Non Veg', 'Fried chicken dumplings', 'Available', 'Chicken, Flour, Oil', 'Gluten', 'ChickenFriedMomo.png'),
(8, 'Chicken Jhol Momo', 'Lunch', 'Non Veg', 'Chicken momo in soup', 'Available', 'Chicken, Flour, Soup broth', NULL, 'ChickenJholMomo.png'),
(9, 'Chicken Tandoori With Naan', 'Lunch', 'Non Veg', 'Tandoori chicken with naan bread', 'Available', 'Chicken, Spices, Wheat', NULL, 'ChickenTandooriWithNaan.png'),
(10, 'Club Sandwich', 'Snack', 'Non Veg', 'Layered club sandwich', 'Available', 'Bread, Chicken, Egg, Veggies', 'Egg', 'ClubSandwich.png'),
(11, 'Fruit Bowl', 'Breakfast', 'Veg', 'Mixed fresh fruit bowl', 'Available', 'Seasonal fruits', NULL, 'FruitBowl.png'),
(12, 'Non Veg Thakali', 'Lunch', 'Non Veg', 'Traditional non-veg Nepali set', 'Available', 'Rice, Meat, Curry, Vegetables', NULL, 'NonVegThakali.png'),
(13, 'Potato Wedges', 'Snack', 'Veg', 'Crispy potato wedges', 'Available', 'Potato, Oil, Salt', NULL, 'PotatoWedges.png'),
(14, 'Samosa', 'Snack', 'Veg', 'Fried stuffed pastry', 'Available', 'Flour, Potato, Spices', 'Gluten', 'Samosa.png'),
(15, 'Steamed Buff Momo', 'Snack', 'Non Veg', 'Steamed buffalo dumplings', 'Available', 'Buff, Flour, Spices', NULL, 'SteamedBuffMomo.png'),
(16, 'Steamed Chicken Momo', 'Snack', 'Non Veg', 'Steamed chicken dumplings', 'Available', 'Chicken, Flour, Spices', NULL, 'SteamedChickenMomo.png'),
(17, 'Veg Thukpa', 'Lunch', 'Veg', 'Vegetable noodle soup', 'Available', 'Noodles, Vegetables, Broth', NULL, 'VegThukpa.png'),
(18, 'test', 'food', 'Veg', 'Test Case', 'Not Available', 'test', 'test', 'uploads/items/default.png');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `order_id` smallint(5) UNSIGNED NOT NULL,
  `user_id` smallint(5) UNSIGNED NOT NULL,
  `order_date` datetime DEFAULT NULL,
  `order_status` tinyint(3) UNSIGNED NOT NULL,
  `order_note` varchar(1000) DEFAULT NULL,
  `preferred_date` datetime DEFAULT NULL
 )
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

--
-- Dumping data for table `order_outlet_item`
--

INSERT INTO `order_outlet_item` (`order_id`, `outlet_id`, `item_id`, `item_qty`, `order_subtotal`) VALUES
(1, 1, 4, 1, 175.00),
(2, 1, 17, 2, 200.00),
(3, 1, 16, 2, 300.00),
(4, 1, 5, 2, 300.00),
(5, 1, 5, 1, 150.00),
(5, 1, 6, 1, 85.00),
(6, 1, 4, 1, 175.00),
(6, 1, 7, 1, 120.00),
(7, 1, 4, 2, 350.00),
(7, 1, 5, 1, 150.00),
(8, 1, 4, 1, 175.00),
(9, 1, 5, 1, 150.00),
(10, 1, 4, 1, 175.00),
(11, 1, 4, 1, 175.00),
(12, 1, 4, 1, 175.00);

-- --------------------------------------------------------

--
-- Table structure for table `outlet`
--

CREATE TABLE `outlet` (
  `outlet_id` tinyint(3) UNSIGNED NOT NULL,
  `outlet_name` varchar(100) NOT NULL,
  `outlet_status` varchar(15) NOT NULL,
  `outlet_image` varchar(255) DEFAULT NULL
=======
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `outlet`
--

INSERT INTO `outlet` (`outlet_id`, `outlet_name`, `outlet_status`, `outlet_image`) VALUES
(1, 'Canteen', 'Active', NULL),
(2, 'Coffee Station', 'Active', NULL),
(3, 'Momo Station', 'Active', NULL),
(4, 'Chautari', 'Active', NULL),
(5, 'Birt Cafe', 'Active', NULL),
(6, 'Kumari', 'Active', NULL);

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
  `order_id` smallint(5) UNSIGNED NOT NULL,
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
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `number` varchar(15) NOT NULL,
  `email` varchar(100) NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `image` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_outlet`
--

INSERT INTO `order` (`order_id`, `user_id`, `order_date`, `order_status`, `order_note`, `preferred_date`) VALUES
(1, 9, '2026-05-01 21:00:29', 0, '[ASAP] ', NULL),
(2, 9, '2026-05-01 21:01:25', 0, '[ASAP] ', NULL),
(3, 9, '2026-05-01 21:01:41', 0, '[ASAP] ', NULL),
(4, 9, '2026-05-01 21:02:03', 0, '[ASAP] chow chow', NULL),
(5, 9, '2026-05-01 21:02:20', 0, '[ASAP] two item', NULL),
(6, 9, '2026-05-01 21:02:34', 0, '[ASAP] Three', NULL),
(7, 9, '2026-05-01 21:03:02', 0, 'Scheduled', NULL);
CREATE TABLE `user_outlet` (
  `user_id` smallint(5) UNSIGNED NOT NULL,
  `outlet_id` tinyint(3) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_outlet`
--

INSERT INTO `user_outlet` (`user_id`, `outlet_id`) VALUES
(2, 1),
(3, 2),
(4, 3),
(5, 4),
(6, 5),
(7, 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `favorite`
--
ALTER TABLE `favorite`
  ADD PRIMARY KEY (`user_id`,`outlet_id`,`item_id`),
  ADD KEY `outlet_id` (`outlet_id`,`item_id`);
-- Indexes for table `favorite`
--
ALTER TABLE `favorite`
  ADD PRIMARY KEY (`user_id`,`item_id`),
  ADD KEY `Favorite_item` (`item_id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedback_id`),
  ADD KEY `user_id` (`user_id`);

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
  ADD KEY `Order_user` (`user_id`);

--
-- Indexes for table `order_outlet_item`
--
ALTER TABLE `order_outlet_item`
  ADD PRIMARY KEY (`order_id`,`outlet_id`,`item_id`),
  ADD KEY `Order_Item_2` (`item_id`),
  ADD KEY `fk_orderitem_outletitem` (`outlet_id`,`item_id`);

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
  ADD KEY `Outlet_Item_2` (`item_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_outlet`
--
ALTER TABLE `user_outlet`
  ADD PRIMARY KEY (`user_id`,`outlet_id`),
  ADD KEY `User_Outlet_2` (`outlet_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `order_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `favorite`
--
ALTER TABLE `favorite`
  ADD CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `favorite_ibfk_2` FOREIGN KEY (`outlet_id`,`item_id`) REFERENCES `outlet_item` (`outlet_id`, `item_id`);
-- Constraints for table `favorite`
--
ALTER TABLE `favorite`
  ADD CONSTRAINT `Favorite_item` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  ADD CONSTRAINT `Favorite_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `Order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `order_outlet_item`
--
ALTER TABLE `order_outlet_item`
  ADD CONSTRAINT `Order_Item_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  ADD CONSTRAINT `Order_Item_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  ADD CONSTRAINT `fk_orderitem_outletitem` FOREIGN KEY (`outlet_id`,`item_id`) REFERENCES `outlet_item` (`outlet_id`, `item_id`);

--
-- Constraints for table `outlet_item`
--
ALTER TABLE `outlet_item`
  ADD CONSTRAINT `Outlet_Item_1` FOREIGN KEY (`outlet_id`) REFERENCES `outlet` (`outlet_id`),
  ADD CONSTRAINT `Outlet_Item_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`);

--
-- Constraints for table `user_outlet`
--
ALTER TABLE `user_outlet`
  ADD CONSTRAINT `User_Outlet_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `User_Outlet_2` FOREIGN KEY (`outlet_id`) REFERENCES `outlet` (`outlet_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
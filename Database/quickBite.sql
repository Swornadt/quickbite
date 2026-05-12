-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 12, 2026 at 02:39 PM
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
  `reset_pwd` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `fname`, `lname`, `number`, `email`, `gender`, `dob`, `password`, `role`, `status`, `image`, `reset_pwd`) VALUES
(1, 'i', 'try', '9846714966', 'email@gmail.com', 'Male', '1937-01-01', '$2a$10$f/TtbEYE2mZdabIG/kzR7.UQaZom/VyVmMPw72dJlYr.G9chOF..O', 'admin', 'active', 'uploads/default.png', NULL),
(2, 'Canteen', 'Staff', '9846714911', 'canteen@gmail.com', 'Male', '2010-01-01', '$2a$10$UKVer9zKrOrM/pplEPah3OwR.g3oO2xNmPfyjRX2n8e94orT.cTje', 'staff', 'active', 'uploads/default.png', NULL),
(3, 'Coffee Station', 'Staff', '9846714922', 'coffee@gmail.com', 'Male', '2009-12-27', '$2a$10$/Jb7QVxjJ/YFSGddo5JDH.ARTRTzbL.JIJAyRG8DBpzv8BkNQH/HC', 'staff', 'active', 'uploads/default.png', NULL),
(4, 'Momo Station', 'Staff', '9846714933', 'momo@gmail.com', 'Male', '2010-01-01', '$2a$10$6GR9aIN8ma7.OfhlmTR2YuB9ILpSG3bvz80PQS09Ek13.h7EvHn3S', 'staff', 'active', 'uploads/default.png', NULL),
(5, 'Chautari', 'Staff', '9846714944', 'chautari@gmail.com', 'Male', '2010-01-01', '$2a$10$YMkpgiDG.mSt9HQOb0W5C.jbzZfSH0zH.YFDzG2hNhHSiVg7sumfC', 'staff', 'active', 'uploads/default.png', NULL),
(6, 'Brit Cafe', 'Staff', '9846714955', 'brit@gmail.com', 'Male', '2010-01-01', '$2a$10$R5bcnYajdxj6qsvz7iHUKeXtxGG6eEVV9gCVtx4ERsRKLDc8.knti', 'staff', 'active', 'uploads/default.png', NULL),
(7, 'Kumari', 'Staff', '9846714866', 'kumari@gmail.com', 'Male', '2010-01-01', '$2a$10$wwdNoNqwyiX0nTCIokx0se9BDCGKBWbZo6D2DGYfq3ZAMspQmPXZm', 'staff', 'active', 'uploads/default.png', NULL),
(9, 'Sai', 'Stha', '9846714900', 'email@gmail.com', 'Male', '1899-12-31', '$2a$10$9V96dqG3PIwQazhN.fqITe5qqJgQ4MEKfXQuJFqZo4Jp9V.ltDG1i', 'user', 'active', 'uploads/default.png', NULL),
(10, 'Test', 'Case', '9988776655', 'email123@gmail.com', 'Male', '2006-12-31', '$2a$10$de3Vz2GsNdPSTC6rvYJhRuTniO9mdxrgPXFWdqqeEqXhto3ddEX8G', 'customer', 'active', 'uploads/default.png', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

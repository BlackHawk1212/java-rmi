-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2020 at 06:24 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sltdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `empId` int(8) NOT NULL,
  `name` varchar(100) NOT NULL,
  `empStatus` varchar(25) NOT NULL,
  `email` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`empId`, `name`, `empStatus`, `email`) VALUES
(1, 'Nimesh Mendis', 'Permanent Employee', 'nimeshmendis1997@gmail.com'),
(2, 'Tom Cruise', 'Trainee Employee', 'tom@yahoo.com'),
(3, 'Dean Winchester', 'Permanent Employee', 'deanimpala67@gmail.com'),
(4, 'Sam Winchester', 'Permanent Employee', 'sammy@gmail.com'),
(5, 'Test', 'Trainee Employee', 'testing123'),
(44, 'nimesh', 'Permanent Employee', 'nimeshmendis1997@gmail.com'),
(45, 'nimesh', 'Permanent Employee', 'nimeshmendis1997@gmailcom'),
(46, 'nimesh', 'Permanent Employee', 'nimeshmendis1997@gmailcom');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `adminId` int(10) NOT NULL,
  `adminName` varchar(50) NOT NULL,
  `adminPw` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`adminId`, `adminName`, `adminPw`) VALUES
(1, 'admin', 'admin123'),
(2, 'alpha', 'alpha123'),
(3, 'delta', 'delta123'),
(4, 'black hawk', 'blacky123'),
(5, 'test', 'test'),
(6, 'tango', '123');

-- --------------------------------------------------------

--
-- Table structure for table `options`
--

CREATE TABLE `options` (
  `optionId` int(15) NOT NULL,
  `answer` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `options`
--

INSERT INTO `options` (`optionId`, `answer`) VALUES
(1, 'Poor'),
(2, 'Below Average'),
(3, 'Average'),
(4, 'Above Average'),
(5, 'Outstanding');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `qId` int(10) NOT NULL,
  `question` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`qId`, `question`) VALUES
(1, 'How is your inter-relationship with between your co-staff/peers?'),
(2, 'How is your working environment?'),
(3, 'Rate work related facilities in your workplace (Infrastructure)?'),
(4, 'Rate common facilities given to your staff (Food, Health)?'),
(5, 'How is the induction training given to you?'),
(6, 'How superiors or co-staff share knowledge among you?'),
(7, 'How about your hands-on experience?'),
(8, 'Rate timely awareness sessions you are getting.'),
(9, 'How is your satisfaction related to the work?'),
(10, 'How department enabling working options for you (Work from home / in office)?');

-- --------------------------------------------------------

--
-- Table structure for table `responds_json`
--

CREATE TABLE `responds_json` (
  `respond_Id` int(8) NOT NULL,
  `json_respond` varchar(6000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `responds_json`
--

INSERT INTO `responds_json` (`respond_Id`, `json_respond`) VALUES
(1, '{\\\"How is your inter-relationship with between your co-staff/peers?\\\": {\\\"Poor\\\" : 12,\\\"Below Average\\\" : 10,\\\"Average\\\" : 8,\\\"Above Average\\\" : 16,\\\"Outstanding\\\" : 14},\\\"How is your working environment?\\\": {\\\"Poor\\\" : 8,\\\"Below Average\\\" : 9,\\\"Average\\\" : 15,\\\"Above Average\\\" : 18,\\\"Outstanding\\\" : 10},\\\"Rate work related facilities in your workplace (Infrastructure)?\\\": {\\\"Poor\\\" : 21,\\\"Below Average\\\" : 9,\\\"Average\\\" : 12,\\\"Above Average\\\" : 8,\\\"Outstanding\\\" : 10},\\\"Rate common facilities given to your staff (Food, Health)?\\\": {\\\"Poor\\\" : 13,\\\"Below Average\\\" : 8,\\\"Average\\\" : 12,\\\"Above Average\\\" : 16,\\\"Outstanding\\\" : 11},\\\"How is the induction training given to you?\\\": {\\\"Poor\\\" : 15,\\\"Below Average\\\" : 14,\\\"Average\\\" : 10,\\\"Above Average\\\" : 8,\\\"Outstanding\\\" : 10},\\\"How superiors or co-staff share knowledge among you?\\\": {\\\"Poor\\\" : 13,\\\"Below Average\\\" : 9,\\\"Average\\\" : 9,\\\"Above Average\\\" : 14,\\\"Outstanding\\\" : 15},\\\"How about your hands-on experience?\\\": {\\\"Poor\\\" : 10,\\\"Below Average\\\" : 11,\\\"Average\\\" : 15,\\\"Above Average\\\" : 15,\\\"Outstanding\\\" : 9},\\\"Rate timely awareness sessions you are getting.\\\": {\\\"Poor\\\" : 11,\\\"Below Average\\\" : 15,\\\"Average\\\" : 10,\\\"Above Average\\\" : 13,\\\"Outstanding\\\" : 11},\\\"How is your satisfaction related to the work?\\\": {\\\"Poor\\\" : 13,\\\"Below Average\\\" : 11,\\\"Average\\\" : 13,\\\"Above Average\\\" : 12,\\\"Outstanding\\\" : 11},\\\"How department enabling working options for you (Work from home / in office)?\\\": {\\\"Poor\\\" : 7,\\\"Below Average\\\" : 13,\\\"Average\\\" : 14,\\\"Above Average\\\" : 12,\\\"Outstanding\\\" : 14}}');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`empId`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`adminId`);

--
-- Indexes for table `options`
--
ALTER TABLE `options`
  ADD PRIMARY KEY (`optionId`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`qId`);

--
-- Indexes for table `responds_json`
--
ALTER TABLE `responds_json`
  ADD PRIMARY KEY (`respond_Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `empId` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `adminId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `options`
--
ALTER TABLE `options`
  MODIFY `optionId` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `qId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

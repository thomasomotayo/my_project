-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 29, 2021 at 02:34 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library_managements`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('omotayo', '123');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `accersion_no` varchar(50) NOT NULL,
  `isbn_no` varchar(50) NOT NULL,
  `cl_no` varchar(50) NOT NULL,
  `book_title` varchar(50) NOT NULL,
  `author_name` varchar(50) NOT NULL,
  `book_edition` varchar(50) NOT NULL,
  `shelf_no` varchar(50) NOT NULL,
  `row_no` varchar(50) NOT NULL,
  `column_no` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`accersion_no`, `isbn_no`, `cl_no`, `book_title`, `author_name`, `book_edition`, `shelf_no`, `row_no`, `column_no`) VALUES
('001', '111AB', '5', 'Java Book', 'Omotayo', '3rd', '3', '2', '3'),
('002', 'ASD12', 'd', 'ghg', 'bnmb', '5th', '3', '2', '1');

-- --------------------------------------------------------

--
-- Table structure for table `book_borrowed`
--

CREATE TABLE `book_borrowed` (
  `reg_no` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `level` varchar(50) NOT NULL,
  `semester` varchar(50) NOT NULL,
  `accerssion_no` varchar(50) NOT NULL,
  `date` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book_borrowed`
--

INSERT INTO `book_borrowed` (`reg_no`, `name`, `level`, `semester`, `accerssion_no`, `date`) VALUES
('0001', 'Aseyo Wahab', '300 Level', 'Second Semester', '001', 'MMM d, yyyy'),
('0002', 'Bayegun Olusola', '100 Level', 'First Semester', '001', 'MMM d, yyyy');

-- --------------------------------------------------------

--
-- Table structure for table `returned_book`
--

CREATE TABLE `returned_book` (
  `reg_no` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `level` varchar(50) NOT NULL,
  `semester` varchar(50) NOT NULL,
  `accerssion_no` varchar(50) NOT NULL,
  `date` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `returned_book`
--

INSERT INTO `returned_book` (`reg_no`, `name`, `level`, `semester`, `accerssion_no`, `date`) VALUES
('0001', 'Aseyo Wahab', '300 Level', 'Second Semester', '001', 'MMM d, yyyy'),
('0003', 'Adola Susan', '300 Level', 'First Semester', '002', 'MMM d, yyyy');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `reg_no` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `level` varchar(50) NOT NULL,
  `semester` varchar(50) NOT NULL,
  `contact` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`reg_no`, `name`, `level`, `semester`, `contact`) VALUES
('0001', 'Aseyo Wahab', '300 Level', 'Second Semester', '090999876'),
('0002', 'Bayegun Olusola', '100 Level', 'First Semester', '080990976'),
('0003', 'Adola Susan', '300 Level', 'First Semester', '090887643');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD UNIQUE KEY `user` (`username`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD UNIQUE KEY `accersion` (`accersion_no`);

--
-- Indexes for table `book_borrowed`
--
ALTER TABLE `book_borrowed`
  ADD UNIQUE KEY `regg` (`reg_no`);

--
-- Indexes for table `returned_book`
--
ALTER TABLE `returned_book`
  ADD UNIQUE KEY `regn` (`reg_no`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD UNIQUE KEY `regno` (`reg_no`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 04, 2026 at 06:17 AM
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
-- Database: `revenue_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `fraud_alerts`
--

CREATE TABLE `fraud_alerts` (
  `id` int(11) NOT NULL,
  `payment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `alert_type` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `is_resolved` tinyint(1) DEFAULT 0,
  `auditor_notes` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fraud_alerts`
--

INSERT INTO `fraud_alerts` (`id`, `payment_id`, `user_id`, `alert_type`, `description`, `is_resolved`, `auditor_notes`, `created_at`) VALUES
(1, 2, 2, 'amount_deviation', 'Payment amount (100000) deviates by 90.48% from user\'s average (52500.000000).', 0, NULL, '2026-07-04 00:09:46');

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `token` varchar(64) NOT NULL,
  `expires_at` datetime NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `tax_type` varchar(50) NOT NULL,
  `base_amount` decimal(10,2) NOT NULL,
  `calculated_tax` decimal(10,2) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `payment_date` datetime NOT NULL,
  `transaction_id` varchar(50) NOT NULL,
  `receipt_number` varchar(50) DEFAULT NULL,
  `status` enum('pending','verified','flagged','rejected') DEFAULT 'pending',
  `verified_by` int(11) DEFAULT NULL,
  `verified_at` datetime DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`id`, `user_id`, `tax_type`, `base_amount`, `calculated_tax`, `amount`, `payment_date`, `transaction_id`, `receipt_number`, `status`, `verified_by`, `verified_at`, `created_at`) VALUES
(1, 2, 'business', '50000.00', '5000.00', '5000.00', '2026-07-04 00:56:08', 'TXN6A484C18AC216', 'RCP202607041719', 'pending', NULL, NULL, '2026-07-03 23:56:08'),
(2, 2, 'business', '1000000.00', '100000.00', '100000.00', '2026-07-04 01:09:46', 'TXN_1783123769609_148805', 'RCP202607046731', 'flagged', NULL, NULL, '2026-07-04 00:09:46'),
(3, 2, 'business', '1000000.00', '100000.00', '100000.00', '2026-07-04 01:23:49', 'TXN_1783124621137_796970', 'RCP202607049407', 'verified', NULL, NULL, '2026-07-04 00:23:49');

-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE `settings` (
  `id` int(11) NOT NULL,
  `setting_key` varchar(50) NOT NULL,
  `setting_value` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `settings`
--

INSERT INTO `settings` (`id`, `setting_key`, `setting_value`) VALUES
(1, 'tax_rate_income', '15'),
(2, 'tax_rate_business', '10'),
(3, 'fraud_frequency_limit', '3'),
(4, 'fraud_amount_deviation', '50'),
(5, 'paystack_public_key', 'pk_test_7d1d01424f889542e452b6e164b85c2328f908bd'),
(6, 'paystack_secret_key', 'sk_test_f8e754c1ccdbd4116546bb0235bb428988578c2c');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('admin','officer','auditor','taxpayer') NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `nin` varchar(20) DEFAULT NULL,
  `business_number` varchar(20) DEFAULT NULL,
  `status` enum('active','inactive') DEFAULT 'active',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`, `full_name`, `email`, `phone`, `nin`, `business_number`, `status`, `created_at`) VALUES
(2, 'ethondigital', '$2y$10$/o/hD/yLlJTkTm2DjNFWvOxcU3JAmfs6IA0U7XJszROubKkpwBULm', 'taxpayer', 'Ethon Digital Enterprises', 'thomasomotayo@gmail.com', '+2348137219798', NULL, 'RC-1234567', 'active', '2026-07-03 23:54:58'),
(3, 'admin', '$2y$10$pu5MqLdifAWw723rSwhWPust9ojAyCkOHeWn0pcbRhWpxkzKPWYxK', 'admin', 'System Admin', 'admin@revenue.com', NULL, NULL, NULL, 'active', '2026-07-04 00:32:34'),
(4, 'officer1', '$2y$10$tDD2O5rD5Hx9h9PvUm4Zley/1Qg6xWJ/8yLiqT7sqhzmcFYjzH3GS', 'officer', 'John Officer', 'john@gmail.com', NULL, NULL, NULL, 'active', '2026-07-04 00:43:06'),
(5, 'auditor1', '$2y$10$X3RZuMnfeXz6ubN29RqajuzUsGn4iJs8pXSWhzM18CjquEZcBpCYa', 'auditor', 'Walee Auditor', 'wale@gmail.com', NULL, NULL, NULL, 'active', '2026-07-04 00:44:35');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fraud_alerts`
--
ALTER TABLE `fraud_alerts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `payment_id` (`payment_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `receipt_number` (`receipt_number`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `verified_by` (`verified_by`);

--
-- Indexes for table `settings`
--
ALTER TABLE `settings`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `setting_key` (`setting_key`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `nin` (`nin`),
  ADD UNIQUE KEY `business_number` (`business_number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fraud_alerts`
--
ALTER TABLE `fraud_alerts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `password_resets`
--
ALTER TABLE `password_resets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `settings`
--
ALTER TABLE `settings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `fraud_alerts`
--
ALTER TABLE `fraud_alerts`
  ADD CONSTRAINT `fraud_alerts_ibfk_1` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fraud_alerts_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `payments_ibfk_2` FOREIGN KEY (`verified_by`) REFERENCES `users` (`id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 30, 2013 at 05:38 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `schoolims`
--

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE IF NOT EXISTS `class` (
  `class_id` int(5) NOT NULL AUTO_INCREMENT,
  `class` varchar(20) NOT NULL,
  `class_sec` varchar(10) NOT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`class_id`, `class`, `class_sec`) VALUES
(1, 'Nursery', '0'),
(2, 'K.G', '0'),
(3, 'One', '0'),
(4, 'Two', '0'),
(5, 'Three', '0'),
(6, 'Fifteen', '0'),
(7, 'Forthsem', '0');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE IF NOT EXISTS `event` (
  `evt_id` int(10) NOT NULL AUTO_INCREMENT,
  `evt_name` varchar(50) NOT NULL,
  `evt_date` varchar(15) NOT NULL,
  `evt_detail` varchar(255) NOT NULL,
  `evt_type` tinyint(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`evt_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`evt_id`, `evt_name`, `evt_date`, `evt_detail`, `evt_type`) VALUES
(1, 'First event', '1377759600000', 'Please be registered.!!!!!', 1),
(2, 'sdfasf', '1376550000000', 'sdfasdf', 0),
(3, 'dsfaf', '1375945200000', 'dasfdasfads fsd fa\nfd\nsaf sda\nf a', 0),
(4, 'Events first', '1374044400000', 'sfsdf sdf sdf ', 0),
(5, 'Name is still vacant edit to supply name', '1378969200000', 'fvasdjlkfjlsdkjfdl sjfl jsd\nfsdflskdjfl jflsjflsdk fjlssd', 0),
(9, 'Opening party', '1375945200000', 'This is the opening party', 1),
(10, 'Past holiday with no any reason', '1376377200000', 'No description is given', 0),
(13, 'Name of the event is empty.', '1379055600000', 'asdfafasdfsf', 0),
(16, 'retsdfasdf', '1376463600000', 'asdafdsafsfasdfasfasdfsa', 0),
(17, 'Aaja sarkar sanga aako college ma', '1378710000000', 'fhgjhgjfhgj', 1),
(19, 'Ascol day', '1379660400000', 'THis  is to notify th', 1),
(20, 'hkhkhkjjkhk', '1378710000000', 'ljkljljl', 0);

-- --------------------------------------------------------

--
-- Table structure for table `marks`
--

CREATE TABLE IF NOT EXISTS `marks` (
  `mrk_id` int(5) NOT NULL AUTO_INCREMENT,
  `std_id` int(5) NOT NULL,
  `sub_id` int(5) NOT NULL,
  `mrk_obt` int(10) NOT NULL DEFAULT '40',
  PRIMARY KEY (`mrk_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Dumping data for table `marks`
--

INSERT INTO `marks` (`mrk_id`, `std_id`, `sub_id`, `mrk_obt`) VALUES
(5, 3, 2, 90),
(6, 3, 3, 50),
(7, 3, 4, 90),
(8, 3, 5, 90),
(9, 3, 6, 80),
(10, 3, 7, 90),
(11, 3, 8, 90),
(12, 3, 9, 99),
(15, 2, 10, 99),
(16, 2, 11, 99),
(17, 10, 12, 89),
(18, 522, 13, 80),
(19, 522, 14, 90),
(20, 12, 2, 22),
(21, 12, 3, 32),
(22, 12, 4, 34),
(23, 12, 5, 56),
(24, 12, 6, 55),
(25, 12, 7, 66),
(26, 12, 8, 65),
(27, 12, 9, 55),
(28, 4, 10, 90),
(29, 4, 11, 99);

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE IF NOT EXISTS `schedule` (
  `sh_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NOT NULL,
  `sh_start` varchar(25) NOT NULL,
  `sh_gap` varchar(25) NOT NULL,
  `teh_id` int(10) NOT NULL,
  `sub_id` int(11) NOT NULL,
  PRIMARY KEY (`sh_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `section`
--

CREATE TABLE IF NOT EXISTS `section` (
  `section_id` int(5) NOT NULL AUTO_INCREMENT,
  `section_name` varchar(20) NOT NULL,
  `class_id` int(5) NOT NULL,
  PRIMARY KEY (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `std_id` int(5) NOT NULL AUTO_INCREMENT,
  `std_roll` int(5) NOT NULL,
  `std_name` varchar(50) NOT NULL,
  `std_lname` varchar(25) NOT NULL,
  `class_id` varchar(20) NOT NULL,
  `std_parentName` varchar(25) NOT NULL,
  `std_parentPhone` varchar(15) NOT NULL,
  `std_Address` varchar(15) NOT NULL,
  `std_Dob` varchar(15) NOT NULL,
  `std_homePh` varchar(15) NOT NULL,
  `std_Sex` varchar(10) NOT NULL,
  `std_session` varchar(15) NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`std_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`std_id`, `std_roll`, `std_name`, `std_lname`, `class_id`, `std_parentName`, `std_parentPhone`, `std_Address`, `std_Dob`, `std_homePh`, `std_Sex`, `std_session`) VALUES
(1, 1, 'Rejish', 'Ghimre', 'Nursery', 'Abc', '0909090909', 'Jhor', '2050-10-12', '9090909090', 'Male', '0000-00-00'),
(2, 2, 'Suman', 'Adhikari', 'Nursery', 'Rajan', '1111111', 'Jhor', '9999-00-00', '0000-000-000', 'Male', '0000-00-00'),
(3, 3, 'Suman', 'Adhikari', 'K.G', 'Rajan', '1111111', 'Jhor', '9999-00-00', '0000-000-000', 'Male', '0000-00-00'),
(4, 4, 'Ram dai', 'fsafsdf', 'Nursery', 'dfsafa', '43523523', 'fadsfsdfassdf', '435235425', '3425235', 'Male', '0000-00-00'),
(5, 5, 'dfasdfasdf', 'sdfsafsaf', 'Nursery', 'sdfaf', 'sdfafsf', 'sdfsfdsf', 'ewrqr', 'qwerr', 'Female', '0000-00-00'),
(6, 10, 'Kale ', 'Adhikari', 'One', 'Gore PD cerma', '123456789', 'Jhor', '2050-2-25', '9876543210', 'Male', '0000-00-00'),
(7, 522, 'Om dai', 'Dhakal', 'Fifteen', 'bla', 'bla', 'bla', 'bla', 'bla', 'Male', '0000-00-00'),
(8, 12, 'Sita ram', 'sdfafs', 'K.G', 'sdfasfsdfdd', 'afasdfsdf', 'safsdfafs', 'sdffad', 'sdfafs', 'Female', '0000-00-00'),
(9, 55, 'Manish', 'Pandey', 'Forthsem', 'sdfsa', 'sdfa', 'sdsdafas', 'fdsdaf', 'dsfsaf', 'Male', '0000-00-00');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `sub_id` int(5) NOT NULL AUTO_INCREMENT,
  `sub_name` varchar(50) NOT NULL,
  `class_id` varchar(25) NOT NULL,
  `sub_publication` varchar(50) NOT NULL,
  `sub_edition` varchar(20) NOT NULL,
  `sub_authors` varchar(100) NOT NULL,
  `sub_teach` varchar(25) NOT NULL,
  `sub_fm` int(5) NOT NULL DEFAULT '100',
  `sub_pm` int(5) NOT NULL DEFAULT '40',
  PRIMARY KEY (`sub_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`sub_id`, `sub_name`, `class_id`, `sub_publication`, `sub_edition`, `sub_authors`, `sub_teach`, `sub_fm`, `sub_pm`) VALUES
(2, 'Software Engineering', 'K.G', 'JPT', '2nd', 'None', 'Suman dai Adhikari', 100, 40),
(3, 'Java Progarmming', 'K.G', 'None', 'fdsfs', 'No Authors', 'Suman dai Adhikari', 100, 40),
(4, 'Database Progarmming', 'K.G', 'None', 'fdsfs', 'No Authors', 'Suman dai Adhikari', 100, 40),
(5, 'PL/SQL Progarmming', 'K.G', 'None', 'fdsfs', 'No Authors', 'Suman dai Adhikari', 100, 40),
(6, 'C++ Progarmming', 'K.G', 'None', 'fdsfs', 'No Authors', 'Suman dai Adhikari', 100, 40),
(7, 'ASP.NET  Progarmming', 'K.G', 'None', 'fdsfs', 'No Authors', 'Suman dai Adhikari', 100, 40),
(8, 'PHP  Progarmming', 'K.G', 'None', 'fdsfs', 'No Authors', 'Suman dai Adhikari', 100, 40),
(9, 'Socail', 'K.G', 'dsfasfsasdafs', 'sdfsafsafaf', 'afdasdfsfsfsdf', 'Sita ram adhikari adhi', 100, 40),
(10, 'Atr', 'Nursery', 'Ekta', '3rd', 'No needed', 'Suman dai Adhikari', 100, 40),
(11, 'Science Exp', 'Nursery', 'Ekta', '3rd', 'No needed', 'Suman dai Adhikari', 100, 40),
(12, 'Gaming', 'One', 'Pearson', '2nd', 'Dietel and Dietel', 'Delli Pd.  Cerma', 100, 40),
(13, 'Oracle db', 'Fifteen', 'hkhjhjk', 'kjhkhjkh', 'Om dai', 'Sita ram adhikari adhi', 100, 40),
(14, 'bla bla bla', 'Fifteen', 'bla', 'bla', 'bla', 'Sita ram adhikari adhi', 100, 40),
(15, 'SAD', 'Forthsem', 'dsff', 'sdfas', 'gfadg', 'Hiryna Bstakoti', 100, 40);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE IF NOT EXISTS `teacher` (
  `teh_id` int(5) NOT NULL AUTO_INCREMENT,
  `teh_name` varchar(50) NOT NULL,
  `teh_lname` varchar(20) NOT NULL,
  `teh_enroll` varchar(20) NOT NULL,
  `teh_level` varchar(25) NOT NULL,
  `teh_sex` varchar(15) NOT NULL,
  PRIMARY KEY (`teh_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teh_id`, `teh_name`, `teh_lname`, `teh_enroll`, `teh_level`, `teh_sex`) VALUES
(2, 'Suman dai', 'Adhikari', '2010', 'Secondary', 'Male'),
(3, 'Delli Pd.', ' Cerma', '2070-12-30', 'Secondary', 'Male'),
(4, 'Hemanta ', 'G.C', '2068-12-30', 'Secondary', 'Male'),
(5, 'Sita ram adhikari', 'adhi', '324141413', 'Secondary', 'Male'),
(6, 'Hiryna', 'Bstakoti', 'sgfasgas', 'Secondary', 'Male');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `uid` int(5) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL,
  `upass` varchar(50) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`uid`, `uname`, `upass`) VALUES
(1, 'suman', 'test');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

/*
Navicat SQLite Data Transfer

Source Server         : housenumsys
Source Server Version : 30706
Source Host           : :0

Target Server Type    : SQLite
Target Server Version : 30706
File Encoding         : 65001

Date: 2014-04-25 00:56:48
*/

PRAGMA foreign_keys = OFF;

-- ----------------------------
-- Table structure for "main"."house"
-- ----------------------------
DROP TABLE "main"."house";
CREATE TABLE "house" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT,
"houseno"  INTEGER,
"lganame"  TEXT,
"district"  TEXT,
"ward"  TEXT,
"street"  TEXT,
"holdername"  TEXT,
"holderaddress"  TEXT,
"mobileno"  TEXT,
"nationality"  TEXT,
"landuse"  TEXT,
"date"  TEXT
);

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO "main"."house" VALUES (1, 1, 'Gwarzo', 'Getso', 'Zango', 'Bakin Kasuwa', 'Alhaji Muhammad', 'No 3 Zango Road, Getso', 2348069354904, 'Nigeria', 'Residential', '04/25/2014');

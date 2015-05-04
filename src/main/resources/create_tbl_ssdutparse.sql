create table `ssdutparse`(
	`id` int NOT NULL AUTO_INCREMENT,
	`sp_title` varchar(200) DEFAULT '',
	`sp_url` varchar(200) DEFAULT '',
	`sp_date` varchar(20) DEFAULT '',
	`sp_src` varchar(100) DEFAULT '',
	`sp_count` int DEFAULT 0,
	`sp_content` text,
	PRIMARY KEY (`id`)
)
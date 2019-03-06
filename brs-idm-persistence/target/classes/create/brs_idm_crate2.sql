CREATE TABLE `brs_idm_bytearray` (
  `id_` varchar(64) NOT NULL,
  `name_` varchar(255) DEFAULT NULL,
  `rev_` int(11) DEFAULT NULL,
  `bytes_` longblob,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `brs_idm_membership` (
  `user_id_` varchar(64) NOT NULL,
  `role_id_` varchar(64) NOT NULL,
  PRIMARY KEY (`user_id_`,`role_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `brs_idm_priv` (
  `id_` varchar(64) NOT NULL,
  `name_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `brs_idm_priv_mapping` (
  `id_` int(11) NOT NULL AUTO_INCREMENT,
  `priv_id_` varchar(64) NOT NULL,
  `user_id_` varchar(64) DEFAULT NULL,
  `role_id_` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `brs_idm_role` (
  `id_` varchar(64) NOT NULL,
  `name_` varchar(255) DEFAULT NULL,
  `type_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `brs_idm_user` (
  `id_` varchar(64) NOT NULL,
  `first_` varchar(255) DEFAULT NULL,
  `last_` varchar(255) DEFAULT NULL,
  `email_` varchar(255) DEFAULT NULL,
  `pwd_` varchar(255) DEFAULT NULL,
  `picture_id_` varchar(64) DEFAULT NULL,
  `display_name_` varchar(255) DEFAULT NULL,
  `type_` varchar(45) DEFAULT NULL,
  `login_ip_` varchar(255) DEFAULT NULL,
  `login_status_` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

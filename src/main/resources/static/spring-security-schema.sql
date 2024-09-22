USE `db_books`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

-- *************************
-- Estructura de tabla `users`
-- *************************

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ***************************
-- insertando datos en `users`
-- Considere el prefijo {noop} cuando la contraseña no esta encriptada y {bcrypt} encriptado con algoritmo bcrypt
-- admin/admin123 - lectura/lectura123
-- ***************************

INSERT INTO `users`
VALUES
('admin','{bcrypt}$2a$10$6XjdFFp6mkZyujPXr1GjuO1tgqTmiqUhWLcl9F9TniihT7hfNlxi.',1),
('lectura','{bcrypt}$2a$10$zI0k5mkUog/.tFQU0Ixr0OZq1xE4bAde7CRIbrBZLDZDDmvu1VPUm',1);


-- *********************************
-- Estructura de tabla `authorities`
-- *********************************

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ***********************************
-- Insertando datos en `authorities`
-- **********************************

INSERT INTO `authorities`
VALUES
('admin','ROLE_ADMINISTRADOR'),
('admin','ROLE_LECTURA'),
('lectura','ROLE_LECTURA');



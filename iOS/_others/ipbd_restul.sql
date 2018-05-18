CREATE TABLE `Spectacol` 
( `id` INT NOT NULL AUTO_INCREMENT , 
  `nume` VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL , 
  `descriere` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
  `trailer` VARCHAR(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL , 
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;



CREATE TABLE `PozeSpectacol` 
( `id` INT NOT NULL AUTO_INCREMENT , 
  `id_spectacol` INT NOT NULL ,
  `locatiepoza` VARCHAR(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL , 
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_spectacol`)
  REFERENCES `Spectacol`(id)
  ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;


CREATE TABLE `Actori`
( `id` INT NOT NULL AUTO_INCREMENT ,
  `nume` VARCHAR(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
  `prenume` VARCHAR(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
  `poza` VARCHAR(140) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
  PRIMARY KEY(`id`)
  ) ENGINE = InnoDB CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;
  
  
CREATE TABLE `Critici`
( `id` INT NOT NULL AUTO_INCREMENT ,
  `id_spectacol` INT NOT NULL, 
  `nume_critic` VARCHAR(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL , 
  `text` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
  PRIMARY KEY(`id`),
  FOREIGN KEY (`id_spectacol`)
  REFERENCES `Spectacol`(id)
  ON DELETE CASCADE ON UPDATE CASCADE
  ) ENGINE = InnoDB CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;
  
  
CREATE TABLE `Joaca`
( `id_spectacol` INT NOT NULL,
  `id_actor` INT NOT NULL,
  FOREIGN KEY (`id_spectacol`)
  REFERENCES `Spectacol`(id)
  ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`id_actor`)
  REFERENCES `Actori`(id)
  ON DELETE CASCADE ON UPDATE CASCADE
  ) ENGINE = InnoDB CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;
  
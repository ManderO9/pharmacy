
create database if not exists tpAcsi;

use tpAcsi;


CREATE TABLE users (
    idUser INT AUTO_INCREMENT,
    role ENUM('admin', 'client'),
    nom VARCHAR(50),
    motDePass VARCHAR(50),
    email VARCHAR(50),
    PRIMARY KEY (idUser)
);

CREATE TABLE commands (
    idCommande INT AUTO_INCREMENT,
    dateCreation DATE,
    etat ENUM('unsaved', 'saved', 'confirmed'),
    idUser INT,
    FOREIGN KEY (idUser)
        REFERENCES users (idUser),
    PRIMARY KEY (idCommande)
);
 
CREATE TABLE medicaments (
    idMedicament INT AUTO_INCREMENT,
    PRIMARY KEY (idMedicament),
    nom VARCHAR(50),
    dateFabrication DATE,
    dateExpiration DATE,
    constituant VARCHAR(500),
    commentPrendre VARCHAR(50),
    prix FLOAT
);





insert into medicaments (idMedicament, nom, dateFabrication, dateExpiration, constituant, commentPrendre, prix) values( 1, "nom","2015-10-1", "2020-1-2","constituant" , "commentPrendre",124.12);
insert into medicaments (idMedicament, nom, dateFabrication, dateExpiration, constituant, commentPrendre, prix) values( 2, "nom","2015-10-1", "2020-1-2","constituant" , "commentPrendre",124.12);
insert into medicaments (idMedicament, nom, dateFabrication, dateExpiration, constituant, commentPrendre, prix) values( 3, "nom","2015-10-1", "2020-1-2","constituant" , "commentPrendre",124.12);
insert into medicaments (idMedicament, nom, dateFabrication, dateExpiration, constituant, commentPrendre, prix) values( 4, "nom","2015-10-1", "2020-1-2","constituant" , "commentPrendre",124.12);

insert into users (idUser, role, nom, motDePass, email) values(1, "client", "name", "password", "email@test.com");
insert into users (idUser, role, nom, motDePass, email) values(2, "client", "name", "password", "email@test.com");
insert into users (idUser, role, nom, motDePass, email) values(3, "client", "name", "password", "email@test.com");

insert into commands (idCommande, dateCreation, etat, idUser) values(1,"2022-10-11","saved",1);
insert into commands (idCommande, dateCreation, etat, idUser) values(2,"2022-10-11","saved",1);
insert into commands (idCommande, dateCreation, etat, idUser) values(3,"2022-10-11","saved",1);
insert into commands (idCommande, dateCreation, etat, idUser) values(4,"2022-10-11","saved",1);
insert into commands (idCommande, dateCreation, etat, idUser) values(5,"2022-10-11","saved",2);
insert into commands (idCommande, dateCreation, etat, idUser) values(6,"2022-10-11","saved",2);


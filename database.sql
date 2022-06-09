

create database if not exists tpAcsi;

use tpAcsi;


CREATE TABLE commands (
    idCommande INT AUTO_INCREMENT,
    dateCreation DATE,
    etat ENUM('unsaved', 'saved', 'confirmed'),
    PRIMARY KEY (idCommande)
);
 
CREATE TABLE users (
    idUser INT AUTO_INCREMENT,
    role ENUM('admin', 'client'),
    nom VARCHAR(50),
    motDePass VARCHAR(50),
    email VARCHAR(50),
    PRIMARY KEY (idUser)
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



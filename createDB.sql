CREATE TABLE Proj_Client (
    id_cli INTEGER NOT NULL,
    nom_cli VARCHAR2(100) NOT NULL,
    ville_cli VARCHAR2(100) NOT NULL,
    tel_cli VARCHAR2(20) NOT NULL,
    PRIMARY KEY (id_cli)
);

CREATE TABLE Proj_Projet (
    id_proj INTEGER NOT NULL,
    titre_proj VARCHAR2(100) NOT NULL,
    client_proj INTEGER NOT NULL,
    date_debut DATE NOT NULL,
    date_butoir DATE NOT NULL,
    PRIMARY KEY (id_proj),
    UNIQUE (titre_proj)
);

CREATE TABLE Proj_Employe (
    id_emp INTEGER NOT NULL,
    nom_emp VARCHAR2(100) NOT NULL,
    prenom_emp VARCHAR2(100) NOT NULL,
    gsm_emp VARCHAR2(15) NOT NULL,
    email_emp VARCHAR2(100) NOT NULL,
    PRIMARY KEY (id_emp)
);

CREATE TABLE Proj_Travail (
    id_proj INTEGER NOT NULL,
    id_emp INTEGER NOT NULL,
    date_engagement INTEGER NOT NULL,
    pourcentage INTEGER NOT NULL,
    CONSTRAINT PK_travail PRIMARY KEY (id_proj, id_emp)
);

CREATE TABLE Proj_Discipline (
    id_disc INTEGER NOT NULL,
    nom_disc VARCHAR2(150) NOT NULL,
    PRIMARY KEY (id_disc)
);

CREATE TABLE Proj_Temps (
    id_proj INTEGER NOT NULL,
    id_disc INTEGER NOT NULL,
    nb_jh INTEGER NOT NULL,
CONSTRAINT PK_temps PRIMARY KEY (id_proj, id_disc)
);

CREATE TABLE Proj_Niveau (
    id_niv INTEGER NOT NULL,
    desc_niv VARCHAR2(150) NOT NULL,
    PRIMARY KEY (id_niv),
    UNIQUE (desc_niv)
);

CREATE TABLE Proj_Competence (
    id_emp INTEGER NOT NULL,
    id_disc INTEGER NOT NULL,
    id_niv INTEGER NOT NULL,
CONSTRAINT PK_competence PRIMARY KEY (id_emp, id_disc)
);

ALTER TABLE Proj_Projet ADD FOREIGN KEY (client_proj) REFERENCES Proj_Client(id_cli);
ALTER TABLE Proj_Travail ADD FOREIGN KEY (id_proj) REFERENCES Proj_Projet(id_proj);
ALTER TABLE Proj_Temps ADD FOREIGN KEY (id_proj) REFERENCES Proj_Projet(id_proj);
ALTER TABLE Proj_Temps ADD FOREIGN KEY (id_disc) REFERENCES Proj_Discipline(id_disc);
ALTER TABLE Proj_Competence ADD FOREIGN KEY (id_emp) REFERENCES Proj_Employe(id_emp);
ALTER TABLE Proj_Competence ADD FOREIGN KEY (id_disc) REFERENCES Proj_Discipline(id_disc);

DROP SCHEMA IF EXISTS ekonomskifakultet;
CREATE SCHEMA ekonomskifakultet DEFAULT CHARACTER SET utf8;
USE ekonomskifakultet;

CREATE TABLE studenti (
	student_id INT AUTO_INCREMENT,
	indeks VARCHAR(20) UNIQUE NOT NULL,
	ime VARCHAR(20) NOT NULL,
	prezime VARCHAR(20) NOT NULL,
	grad VARCHAR(20) NOT NULL,
	PRIMARY KEY(student_id) 
);

CREATE TABLE profesori (
	profesor_id INT AUTO_INCREMENT,
	ime VARCHAR(20) NOT NULL,
	prezime VARCHAR(20) NOT NULL,
	zvanje VARCHAR(20) NOT NULL,
	PRIMARY KEY(profesor_id)
);

CREATE TABLE predmeti (
	predmet_id INT AUTO_INCREMENT,
	naziv VARCHAR(20) NOT NULL,
	PRIMARY KEY(predmet_id)
);

CREATE TABLE ispitni_rokovi (
	rok_id INT AUTO_INCREMENT,
	naziv VARCHAR(20) NOT NULL,
	pocetak DATE NOT NULL,
	kraj DATE NOT NULL,
	PRIMARY KEY(rok_id)
);

CREATE TABLE predaje (
	profesor_id INT NOT NULL,
	predmet_id INT NOT NULL,
	PRIMARY KEY(profesor_id, predmet_id),
	
	FOREIGN KEY (profesor_id) REFERENCES profesori(profesor_id)
	    ON DELETE RESTRICT,
	FOREIGN KEY (predmet_id) REFERENCES predmeti(predmet_id)
	    ON DELETE RESTRICT
);
	
CREATE TABLE pohadja (
	student_id INT NOT NULL,
	predmet_id INT NOT NULL,
	PRIMARY KEY(student_id, predmet_id),
	
	FOREIGN KEY (student_id) REFERENCES studenti(student_id)
		ON DELETE RESTRICT,
	FOREIGN KEY (predmet_id) REFERENCES predmeti(predmet_id)
		ON DELETE RESTRICT
); 
	
CREATE TABLE ispitne_prijave (
	student_id INT NOT NULL,
	predmet_id INT NOT NULL,
	rok_id INT NOT NULL,
	teorija INT,
	zadaci INT,
	PRIMARY KEY (student_id, predmet_id, rok_id),
	
	FOREIGN KEY (student_id) REFERENCES studenti(student_id)
		ON DELETE RESTRICT,
	FOREIGN KEY (predmet_id) REFERENCES predmeti(predmet_id)
		ON DELETE RESTRICT,                        
	FOREIGN KEY (rok_id) REFERENCES ispitni_rokovi(rok_id)
		ON DELETE RESTRICT
);

INSERT INTO studenti (indeks, ime, prezime, grad) VALUES ('E 1/12', 'Petar', 'Mihajlovic', 'Novi Sad');
INSERT INTO studenti (indeks, ime, prezime, grad) VALUES ('E 2/12', 'Dejan', 'Ivanovic', 'Loznica');
INSERT INTO studenti (indeks, ime, prezime, grad) VALUES ('E 3/12', 'Zoran', 'Kovacevic', 'Indjija');
INSERT INTO studenti (indeks, ime, prezime, grad) VALUES ('E 4/12', 'Marko', 'Popovic', 'Novi Sad');

INSERT INTO profesori (ime, prezime, zvanje) VALUES ('Marko', 'Popovic', 'Docent');
INSERT INTO profesori (ime, prezime, zvanje) VALUES ('Milan', 'Janjic', 'Docent');
INSERT INTO profesori (ime, prezime, zvanje) VALUES ('Zeljko', 'Djuric', 'Asistent');
	
INSERT INTO predmeti (naziv) VALUES ('Algebra');
INSERT INTO predmeti (naziv) VALUES ('Matematika');
INSERT INTO predmeti (naziv) VALUES ('Engleski jezik');
INSERT INTO predmeti (naziv) VALUES ('Francuski jezik');
-- Format u kojem SUBP prima datum se moze konfigurisati. Ovo je default format (godina - mesec - dan) za MySQL server. Datumi se u SQLu prosledjuju kao stringovi u odgovarajucem formatu
INSERT INTO ispitni_rokovi (naziv, pocetak, kraj) VALUES ('Januarski', '2015-01-15', '2015-01-29');
INSERT INTO ispitni_rokovi (naziv, pocetak, kraj) VALUES ('Februarski', '2015-02-01', '2015-02-14');

INSERT INTO predaje VALUES (1, 1); -- Ovako je napisano zbog jednostavnosti, ali je ovo potencijalna greska. Pretpostavlja se da ce prvom predmetu biti dodeljen identifikator 1, a ne mora to uvek da bude slucaj obzirom da SUBP odredjuje tu vrednost (npr. ako vec ima 5 predmeta u bazi podataka, novi predmet ce dobiti id 6)
INSERT INTO predaje VALUES (1, 2);
INSERT INTO predaje VALUES (2, 2);
INSERT INTO predaje VALUES (3, 1);
	
INSERT INTO pohadja VALUES (1, 1);
INSERT INTO pohadja VALUES (1, 2);
INSERT INTO pohadja VALUES (2, 1);
INSERT INTO pohadja VALUES (3, 1);

INSERT INTO ispitne_prijave VALUES (1, 1, 1, 20, 70);
INSERT INTO ispitne_prijave VALUES (1, 2, 1, 10, 54);
INSERT INTO ispitne_prijave VALUES (2, 1, 1, 10, 10);
INSERT INTO ispitne_prijave VALUES (2, 1, 2, 40, 30);
INSERT INTO ispitne_prijave VALUES (3, 1, 1, 10, 30);
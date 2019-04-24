select * from medewerkers;

ALTER TABLE medewerkers ADD geslacht varchar(3) ADD CONSTRAINT typegeslacht CHECK (geslacht LIKE '[M,V]');

select * from afdelingen;

-- afdelingsnummer genereren
CREATE SEQUENCE test
START WITH 50
INCREMENT BY 10
MAXVALUE 1000
CYCLE;

-- ALTER NUMBER
ALTER TABLE AFDELINGEN MODIFY ANR number(4,0);

-- afdelingen toevoegen
INSERT INTO afdelingen (ANR, NAAM, LOCATIE, HOOFD) VALUES (test.nextval, 'CONTACT', 'HILVERSUM', 7782);
INSERT INTO afdelingen (ANR, NAAM, LOCATIE, HOOFD) VALUES (test.nextval, 'ICT', 'AMSTERDAM', 7782);

CREATE TABLE his_adressentabel(
    POSTCODE varchar(255) NULL,
    HUISNUMMER INT,
    INGANGSDATUM DATE NOT NULL,
    EINDDATUM DATE,
    TELEFOON INT UNIQUE,
    MED_MNR INT NOT NULL,
    FOREIGN KEY (MED_MNR) REFERENCES MEDEWERKERS(mnr),
    CONSTRAINT PK_address PRIMARY KEY (POSTCODE,HUISNUMMER,INGANGSDATUM)
);

ALTER TABLE his_adressentabel ADD CONSTRAINT einddatum CHECK ( EINDDATUM >= INGANGSDATUM ) ADD CONSTRAINT postcode CHECK ( POSTCODE LIKE '[0-9{4},A-Z{2}]' );

SELECT * FROM his_adressentabel;
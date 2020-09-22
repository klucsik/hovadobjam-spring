INSERT INTO PUBLIC.MATERIAL (ID, NAME)
VALUES (HIBERNATE_SEQUENCE.nextval, 'Komposztfile');

INSERT INTO PUBLIC.TRASH (ID, NAME, MATERIAL_ID)
VALUES (HIBERNATE_SEQUENCE.nextval, 'Krumplifile', (select ID from MATERIAL where MATERIAL.NAME like 'Komposztfile')),
       (HIBERNATE_SEQUENCE.nextval, 'Almafile', (select ID from MATERIAL where MATERIAL.NAME like 'Komposztfile')),
       (HIBERNATE_SEQUENCE.nextval, 'Burgonyafile', NULL);


INSERT INTO PUBLIC.MATERIAL (ID, NAME)
VALUES (HIBERNATE_SEQUENCE.nextval, 'Komposztfile');

INSERT INTO PUBLIC.TRASH (ID, NAME, MATERIAL_ID)
VALUES (HIBERNATE_SEQUENCE.nextval, 'Krumplifile', (select ID from MATERIAL where MATERIAL.NAME like 'Komposztfile')),
       (HIBERNATE_SEQUENCE.nextval, 'Almafile', (select ID from MATERIAL where MATERIAL.NAME like 'Komposztfile')),
       (HIBERNATE_SEQUENCE.nextval, 'Burgonyafile', NULL);

INSERT INTO PUBLIC.USER (ID, USERNAME, PASSWORD)
VALUES (HIBERNATE_SEQUENCE.nextval, 'admin', '$2a$10$sHN77eCJzUWbTC7hyDWJ/OBCf5kcPXYZ7NB9DIASYgF.w4XVRGN1e');


ALTER TABLE member ALTER COLUMN seq_member RESTART WITH 5;

INSERT INTO member(seq_member, email, phone_number, name)
VALUES(2, 'test02@gmail.com', '010-0000-0000', 'test02');

INSERT INTO member(seq_member, email, phone_number, name)
VALUES(3, 'test03@gmail.com', '010-0000-0000', 'test03');

INSERT INTO member(seq_member, email, phone_number, name)
VALUES(4, 'test04@gmail.com', '010-0000-0000', 'test04');
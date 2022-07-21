DROP TABLE IF EXISTS garlic_quotation CASCADE;
CREATE TABLE garlic_quotation
(
   id VARCHAR(1)   NOT NULL,
   date TIMESTAMP,
   n int,
   avg decimal(9,3),
   quots VARCHAR(30),
);
ALTER TABLE garlic_quotation add CONSTRAINT pk_garlic_quotation PRIMARY KEY(id);

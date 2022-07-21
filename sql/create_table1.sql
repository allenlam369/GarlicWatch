CREATE TABLE quotation (
	id int8 NOT NULL,
	"date" timestamp NULL,
	n int4 NULL,
	avg numeric(9, 3) NULL,
	"quote" varchar(50) NULL,
	CONSTRAINT pk_quotation PRIMARY KEY (id)
	UNIQUE KEY `date` (`date`)
);



    create table public.quotation (
       id int8 not null,
        date date,
        n int4,
        avg float8,
        quote varchar(255),
        primary key (id)
    )
	
	    create table garlic.quotation (
       id int8 not null,
        date date,
        n int4,
        avg float8,
        quote varchar(50),
        primary key (id)
    )
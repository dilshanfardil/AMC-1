CREATE TABLE users(
	user_id character varying(10) NOT NULL,
	user_name character varying(40),
	password character varying(12),
	name character varying(12),
	gender character varying(6),
	phone integer,
	nic integer,
	email character varying(50),
	address character varying(100),
	role character varying(15),
		CONSTRAINT user_pkey PRIMARY KEY (user_id)
	);
	
CREATE TABLE appointments(
 	app_id character varying(10) NOT NULL,
	cus_id character varying(10) NOT NULL,
	doc_id character varying(10) NOT NULL,
	app_date date NOT NULL,
	duration_left integer,
	medicine character varying(500),
	feedback character varying(250),
	comment character varying(250),
	charge double precision,
	CONSTRAINT app_pkey PRIMARY KEY (app_id)
	);
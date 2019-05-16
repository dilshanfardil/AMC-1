CREATE TABLE users(
	reg_no integer NOT NULL,
	user_name character varying(40),
	password character varying(12),
	role character varying(15),
		CONSTRAINT user_pkey PRIMARY KEY (reg_no)
	);
	
CREATE TABLE appointments(
  app_id SERIAL,
  user_id integer,
  doc_id integer,
  app_date date, 
  duration_left integer,
  charge double precision,
  CONSTRAINT app_pkey PRIMARY KEY (app_id));
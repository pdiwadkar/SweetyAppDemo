--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: userglucosereading; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE userglucosereading (
    glucoselevel bigint,
    loginid integer NOT NULL,
    id integer NOT NULL,
    recordingtime timestamp without time zone
);


ALTER TABLE public.userglucosereading OWNER TO postgres;

--
-- Name: userglucosereading_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE userglucosereading_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.userglucosereading_id_seq OWNER TO postgres;

--
-- Name: userglucosereading_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE userglucosereading_id_seq OWNED BY userglucosereading.id;


SET default_with_oids = true;

--
-- Name: userinfo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE userinfo (
    loginname text NOT NULL,
    password text NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.userinfo OWNER TO postgres;

--
-- Name: TABLE userinfo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE userinfo IS 'User login information.';


--
-- Name: COLUMN userinfo.loginname; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN userinfo.loginname IS 'LoginName';


--
-- Name: userinfo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE userinfo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.userinfo_id_seq OWNER TO postgres;

--
-- Name: userinfo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE userinfo_id_seq OWNED BY userinfo.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userglucosereading ALTER COLUMN id SET DEFAULT nextval('userglucosereading_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userinfo ALTER COLUMN id SET DEFAULT nextval('userinfo_id_seq'::regclass);


--
-- Data for Name: userglucosereading; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY userglucosereading (glucoselevel, loginid, id, recordingtime) FROM stdin;
177	8	264	2017-04-27 22:51:09.209
616	8	265	2017-04-27 22:51:14.982
162	8	266	2017-04-27 22:51:20.623
818	8	267	2017-04-27 22:51:26.345
\.


--
-- Name: userglucosereading_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('userglucosereading_id_seq', 267, true);


--
-- Data for Name: userinfo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY userinfo (loginname, password, id) FROM stdin;
DemoUser1	DemoUser1	8
DemoUser2	DemoUser2	9
\.


--
-- Name: userinfo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('userinfo_id_seq', 9, true);


--
-- Name: LoginID; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY userinfo
    ADD CONSTRAINT "LoginID" UNIQUE (id);


--
-- Name: UserName; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY userinfo
    ADD CONSTRAINT "UserName" PRIMARY KEY (loginname);


--
-- Name: userglucosereading_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY userglucosereading
    ADD CONSTRAINT userglucosereading_pkey PRIMARY KEY (id);


--
-- Name: fk_loginid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY userglucosereading
    ADD CONSTRAINT fk_loginid FOREIGN KEY (loginid) REFERENCES userinfo(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: userinfo; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE userinfo FROM PUBLIC;
REVOKE ALL ON TABLE userinfo FROM postgres;
GRANT ALL ON TABLE userinfo TO postgres;
GRANT ALL ON TABLE userinfo TO PUBLIC;


--
-- PostgreSQL database dump complete
--


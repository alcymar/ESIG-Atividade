-- This script was generated by the ERD tool in pgAdmin 4.
-- Please log an issue at https://github.com/pgadmin-org/pgadmin4/issues/new/choose if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE IF NOT EXISTS public."Cargo"
(
    "ID" bigserial NOT NULL,
    "Nome_Cargo" character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT "Cargo_pkey" PRIMARY KEY ("ID")
);

CREATE TABLE IF NOT EXISTS public."Cargo_Vencimentos"
(
    "ID" bigserial NOT NULL,
    "Cargo_ID" bigserial NOT NULL,
    "Vencimento_ID" bigserial NOT NULL,
    CONSTRAINT "Cargo_Vencimentos_pkey" PRIMARY KEY ("ID")
);

CREATE TABLE IF NOT EXISTS public."Pessoa"
(
    "ID" bigserial NOT NULL,
    "Nome" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    "Cidade" character varying(50) COLLATE pg_catalog."default" NOT NULL,
	"Email" character varying(50) COLLATE pg_catalog."default",
    "CEP" character varying(50) COLLATE pg_catalog."default",
    "Endereco" character varying(100) COLLATE pg_catalog."default",
    "Pais" character varying(50) COLLATE pg_catalog."default",
    "Usuario" character varying(30) COLLATE pg_catalog."default",
	"Telefone" character varying(50) COLLATE pg_catalog."default",
    "Data_Nascimento" timestamp(6) with time zone,
	"Cargo_ID" bigserial,
    CONSTRAINT "Pessoa_pkey" PRIMARY KEY ("ID")
);

CREATE TABLE IF NOT EXISTS public."Vencimentos"
(
    "ID" bigserial NOT NULL,
    "Descricao" character varying(30) COLLATE pg_catalog."default" NOT NULL,
    "Valor" real,
    "Tipo" character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Vencimentos_pkey" PRIMARY KEY ("ID")
);

CREATE TABLE IF NOT EXISTS public.pessoa_salario_consolidado
(
    pessoa_id integer NOT NULL,
    nome_pessoa character varying(50) COLLATE pg_catalog."default" NOT NULL,
    nome_cargo character varying(50) COLLATE pg_catalog."default" NOT NULL,
    salario numeric(7, 0) NOT NULL,
    CONSTRAINT pessoa_salario_consolidado_pkey PRIMARY KEY (pessoa_id)
);

ALTER TABLE IF EXISTS public."Cargo_Vencimentos"
    ADD CONSTRAINT "Cargo_Fkey" FOREIGN KEY ("Cargo_ID")
    REFERENCES public."Cargo" ("ID") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public."Cargo_Vencimentos"
    ADD CONSTRAINT "Vencimento_FKey" FOREIGN KEY ("Vencimento_ID")
    REFERENCES public."Vencimentos" ("ID") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public."Pessoa"
    ADD CONSTRAINT "PessoaCargoFKey" FOREIGN KEY ("Cargo_ID")
    REFERENCES public."Cargo" ("ID") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;




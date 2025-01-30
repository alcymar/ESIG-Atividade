select p."Nome", c."Nome_Cargo", v."Valor", v."Tipo" from public."Pessoa" p
inner join public."Cargo" c on p."Cargo_ID" = c."ID"
inner join public."Cargo_Vencimentos" cv on c."ID" = cv."Cargo_ID"
inner join public."Vencimentos" v on cv."Vencimento_ID" = v."ID" 
where  cv."Cargo_ID" = '2' and p."Nome" = 'Daniel Cunha';

select * from public."pessoa_salario_consolidado"
delete from public."pessoa_salario_consolidado"

select * from public."Pessoa" p
select * from public."Cargo" c
select * from public."Cargo_Vencimentos" cv
select * from public."Vencimentos" v
select * from public."pessoa_salario_consolidado" psc

ALTER DATABASE "ESIG" SET datestyle TO ISO, MDY;

show datestyle

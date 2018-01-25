# API REST Cidades

Requisitos:
* Java: **1.8.0**
* WildFly **10.x**
* PostgreSQL **9.6**

Configuração PostgreSQL:
* Database: desafio-java
* Username: postgres
* Password: postgres
* Host: localhost
* Port: 5432

## Carregando banco de dados

Para importar as informações do arquivo CSV é necessário executar o script abaixo dentro do PostgreSQL.

		CREATE TABLE cidade
		(
		    ibge_id integer NOT NULL,
		    uf char(2),
		    name varchar,
		    capital boolean,
		    lon double precision,
		    lat double precision,
		    no_accents varchar,
		    alternative_names varchar,
		    microregion varchar,
		    mesoregion varchar 
		);
		
		COPY cidade(ibge_id, uf, name, capital, lon, lat,
             no_accents, alternative_names, microregion, mesoregion)
		FROM '<CAMINHO_DO_ARQUIVO>/Travalho Java - Cidades.csv' DELIMITER ',' CSV HEADER;
		
## Como utilizar a API?
    
    
Retorna informações da cidade de acordo com o IBGE informado.
    
    <host>:<port>/api-cidades/rest/cidade/<ibge-id>

Utilizando método POST, grava uma nova cidade.
Para deletar uma cidade, basta utilizar o método DELETE.
    
    <host>:<port>/api-cidades/rest/cidade

Retorna a lista de todas capitais cadastradas.

    <host>:<port>/api-cidades/rest/cidade/capitais

Retorna a sigla do estado com o seu total de cidades.
    
    <host>:<port>/api-cidades/rest/cidade/total-por-estados

Retorna o nome de todas cidades do estado passado como parâmetro.

    <host>:<port>/api-cidades/rest/cidade/cidades-por-estado/<uf>


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
    
    
Utilizando o método GET, retorna informações da cidade de acordo com o IBGE informado.
Para deletar uma cidade, basta utilizar o método DELETE.
    
    <host>:<port>/api-cidades/rest/cidade/<ibge-id>

Utilizando método POST, grava uma nova cidade.
    
    <host>:<port>/api-cidades/rest/cidade

Retorna a lista de todas capitais cadastradas.

    <host>:<port>/api-cidades/rest/capitais

Retorna a sigla do estado com o seu total de cidades.
    
    <host>:<port>/api-cidades/rest/total-por-estados

Retorna o nome de todas cidades do estado passado como parâmetro.

    <host>:<port>/api-cidades/rest/cidades-por-estado/<uf>

Retorna o total de registros.

    <host>:<port>/api-cidades/rest/total-de-registros
    
Retorna os estados com maior e menor número de cidades.

    <host>:<port>/api-cidades/rest/estados-extremos
    
Retorna quilometragem e nome das duas cidades mais distantes.

    <host>:<port>/api-cidades/rest/cidades-mais-distantes
    
### Ainda não implementado:

Retorna consulta de coluna específica com filtro.

	<host>:<port>/api-cidades/rest/consulta/<coluna>/<filtro>
	
Retorna registros totais distintos da coluna. 
	
	<host>:<port>/api-cidades/rest/totais/<coluna>

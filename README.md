# Poggi_Alessandro_Test
Assessment Test SOA


Premessa:

Il progetto è stato sviluppato su Windows 10 /Linux Mint 15 (versione iniziale) e testato su Windows7/10  con browser Google Chrome e Microsoft Edge sia in modalità normale che in incognito.
Il progetto è stato compilato con versione 1.8 di java testato ed eseguito in modalità developer su server locale Apache tomcat v8.0
Come database è stato utilizzato mysql57
Istruzioni:


* Importare  il progetto e buildarlo con maven
* eseguire la query create_users.sql all'interno della directory query
* configurare opportunamente il file config.properties nella directory src/main/resources per l'accesso al proprio db
* (facoltativo) configurare il file di log log4j2.config nella directory src/main/resources
* Per potersi autenticare al servizio è necessario popolare la tabella degli utenti che avrà una struttura a colonne user_id, email,username,password. le chiavi di autenticazione al servizio sono email e password (la password su db non è stata criptata). E' possibile inserire a mano questi dati, oppure utilizzando uno unit Test (TestDAO) di prova che popola automaticamente la tabella seguendo questo standard:
username: utente1@faac.com password:utente1 ... username: utenteN@faac.com password:utenteN    dove N sta per numero progressivo
* Deployare il war del progetto 

Il progetto sarà accessibile all'indirizzo locale (o di macchina):

http://localhost:8080/FaacSoa/

per la parte di front-end / rest.

Per la parte ws-soap sarà possibile accedere all'indirizzo :

http://localhost:8080/FaacSoa/ws/users.wsdl

Utilizzando uno strumento come soap ui sarà facilmente possibile importare il progetto e testare la chiamata

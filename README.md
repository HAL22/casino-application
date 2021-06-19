# casino-application
<b>You have to create a database called casino before running the application</b>

1. Systems Requirments
- Look at the pom.xml file
- For the application and testing look at the respective
 application.properties files
- Java 8
- Spring Boot 2.5.1
- Postgress DB download for Mac from https://postgresapp.com
P.S. I used the H2 database for testing only
2. Application endpoints
- Balance: Get api/v1/casino/transaction/balance/{playerId}/{transactionId}
- Deposit: Put api/v1/casino/transaction/deposit/{playerId}/{transactionId}
Parameter = amount 
- Deduct: Put api/v1/casino/transaction/deduct/{playerId}/{transactionId}
Parameter = amount
- 10 or less transactions: Put api/v1/casino/player/transactions
Parameter = password(= swordfish) and username(= user). Basic Auth security was used.
3. Databases
- Postgress db was used for the application. Settings are on the application.properties. The db runs fresh everytime the application is executed
- H2 db was used for testing.Settings are on test/application.properties

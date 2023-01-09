# Microservices

What is this app?

I built this app using a microservice style layout. It has a service that allows you to use a rest client, such as Postman, to send JSON to the exposed endpoint. This will save a customer profile to a database, record and check for duplicate entries in a seperate database, and throw an exception is one is found. The PostgreSQL databases are spun up using a Docker image. The eureka client enables the customer microservice to communicate with the fraud checker, as well as load balance requests in a round-robin fashion.

The customer service is made up of :

1. Customer - The model that is manipulated by the customer service class. It is mapped to its own database. Has fields that one would commonly find. I.E. first name, last name, email.

2. Customer service - Manipulates the customer model via the registerCustomer method. It builds a model using the info from a customer registration request. It then saves the model to it's own database and checks for duplicate registrations via the fraud microservice.

3. Customer registration request -  This class describes the fields used to build the customer model by the service class.

4. Customer Controller - This is the endpoint for the service. The server is run on a local machine. The Customer Service class is injected here in order to have access to it's registration method. It takes in a request body in the shape of the Customer Registration Request class. It then uses this information to build a customer model, saves it to the database, checks for duplicate entries, and throws an exception if one is found.

5. Customer Repository -  Extends the JPA repository in order to have access to its methods as well as being able to map the customer model to the database.



Fraud Service:

1. Fraud Check History model - This class is mapped to a database. Contains Id, customer Id, isFraudster (boolean), and createdAt(local date & time) fields.

2. Fraud Check Repository: Extends the JPA repository for the same reasons as the customer repository.

3. Fraud Check response -  Is used by the controller to return true or false

4. Fraud Check Service - Builds the model using the customer Id from the customer service, saves it to the repository, and outputs something to the customer microservice.

5. Fraud Controller - The exposed endpoint for this service. GET requests to the endpoint sent by Service are mapped to the isFraudster method of this class. The Fraud check service is injected into this class in order to access is method to start the entire process.


# Product Microservice
## Description and Working
A product should have a name and some representation of its price.

Each order should be recorded and have a list of products. It should also have the buyer’s e-mail, and the
time the order was placed. The total value of the order should always be calculated, based on the prices
of the products in it.
It should be possible to change the product’s price, but this shouldn’t affect the total value of orders which
have already been placed.

## How to Setup Local Database
This can be done by running the `docker-compose.yml` with the following command at the root project directory : 
```dockerfile
docker-compose up -d
```

## How ot test the REST API 
This can be done after starting the application, and opening the following URL : 
```
http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
```

After that the application can be run locally and connected to the local database.

## Assumptions and Improvements
A number of things are on purpose kept simple so as not to blow the assignment scope out of proportion, they're as follows:

* For configuration a solution like Spring Cloud Config or AWS AppConfig should instead be used because it provides a proper version control way of managing the configuration and decouples the config from the resource server
* For all types of secrets, e.g. database username, password and things alike Spring Vault or AWS SecretsManager with Spring Cloud integration should be used so as to secure and decouple the sensitive secrets from the application server.
* User entity is assumed to be there.
* A proper exception handling mechanism is currently missing.
* For mapping purposes the logic is custom, a library like MapStruct could in theory be made use of.


### Possible Future Protocols/Methods for Authentication
Authentication should be completely detached from the resource/application server, I propose making use of Keycloak
authentication server as a token issuer and the resource server is protected using a validator that verifies that the incoming request contains
a token that's generated using the same private key as the one that's used by Keycloak. Another consideration here is to not reinvent the wheel and
risk introducing critical vulnerabilities along the way, hence, Keycloak. It's been around for quite some time, tested by security experts and
provides numerous options for user authentication such as MFA etc.

### Service Redundancy Considerations

For making the service redundant itself I will make use of Elastic Bean Stalk Service provided by AWS with a default instance size of 2 (an example). It provides auto-scaling functionality using
which I can ask EBS to introduce new application servers based on different criteria, for example, we could begin with
2 instances and if the CPU Utilization transcends a certain threshold a certain number of times we make the assumption that the application
is under high load, and hence EBS introduces a new node, and scales down when the load becomes less. 

If this solution is not sufficient for us or becomes difficult to manage for us, we would rather make use of Kubernetes to manage a cluster of nodes,
scaling it up and down as necessary under different load circumstances.

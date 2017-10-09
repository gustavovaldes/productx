# Product RESTful Api

RESTFul API for Products and Categories, it support CRUD operations and hypermedia (HATEOAS based) 

  
## Prerequisites
`java8`

## API

`http://localhost:8080/profile`

**HAL Browser enabled**

`http://localhost:8080/browser/index.html#/`
 
**Category use**

 `http://localhost:8080/category`
 
 Main uses (examples):
 
 Create
` curl -i -X POST -H "Content-Type:application/json"  -d '{"code": "cat1", "name": "categoryOne", "description": "category one"}' http://localhost:8080/category  -u guga:guga
` 
 Search
 `curl http://localhost:8080/category -u guga:guga`
 `curl http://localhost:8080/category/cat1 -u guga:guga`
  
 Update
 `curl -i -X PATCH -H "Content-Type:application/json"  -d '{"name": "categoryOneUpdated"}' http://localhost:8080/category/cat1  -u guga:guga`
 
 Delete
 `curl -i -X DELETE http://localhost:8080/category/cat1  -u guga:guga`

Additionally pagination is provided, full other links can be discovered using HAL.


**Product use**

 `http://localhost:8080/product`
 
 Create
 `curl -i -X POST -H "Content-Type:application/json"  -d '{ "name":"p4", "description":"d1", "currency":"CAD", "price":"10.5", "code":"s1"  }' http://localhost:8080/product -u guga:guga`
 
 Update
`  curl -i -X PATCH -H "Content-Type:application/json"  -d '{"category": "http://localhost:8080/category/cat1"}' http://localhost:8080/product/1  -u guga:guga`

 Search
`curl http://localhost:8080/product/ -u guga:guga`
`curl http://localhost:8080/product/1 -u guga:guga`
`curl http://localhost:8080/product/1?projection=inlineCategory -u guga:guga`

 Delete
 `curl -i -X DELETE http://localhost:8080/product/1  -u guga:guga`

## More information 

_Health_: `http://localhost:8080/health`

_Metrics_: `http://localhost:8080/metrics`

_Environment_: `http://localhost:8080/env`

_H2 Console_:` http://localhost:8080/db `
*use default values (jdbc:h2:~/products, user sa) 

## Currency conversion

When product currency is different that EUR,  rate conversion is calculated using http://fixer.io API (Using the 
latest rate available)
Information is displayed in attribute priceEUR.  This attribute exist only in the resources, but not in the DB. If the product currency is EUR, priceEUR and price are the same value.



## Run Service
`./gradlew bootRun`

q
## Test 
`./gradlew test`






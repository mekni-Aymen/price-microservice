
## The main feature of hexagonal architecture

- you can easly tested each layer sparetly.
- you can run automated regression-test against it.
- work when the database unavailable.
- esay to upgrade to new technolgy.
- esay to linked application to each others.
- protected you code from leakage between business and I/O.


				com.example.intervista
				|___ **domain**				# main business
				|    |__model
				|    |  |__PriceModel.java 
				|    |__port
				|       |__PriceRepository.java
				|
				|___ **application**		# usecase
				|    |__ ManagerPrice.java	 
				|	 
				|___ **infrastructure**		# adapter (technolgy)
				|    |__entities
				|    |  |__Price.java
				|    |
				|    |__exceptions
				|    |  |__GlobalExceptionHandler.java
				|    |  |__ParameterNotFoundException.java
				|    |  |__PriceNotFoundException.java
				|    |
				|    |__persistence
				|    |  |__jpa
				|    |  |  |__JpaPriceRepository.java
				|    |  |  |__SpringDataJpaPriceRepository.java
				|    |
				|    |__ rest
				|    |   |__PriceController.java
				|    | 
				|    |__ conf
				|        |__DataInitializer.java
				|
				|___ MicroserviceApplication.java
				
In case of the example of the branch **<add-mongodb-docker>** we have add the database mongodb without
changing the business logic in our example we show the main benefice of the architecture hexagonal
in this example we just need to update the **infrastructure** layer. adding new entite DPrice with 
the package **mongodb** to connect with the database MongoDB.

	            com.example.intervista
				|___ **domain**				# main business
				|    |__model
				|    |  |__PriceModel.java 
				|    |__port
				|       |__PriceRepository.java
				|
				|___ application		# usecase
				|    |__ ManagerPrice.java	 
				|	 
				|___ **infrastructure**		# adapter (technolgy)
				|    |__entities
				|    |  |__DPrice.java
				|    |  |__Price.java
				|    |
				|    |__exceptions
				|    |  |__GlobalExceptionHandler.java
				|    |  |__ParameterNotFoundException.java
				|    |  |__PriceNotFoundException.java
				|    |
				|    |__persistence
				|    |  |__jpa
				|    |  |  |__JpaPriceRepository.java
				|    |  |  |__SpringDataJpaPriceRepository.java
				|    |  |__mongodb
				|    |     |__MongodbPriceRepository.java
				|    |     |__SpringDataMongoPriceRepository.java
				|    |
				|    |__ rest
				|    |   |__PriceController.java
				|    | 
				|    |__ conf
				|        |__DataInitializer.java
				|
				|___ MicroserviceApplication.java

### 1. Package Domaine:
In this package we define the **models** and The **ports** are **interfaces** 
or entry points to the application, allowing it to interact with external systems. 
**PriceRepository:** The interface through which external systems (like a web service) 
can send pricing processing requests to the core domain **ManagePrice**.

### 2. Package Application (core):
This is the heart of the application, where business logic resides. 
It's designed to be independent of any external systems or frameworks, 
ensuring that the core logic can evolve without being affected by the external changes.
The core logic might include a **ManagerPrice** class responsible for processing price.

### 3. Package Infrastructure (adapters):
we have two type of adapters: **inbound adapters** and **outbound adapters**, and this layer *** only for the technical business.

##### Inbound Adapters:
the package **persistance** that are the components that translate external requests into the format expected by the application's 
inbound ports **PriceRepository**. in our example, **rest package** that converts HTTP requests into application-specific commands.

##### Outbound Adapters:
These translate the application's outbound requests into the appropriate calls to external systems. 
For instance, a database repository adapter that interacts with a SQL database,
**JpaPriceRepository** implements **PriceRepository** and there methoded and use for
the persistance **SpringDataJpaPriceRepository** who this last one use **JpaRepository** to implement
the intraction with the database **H2**.
with the same why we have develop the interaction with the database **MongoDB**. 

In our case added a new integration of a database without touching the business layer,
and aims towards it can modify at the **application** layer without touching the **infrastructure** layer

### Testing:
Among the main objectives of using hexagonal architecture is to prove test each layer separately























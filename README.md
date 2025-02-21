
## the main feature of hexagonal architecture

- you can easly tested each layer sparetly.
- you can run automated regression-test against it.
- work when the database unavailable.
- esay to upgrade to new technolgy.
- esay to linked application to each others.
- protected you code from leakage between business and I/O.


				com.example.microservice
				|___ domain			# main business
				|    |__ model
				|	 |__ port
				|	 |__ service
				|
				|___ application		# usecase
				|    |__ usecase
				|	 |__ dto
				|	 
				|___ infrastructure		# adapter (technolgy)
				|	 |__ persistence
				|    |__ rest 
				|    |__ messaging
				|    |__ config
				|
				|___ MicroserviceApplication.java
				
























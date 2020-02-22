# zenhomes-tasks

# **Task 1: Domain Modelling**

Repository name: domain-task1

Please create the domain model for the following use cases:
1. A landlord can rent out a separate apartment,the whole building or several apartments to another
party.
2. A landlord can sign a rental contract with one or multiple tenants.
3. One tenant can rent multiple apartments from the same landlord.
4. One tenant can rent multiple apartments simultaneously.
5. A landlord can also be a tenant of anotherlandlord.
  
We suggest ER diagram as an outcome of this task but you can choose a format that makes more sense
for you.

**ER diagram**

ERD description:

* *Location* - used to represent location type (example: House, Flat building ...) also may contain address line.

              Relations: 
                        * Apartment : many to one : Location; "One location can have many Apartments".
              
* *Apartment* - use to represent apartment information like price, locaton, owner ...
                Apartment can have a one or many rent contracts but only one ontract might be actual.

              Relations: 
                         * Location : one to many : Apartment; "Different appartments can have a same location"
                         * Actor : one to many : Apartment; "Apartment can have one owner `landlord`".
                         * Contract : many to one : Apartment; "Apartment can have a many contracts". rel_table: ApartmentContractRelation
                         
                         
* *Actor* - use to represent tenant or landlord actor. Can be landlord and tenant in the same time.
    
1. Actor might be a landlord if own at lest one apartment a appartment marked as for rent.
2. Actor become a tenat if has one or many active contracts.
3. Actor may own location if own all appartments on location 
            
              Relations: 
                         * Apartment : many to one : Actor ; "Different appartments can have a one location"
                         * Contract : manu to many : Actor ; "Contract can have a may tenants". rel_table: TenantsRelation
                         
* *Contract* - use to represent relation between landlords and tenants.
1. Valid contract must contain one or many apartments with price and marked for rent and without other active contracts. 
2. Valid contract must have one or many tenants.
3. Valid contract must have start date and may have end date if end date not present this mean that contract timeless, to interrupt timeless contract just update end date and set past date.
               
               Relations: 
                         * Actor : many to many : Contract ; "Tenant can have a many contracts". rel_table: TenantsRelation
                         * Apartments : manu to many : Contract ; "Contract can contain a many appartments". rel_table: ApartmentContractRelation
               

![diagram data source is "domain-task1/landlord-ddm.puml"](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/G-Art/zenhomes-task/master/domain-task1/landlord-ddm.puml)

# **Task 2: Architecture & Coding**

Repository name: coding-task2

You have to design and build the system that allows to receive and collect data about energy consumption
from different villages. As a result, your system should, on demand, give outthe consumption report per
village forthe last 24h. As a result of your work, we expectthe end-to-end design ofthe system (a model,
system architecture,technology, and frameworks choice,testing strategy, etc.). We would also like to see
your code forthe whole system orreasonable part ofit. Since our main programming language is Java, it
would be nice if you implementthis solution using it. However, if you have a project (with a task of similar
complexity),then you can share it with us as well, instead of coding this task. In this case please create a
statement/document explaining the problem thatis being solved with it,this project should speaks for
your code skills :-).

Considerthat your system has anAPIthatis called by electricity counters:

    POST /counter_callback
    {
        "counter_id": "1",
        "amount": 10000.123
    }

To get additional information aboutthe counter you have to callthe following external API:

    GET https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters/1
        {
            "id": "1",
            "village": {
            "id": "100",
            "name": "Villarriba"
        }
    }

As a result, it's expected that your system will expose the following API:

    GET /consumption_report?duration=24h
    {
        "villages": [
            {
                "village_name": "Villarriba",
                "consumption": 12345.123
            },
            {
                "village_name": "Villabajo",
                "consumption": 23456.123
            }
        ]
    }
    
    
**Architecture**

![diagram data source is "coding-task2/architecture.puml"](http://www.plantuml.com/plantuml/proxy?&src=https://raw.githubusercontent.com/G-Art/zenhomes-task/master/coding-task2/architecture.puml)

Web application contain two modules core and web-counter-api.

Based on Spring framework and java 11, build system is Gradle.

Following diagram display how application works.

*Note:* instead of DB i use mock repository

![diagram data source is "coding-task2/sequence.puml"](http://www.plantuml.com/plantuml/proxy?&src=https://raw.githubusercontent.com/G-Art/zenhomes-task/master/coding-task2/sequence.puml)


**Build and deployment**

Application use gradle wrapper for build need to perform flowing commend:

Unix/Mac:

    {app path} ./gradlew build

Windows:
    
    {app path} gradlew.bat build

Deployment has two way 
 1. Using Spring boot perform command
        
    Unix/Mac:
    
        {app path} ./gradlew bootRun
    
    Windows:
        
        {app path} gradlew.bat bootRun
    
    After executing application will be available by next url http://localhost:8080/ 

 2. Build and deploy manual to dedicated servlet container
    war file will be available in `coding-task2/web-counter-api/build/libs/` folder

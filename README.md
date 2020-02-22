# zenhomes-tasks

# **Task 1: Domain Modelling**

Repository name: domain-ret
Please create the domain modelforthe following use cases:
1. A landlord can rent out a separate apartment,the whole building or several apartments to another
party.
2. A landlord can sign a rental contract with one or multiple tenants.
3. One tenant can rent multiple apartments from the same landlord.
4. One tenant can rent multiple apartments simultaneously.
5. A landlord can also be a tenant of anotherlandlord.
  
We suggest ER diagram as an outcome ofthis task but you can choose a formatthat makes more sense
for you.

**ER diagram**

ERD description:

* *Location* - used to represent location type (example: House, Flat building ...) also may contain addres line.

              Relations: 
                        * Apartment : many to one : Location; "One location can have many Apartments".
              
* *Apartment* - use to represent apartment information like price, locaton, owner ...
                Apartment can have a one or many rent contracts but only one ontract might be actual.

              Relations: 
                         * Location : one to many : Apartment; "Different appartments can have a same location"
                         * Actor : one to many : Apartment; "Apartment can have one owner `landlord`".
                         * Contract : many to one : Apartment; "Apartment can have a many contracts". rel_table: ApartmentContractRelation
                         
                         
* *Actor* - use to represent tenant or landlord actor. Can be landlord and tenant in the same time.
            Actor might be a landlord if own at lest one apartment a appartment marked as for rent.
            Actor become a tenat if has one or many active contracts.
            Actor may own location if own all appartments on location 
            
              Relations: 
                         * Apartment : many to one : Actor ; "Different appartments can have a one location"
                         * Contract : manu to many : Actor ; "Contract can have a may tenants". rel_table: TenantsRelation
                         
* *Contract* - use to represent relation between landlords and tenents.
               Valid contract must contain one or many apartments with price and marked for rent and without other active                  contracts. 
               Valid contract must have one or many tenants.
               Valid contract must have start date and may have end date if end date not present this mean that contract timeless, to interrupt timeless contract just update end date and set past date.
               
               Relations: 
                         * Actor : many to many : Contract ; "Tenant can have a many contracts". rel_table: TenantsRelation
                         * Apartments : manu to many : Contract ; "Contract can contain a many appartments". rel_table: ApartmentContractRelation
               

![alternative text](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/G-Art/zenhomes-task/master/domain-task1/landlord-ddm.puml)


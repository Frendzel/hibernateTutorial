**RELACJE** (uniDirectional, biDirectional)
* ONE TO MANY
* MANY TO ONE
* MANY TO MANY
* ONE TO ONE

http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#associations-many-to-one-example
https://vladmihalcea.com/2015/03/05/a-beginners-guide-to-jpa-and-hibernate-cascade-types/

**CRITERIA QUERY I BAZA MYSQL**

**FETCHING:** 
LAZY, EAGER
https://github.com/eugenp/tutorials/tree/master/spring-hibernate4/src/main/java/com/baeldung/hibernate/fetching

**EMBEDABLE**
@Entity
User
  -- long id
  -- String name
  -- String email
     @Embedded
  -- UserDetails userDetail

@Embeddable
UserDetails
  -- Date dateOfBirth
  -- String sex
  -- String address
  -- String maritalStatus
  
**INHERITANCE**\
* per hierarchy
* per class
* per subclass

**BATCH**\
https://www.tutorialspoint.com/hibernate/hibernate_batch_processing.htm


**ZROBIENIE PEŁNEGO DAO** -> criteria query i sessionFactory osobnej warstwie

**LOGOWANIE**
https://www.javatpoint.com/hibernate-logging-by-log4j-using-properties-file
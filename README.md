#### Spring Core
##  Create a Spring-based module, which handles event ticket booking.

    1.Based on the attached source code model implement three service classes:
* UserService
* EventService
* Ticket service

which should contain user, event, and booking-related functionality accordingly.
Create an implementation of the BookingFacade interface which should delegate method calls to services 
mentioned above.(0.5 point)

     2. Configure spring application context based on the XML config file. (0.5 point)

     3. Implement DAO objects for each of the domain model entities (User, Event, Ticket). 
They should store in and retrieve data from a common in-memory storage - java map.
Each entity should be stored under a separate namespace, so you could list particular entity types. (0.5 point)

Example for ticket - map entry {"ticket:" à {}}.

E.g. {"ticket:12345" à {"id" : 12345, "place" : 23, ......}}

    4. Storage should be implemented as a separate spring bean.
       Implement the ability to initialize storage with some prepared data from the file during the application start
       (use spring bean post-processing features).
       Path to the concrete file should be set using property placeholder and external property file. (1 point)

    5. DAO with storage bean should be inserted into services beans using auto wiring.
       Services beans should be injected into the facade using constructor-based injections.
       The rest of the injections should be done in a setter-based way. (1 point)

### Autowiring
The Spring framework enables automatic dependency injection.
In other words, by declaring all the bean dependencies in a Spring configuration file, 
Spring container can autowire relationships between collaborating beans. This is called Spring bean autowiring.
Autowiring allows Spring to resolve and inject collaborating beans into our bean.
The Spring container can autowire relationships between collaborating beans without using <constructor-arg>
and <property> elements, which helps cut down on the amount of XML configuration you write for a big Spring-based application.
source https://www.tutorialspoint.com/spring/spring_beans_autowiring.htm

    6. Cover code with unit tests. (0.5 point)

    7. Code should contain proper logging. (0.5 point)

    8. Create several integration tests that instantiate the Application Context directly,
       lookup facade bean and perform some real-life scenario 
       (e.g. create user, then create event, then book ticket for this event for the user, then cancel it). 
       (0.5 point)
Integration tests verify the end-to-end behavior of a system.
The Spring TestContext Framework provides consistent loading of Spring ApplicationContexts and WebApplicationContexts as well as caching of those contexts.
By default, once loaded, the configured ApplicationContext is reused for each test.
source:    https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/integration-testing.html
Notice that in @ContextConfiguration, we provided the ApplicationConfig.class config class,
which loads the configuration we need for this particular test. In this project was used the XML-based configuration:
@ContextConfiguration(locations={""})




Main steps
1. add dependency spring-core, spring-context, spring-beans
2. to configure app add xml-file into resources directory,and add config beans
source https://docs.spring.io/spring-framework/docs/5.1.9.RELEASE/spring-framework-reference/core.html#beans-basics
3. **to enable using property -placeholder add**
   xmlns:context="http://www.springframework.org/schema/context"
**and**
   <context:property-placeholder location="classpath:app.properties"/>
source: https://zetcode.com/spring/propertyplaceholder/

**to read the Map or Map of Map,or Map of List from property file use syntax:**

@Value("#{${key.of.property}}")
The #{} operator sends data into spEL parser,
what means it will try to convert data into its know types, based on spEL syntax as shown here in Spring Docs.

simple.map={'KEY1': 'value1', 'KEY2': 'value3', 'KEY3': 'value5'}

map.of.list={\
'KEY1': {'value1','value2'}, \
'KEY2': {'value3','value4'}, \
'KEY3': {'value5'} \
}
@Value("#{${simple.map}}")
Map<String, String> simpleMap;

@Value("#{${map.of.list}}")
Map<String, List<String>> mapOfList;
in this project was used Map of Map and xml configuration instead of annotation based configuration

### Extra mile (Optional)
Use "p" namespace to define properties Use SLF4J API for logging,
configure Spring to use SLF4J, and add some Spring logging output to the common application log.

#####  Spring p-namespace

Spring p-namespace is an XML shortcut and replacement of the <property/> subelement of the <bean/> tag.

To enable the p-namespace feature, we need to add the xmlns:p="http://www.springframework.org/schema/p" into the XML file.
Note that this namespace does not have a separate XSD file; therefore, IDEs such as IntelliJ do not recognize it.
source: https://zetcode.com/spring/pnamespace/

#####  SLF4J

The SLF4J or the Simple Logging Facade for Java is an abstraction layer for various Java logging frameworks,
like Log4j 2 or Logback. 

This allows for plugging different logging frameworks at deployment time without the need for code changes.

_To enable SLF4J in your project you need to include the slf4j-api library and logging framework of your choice._

We will also include the slf4j-simple library, so that can show how the SLF4J API looks in an easy way 
without complicating it with additional logging framework configuration.

The slf4j-simple results in the SLF4J facade printing all log messages with INFO level or above to be printed in the System.err.

dependencies {
implementation 'org.slf4j:slf4j-api:1.7.30'
implementation 'org.slf4j:slf4j-simple:1.7.30'
}
source: https://sematext.com/blog/slf4j-tutorial/
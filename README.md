# The Great Guild System!!!
A spring boot RESTful service project. Treated as a programming play ground where to try and find good coding principles of clean code.

## Design Principles:
#### GoogleTechTalks: [The Clean Code Talks](https://www.youtube.com/watch?v=4F72VULWFvc&list=PL693EFD059797C21E)

* [Too many conditions? Using inheritance and polymorphism instead!](https://youtu.be/4F72VULWFvc?list=PL693EFD059797C21E) (When behavior changes based on state or parallel conditionals are in multiple places in code. It’s good to use polymorphism!)
 
* [Global State and Singletons may not be good ideas.](https://youtu.be/-FRm3VPhseI?list=PL693EFD059797C21E)

* [Great things about Dependency Injection.](https://youtu.be/RlfLCWKxHJ0?list=PL693EFD059797C21E)

* [Writing testable codes.](https://youtu.be/wEhu57pih5w)

* [OO Design for Testability.](https://youtu.be/acjvKJiOvXw?list=PL693EFD059797C21E)



#### Field Dependency Injection Considered Harmful

* http://vojtechruzicka.com/field-dependency-injection-considered-harmful/

#### Talks about Testing by Misko Hevery 

* [Examples of Bad and Good Unit Tests](https://youtu.be/c5YltI3N1tc)

* [Box Tech Talk: Psychology of Testing](https://youtu.be/pqomi6W4AJ4)

#### Talks about Unit Testing by Roy Osherove

* [Unit Testing Best Practices](https://youtu.be/dJUVNFxrK_4)

* [The Difference between Mock and Stub](https://youtu.be/fAb_OnooCsQ)

* [Readable, Maintainable, Trustworthy Unit Tests](https://youtu.be/ybDhy7b2i2o)

#### Notes of Spring

* [Generic Spring MVC Controller with Inheritance](https://stackoverflow.com/questions/15382345/generic-spring-mvc-controller-with-inheritance)

## Current Progress:
#### 1. Restful API for user model:
 * 1-1. Using HandlerMethodArgumentResolver for getting user data.
 * 1-2. Using Inheritance for role control. Comparing with AOP, it has better maintainability and testability.
 * 1-3. Strategy Pattern for different team creation controllers. Application works differently through wiring components differently.
 
#### 2. Internationalized message handling, exception response handling.

#### 3. Used/Will used design patterns:
 * 3-1. Factory Method Pattern
 * 3-2. Builder Pattern
 * 3-3. Composite Pattern
 * 3-4. Strategy Pattern (Using polymorphism and generics)
 
#### 4. Hibernate Inheritance Mapping
 
#### 5. Testing
 * 4-1. Integration testing with SpringRunner and H2 in-memory database.
 * 4-2. Object Mother Pattern
 * 4-3. Unit testing with Mockito.
 
 

### Singleton Class: Singleton

The `Singleton` class represents a design pattern that ensures the existence of only one instance of the class throughout the application. It provides a global point of access to this single instance and is commonly used when you want to restrict object instantiation to a single occurrence.

#### Constructors

- `private Singleton(String message)`: Private constructor that initializes the Singleton instance with the provided `message`.

#### Methods

- `public static Singleton getInstance()`: Returns the Singleton instance. If the instance does not exist, a new instance is created. Otherwise, the existing instance is returned.

- `public String welcomeMethod()`: Demonstrates accessing the methods of the Singleton class. It returns a welcome message.

#### Usage Example

```java
// Obtain the singleton instance
Singleton singleton1 = Singleton.getInstance();

System.out.println("First time singleton class object: " + singleton1);
System.out.println(singleton1.welcomeMethod());

// Reusing the existing instance
Singleton singleton2 = Singleton.getInstance();

System.out.println("Second time singleton class object: " + singleton2);
System.out.println(singleton2.welcomeMethod());
```

#### Definition and Use Case Scenarios

A Singleton class is a design pattern that ensures a class has only one instance, provides global access to that instance, and is commonly used when you want to:

1. Restrict object creation: When you want to restrict the instantiation of a class to a single object, ensuring that no other instances can be created. This can be useful when managing resources or enforcing a single configuration throughout the application.

2. Global access: When you need a single, shared instance to be accessible from multiple parts of the application. This avoids the need for passing references between objects and simplifies access to shared resources.

3. Stateful objects: When you have stateful objects that need to maintain their state across different parts of the application. By using a Singleton, you ensure that all interactions occur with the same instance, preserving the object's state.

4. Resource management: When you want to manage limited resources, such as database connections, thread pools, or caches. A Singleton can handle the creation and management of these resources, ensuring they are shared efficiently across the application.

# Communication Middleware & load balancing tasks - Distributed Systems

The aim of this practical was to design and implement a simple distributed system with the help of existing communication middleware. To achieve this, I’ve used the Java Remote Method Invocation (RMI), a Java API which allows for remote communication between programs written in the Java programming language.

## Compilation

Navigate to the src directory
```bash
cd src
```
Compile Java code
```bash
javac *.java
```

## Start registry

 Starts a remote object registry on port 1099
```bash
rmiregistry &
```

## Running
### Run Server
```bash
java Server
```

### Run Client
```bash
java Client
```

## Client Usage
Simply keep entering the number of resources required by each of the tasks that will be requested.

``
Resources required for <generated_task_id> : <int> 
``
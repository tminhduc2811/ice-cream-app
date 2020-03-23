|-- PROJECT FOLDER
    |-- pom.xml # Maven, dependencies
    |-- README.md
    |-- src
    |   |-- main
    |   |   |-- java
    |   |   |   |-- com
    |   |   |       |-- atcud
    |   |   |           |-- icecreamapp
    |   |   |               |-- directoryList.md
    |   |   |               |-- IceCreamAppApplication.java
    |   |   |               |-- [config]        # Contains app's config files
    |   |   |               |-- [controllers]   # Controller layer
    |   |   |               |-- [DTO]           # Contains Data transfer object
    |   |   |               |-- [entities]      # Contains models
    |   |   |               |-- [exceptions]    # Global exception, custom exception
    |   |   |               |-- [repositories]  # Repository layer, contains interfaces
    |   |   |               |   |-- [impl]      # Contains implementations of interfaces
    |   |   |               |-- [security]      # Contains security files such as JwtProvider, Authentication Provider, Filter...
    |   |   |               |-- [services]      # Service layer, contains interfaces
    |   |   |                   |-- [impl]      # Contains implementations of interfaces
    |   |   |-- resources
    |   |       |-- application.properties      # App configuration
    |   |-- test
    |       |-- java
    |       |   |-- com
    |       |       |-- atcud
    |       |           |-- icecreamapp
    |       |               |-- [controllers]   # Unit test for controller layer
    |       |               |-- [services]      # Unit test for service layer
    |       |-- resources
    |           |-- application-test.yml
    |-- web-ui                                  # Frontend
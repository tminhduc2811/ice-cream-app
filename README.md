## SpringBoot (Java) Backend + Angular 
An application to depict how to make a RESTful web services with clientside using Angular


### Technology Stack
Component         | Technology
---               | ---
Frontend          | [Angular 5](https://github.com/angular/angular)
Backend (REST)    | [SpringBoot](https://projects.spring.io/spring-boot) (Java)
Security          | Token Based (Spring Security and JWT Authentication & Authorization )
REST Documentation| [Swagger UI / Springfox](https://github.com/springfox/springfox) and [ReDoc](https://github.com/Rebilly/ReDoc)
Persistence       | [JPA](https://github.com/spring-projects/spring-data-jpa)
JUnit, Mockito    | Unit-testing with [JUnit](https://github.com/junit-team/junit4) and [Mockito](https://github.com/mockito/mockito)
Client Build Tools| [angular-cli](https://github.com/angular/angular-cli), Webpack, npm
Server Build Tools| Maven(Java)

## Folder Structure
```bash
|-- Project Folder
    |-- IceCreamAppApplication.java
    |-- config
    |   |-- Swagger2Config.java
    |   |-- WebSecurityConfig.java
    |-- controllers
    |   |-- CustomerController.java
    |   |-- FAQController.java
    |   |-- FeedbackController.java
    |   |-- IcecreamController.java
    |   |-- OrderController.java
    |   |-- RecipeController.java
    |   |-- RoleController.java
    |   |-- UserController.java
    |-- DTO
    |   |-- CustomerDTO.java
    |   |-- DTOBuilder.java
    |   |-- LoginResponseDTO.java
    |   |-- OrderDTO.java
    |   |-- UserDTO.java
    |   |-- entities
    |       |-- RoleIdList.java
    |       |-- UserLogin.java
    |-- entities
    |   |-- Customer.java
    |   |-- FAQ.java
    |   |-- Feedback.java
    |   |-- Icecream.java
    |   |-- Order.java
    |   |-- OrderDetail.java
    |   |-- Payment.java
    |   |-- Recipe.java
    |   |-- Role.java
    |   |-- User.java
    |-- exceptions
    |   |-- AppAuthenticationEntryPoint.java
    |   |-- CustomException.java
    |   |-- GlobalExceptionHandlerController.java
    |-- repositories
    |   |-- CustomerRepository.java
    |   |-- FAQRepository.java
    |   |-- FeedbackRepository.java
    |   |-- IcecreamRepository.java
    |   |-- OrderDetailRepository.java
    |   |-- OrderRepository.java
    |   |-- PaymentRepository.java
    |   |-- RecipeRepository.java
    |   |-- RoleRepository.java
    |   |-- UserRepository.java
    |   |-- impl
    |       |-- CustomerRepositoryImpl.java
    |       |-- FAQRepositoryImpl.java
    |       |-- FeedbackRepositoryImpl.java
    |       |-- IcecreamRepositoryImpl.java
    |       |-- OrderDetailRepositoryImpl.java
    |       |-- OrderRepositoryImpl.java
    |       |-- PaymentRepositoryImpl.java
    |       |-- RecipeRepositoryImpl.java
    |       |-- RoleRepositoryImpl.java
    |       |-- UserRepositoryImpl.java
    |-- security
    |   |-- CustomerAuthenticationProvider.java
    |   |-- CustomerDetails.java
    |   |-- CustomerDetailsService.java
    |   |-- CustomUserDetails.java
    |   |-- CustomUserService.java
    |   |-- JwtAuthenticationFilter.java
    |   |-- JwtTokenProvider.java
    |   |-- UserAuthenticationProvider.java
    |-- services
        |-- CustomerService.java
        |-- FAQService.java
        |-- FeedbackService.java
        |-- IcecreamService.java
        |-- OrderDetailService.java
        |-- OrderService.java
        |-- PaymentService.java
        |-- RecipeService.java
        |-- RoleService.java
        |-- UserService.java
        |-- impl
            |-- CustomerServiceImpl.java
            |-- FAQServiceImpl.java
            |-- FeedbackServiceImpl.java
            |-- IcecreamServiceImpl.java
            |-- OrderDetailServiceImpl.java
            |-- OrderServiceImpl.java
            |-- PaymentServiceImpl.java
            |-- RecipeServiceImpl.java
            |-- RoleServiceImpl.java
            |-- UserServiceImpl.java

```

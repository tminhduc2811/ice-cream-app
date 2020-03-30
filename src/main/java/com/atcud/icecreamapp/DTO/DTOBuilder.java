package com.atcud.icecreamapp.DTO;

import com.atcud.icecreamapp.DTO.entities.*;
import com.atcud.icecreamapp.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class DTOBuilder {

    public static <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));

    }

    public static OrderDTO orderToDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getCustomer().getId(),
                order.getCustomer().getFullName(),
                order.getCustomer().getPhoneNumber(),
                order.getCustomer().getEmail(),
                order.getPayment().getCardType(),
                order.getPayment().getCardNumber(),
                order.getPayment().getId(),
                order.getPaymentOption(),
                order.getCreatedDate(),
                order.getDeliveryDetail(),
                order.getNotes(),
                order.getStatus(),
                order.getOrderDetails()
        );
    }

    public static CustomerDTO customerToDTO(Customer customer) {
        return new CustomerDTO(customer.getId(),
                customer.getUserName(),
                "",
                customer.getFirstName(),
                customer.getLastName(),
                customer.getAddress(),
                customer.getPhoneNumber(),
                customer.getEmail(),
                customer.getGender(),
                customer.getBirthday(),
                customer.getAvatar(),
                customer.getExpiredDate(),
                customer.getStatus(),
                customer.getNumOfLoginFailed());
    }

    // TODO: something wrong here, modify later
    public static LoginResponseDTO loginResponseDTO(LoginResponseDTO loginResponseDTO) {
        return new LoginResponseDTO(
                loginResponseDTO.getUserName(),
                loginResponseDTO.getToken()
        );
    }

    public static UserDTO userToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUserName(),
                "",
                user.getFullName(),
                user.getEmail(),
                user.getStatus(),
                user.getAvatar(),
                user.getAllRoles()
        );
    }

    public static RecipeDTO recipeToDTO(Recipe recipe) {
        return new RecipeDTO(
                recipe.getId(),
                recipe.getUser().getId(),
                recipe.getIcecream().getId(),
                recipe.getTitle(),
                recipe.getDescription(),
                recipe.getPrice(),
                recipe.getStatus(),
                recipe.getViewCount(),
                recipe.getImage(),
                recipe.getDetails(),
                recipe.getUploadedDate()
        );
    }

    public static FeedbackDTO feedbackToDTO(Feedback feedback) {
        return new FeedbackDTO(
                feedback.getId(),
                feedback.getCustomer().getFullName(),
                feedback.getOrder().getId(),
                feedback.getDetails(),
                feedback.getCreatedDate()
        );
    }

    public static AuthInfo authInfoToDTO(User user) {
        return new AuthInfo(
                true,
                user.getAllRoles(),
                user.getUserName(),
                user.getAvatar()
        );
    }

    public static AuthResponseDTO authResponseToDTO(AuthInfo authInfo, String token) {
        return new AuthResponseDTO(
                token,
                authInfo
        );
    }
}

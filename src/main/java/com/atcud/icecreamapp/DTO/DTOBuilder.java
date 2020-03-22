package com.atcud.icecreamapp.DTO;

import com.atcud.icecreamapp.entities.Customer;
import com.atcud.icecreamapp.entities.Order;
import com.atcud.icecreamapp.entities.User;

public class DTOBuilder {

    public static OrderDTO orderToDTO(Order order) {
        return new OrderDTO(order.getId(),
                order.getCustomer().getId(),
                order.getPayment().getId(),
                order.getPaymentOption(),
                order.getCreatedDate(),
                order.getDeliveryDetail(),
                order.getNotes(),
                order.getStatus());
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
                user.getStatus(),
                user.getAvatar()
        );
    }
}

package com.atcud.icecreamapp.DTO;

import com.atcud.icecreamapp.DTO.entities.*;
import com.atcud.icecreamapp.entities.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DTOBuilder {

    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static <D, T> Page<D> mapPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));

    }

    public static <D, T> D mapObject(final T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }


    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        List<T> list = new ArrayList<>();
        for (S s : source) {
            list.add(modelMapper.map(s, targetClass));
        }
        return list;
    }


    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
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

//    public static FeedbackDTO feedbackToDTO(Feedback feedback) {
//        return new FeedbackDTO(
//                feedback.getId(),
//                feedback.getCustomer().getFullName(),
//                feedback.getOrder().getId(),
//                feedback.getDetails(),
//                feedback.getCreatedDate()
//        );
//    }

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

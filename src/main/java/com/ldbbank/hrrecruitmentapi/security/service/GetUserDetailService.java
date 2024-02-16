package com.ldbbank.hrrecruitmentapi.security.service;


import com.ldbbank.hrrecruitmentapi.entity.Users;
import com.ldbbank.hrrecruitmentapi.payload.response.UserResponse;
import com.ldbbank.hrrecruitmentapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;

@Service
@Slf4j
public class GetUserDetailService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public GetUserDetailService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public HashMap<String, Object> getUserDetail() {

        HashMap<String, Object> user = new LinkedHashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userName = authentication.getName();
        Optional<Users> usersOptional = userRepository.findByUsername(userName);

        UserResponse userResponse = modelMapper.map(usersOptional.get(), UserResponse.class);

        user.put("userDetail", userResponse);

//        log.info("user login detail {0} " + user);

        return user;
    }
}

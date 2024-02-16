package com.ldbbank.hrrecruitmentapi.security.service.authen;

import com.ldbbank.hrrecruitmentapi.entity.UserRoles;
import com.ldbbank.hrrecruitmentapi.entity.Users;
import com.ldbbank.hrrecruitmentapi.payload.request.LoginRequest;
import com.ldbbank.hrrecruitmentapi.payload.request.RegisterRequest;
import com.ldbbank.hrrecruitmentapi.payload.response.JwtAuthResponse;
import com.ldbbank.hrrecruitmentapi.payload.response.ResponseMessage;
import com.ldbbank.hrrecruitmentapi.repository.UserRepository;
import com.ldbbank.hrrecruitmentapi.repository.UserRoleRepository;
import com.ldbbank.hrrecruitmentapi.security.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRoleRepository userRoleRepository;

    public AuthenticationServiceImpl(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            UserRoleRepository userRoleRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public Users register(RegisterRequest request) {
        System.out.println("user id" + request);
        Users user = new Users();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setIs_active(1);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Users saveUser = userRepository.save(user);
        if (saveUser != null){
            Optional<Users> allUser = userRepository.findByUsername(request.getUsername());
            Integer userID = allUser.get().getId();

            UserRoles userRoles = new UserRoles();

            userRoles.setUser_id(userID);
            userRoles.setRole_id(request.getRole_id());

            userRoleRepository.save(userRoles);


            System.out.println("user id" + userID);


            return saveUser;


        }

        return  null;
    }

    @Override
    public JwtAuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        Users user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(user);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(jwt);
        jwtAuthResponse.setExpiresIn(jwtService.getExpirationTime()); //
        jwtAuthResponse.setUserRole(user.getRoles());
        return jwtAuthResponse;
    }


}

package com.openclassrooms.mddapi.service;

import javax.transaction.Transactional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.Exception.AlreadyExistsException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.service.JwtService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private JwtService jwtService;

    @Override
    public String register(RegisterRequest request) throws AlreadyExistsException {
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new AlreadyExistsException("This email has already been used.");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        user = userRepository.save(user);

        Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        return jwtService.generateToken(auth);
    }
}

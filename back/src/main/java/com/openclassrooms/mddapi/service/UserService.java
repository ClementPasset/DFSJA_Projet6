package com.openclassrooms.mddapi.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.Exception.AlreadyExistsException;
import com.openclassrooms.mddapi.Exception.NotFoundException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;
import com.openclassrooms.mddapi.payload.request.UpdateUserRequest;
import com.openclassrooms.mddapi.payload.response.TokenResponse;
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

    private AuthenticationManager authManager;

    /**
     * register a new User and returns the Jwt Token
     * 
     * @param request
     * @return String
     * @throws AlreadyExistsException
     */
    @Override
    public TokenResponse register(RegisterRequest request) throws AlreadyExistsException {
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new AlreadyExistsException("This email has already been used.");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        user = userRepository.save(user);

        Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(auth);

        TokenResponse tokenResponse = jwtService.generateToken(auth);
        User currentUser = getCurrentUser();
        tokenResponse.setEmail(currentUser.getEmail());
        tokenResponse.setUsername(currentUser.getUsername());

        return tokenResponse;
    }

    /**
     * Returns a Jwt Token to the user logging in
     * 
     * @param request
     * @return String
     * @throws Exception
     */
    @Override
    public TokenResponse login(LoginRequest request) throws Exception {
        Authentication auth = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        TokenResponse tokenResponse = jwtService.generateToken(auth);
        User currentUser = getCurrentUser();
        tokenResponse.setEmail(currentUser.getEmail());
        tokenResponse.setUsername(currentUser.getUsername());

        return tokenResponse;
    }

    /**
     * Returns the currently logged in user
     * 
     * @return User
     * @throws AuthenticationException
     */
    public User getCurrentUser() throws AuthenticationException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByEmail(auth.getName());
    }

    /**
     * returns a user from an id
     * 
     * @param id
     * @return User
     * @throws NotFoundException
     */
    @Override
    public User getUser(Long id) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NotFoundException("User not found.");
        }
        return optionalUser.get();
    }

    /**
     * @param request
     * @return TokenResponse
     */
    @Override
    public TokenResponse updateUser(UpdateUserRequest request) {
        User currentUser = getCurrentUser();
        currentUser.setEmail(request.getEmail());
        currentUser.setUsername(request.getUsername());
        User updatedUser = userRepository.save(currentUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(updatedUser.getEmail(),
                updatedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);

        TokenResponse tokenResponse = this.jwtService.generateToken(auth);
        tokenResponse.setEmail(updatedUser.getEmail());
        tokenResponse.setUsername(updatedUser.getUsername());

        return tokenResponse;
    }

}

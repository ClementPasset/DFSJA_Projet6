package com.openclassrooms.mddapi.service;

import org.springframework.security.core.AuthenticationException;

import com.openclassrooms.mddapi.Exception.AlreadyExistsException;
import com.openclassrooms.mddapi.Exception.NotFoundException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;
import com.openclassrooms.mddapi.payload.request.UpdateUserRequest;
import com.openclassrooms.mddapi.payload.response.TokenResponse;

public interface IUserService {
    public TokenResponse register(RegisterRequest request) throws AlreadyExistsException;

    public TokenResponse login(LoginRequest request) throws Exception;

    public User getUser(Long id) throws NotFoundException;

    public User getCurrentUser() throws AuthenticationException;

    public TokenResponse updateUser(UpdateUserRequest request);
}

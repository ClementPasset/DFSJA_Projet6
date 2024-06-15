package com.openclassrooms.mddapi.service;

import org.springframework.security.core.AuthenticationException;

import com.openclassrooms.mddapi.Exception.AlreadyExistsException;
import com.openclassrooms.mddapi.Exception.NotFoundException;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;

public interface IUserService {
    public String register(RegisterRequest request) throws AlreadyExistsException;

    public String login(LoginRequest request) throws Exception;

    public User getUser(Long id) throws NotFoundException;

    public User getCurrentUser() throws AuthenticationException;
}

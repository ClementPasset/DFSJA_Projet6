package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.Exception.AlreadyExistsException;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;

public interface IUserService {
    public String register(RegisterRequest request) throws AlreadyExistsException;

    public String login(LoginRequest request) throws Exception;
}

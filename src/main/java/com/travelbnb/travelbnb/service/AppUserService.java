package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.dto.AppUserDto;
import com.travelbnb.travelbnb.payload.LoginDto;

public interface AppUserService {
    AppUserDto createUser(AppUserDto dto);

    String verifyLogin(LoginDto loginDto);
}

package com.travelbnb.travelbnb.service;

import com.travelbnb.travelbnb.dto.AppUserDto;
import com.travelbnb.travelbnb.entity.AppUser;
import com.travelbnb.travelbnb.payload.LoginDto;
import com.travelbnb.travelbnb.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AppUserServiceIMPL implements  AppUserService{


    private AppUserRepository appUserRepository ;

    private JWTService jwtService ;

    public AppUserServiceIMPL(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }


    @Override
    public AppUserDto createUser(AppUserDto dto) {
        AppUser appUser = dtoToEntity(dto);
        AppUser saveAppUser = appUserRepository.save(appUser);
        AppUserDto dto1 = entityToDto(saveAppUser);
        return dto1;
    }
    public AppUser dtoToEntity(AppUserDto appUserDto){

        AppUser appUser = new AppUser();

        appUser.setName(appUserDto.getName());
        appUser.setEmail(appUserDto.getEmail());
        appUser.setUsername(appUserDto.getUsername());
        appUser.setPassword(appUserDto.getPassword());
        appUser.setRole(appUserDto.getRole());
        return appUser;
    }

    public AppUserDto entityToDto(AppUser appUser ){
        AppUserDto aDto = new AppUserDto();

        aDto.setId(appUser.getId());
        aDto.setName(appUser.getName());
        aDto.setEmail(appUser.getEmail());
        aDto.setUsername(appUser.getUsername());
        aDto.setPassword(appUser.getPassword());
        aDto.setRole(appUser.getRole());



        return aDto;

    }



    @Override
    public String verifyLogin(LoginDto loginDto) {

        Optional<AppUser> opUserName = appUserRepository.findByUsername(loginDto.getUsername());

        if (opUserName.isPresent()){
            AppUser appUser = opUserName.get();
            if (BCrypt.checkpw(loginDto.getPassword(),appUser.getPassword())){
                String token = jwtService.generateToken(appUser);
                return token;
            }
        }

        return null;
    }

}

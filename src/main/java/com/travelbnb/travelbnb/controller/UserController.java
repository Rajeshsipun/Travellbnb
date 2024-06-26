package com.travelbnb.travelbnb.controller;


import com.travelbnb.travelbnb.dto.AppUserDto;
import com.travelbnb.travelbnb.payload.JWTTokenDto;
import com.travelbnb.travelbnb.payload.LoginDto;
import com.travelbnb.travelbnb.repository.AppUserRepository;
import com.travelbnb.travelbnb.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private AppUserRepository appUserRepository ;

    private AppUserService appUserService ;

    public UserController(AppUserRepository appUserRepository, AppUserService appUserService) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
    }

    //http://localhost:8080/api/v1/user/createUser

    @PostMapping("/createUser")
    public ResponseEntity<?>createUser(
            @RequestBody AppUserDto dto ){


        if (appUserRepository.existsByEmail(dto.getEmail())){

            return new ResponseEntity<>("Exists Email",HttpStatus.BAD_REQUEST);

        }if (appUserRepository.existsByUsername(dto.getUsername())){

         return new ResponseEntity<>( "Exists Username",HttpStatus.BAD_REQUEST) ;
        }

        dto.setPassword(BCrypt.hashpw(dto.getPassword(),BCrypt.gensalt(10)));
        AppUserDto user = appUserService.createUser(dto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }


    //http://localhost:8080/api/v1/user/login

    @PostMapping ("/login")
    public ResponseEntity<?>verifyLogin(@RequestBody LoginDto loginDto ){

        String token = appUserService.verifyLogin(loginDto);

       if(token !=null){

           JWTTokenDto jwtToken= new JWTTokenDto();//
           jwtToken.setType("JWT Token");//
           jwtToken.setToken(token);//
           return new ResponseEntity<>(jwtToken,HttpStatus.OK);//
       }
        return new ResponseEntity<>("Invalid Token",HttpStatus.CREATED);
}



}

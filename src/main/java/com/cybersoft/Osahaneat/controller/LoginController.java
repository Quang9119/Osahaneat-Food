package com.cybersoft.Osahaneat.controller;

import com.cybersoft.Osahaneat.payload.ResponseData;
import com.cybersoft.Osahaneat.payload.request.SignUpRequest;

import com.cybersoft.Osahaneat.service.imp.LoginServiceImp;
import com.cybersoft.Osahaneat.utils.JwtUtilsHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


//hello testbranchAAAAA
@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginServiceImp loginServiceImp;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password) {
        ResponseData responseData = new ResponseData();


        if(loginServiceImp.checkLogin(username,password)) {
            String token = jwtUtilsHelper.generateToken(username);
            responseData.setData(token);
        }
        else {
            responseData.setData("");
            responseData.setSuccess(false);

        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        ResponseData responseData = new ResponseData();
        responseData.setData(loginServiceImp.addUser((signUpRequest)));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}

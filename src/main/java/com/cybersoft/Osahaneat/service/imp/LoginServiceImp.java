package com.cybersoft.Osahaneat.service.imp;

import com.cybersoft.Osahaneat.dto.UserDTO;
import com.cybersoft.Osahaneat.payload.request.SignUpRequest;

import java.util.List;

public interface LoginServiceImp {
    List<UserDTO> getAllUser();
    boolean checkLogin(String username,String password);
    boolean addUser(SignUpRequest signUpRequest);

}

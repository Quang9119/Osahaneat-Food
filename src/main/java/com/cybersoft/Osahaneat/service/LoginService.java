package com.cybersoft.Osahaneat.service;

import com.cybersoft.Osahaneat.dto.UserDTO;
import com.cybersoft.Osahaneat.entity.Roles;
import com.cybersoft.Osahaneat.entity.Users;
import com.cybersoft.Osahaneat.payload.request.SignUpRequest;
import com.cybersoft.Osahaneat.repository.UserRepository;
import com.cybersoft.Osahaneat.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImp {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    public List<UserDTO> getAllUser(){
        List<Users> listUsers = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (Users  users:listUsers) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(users.getId());
            userDTO.setUserName(users.getUserName());
            userDTO.setPassword(users.getPassword());
            userDTO.setFullname(users.getUserName());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        Users user = userRepository.findByUserName(username);
        return  passwordEncoder.matches(password,user.getPassword());
    }

    @Override
    public boolean addUser(SignUpRequest signUpRequest) {
        Roles roles = new Roles();
        roles.setId(signUpRequest.getRoleId());
        Users users = new Users();
        users.setFullname(signUpRequest.getFullname());
        users.setUserName(signUpRequest.getEmail());
        users.setPassword(signUpRequest.getPassword());
        users.setRoles(roles);
        try {
            userRepository.save(users);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }


}

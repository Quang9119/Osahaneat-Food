package com.cybersoft.Osahaneat.service;

import com.cybersoft.Osahaneat.dto.UserDTO;
import com.cybersoft.Osahaneat.entity.Users;
import com.cybersoft.Osahaneat.repository.UserRepository;
import com.cybersoft.Osahaneat.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserDTO> getAllUser() {
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
}

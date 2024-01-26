package com.cybersoft.Osahaneat.service.imp;

import com.cybersoft.Osahaneat.dto.RestaurantDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

public interface RestaurantServiceImp {
        boolean insertRestaurant(MultipartFile file,
                                 String title ,
                                 String subtitle ,
                                 String description ,
                                 boolean is_freeship ,
                                 String address ,
                                 String open_date ) throws ParseException;
        List<RestaurantDTO> getHomePageRestaurant();
        RestaurantDTO getDetailRestaurant(int id);
}

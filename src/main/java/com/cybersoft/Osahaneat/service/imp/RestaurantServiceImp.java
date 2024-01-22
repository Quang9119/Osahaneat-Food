package com.cybersoft.Osahaneat.service.imp;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;

public interface RestaurantServiceImp {
    boolean insertRestaurant(MultipartFile file,
                             String title ,
                             String subtitle ,
                             String description ,
                             boolean is_freeship ,
                             String address ,
                             String open_date ) throws ParseException;
}

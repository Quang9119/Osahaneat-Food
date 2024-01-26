package com.cybersoft.Osahaneat.service.imp;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface MenuServiceImp {
    boolean createMenu(MultipartFile file,String title,String is_freeship,String time_ship,Double price,int cate_id);
}

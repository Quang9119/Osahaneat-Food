package com.cybersoft.Osahaneat.controller;

import com.cybersoft.Osahaneat.payload.ResponseData;
import com.cybersoft.Osahaneat.service.MenuService;
import com.cybersoft.Osahaneat.service.imp.FileServiceImp;
import com.cybersoft.Osahaneat.service.imp.MenuServiceImp;
import com.cybersoft.Osahaneat.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;

@CrossOrigin("*")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuServiceImp menuServiceImp;

    @Autowired
    FileServiceImp fileServiceImp;

    @PostMapping()
    public ResponseEntity<?> createMenu(@RequestParam MultipartFile file,
                                              @RequestParam String title,
                                              @RequestParam String is_freeship,
                                              @RequestParam String time_ship,
                                              @RequestParam Double price,
                                              @RequestParam int cate_id) throws ParseException {
        ResponseData responseData = new ResponseData();
        boolean isSuccess = menuServiceImp.createMenu(file,title,is_freeship, time_ship, price, cate_id);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileRestaurant(@PathVariable String filename) {
        Resource resource = fileServiceImp.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

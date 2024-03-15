package com.cybersoft.Osahaneat.service;

import com.cybersoft.Osahaneat.dto.CategoryDTO;
import com.cybersoft.Osahaneat.dto.MenuDTO;
import com.cybersoft.Osahaneat.entity.Category;
import com.cybersoft.Osahaneat.entity.Food;
import com.cybersoft.Osahaneat.repository.CategoryRepository;
import com.cybersoft.Osahaneat.service.imp.CategoryServiceImp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RedisTemplate redisTemplate;

    private Gson gson = new Gson();
    @Override
    public List<CategoryDTO> getCategoryHomePage() {
        List<CategoryDTO> listcategoryDTOS = new ArrayList<>();
        String dataRedis = (String) redisTemplate.opsForValue().get("category");
        if(dataRedis == null) {
            System.out.println("Chua co data");
            PageRequest pageRequest =PageRequest.of(0,3, Sort.by("id"));
            Page<Category> listCategory = categoryRepository.findAll(pageRequest);

            for (Category data: listCategory) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setName(data.getNameCate());
                List<MenuDTO> menuDTOS = new ArrayList<>();
                for (Food dataFood:  data.getLisFoods()) {
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setTitle(dataFood.getTitle());
                    menuDTO.setImage(dataFood.getImage());
                    menuDTO.setFreeship(dataFood.isFreeShip());
                    menuDTO.setPrice(dataFood.getPrice());
                    menuDTOS.add(menuDTO);

                }
                categoryDTO.setMenus(menuDTOS);
                listcategoryDTOS.add(categoryDTO);
            }

            String dataJson = gson.toJson(listcategoryDTOS);

            redisTemplate.opsForValue().set("category",dataJson);
        }
        else {
            System.out.println("Co data");
            Type listType = new TypeToken<List<CategoryDTO>>() {}.getType();
            listcategoryDTOS = gson.fromJson(dataRedis,listType);
        }

        return listcategoryDTOS;
    }
}

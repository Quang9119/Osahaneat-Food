package com.cybersoft.Osahaneat.service;

import com.cybersoft.Osahaneat.dto.CategoryDTO;
import com.cybersoft.Osahaneat.dto.MenuDTO;
import com.cybersoft.Osahaneat.entity.Category;
import com.cybersoft.Osahaneat.entity.Food;
import com.cybersoft.Osahaneat.repository.CategoryRepository;
import com.cybersoft.Osahaneat.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryDTO> getCategoryHomePage() {
        PageRequest pageRequest =PageRequest.of(0,3, Sort.by("id"));
        Page<Category> listCategory = categoryRepository.findAll(pageRequest);
        List<CategoryDTO> listcategoryDTOS = new ArrayList<>();
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
        return listcategoryDTOS;
    }
}

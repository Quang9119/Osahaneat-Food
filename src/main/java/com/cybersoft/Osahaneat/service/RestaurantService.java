package com.cybersoft.Osahaneat.service;

import com.cybersoft.Osahaneat.dto.CategoryDTO;
import com.cybersoft.Osahaneat.dto.MenuDTO;
import com.cybersoft.Osahaneat.dto.RestaurantDTO;
import com.cybersoft.Osahaneat.entity.Food;
import com.cybersoft.Osahaneat.entity.MenuRestaurant;
import com.cybersoft.Osahaneat.entity.RatingRestaurant;
import com.cybersoft.Osahaneat.entity.Restaurant;
import com.cybersoft.Osahaneat.repository.RestaurantRepository;
import com.cybersoft.Osahaneat.service.imp.FileServiceImp;
import com.cybersoft.Osahaneat.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RestaurantService implements RestaurantServiceImp {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FileServiceImp fileServiceImp;


    @Override
    public boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description, boolean is_freeship, String address, String open_date) {
        boolean isInsertSuccess = false;
        try {
            boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
            if(isSaveFileSuccess) {
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubtitle(subtitle);
                restaurant.setDesc(description);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setFreeship(is_freeship);
                restaurant.setAddress(address);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date date = simpleDateFormat.parse(open_date);
                restaurant.setOpenDate(date);

                restaurantRepository.save(restaurant);
                isInsertSuccess = true;
            }
        }
        catch (Exception e) {
            System.out.println("Error insert restaurant :" + e.getMessage());
        }

        return isInsertSuccess;
    }

    @Override
    public List<RestaurantDTO> getHomePageRestaurant() {
        List<RestaurantDTO> restaurantDTOS= new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0,6);
        Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);
        for (Restaurant data:listData) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setId(data.getId());
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setSubtitle(data.getSubtitle());
            restaurantDTO.setFreeship(data.getFreeship());
            restaurantDTO.setRating(caculatorRating(data.getLisRatingRestaurants()));


            restaurantDTOS.add(restaurantDTO);

        }
        return restaurantDTOS;
    }

    @Override
    public RestaurantDTO getDetailRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        RestaurantDTO restaurantDTO = null;
        if (restaurant.isPresent()) {
            List<CategoryDTO> categoryDTOList = new ArrayList<>();
            Restaurant data = restaurant.get();
            restaurantDTO = new RestaurantDTO();
            restaurantDTO.setDesc(data.getDesc());
            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setSubtitle(data.getImage());
            restaurantDTO.setRating(caculatorRating(data.getLisRatingRestaurants()));
            restaurantDTO.setFreeship(data.getFreeship());
            restaurantDTO.setOpenDate(data.getOpenDate());
            //category
            for (MenuRestaurant menuRestaurant : data.getLisMenuRestaurants()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                List<MenuDTO> menuDTOList = new ArrayList<>();
                categoryDTO.setName(menuRestaurant.getCategory().getNameCate());
                //menu
                for (Food food : menuRestaurant.getCategory().getLisFoods()) {
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setImage(food.getImage());
                    menuDTO.setTitle(food.getTitle());
                    menuDTO.setFreeship(food.isFreeShip());
                    menuDTO.setPrice(food.getPrice());
                    menuDTO.setDesc(food.getDesc());


                    menuDTOList.add((menuDTO));
                }
                categoryDTO.setMenus(menuDTOList);
                categoryDTOList.add(categoryDTO);

            }

            restaurantDTO.setCategories(categoryDTOList);
        }
        return restaurantDTO;
    }

    private double caculatorRating(Set<RatingRestaurant> listRating) {

        double totalPoint = 0;
        for (RatingRestaurant data: listRating) {
            totalPoint += data.getRatePoint();
        }
        return totalPoint/listRating.size();

    }
}

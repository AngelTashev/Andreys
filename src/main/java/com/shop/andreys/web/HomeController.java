package com.shop.andreys.web;

import com.shop.andreys.model.view.ItemHomeViewModel;
import com.shop.andreys.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final ItemService itemService;

    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(ItemService itemService, ModelMapper modelMapper) {
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String getHome(Model model, HttpSession httpSession) {
        if(httpSession.getAttribute("user") == null) {
            return "index";
        }

        model.addAttribute("items", this.itemService.getAllItems()
                                        .stream()
                                        .map(item ->  {
                                            ItemHomeViewModel itemHomeViewModel = this.modelMapper.map(item, ItemHomeViewModel.class);
                                            itemHomeViewModel.setImageSrc(String.format("/img/%s-%s.jpg", item.getGender().name(), item.getCategory().getName().name()));
                                            return itemHomeViewModel;
                                        })
                                        .collect(Collectors.toList()));
        return "home";
    }
}

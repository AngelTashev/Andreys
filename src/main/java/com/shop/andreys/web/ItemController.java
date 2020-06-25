package com.shop.andreys.web;

import com.shop.andreys.model.binding.ItemAddBindingModel;
import com.shop.andreys.model.service.ItemServiceModel;
import com.shop.andreys.model.view.ItemDetailsViewModel;
import com.shop.andreys.service.CategoryService;
import com.shop.andreys.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ModelMapper modelMapper;

    private final ItemService itemService;
    private final CategoryService categoryService;

    @Autowired
    public ItemController(ModelMapper modelMapper, ItemService itemService, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @GetMapping("/add")
    public String getItemAddForm(Model model, HttpSession httpSession) {
        // Security
        if(httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        if (!model.containsAttribute("itemAddBindingModel")) {
            model.addAttribute("itemAddBindingModel", new ItemAddBindingModel());
        }
        return "add-item";
    }

    @PostMapping("/add")
    public String confirmAddItemForm(@Valid @ModelAttribute("itemAddBindingModel")
                                                 ItemAddBindingModel itemAddBindingModel,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || itemAddBindingModel.getCategory().trim().isEmpty() ||
                                            itemAddBindingModel.getGender().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.itemAddBindingModel" , bindingResult);
            return "redirect:add";
        }

        ItemServiceModel itemServiceModel = this.modelMapper.map(itemAddBindingModel, ItemServiceModel.class);
        itemServiceModel.setCategory(this.categoryService.getByName(itemAddBindingModel.getCategory()));
        this.itemService.addItem(itemServiceModel);

        return "redirect:/";
    }

    @GetMapping("/details/{id}")
    public ModelAndView getItemDetails(@PathVariable("id") String id, ModelAndView modelAndView, HttpSession httpSession) {
        // Security
        if(httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        modelAndView.setViewName("details-item");
        ItemServiceModel itemServiceModel = this.itemService.getItemById(id);
        ItemDetailsViewModel itemDetailsViewModel = this.modelMapper.map(itemServiceModel, ItemDetailsViewModel.class);
        itemDetailsViewModel.setImageSrc(String.format("/img/%s-%s.jpg", itemServiceModel.getGender().name(), itemServiceModel.getCategory().getName().name()));
        modelAndView.addObject("item", itemDetailsViewModel);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") String id, HttpSession httpSession) {
        // Security
        if(httpSession.getAttribute("user") == null) {
            return  "redirect:/";
        }

        this.itemService.deleteById(id);
        return "redirect:/";
    }
}

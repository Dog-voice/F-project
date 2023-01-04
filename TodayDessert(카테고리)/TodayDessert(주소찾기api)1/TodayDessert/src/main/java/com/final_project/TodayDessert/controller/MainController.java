package com.final_project.TodayDessert.controller;

import com.final_project.TodayDessert.dto.ItemSearchDto;
import com.final_project.TodayDessert.dto.MainItemDto;
import com.final_project.TodayDessert.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;


//    @GetMapping(value = "/")
//    public String main() {
//        return "main";
//    }
    @GetMapping(value = "/")
    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page,Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MainItemDto> items =
                itemService.getMainItemPage(itemSearchDto, pageable);
                    model.addAttribute("items",items);
                    model.addAttribute("itemSerchDto", itemSearchDto);
                    model.addAttribute("maxPage", 5);
                    return "main";
    }
//  2022-11-27 추가
    @GetMapping(value = "/butterbar")
    public String butterbar(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MainItemDto> items =
                itemService.getButterbarPage(itemSearchDto, pageable);
                    model.addAttribute("items", items);
                    model.addAttribute("itemSearchDto", itemSearchDto);
                    model.addAttribute("maxPage", 5);

                    return "category/butterbar";
    }

    @GetMapping(value = "/cannele")
    public String cannele(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MainItemDto> items =
                itemService.getCannelePage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "category/cannele";
    }

    @GetMapping(value = "/cookies")
    public String cookies(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MainItemDto> items =
                itemService.getCookiesPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "category/cookies";
    }

    @GetMapping(value = "/financier")
    public String financier(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MainItemDto> items =
                itemService.getFinancierPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "category/financier";
    }

    @GetMapping(value = "/macaron")
    public String macaron(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MainItemDto> items =
                itemService.getMacaronPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "category/macaron";
    }

    @GetMapping(value = "/scone")
    public String scone(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MainItemDto> items =
                itemService.getSconePage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "category/scone";
    }

    @GetMapping(value = "/vegan")
    public String vegan(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MainItemDto> items =
                itemService.getVeganPage(itemSearchDto, pageable);
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "category/vegan";
    }

}

package com.citi.financemanager.Controller;


import com.citi.financemanager.Entity.HomeItems;
import com.citi.financemanager.Service.HomeItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @Autowired
    HomeItemsService homeItemsService;

    @GetMapping(value = "/home", produces = {"application/json", "application/xml"})
    public HomeItems getAllItems() {
        return homeItemsService.getHomeItems();
    }
}

package com.citi.financemanager.Controller;

import com.citi.financemanager.Entity.BudgetEntity;
import com.citi.financemanager.Service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget")
public class
BudgetController {
    @Autowired
    BudgetService budgetService;

    @GetMapping(value = "/details", produces = {"application/json", "application/xml"})
    public List<BudgetEntity> getAllBudget() {
        return budgetService.getAllBudget();
    }


    @PutMapping(value = "/modify", consumes = {"application/json", "application/xml"})
    public String modifyBudget(@RequestBody BudgetEntity item) {
        if(budgetService.ifContainsBudget(item)){
            budgetService.modifyBudget(item);
            return "ok";
        }
        return "error";
    }

    @PostMapping(value = "/add", consumes = {"application/json", "application/xml"})
    public String addBudget(@RequestBody BudgetEntity item) {
        if(!budgetService.ifContainsBudget(item)){
            budgetService.addItemToBudget(item);
            return "ok";
        }
        return "error";
    }

    @DeleteMapping(value = "/del", consumes = {"application/json", "application/xml"})
    public String delBudget(@RequestBody BudgetEntity item) {
        if(budgetService.ifContainsBudget(item)){
            budgetService.deleteBudgetItem(item);
            return "ok";
        }
        return "error";
    }

}

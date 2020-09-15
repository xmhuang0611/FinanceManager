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

    @PutMapping(value = "/manage", consumes = {"application/json", "application/xml"})
    public ResponseEntity modifyBudget(@RequestBody BudgetEntity item) {
        if (!budgetService.ifContainsBudget(item))
            return ResponseEntity.notFound().build();
        else {
            budgetService.modifyBudget(item);
            return ResponseEntity.ok().build();
        }
    }


}

package com.citi.financemanager.Controller;


import com.citi.financemanager.Entity.IncomeEntity;
import com.citi.financemanager.Service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    IncomeService incomeService;

    //get all incomes
    @GetMapping(value = "/details", produces = {"application/json", "application/xml"})
    public List<IncomeEntity> gelIncomes() {
        return incomeService.getIncomesList();
    }

    //add an income
    @PutMapping(value = "income_add", consumes = {"application/json", "application/xml"})
    public ResponseEntity<IncomeEntity> addItemToCategory(@RequestBody IncomeEntity item) {
        incomeService.addIncome(item);
        return ResponseEntity.ok().build();
    }

    //get total income sum
    @GetMapping(value = "/sum")
    public double gelTotalIncomes() {
        return incomeService.getTotalIncome();
    }

    //delete an income
    @DeleteMapping(value = "/income_delete", consumes = {"application/json", "application/xml"})
    public ResponseEntity deleteIncome(@RequestBody IncomeEntity incomeEntity) {
        incomeService.deleteItemInIncome(incomeEntity);
        return ResponseEntity.ok().build();
    }

    //modify an income
    @PutMapping(value = "income_modify", consumes = {"application/json", "application/xml"})
    public ResponseEntity<IncomeEntity> updateItemToCategory(@RequestBody IncomeEntity item) {
        incomeService.updateIncome(item);
        return ResponseEntity.ok().build();
    }

}

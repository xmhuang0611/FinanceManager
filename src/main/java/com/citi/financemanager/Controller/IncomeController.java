package com.citi.financemanager.Controller;


import com.citi.financemanager.Entity.IncomeEntity;
import com.citi.financemanager.Service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    IncomeService incomeService;

    @GetMapping(value = "/details", produces = {"application/json", "application/xml"})
    public List<IncomeEntity> gelIncomes() {
        return incomeService.getIncomesList();
    }

    @PostMapping(consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<IncomeEntity> addItemToCategory(@RequestBody IncomeEntity item) {
        incomeService.addIncome(item);
        URI uri = URI.create("/add_income" + item.getId());
        return ResponseEntity.created(uri).body(item);
    }
}

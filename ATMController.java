package com.atm.Controller;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.atm.Model.ATM;
import com.atm.Model.BankAccount;

@Controller
public class ATMController {

    private ATM atm;

    public ATMController() {
        // Initialize the ATM with an initial balance of 1000.0
        BankAccount account = new BankAccount(1000.0);
        this.atm = new ATM(account);
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("amount") double amount, Model model) {
        boolean success = atm.withdraw(amount);
        model.addAttribute("message", success ? "Withdrawal successful!" : "Insufficient funds.");
        model.addAttribute("balance", atm.checkBalance());
        return "index";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("amount") double amount, Model model) {
        atm.deposit(amount);
        model.addAttribute("message", "Deposit successful!");
        model.addAttribute("balance", atm.checkBalance());
        return "index";
    }

    @GetMapping("/balance")
    public String checkBalance(Model model) {
        model.addAttribute("balance", atm.checkBalance());
        return "index";
    }
}

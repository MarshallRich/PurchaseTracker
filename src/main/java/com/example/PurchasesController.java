package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by MacLap on 3/9/16.
 */

@Controller
public class PurchasesController {

    @Autowired
    CustomerRepository customers;

    @Autowired
    PurchaseRepository purchases;

    @PostConstruct
    public void init() throws FileNotFoundException {

        if (customers.count() == 0) {

            File f1 = new File("customers.csv");

            Scanner fileScanner1 = new Scanner(f1);

            int i = 0;
            while (fileScanner1.hasNext()) {
                String line = fileScanner1.nextLine();
                if (i != 0) {
                    String[] column = line.split(",");
                    Customer c = new Customer(column[0], column[1]);

                    customers.save(c);
                }
                i++;
            }
        }

        if (purchases.count() == 0) {
            File f2 = new File("purchases.csv");

            Scanner fileScanner2 = new Scanner(f2);

            int j = 0;
            while (fileScanner2.hasNext()) {
                String line = fileScanner2.nextLine();
                if (j != 0) {
                    String[] column = line.split(",");
                    Purchase p = new Purchase(column[1], column[2], column[3], column[4]);
                    p.customer = customers.findById(Integer.valueOf(column[0]));
                    purchases.save(p);
                }
                j++;
            }
        }
    }

    @RequestMapping(path="/", method = RequestMethod.GET)
    public String home(Model model, String category) {
        if (category != null) {
            model.addAttribute("purchases", purchases.findByCategory(category));
        }
        else {
            model.addAttribute("purchases", purchases.findAll());
        }
        return "home";
    }
}

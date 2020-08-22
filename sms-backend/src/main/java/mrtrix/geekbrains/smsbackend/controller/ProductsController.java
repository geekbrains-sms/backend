package mrtrix.geekbrains.smsbackend.controller;

import mrtrix.geekbrains.smsbackend.entity.Product;
import mrtrix.geekbrains.smsbackend.service.ProductsService;
import mrtrix.geekbrains.smsbackend.utils.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showAll(Model model, @RequestParam Map<String, String>  requestParams) {
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("p", "1"));
        ProductFilter productFilter = new ProductFilter(requestParams);

        Page<Product> products = productsService.findAll(productFilter.getSpec(), pageNumber);
        model.addAttribute("products", products);
        model.addAttribute("filterDef", productFilter.getFilterDefinition().toString());
        return "all_products";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_product_form";
    }

    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute Product product) {
        productsService.saveOrUpdate(product);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productsService.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product product) {
        productsService.saveOrUpdate(product);
        return "redirect:/products/";
    }
}
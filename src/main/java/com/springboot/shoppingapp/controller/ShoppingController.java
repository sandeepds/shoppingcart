package com.springboot.shoppingapp.controller;

import com.springboot.shoppingapp.dto.CartDto;
import com.springboot.shoppingapp.dto.ProductDto;
import com.springboot.shoppingapp.dto.UserDto;
import com.springboot.shoppingapp.entity.User;
import com.springboot.shoppingapp.service.CartService;
import com.springboot.shoppingapp.service.ProductService;
import com.springboot.shoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ShoppingController {

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @GetMapping
    public List findAll() {
        return userService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody UserDto user) {
        int id = userService.add(user);
        return "User Added with id:" + id;
    }

    @GetMapping("/{userId}")
    public UserDto showUser(@PathVariable(name = "userId") int id) {
        return (userService.findById(new Integer((id))));
    }

    @GetMapping("/{userId}/cart")
    public CartDto showCart(@PathVariable(name = "userId") int id) {
        return (userService.viewCart(new Integer((id))));
    }

    @RequestMapping(value = "/{userId}/cart", method = RequestMethod.POST)
    public CartDto addToCart(@PathVariable(name = "userId") int id, @RequestBody ProductDto productDto) {
        return (cartService.addToCart(new Integer((id)), productDto));
    }

    @RequestMapping(value = "/{userId}/cart", method = RequestMethod.PUT)
    public CartDto updateCart(@PathVariable(name = "userId") int id, @RequestBody ProductDto productDto) {
        return (cartService.updateCart(new Integer((id)), productDto));
    }

    @RequestMapping(value = "/{userId}/cart/{prodId}", method = RequestMethod.DELETE)
    public CartDto removeFromCart(@PathVariable(name = "userId") int id,@PathVariable(name = "prodId") int prodId) {
        return (cartService.removeFromCart(new Integer(id), prodId));
    }

    @GetMapping("/showProducts")
    public List<ProductDto> showProducts(@PathVariable(name = "userId") int id) {
    	 //List<ProductDto> product = productService.getAll();
    	 //return product;
        return null;
    }

}

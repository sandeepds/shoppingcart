package com.springboot.shoppingapp.service.impl;

import com.springboot.shoppingapp.dto.CartDto;
import com.springboot.shoppingapp.dto.DtoConverter;
import com.springboot.shoppingapp.dto.UserDto;
import com.springboot.shoppingapp.entity.Cart;
import com.springboot.shoppingapp.entity.User;
import com.springboot.shoppingapp.repo.UserRepository;
import com.springboot.shoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repo;

    @Override
    public List getAll() {
        return repo.findAll();
    }

    @Override
    public UserDto findById(Integer integer) {
        List<User> list = repo.findUserByUserId(integer);

        UserDto userDto = DtoConverter.convert((User) (list != null && !list.isEmpty() ? list.get(0) : null));
        return userDto;
    }

    @Override
    public int add(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getName());
        Cart cart = new Cart();
        user.setCart(cart);
        return repo.save(user).getUserId();
    }

    @Override
    public CartDto viewCart(Integer integer) {

        List<User> list = repo.findUserByUserId(integer);
        User user = (User) (list != null && !list.isEmpty() ? list.get(0) : null);

        return DtoConverter.convert((Cart) (user != null ? user.getCart() : null));
    }

}

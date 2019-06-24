package com.springboot.shoppingapp.service.impl;

import com.springboot.shoppingapp.dto.DtoConverter;
import com.springboot.shoppingapp.dto.ProductSearchDto;
import com.springboot.shoppingapp.entity.Apparal;
import com.springboot.shoppingapp.entity.Book;
import com.springboot.shoppingapp.entity.Product;
import com.springboot.shoppingapp.repo.ApparalRepository;
import com.springboot.shoppingapp.repo.BookRepository;
import com.springboot.shoppingapp.repo.ProductRepository;
import com.springboot.shoppingapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository pRepo;

    @Autowired
    BookRepository bookRepo;

    @Autowired
    ApparalRepository apparalRepo;

    @Override
    public List getAll() {
        List<Product> list = pRepo.findAll();
        return ProductSearchListDto(list);
    }

    @Override
    public List getByName(String name) {
        return ProductSearchListDto(pRepo.findByProductNameContains(name));
    }

    @Override
    public ProductSearchDto getById(int id) {
        Product product = pRepo.findById(id).orElse(null);
        return product != null ? DtoConverter.convert(product) : null;
    }

    private List ProductSearchListDto(List<Product> list) {
        List<ProductSearchDto> dtoList = null;
        if (list != null && !list.isEmpty()) dtoList = new ArrayList<>();
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            Product product = (Product) itr.next();
            ProductSearchDto dto = DtoConverter.convert(product);
            dtoList.add(dto);
        }
        return dtoList;
    }
}

package com.formacionbdi.springboot.app.item.models.service;

import com.formacionbdi.springboot.app.item.client.ProductoClientRest;
import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements ItemService {


    @Autowired
    private ProductoClientRest clienteFeign;

    @Override
    public List<Item> findAll() {
        return clienteFeign
                .list()
                .stream()
                .map(p -> new Item(p,1))
                .collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(clienteFeign.detail(id),cantidad);
    }

    @Override
    public Producto save(Producto producto) {
        return clienteFeign.save(producto);
    }

    @Override
    public Producto update(Producto producto, Long id) {
        return clienteFeign.edit(producto,id);
    }

    @Override
    public void delete(Long id) {
        clienteFeign.delete(id);
    }
}

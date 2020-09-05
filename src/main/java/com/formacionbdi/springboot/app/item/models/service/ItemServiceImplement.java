package com.formacionbdi.springboot.app.item.models.service;

import com.formacionbdi.springboot.app.item.models.Item;
//import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.models.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImplement implements ItemService{

    @Autowired
    private RestTemplate clienteRest;

    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays
                .asList( clienteRest.getForObject("/list",Producto[].class));
        return productos
                .stream()
                .map(p -> new Item(p,1))
                .collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String,String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id",id.toString());
        Producto producto =
                clienteRest.getForObject("/list/{id}",Producto.class,pathVariables);
        return new Item(producto,cantidad);
    }

    @Override
    public Producto save(Producto producto) {
        HttpEntity<Producto> requestBody = new HttpEntity<Producto>(producto);
        ResponseEntity<Producto> responseEntity = clienteRest
                .exchange("/save", HttpMethod.POST,requestBody,Producto.class);
        Producto productoResponse = responseEntity.getBody();
        return productoResponse;
    }

    @Override
    public Producto update(Producto producto, Long id) {
        Map<String,String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id",id.toString());
        HttpEntity<Producto> requestBody = new HttpEntity<Producto>(producto);
        ResponseEntity<Producto> responseEntity = clienteRest
                .exchange("/edit/{id}",HttpMethod.PUT,requestBody,Producto.class,pathVariables);


        return responseEntity.getBody();
    }

    @Override
    public void delete(Long id) {
        Map<String,String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id",id.toString());

        clienteRest.delete("/delete/{id}",pathVariables);

    }
}



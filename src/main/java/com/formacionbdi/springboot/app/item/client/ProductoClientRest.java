package com.formacionbdi.springboot.app.item.client;

//import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.models.entity.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "servicio-productos")
public interface  ProductoClientRest {

    @GetMapping("/list")
    public List<Producto> list();

    @GetMapping("/list/{id}")
    public Producto detail(@PathVariable Long id);

    @PostMapping("/save")
    public Producto save(@RequestBody Producto producto);

    @PutMapping("/edit/{id}")
    public Producto edit(@RequestBody Producto producto,@PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    public Producto delete(@PathVariable Long id);
}

package com.formacionbdi.springboot.app.item.controllers;

import com.formacionbdi.springboot.app.item.models.Item;
//import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.item.models.service.ItemService;
import com.formacionbdi.springboot.app.models.entity.Producto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Permite refrescar los componente controlladores ,Clases con @Component ; @Service
@RefreshScope
@RestController
public class ItemController {

    private static Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private Environment env;
/*
    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;*/

    @Autowired
    @Qualifier("serviceRestTemplate")
    private ItemService itemService;


    @Value("${config.text}")
    private String text;

    @GetMapping("/list")
    public List<Item> lis(){
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/list/{id}/cantidad/{cantidad}")
    public Item detail(@PathVariable Long id, @PathVariable Integer cantidad){
        return itemService.findById(id,cantidad);
    }

    public Item metodoAlternativo(Long id,Integer cantidad){
        Item item = new Item();
        Producto producto
                = new Producto();
        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Miguel");
        producto.setPrecio(500.0);
        item.setProducto(producto);

        return item;
    }

    @GetMapping("/getconfig")
    public ResponseEntity<?> getConfig(@Value("${server.port}")String port){

        log.info("Puerto: "+ port);
        Map<String,String> json = new HashMap<>();
        json.put("text",text);
        json.put("port",port);

       // if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")){
            json.put("autor.name",env.getProperty("config.autor.name"));
            json.put("autor.email",env.getProperty("config.autor.email"));
       // }
        return new ResponseEntity<Map<String,String>>(json, HttpStatus.OK);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto save(@RequestBody Producto producto){
        return itemService.save(producto);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto update(@RequestBody Producto producto,@PathVariable Long id){
        return itemService.update(producto,id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        itemService.delete(id);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.service.CategoriaService;
import com.proyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    private ProductoService productoService;
 
     private CategoriaService categoriaService;
    
     
     
   @GetMapping("/")
    public String listado(Model model){
        
        var listado = productoService.getProductos(true);
 //pasar info de un java class a interface
        model.addAttribute("productos",listado);
        return "index";
    }
}

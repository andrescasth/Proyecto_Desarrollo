package com.proyecto.controller;

import com.proyecto.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.proyecto.service.FirebaseStorageService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.proyecto.domain.Producto;
import com.proyecto.service.CategoriaService;


import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/producto")

public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    @Autowired
     private CategoriaService categoriaService;
    
     
     
   @GetMapping("/listado")
    public String listado(Model model){
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias",categorias);
        var listado = productoService.getProductos(false);
       
        
        //pasar info de un java class a interface
        model.addAttribute("productos",listado);
        model.addAttribute("totalProductos", listado.size());
   
        return "/producto/listado";
    }
        @Autowired
        
        private FirebaseStorageService firebaseStorageService;
        
         @PostMapping("/guardar")
        public String guardar(Producto producto, @RequestParam("imagenFile") MultipartFile imagenFile){
            
            if(!imagenFile.isEmpty()){
                
                
                //se requiere guardar una imagen en Firebase Storage
                
                productoService.save(producto);
                String rutaImagen = 
                        firebaseStorageService.cargaImagen(imagenFile, "producto", producto.getIdProducto());           
         
                producto.setRutaImagen(rutaImagen);
               }

        productoService.save(producto);
        return "redirect:/producto/listado";
        }
        
        
        
        @GetMapping("/modificar/{idProducto}")
        public String modifica( Producto producto, Model model){
            var categorias = categoriaService.getCategorias(true);
            model.addAttribute("categorias",categorias);
            producto=productoService.getProducto(producto);
            model.addAttribute("producto", producto);
            return"/producto/modifica";
        }
        
        
         @GetMapping("/eliminar/{idProducto}")
        public String elimina( Producto producto, Model model){
           productoService.delete(producto);
            return"redirect:/producto/listado";
        }
}

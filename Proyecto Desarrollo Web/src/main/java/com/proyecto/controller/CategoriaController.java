package com.proyecto.controller;

import com.proyecto.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.proyecto.service.FirebaseStorageService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.proyecto.domain.Categoria;


import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/categoria")

public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/listado")
    public String listado(Model model){
        var listado = categoriaService.getCategorias(false);
        
        //pasar info de un java class a interface
        model.addAttribute("categorias",listado);
        model.addAttribute("totalCategorias", listado.size());
   
        return "/categoria/listado";
    }
        @Autowired
        
        private FirebaseStorageService firebaseStorageService;
        
        @PostMapping("/guardar")
        public String guardar(Categoria categoria, @RequestParam("imagenFile") MultipartFile imagenFile){
            
            if(!imagenFile.isEmpty()){
                
                
                //se requiere guardar una imagen en Firebase Storage
                
                categoriaService.save(categoria);
                String rutaImagen = 
                        firebaseStorageService.cargaImagen(imagenFile, "categoria", categoria.getIdCategoria());           
         
                categoria.setRutaImagen(rutaImagen);
               }

        categoriaService.save(categoria);
        return "redirect:/categoria/listado";
        }
        
        
        
        @GetMapping("/modificar/{idCategoria}")
        public String modifica( Categoria categoria, Model model){
            categoria=categoriaService.getCategoria(categoria);
            model.addAttribute("categoria", categoria);
            return"/categoria/modifica";
        }
        
        
         @GetMapping("/eliminar/{idCategoria}")
        public String elimina( Categoria categoria, Model model){
           categoriaService.delete(categoria);
            return"redirect:/categoria/listado";
        }
}

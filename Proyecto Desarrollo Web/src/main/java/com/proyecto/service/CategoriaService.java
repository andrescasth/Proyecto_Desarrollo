package com.proyecto.service;

import com.proyecto.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    
    
    //Recupera una ista de registros de la tabla categoria
    public List<Categoria> getCategorias(boolean activos);
    
    
    //Recupera un registro de la tabla categoria, buscando
    //e; atributo idCategoria
    
    public Categoria getCategoria(Categoria categoria);
    
    //elimina un registro de la tabla categoria si encuentra un regsityo con el id pasado
    
    public void delete (Categoria categoria);
    
      //Si el paramentro categoria pasado tiene un valor en idCategoria lo intenta actualizar, si no tiene valor 
    //se crea el registro
    
    public void save (Categoria categoria);
}

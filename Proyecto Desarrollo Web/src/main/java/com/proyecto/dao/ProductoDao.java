/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.dao;

import com.proyecto.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductoDao extends JpaRepository<Producto, Long> {
    
    //Consulta JPA
    
    //Esta consulta utiliza un Query JPA
    public List<Producto>
            findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
 
    //Esta consulta utiliza un Query JPQL
    @Query(value = "SELECT p "
            + "FROM Producto p "
            + "WHERE p.precio "
            + "BETWEEN :precioInf and :precioSup "
            + "ORDER BY p.descripcion")
    public List<Producto>
            consultaJPQL(double precioInf, double precioSup);
 
    //Esta consulta utiliza un Query con SQL NATIVO
    @Query(nativeQuery=true,
            value = "SELECT * "
            + "FROM producto p "
            + "WHERE p.precio "
            + "BETWEEN :precioInf and :precioSup "
            + "ORDER BY p.descripcion")
    public List<Producto>
            consultaNativa(double precioInf, double precioSup);
}
package com.proyecto.controller;

import com.proyecto.domain.Item;
import com.proyecto.domain.Producto;
import com.proyecto.service.ItemService;
import com.proyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarritoController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/carrito/agregar/{idProducto}")
    public ModelAndView agregar(Model model, Item item) {
        Item item2 = itemService.get(item);
        if (item2==null) {
            Producto p = productoService.getProducto(item);
            item2=new Item(p);
        }
        itemService.save(item2);
        var lista = itemService.gets();
        var totalCarrito=0;
        var carritoTotalVenta=0;
        for (Item i : lista) {
            totalCarrito+=i.getCantidad();
            carritoTotalVenta+=(i.getCantidad()*i.getPrecio());
        }
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarrito);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }

}

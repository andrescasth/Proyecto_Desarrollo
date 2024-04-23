
package com.proyecto.service.impl;

import com.proyecto.dao.UsuarioDao;
import com.proyecto.domain.Rol;
import com.proyecto.domain.Usuario;
import com.proyecto.service.UsuarioDetailsService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
 
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService{
@Autowired
private UsuarioDao usuarioDao;
    
@Autowired
private HttpSession session;
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        
        
        //Se busca el usuario que tenga ese username en el parametro
        Usuario usuario= usuarioDao.findByUsername(username);
        
        //Se validad si recupero un usuario       
        if(usuario==null){
            
            //No lo encontro
            throw new UsernameNotFoundException(username);
        }
        
        
        //tomar la imagen
        session.removeAttribute("rutaImagen");
        session.setAttribute("rutaImagen", usuario.getRutaImagen());
        
        
        
        
        
        
        //Si se encontro un usuario
        var roles= new ArrayList<GrantedAuthority>();
        for(Rol r : usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(r.getNombre()));
            
        }
       return new User(usuario.getUsername(), 
                       usuario.getPassword(),
                       roles);
    }
    
}

package com.basico.viajes.authentication;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.basico.viajes.dao.CuentaDAO;
import com.basico.viajes.entity.Cuenta;
 
@Service
public class MyDBAuthenticationService implements UserDetailsService {
 
    @Autowired
    private CuentaDAO cuentaDAO;
 
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Cuenta cuenta = cuentaDAO.findCuenta(nombre);
        System.out.println("Cuenta= " + cuenta);
 
        if (cuenta == null) {
        	System.out.println("El usuario no se encuentra en la base de datos");
            throw new UsernameNotFoundException("El usuario " //
                    + nombre + " no se encuentra en nuestra base de datos");
            }
 
        // EMPLOYEE,MANAGER,..
        String role = cuenta.getRole();
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
 
        // ROLE_EMPLOYEE, ROLE_MANAGER
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
 
        grantList.add(authority);
 
        boolean enabled = cuenta.isActive();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
 
        UserDetails userDetails = (UserDetails) new User(cuenta.getNombre(), //
                cuenta.getPass(), enabled, accountNonExpired, //
                credentialsNonExpired, accountNonLocked, grantList);
        return userDetails;
        }
	}
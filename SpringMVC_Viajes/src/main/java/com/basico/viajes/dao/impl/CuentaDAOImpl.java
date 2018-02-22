package com.basico.viajes.dao.impl;
 
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.basico.viajes.dao.CuentaDAO;
import com.basico.viajes.entity.Cuenta;
 
// Transactional for Hibernate
@Transactional
public class CuentaDAOImpl implements CuentaDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
 
    public Cuenta findCuenta(String nombre) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Cuenta.class);
//        crit.createAlias("Cuentas", "Cnt");
        crit.add(Restrictions.eq("nombre", nombre));
//        crit.add(Restrictions.eq("Cnt.nombre",nombre));
        return (Cuenta) crit.uniqueResult();
        }
    }
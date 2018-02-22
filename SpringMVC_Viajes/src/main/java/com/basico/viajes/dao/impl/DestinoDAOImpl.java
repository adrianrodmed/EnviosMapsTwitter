package com.basico.viajes.dao.impl;
 
import java.util.Date;
 
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.basico.viajes.dao.DestinoDAO;
import com.basico.viajes.entity.Destino;
import com.basico.viajes.model.DestinoInfo;
import com.basico.viajes.model.PaginationResult;
 
// Transactional for Hibernate
@Transactional
public class DestinoDAOImpl implements DestinoDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
    public Destino findDestino(String iddestino) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Destino.class);
//        crit.createAlias("Destinos", "Dst");
        crit.add(Restrictions.eq("iddestino", iddestino));
//        crit.add(Restrictions.eq("Cnt.iddestino", iddestino));
        return (Destino) crit.uniqueResult();
        }
 
    public DestinoInfo findDestinoInfo(String iddestino) {
        Destino destino = this.findDestino(iddestino);
        if (destino == null) {
            return null;
            }
        return new DestinoInfo(destino.getIddestino(), destino.getNombre(), //
        		destino.getDescripcioncorta(), destino.getFecha(), //
        		destino.getNumplazas(), destino.getPrecio());
        }
 
    public void save(DestinoInfo destinoInfo) {
        String iddestino = destinoInfo.getIddestino();
        Destino destino = null;
        boolean isNew = false;
        if (iddestino != null) {
            destino = this.findDestino(iddestino);
            }
        if (destino == null) {
            isNew = true;
            destino = new Destino();
            destino.setFecha(new Date());
            }
        destino.setIddestino(iddestino);;
        destino.setNombre(destinoInfo.getNombre());
//        destino.setDescripcioncorta(destinoInfo.getDescripcioncorta());
//        destino.setFecha(destinoInfo.getFecha());
//        destino.setNumplazas(destinoInfo.getNumplazas());
        destino.setPrecio(destinoInfo.getPrecio());
 
        if (destinoInfo.getFileData() != null) {
            byte[] imagen = destinoInfo.getFileData().getBytes();
            if (imagen != null && imagen.length > 0) {
                destino.setImagen(imagen);
                }
            }
        if (isNew) {
            this.sessionFactory.getCurrentSession().persist(destino);
            }
        this.sessionFactory.getCurrentSession().flush();
        }
 
    public PaginationResult<DestinoInfo> queryDestinos(int page, int maxResult, int maxNavigationPage,
            String likeName) {
        String sql = "Select new " + DestinoInfo.class.getName() //
                + "(d.iddestino, d.nombre, d.descripcioncorta, d.fecha, d.numplazas, d.precio) " + " from "//
                + Destino.class.getName() + " d ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(d.nombre) like :likeName ";
            }
        sql += " order by d.fecha desc ";
        //
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        if (likeName != null && likeName.length() > 0) {
            query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
            }
        return new PaginationResult<DestinoInfo>(query, page, maxResult, maxNavigationPage);
        }
 
    public PaginationResult<DestinoInfo> queryDestinos(int page, int maxResult, int maxNavigationPage) {
        return queryDestinos(page, maxResult, maxNavigationPage, null);
        }
    }
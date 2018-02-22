package com.basico.viajes.dao.impl;
 
import java.util.Date;
import java.util.List;
import java.util.UUID;
 
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.basico.viajes.dao.CompraDAO;
import com.basico.viajes.dao.DestinoDAO;
import com.basico.viajes.entity.Compra;
import com.basico.viajes.entity.Destino;
import com.basico.viajes.entity.Reserva;
import com.basico.viajes.model.CarritoInfo;
import com.basico.viajes.model.CompraInfo;
import com.basico.viajes.model.CompradorInfo;
import com.basico.viajes.model.LineaCarritoInfo;
import com.basico.viajes.model.PaginationResult;
import com.basico.viajes.model.ReservaInfo;
 
//Transactional for Hibernate
@Transactional
public class CompraDAOImpl implements CompraDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    @Autowired
    private DestinoDAO destinoDAO;
 
    private int getMaxNumcompra() {
        String sql = "Select max(c.numcompra) from " + Compra.class.getName() + " c ";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        Integer value = (Integer) query.uniqueResult();
        if (value == null) {
            return 0;
            }
        return value;
        }
 
    public void saveCompra(CarritoInfo carritoInfo) {
        Session session = sessionFactory.getCurrentSession();
 
        int numcompra = this.getMaxNumcompra() + 1;
        Compra compra = new Compra();
 
        compra.setIdcompra(UUID.randomUUID().toString());
        compra.setNumcompra(numcompra);
        compra.setFechacompra(new Date());
        compra.setPreciototal(carritoInfo.getPrecioTotal());
 
        CompradorInfo compradorInfo = carritoInfo.getCompradorInfo();
        compra.setNombrecomprador(compradorInfo.getNombrecomprador());
        compra.setMailcomprador(compradorInfo.getMailcomprador());
        compra.setTelefonocomprador(compradorInfo.getTelefonocomprador());
        compra.setDireccioncomprador(compradorInfo.getDireccioncomprador());
 
        session.persist(compra);
 
        List<LineaCarritoInfo> lineas = carritoInfo.getLineaCarrito();
 
        for (LineaCarritoInfo linea : lineas) {
            Reserva reserva = new Reserva();
            reserva.setIdreserva(UUID.randomUUID().toString());
            reserva.setCompra(compra);
            reserva.setPreciototal(linea.getPreciototal());
            reserva.setPrecio(linea.getDestinoInfo().getPrecio());
            reserva.setCantidad(linea.getCantidad());
//            reserva.setNumplazas();
 
            String iddestino = linea.getDestinoInfo().getIddestino();
            Destino destino = this.destinoDAO.findDestino(iddestino);
            reserva.setDestino(destino);
            session.persist(reserva);
            }

        carritoInfo.setNumcompra(numcompra);
        }
 
    // @page = 1, 2, ...
    public PaginationResult<CompraInfo> listCompraInfo(int page, int maxResult, int maxNavigationPage) {
        String sql = "Select new " + CompraInfo.class.getName()//
                + "(com.idcompra, com.fechacompra, com.numcompra, com.preciototal, "
                + " com.nombrecomprador, com.direccioncomprador, com.mailcomprador, com.telefonocomprador) " + " from "
                + Compra.class.getName() + " com "//
                + " order by com.numcompra desc";
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        return new PaginationResult<CompraInfo>(query, page, maxResult, maxNavigationPage);
        }
 
    public Compra findCompra(String idcompra) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Compra.class);
//        crit.createAlias("Compras", "Cmp");
        crit.add(Restrictions.eq("idcompra", idcompra));
//        crit.add(Restrictions.eq("Cnt.idcompra", idcompra));
        return (Compra) crit.uniqueResult();
        }
 
    public CompraInfo getCompraInfo(String idcompra) {
        Compra compra = this.findCompra(idcompra);
        if (compra == null) {
            return null;
            }
        return new CompraInfo(compra.getIdcompra(),compra.getFechacompra(),//
        		compra.getNumcompra(), compra.getPreciototal(), //
        		compra.getDireccioncomprador(), compra.getMailcomprador(), //
        		compra.getNombrecomprador(), compra.getTelefonocomprador());
        }
 
    public List<ReservaInfo> listReservasInfos(String idcompra) {
        String sql = "Select new " + ReservaInfo.class.getName() //
                + "(r.idreserva, r.destino.iddestino, r.destino.nombre , r.cantidad, r.precio, r.preciototal, r.numplazas) "//
                + " from " + Reserva.class.getName() + " r "//
                + " where r.compra.idcompra = :idcompra ";
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setParameter("idcompra", idcompra);
        return query.list();
        }
	}
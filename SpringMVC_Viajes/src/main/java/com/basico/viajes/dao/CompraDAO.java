package com.basico.viajes.dao;
 
import java.util.List;

import com.basico.viajes.model.CarritoInfo;
import com.basico.viajes.model.CompraInfo;
import com.basico.viajes.model.PaginationResult;
import com.basico.viajes.model.ReservaInfo;
 
public interface CompraDAO {
 
    public void saveCompra(CarritoInfo carritoInfo);
 
    public PaginationResult<CompraInfo> listCompraInfo(int page,
            int maxResult, int maxNavigationPage);
    
    public CompraInfo getCompraInfo(String idcompra);
    
    public List<ReservaInfo> listReservasInfos(String idcompra);
    }
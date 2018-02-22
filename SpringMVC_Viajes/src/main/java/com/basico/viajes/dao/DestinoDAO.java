package com.basico.viajes.dao;
 
import com.basico.viajes.entity.Destino;
import com.basico.viajes.model.DestinoInfo;
import com.basico.viajes.model.PaginationResult;
 
public interface DestinoDAO {
 
    public Destino findDestino(String iddestino);
    
    public DestinoInfo findDestinoInfo(String iddestino) ;
  
    public PaginationResult<DestinoInfo> queryDestinos(int page,
                       int maxResult, int maxNavigationPage  );
    
    public PaginationResult<DestinoInfo> queryDestinos(int page, int maxResult,
                       int maxNavigationPage, String likeName);
 
    public void save(DestinoInfo destinoInfo);
    }
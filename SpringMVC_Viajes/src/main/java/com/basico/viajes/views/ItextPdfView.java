package com.basico.viajes.views;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basico.viajes.model.CarritoInfo;
import com.basico.viajes.model.LineaCarritoInfo;
import com.basico.viajes.util.Utiles;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ItextPdfView extends AbstractITextPdfView {

    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT);

    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document, PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
//        List<CartInfo> cartinfos = (List<CartInfo>) model.get("cartinfos");
        CarritoInfo carritoinfo = Utiles.getSesionCarrito(request);
        PdfPTable table = new PdfPTable(5);
        table.setWidths(new int[]{35, 10, 10, 10, 35});

        table.addCell("Nombre del producto");
        table.addCell("Fecha");
        table.addCell("Cantidad");
        table.addCell("Subtotal");
        table.addCell("Mail del comprador");
        
        for (LineaCarritoInfo cInfo : carritoinfo.getLineaCarrito()){
        	table.addCell(String.valueOf(cInfo.getDestinoInfo().getNombre()));
        	table.addCell(String.valueOf(cInfo.getDestinoInfo().getFecha()));
    		table.addCell(String.valueOf(carritoinfo.getCantidadTotal()));
    		table.addCell(String.valueOf(carritoinfo.getPrecioTotal()));
    		table.addCell(String.valueOf(carritoinfo.getCompradorInfo().getMailcomprador()));
    		}
        document.add(table);
        }
    }
package com.basico.viajes.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.basico.viajes.dao.CompraDAO;
import com.basico.viajes.dao.DestinoDAO;
import com.basico.viajes.model.CompraInfo;
import com.basico.viajes.model.DestinoInfo;
import com.basico.viajes.model.PaginationResult;
import com.basico.viajes.model.ReservaInfo;
import com.basico.viajes.validator.DestinoInfoValidator;
 
@Controller
// Enable Hibernate Transaction.
@Transactional
// Need to use RedirectAttributes
@EnableWebMvc
public class AdminController {
 
    @Autowired
    private CompraDAO compraDAO;
 
    @Autowired
    private DestinoDAO destinoDAO;
 
    @Autowired
    private DestinoInfoValidator destinoInfoValidator;

    // Configurated In ApplicationContextConfig.
    @Autowired
    private ResourceBundleMessageSource messageSource;
 
    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
            }
        System.out.println("Target=" + target);
 
        if (target.getClass() == DestinoInfo.class) {
            dataBinder.setValidator(destinoInfoValidator);
            // For upload Image.
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
            }
        }
 
    // GET: Show Login Page
    @RequestMapping(value = { "/registro" }, method = RequestMethod.GET)
    public String registro(Model model) {
        return "registro";
        }
 
    @RequestMapping(value = { "/cuentaInfo" }, method = RequestMethod.GET)
    public String cuentaInfo(Model model) {
 
        UserDetails detalles = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(detalles.getPassword());
        System.out.println(detalles.getUsername());
        System.out.println(detalles.isEnabled());
 
        model.addAttribute("detalles", detalles);
        return "cuentaInfo";
        }
 
    @RequestMapping(value = { "/listaCompra" }, method = RequestMethod.GET)
    public String listaCompra(Model model, //
            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
            }
        catch (Exception e) {
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;
 
        PaginationResult<CompraInfo> paginationResult //
        = compraDAO.listCompraInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
 
        model.addAttribute("paginationResult", paginationResult);
        return "listaCompra";
        }
 
    // GET: Show product.
    @RequestMapping(value = { "/destino" }, method = RequestMethod.GET)
    public String destino(Model model, @RequestParam(value = "iddestino", defaultValue = "") String iddestino) {
        DestinoInfo destinoInfo = null;
 
        if (iddestino != null && iddestino.length() > 0) {
            destinoInfo = destinoDAO.findDestinoInfo(iddestino);
            }
        if (destinoInfo == null) {
            destinoInfo = new DestinoInfo();
            destinoInfo.setNewDestino(true);
            }
        model.addAttribute("destinoForm", destinoInfo);
        return "destino";
        }
 
    // POST: Save product
    @RequestMapping(value = { "/destino" }, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String destinoSave(Model model, //
            @ModelAttribute("destinoForm") @Validated DestinoInfo destinoInfo, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
 
        if (result.hasErrors()) {
            return "destino";
            }
        try {
            destinoDAO.save(destinoInfo);
            }
        catch (Exception e) {
            // Need: Propagation.NEVER?
            String mensaje = e.getMessage();
            model.addAttribute("mensaje", mensaje);
            // Show product form.
            return "destino";
            }
        return "redirect:/listaDestino";
        }
 
    @RequestMapping(value = { "/compra" }, method = RequestMethod.GET)
    public String compraView(Model model, @RequestParam("idcompra") String idcompra) {
        CompraInfo compraInfo = null;
        if (idcompra != null) {
            compraInfo = this.compraDAO.getCompraInfo(idcompra);
            }
        if (compraInfo == null) {
            return "redirect:/listaCompra";
            }
        List<ReservaInfo> reservas = this.compraDAO.listReservasInfos(idcompra);
        compraInfo.setReservas(reservas);
        model.addAttribute("compraInfo", compraInfo);
        return "compra";
        }
    }
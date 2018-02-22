package com.basico.viajes.controller;
 
import java.io.IOException;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.basico.viajes.dao.CompraDAO;
import com.basico.viajes.dao.DestinoDAO;
import com.basico.viajes.entity.Destino;
import com.basico.viajes.model.CarritoInfo;
import com.basico.viajes.model.CompradorInfo;
import com.basico.viajes.model.DestinoInfo;
import com.basico.viajes.model.PaginationResult;
import com.basico.viajes.util.Utiles;
import com.basico.viajes.validator.CompradorInfoValidator;
 
@Controller
// Enable Hibernate Transaction.
@Transactional
// Need to use RedirectAttributes
@EnableWebMvc
public class MainController {
 
    @Autowired
    private CompraDAO compraDAO;
 
    @Autowired
    private DestinoDAO destinoDAO;
 
    @Autowired
    private CompradorInfoValidator compradorInfoValidator;
 
    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
            }
        System.out.println("Target=" + target);
 
        // For Cart Form.
        // (@ModelAttribute("cartForm") @Validated CartInfo cartForm)
        if (target.getClass() == CarritoInfo.class) {
        	
        	}
        // For Customer Form.
        // (@ModelAttribute("customerForm") @Validated CustomerInfo
        // customerForm)
        else if (target.getClass() == CompradorInfo.class) {
            dataBinder.setValidator(compradorInfoValidator);
            }
        }
 
    @RequestMapping("/403")
    public String accesoDenegado() {
        return "/403";
        }
 
    @RequestMapping("/")
    public String home() {
        return "index";
        }

    @RequestMapping({ "/listaDestino" })
    public String listaDestinoHandler(Model model, //
            @RequestParam(value = "name", defaultValue = "") String likeName,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;
 
        PaginationResult<DestinoInfo> result = destinoDAO.queryDestinos(page, maxResult, maxNavigationPage, likeName);
        model.addAttribute("paginationDestinos", result);
        return "listaDestino";
        }
 
    @RequestMapping({ "/compraDestino" })
    public String listaDestinoHandler(HttpServletRequest request, Model model, //
    		@RequestParam(value = "iddestino", defaultValue = "") String iddestino) {
        Destino destino = null;
        if (iddestino != null && iddestino.length() > 0) {
            destino = destinoDAO.findDestino(iddestino);
            }
        if (destino != null) {
 
            // Cart info stored in Session.
            CarritoInfo carritoInfo = Utiles.getSesionCarrito(request);
            DestinoInfo destinoInfo = new DestinoInfo(destino);
 
            carritoInfo.addDestino(destinoInfo, 1);
            }
        // Redirect to shoppingCart page.
        return "redirect:/carritoCompra";
        }
 
    @RequestMapping({ "/eliminarDestinoCarritoCompra" })
    public String removeDestinoHandler(HttpServletRequest request, Model model, //
            @RequestParam(value = "iddestino", defaultValue = "") String iddestino) {
        Destino destino = null;
        if (iddestino != null && iddestino.length() > 0) {
            destino = destinoDAO.findDestino(iddestino);
            }
        if (destino != null) {
            // Cart Info stored in Session.
            CarritoInfo carritoInfo = Utiles.getSesionCarrito(request);
            DestinoInfo destinoInfo = new DestinoInfo(destino);
            carritoInfo.removeDestino(destinoInfo);
            }
        // Redirect to shoppingCart page.
        return "redirect:/carritoCompra";
        }
 
    // POST: Update quantity of products in cart.
    @RequestMapping(value = { "/carritoCompra" }, method = RequestMethod.POST)
    public String carritoCompraUpdateQty(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("carritoForm") CarritoInfo carritoForm) {
 
        CarritoInfo carritoInfo = Utiles.getSesionCarrito(request);
        carritoInfo.updateCantidad(carritoForm);
        return "redirect:/carritoCompra";
        }
 
    // GET: Show Cart
    @RequestMapping(value = { "/carritoCompra" }, method = RequestMethod.GET)
    public String carritoCompraHandler(HttpServletRequest request, Model model) {
        CarritoInfo miCarrito = Utiles.getSesionCarrito(request);
        model.addAttribute("carritoForm", miCarrito);
        return "carritoCompra";
        }
 
    // GET: Enter customer information.
    @RequestMapping(value = { "/carritoCompraComprador" }, method = RequestMethod.GET)
    public String carritoCompraCompradorForm(HttpServletRequest request, Model model) {
        CarritoInfo carritoInfo = Utiles.getSesionCarrito(request);
        // Cart is empty.
        if (carritoInfo.isEmpty()) {
            return "redirect:/carritoCompra";
            }
 
        CompradorInfo compradorInfo = carritoInfo.getCompradorInfo();
        if (compradorInfo == null) {
            compradorInfo = new CompradorInfo();
            }
        model.addAttribute("compradorForm", compradorInfo);
        return "carritoCompraComprador";
        }
 
    // POST: Save customer information.
    @RequestMapping(value = { "/carritoCompraComprador" }, method = RequestMethod.POST)
    public String carritoCompraCompradorSave(HttpServletRequest request, //
            Model model, //
            @ModelAttribute("compradorForm") @Validated CompradorInfo compradorForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
  
        // If has Errors.
        if (result.hasErrors()) {
            compradorForm.setValid(false);
            // Forward to reenter customer info.
            return "carritoCompraComprador";
            }
 
        compradorForm.setValid(true);
        CarritoInfo carritoInfo = Utiles.getSesionCarrito(request);
 
        carritoInfo.setCompradorInfo(compradorForm);
 
        // Redirect to Confirmation page.
        return "redirect:/confirmacionCarritoCompra";
    }
 
    // GET: Review Cart to confirm.
    @RequestMapping(value = { "/confirmacionCarritoCompra" }, method = RequestMethod.GET)
    public String confirmacionCarritoCompraReview(HttpServletRequest request, Model model) {
        CarritoInfo carritoInfo = Utiles.getSesionCarrito(request);
        if (carritoInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/carritoCompra";
            }
        else if (!carritoInfo.isValidComprador()) {
            // Enter customer info.
            return "redirect:/carritoCompraComprador";
            }
        return "confirmacionCarritoCompra";
        }
 
    // POST: Send Cart (Save).
    @RequestMapping(value = { "/confirmacionCarritoCompra" }, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String confirmacionCarritoCompraSave(HttpServletRequest request, Model model) {
        CarritoInfo carritoInfo = Utiles.getSesionCarrito(request);
         // Cart have no products.
        if (carritoInfo.isEmpty()) {
            // Redirect to shoppingCart page.
            return "redirect:/carritoCompra";
            }
        else if (!carritoInfo.isValidComprador()) {
            // Enter customer info.
            return "redirect:/carritoCompraComprador";
            }
        try {
        	compraDAO.saveCompra(carritoInfo);
            }
        catch (Exception e) {
            // Need: Propagation.NEVER?
            return "confirmacionCarritoCompra";
            }
        // Remove Cart In Session.
        Utiles.eliminaSesionCarrito(request); 
        // Store Last ordered cart to Session.
        Utiles.guardaUltimaCompraSesionCarrito(request, carritoInfo);
        // Redirect to successful page.
        return "redirect:/finalizacionCarritoCompra";
    }
 
    @RequestMapping(value = { "/finalizacionCarritoCompra" }, method = RequestMethod.GET)
    public String finalizacionCarritoCompra(HttpServletRequest request, Model model) {
        CarritoInfo ultimaCompraCarrito = Utiles.getUltimaCompraSesionCarrito(request);
        if (ultimaCompraCarrito == null) {
            return "redirect:/carritoCompra";
            }
        return "finalizacionCarritoCompra";
        }
 
    @RequestMapping(value = { "/imagenDestino" }, method = RequestMethod.GET)
    public void imagenDestino(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam("iddestino") String iddestino) throws IOException {
        Destino destino = null;
        if (iddestino != null) {
            destino = this.destinoDAO.findDestino(iddestino);
            }
        if (destino != null && destino.getImagen() != null) {
            response.setContentType("imagen/jpeg, imagen/jpg, imagen/png, imagen/gif");
            response.getOutputStream().write(destino.getImagen());
            }
        response.getOutputStream().close();
        }
    }
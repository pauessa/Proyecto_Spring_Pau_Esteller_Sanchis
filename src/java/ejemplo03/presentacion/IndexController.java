/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo03.presentacion;

import com.fpmislata.persistencia.dao.BussinessException;
import com.fpmislata.persistencia.dao.BussinessMessage;
import ejemplo03.dominio.Cine;
import ejemplo03.persistencia.dao.CineDAO;
import ejemplo03.persistencia.dao.PeliculaDAO;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lorenzo González
 */
@Controller
public class IndexController {


    @RequestMapping({"/index.html"})
    public ModelAndView listarIndex(HttpServletRequest request, HttpServletResponse response) {
        String viewName;
            viewName = "index";
       return new ModelAndView(viewName);
    }
    
   
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo03.presentacion;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

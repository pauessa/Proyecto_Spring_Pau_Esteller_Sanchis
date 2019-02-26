/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo03.presentacion;

import com.fpmislata.persistencia.dao.BussinessException;
import com.fpmislata.persistencia.dao.BussinessMessage;
import ejemplo03.dominio.Cine;
import ejemplo03.dominio.Sesion;
import ejemplo03.persistencia.dao.CineDAO;
import ejemplo03.persistencia.dao.SesionDAO;
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
public class SesionController {

    @Autowired
    private SesionDAO sesionDAO;
    
    @RequestMapping({"/sesion.html"})
    public ModelAndView listarSesions(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            List<Sesion> sesions=sesionDAO.findAll();
            model.put("sesions",sesions);
            viewName = "SesionsLista";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/sesion.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }
    
    @RequestMapping({"/sesion/newForInsert"})
    public ModelAndView newForInsert(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            Sesion sesion = sesionDAO.create();
            model.put("formOperation", FormOperation.Insert);
            model.put("sesion", sesion);
            viewName = "sesion";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/sesion.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/sesion/readForUpdate"})
    public ModelAndView readForUpdate(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un id_Cine válido"));
            }

            Sesion sesion = sesionDAO.get(id);
            if (sesion == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe el profesor con id_Cine=" + id));
            }
            model.put("formOperation", FormOperation.Update);
            model.put("sesion", sesion);
            viewName = "sesion";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/sesion.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/sesion/readForDelete"})
    public ModelAndView readForDelete(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un sesion válido"));
            }

            Sesion sesion = sesionDAO.get(id);
            if (sesion == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe el profesor con sesion=" + id));
            }
            model.put("formOperation", FormOperation.Delete);
            model.put("sesion", sesion);
            viewName = "sesion";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/sesion.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/sesion/insert.html"})
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        Sesion sesion = null;
        try {
            sesion = sesionDAO.create();
            sesion.setDiaSesion(request.getParameter("diaSesion"));
            sesion.setHorasSesion(request.getParameter("horasSesion"));
            


            sesionDAO.saveOrUpdate(sesion);

            viewName = "redirect:/sesion.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            if (sesion!=null) {
                sesion.setIdSesion(0);
            }
            model.put("sesion", sesion);
            model.put("formOperation", FormOperation.Insert);
            viewName = "sesion";
        }



        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/sesion/update.html"})
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        Sesion sesion = null;
        try {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un sesion válido"));
            }
            sesion = sesionDAO.get(id);
            if (sesion == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe el profesor."));
            }
           sesion.setDiaSesion(request.getParameter("diaSesion"));
            sesion.setHorasSesion(request.getParameter("horasSesion"));

            sesionDAO.saveOrUpdate(sesion);

            viewName = "redirect:/sesion.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("sesion", sesion);
            model.put("formOperation", FormOperation.Update);
            viewName = "sesion";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/sesion/delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        Sesion sesion=null;
        try {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            sesion = sesionDAO.get(id);
            if (sesion == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe el profesor a borrar"));
            }

            sesionDAO.delete(id);

            viewName = "redirect:/sesion.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("sesion", sesion);
            model.put("formOperation", FormOperation.Delete);
            viewName = "sesion";
        }

        return new ModelAndView(viewName, model);
    }

}

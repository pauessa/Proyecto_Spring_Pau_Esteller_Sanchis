/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo03.presentacion;

import com.fpmislata.persistencia.dao.BussinessException;
import com.fpmislata.persistencia.dao.BussinessMessage;
import ejemplo03.dominio.Cine;
import ejemplo03.persistencia.dao.CineDAO;
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
public class CineController {

    @Autowired
    private CineDAO cineDAO;
    
    @RequestMapping({"/index.html"})
    public ModelAndView listarCines(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            List<Cine> cines=cineDAO.findAll();
            model.put("cines",cines);
            viewName = "cinesLista";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }
    
    @RequestMapping({"/cine/newForInsert"})
    public ModelAndView newForInsert(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            Cine cine = cineDAO.create();
            model.put("formOperation", FormOperation.Insert);
            model.put("cine", cine);
            viewName = "cine";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/cine/readForUpdate"})
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

            Cine cine = cineDAO.get(id);
            if (cine == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe el profesor con id_Cine=" + id));
            }
            model.put("formOperation", FormOperation.Update);
            model.put("cine", cine);
            viewName = "cine";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/cine/readForDelete"})
    public ModelAndView readForDelete(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un id_Cine válido"));
            }

            Cine cine = cineDAO.get(id);
            if (cine == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe el profesor con id_Cine=" + id));
            }
            model.put("formOperation", FormOperation.Delete);
            model.put("cine", cine);
            viewName = "cine";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/cine/insert.html"})
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        Cine cine = null;
        try {
            cine = cineDAO.create();
            cine.setDirecionCine(request.getParameter("direcionCine"));
            cine.setMunicipioCine(request.getParameter("minicipioCine"));
            cine.setNombreCine(request.getParameter("nombreCine"));
            //cine.setSesions(request.getParameter("sesion"));


            cineDAO.saveOrUpdate(cine);

            viewName = "redirect:/index.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            if (cine!=null) {
                cine.setIdCine(0);
            }
            model.put("cine", cine);
            model.put("formOperation", FormOperation.Insert);
            viewName = "cine";
        }



        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/cine/update.html"})
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        Cine cine = null;
        try {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un id_Cine válido"));
            }
            cine = cineDAO.get(id);
            if (cine == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe el profesor."));
            }
           cine.setDirecionCine(request.getParameter("direcionCine"));
            cine.setMunicipioCine(request.getParameter("minicipioCine"));
            cine.setNombreCine(request.getParameter("nombreCine"));

            cineDAO.saveOrUpdate(cine);

            viewName = "redirect:/index.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("cine", cine);
            model.put("formOperation", FormOperation.Update);
            viewName = "cine";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/cine/delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        Cine cine=null;
        try {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            cine = cineDAO.get(id);
            if (cine == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe el profesor a borrar"));
            }

            cineDAO.delete(id);

            viewName = "redirect:/index.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("cine", cine);
            model.put("formOperation", FormOperation.Delete);
            viewName = "cine";
        }

        return new ModelAndView(viewName, model);
    }

}

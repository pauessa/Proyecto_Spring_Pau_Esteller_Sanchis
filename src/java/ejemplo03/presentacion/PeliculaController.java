/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo03.presentacion;

import com.fpmislata.persistencia.dao.BussinessException;
import com.fpmislata.persistencia.dao.BussinessMessage;
import ejemplo03.dominio.Cine;
import ejemplo03.dominio.Pelicula;
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
public class PeliculaController {

    @Autowired
    private PeliculaDAO peliculaDAO;
    
    @RequestMapping({"/pelicula.html"})
    public ModelAndView listarPelicula(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            List<Pelicula> pelicules=peliculaDAO.findAll();
            model.put("pelicules",pelicules);
            viewName = "peliculaLista";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/pelicula.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }
    
    @RequestMapping({"/pelicula/newForInsert"})
    public ModelAndView newForInsert(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            Pelicula pelicula = peliculaDAO.create();
            model.put("formOperation", FormOperation.Insert);
            model.put("pelicula", pelicula);
            viewName = "pelicula";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/pelicula.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/pelicula/readForUpdate"})
    public ModelAndView readForUpdate(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un id válido"));
            }

            Pelicula pelicula = peliculaDAO.get(id);
            if (pelicula == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe el profesor con id=" + id));
            }
            model.put("formOperation", FormOperation.Update);
            model.put("pelicula", pelicula);
            viewName = "pelicula";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/pelicula.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/pelicula/readForDelete"})
    public ModelAndView readForDelete(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un id válido"));
            }

            Pelicula pelicula = peliculaDAO.get(id);
            if (pelicula == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe el profesor con id=" + id));
            }
            model.put("formOperation", FormOperation.Delete);
            model.put("pelicula", pelicula);
            viewName = "pelicula";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/pelicula.html");
            viewName = "error";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/pelicula/insert.html"})
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        Pelicula pelicula = null;
        try {
            pelicula = peliculaDAO.create();
            pelicula.setTituloPelicula(request.getParameter("titulopelicula"));
            pelicula.setDirectorPelicula(request.getParameter("directorpelicula"));
            pelicula.setInterpretePelicula(request.getParameter("interpretepelicula"));
            pelicula.setCategoriaPelicula(request.getParameter("categoriapelicula"));

            
            //cine.setSesions(request.getParameter("sesion"));


            peliculaDAO.saveOrUpdate(pelicula);

            viewName = "redirect:/pelicula.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            if (pelicula!=null) {
                pelicula.setIdPelicula(0);
            }
            model.put("pelicula", pelicula);
            model.put("formOperation", FormOperation.Insert);
            viewName = "pelicula";
        }



        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/pelicula/update.html"})
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        Pelicula pelicula = null;
        try {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un id válido"));
            }
            pelicula = peliculaDAO.get(id);
            if (pelicula == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe el profesor."));
            }
           pelicula.setTituloPelicula(request.getParameter("titulopelicula"));
            pelicula.setDirectorPelicula(request.getParameter("directorpelicula"));
            pelicula.setInterpretePelicula(request.getParameter("interpretepelicula"));
            pelicula.setCategoriaPelicula(request.getParameter("categoriapelicula"));

            peliculaDAO.saveOrUpdate(pelicula);

            viewName = "redirect:/pelicula.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("pelicula", pelicula);
            model.put("formOperation", FormOperation.Update);
            viewName = "pelicula";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/pelicula/delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        Pelicula pelicula=null;
        try {
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            pelicula = peliculaDAO.get(id);
            if (pelicula == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe el profesor a borrar"));
            }

            peliculaDAO.delete(id);

            viewName = "redirect:/pelicula.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("pelicula", pelicula);
            model.put("formOperation", FormOperation.Delete);
            viewName = "pelicula";
        }

        return new ModelAndView(viewName, model);
    }

}

package ejemplo03.persistencia.dao.impl;

import com.fpmislata.persistencia.dao.impl.GenericDAOImplHibernate;
import ejemplo03.dominio.Pelicula;
import ejemplo03.persistencia.dao.PeliculaDAO;

public class PeliculaDAOImplCartelera extends GenericDAOImplHibernate<Pelicula,Integer> implements  PeliculaDAO {

}

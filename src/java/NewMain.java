
import com.proyecto.POJOS.Cultivo;
import com.proyecto.POJOS.Historialrecomendacion;
import com.proyecto.POJOS.Planta;
import com.proyecto.POJOS.Recomendacion;
import com.proyecto.dao.DaoRecomendacion;
import com.proyecto.impl.DaoRecomendacionCuidadoImpl;
import com.proyecto.util.HibernateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FAMILIA
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Query consulta = sesion.createSQLQuery(" SELECT planta.nombre,planta.dias_a_cosechar,cultivo.fecha_siembra\n"
                + "FROM usuario\n"
                + "INNER JOIN huerto   on usuario.id_usuario  = huerto.id_usuario\n"
                + "INNER JOIN cultivo  on huerto.id  = cultivo.id_huerto\n"
                + "INNER JOIN planta   on cultivo.id_Planta  = planta.id\n"
                + "where usuario.id_usuario =:id_usuario");
        consulta.setParameter("id_usuario", 3);
        List<Object[]> listaBusqueda = consulta.list();
        List<Cultivo> lista = new ArrayList<>();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        for (Object[] row : listaBusqueda) {
            Cultivo cultivo = new Cultivo();
            String strFecha = row[2].toString();
            Date fecha = null;
            try {
                fecha = formatoDelTexto.parse(strFecha);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            Planta planta = new Planta();
            planta.setNombre(row[0].toString());
            planta.setDiasACosechar(Integer.parseInt(row[1].toString()));
            cultivo.setPlanta(planta);
            cultivo.setFechaSiembra(fecha);
            cultivo.setFechaInicialCosecha(NewMain.fechaCosecha(fecha, Integer.parseInt(row[1].toString())));
            lista.add(cultivo);
        }
        for(Cultivo cultivo : lista) {
            System.err.println(cultivo.getFechaSiembra()+" "+cultivo.getFechaInicialCosecha()+" "+ cultivo.getPlanta().getNombre());
            
        } */
        
        DaoRecomendacion dao = new DaoRecomendacionCuidadoImpl();
        Historialrecomendacion historial = new Historialrecomendacion();
        Recomendacion recomendacion = (Recomendacion) dao.crearRecomendacion(3, 1); 
        System.err.println(recomendacion.getDescripcion());
        
       
    }
    
    public static Date fechaCosecha(Date fecha, int dias) {
        if (dias == 0) {
            return fecha;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }
    
}

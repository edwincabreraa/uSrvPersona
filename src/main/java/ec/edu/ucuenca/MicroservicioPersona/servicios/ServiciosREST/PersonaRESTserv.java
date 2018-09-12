package ec.edu.ucuenca.MicroservicioPersona.servicios.ServiciosREST;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ucuenca.MicroservicioPersona.dao.PersonaDAO;
import ec.edu.ucuenca.MicroservicioPersona.dom.RespuestaJSON;
import ec.edu.ucuenca.MicroservicioPersona.servicios.PersonaServicios;

@RestController
public class PersonaRESTserv {
	@Autowired
    private PersonaDAO udao;
    @Autowired
    PersonaServicios usuarioSrv;
    private static final Logger logger = Logger.getLogger(PersonaRESTserv.class.getName());
	public PersonaRESTserv() {
		// TODO Auto-generated constructor stub
	}
    
  
    @RequestMapping(value = "/crear_doctor", method = RequestMethod.GET)
    public RespuestaJSON crear_doctor(@RequestParam String cedula, 
                                 @RequestParam String nombre,
                                 @RequestParam String email,
                                 @RequestParam String esp,
                                 @RequestParam String pw) throws SQLException{
        RespuestaJSON isCreado = usuarioSrv.registrarDoctor(cedula, nombre, email, esp, pw);
        logger.log(Level.INFO, "Resultado /crear_doctor : ["+ isCreado.getMensaje() + "] --- Consulta: /crear_usuario?cedula="+cedula+"&nombre="+nombre+"&email="+ email + "&esp="+esp);
        return isCreado;
    }
    @RequestMapping(value = "/getPacienteInfo", method = RequestMethod.GET)
    public RespuestaJSON get_Paciente_Info(@RequestParam String email) throws SQLException{
        RespuestaJSON isCreado = usuarioSrv.getPacienteInfo(email);
        logger.log(Level.INFO, "Resultado /getPacienteInfo : ["+ isCreado.getMensaje() + "] --- Consulta: /getPacienteInfo?email="+ email);
        return isCreado;
    }
    @RequestMapping(value = "/crear_paciente", method = RequestMethod.GET)
    public RespuestaJSON crear_paciente(@RequestParam String cedula, 
                                 @RequestParam String nombre,
                                 @RequestParam String email,
                                 @RequestParam Integer edad,
                                 @RequestParam String pw) throws SQLException{
        RespuestaJSON isCreado = usuarioSrv.registrarPaciente(cedula, nombre, email, edad, pw);
        logger.log(Level.INFO, "Resultado /crear_paciente : ["+ isCreado.getMensaje() + "] --- Consulta: /crear_usuario?cedula="+cedula+"&nombre="+nombre+"&email="+ email + "&edad="+edad);
        return isCreado;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public RespuestaJSON login(@RequestParam String usuario, @RequestParam String password) throws SQLException{
        RespuestaJSON isCreado = usuarioSrv.loginUsuario(usuario, password);
        logger.log(Level.INFO, "Resultado /login : ["+ isCreado.getMensaje() + "] --- Consulta: /login?usuario="+usuario);
        return isCreado;
    }    
     

    
    @RequestMapping(value = "/get_pacientes_doctor", method = RequestMethod.GET)
    public RespuestaJSON get_pacientes_doctor(@RequestParam String email) throws SQLException{
        RespuestaJSON respuestaUsuarioId = usuarioSrv.consultarPacientes(email);
        if(!respuestaUsuarioId.isEstado()){
            return respuestaUsuarioId;
        }
        logger.log(Level.INFO, "Resultado /get_pacientes_doctor : ["+ respuestaUsuarioId.getMensaje() + "] --- Consulta: /get_cursos_usuario?idUsuario="+email);
        return respuestaUsuarioId;
    }  
    
    @RequestMapping(value = "/get_doctores_paciente", method = RequestMethod.GET)
    public RespuestaJSON get_doctores_paciente(@RequestParam String email) throws SQLException{
        RespuestaJSON respuestaUsuarioId = usuarioSrv.consultarPacientes(email);
        if(!respuestaUsuarioId.isEstado()){
            return respuestaUsuarioId;
        }
        logger.log(Level.INFO, "Resultado /get_pacientes_doctor : ["+ respuestaUsuarioId.getMensaje() + "] --- Consulta: /get_cursos_usuario?idUsuario="+email);
        return respuestaUsuarioId;
    }  
    
    @RequestMapping(value = "/get_pulso_paciente", method = RequestMethod.GET)
    public RespuestaJSON get_pulso_paciente(@RequestParam String email) throws SQLException{
        RespuestaJSON respuestaUsuarioId = usuarioSrv.gelPulsoPaciente(email);
        if(!respuestaUsuarioId.isEstado()){
            return respuestaUsuarioId;
        }
        logger.log(Level.INFO, "Resultado /get_pacientes_doctor : ["+ respuestaUsuarioId.getMensaje() + "] --- Consulta: /get_cursos_usuario?idUsuario="+email);
        return respuestaUsuarioId;
    }  
    
    @RequestMapping(value = "/set_pulso_paciente", method = RequestMethod.GET)
    public RespuestaJSON set_pulso_paciente(@RequestParam String email, @RequestParam Integer pulso) throws SQLException{
        RespuestaJSON respuestaUsuarioId = usuarioSrv.setPulsoPaciente(email, pulso);
        if(!respuestaUsuarioId.isEstado()){
            return respuestaUsuarioId;
        }
        logger.log(Level.INFO, "Resultado /set_pulso_paciente : ["+ respuestaUsuarioId.getMensaje() + "] --- Consulta: /set_pulso_paciente="+email);
        return respuestaUsuarioId;
    }  
    
   
    @RequestMapping(value = "/set_pasos_paciente", method = RequestMethod.GET)
    public RespuestaJSON set_pasos_paciente(@RequestParam String email, @RequestParam Integer pulso) throws SQLException{
        RespuestaJSON respuestaUsuarioId = usuarioSrv.setPasosPaciente(email, pulso);
        if(!respuestaUsuarioId.isEstado()){
            return respuestaUsuarioId;
        }
        logger.log(Level.INFO, "Resultado /set_pasos_paciente : ["+ respuestaUsuarioId.getMensaje() + "] --- Consulta: /set_pasos_paciente="+email);
        return respuestaUsuarioId;
    }  
    

}

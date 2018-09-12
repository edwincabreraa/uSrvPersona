package ec.edu.ucuenca.MicroservicioPersona.servicios;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.hibernate.collection.internal.PersistentSortedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ec.edu.ucuenca.MicroservicioPersona.dao.PersonaDAO;
import ec.edu.ucuenca.MicroservicioPersona.dom.Doctor;
import ec.edu.ucuenca.MicroservicioPersona.dom.Paciente;
import ec.edu.ucuenca.MicroservicioPersona.dom.Persona;
import ec.edu.ucuenca.MicroservicioPersona.dom.RespuestaJSON;

@Service
public class PersonaServicios {
	@Autowired
    PersonaDAO usuarioDao;
	public PersonaServicios() {
		// TODO Auto-generated constructor stub
	}
	public RespuestaJSON existeIdUsuario(Long idUsuario) throws SQLException{
        RespuestaJSON respuesta = new RespuestaJSON();
        boolean exists = usuarioDao.isUser(idUsuario);

        if (exists == false) {
            respuesta.setEstado(false);
            respuesta.setMensaje("No es usuario del sistema");
            respuesta.setRespuesta(false);
            return respuesta;
        }
        return null;
    }

    String mensaje = "";
    
    /*@Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }*/
    public RespuestaJSON registrarPaciente(String cedula, String nombre, String email, Integer edad, String pw) throws SQLException {
        RespuestaJSON respuesta = new RespuestaJSON();
        Paciente existente;
        mensaje = "";
        System.out.println("entro");
        if (usuarioDao.hasEmail(email)) {
            mensaje = "El correo ya está registrado en M&IoT";
        } else if (usuarioDao.hasUser(cedula)) {
            mensaje += "El nick registrado ya existe en M&IoT";
        } else if (Persona.validateEmail(email)) {
            //pw =//passwordEncoder.encode(pw);
            if (usuarioDao.addPaciente(cedula, nombre, edad, email, pw)){
                mensaje = "El usuario se ha agregado correctamente";
                respuesta.setEstado(true);
                respuesta.setMensaje(mensaje);
                respuesta.setRespuesta(true);
                return respuesta;
            } else {
                mensaje = "No se ha podido registrar el usuario";
            }
        } else {
            //   existente=new Usuario(null,nick,nombre,email,idioma,pw);
            mensaje = "Ingrese un correo válido ";
        }
        respuesta.setEstado(false);
        respuesta.setMensaje(mensaje);
        respuesta.setRespuesta(false);
        return respuesta;
    }
    
    public RespuestaJSON registrarDoctor(String cedula, String nombre, String email, String esp, String pw) throws SQLException {
        RespuestaJSON respuesta = new RespuestaJSON();
        Paciente existente;
        mensaje = "";
        if (usuarioDao.hasEmail(email)) {
            mensaje = "El correo ya está registrado en M&IoT";
        } else if (usuarioDao.hasUser(cedula)) {
            mensaje += "El nick registrado ya existe en M&IoT";
        } else if (Persona.validateEmail(email)) {
          //  pw = passwordEncoder.encode(pw);
            if (usuarioDao.addDoctor(cedula, nombre, email, pw, esp)){
                mensaje = "El usuario se ha agregado correctamente";
                respuesta.setEstado(true);
                respuesta.setMensaje(mensaje);
                respuesta.setRespuesta(true);
                return respuesta;
            } else {
                mensaje = "No se ha podido registrar el usuario";
            }
        } else {
            //   existente=new Usuario(null,nick,nombre,email,idioma,pw);
            mensaje = "Ingrese un correo válido ";
        }
        respuesta.setEstado(false);
        respuesta.setMensaje(mensaje);
        respuesta.setRespuesta(false);
        return respuesta;
    }

    
	public RespuestaJSON loginUsuario(String usuario, String pw) throws SQLException {
        RespuestaJSON respuesta = new RespuestaJSON();
        mensaje = "";
        Persona d=usuarioDao.getUserByMail(usuario);
        if (d!=null) {
        	if(d.getPw().equals(pw)){
        		System.out.println("existe: "+d);
        		if(usuarioDao.getPacienteById(d.getIdUsuario())!=null) {
        			System.out.println("es Paciente");
                	mensaje = "El usuario ha ingresado al Sistema";
                    respuesta.setEstado(true);
                    respuesta.setMensaje(mensaje);
                    respuesta.setRespuesta(true); 
        		}else {
        		System.out.println("es Doctor");
        		mensaje = "El usuario ha ingresado al Sistema";
                respuesta.setEstado(true);
                respuesta.setMensaje(mensaje);
                respuesta.setRespuesta(false); //false doctor
        		}
        	}
        	else {
                mensaje = "USUARIO/CONTRASEÑA invalidos";
                respuesta.setEstado(false);
                respuesta.setMensaje(mensaje);
                respuesta.setRespuesta(false);
            }
        }
        
        System.out.println("Mensaje: "+mensaje);
        return respuesta;
	}
	
	public RespuestaJSON consultarPacientes(String email){
		RespuestaJSON respuesta = new RespuestaJSON();
        mensaje = "";
        List<Paciente> p=usuarioDao.getPacientesByDoctor(email);
        if (p.size()>0) {
        	respuesta.setEstado(true);
            respuesta.setMensaje("Éxito");
            respuesta.setRespuesta(p);
            return respuesta;
        }
		return null;
	}
	/*public RespuestaJSON consultarDocs(String email){
		RespuestaJSON respuesta = new RespuestaJSON();
        mensaje = "";
        List<Doctor> d=usuarioDao.getMedicoByPaciente(email);
        if (d.size()>0) {
        	respuesta.setEstado(true);
            respuesta.setMensaje("Éxito");
            respuesta.setRespuesta(d);
            return respuesta;
        }
		return null;
	}*/
	
	public RespuestaJSON gelPulsoPaciente(String email){
		RespuestaJSON respuesta = new RespuestaJSON();
        mensaje = "";
        Persona persona=usuarioDao.getUserByMail(email);
        Paciente p=usuarioDao.getPacienteById(persona.getIdUsuario());
        if (p!=null) {
        	Integer pulso=p.getFrecuenciaCadiaca();
        	respuesta.setEstado(true);
            respuesta.setMensaje("Éxito");
            respuesta.setRespuesta(pulso);
            return respuesta;
        }
		return null;
	}
	
	public RespuestaJSON setPulsoPaciente(String email, Integer pulso){
		RespuestaJSON respuesta = new RespuestaJSON();
        mensaje = "";
		Boolean resp=usuarioDao.setPulsoPaciente(email, pulso);
        if (resp==true) {
        	respuesta.setEstado(true);
            respuesta.setMensaje("Éxito");
            respuesta.setRespuesta("ok");
            return respuesta;
        }else {
        	respuesta.setEstado(false);
            respuesta.setMensaje("incorrecto");
            respuesta.setRespuesta("no");
            return respuesta;
        }
	}
	public RespuestaJSON setPasosPaciente(String email, Integer pulso){
		RespuestaJSON respuesta = new RespuestaJSON();
        mensaje = "";
		Boolean resp=usuarioDao.setPasosPaciente(email, pulso);
        if (resp==true) {
        	respuesta.setEstado(true);
            respuesta.setMensaje("Éxito");
            respuesta.setRespuesta("ok");
            return respuesta;
        }else {
        	respuesta.setEstado(false);
            respuesta.setMensaje("incorrecto");
            respuesta.setRespuesta("no");
            return respuesta;
        }
	}
	
	public RespuestaJSON getPacienteInfo(String email) {
		// TODO Auto-generated method stubRespuestaJSON respuesta = new RespuestaJSON();
		RespuestaJSON respuesta = new RespuestaJSON();
		mensaje = "";
		Paciente resp=usuarioDao.getPaciente(email);
        if (resp!=null) {
        	respuesta.setEstado(true);
            respuesta.setMensaje("Éxito");
            respuesta.setRespuesta(resp);
            return respuesta;
        }else {
        	respuesta.setEstado(false);
            respuesta.setMensaje("incorrecto");
            respuesta.setRespuesta("no");
            return respuesta;
        }
	}


}

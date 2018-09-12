package ec.edu.ucuenca.MicroservicioPersona.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ec.edu.ucuenca.MicroservicioPersona.dom.Doctor;
import ec.edu.ucuenca.MicroservicioPersona.dom.Paciente;
import ec.edu.ucuenca.MicroservicioPersona.dom.Persona;
import ec.edu.ucuenca.MicroservicioPersona.dom.RespuestaJSON;
import ec.edu.ucuenca.MicroservicioPersona.repository.DoctorRep;
import ec.edu.ucuenca.MicroservicioPersona.repository.PacienteRep;
import ec.edu.ucuenca.MicroservicioPersona.repository.PersonaRep;

@Repository
public class PersonaDAO {
	@Autowired
    private PersonaRep personarep;
	@Autowired
    private PacienteRep prep;
	
	@Autowired
    private DoctorRep drep;
    
    private static final Logger logger = Logger.getLogger(PersonaDAO.class.getName());


	public PersonaDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public Persona isPaciente(String cedula) {
		Persona existente=personarep.findOneByCedula(cedula);
		if(existente==null) {
			existente=personarep.findOneByEmail(cedula);
			System.out.println("ES:"+ existente+ existente.getCedula());
			if(existente!=null) {
				return existente;
			}else
				return null;
		}
		return existente;
	}
	public Boolean setPulsoPaciente(String email, Integer pulso){
        Persona persona=getUserByMail(email);
        Paciente p=getPacienteById(persona.getIdUsuario());
        if (p!=null) {
        	p.setFrecuenciaCadiaca(pulso);
        	prep.save(p);
            return true;
        }
		return false;
	}
	public Boolean setPasosPaciente(String email, Integer pulso){
        Persona persona=getUserByMail(email);
        Paciente p=getPacienteById(persona.getIdUsuario());
        if (p!=null) {
        	p.setPasos(pulso);
        	prep.save(p);
            return true;
        }
		return false;
	}
	public Paciente getPaciente(String email){
        Persona persona=getUserByMail(email);
        Paciente p=getPacienteById(persona.getIdUsuario());
        if (p!=null) {
            return p;
        }
		return null;
	}
	public Doctor isDoctor(String id) {
		Doctor existente=(Doctor)personarep.findOneByCedula(id);
		if(existente==null) {
			existente=(Doctor)personarep.findOneByEmail(id);
			if(existente!=null) {
				System.out.println("pasoe");
				return existente;
			}else
				return null;
		}
		return existente;
	}
	
   
    
    public boolean addPaciente(String cedula,  String nombre,Integer edad,String mail,String pass){       
        Paciente u = new Paciente(cedula,nombre,mail,edad,pass);
        prep.save(u);
        return true;
    }
    public boolean addDoctor(String cedula,  String nombre,String mail,String pass, String esp){       
        Doctor u = new Doctor(cedula,nombre,mail,pass,esp);
        drep.save(u);
        return true;
    }
    
    public boolean updatePacienteFrecuencia(String mail,Integer frecuencia){
        Paciente u = (Paciente) personarep.findOneByEmail(mail);
        u.setFrecuenciaCadiaca(frecuencia);
        prep.save(u);
        return true;
    }
    public boolean updatePacientePasos(String mail,Integer pasos){
        Paciente u = (Paciente) personarep.findOneByEmail(mail);
        u.setPasos(pasos);
        prep.save(u);
        return true;
    }
    
    public Paciente getPacienteById(Long idPersona){
        return prep.findById(idPersona).get();
    }
    
    public Doctor getDoctorById(Long idPersona){
        return drep.findById(idPersona).get();
    }
   
    public Persona getUserByMail(String email){
        return personarep.findOneByEmail(email);
    }
    
    
    public boolean hasUser(String cedula){
        Persona u = personarep.findOneByCedula(cedula);
        if(u == null){
            return false;
        }else{
            return true;
        }
    }
    
    
    public boolean hasEmail(String email){
        Persona u = personarep.findOneByEmail(email);
        if(u == null){
            return false;
        }else{
            return true;
        }
    }
    

    public boolean isUser(Long id) {
        Optional<Persona> p = personarep.findById(id);
        if (p.get().getCedula() == null){
            return false;
        }else{
            return true;
        }
    }
   
    public List<Paciente> getPacientesByDoctor(String email){
        Doctor u = drep.findOneByEmail(email);
        return u.getPacientes();
    }
    
    
}

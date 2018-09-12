package ec.edu.ucuenca.MicroservicioPersona.repository;

import org.springframework.data.repository.CrudRepository;

import ec.edu.ucuenca.MicroservicioPersona.dom.Paciente;
import ec.edu.ucuenca.MicroservicioPersona.dom.Persona;



public interface PersonaRep extends CrudRepository<Persona, Long>{
	Persona findOneByEmail(String email);
	Persona findOneByCedula(String cedula);

}

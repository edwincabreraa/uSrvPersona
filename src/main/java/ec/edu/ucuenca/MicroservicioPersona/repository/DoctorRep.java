package ec.edu.ucuenca.MicroservicioPersona.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ec.edu.ucuenca.MicroservicioPersona.dom.Doctor;
import ec.edu.ucuenca.MicroservicioPersona.dom.Persona;

@Repository
public interface DoctorRep extends CrudRepository<Doctor, Long>{

	Doctor findOneByEmail(String email);
	Doctor findOneByCedula(String cedula);
	Doctor findOneByPacientes_IdUsuarioAndIdUsuario( Long idPaciente,Long idDoctor);

}

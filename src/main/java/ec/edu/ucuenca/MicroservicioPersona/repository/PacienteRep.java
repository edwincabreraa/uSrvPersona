package ec.edu.ucuenca.MicroservicioPersona.repository;

import ec.edu.ucuenca.MicroservicioPersona.dom.Paciente;
import ec.edu.ucuenca.MicroservicioPersona.dom.Persona;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * Lo siguiente que necesitamos es el acceso a la entidad queremos poder 
 * eliminar, crear, editar y buscar elementos en nuestro conjunto de datos, 
 * Spring provee mecanismos para crear repositorios de manera rápida y 
 * sencilla, usando la interface CrudRepository.*/

/*Spring Data JPA nos da la facilidad de no tener que implementar la 
 * interface, en tiempo de ejecución se reconocen los métodos y se 
 * implementan, solo debemos indicar a la interface 
 * CrudRepository<Entidad, ID>, el tipo de la entidad que usaremos y el 
 * tipo del campo id*/

@Repository
public interface PacienteRep extends CrudRepository<Paciente, Long>{
	
	
}

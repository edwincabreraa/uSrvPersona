/**
 * 
 */
package ec.edu.ucuenca.MicroservicioPersona.dom;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Paola
 *
 */
@Entity
@DiscriminatorValue("DOCTOR")
@PrimaryKeyJoinColumn(name="personaId")
public class Doctor extends Persona implements Serializable {
	
	private String especialidad;
	@JsonIgnore
    @ManyToMany
    @JoinTable(
      name="Doctor_Pacientes",
      joinColumns=@JoinColumn(name="doctorId", referencedColumnName="personaId"),
      inverseJoinColumns=@JoinColumn(name="pacienteId", referencedColumnName="personaId"))
	private List<Paciente> pacientes;
	
	
	
	public Doctor(String cedula, String nombre, String email, String pw) {
		super(cedula, nombre, email, pw);
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param idUsuario
	 * @param cedula
	 * @param nombre
	 * @param email
	 * @param pw
	 */
	public Doctor( String cedula, String nombre, String email, String pw, String esp) {
		super(cedula, nombre, email, pw);
		// TODO Auto-generated constructor stub
		this.especialidad=esp;
	}

	
	public List<Paciente> getPacientes() {
		return pacientes;
	}


	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}


	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
}

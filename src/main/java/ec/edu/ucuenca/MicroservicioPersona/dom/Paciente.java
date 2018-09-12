package ec.edu.ucuenca.MicroservicioPersona.dom;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@PrimaryKeyJoinColumn(name="personaId")
@DiscriminatorValue("PACIENTE")
public class Paciente extends Persona implements Serializable {
	
	
    private Integer pasos;
	private Integer frecuenciaCadiaca;
    protected Integer edad;
    
    
    public Paciente() {
    	
    }
	public Paciente(String cedula, String nombre, String email, String pw) {
		super(cedula, nombre, email, pw);
	}

	/**
	 * @param idUsuario
	 * @param cedula
	 * @param nombre
	 * @param email
	 * @param edad
	 * @param pw
	 */
	public Paciente( String cedula, String nombre, String email, Integer edad, String pw) {
		super( cedula, nombre, email, pw);
		this.edad=edad;
		this.pasos=0;
		this.frecuenciaCadiaca=0;
		// TODO Auto-generated constructor stub
	}
	
	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}



	public Integer getPasos() {
		return pasos;
	}

	public void setPasos(Integer pasos) {
		this.pasos = pasos;
	}

	public Integer getFrecuenciaCadiaca() {
		return frecuenciaCadiaca;
	}

	public void setFrecuenciaCadiaca(Integer frecuenciaCadiaca) {
		this.frecuenciaCadiaca = frecuenciaCadiaca;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paciente other = (Paciente) obj;
       /* if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }*/
        return true;
    }
    
}

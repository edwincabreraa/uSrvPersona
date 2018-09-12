package ec.edu.ucuenca.MicroservicioPersona.dom;

import java.io.Serializable;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
/*Recordamos que en esta estrategia tendremos una tabla para los atributos
 *  comunes (los atributos del padre), y una tabla por cada subclase 
 *  (con exclusivamente los atributos nuevos que aporta la subclase). 
 *  Para conseguir un objeto se hará join con las tablas necesarias.*/
@Entity
@Table(name = "Persona")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="DISCRIMINATOR",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("PERSONA")
public class Persona implements Serializable{//UserDetails{
	@SuppressWarnings("compatibilty:3445934866919647762")
	private static final long serialVersionUID=1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="usuario_seq")
    @SequenceGenerator(name="usuario_seq", sequenceName="usuarios_seq")
    @JsonIgnore
    protected Long idUsuario;
	
    @Column(unique=true, length = 90, nullable=false)
    protected String cedula;
    @Column(nullable=false)
    protected String nombre;
    @Column(unique=true, length=90, nullable=false)
    protected String email;
    @Column(unique=true, length = 90, nullable=false)
    protected String pw;
    @Column(name = "activo", nullable = false)
    private boolean activo; 
    
    public Persona() {
    	
    }
	public Persona(String cedula, String nombre, String email, String pw) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.email = email;
		this.pw = pw;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}
	public void setPassword(String pw) {
		this.pw = pw;
	}
	
	//Validar los email de usuario
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public static boolean validateEmail(String email) {
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
 
        // Match the given input against this pattern
        Matcher matcher1 = pattern.matcher(email);
        
       if(matcher1.find()==true){
                 System.out.println("Correcto: "+email);
                 return true;}
       else {
            System.out.println("Error: "+email);
               return false;
       }
 
    }

    /*@Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();	
        return authorities;
    }

	@Override
	@JsonIgnore
	public String getPassword() {
		// TODO Auto-generated method stub
		return pw;
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return activo;
	}
	@JsonIgnore
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    */
    
}
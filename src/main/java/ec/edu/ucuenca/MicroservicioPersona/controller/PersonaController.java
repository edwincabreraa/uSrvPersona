package ec.edu.ucuenca.MicroservicioPersona.controller;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*crearemos un controlador de bienvenida que será responsable de manejar 
 * las GETsolicitudes /greetingdevolviendo el nombre de una Vista, 
 * en este caso, "bienvenida". Una vista es responsable de representar el 
 * contenido HTML.*/

//@Controllerla anotación indica que una clase anotada es a "Controller"
//(por ejemplo, un controlador web).
@Controller
public class PersonaController {
	@RequestMapping("/consultaID")
	public String consultaID(@RequestParam String id_Usuario) {

        return "welcome";
    }
	
	//@RequestMappingla anotación asegura que las HTTPsolicitudes a 
	// /validarLogin se asignan al login()método.
	@RequestMapping(value = "/validarLogin", method = RequestMethod.GET)
    public Boolean login(@RequestParam String id_Usuario, @RequestParam String pw_Usuario) {
    	//logica de inicio de sesion
    	try {
    		
    		 	  		
    		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
    }
	
	@RequestMapping(value = "/registrarUsuario", method = RequestMethod.GET)
    public Boolean registrarUsuario(@RequestParam String id_Usuario, @RequestParam String pw_Usuario) {
    	//logica de crear usuario
    	try {
    		
    		 	  		
    		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
    }
	
	@RequestMapping(value = "/registrarRitmo", method = RequestMethod.GET)
    public Boolean registrarRitmo(@RequestParam String id_Usuario, @RequestParam String pw_Usuario) {
    	//logica de crear usuario
    	try {
    		
    		 	  		
    		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
    }
	
	@RequestMapping(value = "/registrarPasos", method = RequestMethod.GET)
    public Boolean registrarPasos(@RequestParam String id_Usuario, @RequestParam String pw_Usuario) {
    	//logica de crear usuario
    	try {
    		
    		 	  		
    		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
    }
	
	@RequestMapping("/consultaRitmo")
	public String consultaRitmo() {

        return "welcome";
    }
	
}

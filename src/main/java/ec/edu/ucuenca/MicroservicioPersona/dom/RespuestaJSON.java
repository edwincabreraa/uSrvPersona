package ec.edu.ucuenca.MicroservicioPersona.dom;

public class RespuestaJSON {
    private boolean estado;
    private String mensaje;
    private Object respuesta;
    
    
    /**
	 * 
	 */
	

	public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Object respuesta) {
        this.respuesta = respuesta;
    }
    
}

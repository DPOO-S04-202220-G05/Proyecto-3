package Analizador;

public class jugador {

    /* ===========
    * * ATRIBUTOS
       ===========
    */ 

    /*
    * El Nombre del jugador
    */
	private String Nombre;

    /*
    * La posicion en la que juega el jugador
    */
	private String Posicion;

    /*
    * El costo de añadir al jugador al equipo
    */
	private Float Precio;

    /*
    * La posicion en la que juega el jugador
    */
	private Integer Puntos;

    /*
    * El equipo real al que pertenece el jugador.
    */
    private String EquipoOriginal;

    /* ==================================================================================
    * * 							MÉTODOS
       ==================================================================================

	// =======================================================================
	// 						Metodo Constructor
	// =======================================================================

    */

    public jugador(String nombreJugador, String posicion, Float precio, Integer puntos, String Equipo){
        this.Nombre = nombreJugador;
        this.Posicion = posicion;
        this.Precio = precio;
        this.Puntos = puntos;
        this.EquipoOriginal = Equipo;
    }

    // =======================================================================
	// 				Métodos para consultar los atributos
	// =======================================================================

    /**
	 * Consulta el nombre del Jugador
	 * 
	 * @return String nombre
	 */   
    
    public String getNombre() 
    {
    	return Nombre;
    }

    /**
	 * Consulta el nombre del Jugador
	 * 
	 * @return String posicion
	 */   
    
    public String getPosicion() 
    {
    	return Posicion;
    }

    /**
	 * Consulta el precio del Jugador
	 * 
	 * @return float precio
	 */   
    
    public Float getPrecio() 
    {
    	return Precio;
    }

    /**
	 * Consulta los puntos del Jugador
	 * 
	 * @return int puntos
	 */   
    
    public Integer getPuntos() 
    {
    	return Puntos;
    }

    /**
	 * Modifica los puntos del Jugador
	 * 
	 * @param int puntos
	 * @return void
	 */   
    
    public void setPuntos(Integer puntos) 
    {
    	this.Puntos = puntos;
    }

    /**
	 * Consulta el equipo real al que pertenece el jugador
	 * 
	 * @return equipo
	 */   
    
    public String getEquipo() 
    {
    	return EquipoOriginal;
    }

    /**
	 * Modifica el equipo al que pertenece el jugador
	 * 
	 * @param equipo EquipoR
	 * @return void
	 */   
    
    public void setEquipo(String EquipoR) 
    {
    	this.EquipoOriginal = EquipoR;
    }


}

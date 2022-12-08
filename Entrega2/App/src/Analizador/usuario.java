package Analizador;

public class usuario {
	
	/* ===========
	 * * ATRIBUTOS
	   ===========
	*/ 
	
	/*
	 * Nombre del usuario "normal"
	 */
	private String Nombre;
	
	/*
	 * Contraseña del usuario "normal"
	 */
	private String Contraseña;
	
	
	
	/* ==================================================================================
	 * * 							MÉTODOS
	   ==================================================================================
	*/
	
	// =======================================================================
	// 						Metodo Constructor
	// =======================================================================
	
	
	/*
	 * Construye un nuevo usuario "Normal"
	 */
	public usuario(String ElNombre, String LaContraseña)
	{
		this.Nombre = ElNombre;
		this.Contraseña = LaContraseña; 
	}
	
	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************
	
	public String getNombreUsuarioN()
	{
		return Nombre;
	}
	
	public String getContraseñaUsuarioN()
	{
		return Contraseña;
	}
	

}

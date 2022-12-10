package Analizador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Persistencia.paqueteDatos;
public class equipo {

    /* ===========
    * * ATRIBUTOS
       ===========
    */ 

    /*
    * El Nombre del equipo
    */
	private String Nombre;

    /*
    * Datos del dueño del equipo
    */
	private String Dueño;

    /*
    * Capitan del equipo
    */
	private jugador Capitan;
	
    /*
    * Lista de jugadores que forman el equipo
    */
	private ArrayList<jugador> Jugadores;

    /*
    * Lista de jugadores que participaran en el siguiente partido
    */
	private ArrayList<jugador> Titulares;
	
    /*
    * El presupuesto restante para comprar jugadores
    */
	private float Presupuesto;	
	
    /*
    * Los puntos que el equipo ha acumulado por la participacion de los jugadores en partidos
    */
	private int Puntos;
	
    /*
    * Es true si el equipo representa un Equipo del mundo real, es false si el equipo representa un equipo de fantasia
    */
	private boolean Real;
	
    /* ==================================================================================
    * * 							MÉTODOS
       ==================================================================================

	// =======================================================================
	// 						Metodo Constructor
	// =======================================================================

	/**
	 * Construye un nuevo equipo e inicializa sus atributos con la información de
	 * los parámetros.
	 * 
	 * @param NombreE Un string con el nombre del equipo (Pasado por el usuario).
	 * @param DueñoE Un objeto de clase usuario (Se obtiene porque el usuario ya inicio sesion).
	 */	

    public equipo(String NombreE, String DueñoE, boolean RealE, float PresupuestoE)
    {
    	this.Nombre = NombreE;
    	this.Dueño = DueñoE;
    	this.Real = RealE;
    	this.Presupuesto = PresupuestoE;
    	this.Jugadores = new ArrayList<jugador>();
    	this.Titulares = new ArrayList<jugador>();
    	this.Puntos = 0;
    }
	
	
	// =======================================================================
	// 				Métodos para consultar los atributos
	// =======================================================================

	/**
	 * Consulta el nombre del Equipo
	 * 
	 * @return String nombre
	 */   
    
    public String getNombre() 
    {
    	return Nombre;
    }
    
	/**
	 * Consulta los datos del usuario dueño del equipo
	 * 
	 * @return string dueño
	 */   
    
    public String getDueño() 
    {
    	return Dueño;
    }
    
	/**
	 * Consulta los jugadores del equipo
	 * 
	 * @return ArrayList<jugador> jugadores
	 */   
    
    public ArrayList<jugador> getJugadores() 
    {
    	return Jugadores;
    }
    
	/**
	 * Consulta los titulares del equipo
	 * 
	 * @return ArrayList<jugador> titulares
	 */   
    
    public ArrayList<jugador> getTitulares() 
    {
    	return Titulares;
    }
    
	/**
	 * Consulta el presupuesto restante del equipo
	 * 
	 * @return float presupuesto
	 */   
    
    public float getPresupuesto() 
    {
    	return Presupuesto;
    }
    
    
	/**
	 * Consulta los puntos acumulados por el equipo
	 * 
	 * @return int puntos
	 */   
    
    public Integer getPuntos() 
    {
    	return Puntos;
    }
    
	/**
	 * Consulta si el equipo es real o no
	 * 
	 * @return boolean real
	 */   
    
    public boolean getReal() 
    {
    	return Real;
    }
	
	
	// =======================================================================
	// 				Métodos para modificar los atributos
	// =======================================================================

	/**
	 * Modifica el nombre del Equipo
	 * 
	 * @return void
	 */   
    
    public void setNombre(String NombreE) 
    {
    	this.Nombre = NombreE;
    }
    
	/**
	 * Modifica los datos del usuario dueño del equipo
	 * 
	 * @return void
	 */   
    
    public void setDueño(String DueñoE) 
    {
    	this.Dueño = DueñoE;
    }
    
	/**
	 * Modifica los jugadores del equipo
	 * 
	 * @return void
	 */   
    
    public void setJugadores(ArrayList<jugador> JugadoresE) 
    {
    	this.Jugadores = JugadoresE;
    }

	/**
	 * Compra un jugador y lo añade al equipo
	 * 
	 * @return void
	 */   
    
    public void comprarJugador(jugador JugadorE) 
    {
    	float precio = JugadorE.getPrecio();
    	ArrayList<String> nombresj = new ArrayList<>();
    	for(jugador j: this.Jugadores)
    		{
    			nombresj.add(j.getNombre());
    		}
    	String nombredeljugador = JugadorE.getNombre();
    	if (this.Presupuesto>=precio && !nombresj.contains(nombredeljugador))
	    	{
	    		this.addJugador(JugadorE);
	    		this.Presupuesto-=precio;
	    	}
    	
    }


	/**
	 * Vende un jugador por 97% de su precio y lo retira del equipo
	 * 
	 * @return void
	 */   
    
    public void venderJugador(jugador JugadorE) 
    {
    	ArrayList<String> nombresj = new ArrayList<>();
    	for(jugador j: this.Jugadores)
    		{
    			nombresj.add(j.getNombre());
    		}
    	String nombredeljugador = JugadorE.getNombre();
    	if (nombresj.contains(nombredeljugador))
	    	{
	        	float precio = JugadorE.getPrecio();
	    		this.removeJugador(JugadorE);
    			jugador TitularE = JugadorE;
	    		for(jugador j: this.Titulares)
	    		{
	    			if (j.getNombre().equals(nombredeljugador)) 
	    			{
	    	    		TitularE = j;
	    			}
	    		}
	    		this.removeTitular(TitularE);
	    		this.Presupuesto+=precio*0.97;
	    		
	    		
	    		try {
	    			File CarpetaVentas = new File("Ventas");
	    			if(!CarpetaVentas.exists())
	    			{
	    			CarpetaVentas.mkdir();
	    			}
	    			FileWriter csvWriter = new FileWriter("Ventas/" + Math.round(precio)+ "-" + (this.Nombre) +  "-.txt");
					csvWriter.flush();
					csvWriter.close();
	    		
	    		
	    		} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
    }

	/**
	 * Añade un jugador al equipo
	 * 
	 * @return void
	 */   
    
    public void addJugador(jugador JugadorE) 
    {
    	ArrayList<jugador> JugadoresE = this.Jugadores;
    	JugadoresE.add(JugadorE);
    }
    
  

	/**
	 * Remueve un jugador del equipo
	 * 
	 * @return void
	 */   
    
    public void removeJugador(jugador JugadorE) 
    {
    	ArrayList<jugador> JugadoresE = this.Jugadores;
    	JugadoresE.remove(JugadorE);
    }
    
	/**
	 * Modifica los titulares del equipo
	 * 
	 * @return void
	 */   
    
    public void setTitulares(ArrayList<jugador> TitularesE) 
    {
    	this.Titulares = TitularesE;
    }
    
	/**
	 * Elige el capitan del equipo
	 * 
	 * @return void
	 */   
    public void setCapitan(jugador capitanE) 
    {
    	this.Capitan = capitanE;
    }

	/**
	 * Retorna el capitan del equipo
	 * 
	 * @return jugador
	 */   
    public jugador getCapitan() 
    {
    	return this.Capitan;
    }
    
	/**
	 * elige los titulares del equipo y se aniaden a la lista de titulares
	 * 
	 * @return void
	 */   
   
    public void elegirTitular(jugador JugadorTitular)
    {
    	//Nuevo arraylist de titulares
    	ArrayList<String> TitularesEquipo = new ArrayList<>();
    	
    	//Se itera a los titulares que esten y se aniaden al arraylist
    	for(jugador Titular: this.Titulares)
		{
    		TitularesEquipo.add(Titular.getNombre());
		}
    	
    	//Se obtiene el nombre del titular que se eligio
    	String nombredeljugador = JugadorTitular.getNombre();
    	
    	//Si el nombre no esta en el arraylist de titulares
    	if(!TitularesEquipo.contains(nombredeljugador))
    	{
    		//Se aniade a lista de titulares
    		this.addTitular(JugadorTitular);
    	}
    }
    
    
    public void modificarTitular(jugador TitularNuevo, int TitularViejo) 
    {
    	ArrayList<jugador> TitularesEquipo = this.Titulares;
    	if (TitularViejo!=-1)
    	{
    		jugador reemplazado = TitularesEquipo.get(TitularViejo);
    		
    		
    		
    		this.removeTitular(reemplazado);
    		
    		this.addTitular(TitularNuevo);
    	}
    	else
    	{
    		this.addTitular(TitularNuevo);
    	}
    }

	/**
	 * Añade un titular a la lista de titulares
	 * 
	 * @return void
	 */   
    
    public void addTitular(jugador JugadorTitular) 
    {
    	ArrayList<jugador> TitularesEquipo = this.Titulares;
    	TitularesEquipo.add(JugadorTitular);
    }
    
	/**
	 * Remueve un titular del equipo
	 * 
	 * @return void
	 */   
    
    public void removeTitular(jugador JugadorE) 
    {
    	ArrayList<jugador> TitularesE = this.Titulares;
    	TitularesE.remove(JugadorE);
    }
    
	/**
	 * Modifica el presupuesto restante del equipo
	 * 
	 * @return void
	 */   
    
    public void setPresupuesto(float PresupuestoE) 
    {
    	this.Presupuesto = PresupuestoE;
    }
    
	/**
	 * Modifica los puntos acumulados por el equipo
	 * 
	 * @return void
	 */   
    
    public void setPuntos(int PuntosE) 
    {
    	this.Puntos = PuntosE;
    }
    
    public void sumarPuntos(int PuntosE) 
    {
    	this.Puntos += PuntosE;
    }
    
	/**
	 * Modifica si el equipo es real o no
	 * 
	 * @return boolean real
	 */   
    
    public void getReal(boolean RealE) 
    {
    	this.Real = RealE;
    }
    
   
}

package Analizador;

import java.util.ArrayList;
import java.util.HashMap;

public class temporada {
	
	/* ===========
	 * * ATRIBUTOS
	   ===========
	*/ 
	
	/*
	 * Lista de jugadores disponibles para compra en la temporada
	 */
	private HashMap<String, equipo> Equipos;
	
	/*
	 * Tabla de hash que contiene las fechas de los partidos
	 */
	private HashMap<String, partido> Fechas;

	
	
	/* ==================================================================================
	 * * 							MÉTODOS
	   ==================================================================================
	*/
	
	// =======================================================================
	// 						Metodo Constructor
	// =======================================================================
	/**
	 * Construye una nueva temporada e inicializa sus atributos con la información de
	 * los parámetros.
	 * 
	 * @param Aun no se sabe
	 */	

	public temporada(HashMap equipos, HashMap partidos)
	{
		this.Equipos = equipos;
		this.Fechas = partidos;
	}
    
}

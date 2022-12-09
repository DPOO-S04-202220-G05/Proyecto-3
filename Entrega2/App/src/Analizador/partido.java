package Analizador;

import java.io.IOException;
import java.util.*;

public class partido {
	
	/* ===========
	 * * ATRIBUTOS
	   ===========
	*/ 

	/*
	 * Equipo local
	 */
	private HashMap equipoLocal;

	/*
	 * Equipo visitante
	 */
	private equipo equipoVisitante;

	/*
	 * Fecha del partido
	 */
	private String fechaPartido;

	/*
	 * Desempeño equipo local
	 */
	private HashMap<String, desempenio> desemepenioEquipoLocal;

	/*
	 * Desempeño equipo visitante
	 */
	private HashMap<String, desempenio> desemepenioEquipoVisitante;
	
	/* ==================================================================================
	 * * 							MÉTODOS
	   ==================================================================================
	*/
	
	// =======================================================================
	// 						Metodo Constructor
	// =======================================================================
	/**
	 * Construye una nuevo partido e inicializa sus atributos con la información de
	 * los parámetros.
	 * 
	 * @param Aun no se sabe
	 */	

	public partido(HashMap desempenioEquipoLocal, HashMap desempenioEquipoVisitante, String fechaPartido)
	{
		this.desemepenioEquipoLocal = desempenioEquipoLocal;
		this.desemepenioEquipoVisitante = desempenioEquipoVisitante;
		this.fechaPartido = fechaPartido;
	}

	public partido getPartido(HashMap partidos, String fechaPartido)
	{
		partido partido = (partido) partidos.get(fechaPartido);

		return partido;

	}

	public void asignarPuntosJugador() throws IOException
	{
		datos persistencia = new datos();
		Set<String> setnombresl = this.desemepenioEquipoLocal.keySet();
		ArrayList<String> nombresl = new ArrayList<String>(setnombresl);
		for(String nombre: nombresl)
			{
				jugador jugadorf = persistencia.consultarJugador(nombre);
				desempenio desempenioj = this.desemepenioEquipoLocal.get(nombre);
				jugador jugadorj = persistencia.consultarJugador(nombre);
				String posicion = jugadorj.getPosicion();
				int puntosj = desempenioj.calculadoraPuntos(posicion);
				puntosj += jugadorf.getPuntos();
				jugadorj.setPuntos(puntosj);
				persistencia.cargarJugador(jugadorj);
			}
		Set<String> setnombresv = this.desemepenioEquipoVisitante.keySet();
		ArrayList<String> nombresv = new ArrayList<String>(setnombresv);
		for(String nombre: nombresv)
			{
				jugador jugadorf = persistencia.consultarJugador(nombre);
				desempenio desempenioj = this.desemepenioEquipoVisitante.get(nombre);
				jugador jugadorj = persistencia.consultarJugador(nombre);
				String posicion = jugadorj.getPosicion();
				int puntosj = desempenioj.calculadoraPuntos(posicion);
				puntosj += jugadorf.getPuntos();
				jugadorj.setPuntos(puntosj);
				persistencia.cargarJugador(jugadorj);
			}

	}

	public HashMap getdesemepenioEquipoLocal()
	{
		return this.desemepenioEquipoLocal;
	}

	public HashMap getdesemepenioEquipoVisitante()
	{
		return this.desemepenioEquipoVisitante;
	}
    
}

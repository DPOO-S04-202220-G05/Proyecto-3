package Controller;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JOptionPane;

import Analizador.datos;
import Analizador.equipo;
import Analizador.jugador;
import Interfaz.PopUps;

public class controller {
    
    private datos datos = new datos();
    String nombUsu;
    equipo EquipoUsu;

    public void loadSeason(File teamsFile, File seasonFile) throws IOException{
        datos.cargar_datos_temporada(teamsFile, seasonFile);
    }

    public void loadMatch(File matchFile) throws IOException{
        datos.cargarPartido(matchFile);
    }
    
    public void crearEquipo(String nombreE) throws IOException
    {
    	this.EquipoUsu = datos.cargar_datos_de_Equipo(nombreE, nombUsu, false, 100000000);
    }
    
    public String consultarMejorEquipoFecha(int Fecha) throws IOException 
    {
		return datos.obtenerMejorEquipoFecha(Fecha).replace(".txt", "");
    }

	public ArrayList<equipo> consultarEquipos(String userName) throws IOException
    {
    	ArrayList<equipo> teams = datos.consultarEquipos(userName);
    	
		return teams;
    }
    // POSIBLE ELIMINAR
    public ArrayList<jugador> consultarEquipo(String userName) throws IOException
    {
    	equipo team = datos.consultarEquipo(userName);
    	ArrayList<jugador> teamPlayers = consultarJugadores(team);
    	return teamPlayers;
    }
	
    public ArrayList<jugador> consultarJugadores(equipo equipo) throws IOException
    {
    	return datos.mostrarMisJugadores(equipo);
    }

    public ArrayList<jugador> consultarTitulares(equipo team) throws IOException
    {
    	ArrayList<jugador> teamPlayers = datos.verMisTitulares(team);
    	return teamPlayers;
    }
    
    public ArrayList<String> consultarFechasCerradas() throws IOException
    {
		ArrayList<String> cerradas = datos.consultarFechasCerradas();
    	return cerradas;
    }
    
    public HashMap<String, equipo> mostrarEquiposReales() throws IOException
    {
        return datos.mostrarEquiposReales();
    }
    
    public ArrayList<jugador> getJugadoresUsuario(equipo equipo) throws IOException
    {
    	this.EquipoUsu = equipo;
    	if (!EquipoUsu.getNombre().equals(""))
    	{
			return (EquipoUsu.getJugadores());
    	}
    	ArrayList<jugador> vacio = new ArrayList<jugador>();
    	return vacio;
    }

	public ArrayList<jugador> sortJugadoresUsuario(equipo equipo)
	{
		ArrayList<jugador> jugadores = equipo.getJugadores();
		Collections.sort(jugadores, ((o1, o2) -> ((jugador) o2).getPuntos().compareTo(((jugador) o1).getPuntos())));
		return jugadores;
	}
    
    public void venderJugador(String numIntegrante, equipo equipo) throws IOException
    {
    	this.EquipoUsu = equipo;
    	ArrayList<jugador> JugadoresE = EquipoUsu.getJugadores();
    	jugador Integrante = JugadoresE.get(Integer.parseInt(numIntegrante)-1);
    	datos.venderJugador(EquipoUsu, Integrante);
    }

    
    public void comprarJugador(String numIntegrante, ArrayList<jugador> jugadores, equipo equipo) throws IOException
    {
    	this.EquipoUsu = equipo;
    	jugador Comprado = jugadores.get(Integer.parseInt(numIntegrante)-1);
    	float PresuspuestoActual = EquipoUsu.getPresupuesto();
		PopUps popup = new PopUps();
		if(datos.verificarEquipo(EquipoUsu.getJugadores(), Comprado, 15))
		{
			datos.comprarJugador(EquipoUsu, Comprado);
			System.out.println("\n");
			popup.compraExitosa();
		}
		else
		{
			popup.compraExedeLimite();
		}
		
		popup.presupuesto(PresuspuestoActual);;
    	datos.comprarJugador(EquipoUsu, Comprado);
    }
    
    
    /**
     * Crea una nueva cuenta
     * 
     * 
     * @param name
     * @param password
     * @return Un bool, si la creacion fue exitosa o no
     * @throws IOException
     */
    public boolean newAccount(String name, String password) throws IOException
    {
    	
    	boolean creacionValida = false;
    	
    	creacionValida = datos.crearCuenta(name, password);
    	
    	return creacionValida;
    }
    
    public boolean iniciarSesion(String name, String password, String tipoUsuario) throws IOException
    {
    	boolean inicioValido = false;
		this.nombUsu = name;
    	
    	inicioValido = datos.iniciarSesion(name, password, tipoUsuario);   
    	
    	return inicioValido;
    }
    
    public String getUserName() {
    	return this.nombUsu;
    }

    public ArrayList<equipo> tablaPosiciones() throws IOException{
      return datos.obtenerTablaPosiciones();
    }
    
    public ArrayList<String> jugadorconMasyMenosPuntos(equipo equipo) throws IOException
    {
    	this.EquipoUsu = equipo;
		HashMap<String, equipo> EquiposJugadores = datos.mostrarEquiposReales();
		HashMap<Integer, String> PuntosDeJugador = new HashMap<Integer, String>(); 
		int Mayor = 0;
		int Menor = 0;
		Set<String> Llaves = EquiposJugadores.keySet();
		String[] Equipos = Llaves.toArray(new String[Llaves.size()]);

		for (String nombreEquipo: Equipos)
		{
			ArrayList<jugador> Jugadores =  EquiposJugadores.get(nombreEquipo).getJugadores();
			for(int i=0; i<Jugadores.size(); i++)
			{
				int Mayor1 = Jugadores.get(i).getPuntos();
				
				//System.out.println(Mayor1 + " : " + Jugadores.get(i).getNombre() );
				PuntosDeJugador.put(Mayor1, Jugadores.get(i).getNombre());
				if(Mayor1>Mayor)
				{
					Mayor = Mayor1;
				}
				
				if(Mayor1<Menor)
				{
				    Menor = Mayor1;
				}
			}
		
		}
		
		String jugadorMas = PuntosDeJugador.get(Mayor).toString(); 
		String jugadorMenos = PuntosDeJugador.get(Menor).toString();
		String MayorS = Mayor+"";
		String MenorS = Menor+"";
		
		ArrayList<String> JugadorMenosyMas = new  ArrayList<String>();
		
		JugadorMenosyMas.add(jugadorMas);
		JugadorMenosyMas.add(MayorS);
		JugadorMenosyMas.add(jugadorMenos);
		JugadorMenosyMas.add(MenorS);
		return JugadorMenosyMas;
    }
    
    public void elegirTitular(String seleccionN, equipo equipo) throws IOException 
    {
		this.EquipoUsu = equipo;
    	ArrayList<jugador> Jugadores = (EquipoUsu.getJugadores());
    	jugador titularElegido = Jugadores.get(Integer.parseInt(seleccionN)-1);
    	PopUps popup = new PopUps();
    	
    	if(datos.verificarEquipo(EquipoUsu.getTitulares(), titularElegido, 11))
    	{
    		datos.elegirTitular(EquipoUsu, titularElegido);
    		popup.titularExitoso();
    	}
    	else
    	{
    		popup.titularNoExitoso();
    	}
    	
    }
    
    public void elegirTitularTitularesLlenos(String titular, String titularAreemplazar, equipo equipo) throws IOException
    {
		this.EquipoUsu = equipo;
    	ArrayList<jugador> Jugadores = (EquipoUsu.getJugadores());
    	jugador titularElegido = Jugadores.get(Integer.parseInt(titular)-1);
    	jugador JugadorViejo= EquipoUsu.getTitulares().get(Integer.parseInt(titularAreemplazar)-1);
    	PopUps popup = new PopUps();
    	if(datos.verificarEquipoTitulares(EquipoUsu.getTitulares(), titularElegido, JugadorViejo) && !datos.seRepiteNombre(EquipoUsu, titularElegido) )
		{
			datos.modificarTitular(EquipoUsu, titularElegido, Integer.parseInt(titularAreemplazar)-1);
			popup.titularExitoso();
			
		}
		else
		{
			popup.titularNoExitoso();
		}
    	
    }
    
    public void elegirCapitan(String titular, equipo equipo) throws IOException
    {
    	this.EquipoUsu = equipo;
    	PopUps popup = new PopUps();
    	jugador JugadorCapitan=EquipoUsu.getTitulares().get(Integer.parseInt(titular)-1);
    	EquipoUsu.setCapitan(JugadorCapitan);
		datos.cargarEquipo(EquipoUsu);
		popup.elegirCap(JugadorCapitan.getNombre());
    }
    
    public ArrayList<String[]> mostrarCalendario() throws IOException
    {
    	ArrayList<String[]> calendar = datos.mostrarCalendario();
    	
    	return calendar;
    }

    public int actualizarPuntos(equipo equipo) throws IOException
    {
    	this.EquipoUsu = equipo;
    	if (!EquipoUsu.getNombre().equals(""))
    		{
	    		ArrayList<jugador> Titulares = (EquipoUsu.getTitulares());
	    		int puntostotales = 0;
	    		jugador Capitan = EquipoUsu.getCapitan();
	    		if (Capitan!=null) 
	    		{
	    		for(jugador j: Titulares)
	    			{
	    				puntostotales += j.getPuntos();
	    			}
	    		if (datos.puntosCapitan(Capitan))
	    			{
	    				puntostotales += 5;
	    			}
	    		this.EquipoUsu.setPuntos(puntostotales);
	    		datos.cargarEquipo(EquipoUsu);
	    		return EquipoUsu.getPuntos();
	    		}
    		}
		return 0;
    }
    public ArrayList<equipo> obtenerEquiposFantasia() throws IOException{
		return datos.obtenerEquiposFantasia();
	}
}

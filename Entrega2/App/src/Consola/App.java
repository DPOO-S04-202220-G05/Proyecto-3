import Analizador.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class App {
	
	private datos datos = new datos();
    String nombUsu;
    equipo EquipoUsu;
	
	
    public static void main(String[] args) throws Exception {
        
        App app = new App();
		app.ejecutarAplicacion();
    }
    
    /**
	 * =================================================================================================================
	 * PRINCIPAL
	 * =================================================================================================================
     * @throws IOException 
	 */
    public void ejecutarAplicacion() throws IOException
    {
    	System.out.println("==============================================");
    	System.out.println("Fútbol de Fantasía");
    	System.out.println("==============================================");
    	
    	boolean continuar = true;
    	while (continuar)
		{
	    	mostrarOpciones();
			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
			
			//INICIO DE SESION
			if (opcion_seleccionada == 1)
			{
				mostrarOpcionesDeInicioSesion();
				opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				
				//ADMIN
				if(opcion_seleccionada == 1)
				{
					boolean iniad = iniciarSesion("Admin");
					boolean contiad = true;
					while(contiad)
						{
						if(iniad)
							{
									//OPCIONES DEL ADMIN
								mostrarOpcionesAdmin();
								opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
								if(opcion_seleccionada == 1)
								{
									ejecutarCargarArchivoTemporada();
								}
								else if(opcion_seleccionada == 2)
								{
									ejecutarCargarArchivoPartido();
								}
								else if(opcion_seleccionada == 3)
								{
									contiad = false;
								}
							}
						}
				}
				
				//USUARIO
				else if(opcion_seleccionada == 2)
				{
					boolean iniusu = iniciarSesion("UsuarioN");
					actualizarPuntos();
					boolean contiusu = true;
					while(contiusu)
					{
						if(iniusu)
						{
							//OPCIONES DEL USUARIO
							mostrarOpcionesUsuario();
							opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
							if (opcion_seleccionada == 1)
							{
								String nombreE = input("Por favor digite el nombre para su equipo");
								crearEquipo(nombreE);
							}
							//VER MI EQUIPO
							else if (opcion_seleccionada == 2)
							{
						    	this.EquipoUsu = datos.consultarEquipo(nombUsu);
						    	if (!EquipoUsu.getNombre().equals(""))
						    	{
									ArrayList<jugador> JugadoresMiEquipo = datos.mostrarMisJugadores(EquipoUsu);
									int centinela = 1;
									for (jugador JugadorMiEquipo:JugadoresMiEquipo)
										{
											String nombre = JugadorMiEquipo.getNombre();
											String posicion = JugadorMiEquipo.getPosicion();
											System.out.println(centinela+". "+nombre + " - " + posicion);
											centinela +=1;
										}
						    	}
							}
							else if (opcion_seleccionada == 3)
							{
								comprarJugador();
							}
							else if (opcion_seleccionada == 4)
							{
								venderJugador();
							}
							else if (opcion_seleccionada == 5)
							{
							
								elegirTitular();
								
							}
							
							//VER MIS TITULARES
							else if (opcion_seleccionada == 6)
							{
								this.EquipoUsu = datos.consultarEquipo(nombUsu);
						    	if (!EquipoUsu.getNombre().equals(""))
						    	{
									ArrayList<jugador> JugadoresTitulares = datos.verMisTitulares(EquipoUsu);
									int centinela = 1;
									for (jugador JugadorTitular:JugadoresTitulares)
										{
											String nombre = JugadorTitular.getNombre();
											String posicion = JugadorTitular.getPosicion();
											System.out.println(centinela+". "+nombre + " - " + posicion);
											centinela +=1;
										}
						    	}
								
							}
							//ELEGIR TITULAR
							else if (opcion_seleccionada == 7)
							{
								elegirCapitan();
								
							}
							else if (opcion_seleccionada == 8)
							{
								mostrarOpcionesEstadisticas();
								opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));

								if (opcion_seleccionada == 1)
								{
									System.out.println("\n" + datos.obtenerTablaPosiciones());
								}
								else if (opcion_seleccionada == 2)
								{
									ArrayList<String> cerradas = datos.consultarFechasCerradas();
									int centinela = 1;
									for (String cerrada: cerradas)
									{
										System.out.print("\n"+centinela+". "+cerrada);
										centinela+=1;
									}
									int elegida = Integer.parseInt(input("\nElija una fecha para ver el mejor equipo de fantasia"));
									System.out.print(datos.obtenerMejorEquipoFecha(elegida).replace(".txt", ""));
								}
								else if (opcion_seleccionada == 3)
								{
									jugadorconMasyMenosPuntos();
								}
							}
							
							else if (opcion_seleccionada == 9)
							{
								 mostrarCalendario();
							}			
							else if (opcion_seleccionada == 10)
							{
								contiusu = false;
							}
						}
					}
					
				}
				
				else if(opcion_seleccionada == 3)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				
			}
			
			//CREACION DE CUENTA USUARIO NORMAL
			else if (opcion_seleccionada == 2)
			{
				crearCuenta();
			}
			
			
			//SALIR
			else if (opcion_seleccionada == 3)
			{
				System.out.println("Saliendo de la aplicación ...");
				continuar = false;
			}
		}
    }
    
    /**
	 * =================================================================================================================
	 * MENUS
	 * =================================================================================================================
	 */
    
    //OPCIONES PARA EL ADMIN
    public void mostrarOpcionesAdmin()
    {
    	System.out.println("\nOpciones de la aplicación\n");
    	System.out.println("1. Cargar archivo de Jugadores y de Temporada");
    	System.out.println("2. Cargar archivo de Partido");
    	System.out.println("3. Salir");
    }
    
    //OPCIONES PARA EL USUARIO
    public void mostrarOpcionesUsuario()
    {
    	System.out.println("\nOpciones de la aplicación\n");
    	System.out.println("1. Crear un nuevo equipo");
    	System.out.println("2. Ver mi equipo");
    	System.out.println("3. Comprar jugador");
    	System.out.println("4. Vender jugador");
    	System.out.println("5. Configurar alineación");
    	System.out.println("6. Ver mis titulares-alineación");
    	System.out.println("7. Elegir capitan");
    	System.out.println("8. Mostrar estadísticas");
    	System.out.println("9. Mostrar Calendario");
    	System.out.println("10. Cerrar Sesion");
    }
    
    //OPCIONES GENERALES
    public void mostrarOpciones()
    {
    	System.out.println("\nOpciones de la aplicación\n");
    	System.out.println("1. Iniciar sesión");
    	System.out.println("2. Crear cuenta");
    	System.out.println("3. Salir");
    }
    
    //OPCIONES PARA INICAR SESION, PUEDE SER COMO ADMIN O USUARIO
    public void mostrarOpcionesDeInicioSesion()
    {
    	System.out.println("\nOpciones de la aplicación\n");
    	System.out.println("¿Cómo desea iniciar sesión?");
    	System.out.println("1. Administrador");
    	System.out.println("2. Usuario");
    	System.out.println("3. Salir");
    }

	public void mostrarOpcionesEstadisticas()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar tabla de Posiciones");
		System.out.println("2. Mostrar mejor equipo por fecha ");
		System.out.println("3. Mostrar jugador con más y menos puntos");
	}
    
     /**
	 * =================================================================================================================
	 * CARGAS
	 * =================================================================================================================
     * @throws IOException 
	 */
    private void ejecutarCargarArchivoTemporada() throws IOException
    {
    	String nombreArchivoJugadores = (input("Por favor ingrese la ruta del archivo de jugadores a cargar")).replace("\"", "");
    	String nombreArchivoTemporada = (input("Por favor ingrese la ruta del archivo de la temporada a cargar")).replace("\"", "");
    	File archivoJugadores = new File(nombreArchivoJugadores);
		File archivoTemporada = new File(nombreArchivoTemporada);
        datos.cargar_datos_temporada(archivoJugadores, archivoTemporada);
    	
    }
    
    private void ejecutarCargarArchivoPartido() throws IOException
    {
    	String nombreArchivoPartido = (input("Por favor ingrese la ruta del archivo a cargar")).replace("\"", "");
    	File archivoPartido = new File(nombreArchivoPartido);
		datos.cargarPartido(archivoPartido);
    }
    
    private void crearCuenta() throws IOException
    {
    	
    	System.out.println("Crear usuario");
    	String Nombre  = input("Por favor digite el nombre de usuario que desea");
		String Contrasenia  = input("Por favor digite la contrasenia que desea");
		
		if(datos.crearCuenta(Nombre, Contrasenia))
		{
			System.out.println("El usuario fue creado con exito");
		}
		else 
		{
			System.out.println("Creacion no exitosa, el nombre ya fue usado");
		}
		
    }
    
    /**
	 * =================================================================================================================
	 * FUNCIONES QUE LLAMAN A DATOS( NO SUPE QUE OTRO NOMBRE PONER XD)
	 * =================================================================================================================
     * @throws IOException 
	 */
    	
    
    /**
     * Valida el inicio de sesion
     * 
     * @param tipoUsuario: puede ser "UsuarioN" (Usuario normal) o "Admin"
     * @return
     * @throws IOException
     */
    private boolean iniciarSesion(String tipoUsuario) throws IOException
    {
    	Boolean inicioValido = false;
    	System.out.println("Inicio de sesion");
    	String Nombre  = input("Por favor digite su nombre");
		String Contrasenia  = input("Por favor digite su contrasenia");
		
		
		if(datos.iniciarSesion(Nombre, Contrasenia, tipoUsuario))
		{
			inicioValido = true;
			System.out.println("Inicio sesión correctamente");
			nombUsu = Nombre;
		}
		else
		{
			System.out.println("Inicio de sesión invalido, por favor verifique su usuario y contrasenia");
		}
		datos.setPaths();
		
		
		return inicioValido;
    }
    
    private void crearEquipo(String nombreE) throws IOException
    {
    	EquipoUsu = datos.cargar_datos_de_Equipo(nombreE, nombUsu, false, 100000000);
    }
    
    private void comprarJugador() throws IOException
    {
    	this.EquipoUsu = datos.consultarEquipo(nombUsu);
    	if (!EquipoUsu.getNombre().equals(""))
    	{
			HashMap<String, equipo> EquiposJugadores = datos.mostrarEquiposReales();
			Set<String> Llaves = EquiposJugadores.keySet();
			String[] Equipos = Llaves.toArray(new String[Llaves.size()]);
			int centinelaE = 1;
			for (String nombre: Equipos)
				{
					System.out.println(centinelaE+". "+nombre);
					centinelaE +=1;
				}
			int seleccionE = Integer.parseInt(input("Elija el equipo al que pertenece el jugador que desea comprar "))-1;
			String nombreE = Equipos[seleccionE];
			ArrayList<jugador> Jugadores = (EquiposJugadores.get(nombreE)).getJugadores();
			int centinelaJ = 1;
			for(jugador Integrante: Jugadores)
			{
				String nombrej = Integrante.getNombre();
				String posicionj = Integrante.getPosicion();
				float precioj = Integrante.getPrecio();
				System.out.println(centinelaJ+". "+ nombrej + " - " + posicionj + " - " + precioj);
				centinelaJ +=1;
			}
			int seleccionJ = Integer.parseInt(input("Elija el jugador que desea comprar "))-1;
			jugador Integrante = Jugadores.get(seleccionJ);
		
			float PresuspuestoActual = EquipoUsu.getPresupuesto();
			if(datos.verificarEquipo(EquipoUsu.getJugadores(), Integrante, 15))
			{
				datos.comprarJugador(EquipoUsu, Integrante);
				System.out.println("\n");
				float PresuspuestoNuevo = EquipoUsu.getPresupuesto();
				
				if(PresuspuestoNuevo==PresuspuestoActual)
				{
					System.out.println("Su presuspuesto no es suficiente");
				}
			}
			else
			{
				System.out.println("\n");
				System.out.println("Posicion no permitida, excede el limite");
			}
			
			System.out.println("Su presupuesto es de: " + EquipoUsu.getPresupuesto() + "$");
    	}
    }
    
    private void venderJugador() throws IOException
    {
    	this.EquipoUsu = datos.consultarEquipo(nombUsu);
    	if (!EquipoUsu.getNombre().equals(""))
    	{
			ArrayList<jugador> Jugadores = (EquipoUsu.getJugadores());
			int centinelaJ = 1;
			for(jugador Integrante: Jugadores)
			{
				String nombrej = Integrante.getNombre();
				String posicionj = Integrante.getPosicion();
				float precioj = Integrante.getPrecio();
				System.out.println(centinelaJ+". "+ nombrej + " - " + posicionj + " - " + precioj);
				centinelaJ +=1;
			}
			int seleccionJ = Integer.parseInt(input("Elija el jugador que desea vender: "))-1;
			jugador Integrante = Jugadores.get(seleccionJ);
			datos.venderJugador(EquipoUsu, Integrante);
			System.out.println("\n");
			System.out.println("Su presupuesto es de: " + EquipoUsu.getPresupuesto() + "$");
    	}
    }
    
    private void actualizarPuntos() throws IOException
    {
    	this.EquipoUsu = datos.consultarEquipo(nombUsu);
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
	    		System.out.println("Sus puntos son: "+EquipoUsu.getPuntos());
	    		}
    		}
    }
    
    private void elegirTitular() throws IOException
    {
    	this.EquipoUsu = datos.consultarEquipo(nombUsu);

    	if (!EquipoUsu.getNombre().equals(""))
    	{
	    	//Se obtienen los jugadores en el equipo de fantasia (equipo completo - 15)
			ArrayList<jugador> Jugadores = (EquipoUsu.getJugadores());
			int centinelaJ = 1;
		
			//Se itera la lista de jugadores (equipo completo - 15)
			
			for(jugador Integrante: Jugadores)
			{
				//Se obtiene el nombre y la posicion del jugador
				String nombrej = Integrante.getNombre();
				String posicionj = Integrante.getPosicion();
				
				//Se muestran al usuario
				System.out.println(centinelaJ+". "+ nombrej + " - " + posicionj);
				centinelaJ +=1;
			}
			
			//Se selecciona el jugador que se desea elegir como titular
			int seleccionN = Integer.parseInt(input("Elija el jugador que desea elegir como titular "));
			
			//Se obtiene el jugador seleccionado
			jugador titularElegido = Jugadores.get(seleccionN-1);
			
			
			//Si el equipo ya esta lleno
			if(EquipoUsu.getTitulares().size()==11)
			{
				
				for(int e=0;e<EquipoUsu.getTitulares().size();e++)
				{
					String titularReemplazo = EquipoUsu.getTitulares().get(e).getNombre();
					String posicionReemplazo = EquipoUsu.getTitulares().get(e).getPosicion();
					System.out.println(e+1+". "+ titularReemplazo + " - " + posicionReemplazo);
				}
				int seleccionReemplazo = Integer.parseInt(input("Elija el jugador que desea reemplazar como titular "));
				
				jugador JugadorViejo= EquipoUsu.getTitulares().get(seleccionReemplazo-1);
			
				//Se verifica que la posicion de campio sea valida
				
				
				if(datos.verificarEquipoTitulares(EquipoUsu.getTitulares(), titularElegido, JugadorViejo) && !datos.seRepiteNombre(EquipoUsu, titularElegido) )
				{
					datos.modificarTitular(EquipoUsu, titularElegido, seleccionReemplazo-1);
				}
				else
				{
					System.out.println("Posicion no permitida");
				}
			}
			
			//Si el tamanio del equipo es menor a 11
			//Se valida que las posiciones sean coherentes
			else if(datos.verificarEquipo(EquipoUsu.getTitulares(), titularElegido, 11))
			{
				//Se elige el titular
				datos.elegirTitular(EquipoUsu, titularElegido);
			}
			else
			{
				System.out.println("Posicion no permitida, excede el limite");
			}
		
    	}
    }
    
    private void elegirCapitan() throws IOException
    {
    	this.EquipoUsu = datos.consultarEquipo(nombUsu);
    	if (!EquipoUsu.getNombre().equals(""))
    	{
			for(int e=0;e<EquipoUsu.getTitulares().size();e++)
			{
				String titularCapitan = EquipoUsu.getTitulares().get(e).getNombre();
				String posicionCapitan = EquipoUsu.getTitulares().get(e).getPosicion();
				System.out.println(e+1+". "+ titularCapitan + " - " + posicionCapitan);
			}
			
			int seleccionCapitan = Integer.parseInt(input("Elija el jugador que desea reemplazar como titular "));
			jugador JugadorCapitan=EquipoUsu.getTitulares().get(seleccionCapitan-1);
			EquipoUsu.setCapitan(JugadorCapitan);
			datos.cargarEquipo(EquipoUsu);
			
			System.out.println("\n");
			System.out.println("=================================================================");
			System.out.println("Ha seleccionado a " + JugadorCapitan.getNombre() + " como capitan");
			System.out.println("=================================================================");
    	}
    }
    
    public void jugadorconMasyMenosPuntos() throws IOException
    {
    	this.EquipoUsu = datos.consultarEquipo(nombUsu);
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
		System.out.println("=====================================================================================================");
		System.out.println("El jugador con mas puntos es " + PuntosDeJugador.get(Mayor).toString() + " con " + Mayor + " puntos");
		System.out.println("=====================================================================================================");
		System.out.println("El jugador con menos puntos es " + PuntosDeJugador.get(Menor).toString() + " con " + Menor + " puntos");
		System.out.println("=====================================================================================================");
	
    }
    
    public void mostrarCalendario() throws IOException
	{
		datos.mostrarCalendario();
	}
    /**
	 * =================================================================================================================
	 * INPUT
	 * =================================================================================================================
	 */
    
    /**
	 * Este método sirve para imprimir un mensaje en la consola pidiéndole
	 * información al usuario y luego leer lo que escriba el usuario.
	 * 
	 * @param mensaje El mensaje que se le mostrará al usuario
	 * @return La cadena de caracteres que el usuario escriba como respuesta.
	 */
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}

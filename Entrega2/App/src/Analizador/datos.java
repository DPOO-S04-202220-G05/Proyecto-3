package Analizador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Persistencia.paqueteDatos;

import java.util.*;


public class datos {

    /* ===========
    * * ATRIBUTOS
       ===========
    */ 

    private HashMap<String, equipo> equipos = new HashMap<String, equipo>();
    private HashMap<String, partido> partidos = new HashMap<String, partido>();
    private paqueteDatos persistencia = new paqueteDatos();
    
    /* ===========
    * * MÉTODOS
       ===========
    */

    
	public void cargar_datos_temporada(File archivoJugadoresyEquipos, File archivoTemporada) throws IOException{
        Scanner lector = new Scanner(archivoJugadoresyEquipos);

		persistencia.crearTemporada();
        
        while (lector.hasNextLine()){
            String info = lector.nextLine();
            String[] partes = info.split(";");
			String nombreEquipo = partes[0];
			String jugadores = partes[1];
            
			String[] infoJugadores = jugadores.split(",");

            equipo equipoReal = new equipo(nombreEquipo, "real"+nombreEquipo, true, 0);
			equipos.put(nombreEquipo, equipoReal);
            
			for(int i = 0; i < infoJugadores.length; i++){
				String[] infoJugador = infoJugadores[i].split("%");
				String nombreJugador = infoJugador[0];
				Float precioJugador = Float.parseFloat(infoJugador[1]);
				String posicionJugador = infoJugador[2];

				jugador jugadorReal = new jugador(nombreJugador, posicionJugador, precioJugador, 0,nombreEquipo);
		    	persistencia.cargarDatosJugador(jugadorReal);

				equipo equipoJugador = (equipo) equipos.get(nombreEquipo);
				equipoJugador.addJugador(jugadorReal);
	        	persistencia.cargarDatosEquipo(equipoReal);
			}   
        }

        try (Scanner line = new Scanner(archivoTemporada)) {
			String info = line.nextLine();
			while (line.hasNextLine()){
				info = line.nextLine();
				
			    String[] partes = info.split(",");
			    
			    String nombreEquipoLocal = partes[0];
			    String nombreEquipoVisitante = partes[1];
			    String fechaDias = partes[2];
				String hora = partes[3];
				int fecha = Integer.parseInt(partes[4]);

				persistencia.crearFecha(fecha);
				persistencia.crearPartido(nombreEquipoLocal, nombreEquipoVisitante, fechaDias, hora, fecha);


			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

	public equipo cargar_datos_de_Equipo(String nombreEquipo,String Usuario, boolean real, float presupuesto) throws IOException 
	{
		equipo nuevoE = new equipo(nombreEquipo,Usuario, real, presupuesto);
    	persistencia.cargarDatosEquipo(nuevoE);
		return nuevoE ;
	}

	public void cargarEquipo(equipo e) throws IOException 
	{
    	persistencia.cargarDatosEquipo(e);
	}

	public void cargarJugador(jugador j) throws IOException 
	{
    	persistencia.cargarDatosJugador(j);
	}
	
	public void cargarPartido(File archivoPartido) throws IOException
	{
		@SuppressWarnings("resource")
		Scanner lector = new Scanner(archivoPartido);
		String infoPartido = lector.nextLine();
		infoPartido = lector.nextLine();
		String[] partidoInfo = infoPartido.split(",");

		String equipoLocal = partidoInfo[0];
		String equipoVisitante = partidoInfo[1];
		String fechaFormat = partidoInfo[2] + "&" + partidoInfo[3];
		int fecha = Integer.parseInt(partidoInfo[4].replace(";", ""));

		HashMap<String, desempenio> desempenioEquipoLocal = new HashMap<>();
		HashMap<String, desempenio> desempenioEquipoVisitante = new HashMap<>();

		while (lector.hasNextLine())
		{
			infoPartido = lector.nextLine();

			String[] partes = infoPartido.split(";");

			String equipo = partes[0];

			String[] infoJugador = partes[1].split(",");
			String nombreJugador = infoJugador[0];
			int cantidadMinutos = Integer.parseInt(infoJugador[1]);
			int goles = Integer.parseInt(infoJugador[2]);
			int penaltis = Integer.parseInt(infoJugador[3]);
			int autogoles = Integer.parseInt(infoJugador[4]);
			int asistencias = Integer.parseInt(infoJugador[5]);
			int golesRecibidos = Integer.parseInt(infoJugador[6]);
			int penaltisRecibidos = Integer.parseInt(infoJugador[7]);
			int penaltisAtajados = Integer.parseInt(infoJugador[8]);
			int penaltisErrados = Integer.parseInt(infoJugador[9]);
			int tarjetasAmarillas = Integer.parseInt(infoJugador[10]);
			int tarjetasRojas = Integer.parseInt(infoJugador[11]);
			int manos = Integer.parseInt(infoJugador[12]);
			int tirosLibres = Integer.parseInt(infoJugador[13]);
			int golTiroLibre = Integer.parseInt(infoJugador[14]);
			

			desempenio desempenioJugador = new desempenio(cantidadMinutos, goles, penaltis, autogoles, asistencias, golesRecibidos, penaltisRecibidos, penaltisAtajados, penaltisErrados, tarjetasAmarillas, tarjetasRojas, manos, tirosLibres, golTiroLibre);

			if(equipo.equals(equipoLocal))
			{
				desempenioEquipoLocal.put(nombreJugador, desempenioJugador);
			}
			else
			{
				desempenioEquipoVisitante.put(nombreJugador, desempenioJugador);
			}
		}

		partido partidoFinal = new partido(desempenioEquipoLocal, desempenioEquipoVisitante, fechaFormat);
		partidoFinal.asignarPuntosJugador();

		persistencia.cargarPartido(partidoFinal, equipoLocal, equipoVisitante, fecha, fechaFormat);

	}

	public ArrayList<equipo> consultarEquipos(String nombreUsuario) throws IOException 
	{
		ArrayList<File> ArchivoEquipos = persistencia.consultarDatosEquipos(nombreUsuario);
		ArrayList<equipo> EquiposUsuario = new ArrayList<>();

		for (File equipo : ArchivoEquipos){
			equipo EquipoUsu;
			if (equipo.getName().equals(""))
			{
				EquipoUsu= new equipo("",nombreUsuario,false,0);
				EquiposUsuario.add(EquipoUsu);
			}
			EquipoUsu = convertirArchivoEquipo(equipo);
			EquiposUsuario.add(EquipoUsu);
		}

		return EquiposUsuario;
	}


	public equipo consultarEquipo(String nombreUsuario) throws IOException 
	{
		File ArchivoEquipo = persistencia.consultarDatosEquipo(nombreUsuario);
		equipo EquipoUsu;
		if (ArchivoEquipo.getName().equals(""))
		{
			EquipoUsu= new equipo("",nombreUsuario,false,0);
			return EquipoUsu;
		}
		EquipoUsu = convertirArchivoEquipo(ArchivoEquipo);
		return EquipoUsu ;
	}

	public equipo convertirArchivoEquipo(File archivoEquipo) throws IOException 
	{
		BufferedReader bufferLectura = new BufferedReader(new FileReader(archivoEquipo));
		String linea = bufferLectura.readLine();
		linea = bufferLectura.readLine();
		String[] datosEquipo = linea.split(",");
		String nombreUsuario = datosEquipo[0];
		String nombreEquipo = datosEquipo[1];
		List<String> nombresjugadores = Arrays.asList(datosEquipo[2].split("-"));
		ArrayList<jugador> Jugadores = new ArrayList<>();
		for (String nombrej: nombresjugadores)
		{
			if (!nombrej.equals(""))
			{
				jugador j = consultarJugador(nombrej);
				if (!j.getNombre().equals(""))
				{
					Jugadores.add(j);
				}
			}
		}
		List<String> nombresTitulares = Arrays.asList(datosEquipo[3].split("-"));
		ArrayList<jugador> Titulares = new ArrayList<>();
		for (String nombret: nombresTitulares)
		{
			if (!nombret.equals(""))
			{
				jugador j = consultarJugador(nombret);
				if (!j.getNombre().equals(""))
				{
					Titulares.add(consultarJugador(nombret));
				}
			}
		}
		float presupuestoE = Float.parseFloat(datosEquipo[4]);
		int puntosE = Integer.parseInt(datosEquipo[5]);
		boolean realE = Boolean.parseBoolean(datosEquipo[6]);
		bufferLectura.close();
		
		equipo EquipoUsu = new equipo(nombreEquipo,nombreUsuario, realE, presupuestoE);
		EquipoUsu.setPuntos(puntosE);
		EquipoUsu.setJugadores(Jugadores);
		EquipoUsu.setTitulares(Titulares);
		if (!realE && datosEquipo.length>7)
		{
			jugador capitan = consultarJugador(datosEquipo[7]);
			if (capitan!=null)
			{
				EquipoUsu.setCapitan(capitan);
			}
		}
		return EquipoUsu ;
	}


	public ArrayList<equipo> obtenerEquiposFantasia() throws IOException{
		return persistencia.ObtenerEquiposFantasia();
	}
	
	
	public jugador consultarJugador(String nombreJugador) throws IOException 
	{
		File ArchivoJugador = persistencia.consultarDatosJugador(nombreJugador);
		jugador jugador;
		if (ArchivoJugador.getName().equals(""))
		{
			jugador= new jugador("","",0f,0,"");
			return jugador;
		}
		jugador = convertirArchivoJugador(ArchivoJugador);
		return jugador ;
	}
	
	public jugador convertirArchivoJugador(File archivoJugador) throws IOException 
	{
		if (archivoJugador.exists())
		{
			BufferedReader bufferLectura = new BufferedReader(new FileReader(archivoJugador));
			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();
			String[] datosJugador = linea.split(",");
			String nombreJ = datosJugador[0];
			String posicionJ = datosJugador[1];
			Float precioJ = Float.parseFloat(datosJugador[2].replace(".0", ""));
			int puntosJ = Integer.parseInt(datosJugador[3]);
			String equipo = datosJugador[4];
			bufferLectura.close();
			
			jugador JugadorJ = new jugador(nombreJ,posicionJ, precioJ, puntosJ,equipo);
			return JugadorJ ;
		}
		return new jugador("","",0f,0,"");
	}
	
	/**
	 * 
	 * Muestra el equipo de fantasia al usuario
	 * @param MiEquipo
	 * @return
	 */
	public ArrayList<jugador> mostrarMisJugadores(equipo MiEquipo) 
	{
		ArrayList<jugador> MisJugadores = MiEquipo.getJugadores();
		return MisJugadores;
	}
	
	public ArrayList<jugador> verMisTitulares(equipo MiEquipo) 
	{
		ArrayList<jugador> MisTitulares = MiEquipo.getTitulares();
		return MisTitulares;
	}

	public void comprarJugador(equipo EquipoUsu, jugador Comprado) throws IOException
	{
		EquipoUsu.comprarJugador(Comprado);
    	persistencia.cargarDatosEquipo(EquipoUsu);
	}

	public void venderJugador(equipo EquipoUsu, jugador Vendido) throws IOException
	{
		EquipoUsu.venderJugador(Vendido);
    	persistencia.cargarDatosEquipo(EquipoUsu);
	}

	public void elegirTitular(equipo EquipoUsu, jugador titularElegido) throws IOException
	{
		EquipoUsu.elegirTitular(titularElegido);
    	persistencia.cargarDatosEquipo(EquipoUsu);
	}

	
	public void modificarTitular(equipo EquipoUsu, jugador titularNuevo, int TitularViejo) throws IOException
	{
		EquipoUsu.modificarTitular(titularNuevo, TitularViejo);
		persistencia.cargarDatosEquipo(EquipoUsu);
	}
	
	public HashMap<String, equipo> mostrarEquiposReales() throws IOException 
	{
		ArrayList<File> archivosequipos = persistencia.consultarEquiposReales();
		HashMap<String,equipo> mapaequipos = new HashMap<>();
		for(File archivoequipo: archivosequipos)
			{
				equipo equipoReal = convertirArchivoEquipo(archivoequipo);
				String nombreequipo = equipoReal.getNombre();
				mapaequipos.put(nombreequipo,equipoReal);
			}
		return mapaequipos;
	}
	
	public boolean puntosCapitan(jugador Capitan) throws IOException
	{
		if (Capitan!=null)
		{
			String nombrequipo = Capitan.getEquipo();
			return persistencia.verificarGanador(nombrequipo);
		}
		return false;
	}
	
	/*
	 * Se cargan usuarios, si es que ya existian previamente, y se cargan usuarios que se creen
	 * durante la ejecución de la app
	 */

		
	/**
	 * 
	 * Se encarga de crear la cuenta del usuario
	 * 
	 * @param NombreUsuario
	 * @param ContraseniaUsuario
	 * @return True: el usuario se cargo con exito. False: El usuario no se cargo
	 * @throws IOException
	 */
	public Boolean crearCuenta(String NombreUsuario, String ContraseniaUsuario) throws IOException
	{
		Boolean creacionValida = false;
		
		//Si la carpeta "Usuarios existe"
		if(persistencia.existeCarpeta("Usuarios"))
			
		{
			//Se cargan los usuarios ya creados previamente
			persistencia.cargarUsuariosExistentes();
			
			//Se verifica que el nuevo nombre de usuario no se uso antes
			Boolean existeNombre = persistencia.verificarNombreUsuario(NombreUsuario);

			//Si el nombre no ha sido usado antes
			if(!existeNombre)
			{
				//La creacion es valida, y se carga el usuario
				creacionValida = true;
				persistencia.cargarDatosUsuario(NombreUsuario, ContraseniaUsuario);
			}
			
		}
		
		//Si la carpeta "Usuarios" no existe
		else 
		{
			//Se crea la carpeta "Usuarios"
			persistencia.crearCarpetaUsuario();
			//Se guarda el nuevo usuario y la creacion es valida
			persistencia.cargarDatosUsuario(NombreUsuario, ContraseniaUsuario);
			creacionValida = true;
		}
		
		
		return creacionValida;
	}
	
	
	/**
	 * 
	 * Verifica que el inicio de sesion sea valida
	 * Dependiendo del tipo de usuario realiza la validacion
	 * 
	 * @param Nombre
	 * @param Contrasenia
	 * @param tipoUsuario: Puede ser o "UsuarioN"(Usuario normal) o "Administrador"
	 * @return True: Si la sesion es valida, de lo contrario false
	 * @throws IOException
	 */
	public boolean iniciarSesion(String Nombre, String Contrasenia, String tipoUsuario) throws IOException
	{
		Boolean sesionIniciada = false;
		
		//Carga los usuarios si es que no habian sido cargados antes
		
		
		if(tipoUsuario.equals("UsuarioN"))
		{
			if(persistencia.existeCarpeta("Usuarios"))
			{
				//Carga los usuarios si es que no habian sido cargados antes
				persistencia.cargarUsuariosExistentes();
				
				//Si el nombre y la contrasenia coinciden el inicio de sesion es valido
				if(persistencia.verificarDatosUsuario(Nombre, Contrasenia))
				{
					sesionIniciada = true;
				}
			}
			
		}
		
		else if (tipoUsuario.equals("Admin"))
		{
			
			if(persistencia.verificarAdmin(Nombre, Contrasenia))
			{
				sesionIniciada = true;
			}
			
		}
		return sesionIniciada;
	}
	
	/**
	 * 
	 * Verifica si el equipo que se va a crear cumple la condicion de las posiciones, y la cantidad,
	 * esos parametros varian segun si el equipo es el completo - fantasia (size 15) o el de titulares(size 11)
	 * 
	 * @param Jugadores: Arraylist con los jugadores del equipo en cuestion
	 * @param JugadorActual: Jugador que se va a adicionar al equipo
	 * @param tamanio: Es 11 si son los titulares, es 15 si es el equipo completo - fantasia
	 * @return False: si el equipo no cumple las condiciones, true de lo contrario.
	 */
	public Boolean verificarEquipo(ArrayList<jugador> Jugadores, jugador JugadorActual, int tamanio)
	{
		
		Boolean verificacion=false;
		
		int tamanioEquipo=Jugadores.size();
		
		//Contadores de posicion
		int arquero = 0;
		int defensa = 0;
		int delantero = 0;
		int mediocampista = 0;
		
		//Si el equipo es de fantasia tiene 15 jugadores, y el tamanio del equipo no se ha superado
		if(tamanio==15 && tamanioEquipo<tamanio)
		{
			
			//Esta es la cantidad maxima de jugadores que se puede tener por cada posicion
			int cantidadArqueros = 2;
			int cantidadDefensas = 5 ;
			int cantidadMedioCampistas = 5;
			int cantidadDelanteros = 3;
			
			
				
			//Se cuentan las posiciones que tenga el equipo
			for(int i=0; i<tamanioEquipo ; i++)
				
			{
				jugador Eljugador = Jugadores.get(i);
				String posicion = Eljugador.getPosicion();
					
				if(posicion.equals("Arquero"))
				{
					arquero++;
				}
				else if(posicion.equals("Defensa"))
				{	
					defensa++;
				}
				else if(posicion.equals("Delantero"))
				{	
					delantero++;
				}
				else if(posicion.equals("MedioCampista"))
				{	
					mediocampista ++;
				}
			}
			
			
			//Una vez se contaron las posiciones 
			//Se suma la nueva posicion
			
			String posicionActual = JugadorActual.getPosicion();
			if(posicionActual.equals("Arquero"))
			{
				arquero++;
			}
			else if(posicionActual.equals("Defensa"))
			{	
				defensa++;
			}
			else if(posicionActual.equals("Delantero"))
			{	
				delantero++;
			}
			else if(posicionActual.equals("MedioCampista"))
			{	
				mediocampista ++;
			}

			//Se valida que la nueva posicion sea valida, es decir que no supere el limite maximo de posiciones
			if(arquero<=cantidadArqueros && defensa<=cantidadDefensas && mediocampista<=cantidadMedioCampistas && delantero<=cantidadDelanteros)
			{
				verificacion = true;
			}
		
		//
		}
			
		//Si solo son los titulares
		else if(tamanio==11 && tamanioEquipo<tamanio)
		{
			int cantidadArqueros = 1;
			int cantidadDefensas = 4;
			int cantidadMedioCampistas = 4;
			int cantidadDelanteros = 2;
			//Se cuentan las posiciones que tenga el equipo
			for(int i=0; i<tamanioEquipo ; i++)
				
			{
				jugador Eljugador = Jugadores.get(i);
				String posicion = Eljugador.getPosicion();
					
				if(posicion.equals("Arquero"))
				{
					arquero++;
				}
				else if(posicion.equals("Defensa"))
				{	
					defensa++;
				}
				else if(posicion.equals("Delantero"))
				{	
					delantero++;
				}
				else if(posicion.equals("MedioCampista"))
				{	
					mediocampista ++;
				}
			}
			
			//Una vez se contaron las posiciones 
			//Se suma la nueva posicion
			
			String posicionActual = JugadorActual.getPosicion();
			if(posicionActual.equals("Arquero"))
			{
				arquero++;
			}
			else if(posicionActual.equals("Defensa"))
			{	
				defensa++;
			}
			else if(posicionActual.equals("Delantero"))
			{	
				delantero++;
			}
			else if(posicionActual.equals("MedioCampista"))
			{	
				mediocampista ++;
			}
			
			//Se valida que la nueva posicion sea valida, es decir que no supere el limite maximo de posiciones
			if(arquero<=cantidadArqueros && defensa<=cantidadDefensas && mediocampista<=cantidadMedioCampistas && delantero<=cantidadDelanteros)
			{
				verificacion = true;
			}
		}
		
		return verificacion;
		
	}
	
	
	public Boolean verificarEquipoTitulares(ArrayList<jugador> Jugadores, jugador JugadorActual, jugador JugadorViejo)
	{
		Boolean verificacion=false;
		int tamanioEquipo=Jugadores.size();
		
		//Contadores de posicion
		int arquero = 0;
		int defensa = 0;
		int delantero = 0;
		int mediocampista = 0;
		
		String posicionVieja = JugadorViejo.getPosicion();
		
		if(posicionVieja.equals("Arquero"))
		{
			arquero--;
			System.out.println(arquero);
		}
		else if(posicionVieja.equals("Defensa") )
		{	
			defensa--;
		}
		else if(posicionVieja.equals("Delantero") )
		{	
			delantero--;
		}
		else if(posicionVieja.equals("MedioCampista") )
		{	
			mediocampista--;
		}
		//Cantidades maximas
		int cantidadArqueros = 1;
		int cantidadDefensas = 4;
		int cantidadMedioCampistas = 4;
		int cantidadDelanteros = 2;
		//Se cuentan las posiciones que tenga el equipo
		for(int i=0; i<tamanioEquipo ; i++)
			
		{
			jugador Eljugador = Jugadores.get(i);
			String posicion = Eljugador.getPosicion();
				
			if(posicion.equals("Arquero"))
			{
				arquero++;
			}
			else if(posicion.equals("Defensa") )
			{	
				defensa++;
			}
			else if(posicion.equals("Delantero") )
			{	
				delantero++;
			}
			else if(posicion.equals("MedioCampista")  )
			{	
				mediocampista ++;
			}
		}
		
		//Una vez se contaron las posiciones 
		//Se suma la nueva posicion
		
		String posicionActual = JugadorActual.getPosicion();
		if(posicionActual.equals("Arquero"))
		{
			arquero++;
			System.out.println(arquero);
		}
		else if(posicionActual.equals("Defensa"))
		{	
			defensa++;
		}
		else if(posicionActual.equals("Delantero"))
		{	
			delantero++;
		}
		else if(posicionActual.equals("MedioCampista"))
		{	
			mediocampista ++;
		}
		
		//Se valida que la nueva posicion sea valida, es decir que no supere el limite maximo de posiciones
		if(arquero<=cantidadArqueros && defensa<=cantidadDefensas && mediocampista<=cantidadMedioCampistas && delantero<=cantidadDelanteros)
		{
			verificacion = true;
		}
		
		
		
		return verificacion;
		
		
	}
	
	
	/**
	 * Verifica si un nombre se repite
	 * @param EquipoUsuario
	 * @param JugadorNuevo
	 * @return false:no se repeite, true de lo contrario
	 */
	public Boolean seRepiteNombre(equipo EquipoUsuario, jugador JugadorNuevo)
	{
		Boolean seRepite = false;
		
		ArrayList<jugador> Titulares = EquipoUsuario.getTitulares();
		
		for(int i = 0; i<Titulares.size();i++)
		{
			String NombreTitulares = Titulares.get(i).getNombre();
			
			if(NombreTitulares.equals(JugadorNuevo.getNombre()))
			{
				seRepite=true;
			}
		}
		
		
		return seRepite;
	}
	


	public void setPaths() {
		persistencia.setPaths();
		
	}

	public ArrayList<equipo> obtenerTablaPosiciones() throws IOException{
		ArrayList<File> equiposFantasia = persistencia.consultarEquiposFantasia();
		ArrayList<equipo> equipos = new ArrayList<>();
		
		Iterator<File> iterator = equiposFantasia.iterator();

		while(iterator.hasNext())
		{
			File equipo = (File) iterator.next();
			equipo equipoFantasia = convertirArchivoEquipo(equipo);
			
			equipos.add(equipoFantasia);
		}

		Collections.sort(equipos, ((o1, o2) -> o2.getPuntos().compareTo(o1.getPuntos())));

		return equipos;
	}
	
	/**
	 * Muestra los partidos que se van a jugar
	 * @throws IOException
	 */
	public ArrayList<String[]> mostrarCalendario() throws IOException
	{
		HashMap<String,ArrayList<String[]>> calendario = persistencia.cargarCalendario();
		
			ArrayList<String[]> Datos = calendario.get("1");
			
		return Datos;
		
	}
	public String obtenerMejorEquipoFecha(int elegida) {
		return persistencia.consultarMejorEquipoFecha(elegida);
	}
	public ArrayList<String> consultarFechasCerradas() throws IOException {
		return this.persistencia.consultarFechasCerradas();
	}
	
}

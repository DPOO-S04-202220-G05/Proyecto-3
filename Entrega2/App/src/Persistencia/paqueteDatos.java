package Persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import Analizador.*;

public class paqueteDatos {
	
	/* ===========
	* * ATRIBUTOS
	   ===========
	*/ 
	private ArrayList<String[]> usuarios = new ArrayList<String[]>();
	private File CarpetaUsuarios = new File("Usuarios");
	private File CarpetaEquipos ;
	private File CarpetaJugadores ;
	private File CarpetaPartidos ;
	private File CarpetaTemporadas = new File("Temporadas");
	private File CarpetaFechas;
	
	
	
	/**
	 * =================================================================================================================
	 * METODOS
	 * =================================================================================================================
	 * 
	 */
	
	
	
	/**
	 * =================================================================================================================
	 * METODOS USUARIO
	 * =================================================================================================================
	 * 
	 */
	
	/**
	 * 
	 * Verifica que el inicio de sesion sea valido, esto lo hace comparando el nombre y contrasenia ingresados
	 * con los nombres y contrasenia guardados en la carpeta usuarios
	 * 
	 * @param Nombre
	 * @param Contraseña
	 * @return True: Si el nombre y la contrasenia son correctos, de lo contrario false.
	 * @throws IOException
	 */
	public Boolean verificarDatosUsuario(String Nombre, String Contraseña) throws IOException {
		
		Boolean verificado=false;
		
		for (File file : CarpetaUsuarios.listFiles()) 
		{
				String NombreArchivoUsuario = file.getName();
				BufferedReader bufferLectura = new BufferedReader(new FileReader("Usuarios/" + NombreArchivoUsuario));
				String linea = bufferLectura.readLine();
				linea = bufferLectura.readLine();
				String[] usuario = linea.split(",");
				if(usuario[0].equals(Nombre) && usuario[1].equals(Contraseña)) 
				{
					verificado = true;
				}
				bufferLectura.close();
		}
		
		return verificado;
		
	}
	
	/**
	 * Verifica el inicio de sesion del admin
	 * 
	 * @param Nombre
	 * @param Contraseña
	 * @return True: Si el nombre del admin y la contraseña coinciden. False, de lo contrario.
	 */
	
	public Boolean verificarAdmin(String Nombre, String Contraseña)
	{
		Boolean verificado=false;
		
		if(Nombre.equals("Admin") && Contraseña.equals("123"))
		{
			verificado = true;
		}
		
		return verificado;
	}
	
	/**
	 * Verifica si un nombre de usuario se ha usado antes
	 * 
	 * @param Nombre
	 * @return False: el nombre no se ha usado, True: El nombre ya fue usado
	 * @throws IOException 
	 */
	public Boolean verificarNombreUsuario(String Nombre) throws IOException 
	{
		Boolean existeNombre = false;
		
		
		for (File file : CarpetaUsuarios.listFiles()) 
		{
			String NombreArchivoUsuario = file.getName();
			BufferedReader bufferLectura = new BufferedReader(new FileReader("Usuarios/" + NombreArchivoUsuario));
			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();
			String[] usuario = linea.split(",");
			if(usuario[0].equals(Nombre)) 
			{
				existeNombre = true;
			}
			bufferLectura.close();
		}
		return existeNombre;
	}
	
	/**
	 * Carga los datos del usuario creado a la carpeta Usuarios y la lista de usuarios
	 * 
	 * @param Nombre
	 * @param Contraseña
	 * @throws IOException
	 */
	public void cargarDatosUsuario(String Nombre, String Contraseña) throws IOException
	{
		//Se crea un nuevo usuario
		usuario elUsuario = new usuario(Nombre, Contraseña);
		String[] usuario = {elUsuario.getNombreUsuarioN(),elUsuario.getContraseñaUsuarioN()};
		
		//Se aniade el nuevo usuario a la lista de usuarios
		usuarios.add(usuario);
		
		//Se usa la longitud para saber cuantos usuarios hay en el sistema
		int longitud = usuarios.size();
		
		//Se crea, en la carpeta Usuarios, un csv con el siguiente nombre: "nombreUsuario-contraseniaUsuario.csv"
		//Este contiene la info del usuario

		FileWriter csvWriter = new FileWriter("Usuarios/" + elUsuario.getNombreUsuarioN() + "-" + elUsuario.getContraseñaUsuarioN() +  ".csv");
		csvWriter.append("usuario,contrasenia");
		csvWriter.append("\n");
		csvWriter.append(elUsuario.getNombreUsuarioN() + "," + elUsuario.getContraseñaUsuarioN() );
		csvWriter.flush();
		csvWriter.close();
		
		//Se imprime cuantos usuarios hay en el sistema
		System.out.println("Hay " + longitud + " usuarios en el sistema");
	}
	
	public void cargarDatosEquipo(equipo datos) throws IOException
	{
		setPaths();
		String nombre = datos.getNombre();
		String nombreD = datos.getDueño();
		ArrayList<jugador> listajugadores = datos.getJugadores();
		String nombresjugadores = "";
		for (jugador j: listajugadores)
		{
			nombresjugadores += j.getNombre()+"-";
		}
		ArrayList<jugador> listatitulares = datos.getTitulares();
		String nombrestitulares = "";
		for (jugador j: listatitulares)
		{
			nombrestitulares += j.getNombre()+"-";
		}
		float presupuesto = datos.getPresupuesto();
		int puntos = datos.getPuntos();
		boolean real = datos.getReal();
		String nombrecarpeta = CarpetaEquipos.getPath();
		if (!existeCarpeta(nombrecarpeta))
		{
			CarpetaEquipos.mkdir();
		}
		//Se crea, en la carpeta Usuarios, un csv con el siguiente nombre: "nombreUsuario-contraseniaUsuario.csv"
		//Este contiene la info del usuario
		
		FileWriter csvWriter = new FileWriter(nombrecarpeta + "/" + nombreD + "-" + nombre +  ".csv");
		if (!real) 
		{
			String nomcapitan = "";
			jugador capitan = datos.getCapitan();
			if (capitan!=null)
			{
				nomcapitan = capitan.getNombre();
			}
			csvWriter.append("nombre.duenio,nombre.equipo,jugadores,titulares,presupuesto,puntos,real,capitan");
			csvWriter.append("\n");
			csvWriter.append(nombreD + "," + nombre  + "," + nombresjugadores + "," + nombrestitulares + "," + presupuesto + "," + puntos + "," + real + "," + nomcapitan);
		}
		else
		{
			csvWriter.append("nombre.duenio,nombre.equipo,jugadores,titulares,presupuesto,puntos,real");
			csvWriter.append("\n");
			csvWriter.append(nombreD + "," + nombre  + "," + nombresjugadores + "," + nombrestitulares + "," + presupuesto + "," + puntos + "," + real);
		}
		csvWriter.flush();
		csvWriter.close();
		
	}

	public ArrayList<File> consultarDatosEquipos(String Nombre) throws IOException 
	{
		setPaths();
		ArrayList<File> equiposUsuario = new ArrayList<>();
		if (CarpetaEquipos.listFiles()!=null)
		{
			for (File file : CarpetaEquipos.listFiles()) 
			{
				String NombreArchivo = file.getName();
				BufferedReader bufferLectura = new BufferedReader(new FileReader(CarpetaEquipos +"/" + NombreArchivo));
				String linea = bufferLectura.readLine();
				linea = bufferLectura.readLine();
				String[] equipo = linea.split(",");
				if(equipo[0].equals(Nombre)) 
				{
					bufferLectura.close();
					equiposUsuario.add(file);
				}
				bufferLectura.close();
			}

			return equiposUsuario;
		}
		File file = new File("");
		equiposUsuario.add(file);
		return equiposUsuario;
	}

	public File consultarDatosEquipo(String Nombre) throws IOException 
	{
		setPaths();
		if (CarpetaEquipos.listFiles()!=null)
		{
			for (File file : CarpetaEquipos.listFiles()) 
			{
				String NombreArchivo = file.getName();
				BufferedReader bufferLectura = new BufferedReader(new FileReader(CarpetaEquipos +"/" + NombreArchivo));
				String linea = bufferLectura.readLine();
				linea = bufferLectura.readLine();
				String[] equipo = linea.split(",");
				if(equipo[0].equals(Nombre)) 
				{
					bufferLectura.close();
					return file;
				}
				bufferLectura.close();
			}
		}
		File file = new File("");
		return file;
	}

	public ArrayList<File> consultarEquiposReales() throws IOException 
	{
		setPaths();
		ArrayList<File> archivosreales = new ArrayList<>();
		if (CarpetaEquipos.listFiles()!=null)
		{
			for (File file : CarpetaEquipos.listFiles()) 
			{
				String NombreArchivo = file.getName();
				BufferedReader bufferLectura = new BufferedReader(new FileReader(CarpetaEquipos +"/" + NombreArchivo));
				String linea = bufferLectura.readLine();
				linea = bufferLectura.readLine();
				String[] equipo = linea.split(",");
				if(equipo[0].length()<4)
				{}
				else if((equipo[0].substring(0,4)).equals("real")) 
				{
					bufferLectura.close();
					archivosreales.add(file);
				}
				bufferLectura.close();
			}
		}
		return archivosreales;
	}
	
	public void cargarDatosJugador(jugador datos) throws IOException
	{
		String nombre = datos.getNombre();
		String posicion = datos.getPosicion();
		float precio = datos.getPrecio();
		int puntos = datos.getPuntos();
		String equipo = datos.getEquipo();
		String nombrecarpeta = CarpetaJugadores.getPath();
		if (existeCarpeta(nombrecarpeta))
		{
			//Se crea, en la carpeta Usuarios, un csv con el siguiente nombre: "nombreUsuario-contraseniaUsuario.csv"
			//Este contiene la info del usuario
			File file = consultarDatosJugador(nombre);
			file.delete();
			FileWriter csvWriter = new FileWriter(nombrecarpeta + "/" + nombre +  ".csv");
			csvWriter.append("nombre,posicion,precio,puntos,equipo");
			csvWriter.append("\n");
			csvWriter.append(nombre  + "," + posicion + "," + precio + "," + puntos + "," + equipo);
			csvWriter.flush();
			csvWriter.close();
		}

		else
		{
			CarpetaJugadores.mkdir();
			//Se crea, en la carpeta Usuarios, un csv con el siguiente nombre: "nombreUsuario-contraseniaUsuario.csv"
			//Este contiene la info del usuario
			FileWriter csvWriter = new FileWriter(nombrecarpeta + "/" + nombre +  ".csv");
			csvWriter.append("nombre,posicion,precio,puntos,equipo");
			csvWriter.append("\n");
			csvWriter.append(nombre  + "," + posicion + "," + precio + "," + puntos + "," + equipo);
			csvWriter.flush();
			csvWriter.close();
		}
	}
	

	public File consultarDatosJugador(String Nombre) throws IOException 
	{
		setPaths();
		if (CarpetaJugadores.listFiles()!=null)
		{
		for (File file : CarpetaJugadores.listFiles()) 
		{
			String NombreArchivo = file.getName();
			BufferedReader bufferLectura = new BufferedReader(new FileReader(CarpetaJugadores  +"/" + NombreArchivo));
			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();
			String[] jugador = linea.split(",");
			if(jugador[0].equals(Nombre)) 
			{
				bufferLectura.close();
				return file;
			}
			bufferLectura.close();
		}
		}
		File file = new File("");
		return file;
	}
	
	/**
	 * Se revisa si la carpeta usuario existe, en tal caso, se guardan los usuarios 
	 * que esten dentro de la carpeta
	 * 
	 * Retorna true, si se cargaron usuarios y false si no se cargaron
	 * @throws IOException 
	 */
	
	
	/**
	 * Carga los usuarios guardados en la carpeta Usuarios
	 */
	public void cargarUsuariosExistentes() throws IOException
	{
		//Este if se usa para validar que la carpeta no sea cargada en cada uso de la funcion
		if (usuarios.size()==0) 
		{
			for (File file : CarpetaUsuarios.listFiles()) 
			{
					String NombreArchivoUsuario = file.getName();
					BufferedReader bufferLectura = new BufferedReader(new FileReader("Usuarios/" + NombreArchivoUsuario));
					String linea = bufferLectura.readLine();
					linea = bufferLectura.readLine();
					String[] usuario = linea.split(","); 
					usuarios.add(usuario);
					bufferLectura.close();
			}
		}
	}
	
	
	/**
	 * 
	 * @return Un hasmap calendario, donde la llave es 1, y los valores un arraylist que contiene varios String[] donde los String[] son la linea del csv
	 * @throws IOException
	 */
	
	public HashMap<String, ArrayList<String[]>> cargarCalendario() throws IOException
	{
		
		ArrayList<String[]> datos = new ArrayList<String[]>();
		HashMap<String, ArrayList<String[]>> calendario = new HashMap<String, ArrayList<String[]>>();
		BufferedReader bufferLectura = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/App/calendario.csv" ));
		
		String linea = bufferLectura.readLine();
		
		while(linea != null)
		{
			
			linea = bufferLectura.readLine();
			if(linea != null)
			{
				String[] dato = linea.split(",");
				datos.add(dato);
				calendario.put("1", datos);
				
			}
			
		}
		
		bufferLectura.close();
		return calendario;
	}
	/**
	 * Comprueba si existe una carpeta.
	 * 
	 * @param nombreCarpeta
	 * @return Si existe, retorna true, si no false
	 */
	public Boolean existeCarpeta(String nombreCarpeta)
	{
	
		Boolean existe = false;
		
		File Carpeta = new File(nombreCarpeta);
		
		if(Carpeta.exists())
		{
			if(Carpeta.isDirectory())
			{
				existe = true;
			}
		}
		return existe;
	}
	
	
	/**
	 * Crea una carpeta "Usuarios"
	 */
	public void crearCarpetaUsuario()
	{
		CarpetaUsuarios.mkdir();
		System.out.println("=================================================================================================");
        System.out.println("Se ha creado una nueva carpeta Usuarios, recuerde la sincronización con GitHub, si es necesario!!");
        System.out.println("=================================================================================================");
	}
	
	/**
	 * =================================================================================================================
	 * METODOS PARTIDOS
	 * =================================================================================================================
	 * @throws IOException 
	 * 
	 */
	
	
	
	public void cargarPartidosCarpeta(String[] Fecha) throws IOException
	{
		
		CarpetaPartidos.mkdir();
		FileWriter csvWriter = new FileWriter(CarpetaPartidos+"/" + Fecha[0] + "-" + Fecha[1] + "-" + Fecha[2] + ".csv");
		csvWriter.append("equipo,jugador,cantidadMinutos,goles,penaltis,autogoles,asistencias,golesRecibidos,penaltisRecibidos,penaltisErrados,tarjetasAmarillas,tarjetasRojas");
		csvWriter.flush();
		csvWriter.close();
		
	}

	public void crearTemporada()
	{
		if(!existeCarpeta("Temporadas"))
			{
				CarpetaTemporadas.mkdir();
			}
		ArrayList<File> archivosTemporadas = new ArrayList<>();
		for (File file : CarpetaTemporadas.listFiles()) 
			{
				String NombreArchivo = file.getName();
				if(NombreArchivo.length()<9)
				{}
				else if((NombreArchivo.substring(0,9)).equals("Temporada")) 
				{
					archivosTemporadas.add(file);
				}
			}
		int numeroTemporadaActual = archivosTemporadas.size()+1;
		File carpetaTemporadaActual = new File("Temporadas/Temporada"+numeroTemporadaActual);
		carpetaTemporadaActual.mkdir();
		this.CarpetaUsuarios = new File("Usuarios");
		this.CarpetaEquipos = new File("Temporadas/Temporada"+numeroTemporadaActual+"/Equipos");
		this.CarpetaJugadores = new File("Temporadas/Temporada"+numeroTemporadaActual+"/Jugadores");
		this.CarpetaPartidos = new File("Temporadas/Temporada"+numeroTemporadaActual+"/Partidos");
		this.CarpetaFechas = new File("Temporadas/Temporada"+numeroTemporadaActual+"/Fechas");
	}
	
	public void setPaths()
	{
	if(!existeCarpeta("Temporadas"))
		{
			CarpetaTemporadas.mkdir();
		}
	ArrayList<File> archivosTemporadas = new ArrayList<>();
	for (File file : CarpetaTemporadas.listFiles()) 
		{
			String NombreArchivo = file.getName();
			if(NombreArchivo.length()<9)
			{}
			else if((NombreArchivo.substring(0,9)).equals("Temporada")) 
			{
				archivosTemporadas.add(file);
			}
		}
		int numeroTemporadaActual = archivosTemporadas.size();
		this.CarpetaUsuarios = new File("Usuarios");
		this.CarpetaEquipos = new File("Temporadas/Temporada"+numeroTemporadaActual+"/Equipos");
		this.CarpetaJugadores = new File("Temporadas/Temporada"+numeroTemporadaActual+"/Jugadores");
		this.CarpetaPartidos = new File("Temporadas/Temporada"+numeroTemporadaActual+"/Partidos");
		this.CarpetaFechas = new File("Temporadas/Temporada"+numeroTemporadaActual+"/Fechas");
	}
	
	public void crearFecha(int numeroFecha){
		if(!existeCarpeta("Fechas"))
			{
				CarpetaFechas.mkdir();
			}
		File carpetaFechaActual = new File(CarpetaFechas + "/Fecha" + numeroFecha);
		if(!existeCarpeta(CarpetaFechas + "/Fecha" + numeroFecha))
			{
				carpetaFechaActual.mkdir();
			}
	}

	public void crearPartido(String nombreEquipoLocal, String nombreEquipoVisitante, String fechaDias, String fechaHoras, int numeroFecha) throws IOException{
		String carpetaFecha = CarpetaFechas.getPath() + "/Fecha" + numeroFecha;

		FileWriter csvWriter = new FileWriter(carpetaFecha + "/" + nombreEquipoLocal + "-" + nombreEquipoVisitante + "_" + fechaDias + "&" + fechaHoras.replace(":", "-") + ".csv");
		csvWriter.flush();
		csvWriter.close();
	}

	public File consultarDatosPartido(String nombreEquipo,String numeroFecha) throws IOException
	{
		setPaths();
		String nombreCarpeta = CarpetaFechas.getPath() + "/" + numeroFecha;
		File carpetaFecha = new File(nombreCarpeta);
		if (carpetaFecha.listFiles()!=null)
		{
			for (File file : carpetaFecha.listFiles()) 
			{
				String NombreArchivo = file.getName();
				String[] partesNombreArchivo = NombreArchivo.split("_");
				String[] equipos = partesNombreArchivo[0].split("-");
				String equipoLocal = equipos[0];
				String equipoVisitante = equipos[1];
				if(equipoLocal.equals(nombreEquipo) ||equipoVisitante.equals(nombreEquipo) ) 
				{
					return file;
				}
			}
		}
		File file = new File("");
		return file;
	}

	public ArrayList<File> consultarEquiposFantasia() throws IOException 
	{
		setPaths();
		ArrayList<File> archivosequipos = new ArrayList<>();
		for (File file : CarpetaEquipos.listFiles()) 
		{
			String NombreArchivo = file.getName();
			if(!((NombreArchivo.substring(0,4)).equals("real"))) 
			{
				archivosequipos.add(file);
			}
		}
		return archivosequipos;
	}
	
	public void cargarPartido(partido partido, String nombreEquipoLocal, String nombreEquipoVisitante, int fecha, String fechaFormat) throws IOException{
		setPaths();
		File abiertas = new File("abiertas.txt");
		File cerradas = new File("cerradas.txt");
	    if (!abiertas.exists()) 
		    {
	    		abiertas.createNewFile();
	    		FileWriter txtWriter = new FileWriter("abiertas.txt");
	    		txtWriter.append("fecha"+1);
	    		txtWriter.flush();
	    		txtWriter.close();
	    		if ("fecha1".equals("fecha"+fecha))
				{
					HashMap<String, desempenio> equipoLocal = partido.getdesemepenioEquipoLocal();
					HashMap<String, desempenio> equipoVisitante = partido.getdesemepenioEquipoVisitante();
					HashMap<String, Integer> puntosJugadoresLocales = new HashMap<>();
					HashMap<String, Integer> puntosJugadoresVisitantes = new HashMap<>();
					HashMap<String, Boolean> golJugadoresLocales = new HashMap<>();
					HashMap<String, Boolean> golJugadoresVisitantes = new HashMap<>();
					HashMap<String, Boolean> sesentaJugadoresLocales = new HashMap<>();
					HashMap<String, Boolean> sesentaJugadoresVisitantes = new HashMap<>();
					int puntosLocal = 0;
					int puntosVisitantes = 0;
					String ganador;

					datos datos = new datos();
					Set<String> setnombresl = equipoLocal.keySet();
					ArrayList<String> nombresl = new ArrayList<String>(setnombresl);

					for(String nombre: nombresl)
						{
							desempenio desempenioj = equipoLocal.get(nombre);
							jugador jugadorj = datos.consultarJugador(nombre);
							String posicion = jugadorj.getPosicion();
							int puntosj = desempenioj.calculadoraPuntos(posicion);
							boolean gol = desempenioj.gol();
							boolean sesenta = desempenioj.sesenta();
							
							golJugadoresLocales.put(nombre, gol);
							sesentaJugadoresLocales.put(nombre, sesenta);
							puntosJugadoresLocales.put(nombre, puntosj);
							puntosLocal += puntosj;
						}
					Set<String> setnombresv = equipoVisitante.keySet();
					ArrayList<String> nombresv = new ArrayList<String>(setnombresv);
					for(String nombre: nombresv)
						{
							desempenio desempenioj = equipoVisitante.get(nombre);
							jugador jugadorj = datos.consultarJugador(nombre);
							String posicion = jugadorj.getPosicion();
							int puntosj = desempenioj.calculadoraPuntos(posicion);
							boolean gol = desempenioj.gol();
							boolean sesenta = desempenioj.sesenta();
							
							golJugadoresVisitantes.put(nombre, gol);
							sesentaJugadoresVisitantes.put(nombre, sesenta);
							puntosJugadoresVisitantes.put(nombre, puntosj);
							puntosVisitantes += puntosj;
						}

					ganador = "";
					
					if(puntosLocal > puntosVisitantes)
					{
						ganador = nombreEquipoLocal;
					}
					else if(puntosLocal < puntosVisitantes)
					{
						ganador = nombreEquipoVisitante;
					}

					File carpetaFecha = new File(CarpetaFechas + "/Fecha" + fecha);
				
					String NombreArchivoPartido = nombreEquipoLocal + "-" + nombreEquipoVisitante + "_" + fechaFormat.replace(":", "-").replace("/", "-");
					
					FileWriter csvWriter = new FileWriter(carpetaFecha + "/" + NombreArchivoPartido + ".csv");
					
					csvWriter.append(ganador);
					csvWriter.append("\n");
					
					

					for(String nombre: nombresl)
					{
						int puntosJugador = puntosJugadoresLocales.get(nombre);
						boolean golJugador = golJugadoresLocales.get(nombre);
						boolean sesentaJugador = sesentaJugadoresLocales.get(nombre);

						csvWriter.append(nombreEquipoLocal + ";" + nombre + "," + puntosJugador + "," + golJugador + "," +sesentaJugador);
						csvWriter.append("\n");

					}

					for(String nombre: nombresv)
					{
						int puntosJugador = puntosJugadoresVisitantes.get(nombre);
						boolean golJugador = golJugadoresVisitantes.get(nombre);
						boolean sesentaJugador = sesentaJugadoresVisitantes.get(nombre);

						csvWriter.append(nombreEquipoVisitante + ";" + nombre + "," + puntosJugador + "," + golJugador + "," + sesentaJugador);
						csvWriter.append("\n");
					}

					csvWriter.flush();
					csvWriter.close();
					
				}
		    }
	    else 
	    {
			BufferedReader bufferLectura = new BufferedReader(new FileReader("abiertas.txt"));
			String linea;
			String ultima ="";
			while((linea = bufferLectura.readLine()) != null)
			{
				ultima = linea;
			}
			if (ultima.equals("fecha"+fecha))
			{
				HashMap<String, desempenio> equipoLocal = partido.getdesemepenioEquipoLocal();
				HashMap<String, desempenio> equipoVisitante = partido.getdesemepenioEquipoVisitante();
				HashMap<String, Integer> puntosJugadoresLocales = new HashMap<>();
				HashMap<String, Integer> puntosJugadoresVisitantes = new HashMap<>();
				HashMap<String, Boolean> golJugadoresLocales = new HashMap<>();
				HashMap<String, Boolean> golJugadoresVisitantes = new HashMap<>();
				HashMap<String, Boolean> sesentaJugadoresLocales = new HashMap<>();
				HashMap<String, Boolean> sesentaJugadoresVisitantes = new HashMap<>();
				int puntosLocal = 0;
				int puntosVisitantes = 0;
				String ganador;

				datos datos = new datos();
				Set<String> setnombresl = equipoLocal.keySet();
				ArrayList<String> nombresl = new ArrayList<String>(setnombresl);

				for(String nombre: nombresl)
					{
						desempenio desempenioj = equipoLocal.get(nombre);
						jugador jugadorj = datos.consultarJugador(nombre);
						String posicion = jugadorj.getPosicion();
						int puntosj = desempenioj.calculadoraPuntos(posicion);
						boolean gol = desempenioj.gol();
						boolean sesenta = desempenioj.sesenta();
						if (fecha>2) 
						{
							if (consultarTresGoles(fecha,nombreEquipoVisitante,nombre)&& gol) 
							{
								puntosj+=10;
							}
							if (consultarSesentaMinutos(fecha,nombreEquipoVisitante,nombre)&& sesenta) 
							{
								puntosj+=5;
							}
							
						} 
						
						golJugadoresLocales.put(nombre, gol);
						sesentaJugadoresLocales.put(nombre, sesenta);
						puntosJugadoresLocales.put(nombre, puntosj);
						puntosLocal += puntosj;
					}
				Set<String> setnombresv = equipoVisitante.keySet();
				ArrayList<String> nombresv = new ArrayList<String>(setnombresv);
				for(String nombre: nombresv)
					{
						desempenio desempenioj = equipoVisitante.get(nombre);
						jugador jugadorj = datos.consultarJugador(nombre);
						String posicion = jugadorj.getPosicion();
						int puntosj = desempenioj.calculadoraPuntos(posicion);
						boolean gol = desempenioj.gol();
						boolean sesenta = desempenioj.sesenta();
						if (fecha>2) 
						{
							if (consultarTresGoles(fecha,nombreEquipoVisitante,nombre)&& gol) 
							{
								puntosj+=10;
							}
							if (consultarSesentaMinutos(fecha,nombreEquipoVisitante,nombre)&& sesenta) 
							{
								puntosj+=5;
							}
						} 
						
						golJugadoresVisitantes.put(nombre, gol);
						sesentaJugadoresVisitantes.put(nombre, sesenta);
						puntosJugadoresVisitantes.put(nombre, puntosj);
						puntosVisitantes += puntosj;
					}

				ganador = "";
				
				if(puntosLocal > puntosVisitantes)
				{
					ganador = nombreEquipoLocal;
				}
				else if(puntosLocal < puntosVisitantes)
				{
					ganador = nombreEquipoVisitante;
				}

				File carpetaFecha = new File(CarpetaFechas + "/Fecha" + fecha);
			
				String NombreArchivoPartido = nombreEquipoLocal + "-" + nombreEquipoVisitante + "_" + fechaFormat.replace(":", "-").replace("/", "-");
				
				FileWriter csvWriter = new FileWriter(carpetaFecha + "/" + NombreArchivoPartido + ".csv");
				
				csvWriter.append(ganador);
				csvWriter.append("\n");
				
				

				for(String nombre: nombresl)
				{
					int puntosJugador = puntosJugadoresLocales.get(nombre);
					boolean golJugador = golJugadoresLocales.get(nombre);
					boolean sesentaJugador = sesentaJugadoresLocales.get(nombre);

					csvWriter.append(nombreEquipoLocal + ";" + nombre + "," + puntosJugador + "," + golJugador + "," + sesentaJugador);
					csvWriter.append("\n");

				}

				for(String nombre: nombresv)
				{
					int puntosJugador = puntosJugadoresVisitantes.get(nombre);
					boolean golJugador = golJugadoresVisitantes.get(nombre);
					boolean sesentaJugador = sesentaJugadoresVisitantes.get(nombre);

					csvWriter.append(nombreEquipoVisitante + ";" + nombre + "," + puntosJugador + "," + golJugador + "," + sesentaJugador);
					csvWriter.append("\n");
				}

				csvWriter.flush();
				csvWriter.close();
				
			}
		    else if (ultima.equals("fecha"+(fecha-1)))
		    {
	    		FileWriter txtWriter = new FileWriter("abiertas.txt");
	    		txtWriter.append("fecha"+fecha);
	    		txtWriter.flush();
	    		txtWriter.close();
	    		
	    	    if (!cerradas.exists()) 
	    		    {
	    	    		cerradas.createNewFile();
	    		    }
	    		FileWriter lstWriter = new FileWriter("cerradas.txt",true);
	    		lstWriter.append(ultima);
	    		lstWriter.flush();
	    		lstWriter.close();

				calcularMejorEquipoFecha(fecha - 1);

				HashMap<String, desempenio> equipoLocal = partido.getdesemepenioEquipoLocal();
				HashMap<String, desempenio> equipoVisitante = partido.getdesemepenioEquipoVisitante();
				HashMap<String, Integer> puntosJugadoresLocales = new HashMap<>();
				HashMap<String, Integer> puntosJugadoresVisitantes = new HashMap<>();
				HashMap<String, Boolean> golJugadoresLocales = new HashMap<>();
				HashMap<String, Boolean> golJugadoresVisitantes = new HashMap<>();
				HashMap<String, Boolean> sesentaJugadoresLocales = new HashMap<>();
				HashMap<String, Boolean> sesentaJugadoresVisitantes = new HashMap<>();
				int puntosLocal = 0;
				int puntosVisitantes = 0;
				String ganador;

				datos datos = new datos();
				Set<String> setnombresl = equipoLocal.keySet();
				ArrayList<String> nombresl = new ArrayList<String>(setnombresl);

				for(String nombre: nombresl)
					{
						desempenio desempenioj = equipoLocal.get(nombre);
						jugador jugadorj = datos.consultarJugador(nombre);
						String posicion = jugadorj.getPosicion();
						int puntosj = desempenioj.calculadoraPuntos(posicion);
						boolean gol = desempenioj.gol();
						boolean sesenta = desempenioj.sesenta();
						if (fecha>2) 
						{
							if (consultarTresGoles(fecha,nombreEquipoVisitante,nombre)&& gol) 
							{
								puntosj+=10;
							}
							if (consultarSesentaMinutos(fecha,nombreEquipoVisitante,nombre)&& sesenta) 
							{
								puntosj+=5;
							}
							
						} 
						
						golJugadoresLocales.put(nombre, gol);
						sesentaJugadoresLocales.put(nombre, sesenta);
						puntosJugadoresLocales.put(nombre, puntosj);
						puntosLocal += puntosj;
					}
				Set<String> setnombresv = equipoVisitante.keySet();
				ArrayList<String> nombresv = new ArrayList<String>(setnombresv);
				for(String nombre: nombresv)
					{
						desempenio desempenioj = equipoVisitante.get(nombre);
						jugador jugadorj = datos.consultarJugador(nombre);
						String posicion = jugadorj.getPosicion();
						int puntosj = desempenioj.calculadoraPuntos(posicion);
						boolean gol = desempenioj.gol();
						boolean sesenta = desempenioj.sesenta();
						if (fecha>2) 
						{
							if (consultarTresGoles(fecha,nombreEquipoVisitante,nombre)&& gol) 
							{
								puntosj+=10;
							}
							if (consultarSesentaMinutos(fecha,nombreEquipoVisitante,nombre)&& sesenta) 
							{
								puntosj+=5;
							}
							
						} 
						
						golJugadoresVisitantes.put(nombre, gol);
						sesentaJugadoresVisitantes.put(nombre, sesenta);
						puntosJugadoresVisitantes.put(nombre, puntosj);
						puntosVisitantes += puntosj;
					}

				ganador = "";
				
				if(puntosLocal > puntosVisitantes)
				{
					ganador = nombreEquipoLocal;
				}
				else if(puntosLocal < puntosVisitantes)
				{
					ganador = nombreEquipoVisitante;
				}

				File carpetaFecha = new File(CarpetaFechas + "/Fecha" + fecha);
			
				String NombreArchivoPartido = nombreEquipoLocal + "-" + nombreEquipoVisitante + "_" + fechaFormat.replace(":", "-").replace("/", "-");
				
				FileWriter csvWriter = new FileWriter(carpetaFecha + "/" + NombreArchivoPartido + ".csv");
				
				csvWriter.append(ganador);
				csvWriter.append("\n");
				
				

				for(String nombre: nombresl)
				{
					int puntosJugador = puntosJugadoresLocales.get(nombre);
					boolean golJugador = golJugadoresLocales.get(nombre);
					boolean sesentaJugador = sesentaJugadoresLocales.get(nombre);

					csvWriter.append(nombreEquipoLocal + ";" + nombre + "," + puntosJugador + "," + golJugador + "," + sesentaJugador);
					csvWriter.append("\n");

				}

				for(String nombre: nombresv)
				{
					int puntosJugador = puntosJugadoresVisitantes.get(nombre);
					boolean golJugador = golJugadoresVisitantes.get(nombre);
					boolean sesentaJugador = sesentaJugadoresVisitantes.get(nombre);

					csvWriter.append(nombreEquipoVisitante + ";" + nombre + "," + puntosJugador + "," + golJugador + "," + sesentaJugador);
					csvWriter.append("\n");
				}

				csvWriter.flush();
				csvWriter.close();
		    }
			bufferLectura.close();
	    }

	}

	public File consultarDatosPartido(String Nombre) throws IOException 
	{
		setPaths();
		if (CarpetaJugadores.listFiles()!=null)
		{
			for (File file : CarpetaJugadores.listFiles()) 
			{
				String NombreArchivo = file.getName();
				BufferedReader bufferLectura = new BufferedReader(new FileReader(CarpetaJugadores  +"/" + NombreArchivo));
				String linea = bufferLectura.readLine();
				linea = bufferLectura.readLine();
				String[] jugador = linea.split(",");
				if(jugador[0].equals(Nombre)) 
				{
					bufferLectura.close();
					return file;
				}
				bufferLectura.close();
			}
		}
		File file = new File("");
		return file;
	}
	
	public boolean verificarGanador(String equipo) throws IOException
	{
		setPaths();
		BufferedReader bufferLecturaabiertas = new BufferedReader(new FileReader("abiertas.txt"));
		String linea;
		String fecha ="";
		while((linea = bufferLecturaabiertas.readLine()) != null)
		{
			fecha = linea;
		}
		File partido = consultarDatosPartido(equipo,fecha);
		BufferedReader bufferLecturaPartido = new BufferedReader(new FileReader(partido));
		String ganador= bufferLecturaPartido.readLine();
		if (ganador==null) {}
		else if (ganador.equals(equipo))
		{
			bufferLecturaPartido.close();
			bufferLecturaabiertas.close();
			return true;
		}
		bufferLecturaPartido.close();
		bufferLecturaabiertas.close();
		return false;
	}

	public String consultarFechaAbierta() throws IOException
	{
		BufferedReader bufferLecturaabiertas = new BufferedReader(new FileReader("abiertas.txt"));
		String linea;
		String fecha ="";
		while((linea = bufferLecturaabiertas.readLine()) != null)
		{
			fecha = linea;
		}
		bufferLecturaabiertas.close();
		if (fecha.equals(""))
		{
			fecha = "fecha"+0;
		}
		return fecha;
	}

	public ArrayList<String> consultarFechasCerradas() throws IOException
	{
		BufferedReader bufferLectura = new BufferedReader(new FileReader("cerradas.txt"));
		ArrayList<String> fechas = new ArrayList<>();
		String linea;
		String fecha ="";
		while((linea = bufferLectura.readLine()) != null)
		{
			fechas.add(linea);
		}
		bufferLectura.close();
		if (fecha.equals(""))
		{
			fecha = "fecha"+0;
		}
		return fechas;
	}
	
	public void calcularMejorEquipoFecha(int fecha) throws IOException
	{
		datos datos = new datos();
		ArrayList<File> equiposFantasia = consultarEquiposFantasia();
		datos convertidorArchivos = new datos();
		ArrayList<equipo> equipos = new ArrayList<>();

		for(File f: equiposFantasia) 
		{
			equipo equipoFantasia = convertidorArchivos.convertirArchivoEquipo(f);
	    	if (!equipoFantasia.getNombre().equals(""))
	    		{
		    		ArrayList<jugador> Titulares = (equipoFantasia.getTitulares());
		    		int puntostotales = 0;
		    		jugador Capitan = equipoFantasia.getCapitan();
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
		    		equipoFantasia.setPuntos(puntostotales);
		    		}
	    		}
			equipos.add(equipoFantasia);
		}

		Collections.sort(equipos, ((o1, o2) -> ((equipo) o2).getPuntos().compareTo(((equipo) o1).getPuntos())));

		if (equipos.size()>0)
		{
		equipo maxEquipo = (equipo) equipos.get(0);

		String nombreMaxEquipo = maxEquipo.getNombre();

		String carpetaFecha = CarpetaFechas.getPath() + "/Fecha" + fecha;

		File archivoMaxEquipo = new File(carpetaFecha, nombreMaxEquipo+".txt");

		archivoMaxEquipo.createNewFile();
		}
	}
	
	public String consultarMejorEquipoFecha(int numeroFecha)
	{
		setPaths();
		String nombreCarpeta = CarpetaFechas.getPath() + "/Fecha" +numeroFecha;
		File carpetaFecha = new File(nombreCarpeta);
		if (carpetaFecha.listFiles()!=null)
		{
			for (File file : carpetaFecha.listFiles()) 
			{
				String NombreArchivo = file.getName();
				if (!(NombreArchivo.length()>3)) {}
				if(NombreArchivo.substring(NombreArchivo.length() - 3).equals("txt"))
				{
					return NombreArchivo;
				}
			}
		}
		File file = new File("");
		return "";
	}
	
	public boolean consultarTresGoles(int numeroFechaActual, String nombreEquipo, String nombreJ) throws IOException
	{
		setPaths();
		int centinela = 1;
		boolean vabien = false;
		while (centinela!=3)
		{
		String nombreCarpeta = CarpetaFechas.getPath() + "/Fecha" + (numeroFechaActual-centinela);
		File carpetaFecha = new File(nombreCarpeta);
		if (carpetaFecha.listFiles()!=null)
		{
			for (File file : carpetaFecha.listFiles()) 
			{
				String NombreArchivo = file.getName();
				ArrayList<String> Separacion = new ArrayList<String>(Arrays.asList(NombreArchivo.split("_")));
				ArrayList<String> NombresEquiposPartido = new ArrayList<String>(Arrays.asList((Separacion.get(0)).split("-")));
				if((nombreEquipo.equals(NombresEquiposPartido.get(0)))||(nombreEquipo.equals(NombresEquiposPartido.get(1))))
				{
					BufferedReader bufferLecturaPartido = new BufferedReader(new FileReader(nombreCarpeta+"/"+NombreArchivo));
					String linea;
					String gol ="";
					bufferLecturaPartido.readLine();
					while((linea = bufferLecturaPartido.readLine()) != null)
					{
						String[] separador = linea.split(";");
						String[] datos = separador[1].split(",");
						String nombre = datos[0];
						if(nombre.equals(nombreJ))
						{
							gol = datos[2];
							if(gol.equals("true"))
							{
								vabien = true;
							}
							
						}
					}
					bufferLecturaPartido.close();
				}
			}
			
		}
		centinela+=1;
		}
		return vabien;
	}
	
	public boolean consultarSesentaMinutos(int numeroFechaActual, String nombreEquipo, String nombreJ) throws IOException
	{
		setPaths();
		int centinela = 1;
		boolean vabien = false;
		while (centinela!=3)
		{
		String nombreCarpeta = CarpetaFechas.getPath() + "/Fecha" + (numeroFechaActual-centinela);
		File carpetaFecha = new File(nombreCarpeta);
		if (carpetaFecha.listFiles()!=null)
		{
			for (File file : carpetaFecha.listFiles()) 
			{
				String NombreArchivo = file.getName();
				ArrayList<String> Separacion = new ArrayList<String>(Arrays.asList(NombreArchivo.split("_")));
				ArrayList<String> NombresEquiposPartido = new ArrayList<String>(Arrays.asList((Separacion.get(0)).split("-")));
				if((nombreEquipo.equals(NombresEquiposPartido.get(0)))||(nombreEquipo.equals(NombresEquiposPartido.get(1))))
				{
					BufferedReader bufferLecturaPartido = new BufferedReader(new FileReader(nombreCarpeta+"/"+NombreArchivo));
					String linea;
					String sesenta ="";
					bufferLecturaPartido.readLine();
					while((linea = bufferLecturaPartido.readLine()) != null)
					{
						String[] separador = linea.split(";");
						String[] datos = separador[1].split(",");
						String nombre = datos[0];
						if(nombre.equals(nombreJ))
						{
							sesenta = datos[3];
							if(sesenta.equals("true"))
							{
								vabien = true;
							}
							
						}
					}
					bufferLecturaPartido.close();
				}
			}
			
		}
		centinela+=1;
		}
		return vabien;
	}
	
	public ArrayList<equipo> ObtenerEquiposFantasia() throws IOException
	{
		datos datos = new datos();
		setPaths();
		ArrayList<equipo> EquiposFantasia = new ArrayList<equipo>();
		for (File file : CarpetaEquipos.listFiles()) 
		{
			BufferedReader bufferLectura = new BufferedReader(new FileReader(file));
			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();
			String[] datosEquipo = linea.split(",");
			if(datosEquipo[6].equals("false"))
			{
				String nombreUsuario = datosEquipo[0];
				String nombreEquipo = datosEquipo[1];
				List<String> nombresjugadores = Arrays.asList(datosEquipo[2].split("-"));
				ArrayList<jugador> Jugadores = new ArrayList<>();
				for (String nombrej: nombresjugadores)
				{
					if (!nombrej.equals(""))
					{
						jugador j = datos.consultarJugador(nombrej);
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
						jugador j = datos.consultarJugador(nombret);
						if (!j.getNombre().equals(""))
						{
							Titulares.add(datos.consultarJugador(nombret));
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
					jugador capitan = datos.consultarJugador(datosEquipo[7]);
					if (capitan!=null)
					{
						EquipoUsu.setCapitan(capitan);
					}
				}
				
				EquiposFantasia.add(EquipoUsu);
				
			}
		}
		return EquiposFantasia;
	}	
}

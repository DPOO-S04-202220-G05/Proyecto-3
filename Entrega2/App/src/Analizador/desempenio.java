package Analizador;

public class desempenio {
	
	/* ===========
	 * * ATRIBUTOS
	   ===========
	*/ 
	
	/*
	 * Un int que representa los minutos que jugo el jugador
	 */
	private int cantidaMinutos;
	
	/*
	 * Un int que representa la cantidad de goles que marco el jugador
	 */
	private int goles;

	/*
	 * Un int que representa la cantidad de penaltis que marco el jugador
	 */
	private int penaltis;

	/*
	 * Un int que representa la cantidad de autogoles que marco el jugador
	 */
	private int autogoles;

	/*
	 * Un int que representa la cantidad de asistencias del jugador
	 */
	private int asistencias;

	/*
	 * Un int que representa la cantidad de autogoles que recibio el jugador
	 */
	private int golesRecibidos;

	/*
	 * Un int que representa la cantidad de penaltis que recibio el jugador
	 */
	private int penaltisRecibidos;
	

	/*
	 * Un int que representa la cantidad de penaltis que atajo el jugador
	 */
	private int penaltisAtajados;
	
	/*
	 * Un int que representa la cantidad de penaltis que erro el jugador
	 */
	private int penaltisErrados;

	/*
	 * Un int que representa la cantidad de tarjetas amarillas que recibio el jugador
	 */
	private int tarjetasAmarillas;

	/*
	 * Un int que representa la cantidad de tarjetas rojas que recibio el jugador
	 */
	private int tarjetasRojas;
	/* ==================================================================================
	 * * 							MÉTODOS
	   ==================================================================================
	*/
	
	// =======================================================================
	// 						Metodo Constructor
	// =======================================================================
	/**
	 * Construye una nueva tarjeta de desempeño de un jugador e inicializa sus atributos con la información de
	 * los parámetros.
	 * 
	 * @param aun no se sabe
	 */

	public desempenio(int cantidadMinutos, int goles, int penaltis, int autogoles, int asistencias, int golesRecibidos, int penaltisRecibidos, int penaltisAtajados, int penaltisErrados, int tarjetasAmarillas, int tarjetasRojas){
		this.cantidaMinutos = cantidadMinutos;
		this.goles = goles;
		this.penaltis = penaltis;
		this.autogoles = autogoles;
		this.asistencias = asistencias;
		this.golesRecibidos = golesRecibidos;
		this.penaltisAtajados = penaltisAtajados;
		this.penaltisRecibidos = penaltisRecibidos;
		this.penaltisAtajados = penaltisAtajados; 
		this.penaltisErrados = penaltisErrados;
		this.tarjetasAmarillas = tarjetasAmarillas;
		this.tarjetasRojas = tarjetasRojas;

	}
	
	public int calculadoraPuntos(String posicionj)
	{
		int puntos=0;
		if(this.cantidaMinutos>=0)
			{
			if(this.cantidaMinutos<=60)
				{
					puntos+=1;
				}
			if(this.cantidaMinutos>60)
				{
					puntos+=2;
				}
			if(posicionj.equals("Delantero"))
				{
					puntos+=4*this.goles;
				}
			else if(posicionj.equals("MedioCampista"))
				{
					puntos+=5*this.goles;
				}
			else if(posicionj.equals("Arquero")||posicionj.equals("Defensa"))
				{
					puntos+=6*this.goles;
					if(this.golesRecibidos==0)
					{
						puntos+=4;
					}
					if(posicionj.equals("Arquero") && this.penaltisAtajados>0)
					{
						puntos+=5;
					}
				}
			puntos-=2*this.penaltisErrados;
			puntos-=1*this.tarjetasAmarillas;
			puntos-=3*this.tarjetasRojas;
			puntos-=2*this.autogoles;
			puntos+=3*this.asistencias;
			}
		return puntos;
	}
    
    
}

package Interfaz;

import javax.swing.*;

public class PopUps {
	private JFrame jFrame = new JFrame();
		
	//Muestra un pop up diciendo que la cuenta se creo exitosamente
	public void showCuentaExitosa() {
		JOptionPane.showMessageDialog(jFrame, "Cuenta creada exitosamente!");
    }
	
	public void showCuentaNoCreada()
	{
		JOptionPane.showMessageDialog(jFrame, "Cuenta no creada, el usuario con ese nombre ya existe");
	}
	
	public void showInicioNoExitoso()
	{
		JOptionPane.showMessageDialog(jFrame, "Verifique su nombre de usuario y contraseña");
	}
	
	public void showInicioExitoso()
	{
		JOptionPane.showMessageDialog(jFrame, "Inicio de sesión exitoso");
	}
	
	public void showCreacionEqExitoso()
	{
		JOptionPane.showMessageDialog(jFrame, "Equipo Creado exitosamente");
	}
	
	public void showBestTeam(String Nombre)
	{
		JOptionPane.showMessageDialog(jFrame, "El mejor Equipo es!: " + Nombre);
	}
	

	public void mejorYpeorJugador(String Mejor, String MejorP, String Peor, String PeorP)
	{
		JOptionPane.showMessageDialog(jFrame, "El jugador con más puntos es: " + Mejor + " con " + MejorP +  " Puntos "
										+ "\nEl jugador con menos puntos es: " + Peor + " con " + PeorP +  " Puntos ");
	}
	
	public void compraExitosa()
	{
		JOptionPane.showMessageDialog(jFrame, "Jugador comprado exitosamente");
	}
	
	public void compraExedeLimite()
	{
		JOptionPane.showMessageDialog(jFrame, "Posicion no permitida, excede el limite");
	}
	
	public void presupuesto(float presupuesto)
	{
		JOptionPane.showMessageDialog(jFrame, "Su presupuesto es de: " + presupuesto + "$");
	}
	
	public void titularExitoso()
	{
		JOptionPane.showMessageDialog(jFrame, "Jugador elegido como titular exitosamente");
	}
	
	public void titularNoExitoso()
	{
		JOptionPane.showMessageDialog(jFrame, "Posicion no permitida");
	}
	
	public void elegirCap(String name)
	{
		JOptionPane.showMessageDialog(jFrame, "Eligio a " + name + " como capitan");
	}
	
	public void errorMejorEquipo()
	{
		JOptionPane.showMessageDialog(jFrame,"Error!: No se ha acabado ninguna fecha, por lo que no hay un mejor equipo");
	}


	public void puntosUsuario(int puntos)
	{
		JOptionPane.showMessageDialog(jFrame,"Su equipo tiene : "+puntos+" puntos !");
	}
    
}


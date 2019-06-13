package imagen;

import java.io.IOException;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Imagen imagen= new Imagen();
		// imagen.imprimirEstructura();
		
		Diferencias diferencias= new Diferencias();
		Vector<Integer>dif= diferencias.getDiferencias();
		
		System.out.println("paquetes en imagen     " + imagen.getSize());
		System.out.println("N° Diferencias" + diferencias.getSize());
		System.out.println("Paq Fila Col");
		
		int numeroPaquete, fila, columna;
		for(int i= 0; i<dif.size(); i+=3) {
			numeroPaquete= dif.elementAt(i);
			fila= dif.elementAt(i+1);
			columna= dif.elementAt(i+2);
			System.out.println(numeroPaquete + " " + fila +  " " + columna);
		
			if (fila != -1 && columna != -1) {
				Paquete paquete = imagen.getPaquete(numeroPaquete);
				paquete.repararPaquete(fila, columna);
			}
		}
		
		imagen.guardaImagenNueva();
		
		// imagen.imprimir();

		System.out.println("");
		System.out.println("Imagen Nueva Guardada!!");


	}
}


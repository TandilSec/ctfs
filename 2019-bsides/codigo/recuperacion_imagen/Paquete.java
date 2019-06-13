package imagen;

import java.util.Vector;

public class Paquete {
	Vector<Byte>paquete;

	public Paquete() {
		paquete= new Vector<Byte>();
	}
	
	public void agregarByte(byte b) {
		paquete.add(b);
	}
	
	public void borrar() {
		paquete.clear();
	}
	
	public Vector<Byte> getElementos(){
		return (Vector<Byte>) this.paquete.clone();
	}
	
	public void repararPaquete(int fila, int columna) {
		// calcula byte dentro del paquete y el bit a reparar dentro del byte
		// a partir de los valores de los vectores paridad
		
		int byte_, bit;
		
		if (columna <= 7) {
			byte_= fila*2;
			bit= 7 - columna; //porque los bits se cuentan al reves  7 6 5 4 3 2 1 0
		}else {
			byte_= fila*2 + 1;
			bit= 15 - columna;
		}
		

		//obtengo el byte dentro del paquete 
		byte b= paquete.elementAt(byte_);
		
		// arreglo el bit (le pongo el valor contrario al que tiene)
		setBit(b, bit, !getBit(b, bit));
		
		// pongo el byte arreglado de nuevo en el paquete
		paquete.set(byte_, b);
		
	}
	

	public final static Boolean getBit(byte byte_, int posicion){
	    return (byte_ & (1 << posicion)) != 0;
	}
	
	public final static byte setBit(int byte_,int posicion,boolean valor){
	    if (valor)
	        return (byte) (byte_ | (1 << posicion));
	    return (byte) (byte_ & ~(1 << posicion));
	}
	
	public final static String decToBin(int decimal) {
		return String.format("%8s", Integer.toBinaryString(decimal)).replace(' ', '0'); 
	}
	

	
	
	
	
}

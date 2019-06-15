package imagen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Imagen {
	Vector<Paquete>imagen;
	
	public Imagen() throws IOException {
		imagen= new Vector<Paquete>();
		
		// abre el archivo y lo carga paquete x paquete en el vector
		String directory = "/home/alejandro/Codigo/eclipse/imagen/src/imagen";
		String pathRota = directory + File.separator + "imagen_rota.png";

		File archivo = new File(pathRota);
        FileInputStream fis = new FileInputStream(archivo);
//		FileReader fis = new FileReader(archivo);  

		        
        int b;
        int bytesLeidos = 0;
        Paquete paquete= new Paquete();
        
        while((b = fis.read()) != -1){
            paquete.agregarByte((byte) b);
            bytesLeidos++;
            if(bytesLeidos == 10){
            	imagen.add(paquete);
            	// System.out.println("bytes leidos: " + bytesLeidos);
            	bytesLeidos= 0;
            	paquete= new Paquete();
            }
        }
        fis.close();
	}
	
	public void imprimirEstructura() {
        for (int i=0; i<imagen.size();i++) {
        	System.out.println("paquete N° " + i + " tamaño: "+ imagen.elementAt(i).getElementos().size());        		
        	Vector<Byte> elementosPaquete= imagen.elementAt(i).getElementos();
        	for (int j= 0; j< elementosPaquete.size(); j++) {
        		System.out.print("byte " + j + " " + elementosPaquete.elementAt(j)+ " ");
        	}
        }
        System.out.println("");
	}		
        
        		
	public Paquete getPaquete(int numero) {
		return imagen.elementAt(numero);
	}
	
	public int getSize() {
		return imagen.size();
	}
	
	public void guardaImagenNueva() throws IOException {
		String directory = "/home/alejandro/Codigo/eclipse/imagen/src/imagen";
		String pathNueva = directory + File.separator + "imagenNueva.png";

        File archivoSalida = new File(pathNueva);
        FileOutputStream fos = new FileOutputStream(archivoSalida);
		
        
        for (Paquete paquete: imagen) {
			for(byte b: paquete.getElementos()) {
				fos.write(b);
			}
		}
        fos.close();
	}
	
	public void imprimir() {
        for (Paquete paquete: imagen) {
			for(byte b: paquete.getElementos()) {
				System.out.print(b + " ");
			}
			System.out.println("");
		}
	}
	
	
}


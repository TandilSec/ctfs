package imagen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Diferencias {
	String directory = "/home/alejandro/Codigo/eclipse/imagen/src/imagen";
	
	// en el vector se guardan: Nro.paquete, fila, columna, nro.paquete, fila, columna.....
	Vector<Integer>diferencias;

	public Diferencias() {
		diferencias= new Vector<Integer>();
		
		Vector<String>bits_paridad= cargarParidadOk();
		Vector<String>paridad_roto= cargarParidadRoto();
		
		String filaRoto= "";
		String columnaRoto= "";
		String filaOk= "";
		String columnaOk= "";
		
		for(int i=0; i<7579; i++) {
			filaRoto= (paridad_roto.elementAt(i)).substring(0, 5);
			filaOk= (bits_paridad.elementAt(i)).substring(0, 5);
			columnaRoto= paridad_roto.elementAt(i).substring(6);
			columnaOk=bits_paridad.elementAt(i).substring(6);
			
			diferencias.add(i);				//i lleva el numero de paquete
			//chequea las filas
			char temp1, temp2;
			int resultado= -1;				// si no hay diferencia guarda -1
			
			for (int k=0; k<5; k++) {
				temp1= filaRoto.charAt(k);
				temp2= filaOk.charAt(k);
				if (temp1 != temp2) {
					resultado= k;
				}
			}
			diferencias.add(resultado);		//carga la fila de la diferencia
			
			resultado= -1;
			for (int k=0; k<16; k++) {
				temp1= columnaRoto.charAt(k);
				temp2= columnaOk.charAt(k);
				if (temp1 != temp2) {
					resultado= k;
				}
			}
			diferencias.add(resultado);		//carga la columna de la diferencia
		}
	}
	public int getSize() {
		return diferencias.size();
	}
	
	public Vector<Integer> getDiferencias(){
		return (Vector<Integer>) diferencias.clone();
	}

	public Vector<String> cargarParidadOk() {
		Vector<String>bits_paridad= new Vector<String>();
		
		String paridadOk = directory + File.separator + "bits_paridad.txt";
		
		// cargo las paridades reales en un vector
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(paridadOk))) {  
		    String line = bufferedReader.readLine();
		    bits_paridad.add(line);
		    while(line != null) {
		        //System.out.println(line);
		        line = bufferedReader.readLine();
		        bits_paridad.add(line);
		    }
		    bufferedReader.close();
		} catch (FileNotFoundException e) {
		    // exception handling
		} catch (IOException e) {
		    // exception handling
		}
		return bits_paridad;
	}
	
	
	public Vector<String> cargarParidadRoto() {
		Vector<String>paridad_roto= new Vector<String>();
		
		String paridadRoto = directory + File.separator + "paridad_roto.txt";
		
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(paridadRoto))) {  
		    String line = bufferedReader.readLine();
		    paridad_roto.add(line);
		    while(line != null) {
		        //System.out.println(line);
		        line = bufferedReader.readLine();
		        paridad_roto.add(line);
		    }
		    bufferedReader.close();
		} catch (FileNotFoundException e) {
		    // exception handling
		} catch (IOException e) {
		    // exception handling
		}
		return paridad_roto;
	}
	
	public void imprimeDiferencias() {
		for(int i=0; i<diferencias.size(); i+=3) {
			System.out.println("paquete: " + diferencias.elementAt(i)+" x" + diferencias.elementAt(i+1)+" y" + diferencias.elementAt(i+2));
		}
	}
	
	public void guardaArchivoDiferencias(){
		String diferencia = directory + File.separator + "diferencias.txt";
	
		// escribe diferencias en archivo
		String contenido= "";
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(diferencia))) {  
			for(int i=0; i<diferencias.size(); i++) {
				contenido = diferencias.elementAt(i) + "\n";
				bufferedWriter.write(contenido);
			}
		} catch (IOException e) {
		    // exception handling
		}
	}


}


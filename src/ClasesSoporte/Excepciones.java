package ClasesSoporte;

import javax.swing.JOptionPane;

	
public class Excepciones extends Exception {


		public static void ErrorImportacion(String pestania, String campo) {
			
			JOptionPane.showMessageDialog(null, "Error en la importación en la pestaña "+pestania+" en la tabla "+campo);	

		}
		
		public static void FicheroNoEncontrado() {
			
			JOptionPane.showMessageDialog(null, "No se encontró el fichero");
		}
		
	}



package Exportar;

import java.util.ArrayList;


/*
 * CLASE PARA AÑADIR A UNA LISTA LAS RUTAS QUE NOS INTERESEN
 * 
 * LAS RUTAS DEBERÁN SER DEFINIDAS EN UN FICHERO SYSTEM.PROPERTIES
 * 
 * SE LEERÁN TODOS LOS EXCELS SEGÚN LAS RUTAS CARGADAS EN ESTA CLASE
 */

public class CargarRutasArchivosImportacion {


		private ArrayList<String> rutas= new ArrayList<String>();
		
		public CargarRutasArchivosImportacion(Configuracio config) {
			
			  rutas.add(config.getPathImportacion()); //id 35}
}
		
		
		public ArrayList<String> getRutasMunicipios() {
			
			return rutas;
		}
}
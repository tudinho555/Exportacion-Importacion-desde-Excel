package Exportar;

import java.util.ArrayList;


/*
 * CLASE PARA A�ADIR A UNA LISTA LAS RUTAS QUE NOS INTERESEN
 * 
 * LAS RUTAS DEBER�N SER DEFINIDAS EN UN FICHERO SYSTEM.PROPERTIES
 * 
 * SE LEER�N TODOS LOS EXCELS SEG�N LAS RUTAS CARGADAS EN ESTA CLASE
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
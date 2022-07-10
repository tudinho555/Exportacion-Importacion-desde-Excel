package ObtenerExcel;

import java.util.HashMap;

/*
 * CLASE DONDE SE MAPEA LA RUTA DEL EXCEL CON UN ID 
 * 
 */

public class Obtenerxlsx {

	HashMap <String, Integer> rutas = new HashMap<String, Integer>();
	
	public Obtenerxlsx() {
		
		rutas.put("INTRODUCE_RUTA.xlsx", 1);
		
	}
	

	public int getId(String ruta) {
		
		return rutas.get(ruta).intValue();
	}
}

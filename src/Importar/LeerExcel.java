package Importar;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;



/*
 * CLASE QUE NOS PERMITE RECOGER DATOS DE UN EXCEL SEG�N LOS PAR�METROS DADOS
 * 
 * CADA HOJA DEL .xlsx QUE SE QUIERAN LEER HEREDAR�N EL CONJUNTO DE M�TODOS CONTENIDOS EN ESTA CLASE
 * 
 * LOS DATOS SE RECOGEN EN UN ArrayList<ArrayList<String>>, EN DONDE:
 * 
 * 				- CADA SUBLISTA CONTIENE LOS DATOS DE UNA FILA
 * 				- CADA NODO DE LA SUBLISTA CONTIENE LOS DATOS DE UNA CELDA
 * 				- LA LISTA PADRE CONTIENE TODAS LAS FILAS EXTRA�DAS
 */


public class LeerExcel {

	protected Workbook workbook;
	protected ArrayList <ArrayList<String>> listaPadre;
    protected InputStream is;
    protected String pestania;
    protected String pestaniaIngles;
    
    
    /*
     * Inicializamos el excel pasado como par�metro y la lista padre
     */
    
	public LeerExcel(String ruta, Workbook workbook) throws EncryptedDocumentException, InvalidFormatException, IOException  {

		this.workbook=workbook;
		listaPadre=new ArrayList<>();

	} 
	

	
 protected ArrayList<ArrayList<String>> RecorreExcel(int columnaInicio, int filaInicio, int ultimaColumna, String condicionSalida, String pestania) throws FileNotFoundException {
		
	 
		boolean sigue=false;
		boolean fin=false;	
		
		for (org.apache.poi.ss.usermodel.Sheet sheet : workbook){													//recorrer el excel entero
			
			if (!sheet.getSheetName().equals(pestania) && !sheet.getSheetName().equals(pestaniaIngles)) continue;   //comprobar que la hoja coincide con la que queremos leer
			
			for (Row r : sheet) {																					//si coincide, recorrer filas de la hoja
												
				if (r.getRowNum()<filaInicio) continue;						//saltamos la fila si no coincide con el lugar donde queremos empezar la lectura
				
				else listaPadre.add(new ArrayList<>());						//Creamos una sublista en caso de que coincida
								
			    for (Cell c : r) {																					//recorrer celdas
			   
			    	if (c.getColumnIndex()>=columnaInicio && r.getRowNum()>=filaInicio && c.getColumnIndex()<=ultimaColumna && !fin ){    //comprobamos que la celda se sit�a entre los par�metros que queremos leer 
                                 
	    			     	sigue = true;
	    			
	    			     	if (c.getStringCellValue().trim().equals(condicionSalida)) {
	    				                     
	    			     		sigue = false;
	    			     		fin=true;
	    			     		listaPadre.remove(listaPadre.size()-1);												//borramos la �ltima sublista dado que ah� el programa solo ha encontrado la condici�n para dejar de leer
	    			     					
	    			     				}
	    			     						
    				        if (sigue)
	    		    		
    				        	listaPadre.get(listaPadre.size()-1).add(c.getStringCellValue().trim());				//si 'sigue' es cierto, a�adimos el valor de la celda a un nodo de la lista
    				           				  
			    	 	}
			    	
	    			if (fin) break;
			    }//FIN RECORRIDO DE FILA
			   	  
			    if (fin) break;
			}//FIN RECORRIDO COLUMNA
			
			if (fin) break;
		}//FIN RECORRIDO LIBRO
		
		return listaPadre;

	}
 
 
 /*
  * 
  *    EL SIGUIENTE M�TODO SIRVE PARA BORRAR FILAS EN CASO DE QUE UN ELEMENTO (columna) PASADO POR PAR�METRO EST� VAC�O
  * 
  * 
  */
 
 public ArrayList<ArrayList<String>> borraFilas(ArrayList<ArrayList<String>> listaPadre, int colobligatoria){
	 
	 for (int i=0; i<listaPadre.size(); i++) {
		 
		 if (listaPadre.get(i).get(colobligatoria).trim().isEmpty()) {
			 listaPadre.remove(i);
			 i--;
		 }
		 
	 }
	 
	 return listaPadre;
	 
 }
 
 
 /*
  * devuelve una lista con los nombres de las pesta�as del excel
  */
 
 public ArrayList<String> obtenerpestanyas(String contiene) {

	
	 ArrayList<String> nombres = new ArrayList<String>();
	 
 		for (org.apache.poi.ss.usermodel.Sheet sheet : workbook){
 			
 			if (sheet.getSheetName().contains(contiene))
 				nombres.add(sheet.getSheetName());
 		}
 		
 		return nombres;
 }
 	
 public void cerrarStreams() throws IOException {
 	
 		workbook.close();
 		
 	}

 	
}
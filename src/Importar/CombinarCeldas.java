package Importar;


import java.util.ArrayList;



/*
 * LAS CELDAS COMBINADAS DEL EXCEL GENERAN NODOS VACÍOS DEL ARRAYLIST
 * 
 * ESTA CLASE SOLUCIONA ESE HECHO, BORRANDO ESPACIOS EN BLANCO DE UN ARRAYLIST SEGÚN UN INTERVALO DE COORDENADAS DADO 
 * 
 */


public class CombinarCeldas {
	

	//-------------------LLenamos los nodos vacios con la palabra "REMOVE"------------------------------------------------
    
	public static ArrayList<ArrayList<String>> combina (ArrayList <ArrayList<String>> lista,int pos, int [][]desdeHasta) {
        
        
        for (int i=0; i<lista.size(); i++) {
            
            ArrayList <String> a = lista.get(i);
            
            for (int j=0; j<a.size();j ++) {
                
                for (int [] parametros : desdeHasta) {
                    
                	
                if(j+pos>=parametros[0] && j+pos <= parametros[1] && (a.get(j).isEmpty())) {
                    
                    
                		a.set(j, "REMOVE");
                   
                }
               }
                
            }
            
        }
             
        lista=noMeBorresTodosMelón(lista, pos, desdeHasta);         //llamamos a este método para solucionar el hecho de que en caso de que todos los elementos de
        															//una celda combinada esté vacío, los borra todos
        
        lista=borraVacios(lista);
        
        return lista;
        
    }
    
//-------------------MÉTODO QUE DA SOPORTE PARA EL CASO EN EL QUE TODOS LOS ELEMENTOS DE UNA CELDA COMBINADA ESTÉN VACÍOS
//-------------------SI SE DA EL CASO, BORRA TODOS LOS ELEMENTOS MENOS UNO (EL CORRESPONDIENTE AL ATRIBUTO QUE LE INTERESA)
    //---
  public static ArrayList<ArrayList<String>> noMeBorresTodosMelón (ArrayList <ArrayList<String>> lista,int pos, int [][]desdeHasta) {
        
	  
       int cuenta=0;
       
        for (int i=0; i<lista.size(); i++) {
            
            ArrayList <String> a = lista.get(i);
            
            for (int [] parametros : desdeHasta) {
                
                for (int j=0; j<a.size();j ++) {
                                    	
                if(j+pos>=parametros[0] && j+pos <= parametros[1] && (a.get(j).equals("REMOVE"))) {
                                       
                		cuenta++;
                		
                		if (cuenta == parametros[1]-parametros[0]+1) a.set(j, "ESTENOSEBORRA");                	                	
                    
                		}
                }
                cuenta=0;
            }
            
        }
        
      return lista;
  }
    
  //-----------------------MÉTODO QUE BORRA ELEMENTOS QUE ALMACENAN LA PALABRA REMOVE----------
  
  public static ArrayList<ArrayList<String>> borraVacios(ArrayList <ArrayList<String>> lista) {
	  
	  for (int i=0; i<lista.size(); i++) {
          
          ArrayList <String> a = lista.get(i);
                
              for (int j=0; j<a.size();j ++) {
            	  
            	  	if (a.get(j).equals("REMOVE")) {
            	  		a.remove(j);
            	  		j--;
            	  	}
            	  	
            	  	else if (a.get(j).equals("ESTENOSEBORRA")) a.set(j, "");
              }
              
          }
	  return lista;      
      
     	  
  }
    
}
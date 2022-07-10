package HojasExportar;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;

import es.caib.paces.back.files.exportar.Escribir;
import es.caib.paces.back.security.UserInfo;


public class ExportarHoja1 {

	private EncuentraMaestros encuentramaestros;					//encontrar datos maestros si se precisase	
	private String pestania;
	private ArrayList<ArrayList<String>> listaPadre;
	private Escribir e;
	private int exceso;
	private ObtenDTOEstrategias obtendto;							//donde almacenaremos el dto del que extraeremos sus datos
	
	
	public EscribirEstrategias(Workbook workbook, String excelFilePath, UserInfo userInfo) throws EncryptedDocumentException, IOException {
		
		pestania= "NombreHojaDelExcel";										//Pestaña a la que apunta la clase
		listaPadre=new ArrayList<ArrayList<String>>();						//Lista donde guardamos los datos extraídos del dto, preparados para escribirlos en el excel
		e= new Escribir(workbook, pestania );
		exceso=0;															//variable que sirve para correr filas
		obtendto= new ObtenDTO(userInfo);                        			//obtenemos el dto extraído de la base de datos
		encuentramaestros= new EncuentraMaestrosEstgrategias();     		//instanciamos la clase para obtener los datos maestros
	}
	
	
	/*
	 * 
	 * Método para ejecutar los métodos que dan soporte a cada una de las tablas del excel de forma secuencial
	 * 
	 */
	
	public void iniciaExportacion() throws IOException {
	
		if (obtendto.getDtopral()!=null) {
			
			this.escribirTabla1(obtendto.getDtopral());   
		
			this.escribirTabla2(obtendto.getDtopral());     
    	
		//Añadir tantos métodos como tablas se quieran exportar
		}
	}

	
	private void escribirTabla1(DTO dto) throws IOException {
		
		//------------------------------INICIALIZAR ATRIBUTOS------------------------------
		
		int fila= 7;			//fila del excel donde se empezará a escribir				
		int col= 4;				//columna del excel donde se empezará a escribir
		
		//------------------------------EXTRAER DATOS DTO----------------------------------
		
		listaPadre=ExtraerDatosdto.extraerTabla1(dto, listaPadre);		//extraer los datos del dto y almacenarlos en la listapadre

		//------------------------------ESCRIBIR EN EL FICHERO-----------------------------
		e.escribir(fila, col, listaPadre,null);							//enviar los datos para su escritura
		
		//------------------------------BORRAR LISTA---------------------------------------
		listaPadre.clear();
	
	}

	//Añadir tantos métodos con el mismo formato que el anterior como tablas se quieran exportar 
}

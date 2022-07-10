import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.spi.CDI;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import com.monitorjbl.xlsx.StreamingReader;


public class ImportarArchivos {

	private ObtenerExcelMunicipios idmunicipio;
	private InputStream is;
	private Workbook workbook;
	
	private ServiceFacade1 pacesEstraService = CDI.current().select(ServiceFacade1.class).get();
	
	
	
	public ImportarArchivos(ArrayList<String> rutas) throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		
		this.rutas= rutas;
		id= new Obtenerxlsx();
	}
	
	
	public void iniciaImportacion() throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		
		ArrayList<String> rutasError= new ArrayList<String>();
		int id;
		
	
		for (String ruta : rutas) {					//recorremos las rutas de importación
	
			try {
				
				id = idmunicipio.getId(ruta);			
				asociar_worbook(ruta);	
			
				/*
				 * 
				 * AÑADIR AQUÍ LAS CLASES PARA LEER LAS HOJAS DEL EXCEL
				 * 
				 */
			
			}catch(Exception e) {
			
				
				rutasError.add(ruta);
				cerrar_workbook();
			}

		}
		gestionErroresImportacion(rutasError);
	
	
	
}
	
	/*
	 * El siguiente método nos mostrará las rutas de los excel en los que
	 * 
	 * haya saltado la excepción
	 */
	
	public void gestionErroresImportacion(ArrayList<String> rutasError) {

		if (rutasError.size() > 0) {

			String cadenaerrores = "Error en la importación en las siguientes rutas:\n";
			
			for (String e : rutasError) {

				cadenaerrores = cadenaerrores.concat(e + "\n");
			}

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", cadenaerrores));
		} else
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Importación realizada sin excepciones"));

	}
}
package HojasImportar;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import es.caib.paces.back.files.importar.Excepciones;
import es.caib.paces.back.files.importar.importarEstrategias.DatosMaestrosEstrategias;
import es.caib.paces.back.files.importar.importarEstrategias.MontarDTOEstrategias;
import es.caib.paces.service.model.PacesEstrategiasDTO;

public class ImportarHoja1 {

	private DTO dto;
	private int exceso; // Variable para saber si el usuario ha introducido columnas de más
	private DatosMaestros maestros;

	public ImportarHoja1(String ruta, Workbook workbook, int id) throws EncryptedDocumentException, InvalidFormatException, IOException {

		super(ruta, workbook);

		dto = new DTO();
		exceso = 0;
		pestania = "Nombre de pestanya";
		maestros = new DatosMaestros;
		this.id = id;
	}
	
	public void iniciarImportacion() throws IOException {

		this.importTabla1();
		
	}


	private void importVision() throws FileNotFoundException { // TODO HECHO

		// -------------------------------------Inicializar atributos----------------------------------------
		int columnaInicio = 4;
		int filaInicio = 7;
		int ultimaColumna = 4;
		String condicionSalida = "F";

		// -------------------------------------Recoger datos de excel----------------------------------------
		try {
			listaPadre = RecorreExcel(columnaInicio, filaInicio, ultimaColumna, condicionSalida, pestania,);

		//--------------------------------------Montar DTO a partir de los datos extraídos----------------

			dto = MontarDTO.montarTabla1(dto, listaPadre);		

		// --------------------------------------PRUEBAS POR CONSOLA (descomentar si se quiere ver el contenido del dto por consola)--------
			//MostrarDatosEstrategiasTEST.mostrarVision(dto);

		// --------------------------------------Borrar lista---------------------------------------------------

			listaPadre.clear();

		} catch (Exception e) {
			new Excepciones().ErrorImportacion(pestania, "NombreDeLaHoja");
		}

	}

}

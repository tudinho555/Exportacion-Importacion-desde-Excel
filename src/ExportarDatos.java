import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import es.caib.paces.back.Configuracio;
import es.caib.paces.back.files.exportar.exportarAcciones.EscribirAcciones;
import es.caib.paces.back.files.exportar.exportarEstrategias.EscribirEstrategias;
import es.caib.paces.back.files.exportar.exportarPlanesAccion.EscribirPlanesAccion;
import es.caib.paces.back.files.exportar.exportarRiesgosVul.EscribirRiesgosvul;
import es.caib.paces.back.files.exportar.exportarScoreboard.EscribirScoreboard;
import es.caib.paces.back.files.exportarInventarios.EscribirConsumosFinales;
import es.caib.paces.back.files.exportarInventarios.EscribirDatosGenerales;
import es.caib.paces.back.files.exportarInventarios.EscribirFactoresEmision;
import es.caib.paces.back.security.UserInfo;

public class ExportarDatos {

	
	/* VARIABLES QUE USAREMOS PARA PASARLE COMO PARÁMETRO A LAS DIFERENTES CLASES */

	private FileInputStream inputStreamSalida;
	private static String excelFilePath;
	private static String excelFilePathSalida;
	private Workbook workbookSalida;
	private static File archivoDestino;

	public final static int BUF_SIZE = 1024; // SE PUEDE AUMENTAR

	private UserInfo userInfo;
	
	public ExportarDatos(UserInfo userInfo, Configuracio config) throws Exception {

		excelFilePath = config.getPathExportacion().concat("/exportData.xlsx");
		excelFilePathSalida = config.getPathExportacion().concat("/").concat(generaCadenaAleatoria()).concat(".xlsx");
		this.userInfo = userInfo;

		copiarExcel(); 																		// clonar el excel que tenemos en la carpeta del proyecto

		archivoDestino = new File(excelFilePathSalida);

		inputStreamSalida = new FileInputStream((archivoDestino));

		workbookSalida = WorkbookFactory.create(inputStreamSalida);

	}

	/*
	 * Esta clase escribirá en la hoja especificada, instanciando su clase
	 */

	public void empiezaexportacion() throws EncryptedDocumentException, IOException {

		escribirhoja = new EscribirDatosGenerales(workbookSalida, excelFilePathSalida, userInfo);
		escribirhoja.escribirDatos();

	}
	
	public void escribirFichero() throws IOException {

		FileOutputStream outputStream = new FileOutputStream(archivoDestino);
		workbookSalida.write(outputStream);
		workbookSalida.setForceFormulaRecalculation(true);
		outputStream.close();
	}

	public void cerrarStreams() throws IOException {

		workbookSalida.close();

		if (inputStreamSalida != null)
			inputStreamSalida.close();

	}

	/*
	 * Genera una copia del excel guardado en las carpetas del servidor
	 */

	public static void copiarExcel() throws Exception {

		FileInputStream fis = new FileInputStream(excelFilePath);
		FileOutputStream fos = new FileOutputStream(excelFilePathSalida);

		try {
			byte[] buf = new byte[BUF_SIZE];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (fis != null)
				fis.close();
			if (fos != null)
				fos.close();
		}

	}

	/*
	 * Genera un nombre aleatorio que se usará para el archivo excel que se ha
	 * clonado
	 */

	public String generaCadenaAleatoria() {

		int minimo = 65;
		int maximo = 91;
		int longitudNombre = 30;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(longitudNombre);
		for (int i = 0; i < longitudNombre; i++) {
			int randomlimite = minimo + (int) (random.nextFloat() * (maximo - minimo + 1));
			buffer.append((char) randomlimite);
		}
		String cadenaGenerada = buffer.toString();

		return cadenaGenerada;
	}

	
	/*
	 * Método que permite la descarga del fichero en formato .xlsx
	 */

	public void transferirDatos() throws IOException {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		response.reset();

		response.setHeader("Content-Disposition", "attachment; filename=ExportData.xlsx");

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

		response.setContentLength((int) archivoDestino.length());

		OutputStream responseOutputStream;

		responseOutputStream = response.getOutputStream();

		InputStream fileInputStream;

		fileInputStream = new FileInputStream(archivoDestino);

		byte[] bytesBuffer = new byte[2048];

		int bytesRead;

		while ((bytesRead = fileInputStream.read(bytesBuffer)) > 0) {
			responseOutputStream.write(bytesBuffer, 0, bytesRead);
		}

		responseOutputStream.flush();

		fileInputStream.close();

		responseOutputStream.close();

		facesContext.responseComplete();

	}

	/*
	 * método para borrar del servidor el archivo que se ha generado para la
	 * descarga
	 */

	public void borrarArchivoGenerado() {

		archivoDestino.delete();
	}
}

package Exportar;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;



/*
 * 
 * 	CLASE QUE DA SOPORTE PARA LA ESCRITURA EN UN .xlsx DETERMINADO EN UNA HOJA DETERMINADA. 
 * 
 *  PERMITE EL FORMATEO DE CELDAS, CLONACIÓN DE FILAS EN CASO DE QUERER INSERTAR MÁS DATOS EN LA PLANTILLA,
 *  
 *  	CLONACIÓN DE HOJAS E INSERCIÓN EN POSICIONES DETERMINADAS Y CIERRE DE STREAMS.
 */


public class EscribirExcel {

	private Workbook workbook;
	private FileInputStream inputStream;
	private Sheet sheet;

	
	/*
	 * recibimos en el constructor el excel (workbook) y la hoja
	 */
	
	public EscribirExcel(Workbook workbook, String pestania) throws EncryptedDocumentException, IOException {
		
		this.workbook = workbook; 							// workbook representando al excel
		
		sheet = workbook.getSheet(pestania); 				// obtener la hoja según el nombre pasado como parámetro
		
		workbook.setForceFormulaRecalculation(true); 		// forzar a recalcular las fórmulas cuando se abra el excel

	}

	
	
	/*
	 * Método que recibe unos datos y los escribe a partir de una lista.
	 * 
	 * Cada elemento de la lista se escribirá en una celda.
	 * 
	 * Cada lista de listas se escribirá en una fila.
	 */
	
	public void escribir(int fila, int columnaInicio, ArrayList<ArrayList<String>> datos, int[][] celdasCombinadas)
			throws IOException {

		Cell cell;
		Row row;
		int col = columnaInicio;

		for (ArrayList<String> lista : datos) {

			row = sheet.getRow(fila);

			for (String nodo : lista) {

				cell = row.getCell(col);
				cell.setCellValue(nodo);
				col++;

				if (celdasCombinadas != null)
					for (int[] el : celdasCombinadas) {

						while (col > el[0] && col <= el[1]) {

							col++;
						}
					}
			}

			fila++;
			col = columnaInicio;

		}

	}

	
	/*
	 * Sobrecarga del método anterior para escribir en formato numérico
	 */

	public void escribir(int fila, int columnaInicio, List<ArrayList<Double>> datos, int[][] celdasCombinadas)throws IOException {

		Cell cell;
		Row row;
		int col = columnaInicio;

		for (ArrayList<Double> lista : datos) {

			row = sheet.getRow(fila);

			for (Double nodo : lista) {

				cell = row.getCell(col);
				cell.setCellValue(nodo);
				col++;

				if (celdasCombinadas != null)
					for (int[] el : celdasCombinadas) {

						while (col > el[0] && col <= el[1]) {

							col++;
						}
					}

			}

			fila++;
			col = columnaInicio;

		}

	}

	
	/*
	 * método al que hay que llamar para escribir los datos que le hemos indicado
	 */
	
	public void actualizarFichero(String excelFilePath) throws IOException {

		FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		
		workbook.write(outputStream);

		outputStream.close();
		

	}

	public void cerrarStreams() throws IOException {

		if (workbook != null)
			workbook.close();

		if (inputStream != null)
			inputStream.close();

	}

	
	/*
	 * Método para insertar filas con el formato de la fila especificada en el atributo 'fila'
	 */
	
	public void insertarFila(int fila, int maxElementos, String pestania) throws IOException {
		
		int lastRow = sheet.getLastRowNum();						
		Row r = sheet.getRow(fila + maxElementos - 1);	
		sheet.shiftRows(fila + maxElementos, lastRow, 1);		
		sheet.createRow(fila + maxElementos);
		Row nueva = sheet.getRow(fila + maxElementos);
		Cell nuevacelda;
		int contador = 0;
		
		for (Cell c : r) {
		
			c.setCellType(CellType.STRING); 
			nueva.createCell(contador).setCellValue(c.getStringCellValue());
			nuevacelda = nueva.getCell(contador);
			nuevacelda.setCellStyle(c.getCellStyle());
			contador++;
		}
		
	}

	
	/*
	 * Método que sirve para que las celdas aparezcan combinadas y sin espacios en
	 * blanco
	 */
		
	public void FormateaCeldas(int[][] celdasCombinadas, int filaInicio, int filaFin) throws IOException {

		CellRangeAddress cellRangeAddress;

		for (int i = filaInicio; i <= filaFin; i++) {

			for (int[] el : celdasCombinadas) {

				cellRangeAddress = new CellRangeAddress(i, i, el[0], el[1]); // el merge se realizará entre la fila i y
																				// la fila i (una sola fila) entre los
																				// elementos marcados por el[0] y el[1]
				sheet.addMergedRegion(cellRangeAddress);
			}
		}
	}
	
	
	/*  
	 * clonar la pestaña indicada y colocar hoja clonada en el sitio indicado 
	 */

	public void duplicaPestania(int posInserta, int posclona) { 

		workbook.cloneSheet(posclona); // clonar la pestaña
		this.sheet = workbook.getSheetAt(workbook.getNumberOfSheets() - 1); // escribir sobre la pestaña clonada
		workbook.setSheetOrder(workbook.getSheetName(workbook.getNumberOfSheets() - 1), posInserta);

	}
	
	/*
	 * Métodos para actualizar la pestaña sobre la que se está escribiendo (según si recibe el nombre o la posición de la pestaña)
	 */
	
	public void setpestania(String pestaniaOriginal) {
		
		this.sheet=workbook.getSheet(pestaniaOriginal);
	}
	
	public void setpestania(int posescribe) {
		
		this.sheet=workbook.getSheetAt(posescribe);
	}
	


}
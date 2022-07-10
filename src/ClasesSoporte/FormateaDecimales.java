package ClasesSoporte;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FormateaDecimales {

public static double formatea(Double num, short numDecimales) {
		
		BigDecimal bd = new BigDecimal(num);
		bd = bd.setScale(numDecimales, RoundingMode.HALF_UP);
		
		return bd.doubleValue();
	}
}

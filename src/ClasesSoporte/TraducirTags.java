package ClasesSoporte;


import java.util.HashMap;
import java.util.ResourceBundle;


/*
 * Almacenamos los tags de los datos maestros de la hoja de estrategias
 * 
 */

public class TraducirTags {

	ResourceBundle labelsBundle = null;
	HashMap <String,String> personalAsignado;
	HashMap <String,String> partesInteresadas;
	HashMap <String,String> presupuestoGlobal;

	
	public TraducirTagsEstrategias() {
		
		labelsBundle = ResourceBundle.getBundle("back.LabelsBack");
		
		personalAsignado= new HashMap(); 
		
		personalAsignado.put(labelsBundle.getString("pac_t_personal_asig_autLoc").toLowerCase(), "pac_t_personal_asig_autLoc" );
		personalAsignado.put(labelsBundle.getString("pac_t_personal_asig_OtrosN").toLowerCase(), "pac_t_personal_asig_OtrosN" );
		personalAsignado.put(labelsBundle.getString("pac_t_personal_asig_Coord").toLowerCase(), "pac_t_personal_asig_Coord" );
		personalAsignado.put(labelsBundle.getString("pac_t_personal_asig_Prom").toLowerCase(), "pac_t_personal_asig_Prom" );
		personalAsignado.put(labelsBundle.getString("pac_t_personal_asig_Prom").toLowerCase(), "pac_t_personal_asig_Prom" );
		personalAsignado.put(labelsBundle.getString("pac_t_personal_asig_Cons").toLowerCase(), "pac_t_personal_asig_Cons" );
		personalAsignado.put(labelsBundle.getString("pac_t_personal_asig_Otros").toLowerCase(), "pac_t_personal_asig_Otros" );
		
		partesInteresadas=new HashMap();
		
		partesInteresadas.put(labelsBundle.getString("pac_t_part_interes_es_Personal").toLowerCase(), "pac_t_part_interes_es_Personal" );
		partesInteresadas.put(labelsBundle.getString("pac_t_part_interes_es_PartesIExternas").toLowerCase(), "pac_t_part_interes_es_PartesIExternas");
		partesInteresadas.put(labelsBundle.getString("pac_t_part_interes_es_PartesIOtrosNiveles").toLowerCase(), "pac_t_part_interes_es_PartesIOtrosNiveles");
	
		presupuestoGlobal= new HashMap();
		
		presupuestoGlobal.put(labelsBundle.getString("pac_t_fuentes_finan_Recursos").toLowerCase(), "pac_t_fuentes_finan_Recursos");
		presupuestoGlobal.put(labelsBundle.getString("pac_t_fuentes_finan_Publico").toLowerCase(), "pac_t_fuentes_finan_Publico");
		presupuestoGlobal.put(labelsBundle.getString("pac_t_fuentes_finan_Privado").toLowerCase(), "pac_t_fuentes_finan_Privado");
		presupuestoGlobal.put(labelsBundle.getString("pac_t_fuentes_finan_ProgramasNAC").toLowerCase(), "pac_t_fuentes_finan_ProgramasNAC");
		presupuestoGlobal.put(labelsBundle.getString("pac_t_fuentes_finan_ProgramasUE").toLowerCase(), "pac_t_fuentes_finan_ProgramasUE");
		presupuestoGlobal.put(labelsBundle.getString("pac_t_fuentes_finan_NoAsignado").toLowerCase(), "pac_t_fuentes_finan_NoAsignado");
	
	}

	public String getTagMaestroPersonalAsignado(String nombreMaestro) {
			
		String etiqueta=null;
		
		if (personalAsignado.get(nombreMaestro.toLowerCase())!=null) {
			
			etiqueta=personalAsignado.get(nombreMaestro.toLowerCase());
		
			return etiqueta;
			}
		
		return etiqueta;
	}
	
	public String getTagMaestroPartesInteresadas(String nombreMaestro){
		
		String etiqueta=null;
		
		if (partesInteresadas.get(nombreMaestro.toLowerCase())!=null) {
			
			etiqueta=partesInteresadas.get(nombreMaestro.toLowerCase());
	
			return etiqueta;
			}
		
		return etiqueta;
	}
	
	public String getTagMaestroPresGlobal(String nombreMaestro){
		
		String etiqueta=null;
	
		if (presupuestoGlobal.get(nombreMaestro.toLowerCase())!=null) {
			
			etiqueta=presupuestoGlobal.get(nombreMaestro.toLowerCase());
	
	
			return etiqueta;
			}
		
		return etiqueta;
	}
}

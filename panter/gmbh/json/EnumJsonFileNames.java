/**
 * panter.gmbh.json
 * @author martin
 * @date 15.06.2020
 * 
 */
package panter.gmbh.json;

import static panter.gmbh.basics4project.ENUM_REGISTRY.SUBDIR_TO_JSON;

import java.io.File;

import panter.gmbh.indep.bibALL;

/**
 * @author martin
 * @date 15.06.2020
 *
 */
public enum EnumJsonFileNames {
	
	 B2MaskTransportArtSetting("vector_masksetting_with_transportart.json","Maskensettings Vektor-basierte Fuhre, die definiert, was passiert, wenn eine bestimmte Transportart ausgewählt wird")
	,B2MaskFieldDescription("vector_mask_field_descriptions.json","Beschreibungstexte zu den Feldern auf der Vektor-basierten Fuhre")
	;
	
	private String fileName = null;
	private String description = null;

	private EnumJsonFileNames(String fileName, String description) {
		this.fileName = 	fileName;
		this.description = 	description;
	}

	public String getFileName() {
		return fileName;
	}

	public String getDescription() {
		return description;
	}
	
	
	
	public File getFile() {
		
		File fileToJson = null;

		try {
			String basePath = File.separator+bibALL.get_WEBROOTPATH()+File.separator+SUBDIR_TO_JSON.getStringValue()+File.separator;
			
			File jsonFileMandant = 	new File(basePath+bibALL.get_ID_MANDANT()+File.separator+this.getFileName());
			File jsonFileAll = 		new File(basePath+"ALLE"+File.separator+this.getFileName());
			
			if (jsonFileMandant.exists()) {
				fileToJson = jsonFileMandant;
			} else if (jsonFileAll.exists()) {
				fileToJson = jsonFileAll;	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileToJson;
	}
	
	
}

package panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER;

import panter.gmbh.indep.archive.Archiver_Normalized_Tablename;

public class SCAN__ENUM {

	
	//eine sammlung von enums, die mit vor- und nachangestelltem # in den Scanaufrufen ersetzt werden 
	public static enum REPLAC_CONST {
		FILENAME,                   //rueckgabename des scanergebnisses
		WEBROOTPATH,                //pfad im dateisystem, wo die application anfaengt
		OUTPUTPATH,                 //pfad zum output-ordner
		COMPLETEPATHOUTPUT,         //kompletter pfad zum output-ordner auf dem tomcat-server
		DPI, 						//angabe zur aufloesung
	};

	
	//eine sammlung von programmkennern, die dem Scannerprofil mitgegeben werden kann, z.B. um Dokumente, die an rechnungen angehaengt werden muessen
	//zu identifizieren
	public static enum PROGRAMM_KENNER_4_SCANFILES {
		RECHNUNG_ANHANG,
		GUTSCHRIFT_ANHANG,
		RECH_GUT_ANHANG,
	}

	
	
	/**
	 * gibt eine uebersetzung fuer die download-namen von scanfiles, wenn eine tabelle hier aufgefuehrt ist, wird sie renames
	 * @author martin
	 *
	 */
	public enum TABLENAME_REPLACERS {
		  VPOS_TPA_FUHRE("TPF")
		 ,VKOPF_RG("VKRG")
		;
		
		
		private String NewName = null;
		TABLENAME_REPLACERS(String newName) {
			this.NewName=newName;
		}
		public String get_NewName() {
			return NewName;
		}
		
	}
	
	
	
	
	/**
	 * 
	 * @param origTableName
	 * @return translatedname or tablebasename
	 */
	public static String translate(String origTableName) {
		String ctableBase = new Archiver_Normalized_Tablename(origTableName).get_TableBaseName();
		
		for (TABLENAME_REPLACERS replacer: TABLENAME_REPLACERS.values()) {
			if (replacer.name().equals(ctableBase.toUpperCase().trim())) {
				return replacer.get_NewName();
			}
		}

		return ctableBase;
		
	}
	
	
}

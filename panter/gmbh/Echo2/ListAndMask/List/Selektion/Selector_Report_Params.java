/**
 * panter.gmbh.Echo2.ListAndMask.List.Selektion
 * @author manfred
 * @date 31.05.2016
 * 
 */
package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL_TYP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;




/**
 * @author manfred
 * @date 31.05.2016
 *
 */
public class Selector_Report_Params {


	
	public enum ENUM_Selector_Report_Params{	
		
		// LAGERBEWEGUNGEN
		LAGERLISTE_KONTO_ID_ADRESSE_LAGER(MODUL.NAME_MODUL_LAGERLISTE_KONTO,"Lageradresse-ID"),
		LAGERLISTE_KONTO_HAUPTSORTE(MODUL.NAME_MODUL_LAGERLISTE_KONTO,"Hauptsorte"),
		LAGERLISTE_KONTO_ID_ARTIKEL(MODUL.NAME_MODUL_LAGERLISTE_KONTO,"Artikel-ID"),
		LAGERLISTE_KONTO_DATUM_VON(MODUL.NAME_MODUL_LAGERLISTE_KONTO,"Datum von"),
		LAGERLISTE_KONTO_DATUM_BIS(MODUL.NAME_MODUL_LAGERLISTE_KONTO,"Datum bis"),
		LAGERLISTE_KONTO_STORNO(MODUL.NAME_MODUL_LAGERLISTE_KONTO,"Ist Storniert"),
		LAGERLISTE_KONTO_BUCHUNGSTYP(MODUL.NAME_MODUL_LAGERLISTE_KONTO,"Buchungstyp"),
		LAGERLISTE_KONTO_SHOW_FREMDWARENLAGER(MODUL.NAME_MODUL_LAGERLISTE_KONTO,"Fremwarenlager anzeigen"),
		LAGERLISTE_KONTO_SHOW_EIGENWARENLAGER(MODUL.NAME_MODUL_LAGERLISTE_KONTO,"Eigenwarenlager anzeigen"),
		LAGERLISTE_KONTO_ID_EINHEIT(MODUL.NAME_MODUL_LAGERLISTE_KONTO,"Einheit-ID"),

		// LAGERSALDO
		LAGERLISTE_ID_ADRESSE_LAGER(MODUL.NAME_MODUL_LAGERLISTE,"Lageradresse-ID"),
		LAGERLISTE_ID_ARTIKEL(MODUL.NAME_MODUL_LAGERLISTE,"Artikel-ID"),
		LAGERLISTE_HAUPTSORTE(MODUL.NAME_MODUL_LAGERLISTE,"HAUPTSORTE"),
		
		;
		
		private ENUM_Selector_Report_Params(MODUL modul, String paramDesc) {
			this.m_Modul = modul;
			this.m_Description = paramDesc;
		}
		
		private MODUL 	m_Modul = null;
		private String 	m_Description = null;
		
		
		/**
		 * gibt die Description des Enums zurück, falls leer den Namen des Enums
		 * @author manfred
		 * @date 31.05.2016
		 *
		 * @return
		 */
		public String get_Description() {
			if (S.isEmpty(this.m_Description)) {
				return this.name();
			}
			return m_Description;
		}
		
		/**
		 * gibt den Namen des Enums zurück
		 * @author manfred
		 * @date 31.05.2016
		 *
		 * @return
		 */
		public String get_Name(){
			return this.name();
		}
		
		/**
		 * gibt den Namen des Enums zurück, begrenzt mit #
		 * z.B. #KONTO_ID_ADRESSE#
		 * @author manfred
		 * @date 01.06.2016
		 *
		 * @return
		 */
		public String get_Name_For_Param(){
			return "#" + this.name() + "#";
		}
		
		public MODUL getModul(){
			return m_Modul;
		}
		
	
	}
	
	
	
	
	/**
	 * gibt alle definierten Selektions-Report-Paramter eines Moduls zurück
	 * @author manfred
	 * @date 31.05.2016
	 *
	 * @param modul
	 * @return
	 */
	public static Vector<ENUM_Selector_Report_Params> getParamForModul(MODUL modul){
		Vector<ENUM_Selector_Report_Params> vec = new Vector<>();
		for (ENUM_Selector_Report_Params param: ENUM_Selector_Report_Params.values()){
			if (param.m_Modul.equals(modul)){
				vec.addElement(param);
			}
		}
		return vec;
	}
	
	
}

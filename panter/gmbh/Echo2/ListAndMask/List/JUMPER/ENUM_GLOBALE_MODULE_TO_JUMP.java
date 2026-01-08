/**
 * 
 */
package panter.gmbh.Echo2.ListAndMask.List.JUMPER;

import java.util.HashMap;

import panter.gmbh.basics4project.CallStringAnalyser;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;

/**
 * @author martin
 * Definiert eine liste von modulkennern, die angesprungen werden koennen, aber nicht in der standard-menueauswahl sind
 * (damit koennen module im menue zusaetze auch angesprungen werden, diese sollten aber eine validierung besitzen, d.h. ein eintrag in ENUM_VALIDATION(E2_MODULNAME_ENUM.MODUL)
 */
public enum ENUM_GLOBALE_MODULE_TO_JUMP {

	AH7_STEUERTABELLE(MODUL.AH7_STEUERDATEI_LISTE, "AH7-Druck-Steuertabelle")
	,AH7_PROFIL(MODUL.AH7_PROFIL_LIST, "AH7-Profile")
	
	
	;
	
	private E2_MODULNAME_ENUM.MODUL 	modul = null;
	private String						tabText4User = null;
	
	/**
	 * @param p_modul
	 * @param tabText4User
	 */
	private ENUM_GLOBALE_MODULE_TO_JUMP(MODUL p_modul, String p_tabText4User) {
		this.modul = p_modul;
		this.tabText4User = p_tabText4User;
	}
	
	public E2_MODULNAME_ENUM.MODUL getModul() {
		return modul;
	}
	
	
	
	public String getTabText4User() {
		return tabText4User;
	}

	
	public static HashMap<String, String> get_hmGlobaleModule() {
		HashMap<String, String> hm = new HashMap<String, String>();
		
		for (ENUM_GLOBALE_MODULE_TO_JUMP e: ENUM_GLOBALE_MODULE_TO_JUMP.values()) {
			hm.put( CallStringAnalyser.ModulTypes.echo2_starter.toString()+":"+e.modul.get_callKey(), e.tabText4User);
		}
		
		return hm;
	}


	/**
	 * hashmap mit key = E2_MODULNAME_ENUM.MODUL
	 * @return
	 */
	public static HashMap<E2_MODULNAME_ENUM.MODUL, String> get_hmModule() {
		HashMap<E2_MODULNAME_ENUM.MODUL, String> hm = new HashMap<E2_MODULNAME_ENUM.MODUL, String>();
		
		for (ENUM_GLOBALE_MODULE_TO_JUMP e: ENUM_GLOBALE_MODULE_TO_JUMP.values()) {
			hm.put(e.modul, e.tabText4User);
		}
		
		return hm;
	}


	
	
}

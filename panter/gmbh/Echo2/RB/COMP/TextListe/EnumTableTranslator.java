/**
 * panter.gmbh.basics4project
 * @author martin
 * @date 26.03.2020
 * 
 */
package panter.gmbh.Echo2.RB.COMP.TextListe;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;

/**
 * @author martin
 * @date 26.03.2020
 * hilfe fuer die zuordung der textlisten und speichernamen 
 * 
 */
public enum EnumTableTranslator implements IF_enum_4_db_specified<EnumTableTranslator>{
	
	 VKOPF_KON_EK(_TAB.vkopf_kon,"Einkaufskontrakt")
	,VKOPF_KON_VK(_TAB.vkopf_kon,"Verkaufskontrakt")
	,VKOPF_STD_EK(_TAB.vkopf_std,"Abnahmeangebot")
	,VKOPF_STD_VK(_TAB.vkopf_std,"Verkaufsangebot")
	,VKOPF_RG_RECH(_TAB.vkopf_rg,"Rechnung")
	,VKOPF_RG_GUT(_TAB.vkopf_rg,"Gutschrift")
	,TEXT_LISTE_VORLAGE(_TAB.text_liste_vorlage,"Textlisten-Vorlage")
	;
	
	private String readableName = null;
	private _TAB   tableForTextListe = null;

	
	private EnumTableTranslator(_TAB table, String readableName) {
		this.tableForTextListe = table;
		this.readableName=readableName;
	}


	public _TAB getTableForTextListe() {
		return tableForTextListe;
	}
	
	public String getTableNameForTextListe() {
		return tableForTextListe.baseTableName();
	}
	

	
	public String getSaveKeyForVorlage() {
		if (this == EnumTableTranslator.TEXT_LISTE_VORLAGE) {
			return null;
		}
		return this.name();
	}

	
	
	/**
	 * 
	 * @author martin
	 * @date 26.03.2020
	 *
	 * @param tab
	 * @param addonTag
	 * @return  EnumTableTranslator-object or null
	 */
	public static EnumTableTranslator getTableTranslator(_TAB tab) {
		for (EnumTableTranslator trans: EnumTableTranslator.values()) {
			if (trans.tableForTextListe==tab) {
				return trans;
			}
		}
		return null;
	}


	@Override
	public String db_val() {
		return this.name();
	}


	@Override
	public String user_text() {
		return S.NN(readableName,this.name());
	}


	@Override
	public EnumTableTranslator[] get_Values() {
		return EnumTableTranslator.values();
	}





	
}

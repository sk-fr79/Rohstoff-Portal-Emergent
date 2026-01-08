/**
 * panter.gmbh.basics4project
 * @author martin
 * @date 30.03.2020
 * 
 */
package panter.gmbh.basics4project;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;

/**
 * @author martin
 * @date 30.03.2020
 *
 */
public enum EnumReadableTableName implements IF_enum_4_db_specified<EnumReadableTableName> {
	
	 KONTRAKT(_TAB.vkopf_kon,"Kontrakt")
	,ANGEBOT(_TAB.vkopf_std,"Angebot")
	,RECHNUNG(_TAB.vkopf_rg,"Rechnung/Gutschrift")
	,TEXT_LISTE_VORLAGE(_TAB.text_liste_vorlage,"Textlisten-Template")
	;

	private _TAB		tab = null;
	private String 		readableName = null;
	
	
	private EnumReadableTableName(_TAB p_tab, String p_readableName) {
		this.tab = p_tab;
		this.readableName = p_readableName;
	}

	@Override
	public String db_val() {
		return name();
	}

	@Override
	public String user_text() {
		return readableName;
	}

	@Override
	public EnumReadableTableName[] get_Values() {
		return EnumReadableTableName.values();
	}

	public _TAB getTab() {
		return tab;
	}

	

	/**
	 * 
	 * @author martin
	 * @date 30.03.2020
	 *
	 * @param baseTableName
	 * @return
	 */
	public EnumReadableTableName getEnumFromBaseTableName(String baseTableName) {
		EnumReadableTableName ret = null;
		
		for (EnumReadableTableName t: EnumReadableTableName.values()) {
			if (t.tab.baseTableName().equals(baseTableName)) {
				ret = t;
			}
		}
		return ret;
	}
	
	
}

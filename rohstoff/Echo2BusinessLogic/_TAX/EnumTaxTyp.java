/**
 * rohstoff.Echo2BusinessLogic._TAX
 * @author martin
 * @date 11.11.2020
 * 
 */
package rohstoff.Echo2BusinessLogic._TAX;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;

/**
 * @author martin
 * @date 11.11.2020
 *
 */
public enum EnumTaxTyp implements IF_enum_4_db_specified<EnumTaxTyp>{
	TAXTYP_UNDEFINIERT(TAX_CONST.TAXTYP_UNDEFINIERT,"<undefiniert>")
	,TAXTYP_RC_DEUTSCH(TAX_CONST.TAXTYP_RC_DEUTSCH,"Reverse Charge Deutschland")
	,TAXTYP_NULL_LAGERSEITE(TAX_CONST.TAXTYP_NULL_LAGERSEITE,"Lagerseite/Proformaeintrag")
	
	;
	
	private String userText = null;
	private String dbValue = null;
	
	private EnumTaxTyp(String dbValue,String userText) {
		this.userText = userText; 
		this.dbValue = dbValue;
	}
	
	

	@Override
	public String db_val() {
		return this.dbValue;
	}

	@Override
	public String user_text() {
		return S.NN(this.userText,this.name());
	}

	@Override
	public EnumTaxTyp[] get_Values() {
		return EnumTaxTyp.values();
	} 
	
	
	
	

}

/**
 * rohstoff.businesslogic.bewegung2.global
 * @author martin
 * @date 05.06.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;

/**
 * @author martin
 * @date 05.06.2020
 *
 */
public enum EnumMaskSonderLabel  implements IF_enum_4_db_specified<EnumMaskSonderLabel>{
	 titel_0_0
	,titel_0_1
	,titel_0_2
	,titel_1_0
	,titel_1_1
	,titel_1_2
	,einheit_0_0
	,einheit_0_1
	,einheit_0_2
	,waehrung_0_0
	,waehrung_0_1
	,waehrung_0_2
	
	;


	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		return this.name();
	}

	@Override
	public EnumMaskSonderLabel[] get_Values() {
		return EnumMaskSonderLabel.values();
	}

}

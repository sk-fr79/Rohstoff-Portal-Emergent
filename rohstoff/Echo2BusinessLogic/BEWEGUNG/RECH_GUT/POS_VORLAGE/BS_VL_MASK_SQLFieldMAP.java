package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;

/*
 * benutzung durch: Rechnung-Gutschrift-Modul, 	oBS_Setting != null, cID_VPOS_TPA_FUHRE==null
 * freie positionen:							oBS_Setting == null, cID_VPOS_TPA_FUHRE == null = Freie positionen, cID_VPOS_TPA_FUHRE != null Fuhren-Buchungsmodul 
 */
public class BS_VL_MASK_SQLFieldMAP extends Project_SQLFieldMAP {

	public BS_VL_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_VPOS_RG_VL", "", false);
		
		BS__CompMap_FieldMAP_Gemeinsamkeiten.set_BasicSQLFieldSettings_for_Positions(this, "0");

		this.get_("WAEHRUNGSKURS").set_cDefaultValueFormated("1");
		
		/*
		 * defaultwerte setzen
		 */
		this.get_("POSITIONSNUMMER").set_cDefaultValueFormated("0");
		this.get_("AUSFUEHRUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		this.get_("POSITION_TYP").set_cDefaultValueFormated(bibALL.MakeSql(myCONST.VG_POSITION_TYP_ARTIKEL));
		this.get_("LAGER_VORZEICHEN").set_cDefaultValueFormated("-1");
		
		/*
		 * must-values
		 */
		this.get_("ANZAHL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("EINZELPREIS").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("EINHEITKURZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("EINHEIT_PREIS_KURZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("STEUERSATZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("AUSFUEHRUNGSDATUM").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		
		
		this.initFields();
	}

}

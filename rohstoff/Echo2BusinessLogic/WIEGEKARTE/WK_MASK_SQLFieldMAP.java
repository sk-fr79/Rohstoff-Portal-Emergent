package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class WK_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public WK_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_WIEGEKARTE", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("BEFUND").set_cDefaultValueFormated("");
		this.get_("ID_ADRESSE_LAGER").set_cDefaultValueFormated("");
		this.get_("ID_ADRESSE_LIEFERANT").set_cDefaultValueFormated("");
		this.get_("ID_ARTIKEL_SORTE").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_TPA_FUHRE").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_TPA_FUHRE_ORT").set_cDefaultValueFormated("");
		this.get_("ID_WIEGEKARTE").set_cDefaultValueFormated("");
		this.get_("IST_LIEFERANT").set_cDefaultValueFormated("");
		this.get_("KENNZEICHEN").set_cDefaultValueFormated("");
		this.get_("LFD_NR").set_cDefaultValueFormated("");
		this.get_("NETTOGEWICHT").set_cDefaultValueFormated("");
		
		
//		this.get_("BEFUND").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_ADRESSE_LAGER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_ADRESSE_LIEFERANT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_ARTIKEL_SORTE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_VPOS_TPA_FUHRE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_VPOS_TPA_FUHRE_ORT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_WIEGEKARTE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("IST_LIEFERANT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("KENNZEICHEN").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("LFD_NR").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("NETTOGEWICHT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		/*
		 * beispiel fuer felder
		 *		
		this.get_("GENERIERUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		this.get_("ERLEDIGT").set_cDefaultValueFormated("N");
		this.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED(bibE2.get_CurrSession()));
		*/

		this.initFields();
	}

}

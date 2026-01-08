package rohstoff.Echo2BusinessLogic.LAGER_LISTE;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class LAG_KTO_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4055799948855968928L;

	public LAG_KTO_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_LAGER_KONTO", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("BUCHUNGSDATUM").set_cDefaultValueFormated("");
		this.get_("BUCHUNGSTYP").set_cDefaultValueFormated("");
		this.get_("ID_ADRESSE_LAGER").set_cDefaultValueFormated("");
		this.get_("ID_ARTIKEL_SORTE").set_cDefaultValueFormated("");
		this.get_("ID_LAGER_KONTO").set_cDefaultValueFormated("");
		this.get_("ID_LAGER_KONTO_PARENT").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_TPA_FUHRE").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_TPA_FUHRE_ORT").set_cDefaultValueFormated("");
		this.get_("IST_KOMPLETT").set_cDefaultValueFormated("");
		this.get_("MENGE").set_cDefaultValueFormated("");
		this.get_("PREIS").set_cDefaultValueFormated("");
		this.get_("SALDO").set_cDefaultValueFormated("");
		this.get_("STORNO").set_cDefaultValueFormated("");
		this.get_("BUCHUNGSDATUM").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("BUCHUNGSTYP").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_ADRESSE_LAGER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_ARTIKEL_SORTE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_LAGER_KONTO").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_LAGER_KONTO_PARENT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_VPOS_TPA_FUHRE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_VPOS_TPA_FUHRE_ORT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("IST_KOMPLETT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("MENGE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PREIS").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("SALDO").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("STORNO").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
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

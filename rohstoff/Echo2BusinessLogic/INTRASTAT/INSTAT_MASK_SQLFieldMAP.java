package rohstoff.Echo2BusinessLogic.INTRASTAT;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class INSTAT_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public INSTAT_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_INTRASTAT_MELDUNG", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("ANMELDEFORM").set_cDefaultValueFormated("");
		this.get_("ANMELDEMONAT").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_TPA_FUHRE").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_TPA_FUHRE_ORT").set_cDefaultValueFormated("");
		this.get_("BESTIMM_LAND").set_cDefaultValueFormated("");
		this.get_("BESTIMM_REGION").set_cDefaultValueFormated("");
		this.get_("BEZUGSJAHR").set_cDefaultValueFormated("");
		this.get_("BEZUGSMONAT").set_cDefaultValueFormated("");
		this.get_("BUNDESLAND_FA").set_cDefaultValueFormated("");
		this.get_("EIGENMASSE").set_cDefaultValueFormated("");
		this.get_("EXPORTFREIGABE").set_cDefaultValueFormated("");
		this.get_("EXPORTIERT_AM").set_cDefaultValueFormated("");
		this.get_("GESCHAEFTSART").set_cDefaultValueFormated("");
		this.get_("ID_INTRASTAT_MELDUNG").set_cDefaultValueFormated("");
		this.get_("LAND_URSPRUNG").set_cDefaultValueFormated("");
		this.get_("MASSEINHEIT").set_cDefaultValueFormated("");
		this.get_("MELDEART").set_cDefaultValueFormated("");
		this.get_("PAGINIERNUMMER").set_cDefaultValueFormated("");
		this.get_("RECHBETRAG").set_cDefaultValueFormated("");
		this.get_("STATISTISCHER_WERT").set_cDefaultValueFormated("");
		this.get_("STEUERNR").set_cDefaultValueFormated("");
		this.get_("UNTERSCHEIDUNGSNR").set_cDefaultValueFormated("");
		this.get_("VERKEHRSZWEIG").set_cDefaultValueFormated("");
		this.get_("WAEHRUNGSKENNZIFFER").set_cDefaultValueFormated("");
		this.get_("WARENNR").set_cDefaultValueFormated("");
		
//		this.get_("ANMELDEFORM").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ANMELDEMONAT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_VPOS_TPA_FUHRE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_VPOS_TPA_FUHRE_ORT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("BESTIMM_LAND").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("BESTIMM_REGION").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("BEZUGSJAHR").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("BEZUGSMONAT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("BUNDESLAND_FA").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("EIGENMASSE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("EXPORTFREIGABE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("EXPORTIERT_AM").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("GESCHAEFTSART").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_INTRASTAT_MELDUNG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("LAND_URSPRUNG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("MASSEINHEIT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("MELDEART").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("PAGINIERNUMMER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("RECHBETRAG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("STATISTISCHER_WERT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("STEUERNR").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("UNTERSCHEIDUNGSNR").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("VERKEHRSZWEIG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("WAEHRUNGSKENNZIFFER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("WARENNR").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
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

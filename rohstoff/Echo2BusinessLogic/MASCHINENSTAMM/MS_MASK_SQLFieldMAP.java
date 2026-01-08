package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MS_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public MS_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_MASCHINEN", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
//		this.get_("AKTIV").set_cDefaultValueFormated("");
//		this.get_("BEMERKUNG").set_cDefaultValueFormated("");
//		this.get_("BESCHREIBUNG").set_cDefaultValueFormated("");
//		this.get_("BRIEFNUMMER").set_cDefaultValueFormated("");
//		this.get_("DATUM_ANSCHAFFUNG").set_cDefaultValueFormated("");
//		this.get_("ERZEUGT_AM").set_cDefaultValueFormated("");
//		this.get_("ERZEUGT_VON").set_cDefaultValueFormated("");
//		this.get_("FAHRGESTELLNUMMER").set_cDefaultValueFormated("");
//		this.get_("GEKAUFT_AB").set_cDefaultValueFormated("");
//		this.get_("GEWAEHRLEISTUNG_BIS").set_cDefaultValueFormated("");
//		this.get_("HERSTELLER").set_cDefaultValueFormated("");
//		this.get_("ID_ADRESSE_STANDORT").set_cDefaultValueFormated("");
//		this.get_("ID_MASCHINEN").set_cDefaultValueFormated("");
//		this.get_("ID_MASCHINENTYP").set_cDefaultValueFormated("");
//		this.get_("ID_USER_BEDIENER1").set_cDefaultValueFormated("");
//		this.get_("ID_USER_BEDIENER2").set_cDefaultValueFormated("");
//		this.get_("KFZKENNZEICHEN").set_cDefaultValueFormated("");
//		this.get_("KOSTEN_ANSCHAFFUNG").set_cDefaultValueFormated("");
//		this.get_("KOSTENSTELLE1").set_cDefaultValueFormated("");
//		this.get_("KOSTENSTELLE2").set_cDefaultValueFormated("");
//		this.get_("LEASING_BIS").set_cDefaultValueFormated("");
//		this.get_("TYPENBEZ").set_cDefaultValueFormated("");
//		
//		this.get_("AKTIV").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		/*
		 * beispiel fuer felder
		 *		
		this.get_("GENERIERUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		this.get_("ERLEDIGT").set_cDefaultValueFormated("N");
		this.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED(bibE2.get_CurrSession()));
		*/
		
		this.get_("AKTIV").set_cDefaultValueFormated("Y");

		this.initFields();
	}

}

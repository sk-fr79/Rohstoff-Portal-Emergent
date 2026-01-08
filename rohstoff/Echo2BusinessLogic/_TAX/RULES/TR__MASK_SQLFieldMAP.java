package rohstoff.Echo2BusinessLogic._TAX.RULES;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;

public class TR__MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public TR__MASK_SQLFieldMAP() throws myException 
	{
		super("JT_HANDELSDEF", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		String cID_LAND = bibALL.get_RECORD_MANDANT().get_ID_LAND_cF_NN("");
		
		//this.get_("ART_GESCHAEFT").set_cDefaultValueFormated(TAX_CONST.ART_HANDEL_NICHT_KLASSIFIZIERT);
		
		this.get_(_DB.HANDELSDEF$ID_LAND_QUELLE_GEO).set_cDefaultValueFormated(cID_LAND);
		this.get_(_DB.HANDELSDEF$ID_LAND_QUELLE_JUR).set_cDefaultValueFormated(cID_LAND);
		this.get_(_DB.HANDELSDEF$ID_LAND_ZIEL_GEO).set_cDefaultValueFormated(cID_LAND);
		this.get_(_DB.HANDELSDEF$ID_LAND_ZIEL_JUR).set_cDefaultValueFormated(cID_LAND);
		
		//this.get_("MELDUNG_FUER_USER").set_cDefaultValueFormated(TAX_CONST.MELDUNG_KEINE);
		this.get_(RECORD_HANDELSDEF.FIELD__TP_VERANTWORTUNG).set_cDefaultValueFormated(TAX_CONST.TP_VERANTWORTUNG_MANDANT);
		this.get_(RECORD_HANDELSDEF.FIELD__TYP_MELDUNG).set_cDefaultValueFormated(TAX_CONST.MELDUNG_INFO);
		
		this.get_(_DB.HANDELSDEF$AKTIV).set_cDefaultValueFormated("Y");

		this.get_(_DB.HANDELSDEF$ID_TAX_QUELLE).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_(_DB.HANDELSDEF$ID_TAX_ZIEL).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);


		this.initFields();
	}

}

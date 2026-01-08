package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSA_K_MASK_SQLFieldMAP extends Project_SQLFieldMAP {

	public BSA_K_MASK_SQLFieldMAP(BS__SETTING SETTING) throws myException 
	{
		super("JT_VKOPF_STD", ":VORGANG_TYP:", false);
		
		
		/*
		 * beschraenkung fuer das Feld VORGANG_TYP
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VKOPF_STD","VORGANG_TYP","VORGANG_TYP",new MyE2_String("Vorgangsart"),bibALL.MakeSql(SETTING.get_cBELEGTYP()),bibE2.get_CurrSession()), false);
		
		/*
		 * spezielle einstellungen
		 */
		this.get_("ERSTELLUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		
		RECORD_USER oUser = new RECORD_USER(bibALL.get_ID_USER());

		this.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_RECORD_USER().get_ID_USER_cF());
		this.get_("NAME_BEARBEITER_INTERN").set_cDefaultValueFormated(oUser.get___KETTE(bibALL.get_Vector("VORNAME", "NAME1")));
		this.get_("TEL_BEARBEITER_INTERN").set_cDefaultValueFormated(bibALL.get_RECORD_USER().get_TELEFON_cF_NN(""));
		this.get_("FAX_BEARBEITER_INTERN").set_cDefaultValueFormated(bibALL.get_RECORD_USER().get_TELEFAX_cF_NN(""));

		this.get_("ID_WAEHRUNG_FREMD").set_cDefaultValueFormated(bibALL.get_ID_BASISWAEHRUNG());
		this.get_("ID_WAEHRUNG_FREMD").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		/*
		 * jetzt die MUSS-Felder definieren
		 */
		this.get_("NAME1").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PLZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ORT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_ADRESSE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		
		this.get_("DELETED").set_cDefaultValueFormated("N");
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		
		this.initFields();
	}

}

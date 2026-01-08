package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSK_K_MASK_SQLFieldMAP extends Project_SQLFieldMAP {

	public BSK_K_MASK_SQLFieldMAP(BS__SETTING SETTING) throws myException 
	{
		super("JT_VKOPF_KON", ":VORGANG_TYP:"+VKOPF_KON.ist_fixierung.fn()+":"+VKOPF_KON.typ_25_to.fn()+":"+VKOPF_KON.typ_ladung.fn()+":", false);
		
		
		/*
		 * beschraenkung fuer das Feld VORGANG_TYP
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VKOPF_KON","VORGANG_TYP","VORGANG_TYP",new MyE2_String("Vorgangsart"),bibALL.MakeSql(SETTING.get_cBELEGTYP()),bibE2.get_CurrSession()), false);
		
		/*
		 * nur NICHT-fixierungskontrakte
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VKOPF_KON",VKOPF_KON.ist_fixierung.fn(),VKOPF_KON.ist_fixierung.fn(),new MyE2_String("Vorgangsart"),"'N'",bibE2.get_CurrSession()), false);

		
		/*
		 * spezielle einstellungen
		 */
		this.get_("ERSTELLUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		
		this.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_RECORD_USER().get_ID_USER_cF());
		this.get_("NAME_BEARBEITER_INTERN").set_cDefaultValueFormated(bibALL.get_RECORD_USER().get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " "));
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

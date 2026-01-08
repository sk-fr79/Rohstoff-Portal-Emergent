package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSRG_K_MASK_SQLFieldMAP extends Project_SQLFieldMAP {

	public BSRG_K_MASK_SQLFieldMAP(BS__SETTING SETTING) throws myException 
	{
		super("JT_VKOPF_RG", ":VORGANG_TYP:", false);
		
		
		/*
		 * beschraenkung fuer das Feld VORGANG_TYP
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VKOPF_RG","VORGANG_TYP","VORGANG_TYP",new MyE2_String("Vorgangsart"),bibALL.MakeSql(SETTING.get_cBELEGTYP()),bibE2.get_CurrSession()), false);
		
		/*
		 * spezielle einstellungen
		 */
		this.get_("ERSTELLUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		
		//myUser oUser = new myUser(bibALL.get_ID_USER(),bibE2.get_CurrSession());

		this.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_RECORD_USER().get_ID_USER_cF());
		this.get_("NAME_BEARBEITER_INTERN").set_cDefaultValueFormated(bibALL.get_RECORD_USER().get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " "));
		this.get_("TEL_BEARBEITER_INTERN").set_cDefaultValueFormated(bibALL.get_RECORD_USER().get_TELEFON_cF_NN(""));
		this.get_("FAX_BEARBEITER_INTERN").set_cDefaultValueFormated(bibALL.get_RECORD_USER().get_TELEFAX_cF_NN(""));
		
		this.get_("DRUCKZAEHLER").set_cDefaultValueFormated("0");

		this.get_("ID_WAEHRUNG_FREMD").set_cDefaultValueFormated(bibALL.get_ID_BASISWAEHRUNG());
		this.get_("ID_WAEHRUNG_FREMD").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		/*
		 * jetzt die MUSS-Felder definieren
		 */
		this.get_("NAME1").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PLZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ORT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_ADRESSE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.get_("DRUCKZAEHLER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("DRUCKZAEHLER").set_bFieldCanBeWrittenInMask(false);
		
		
		this.get_("DELETED").set_cDefaultValueFormated("N");
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		
//		((MyE2_DB_TextField) this.get__Comp("UMSATZSTEUERLKZ_MANDANT")).set_iWidthPixel(30);
//		((MyE2_DB_TextField) this.get__Comp("UMSATZSTEUERID_MANDANT")).set_iWidthPixel(90);

		if (S.isFull(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF()))
		{
			RECORD_ADRESSE recAdresseMandant = new RECORD_ADRESSE(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF());
		
			this.get_("UMSATZSTEUERLKZ_MANDANT").set_cDefaultValueFormated(recAdresseMandant.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_UMSATZSTEUERLKZ_cUF_NN(""));
			this.get_("UMSATZSTEUERID_MANDANT").set_cDefaultValueFormated(recAdresseMandant.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).get_UMSATZSTEUERID_cUF_NN(""));
		}
		
		
		this.initFields();
	}

}

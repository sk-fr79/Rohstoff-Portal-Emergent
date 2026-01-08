package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_EINHEIT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_EINHEIT_PREIS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComponentMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_P_MapValidatorBeforeSave;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_SelectField_POSITIONSTYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSK_P_MASK__ComponentMAP extends BS_ComponentMAP {

	private BSK_PK_MASK_ComponentMAP oVPOS_KON_TRAKT_ComponentMAP = null;
	
	
	public BSK_P_MASK__ComponentMAP(BS__SETTING oSetting) throws myException 
	{
		super(new BSK_P_MASK_SQLFieldMAP(oSetting));
		
		BSK_P_MASK_SQLFieldMAP oFM = (BSK_P_MASK_SQLFieldMAP)this.get_oSQLFieldMAP();

		this.oVPOS_KON_TRAKT_ComponentMAP = new BSK_PK_MASK_ComponentMAP(oFM, oSetting, this);

		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VKOPF_KON")),					new MyE2_String("ID Kopf"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_KON")),					new MyE2_String("ID Pos"));
		this.add_Component(new BS_SelectField_POSITIONSTYP(oFM.get_("POSITION_TYP"),oFM,true,false,false),	new MyE2_String("Pos.Typ"));
		
		/*
		 * 	
		 * haengt folgende felder an:
		 * "ANZAHL"
		 * "ARTBEZ1"
		 * "ARTBEZ2"
		 * "EINZELPREIS"
		 * "GESAMTPREIS"
		 * "EINZELPREIS_ABZUG"
		 * "EINZELPREIS_RESULT"
		 * "GESAMTPREIS_ABZUG"
		 * "EINZELPREIS_FW"
		 * "GESAMTPREIS_FW"
		 * "EINZELPREIS_ABZUG_FW"
		 * "EINZELPREIS_RESULT_FW"
		 * "GESAMTPREIS_ABZUG_FW"
		 * "WAEHRUNGSKURS"
		 * ANR1
		 * ANR2
		 * ID_ARTIKEL
		 * POSITIONSNUMMER
		 * MWST
		 * LIEFERBEDINGUNGEN
		 * ZAHLUNGSBEDINGUNGEN
		 * ID_ZAHLUNGSBEDINGUNGEN
		 * ZAHLTAGE
		 * FIXMONAT
		 * FIXTAG
		 * SKONTO_PROZENT
		 */ 
		String cKlemmbrettKenner = "EK_KON";
		if (oSetting.get_cBELEGTYP().equals(myCONST.VORGANGSART_VK_KONTRAKT))
		{
			cKlemmbrettKenner = "VK_KON";
		}
		
		BS__CompMap_FieldMAP_Gemeinsamkeiten.add_Basic_POSITION_Fields_To_ComponentMap(this, oFM, cKlemmbrettKenner,"KON");

		
		this.add_Component(new BSK_P_MASK_SEARCH_ArtikelBEZ(oFM,oSetting),					new MyE2_String("ID-Art-Bez."));

		
		this.add_Component(new BS_ComboBox_EINHEIT(oFM),new MyE2_String("Mg.-EH"));
		this.add_Component(new BS_ComboBox_EINHEIT_PREIS(oFM),new MyE2_String("Pr.-EH"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MENGENDIVISOR"),true,48),new MyE2_String("Divisor"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MENGEN_TOLERANZ_PROZENT"),true,30,3,false),new MyE2_String("Mengentoleranz in %"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BESTELLNUMMER")),  new MyE2_String("Externe Bestellnummer"));

		
		// jetzt die toechter fuer liefertermine und lagerlieferungen
		this.add_Component(BSK__CONST.HASH_KEY_DAUGHTERTABLE_LIEFERTERMINE, new BSK_P_MASK_Daughter_Liefertermin(oFM,this),new MyE2_String("Liefertermine"));
		this.add_Component(BSK__CONST.HASH_KEY_DAUGHTERTABLE_LAGERLIEFERUNG, new BSK_P_MASK_Daughter_LagerLieferung(oFM,this),new MyE2_String("Lagerlieferungen"));
		this.add_Component(BSK__CONST.HASH_KEY_DAUGHTERTABLE_AENDERUNGSPROTOKOLL, new BSK_P_MASK_Daughter_ChangeProtokoll(oFM,this),new MyE2_String("Mengenaenderungen"));
		
		//jetzt den button fuer das aufpoppen der adress-maske 
		this.add_Component(BSK__CONST.BUTTON_TO_LOAD_ADRESSE,new BSK_P_MASK_BT_AdressePOPUP_BUTTON(), new MyE2_String("Adress-Lade-Button"));
		
		
		//2013-09-25: kopfinfos in positionsmaske
		this.add_Component(new MyE2_DB_Label(oFM.get(BSK__CONST.HASHKEY_VK_INFO_FIRMA)), new MyE2_String("Firma"));
		this.add_Component(new MyE2_DB_Label(oFM.get(BSK__CONST.HASHKEY_VK_INFO_BUCHUNGNR)), new MyE2_String("Buchungsnummer"));
		this.add_Component(new MyE2_DB_Label(oFM.get(BSK__CONST.HASHKEY_VK_INFO_BUCHUNGPOSNR)), new MyE2_String("Position"));
		
		this.add_Component(new MyE2_DB_Label(oFM.get(BSK__CONST.HASHKEY_VK_INFO_MENGE)), new MyE2_String("Menge"));
		this.add_Component(new MyE2_DB_Label(oFM.get(BSK__CONST.HASHKEY_VK_INFO_PREIS)), new MyE2_String("Preis"));
		this.add_Component(new MyE2_DB_Label(oFM.get(BSK__CONST.HASHKEY_VK_INFO_SORTE)), new MyE2_String("Sorte"));
		
		/*
		 * Feldgroessen
		 */
		
		BS__CompMap_FieldMAP_Gemeinsamkeiten.format_BasicPositionFields(this);


		((MyE2_DB_TextField)this.get("MENGENDIVISOR")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		
		
		((MyE2_DB_TextField)this.get("ID_ARTIKEL")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("ID_ARTIKEL")).set_iWidthPixel(80);
		((MyE2_DB_TextField)this.get("ID_VKOPF_KON")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("ID_VKOPF_KON")).set_iWidthPixel(80);
		((MyE2_DB_TextField)this.get("ID_VPOS_KON")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("ID_VPOS_KON")).set_iWidthPixel(80);
		
		
		//2012-10-16: feste einstellung: felder EINHEIT,EINHEIT_PREIS,MENGENDIVISOR sind INAKTIV
		this.get__DBComp("EINHEITKURZ").EXT().set_bDisabledFromBasic(true);
		this.get__DBComp("EINHEIT_PREIS_KURZ").EXT().set_bDisabledFromBasic(true);
		this.get__DBComp("MENGENDIVISOR").EXT().set_bDisabledFromBasic(true);

		
		/*
		 * abgeschaltete felder
		 */
		BS__CompMap_FieldMAP_Gemeinsamkeiten.set_READ_ONLY_Fields(this);
		((MyE2_DB_TextField)this.get("ANR1")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ANR2")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ID_ARTIKEL")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ID_VKOPF_KON")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ID_VPOS_KON")).EXT().set_bDisabledFromBasic(true);
		
		((MyE2_DB_TextField)this.get("BESTELLNUMMER")).set_iWidthPixel(200);

		
		/*
		 * positionstyp aktivieren
		 */
		((BS_SelectField_POSITIONSTYP)this.get("POSITION_TYP")).add_oActionAgent(new ownActionAgentSettingPositionsTyp(this.oVPOS_KON_TRAKT_ComponentMAP));
		// wird erstmal totgestellt
		((BS_SelectField_POSITIONSTYP)this.get("POSITION_TYP")).EXT().set_bDisabledFromBasic(true);
		
		//zahlungsbedingungen und bestellnummer gleich lang
		((MyE2_SelectField)this.get__Comp(RECORD_VPOS_KON.FIELD__ID_ZAHLUNGSBEDINGUNGEN)).setWidth(new Extent(250));
		((MyE2_TextField)this.get__Comp(RECORD_VPOS_KON.FIELD__BESTELLNUMMER)).setWidth(new Extent(250));
		
		
		this.set_oMAPSettingAgent(new BSK_P_MASK_MAP_SETTING_AGENT());
		this.add_oMAPValidator(new BS_P_MapValidatorBeforeSave(false));
		this.add_oMAPValidator(new BSK_P_MASK_MapValidator_CheckMengeInLieferdaten());
		
		this.set_oSubQueryAgent(new BSK_P_MASK_ComponentMAP_Subquery_Agent());
		
		this.get_V_ADDON_SQL_STATEMENT_BUILDER().add(new BSK_P_MASK__ComponentMAP_builder_AddOnSQL_Protokoll_Mengenaenderung());
		
		
	}

	
	
	
	
		
	
	
	/*
	 * zusaetzlicher actionagent, der im positionstyp bei nicht-artikel auch die vpos_kon_trakt - felder schliesst und oeffnet
	 */
	private class ownActionAgentSettingPositionsTyp extends XX_ActionAgent
	{
		private BSK_PK_MASK_ComponentMAP o_VPOS_KON_TRAKT_ComponentMAP = null;
		
		private String[] cHelpText 	= 	{"ID_VPOS_KON_TRAKT","ID_VPOS_KON","LIEFERZEIT","LIEFERORT","BEMERKUNG_INTERN","GUELTIG_VON","GUELTIG_BIS"};
		private String[] cHelpCB   	=	{"UEBERLIEFER_OK","ABGESCHLOSSEN"};

		
		public ownActionAgentSettingPositionsTyp(BSK_PK_MASK_ComponentMAP   VPOS_KON_TRAKT_ComponentMAP)
		{
			super();
			o_VPOS_KON_TRAKT_ComponentMAP = VPOS_KON_TRAKT_ComponentMAP;
		}


		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			MyE2_DB_SelectField oSelField = (MyE2_DB_SelectField)bibE2.get_LAST_ACTIONEVENT().getSource();

			if (oSelField.get_ActualWert().equals(myCONST.VG_POSITION_TYP_ARTIKEL))
			{
				this.setEnabled(true);
			}
			else
			{
				this.setEnabled(false);
				
				for (int i=0;i<cHelpText.length;i++)
					((MyE2IF__DB_Component)this.o_VPOS_KON_TRAKT_ComponentMAP.get(cHelpText[i])).set_cActualMaskValue("");
				
				for (int i=0;i<cHelpCB.length;i++)
					((MyE2_DB_CheckBox)this.o_VPOS_KON_TRAKT_ComponentMAP.get(cHelpCB[i])).setSelected(false);
			}
		}


		
		private void setEnabled(boolean bEnabled) throws myException
		{
			// die metadefs werden im innersten validator (MyFieldStandardValidator) benutzt 
			this.o_VPOS_KON_TRAKT_ComponentMAP.get_oSQLFieldMAP().get_("GUELTIG_VON").get_oFieldMetaDef().set_bIsNullableInteractiv(!bEnabled);
			this.o_VPOS_KON_TRAKT_ComponentMAP.get_oSQLFieldMAP().get_("GUELTIG_BIS").get_oFieldMetaDef().set_bIsNullableInteractiv(!bEnabled);

			
			for (int i=0;i<cHelpText.length;i++)
				((MyE2IF__Component)this.o_VPOS_KON_TRAKT_ComponentMAP.get(cHelpText[i])).set_bEnabled_For_Edit(bEnabled);
			
			for (int i=0;i<cHelpCB.length;i++)
				((MyE2_DB_CheckBox)this.o_VPOS_KON_TRAKT_ComponentMAP.get(cHelpCB[i])).set_bEnabled_For_Edit(bEnabled);
			
			((MyE2_Button) this.o_VPOS_KON_TRAKT_ComponentMAP.get(BSK__CONST.HASH_KEY_POSITION_MASK_SET_ACTUAL_MONTH)).set_bEnabled_For_Edit(bEnabled);
			((MyE2_Button) this.o_VPOS_KON_TRAKT_ComponentMAP.get(BSK__CONST.HASH_KEY_POSITION_MASK_SET_NEXT_MONTH)).set_bEnabled_For_Edit(bEnabled);

		}
	}



	public BSK_PK_MASK_ComponentMAP get_oVPOS_KON_TRAKT_ComponentMAP()
	{
		return oVPOS_KON_TRAKT_ComponentMAP;
	}
	
	
	
	
	
	
}

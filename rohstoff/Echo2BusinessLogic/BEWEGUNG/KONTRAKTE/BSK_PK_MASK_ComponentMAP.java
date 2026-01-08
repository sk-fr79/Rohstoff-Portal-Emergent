package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.Date;
import java.util.HashMap;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.MAP_Validator_CheckCorrectDateRange;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.E2_Button_SetDateRange;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea_WithSelektorEASY;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_KOPF;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_POS;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_TRANSPORTMITTEL;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;


/*
 * zusatzfelder des 1x1-tabelle jt_vpos_kon_trakt
 */
public class BSK_PK_MASK_ComponentMAP extends E2_ComponentMAP {

	
	//2020-02-12: wird gebraucht fuer die validierung auf vorhandene faktfrist
	private BSK_P_MASK__ComponentMAP bskPMaskComponentMap;


	public BSK_PK_MASK_ComponentMAP(BSK_P_MASK_SQLFieldMAP 		oSQLFieldMap_VPOS_KON, BS__SETTING oSetting, BSK_P_MASK__ComponentMAP p_bskPMaskComponentMap) throws myException 
	{
		
		super(new BSK_PK_MASK_SQLFieldMAP(oSQLFieldMap_VPOS_KON));
		this.bskPMaskComponentMap = p_bskPMaskComponentMap;
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();

		MyE2_DB_CheckBox cbUeberlieferOK =  new MyE2_DB_CheckBox(oFM.get_("UEBERLIEFER_OK"));
		
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_KON_TRAKT")),			new MyE2_String("ID (VPOS_KON_TRAKT)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_KON")),					new MyE2_String("ID (VPOS_KON)"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("LIEFERZEIT")),					new MyE2_String("Lieferung am"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("LIEFERORT")),					new MyE2_String("Lieferort"));
		this.add_Component(cbUeberlieferOK,													new MyE2_String("Überlieferung OK"));
		this.add_Component(new BS_ComboBox_TRANSPORTMITTEL(oFM,true),						new MyE2_String("Transportmittel"));			
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("ABGESCHLOSSEN")),					new MyE2_String("Abgeschlossen"));	
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("BEMERKUNG_INTERN"),500,6),		new MyE2_String("Bemerkungen"));
		
		//this.add_Component(new MyE2_DB_TextArea(oFM.get_("BEMERKUNG_EXTERN")),				new MyE2_String("Bemerkungen Beleg"));

		MyE2_DB_TextArea_WithSelektorEASY  oBemExtern = new MyE2_DB_TextArea_WithSelektorEASY(oFM.get_("BEMERKUNG_EXTERN"),"KONTRAKT_POS_BEMERKUNGEN_BELEG_INT");
		this.add_Component(oBemExtern, new MyE2_String("Bemerkungen Beleg"));
		oBemExtern.get_oTextArea().set_iWidthPixel(500);
		oBemExtern.get_oTextArea().set_iRows(6);
		
		
		
		String cKlemmbrettKenner = "EK_KONTRAKT_POS_FIXIERUNGSBED";
		if (oSetting.get_cBELEGTYP().equals(myCONST.VORGANGSART_VK_KONTRAKT))
		{
			cKlemmbrettKenner = "VK_KONTRAKT_POS_FIXIERUNGSBED";
		}

		MyE2_DB_TextArea_WithSelektorEASY  oFixierungsBedingungen = new MyE2_DB_TextArea_WithSelektorEASY(oFM.get_("FIXIERUNGSBEDINGUNGEN"),cKlemmbrettKenner);
		this.add_Component(oFixierungsBedingungen, new MyE2_String("Fixierungsbedingungen"));
		oFixierungsBedingungen.get_oTextArea().set_iWidthPixel(500);
		oFixierungsBedingungen.get_oTextArea().set_iRows(10);
		oFixierungsBedingungen.set_bAddTextBehind(true);

		
		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("GUELTIG_VON")),					new MyE2_String("gültig von"));
		this.add_Component(new MyE2_DB_TextField_DatePOPUP_OWN(oFM.get_("GUELTIG_BIS")),					new MyE2_String("gültig bis"));

		// zwei buttons um in der maske die zeitraeume zu setzen
		this.add_Component(BSK__CONST.HASH_KEY_POSITION_MASK_SET_ACTUAL_MONTH,
				new E2_Button_SetDateRange(true,(MyE2_DB_TextField_DatePOPUP_OWN)this.get__Comp("GUELTIG_VON"),(MyE2_DB_TextField_DatePOPUP_OWN)this.get__Comp("GUELTIG_BIS")),new MyE2_String("Aktuellen Monat besetzen"));
		this.add_Component(BSK__CONST.HASH_KEY_POSITION_MASK_SET_NEXT_MONTH,
				new E2_Button_SetDateRange(false,(MyE2_DB_TextField_DatePOPUP_OWN)this.get__Comp("GUELTIG_VON"),(MyE2_DB_TextField_DatePOPUP_OWN)this.get__Comp("GUELTIG_BIS")),new MyE2_String("Nächsten Monat besetzen"));

		
		
		this.add_Component(BSK__CONST.BUTTON_TO_TAKE_LIEFERORTE,
				new BSK_P_MASK_ButtonToGetLieferort(),new MyE2_String("Lieferort suchen"));

		
		//2016-02-29: das feld "abgeschlossen" ist nur als anzeigefeld gedacht und wird in der liste umgeschaltet
		this.get__Comp(VPOS_KON_TRAKT.abgeschlossen.fn()).EXT().set_bDisabledFromBasic(true);
		
		
		//2020-02-12: VERLAENGERTE_FAKT_FRIST - checkbox definiert, ob VERLAENGERTE_FAKT_FRIST - eintraege beim kunden herangezogen werden koennen
		this.add_Component((MyE2_DB_CheckBox)new MyE2_DB_CheckBox(oFM.get_(VPOS_KON_TRAKT.verlaengerte_fakt_frist.fn()))._ttt(S.ms("Falls beim Kunden eine Kreditversicherung mit verlängerter Fakturierungsfrist hinterlegt ist, kann die Nutzung dieser Frist hier eingeschaltet werden")),
											new MyE2_String("Verlängerte Fakt.Frist"));	

		
		/*
		 * Feldgroessen
		 */
		((MyE2_DB_TextField)this.get("ID_VPOS_KON_TRAKT")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("ID_VPOS_KON_TRAKT")).set_iWidthPixel(80);
		((MyE2_DB_TextField)this.get("ID_VPOS_KON")).setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		((MyE2_DB_TextField)this.get("ID_VPOS_KON")).set_iWidthPixel(80);
		
		((MyE2_DB_TextField)this.get("LIEFERZEIT")).set_iWidthPixel(500);
		((MyE2_DB_TextField)this.get("LIEFERORT")).set_iWidthPixel(500);
		
		
//		//test
//		((MyE2IF__DB_Component)this.get("LIEFERORT")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_AFTER_INIT_SQLFieldMAP(false);
//		((MyE2IF__DB_Component)this.get("LIEFERZEIT")).EXT_DB().get_oSQLField().set_bIsNullableByUserDef_AFTER_INIT_SQLFieldMAP(false);

		
		
		
		/*
		 * abgeschaltete felder
		 */
		((MyE2_DB_TextField)this.get("ID_VPOS_KON_TRAKT")).EXT().set_bDisabledFromBasic(true);
		((MyE2_DB_TextField)this.get("ID_VPOS_KON")).EXT().set_bDisabledFromBasic(true);

		this.add_oMAPValidator(new MAP_Validator_CheckCorrectDateRange("GUELTIG_VON","GUELTIG_BIS"));
		this.add_oMAPValidator(new MapValidatorCheckFaktFrist());
		
		cbUeberlieferOK.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
			}
		});

		cbUeberlieferOK.add_GlobalAUTHValidator_AUTO("UEBERLIEFERUNG_ERLAUBEN");
		
		
		this.set_oMAPSettingAgent(new BSK_PK_MASK_MAP_SETTING_AGENT());
		
		
	}
	
	
	private class MapValidatorCheckFaktFrist extends XX_MAP_ValidBeforeSAVE {

		@Override
		public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException {
			
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			BSK_PK_MASK_ComponentMAP  pk_map = BSK_PK_MASK_ComponentMAP.this;
			BSK_P_MASK__ComponentMAP  p_map =  BSK_PK_MASK_ComponentMAP.this.bskPMaskComponentMap;
			
			boolean useVerlaengerteFaktFrist = (pk_map.get_cActualDBValueFormated(VPOS_KON_TRAKT.verlaengerte_fakt_frist.fn(),"N").equals("Y"));
			
			if (useVerlaengerteFaktFrist) {
			
				Date    vertragGueltigKeitVon = null;
				Date    vertragGueltigKeitBis = null;
				
				MyDate gueltVon = pk_map.get_ActualMyDate(VPOS_KON_TRAKT.gueltig_von.fn(), true, true, null);
				MyDate gueltBis = pk_map.get_ActualMyDate(VPOS_KON_TRAKT.gueltig_bis.fn(), true, true, null);
				
				if (gueltVon!=null) {
					vertragGueltigKeitVon = gueltVon.getDate();
				}
	
				if (gueltBis!=null) {
					vertragGueltigKeitBis = gueltBis.getDate();
				}
	
				Rec21_adresse ra = null;
				Long idKopf = null;
				HashMap<String, String> restrictMap =p_map.get_oSQLFieldMAP().get_hmRestrictionFieldValues();
				
				try {
					idKopf = new MyLong( restrictMap.get(VPOS_KON.id_vkopf_kon.fn())).get_oLong();
					ra = new Rec21_adresse(new Rec21(_TAB.vkopf_kon)._fill_id(idKopf).get_up_Rec21(ADRESSE.id_adresse));
					RecList21  versKopf = ra.getKreditversicherungenAktivUndInaktiv();
					boolean mindestensEineVersicherungMitFaktFristimKontraktZeitRaum = false;
					
					for (Rec21 kreditvers: versKopf) {
						if (O.NN(kreditvers.getLongDbValue(KREDITVERS_KOPF.fakturierungsfrist),0l).longValue()>0l) { 
							RecList21 positionen = kreditvers.get_down_reclist21(KREDITVERS_POS.id_kreditvers_kopf);
							
							for (Rec21 kreditPos: positionen) {
								Date  kreditVersVon = kreditPos.getDateDbValue(KREDITVERS_POS.gueltig_ab);
								Date  kreditVersBis = kreditPos.getDateDbValue(KREDITVERS_POS.gueltig_bis);
								if (kreditVersBis==null) {
									kreditVersBis = new MyDate("01.01.2200").getDate();
								}
								
								if (     (kreditVersVon.before(vertragGueltigKeitVon) && kreditVersBis.after(vertragGueltigKeitVon) ) 
								         || 
								         (kreditVersVon.before(vertragGueltigKeitBis) && kreditVersBis.after(vertragGueltigKeitBis) )
								         ||
								         (vertragGueltigKeitVon.before(kreditVersVon) && vertragGueltigKeitBis.after(kreditVersVon) )
								         ||
								         (vertragGueltigKeitVon.before(kreditVersBis) && vertragGueltigKeitBis.after(kreditVersBis) )
								   ) {
									mindestensEineVersicherungMitFaktFristimKontraktZeitRaum = true;
								}
							}
						}
					}
					
	
					if (!mindestensEineVersicherungMitFaktFristimKontraktZeitRaum) {
						mv._addAlarm("Sie haben den Schalter <Verl.Fakturierungsfrist> aktiviert, obwohl im Moment keine Kreditversicherung diesen Vertragszeitraum tangiert!");
					}
					
					
				} catch (Exception e) {
					mv._addAlarm("Systemfehler beim Prüfen der Kreditversicherungen");
	
					e.printStackTrace();
				}
				
	//			mv._addInfo(useVerlaengerteFaktFrist?"Frist ein":"Frist aus");
	//			mv._addInfo("Datum von :"+vertragGueltigKeitVon.toString()+" bis "+vertragGueltigKeitBis.toString());
	//			mv._addInfo("Adresse: "+ra.get__FullNameAndAdress_mit_id());
				
			}
			return mv;
		}
		
	}
	
}

package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import java.util.HashMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.__SYSTEM_MESSAGE_GENERATOR_FUHREN;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.FU_ZeigeAufMaske_FremdwarenLager;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__LoadAdressFieldsInMaskAfterSearchAdress;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED.FU_AL_Component;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS.FUO_Set_And_Valid_AnzeigeFremdWaehrung;
import rohstoff.utils.AdressPopUP_FOR_DB_SEARCH_Adresse;
import rohstoff.utils.MyAdress;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class FUO_MASK_DB_SEARCH_Adresse extends DB_SEARCH_Adress
{

	private String EK_VK = null;

	
	public FUO_MASK_DB_SEARCH_Adresse(SQLField osqlField, String cEK_VK, FU_DAUGHTER_ORTE FUO_DaugherComponent) throws myException
	{
		super( osqlField, (cEK_VK.equals("EK")),(cEK_VK.equals("VK")),E2_INSETS.I_0_0_0_0);
		

	
		this.EK_VK = cEK_VK;
		
		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskSettings());

		this.set_AddOnComponent(new AdressPopUP_FOR_DB_SEARCH_Adresse());
		this.get_oTextFieldForSearchInput().set_iWidthPixel(75);
		this.get_oTextForAnzeige().setWidth(new Extent(400));
		
		//2012-08-07: Suche nur in adressen ohne sperrvermerke (ausser barkunden)
		this.get_oSeachBlock().get_vZusatzWhereBedingungen().add(
				this.EK_VK.equals("EK")
				?
				"(NVL(JT_ADRESSE.WARENEINGANG_SPERREN,'N')='N' OR NVL(JT_ADRESSE.BARKUNDE,'N')='Y')"
				:
				"(NVL(JT_ADRESSE.WARENAUSGANG_SPERREN,'N')='N' OR NVL(JT_ADRESSE.BARKUNDE,'N')='Y')");
		//2012-08-07

		
		this.get_oTextForAnzeige().setFont(new E2_FontBold());
		
		this.EXT().INIT_Schalter_TRUE(FU___CONST.SCHALTER_PRUEFE_UND_WARNE_WG_AVV_VERTRAG);

		
	}


	
	private class ownMaskSettings extends XX_MaskActionAfterFound
	{
		
		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException
		{
			//2013-05-28: zeige fremdwarenlager auf der maske an (hier, damit es auch beim laden der maske ausgefuehrt wird
			new ownFUO_ZeigeAufMaske_FremdwarenLager(
					(MyE2_Grid)FUO_MASK_DB_SEARCH_Adresse.this.EXT().get_oComponentMAP().get__Comp(FUO__CONST.HASH_MASK_COMP_FREMDWARENLAGER),
					true);

			
			if (!bAfterAction) return;
		
			MyAdress oAdress = new MyAdress(cMaskValue, true);
			
			E2_ComponentMAP oMap = FUO_MASK_DB_SEARCH_Adresse.this.EXT().get_oComponentMAP();
			HashMap<String, MyE2IF__Component> hmRealCompents = oMap.get_hmRealComponents();
		
			new FU__LoadAdressFieldsInMaskAfterSearchAdress(FUO_MASK_DB_SEARCH_Adresse.this.EK_VK,
					FU__LoadAdressFieldsInMaskAfterSearchAdress.get_UebersetzerMapFuer_Zusatzort_Seite(),true).set_MaskValues(hmRealCompents, oAdress);
			
			
			//die Buchungsinfos leermachen
			oMap.get__DBComp("MANUELL_PREIS").set_cActualMaskValue("N");
			oMap.get__DBComp("EINZELPREIS").set_cActualMaskValue("");
			oMap.get__DBComp("STEUERSATZ").set_cActualMaskValue("");
			oMap.get__DBComp("EU_STEUER_VERMERK").set_cActualMaskValue("");
			// ---------------------------

			//2013-09-26: auch die id_tax loeschen
			oMap.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$ID_TAX).set_cActualMaskValue("");
			
			
			//2014-06-02: die sonderlieferbedingung wieder entfernen
			if (S.isFull(oMap.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$LIEFERBED_ALTERNATIV).get_cActualDBValueFormated())) {
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung! Die alternative Lieferbedingung im Fuhrenort wird durch das Laden des Kontraktes/derAdresse zurückgesetzt !")));
			}

			oMap.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$LIEFERBED_ALTERNATIV).set_cActualMaskValue("");
			((FU_AL_Component)oMap.get__Comp(FUO__CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV)).clear_Lieferbedingungen();
			((FU_AL_Component)oMap.get__Comp(FUO__CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV)).refreshLieferbedingung();
			//2014-06-02

			
			

			//jetzt den popup-warner fuer die evtl. nicht vorhandenen avv-kontrakte aufrufen,
			//dazu muss der status der warnung ohne avv_vertrag gecheckt werden
			MyE2_DB_CheckBox oCB_MustWarn = (MyE2_DB_CheckBox)FUO_MASK_DB_SEARCH_Adresse.this.EXT().get_oComponentMAP().get__Comp("OHNE_AVV_VERTRAG_CHECK");
			
			new FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden(
							FUO_MASK_DB_SEARCH_Adresse.this,
							(MyE2_DB_MaskSearchField)oMap.get__Comp("ID_VPOS_KON"),
							FUO_MASK_DB_SEARCH_Adresse.this.EK_VK,
							oAdress.get_cID_ADRESSE_orig(),
							oCB_MustWarn,
							bIS_PrimaryCall, 
							true);

			
			//2011-11-18: system-meldungen anzeigen
			new __SYSTEM_MESSAGE_GENERATOR_FUHREN(oAdress.get_cID_ADRESSE_orig(), FUO_MASK_DB_SEARCH_Adresse.this.EK_VK).ACTIVATE_MESSAGES();
			
			
			//20180607: waehrungsanzeiger
			new FUO_Set_And_Valid_AnzeigeFremdWaehrung().setManual(FUO_MASK_DB_SEARCH_Adresse.this.EXT().get_oComponentMAP());
			
		}
	}
	
	
	private class ownFUO_ZeigeAufMaske_FremdwarenLager extends FU_ZeigeAufMaske_FremdwarenLager {
		public ownFUO_ZeigeAufMaske_FremdwarenLager(MyE2_Grid ogridZurAnzeige, boolean bOnlyClearAnzeige)	throws myException {
			super(ogridZurAnzeige, bOnlyClearAnzeige);
		}
		@Override
		public String get_cID_LAGER_AUS_MASKE() throws myException {
			FUO_MASK_DB_SEARCH_Adresse oThis = FUO_MASK_DB_SEARCH_Adresse.this;
			
			long lRueck = oThis.EXT().get_oComponentMAP().get_LActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE_LAGER, -1l, -1l);
			if (lRueck == -1) {
				return "";
			} else {
				return ""+lRueck;
			}
		}
		@Override
		public boolean get_bLadestationIstVonMandant() throws myException {
			FUO_MASK_DB_SEARCH_Adresse oThis = FUO_MASK_DB_SEARCH_Adresse.this;
			long lID_ADRESSE = oThis.EXT().get_oComponentMAP().get_LActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE, -1l, -1l);
			if (lID_ADRESSE != -1l && bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(-2l).longValue()==lID_ADRESSE) {
				return true;
			} else {
				return false;
			}
		}

	}

	
	
}

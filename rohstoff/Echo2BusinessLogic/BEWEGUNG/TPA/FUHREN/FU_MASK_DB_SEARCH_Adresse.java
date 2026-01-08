package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.HashMap;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.__SYSTEM_MESSAGE_GENERATOR_FUHREN;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_BT_MASK_NEW_ADRESS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.FU_ZeigeAufMaske_FremdwarenLager;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED.FU_AL_Component;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS.FU_Set_And_Valid_AnzeigeFremdWaehrung;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.utils.AdressPopUP_FOR_DB_SEARCH_Adresse;
import rohstoff.utils.MyAdress;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class FU_MASK_DB_SEARCH_Adresse extends DB_SEARCH_Adress
{


	
	private String cEK_VK = null;
	
	
	public FU_MASK_DB_SEARCH_Adresse(SQLField osqlField, String EK_VK) throws myException
	{
		super(osqlField,(EK_VK.equals("EK")),(EK_VK.equals("VK")), E2_INSETS.I_0_0_1_0);
		
		this.cEK_VK = EK_VK;
		
		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskSettings());
		
		this.get_vAddOnComponents().removeAllElements();
		

		//auf der EK-Seite ein neueingabe-button fuer bar-anlieferer
		if (this.cEK_VK.equals("EK"))
		{
			this.add_AddOnComponent(new BS_BT_MASK_NEW_ADRESS("ID_ADRESSE_START",this));
		}
		this.add_AddOnComponent(new AdressPopUP_FOR_DB_SEARCH_Adresse());
		
		this.get_oTextFieldForSearchInput().set_iWidthPixel(60);
		
		//2012-08-07: Suche nur in adressen ohne sperrvermerke (ausser barkunden)
		this.get_oSeachBlock().get_vZusatzWhereBedingungen().add(
				this.cEK_VK.equals("EK")
				?
				"(NVL(JT_ADRESSE.WARENEINGANG_SPERREN,'N')='N' OR NVL(JT_ADRESSE.BARKUNDE,'N')='Y')"
				:
				"(NVL(JT_ADRESSE.WARENAUSGANG_SPERREN,'N')='N' OR NVL(JT_ADRESSE.BARKUNDE,'N')='Y')");
		//2012-08-07
		
		
		this.get_oTextForAnzeige().setFont(new E2_FontBold());
		//2011-02-05: label-anzeige
		this.set_bLabel4AnzeigeStattText4Anzeige(true);
		this.get_oLabel4Anzeige().get_oLabel().setFont(new E2_FontPlain());
		this.get_oLabel4Anzeige().set_iWidth(220);
		
		this.set_bShowSearchButtonLeft(true);
		
		this.EXT().INIT_Schalter_TRUE(FU___CONST.SCHALTER_PRUEFE_UND_WARNE_WG_AVV_VERTRAG);
		
		this._addValueChangeListener(c->{
			getGridForShowResults()._setSize(190,30)._clear();
			Long idAdresse = ((MyE2_DB_MaskSearchField)c).getLongValue();
			if (idAdresse!=null) {
				try {
					Rec21_adresse   adresse = new Rec21_adresse()._fill_id(idAdresse);
					Rec21 			land = adresse.get_up_Rec21(LAND.id_land);
					getGridForShowResults()._add(new RB_lab()._t(adresse.get__Name1Name2StrassePlzOrt(",  ")), new RB_gld()._left_top()._ins(2));
					if (land!=null) {
						getGridForShowResults()._add(new RB_lab()._t(land.getUfs(LAND.laendercode,"-"))._fsa(4)._b(), new RB_gld()._center_mid()._ins(2));
					}
					
				} catch (myException e) {
					e.printStackTrace();
					getGridForShowResults()._s(1)._add(new RB_lab("Error !!!"), new RB_gld()._center_mid());
				}
						
				
			}
			
		});

	}


	private class ownMaskSettings extends XX_MaskActionAfterFound
	{

		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException
		{
			if (!bAfterAction) return;
			
			FU_MASK_DB_SEARCH_Adresse oThis = FU_MASK_DB_SEARCH_Adresse.this;
			
			
			new ownFU_ZeigeAufMaske_FremdwarenLager(oThis.cEK_VK.equals("EK")?
					(MyE2_Grid)oThis.EXT().get_oComponentMAP().get__Comp(FU___CONST.HASH_MASK_COMP_FREMDWARENLAGER_LADESEITE):
					(MyE2_Grid)oThis.EXT().get_oComponentMAP().get__Comp(FU___CONST.HASH_MASK_COMP_FREMDWARENLAGER_ABLADESEITE)
					,true);

			
			MyAdress oAdress = new MyAdress(cMaskValue,true);
			
			E2_ComponentMAP oMap = FU_MASK_DB_SEARCH_Adresse.this.EXT().get_oComponentMAP();
			HashMap<String, MyE2IF__Component> hmRealCompents = oMap.get_hmRealComponents();

			
			new FU__LoadAdressFieldsInMaskAfterSearchAdress(oThis.cEK_VK,
					oThis.cEK_VK.equals("EK")?	FU__LoadAdressFieldsInMaskAfterSearchAdress.get_UebersetzerMapFuer_EK_Seite():
												FU__LoadAdressFieldsInMaskAfterSearchAdress.get_UebersetzerMapFuer_VK_Seite(),true).set_MaskValues(hmRealCompents, oAdress);
			
			//die Buchungsinfos leermachen
			oMap.get__DBComp("MANUELL_PREIS_"+		oThis.cEK_VK).set_cActualMaskValue("N");
			oMap.get__DBComp("EINZELPREIS_"+		oThis.cEK_VK).set_cActualMaskValue("");
			oMap.get__DBComp("STEUERSATZ_"+			oThis.cEK_VK).set_cActualMaskValue("");
			oMap.get__DBComp("EU_STEUER_VERMERK_"+	oThis.cEK_VK).set_cActualMaskValue("");
			// ---------------------------
			//2013-09-26: die neue steuerid auch leermachen
			oMap.get__DBComp(oThis.cEK_VK.equals("EK")?_DB.VPOS_TPA_FUHRE$ID_TAX_EK:_DB.VPOS_TPA_FUHRE$ID_TAX_VK).set_cActualMaskValue("");
			
			
			//2014-06-02: die sonderlieferbedingung wieder entfernen
			//2014-06-05: die sonderlieferbedingung wieder entfernen
			if (S.isFull(oMap.get__DBComp(cEK_VK.equals("EK")?_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK:_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_VK).get_cActualDBValueFormated())) {
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Achtung! Die alternative Lieferbedingung in der Fuhre wird durch das Laden des Kontraktes/der Adresse zurückgesetzt !")));
			}

			oMap.get__DBComp(oThis.cEK_VK.equals("EK")?_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK:_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_VK).set_cActualMaskValue("");
			((FU_AL_Component)oMap.get__Comp(oThis.cEK_VK.equals("EK")?FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK:FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK)).clear_Lieferbedingungen();
			((FU_AL_Component)oMap.get__Comp(oThis.cEK_VK.equals("EK")?FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK:FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK)).refreshLieferbedingung();
			//2014-06-02
			

			//jetzt den popup-warner fuer die evtl. nicht vorhandenen handelsvertaege aufrufen
			new FU__Popup_Warne_kein_Handelsvertrag_AND_Check_Kontrakt_if_kontrakt_vorhanden(
									FU_MASK_DB_SEARCH_Adresse.this,
									(MyE2_DB_MaskSearchField)oThis.EXT().get_oComponentMAP().get__Comp("ID_VPOS_KON_"+oThis.cEK_VK),
									oThis.cEK_VK,oAdress.get_cID_ADRESSE_orig(),
									(MyE2_DB_CheckBox)oMap.get_Comp("OHNE_AVV_VERTRAG_CHECK"),
									bIS_PrimaryCall, 
									true);
			
			//2011-11-17: meldungen anzeigen
			new __SYSTEM_MESSAGE_GENERATOR_FUHREN(oAdress.get_cID_ADRESSE(), oThis.cEK_VK).ACTIVATE_MESSAGES();
			
			
			//2018-06-05: fremdwaehrungsanzeige refreshen
			new FU_Set_And_Valid_AnzeigeFremdWaehrung().setManual(FU_MASK_DB_SEARCH_Adresse.this.EXT().get_oComponentMAP());
		}
		
	}
 
	private class ownFU_ZeigeAufMaske_FremdwarenLager extends FU_ZeigeAufMaske_FremdwarenLager {
		public ownFU_ZeigeAufMaske_FremdwarenLager(MyE2_Grid ogridZurAnzeige, boolean bOnyClearAnzeige)	throws myException {
			super(ogridZurAnzeige, bOnyClearAnzeige);
		}
		@Override
		public String get_cID_LAGER_AUS_MASKE() throws myException {
			FU_MASK_DB_SEARCH_Adresse oThis = FU_MASK_DB_SEARCH_Adresse.this;
			
			long lRueck = oThis.EXT().get_oComponentMAP().get_LActualDBValue(oThis.cEK_VK.equals("EK")?
														_DB.VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_START:
														_DB.VPOS_TPA_FUHRE$ID_ADRESSE_LAGER_ZIEL, -1l, -1l);
			if (lRueck == -1) {
				return "";
			} else {
				return ""+lRueck;
			}
		}
		@Override
		public boolean get_bLadestationIstVonMandant() throws myException {
			FU_MASK_DB_SEARCH_Adresse oThis = FU_MASK_DB_SEARCH_Adresse.this;
			long lID_ADRESSE = oThis.EXT().get_oComponentMAP().get_LActualDBValue(
					oThis.cEK_VK.equals("EK")?_DB.VPOS_TPA_FUHRE$ID_ADRESSE_START:_DB.VPOS_TPA_FUHRE$ID_ADRESSE_ZIEL, -1l, -1l);
			if (lID_ADRESSE != -1l && bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(-2l).longValue()==lID_ADRESSE) {
				return true;
			} else {
				return false;
			}
		}

	}

}

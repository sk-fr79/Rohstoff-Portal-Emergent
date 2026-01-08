package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.FieldValidator_FieldDisabled;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_SearchBlock;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.FU_ZeigeAufMaske_FremdwarenLager;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__LoadAdressFieldsInMaskAfterSearchAdress;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__MaskSettingsAfterInput__Plausibilitaet;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU__Popup_Warne_kein_Handelsvertrag_Bei_Lagerort;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.__FU_ListenBuilder_ButtonListSearchLagerOrt;
import rohstoff.utils.MyAdress;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class FUO_MASK_DB_SEARCH_AdresseLager extends DB_SEARCH_Adress
{

	private String EK_VK = null;
	
	public FUO_MASK_DB_SEARCH_AdresseLager(SQLField osqlField, MyE2_DB_MaskSearchField SearchAdressField, String cEK_VK, FU_DAUGHTER_ORTE FUO_DaugherComponent) throws myException
	{
//		super(osqlField, null, "SELECT trim(  NVL(NAME1,'-'))|| ' ' || trim(  NVL(NAME2,'-'))|| ', ' || trim(  NVL(STRASSE,'-')) || ', ' || trim(  NVL(ORT,'-')) ||' " +
//		" ('||trim(TO_CHAR(ID_ADRESSE))||')' FROM " + bibE2.cTO() + ".JT_ADRESSE WHERE ID_ADRESSE=#WERT#", null);

		super(osqlField, null, DB_SEARCH_Adress.cAdressSearchQuery4LabelLagerAdress, null);

		
		

		this.EK_VK = cEK_VK;

		this.set_oSeachBlock(new ownSearchBlock(SearchAdressField));
		this.get_oTextFieldForSearchInput().EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
		
		this.get_oTextFieldForSearchInput().set_iWidthPixel(75);
		this.get_oTextForAnzeige().setWidth(new Extent(400));

		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskSettings());
		this.set_oPopupWidth(new Extent(600));
		
		this.add_ValidatorStartSearch(new XX_ActionValidator()
		{

			@Override
			public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
			{
				MyE2_MessageVector oMV = new MyE2_MessageVector();

				Long lID_ADRESSE = FUO_MASK_DB_SEARCH_AdresseLager.this.EXT().get_oComponentMAP().get_LActualDBValue("ID_ADRESSE", true, true, null);
				if (lID_ADRESSE==null)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte zuerst die Basis-Adresse laden !")));
				}
				return oMV;
			}

			@Override
			protected MyE2_MessageVector isValid(String unformated)	throws myException
			{
				return null;
			}
			
		});
		
		this.get_oTextForAnzeige().setFont(new E2_FontBold());

	}

	
	
	
	private class ownSearchBlock extends XX_SearchBlock
	{

		
		MyE2_DB_MaskSearchField oSearchFieldBaseAdress = null;
		
		public ownSearchBlock(MyE2_DB_MaskSearchField SearchAdressField)
		{
			super();
			oSearchFieldBaseAdress = SearchAdressField;
		}

		public Vector<XX_Button4SearchResultList[]> get_vResultButtons(String SearchText) throws myException
		{
			Vector<XX_Button4SearchResultList[]> vRueck = new Vector<XX_Button4SearchResultList[]>();
			
			String cID_ADRESS_BASE = this.oSearchFieldBaseAdress.get_oTextFieldForSearchInput().getText();
			if (!bibALL.isEmpty(cID_ADRESS_BASE))
			{
				cID_ADRESS_BASE = bibALL.ReplaceTeilString(cID_ADRESS_BASE,".","");
				if (bibALL.isInteger(cID_ADRESS_BASE))
				{
					
					
					//2013-05-28: gleichstellung mit FU_MASK_DB_SEARCH_AdresseLager 
					
					//MyAdress oAdress = new MyAdress(cID_ADRESS_BASE,bibE2.get_CurrSession());
					
					//VectorDataBaseQuery vLief = MyAdress.find_Lieferadressen(cID_ADRESS_BASE);
					
					//MyAdress oAdress = new MyAdress(cID_ADRESS_BASE,bibE2.get_CurrSession());
					RECORD_ADRESSE  		recAdresse = new RECORD_ADRESSE(cID_ADRESS_BASE);
					
					String cQuery = "SELECT  JT_ADRESSE.*, JT_LIEFERADRESSE."+_DB.LIEFERADRESSE$ID_ADRESSE_FREMDWARE +
										" FROM "+
											bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_LIEFERADRESSE "+
									    " WHERE " +
									    	" JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER " +
									    " AND "+
									    	" JT_LIEFERADRESSE.ID_ADRESSE_BASIS="+cID_ADRESS_BASE+
									    " ORDER BY JT_ADRESSE.ORT";

//					" AND NVL(JT_ADRESSE.AKTIV,'N')='Y' "+                                //aenderung 2011-01-28: nur aktive lieferadressen suchen	
					
					RECLIST_ADRESSE 		reclistAdress1 = new RECLIST_ADRESSE(cQuery);
					RECLIST_ADRESSE 		reclistAdressen = new RECLIST_ADRESSE();
					reclistAdressen.ADD(recAdresse, false);
					reclistAdressen.putAll(reclistAdress1, false);

					
					if (reclistAdressen.size() == 0)
					{
						throw new myException("FUO_MASK_DB_SEARCH_AdresseLager:ownSearchBlock:Error finding <LIEFERADRESSE>");
					}
					else
					{
						//2011-09-15: vereinheitlichte lagerorte
						vRueck.addAll(new __FU_ListenBuilder_ButtonListSearchLagerOrt(reclistAdressen).get_vButtonArrays());
						
					}

					
				}
				else
				{
					FUO_MASK_DB_SEARCH_AdresseLager.this.prepare_ContentForNew(true);
					throw new myExceptionForUser(new MyE2_String("Bitte zuerst eine Kunden-Adresse suchen").CTrans());
				}
			}
			else
			{
				FUO_MASK_DB_SEARCH_AdresseLager.this.prepare_ContentForNew(true);
			}
			
			return vRueck;
		}


		
		@Override
		public E2_BasicModuleContainer get_ContainerForShowResults()
		{
			
			return new ownBasicModuleContainer();
		}
		
		private class ownBasicModuleContainer extends E2_BasicModuleContainer
		{
			
		}

	}
	
	
	
	
	
	private class ownMaskSettings extends XX_MaskActionAfterFound
	{
		// private String cFieldsToFill = "|A_NAME1|A_NAME2|A_NAME3|A_STRASSE|A_HAUSNUMMER|A_PLZ|A_ORT|A_ORTZUSATZ|TEL_ABNEHMER|OEFFNUNGSZEITEN_ABN|FAX_ABNEHMER|";
		
		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException
		{
			FUO_MASK_DB_SEARCH_AdresseLager oThis = FUO_MASK_DB_SEARCH_AdresseLager.this;

			//2013-05-28: zeige fremdwarenlager auf der maske an
			new ownFUO_ZeigeAufMaske_FremdwarenLager((MyE2_Grid)oThis.EXT().get_oComponentMAP().get__Comp(FUO__CONST.HASH_MASK_COMP_FREMDWARENLAGER),false);
			
			if (bAfterAction)
			{
				
				MyAdress oAdress = new MyAdress(cMaskValue, true);
				
				E2_ComponentMAP oMap = oThis.EXT().get_oComponentMAP();
				HashMap<String, MyE2IF__Component> hmRealCompents = oMap.get_hmRealComponents();
			
				new FU__LoadAdressFieldsInMaskAfterSearchAdress(FUO_MASK_DB_SEARCH_AdresseLager.this.EK_VK,
						FU__LoadAdressFieldsInMaskAfterSearchAdress.get_UebersetzerMapFuer_Zusatzort_Seite(),false).set_MaskValues(hmRealCompents, oAdress);
				
				//die Buchungsinfos leermachen
				oMap.get__DBComp("MANUELL_PREIS").set_cActualMaskValue("N");
				oMap.get__DBComp("EINZELPREIS").set_cActualMaskValue("");
				oMap.get__DBComp("STEUERSATZ").set_cActualMaskValue("");
				oMap.get__DBComp("EU_STEUER_VERMERK").set_cActualMaskValue("");
				// ---------------------------
				//2013-09-26: auch die id_tax loeschen
				oMap.get__DBComp(_DB.VPOS_TPA_FUHRE_ORT$ID_TAX).set_cActualMaskValue("");

				
				if (bIS_PrimaryCall)
				{
					new FU__MaskSettingsAfterInput__Plausibilitaet(oThis);
					
					//2014-05-23: auch hier nach einem EU-Handelsvertrag fragen
					new FU__Popup_Warne_kein_Handelsvertrag_Bei_Lagerort(
							FUO_MASK_DB_SEARCH_AdresseLager.this,
							FUO_MASK_DB_SEARCH_AdresseLager.this.EK_VK,
							oAdress.get_cID_ADRESSE_orig(),
							(MyE2_DB_CheckBox)oMap.get_Comp("OHNE_AVV_VERTRAG_CHECK"),
							bIS_PrimaryCall, 
							true);

					
				}
			}
		}
	}

	
	private class ownFUO_ZeigeAufMaske_FremdwarenLager extends FU_ZeigeAufMaske_FremdwarenLager {
		public ownFUO_ZeigeAufMaske_FremdwarenLager(MyE2_Grid ogridZurAnzeige, boolean bOnlyClearAnzeige)	throws myException {
			super(ogridZurAnzeige, bOnlyClearAnzeige);
		}
		@Override
		public String get_cID_LAGER_AUS_MASKE() throws myException {
			FUO_MASK_DB_SEARCH_AdresseLager oThis = FUO_MASK_DB_SEARCH_AdresseLager.this;
			
			long lRueck = oThis.EXT().get_oComponentMAP().get_LActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE_LAGER, -1l, -1l);
			if (lRueck == -1) {
				return "";
			} else {
				return ""+lRueck;
			}
		}
		@Override
		public boolean get_bLadestationIstVonMandant() throws myException {
			FUO_MASK_DB_SEARCH_AdresseLager oThis = FUO_MASK_DB_SEARCH_AdresseLager.this;
			long lID_ADRESSE = oThis.EXT().get_oComponentMAP().get_LActualDBValue(_DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE, -1l, -1l);
			if (lID_ADRESSE != -1l && bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_lValue(-2l).longValue()==lID_ADRESSE) {
				return true;
			} else {
				return false;
			}
		}

	}

}

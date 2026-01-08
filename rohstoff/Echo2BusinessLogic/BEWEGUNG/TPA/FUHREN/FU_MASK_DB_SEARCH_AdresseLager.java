package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.FieldValidator_FieldDisabled;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_SearchBlock;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.FU_ZeigeAufMaske_FremdwarenLager;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.utils.MyAdress;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class FU_MASK_DB_SEARCH_AdresseLager extends DB_SEARCH_Adress
{
	
	private String cEK_VK = null;
	
	public FU_MASK_DB_SEARCH_AdresseLager(SQLField osqlField, String EK_VK) throws myException
	{

		super(osqlField, null, DB_SEARCH_Adress.cAdressSearchQuery4LabelLagerAdress, E2_INSETS.I_0_0_1_0);

		this.cEK_VK = EK_VK;
		
		this.set_oSeachBlock(new ownSearchBlock());
		this.get_oTextFieldForSearchInput().EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskSettings());
		this.set_oPopupWidth(new Extent(600));
		this.get_oTextForAnzeige().setFont(new E2_FontBold());
		this.get_oTextFieldForSearchInput().set_iWidthPixel(60);

		//2011-02-05: label-anzeige
		this.set_bLabel4AnzeigeStattText4Anzeige(true);
		this.get_oLabel4Anzeige().get_oLabel().setFont(new E2_FontPlain());
		this.get_oLabel4Anzeige().set_iWidth(220);


		this.add_ValidatorStartSearch(new XX_ActionValidator()
		{
			@Override
			public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
			{
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				
				//nachsehen, ob eine Firma angegeben ist
				FU_MASK_DB_SEARCH_AdresseLager oThis = FU_MASK_DB_SEARCH_AdresseLager.this;
				String cID_Adresse = null;
				if (oThis.cEK_VK.equals("EK"))
				{
					cID_Adresse = ""+oThis.EXT().get_oComponentMAP().get_LActualDBValue("ID_ADRESSE_START", true,true,new Long(0)).longValue();
				}
				else
				{
					cID_Adresse = ""+oThis.EXT().get_oComponentMAP().get_LActualDBValue("ID_ADRESSE_ZIEL", true,true,new Long(0)).longValue();
				}
					   
				if (cID_Adresse.equals("0"))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst eine Firma suchen !"));
				}
				
				return oMV;
			}

			@Override
			protected MyE2_MessageVector isValid(String unformated)	throws myException
			{
				return null;
			}
			
		});
		
		
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

	private class ownSearchBlock extends XX_SearchBlock
	{

		
		public ownSearchBlock()
		{
			super();
		}

		public Vector<XX_Button4SearchResultList[]> get_vResultButtons(String SearchText) throws myException
		{
			Vector<XX_Button4SearchResultList[]>  vRueck = new Vector<XX_Button4SearchResultList[]> ();

			FU_MASK_DB_SEARCH_AdresseLager oThis = FU_MASK_DB_SEARCH_AdresseLager.this;
			
			long lID_ADRESSE = oThis.EXT().get_oComponentMAP().get_LActualDBValue(
					        oThis.cEK_VK.equals("EK")?"ID_ADRESSE_START":"ID_ADRESSE_ZIEL", true, true, new Long(-1)).longValue();
			
			if (lID_ADRESSE>0)
			{
				String cID_ADRESS_BASE = ""+lID_ADRESSE;
				
				cID_ADRESS_BASE = bibALL.ReplaceTeilString(cID_ADRESS_BASE,".","");
				if (bibALL.isInteger(cID_ADRESS_BASE))
				{
					
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
									    " AND JT_ADRESSE.SONDERLAGER IS NULL "+	
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
					FU_MASK_DB_SEARCH_AdresseLager.this.prepare_ContentForNew(true);
					throw new myExceptionForUser(new MyE2_String("Bitte zuerst eine Basis-Adresse suchen").CTrans());
				}
			}
			else
			{
				FU_MASK_DB_SEARCH_AdresseLager.this.prepare_ContentForNew(true);
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
			FU_MASK_DB_SEARCH_AdresseLager oThis = FU_MASK_DB_SEARCH_AdresseLager.this;

			new ownFU_ZeigeAufMaske_FremdwarenLager(oThis.cEK_VK.equals("EK")?
					(MyE2_Grid)oThis.EXT().get_oComponentMAP().get__Comp(FU___CONST.HASH_MASK_COMP_FREMDWARENLAGER_LADESEITE):
						(MyE2_Grid)oThis.EXT().get_oComponentMAP().get__Comp(FU___CONST.HASH_MASK_COMP_FREMDWARENLAGER_ABLADESEITE)
					,false);
			
			
			if (!bAfterAction) return;


			MyAdress oAdress = new MyAdress(cMaskValue, true);
			
			E2_ComponentMAP oMap = FU_MASK_DB_SEARCH_AdresseLager.this.EXT().get_oComponentMAP();
			HashMap<String, MyE2IF__Component> hmRealCompents = oMap.get_hmRealComponents();
		
			if (oThis.cEK_VK.equals("EK"))
			{
				new FU__LoadAdressFieldsInMaskAfterSearchAdress(oThis.cEK_VK,
						FU__LoadAdressFieldsInMaskAfterSearchAdress.get_UebersetzerMapFuer_EK_Seite(),false).set_MaskValues(hmRealCompents, oAdress);
			}
			else
			{
				new FU__LoadAdressFieldsInMaskAfterSearchAdress(oThis.cEK_VK,
						FU__LoadAdressFieldsInMaskAfterSearchAdress.get_UebersetzerMapFuer_VK_Seite(),false).set_MaskValues(hmRealCompents, oAdress);
			}

			//die Buchungsinfos leermachen
			oMap.get__DBComp("MANUELL_PREIS_"+		oThis.cEK_VK).set_cActualMaskValue("N");
			oMap.get__DBComp("EINZELPREIS_"+		oThis.cEK_VK).set_cActualMaskValue("");
			oMap.get__DBComp("STEUERSATZ_"+			oThis.cEK_VK).set_cActualMaskValue("");
			oMap.get__DBComp("EU_STEUER_VERMERK_"+	oThis.cEK_VK).set_cActualMaskValue("");
			// ---------------------------
			//2013-09-26: die neue steuerid auch leermachen
			oMap.get__DBComp(oThis.cEK_VK.equals("EK")?_DB.VPOS_TPA_FUHRE$ID_TAX_EK:_DB.VPOS_TPA_FUHRE$ID_TAX_VK).set_cActualMaskValue("");

			
			if (bIS_PrimaryCall)
			{
				new FU__MaskSettingsAfterInput__Plausibilitaet(oThis);
				
				//2014-05-23: auch hier nach einem EU-Handelsvertrag fragen
				new FU__Popup_Warne_kein_Handelsvertrag_Bei_Lagerort(
						FU_MASK_DB_SEARCH_AdresseLager.this,
						oThis.cEK_VK,
						oAdress.get_cID_ADRESSE_orig(),
						(MyE2_DB_CheckBox)oMap.get_Comp("OHNE_AVV_VERTRAG_CHECK"),
						bIS_PrimaryCall, 
						true);
				
			}
			
		}
	}

	
	
	private class ownFU_ZeigeAufMaske_FremdwarenLager extends FU_ZeigeAufMaske_FremdwarenLager {
		public ownFU_ZeigeAufMaske_FremdwarenLager(MyE2_Grid ogridZurAnzeige,  boolean bOnlyClearAdresse)	throws myException {
			super(ogridZurAnzeige, bOnlyClearAdresse);
		}
		@Override
		public String get_cID_LAGER_AUS_MASKE() throws myException {
			FU_MASK_DB_SEARCH_AdresseLager oThis = FU_MASK_DB_SEARCH_AdresseLager.this;
			
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
			FU_MASK_DB_SEARCH_AdresseLager oThis = FU_MASK_DB_SEARCH_AdresseLager.this;
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

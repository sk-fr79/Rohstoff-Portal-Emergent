package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.Echo2.components.MaskSearchField.XX_MaskActionAfterFoundNonDB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON_TRAKT;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_ActionAfterFound;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_STANDARD_Element;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_VALIDATOR_AllBeforeIsFilled;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputDatum;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_PreisEingabe;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAdressFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAngebotsFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchSortenFields;

public class FUS_SearchKontrakte extends MyE2_MaskSearchField implements __FUS_STANDARD_Element
{

	public static String	cSQL4Label	= "SELECT NVL(JT_VKOPF_KON.BUCHUNGSNUMMER,'??')||'-'||NVL(JT_VPOS_KON.POSITIONSNUMMER,'')||' '||"
												+ "'['||NVL(JT_VPOS_KON.ANR1,'')||'-'||NVL(JT_VPOS_KON.ANR2,'')||'] '||NVL(JT_VKOPF_KON.NAME1,'')||' '||NVL(JT_VKOPF_KON.ORT,'')||' - '||"
												+ " TO_CHAR(JT_VPOS_KON.ANZAHL,'999G999G999', 'NLS_NUMERIC_CHARACTERS='',.''')||' '||NVL(JT_VPOS_KON.EINHEITKURZ,'??') ||' '|| NVL(JT_VPOS_KON.ARTBEZ1,'')||''||'   ('||"
												+ "    NVL(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'dd.mm.'),'-')||' - '||" + "    NVL(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_BIS,'dd.mm.'),'-')||')'" + " FROM "
												+ "    JT_VKOPF_KON, JT_VPOS_KON,JT_VPOS_KON_TRAKT " + " WHERE " + "    JT_VKOPF_KON.ID_VKOPF_KON   = JT_VPOS_KON.ID_VKOPF_KON AND JT_VPOS_KON.ID_VPOS_KON = JT_VPOS_KON_TRAKT.ID_VPOS_KON AND "
												+ "    JT_VPOS_KON.ID_VPOS_KON=#WERT#";

	private String			cEK_VK		= null;

	public FUS_SearchKontrakte(String EK_VK) throws myException
	{
		super(new FUS_SearchKontrakte__SearchBlock(), FUS_SearchKontrakte.cSQL4Label, E2_INSETS.I_0_0_0_0, true);

		((FUS_SearchKontrakte__SearchBlock) this.get_oSeachBlock()).set_oFUS_SearchKontrakte(this);

		this.get_oButtonErase().__setImages(E2_ResourceIcon.get_RI("eraser_xxs.png"), true);
		this.set_bShowSearchButtonLeft(true);

		this.cEK_VK = EK_VK;

		this.set_bLabel4AnzeigeStattText4Anzeige(true);
		this.set_oPopupWidth(new Extent(900));
		this.set_oPopupHigh(new Extent(600));

		this.get_oTextFieldForSearchInput().set_iWidthPixel(80);

		this.get_ValidatorStartSearch().add(new __FUS_VALIDATOR_AllBeforeIsFilled(this));

		this.set_oMaskActionAfterMaskValueIsFound(new XX_MaskActionAfterFoundNonDB()
		{
			@Override
			public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_MaskSearchField oSearchField, boolean bAfterAction) throws myException
			{
				MyLong ID_VPOS_KON = new MyLong(cMaskValue);
				
				if (!ID_VPOS_KON.get_cErrorCODE().equals(MyLong.ALL_OK))
				{
					throw new myException(this, "Search-Result is not allowed !!!");
				}

				
				new __FUS_ActionAfterFound(FUS_SearchKontrakte.this);
				FUS_SearchKontrakte.this.do_afterFieldWasFilled(""+ID_VPOS_KON.get_lValue());
				
				
				
				
			}
		});
		
		
		this.get_oButtonErase().add_oActionAgent(new actionAgentAfterRadiergummi());

	}

	
	public class actionAgentAfterRadiergummi extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			new _SEARCH_PreisEingabe().get_found_PreisFeld(FUS_SearchKontrakte.this.get_bIS_EK()).set_bEnabled_For_Edit(true);
			new _SEARCH_PreisEingabe().get_found_PreisFeld(FUS_SearchKontrakte.this.get_bIS_EK()).setText("");
		}
	}



	public String get_cEK_VK()
	{
		return cEK_VK;
	}

	public boolean get_bIS_EK()
	{
		return (this.cEK_VK.equals("EK"));
	}

	@Override
	public void mark_MUST_BE_Filled(boolean bMarked) throws myException
	{
		boolean bIsEnabled = this.get_oTextFieldForSearchInput().isEnabled();
		this.get_oTextFieldForSearchInput().setStyle(this.get_oTextFieldForSearchInput().EXT().get_STYLE_FACTORY().get_Style(bIsEnabled,true,bMarked));
	}

	@Override
	public MyE2_String get_InfoMessage() throws myException
	{
		return new MyE2_String(this.cEK_VK.equals("EK") ? "Bitte EK-Kontrakt ausfüllen" : "Bitte die VK-Kontrakt  ausfüllen");
	}

	@Override
	public boolean get_bIsCorrectFilled(boolean bMarkWhenFalse) throws myException
	{
		this.mark_MUST_BE_Filled(false);

		
		MyInteger intInput = new MyInteger(this.get_oTextFieldForSearchInput().getText());

		if (intInput.get_cErrorCODE().equals(MyInteger.ALL_OK))
		{
			if (this.get_bValueWasSearched())
			{
				return true;
			}
		}
		
		if (bMarkWhenFalse) 	this.mark_MUST_BE_Filled(true);

		return false;
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGefuelltSeinMuessen() throws myException
	{
		Vector<__FUS_STANDARD_Element> vRueck = new Vector<__FUS_STANDARD_Element>();

		vRueck.add(new _SEARCH_InputDatum().get_Found_FUS_InputDatum());
		if (this.get_bIS_EK())
		{
			vRueck.add(new _SEARCH_SearchAdressFields().get_Found_EK_AdressField());
			vRueck.add(new _SEARCH_SearchAdressFields().get_Found_VK_AdressField());
			
		}
		else
		{
			vRueck.add(new _SEARCH_SearchAdressFields().get_Found_EK_AdressField());
			vRueck.add(new _SEARCH_SearchAdressFields().get_Found_VK_AdressField());
		}

		return vRueck;
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGeleertWerden() throws myException
	{
		Vector<__FUS_STANDARD_Element> vRueck = new Vector<__FUS_STANDARD_Element>();

		if (this.get_bIS_EK())
		{
			vRueck.add(new _SEARCH_SearchSortenFields().get_Found_EK_SortenField());
			vRueck.add(new _SEARCH_SearchAngebotsFields().get_Found_EK_AngebotField());
			vRueck.add(new _SEARCH_PreisEingabe().get_found_PreisFeld_EK());

		}
		else
		{
			vRueck.add(new _SEARCH_SearchSortenFields().get_Found_VK_SortenField());
			vRueck.add(new _SEARCH_SearchAngebotsFields().get_Found_VK_AngebotField());
			vRueck.add(new _SEARCH_PreisEingabe().get_found_PreisFeld_VK());
		}

		return vRueck;
	}

	@Override
	public void clean__Field() throws myException
	{
		this.clean();
	}

	public RECORD_VPOS_KON get_Actual_RECORD_VPOS_KON() throws myException
	{
		RECORD_VPOS_KON recRueck = null;

		if (this.get_bIsCorrectFilled(false))
		{
			MyInteger intID_Kontrakt = new MyInteger(this.get_oTextFieldForSearchInput().getText());

			if (intID_Kontrakt.get_cErrorCODE().equals(MyInteger.ALL_OK))
			{
				recRueck = new RECORD_VPOS_KON(intID_Kontrakt.get_iValue());
			}
		}
		return recRueck;
	}

	public RECORD_VPOS_KON_TRAKT get_Actual_RECORD_VPOS_KON_TRAKT() throws myException
	{
		RECORD_VPOS_KON recVPOS_KON = this.get_Actual_RECORD_VPOS_KON();

		RECORD_VPOS_KON_TRAKT recRueck = null;

		if (recVPOS_KON != null)
		{
			recRueck = recVPOS_KON.get_DOWN_RECORD_LIST_VPOS_KON_TRAKT_id_vpos_kon().get(0);
		}
		return recRueck;
	}

	public RECORD_VKOPF_KON get_Actual_RECORD_VKOPF_KON() throws myException
	{
		RECORD_VPOS_KON recVPOS_KON = this.get_Actual_RECORD_VPOS_KON();

		RECORD_VKOPF_KON recRueck = null;

		if (recVPOS_KON != null)
		{
			recRueck = recVPOS_KON.get_UP_RECORD_VKOPF_KON_id_vkopf_kon();
		}
		return recRueck;
	}

	@Override
	public Boolean get_IS_EK() throws myException
	{
		return new Boolean(this.get_bIS_EK());
	}

	
	@Override
	public E2_BasicModuleContainer get_ownContainer() throws myException
	{
		return new ownE2_Container();
	}

	private class ownE2_Container extends E2_BasicModuleContainer
	{
		public ownE2_Container()
		{
			super();
		}
	}

	
	@Override
	public void do_afterFieldWasFilled(String cID) throws myException
	{
		RECORD_VPOS_KON  recKontrakt = new RECORD_VPOS_KON(cID);

		if (this.get_bIS_EK())
		{
			new _SEARCH_SearchSortenFields().get_Found_EK_SortenField().fill_MaskText_And_Label(recKontrakt.get_ID_ARTIKEL_BEZ_cUF());
			//jetzt die abladesorte definieren, wenn die abladestelle mandant ist
			new _SEARCH_SearchSortenFields().get_Found_EK_SortenField().do_afterFieldWasFilled(recKontrakt.get_ID_ARTIKEL_BEZ_cUF_NN(""));
		}
		else
		{
			new _SEARCH_SearchSortenFields().get_Found_VK_SortenField().fill_MaskText_And_Label(this.get_Actual_RECORD_VPOS_KON().get_ID_ARTIKEL_BEZ_cUF());
		}


		
		new _SEARCH_PreisEingabe().get_found_PreisFeld(FUS_SearchKontrakte.this.get_bIS_EK()).set_bEnabled_For_Edit(false);
		
		//dann den jeweiligen preis eintragen
		new _SEARCH_PreisEingabe().get_found_PreisFeld(FUS_SearchKontrakte.this.get_bIS_EK()).setText(recKontrakt.get_EINZELPREIS_cF_NN("0,00"));
		
		
		
	}
	

}

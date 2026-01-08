package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.Echo2.components.MaskSearchField.XX_DoSomeThingAfterComponentIsFilled;
import panter.gmbh.Echo2.components.MaskSearchField.XX_MaskActionAfterFoundNonDB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_ActionAfterFound;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_STANDARD_Element;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_VALIDATOR_AllBeforeIsFilled;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_AnzeigeMengenEinheit;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputDatum;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_PreisEingabe;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAdressFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAngebotsFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchKontraktFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchSortenFields;

public class FUS_SearchArtikelBez extends MyE2_MaskSearchField implements __FUS_STANDARD_Element
{

	public static  String cSQL4Label = 
		"SELECT  NVL(ANR1,'-') || ' -- ' ||  NVL(ANR2,'-') || ' -- ' || trim(  NVL(JT_ARTIKEL_BEZ.ARTBEZ1,'-')) from " + 
		 bibE2.cTO() + ".JT_ARTIKEL_BEZ INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL ON (JT_ARTIKEL.ID_ARTIKEL=JT_ARTIKEL_BEZ.ID_ARTIKEL) WHERE " +
		 		" JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ=#WERT#";
	

	private String cEK_VK = null;
	

	public FUS_SearchArtikelBez(String EK_VK) throws myException
	{
		super(new FUS_SearchArtikelBez__SearchBlock(), FUS_SearchArtikelBez.cSQL4Label, E2_INSETS.I_0_0_0_0, true);
		
		((FUS_SearchArtikelBez__SearchBlock)this.get_oSeachBlock()).set_oFUS_SearchArtikelBez(this);
		
		this.get_oButtonErase().__setImages(E2_ResourceIcon.get_RI("eraser_xxs.png"), true);
		this.set_bShowSearchButtonLeft(true);

		
		this.cEK_VK = EK_VK;
		
		if (this.get_bIS_EK())
		{
			this.set_oDoSomeThingAfterComponentIsFilled(new Schreibe_Einheit_nach_Sortenfuellen());
		}
		
		this.set_bLabel4AnzeigeStattText4Anzeige(true);
		this.set_oPopupWidth(new Extent(900));
		this.set_oPopupHigh(new Extent(600));
		
		this.get_oTextFieldForSearchInput().set_iWidthPixel(80);

		
		this.get_ValidatorStartSearch().add(new __FUS_VALIDATOR_AllBeforeIsFilled(this));
		
		this.set_oMaskActionAfterMaskValueIsFound(new XX_MaskActionAfterFoundNonDB()
		{
			@Override
			public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue,
					MyE2_MaskSearchField oSearchField, boolean bAfterAction)
					throws myException
			{
				MyLong lID = new MyLong(cMaskValue);
				if (!lID.get_cErrorCODE().equals(MyLong.ALL_OK))
				{
					throw new myException(this,"value is not allowed ...");
				}

				new __FUS_ActionAfterFound(FUS_SearchArtikelBez.this);
				
				FUS_SearchArtikelBez.this.do_afterFieldWasFilled(""+lID.get_lValue());
				
			}
		});
	}

	
	private class Schreibe_Einheit_nach_Sortenfuellen extends XX_DoSomeThingAfterComponentIsFilled
	{
		@Override
		public void doSomeThingAfterComponentIsFilled(MyE2_MaskSearchField ownSearchField, String cActualMaskValue) throws myException
		{
			new _SEARCH_AnzeigeMengenEinheit().get_Found_FUS_AnzeigeMengenEinheit().setText("");

			RECORD_ARTIKEL  recArtikel = new _SEARCH_SearchSortenFields().get_Found_EK_SortenField().get_ActualRecArtikel();
			if (recArtikel != null)
			{
				new _SEARCH_AnzeigeMengenEinheit().get_Found_FUS_AnzeigeMengenEinheit().setText(recArtikel.get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cUF());
			}
			
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
		this.get_oTextFieldForSearchInput().setBackground(new E2_ColorEditBackground());
		
		if (bMarked)
		{
			this.get_oTextFieldForSearchInput().setBackground(new E2_ColorHelpBackground());
		}
	}

	@Override
	public MyE2_String get_InfoMessage() throws myException
	{
		return new MyE2_String(this.cEK_VK.equals("EK")?"Bitte die Ladesorte ausfüllen":"Bitte die Abladesorte ausfüllen");
	}

	
	

	@Override
	public boolean get_bIsCorrectFilled(boolean bMarkWhenFalse) throws myException
	{
		this.mark_MUST_BE_Filled(false);

		
		MyInteger  intInput = new MyInteger(this.get_oTextFieldForSearchInput().getText());
		
		if (intInput.get_cErrorCODE().equals(MyInteger.ALL_OK))
		{
			if (this.get_bValueWasSearched())
			{
				return true;
			}
		}
		if (bMarkWhenFalse) this.mark_MUST_BE_Filled(true);

		return false;
	}
	
	
	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGefuelltSeinMuessen() throws myException
	{
		Vector<__FUS_STANDARD_Element> vRueck = new Vector<__FUS_STANDARD_Element>();
		
		if (this.get_bIS_EK())
		{
			vRueck.add(new _SEARCH_InputDatum().get_Found_FUS_InputDatum());
			vRueck.add(new _SEARCH_SearchAdressFields().get_Found_EK_AdressField());
			vRueck.add(new _SEARCH_SearchAdressFields().get_Found_VK_AdressField());
		}
		else
		{
			vRueck.add(new _SEARCH_InputDatum().get_Found_FUS_InputDatum());
			vRueck.add(new _SEARCH_SearchAdressFields().get_Found_EK_AdressField());
			vRueck.add(new _SEARCH_SearchAdressFields().get_Found_VK_AdressField());
			vRueck.add(new _SEARCH_SearchSortenFields().get_Found_EK_SortenField());
		}

		return vRueck;
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGeleertWerden() throws myException
	{
		Vector<__FUS_STANDARD_Element> vRueck = new Vector<__FUS_STANDARD_Element>();
		
		if (this.get_bIS_EK())
		{
			vRueck.add(new _SEARCH_SearchSortenFields().get_Found_VK_SortenField());
			vRueck.add(new _SEARCH_SearchKontraktFields().get_Found_EK_KontraktField());
			vRueck.add(new _SEARCH_SearchAngebotsFields().get_Found_EK_AngebotField());

			vRueck.add(new _SEARCH_SearchKontraktFields().get_Found_VK_KontraktField());
			vRueck.add(new _SEARCH_SearchAngebotsFields().get_Found_VK_AngebotField());
			
		}
		else
		{
			vRueck.add(new _SEARCH_SearchKontraktFields().get_Found_VK_KontraktField());
			vRueck.add(new _SEARCH_SearchAngebotsFields().get_Found_VK_AngebotField());
		}

		return vRueck;
	}

	@Override
	public void clean__Field() throws myException
	{
		this.clean();
	}


	
	public RECORD_ARTIKEL_BEZ get_ActualRecArtBez() throws myException
	{
		RECORD_ARTIKEL_BEZ recRueck = null;
		
		if (this.get_bIsCorrectFilled(false))
		{
			MyInteger  intID_ArtBez = new MyInteger(this.get_oTextFieldForSearchInput().getText());
			
			if (intID_ArtBez.get_cErrorCODE().equals(MyInteger.ALL_OK))
			{
				recRueck = new RECORD_ARTIKEL_BEZ(intID_ArtBez.get_iValue());
			}
		}
		return recRueck;
	}
	
	public RECORD_ARTIKEL get_ActualRecArtikel() throws myException
	{
		RECORD_ARTIKEL 		recRueck 	= null;
		
		RECORD_ARTIKEL_BEZ  recArtBez 	= this.get_ActualRecArtBez();
		
		if ( recArtBez != null)
		{
			recRueck = recArtBez.get_UP_RECORD_ARTIKEL_id_artikel();
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
	public void do_afterFieldWasFilled(String cFillValue) throws myException
	{

		FUS_SearchAdresse    	oLadeAdresse  =  new _SEARCH_SearchAdressFields().get_Found_EK_AdressField();

		
		FUS_SearchArtikelBez 	oAbladeSorte    =  new _SEARCH_SearchSortenFields().get_Found_VK_SortenField();
		FUS_SearchAdresse    	oAbladeAdresse  =  new _SEARCH_SearchAdressFields().get_Found_VK_AdressField();
		
		RECORD_ADRESSE       	recStartAdresse = 		oLadeAdresse.get_ActualRecHauptAdresse();
		RECORD_ADRESSE       	recZielAdresse = 		oAbladeAdresse.get_ActualRecHauptAdresse();
		RECORD_ARTIKEL_BEZ  	recEigeneSortenBez = 	this.get_ActualRecArtBez();
		RECORD_ARTIKEL 		  	recEigene_Sorte = 		this.get_ActualRecArtikel();

		
		if (this.get_bIS_EK())     //dann die VK-Sorte setzen, je nach abladeadresse
		{
			

			if (recZielAdresse != null && recZielAdresse.get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))
			{
				
				if (recEigeneSortenBez!=null)
				{
					//dann die aktuelle erste sortenbezeichnung holen
					RECORD_ARTIKEL  recArtikel = recEigeneSortenBez.get_UP_RECORD_ARTIKEL_id_artikel();
					
					RECLIST_ARTIKEL_BEZ  reclistArtbez = recArtikel.get_DOWN_RECORD_LIST_ARTIKEL_BEZ_id_artikel("NVL(AKTIV,'N')='Y'", "ANR2", true);
					
					if (reclistArtbez.get_vKeyValues().size()>0)
					{
						oAbladeSorte.fill_MaskText_And_Label(reclistArtbez.get(0).get_ID_ARTIKEL_BEZ_cUF());
					}
				}
				
			}
			else if (recZielAdresse != null && !recZielAdresse.get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--")))
			{
				if (recEigene_Sorte!=null)
				{
				
					//ist die zieladresse eine fremde adresse, dann bei den dortigen kundenspezifischen artikeln schauen, ob es eine dieser hauptsorte gibt und die erste anzeigen
					RECLIST_ARTIKELBEZ_LIEF  recListArtikelbez_lief = recZielAdresse.get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse(RECORD_ARTIKELBEZ_LIEF.FIELD__ARTBEZ_TYP+"='VK'", null, true);
					
					RECLIST_ARTIKEL_BEZ  	reclistBEZ_Sammler = 		new RECLIST_ARTIKEL_BEZ();
					
					if (recListArtikelbez_lief.get_vKeyValues().size()>0)
					{
						for (int i=0;i<recListArtikelbez_lief.get_vKeyValues().size();i++)
						{
							RECORD_ARTIKEL_BEZ  recArtBez = recListArtikelbez_lief.get(i).get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez();
							RECORD_ARTIKEL     	recArtikel = recArtBez.get_UP_RECORD_ARTIKEL_id_artikel();
							
							if (recEigene_Sorte.get_ANR1_cUF_NN("--").equals(recArtikel.get_ANR1_cUF_NN("---")))
							{
								reclistBEZ_Sammler.ADD(recArtBez, true);
							}
							
							if (reclistBEZ_Sammler.get_vKeyValues().size()>0)
							{
								Vector<RECORD_ARTIKEL_BEZ> vSorted= reclistBEZ_Sammler.GET_SORTED_VECTOR(bibALL.get_Vector("ANR2"), true);
							
								oAbladeSorte.fill_MaskText_And_Label(vSorted.get(0).get_ID_ARTIKEL_BEZ_cUF());
							}
						}
					}
				}
				
			}
			
		}
		
		
		
		//2012-01-05: wenn die artikelbezeichung neu geladen wird, dann muss das preisfeld geoeffnet und geloescht werden
		if (FUS_SearchArtikelBez.this.cEK_VK.equals("EK"))
		{
			//bei fremden adressen muss das preisfeld aufgehen, da bei diesem suchfeld ein evtl. vorhandenes angebot geloescht wird
			if (recStartAdresse != null && (!recStartAdresse.get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--"))))
			{
				new _SEARCH_PreisEingabe().get_found_PreisFeld(true).set_bEnabled_For_Edit(true);
				new _SEARCH_PreisEingabe().get_found_PreisFeld(true).setText("");
			}
		}
		else
		{
			//bei fremden adressen muss das preisfeld aufgehen, da bei diesem suchfeld ein evtl. vorhandenes angebot geloescht wird
			if (recZielAdresse != null && (!recZielAdresse.get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--"))))
			{
				new _SEARCH_PreisEingabe().get_found_PreisFeld(false).set_bEnabled_For_Edit(true);
				new _SEARCH_PreisEingabe().get_found_PreisFeld(false).setText("");
			}
		
		}
			

		
		
	}
	

}
 
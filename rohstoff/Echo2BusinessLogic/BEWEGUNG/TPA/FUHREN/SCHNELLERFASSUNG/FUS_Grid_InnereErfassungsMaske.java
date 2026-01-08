package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_Subgrid_4_Mask;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_AnzeigeMengenEinheit;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_Combo_Transportmittel;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_InputAnzahlContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_InputDatum;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_InputDatum2;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_InputLKW;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_InputLKW_Anhaenger;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_Mask_SHOW_SUM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_PreisEingabe;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_SearchAdresse;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_SearchAngebote;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_SearchArtikelBez;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_SearchKontrakte;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_WiegeMengeTextField;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS.FUS_Wiegeschein;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_Combo_Transportmittel;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputAnzahlContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputDatum;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputDatum2;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputLKW;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputLKW_Anhaenger;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_PreisEingabe;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAdressFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAngebotsFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchKontraktFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchSortenFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_WiegeMengeTextField;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_Wiegeschein;
import echopointng.Separator;

public class FUS_Grid_InnereErfassungsMaske extends MyE2_Grid
{
	
	private boolean   					  bVarianteFahrplan = false;

		
	public FUS_Grid_InnereErfassungsMaske(boolean  VarianteFuerFahrplan) throws myException
	{
		super(6, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.bVarianteFahrplan = VarianteFuerFahrplan;
		
		Insets oIN = new Insets(6,6,6,1);
		
		GridLayoutData  oGL1 = MyE2_Grid.LAYOUT_LEFT_CENTER(oIN,null,1,1);
		GridLayoutData  oGL2 = MyE2_Grid.LAYOUT_LEFT_CENTER(oIN,null,2,1);
		GridLayoutData  oGL3 = MyE2_Grid.LAYOUT_LEFT_CENTER(oIN,null,3,1);
//		GridLayoutData  oGL4 = MyE2_Grid.LAYOUT_LEFT_CENTER(oIN,null,4,1);
//		GridLayoutData  oGL5 = MyE2_Grid.LAYOUT_LEFT_CENTER(oIN,null,5,1);
		GridLayoutData  oGL6 = MyE2_Grid.LAYOUT_LEFT_CENTER(oIN,null,6,1);
		
		//0

		if (this.bVarianteFahrplan)  //fahrplanvariante hat ein zweites datum
		{
			E2_Subgrid_4_Mask  oSubGrid = new E2_Subgrid_4_Mask(
					bibVECTOR.get_Vector(new MyE2_String("Datum von"), new MyE2_String("Datum bis"), new MyE2_String("Transportmittel:"), new MyE2_String("Zahl Container"), new MyE2_String("KFZ-Kennz."), new MyE2_String("Anh.-Kennz.")),
					bibVECTOR.get_vCompVector(new FUS_InputDatum(), new FUS_InputDatum2(), new FUS_Combo_Transportmittel(), new FUS_InputAnzahlContainer(), new FUS_InputLKW(), new FUS_InputLKW_Anhaenger()),
					MyE2_Grid.LAYOUT_LEFT_CENTER(new Insets(2,1,20,1)),
					MyE2_Grid.LAYOUT_LEFT_CENTER(new Insets(2,1,20,1)),
					false,
					new Border(1, new E2_ColorDark(), Border.STYLE_SOLID), 
					false);
			
			this.add(oSubGrid,oGL6);
		}
		else     
		{
			this.add(new E2_ComponentGroupHorizontal(	0,
														new MyE2_Label(new MyE2_String("Datum:")),
														new FUS_InputDatum(),
														new MyE2_Label(new MyE2_String("Transportmittel:")),
														new FUS_Combo_Transportmittel(),
														new MyE2_Label(new MyE2_String("KFZ-Kennz.")),
														new FUS_InputLKW(),
														new MyE2_Label(new MyE2_String("/")),
														new FUS_InputLKW_Anhaenger(),
														E2_INSETS.I_0_0_20_0),oGL6);
		}
		this.setRowHeight(0, new Extent(20));
		
		//1
		this.add(new Separator(),6,E2_INSETS.I_0_0_0_0);
		
		//2
		this.add(new MyE2_Label(new MyE2_String("Ladestelle:")), oGL1);
		this.add(new FUS_SearchAdresse("EK"),oGL2);
		this.add(new MyE2_Label(new MyE2_String("Abladestelle:")), oGL1);
		this.add(new FUS_SearchAdresse("VK"),oGL2);
		this.setRowHeight(2, new Extent(50));
		
		//3
		this.add(new MyE2_Label(new MyE2_String("EK-Kontrakt:")), oGL1);
		this.add(new FUS_SearchKontrakte("EK"),oGL2);
		this.add(new MyE2_Label(new MyE2_String("VK-Kontrakt:")), oGL1);
		this.add(new FUS_SearchKontrakte("VK"),oGL2);
		this.setRowHeight(3, new Extent(30));

		//4
		this.add(new MyE2_Label(new MyE2_String("Abnahmeangebot:")), oGL1);
		this.add(new FUS_SearchAngebote("EK"),oGL2);
		this.add(new MyE2_Label(new MyE2_String("Verkaufsangebot:")), oGL1);
		this.add(new FUS_SearchAngebote("VK"),oGL2);
		this.setRowHeight(4, new Extent(30));

		//5
		this.add(new MyE2_Label(new MyE2_String("Ladesorte:")), oGL1);
		this.add(new FUS_SearchArtikelBez("EK"),oGL2);
		this.add(new MyE2_Label(new MyE2_String("Abladesorte:")), oGL1);
		this.add(new FUS_SearchArtikelBez("VK"),oGL2);
		this.setRowHeight(5, new Extent(30));

		//6
		this.add(new MyE2_Label(new MyE2_String("Wiegekarte:")), oGL1);
		this.add(new FUS_Wiegeschein("EK"),oGL2);
		this.add(new MyE2_Label(new MyE2_String("Wiegekarte:")), oGL1);
		this.add(new FUS_Wiegeschein("VK"),oGL2);
		//this.setRowHeight(5, new Extent(30));
		
		
		//7
		this.add(new Separator(),6,E2_INSETS.I_0_0_0_0);
		
		String cBasisWaehrung = S.isEmpty(bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_cUF_NN(""))?
								"-":
								new RECORD_MANDANT(bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()).get_UP_RECORD_WAEHRUNG_id_waehrung().get_WAEHRUNGSSYMBOL_cUF_NN("");
		
		this.add(new MyE2_Label(new MyE2_String("Preis Lieferant:")), oGL1);
		this.add(new E2_ComponentGroupHorizontal(0, new FUS_PreisEingabe("EK"), new MyE2_Label(cBasisWaehrung), E2_INSETS.I_0_0_5_0),oGL2);
		this.add(new MyE2_Label(new MyE2_String("Preis Abnehmer:")), oGL1);
		this.add(new E2_ComponentGroupHorizontal(0, new FUS_PreisEingabe("VK"), new MyE2_Label(cBasisWaehrung), E2_INSETS.I_0_0_5_0),oGL2);

		//8
		this.add(new Separator(),6,E2_INSETS.I_0_0_0_0);
		
		//7
		this.add(new MyE2_Label(new MyE2_String("Wiegemenge:")), oGL1);
		this.add(new E2_ComponentGroupHorizontal(0, 
								new FUS_WiegeMengeTextField(), 
								new FUS_AnzeigeMengenEinheit(""),
								new E2_ComponentGroupHorizontal(0,new FUS_Mask_SHOW_SUM(),new Insets(40,0,0,0)),
								E2_INSETS.I_0_0_10_0),oGL3);
		this.add(new E2_ComponentGroupHorizontal(0, new FUS_BT_ADD_TO_LIST(), E2_INSETS.I_0_0_0_0),  MyE2_Grid.LAYOUT_RIGHT_CENTER(oIN,null,2,1));
		this.setRowHeight(5, new Extent(40));

		
		
		//8
		this.add(new Separator(),6,E2_INSETS.I_0_0_0_0);

		//9
		MyE2_ContainerEx  oContainerEX = new MyE2_ContainerEx();
		oContainerEX.setWidth(new Extent(100,Extent.PERCENT));
		oContainerEX.setHeight(new Extent(200));
	    oContainerEX.add(new FUS_Grid_ErfassteFuhren());
		this.add(oContainerEX,6,E2_INSETS.I_0_0_0_0);
		
		this.add(new Separator(),6,E2_INSETS.I_0_0_0_0);
		
		this.add(new E2_ComponentGroupHorizontal(0, new FUS_BT_BAUE_FUHREN(), E2_INSETS.I_0_0_0_0),  MyE2_Grid.LAYOUT_RIGHT_CENTER(oIN,null,6,1));

		
	}

	
	//-----------------------------
	
	public void fill_Comp_LADESTELLE(RECORD_ADRESSE  record_LADESTELLE) throws myException
	{
		if (record_LADESTELLE!=null)
		{
			new _SEARCH_SearchAdressFields().get_Found_EK_AdressField().fill_MaskText_And_Label(record_LADESTELLE.get_ID_ADRESSE_cUF());
			new _SEARCH_SearchAdressFields().get_Found_EK_AdressField().do_afterFieldWasFilled(record_LADESTELLE.get_ID_ADRESSE_cUF());
		}
	}


	public void fill_Comp_ABLADESTELLE(RECORD_ADRESSE  record_ABLADESTELLE) throws myException
	{
		if (record_ABLADESTELLE!=null)
		{
			new _SEARCH_SearchAdressFields().get_Found_VK_AdressField().fill_MaskText_And_Label(record_ABLADESTELLE.get_ID_ADRESSE_cUF());
			new _SEARCH_SearchAdressFields().get_Found_VK_AdressField().do_afterFieldWasFilled(record_ABLADESTELLE.get_ID_ADRESSE_cUF());
		}
	}


	public void fill_Comp_LADESORTE(RECORD_ARTIKEL_BEZ  record_LADESORTE) throws myException
	{
		if (record_LADESORTE!=null)
		{
			new _SEARCH_SearchSortenFields().get_Found_EK_SortenField().fill_MaskText_And_Label(record_LADESORTE.get_ID_ARTIKEL_BEZ_cUF());
			new _SEARCH_SearchSortenFields().get_Found_EK_SortenField().do_afterFieldWasFilled(record_LADESORTE.get_ID_ARTIKEL_BEZ_cUF());

		}
	}


	public void fill_Comp_ABLADESORTE(RECORD_ARTIKEL_BEZ  record_ABLADESORTE) throws myException
	{
		if (record_ABLADESORTE!=null)
		{
			new _SEARCH_SearchSortenFields().get_Found_VK_SortenField().fill_MaskText_And_Label(record_ABLADESORTE.get_ID_ARTIKEL_BEZ_cUF());
			new _SEARCH_SearchSortenFields().get_Found_VK_SortenField().do_afterFieldWasFilled(record_ABLADESORTE.get_ID_ARTIKEL_BEZ_cUF());
		}
	}

	
	
	

	public void fill_Comp_LADEKONTRAKT(RECORD_VPOS_KON  record_LADEKONTRAKT) throws myException
	{
		if (record_LADEKONTRAKT!=null)
		{
			new _SEARCH_SearchKontraktFields().get_Found_EK_KontraktField().fill_MaskText_And_Label(record_LADEKONTRAKT.get_ID_VPOS_KON_cUF());
			new _SEARCH_SearchKontraktFields().get_Found_EK_KontraktField().do_afterFieldWasFilled(record_LADEKONTRAKT.get_ID_VPOS_KON_cUF());
		}
	}

	

	public void fill_Comp_ABLADEKONTRAKT(RECORD_VPOS_KON  record_ABLADEKONTRAKT) throws myException
	{
		if (record_ABLADEKONTRAKT!=null)
		{
			new _SEARCH_SearchKontraktFields().get_Found_VK_KontraktField().fill_MaskText_And_Label(record_ABLADEKONTRAKT.get_ID_VPOS_KON_cUF());
			new _SEARCH_SearchKontraktFields().get_Found_VK_KontraktField().do_afterFieldWasFilled(record_ABLADEKONTRAKT.get_ID_VPOS_KON_cUF());
		}
	}


	

	public void fill_Comp_LADEANGEBOT(RECORD_VPOS_STD record_LADEANGEBOT) throws myException
	{
		if (record_LADEANGEBOT!=null)
		{
			new _SEARCH_SearchAngebotsFields().get_Found_EK_AngebotField().fill_MaskText_And_Label(record_LADEANGEBOT.get_ID_VPOS_STD_cUF());
			new _SEARCH_SearchAngebotsFields().get_Found_EK_AngebotField().do_afterFieldWasFilled(record_LADEANGEBOT.get_ID_VPOS_STD_cUF());
		}
	}


	public void fill_Comp_ABLADEANGEBOT(RECORD_VPOS_STD record_ABLADEANGEBOT) throws myException
	{
		if (record_ABLADEANGEBOT!=null)
		{
			new _SEARCH_SearchAngebotsFields().get_Found_VK_AngebotField().fill_MaskText_And_Label(record_ABLADEANGEBOT.get_ID_VPOS_STD_cUF());
			new _SEARCH_SearchAngebotsFields().get_Found_VK_AngebotField().do_afterFieldWasFilled(record_ABLADEANGEBOT.get_ID_VPOS_STD_cUF());
		}
	}


	public void fill_Comp_WiegeDatum(MyDate  oWiegeDatum) throws myException
	{
		if (oWiegeDatum != null && oWiegeDatum.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			new _SEARCH_InputDatum().get_Found_FUS_InputDatum().get_oTextField().setText(oWiegeDatum.get_cDateStandardFormat());
			new _SEARCH_InputDatum().get_Found_FUS_InputDatum().do_afterFieldWasFilled(oWiegeDatum.get_cDateStandardFormat());
		}
	}

	public void fill_Comp_Datum2(MyDate  oDatum2) throws myException
	{
		if (new _SEARCH_InputDatum2().get_Found_FUS_InputDatum2() !=null)
		{
			if (oDatum2 != null && oDatum2.get_cErrorCODE().equals(MyDate.ALL_OK))
			{
				new _SEARCH_InputDatum2().get_Found_FUS_InputDatum2().get_oTextField().setText(oDatum2.get_cDateStandardFormat());
				new _SEARCH_InputDatum2().get_Found_FUS_InputDatum2().do_afterFieldWasFilled(oDatum2.get_cDateStandardFormat());
			}
		}
	}


	public void fill_Comp_AnzahlContainer(MyBigDecimal  oAnzahl) throws myException
	{
		if (new _SEARCH_InputAnzahlContainer().get_Found_FUS_InputAnzahlContainer()!=null)
		{
			if (oAnzahl != null && oAnzahl.get_cErrorCODE().equals(MyDate.ALL_OK))
			{
				new _SEARCH_InputAnzahlContainer().get_Found_FUS_InputAnzahlContainer().setText(oAnzahl.get_FormatedRoundedNumber(0));
				new _SEARCH_InputAnzahlContainer().get_Found_FUS_InputAnzahlContainer().do_afterFieldWasFilled(oAnzahl.get_FormatedRoundedNumber(0));
			}
		}
	}


	public void fill_Comp_WiegeMenge(MyBigDecimal bdWiegeMenge) throws myException
	{
		
		if (bdWiegeMenge != null && bdWiegeMenge.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			new _SEARCH_WiegeMengeTextField().get_Found_FUS_WiegeMengeTextField().setText(bdWiegeMenge.get_FormatedRoundedNumber(3));
			new _SEARCH_WiegeMengeTextField().get_Found_FUS_WiegeMengeTextField().do_afterFieldWasFilled(bdWiegeMenge.get_FormatedRoundedNumber(3));
		}
		else
		{
			new _SEARCH_WiegeMengeTextField().get_Found_FUS_WiegeMengeTextField().setText("");
			new _SEARCH_WiegeMengeTextField().get_Found_FUS_WiegeMengeTextField().do_afterFieldWasFilled("");
		}
	}

	public void fill_Comp_TransportMittel(String cTransportmittel) throws myException
	{
		
		if (S.isFull(cTransportmittel))
		{
			new _SEARCH_Combo_Transportmittel().get_found_ComboBox().get_oTextField().setText(cTransportmittel);
			new _SEARCH_Combo_Transportmittel().get_found_ComboBox().do_afterFieldWasFilled(cTransportmittel);
		}
	}

	public void fill_Comp_EK_PREIS(MyBigDecimal bdPreisEK) throws myException
	{
		
		if (bdPreisEK != null && bdPreisEK.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			new _SEARCH_PreisEingabe().get_found_PreisFeld_EK().setText(bdPreisEK.get_FormatedRoundedNumber(2));
			new _SEARCH_PreisEingabe().get_found_PreisFeld_EK().do_afterFieldWasFilled(bdPreisEK.get_FormatedRoundedNumber(2));
		}
		else
		{
			new _SEARCH_PreisEingabe().get_found_PreisFeld_EK().setText("");
			new _SEARCH_PreisEingabe().get_found_PreisFeld_EK().do_afterFieldWasFilled("");
			
		}
	}

	public void fill_Comp_VK_PREIS(MyBigDecimal bdPreisVK) throws myException
	{
		
		if (bdPreisVK != null && bdPreisVK.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			new _SEARCH_PreisEingabe().get_found_PreisFeld_VK().setText(bdPreisVK.get_FormatedRoundedNumber(2));
			new _SEARCH_PreisEingabe().get_found_PreisFeld_VK().do_afterFieldWasFilled(bdPreisVK.get_FormatedRoundedNumber(2));
		}
	}
	
	public void fill_Comp_LKW_Kennzeichen(String cKennzeichen) throws myException
	{
		new _SEARCH_InputLKW().get_Found_FUS_InputLKW().setText(S.NN(cKennzeichen));
	}

	public void fill_Comp_LKW_AnhaengerKennzeichen(String cKennzeichen) throws myException
	{
		new _SEARCH_InputLKW_Anhaenger().get_Found_FUS_InputLKW_Anhaenger().setText(S.NN(cKennzeichen));
	}

	public void fill_Comp_Wiegekarte_EK(String cWiegekarteEK) throws myException
	{
		new _SEARCH_Wiegeschein().get_found_Wiegeschein(true).setText(S.NN(cWiegekarteEK));
	}

	public void fill_Comp_Wiegekarte_VK(String cWiegekarteVK) throws myException
	{
		new _SEARCH_Wiegeschein().get_found_Wiegeschein(false).setText(S.NN(cWiegekarteVK));
	}

	public boolean get_bVarianteFahrplan()
	{
		return bVarianteFahrplan;
	}


	public void set_bVarianteFahrplan(boolean bVarianteFahrplan)
	{
		this.bVarianteFahrplan = bVarianteFahrplan;
	}

}

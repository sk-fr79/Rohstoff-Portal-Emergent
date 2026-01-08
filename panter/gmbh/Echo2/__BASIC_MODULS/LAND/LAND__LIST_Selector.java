package panter.gmbh.Echo2.__BASIC_MODULS.LAND;


import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class LAND__LIST_Selector extends E2_ListSelectorContainer
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public LAND__LIST_Selector(E2_NavigationList oNavigationList, String cLIST_MODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		int[] iSpalten = {40,40};
		
		E2_ListSelektorMultiselektionStatusFeld_STD  oCB_PostCode_YES_NO = 
					new E2_ListSelektorMultiselektionStatusFeld_STD(iSpalten, true, new MyE2_String("Besitzt PostCode: "),new Extent(100));
		
		oCB_PostCode_YES_NO.ADD_STATUS_TO_Selector(true, "(NVL("+_DB.LAND$HAT_POSTCODE+",'N')='Y')", new MyE2_String("Ja"), new MyE2_String("Alle Länder mit Schalter: Hat PostCode=JA"));
		oCB_PostCode_YES_NO.ADD_STATUS_TO_Selector(true, "(NVL("+_DB.LAND$HAT_POSTCODE+",'N')='N')", new MyE2_String("Nein"), new MyE2_String("Alle Länder mit Schalter: Hat PostCode=NEIN"));
		
		E2_ListSelektorMultiselektionStatusFeld_STD  oCB_EU_YES_NO = 
				new E2_ListSelektorMultiselektionStatusFeld_STD(iSpalten, true, new MyE2_String("Ist EU-LAND: "),new Extent(100));
	
		oCB_EU_YES_NO.ADD_STATUS_TO_Selector(true, "(NVL("+_DB.LAND$INTRASTAT_JN+",'N')='Y')", new MyE2_String("Ja"), new MyE2_String("Alle Länder aus der EU"));
		oCB_EU_YES_NO.ADD_STATUS_TO_Selector(true, "(NVL("+_DB.LAND$INTRASTAT_JN+",'N')='N')", new MyE2_String("Nein"), new MyE2_String("Alle Länder, die NICHT-EU sind"));
		

		E2_ListSelektorMultiselektionStatusFeld_STD  oCB_NOTI_YES_NO = 
				new E2_ListSelektorMultiselektionStatusFeld_STD(iSpalten, true, new MyE2_String("Länder mit Einfuhr-Noti.: "),new Extent(100));
	
		oCB_NOTI_YES_NO.ADD_STATUS_TO_Selector(true, "(NVL("+_DB.LAND$GENERELLE_EINFUHR_NOTI+",'N')='Y')", new MyE2_String("Ja"), new MyE2_String("Alle Länder mit Einfuhr-Notifizierung"));
		oCB_NOTI_YES_NO.ADD_STATUS_TO_Selector(true, "(NVL("+_DB.LAND$GENERELLE_EINFUHR_NOTI+",'N')='N')", new MyE2_String("Nein"), new MyE2_String("Alle Länder ohne Einfuhr-Notifizierung"));
		

		this.oSelVector.add(oCB_PostCode_YES_NO);
		this.oSelVector.add(oCB_EU_YES_NO);
		this.oSelVector.add(oCB_NOTI_YES_NO);
		
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cLIST_MODULE_KENNER);
		this.oSelVector.add(oDB_BasedSelektor);


		MyE2_Grid oGridInnen = new MyE2_Grid(5,0);
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
		
		oGridInnen.add(oCB_EU_YES_NO.get_oComponentForSelection(), E2_INSETS.I(0,0,30,0));
		oGridInnen.add(oCB_PostCode_YES_NO.get_oComponentForSelection(), E2_INSETS.I(0,0,30,0));
		oGridInnen.add(oCB_NOTI_YES_NO.get_oComponentForSelection(), E2_INSETS.I(0,0,30,0));
		
		if (oDB_BasedSelektor.get_bIsFilled()) {
			oGridInnen.add(new MyE2_Label("Zusätzliche Selektionen"), E2_INSETS.I(0,0,5,0));
			oGridInnen.add(oDB_BasedSelektor.get_oComponentForSelection(), E2_INSETS.I(0,0,30,0));
		} else {
			oGridInnen.add(new MyE2_Label(""), E2_INSETS.I(0,0,5,0));
			oGridInnen.add(new MyE2_Label(""), E2_INSETS.I(0,0,30,0));
		}

	
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}

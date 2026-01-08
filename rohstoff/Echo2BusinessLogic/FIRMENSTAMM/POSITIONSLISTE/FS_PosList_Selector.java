package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.POSITIONSLISTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo_NNG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_Date_von_bis_POPUP_OWN;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class FS_PosList_Selector extends E2_SelectionComponentsVector {
	
	private static int[] CheckBoxSelektorSpalten = {70,70};

	
	private ownSelectorLeistungsdatumZeitraum  	oSelectDatumVonBis = null;
	private MyE2_CheckBox 						oCB_Geschlossen = new MyE2_CheckBox(new MyE2_String("Nur unbezahlte zeigen"),true,false);
	private ownSelektor_EingangAusgang	oSelEingangAusgang = new ownSelektor_EingangAusgang(); 
	
	
	public FS_PosList_Selector(E2_NavigationList onavigationList) 	throws myException {
		super(onavigationList);
	
		this.oSelectDatumVonBis = new ownSelectorLeistungsdatumZeitraum();
		
		this.add(this.oSelectDatumVonBis);
		
		
		String GPREIS_FW = 			"NVL("+_DB.VPOS_RG+"."+_DB.VPOS_RG$GESAMTPREIS_FW+",0)";
		String GPREIS_ABZUG_FW = 	"NVL("+_DB.VPOS_RG+"."+_DB.VPOS_RG$GESAMTPREIS_ABZUG_FW+",0)";
		String ZAHLUNG_KONTROLLE = 	"NVL("+_DB.VPOS_RG+"."+_DB.VPOS_RG$ZAHLUNG_KONTROLLE+",0)";
		
		this.add(new E2_ListSelectorStandard(	this.oCB_Geschlossen, 
												"NVL("+_DB.FIBU$BUCHUNG_GESCHLOSSEN+",'N')='N'" +
												" AND "+GPREIS_FW+"-"+GPREIS_ABZUG_FW+"-"+ZAHLUNG_KONTROLLE+"<>0",
												""));
		
		this.add(oSelEingangAusgang);
		
	}
	
	
	private class ownSelectorLeistungsdatumZeitraum extends E2_SelektorDateFromTo_NNG {

		public ownSelectorLeistungsdatumZeitraum() throws myException {
			super(_DB.VPOS_RG$AUSFUEHRUNGSDATUM,_DB.VPOS_RG$AUSFUEHRUNGSDATUM);
			this.setWidthInPixel_InputFields(80);
		}

		@Override
		public void Ordne_Komponenten_An_in_DateVonbisPopup(MyE2_TextField_Date_von_bis_POPUP_OWN 	ownSelectVonBisPopup, 
															MyE2_TextField 							oTextFieldVon, 
															MyE2_TextField 							oTextFieldBis, 
															MyE2_Button 							oButtonCalendar, 
															MyE2_Button 							oButtonEraserVon,
															MyE2_Button 							oButtonEraserBis) throws myException
		{
			
			ownSelectVonBisPopup.setSize(5);
			ownSelectVonBisPopup.add(oTextFieldVon,E2_INSETS.I(0, 0, 3, 0));
			ownSelectVonBisPopup.add(oButtonEraserVon,E2_INSETS.I(0, 0, 15, 0));
			ownSelectVonBisPopup.add(oTextFieldBis,E2_INSETS.I(0, 0, 3, 0));
			ownSelectVonBisPopup.add(oButtonEraserBis,E2_INSETS.I(0,0,5,0));
			ownSelectVonBisPopup.add(oButtonCalendar,E2_INSETS.I(0,0,5,0));
			
			
		}

		@Override
		public void HaengeMeineElementeAn_DateVonBisPopup(MyE2_TextField_Date_von_bis_POPUP_OWN ownSelectVonBisPopup, MyE2_Button oButtonLos, MyE2_Button oButtonHelp) throws myException
		{
			ownSelectVonBisPopup.setSize(6);
			ownSelectVonBisPopup.add(oButtonHelp,E2_INSETS.I_0_0_5_0);
		}
		
	}

	
	public class ownSelektor_EingangAusgang extends E2_ListSelektorMultiselektionStatusFeld_STD
	{
		
		public ownSelektor_EingangAusgang()
		{
			super(FS_PosList_Selector.CheckBoxSelektorSpalten,false,new MyE2_String(""),new Extent(80));
			
			this.ADD_STATUS_TO_Selector(false,	"("+_DB.VPOS_RG$LAGER_VORZEICHEN+"=1)",	new MyE2_String("Einkauf"),  	 	new MyE2_String("Zeige Einkaufspositionen"));		
			this.ADD_STATUS_TO_Selector(true,	"("+_DB.VPOS_RG$LAGER_VORZEICHEN+"=-1)",	new MyE2_String("Verkauf"),   	new MyE2_String("Zeige Verkaufspositionen"));		
		}
	}
	
	public MyE2_Grid get_oGridWithSelectors() throws myException {
		MyE2_Grid oGridRueck = new MyE2_Grid(5, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		oGridRueck.add(new MyE2_Label(new MyE2_String("Leistungsdatum:")),E2_INSETS.I(1,1,4,1));
		oGridRueck.add(this.oSelectDatumVonBis.get_oComponentForSelection(), E2_INSETS.I(1,1,15,1));
		oGridRueck.add(this.oCB_Geschlossen, E2_INSETS.I(1,1,15,1));
		oGridRueck.add(this.oSelEingangAusgang.get_oComponentForSelection(), E2_INSETS.I(1,1,15,1));
		oGridRueck.add(this.get_oButtonReload(),E2_INSETS.I(1,1,1,1));
		return oGridRueck;
	}
	

}

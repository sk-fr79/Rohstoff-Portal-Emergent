package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT._SETTING_DATUM;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.E2_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_LIST_ActionAgent_Mail_Print;

//alternative berechnungsmethode des Rechnungsdatums (alte version wieder zurueck) 
public class PREPARE_K_LIST_BUTTON_PRINT_POPUPBEFORE_ALTERNATIV2 extends E2_BasicModuleContainer_PopUp_BeforeExecute {

	private Vector<String>   					vID_VKOPF_RG = null;
	private BS_K_LIST_ActionAgent_Mail_Print  	oActionAgentPrintOrMail = null;
	private MyE2_Grid  oGridBase = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	
	
	private LinkedHashMap<String, ListeZeileRechnungen>  hmListenDefFuerEingaben = 
			new LinkedHashMap<String, PREPARE_K_LIST_BUTTON_PRINT_POPUPBEFORE_ALTERNATIV2.ListeZeileRechnungen>();
	
	private int i_BaseWidth_Window = 800;
	private int i_BaseHeight_Window = 450;
	
	private boolean b_Preview = false;
	
	public PREPARE_K_LIST_BUTTON_PRINT_POPUPBEFORE_ALTERNATIV2(BS_K_LIST_ActionAgent_Mail_Print  ActionAgentPrintOrMail, boolean bPreview) throws myException
	{
		super();
		this.b_Preview = bPreview;
		
		this.oActionAgentPrintOrMail = ActionAgentPrintOrMail;
		this.add_In_ContainerEX(this.oGridBase, new Extent(this.i_BaseWidth_Window-20), new Extent(this.i_BaseHeight_Window-150), E2_INSETS.I(2,2,2,2));
		
		this.set_oExtWidth(new Extent(this.i_BaseWidth_Window));
		this.set_oExtHeight(new Extent(this.i_BaseHeight_Window));
		
		this.set_bVisible_Row_For_Messages(true);
		
		E2_ComponentGroupHorizontal oButtonLeiste = new E2_ComponentGroupHorizontal(0,this.get_oButtonForOK(),this.get_oButtonForAbbruch(),E2_INSETS.I_10_2_10_2);
		this.add(oButtonLeiste,  E2_INSETS.I_5_5_5_5);
		this.set_bShowWindowAsSplitpane(false);
		
		//2011-07-05: individuelles resizing
		this.set_oResizeHelper(new ownResizer());

	}
	
	
	@Override
	protected void doOwnCode_in_ok_button() throws myException {
		MyE2_MessageVector  oMVSammler = new MyE2_MessageVector();
		
		for (String cID_VKOPF_RG: this.hmListenDefFuerEingaben.keySet()) {
			oMVSammler.add_MESSAGE(this.hmListenDefFuerEingaben.get(cID_VKOPF_RG).pruefe_DatumsFelder());
		}
		
		if (oMVSammler.get_bIsOK()) {
			
			Vector<String>  vSQL = new Vector<String>();
			for (String cID_VKOPF_RG: this.hmListenDefFuerEingaben.keySet()) {
				RECORD_VKOPF_RG_SETTING_DATUM recRG = new RECORD_VKOPF_RG_SETTING_DATUM(cID_VKOPF_RG);
				HashMap<String, MyDate> hmErgebnisse = new HashMap<String, MyDate>();
				hmErgebnisse.put(_DB.VKOPF_RG$DRUCKDATUM,  this.hmListenDefFuerEingaben.get(cID_VKOPF_RG).get_O_RECHUNGSDATUM());
				hmErgebnisse.put(_DB.VPOS_RG$ZAHLUNGSBED_CALC_DATUM, this.hmListenDefFuerEingaben.get(cID_VKOPF_RG).get_O_ZAHLUNGSDATUM());

				oMVSammler.add_MESSAGE(recRG.ErzeugeStatements_fuer_RechnungsDatum_AND_ZahlungsZiel_Alternativ(vSQL,hmErgebnisse));
			}
			
			DEBUG.System_print(vSQL);
			
			if (oMVSammler.get_bIsOK()) {
				oMVSammler.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
			}
			
		}

		bibMSG.add_MESSAGE(oMVSammler);

	}

	@Override
	protected void doOwnCode_in_cancel_button() throws myException {
		
	}

	@Override
	public void doBuildContent(ExecINFO oExecInfo) throws myException {
		
		MyE2_Grid  oGridEingabeWerte = new MyE2_Grid(4, MyE2_Grid.STYLE_GRID_DDARK_BORDER_INSETS_11_W100());
		
		this.oGridBase.removeAll();
		this.oGridBase.add(oGridEingabeWerte, E2_INSETS.I(0,0,0,0));
		
		GridLayoutData  oGLVorschaudruck = 	MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(5,10,5,10), new E2_ColorBase(), oGridEingabeWerte.getSize(), 1);
		GridLayoutData  oGLFinaldruck = 	MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(5,10,5,10), new E2_ColorAlarm(), 		oGridEingabeWerte.getSize(), 1);
		
		if (this.b_Preview) {
			oGridEingabeWerte.add(new MyE2_Label(new MyE2_String("VORSCHAUDRUCK"), new E2_FontBold(4)), oGLVorschaudruck);
		} else {
			oGridEingabeWerte.add(new MyE2_Label(new MyE2_String("Achtung! Endgültiger Druck! Beleg(e) werden geschlossen !!"), new E2_FontBold(4)), oGLFinaldruck);
		}
		
		
		oGridEingabeWerte.add(new MyE2_Label(new MyE2_String("Rechnung ID")), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2),new E2_ColorDark(),null,null));
		oGridEingabeWerte.add(new MyE2_Label(new MyE2_String("Beschreibung")), MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(2,2,2,2),new E2_ColorDark(),null,null));
		oGridEingabeWerte.add(new MyE2_Label(new MyE2_String("Rechnungsdatum")), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2),new E2_ColorDark(),null,null));
		oGridEingabeWerte.add(new MyE2_Label(new MyE2_String("Zahlungsziel")), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2),new E2_ColorDark(),null,null));
		

		for (String ID_VKOPF_RG: this.hmListenDefFuerEingaben.keySet()) {
			oGridEingabeWerte.add(this.hmListenDefFuerEingaben.get(ID_VKOPF_RG).ID_VKOPF_RG, MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(2,2,2,2),new E2_ColorDark(),null,null));
			oGridEingabeWerte.add(this.hmListenDefFuerEingaben.get(ID_VKOPF_RG).INFO_TEXT, MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(2,2,2,2),new E2_ColorDark(),null,null));
			oGridEingabeWerte.add(this.hmListenDefFuerEingaben.get(ID_VKOPF_RG).RECHUNGSDATUM, MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(2,2,2,2),new E2_ColorDark(),null,null));
			oGridEingabeWerte.add(this.hmListenDefFuerEingaben.get(ID_VKOPF_RG).ZAHLUNGSDATUM, MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(2,2,2,2),new E2_ColorDark(),null,null));
		}
		
	}
	
	
	private class ownResizer extends XX_BasicContainerResizeHelper
	{
		@Override
		public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException
		{
			Extent  oWidth = ownContainer.get_oExtWidth();
			Extent  oHeight = ownContainer.get_oExtHeight();
			
			if (oWidth.getUnits()==Extent.PX && oHeight.getUnits()==Extent.PX)
			{
				E2_RecursiveSearch_Component oSearch = new E2_RecursiveSearch_Component(ownContainer, bibALL.get_Vector(MyE2_ContainerEx.class.getName()), null);
				
				if (oSearch.get_vAllComponents().size()==1)
				{
					MyE2_ContainerEx oContainerEx = (MyE2_ContainerEx)oSearch.get_vAllComponents().get(0);
					
					oContainerEx.setWidth(new Extent(oWidth.getValue()-40));
					
					oContainerEx.setHeight(new Extent(oHeight.getValue()-150));
				}
			}
		}
	}


	@Override
	public boolean makePreparationAndCheck_IF_MUST_BE_SHOWN(ExecINFO oExecInfo) throws myException {
		//in dieser variante der einzige funktionale Block
		//angezeigt wird hier nix
		
		this.hmListenDefFuerEingaben.clear();
		
		boolean b_PopupAnzeigen = false;
		
		this.vID_VKOPF_RG = new Vector<String>();
		this.vID_VKOPF_RG.addAll(this.oActionAgentPrintOrMail.fill_IDs_To_Print());
		
		if (this.vID_VKOPF_RG.size()==0) {
			return false;    //dann eh nix machen
		}

		//Step 1: pruefen, ob gemischte RG-Positionen (Abgeschlossen/nicht abgeschlossen) vorliegen
		CHECK_NurFertigeOderOffene  oCheck = new CHECK_NurFertigeOderOffene(this.vID_VKOPF_RG);
		if (oCheck.get_bGemischteAuswahl()) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es dürfen nur mehrere NICHT-Abgeschlossene oder mehrere ABGESCHLOSSENE gleichzeitig gedruckt werden !!"));
			return false;    //dann eh nix machen
		}
		
		
		//step 2: rechnungspositionen lueckenlos schreiben
		MyE2_MessageVector  oMV = 	new MyE2_MessageVector();
		KORR_PosNummern 	oKorr = new KORR_PosNummern(this.vID_VKOPF_RG) ;
		
		if (!oKorr.MakeKorrection(oMV)) {
			bibMSG.add_MESSAGE(oMV);
			return false;
		}
		
		if (oCheck.get_bNurNeue()) {        //nur dann gehts hier weiter, sonst ist bereits alles geschrieben
			if (oCheck.get_bOhneLeistungsdatumVorhanden()) {
				//sollte nicht vorkommen
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("In der Auswahl existieren Rechnungspositionen ohne Leistungsdatum! Bitte prüfen!"));
			} else {
		
				//hier werden die Werte geschrieben
				for (String cID_VKOPF_RG: this.vID_VKOPF_RG) {
					RECORD_VKOPF_RG_SETTING_DATUM recRK = new RECORD_VKOPF_RG_SETTING_DATUM(cID_VKOPF_RG);
					
					//fuer jede neue Rechnung die Daten errechnen und in hmTemp zurueckgeben
					HashMap<String, MyDate>  hmTemp = new HashMap<String, MyDate>();
					oMV.add_MESSAGE(recRK.Suche_RechnungsDatum_und_ZahlDatum_Alternativ(hmTemp));
					//in hmTemp stehen jetzt das druckdatum und das zahlungsziel
					
					this.hmListenDefFuerEingaben.put(cID_VKOPF_RG, new ListeZeileRechnungen(	cID_VKOPF_RG, 
																								hmTemp.get(_DB.VKOPF_RG$DRUCKDATUM), 
																								hmTemp.get(_DB.VPOS_RG$ZAHLUNGSBED_CALC_DATUM), 
																								recRK.get___KETTE(bibVECTOR.get_Vector( 
																																_DB.VKOPF_RG$NAME1,
																																_DB.VKOPF_RG$NAME2,
																																_DB.VKOPF_RG$ORT))
																							));
				}
			
				if (oMV.get_bHasAlarms()) {
					bibMSG.add_MESSAGE(oMV);
				} else {
					b_PopupAnzeigen = true;
				}
			}
		}
		
		return b_PopupAnzeigen;    
	}

	
	
	
	/**
	 * hilfsklasse zur darstellung der liste fuer evtl. korrektur der datumswerte
	 * @author martin
	 *
	 */
	private class ListeZeileRechnungen {
		public MyE2_TextField 		ID_VKOPF_RG = null;
		public MyE2_TextField 		RECHUNGSDATUM = null;
		public MyE2_TextField 		ZAHLUNGSDATUM = null;
		public MyE2_TextField 		INFO_TEXT = null;

		public ListeZeileRechnungen(String iD_VKOPF_RG, MyDate rECHUNGSDATUM, MyDate zAHLUNGSDATUM, String iNFO_TEXT) throws myException {
			super();
			ID_VKOPF_RG = new MyE2_TextField(iD_VKOPF_RG, 100, 10);
			RECHUNGSDATUM = new MyE2_TextField(rECHUNGSDATUM.get_cDateStandardFormat(), 100, 10);
			ZAHLUNGSDATUM = new MyE2_TextField(zAHLUNGSDATUM.get_cDateStandardFormat(), 100, 10);
			INFO_TEXT = 	new MyE2_TextField(iNFO_TEXT, 400, 400);
			
			ID_VKOPF_RG.set_bEnabled_For_Edit(false);
			INFO_TEXT.set_bEnabled_For_Edit(false);
		}
		
		public MyE2_MessageVector pruefe_DatumsFelder() {
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			MyDate oDateRech = new MyDate(this.RECHUNGSDATUM.getText());
			MyDate oDateZahl = new MyDate(this.ZAHLUNGSDATUM.getText());

			this.RECHUNGSDATUM.setStyle(this.RECHUNGSDATUM.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
			this.ZAHLUNGSDATUM.setStyle(this.ZAHLUNGSDATUM.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
			
			if (!oDateRech.get_bOK()) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler in Rechnungsdatum: ",true,this.ID_VKOPF_RG.getText(),false)));
				this.RECHUNGSDATUM.setStyle(this.RECHUNGSDATUM.EXT().get_STYLE_FACTORY().get_Style(true,true,true));
			}
			if (!oDateZahl.get_bOK()) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler in Zahlungsziel: ",true,this.ID_VKOPF_RG.getText(),false)));
				this.ZAHLUNGSDATUM.setStyle(this.ZAHLUNGSDATUM.EXT().get_STYLE_FACTORY().get_Style(true,true,true));
			}
			
			return oMV;
		}
		
		
		public MyDate get_O_RECHUNGSDATUM() {
			return new MyDate(this.RECHUNGSDATUM.getText());
		}
		public MyDate get_O_ZAHLUNGSDATUM() {
			return new MyDate(this.ZAHLUNGSDATUM.getText());
		}
		
	}
	
	
	
}

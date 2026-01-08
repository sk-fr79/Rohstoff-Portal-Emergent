package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.DB_RB.BASICS.DBRB_MASK;
import panter.gmbh.Echo2.DB_RB.BASICS.IF_DBRB_Component;
import panter.gmbh.Echo2.DB_RB.BASICS.COMPONENTS.MyE2_DBRB_ComboBoxErsatzArea;
import panter.gmbh.Echo2.DB_RB.TOOLS.DBRB_BUTTON_ToEditSomeFields;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED.FU_AL_Erzeuge_ButtonTextUndToolTips;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED.FU_AL_LieferbedZeigeAktuellStatistik;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED.FU_AL_SucheLieferBed;

public class FU_LIST_Expander_Teilpositionen_BT_EditLieferbedingung extends DBRB_BUTTON_ToEditSomeFields {
 
	private FU_AL_SucheLieferBed  			oSucheLieferBed = null;
	private E2_ComponentMAP       			oMapFuhre = null;
	private RECORD_VPOS_TPA_FUHRE_ORT  		oRecFuhreOrt = null;

	public FU_LIST_Expander_Teilpositionen_BT_EditLieferbedingung(RECORD_VPOS_TPA_FUHRE_ORT recOrt, E2_ComponentMAP oMAP) throws myException {
		super("<lieferbed>",MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
		
		this.oRecFuhreOrt = recOrt;
		
		this.setFont(new E2_FontPlain(-2));
		this.setForeground(Color.BLACK);
		this.setLineWrap(true);

		boolean bEK = recOrt.get_DEF_QUELLE_ZIEL_cUF().equals("EK");
		
		this.oSucheLieferBed = new FU_AL_SucheLieferBed(recOrt, 
				 _DB.VPOS_TPA_FUHRE_ORT$ID_ADRESSE, 
				 _DB.VPOS_TPA_FUHRE_ORT$LIEFERBED_ALTERNATIV, 
				 _DB.VPOS_TPA_FUHRE_ORT$ID_VPOS_KON, 
				 _DB.VPOS_TPA_FUHRE_ORT$ID_VPOS_STD, 
				 (bEK?_DB.ADRESSE$ID_LIEFERBEDINGUNGEN:_DB.ADRESSE$ID_LIEFERBEDINGUNGEN_VK),
				 true);
		
		FU_AL_LieferbedZeigeAktuellStatistik oZeigeAktuelleStatistikEinstufung = 
											new FU_AL_LieferbedZeigeAktuellStatistik(
															null, 
															recOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF(), 
															recOrt.get_DEF_QUELLE_ZIEL_cUF());
		
		FU_AL_Erzeuge_ButtonTextUndToolTips  oGeneratorText = new FU_AL_Erzeuge_ButtonTextUndToolTips(this.oSucheLieferBed, oZeigeAktuelleStatistikEinstufung);
		
		this.oMapFuhre = oMAP;
		
		this.set_Text(oGeneratorText.get_cTextAufButton());
		this.setToolTipText(new MyE2_String("Lieferbedingung des Zusatzorts erfassen:",true,"  ----  ",false,oGeneratorText.get_cToolTips(),false).CTrans());
		
		MyE2_Label oLabelInfo = new MyE2_Label(new MyE2_String("Lieferbedingung ermittelt: ",true,oSucheLieferBed.get_cLIEFERBED_ohne_FUHRE(),false));
						
		MyE2_String cBeschriftung = bEK 
									?
						 			new MyE2_String("Lieferbedingung WE-Zusatzort: ")
									:
						 			new MyE2_String("Lieferbedingung WA-Zusatzort: ");

		//hier einen refresh der map einfuegen
		this.set_oActionAddToSaveButton(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				FU_LIST_Expander_Teilpositionen_BT_EditLieferbedingung.this.oMapFuhre._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
			}
		});				 			  
						 			
						 			
						 			
		this.INIT_Button(		oLabelInfo, 
								new MyE2_String("Speichern"), 
								new MyE2_String("Abbruch"), 
								FU___CONST.FU_AUTHCODE_EDIT_ALTERNATIVE_LIEFERBEDINGUNG, 
								cBeschriftung, 
								_DB.VPOS_TPA_FUHRE_ORT$LIEFERBED_ALTERNATIV);
		
		
		this.set_bSaveWithoutTriggerCheck(true);
		
		this.add_oActionAgent(new ownActionAgent());
	}

	


	
	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			if (FU_LIST_Expander_Teilpositionen_BT_EditLieferbedingung.this.oSucheLieferBed.get_bIS_MANDANT()) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Adresse ist eine eigene Adresse, Lieferbedingung ist nicht sinnvoll!")));
			} else {
			
				ownBasicModuleContainer  oBasicContainer = new ownBasicModuleContainer();
				MyE2_Grid oGrid = FU_LIST_Expander_Teilpositionen_BT_EditLieferbedingung.this.BaueMaskenGrid(
						new RECORD_VPOS_TPA_FUHRE_ORT(FU_LIST_Expander_Teilpositionen_BT_EditLieferbedingung.this.oRecFuhreOrt.get_ID_VPOS_TPA_FUHRE_ORT_cUF()), oBasicContainer);
				oBasicContainer.add(oGrid, E2_INSETS.I_2_2_2_2);
				oBasicContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(280), new MyE2_String("Lieferbedingung der Hauptfuhre erfassen ..."));
			}
		}
	}
	
	
	private class ownBasicModuleContainer extends E2_BasicModuleContainer {
	}
	
	
	@Override
	public MyRECORD_IF_RECORDS createRecord(String cID_LIST_UF) throws myException {
		return new RECORD_VPOS_TPA_FUHRE(cID_LIST_UF);
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		return null;
	}


	@Override
	public IF_DBRB_Component createComponent(DBRB_MASK oDBRB_Mask) 	throws myException {
		return new ownDBRB_Combobox_Ersatz(oDBRB_Mask, this.get_cFieldName().toUpperCase());
	}


	
	private class ownDBRB_Combobox_Ersatz extends MyE2_DBRB_ComboBoxErsatzArea {
		public ownDBRB_Combobox_Ersatz(DBRB_MASK oDBRB_MASK, String cFieldName) throws myException {
			super(oDBRB_MASK, cFieldName, "SELECT KURZBEZEICHNUNG AS A,KURZBEZEICHNUNG AS B FROM "+bibE2.cTO()+".JT_LIEFERBEDINGUNGEN WHERE NVL(AKTIV,'N')='Y' ORDER BY KURZBEZEICHNUNG");
			
			this.get_oTextArea().set_iWidthPixel(400);
			this.get_oTextArea().set_iRows(3);
			
		}
	}


	@Override
	public MyE2_Grid createGrid4PopupWindow(MyE2_String cFeldBeschriftung, Component oComponent4Edit, Component oComponent4UserInfo, MyE2_Button btSAVE, MyE2_Button btCANCEL, E2_BasicModuleContainer oContainer4Popup) throws myException {
		
		int[]  iSpalten = {150,100,100,100};
		
		MyE2_Grid oGridRueck = new MyE2_Grid(iSpalten, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		FU_AL_SucheLieferBed oSucheLB =  FU_LIST_Expander_Teilpositionen_BT_EditLieferbedingung.this.oSucheLieferBed;
		
		
		oGridRueck.add(new MyE2_Label(cFeldBeschriftung,new E2_FontBold()), 4, E2_INSETS.I(1,1,10,10));
		
		oGridRueck.add(new MyE2_Label(new MyE2_String("Ermittelte Lieferbedingung:"),true), 1, E2_INSETS.I(1,1,10,10));
		oGridRueck.add(new MyE2_Label(new MyE2_String(S.NN(oSucheLB.get_cLIEFERBED_ohne_FUHRE(),"<leer>"),false), new E2_FontBoldItalic(),true),2, E2_INSETS.I(1,1,10,10));
		oGridRueck.add(new MyE2_Label(oSucheLB.get_cHerkunftsinfo_ohne_Fuhre(),true), 1, E2_INSETS.I(1,1,10,10));
		
		
		oGridRueck.add(new MyE2_Label(new MyE2_String("Neue Lieferbedingung:"),true), 1, E2_INSETS.I(1,1,10,20));
		oGridRueck.add(oComponent4Edit, 3, E2_INSETS.I(1, 1, 10, 20));
		
		btSAVE.setStyle(MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
		btCANCEL.setStyle(MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
		
		oGridRueck.add(btSAVE,2, E2_INSETS.I(1, 1, 10, 20));
		oGridRueck.add(btCANCEL,2, E2_INSETS.I(1, 1, 10, 20));

		return oGridRueck;
	}


	@Override
	public MyE2_MessageVector makeAddonValidation(DBRB_BUTTON_ToEditSomeFields ownButton) throws myException {
		//hier pruefen, dass nur eingaben erlaubt sind, die mit einer vorhandenen lieferbedingung beginnen
		MyE2_MessageVector  oMV = new MyE2_MessageVector();

		String cActualLFBed = S.NN(this.get_ActualDBValue(this.get_cFieldName())).trim();
		
		if (S.isFull(cActualLFBed)) {
			boolean bGefunden = false;
			
			RECLIST_LIEFERBEDINGUNGEN  rlLiefBed = new RECLIST_LIEFERBEDINGUNGEN("SELECT * FROM "+bibE2.cTO()+".JT_LIEFERBEDINGUNGEN");
			for (RECORD_LIEFERBEDINGUNGEN recLFB: rlLiefBed.values()) {
				if (cActualLFBed.startsWith(recLFB.get_KURZBEZEICHNUNG_cUF_NN("---"))) {
					bGefunden = true;
				}
			}
			
			if (!bGefunden) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die neue Lieferbedingung MUSS mit eine Lieferbedingung aus dem Stammsatz beginnen")));
			}
		}
		return oMV;
	}
	
}

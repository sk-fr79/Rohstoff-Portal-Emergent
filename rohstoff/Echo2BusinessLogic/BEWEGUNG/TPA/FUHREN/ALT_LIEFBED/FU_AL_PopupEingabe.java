package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LIEFERBEDINGUNGEN;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class FU_AL_PopupEingabe extends E2_BasicModuleContainer {

	
	private String 							c_OldValueAtPopup = null;
	private FU_AL_Component 				oFU_AL_Component = null;
	
	private int[]   						iSpalten = {150,100,100,100};
	public FU_AL_PopupEingabe(	FU_AL_Component 	fu_AL_Component) throws myException {
		super();
		
		this.oFU_AL_Component = fu_AL_Component;
		this.c_OldValueAtPopup = S.NN(this.oFU_AL_Component.get_oDD_HiddenLieferbed().get_cActualMaskValue());
		
		
		MyE2_Grid oGridInnen = new MyE2_Grid(iSpalten, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGridInnen.add(new MyE2_Label(new MyE2_String("Ermittelte Lieferbedingung:"),true), 1, E2_INSETS.I(1,1,10,10));
		oGridInnen.add(new MyE2_Label(new MyE2_String(S.NN(this.oFU_AL_Component.get_Lieferbedingung_OhneFuhre(),"<leer>"),false), new E2_FontBoldItalic(),true),2, E2_INSETS.I(1,1,10,10));
		oGridInnen.add(new MyE2_Label(this.oFU_AL_Component.get_cHerkunftsinfo_ohne_Fuhre(),true), 1, E2_INSETS.I(1,1,1,10));
		
		
		oGridInnen.add(new MyE2_Label(new MyE2_String("Neue Lieferbedingung:"),true), 1, E2_INSETS.I(1,1,10,10));
		oGridInnen.add(this.oFU_AL_Component.get_oDD_HiddenLieferbed(), 3, E2_INSETS.I(1, 1, 1, 1));
		
		oGridInnen.add(new ownButtonSave(),2, E2_INSETS.I(1, 1, 1, 1));
		oGridInnen.add(new ownButtonCancel(),2, E2_INSETS.I(1, 1, 1, 1));
		
		this.add(oGridInnen, E2_INSETS.I(10, 10, 10, 10));
		
		
		this.add_CloseActions(new ownCloseAction());
		
		//hier pruefen, ob es der mandant ist, dann abbruch
		if (this.oFU_AL_Component.get_bIS_MANDANT()) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Adresse ist eine eigene Adresse, Lieferbedingung ist nicht sinnvoll!")));
		} else {
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(250), new MyE2_String("Lieferbedingung (alternativ)"));
		}
	}
	
	

	public MyE2_MessageVector validLieferbedingung() throws myException {
		//hier pruefen, dass nur eingaben erlaubt sind, die mit einer vorhandenen lieferbedingung beginnen
		MyE2_MessageVector  oMV = new MyE2_MessageVector();

		String cActualLFBed = S.NN(this.oFU_AL_Component.get_oDD_HiddenLieferbed().get_cActualMaskValue()).trim();
		
		
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
	
	
	
	private class ownButtonSave extends MyE2_Button {
		
		public ownButtonSave() {
			super(new MyE2_String("Lieferbedingung setzen"), MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
			
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					
					MyE2_MessageVector  oMV = FU_AL_PopupEingabe.this.validLieferbedingung();
					
					if (oMV.get_bIsOK()) {
						FU_AL_PopupEingabe.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
						FU_AL_PopupEingabe.this.oFU_AL_Component.clear_Lieferbedingungen();
						FU_AL_PopupEingabe.this.oFU_AL_Component.refreshLieferbedingung();
					} else {
						bibMSG.add_MESSAGE(oMV);
					}
				}
			});
		}
		
	}
	
	private class ownButtonCancel extends MyE2_Button {
		
		public ownButtonCancel() {
			super(new MyE2_String("Abbruch"), MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
			
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					// bei cancel wird der alte zustand wieder hergestellt
					FU_AL_PopupEingabe.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			});
		}
	}
	
	
	private class ownCloseAction extends XX_ActionAgentWhenCloseWindow {
		public ownCloseAction() {
			super(FU_AL_PopupEingabe.this);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (FU_AL_PopupEingabe.this.get_cWINDOW_CLOSE_STATUS().equals(E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_CANCEL)) {
				FU_AL_PopupEingabe.this.oFU_AL_Component.get_oDD_HiddenLieferbed().set_cActualMaskValue(FU_AL_PopupEingabe.this.c_OldValueAtPopup);
				FU_AL_PopupEingabe.this.oFU_AL_Component.clear_Lieferbedingungen();
				FU_AL_PopupEingabe.this.oFU_AL_Component.refreshLieferbedingung();
				//DEBUG.System_println("Wert wird resettet ..");
			}
		}
	}
	

}

	


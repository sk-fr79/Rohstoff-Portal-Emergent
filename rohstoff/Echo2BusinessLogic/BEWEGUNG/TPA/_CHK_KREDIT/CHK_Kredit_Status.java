package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA._CHK_KREDIT;

import java.util.Vector;

import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.E2_BasicModuleContainer_PopUp_BeforeExecute;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.KUNDENSTATUS.STATKD_StatusErmittlung_Kreditversicherung;

public abstract class CHK_Kredit_Status extends E2_BasicModuleContainer_PopUp_BeforeExecute {

	private Vector<String>  vID_VPOS_TPA_FUHRE = null;
	
	
	private Vector<STATKD_StatusErmittlung_Kreditversicherung> vStatus = new Vector<STATKD_StatusErmittlung_Kreditversicherung>();
	
	private MyE2_Grid    oGridInnen = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
	
	@Override
	protected void doOwnCode_in_ok_button() throws myException {
		for (STATKD_StatusErmittlung_Kreditversicherung oStatus : vStatus){
			// Meldung mit der Warnung verschicken, dass die Fuhre bestätigt wurde.
			oStatus.warnung_FuhreBestaetigt();
		}
	}

	@Override
	protected void doOwnCode_in_cancel_button() throws myException {
	}

	@Override
	public void doBuildContent(ExecINFO oExecInfo) throws myException {
		
		this.add(this.oGridInnen, E2_INSETS.I(5,5,5,5));
		
		// Header
		GridLayoutData  oGL_Alarm = MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(0,5,0,5), new E2_ColorAlarm(), 1, 1);
		GridLayoutData  oGL_Normal = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(0,2,0,2), new E2_ColorBase(), 1, 1);
		this.oGridInnen.add(new MyE2_Label(new MyE2_String("Kreditversicherungs-Prüfung!"),new E2_FontBold(4),true), oGL_Alarm);
		
		// Texte aus den Statusobjekten lesen
		for (STATKD_StatusErmittlung_Kreditversicherung oStatus : vStatus){
			for (String s: oStatus.getMessageTexts()){
				this.oGridInnen.add(new MyE2_TextArea(s,600,999999,8), oGL_Normal);
			}
		}

//		for (String cID_Fuhre:this.vID_VPOS_TPA_FUHRE) {
//			this.add(new MyE2_Label(cID_Fuhre), E2_INSETS.I_1_1_1_1);
//		}
		
		
		this.oGridInnen.add(new MyE2_Label(new MyE2_String("Es wird eine Meldung an die zuständigen Mitarbeiter gesendet."),MyE2_Label.STYLE_NORMAL_BOLD(),true), oGL_Normal);
//		this.add(new MyE2_Label(new MyE2_String("Ok - Vorgang wird trotzdem fortgesetzt!"),MyE2_Label.STYLE_NORMAL_BOLD(),true), E2_INSETS.I_0_5_0_0);
//		this.add(new MyE2_Label(new MyE2_String("Abbrechen - Vorgang wird abgebrochen!"),MyE2_Label.STYLE_NORMAL_BOLD(),true), E2_INSETS.I_0_0_0_0);
			
		
		this.get_oButtonForOK().set_Text(new MyE2_String("Ok - Vorgang wird trotzdem fortgesetzt!"));
		this.get_oButtonForAbbruch().set_Text(new MyE2_String("Abbrechen - Vorgang wird abgebrochen!"));
		
		this.get_oButtonForOK().setFont(new E2_FontBold(2));
		//this.get_oButtonForAbbruch().set_Text(new MyE2_String("Abbrechen - Vorgang wird abgebrochen!"));
		
		this.add(new E2_ComponentGroupHorizontal(0,this.get_oButtonForOK(),this.get_oButtonForAbbruch(),E2_INSETS.I_0_2_10_2),E2_INSETS.I_5_5_5_5);
	}
	
	

	
	@Override
	public boolean makePreparationAndCheck_IF_MUST_BE_SHOWN(ExecINFO oExecInfo) throws myException {
		boolean bRueck = false;
		this.vID_VPOS_TPA_FUHRE = this.get_vID_VPOS_TPA_FUHREN();              //nur einmal abfragen
		
		if (this.get_vID_VPOS_TPA_FUHREN().size()>0) {
			
			for(String sIDFuhre: this.vID_VPOS_TPA_FUHRE){
				
				STATKD_StatusErmittlung_Kreditversicherung oStatus = new STATKD_StatusErmittlung_Kreditversicherung();
				boolean bRet = oStatus.pruefeFuhre(sIDFuhre);
				if (!bRet) {
					vStatus.add(oStatus);
				}
				bRueck |= !bRet;
			}
		} 
		
		return bRueck;
	}
	

	
	public abstract Vector<String>  get_vID_VPOS_TPA_FUHREN() throws myException;
	
}

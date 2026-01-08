package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_BASIC_InfoMessageWithAddonComponent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ComponentMAP;

/**
 * zusatz-actionagent fuer die fuhrenmaske, um steuerdefinitionen zu holen
 * @author martin
 *
 */
public class _HD_ActionAgent_FindTaxDef__FUHREN_MASKE extends _HD_ActionAgent_FindTaxDef__ABSTRACT {


//	private OwnFUComponentMAPHelper  	oFU_MAP_Helper = 	null;
	
	//2013-08-13: aus der fuhrenmaske kann der fall eintreten, dass auf einer seite schon ein preisabschluss erfolgt ist. dann 
	//            darf diese seite nicht mehr veraendert werden (steuerlich)
	private boolean bSperreEK = false;
	private boolean bSperreVK = false;
	
	
	 
	public _HD_ActionAgent_FindTaxDef__FUHREN_MASKE(boolean SperreEK, 	boolean SperreVK) {
		super();
		this.bSperreEK = SperreEK;
		this.bSperreVK = SperreVK;
	}


	@Override
	public HD_Stationen create_Stationen(ExecINFO oExecInfo) 	throws myException {
		HD_Stationen 		vHD_Stationen = 	new HD_Stationen(true);
		
		MyE2_Button 			oButtonSteuer  = 	(MyE2_Button) oExecInfo.get_MyActionEvent().getSource();
		FU_MASK_ComponentMAP  	oMAP_FU = 			(FU_MASK_ComponentMAP)oButtonSteuer.EXT().get_oComponentMAP();
	
		vHD_Stationen.add(new _HD_Station_FUHREN_MASKE(oMAP_FU, true, 	this.bSperreEK));
		vHD_Stationen.add(new _HD_Station_FUHREN_MASKE(oMAP_FU, false, this.bSperreVK));
		
		return vHD_Stationen;
	}

	
	@Override
	public void _action_if_found_uniqueDefinition(HD_WarenBewegungEinstufung oHD_Fuhreneinstufung)	throws myException {
		
		//alle vectoren durchgehen und bei allen HD_Station_ - objekten den update ausfuehren
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		oMV.add_MESSAGE(oHD_Fuhreneinstufung.get_oHD_StationEK().applyResults(oHD_Fuhreneinstufung, oHD_Fuhreneinstufung.get_cID_TAX_IN_Korrekt_UF(), oHD_Fuhreneinstufung.get_cINTRASTAT_IN(), oHD_Fuhreneinstufung.get_c_TRANSIT_EK(), true));
		oMV.add_MESSAGE(oHD_Fuhreneinstufung.get_oHD_StationVK().applyResults(oHD_Fuhreneinstufung, oHD_Fuhreneinstufung.get_cID_TAX_OUT_Korrekt_UF(), oHD_Fuhreneinstufung.get_cINTRASTAT_OUT(), oHD_Fuhreneinstufung.get_c_TRANSIT_VK(), false));

		bibMSG.add_MESSAGE(oMV);

		if (bibMSG.get_bHasAlarms()) {
			return;
		}
		

		HD_WarenBewegungEinstufungen vFuhrenEinstufung_single = new HD_WarenBewegungEinstufungen();
		vFuhrenEinstufung_single.add(oHD_Fuhreneinstufung,false);
		
		//2013-09-13: messageinfo-button in den anhang bei den meldungen reinhaengen
//		new HD_PopupZeigeFuhreneinstufung(vFuhrenEinstufung_single,
//						new MyE2_String("Es wurde der folgende Sachverhalt bei dieser Fuhre gefunden und geschrieben:"),
//						vHD_FuhrenEinstufung_VECT_SammlerAlle.size()==1);
	
		Vector<MyE2IF__Component> vMessageZusatzButtons = new Vector<MyE2IF__Component>();
		vMessageZusatzButtons.add(new ownInfoButton(vFuhrenEinstufung_single, true));
		bibMSG.add_MESSAGE(new MyE2_BASIC_InfoMessageWithAddonComponent(
				new MyE2_String("Es wurde der folgende Sachverhalt bei dieser Fuhre gefunden und geschrieben:"),
						new ownInfoButton(vFuhrenEinstufung_single, true),
						new Extent(400),
						new Extent(100)),
						true);
		
	}


	/*
	 * infobutton zum anhaengen an die info-message, damit man direkt auf die definition kommt
	 */
	private class ownInfoButton extends MyE2_Button {
		private HD_WarenBewegungEinstufungen v_FuhrenEinstufung_single = null;
		private boolean b_SchreibeFuhrenInfoInPopup = false;
		public ownInfoButton(HD_WarenBewegungEinstufungen vFuhrenEinstufung_single, boolean bSchreibeFuhrenInfoInPopup) {
			super("Anzeigen der Einstufung",MyE2_Button.StyleTextButton(Color.YELLOW, Color.LIGHTGRAY, Color.BLACK, Color.DARKGRAY));
			this.v_FuhrenEinstufung_single = vFuhrenEinstufung_single;
			this.b_SchreibeFuhrenInfoInPopup = bSchreibeFuhrenInfoInPopup;
			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					new HD_PopupZeigeFuhreneinstufung(
							ownInfoButton.this.v_FuhrenEinstufung_single,
							new MyE2_String("Es wurde der folgende Sachverhalt bei dieser Fuhre gefunden und geschrieben:"),
							ownInfoButton.this.b_SchreibeFuhrenInfoInPopup);
				}
			});
		}
	}	
	 

	@Override
	public MyE2_String get_Message_Eindeutige_Fuhreneinstufung_Kann_nicht_uebernommen_werden() {
		return new MyE2_String("Es wurden bereits abgeschlossene Preise an einigen Orten dieser Fuhre gefunden !");
	}
	
}

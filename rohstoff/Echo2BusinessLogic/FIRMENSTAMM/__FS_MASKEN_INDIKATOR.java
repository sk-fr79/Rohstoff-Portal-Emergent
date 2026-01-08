package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_USER_EXT;

/**
 * anzeigehilfe fuer die Maske, die definiert, wie die Adresse
 * bezueglich der Handelsdefinitionen eingestuft wird
 * @author martin
 *
 */
public class __FS_MASKEN_INDIKATOR extends MyE2_Grid {

	private __FS_Adress_Check  oAdressCheck = null;
	
	private int[]   iBreiten={100,250};
	
	public __FS_MASKEN_INDIKATOR() {
		super(2, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
	}

	
	
	public void REFRESH_ANZEIGE()  throws myException {
		this.oAdressCheck = new __FS_Adress_Check(this.EXT().get_oComponentMAP());
		
		this.removeAll();
		this.setSize(2);
		this.setColumnWidth(0, new Extent(iBreiten[0]));
		this.setColumnWidth(1, new Extent(iBreiten[1]));
 
		RECORD_USER_EXT  oUSER_EXT = new RECORD_USER_EXT(bibSES.get_RECORD_USER());
		
		MutableStyle  oStyleOK = new MutableStyle();
		oStyleOK.setProperty(Label.PROPERTY_FONT, new E2_FontBold());
		oStyleOK.setProperty(Label.PROPERTY_FOREGROUND, oUSER_EXT.get_COLOR_OK_INDIVID());
		oStyleOK.setProperty(Label.PROPERTY_LINE_WRAP, new Boolean(true));
		
		
		MutableStyle  oStyleERROR = new MutableStyle();
		oStyleERROR.setProperty(Label.PROPERTY_FONT, new E2_FontBold());
		oStyleERROR.setProperty(Label.PROPERTY_FOREGROUND, oUSER_EXT.get_COLOR_ERROR_INDIVID());
		oStyleERROR.setProperty(Label.PROPERTY_LINE_WRAP, new Boolean(true));
		
		
		
		//allgemeine felder
		this.add(new MyE2_Label(new MyE2_String("Aktiv:")), E2_INSETS.I(2,2,2,2));
		if (this.oAdressCheck.get_bIstAktiv()) {
			this.add(new MyE2_Label(new MyE2_String("JA"),oStyleOK,true), E2_INSETS.I(2,2,2,2));
		} else {
			this.add(new MyE2_Label(new MyE2_String("NEIN"),oStyleERROR,true), E2_INSETS.I(2,2,2,2));
		}
		
		this.add(new MyE2_Label(new MyE2_String("Wareneingang:")), E2_INSETS.I(2,2,2,2));
		if (!this.oAdressCheck.get_bWE_SPERRE()) {
			this.add(new MyE2_Label(new MyE2_String("JA"),oStyleOK,true), E2_INSETS.I(2,2,2,2));
		} else {
			this.add(new MyE2_Label(new MyE2_String("NEIN"),oStyleERROR,true), E2_INSETS.I(2,2,2,2));
		}
		
		this.add(new MyE2_Label(new MyE2_String("Warenausgang:")), E2_INSETS.I(2,2,2,2));
		if (!this.oAdressCheck.get_bWA_SPERRE()) {
			this.add(new MyE2_Label(new MyE2_String("JA"),oStyleOK,true), E2_INSETS.I(2,2,2,2));
		} else {
			this.add(new MyE2_Label(new MyE2_String("NEIN"),oStyleERROR,true), E2_INSETS.I(2,2,2,2));
		}
		
		if (this.oAdressCheck.get_bADRESSE_IST_OK()) {

			this.add(new MyE2_Label(new MyE2_String("Steuer-Status:")), E2_INSETS.I(2,2,2,2));
			this.add(new MyE2_Label(new MyE2_String("Korrekt"),oStyleOK,true), E2_INSETS.I(2,2,2,2));
		
			this.add(new MyE2_Label(new MyE2_String("Status:")), E2_INSETS.I(2,2,2,2));
			if (this.oAdressCheck.get_bADRESSE_IST_PRIVAT_HOMELAND()) {
				this.add(new MyE2_Label(new MyE2_String("Einstufung als PRIVAT, ",true,this.oAdressCheck.get_cNameHomeLand(),false),oStyleOK,true), E2_INSETS.I(2,2,2,2));
			} else if (this.oAdressCheck.get_bADRESSE_IST_PRIVAT_EU()) {
				this.add(new MyE2_Label(new MyE2_String("Einstufung als PRIVAT (EU)"),oStyleOK,true), E2_INSETS.I(2,2,2,2));
			} else if (this.oAdressCheck.get_bADRESSE_IST_PRIVAT_EX_EU()) {
				this.add(new MyE2_Label(new MyE2_String("Einstufung als PRIVAT (Ausser-EU)"),oStyleOK,true), E2_INSETS.I(2,2,2,2));
			} else if (this.oAdressCheck.get_bADRESSE_IST_FIRMA_HOMELAND()) {
				this.add(new MyE2_Label(new MyE2_String("Einstufung als FIRMA, ",true,this.oAdressCheck.get_cNameHomeLand(),false),oStyleOK,true), E2_INSETS.I(2,2,2,2));
			} else if (this.oAdressCheck.get_bADRESSE_IST_FIRMA_EU()) {
				this.add(new MyE2_Label(new MyE2_String("Einstufung als FIRMA (EU)"),oStyleOK,true), E2_INSETS.I(2,2,2,2));
			} else if (this.oAdressCheck.get_bADRESSE_IST_FIRMA_EX_EU()) {
				this.add(new MyE2_Label(new MyE2_String("Einstufung als FIRMA (Ausser-EU)"),oStyleOK,true), E2_INSETS.I(2,2,2,2));
			}
			
		} else {
			
			this.add(new MyE2_Label(new MyE2_String("Steuer-Status:")), E2_INSETS.I(2,2,2,2));
			this.add(new MyE2_Label(new MyE2_String("Nicht korrekt"),oStyleERROR,true), E2_INSETS.I(2,2,2,2));
		
			this.add(new MyE2_Label(new MyE2_String("Meldung:")), E2_INSETS.I(2,2,2,2));
			this.add(new ownInfoButton(), E2_INSETS.I(2,2,2,2));
		}
		
	}
	
	
	
	private class ownInfoButton extends MyE2_Button {

		public ownInfoButton() {
			super(new MyE2_String("Infos zu Fehlerstatus"), MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Center(new E2_FontBold(-2)));
			this.setIcon(E2_ResourceIcon.get_RI("inforound.png"));
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					new ownPOPUP_Container();
				}
			});
		}
	}
	

	private class ownPOPUP_Container extends E2_BasicModuleContainer {

		public ownPOPUP_Container() throws myException {
			super();
			MyE2_Grid oGridInfo = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
			
			MyE2_MessageVector  oMV = __FS_MASKEN_INDIKATOR.this.oAdressCheck.get_Messages_4_Bewertung();
			
			for (MyE2_Message  oMSG: oMV) {
				oGridInfo.add(oMSG.get_MessageLabel(), E2_INSETS.I(2,4,2,4));
			}
			
			this.add(oGridInfo, E2_INSETS.I(0,0,0,0));
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(300), new MyE2_String("Fehlerinformationen ..."));
		}
		
	}
	
	
}

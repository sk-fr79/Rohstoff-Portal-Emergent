package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Auswertungen.AW_CheckBox;
import panter.gmbh.Echo2.Auswertungen.AW_TextField;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter.RR__bt_callPopup;

public class AW2_BedienPanel extends E2_Grid4MaskSimple {
	//checkbox um nur die angehakten reports anzuzeigen
	private AW_CheckBox            	oCB_ZeigeNurSelektierte = new AW_CheckBox("SHOW_ONLY_SELECTED","Nur ausgewählte");

	//suchfeld und button zum ausloesen
	private AW_TextField             oTFSuchfeld = null;
	private MyE2_Button              oBTSuche   =   new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"));
	
	private AW2__Tab_Reiter 	callingTabGrid = null;
	private boolean   								is_verteiler_tab_with_all = false;
	
	public AW2_BedienPanel(AW2__Tab_Reiter p_callingTabGrid, boolean p_is_verteiler_tab_with_all) throws myException {
		super();
		
		this.oTFSuchfeld = new AW_TextField("SEARCHTEXT","", 150, 200);
		
		this.callingTabGrid = p_callingTabGrid;
		this.is_verteiler_tab_with_all = p_is_verteiler_tab_with_all;
		
		//tooltips verteilen
		this.oTFSuchfeld.setToolTipText(new MyE2_String("Suchbegriff eintragen, findet Reports mit eintragenem Text in der Beschriftung").CTrans());
		this.oCB_ZeigeNurSelektierte.setToolTipText(new MyE2_String("Wenn angehakt, dann wird nur die selektierte Auswahl an Reports angezeigt").CTrans());
		this.oBTSuche.setToolTipText(new MyE2_String("Suchbgegriff anwenden oder (bei leerem Suchbegriff) die Liste neu aufbauen").CTrans());
		
		
		this.def_(20);
		this.def_(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.def_(new Alignment(Alignment.LEFT, Alignment.CENTER));
		this.add_(this.oTFSuchfeld);
		this.add_(this.oBTSuche).def_(E2_INSETS.I(10,0,0,0));
		this.add_(this.oCB_ZeigeNurSelektierte).def_(E2_INSETS.I(2,0,0,0));
		
		this.oBTSuche.add_oActionAgent(new actionRefreshCallingGrid());
		this.oCB_ZeigeNurSelektierte.add_oActionAgent(new actionRefreshCallingGrid());

		boolean is_super = bibALL.get_RECORD_USER().is_DEVELOPER_YES() ||  bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES(); 

		
		if (this.is_verteiler_tab_with_all) {
			//dann den verwaltungsbutton fuer die reportgruppen einblenden
			this.def_(120).def_(E2_INSETS.I(10,0,0,0)).add_(new RR__bt_callPopup(this.callingTabGrid.get_CallingTabContainer()));
		} else {
			if (is_super) {
				this.def_(120).def_(new Alignment(Alignment.CENTER, Alignment.TOP)).
				add_(new AW2_SORT_bt_OrderList(this.callingTabGrid.get_recRReiter(),this.callingTabGrid));
			}
		}

		
	}

	
	private class actionRefreshCallingGrid extends XX_ActionAgent 	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 	{
			AW2_BedienPanel.this.callingTabGrid._refresh_ButtonAnzeige();
		}
	}


	public AW_CheckBox get_cb_ZeigeNurSelektierte() {
		return oCB_ZeigeNurSelektierte;
	}


	public AW_TextField get_tfSuchfeld() {
		return oTFSuchfeld;
	}


	public MyE2_Button get_btSuche() {
		return oBTSuche;
	}

	
	
}

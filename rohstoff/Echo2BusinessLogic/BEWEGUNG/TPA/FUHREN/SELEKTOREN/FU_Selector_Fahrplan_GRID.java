package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MASCHINEN;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class FU_Selector_Fahrplan_GRID extends MyE2_Grid {

	private FU_Selector_Fahrplan_FP_Button 	o_FP_B = null;
	private MyE2_Button  					bt_Clean = new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"),true, new MyE2_String("Die Fahrplanbedingung aus der Selektion entfernen"),null);
	
	
	
	public FU_Selector_Fahrplan_GRID(FU_Selector_Fahrplan_FP_Button oFP_Button) throws myException {
		super(4, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
		this.o_FP_B = oFP_Button;
		this.bt_Clean.add_oActionAgent(new ownActionClean());
		this.fill_Grid();
	}

	
	public void fill_Grid() throws myException {
		this.removeAll();
		this.add(this.o_FP_B, E2_INSETS.I(1,1,1,1));
		if (S.isFull(this.o_FP_B.get_cID_SELECTED_LKW()) && S.isFull(this.o_FP_B.get_cSELECTED_DATE())) {
			RECORD_MASCHINEN recLKW = new RECORD_MASCHINEN(this.o_FP_B.get_cID_SELECTED_LKW());
			
			this.add(new MyE2_Label(new MyE2_String(recLKW.get_KFZKENNZEICHEN_cUF_NN("<kennzeichen>"),false),new E2_FontItalic(-2)), E2_INSETS.I(1,1,1,1));
			this.add(new MyE2_Label(new MyE2_String(this.o_FP_B.get_cSELECTED_DATE()),new E2_FontItalic(-2)), E2_INSETS.I(1,1,1,1));
			this.add(this.bt_Clean, E2_INSETS.I(1,1,1,1));
		}
	}
	
	
	private class ownActionClean extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FU_Selector_Fahrplan_GRID.this.o_FP_B.ClearSelection();
		}
	}


	public MyE2_Button get_BT_Clean() {
		return bt_Clean;
	}
	
	
}

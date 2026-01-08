package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.PROFORMA_RECHNUNG;

import java.util.Vector;

import nextapp.echo2.app.Extent;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class FU_PRO_Action_SucheAlteLieferbedingungen extends XX_ActionAgent {

	private MyE2_TextField   	oTF_Lieferbedingungen = null;
	
	
	public FU_PRO_Action_SucheAlteLieferbedingungen(MyE2_TextField tf_Lieferbedingungen) {
		super();
		this.oTF_Lieferbedingungen = tf_Lieferbedingungen;
	}
	
	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		new ownPopup();
	}


	private class ownPopup extends E2_BasicModuleContainer {
		
		public ownPopup() throws myException {
			super();
			
			this.set_bVisible_Row_For_Messages(true);
			
			String[][] arrLieferbedingungen = bibDB.EinzelAbfrageInArray(
					"SELECT DISTINCT "+_DB.PROFORMA_RECHNUNG$LIEFERBEDINGUNGEN+" FROM "+
							bibE2.cTO()+"."+_DB.PROFORMA_RECHNUNG+" WHERE "+_DB.PROFORMA_RECHNUNG$LIEFERBEDINGUNGEN+" IS NOT NULL ORDER BY 1", 
					"-");
			
			
			Vector<ownButton>  vOwnButton = new Vector<FU_PRO_Action_SucheAlteLieferbedingungen.ownButton>();
			vOwnButton.add(new ownButton("-","",this));
			
			if (arrLieferbedingungen != null && arrLieferbedingungen.length>0) {
				for (int i=0;i<arrLieferbedingungen.length;i++) {
					vOwnButton.add(new ownButton(arrLieferbedingungen[i][0],arrLieferbedingungen[i][0],this));
				}
			}
			
			MyE2_Grid oGrid = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			for (ownButton oButton: vOwnButton) {
				oGrid.add(oButton, E2_INSETS.I_0_0_0_0);
			}
			
			this.add(oGrid, E2_INSETS.I_2_2_2_2);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(300), new Extent(400), new MyE2_String("Bisher in Proforma-Rech."));
		}

	}
	
	
	private class ownButton extends MyE2_Button {
		
		private E2_BasicModuleContainer  oContainer = null;
		

		public ownButton(String cText, String Value, E2_BasicModuleContainer Container) {
			super(new MyE2_String(cText,false), MyE2_Button.StyleButtonCenteredWithDDarkBorder());
			this.EXT().set_C_MERKMAL(Value);
			this.oContainer = Container;
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					MyE2_Button oThis = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();
					FU_PRO_Action_SucheAlteLieferbedingungen.this.oTF_Lieferbedingungen.setText(oThis.EXT().get_C_MERKMAL());
					ownButton.this.oContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			});
		}
		
	}
	
}

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
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.ECHO2.SEARCH_Adress;

public class FU_PRO_Action_SucheAlteAdressen extends XX_ActionAgent {

	private SEARCH_Adress   	oSearchAdress = null;
	private String 				cID_Adresse_FieldName = null;
	
	public FU_PRO_Action_SucheAlteAdressen(SEARCH_Adress o_SearchAdress, String cID_ADRESSE_FieldName) {
		super();
		this.oSearchAdress = 			o_SearchAdress;
		this.cID_Adresse_FieldName = 	cID_ADRESSE_FieldName;
	}
	
	
	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		new ownPopup();
	}


	private class ownPopup extends E2_BasicModuleContainer {
		
		public ownPopup() throws myException {
			super();
			
			this.set_bVisible_Row_For_Messages(true);
			
			String cADRFeld = FU_PRO_Action_SucheAlteAdressen.this.cID_Adresse_FieldName;
			
			String[][] arrLieferbedingungen = bibDB.EinzelAbfrageInArray(
					"SELECT DISTINCT "+
							"NVL(JT_ADRESSE.NAME1,'')||' '||NVL(JT_ADRESSE.NAME2,'')||' '||NVL(JT_ADRESSE.ORT,'')"+","+
							_DB.PROFORMA_RECHNUNG+"."+cADRFeld+"" +
							" FROM "+
							bibE2.cTO()+"."+_DB.PROFORMA_RECHNUNG+
							" INNER JOIN "+bibE2.cTO()+".JT_ADRESSE " +
							" ON ("+_DB.PROFORMA_RECHNUNG+"."+cADRFeld+" = JT_ADRESSE.ID_ADRESSE) "+
							" WHERE "+_DB.PROFORMA_RECHNUNG+"."+cADRFeld+" IS NOT NULL ORDER BY 1", 
					"-");
			
			
			Vector<ownButton>  vOwnButton = new Vector<FU_PRO_Action_SucheAlteAdressen.ownButton>();
			vOwnButton.add(new ownButton("-","",this));
			
			if (arrLieferbedingungen != null && arrLieferbedingungen.length>0) {
				for (int i=0;i<arrLieferbedingungen.length;i++) {
					vOwnButton.add(new ownButton(arrLieferbedingungen[i][0],arrLieferbedingungen[i][1],this));
					if (i>100) {
						break;
					}
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
					FU_PRO_Action_SucheAlteAdressen.this.oSearchAdress.fill_MaskText_And_Label(oThis.EXT().get_C_MERKMAL());
					ownButton.this.oContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
			});
		}
		
	}
	
}

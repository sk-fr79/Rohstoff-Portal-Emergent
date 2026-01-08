package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2IF_DB_SimpleComponent;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

public abstract class AH7_LIST_ShowAdress extends E2_Grid implements MyE2IF_DB_SimpleComponent, E2_IF_Copy {

	private IF_Field  field = null;
	
	public abstract void renderAdress(Rec21 adress) throws myException;
	
	public AH7_LIST_ShowAdress() {
		super();
	}

	
	public AH7_LIST_ShowAdress _setField(IF_Field f) {
		this.field = f;
		return this;
	}
	
	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) 	throws myException {
		String id_adress = oResultMAP.get_UnFormatedValue(this.getField().fn());
		if (S.isFull(id_adress)) {
			Rec21 adress = new Rec21(_TAB.adresse)._fill_id(id_adress);
			this.renderAdress(adress);
		} else {
			this._clear();
		}
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		
	}

	public IF_Field getField() {
		return field;
	}

	public void setField(IF_Field f) {
		this.field = f;
	}


	@Override
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException {
		this.setEnabled(true);
	}
	

	public XX_ActionAgent getActionToRefreshNaviList()  {
		return new ownAction();
	}
	
	private class ownAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			AH7_LIST_ShowAdress.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to()._REBUILD_ACTUAL_SITE("");
		}
	}



}

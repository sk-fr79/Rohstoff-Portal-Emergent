package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Button;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class REP_VER_LIST_groupByUuid extends MyE2_DB_Button {

	public REP_VER_LIST_groupByUuid(SQLField osqlField) throws myException {
		super(osqlField);
		this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());

		this.add_oActionAgent(new ownActionAgent());
		this._ttt(S.ms("Zeige in der Liste alle Reports zu diesem Block").CTrans());
	}

	@Override
	public Object get_Copy(Object ob) throws myExceptionCopy {
		REP_VER_LIST_groupByUuid oButtCopy = null;

		try 
		{
			oButtCopy = new REP_VER_LIST_groupByUuid(this.EXT_DB().get_oSQLField());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
		return oButtCopy;
	}

//	@Override
//	public void set_cActualMaskValue(String cText) throws myException {
////		if(S.isFull(cText)) {
////			this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to().get_vActualID_Segment();
////		}
//	}
	
	private class ownActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			REP_VER_LIST_groupByUuid oThis = REP_VER_LIST_groupByUuid.this;

			E2_NavigationList naviList = oThis.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();

			Rec21 rec_base = new Rec21(_TAB.report_log)._fill_id(oThis.get_cActualRowID());

			SEL reclist_query = new SEL().FROM(_TAB.report_log).WHERE(new vgl(REPORT_LOG.report_uuid, rec_base.get_ufs_dbVal(REPORT_LOG.report_uuid)));
			RecList21 reclist_uuid = new RecList21(_TAB.report_log)._fill(reclist_query.s());

			if(reclist_uuid.size()>0) {
				naviList.get_oComponentMAP__REF().get_oSQLFieldMAP().clear_ORDER_SEGMENT();
				naviList.get_oComponentMAP__REF().get_oSQLFieldMAP().add_ORDER_SEGMENT(REP_VER_CONST.UUID_ORDER_BY);

				naviList.get_vActualID_Segment().removeAllElements();
				naviList.get_vActualID_Segment().addAll(reclist_uuid.keySet());

				naviList._REBUILD_ACTUAL_SITE("");
			}
		}
	}


}

/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4Confirm;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_STATUSRELATION;

/**
 * @author martin
 *
 */
public class AH7_bt_deleteInactiveRelations extends E2_Button {

	
	private E2_NavigationList naviList=null;

	/**
	 * @throws myException 
	 * 
	 */
	public AH7_bt_deleteInactiveRelations(E2_NavigationList p_naviList) throws myException {
		super();
		this.naviList = p_naviList;
		this._tr("Inaktive AH7-Druck-Steuerdatensätze löschen")._lw()._width(200)._s_BorderText()._ttt("Alle AH7-Druck-Steuereinträge, die inaktiv (rot oder gelb) sind, löschen !");
		
		this.add_oActionAgent(new ownAction());
		this._aaa(()->{this.naviList._REBUILD_COMPLETE_LIST("");});
		this.add_GlobalValidator(ENUM_VALIDATION.AH7_STEUERDATEI_DELETE.getValidatorWithoutSupervisorPersilschein());
		this.setBreak4PopupController(new OwnBreakController());
	}

	
	private class ownAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			And and = new 	And(	new vgl(AH7_STEUERDATEI.status_relation,COMP.NOT_EQ,AH7__ENUM_STATUSRELATION.ACTIVE.db_val()))
							.and(   new vglParam(AH7_STEUERDATEI.id_mandant))
							;
			
			SqlStringExtended sqlExt = new SqlStringExtended("DELETE FROM "+bibE2.cTO()+"."+_TAB.ah7_steuerdatei.fullTableName()+" WHERE "+and.s());
			sqlExt._addParameters(new VEK<ParamDataObject>()._a(new Param_Long(new Long(bibALL.get_ID_MANDANT()))));
			
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			
			oDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(sqlExt.generatePreparedStatement(oDB), true);
			bibALL.destroy_myDBToolBox(oDB);
		}
	}

	private class OwnBreakController extends E2_Break4PopupController {
		public OwnBreakController() {
			super();
			Break4Confirm confirm = new Break4Confirm();
			confirm._setWindowTitle(S.ms("Löschen bestätigen ..."));
			confirm._setTitle(S.ms("Alle NICHT aktiven Relationen löschen ?"));
			confirm._setTextForYes(S.ms("JA - löschen"));
			this._registerBreak(confirm);
		}
	}
	
	
}

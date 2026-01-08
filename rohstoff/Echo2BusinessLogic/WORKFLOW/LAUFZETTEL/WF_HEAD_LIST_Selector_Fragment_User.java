package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT.WF_ListSelektor_3_super_user_selektor;

/**
 * DropDown-Selektor für den Benutzer
 * @author manfred
 * 
 */
public class WF_HEAD_LIST_Selector_Fragment_User  extends XX_ListSelektor{

		private WF_HEAD_LIST_Selection_User_DropDown 	oSelectUser = null;
		private MyE2_Button      						bt_me 		= new MyE2_Button(E2_ResourceIcon.get_RI("user-mini.png"));
		
		
		public WF_HEAD_LIST_Selector_Fragment_User() throws myException {
			oSelectUser = new WF_HEAD_LIST_Selection_User_DropDown(false, 290);
			bt_me.add_oActionAgent(new cActionSelectMe());
		}
		
		@Override
		public String get_WhereBlock() throws myException {
			// ausgewählten User ermitteln
			String idUser = oSelectUser.get_ActualWert();

			String sSelectOwnActiveOnly = "";
			if (!bibALL.isEmpty(idUser) ){
				sSelectOwnActiveOnly =
								" JT_LAUFZETTEL.ID_LAUFZETTEL IN ( "+
								" 		SELECT L.ID_LAUFZETTEL "+
								" 		FROM   JT_LAUFZETTEL L LEFT OUTER JOIN JT_LAUFZETTEL_EINTRAG E ON L.ID_LAUFZETTEL = E.ID_LAUFZETTEL "+
								" 		WHERE  ( " +
							    "  						E.ID_USER_BESITZER = "+ idUser + 
							    " 						OR E.ID_USER_BEARBEITER = "+ idUser +
							    " 						OR E.ID_USER_BEARBEITER = "+ idUser +
							    " 						OR E.ID_USER_ABGESCHLOSSEN_VON = "+ idUser +
							    " 						OR L.ID_USER_BESITZER = "+ idUser +
							    " 						OR L.ID_USER_SUPERVISOR = "+ idUser +
							    " 						OR L.ID_USER_ABGESCHLOSSEN_VON = "+ idUser +
							    
								" 				) " +
								"	) ";
			}
			return sSelectOwnActiveOnly;
		}

		
		@Override
		public Component get_oComponentForSelection() throws myException {
			MyE2_Grid oGridRueck = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			oGridRueck.add(this.oSelectUser);
			oGridRueck.add(this.bt_me);
			return oGridRueck;
		}

		@Override
		public Component get_oComponentWithoutText() throws myException {
			return get_oComponentForSelection();
		}

		@Override
		public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
			oSelectUser.add_oActionAgent(oAgent);
			bt_me.add_oActionAgent(oAgent);
			
		}

		@Override
		public void doInternalCheck() {
			
		}
		
		
		/**
		 * Referenz auf das Auswahlfeld
		 * @return
		 */
		public MyE2_SelectField get_SelectField(){
			return oSelectUser;
		}
		
		

		/**
		 * Actionagent für die Selektion des angemeldeten Benutzers oder leerem Feld
		 * @author manfred
		 * @date 29.06.2016
		 *
		 */
		private class cActionSelectMe extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (S.isEmpty(WF_HEAD_LIST_Selector_Fragment_User.this.oSelectUser.get_ActualWert())) {
					WF_HEAD_LIST_Selector_Fragment_User.this.oSelectUser.set_ActiveValue_OR_FirstValue(bibALL.get_ID_USER());
				} else {
					WF_HEAD_LIST_Selector_Fragment_User.this.oSelectUser.set_ActiveValue_OR_FirstValue("");
				}
			}
		}
	

}

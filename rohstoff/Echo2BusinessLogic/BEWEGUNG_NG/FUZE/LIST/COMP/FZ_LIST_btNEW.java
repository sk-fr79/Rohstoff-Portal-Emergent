package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.COMP;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Decision.ActionAgent_4_decision;
import panter.gmbh.Echo2.ActionEventTools.Decision.IF_components_4_decision;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_MASK_TYPE;

/**
 * eine komponente mit vorselektion und button zur neueingabe
 * @author martin
 *
 */
public class FZ_LIST_btNEW extends E2_Grid {

	
	private ownNewButton       		bt_startNew = null;
	private MyE2_SelectField       	sel_NewType = null;
	
	private E2_NavigationList      naviList = null;
	
	
	public FZ_LIST_btNEW(E2_NavigationList  p_naviList) throws myException {
//		super(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.naviList = p_naviList;
		this.bt_startNew = new ownNewButton();
		this.sel_NewType = new MyE2_SelectField(ENUM_MASK_TYPE.get_ddArray(true), "", false, new Extent(190));
		
		this._st(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS())._s(2);
		
		this._a(this.sel_NewType, new RB_gld()._ins(2));
		this._a(this.bt_startNew, new RB_gld()._ins(2));
		
		this.bt_startNew.add_oActionAgent(new ownActionWithPopup(this.bt_startNew),true);
	}
	
	

	
	private class ownNewButton extends MyE2_Button implements IF_components_4_decision {
		
		private Vector<XX_ActionAgent>  v_save_agents = new Vector<XX_ActionAgent>();   //zur zwischenspeicherung
		private Vector<XX_ActionAgent>  v_save_agents_at_start = new Vector<XX_ActionAgent>();   //falls ein button mehrmals aufgerufen wird, wird bei ersten aufruf hier der status am start gespeichert
		
		public ownNewButton() throws myException {
			super(E2_ResourceIcon.get_RI("new.png"),true);
		}

		@Override
		public Vector<XX_ActionAgent> get_storage_vector_4_action_agents() throws myException {
			return this.v_save_agents;
		}

		@Override
		public Vector<XX_ActionAgent> get_storage_vector_4_status_at_start() throws myException {
			return this.v_save_agents_at_start;
		}
	}
	
	
	/*
	 * actionagent mit der moeglichkeit, ein auswahlfenster einzublenden
	 */
	private class ownActionWithPopup extends ActionAgent_4_decision {

		public ownActionWithPopup(IF_components_4_decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		public Boolean make_decision_true_4_special__false_4_normal(	Vector<XX_ActionAgent> v_addon_actions_when_false) throws myException {
			boolean b_rueck = true;
			
			FZ_LIST_btNEW oThis = FZ_LIST_btNEW.this;
			
			String c_maskTyp = oThis.sel_NewType.get_ActualWert();
			//wenn im selektor etwas ausgewaehlt war, dann direkt starten
			if (S.isFull(c_maskTyp)) {
				ENUM_MASK_TYPE en_type = ENUM_MASK_TYPE.find_typ(c_maskTyp);
				if (en_type != null) {
					XX_ActionAgent  agent4new = ENUM_MASK_TYPE.generate_NewAgent(en_type,FZ_LIST_btNEW.this.naviList);
					if (agent4new != null) {
						v_addon_actions_when_false.add(agent4new);
						b_rueck = false;   //hier wird der start direkt durchgereicht
					} else {
						throw new myException("Error: "+en_type.user_text()+" not implemented yet");
					}
				}
			}
			return b_rueck;    // ist genau ein type selektiert, dann steht der typ fest
		}

		@Override
		public void generate_fill_and_show_popup(	IF_components_4_decision activeComponent,
													Vector<XX_ActionAgent> v_action_agent_standard)
													throws myException {
			
			ownBasicContainer  container = new ownBasicContainer();
			//fuer jeden fuhrentyp eine Checkbox einblenden
			MyE2_Grid  gridStartButtons = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			
			for (ENUM_MASK_TYPE typ: ENUM_MASK_TYPE.values()) {
				if (typ.getVectorType().isEditable()) {
					//fuer jedem editierbaren type einene button erzeugen und mit den agents versehen
					MyE2_Button  start = new MyE2_Button(typ.user_text_lang());
					
					XX_ActionAgent  newAgent = ENUM_MASK_TYPE.generate_NewAgent(typ, FZ_LIST_btNEW.this.naviList);
					if (newAgent != null) {
						//ein button im popup wird nur erzeugt, wenn im MASK-TYP-enum eine actioagent zugeordnet ist
						start.add_oActionAgent(newAgent);
						start.add_oActionAgent(this.generate_close_window_action(container,true));
						gridStartButtons.add(start, MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(3,2,3,2)));
					}
				}
			}
			
			MyE2_Button cancel = new MyE2_Button(new MyE2_String("Abbruch"), MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder(new E2_FontBold()));
			cancel.add_oActionAgent(this.generate_close_window_action(container, false));
			gridStartButtons.add(cancel, MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(3,10,3,2)));
			

			container.add(gridStartButtons,E2_INSETS.I(2,2,2,2));
			container.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(400), new MyE2_String("Bitte wählen Sie einen Bewegungstyp..."));
		}

		
	}

	
	
	
	
	
	private class ownBasicContainer extends E2_BasicModuleContainer {
	}
	
}

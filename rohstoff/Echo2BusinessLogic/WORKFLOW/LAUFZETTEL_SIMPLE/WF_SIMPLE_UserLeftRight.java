/**
 * rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE
 * @author manfred
 * @date 03.04.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;

import java.util.Vector;

import org.jfree.ui.Align;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.HIGHLEVEL.USER_LEFT_RIGHT.V21.ULR_UserLeftRight_v21;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.LeftRightSelect2.LR_CB2;
import panter.gmbh.Echo2.components.LeftRightSelect2.LR_ObjectExtender;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_user;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.RecList21_User;

/**
 * @author manfred
 * @date 03.04.2019
 *
 */
public class WF_SIMPLE_UserLeftRight extends ULR_UserLeftRight_v21 {

	/**
	 * @author manfred
	 * @date 03.04.2019
	 *
	 * @throws myException
	 */
	public WF_SIMPLE_UserLeftRight() throws myException {
		super();
		
		// default-User-list mit allen aktiven Usern
		SqlStringExtended sql = RecList21_User.getSqlExt_Default(true, false);
		
		this._init(sql, null);
		this.set_style_and_size(MyE2_Grid.STYLE_GRID_DDARK_BORDER(), 400,390,150);
	}




	@Override
	public Vector<MyRECORD_IF_FILLABLE> maskContents_Transfer_To_Record_And_Prepare_Save(MyE2_MessageVector oMV,
			RB_ComponentMap rb_maskThisBelongsTo) throws myException {

		return null;
	}


	@Override
	public Component component_4_list(LR_CB2 cb, LR_ObjectExtender place_4_everything) throws myException {
		return cb;

//		BSP:
//		if (cb.button_is_right_side()) {
//			REM_MASK_UserLeftRight_kapsel kaps = (REM_MASK_UserLeftRight_kapsel)place_4_everything;
//			E2_Grid4MaskSimple  gm = new E2_Grid4MaskSimple()._add(cb,new RB_gld()._ins(E2_INSETS.I(2,1,2,1)))
//																._add(kaps.get_cb_change(),new RB_gld()._ins(E2_INSETS.I(2,1,2,1)))
//																._add(kaps.get_cb_close(),new RB_gld()._ins(E2_INSETS.I(2,1,2,1)))
//																._add(kaps.get_cb_Sofortnachricht(),new RB_gld()._ins(E2_INSETS.I(2,1,2,1)))
//																._add(kaps.get_cb_sendMail(),new RB_gld()._ins(E2_INSETS.I(2,1,2,1)))
//																._setSize(300,60,60,60,60);
//			return gm;
//		} 

	}


	@Override
	public void rb_set_db_value_manual(String id_main_table) throws myException {
		
		//fuellen der komponente
		this.get_v_cb_left().clear();
		this.get_v_cb_right().clear();
		
		//alle momentan dieser haupttabelle zugeordneten kreuztabellen-werte 
		this.get_vector_defined_crosstable_after_load().clear();
		if (S.isFull(id_main_table)) {
			//	z.B. füllen was in der DB gesetzt ist... 
			//	this.get_vector_defined_crosstable_after_load().addAll(this.query_rosstable_records_to_main_id(id_main_table));
		}
	
		
		//zuerst die komplette auswahl links
		for (String id: this.get_rl_user_pool().keySet() ) {
			Rec21 r = this.get_rl_user_pool().get(id);
			Rec21_user ru = new Rec21_user(r);
			
			LR_CB2 	cb_left = new LR_CB2(this);
			cb_left.set_right_side(false);
			cb_left.set_place_4_everything(new WF_SIMPLE_UserLeftRightKapsel(ru));
			cb_left.setSelected(false);
			this.get_v_cb_left().add(cb_left);
		}

		this.refresh_left();
		this.refresh_right();
	}

	
	@Override
	public void add_content_before_left_right_panel() throws myException {
		E2_Grid 	grid_left = new E2_Grid()._setSize(30,30,200);
		E2_Button 	btnAddAll = new E2_Button();
		E2_Button   btnAddMe  = new E2_Button();
		
		btnAddAll	._image(E2_ResourceIcon.get_RI("to_right_dbl.png") , true)
					._ttt("Alle Benutzer auswählen");
		
		btnAddMe	._image(E2_ResourceIcon.get_RI("user-mini.png") , true)
					._ttt("Selektiert den aktuellen Anwender");

		grid_left	._a(btnAddAll,new RB_gld()._al(Alignment.ALIGN_LEFT))
					._a(btnAddMe, new RB_gld()._al(Alignment.ALIGN_LEFT))
					._a(new MyE2_Label(new MyE2_String("Benutzer")))
					;
	
		this.add(grid_left,E2_INSETS.I(3,3,3,3));
		
		E2_Grid grid_right = new E2_Grid()._setSize(30,200);
		E2_Button 	btnRemoveAll = new E2_Button();
		btnRemoveAll._image(E2_ResourceIcon.get_RI("to_left_dbl.png") , true)
					._ttt("Alle ausgewählten Benutzer löschen");
		
		grid_right	._a(btnRemoveAll,new RB_gld()._al(Alignment.ALIGN_LEFT))
					._a(new MyE2_Label(new MyE2_String("Ausgewählte Benutzer")))
					;

		
		this.add(grid_right,E2_INSETS.I(3,3,3,3));

		
		btnAddAll._aaa(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				for(LR_CB2 cb: get_v_cb_left()){
					cb.setSelected(true);
					cb.doActionPassiv();
				}
			}
		});
		
		btnAddMe._aaa(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				for(LR_CB2 cb: get_v_cb_left()){
						WF_SIMPLE_UserLeftRightKapsel kapsel =(WF_SIMPLE_UserLeftRightKapsel)cb.get_place_4_everything();
						if (kapsel.get_rec_user().get_key_value().equals(bibALL.get_RECORD_USER().get_ID_USER_cUF())) {
							if (!cb.isSelected()){
								cb.setSelected(true);
								cb.doActionPassiv();
							}
						}
				}
			}
		});
		

		btnRemoveAll._aaa(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				while (!get_v_cb_right().isEmpty()){
					LR_CB2 cb = get_v_cb_right().lastElement();
					cb.setSelected(false);
					cb.doActionPassiv();
				}
			}
		});
		
	}


	@Override
	public void add_content_after_left_right_panel() throws myException {
	}




	@Override
	public void rb_set_belongs_to(RB_ComponentMap obj) throws myException {
		// TODO Auto-generated method stub
		
	}




	@Override
	public RB_ComponentMap rb_get_belongs_to() throws myException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public RB_K getMyKeyInCollection() throws myException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public RB_K setMyKeyInCollection(RB_K key) throws myException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Vector<Rec21_user> query_rosstable_records_to_main_id(String id_bordercrossing) throws myException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Rec21_user extract_user_from_crosstable(Rec21_user rec_cross_tab) throws myException {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Blind schalten, da nur bei Neuanlage wichtig
	 */
	@Override
	public Vector<MySqlStatementBuilder> get_vSQL_StatementBuilders_Others(MyE2_MessageVector oMV,
			RB_ComponentMap rb_maskThisBelongsTo) throws myException {
		return null;
	}



}

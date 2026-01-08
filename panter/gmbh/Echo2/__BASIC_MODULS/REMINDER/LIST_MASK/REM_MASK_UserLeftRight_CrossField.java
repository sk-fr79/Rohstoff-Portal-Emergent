package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import java.util.Comparator;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.HIGHLEVEL.USER_LEFT_RIGHT.ULR_UserLeftRight;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Complex;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.LeftRightSelect2.LR_CB2;
import panter.gmbh.Echo2.components.LeftRightSelect2.LR_IF_wrap_Component;
import panter.gmbh.Echo2.components.LeftRightSelect2.LR_ObjectExtender;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_USER;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REMINDER_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_REMINDER_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_FILLABLE;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;


public class REM_MASK_UserLeftRight_CrossField extends ULR_UserLeftRight implements IF_RB_Component_Complex, LR_IF_wrap_Component {


	
	public REM_MASK_UserLeftRight_CrossField() throws myException {
		super();
		SEL selUser = new SEL(_TAB.user)
						.FROM(_TAB.user)
						.WHERE(USER.id_mandant, bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF())
						.AND(new vgl_YN(USER.aktiv,true))
						.ORDERUP(USER.name1).ORDERUP(USER.kuerzel);
		
		
		this._init(selUser, REMINDER_USER.id_reminder_def);
		this.set_style_and_size(MyE2_Grid.STYLE_GRID_DDARK_BORDER(), 250,540,300);
	}

	
	@Override
	public Vector<MyRECORD_IF_FILLABLE> maskContents_Transfer_To_Record_And_Prepare_Save(MyE2_MessageVector oMV, RB_ComponentMap rb_maskThisBelongsTo) throws myException {
		
		Vector<MyRECORD_IF_FILLABLE> v_inserts_rueck = new Vector<>();
		
		//alle rechten speichern
		for (LR_CB2 caps: this.get_v_cb_right()) {
			REM_MASK_UserLeftRight_kapsel u_caps = (REM_MASK_UserLeftRight_kapsel)caps.get_place_4_everything();
			RECORD_USER rec_user = u_caps.get_rec_user();
			
			RECORDNEW_REMINDER_USER rn = new RECORDNEW_REMINDER_USER();
			
			rn.set_NEW_VALUE_ID_USER(rec_user.get_ID_USER_cF());
			rn.set_NEW_VALUE_ALLOW_CHANGE(u_caps.get_cb_change().isSelected()?"Y":"N");
			rn.set_NEW_VALUE_ALLOW_CLOSE(u_caps.get_cb_close().isSelected()?"Y":"N");
			rn.set_NEW_VALUE_SOFORTANZEIGE(u_caps.get_cb_Sofortnachricht().isSelected()?"Y":"N");
			rn.set_NEW_VALUE_SEND_MAIL(u_caps.get_cb_sendMail().isSelected()?"Y":"N");
			
			v_inserts_rueck.add(rn);
		}
		
		return v_inserts_rueck;
	}

	
	public Vector<MyRECORD> query_rosstable_records_to_main_id(String id_reminder_def) throws myException {
		
		Vector<MyRECORD> v = new Vector<MyRECORD>();
		if (S.isFull(id_reminder_def)) {
			SEL sel = new SEL(_TAB.reminder_user).FROM(_TAB.reminder_user).WHERE(REMINDER_USER.id_reminder_def,id_reminder_def);
			v.addAll(new RECLIST_REMINDER_USER(sel.s()).values());
		}
		return v;
	}

	public RECORD_USER extract_user_from_crosstable(MyRECORD rec_cross_tab) throws myException {
		return ((RECORD_REMINDER_USER)rec_cross_tab).get_UP_RECORD_USER_id_user();
	}



	@Override
	public Component component_4_list(LR_CB2 cb, LR_ObjectExtender place_4_everything) throws myException {
		
		if (cb.button_is_right_side()) {
			REM_MASK_UserLeftRight_kapsel kaps = (REM_MASK_UserLeftRight_kapsel)place_4_everything;
			E2_Grid4MaskSimple  gm = new E2_Grid4MaskSimple()._add(cb,new RB_gld()._ins(E2_INSETS.I(2,1,2,1)))
																._add(kaps.get_cb_change(),new RB_gld()._ins(E2_INSETS.I(2,1,2,1)))
																._add(kaps.get_cb_close(),new RB_gld()._ins(E2_INSETS.I(2,1,2,1)))
																._add(kaps.get_cb_Sofortnachricht(),new RB_gld()._ins(E2_INSETS.I(2,1,2,1)))
																._add(kaps.get_cb_sendMail(),new RB_gld()._ins(E2_INSETS.I(2,1,2,1)))
																._setSize(300,60,60,60,60);
			return gm;
		} 

		return cb;
	}

	
	@Override
	public void rb_set_db_value_manual(String id_main_table) throws myException {
		
		
		//fuellen der komponente
		this.get_v_cb_left().clear();
		this.get_v_cb_right().clear();
		
		//alle momentan dieser haupttabelle zugeordneten kreuztabellen-werte 
		this.get_vector_defined_crosstable_after_load().clear();
		if (S.isFull(id_main_table)) {
			this.get_vector_defined_crosstable_after_load().addAll(this.query_rosstable_records_to_main_id(id_main_table));
		}
	
		
		//zuerst die komplette auswahl links
		for (String id: this.get_rl_user_pool().get_vKeyValues()) {
			RECORD_USER ru = this.get_rl_user_pool().get(id);
			LR_CB2 	cb_left = new LR_CB2(this);
			cb_left.set_right_side(false);
			cb_left.set_place_4_everything(new REM_MASK_UserLeftRight_kapsel(ru));
			cb_left.setSelected(false);
			this.get_v_cb_left().add(cb_left);
		}

		
		//den vector der geladenen benutzer sortieren
		Vector<MyRECORD> v_loaded = new Vector<MyRECORD>();
		v_loaded.addAll(this.get_vector_defined_crosstable_after_load());
		v_loaded.sort(new Comparator<MyRECORD>() {
			@Override
			public int compare(MyRECORD o1, MyRECORD o2) {
				try {
					RECORD_USER ru1 = ((RECORD_REMINDER_USER)o1).get_UP_RECORD_USER_id_user();
					RECORD_USER ru2 = ((RECORD_REMINDER_USER)o2).get_UP_RECORD_USER_id_user();
					return ru1.get_NAME1_cUF_NN("").compareTo(ru2.get_NAME1_cUF_NN(""));
				}
				catch (myException e) {
					e.printStackTrace();
				}
				
				return 0;
			}
		});
		
	
		//jetzt die rechten (zugeordnet) sammlungen fuellen
		for  (MyRECORD rec: v_loaded) {
			RECORD_USER user = this.extract_user_from_crosstable(rec);
			RECORD_REMINDER_USER rec_cross = (RECORD_REMINDER_USER)rec;
			
			//diesen user im linken "Pool" finden und kopieren, auf beiden seiten anhaken
			for (LR_CB2 cb_left: this.get_v_cb_left()) {
				RECORD_USER user2 = ((REM_MASK_UserLeftRight_kapsel)cb_left.get_place_4_everything()).get_rec_user();
				REM_MASK_UserLeftRight_kapsel kaps = (REM_MASK_UserLeftRight_kapsel)cb_left.get_place_4_everything();
				if (user.get_ID_USER_cUF().equals(user2.get_ID_USER_cUF())) {
					LR_CB2 cb_right = new LR_CB2(this);
					cb_right.set_right_side(true);
					kaps.set_rec_crosstable(rec_cross);
					kaps.get_cb_change().setSelected(rec_cross.is_ALLOW_CHANGE_YES());
					kaps.get_cb_close().setSelected(rec_cross.is_ALLOW_CLOSE_YES());
					kaps.get_cb_sendMail().setSelected(rec_cross.is_SEND_MAIL_YES());
					kaps.get_cb_Sofortnachricht().setSelected(rec_cross.is_SOFORTANZEIGE_YES());
					
					
					cb_right.set_place_4_everything(kaps);
					this.get_v_cb_right().add(cb_right);
					
					// default-werte setzen
					cb_left.setSelected(true);
					cb_right.setSelected(true);   //der linke wird auch selected
				}
			}
		}
		
		this.refresh_left();
		this.refresh_right();
	}


	@Override
	public void add_content_before_left_right_panel() throws myException {
		this.add(new MyE2_Label(new MyE2_String("Alle Benutzer")),E2_INSETS.I(3,3,3,3));
		this.add(new MyE2_Label(new MyE2_String("Ausgewählte Benutzer")),E2_INSETS.I(3,3,3,3));
	}


	@Override
	public void add_content_after_left_right_panel() throws myException {
	}


}

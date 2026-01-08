package panter.gmbh.Echo2.ListAndMask.List.Settings;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_RestoreUserSettings_ColumnOrder;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList_RestoreUserSettings_ColumnWidth;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_ENUMS.USERSETTINGS;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;


public class LIST_Setting_Plugin_Set_Colum_SortAndWidth extends E2_Grid {
	
	private E2_BasicModuleContainer     	oContainer_this_belongs_to = null;
	private E2_NavigationList      			oNaviList = null;

	private MyE2_Button 					buttonSaveAndStore = 	new MyE2_Button(new MyE2_String("OK-Speichern"));
	private MyE2_Button 					buttonCancel = 			new MyE2_Button(new MyE2_String("Fenster schliessen"));
	private MyE2_Button                     buttonResetToStandard = new MyE2_Button(new MyE2_String("Alle Werte auf Anfang"));

	private v_sort_and_width_components     v_sort_and_width = 		new v_sort_and_width_components();
	
	
	public LIST_Setting_Plugin_Set_Colum_SortAndWidth(E2_NavigationList NaviList, E2_BasicModuleContainer Container_this_belongs_to) throws myException 	{
		super();
		
		this.oNaviList = NaviList;
		this.oContainer_this_belongs_to = Container_this_belongs_to;
		
		this.buttonCancel.add_oActionAgent(new ownActionCloseWindow());
		this.buttonSaveAndStore.add_oActionAgent(new ownActionSaveRowWidth());
		this.buttonSaveAndStore.add_oActionAgent(new ownActionSaveColumnOrder());
		this.buttonSaveAndStore.add_oActionAgent(new ownActionRebuild_List());
		
		this.buttonResetToStandard.add_oActionAgent(new ownActionResetAufStandard());
		this.buttonResetToStandard.add_oActionAgent(new ownActionCloseWindow());
		
		
		this.buttonCancel.setToolTipText(new MyE2_String("Abbruch, die Spalten bleiben wie sie sind").CTrans());
		this.buttonSaveAndStore.setToolTipText(new MyE2_String("Spalteneinstellungen für diese Sitzung ändern und permanent festlegen").CTrans());
		this.buttonResetToStandard.setToolTipText(new MyE2_String("Alle Werte wieder auf Standard setzen (alle Breiten- und Sortiereinstellungen werden entfernt").CTrans());

		
		//die breiten-hashmap einlesen
		HashMap<String, String>  hm_spaltenbreiten = 	new E2_UserSetting_naviList_column_width(this.oNaviList).READ_STORED_COL_WIDTHS();
		
		E2_ComponentMAP 	oMap = 					this.oNaviList.get_oComponentMAP__REF();
		Vector<String> 		vComponentHashKeys = 	oMap.get_vComponentHashKeys();  				//ist schon in der richtigen reihenfolge durch das E2__NavigationList.INIT_WITH_ComponentMAP()

		
		int i_sort = 10;
		for (String hash: vComponentHashKeys) {
			MyE2IF__Component oComponent = (MyE2IF__Component)oMap.get(hash) ;
			// alle komponenten ausser den steuerungskomponenten in die auswahl
			if (!(oComponent instanceof E2_ButtonListMarker || oComponent instanceof E2_CheckBoxForList))	{
				sort_and_width_components saw = new sort_and_width_components(hash,""+i_sort, S.NN(hm_spaltenbreiten.get(hash)), oComponent.EXT().get_cList_or_Mask_Titel());
				if (S.isFull(hm_spaltenbreiten.get(hash))) {
					saw.get_tf_breite().setText(hm_spaltenbreiten.get(hash));
				}
				this.v_sort_and_width.add(saw);
				i_sort += 10;
			}
		}
		
		this.v_sort_and_width.sort();
		this.refreshView();
		
	}
	
	
	private void refreshView() {
		this.removeAll();
		this._a(this.v_sort_and_width.generate_actual_setting_grid(), new RB_gld()._ins(E2_INSETS.I(2,2,2,2)));
		this._a(   new E2_Grid()._a(this.buttonSaveAndStore._lw(),new RB_gld()._ins(E2_INSETS.I(2,0,2,2)))
								._a(this.buttonCancel._lw(),new RB_gld()._ins(E2_INSETS.I(2,2,2,2)))
								._a(this.buttonResetToStandard._lw(),new RB_gld()._ins(E2_INSETS.I(2,2,2,2)))
								._s(1)
							    , new RB_gld()._ins(E2_INSETS.I(2,45,2,2)));
		this._setSize(450,150);
	
	}

	
	
	
	
	private class sort_and_width_components {
		
		private int 				f_sort_id = 0;
		private String    			f_hashKey = null;
		private String	 			f_text = null;
		
		private MyE2_Button   		but_sort_up = new MyE2_Button(E2_ResourceIcon.get_RI("up_mini.png"),true);
		private MyE2_Button   		but_sort_dn = new MyE2_Button(E2_ResourceIcon.get_RI("down_mini.png"),true);
		
		private RB_TextField  		tf_breite =   new RB_TextField(50,4)._fs(-2)._h(10);

		/**
		 * 
		 * @param hashkey
		 * @param sort_id
		 * @param colWidth
		 * @param text
		 */
		public sort_and_width_components(String hashkey, String sort_id, String colWidth, MyString text) {
			super();
			MyLong l_sort = new MyLong(sort_id);
			if (l_sort.get_bOK()) {
				this.f_sort_id = l_sort.get_iValue();
			} else {
				this.f_sort_id = 0;    //sollte nicht vorkommen, da die sortschluessel komplett gefuellt werden beim aufruf
			}
			this.f_hashKey = hashkey;
			this.f_text = text.COrig();
			this.tf_breite.setText(S.NN(colWidth));
			this.but_sort_dn._aaa(new ownActionDown());
			this.but_sort_up._aaa(new ownActionUp());
		}
		
		public int get_sort_id() {
			return f_sort_id;
		}
		
		public String get_text() {
			return f_text;
		}

		public String get_key() {
			return this.f_hashKey;
		}

		
		public void set_sort_id(int p_sort_id) {
			this.f_sort_id = p_sort_id;
		}
		
		public class ownActionDown extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				sort_and_width_components.this.f_sort_id += 11;
				LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.v_sort_and_width.setLast_selected(sort_and_width_components.this);
				LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.v_sort_and_width.sort();
				LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.refreshView();
			}
		}

		public class ownActionUp extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				sort_and_width_components.this.f_sort_id -= 11;
				LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.v_sort_and_width.setLast_selected(sort_and_width_components.this);
				LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.v_sort_and_width.sort();
				LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.refreshView();
			}
		}

		
		public RB_TextField get_tf_breite() {
			return tf_breite;
		}
	}

	
	private class v_sort_and_width_components extends Vector<sort_and_width_components> {

		private sort_and_width_components last_selected = null;
		
		public v_sort_and_width_components() {
			super();
		}
		
		public E2_Grid generate_actual_setting_grid() {
			E2_Grid g = new E2_Grid();

			this.sort();
			
			RB_gld  layOuttitel = new RB_gld()._ins(E2_INSETS.I(2,1,10,1))._col(new E2_ColorDDark());
			
			g._setSize(300,30,30,80)._bo_ddd();
			g._a(new RB_lab()._t(new MyE2_String("Spalte"))._lw()._lw(),layOuttitel)
			._a(new RB_lab()._t(new MyE2_String("rauf"))._lw(),layOuttitel)
			._a(new RB_lab()._t(new MyE2_String("runter"))._lw(),layOuttitel)
			._a(new RB_lab()._t(new MyE2_String("Spaltenbreite (in Pixel)"))._lw(),layOuttitel);
			
			for (sort_and_width_components saw: this) {
				RB_gld lo = new RB_gld()._ins(E2_INSETS.I(2,1,10,1));
				if (this.last_selected==saw) {
					lo._col(new E2_ColorLLLight());
				}
				g._a(new RB_lab()._t(saw.get_text())._lw(),	lo);
				g._a(saw.but_sort_up,						lo._c()._center_top());
				g._a(saw.but_sort_dn,						lo._c()._center_top());
				g._a(saw.tf_breite,							lo);
			}
			
			return g;
		}
		
		
		
		public void sort() {
			//zuerst den vektor nach der sortid sortieren
			this.sort(new Comparator<sort_and_width_components>() {
				@Override
				public int compare(sort_and_width_components o1, sort_and_width_components o2) {
					int i_rueck = 0;
					
					if (o1.get_sort_id()<o2.get_sort_id()) {
						i_rueck=-1;
					} else if (o1.get_sort_id()>o2.get_sort_id()) {
						i_rueck=1;
					}
					
					return i_rueck;
				}
				
			});
			
			//jetzt die abstaende wieder auf 10 kalibrieren
			int i_sort = 10;
			for (sort_and_width_components saw: this) {
				saw.set_sort_id(i_sort);
				i_sort += 10;
			}
		}

		
		public void setLast_selected(sort_and_width_components p_last_selected) {
			this.last_selected = p_last_selected;
		}
	}
	
	
	
	private class ownActionSaveRowWidth extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			v_sort_and_width_components     v_sort_and_width = LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.v_sort_and_width;
			
			HashMap<String, String> hm_2_save = new HashMap<>();
			
			for (sort_and_width_components  saw: v_sort_and_width) {
				String c_breite = saw.get_tf_breite().getText();
				MyLong l = new MyLong(c_breite);
				if (l.get_bOK()) {
					hm_2_save.put(saw.get_key(), ""+l.get_iValue());
				}
			}
			
			new E2_UserSetting_naviList_column_width(LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.oNaviList).SAVE_COL_WIDTHS(hm_2_save);
			
			//jetzt wieder einlesen und anwenden
			new E2_NavigationList_RestoreUserSettings_ColumnWidth(LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.oNaviList);
		}
	}

	
	
	private class ownActionSaveColumnOrder extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			v_sort_and_width_components  v_sort_and_width = LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.v_sort_and_width;
			
			HashMap<String, String> hm_2_save = new HashMap<>();
			
			for (sort_and_width_components  saw: v_sort_and_width) {
				String sortnumber = ""+saw.get_sort_id();
				MyLong l = new MyLong(sortnumber);
				if (l.get_bOK()) {
					hm_2_save.put(saw.get_key(), ""+l.get_iValue());
				}
			}
			
			new E2_UserSetting_naviList_column_order(LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.oNaviList).SAVE_COL_ORDER(hm_2_save);
			
			//jetzt wieder einlesen und anwenden
			new E2_NavigationList_RestoreUserSettings_ColumnOrder(LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.oNaviList);
		}
	}

	
	
	
	private class ownActionRebuild_List extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.oNaviList.BUILD_ComponentMAP_Vector_from_ActualSegment();
			LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.oNaviList.FILL_GRID_From_InternalComponentMAPs(true, true);							// anzeigen
		}
	}

	private class ownActionCloseWindow extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LIST_Setting_Plugin_Set_Colum_SortAndWidth.this.oContainer_this_belongs_to.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
	}

	
	private class ownActionResetAufStandard extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Vector<String>  v_delete_sql = new Vector<>();
			v_delete_sql.add("DELETE FROM "+bibE2.cTO()+"."+USERSETTINGS.fullTabName()+" WHERE "
											+new vgl(USERSETTINGS.id_user, bibALL.get_ID_USER()).s()+" AND "
											+new vgl(USERSETTINGS.hashvalue_session,ENUM_USER_SAVEKEY.SESSIONHASH_SAVE_COLUMN_WIDTH_OF_NAVILIST.name()).s());
			v_delete_sql.add("DELETE FROM "+bibE2.cTO()+"."+USERSETTINGS.fullTabName()+" WHERE "
											+new vgl(USERSETTINGS.id_user, bibALL.get_ID_USER()).s()+" AND "
											+new vgl(USERSETTINGS.hashvalue_session,ENUM_USER_SAVEKEY.SESSIONHASH_SAVE_COLUMN_ORDER_OF_NAVILIST.name()).s());

			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(v_delete_sql,true));
			bibALL.deleteSessionValue(ENUM_USER_SAVEKEY.SESSIONHASH_SAVE_COLUMN_WIDTH_OF_NAVILIST.name());
			bibALL.deleteSessionValue(ENUM_USER_SAVEKEY.SESSIONHASH_SAVE_COLUMN_ORDER_OF_NAVILIST.name());
			
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Einstellungen der Spaltenreihenfolge und der Spaltenbreite wurde gelöscht. Bitte schliessen Sie das Modul und öffnen es neu !"));
		}
		
	}
	
}

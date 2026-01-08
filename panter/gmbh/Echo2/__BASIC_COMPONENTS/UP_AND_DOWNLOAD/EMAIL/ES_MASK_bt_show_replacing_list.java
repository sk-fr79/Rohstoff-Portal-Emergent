package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.LinkedHashMap;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_SplitPane_in_3_lines;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_SaveOneString;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.exceptions.myException;

public class ES_MASK_bt_show_replacing_list extends E2_Button {

	private String 							table_base_name = 	null;
	private String 							id_table = 			null;
	

	/**
	 * 
	 * @param p_table_base_name
	 * @param p_id_table
	 * @throws myException 
	 */
	public ES_MASK_bt_show_replacing_list(String p_table_base_name, String p_id_table ) throws myException {
		super();
		this.table_base_name = 		p_table_base_name;
		this.id_table = 			p_id_table;
		

		
		this._image(E2_ResourceIcon.get_RI("help.png"))
			._aaa(new own_action_agent())
			._ttt(new MyE2_String("Zeigt eine Liste der möglichen Platzhalter an, die im Betreff- und eMail-Text eingesetzt werden können"));
		
		
	}
	
	
	
	

	
	private class own_action_agent extends XX_ActionAgent {

		private RB_TextField  					tf_search = new RB_TextField(300)._sBDD();
		private E2_Grid                         grid_info = new E2_Grid();
		private LinkedHashMap<String, String[]> hm_replaceMap  = null;
		private search_block_with_save          search_block = null;
		
		
		public own_action_agent() throws myException {
			super();
			this.search_block = new search_block_with_save(this.tf_search);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			ES_MASK_bt_show_replacing_list oThis = ES_MASK_bt_show_replacing_list.this;
			
			//jetzt festestellen, ob es eine id_email_send gibt oder ob neueingabe vorliegt
			MyRECORD_IF_RECORDS recTest = oThis.rb_ComponentMap_this_belongsTo().rb_get_belongs_to().rb_Actual_DataobjectCollector().get(new RB_KM(_TAB.email_send)).get_RecORD();
			
			String id_email_send = null;
			if (recTest!=null) {
				id_email_send = ((MyRECORD)recTest).ufs(EMAIL_SEND.id_email_send);
			}
			
			MyE2_MessageVector mv = new MyE2_MessageVector();
			LinkedHashMap<String, String>  db_replacer = new  ES__verify_table_name_and_id(oThis.table_base_name, oThis.id_table, id_email_send, mv).get_hm_replacing_list_FromDataSets(); // ES__generatorReplaceList(oThis.table_base_name, oThis.id_table).get_hm_replacement_terms();
			bibMSG.add_MESSAGE(mv);
			
			this.hm_replaceMap = bibReplacer.get_ListOfReplaceFields(db_replacer);
			this.grid_info._clear()._setSize(300,400,200);
			this.tf_search.setText(S.NN(new E2_UserSetting_SaveOneString(ENUM_USER_SAVEKEY.KEY_SAVE_SEARCHFIELD_IN_PLACEHOLDERLIST).LOAD()));
			
			this.rebuild_grid();
			
			new own_popup(this.grid_info);
		}

		private void rebuild_grid() throws myException {
			this.grid_info._clear();
			this.grid_info._gld(new RB_gld()._ins(2,1,2,1)._col(new E2_ColorDDark()));
			this.grid_info._a(lbb(new MyE2_String("Platzhalter").CTrans()))._a(lbb(new MyE2_String("Erklärung").CTrans()))._a(lbb(new MyE2_String("Aktueller Wert").CTrans()));
			this.grid_info._gld(new RB_gld()._ins(2,1,2,1));
			String suche = S.NN(this.tf_search.getText()).trim();
			
			for (String key: hm_replaceMap.keySet()) {
				if (S.isEmpty(suche) 
					|| key.toUpperCase().contains(suche.toUpperCase()) 
					|| hm_replaceMap.get(key)[0].toUpperCase().contains(suche.toUpperCase()) 
					|| hm_replaceMap.get(key)[1].toUpperCase().contains(suche.toUpperCase()) )
				//suche bereuecksichtigen
				this.grid_info._a(lb(key))._a(lbi(hm_replaceMap.get(key)[0]))._a(lbb(hm_replaceMap.get(key)[1]));
			}

		}
		
		//normaler text
		private RB_lab lb(String text) {
			return new RB_lab()._txt(text)._fo_s_plus(-2);
		}
		private RB_lab lbi(String text) {
			return new RB_lab()._txt(text)._fo_s_plus(-2)._fo_italic();
		}
		
		private RB_lab lbb(String text) {
			return new RB_lab()._txt(text)._fo_s_plus(-2)._fo_bold();
		}


		
		private class own_popup extends E2_BasicModuleContainer {

			public own_popup(E2_Grid  g_info) throws myException {
				super();
				this.add(g_info, E2_INSETS.I(2,2,2,2));
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(600), new MyE2_String("Die hier verfügbaren Platzhalter ..."));
				this.re_arrange_window_variante_1();
			}
			
			
			/**
			 * splitpane mit 3 zeilen, oben das messagegrid, dazwischen ein beliebiges steuergrid, darunter der basiccontainer
			 * (beispiel fuer eigene ableitungen)
			 * 
			 * @return
			 */
			public E2_BasicModuleContainer  re_arrange_window_variante_1() {
				//die eigenen contentpanes werden nicht verwendet
				E2_SplitPane_in_3_lines split = new E2_SplitPane_in_3_lines();
				
				split._set_to_top_pane(this.get_oGridTopLineButtonsAndMessages());
				split._set_to_mid_pane(own_action_agent.this.search_block);
				split._set_to_bottom_pane(this.get_oContentPaneAussen());
				this.get_oContentPaneAussen().setBackground(new E2_ColorBase());
				
				split.set_separator_line1(1, new E2_ColorDDDark());
				split.set_separator_line2(0, new E2_ColorDDDark());
				
				this.get_oSeparator().setVisible(false);

				split._set_height_top(30);
				split._set_height_mid(35);
				
				this.get_oWindowPane().removeAll();
				this.get_oWindowPane().add(split);
				
				return this;
			}
		}

		
		
		private class search_block_with_save extends E2_Grid {

			private TextField         texfield_with_searchtext = null;
			
			public search_block_with_save(TextField  p_texfield_with_searchtext) throws myException {
				super();
				
				this.texfield_with_searchtext = p_texfield_with_searchtext;
				
				this._setSize(300,35,40)._a(this.texfield_with_searchtext, new RB_gld()._ins(4)._left_mid())
										._a(new own_bt_search(), new RB_gld()._ins(4)._left_mid())
										._a(new own_bt_clear(), new RB_gld()._ins(4)._left_mid())
										;
				
			}
			
			private class own_bt_search extends E2_Button {

				public own_bt_search() {
					super();
					this._image(E2_ResourceIcon.get_RI("suche.png"));
					this._aaa(new XX_ActionAgent() {
						@Override
						public void executeAgentCode(ExecINFO oExecInfo) throws myException {
							own_action_agent.this.rebuild_grid();
							new E2_UserSetting_SaveOneString(ENUM_USER_SAVEKEY.KEY_SAVE_SEARCHFIELD_IN_PLACEHOLDERLIST).SAVE(S.NN(search_block_with_save.this.texfield_with_searchtext.getText()));
						}
					});
				}
				
			}
			
			private class own_bt_clear extends E2_Button {

				public own_bt_clear() {
					super();
					this._image(E2_ResourceIcon.get_RI("eraser.png"));
					this._aaa(new XX_ActionAgent() {
						@Override
						public void executeAgentCode(ExecINFO oExecInfo) throws myException {
							search_block_with_save.this.texfield_with_searchtext.setText("");
							own_action_agent.this.rebuild_grid();
							new E2_UserSetting_SaveOneString(ENUM_USER_SAVEKEY.KEY_SAVE_SEARCHFIELD_IN_PLACEHOLDERLIST).SAVE(S.NN(search_block_with_save.this.texfield_with_searchtext.getText()));
						}
					});
				}
				
			}

			
		}

		
	}
	


	
	
	
}

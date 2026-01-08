package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class ES_ModuleContainerMask extends Project_RB_ModuleContainerMASK {

	private ES_MASK_BT_Add_Attachments   	bt_add_attach = 	null;


	private String 							table_base_name = 	null;
	private String 							id_table = 			null;
	
	public ES_ModuleContainerMask(ES_MASK_BT_Add_Attachments   p_bt_add_attach, String table_base_name, String id_table) throws myException {
		super();
		this.registerComponent(new KEY_DUMMY(),new ES_RB_MASK_ComponentMapCollector(table_base_name, id_table));
		
		this.bt_add_attach = 	p_bt_add_attach;
		this.table_base_name = table_base_name;
		this.id_table = 		id_table;
		
		RB_ComponentMap  	oMask = 	this.rb_FirstAndOnlyComponentMapCollector().get(new RB_KM(_TAB.email_send));
//		E2_MaskFiller  		oFiller = 	new E2_MaskFiller(oMask,null,null);
//
//		//2016-09-16: neuer maskenaufbau
//		//grid-help enthaelt die betreff- und textfelder sowie buttons zur uebersetzung und info zu den platzhalter-feldern
//		E2_Grid   grid_help = new E2_Grid()._setSize(150,500,50)
//										 	._a(new own_lab("Betreff"), 				new RB_gld()._ins(2)._left_top())
//										 	._a(oMask.rb_Comp(EMAIL_SEND.betreff),	 	new RB_gld()._ins(2)._left_top())
//										 	._a(new bt_show_info_replace_tags(), 		new RB_gld()._ins(2)._left_top())
//										 	
//										 	._a(new own_lab("Mail-Text"))
//										 	._a(oMask.rb_Comp(EMAIL_SEND.text), 		new RB_gld()._ins(2)._left_top())
//										 	._a();
//											;
		
		
		
		E2_Grid   grid = new E2_Grid()	._setSize(100,410,300)
									 	._a(new own_lab("ID-Email"), 					new RB_gld()._ins(2)._left_top())
									 	._a(oMask.getComp(EMAIL_SEND.id_email_send), 	new RB_gld()._ins(2)._left_top())
									 	._a()
									 	
									 	._a(new own_lab("Absender"), 					new RB_gld()._ins(2)._left_top())
									 	._a(oMask.getComp(EMAIL_SEND.sender_adress),	new RB_gld()._ins(2)._left_top())
									 	._a()
									 	
									 	._a(new own_lab("Betreff"), 					new RB_gld()._ins(2)._left_top())
									 	._a(oMask.getComp(EMAIL_SEND.betreff), 			new RB_gld()._ins(2)._left_top())
									 	._a(oMask.getComp(new RB_KF(EMAIL_SEND.id_email_send,ES_CONST.HASHKEY_MASK_SHOW_INFO_REPLACETAGS)),	new RB_gld()._ins(2)._left_top())
									 	
									 	._a(new own_lab("Mail-Text"), 					new RB_gld()._ins(2)._left_top())
									 	._a(oMask.getComp(EMAIL_SEND.text), 			new RB_gld()._ins(2)._left_top())
									 	._a(oMask.getComp(new RB_KF(EMAIL_SEND.id_email_send,ES_CONST.HASHKEY_MASK_SHOW_FIELDS_4_SEND)),	new RB_gld()._ins(2)._left_top())

									 	._a(new own_lab("Typ der Mail"), 				new RB_gld()._ins(2)._left_top())
									 	._a(oMask.getComp(EMAIL_SEND.send_type), 		new RB_gld()._ins(2)._left_top())
									 	._a()
									 	
									 	._a(new own_lab("Mail-Verteiler"), 				new RB_gld()._ins(2,5,2,2)._left_top())
									 	._a(oMask.getComp(new RB_KF(EMAIL_SEND.id_email_send,ES_CONST.HASHKEY_MASK_DAUGHTER_TARGETS)), new RB_gld()._ins(2,5,2,2)._left_top()._span(2))
									 	
									 	
									 	._a(new own_lab("Anlagen"), 					new RB_gld()._ins(2,5,2,2)._left_top())
									 	._a(oMask.getComp(new RB_KF(EMAIL_SEND.id_email_send,ES_CONST.HASHKEY_MASK_LIST_ARCHIVMEDIEN)), new RB_gld()._ins(2,5,2,2)._left_top()._span(2))
									 	;
		
		
		
		
		
//		MyE2_Grid  oGrid = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
//		oFiller.add_Line(oGrid, "#ID-Email", 1, _DB.EMAIL_SEND$ID_EMAIL_SEND, 1);
//		oFiller.add_Line(oGrid, "#Absender", 1, _DB.EMAIL_SEND$SENDER_ADRESS, 1);
//		oFiller.add_Line(oGrid, "#Betreff", 1, _DB.EMAIL_SEND$BETREFF, 1);
//		oFiller.add_Line(oGrid, "#Mail-Text", 1, _DB.EMAIL_SEND$TEXT, 1);
//		oFiller.add_Line(oGrid, "#Typ der Mail", 1, EMAIL_SEND.send_type.fn()+"|# |", 1);
//		
//		oFiller.add_Line(oGrid, "#Mail-Verteiler", 1, ES_CONST.HASHKEY_MASK_DAUGHTER_TARGETS, 1);
//		oFiller.add_Line(oGrid, "#Anlagen", 1, ES_CONST.HASHKEY_MASK_LIST_ARCHIVMEDIEN, 1);
	
		//jetzt (falls != null) den add_attach-button einfuegen (blendet den direkt in die liste ein)
		if (this.bt_add_attach!=null) {
			((ES_mask_complex_LISTE_ANLAGEN)oMask.getRbComponent(new RB_KF(EMAIL_SEND.id_email_send,ES_CONST.HASHKEY_MASK_LIST_ARCHIVMEDIEN))).add_button_4_adding_attachments(this.bt_add_attach);
		}
		
		
		this.rb_INIT(E2_MODULNAME_ENUM.MODUL.POPUP_EMAIL_SEND, grid, true);
		
	}


	private class own_lab extends RB_lab {

		public own_lab(String text) {
			super();
			this._txt(new MyE2_String(text));
		}
		
	}


//	private class bt_show_info_replace_tags extends E2_Button {
//
//		public bt_show_info_replace_tags() {
//			super();
//			this._image(E2_ResourceIcon.get_RI("help.png"));
//			this.add_oActionAgent(new own_action_agent());
//			
//		}
//		
//		private class own_action_agent extends XX_ActionAgent {
//
//			@Override
//			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//				
//				ES_ModuleContainerMask oThis = ES_ModuleContainerMask.this;
//				
//				LinkedHashMap<String, String[]>  hm_replaceMap  = null;
//				
//				if (S.isFull(oThis.id_table) &&S.isFull(oThis.table_base_name)) {
//
//					hm_replaceMap = bibReplacer.get_ListOfReplaceFields(new ES__generatorReplaceList(oThis.table_base_name, oThis.id_table).get_hm_replacement_terms());
//					
//
//				} else {
//					hm_replaceMap = bibReplacer.get_ListOfReplaceFields(null);
//				}
//				
//				E2_Grid  g_info = new E2_Grid()._setSize(300,400,200);
//				
//				g_info._gld(new RB_gld()._ins(2,1,2,1)._col(new E2_ColorDDark()));
//				g_info._a(lbb(new MyE2_String("Platzhalter").CTrans()))._a(lbb(new MyE2_String("Erklärung").CTrans()))._a(lbb(new MyE2_String("Aktueller Wert").CTrans()));
//				g_info._gld(new RB_gld()._ins(2,1,2,1));
//				for (String key: hm_replaceMap.keySet()) {
//					g_info._a(lb(key))._a(lbi(hm_replaceMap.get(key)[0]))._a(lbb(hm_replaceMap.get(key)[1]));
//				}
//				
//				new own_popup(g_info);
//			}
//
//			//normaler text
//			private RB_lab lb(String text) {
//				return new RB_lab()._txt(text)._fo_s_plus(-2);
//			}
//			private RB_lab lbi(String text) {
//				return new RB_lab()._txt(text)._fo_s_plus(-2)._fo_italic();
//			}
//			
//			private RB_lab lbb(String text) {
//				return new RB_lab()._txt(text)._fo_s_plus(-2)._fo_bold();
//			}
//		}
//		
//
//		private class own_popup extends E2_BasicModuleContainer {
//
//			public own_popup(E2_Grid  g_info) throws myException {
//				super();
//				this.add(g_info, E2_INSETS.I(2,2,2,2));
//				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(600), new MyE2_String("Die hier verfügbaren Platzhalter ..."));
//			}
//			
//		}
//		
//		
//	}
	
	
	public String get_table_base_name() {
		return this.table_base_name;
	}


	public String get_id_table() {
		return this.id_table;
	}


}
 
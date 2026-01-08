package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.filetransfer.UploadEvent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_SplitPane_in_3_lines;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_PopUpWindow_for_Upload_to_Archiv;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_TARGETS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;


public class ES_MASK_BT_Add_Attachments extends E2_Button {
	
	private RB_bt_List2Mask  				list_bt_calling = 	null;
	private ES_LIST_BasicModuleContainer 	es_listContainer = 	null;
	private E2_Button             			bt_upload = 	new E2_Button();
	private E2_Button                       bt_save = 		new E2_Button();
	
	private Vector<ownCB>  					v_sel_anhaenge = new Vector<>();

	
	private grid_addons_from_archiv         grid_with_archiv = null;
	
	private popup_container_to_show_addon_options   container4adding = null;
	
	public ES_MASK_BT_Add_Attachments(RB_bt_List2Mask p_list_bt_calling, ES_LIST_BasicModuleContainer 	listContainer) throws myException {
		super();
		this._image(E2_ResourceIcon.get_RI("new_mini.png"))._s_Image()._ttt(new MyE2_String("Weitere Dateien an die Mail anhängen ..."));
		// this._standard_text_button()._t(new MyE2_String("Weitere Anlagen"))._i(E2_INSETS.I(2,1,2,1))._center();
		this.list_bt_calling = p_list_bt_calling;
		
		this.es_listContainer = listContainer;
		
		this.bt_upload._image(E2_ResourceIcon.get_RI("up_mini.png"))
						._ttt(new MyE2_String("Weitere Dokumente ins Archiv laden / am Mail anhängen"))
						._aaa(new ownActionAgentUpload())
						;
		
		this.bt_save._t(new MyE2_String("Speichern"))
					._ttt(new MyE2_String("Die selektierten Anlagen an die eMail anhängen"))
					._standard_text_button()._center()
					._aaa(new ownActionSaveAddedToEmail())
					;
		
		
		this.add_GlobalValidator(new ownValidatorCheckEmailStatus());
		this.add_GlobalValidator(new ownValidatorCheckStruktur());
		
		this.add_oActionAgent(new ownActionAgentStartPopup());
	}

	
	private class ownActionAgentStartPopup extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			RB_bt_List2Mask  calling_bt = ES_MASK_BT_Add_Attachments.this.list_bt_calling;
			
			//status der maske feststellen
			RB_DataobjectsCollector collector = calling_bt.rb_modulContainerMASK().rb_ComponentMapCollector(new KEY_DUMMY()).rb_Actual_DataobjectCollector();
			
			RB_Dataobject  data_o = collector.get(new RB_KM(_TAB.email_send));

			if (data_o.rb_MASK_STATUS().isStatusEdit()) {
				RECORD_EMAIL_SEND rec_email_send = (RECORD_EMAIL_SEND)data_o.get_MyRECORD();
				
				boolean b_teils_versendet = false;
				
				for (RECORD_EMAIL_SEND_TARGETS target: rec_email_send.get_DOWN_RECORD_LIST_EMAIL_SEND_TARGETS_id_email_send()) {
					if (target.is_SEND_OK_YES()) {
						b_teils_versendet=true;
						break;        //reicht, es kann nichts mehr angehaengt werden
					}
				}
				
				if (!b_teils_versendet) {
					ES_MASK_BT_Add_Attachments.this.container4adding= new popup_container_to_show_addon_options(rec_email_send);
				}
			}			
		}
		
	}
	
	
	private class ownValidatorCheckEmailStatus extends XX_ActionValidator_NG {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			
			RB_bt_List2Mask  calling_bt = ES_MASK_BT_Add_Attachments.this.list_bt_calling;
			
			MyE2_MessageVector  mv = new MyE2_MessageVector();
			
			
			//status der maske feststellen
			RB_DataobjectsCollector collector = calling_bt.rb_modulContainerMASK().rb_ComponentMapCollector(new KEY_DUMMY()).rb_Actual_DataobjectCollector();
			
			RB_Dataobject  data_o = collector.get(new RB_KM(_TAB.email_send));
			
			if (data_o.rb_MASK_STATUS().isStatusEdit()) {
				RECORD_EMAIL_SEND rec_email_send = (RECORD_EMAIL_SEND)data_o.get_MyRECORD();
				
				boolean b_teils_versendet = false;
				
				for (RECORD_EMAIL_SEND_TARGETS target: rec_email_send.get_DOWN_RECORD_LIST_EMAIL_SEND_TARGETS_id_email_send()) {
					if (target.is_SEND_OK_YES()) {
						b_teils_versendet=true;
						break;        //reicht, es kann nichts mehr angehaengt werden
					}
				}
				
				if (b_teils_versendet) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die eMail wurde schon teil- oder komplett versendet. Es kann nichts mehr angehängt werden !")));
				}
			} else {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Diese Funktion ist hier gesperrt")));
			}
			
			return mv;
		}
		
	}

	
	private class ownValidatorCheckStruktur extends XX_ActionValidator_NG {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			
			MyE2_MessageVector  mv = new MyE2_MessageVector();
			if (	!(	ES_MASK_BT_Add_Attachments.this.es_listContainer!=null 
					&& 	S.isFull(ES_MASK_BT_Add_Attachments.this.es_listContainer.get_id_TABLE())
					&& 	S.isFull(ES_MASK_BT_Add_Attachments.this.es_listContainer.get_basename_TABLE()))) {
				
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Keine Tabellenzuordnung der Anlagen vorhanden...")));
			}
			return mv;
		}
	}

	
	
	
	private class popup_container_to_show_addon_options extends E2_BasicModuleContainer {

		private RECORD_EMAIL_SEND 	rec_email_send  = 	null;
		
		public popup_container_to_show_addon_options(RECORD_EMAIL_SEND p_rec_email_send) throws myException {
			super();
			
			ES_MASK_BT_Add_Attachments oThis = ES_MASK_BT_Add_Attachments.this; 
			this.rec_email_send = p_rec_email_send;
			
			oThis.grid_with_archiv = new grid_addons_from_archiv(this.rec_email_send);
			
			this.add(oThis.grid_with_archiv, E2_INSETS.I(3,3,3,3));

			this.CREATE_AND_SHOW_POPUPWINDOW();
		}

		//neue methode, um eine fixierte buttonleiste zu realisieren
		public void CREATE_AND_SHOW_POPUPWINDOW() throws myException 		{

			super.CREATE_AND_SHOW_POPUPWINDOW(new Extent(700),new Extent(500),new MyE2_String("Weitere Anlagen zur vorliegende eMail ..."));
			
			ES_MASK_BT_Add_Attachments oThis = ES_MASK_BT_Add_Attachments.this;
			
			//die eigenen contentpanes werden nicht verwendet
			E2_SplitPane_in_3_lines split = new E2_SplitPane_in_3_lines();
			
			this.get_oSeparator().setVisible(false);
			
			E2_Grid line1 = new E2_Grid()._a(this.get_oGridTopLineButtonsAndMessages(),new RB_gld()._ins(4,2,2,2)._left_mid());
			E2_Grid line2 = new E2_Grid()._setSize(30,150)._a(oThis.bt_upload, new RB_gld()._ins(4))._a(oThis.bt_save, new RB_gld()._ins(4));
			
			//jetzt beide in 100-prozent-rahmen 
			E2_Grid rahmen1 = new E2_Grid()._s(1)._w100()._a(line1, new RB_gld()._ins(4, 1, 4, 1))
														 ._a(new Separator(), new RB_gld()._ins(4, 0, 4, 0))	;
			
			E2_Grid rahmen2 = new E2_Grid()._s(1)._w100()._a(line2, new RB_gld()._ins(4, 1, 4, 1))
														 ._a(new Separator(), new RB_gld()._ins(4, 0, 4, 0))	;
			
			split._set_to_top_pane(rahmen1);
			split._set_to_mid_pane(rahmen2);
			split._set_to_bottom_pane(this);
			
			split._set_height_top(40);
			split._set_height_mid(35);
			
			this.get_oWindowPane().removeAll();
			this.get_oWindowPane().add(split);
		}
	}
	

	
	/**
	 * zeigt die archivdokumente aus dem archiv an, um diese anzuhaengen
	 * @author martin
	 *
	 */
	private class grid_addons_from_archiv extends E2_Grid  {
		private RECORD_EMAIL_SEND 	rec_email_send  = null;

		public grid_addons_from_archiv(RECORD_EMAIL_SEND p_rec_email_send) throws myException {
			super();
			this.rec_email_send = p_rec_email_send;
			
			this.rebuild();
		}
		
		public void rebuild() throws myException {
			
			ES_MASK_BT_Add_Attachments.this.v_sel_anhaenge.clear();
			this.removeAll();
			this._bo_dd();
			
			//hier feststellen, zu welchem archiv die vorhandenen anlagen gehoeren
			RECLIST_EMAIL_SEND_ATTACH rl_att =  this.rec_email_send.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send();

			String id_archiv = 	ES_MASK_BT_Add_Attachments.this.es_listContainer.get_id_TABLE();
			String base_table = ES_MASK_BT_Add_Attachments.this.es_listContainer.get_basename_TABLE();
			
			if (S.isFull(id_archiv) && S.isFull(base_table)) {
				//dann alle raussuchen, die passen und noch nicht vorhanden sind
				
				SEL sel = new SEL(_TAB.archivmedien).FROM(_TAB.archivmedien).WHERE(new vgl(ARCHIVMEDIEN.id_table, id_archiv)).AND(new vgl(ARCHIVMEDIEN.tablename, base_table)).ORDERDOWN(ARCHIVMEDIEN.id_archivmedien);
				Vector<String>  v_ids_used = bibVECTOR.get_vector_from_values(rl_att.get_ID_ARCHIVMEDIEN_hmString_UnFormated(""));
				
				RECLIST_ARCHIVMEDIEN  rla = new RECLIST_ARCHIVMEDIEN(sel.s());
				
				this._setSize(50,50,100,300,200);           //checkbox, download,erstellt am, beschreibung, dateiname
				
				for (String id: rla.get_vKeyValues()) {
					RECORD_ARCHIVMEDIEN_ext rae = new RECORD_ARCHIVMEDIEN_ext(rla.get(id));
					ownCB cb = new ownCB(rae);
					
					if (v_ids_used.contains(id)) {
						cb._check()._disable();
					}

					ES_MASK_BT_Add_Attachments.this.v_sel_anhaenge.add(cb);
					
					RB_gld ld = new RB_gld()._ins(2,1,2,1)._left_top();
					
					this._a(cb, 															ld)
						._a(new down_bt(rae), 												ld)
						._a(new RB_lab()._t(rae.fs(ARCHIVMEDIEN.erstellungsdatum)), 		ld)
						._a(new RB_lab()._t(rae.fs(ARCHIVMEDIEN.dateibeschreibung))._lw(), 	ld)
						._a(new RB_lab()._t(rae.fs(ARCHIVMEDIEN.dateiname))._lw(), 			ld);
					
				}
			}

		}
	}

	
	
	private class down_bt extends E2_Button {
		
		private RECORD_ARCHIVMEDIEN_ext rec = null;

		public down_bt(RECORD_ARCHIVMEDIEN_ext rec) {
			super();
			this._image(E2_ResourceIcon.get_RI("down_mini.png"))._s_Image();
			this.rec = rec;
			
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					down_bt.this.rec.starte__downLoad();
				}
			});
		}
	}

	
	
	private class ownActionAgentUpload extends XX_ActionAgent {
		 
		public void executeAgentCode(ExecINFO oExecInfo) {
			ES_MASK_BT_Add_Attachments oThis = ES_MASK_BT_Add_Attachments.this;
			try	{
				ownUpload_Select oPopUPload = new ownUpload_Select(	oThis.es_listContainer.get_basename_TABLE(),
																	oThis.es_listContainer.get_id_TABLE(),
																	false,
																	"Datei ins Archiv hochladen",
																	"", 
																	null);
				
				bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().add(oPopUPload);
			} catch (myException ex) {
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
		}
	}


	
	private class ownUpload_Select extends E2_PopUpWindow_for_Upload_to_Archiv {

		public ownUpload_Select(String tabelle, String cid_tabelle, boolean overWrite, String ueberschrift, String infoText, E2_NavigationList oNavigation_FileList) throws myException {
			super(tabelle, cid_tabelle, overWrite, ueberschrift, infoText, oNavigation_FileList);
		}

		@Override
		public void do_actionWhenUpload_was_ok(UploadEvent e) throws myException {
			ES_MASK_BT_Add_Attachments  oThis = ES_MASK_BT_Add_Attachments.this;
			
			Vector<ownCB>  v_sel_anhaenge_old = new Vector<>(oThis.v_sel_anhaenge);
			Vector<ownCB>  v_new = 				new Vector<>();
			oThis.grid_with_archiv.rebuild();
			
			//jetzt alt mit neu abgleichen
			for (ownCB cb: oThis.v_sel_anhaenge) {
				
				boolean found = false;
				for (ownCB cb_old: v_sel_anhaenge_old) {
					if (cb_old.rae.ufs(ARCHIVMEDIEN.id_archivmedien).equals(cb.rae.ufs(ARCHIVMEDIEN.id_archivmedien))) {
						found = true;
						if (cb_old.isSelected()) {
							cb.setSelected(true);
						}
					}
				}
				if (!found) {
					v_new.add(cb);
				}
				
			}
			
			
			//falls v_new genau einen umfasst, dann diesen auch waehlen
			if (v_new.size()==1) {
				v_new.get(0).setSelected(true);
			}
		}
	}
	
	
	
	
	private class ownCB extends RB_cb {

		public RECORD_ARCHIVMEDIEN_ext rae = null;
		
		public ownCB(RECORD_ARCHIVMEDIEN_ext p_rae) {
			super();
			this.rae = p_rae;
		}
		
	}
	
	

	private class ownActionSaveAddedToEmail extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			ES_MASK_BT_Add_Attachments  oThis = 		ES_MASK_BT_Add_Attachments.this;
			RB_bt_List2Mask  			calling_bt = 	oThis.list_bt_calling;
			
			//das email-objekt beschaffen
			RB_DataobjectsCollector 	collector = 	calling_bt.rb_modulContainerMASK().rb_ComponentMapCollector(new KEY_DUMMY()).rb_Actual_DataobjectCollector();
			RB_Dataobject  				data_o = 		collector.get(new RB_KM(_TAB.email_send));

			if (data_o.rb_MASK_STATUS().isStatusEdit()) {
				RECORD_EMAIL_SEND rec_email_send = (RECORD_EMAIL_SEND)data_o.get_MyRECORD();

				//jetzt alle checkboxen durchgehen und die selektierten, mit status enabled anhaengen
				
				Vector<ownCB> v_cb = oThis.v_sel_anhaenge;
				Vector<String>  v_sql = new Vector<>();
				
				for (ownCB cb: v_cb) {
					if (cb.isSelected() && cb.isEnabled()) {
						RECORDNEW_EMAIL_SEND_ATTACH  rna = new RECORDNEW_EMAIL_SEND_ATTACH();
						rna.set_NEW_VALUE_ID_ARCHIVMEDIEN(cb.rae.ufs(ARCHIVMEDIEN.id_archivmedien));
						rna.set_NEW_VALUE_ID_EMAIL_SEND(rec_email_send.ufs(EMAIL_SEND.id_email_send));
						
						v_sql.add(rna.get_InsertSQLStatementWith_Id_Field(true, false));
					}
				}
				
				if (v_sql.size()>0) {
					
					MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(v_sql, true);
					if (mv.get_bIsOK()) {
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es wurde Anlagen zur eMail zugefügt ",true, "("+v_sql.size(),false," Anlagen)",true)));
					} else {
						bibMSG.add_MESSAGE(mv);
					}
				} else {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde nichts neues ausgewählt !")));
				}
				
				
				//jetzt die anlagen-komponente aus der maske ziehen
				ES_mask_complex_LISTE_ANLAGEN list_anlagen = 
						(ES_mask_complex_LISTE_ANLAGEN) oThis.list_bt_calling.rb_modulContainerMASK().rb_ComponentMapCollector(new KEY_DUMMY()).
									get(new RB_KM(_TAB.email_send)).getComp(new RB_KF(EMAIL_SEND.id_email_send,ES_CONST.HASHKEY_MASK_LIST_ARCHIVMEDIEN));
				
				
				list_anlagen.rb_Datacontent_to_Component(data_o);
				oThis.container4adding.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				
				
				
			}			
			
			
			
			
			
		}
		
	}
	
	
	
}

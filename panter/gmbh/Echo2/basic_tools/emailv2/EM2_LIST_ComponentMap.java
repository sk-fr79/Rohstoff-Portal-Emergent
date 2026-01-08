 
package panter.gmbh.Echo2.basic_tools.emailv2;
  
  
import java.util.Collection;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V22;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_STATUS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_TYPE;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.DB.E2_DBLabelTranslateDbvalToReadableVal;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_TARGETS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.mail.MailException;
import panter.gmbh.indep.myVectors.HMAP;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22EmailSend;
  
public class EM2_LIST_ComponentMap extends E2_ComponentMAP_V22  {
	
    private EM2_TransportHashMap   m_tpHashMap = null;
    
    public EM2_LIST_ComponentMap(EM2_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new EM2_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        boolean allowEdit = m_tpHashMap.getAllowEdit();
        boolean allowDelete = m_tpHashMap.getAllowDelete();

        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(EM2_CONST.EM2_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(EM2_CONST.EM2_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        if (allowDelete) {
	        this.add_Component(EM2_CONST.EM2_NAMES.DIRECT_DEL.db_val(),    	new EM2_LIST_bt_DeleteInListRow(this.m_tpHashMap)
	        																			._setGridLayout4List(new RB_gld()._ins(4))
	        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
	        																new MyE2_String("?"));
        }
        
        if (allowEdit) {
	        this.add_Component(EM2_CONST.EM2_NAMES.DIRECT_EDIT.db_val(),   	new EM2_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
	        																			._setGridLayout4List(new RB_gld()._ins(4))
	        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
	        																new MyE2_String("?"));
        }
        
        this.add_Component(EM2_CONST.EM2_NAMES.DIRECT_VIEW.db_val(),   	new EM2_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        
        //hier kommen die Felder  
        this.add_Component(new InfoIstVersand());
        
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND.sender_adress),true),     	new MyE2_String(EM2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND.sender_adress)));

        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND.betreff),true),     		new MyE2_String(EM2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND.betreff)));

        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND.betreff_2_send),true)
        															._setVisibleInList(false),     	new MyE2_String(EM2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND.betreff_2_send)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND.text),true),     new MyE2_String(EM2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND.text)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND.text_2_send),true)
        																._setVisibleInList(false),  new MyE2_String(EM2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND.text_2_send)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND.email_type),true)	,       new MyE2_String(EM2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND.email_type)));

        this.add_Component(new E2_DBLabelTranslateDbvalToReadableVal(oFM.get_(EMAIL_SEND.send_type))._setTranslateMap(
        																								new HMAP<String,String>()
        																									._put(SEND_TYPE.SINGLE.db_val(),SEND_TYPE.SINGLE.user_text())
        																									._put(SEND_TYPE.CC.db_val(),SEND_TYPE.CC.user_text())
        																									._put(SEND_TYPE.BCC.db_val(),SEND_TYPE.BCC.user_text())
        																									),     		new MyE2_String(EM2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND.send_type)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND.email_verification_key),true)
        																._setVisibleInList(false),         new MyE2_String(EM2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND.email_verification_key)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND.table_base_name),true),     new MyE2_String(EM2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND.table_base_name)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND.id_table),true),     		new MyE2_String(EM2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND.id_table)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(EMAIL_SEND.id_email_send),true),     	new MyE2_String(EM2_READABLE_FIELD_NAME.getReadableForList(EMAIL_SEND.id_email_send)));
       
        //neu ab 20171025        
        this._setLineWrapListHeader(true 
                                  ,EMAIL_SEND.betreff.fn()
                                  ,EMAIL_SEND.betreff_2_send.fn()
                                  ,EMAIL_SEND.id_email_send.fn()
                                  ,EMAIL_SEND.id_table.fn()
                                  ,EMAIL_SEND.sender_adress.fn()
                                  ,EMAIL_SEND.send_type.fn()
                                  ,EMAIL_SEND.table_base_name.fn()
                                  ,EMAIL_SEND.text.fn()
                                  ,EMAIL_SEND.text_2_send.fn()
        );
        
        RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementCenter
                                 ,EMAIL_SEND.betreff.fn()
                                 ,EMAIL_SEND.betreff_2_send.fn()
                                 ,EMAIL_SEND.id_email_send.fn()
                                 ,EMAIL_SEND.id_table.fn()
                                 ,EMAIL_SEND.sender_adress.fn()
                                 ,EMAIL_SEND.send_type.fn()
                                 ,EMAIL_SEND.table_base_name.fn()
                                 ,EMAIL_SEND.text.fn()
                                 ,EMAIL_SEND.text_2_send.fn()
      	);
      	
      	RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
       	this._setLayoutTitles(gldTitelCenter
                                 ,EMAIL_SEND.betreff.fn()
                                 ,EMAIL_SEND.betreff_2_send.fn()
                                 ,EMAIL_SEND.id_email_send.fn()
                                 ,EMAIL_SEND.id_table.fn()
                                 ,EMAIL_SEND.sender_adress.fn()
                                 ,EMAIL_SEND.send_type.fn()
                                 ,EMAIL_SEND.table_base_name.fn()
                                 ,EMAIL_SEND.text.fn()
                                 ,EMAIL_SEND.text_2_send.fn()
      	);
  
  
        //hier kann das layout der einzelnen spalten definiert werden 
        // spaltenlayout fuer:  EMAIL_SEND.betreff.fn()
        this._setColExtent(     new Extent(EM2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND.betreff)), EMAIL_SEND.betreff.fn());
        this._setLayoutElements(new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.betreff))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND.betreff.fn());
        this._setLayoutTitles(  new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.betreff))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND.betreff.fn());
        // ----
        //
        // spaltenlayout fuer:  EMAIL_SEND.betreff_2_send.fn()
        this._setColExtent(     new Extent(EM2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND.betreff_2_send)), EMAIL_SEND.betreff_2_send.fn());
        this._setLayoutElements(new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.betreff_2_send))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND.betreff_2_send.fn());
        this._setLayoutTitles(  new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.betreff_2_send))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND.betreff_2_send.fn());
        // ----
        //
        // spaltenlayout fuer:  EMAIL_SEND.id_email_send.fn()
        this._setColExtent(     new Extent(EM2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND.id_email_send)), EMAIL_SEND.id_email_send.fn());
        this._setLayoutElements(new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.id_email_send))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND.id_email_send.fn());
        this._setLayoutTitles(  new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.id_email_send))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND.id_email_send.fn());
        // ----
        //
        // spaltenlayout fuer:  EMAIL_SEND.id_table.fn()
        this._setColExtent(     new Extent(EM2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND.id_table)), EMAIL_SEND.id_table.fn());
        this._setLayoutElements(new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.id_table))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND.id_table.fn());
        this._setLayoutTitles(  new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.id_table))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND.id_table.fn());
        // ----
        //
        // spaltenlayout fuer:  EMAIL_SEND.sender_adress.fn()
        this._setColExtent(     new Extent(EM2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND.sender_adress)), EMAIL_SEND.sender_adress.fn());
        this._setLayoutElements(new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.sender_adress))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND.sender_adress.fn());
        this._setLayoutTitles(  new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.sender_adress))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND.sender_adress.fn());
        // ----
        //
        // spaltenlayout fuer:  EMAIL_SEND.send_type.fn()
        this._setColExtent(     new Extent(EM2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND.send_type)), EMAIL_SEND.send_type.fn());
        this._setLayoutElements(new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.send_type))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND.send_type.fn());
        this._setLayoutTitles(  new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.send_type))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND.send_type.fn());
        // ----
        //
        // spaltenlayout fuer:  EMAIL_SEND.table_base_name.fn()
        this._setColExtent(     new Extent(EM2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND.table_base_name)), EMAIL_SEND.table_base_name.fn());
        this._setLayoutElements(new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.table_base_name))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND.table_base_name.fn());
        this._setLayoutTitles(  new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.table_base_name))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND.table_base_name.fn());
        // ----
        //
        // spaltenlayout fuer:  EMAIL_SEND.text.fn()
        this._setColExtent(     new Extent(EM2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND.text)), EMAIL_SEND.text.fn());
        this._setLayoutElements(new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.text))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND.text.fn());
        this._setLayoutTitles(  new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.text))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND.text.fn());
        // ----
        //
        // spaltenlayout fuer:  EMAIL_SEND.text_2_send.fn()
        this._setColExtent(     new Extent(EM2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(EMAIL_SEND.text_2_send)), EMAIL_SEND.text_2_send.fn());
        this._setLayoutElements(new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.text_2_send))._ins(3,1,3,1)._col(new E2_ColorDark()), EMAIL_SEND.text_2_send.fn());
        this._setLayoutTitles(  new RB_gld()._al(EM2_READABLE_FIELD_NAME.getAlignment(EMAIL_SEND.text_2_send))._ins(1,2,1,1)._col(new E2_ColorDark()), EMAIL_SEND.text_2_send.fn());
        // ----
        //
        this.set_oSubQueryAgent(new EM2_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
        
        this.setComponentMapMarker(new EM2_LIST_ComponentMapMapMarker(this));
        
    }
    
    
    
    @Override
    public Object get_Copy(Object objHelp) throws myExceptionCopy {
    	EM2_LIST_ComponentMap mapCopy = null;
    	try {
    		mapCopy = new EM2_LIST_ComponentMap(m_tpHashMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return mapCopy;
    }
    
	/*
     * umstellung auf E2_ComponentMAP_V22 mit marker-option
     * korrespontiert mit  
     */
    public class EM2_LIST_ComponentMapMapMarker extends E2_ComponentMapMarker {
        public EM2_LIST_ComponentMapMapMarker(E2_ComponentMAP_V22 p_map) {
            super(p_map);
        }
        @Override
        protected void innerFormat(Collection<Component> v) {
            super.innerFormat(v);
    		
            E2_ComponentMAP_V22 map = (E2_ComponentMAP_V22)this.getMap();
    		
            try {
                Rec22 recEM2 = map.getRec22();
                //hier koennen formatierungsoptionen hinterlegt werden
                
//                if (recEM2.is_no_db_val(EMAIL_SEND.aktiv)) {
//                    this.setLayoutBackgroundColorInMap(v,new Color(230,230,230));
//                    this.setTextColorInMap(v,Color.DARKGRAY);
//                }
    			
            } catch (myException e) {
                e.printStackTrace();
            }
        }
    }
  
    
    
    private class InfoIstVersand extends E2_UniversalListComponent {

    	private String key = "InfoIstVersand<227eb8e8-cf9e-465d-a2e9-b231ae13c2b7>";
    	
		/**
		 * @author martin
		 * @date 12.02.2021
		 *
		 */
		public InfoIstVersand() {
			super();
			this.EXT().setLongString4ColumnSelection(S.ms("Email-Versand OK ?"));
		}

		@Override
		public String key() throws myException {
			return key;
		}

		@Override
		public String userText() throws myException {
			return "OK?";
		}

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.components.DB.MyE2IF_DB_SimpleComponent#prepare_ContentForNew(boolean)
		 */
		@Override
		public void prepare_ContentForNew(boolean bSetDefault) throws myException {
			this._clear();
		}

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.components.DB.E2_UniversalListComponent#populate(panter.gmbh.indep.dataTools.SQLResultMAP)
		 */
		@Override
		public void populate(SQLResultMAP resultMap) throws myException {
			
			this._w100();
			
			if (m_tpHashMap.getAllowSendButton()) {
				this._s(3);   //sendbutton und anzahl gesendete/ungesendete
			} else {
				this._s(2);
			}
			
			
			Rec22EmailSend recEmail = new Rec22EmailSend(EM2_LIST_ComponentMap.this.getRec22());
			
			
			RecList22 rlTargets = recEmail.get_down_reclist22(EMAIL_SEND_TARGETS.id_email_send);
			
			int countOk = 0;
			int countFail = 0;
			
			for (Rec22 r: rlTargets) {
				if (r.is_yes_db_val(EMAIL_SEND_TARGETS.send_ok)) {
					countOk++;
				} else {
					countFail++;
				}
			}
			
			this._clear();
			if (m_tpHashMap.getAllowSendButton()) {
				this._setSize(30,30,30);  //sendbutton und anzahl gesendete/ungesendete
			} else {
				this._setSize(30,30);
			}

			RB_gld gldNeutral = new RB_gld()._center_mid()._ins(2);
			RB_gld gldOffen   = new RB_gld()._center_mid()._ins(2)._col_back_red();
			RB_gld gldDone    = new RB_gld()._center_mid()._ins(2)._col_back_green();
			
			//		MyE2_ButtonInLIST bt_send = new MyE2_ButtonInLIST(E2_ResourceIcon.get_RI(eMailIsDone?"email_done.png":"email.png"),true);
			
			if (countOk==0) {
				this._a(new RB_lab()._t(""+countOk)._fsa(2),gldNeutral);
			} else {
				this._a(new RB_lab()._t(""+countOk)._fsa(2),gldDone);
			}
			if (countFail==0) {
				this._a(new RB_lab()._t(""+countFail)._fsa(2),gldNeutral);
			} else {
				this._a(new RB_lab()._t(""+countFail)._fsa(2),gldOffen);
			}
			
			if (m_tpHashMap.getAllowSendButton()) {
				try {
					if (recEmail.getSendStatus()==SEND_STATUS.SEND_ALL) {
						this._a(new RB_lab()._icon(E2_ResourceIcon.get_RI("email_done.png")), gldNeutral);
					} else {
						this._a(new ButtonSend(recEmail), gldNeutral);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public Object get_Copy(Object objHelp) throws myExceptionCopy {
			return new InfoIstVersand();
		}

		@Override
		public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		}


		private class ButtonSend extends E2_Button {
			public ButtonSend(Rec22EmailSend recMail) {
				super();
				this._image(E2_ResourceIcon.get_RI("email.png"));
				
				this._aaa(()-> {
					try {
						recMail._sendEmail(null);
						m_tpHashMap.getNavigationList().Refresh_ComponentMAP(recMail.getIdLong().toString(), E2_ComponentMAP.STATUS_VIEW);
						bibMSG.MV()._addInfo(S.ms("Emails wurden verschickt! Ziele:\n").ut(recMail.getLastSendedEmailsOK().concatenante("\n")));
					} catch (MailException e) {
						e.printStackTrace();
						bibMSG.MV()._addAlarm(S.ms("eMail-Fehler beim eMail-Versand\n").ut(recMail.getLastSendedEmailsError().concatenante("\n")));
						bibMSG.MV()._add(e);
					} catch (Exception e) {
						e.printStackTrace();
						bibMSG.MV()._addAlarm(S.ms("Fehler beim eMail-Versand"));
						bibMSG.MV()._add(e);
					}
				});
			}
		}
		
    	
    }
    

    
    
    
}
 
 

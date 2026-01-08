 
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ZUSATZINFOS.MaskBased;
  
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_IF_Rec21;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseInListRow;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_GenericListComponentShowAddonDocuments;
import panter.gmbh.Echo2.components.DB.E2_DBActiveLabelForLists;
import panter.gmbh.Echo2.components.DB.E2_DBLabelTranslateEnumToText;
import panter.gmbh.Echo2.components.DB.E2_DBLabelTranslateIdToText;
import panter.gmbh.Echo2.components.DB.E2_DBLabelUserInList;
import panter.gmbh.Echo2.components.DB.E2_LabelLastChangeTimeStamp;
import panter.gmbh.Echo2.components.DB.E2_LabelLastChangeUser;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentGrid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.ENUM_AdressInfoMessageType;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO;
import panter.gmbh.basics4project.DB_ENUMS.AKTIONSANLASS;
import panter.gmbh.basics4project.DB_ENUMS.BESUCHSERGEBNIS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_INFO;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.HMAP;
  
public class AI_LIST_ComponentMap extends E2_ComponentMAP  implements E2_ComponentMAP_IF_Rec21  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public AI_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new AI_LIST_SqlFieldMAP(p_tpHashMap));
        this.m_tpHashMap = p_tpHashMap;        
        AI__TYP typ = (AI__TYP)p_tpHashMap.getFromExtender(AI_TransportExtender.TYP_INFO_OR_MELDUNG);
        
      	if (typ==AI__TYP.INFO) {
      		baueListeInfos();
      	} else {
      		baueListeMeldungen();
      	}
        
        this.set_oSubQueryAgent(new AI_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
        this.set_Factory4Records(new factory4Records());
    }
    
    
    public AI_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap, SQLFieldMAP oFM) throws myException  {
    	  
    	super(oFM);
        this.m_tpHashMap = p_tpHashMap;        
        AI__TYP typ = (AI__TYP)p_tpHashMap.getFromExtender(AI_TransportExtender.TYP_INFO_OR_MELDUNG);
        
      	if (typ==AI__TYP.INFO) {
      		baueListeInfos();
      	} else {
      		baueListeMeldungen();
      	}
        
        this.set_oSubQueryAgent(new AI_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
        this.set_Factory4Records(new factory4Records());
    }
    
    
    
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_ADRESSE_INFO(cID_MAINTABLE);
        }
    }
    
  
    
    private void baueListeInfos() throws myException {
        AI__TYP typ = (AI__TYP)m_tpHashMap.getFromExtender(AI_TransportExtender.TYP_INFO_OR_MELDUNG);

        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        
        this.add_Component(AI_CONST.AI__NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(AI_CONST.AI__NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(AI_CONST.AI__NAMES.DIRECT_DEL.db_val(),    	new AI_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(AI_CONST.AI__NAMES.DIRECT_EDIT.db_val(),   	new AI_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(AI_CONST.AI__NAMES.DIRECT_VIEW.db_val(),   	new AI_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        this.add_Component(AI_CONST.AI__NAMES.DIRECT_UPLOAD.db_val(), new E2_ButtonAttachmentUseInListRow()
																					._addGlobalValidator(typ.getAttachValidator())
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
        																			, S.ms("?"));
        
        MyE2_DB_MultiComponentGrid gridERFASSUNG_ANLASS = new MyE2_DB_MultiComponentGrid(1);
        gridERFASSUNG_ANLASS.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ADRESSE_INFO.datumeintrag)), S.ms(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.datumeintrag)), null);
        gridERFASSUNG_ANLASS.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ADRESSE_INFO.kuerzel)), S.ms(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.kuerzel)), null);
        gridERFASSUNG_ANLASS.add_Component(new E2_DBLabelTranslateIdToText(	oFM.get_(ADRESSE_INFO.id_aktionsanlass.fn()))
        																			._setTab(_TAB.aktionsanlass)
        																			._addFieldsToShow(new HMAP<IF_Field,String>()
        																										._put(AKTIONSANLASS.kurzbezeichnung, "?"))
        																			,S.ms(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_aktionsanlass)), null);

        gridERFASSUNG_ANLASS.add_Component(new AI_LIST_btSendAdhocMessage(), S.ms("Sofortmeldung"), AI_CONST.AI__NAMES.SEND_ADHOC_MESSAGE.db_val());
        
        
        MyE2_DB_MultiComponentGrid gridDATUM_UND_WV = new MyE2_DB_MultiComponentGrid(1);
        gridDATUM_UND_WV.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ADRESSE_INFO.datumereignis)), S.ms(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.datumereignis)), null);
        gridDATUM_UND_WV.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ADRESSE_INFO.folgedatum)), S.ms(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.folgedatum)), null);
        gridDATUM_UND_WV.add_Component(new MyE2_DB_CheckBox(oFM.get_(ADRESSE_INFO.wiederholungmonatlich.fn())),	S.ms(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.wiederholungmonatlich)), null);
        gridDATUM_UND_WV.add_Component(new MyE2_DB_CheckBox(oFM.get_(ADRESSE_INFO.wiederholungjaehrlich.fn())),	S.ms(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.wiederholungjaehrlich)), null);
       
        
        MyE2_DB_MultiComponentGrid gridBENUTZER = new MyE2_DB_MultiComponentGrid(1);
        gridBENUTZER.add_Component(new E2_DBLabelUserInList(oFM.get_(ADRESSE_INFO.id_user)), 					S.ms(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_user)), null);
        gridBENUTZER.add_Component(new E2_DBLabelUserInList(oFM.get_(ADRESSE_INFO.id_user_ersatz)), 			S.ms(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_user_ersatz)), null);
        gridBENUTZER.add_Component(new E2_DBLabelUserInList(oFM.get_(ADRESSE_INFO.id_user_sachbearbeiter.fn())),S.ms(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_user_sachbearbeiter)), null);
        
        MyE2_DB_MultiComponentGrid gridERGEBNIS = new MyE2_DB_MultiComponentGrid(1);
        gridERGEBNIS.add_Component(new E2_DBLabelTranslateIdToText(	oFM.get_(ADRESSE_INFO.id_besuchsergebnis1.fn()))
																		._setTab(_TAB.besuchsergebnis)
																		._addFieldsToShow(new HMAP<IF_Field,String>()
																									._put(BESUCHSERGEBNIS.kurzbezeichnung, "?"))
																	,S.ms(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_besuchsergebnis1)), null);
        gridERGEBNIS.add_Component(new E2_DBLabelTranslateIdToText(	oFM.get_(ADRESSE_INFO.id_besuchsergebnis2.fn()))
																		._setTab(_TAB.besuchsergebnis)
																		._addFieldsToShow(new HMAP<IF_Field,String>()
																									._put(BESUCHSERGEBNIS.kurzbezeichnung, "?"))
																	,S.ms(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_besuchsergebnis2)), null);
        gridERGEBNIS.add_Component(new E2_DBLabelTranslateIdToText(	oFM.get_(ADRESSE_INFO.id_besuchsergebnis3.fn()))
																		._setTab(_TAB.besuchsergebnis)
																		._addFieldsToShow(new HMAP<IF_Field,String>()
																									._put(BESUCHSERGEBNIS.kurzbezeichnung, "?"))
																	,S.ms(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_besuchsergebnis3)), null);
        
        
        this.add_Component(AI_CONST.AI__NAMES.INFO_BLOCK_ERFASSUNG_ANLASS.db_val(), 	gridERFASSUNG_ANLASS, S.ms("Erfassung / Anlass"));
        this.add_Component(AI_CONST.AI__NAMES.INFO_BLOCK_DATUM_UND_WV.db_val(), 		gridDATUM_UND_WV, S.ms("Datum / Wiedervorlage"));
        this.add_Component(AI_CONST.AI__NAMES.INFO_BLOCK_BENUTZER.db_val(), 			gridBENUTZER, S.ms("Benutzer"));
        this.add_Component(new MyE2_DB_TextArea(oFM.get_(ADRESSE_INFO.text),400,4),  	new MyE2_String(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.text)));
        this.add_Component(AI_CONST.AI__NAMES.INFO_BLOCK_ERGEBNIS.db_val(), 			gridERGEBNIS, S.ms("Ergebnisse"));
        

		this.add_Component(E2_GenericListComponentShowAddonDocuments.LISTKEY4COMPONENTMAP,
				new E2_GenericListComponentShowAddonDocuments(), E2_GenericListComponentShowAddonDocuments.LISTINFO4COMPONENTMAP);

        this.add_Component(new E2_DBActiveLabelForLists(oFM.get_(ADRESSE_INFO.id_adresse_info)),  	new MyE2_String(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_adresse_info)));

        
        this._setLineWrapListHeaderInAll(true 
                                  ,ADRESSE_INFO.datumeintrag.fn()
                                  ,ADRESSE_INFO.datumereignis.fn()
                                  ,ADRESSE_INFO.kuerzel.fn()
                                  ,ADRESSE_INFO.folgedatum.fn()
                                  ,ADRESSE_INFO.id_aktionsanlass.fn()
                                  ,ADRESSE_INFO.id_besuchsergebnis1.fn()
                                  ,ADRESSE_INFO.id_besuchsergebnis2.fn()
                                  ,ADRESSE_INFO.id_besuchsergebnis3.fn()
                                  ,ADRESSE_INFO.id_user.fn()
                                  ,ADRESSE_INFO.id_user_ersatz.fn()
                                  ,ADRESSE_INFO.id_user_sachbearbeiter.fn()
                                  ,ADRESSE_INFO.text.fn()
                                  ,ADRESSE_INFO.wiederholungjaehrlich.fn()
                                  ,ADRESSE_INFO.wiederholungmonatlich.fn()
        );

       	this._setLayoutElementsInAll(new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase()),ADRESSE_INFO.id_adresse_info.fn());

        
        RB_gld gldLeft = 	new RB_gld()._left_top()._ins(2,4,2,2)._col(new E2_ColorBase());
       	this._setLayoutElementsInAll(gldLeft
				                ,ADRESSE_INFO.datumeintrag.fn()
				                ,ADRESSE_INFO.datumereignis.fn()
				                ,ADRESSE_INFO.kuerzel.fn()
				                ,ADRESSE_INFO.folgedatum.fn()
				                ,ADRESSE_INFO.id_aktionsanlass.fn()
				                ,ADRESSE_INFO.id_besuchsergebnis1.fn()
				                ,ADRESSE_INFO.id_besuchsergebnis2.fn()
				                ,ADRESSE_INFO.id_besuchsergebnis3.fn()
				                ,ADRESSE_INFO.id_user.fn()
				                ,ADRESSE_INFO.id_user_ersatz.fn()
				                ,ADRESSE_INFO.id_user_sachbearbeiter.fn()
				                ,ADRESSE_INFO.text.fn()
				                ,ADRESSE_INFO.wiederholungjaehrlich.fn()
				                ,ADRESSE_INFO.wiederholungmonatlich.fn()
				      	);
      	

       	this._setLayoutTitlesInAll(new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorDark()),ADRESSE_INFO.id_adresse_info.fn());
       	
      	RB_gld gldTitelLeft = 	new RB_gld()._left_top()._ins(2,2,2,2)._col(new E2_ColorDark());
       	this._setLayoutTitlesInAll(gldTitelLeft
			                ,ADRESSE_INFO.datumeintrag.fn()
			                ,ADRESSE_INFO.datumereignis.fn()
			                ,ADRESSE_INFO.kuerzel.fn()
			                ,ADRESSE_INFO.folgedatum.fn()
			                ,ADRESSE_INFO.id_aktionsanlass.fn()
			                ,ADRESSE_INFO.id_besuchsergebnis1.fn()
			                ,ADRESSE_INFO.id_besuchsergebnis2.fn()
			                ,ADRESSE_INFO.id_besuchsergebnis3.fn()
			                ,ADRESSE_INFO.id_user.fn()
			                ,ADRESSE_INFO.id_user_ersatz.fn()
			                ,ADRESSE_INFO.id_user_sachbearbeiter.fn()
			                ,ADRESSE_INFO.text.fn()
			                ,ADRESSE_INFO.wiederholungjaehrlich.fn()
			                ,ADRESSE_INFO.wiederholungmonatlich.fn()
      	);
  
  
        //hier kann das layout der einzelnen spalten definiert werden 
        this._setColExtent(     new Extent(AI_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ADRESSE_INFO.datumeintrag)), 			AI_CONST.AI__NAMES.INFO_BLOCK_ERFASSUNG_ANLASS.db_val());
        this._setColExtent(     new Extent(AI_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ADRESSE_INFO.datumereignis)), 			AI_CONST.AI__NAMES.INFO_BLOCK_DATUM_UND_WV.db_val());
        this._setColExtent(     new Extent(AI_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ADRESSE_INFO.id_user)), 				AI_CONST.AI__NAMES.INFO_BLOCK_BENUTZER.db_val());
        this._setColExtent(     new Extent(AI_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ADRESSE_INFO.id_besuchsergebnis1)), 	AI_CONST.AI__NAMES.INFO_BLOCK_ERGEBNIS.db_val());
        this._setColExtent(     new Extent(200), E2_GenericListComponentShowAddonDocuments.LISTKEY4COMPONENTMAP);

      	
        this.set_oSubQueryAgent(new AI_LIST_FORMATING_Agent(this.m_tpHashMap));

    }
    
    private void baueListeMeldungen() throws myException {
        AI__TYP typ = (AI__TYP)m_tpHashMap.getFromExtender(AI_TransportExtender.TYP_INFO_OR_MELDUNG);

        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(AI_CONST.AI__NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(AI_CONST.AI__NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(AI_CONST.AI__NAMES.DIRECT_DEL.db_val(),    	new AI_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(AI_CONST.AI__NAMES.DIRECT_EDIT.db_val(),   	new AI_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(AI_CONST.AI__NAMES.DIRECT_VIEW.db_val(),   	new AI_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        this.add_Component(AI_CONST.AI__NAMES.DIRECT_UPLOAD.db_val(), 	new E2_ButtonAttachmentUseInListRow()
																					._addGlobalValidator(typ.getAttachValidator())
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
        																			, S.ms("?"));
        
        //hier kommen die Felder  
        
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(ADRESSE_INFO.aktiv),true),     		new MyE2_String(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.aktiv)));
        this.add_Component(new MyE2_DB_TextArea(oFM.get_(ADRESSE_INFO.text),400,4),  			new MyE2_String(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.text)));
        this.add_Component(new E2_DBLabelTranslateEnumToText(oFM.get_(ADRESSE_INFO.message_typ))._setEnum(ENUM_AdressInfoMessageType.ADRESS_INFO_TYP_ALLGEMEIN),  
        																						new MyE2_String(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.message_typ)));

		this.add_Component(E2_GenericListComponentShowAddonDocuments.LISTKEY4COMPONENTMAP,
				new E2_GenericListComponentShowAddonDocuments(), E2_GenericListComponentShowAddonDocuments.LISTINFO4COMPONENTMAP);
      
		this.add_Component(AI_CONST.AI__NAMES.LAST_CHANGE.db_val(), new E2_LabelLastChangeTimeStamp()
					._setSortTerms(	ADRESSE_INFO.letzte_aenderung.tnfn()+" ASC"
									,ADRESSE_INFO.letzte_aenderung.tnfn()+" DESC", 
									S.msUt(AI_CONST.AI__NAMES.LAST_CHANGE.user_text())),S.msUt(AI_CONST.AI__NAMES.LAST_CHANGE.user_text()));
		this.add_Component(AI_CONST.AI__NAMES.LAST_USER.db_val(), new E2_LabelLastChangeUser()
					._setSortTerms(	ADRESSE_INFO.geaendert_von.tnfn()+" ASC"
									,ADRESSE_INFO.geaendert_von.tnfn()+" DESC"
									,S.msUt(AI_CONST.AI__NAMES.LAST_USER.user_text())),S.msUt(AI_CONST.AI__NAMES.LAST_USER.user_text()));
		
		
        this.add_Component(new E2_DBActiveLabelForLists(oFM.get_(ADRESSE_INFO.id_adresse_info)),  	new MyE2_String(AI_READABLE_FIELD_NAME.getReadable(ADRESSE_INFO.id_adresse_info)));

        //neu ab 20171025        
        this._setLineWrapListHeader(true 
                                  ,ADRESSE_INFO.aktiv.fn()
                                  ,ADRESSE_INFO.text.fn()
                                  ,ADRESSE_INFO.message_typ.fn()
        );
        
       	this._setLayoutElements(new RB_gld()._left_top()._ins(2,4,2,2)._col(new E2_ColorBase())
                ,ADRESSE_INFO.aktiv.fn()
                ,ADRESSE_INFO.text.fn()
                ,ADRESSE_INFO.message_typ.fn()
                ,E2_GenericListComponentShowAddonDocuments.LISTKEY4COMPONENTMAP
                ,AI_CONST.AI__NAMES.LAST_CHANGE.db_val()
                ,AI_CONST.AI__NAMES.LAST_USER.db_val()
      	);
      	
       	this._setLayoutTitles(new RB_gld()._left_top()._ins(1,2,1,1)._col(new E2_ColorDark())
                ,ADRESSE_INFO.aktiv.fn()
                ,ADRESSE_INFO.text.fn()
                ,ADRESSE_INFO.message_typ.fn()
                ,E2_GenericListComponentShowAddonDocuments.LISTKEY4COMPONENTMAP
                ,AI_CONST.AI__NAMES.LAST_CHANGE.db_val()
                ,AI_CONST.AI__NAMES.LAST_USER.db_val()
      	);
       	
       	this._setLayoutElementsInAll(new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase()),ADRESSE_INFO.id_adresse_info.fn());
       	this._setLayoutTitlesInAll(new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorDark()),ADRESSE_INFO.id_adresse_info.fn());

  
        //hier kann das layout der einzelnen spalten definiert werden 
        // spaltenlayout fuer:  ADRESSE_INFO.aktiv.fn()
        this._setColExtent(     new Extent(AI_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ADRESSE_INFO.aktiv)), ADRESSE_INFO.aktiv.fn());
        this._setColExtent(     new Extent(AI_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ADRESSE_INFO.text)), ADRESSE_INFO.text.fn());
        this._setColExtent(     new Extent(AI_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ADRESSE_INFO.message_typ)), ADRESSE_INFO.message_typ.fn());
        this._setColExtent(     new Extent(200), E2_GenericListComponentShowAddonDocuments.LISTKEY4COMPONENTMAP);

        this.set_oSubQueryAgent(new AI_LIST_FORMATING_Agent(this.m_tpHashMap));

    }
    
	
	//2014-04-04: neue version der copy-struktur mit statischer hilfsmethode
	public Object get_Copy(Object objHelp) throws myExceptionCopy	{
		try {
			AI_LIST_ComponentMap oRueck = new AI_LIST_ComponentMap(this.m_tpHashMap, this.get_oSQLFieldMAP());
			return oRueck;
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
 
 

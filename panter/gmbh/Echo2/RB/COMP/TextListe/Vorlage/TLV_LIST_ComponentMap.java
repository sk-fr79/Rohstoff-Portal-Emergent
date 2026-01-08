 
package panter.gmbh.Echo2.RB.COMP.TextListe.Vorlage;
  
  
import java.util.Collection;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseInListRow;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE_VORLAGE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
  
public class TLV_LIST_ComponentMap extends E2_ComponentMAP_V2  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public TLV_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new TLV_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(TLV_CONST.TLV_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(TLV_CONST.TLV_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(TLV_CONST.TLV_NAMES.DIRECT_DEL.db_val(),    	new TLV_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(TLV_CONST.TLV_NAMES.DIRECT_EDIT.db_val(),   	new TLV_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(TLV_CONST.TLV_NAMES.DIRECT_VIEW.db_val(),   	new TLV_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        this.add_Component(TLV_CONST.TLV_NAMES.DIRECT_UPLOAD.db_val(), new E2_ButtonAttachmentUseInListRow()
																					._addGlobalValidator(TLV_VALIDATORS.ATTACHMENT.getValidator())
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
        																			, S.ms("?"));
        
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TEXT_LISTE_VORLAGE.tablereference),true),     	new MyE2_String(TLV_READABLE_FIELD_NAME.getReadable(TEXT_LISTE_VORLAGE.tablereference)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TEXT_LISTE_VORLAGE.bezeichnung),true),    			 	new MyE2_String(TLV_READABLE_FIELD_NAME.getReadable(TEXT_LISTE_VORLAGE.bezeichnung)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TEXT_LISTE_VORLAGE.id_text_liste_vorlage),true),     	new MyE2_String(TLV_READABLE_FIELD_NAME.getReadable(TEXT_LISTE_VORLAGE.id_text_liste_vorlage)));
 
        //neu ab 20171025        
        this._setLineWrapListHeader(true 
                                  ,TEXT_LISTE_VORLAGE.bezeichnung.fn()
                                  ,TEXT_LISTE_VORLAGE.id_text_liste_vorlage.fn()
                                  ,TEXT_LISTE_VORLAGE.tablereference.fn()
                                  
        );
        
//        RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
//       	this._setLayoutElements(gldElementCenter
//                                 ,TEXT_LISTE_VORLAGE.bezeichnung.fn()
//                                 ,TEXT_LISTE_VORLAGE.id_text_liste_vorlage.fn()
//                                 ,TEXT_LISTE_VORLAGE.tablename_4_textliste.fn()
//      	);
//      	
//      	RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
//       	this._setLayoutTitles(gldTitelCenter
//                                 ,TEXT_LISTE_VORLAGE.bezeichnung.fn()
//                                 ,TEXT_LISTE_VORLAGE.id_text_liste_vorlage.fn()
//                                 ,TEXT_LISTE_VORLAGE.tablename_4_textliste.fn()
//      	);
  
  
        //hier kann das layout der einzelnen spalten definiert werden 
        //
        // spaltenlayout fuer:  TEXT_LISTE_VORLAGE.bezeichnung.fn()
        this._setColExtent(     new Extent(TLV_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE_VORLAGE.bezeichnung)), TEXT_LISTE_VORLAGE.bezeichnung.fn());
        this._setLayoutElements(new RB_gld()._al(TLV_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE_VORLAGE.bezeichnung))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE_VORLAGE.bezeichnung.fn());
        this._setLayoutTitles(  new RB_gld()._al(TLV_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE_VORLAGE.bezeichnung))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE_VORLAGE.bezeichnung.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE_VORLAGE.id_text_liste_vorlage.fn()
        this._setColExtent(     new Extent(TLV_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE_VORLAGE.id_text_liste_vorlage)), TEXT_LISTE_VORLAGE.id_text_liste_vorlage.fn());
        this._setLayoutElements(new RB_gld()._al(TLV_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE_VORLAGE.id_text_liste_vorlage))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE_VORLAGE.id_text_liste_vorlage.fn());
        this._setLayoutTitles(  new RB_gld()._al(TLV_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE_VORLAGE.id_text_liste_vorlage))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE_VORLAGE.id_text_liste_vorlage.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE_VORLAGE.tablename_4_textliste.fn()
        this._setColExtent(     new Extent(TLV_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE_VORLAGE.tablereference)), TEXT_LISTE_VORLAGE.tablereference.fn());
        this._setLayoutElements(new RB_gld()._al(TLV_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE_VORLAGE.tablereference))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE_VORLAGE.tablereference.fn());
        this._setLayoutTitles(  new RB_gld()._al(TLV_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE_VORLAGE.tablereference))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE_VORLAGE.tablereference.fn());
        // ----
        //
      	
        this.set_oSubQueryAgent(new TLV_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
//        this.set_Factory4Records(new factory4Records());
    }
    
//    private class factory4Records extends E2_ComponentMAP_Factory4Records {
//        @Override
//        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
//            return new RECORD_TEXT_LISTE_VORLAGE(cID_MAINTABLE);
//        }
//    }
    
    
    @Override
    public Object get_Copy(Object objHelp) throws myExceptionCopy {
        E2_ComponentMAP_V2 oRueck = new E2_ComponentMAP_V2(this.get_oSQLFieldMAP());
        E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
        oRueck.setComponentMapMarker(new TLV_LIST_ComponentMapMapMarker(oRueck));
	
        return oRueck;
    }
    
	/*
     * umstellung auf E2_ComponentMAP_V2 mit marker-option
     * korrespontiert mit  
     */
    public class TLV_LIST_ComponentMapMapMarker extends E2_ComponentMapMarker {
        public TLV_LIST_ComponentMapMapMarker(E2_ComponentMAP_V2 p_map) {
            super(p_map);
        }
        @Override
        protected void innerFormat(Collection<Component> v) {
            super.innerFormat(v);
    		
            E2_ComponentMAP_V2 map = (E2_ComponentMAP_V2)this.getMap();
    		
            try {
                Rec21 recTLV = map.getRec21();
                //hier koennen formatierungsoptionen hinterlegt werden
                
//                if (recTLV.is_no_db_val(TEXT_LISTE_VORLAGE.aktiv)) {
//                    this.setLayoutBackgroundColorInMap(v,new Color(230,230,230));
//                    this.setTextColorInMap(v,Color.DARKGRAY);
//                }
    			
            } catch (myException e) {
                e.printStackTrace();
            }
        }
    }
  
    
}
 
 

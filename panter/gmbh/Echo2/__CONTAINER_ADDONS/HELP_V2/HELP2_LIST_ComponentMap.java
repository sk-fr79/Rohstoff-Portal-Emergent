 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;
  
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseInListRow;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HILFETEXT;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class HELP2_LIST_ComponentMap extends E2_ComponentMAP  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public HELP2_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new HELP2_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(HELP2_CONST.HELP2_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(HELP2_CONST.HELP2_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(HELP2_CONST.HELP2_NAMES.DIRECT_DEL.db_val(),    	new HELP2_LIST_btDeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(HELP2_CONST.HELP2_NAMES.DIRECT_EDIT.db_val(),   	new HELP2_LIST_btListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(HELP2_CONST.HELP2_NAMES.DIRECT_VIEW.db_val(),   	new HELP2_LIST_btListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        
        this.add_Component(HELP2_CONST.HELP2_NAMES.DIRECT_UPLOAD.db_val(), new E2_ButtonAttachmentUseInListRow()
        																			._addGlobalValidator(ENUM_VALIDATION.HILFETEXT_ATTACHMENT.getValidator())
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
        																			, S.ms("?"));
        
        
        //hier kommen die Felder  
        this.add_Component(HELP2_LIST_CompInfoBlock.key, new HELP2_LIST_CompInfoBlock(this.m_tpHashMap), 			S.ms("Infos/Version"));
        this.add_Component(HELP2_LIST_CompTextBlock.key, new HELP2_LIST_CompTextBlock(this.m_tpHashMap), 			S.ms(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.hilfetext)));
        this.add_Component(new HELP2_LIST_user(oFM.get_(HILFETEXT.id_user_bearbeiter)), 			S.ms(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.id_user_bearbeiter)));
        this.add_Component(new HELP2_LIST_user(oFM.get_(HILFETEXT.id_user_ursprung)),  				S.ms(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.id_user_ursprung)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(HILFETEXT.ticketnummer),true),     	S.ms(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.ticketnummer)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(HILFETEXT.id_hilfetext),true),     	S.ms(HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.id_hilfetext)));
        
        
        //neu ab 20171025        
        this._setLineWrapListHeader(true 
                                  ,HILFETEXT.id_user_bearbeiter.fn()
                                  ,HILFETEXT.id_user_ursprung.fn()
        );
        

  
        //
        // spaltenlayout fuer:  infoblock
        this._setColExtent(     new Extent(HELP2_LIST_CompInfoBlock.widthSpalte), HELP2_LIST_CompInfoBlock.key);
        this._setLayoutElements(new RB_gld()._left_top()._ins(3,1,3,1)._col(new E2_ColorBase()), HELP2_LIST_CompInfoBlock.key);
        this._setLayoutTitles(  new RB_gld()._left_top()._ins(1,2,1,1)._col(new E2_ColorDark()), HELP2_LIST_CompInfoBlock.key);

        // spaltenlayout fuer:  Textblock
        this._setColExtent(     new Extent(HELP2_CONST.HELP2_NUM_CONST.WIDTH_COL_WITH_TEXT_AND_IMAGES.getValue()), HELP2_LIST_CompTextBlock.key);
        this._setLayoutElements(new RB_gld()._left_top()._ins(3,1,3,1)._col(new E2_ColorBase()), HELP2_LIST_CompTextBlock.key);
        this._setLayoutTitles(  new RB_gld()._left_top()._ins(1,2,1,1)._col(new E2_ColorDark()), HELP2_LIST_CompTextBlock.key);

        // ----
        //
        // spaltenlayout fuer:  HILFETEXT.id_hilfetext.fn()
        this._setColExtent(     new Extent(HELP2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(HILFETEXT.id_hilfetext)), HILFETEXT.id_hilfetext.fn());
        this._setLayoutElements(new RB_gld()._al(HELP2_READABLE_FIELD_NAME.getAlignment(HILFETEXT.id_hilfetext))._ins(3,1,3,1)._col(new E2_ColorBase()), HILFETEXT.id_hilfetext.fn());
        this._setLayoutTitles(  new RB_gld()._al(HELP2_READABLE_FIELD_NAME.getAlignment(HILFETEXT.id_hilfetext))._ins(1,2,1,1)._col(new E2_ColorDark()), HILFETEXT.id_hilfetext.fn());
        // ----
        //
        // spaltenlayout fuer:  HILFETEXT.id_user_bearbeiter.fn()
        this._setColExtent(     new Extent(HELP2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(HILFETEXT.id_user_bearbeiter)), HILFETEXT.id_user_bearbeiter.fn());
        this._setLayoutElements(new RB_gld()._al(HELP2_READABLE_FIELD_NAME.getAlignment(HILFETEXT.id_user_bearbeiter))._ins(3,1,3,1)._col(new E2_ColorBase()), HILFETEXT.id_user_bearbeiter.fn());
        this._setLayoutTitles(  new RB_gld()._al(HELP2_READABLE_FIELD_NAME.getAlignment(HILFETEXT.id_user_bearbeiter))._ins(1,2,1,1)._col(new E2_ColorDark()), HILFETEXT.id_user_bearbeiter.fn());
        // ----
        //
        // spaltenlayout fuer:  HILFETEXT.id_user_ursprung.fn()
        this._setColExtent(     new Extent(HELP2_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(HILFETEXT.id_user_ursprung)), HILFETEXT.id_user_ursprung.fn());
        this._setLayoutElements(new RB_gld()._al(HELP2_READABLE_FIELD_NAME.getAlignment(HILFETEXT.id_user_ursprung))._ins(3,1,3,1)._col(new E2_ColorBase()), HILFETEXT.id_user_ursprung.fn());
        this._setLayoutTitles(  new RB_gld()._al(HELP2_READABLE_FIELD_NAME.getAlignment(HILFETEXT.id_user_ursprung))._ins(1,2,1,1)._col(new E2_ColorDark()), HILFETEXT.id_user_ursprung.fn());
        // ----
      	
        this.set_oSubQueryAgent(new HELP2_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
        this.set_Factory4Records(new factory4Records());
    }
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_HILFETEXT(cID_MAINTABLE);
        }
    }
    
  
    
}
 
 

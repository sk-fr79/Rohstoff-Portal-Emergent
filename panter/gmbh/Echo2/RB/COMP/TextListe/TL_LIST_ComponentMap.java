 
package panter.gmbh.Echo2.RB.COMP.TextListe;
  
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
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
  
public class TL_LIST_ComponentMap extends E2_ComponentMAP_V2  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public TL_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new TL_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(TL_CONST.TL_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(TL_CONST.TL_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(TL_CONST.TL_NAMES.DIRECT_DEL.db_val(),    	new TL_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(TL_CONST.TL_NAMES.DIRECT_EDIT.db_val(),   	new TL_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(TL_CONST.TL_NAMES.DIRECT_VIEW.db_val(),   	new TL_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        this.add_Component(TL_CONST.TL_NAMES.DIRECT_UPLOAD.db_val(), new E2_ButtonAttachmentUseInListRow()
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
        																			, S.ms("?"));
        
        //hier kommen die Felder
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TEXT_LISTE.position),true),     					new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.position)));

        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TEXT_LISTE.titel_text),true),     					new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.titel_text)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TEXT_LISTE.fontsize_titel_text),true),     		new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.fontsize_titel_text)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(TEXT_LISTE.bold_titel_text),true),     				new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.bold_titel_text)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(TEXT_LISTE.italic_titel_text),true),     				new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.italic_titel_text)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(TEXT_LISTE.underline_titel_text),true),     			new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.underline_titel_text)));

        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TEXT_LISTE.aufzaehl_text),true),     				new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.aufzaehl_text)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TEXT_LISTE.fontsize_aufzaehl_text),true),     		new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.fontsize_aufzaehl_text)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(TEXT_LISTE.bold_aufzaehl_text),true),     				new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.bold_aufzaehl_text)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(TEXT_LISTE.italic_aufzaehl_text),true),     			new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.italic_aufzaehl_text)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(TEXT_LISTE.underline_aufzaehl_text),true),     		new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.underline_aufzaehl_text)));
        
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TEXT_LISTE.lang_text),true),     					new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.lang_text)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TEXT_LISTE.fontsize_lang_text),true),     			new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.fontsize_lang_text)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(TEXT_LISTE.bold_lang_text),true),     					new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.bold_lang_text)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(TEXT_LISTE.italic_lang_text),true),     				new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.italic_lang_text)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(TEXT_LISTE.underline_lang_text),true),     			new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.underline_lang_text)));
        
     
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TEXT_LISTE.position_enum),true),     				new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.position_enum)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TEXT_LISTE.tablename),true),     					new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.tablename)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TEXT_LISTE.id_table),true),     					new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.id_table)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TEXT_LISTE.id_text_liste),true),     				new MyE2_String(TL_READABLE_FIELD_NAME.getReadableListHeader(TEXT_LISTE.id_text_liste)));

        //neu ab 20171025        
        this._setLineWrapListHeader(true 
                                  ,TEXT_LISTE.aufzaehl_text.fn()
                                  ,TEXT_LISTE.bold_aufzaehl_text.fn()
                                  ,TEXT_LISTE.bold_lang_text.fn()
                                  ,TEXT_LISTE.bold_titel_text.fn()
                                  ,TEXT_LISTE.fontsize_aufzaehl_text.fn()
                                  ,TEXT_LISTE.fontsize_lang_text.fn()
                                  ,TEXT_LISTE.fontsize_titel_text.fn()
                                  ,TEXT_LISTE.id_table.fn()
                                  ,TEXT_LISTE.id_text_liste.fn()
                                  ,TEXT_LISTE.italic_aufzaehl_text.fn()
                                  ,TEXT_LISTE.italic_lang_text.fn()
                                  ,TEXT_LISTE.italic_titel_text.fn()
                                  ,TEXT_LISTE.lang_text.fn()
                                  ,TEXT_LISTE.position_enum.fn()
                                  ,TEXT_LISTE.position.fn()
                                  ,TEXT_LISTE.tablename.fn()
                                  ,TEXT_LISTE.titel_text.fn()
                                  ,TEXT_LISTE.underline_aufzaehl_text.fn()
                                  ,TEXT_LISTE.underline_lang_text.fn()
                                  ,TEXT_LISTE.underline_titel_text.fn()
        );
        
  
        //hier kann das layout der einzelnen spalten definiert werden 
        // spaltenlayout fuer:  TEXT_LISTE.aufzaehl_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.aufzaehl_text)), TEXT_LISTE.aufzaehl_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.aufzaehl_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.aufzaehl_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.aufzaehl_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.aufzaehl_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.bold_aufzaehl_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.bold_aufzaehl_text)), TEXT_LISTE.bold_aufzaehl_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.bold_aufzaehl_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.bold_aufzaehl_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.bold_aufzaehl_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.bold_aufzaehl_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.bold_lang_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.bold_lang_text)), TEXT_LISTE.bold_lang_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.bold_lang_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.bold_lang_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.bold_lang_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.bold_lang_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.bold_titel_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.bold_titel_text)), TEXT_LISTE.bold_titel_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.bold_titel_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.bold_titel_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.bold_titel_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.bold_titel_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.fontsize_aufzaehl_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.fontsize_aufzaehl_text)), TEXT_LISTE.fontsize_aufzaehl_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.fontsize_aufzaehl_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.fontsize_aufzaehl_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.fontsize_aufzaehl_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.fontsize_aufzaehl_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.fontsize_lang_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.fontsize_lang_text)), TEXT_LISTE.fontsize_lang_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.fontsize_lang_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.fontsize_lang_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.fontsize_lang_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.fontsize_lang_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.fontsize_titel_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.fontsize_titel_text)), TEXT_LISTE.fontsize_titel_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.fontsize_titel_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.fontsize_titel_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.fontsize_titel_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.fontsize_titel_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.id_table.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.id_table)), TEXT_LISTE.id_table.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.id_table))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.id_table.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.id_table))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.id_table.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.id_text_liste.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.id_text_liste)), TEXT_LISTE.id_text_liste.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.id_text_liste))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.id_text_liste.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.id_text_liste))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.id_text_liste.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.italic_aufzaehl_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.italic_aufzaehl_text)), TEXT_LISTE.italic_aufzaehl_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.italic_aufzaehl_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.italic_aufzaehl_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.italic_aufzaehl_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.italic_aufzaehl_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.italic_lang_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.italic_lang_text)), TEXT_LISTE.italic_lang_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.italic_lang_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.italic_lang_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.italic_lang_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.italic_lang_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.italic_titel_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.italic_titel_text)), TEXT_LISTE.italic_titel_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.italic_titel_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.italic_titel_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.italic_titel_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.italic_titel_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.lang_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.lang_text)), TEXT_LISTE.lang_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.lang_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.lang_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.lang_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.lang_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.position_enum.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.position_enum)), TEXT_LISTE.position_enum.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.position_enum))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.position_enum.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.position_enum))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.position_enum.fn());
        // ----
        //
        //
        // spaltenlayout fuer:  TEXT_LISTE.position_enum.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.position)), TEXT_LISTE.position.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.position))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.position.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.position))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.position.fn());
        // ----
        //
        //
        // spaltenlayout fuer:  TEXT_LISTE.tablename.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.tablename)), TEXT_LISTE.tablename.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.tablename))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.tablename.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.tablename))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.tablename.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.titel_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.titel_text)), TEXT_LISTE.titel_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.titel_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.titel_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.titel_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.titel_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.underline_aufzaehl_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.underline_aufzaehl_text)), TEXT_LISTE.underline_aufzaehl_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.underline_aufzaehl_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.underline_aufzaehl_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.underline_aufzaehl_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.underline_aufzaehl_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.underline_lang_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.underline_lang_text)), TEXT_LISTE.underline_lang_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.underline_lang_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.underline_lang_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.underline_lang_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.underline_lang_text.fn());
        // ----
        //
        // spaltenlayout fuer:  TEXT_LISTE.underline_titel_text.fn()
        this._setColExtent(     new Extent(TL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(TEXT_LISTE.underline_titel_text)), TEXT_LISTE.underline_titel_text.fn());
        this._setLayoutElements(new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.underline_titel_text))._ins(3,1,3,1)._col(new E2_ColorDark()), TEXT_LISTE.underline_titel_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(TL_READABLE_FIELD_NAME.getAlignment(TEXT_LISTE.underline_titel_text))._ins(1,2,1,1)._col(new E2_ColorDark()), TEXT_LISTE.underline_titel_text.fn());
        // ----
        //
      	
        this.set_oSubQueryAgent(new TL_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
    }
    

    
    
    @Override
    public Object get_Copy(Object objHelp) throws myExceptionCopy {
        E2_ComponentMAP_V2 oRueck = new E2_ComponentMAP_V2(this.get_oSQLFieldMAP());
        E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
        oRueck.setComponentMapMarker(new TL_LIST_ComponentMapMapMarker(oRueck));
	
        return oRueck;
    }
    
	/*
     * umstellung auf E2_ComponentMAP_V2 mit marker-option
     * korrespontiert mit  
     */
    public class TL_LIST_ComponentMapMapMarker extends E2_ComponentMapMarker {
        public TL_LIST_ComponentMapMapMarker(E2_ComponentMAP_V2 p_map) {
            super(p_map);
        }
        @Override
        protected void innerFormat(Collection<Component> v) {
            super.innerFormat(v);
    		
            E2_ComponentMAP_V2 map = (E2_ComponentMAP_V2)this.getMap();
    		
            try {
                Rec21 recTL = map.getRec21();
                //hier koennen formatierungsoptionen hinterlegt werden
                
//                if (recTL.is_no_db_val(TEXT_LISTE.aktiv)) {
//                    this.setLayoutBackgroundColorInMap(v,new Color(230,230,230));
//                    this.setTextColorInMap(v,Color.DARKGRAY);
//                }
    			
            } catch (myException e) {
                e.printStackTrace();
            }
        }
    }
  
    
}
 
 

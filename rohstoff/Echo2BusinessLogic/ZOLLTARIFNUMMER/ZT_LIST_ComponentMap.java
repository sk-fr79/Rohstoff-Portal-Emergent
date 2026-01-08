 
package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER;
  
  
import java.util.Collection;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V22;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseInListRow;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER_IMPORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
  
public class ZT_LIST_ComponentMap extends E2_ComponentMAP_V22  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    // Import-Mode
	private boolean 	_isImport = false;
    
    public ZT_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap,boolean bImport) throws myException  {
  
    	super(new ZT_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        this._isImport = bImport;
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(ZT_CONST.ZT_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(ZT_CONST.ZT_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(ZT_CONST.ZT_NAMES.DIRECT_DEL.db_val(),    	new ZT_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(ZT_CONST.ZT_NAMES.DIRECT_EDIT.db_val(),   	new ZT_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(ZT_CONST.ZT_NAMES.DIRECT_VIEW.db_val(),   	new ZT_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        this.add_Component(ZT_CONST.ZT_NAMES.DIRECT_UPLOAD.db_val(), new E2_ButtonAttachmentUseInListRow()
																					._addGlobalValidator(ZT_VALIDATORS.ATTACHMENT.getValidator())
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
        																			, S.ms("?"));
        
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(ZOLLTARIFNUMMER.aktiv),true),     			new MyE2_String(ZT_READABLE_FIELD_NAME.getListHeader( ZOLLTARIFNUMMER.aktiv)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.bm_nummer),true),     	new MyE2_String(ZT_READABLE_FIELD_NAME.getListHeader(ZOLLTARIFNUMMER.bm_nummer)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.bm_text),true),     	new MyE2_String(ZT_READABLE_FIELD_NAME.getListHeader(ZOLLTARIFNUMMER.bm_text)));

        MyE2_DB_MultiComponentColumn colText = new MyE2_DB_MultiComponentColumn();
        MyE2_DB_MultiComponentColumn colImport = new MyE2_DB_MultiComponentColumn();

      
        
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.letzter_import),true), new MyE2_String(ZT_READABLE_FIELD_NAME.getListHeader(ZOLLTARIFNUMMER.letzter_import)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.nummer),true),     	new MyE2_String(ZT_READABLE_FIELD_NAME.getListHeader(ZOLLTARIFNUMMER.nummer)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(ZOLLTARIFNUMMER.reverse_charge),true),     new MyE2_String(ZT_READABLE_FIELD_NAME.getListHeader(ZOLLTARIFNUMMER.reverse_charge)));
        
        colText.setLayoutData(LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        colText.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.text1),true),     new MyE2_String(ZT_READABLE_FIELD_NAME.getListHeader(ZOLLTARIFNUMMER.text1))		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        colText.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.text2),true),     new MyE2_String(ZT_READABLE_FIELD_NAME.getListHeader(ZOLLTARIFNUMMER.text2))		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        colText.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.text3),true),     new MyE2_String(ZT_READABLE_FIELD_NAME.getListHeader(ZOLLTARIFNUMMER.text3))		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        this.add_Component(ZT_CONST.COLUMN_TEXT,  colText, new MyE2_String("Text"));
   
        
        
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.text1),true),     		new MyE2_String(ZT_READABLE_FIELD_NAME.getListHeader(ZOLLTARIFNUMMER.text1)));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.text2),true),     		new MyE2_String(ZT_READABLE_FIELD_NAME.getListHeader(ZOLLTARIFNUMMER.text2)));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.text3),true),     		new MyE2_String(ZT_READABLE_FIELD_NAME.getListHeader(ZOLLTARIFNUMMER.text3)));

        if (_isImport){
        	String sCol1 = "IMP_" + ZOLLTARIFNUMMER_IMPORT.text1.fn().toUpperCase();
        	String sCol2 = "IMP_" + ZOLLTARIFNUMMER_IMPORT.text2.fn().toUpperCase();
        	String sCol3 = "IMP_" + ZOLLTARIFNUMMER_IMPORT.text3.fn().toUpperCase();

        	colImport.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(sCol1),true),     new MyE2_String("Import Text 1")		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        	colImport.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(sCol2),true),     new MyE2_String("Import Text 2")		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        	colImport.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(sCol3),true),     new MyE2_String("Import Text 3")		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        	
        	colImport.add_Component(new MyE2_DB_CheckBox(oFM.get_("IS_DELETED"),true), new MyE2_String("Gelöscht"),null, LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        	colImport.add_Component(new MyE2_DB_CheckBox(oFM.get_("IS_CHANGED"),true), new MyE2_String("Geändert"),null, LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        	this.add_Component(ZT_CONST.COLUMN_IMPORT, colImport, new MyE2_String("Import"));
        }

        
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.id_zolltarifnummer),true),     new MyE2_String(ZT_READABLE_FIELD_NAME.getListHeader(ZOLLTARIFNUMMER.id_zolltarifnummer)));

        
        //neu ab 20171025        
        this._setLineWrapListHeader(true 
                                  ,ZOLLTARIFNUMMER.aktiv.fn()
                                  ,ZOLLTARIFNUMMER.bm_nummer.fn()
                                  ,ZOLLTARIFNUMMER.bm_text.fn()
                                  ,ZOLLTARIFNUMMER.id_zolltarifnummer.fn()
                                  ,ZOLLTARIFNUMMER.letzter_import.fn()
                                  ,ZOLLTARIFNUMMER.nummer.fn()
                                  ,ZOLLTARIFNUMMER.reverse_charge.fn()
//                                  ,ZOLLTARIFNUMMER.text1.fn()
//                                  ,ZOLLTARIFNUMMER.text2.fn()
//                                  ,ZOLLTARIFNUMMER.text3.fn()
        );
        
  
        this._setColExtent(     new Extent(ZT_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ZOLLTARIFNUMMER.aktiv)), ZOLLTARIFNUMMER.aktiv.fn());
        this._setLayoutElements(new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.aktiv))._ins(3,1,3,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.aktiv.fn());
        this._setLayoutTitles(  new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.aktiv))._ins(1,2,1,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.aktiv.fn());

        this._setColExtent(     new Extent(ZT_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ZOLLTARIFNUMMER.bm_nummer)), ZOLLTARIFNUMMER.bm_nummer.fn());
        this._setLayoutElements(new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.bm_nummer))._ins(3,1,3,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.bm_nummer.fn());
        this._setLayoutTitles(  new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.bm_nummer))._ins(1,2,1,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.bm_nummer.fn());

        this._setColExtent(     new Extent(ZT_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ZOLLTARIFNUMMER.bm_text)), ZOLLTARIFNUMMER.bm_text.fn());
        this._setLayoutElements(new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.bm_text))._ins(3,1,3,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.bm_text.fn());
        this._setLayoutTitles(  new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.bm_text))._ins(1,2,1,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.bm_text.fn());

        this._setColExtent(     new Extent(ZT_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ZOLLTARIFNUMMER.id_zolltarifnummer)), ZOLLTARIFNUMMER.id_zolltarifnummer.fn());
        this._setLayoutElements(new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.id_zolltarifnummer))._ins(3,1,3,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.id_zolltarifnummer.fn());
        this._setLayoutTitles(  new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.id_zolltarifnummer))._ins(1,2,1,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.id_zolltarifnummer.fn());

        this._setColExtent(     new Extent(ZT_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ZOLLTARIFNUMMER.letzter_import)), ZOLLTARIFNUMMER.letzter_import.fn());
        this._setLayoutElements(new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.letzter_import))._ins(3,1,3,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.letzter_import.fn());
        this._setLayoutTitles(  new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.letzter_import))._ins(1,2,1,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.letzter_import.fn());

        this._setColExtent(     new Extent(ZT_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ZOLLTARIFNUMMER.nummer)), ZOLLTARIFNUMMER.nummer.fn());
        this._setLayoutElements(new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.nummer))._ins(3,1,3,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.nummer.fn());
        this._setLayoutTitles(  new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.nummer))._ins(1,2,1,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.nummer.fn());

        this._setColExtent(     new Extent(ZT_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ZOLLTARIFNUMMER.reverse_charge)), ZOLLTARIFNUMMER.reverse_charge.fn());
        this._setLayoutElements(new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.reverse_charge))._ins(3,1,3,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.reverse_charge.fn());
        this._setLayoutTitles(  new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.reverse_charge))._ins(1,2,1,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.reverse_charge.fn());

//        this._setColExtent(     new Extent(ZT_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ZOLLTARIFNUMMER.text1)), ZOLLTARIFNUMMER.text1.fn());
//        this._setLayoutElements(new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.text1))._ins(3,1,3,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.text1.fn());
//        this._setLayoutTitles(  new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.text1))._ins(1,2,1,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.text1.fn());
//
//        this._setColExtent(     new Extent(ZT_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ZOLLTARIFNUMMER.text2)), ZOLLTARIFNUMMER.text2.fn());
//        this._setLayoutElements(new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.text2))._ins(3,1,3,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.text2.fn());
//        this._setLayoutTitles(  new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.text2))._ins(1,2,1,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.text2.fn());
//
//        this._setColExtent(     new Extent(ZT_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(ZOLLTARIFNUMMER.text3)), ZOLLTARIFNUMMER.text3.fn());
//        this._setLayoutElements(new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.text3))._ins(3,1,3,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.text3.fn());
//        this._setLayoutTitles(  new RB_gld()._al(ZT_READABLE_FIELD_NAME.getAlignment(ZOLLTARIFNUMMER.text3))._ins(1,2,1,1)._col(new E2_ColorDark()), ZOLLTARIFNUMMER.text3.fn());
//      	
        this.set_oSubQueryAgent(new ZT_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
    }
    
    
    @Override
    public Object get_Copy(Object objHelp) throws myExceptionCopy {
        E2_ComponentMAP_V22 oRueck = new E2_ComponentMAP_V22(this.get_oSQLFieldMAP());
        E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
        oRueck.setComponentMapMarker(new ZT_LIST_ComponentMapMapMarker(oRueck));
	
        return oRueck;
    }
    
	/*
     * umstellung auf E2_ComponentMAP_V22 mit marker-option
     * korrespontiert mit  
     */
    public class ZT_LIST_ComponentMapMapMarker extends E2_ComponentMapMarker {
        public ZT_LIST_ComponentMapMapMarker(E2_ComponentMAP_V22 p_map) {
            super(p_map);
        }
        @Override
        protected void innerFormat(Collection<Component> v) {
            super.innerFormat(v);
    		
            E2_ComponentMAP_V22 map = (E2_ComponentMAP_V22)this.getMap();
    		
            try {
                Rec22 recZT = map.getRec22();
                //hier koennen formatierungsoptionen hinterlegt werden
                
                if (recZT.is_no_db_val(ZOLLTARIFNUMMER.aktiv)) {
                   // this.setLayoutBackgroundColorInMap(v,new Color(230,230,230));
                    this.setTextColorInMap(v,Color.DARKGRAY);
                }
    			
            } catch (myException e) {
                e.printStackTrace();
            }
        }
    }
  
    
    public boolean isImportMode(){
    	return _isImport;
    }
    
}
 
 

 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
  
import nextapp.echo2.app.Color;
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
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentGrid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class WF_SIMPLE_LIST_ComponentMap extends E2_ComponentMAP  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public WF_SIMPLE_LIST_ComponentMap( RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new WF_SIMPLE_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(WF_SIMPLE_CONST.WF_SIMPLE_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(WF_SIMPLE_CONST.WF_SIMPLE_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(WF_SIMPLE_CONST.WF_SIMPLE_NAMES.DIRECT_DEL.db_val(),    	new WF_SIMPLE_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(WF_SIMPLE_CONST.WF_SIMPLE_NAMES.DIRECT_EDIT.db_val(),   	new WF_SIMPLE_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(WF_SIMPLE_CONST.WF_SIMPLE_NAMES.DIRECT_VIEW.db_val(),   	new WF_SIMPLE_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        
        this.add_Component(WF_SIMPLE_CONST.WF_SIMPLE_NAMES.DIRECT_UPLOAD2.db_val(), new BT_AttachmentToWFEntry()
																					._addGlobalValidator(WF_SIMPLE_VALIDATORS.ATTACHMENT.getValidator())
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
        																			, S.ms("?"));
        
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag)));

        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAUFZETTEL_EINTRAG.id_laufzettel),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.id_laufzettel)));
        
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("LZ_"+LAUFZETTEL.text.fieldName()),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL.text)));
        this.add_Component(new MyE2_DB_TextArea(oFM.get_("LZ_"+LAUFZETTEL.text.fieldName()),500,4),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL.text)));
        
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WF_SIMPLE_CONST.colname_besitzer_name),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(WF_SIMPLE_CONST.colname_besitzer_name) ) );
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WF_SIMPLE_CONST.colname_bearbeiter_name),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(WF_SIMPLE_CONST.colname_bearbeiter_name) ) );

        
		MyE2_DB_MultiComponentGrid oGridAufgabe = new MyE2_DB_MultiComponentGrid(1,250,null);
		oGridAufgabe.setWidth(new Extent(100,Extent.PERCENT));
		MyE2_DB_TextArea lab1 = new MyE2_DB_TextArea(oFM.get_(LAUFZETTEL_EINTRAG.aufgabe),600,1);//,     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.aufgabe));
		MyE2_DB_TextArea lab2 = new MyE2_DB_TextArea(oFM.get_(LAUFZETTEL_EINTRAG.bericht),600,1);//,     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.aufgabe));
		oGridAufgabe.add_Component(lab1, new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.aufgabe)),null);
		oGridAufgabe.add_Component(lab2, new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.bericht)),null);
		this.add_Component(WF_SIMPLE_CONST.colname_aufgabe_bericht,oGridAufgabe, new MyE2_String("Aufgabe/Bericht"));
        
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAUFZETTEL_EINTRAG.aufgabe),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.aufgabe)));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAUFZETTEL_EINTRAG.bericht),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.bericht)));


        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAUFZETTEL_EINTRAG.faellig_am),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.faellig_am)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAUFZETTEL_EINTRAG.angelegt_am),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.angelegt_am)));
        
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(LAUFZETTEL_EINTRAG.geloescht),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.geloescht)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WF_SIMPLE_CONST.colname_status),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(WF_SIMPLE_CONST.colname_status)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WF_SIMPLE_CONST.colname_abgeschlossen_von_name),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(WF_SIMPLE_CONST.colname_abgeschlossen_von_name) ) );
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAUFZETTEL_EINTRAG.abgeschlossen_am),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.abgeschlossen_am)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAUFZETTEL_EINTRAG.kadenz_nach_abschluss),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.kadenz_nach_abschluss)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAUFZETTEL_EINTRAG.nachricht_sent),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.nachricht_sent)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(LAUFZETTEL_EINTRAG.send_nachricht),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.send_nachricht)));

//        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(LAUFZETTEL.geloescht),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL.geloescht)));
        
        
//        this.add_Component(new MyE2_DB_TextArea(oFM.get_(LAUFZETTEL_EINTRAG.bericht),WF_SIMPLE_READABLE_FIELD_NAME.getLabelWidth(LAUFZETTEL_EINTRAG.bericht),3) ,     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.bericht)));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAUFZETTEL_EINTRAG.id_eintrag_parent),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.id_eintrag_parent)));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAUFZETTEL_EINTRAG.id_laufzettel_prio),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.id_laufzettel_prio)));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAUFZETTEL_EINTRAG.id_laufzettel_status),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.id_laufzettel_status)));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAUFZETTEL_EINTRAG.id_parent_kadenz),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.id_parent_kadenz)));
//        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(LAUFZETTEL_EINTRAG.privat),true),     new MyE2_String(WF_SIMPLE_READABLE_FIELD_NAME.getReadable(LAUFZETTEL_EINTRAG.privat)));

        //neu ab 20171025        
        this._setLineWrapListHeader(true 
        						  ,"LZ_"+LAUFZETTEL.text.fn()
                                  ,LAUFZETTEL_EINTRAG.abgeschlossen_am.fn()
                                  ,LAUFZETTEL_EINTRAG.angelegt_am.fn()
//                                  ,LAUFZETTEL_EINTRAG.aufgabe.fn()
//                                  ,LAUFZETTEL_EINTRAG.bericht.fn()
                                  ,LAUFZETTEL_EINTRAG.faellig_am.fn()
                                  ,LAUFZETTEL_EINTRAG.geloescht.fn()
//                                  ,LAUFZETTEL_EINTRAG.id_eintrag_parent.fn()
                                  ,LAUFZETTEL_EINTRAG.id_laufzettel.fn()
                                  ,LAUFZETTEL_EINTRAG.id_laufzettel_eintrag.fn()
//                                  ,LAUFZETTEL_EINTRAG.id_laufzettel_prio.fn()
//                                  ,LAUFZETTEL_EINTRAG.id_laufzettel_status.fn()
//                                  ,LAUFZETTEL_EINTRAG.id_parent_kadenz.fn()
                                  ,WF_SIMPLE_CONST.colname_abgeschlossen_von_name
                                  ,WF_SIMPLE_CONST.colname_bearbeiter_name
                                  ,WF_SIMPLE_CONST.colname_besitzer_name
                                  ,WF_SIMPLE_CONST.colname_status
                                  ,LAUFZETTEL_EINTRAG.kadenz_nach_abschluss.fn()
                                  ,LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit.fn()
                                  ,LAUFZETTEL_EINTRAG.nachricht_sent.fn()
//                                  ,LAUFZETTEL_EINTRAG.privat.fn()
                                  ,LAUFZETTEL_EINTRAG.send_nachricht.fn()
        );

        
        Color colField = new E2_ColorBase();
        Color colHeading = new E2_ColorDark();
        

        //hier kann das layout der einzelnen spalten definiert werden 
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize("LZ_"+ LAUFZETTEL.text)), "LZ_"+LAUFZETTEL.text.fn());
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL.text))._ins(3,1,3,1)._col(colField),"LZ_"+LAUFZETTEL.text.fn());
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL.text))._ins(1,2,1,1)._col(colHeading), "LZ_"+LAUFZETTEL.text.fn());

        
        
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.abgeschlossen_am.fn()
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.abgeschlossen_am)), LAUFZETTEL_EINTRAG.abgeschlossen_am.fn());
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.abgeschlossen_am))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.abgeschlossen_am.fn());
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.abgeschlossen_am))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.abgeschlossen_am.fn());
        // ----
        //
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.angelegt_am.fn()
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.angelegt_am)), LAUFZETTEL_EINTRAG.angelegt_am.fn());
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.angelegt_am))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.angelegt_am.fn());
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.angelegt_am))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.angelegt_am.fn());
        // ----
        //
//        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.aufgabe.fn()
//        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.aufgabe)), LAUFZETTEL_EINTRAG.aufgabe.fn());
//        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.aufgabe))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.aufgabe.fn());
//        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.aufgabe))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.aufgabe.fn());
//        // ----
//        //
//        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.bericht.fn()
//        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.bericht)), LAUFZETTEL_EINTRAG.bericht.fn());
//        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.bericht))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.bericht.fn());
//        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.bericht))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.bericht.fn());
        
        
        
        // ----
        //
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.faellig_am.fn()
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.faellig_am)), LAUFZETTEL_EINTRAG.faellig_am.fn());
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.faellig_am))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.faellig_am.fn());
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.faellig_am))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.faellig_am.fn());

        String sColFaelligAm = this.get_hmRealDBComponents().get(LAUFZETTEL_EINTRAG.faellig_am.fn()).EXT_DB().get_oSQLField().get_cFieldAusdruck();
		this.get_hmRealDBComponents().get(LAUFZETTEL_EINTRAG.faellig_am.fn()).EXT_DB().set_cSortAusdruckFuerSortbuttonUP("NVL("+sColFaelligAm + ",to_date('9999-12-31','yyyy-mm-dd')) ASC  ");
		this.get_hmRealDBComponents().get(LAUFZETTEL_EINTRAG.faellig_am.fn()).EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN( "NVL("+sColFaelligAm + ",to_date('9999-12-31','yyyy-mm-dd')) DESC  ");
		this.get__Comp_From_RealComponents(LAUFZETTEL_EINTRAG.faellig_am.fn()).EXT().set_ToolTipStringForListHeader(new MyE2_String("Sortiert ohne Fälligkeit nach vorne..."));

        
        
        // ----
        //
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.geloescht.fn()
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.geloescht)), LAUFZETTEL_EINTRAG.geloescht.fn());
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.geloescht))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.geloescht.fn());
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.geloescht))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.geloescht.fn());
        // ----
        //
        //
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.id_laufzettel.fn()
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.id_laufzettel)), LAUFZETTEL_EINTRAG.id_laufzettel.fn());
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.id_laufzettel))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.id_laufzettel.fn());
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.id_laufzettel))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.id_laufzettel.fn());
        // ----
        //
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.id_laufzettel_eintrag.fn()
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag)), LAUFZETTEL_EINTRAG.id_laufzettel_eintrag.fn());
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.id_laufzettel_eintrag.fn());
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.id_laufzettel_eintrag.fn());
        // ----
        //
        //
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.id_laufzettel_status.fn()
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WF_SIMPLE_CONST.colname_status)), WF_SIMPLE_CONST.colname_status);
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(WF_SIMPLE_CONST.colname_status))._ins(3,1,3,1)._col(colField), WF_SIMPLE_CONST.colname_status);
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(WF_SIMPLE_CONST.colname_status))._ins(1,2,1,1)._col(colHeading), WF_SIMPLE_CONST.colname_status);
        // ----
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von.fn()
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WF_SIMPLE_CONST.colname_abgeschlossen_von_name) ), WF_SIMPLE_CONST.colname_abgeschlossen_von_name);
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(WF_SIMPLE_CONST.colname_abgeschlossen_von_name))._ins(3,1,3,1)._col(colField), WF_SIMPLE_CONST.colname_abgeschlossen_von_name);
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(WF_SIMPLE_CONST.colname_abgeschlossen_von_name))._ins(1,2,1,1)._col(colHeading), WF_SIMPLE_CONST.colname_abgeschlossen_von_name);
        // ----
        //
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.id_user_bearbeiter.fn()
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WF_SIMPLE_CONST.colname_bearbeiter_name)), WF_SIMPLE_CONST.colname_bearbeiter_name);
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(WF_SIMPLE_CONST.colname_bearbeiter_name))._ins(3,1,3,1)._col(colField), WF_SIMPLE_CONST.colname_bearbeiter_name);
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(WF_SIMPLE_CONST.colname_bearbeiter_name))._ins(1,2,1,1)._col(colHeading), WF_SIMPLE_CONST.colname_bearbeiter_name);
        // ----
        //
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.id_user_besitzer.fn()
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WF_SIMPLE_CONST.colname_besitzer_name)), WF_SIMPLE_CONST.colname_besitzer_name);
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(WF_SIMPLE_CONST.colname_besitzer_name))._ins(3,1,3,1)._col(colField), WF_SIMPLE_CONST.colname_besitzer_name);
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(WF_SIMPLE_CONST.colname_besitzer_name))._ins(1,2,1,1)._col(colHeading), WF_SIMPLE_CONST.colname_besitzer_name);
        // ----
        //
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.kadenz_nach_abschluss.fn()
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.kadenz_nach_abschluss)), LAUFZETTEL_EINTRAG.kadenz_nach_abschluss.fn());
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.kadenz_nach_abschluss))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.kadenz_nach_abschluss.fn());
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.kadenz_nach_abschluss))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.kadenz_nach_abschluss.fn());
        // ----
        //
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit.fn()
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit)), LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit.fn());
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit.fn());
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit.fn());
        // ----
        //
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.nachricht_sent.fn()
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.nachricht_sent)), LAUFZETTEL_EINTRAG.nachricht_sent.fn());
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.nachricht_sent))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.nachricht_sent.fn());
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.nachricht_sent))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.nachricht_sent.fn());
        //
        //
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.send_nachricht.fn()
        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.send_nachricht)), LAUFZETTEL_EINTRAG.send_nachricht.fn());
        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.send_nachricht))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.send_nachricht.fn());
        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.send_nachricht))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.send_nachricht.fn());
        // ----
        //
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.id_eintrag_parent.fn()
//        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.id_eintrag_parent)), LAUFZETTEL_EINTRAG.id_eintrag_parent.fn());
//        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.id_eintrag_parent))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.id_eintrag_parent.fn());
//        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.id_eintrag_parent))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.id_eintrag_parent.fn());
//        // ----
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.id_laufzettel_prio.fn()
//        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.id_laufzettel_prio)), LAUFZETTEL_EINTRAG.id_laufzettel_prio.fn());
//        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.id_laufzettel_prio))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.id_laufzettel_prio.fn());
//        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.id_laufzettel_prio))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.id_laufzettel_prio.fn());
        // ----
        //
        // spaltenlayout fuer:  LAUFZETTEL_EINTRAG.id_parent_kadenz.fn()
//        this._setColExtent(     new Extent(WF_SIMPLE_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(LAUFZETTEL_EINTRAG.id_parent_kadenz)), LAUFZETTEL_EINTRAG.id_parent_kadenz.fn());
//        this._setLayoutElements(new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.id_parent_kadenz))._ins(3,1,3,1)._col(colField), LAUFZETTEL_EINTRAG.id_parent_kadenz.fn());
//        this._setLayoutTitles(  new RB_gld()._al(WF_SIMPLE_READABLE_FIELD_NAME.getAlignment(LAUFZETTEL_EINTRAG.id_parent_kadenz))._ins(1,2,1,1)._col(colHeading), LAUFZETTEL_EINTRAG.id_parent_kadenz.fn());
        // ----
        //
      	
        
        
        
        this.set_oSubQueryAgent(new WF_SIMPLE_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
        this.set_Factory4Records(new factory4Records());
    }
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_LAUFZETTEL_EINTRAG(cID_MAINTABLE);
        }
    }
    
  
    

    
    
}
 
 

package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_WAEHRUNG_DROPDOWN;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER_IMPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZOLLTARIFNUMMER;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGER_LISTE.LAG_KTO_LIST_FORMATING_Agent;
import rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER.ZT_CONST;


public class ZOL_LIST_ComponentMap extends E2_ComponentMAP 
{
	
	private SQLFieldMAP oFM = null;
	private boolean 	_isImport = false;
	
    public ZOL_LIST_ComponentMap(boolean bImport) throws myException
    {
        super(new ZOL_LIST_SqlFieldMAP());
        oFM = this.get_oSQLFieldMAP();
        _isImport = bImport;
        
        this.add_Component(ZOL_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(ZOL_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.nummer),true),     new MyE2_String("ZT-Nummer"));

        
        MyE2_DB_MultiComponentColumn colAktiv = new MyE2_DB_MultiComponentColumn();
        colAktiv.setLayoutData(LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        colAktiv.add_Component(new MyE2_DB_CheckBox(oFM.get_(ZOLLTARIFNUMMER.aktiv),true)				, new MyE2_String("Aktiv")		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        colAktiv.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.letzter_import),true)	, new MyE2_String("Letzer Import")	,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        this.add_Component(ZOL_CONST.COLUMN_AKTIV,colAktiv, new MyE2_String("Aktiv"));
        
        
        MyE2_DB_MultiComponentColumn colText = new MyE2_DB_MultiComponentColumn();
        colText.setLayoutData(LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        colText.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.text1),true),     new MyE2_String("Text 1")		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        colText.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.text2),true),     new MyE2_String("Text 2")		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        colText.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.text3),true),     new MyE2_String("Text 3")		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        this.add_Component(ZT_CONST.COLUMN_TEXT,  colText, new MyE2_String("Text"));
        
        
        
        MyE2_DB_MultiComponentColumn colImport = new MyE2_DB_MultiComponentColumn();
        if (_isImport){
        	String sCol1 = "IMP_" + ZOLLTARIFNUMMER_IMPORT.text1.fn().toUpperCase();
        	colImport.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(sCol1),true),     new MyE2_String("Import Text 1")		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        	String sCol2 = "IMP_" + ZOLLTARIFNUMMER_IMPORT.text2.fn().toUpperCase();
        	colImport.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(sCol2),true),     new MyE2_String("Import Text 2")		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        	String sCol3 = "IMP_" + ZOLLTARIFNUMMER_IMPORT.text3.fn().toUpperCase();
        	colImport.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(sCol3),true),     new MyE2_String("IMport Text 3")		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        	colImport.add_Component(new MyE2_DB_CheckBox(oFM.get_("IS_DELETED"),true), new MyE2_String("Gelöscht"),null, LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        	colImport.add_Component(new MyE2_DB_CheckBox(oFM.get_("IS_CHANGED"),true), new MyE2_String("Geändert"),null, LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        	this.add_Component(ZT_CONST.COLUMN_IMPORT, colImport, new MyE2_String("Import"));
        }
        
        
        MyE2_DB_MultiComponentColumn colEinheit = new MyE2_DB_MultiComponentColumn();
        colEinheit.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.bm_text),true),     new MyE2_String("Einheit")		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        colEinheit.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.bm_nummer),true),     new MyE2_String("EH-Nr")		,null,LayoutDataFactory.get_ColLayoutGridLeft(E2_INSETS.I_2_0_2_0));
        this.add_Component(ZOL_CONST.COLUMN_EINHEIT,  colEinheit, new MyE2_String("Einheit"));
        
        
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(ZOLLTARIFNUMMER.reverse_charge),true),     new MyE2_String("RC (Deutschland)"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(ZOLLTARIFNUMMER.id_zolltarifnummer),true),     new MyE2_String("ID"));

		this.set_oSubQueryAgent(new ZOL_LIST_FORMATING_Agent(this));

        this.set_Factory4Records(new factory4Records());
    }
    
    
    /**
     * gibt zurück, ob es im Import-Modus ist
     * @author manfred
     * @date 16.09.2016
     *
     * @return
     */
    public boolean isImportMode(){
    	return _isImport;
    }
    
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_ZOLLTARIFNUMMER(cID_MAINTABLE);
        }
        
    }
    
    
}
 

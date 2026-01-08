package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MASK_DEF;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MA_DES_LIST_ComponentMap extends E2_ComponentMAP  {
    public MA_DES_LIST_ComponentMap() throws myException  {
        super(new MA_DES_LIST_SqlFieldMAP());
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(MA_DES_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    	new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(MA_DES_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    	new E2_ButtonListMarker(),new MyE2_String("?"));
       
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MASK_DEF.id_mask_def),true),   new MyE2_String("ID"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MASK_DEF.maskname),true),		new MyE2_String("Maskname"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MASK_DEF.maskname_long),true), new MyE2_String("Maskname (lang)"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MASK_DEF.tablename),true),     new MyE2_String("Tabelle"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MASK_DEF.left_offset),true),   new MyE2_String("Offset links"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MASK_DEF.nb_of_cols),true),    new MyE2_String("Spaltenzahl"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(MASK_DEF.pixel_width),true),   new MyE2_String("Spaltenbreite"));
        
        this.add_Component(MA_DES_CONST.KUSTOM_COMPONENT.BT_JUMP_2_DESIGN_UI.name(), 
        		new MA_DES_COMP_bt_call_building_interface(),  					
        		new MyE2_String("Edit"));
       
        this._setLineWrapListHeader(true 
                                  ,MASK_DEF.id_mask_def.fn()
                                  ,MASK_DEF.left_offset.fn()
                                  ,MASK_DEF.maskname.fn()
                                  ,MASK_DEF.maskname_long.fn()
                                  ,MASK_DEF.nb_of_cols.fn()
                                  ,MASK_DEF.pixel_width.fn()
                                  ,MASK_DEF.tablename.fn()
        );
        
        RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementCenter
                                 ,MASK_DEF.id_mask_def.fn()
                                 ,MASK_DEF.maskname.fn()
                                 ,MASK_DEF.maskname_long.fn()
                                 ,MASK_DEF.tablename.fn()
      	);
      	
      	RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
      	RB_gld gldTitelLeft = 		new RB_gld()._left_top()._ins(2,4,2,2)._col(new E2_ColorBase());
      	
      	this._setLayoutElements(gldTitelCenter
                                 ,MASK_DEF.left_offset.fn()
                                 ,MASK_DEF.nb_of_cols.fn()
                                 ,MASK_DEF.pixel_width.fn()
      	);
      	
     	this._setLayoutElements(gldTitelLeft
     			,MASK_DEF.id_mask_def.fn()
                ,MASK_DEF.maskname.fn()
                ,MASK_DEF.maskname_long.fn()
                ,MASK_DEF.tablename.fn()
     			);
      	
        this.set_oSubQueryAgent(new MA_DES_LIST_FORMATING_Agent());
        	
        this.set_Factory4Records(new factory4Records());
    }
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_MASK_DEF(cID_MAINTABLE);
        }
        
    }
    
    
}
 

 
package rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP;
  
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.TAX_GROUP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX_GROUP;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.TAXG_READABLE_FIELD_NAME;
  
public class TAXG_LIST_ComponentMap extends E2_ComponentMAP  {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    public TAXG_LIST_ComponentMap(PARAMHASH  p_params) throws myException  {
        super(new TAXG_LIST_SqlFieldMAP(p_params));
        
        this.params = p_params;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(TAXG_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(TAXG_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX_GROUP.id_tax_group),true),     new MyE2_String(TAXG_READABLE_FIELD_NAME.getReadable(TAX_GROUP.id_tax_group)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(TAX_GROUP.aktiv),true),     		new MyE2_String(TAXG_READABLE_FIELD_NAME.getReadable(TAX_GROUP.aktiv)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX_GROUP.kurztext),true),     	new MyE2_String(TAXG_READABLE_FIELD_NAME.getReadable(TAX_GROUP.kurztext)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(TAX_GROUP.langtext),true),     	new MyE2_String(TAXG_READABLE_FIELD_NAME.getReadable(TAX_GROUP.langtext)));
        this._setLineWrapListHeader(true 
                                  ,TAX_GROUP.aktiv.fn()
                                  ,TAX_GROUP.id_tax_group.fn()
                                  ,TAX_GROUP.kurztext.fn()
                                  ,TAX_GROUP.langtext.fn()
        );
        
        RB_gld gldElement = 	new RB_gld()._left_top()._ins(4,2,2,2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElement
                                 ,TAX_GROUP.aktiv.fn()
                                 ,TAX_GROUP.id_tax_group.fn()
                                 ,TAX_GROUP.kurztext.fn()
                                 ,TAX_GROUP.langtext.fn()
      	);
      	
      	RB_gld gldTitel = 	new RB_gld()._left_top()._ins(4,2,2,2)._col(new E2_ColorDark());
       	this._setLayoutTitles(gldTitel
                                 ,TAX_GROUP.aktiv.fn()
                                 ,TAX_GROUP.id_tax_group.fn()
                                 ,TAX_GROUP.kurztext.fn()
                                 ,TAX_GROUP.langtext.fn()
      	);
        //hier kann die spaltenbreite der einzelnen spalten definiert werden 
        this._setColExtent(new Extent(50), TAX_GROUP.aktiv.fn());
        this._setColExtent(new Extent(80), TAX_GROUP.id_tax_group.fn());
        this._setColExtent(new Extent(300), TAX_GROUP.kurztext.fn());
        this._setColExtent(new Extent(300), TAX_GROUP.langtext.fn());
      	
        this.set_oSubQueryAgent(new TAXG_LIST_FORMATING_Agent(this.params));
        	
        this.set_Factory4Records(new factory4Records());
    }
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_TAX_GROUP(cID_MAINTABLE);
        }
        
    }
    
    
}
 

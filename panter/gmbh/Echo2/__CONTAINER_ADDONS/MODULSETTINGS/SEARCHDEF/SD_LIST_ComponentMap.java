 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SEARCHDEF;
  
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
import panter.gmbh.basics4project.DB_ENUMS.SEARCHDEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_SEARCHDEF;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class SD_LIST_ComponentMap extends E2_ComponentMAP  {
    public SD_LIST_ComponentMap(String modulKenner) throws myException  {
        super(new SD_LIST_SqlFieldMAP(modulKenner));
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(SD_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(SD_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    new E2_ButtonListMarker(),new MyE2_String("?"));
        
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SEARCHDEF.id_searchdef),true),     new MyE2_String("ID"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(SEARCHDEF.aktiv),true),   				new MyE2_String("Aktiv ?"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SEARCHDEF.modulkenner),true),     	new MyE2_String("Modul"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SEARCHDEF.user_text),true),     	new MyE2_String("Benutzertext"));
        
        this._setLineWrapListHeader(true 
                                  ,SEARCHDEF.aktiv.fn()
                                  ,SEARCHDEF.id_searchdef.fn()
                                  ,SEARCHDEF.modulkenner.fn()
                                  ,SEARCHDEF.user_text.fn()
        );
        
        RB_gld gldElementCenter = 	new RB_gld()._left_top()._ins(2,4,2,2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementCenter
                                 ,SEARCHDEF.aktiv.fn()
                                 ,SEARCHDEF.id_searchdef.fn()
                                 ,SEARCHDEF.modulkenner.fn()
                                 ,SEARCHDEF.user_text.fn()
      	);
      	
      	RB_gld gldTitelCenter = 	new RB_gld()._left_top()._ins(1,2,1,1)._col(new E2_ColorDark());
       	this._setLayoutTitles(gldTitelCenter
                                 ,SEARCHDEF.aktiv.fn()
                                 ,SEARCHDEF.id_searchdef.fn()
                                 ,SEARCHDEF.modulkenner.fn()
                                 ,SEARCHDEF.user_text.fn()
      	);

       	this._setColExtent(new Extent(300), SEARCHDEF.modulkenner.fn()
       										,SEARCHDEF.user_text.fn());
       	
       	this._setColExtent(new Extent(50), 	SEARCHDEF.id_searchdef.fn());
       	
       	
        this.set_oSubQueryAgent(new SD_LIST_FORMATING_Agent());
        	
        this.set_Factory4Records(new factory4Records());
    }
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_SEARCHDEF(cID_MAINTABLE);
        }
        
    }
    
    
}
 

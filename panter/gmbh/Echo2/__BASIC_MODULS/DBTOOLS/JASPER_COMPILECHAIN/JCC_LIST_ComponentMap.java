 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.JASPER_COMPILECHAIN;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.JASPER_COMPILE_CHAIN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
  
public class JCC_LIST_ComponentMap extends E2_ComponentMAP  {
	
    public JCC_LIST_ComponentMap() throws myException  {
        super(new JCC_LIST_SqlFieldMAP());
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(JCC_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(JCC_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(JASPER_COMPILE_CHAIN.reportname),true),     			new MyE2_String("Reportname"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(JASPER_COMPILE_CHAIN.compilebasedir),true),     		new MyE2_String("Basis/Mandantenpfad zu Subreport-Verzeichnis"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(JASPER_COMPILE_CHAIN.compiletarget),true),     		new MyE2_String("Ziel zum kompilieren"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(JASPER_COMPILE_CHAIN.id_jasper_compile_chain),true),   new MyE2_String("ID"));
        this._setLineWrapListHeader(true 
                                  ,JASPER_COMPILE_CHAIN.reportname.fn()
                                  ,JASPER_COMPILE_CHAIN.compilebasedir.fn()
                                  ,JASPER_COMPILE_CHAIN.compiletarget.fn()
                                  ,JASPER_COMPILE_CHAIN.id_jasper_compile_chain.fn()
        );
        
        RB_gld gldElementCenter = 	new RB_gld()._left_top()._ins(4,4,4,2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementCenter
       							,JASPER_COMPILE_CHAIN.reportname.fn()
                                ,JASPER_COMPILE_CHAIN.compilebasedir.fn()
                                ,JASPER_COMPILE_CHAIN.compiletarget.fn()
                                ,JASPER_COMPILE_CHAIN.id_jasper_compile_chain.fn()
      	);
      	
      	RB_gld gldTitelCenter = 	new RB_gld()._left_top()._ins(4,4,4,2)._col(new E2_ColorDark());
       	this._setLayoutTitles(gldTitelCenter
       							,JASPER_COMPILE_CHAIN.reportname.fn()
                                ,JASPER_COMPILE_CHAIN.compilebasedir.fn()
                                ,JASPER_COMPILE_CHAIN.compiletarget.fn()
                                ,JASPER_COMPILE_CHAIN.id_jasper_compile_chain.fn()
      	);
      	
        this.set_oSubQueryAgent(new JCC_LIST_FORMATING_Agent());
        	
        this.set_Factory4Records(new factory4Records());
    }
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new Rec21(_TAB.jasper_compile_chain)._fill_id(cID_MAINTABLE);
        }
        
    }
    
    
}
 

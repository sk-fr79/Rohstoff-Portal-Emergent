package panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.LIST;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.DRUCK_CONST;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.DRUCKER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_DRUCKER;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class DRUCK_LIST_ComponentMap extends E2_ComponentMAP 
{
    public DRUCK_LIST_ComponentMap() throws myException
    {
        super(new DRUCK_LIST_SqlFieldMAP());
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(DRUCK_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    			new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(DRUCK_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    			new E2_ButtonListMarker(),new MyE2_String("?"));
        
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(DRUCKER.aktiv)),     						new MyE2_String("Aktiv"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(DRUCKER.beschreibung),true),     		new MyE2_String("Beschreibung"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(DRUCKER.direct_druck_befehl),true),    new MyE2_String("Druckbefehl"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(DRUCKER.id_drucker),true),     		new MyE2_String("ID"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(DRUCKER.name),true),     				new MyE2_String("Name"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(DRUCKER.standort),true),    			new MyE2_String("Standort"));
        
        this.add_Component(DRUCK_CONST.ADDITIONNAL_COMPONENTS.BT_TEST_DRUCK.name(), 				new DRUCK_LIST_bt_test_drucker(), new MyE2_String("Test"));
        
        this.set_Factory4Records(new factory4Records());
    }
    
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_DRUCKER(cID_MAINTABLE);
        }
        
    }
    
    
}
 

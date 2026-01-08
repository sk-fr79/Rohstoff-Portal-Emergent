 
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_DEF;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
public class REM_LIST_ComponentMap extends E2_ComponentMAP 
{
    public REM_LIST_ComponentMap() throws myException
    {
        super(new REM_LIST_SqlFieldMAP());
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(REM_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(REM_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REMINDER_DEF.id_reminder_def),true),   			new MyE2_String("ID"));
        MyE2_Row_EveryTimeEnabled oRowForJumperToListe = new MyE2_Row_EveryTimeEnabled();
        this.add_Component(REM_CONST.LIST_BUTTON_MODUL_CONNECT,oRowForJumperToListe, new MyE2_String("Modul"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REMINDER_DEF.table_name),true),     				new MyE2_String("Tabelle"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REMINDER_DEF.id_table),true),     					new MyE2_String("ID-Tabelle"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REMINDER_DEF.modul_connect_ziel),true),    		new MyE2_String("Ziel-Modul"));
        
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REMINDER_DEF.erinnerung_ab),true),     			new MyE2_String("Erinnerung ab"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(REMINDER_DEF.erinnerung_bei_anlage),true), 			new MyE2_String("Nachr. bei Anlage"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REMINDER_DEF.intervall),true),     			new MyE2_String("Intervall"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REMINDER_DEF.reminder_heading),true),     			new MyE2_String("Titel"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REMINDER_DEF.reminder_text),true),     			new MyE2_String("Text"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REMINDER_DEF.send_mail),true),     				new MyE2_String("Mail verschicken?"));
        
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REMINDER_DEF.id_user_angelegt),true),     			new MyE2_String("Angelegt von (ID)"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REM_CONST.SQL_FIELD_USER_ANGELEGT) ,true),			new MyE2_String("Angelegt von"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REMINDER_DEF.id_user_abgeschlossen),true), 		new MyE2_String("Abgeschlossen von (ID)"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REM_CONST.SQL_FIELD_USER_ABGESCHLOSSEN) ,true),    new MyE2_String("Abgeschlossen von"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(REMINDER_DEF.abgeschlossen_am),true),     			new MyE2_String("Abgeschlossen am"));
        
        
        this.get__Comp(REMINDER_DEF.erinnerung_ab).EXT().set_bLineWrapListHeader(true);
        this.get__Comp(REMINDER_DEF.erinnerung_bei_anlage).EXT().set_bLineWrapListHeader(true);
        this.get__Comp(REMINDER_DEF.intervall).EXT().set_bLineWrapListHeader(true);
        
        this.get__Comp(REMINDER_DEF.id_user_angelegt).EXT().set_bLineWrapListHeader(true);
        this.get__Comp(REMINDER_DEF.id_user_abgeschlossen).EXT().set_bLineWrapListHeader(true);
        this.get__Comp(REMINDER_DEF.intervall).EXT().set_bLineWrapListHeader(true);
        
        this.get__Comp(REM_CONST.SQL_FIELD_USER_ABGESCHLOSSEN).EXT().set_bLineWrapListHeader(true);
        this.get__Comp(REMINDER_DEF.abgeschlossen_am).EXT().set_bLineWrapListHeader(true);
        
        this.set_Factory4Records(new factory4Records());
        this.add_oSubQueryAgent(new REM_LIST_FORMATING_Agent());
    }
    
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_REMINDER_DEF(cID_MAINTABLE);
        }
        
    }
    
    
}
 

 
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_DEF;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_CONST;


public class REM_LIST_SqlFieldMAP extends Project_SQLFieldMAP {
	
    public REM_LIST_SqlFieldMAP() throws myException   {
        super(_TAB.reminder_def.n(), "", false);
        
    
        this.add_JOIN_Table(	USER.fullTabName()
        						, "U1"
        						, SQLFieldMAP.LEFT_OUTER
        						, USER.vorname.fn()+":"+USER.name1.fn()+":"+USER.kuerzel.fn()
        						, true
        						, "U1.ID_USER="+_TAB.reminder_def.n()+"."+REMINDER_DEF.id_user_angelegt, "U1", null);
        
        this.add_JOIN_Table(	USER.fullTabName()
								, "U2"
								, SQLFieldMAP.LEFT_OUTER
								, USER.vorname.fn()+":"+USER.name1.fn()+":"+USER.kuerzel.fn()
								, true
								, "U2.ID_USER="+_TAB.reminder_def.n()+"."+REMINDER_DEF.id_user_abgeschlossen, "U2", null);

        this.add_SQLField(new SQLField("U1.NAME1|| ', ' || U1.VORNAME || ' (' || U1.KUERZEL || ')'",REM_CONST.SQL_FIELD_USER_ANGELEGT, new MyE2_String("Angelegt von"), bibE2.get_CurrSession()), true);
        this.add_SQLField(new SQLField("CASE WHEN ID_USER_ABGESCHLOSSEN IS NOT NULL THEN U2.NAME1 || ', ' || U2.VORNAME || ' (' || U2.KUERZEL || ')' ELSE null END",REM_CONST.SQL_FIELD_USER_ABGESCHLOSSEN, new MyE2_String("Abgeschlossen von"), bibE2.get_CurrSession()), true);
        

                
        this.initFields();
    }
    
}
 

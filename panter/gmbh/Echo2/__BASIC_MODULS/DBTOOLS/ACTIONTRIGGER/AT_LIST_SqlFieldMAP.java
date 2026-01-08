package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class AT_LIST_SqlFieldMAP extends Project_SQLFieldMAP {

	public AT_LIST_SqlFieldMAP() throws myException   {
		
        super(_TAB.trigger_action_def.n(), "", false);
        
		//dummy-felder zum fuellen der komplexen spalten, die eine id_bewegung_vektor brauchen
		this.add_SQLField(new SQLField(	TRIGGER_ACTION_DEF.id_trigger_action_def.tnfn(), 
										AT_CONST.SPALTEN_NAMEN.ID_TRIGGER_DEF2.sqlFieldAlias(), 
										AT_CONST.SPALTEN_NAMEN.ID_TRIGGER_DEF2.userInfo(), bibE2.get_CurrSession()), true);

		this.add_SQLField(new SQLField(	bibALL.MakeSql(AT_CONST.LOGTRIGGER_PREFIX)+"||"+TRIGGER_ACTION_DEF.trigger_name.tnfn()+"||"+bibALL.MakeSql(bibALL.get_ID_MANDANT()), 
										AT_CONST.SPALTEN_NAMEN.ID_TRIGGER_NAME_REAL.sqlFieldAlias(), 
										AT_CONST.SPALTEN_NAMEN.ID_TRIGGER_NAME_REAL.userInfo(), bibE2.get_CurrSession()), true);

		
        this.initFields();
    }
    
}
 

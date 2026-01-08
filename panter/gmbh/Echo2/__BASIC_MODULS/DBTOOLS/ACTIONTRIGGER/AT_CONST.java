 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;


public class AT_CONST {
    
	public static final String LOGTRIGGER_PREFIX = "LG_TR_";
	
	
    public enum TRI_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE(new MyE2_String("Auswahl-Checkbox"))
        ,MARKER_LISTE(new MyE2_String("Markierung Liste"))
        
        ;
        
        private MyE2_String userText = null; 
        private TRI_NAMES(MyE2_String p_userText) {
            this.userText = p_userText;
        }
        
        @Override
        public String db_val() {
            return this.name();
        }
        @Override
        public String user_text() {
            if (S.isEmpty(this.userText)) {
                return this.name();
            } else {
                return this.userText.CTrans();
            }
        }
        @Override
        public String user_text_lang() {
            return this.user_text();
        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return null;
        }
        
    }
    
    
    
    
    public enum TRI_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_TRIGGER")
        ,EDIT("BEARBEITE_TRIGGER")
        ,VIEW("ANZEIGE_TRIGGER")
        ,NEW("NEUEINGABE_TRIGGER")
        ,
        
        ;
        
        private String KEY = null;
        
        private TRI_BUTTONS() {
            this.KEY=this.name();
        }
        private TRI_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(TRI_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        LIST(E2_MODULNAME_ENUM.MODUL.TRIGGER_ACTION_DEF_LIST)
        ,MASK(E2_MODULNAME_ENUM.MODUL.TRIGGER_ACTION_DEF_MASK)
        ;
    	
        private MODUL  modul = null;

        private TRANSLATOR(E2_MODULNAME_ENUM.MODUL p_modul) {
           modul = p_modul;
        }
	    
        public MODUL get_modul() {
	        return modul;
        }
    }
    
    
    
	//sammelt die sondername der spalten (nicht direkt an felder geknuepft)
	public enum SPALTEN_NAMEN {
		 ID_TRIGGER_DEF2(new MyE2_String("ID-Trigger-Def"))
		 ,ID_TRIGGER_NAME_REAL(new MyE2_String("Triggername in der Datenbank"))
		
		;
		
		private String 			hashKey = null;    			//key in der listen-componentmap
		private String 			sqlFieldAlias = null; 		//alias, falls ein sql-field dafuer definiert wird
		private MyE2_String  	info4User = null;
		
		private SPALTEN_NAMEN(MyE2_String p_info4User) {
			this.info4User = 		p_info4User;
		}

		public String hashKey() {
			return this.hashKey==null?this.name():this.hashKey;
		}

		public String sqlFieldAlias() {
			return this.sqlFieldAlias==null?this.name():this.sqlFieldAlias;
		}

		public MyE2_String userInfo() {
			return this.info4User==null?new MyE2_String(this.name(),false):this.info4User;
		}
		
	}

    
	
	
	public enum TRIGGER_ACTION_DEF_TYPES {
		INSERT
		,UPDATE
		,DELETE
	}
    
	
	public static String genRealTriggerName(String triggerName) {
		return AT_CONST.LOGTRIGGER_PREFIX+triggerName+bibALL.get_ID_MANDANT();
	}
	
	
	
	public static RB_KF key_InfoButtonValidation() throws myException {
		return new RB_KF(TRIGGER_ACTION_DEF.id_trigger_action_def,TRIGGER_ACTION_DEF.id_trigger_action_def.fn()+"/1");
	}
	
	public static RB_KF key_InfoButtonExecution() throws myException {
		return new RB_KF(TRIGGER_ACTION_DEF.id_trigger_action_def,TRIGGER_ACTION_DEF.id_trigger_action_def.fn()+"/2");
	}
	

}
 

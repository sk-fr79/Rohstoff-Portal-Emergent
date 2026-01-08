package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Enum4JoinDef;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM._TermCONST.JOINTYPES;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class AH7__CONST {

	// definiert die adress-joins innerhalb der liste
	public enum JOINS implements IF_Enum4JoinDef {
		
		
		
		ID_ADRESSE_GEO_START(null,"JT_AH7_STEUERDATEI", 		ADRESSE.id_adresse, "S", "S_")
		,ID_ADRESSE_GEO_ZIEL(null,"JT_AH7_STEUERDATEI", 		ADRESSE.id_adresse, "Z", "Z_")
		
		,ID_1_ABFALLERZEUGER(null,"JT_AH7_STEUERDATEI", 		ADRESSE.id_adresse, "AE1", "AE1_")
		,ID_1_IMPORT_EMPFAENGER(null,"JT_AH7_STEUERDATEI", 		ADRESSE.id_adresse, "AI1", "AI1_")
		,ID_1_VERBR_VERANLASSER(null,"JT_AH7_STEUERDATEI", 		ADRESSE.id_adresse, "AVA1", "AVA1_")
		,ID_1_VERWERTUNGSANLAGE(null,"JT_AH7_STEUERDATEI", 		ADRESSE.id_adresse, "AVE1", "AVE1_")

		,ID_2_ABFALLERZEUGER(null,"JT_AH7_STEUERDATEI", 		ADRESSE.id_adresse, "AE2", "AE2_")
		,ID_2_IMPORT_EMPFAENGER(null,"JT_AH7_STEUERDATEI", 		ADRESSE.id_adresse, "AI2", "AI2_")
		,ID_2_VERBR_VERANLASSER(null,"JT_AH7_STEUERDATEI", 		ADRESSE.id_adresse, "AVA2", "AVA2_")
		,ID_2_VERWERTUNGSANLAGE(null,"JT_AH7_STEUERDATEI", 		ADRESSE.id_adresse, "AVE2", "AVE2_")
		
		,ID_3_ABFALLERZEUGER(null,"JT_AH7_STEUERDATEI", 		ADRESSE.id_adresse, "AE3", "AE3_")
		,ID_3_IMPORT_EMPFAENGER(null,"JT_AH7_STEUERDATEI", 		ADRESSE.id_adresse, "AI3", "AI3_")
		,ID_3_VERBR_VERANLASSER(null,"JT_AH7_STEUERDATEI", 		ADRESSE.id_adresse, "AVA3", "AVA3_")
		,ID_3_VERWERTUNGSANLAGE(null,"JT_AH7_STEUERDATEI", 		ADRESSE.id_adresse, "AVE3", "AVE3_")

//		,ID_LAND_S(ADRESSE.id_land,"S", 	    				LAND.id_land, "LS", "LS_")
//		,ID_LAND_Z(ADRESSE.id_land,"Z", 						LAND.id_land, "LZ", "LZ_")
//		
//		,ID_AH7_PROFIL(null,"JT_AH7_STEUERDATEI",               AH7_PROFIL.id_ah7_profil,"P","P_")			
		;
		
		private IF_Field  			leftField = 	null;
		private String 				leftTableAlias = 		null;
		private IF_Field  			joinField = 	null;
		private String 				joinTableAlias = 		null; 
		private String      		joinFieldsPrefix =        null;
		
		
		/**
		 * @param p_leftField
		 * @param p_leftTableAlias
		 * @param p_joinField
		 * @param p_joinTableAlias
		 * @param p_joinFieldsPrefix
		 */
		private JOINS(IF_Field p_leftField, String p_leftTableAlias, IF_Field p_joinField, String p_joinTableAlias, String p_joinFieldsPrefix) {
			this.leftField = 			p_leftField;
			this.leftTableAlias = 		p_leftTableAlias;
			this.joinField = 			p_joinField;
			this.joinTableAlias = 		p_joinTableAlias;
			this.joinFieldsPrefix = 	p_joinFieldsPrefix;
		}

		@Override
		public String getSchema() {
			return bibE2.cTO();
		}


		@Override
		public String getJoinTableAlias() {
			return this.joinTableAlias;
		}

		@Override
		public JOINTYPES getJoinTyp() {
			return JOINTYPES.LEFTOUTERJOIN;
		}

		@Override
		public IF_Field getLeftField() throws myException {
			if (this.leftField == null) {
				return _TAB.find_field(_TAB.ah7_steuerdatei, this.name());
			}
			return this.leftField;
		}

		@Override
		public IF_Field getJoinField() {
			return this.joinField;
		}

		@Override
		public VEK<IF_Field> getFields() throws myException {
			if (this.joinField._t()==_TAB.adresse) {
				return new VEK<IF_Field>()._a(ADRESSE.name1,ADRESSE.name2,ADRESSE.strasse,ADRESSE.id_land, ADRESSE.plz, ADRESSE.ort, ADRESSE.ah7_quelle_sicher,ADRESSE.ah7_ziel_sicher);
			} else if (this.joinField._t()==_TAB.land) {
				return new VEK<IF_Field>()._a(LAND.laendercode);
			} else if (this.joinField._t()==_TAB.ah7_profil) {
				return new VEK<IF_Field>()._a(AH7_PROFIL.bezeichnung);
			}
			return new VEK<IF_Field>();
		}

		@Override
		public String getJoinTableFieldPrefix() {
			return this.joinFieldsPrefix;
		}

		@Override
		public String getLeftTableAlias() {
			return this.leftTableAlias;
		}
	}

	/**
		this.add_Component(new MyE2_DB_Label(oFM.get_(AH7_STEUERDATEI.id_1_abfallerzeuger)), new MyE2_String("ID_1_ABFALLERZEUGER"));
		this.add_Component(new MyE2_DB_Label(oFM.get_(AH7_STEUERDATEI.id_1_import_empfaenger)), new MyE2_String("ID_1_IMPORTEUR_EMPFAENGER"));
		this.add_Component(new MyE2_DB_Label(oFM.get_(AH7_STEUERDATEI.id_1_verbr_veranlasser)), new MyE2_String("ID_1_VERBRINGUNGVERANLASSER"));
		this.add_Component(new MyE2_DB_Label(oFM.get_(AH7_STEUERDATEI.id_1_verwertungsanlage)), new MyE2_String("ID_1_VERWERTUNGSANLAGE"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("DRUCKE_BLATT2")), new MyE2_String("DRUCKE_BLATT2"));
		this.add_Component(new MyE2_DB_Label(oFM.get_(AH7_STEUERDATEI.id_2_abfallerzeuger)), new MyE2_String("ID_2_ABFALLERZEUGER"));
		this.add_Component(new MyE2_DB_Label(oFM.get_(AH7_STEUERDATEI.id_2_import_empfaenger)), new MyE2_String("ID_2_IMPORT_EMPFAENGER"));
		this.add_Component(new MyE2_DB_Label(oFM.get_(AH7_STEUERDATEI.id_2_verbr_veranlasser)), new MyE2_String("ID_2_VERBR_VERANLASSER"));
		this.add_Component(new MyE2_DB_Label(oFM.get_(AH7_STEUERDATEI.id_2_verwertungsanlage)), new MyE2_String("ID_2_VERWERTUNGSANLAGE"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("DRUCKE_BLATT3")), new MyE2_String("DRUCKE_BLATT3"));
		this.add_Component(new MyE2_DB_Label(oFM.get_(AH7_STEUERDATEI.id_3_abfallerzeuger)), new MyE2_String("ID_3_ABFALLERZEUGER"));
		this.add_Component(new MyE2_DB_Label(oFM.get_(AH7_STEUERDATEI.id_3_import_empfaenger)), new MyE2_String("ID_3_IMPORT_EMPFAENGER"));
		this.add_Component(new MyE2_DB_Label(oFM.get_(AH7_STEUERDATEI.id_3_verbr_veranlasser)), new MyE2_String("ID_3_VERBR_VERANLASSER"));
		this.add_Component(new MyE2_DB_Label(oFM.get_(AH7_STEUERDATEI.id_3_verwertungsanlage)), new MyE2_String("ID_3_VERWERTUNGSANLAGE"));

	 *
	 */
	

	
    public enum AH7_B_NAMES implements IF_enum_4_db {
        
        CHECKBOX_LISTE(new MyE2_String("Auswahl-Checkbox"))
        ,MARKER_LISTE(new MyE2_String("Markierung Liste"))
        
        ;
        
        private MyE2_String userText = null; 
        private AH7_B_NAMES(MyE2_String p_userText) {
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
    
    
    
    
    public enum AH7_B_BUTTONS implements IF_enum_4_db  {
        DELETE("LOESCHE_AH7_STEUERDATEI")
        ,EDIT("BEARBEITE_AH7_STEUERDATEI")
        ,VIEW("ANZEIGE_AH7_STEUERDATEI")
        ,NEW("NEUEINGABE_AH7_STEUERDATEI")
        ,MASK_BUTTON_ZEIGE_PROFIL("MASK_BUTTON_ZEIGE_PROFIL")
        ,
        
        ;
        
        private String KEY = null;
        
        private AH7_B_BUTTONS() {
            this.KEY=this.name();
        }
        private AH7_B_BUTTONS(String key) {
            this.KEY=key;
        }
        public String     db_val() {    return S.isEmpty(this.KEY)?this.name():this.KEY;}
        @Override
        public String user_text_lang() {return this.name();    }
        @Override
        public String user_text() {        return this.name();        }
        @Override
        public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
            return bibENUM.dd_array(AH7_B_BUTTONS.values(), emptyPairInFront);
        }
        
    }
    
    
    /**
     * translator-enum zwischen verwendeten keys im programmgenerator und den Globalen bezeichnern in E2_MODULNAME_ENUM.MODUL
     * @author martin
     *
     */
    public enum TRANSLATOR {
    	
        //bitte im enum E2_MODULNAME_ENUM.MODUL die zwei Bezeichner AH7_STEUERDATEI_LIST  und AH7_STEUERDATEI_MASK ergaenzen,
        //sowie fuer den starter im zugehoerigen CASE - Verweiger sorgen
       	
    	
        LIST(E2_MODULNAME_ENUM.MODUL.AH7_STEUERDATEI_LISTE)
        ,MASK(E2_MODULNAME_ENUM.MODUL.AH7_STEUERDATEI_MASKE)
        ;
    	
        private MODUL  modul = null;
        private TRANSLATOR(E2_MODULNAME_ENUM.MODUL p_modul) {
           modul = p_modul;
        }
	    public MODUL get_modul() {
	        return modul;
        }
    }

	
	
}

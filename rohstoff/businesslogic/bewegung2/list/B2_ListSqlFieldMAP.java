 
package rohstoff.businesslogic.bewegung2.list;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.EnJoins4Querys;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;


public class B2_ListSqlFieldMAP extends SQLFieldMAP  {
	
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public B2_ListSqlFieldMAP(RB_TransportHashMap  p_tpHashMap) throws myException   {
    	
        super(_TAB.bg_vektor.n());
        

        this.m_tpHashMap = p_tpHashMap;        
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP,this);
        
        
        for (EnJoins4Querys.NamesOfFieldsToRead q: EnJoins4Querys.NamesOfFieldsToRead.values()) {
        	if (q.getTermRelation()!=null) {
        		String s_relation = "";
        		if (q.getTermRelation()!=null ) {
        			s_relation = q.getTermRelation().s();
        			if (q.getAddOnTerm1()!=null) {
        				s_relation = s_relation+" AND "+q.getAddOnTerm1().s();
        			}
        			if (q.getAddOnTerm2()!=null) {
        				s_relation = s_relation+" AND "+q.getAddOnTerm2().s();
        			}
        			
        			s_relation = "("+ s_relation + ")";
        			
        		}
        		this.addJoinBlock(new joinBlock(q.getField()._t(), q.name(), SQLFieldMAP.INNER, "", true, s_relation));
        	}
        }
       
        
        
        
        //zusaetzliche ids
		this.addJoinBlock(new joinBlock(_TAB.adresse,   	"AD1",	SQLFieldMAP.LEFT_OUTER, "",true,	BG_STATION.id_adresse.fn("S1")+"="+		ADRESSE.id_adresse.fn("AD1")));
		this.addJoinBlock(new joinBlock(_TAB.adresse, 		"AD2",	SQLFieldMAP.LEFT_OUTER, "",true,	BG_STATION.id_adresse.fn("S2")+"="+		ADRESSE.id_adresse.fn("AD2")));
		this.addJoinBlock(new joinBlock(_TAB.adresse, 		"AD3",	SQLFieldMAP.LEFT_OUTER, "",true,	BG_STATION.id_adresse.fn("S3")+"="+		ADRESSE.id_adresse.fn("AD3")));
		this.addJoinBlock(new joinBlock(_TAB.adresse,   	"ADB1",	SQLFieldMAP.LEFT_OUTER, "",true,	BG_STATION.id_adresse_basis.fn("S1")+"="+	ADRESSE.id_adresse.fn("ADB1")));
		this.addJoinBlock(new joinBlock(_TAB.adresse, 		"ADB2",	SQLFieldMAP.LEFT_OUTER, "",true,	BG_STATION.id_adresse_basis.fn("S2")+"="+	ADRESSE.id_adresse.fn("ADB2")));
		this.addJoinBlock(new joinBlock(_TAB.adresse, 		"ADB3",	SQLFieldMAP.LEFT_OUTER, "",true,	BG_STATION.id_adresse_basis.fn("S3")+"="+	ADRESSE.id_adresse.fn("ADB3")));
		this.addJoinBlock(new joinBlock(_TAB.artikel_bez,   "AB1",	SQLFieldMAP.LEFT_OUTER, "",true,	BG_ATOM.id_artikel_bez.fn("A1")+"="+	ARTIKEL_BEZ.id_artikel_bez.fn("AB1")));
		this.addJoinBlock(new joinBlock(_TAB.artikel_bez,   "AB2",	SQLFieldMAP.LEFT_OUTER, "",true,	BG_ATOM.id_artikel_bez.fn("A2")+"="+	ARTIKEL_BEZ.id_artikel_bez.fn("AB2")));
		this.addJoinBlock(new joinBlock(_TAB.artikel,   	"AR1",	SQLFieldMAP.LEFT_OUTER, "",true,	ARTIKEL_BEZ.id_artikel.fn("AB1")+"="+	ARTIKEL.id_artikel.fn("AR1")));
		this.addJoinBlock(new joinBlock(_TAB.artikel,   	"AR2",	SQLFieldMAP.LEFT_OUTER, "",true,	ARTIKEL_BEZ.id_artikel.fn("AB2")+"="+	ARTIKEL.id_artikel.fn("AR2")));

		this.addJoinBlock(new joinBlock(_TAB.vpos_kon,   	"VP1",	SQLFieldMAP.LEFT_OUTER, "",true,	BG_ATOM.id_vpos_kon.fn("A1")+"="+	VPOS_KON.id_vpos_kon.fn("VP1")));
		this.addJoinBlock(new joinBlock(_TAB.vpos_kon,   	"VP2",	SQLFieldMAP.LEFT_OUTER, "",true,	BG_ATOM.id_vpos_kon.fn("A2")+"="+	VPOS_KON.id_vpos_kon.fn("VP2")));
		
		this.addJoinBlock(new joinBlock(_TAB.vkopf_kon,   	"VK1",	SQLFieldMAP.LEFT_OUTER, "",true,	VPOS_KON.id_vkopf_kon.fn("VP1")+"="+	VKOPF_KON.id_vkopf_kon.fn("VK1")));
		this.addJoinBlock(new joinBlock(_TAB.vkopf_kon,   	"VK2",	SQLFieldMAP.LEFT_OUTER, "",true,	VPOS_KON.id_vkopf_kon.fn("VP2")+"="+	VKOPF_KON.id_vkopf_kon.fn("VK2")));

		
		
//		//jetzt die ids aus dem basisblock
//		this.add_SQLField(new SQLField(BG_VEKTOR.id_bg_vektor.fn("VE"), 	"VE_ID_BG_VEKTOR", S.ms("Vektor-ID")), false);

		this.add_SQLField(new SQLField(BG_STATION.id_bg_station.fn("S1"), "S1_ID_BG_STATION", S.ms("Start-Station-ID")), false);
		this.add_SQLField(new SQLField(BG_STATION.id_bg_station.fn("S2"), "S2_ID_BG_STATION", S.ms("Mittel-Station-ID")), false);
		this.add_SQLField(new SQLField(BG_STATION.id_bg_station.fn("S3"), "S3_ID_BG_STATION", S.ms("Ziel-Station-ID")), false);
		
		this.add_SQLField(new SQLField(BG_ATOM.id_bg_atom.fn("A1"), 		"A1_ID_BG_ATOM", S.ms("Start-Atom-ID")), false);
		this.add_SQLField(new SQLField(BG_ATOM.id_bg_atom.fn("A2"), 		"A2_ID_BG_ATOM", S.ms("Ziel-Atom-ID")), false);
		
		
		//hier die definitionen aus der enum
		for (EnBgFieldList lab: EnBgFieldList.values()) {
			if (lab.isForSqlFieldDef()) {
				this.add_SQLField(new SQLField(lab.getFieldTerm(), lab.name(), S.ms(lab.getFieldName4User())), false);
			}
		}
		

		
		this.add_SQLField(		new SQLFieldForPrimaryKey(_TAB.bg_vektor.n(),
								_TAB.bg_vektor.keyFieldName(),_TAB.bg_vektor.keyFieldName(),new MyE2_String("ID-BG-Vektor"),bibE2.get_CurrSession(),
								_TAB.bg_vektor.sql_nextval(),true), false);
		

        
        this.initFields();
    }
    
}
 
 

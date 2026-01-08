package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class AH7_LIST_SqlFieldMAP extends Project_SQLFieldMAP {
	
	public AH7_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_AH7_STEUERDATEI", "", false);
		
		for (AH7__CONST.JOINS j: AH7__CONST.JOINS.values()) {
			this.add_JOIN_Table(j,null);
		}
		
		SEL selLandStart = new SEL(LAND.laendercode).FROM(_TAB.land).WHERE(new TermSimple(LAND.id_land.tnfn()+"="+ADRESSE.id_land.fn("S")));
		SEL selLandZiel = new SEL(LAND.laendercode).FROM(_TAB.land).WHERE(new TermSimple(LAND.id_land.tnfn()+"="+ADRESSE.id_land.fn("Z")));
		SEL selProfil = new SEL(AH7_PROFIL.bezeichnung).FROM(_TAB.ah7_profil).WHERE(new TermSimple(AH7_PROFIL.id_ah7_profil.tnfn()+"="+AH7_STEUERDATEI.id_ah7_profil.tnfn()));
		
		this.add_SQLField(new SQLField("("+selLandStart.s()+")", 	"LS_LAENDERCODE", S.ms("Start-Ländercode")), false);
		this.add_SQLField(new SQLField("("+selLandZiel.s()+")", 	"LZ_LAENDERCODE", S.ms("Ziel-Ländercode")), false);
		this.add_SQLField(new SQLField("("+selProfil.s()+")",  		"P_BEZEICHNUNG", S.ms("Profil")), false);
		
		
		this.initFields();
	}
	
}

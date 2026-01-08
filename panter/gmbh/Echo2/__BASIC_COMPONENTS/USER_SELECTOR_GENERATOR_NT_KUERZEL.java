package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import java.util.Vector;

import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.is_not_null;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class USER_SELECTOR_GENERATOR_NT_KUERZEL {
	
	private String[][] arrSelected = 	null;
	private String[][] arrRest = 		null;

	/*
	 * Hilfsklasse, was ein array fuer drop-down-selectoren erzeugt, die ueberall verwendet werden koennen.
	 * Dabei werden die Buero-Benutzer zuerst genannt, dann die benutzer aus dem betrieb dahinter
	 */
	public USER_SELECTOR_GENERATOR_NT_KUERZEL(ENUM_USER_TYP... typ) throws myException  {
		super();

		if (typ==null || typ.length==0) {
			throw new myException(this, "Fehler: bitte userTyp übergeben");
		}
		
		vgl v1 = this.get_vgl(typ[0]);
		And whereblock = new And(v1);

		for (int i=1; i<typ.length;i++) {
			whereblock.or(this.get_vgl(typ[i]));
		}
		whereblock.and(new vgl(USER.aktiv, "Y"));
		And  notWhere = new And(new TermSimple("NOT ("+whereblock.s()+")"));
		
		whereblock.and(new vgl(USER.id_mandant, bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()));
		notWhere.and(new vgl(USER.id_mandant, bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF()));
		
		SEL selUser = new SEL("NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||')'","KUERZEL")
		                      .FROM(_TAB.user).WHERE(whereblock).AND(new is_not_null(USER.kuerzel,null)).ORDERUP("1");
		
		SEL selUserRest = new SEL("NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||')'","KUERZEL")
        						.FROM(_TAB.user).WHERE(notWhere).AND(new is_not_null(USER.kuerzel,null)).ORDERUP("1");
		

		this.arrSelected = bibDB.EinzelAbfrageInArray(selUser.s(),"");
		this.arrRest = bibDB.EinzelAbfrageInArray(selUserRest.s(),"");

		
	}
	
	private vgl get_vgl(ENUM_USER_TYP typ) throws myException {
		vgl rueck = null;
		if (typ == null) {
			throw new myException(this, "Fehler Übergabe des UserTyps!");
		}

		
		switch (typ) {
		case BUERO: 
			rueck = new vgl(USER.ist_verwaltung, "Y");
			break;
		case FAHRER:
			rueck = new vgl(USER.ist_fahrer, "Y");
			break;
		case AKTIV:
			rueck = new vgl(USER.aktiv, "Y");
			break;
		case GESCHAEFTSFUEHRER:
			rueck = new vgl(USER.geschaeftsfuehrer, "Y");
			break;
		case SUPERVISOR:
			rueck = new vgl(USER.ist_supervisor, "Y");
			break;
		case ENTWICKLER:
			rueck = new vgl(USER.developer, "Y");
			break;
		}
		
		if (rueck == null) {
			throw new myException(this, "Fehler: UserTyp ist unbekannt!");
		}
		
		return rueck;
	}
	
	
	
	private void add_(String[][] cPair, Vector<String[]> vPaare)
	{
		for (int i=0;i<cPair.length;i++)
		{
			vPaare.add(cPair[i]);
		}
	}

	/**
	 * 
	 * @param bMitLeerSelectVoraus
	 * @return array mit den ausgewaehlten usern
	 */
	public String[][] get_selUsers(boolean bMitLeerSelectVoraus) {
		return this._get_users(this.arrSelected, bMitLeerSelectVoraus);
	}
	
	/**
	 * 
	 * @param bMitLeerSelectVoraus
	 * @return array mit den NICHT ausgewaehlten usern (fuer schadow)
	 */
	public String[][] get_notSelectedUsers() {
		return this._get_users(this.arrRest, false);
	}
	
	
	private String[][] _get_users(String[][] array, boolean bMitLeerSelectVoraus) {
		
		//jetzt die liste aufbauen
		Vector<String[]> vPaare = new Vector<String[]>();
		
		if (bMitLeerSelectVoraus){
			String[] cLeer = {"--",""};
			vPaare.add(cLeer);
		}

		this.add_(array, vPaare);
		
		String[][] cWerte = new String[vPaare.size()][2]; 
		for (int i=0;i<vPaare.size();i++)
		{
			cWerte[i][0]=vPaare.get(i)[0];
			cWerte[i][1]=vPaare.get(i)[1];
		}

		return cWerte;
	}
	

	
}

package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_HANDELSDEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB_SYNTAX;
import panter.gmbh.indep.exceptions.myException;

/**
 * klasse uebernimmt parameter und sucht die zugehoerige handelsdefinition
 * heraus
 * 
 * @author martin
 * 
 */
public class HD_ErmittlePassendeHandelsDef
{

	private HD_Station					oStationEK			= 			null;
	private HD_Station					oStationVK			= 			null;
	
	//dieser Vector sollte im Treffer-Fall genau eine Fuhreneinstufung enthalten. 
	//wird keine gefunden, dann muss diese eingepflegt werden, gibt es mehrere, dann korrigiert
	private HD_WarenBewegungEinstufungen	warenBewegungEinstufungen	= 	null;
	private RECLIST_HANDELSDEF			rl_HandelsDef			= 		null;

	
	/**
	 * 
	 * @param quelle
	 * @param ziel
	 * @param tp_Verantwortung
	 * @throws myException
	 */
	public HD_ErmittlePassendeHandelsDef(HD_Station quelle, HD_Station ziel) throws myException
	{
		super();
		this.oStationEK = quelle;
		this.oStationVK = ziel;

		this.warenBewegungEinstufungen = new HD_WarenBewegungEinstufungen(this.oStationEK, this.oStationVK);

		//2013-10-01: aktiv/inaktiv checken
		this.rl_HandelsDef = new RECLIST_HANDELSDEF("SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE " + this.get_cWhereBlock()+" AND "+bibDB_SYNTAX.GENERATE_YES_NO_WHERE(_DB.HANDELSDEF, _DB.HANDELSDEF$AKTIV, "Y"));
		
		for (RECORD_HANDELSDEF recDef : rl_HandelsDef.values())
		{
			HD_RECORD_HANDELSDEF recDef2 = new HD_RECORD_HANDELSDEF(recDef);

			HD_WarenBewegungEinstufung oEinstuf = new HD_WarenBewegungEinstufung(recDef2, this.oStationEK, this.oStationVK);

			this.warenBewegungEinstufungen.add(oEinstuf, false);    // nur anfuegen, wenn neuer sachverhalt
		}
		
	}

	
	
	/**
	 * 2014-02-27: weiterer konstruktor, der bei der suche auch inaktive mitnimmt, damit der TF_RuleFinder arbeitet
	 * @param quelle
	 * @param ziel
	 * @param findInactivToo (wenn true, dann sucht das system in allen handelsdefs, auch den inaktiven
	 * @param tp_Verantwortung
	 * @throws myException
	 */
	public HD_ErmittlePassendeHandelsDef(HD_Station quelle, HD_Station ziel, boolean findInactivToo) throws myException
	{
		super();
		this.oStationEK = quelle;
		this.oStationVK = ziel;

		this.warenBewegungEinstufungen = new HD_WarenBewegungEinstufungen(this.oStationEK, this.oStationVK);

		//2013-10-01: aktiv/inaktiv checken
		if (findInactivToo) {
			this.rl_HandelsDef = new RECLIST_HANDELSDEF(
					"SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE " + this.get_cWhereBlock());
		} else {
			this.rl_HandelsDef = new RECLIST_HANDELSDEF(
					"SELECT * FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE " + this.get_cWhereBlock()+" AND "+bibDB_SYNTAX.GENERATE_YES_NO_WHERE(_DB.HANDELSDEF, _DB.HANDELSDEF$AKTIV, "Y"));
		}
		
		for (RECORD_HANDELSDEF recDef : rl_HandelsDef.values())
		{
			HD_RECORD_HANDELSDEF recDef2 = new HD_RECORD_HANDELSDEF(recDef);

			HD_WarenBewegungEinstufung oEinstuf = new HD_WarenBewegungEinstufung(recDef2, this.oStationEK, this.oStationVK);

			this.warenBewegungEinstufungen.add(oEinstuf, false);    // nur anfuegen, wenn neuer sachverhalt
		}
		
	}

	

	
	
	
	/*
	 * suche in JT_HANDELSDEF um einen passenden Datensatz fuer die bewertung
	 * der fuhre zu bekommen
	 */
	private String get_cWhereBlock() throws myException
	{
		Vector<String> vSQLs = new Vector<String>();

		vSQLs.addAll(this.oStationEK.get_vWhereBlock());
		vSQLs.addAll(this.oStationVK.get_vWhereBlock());

		return bibALL.Concatenate(vSQLs, " AND ", " 1=2");
	}

	public HD_WarenBewegungEinstufungen getWarenBewegungEinstufungen()
	{
		return warenBewegungEinstufungen;
	}

	
}

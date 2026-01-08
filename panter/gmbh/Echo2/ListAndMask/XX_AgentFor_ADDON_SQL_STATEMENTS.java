package panter.gmbh.Echo2.ListAndMask;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;


/*
 * agents werden beim erzeugen der sql-statemtents beim speichern der masken herangezogen und
 * liefern (abhaengig vom eingabewert) zusatzstatements
 */
public abstract class XX_AgentFor_ADDON_SQL_STATEMENTS 
{
	
	public abstract Vector<String> get_vAddON_SQL_Statements_NEU(E2_ComponentMAP oMAP, SQLMaskInputMAP oInputMAP, MyE2_MessageVector vMessagesSammler);
	public abstract Vector<String> get_vAddON_SQL_Statements_EDIT(E2_ComponentMAP oMAP, SQLMaskInputMAP oInputMAP, MyE2_MessageVector vMessagesSammler);
	
	
}

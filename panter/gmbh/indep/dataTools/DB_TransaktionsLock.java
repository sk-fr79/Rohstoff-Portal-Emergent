package panter.gmbh.indep.dataTools;


import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class DB_TransaktionsLock extends XX_TransActionLock 
{
	
	/*
	 * transaktions-lock-objekt, wird in der session gespeichert. prueft das vorhandensein eines lock-tabellensatzes
	 * (es muss nur einer sein)
	 */
	public DB_TransaktionsLock() throws myException
	{
		super();
		
		String cQuery = "SELECT COUNT(*) FROM "+bibALL.get_TABLEOWNER()+".JT_LOCK ";
		String cCount = bibDB.EinzelAbfrage(cQuery,"","","",false,true);
		
		if (S.isFull(cCount))
		{
			if (cCount.trim().equals("0"))
			{
				//statt seq_lock.nextval wird hier die id_mandant als id_lock gesetzt, da jeder mandant nur ein lock-eintrag hat
				String cSEQ_Lock = ""+(bibALL.get_RECORD_MANDANT().get_ID_MANDANT_lValue(new Long(-1))+1000);
				if (!bibDB.ExecSQL("INSERT INTO "+bibALL.get_TABLEOWNER()+".JT_LOCK (ID_LOCK,KENNSTRING) VALUES("+cSEQ_Lock+",'GLOBAL_LOCK')",true))
				{
					throw new myException("Error inserting Statement !");
				}
			}
		}
		else
		{
			/*
			 * falls hier ein fehler auftaucht, dann koennte evtl. ein defekter view vorhanden sein. dies wuerde aber dazu fuehren,
			 * dass ein einloggen und neuaufbauen der views nicht mehr gehen wuerde, deshalb wird dies hier beruecksichtigt:
			 */
			String cNamenView = "V" + bibALL.get_ID_MANDANT() + "_" + "JT_LOCK".substring(3);
			String cSqlBaueNeuView = MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"CREATE OR REPLACE VIEW " + cNamenView + " AS SELECT * FROM JT_LOCK WHERE ID_MANDANT=" + bibALL.get_ID_MANDANT().trim();

			boolean bFehler = true;
			
			
			
			if (bibDB.ExecSQL(cSqlBaueNeuView, true))
			{
				cCount = bibDB.EinzelAbfrage(cQuery,"","","",false,true);
				if (bibALL.isInteger(cCount))
				{
					if (bibDB.ExecSQL("INSERT INTO "+bibALL.get_TABLEOWNER()+".JT_LOCK (ID_LOCK,KENNSTRING) VALUES(SEQ_LOCK.NEXTVAL,'GLOBAL_LOCK')",true))
					{
						bFehler = false;
					}
				}
			}

			if (bFehler)
			{
				throw new myException("Error Quering Lock-Numbers !");
			}
			
		}
	}

	protected String get_LockStatement() 
	{
		return "UPDATE "+bibALL.get_TABLEOWNER()+".JT_LOCK SET KENNSTRING=KENNSTRING WHERE ID_MANDANT="+bibALL.get_ID_MANDANT();
	}

}

package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ListSelector;

public class FS__SelectFieldBranche extends MyE2_SelectField 
{
	public FS__SelectFieldBranche() throws myException 
	{
		super();
		String[][] cBranchen = bibDB.EinzelAbfrageInArray("SELECT KURZBEZEICHNUNG,ID_BRANCHE FROM "+bibE2.cTO()+".JT_BRANCHE WHERE KURZBEZEICHNUNG IS NOT NULL ORDER BY KURZBEZEICHNUNG");
		String[][] c2Branche = new String[cBranchen.length+2][2];
		c2Branche[0][0] = "-"; c2Branche[0][1] = "";             																		// leer-eintrag
		c2Branche[1][0] = new MyE2_String("-- keine Branche --").CTrans(); c2Branche[1][1] = FS_ListSelector.NO_BRANCHE;             	// IS NULL - Eintrag
		for (int i=0;i<cBranchen.length;i++)
		{
			c2Branche[i+2][0] = cBranchen[i][0];   c2Branche[i+2][1] = cBranchen[i][1];             // leer-eintrag
		}
		
		this.set_ListenInhalt(c2Branche, false);
	}

}

package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER_IMPORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class ZOL_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{
    public ZOL_LIST_SqlFieldMAP() throws myException 
    {
        super(_TAB.zolltarifnummer.n(), "", false);
        
        this.add_JOIN_Table(
        		ZOLLTARIFNUMMER_IMPORT.fullTabName(), 
				ZOLLTARIFNUMMER_IMPORT.fullTabName(), 
				SQLFieldMAP.LEFT_OUTER, 
				":NUMMER:TEXT1:TEXT2:TEXT3:BM_TEXT:BM_NUMMER:", 
				true,
				ZOLLTARIFNUMMER.nummer.tnfn() + " = " + ZOLLTARIFNUMMER_IMPORT.nummer.tnfn(),
				"IMP_", 
				null);

		this.add_SQLField(
				new SQLField("case when " +  ZOLLTARIFNUMMER_IMPORT.nummer.tnfn()+ " IS NULL then 'Y' else 'N' end", 
						"IS_DELETED", 
						new MyE2_String("Deleted"), 
						bibE2.get_CurrSession()), 
				true);

		this.add_SQLField(
				new SQLField(
						"case when ( ("
								+ "	(nvl(" + ZOLLTARIFNUMMER_IMPORT.text1.tnfn()+",'*')) != (nvl(" + ZOLLTARIFNUMMER.text1.tnfn()+",'*')) OR "
								+ " (nvl(" + ZOLLTARIFNUMMER_IMPORT.text2.tnfn()+",'*')) != (nvl(" + ZOLLTARIFNUMMER.text2.tnfn()+",'*')) OR "
								+ " (nvl(" + ZOLLTARIFNUMMER_IMPORT.text3.tnfn()+",'*')) != (nvl(" + ZOLLTARIFNUMMER.text3.tnfn()+",'*')) "
								+ " ) AND "  +  ZOLLTARIFNUMMER_IMPORT.nummer.tnfn()+ " IS NOT NULL ) then 'Y' else 'N' end", 
				"IS_CHANGED", 
				new MyE2_String("Changed"), 
				bibE2.get_CurrSession()), 
				true);
		
		
        this.initFields();
    }
    
}
 

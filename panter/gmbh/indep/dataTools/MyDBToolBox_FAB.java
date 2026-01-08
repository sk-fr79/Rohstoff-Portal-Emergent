package panter.gmbh.indep.dataTools;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public abstract class MyDBToolBox_FAB {
	
	public abstract MyDBToolBox  generate_INDIVIDUELL_DBToolBox(MyConnection conn) throws myException;

	
	
	
	
	
	
	public static MyDBToolBox generate_STANDARD_DBToolBox() {
		return MyDBToolBox_FAB.generate_STANDARD_DBToolBox(null);
	}
	
	
	// erzeugen einer standard-MyDBToolBox
	public static MyDBToolBox generate_STANDARD_DBToolBox(MyConnection oConn) {
	
	    MyDBToolBox oOB = null;
	    try {
	        oOB = new MyDBToolBox((oConn==null?bibALL.get_oConnectionNormal():oConn));

	        //2015-05-06: ersetzt durch direkten zugriff
	        //oOB.setZusatzFelder((String[][]) bibALL.getSessionValue("ZUSATZFELDER"));
	        oOB.setZusatzFelder(DB_STATICS.get_DB_ZusatzFelder_STD());
	        oOB.set_ErsetzungTableView((String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT"));
	        oOB.set_bErsetzungTableView(true); // standardmässig wird ersetzt tables gegen views bei JT_tables
	        
	    } catch (myException ex)  {
	    }
	    return oOB;
	}

	// methode gibt einen connection-eintrag aus dem CONNPOOL-Eintrag in der session zurück
	// oder baut einen neuen eintrag, wenn keiner mehr frei ist
	public static MyDBToolBox get_myDBToolBox(boolean bZusatzFelder, boolean bErsetzeTableView)
	{
	
	    MyDBToolBox oOB = null;
	    try   {
	        oOB = new MyDBToolBox(bibALL.get_oConnectionNormal());

	        //2015-05-06: ersetzt durch direkten zugriff
//	        if (bZusatzFelder)		{oOB.setZusatzFelder((String[][]) bibALL.getSessionValue("ZUSATZFELDER"));}
	        if (bZusatzFelder)		{oOB.setZusatzFelder(DB_STATICS.get_DB_ZusatzFelder_STD());}
	        if (bErsetzeTableView)	{oOB.set_ErsetzungTableView((String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT"));}
	        
	        oOB.set_bErsetzungTableView(bErsetzeTableView); // standardmässig wird ersetzt tables gegen views bei JT_tables
	    } catch (myException ex)  {
	    }
	    return oOB;
	}


	/**
	 * 2016-09-07: martin
	 * @param bZusatzFelder
	 * @param bErsetzeTableView
	 * @param con
	 * @return
	 */
	public static MyDBToolBox get_myDBToolBox(boolean bZusatzFelder, boolean bErsetzeTableView, MyConnection con)
	{
	
	    MyDBToolBox oOB = null;
	    try   {
	        oOB = new MyDBToolBox(con);

	        //2015-05-06: ersetzt durch direkten zugriff
//	        if (bZusatzFelder)		{oOB.setZusatzFelder((String[][]) bibALL.getSessionValue("ZUSATZFELDER"));}
	        if (bZusatzFelder)		{oOB.setZusatzFelder(DB_STATICS.get_DB_ZusatzFelder_STD());}
	        if (bErsetzeTableView)	{oOB.set_ErsetzungTableView((String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT"));}
	        
	        oOB.set_bErsetzungTableView(bErsetzeTableView); // standardmässig wird ersetzt tables gegen views bei JT_tables
	    } catch (myException ex)  {
	    }
	    return oOB;
	}


	


	// erzeugen einer standard-MyDBToolBox
	public static MyDBToolBox generate_DBToolBox_WithOut_ID_MANDANT_AUTOMATIC(MyConnection oConn) {
	
	    MyDBToolBox oOB = null;
	    try {
	        oOB = new MyDBToolBox((oConn==null?bibALL.get_oConnectionNormal():oConn));

	        //2015-05-06: ersetzt durch direkten zugriff
	        //oOB.setZusatzFelder((String[][]) bibALL.getSessionValue("ZUSATZFELDER"));
	        oOB.setZusatzFelder(DB_STATICS.get_DB_ZusatzFelder(	false, 
																		true, 
																		true, 
																		null, 
																		bibALL.get_RECORD_USER().get_KUERZEL_cUF()));
	        
	        oOB.set_ErsetzungTableView((String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT"));
	        oOB.set_bErsetzungTableView(true); // standardmässig wird ersetzt tables gegen views bei JT_tables
	        
	    } catch (myException ex)  {
	    }
	    return oOB;
	}

}

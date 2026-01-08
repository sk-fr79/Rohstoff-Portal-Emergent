package panter.gmbh.indep;

import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletContext;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__ComponentContainer;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DEBUG.DEBUG_FLAGS;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.dataTools.HASHMAP_MyMetaFieldDef;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;

public class bibServer {

	public static String SERVER_KEY_DB_FIELD_TOOLTIPS = 	"SERVER_KEY_DB_FIELD_TOOLTIPS";
	
	//2013-09-26: einen server-platz zu vorhalten von inset-objekten. wird es einmal aufgerufen, dann wird der inset gespeichert
	public static String SERVER_KEY_INSETS  			= 	"SERVER_KEY_INSETS";
	

	//2014-01-03: key im servelt-context fuer die datenbank-Tabellen-Infos
	public static String SERVER_KEY_DB_TABLE_TOOLTIPS = 	"SERVER_KEY_DB_TABLE_TOOLTIPS";
	
	
	
	
	
	/**
	 * methode liest beim ersten aufruf alle Tooltip-Infos aus der Datenbank aus und legt diese im ServletContext ab
	 * @return
	 */
	public static HashMap<String, String>  get_hm_TooltipsFromDB() {
		
		ServletContext oContext = bibE2.get_CurrSession().getServletContext();
		
		@SuppressWarnings("unchecked")
		HashMap<String, String>  hmRueck = (HashMap<String, String>)oContext.getAttribute(bibServer.SERVER_KEY_DB_FIELD_TOOLTIPS);
		
		
		if (hmRueck == null) {
		
			hmRueck = new HashMap<String, String>();
			
			oContext.setAttribute(bibServer.SERVER_KEY_DB_FIELD_TOOLTIPS, hmRueck);
			
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			String[][] cRueck = oDB.EinzelAbfrageInArray("SELECT UPPER(TABLE_NAME),UPPER(COLUMN_NAME),COMMENTS FROM USER_COL_COMMENTS WHERE COMMENTS LIKE '%"+myCONST.KEY_4_TOOLTIPS_EXTRACT_FROM_DB_DESCRIPTION+"%'","",false);
			bibALL.destroy_myDBToolBox(oDB);
			
			if (cRueck != null && cRueck.length>0)
			{
				for (int i=0;i<cRueck.length ;i++) {
					int iPos = cRueck[i][2].indexOf(myCONST.KEY_4_TOOLTIPS_EXTRACT_FROM_DB_DESCRIPTION);
					hmRueck.put(cRueck[i][0]+":"+cRueck[i][1], cRueck[i][2].substring(iPos+myCONST.KEY_4_TOOLTIPS_EXTRACT_FROM_DB_DESCRIPTION.length()));
				}
			}

		}
		
		return hmRueck;
	}
	
	
	

	/**
	 * methode liest beim ersten aufruf alle Tooltip-Infos zu den Taellen aus der Datenbank aus und legt diese im ServletContext ab
	 * @return
	 */
	public static HashMap<String, String>  get_hm_Tooltips4TablesFromDB() {
		
		ServletContext oContext = bibE2.get_CurrSession().getServletContext();
		
		@SuppressWarnings("unchecked")
		HashMap<String, String>  hmRueck = (HashMap<String, String>)oContext.getAttribute(bibServer.SERVER_KEY_DB_TABLE_TOOLTIPS);
		
		
		if (hmRueck == null) {
		
			hmRueck = new HashMap<String, String>();
			
			oContext.setAttribute(bibServer.SERVER_KEY_DB_TABLE_TOOLTIPS, hmRueck);
			
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			String[][] cRueck = oDB.EinzelAbfrageInArray("SELECT UPPER(TABLE_NAME),COMMENTS FROM USER_TAB_COMMENTS WHERE COMMENTS LIKE '%"+myCONST.KEY_4_TOOLTIPS_EXTRACT_FROM_DB_DESCRIPTION+"%'","",false);
			bibALL.destroy_myDBToolBox(oDB);
			
			if (cRueck != null && cRueck.length>0)
			{
				for (int i=0;i<cRueck.length ;i++) {
					int iPos = cRueck[i][1].indexOf(myCONST.KEY_4_TOOLTIPS_EXTRACT_FROM_DB_DESCRIPTION);
					hmRueck.put(cRueck[i][0], cRueck[i][1].substring(iPos+myCONST.KEY_4_TOOLTIPS_EXTRACT_FROM_DB_DESCRIPTION.length()));
				}
			}

		}
		
		return hmRueck;
	}
	

	
	
	
	
	/**
	 * sucht eine evtl. Info fuer Benutzer aus den Field-Descriptions 
	 * @param cTablename
	 * @param cFieldname
	 * @return
	 */
	public static String get_TooltipInfosFromDBDescription(String cTablename, String cFieldname)  {
		String cRueck = null;
		
		//jetzt zuerst im servercontext nachschauen
		HashMap<String, String> hmTooltipsFromDB = bibServer.get_hm_TooltipsFromDB();
		
		String cKey = cTablename+":"+cFieldname;
		
		if (hmTooltipsFromDB.keySet().contains(cKey)) {
			cRueck = hmTooltipsFromDB.get(cKey);
		}
		return cRueck;
	}

	
	
	/**
	 * sucht eine evtl. Info fuer Benutzer aus den Field-Descriptions 
	 * @param cTablename
	 * @param cFieldname
	 * @return
	 */
	public static String get_Tooltips4TablesFromDB(String cTablename)  {
		String cRueck = null;
		
		//jetzt zuerst im servercontext nachschauen
		HashMap<String, String> hmTooltipsFromDB = bibServer.get_hm_Tooltips4TablesFromDB();
		
		String cKey = cTablename.trim().toUpperCase();
		
		if (hmTooltipsFromDB.keySet().contains(cKey)) {
			cRueck = hmTooltipsFromDB.get(cKey);
		}
		return cRueck;
	}


	
	
	
	
	
	/**
	 * @param oComponent
	 * @return gefundenen Tooltip aus der Datenbank (@USER:) oder null
	 */
	public static String get_TooltipInfosFromDBDescription(Component  oComponent) {
		
		String cToolTip = null;
	
		if (oComponent instanceof MyE2IF__DB_Component) {
			if (! (oComponent instanceof MyE2IF__ComponentContainer)) {
				if (! ((MyE2IF__DB_Component)oComponent).get_bIsComplexObject()  ) {
					MyE2IF__DB_Component oDBComp = (MyE2IF__DB_Component) oComponent;
					if (oDBComp.EXT_DB().get_oSQLField() != null) {
						String cTable = oDBComp.EXT_DB().get_oSQLField().get_cTableName();
						String cField = oDBComp.EXT_DB().get_oSQLField().get_cFieldName();
						
						if (S.isFull(cTable) && S.isFull(cField)) {
							cToolTip = bibServer.get_TooltipInfosFromDBDescription(cTable.toUpperCase(), cField.toUpperCase());
						}
					}
				}
			}
		}
		
		//20171122: tooltips sind fehlerhaft ??
//		if (bibALL.get_bDebugMode() ) {
//			if ((oComponent instanceof MyE2IF__DB_Component) && S.isFull(cToolTip) && (((MyE2IF__DB_Component)oComponent).EXT_DB().get_oSQLField()!=null)) {
//				String s = "Comment-gesteuerter Tooltip: Field:"
//						+ "	"+S.NN(((MyE2IF__DB_Component)oComponent).EXT_DB().get_oSQLField().get_cTableName())
//						+"."+S.NN(((MyE2IF__DB_Component)oComponent).EXT_DB().get_oSQLField().get_cFieldName())+":"+cToolTip;
//				DEBUG.System_println(s, DEBUG_FLAGS.MARTINS_EIGENER.name());
//			}
//		}
		return cToolTip;
	}
	
	
	/**
	 * 
	 * @param oComponent
	 * @return gefundenen Tooltip aus der Datenbank (@USER:) oder null
	 */
	public static String get_cTooltipInfosFromDBDescription(MyE2IF__Component  oComponent) {
		
		if (oComponent instanceof Component) {
			return bibServer.get_TooltipInfosFromDBDescription((Component)oComponent);
		}
		return null;
	}

	
	
	
	
	/**
	 * methode liest beim ersten aufruf alle die insets-hashmap aus dem servlet-context 
	 * @return
	 */
	public static HashMap<String, Insets>  get_hm_INSETS() {
		
		ServletContext oContext = bibE2.get_CurrSession().getServletContext();
		
		@SuppressWarnings("unchecked")
		HashMap<String, Insets>  hmRueck = (HashMap<String, Insets>)oContext.getAttribute(bibServer.SERVER_KEY_INSETS);
		
		if (hmRueck == null) {
		
			hmRueck = new HashMap<String, Insets>();
			
			oContext.setAttribute(bibServer.SERVER_KEY_INSETS, hmRueck);
		}
		
		return hmRueck;
	}

	

	
	
	
	
	
	
	
	/**
	 * 2014-12-05: die erste nocht loopback-device-ip-adresse erfragen
	 * @return
	 * @throws myException
	 */
	public static String get_LocalIpAdress() throws myException {
		String cRueck = null;
		
		try {
			Enumeration<NetworkInterface> netInter = NetworkInterface.getNetworkInterfaces();

//			boolean bFound = false;
			
			while ( netInter.hasMoreElements() )
			{
			  NetworkInterface ni = netInter.nextElement();

//			  System.out.println( "NetworkInterface " + n++ + ": " + ni.getDisplayName() );

			  for ( InetAddress iaddress : Collections.list(ni.getInetAddresses()) )
			  {
				  if (iaddress.isSiteLocalAddress()) {
					  if (iaddress.getHostAddress().startsWith("192.168.0")) {
						  cRueck = iaddress.getHostAddress();
						  break;
					  }
				  }
				  
//			    System.out.println( "CanonicalHostName: " +
//			                         iaddress.getCanonicalHostName() );
//
//			    System.out.println( "IP: " + iaddress.getHostAddress() );
//
//			    System.out.println( "Loopback? " + iaddress.isLoopbackAddress() );
//			    System.out.println( "SiteLocal? " + iaddress.isSiteLocalAddress() );
//			    System.out.println();
			  }
			}
		} catch (SocketException e) {
			e.printStackTrace();
			throw new myException(e.getLocalizedMessage());
		}
		
		return cRueck;
		
	}
	
	
	
	
	
	 /**
	    * This method guarantees that garbage collection is
	    * done unlike <code>{@link System#gc()}</code>
	    */
	   public static void ownGc() {
	     Object obj = new Object();
	     WeakReference<Object> ref = new WeakReference<Object>(obj);
	     obj = null;
	     while(ref.get() != null) {
	       System.gc();
	     }
	     System.runFinalization();
	   }
}

package panter.gmbh.Echo2;


import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import nextapp.echo2.webcontainer.ContainerContext;
import nextapp.echo2.webcontainer.command.BrowserSetCookieCommand;
import panter.gmbh.Echo2.ActionEventTools.E2_Refreshable;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.E2_TabbedPaneForFirstContainer;
import panter.gmbh.basics4project.sessionContainer;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECLIST_USER;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEHRUNG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;


public class bibE2
{
	/*
	 * Funktionalität für das Setzen der Session ausserhalb von Echo, damit man auf die Echo-Methoden zugreifen kann
	 */
	private static sessionContainer m_sessionContainer = new sessionContainer();
	public static void setSessionManually(HttpSession ses) throws Exception{
		m_sessionContainer.setSession(ses);
	}
	
	public static HttpSession getSessionManually(){
		return m_sessionContainer.getSession();
	}
	/**
	 * 
	 */
	
	
	   
    /**
     * rueckgabe der aktiven ApplicationInstance 
     */
    public static ApplicationInstance  get_ActiveInstance()
    {
        return ApplicationInstance.getActive();
//        return getActive();
    }
    
    public static String get_ApplicationPath()
    {
    	//System.out.println(bibE2.get_CurrSession().getServletContext().getRealPath(""));
    	return bibE2.get_CurrSession().getServletContext().getRealPath("");
    }
    
    /**
     * @return HttpSession
     */
    public static HttpSession get_CurrSession()
    {
    	HttpSession ses = null;
        try {
			ContainerContext containerContext = (ContainerContext)  bibE2.get_ActiveInstance().getContextProperty(ContainerContext.CONTEXT_PROPERTY_NAME);
			ses = containerContext.getSession();
		} catch (Exception e) {
			// versuchen, ob man die Session bekommt, die manuell gesetzt werden kann
			ses = bibE2.getSessionManually();
		}
		
        return ses;
     }
    

    /**
     * @return HttpSession
     */
    public static HttpSession get_CurrSession(ApplicationInstance oInstance)
    {
    	HttpSession ses = null;
    	
        try {
			ContainerContext containerContext = (ContainerContext)  oInstance.getContextProperty(ContainerContext.CONTEXT_PROPERTY_NAME);
			ses = containerContext.getSession();
		} catch (Exception e) {
			ses = bibE2.getSessionManually();
		}
        
        return ses;
     }

    
    
    
    /**
     * @return HttpSession
     */
    public static ContainerContext get_ContainerContext()
    {
        ContainerContext containerContext = (ContainerContext)  bibE2.get_ActiveInstance().getContextProperty(ContainerContext.CONTEXT_PROPERTY_NAME);
        return containerContext;
     }

    

    
    
	
	
	public static String get_DB_KENNUNG()
	{
		return  (String)bibALL.getSessionValue("DBKENNUNG");   
	}
	
	

	
	// wrapper-methoden um session einzupacken, damit bei mehreren servlet-applications
	// keine zufälligen namenskollisionen stattfinden
	// statische methode, um die 2 vectoren __JIL_NAMEN
	//                             und      __JIL_OBJECTS azszulesen
	public static void setSessionValue(String cName, Object oObject)
	{
	    bibALL.setSessionValue(cName,oObject);
	}

	
	
	// statische methode, um die 2 vectoren __JIL_NAMEN
	//                             und      __JIL_OBJECTS auszulesen
	public static Object getSessionValue(String cName)
	{
	    return bibALL.getSessionValue(cName);
	}

	
	public static String cTO()
	{
		return bibALL.get_TABLEOWNER();
	}

	
	
	public static Vector<E2_Refreshable>  get_vRefreshables()
	{
		Vector<E2_Refreshable>  vRefreshables = (Vector<E2_Refreshable>)bibE2.getSessionValue("___REFRESHABLES_@@@@@");

		if (vRefreshables == null)
		{
			bibE2.setSessionValue("___REFRESHABLES_@@@@@", new Vector<E2_Refreshable>());
			vRefreshables = (Vector<E2_Refreshable>)bibE2.getSessionValue("___REFRESHABLES_@@@@@");
		}
		
		return vRefreshables;
	}
	
	
	
	/**
	 * @param cTableName
	 * @return SELECT ..NEXTVAL FROM DUAL
	 */
	public static String get_SQL_SEQUENCE(String cTableName)
	{
		String cRueck = "";
		cRueck="SELECT "+bibALL.get_TABLEOWNER()+".SEQ"+cTableName.substring(2)+".NEXTVAL FROM DUAL";
		return cRueck;
	}
	
	/**
	 * @return :ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:
	 */
	public static String get_FIELDLIST_EXCLUDE()
	{
		return ":ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:";
	}
	
	
	/**
	 * @return :ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:
	 */
	public static Vector<String> get_FIELDVECTOR_EXCLUDE()
	{
		return bibALL.get_Vector("ID_MANDANT","GEAENDERT_VON","LETZTE_AENDERUNG");
	}
	


	
	/**
	 * @returns array for dropdowns mit ja nein, is translated, with empty selektor
	 */
	public static String[][] get_YN_Ary_WithLeer()
	{
	    String[][] cYesNo = new String[3][2];
	    
	    cYesNo[0][0] = "-";
	    cYesNo[0][1] = "";
	    cYesNo[1][0] = new MyE2_String("Ja").CTrans();
	    cYesNo[1][1] = "Y";
	    cYesNo[2][0] = new MyE2_String("Nein").CTrans();
	    cYesNo[2][1] = "N";
	
	    return cYesNo;
	}

//	
//	/*
//	 * holt eine liste der aktiven benutzer-mail-adressen aus der datenbank und speichert diese in der session,
//	 * da eine mehrfachabfrage in den massenmailern zu lange dauert
//	 */
//	@SuppressWarnings("unchecked")
//	public static Vector<String> get_activMitarbeiterEmails() throws myException
//	{
//		Vector<String> vRueck = new Vector<String>();
//		
//		if (bibE2.getSessionValue("___MITARBEITER_MAIL_ACTIVE")==null)
//		{
//			BASIC_RECLIST_USER  oRecList = new BASIC_RECLIST_USER("SELECT * FROM "+bibE2.cTO()+".JD_USER WHERE NVL(AKTIV,'N')='Y' AND ID_MANDANT="+bibALL.get_ID_MANDANT()+" ORDER BY NAME1");
//		
//			for (int i=0;i<oRecList.get_vKeyValues().size();i++)
//			{
//				BASIC_RECORD_USER oRec = oRecList.get(i);
//				
//				//2013-01-15: mitarbeiter-eMails nur zurueckgeben, wenn es keine offensichtliche falschadresse ist
////				if (S.isFull(oRec.get_EMAIL_cUF_NN("")))
//				if (S.isFull(oRec.get_EMAIL_cUF_NN("")) && oRec.get_EMAIL_cUF_NN("").contains("@"))
//				{
//					vRueck.add(oRec.get_EMAIL_cUF_NN(""));
//				}
//			}
//			
//			bibE2.setSessionValue("___MITARBEITER_MAIL_ACTIVE",vRueck);
//		}
//		return (Vector<String>)bibE2.getSessionValue("___MITARBEITER_MAIL_ACTIVE");
//	}
//	
	
	
	/*
	 * holt eine liste der aktiven benutzer-mail-adressen aus der datenbank und speichert diese in der session,
	 * da eine mehrfachabfrage in den massenmailern zu lange dauert
	 */
	@SuppressWarnings("unchecked")
	public static Vector<String> get_activMitarbeiterEmails() throws myException
	{
		//2013-04-09: mail-adressen nur einmal zulassen
		VectorSingle vRueck = new VectorSingle();
		
		if (bibE2.getSessionValue("___MITARBEITER_MAIL_ACTIVE")==null)
		{
			BASIC_RECLIST_USER  oRecList = new BASIC_RECLIST_USER("SELECT * FROM "+bibE2.cTO()+".JD_USER WHERE NVL(AKTIV,'N')='Y' AND ID_MANDANT="+bibALL.get_ID_MANDANT()+" ORDER BY NAME1");
		
			for (int i=0;i<oRecList.get_vKeyValues().size();i++)
			{
				BASIC_RECORD_USER oRec = oRecList.get(i);
				
				//2013-01-15: mitarbeiter-eMails nur zurueckgeben, wenn es keine offensichtliche falschadresse ist
//				if (S.isFull(oRec.get_EMAIL_cUF_NN("")))
				if (S.isFull(oRec.get_EMAIL_cUF_NN("")) && oRec.get_EMAIL_cUF_NN("").contains("@") && !oRec.get_EMAIL_cUF_NN("").startsWith("@") && !oRec.get_EMAIL_cUF_NN("").endsWith("@") )
				{
					vRueck.add(oRec.get_EMAIL_cUF_NN(""));
				}
			}
			
			//jetzt noch sortieren
			Collections.sort(vRueck);
			
			bibE2.setSessionValue("___MITARBEITER_MAIL_ACTIVE",vRueck);
		}
		return (Vector<String>)bibE2.getSessionValue("___MITARBEITER_MAIL_ACTIVE");
	}
	
	
	
	
	
	/**
	 * @return last used popup-window
	 */
	@SuppressWarnings("unchecked")
	public static E2_BasicModuleContainer get_LASTPOPUP_CONTAINER()
	{

		WeakReference<E2_BasicModuleContainer>  oRef = (WeakReference<E2_BasicModuleContainer>) bibALL.getSessionValue("LASTPOPUPWINDOW");
		if (oRef != null)
		{
			if (oRef.get() != null)
			{
				return oRef.get();
			}
		}
	    return null;
	}

	
	
	public static void set_LASTPOPUP_CONTAINER(E2_BasicModuleContainer oContainer)
	{
		WeakReference<E2_BasicModuleContainer>  oRef = new WeakReference<E2_BasicModuleContainer>(oContainer);
		bibALL.setSessionValue("LASTPOPUPWINDOW",oRef);
	}
	


	/**
	 * @param oSES
	 * @return last used popup-window
	 */
	@SuppressWarnings("unchecked")
	public static E2_BasicModuleContainer get_LASTMESSAGE_CONTAINER()
	{

		WeakReference<E2_BasicModuleContainer>  oRef = (WeakReference<E2_BasicModuleContainer>) bibALL.getSessionValue("LAST_MESSAGE_CONTAINER");
		if (oRef != null)
		{
			if (oRef.get() != null)
			{
				return oRef.get();
			}
		}
	    return null;
	}

	
	
	public static void set_LAST_MESSAGE_CONTAINER(E2_BasicModuleContainer oContainer)
	{
		WeakReference<E2_BasicModuleContainer>  oRef = new WeakReference<E2_BasicModuleContainer>(oContainer);
		bibALL.setSessionValue("LAST_MESSAGE_CONTAINER",oRef);
	}
	
	
	
	/*
	 * 2012-06-20: singulaerer speicherplatz fuer deleted-fonts in listen 
	 */
	public static Font  get_Font4DeletedLinesInLists()
	{
		Font oFontRueck = (Font)bibALL.getSessionValue("@@##--font4-deleted-listrows##@");
		
		if (oFontRueck==null)
		{
			oFontRueck = new E2_Font(Font.ITALIC+Font.LINE_THROUGH,-2);
			bibALL.setSessionValue("@@##--font4-deleted-listrows##@", oFontRueck);
		}
		
		return oFontRueck;
	}
	
	
	/*
	 * 2012-06-20: singulaerer speicherplatz fuer normal-fonts in listen 
	 */
	public static Font  get_Font4NormalLists()
	{
		Font oFontRueck = (Font)bibALL.getSessionValue("@@##--font4-normal-listrows##@");
		
		if (oFontRueck==null)
		{
			oFontRueck = new E2_FontPlain();
			bibALL.setSessionValue("@@##--font4-normal-listrows##@", oFontRueck);
		}
		
		return oFontRueck;
	}
	


	/*
	 * 2012-06-20: singulaerer speicherplatz fuer inaktiv-color in listen 
	 */
	public static Color  get_Color4InactiveLinesInLists()
	{
		Color oColorRueck = (Color)bibALL.getSessionValue("@@##--Color-inaktiv-listrows##@");
		
		if (oColorRueck==null)
		{
			oColorRueck = Color.DARKGRAY;
			bibALL.setSessionValue("@@##--Color-inaktiv-listrows##@", oColorRueck);
		}
		
		return oColorRueck;
	}
	
	

	
	
	/**
	 * @return last used ActionEvent
	 */
	public static MyActionEvent get_LAST_ACTIONEVENT()
	{
		MyActionEvent  oEvent = (MyActionEvent) bibALL.getSessionValue("LAST_ACTIONEVENT");
	    return oEvent;
	}

	
	
	public static void set_LAST_ACTIONEVENT(MyActionEvent oEvent)
	{
		bibALL.setSessionValue("LAST_ACTIONEVENT",oEvent);
	}
	
	

	/**
	 * @return last used ActionEvent
	 * kann benutzt werden fuer messagehandler, falls mehrere folgende popups vorhanden waren, die nicht mehr vorhanden sind
	 * koennen diese in den startcontainer eingetragen werden
	 */
	@SuppressWarnings("unchecked")
	public static E2_BasicModuleContainer get_LAST_StartContainer_4_DBTimeStamps()
	{
		WeakReference<E2_BasicModuleContainer>  oRef = (WeakReference<E2_BasicModuleContainer>) bibALL.getSessionValue("LAST_STARTCONTAINER_4_TIMESTAMP");
		if (oRef != null)
		{
			if (oRef.get() != null)
			{
				return oRef.get();
			}
		}
	    return null;	
	 }

	
	
	public static void set_StartContainer_4_DBTimeStamps(E2_BasicModuleContainer oContainer)
	{
		WeakReference<E2_BasicModuleContainer> wrContainer = new WeakReference<E2_BasicModuleContainer>(oContainer);
		bibALL.setSessionValue("LAST_STARTCONTAINER_4_TIMESTAMP",wrContainer);
	}
	

	
	
	
	

	/**
	 * @param oSES
	 * @return last used ActionEvent
	 */
	public static String get_LAST_DB_TIMESTAMP()
	{
		String  cTimeStamp = (String) bibALL.getSessionValue("LAST_DB_TIMESTAMP");
	    return cTimeStamp;
	}

	
	
	public static void set_LAST_DB_TIMESTAMP(String cStamp)
	{
		bibALL.setSessionValue("LAST_DB_TIMESTAMP",cStamp);
	}
	
	
	

	
	
	
	
	/**
	 * @param oPane
	 * speichern des alleresten ContentPane
	 */
	public static void SET_FIRST_CONTENTPANE_IN_SESSION(E2_ContentPane oPane)
	{
		bibALL.setSessionValue("STARTINGCONTENTPANE",oPane);
	}

	/**
	 * @param
	 * holt den basis-contentpane
	 */
	public static E2_ContentPane GET_FIRST_CONTENTPANE_IN_SESSION()
	{
		return (E2_ContentPane)bibALL.getSessionValue("STARTINGCONTENTPANE");
	}

	

	/**
	 * @param oPane
	 * speichern des Programm-Tabbed-Panes
	 */
	public static void STORE_PROGRAMM_TABBEDPANE_IN_SESSION(E2_TabbedPaneForFirstContainer ocontainerTabbedPaneFirst)
	{
		bibALL.setSessionValue("PROGRAMM_MODULE_TABBED_PANE",ocontainerTabbedPaneFirst);
	}

	/**
	 * @param
	 * holt den Programm-Tabbed-Panes
	 */
	public static E2_TabbedPaneForFirstContainer GET_PROGRAMM_TABBEDPANE_IN_SESSION()
	{
		return (E2_TabbedPaneForFirstContainer)bibALL.getSessionValue("PROGRAMM_MODULE_TABBED_PANE");
	}


	
	
//	
//	/**
//	 * @return s globlen messageagent, der die messages in einem popup-fenster anzeigt
//	 */
//	public static MessageAgent_InPOPUP get_MessageAgent_InPOPUP()
//	{
//		MessageAgent_InPOPUP oMessageAgent = (MessageAgent_InPOPUP)bibALL.getSessionValue("GLOBALMESSAGEAGENT");
//		
//		if (oMessageAgent==null)
//		{
//			oMessageAgent = new MessageAgent_InPOPUP(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION());
//			bibALL.setSessionValue("GLOBALMESSAGEAGENT",oMessageAgent);
//		}
//		
//		return oMessageAgent;
//	}
//	
	
//	public static Map get_InitialParameterMap()
//	{
//		ApplicationInstance app = ApplicationInstance.getActive();
//        ContainerContext containerContext 
//                = (ContainerContext) app.getContextProperty(ContainerContext.CONTEXT_PROPERTY_NAME);
//        Map parameters = containerContext.getInitialRequestParameterMap();
//		
//        return parameters;
//	}
	

	public static String get_cBASISWAEHRUNG_SYMBOL() throws myException
	{
	    return new RECORD_WAEHRUNG(  new Long(  bibALL.get_ID_BASISWAEHRUNG() ).longValue() ).get_WAEHRUNGSSYMBOL_cF_NN("");
	}


	
	
	//methoden, um styles fuer labels in der Session in einem Cash zu halten
	@SuppressWarnings("unchecked")
	public static  E2_MutableStyle get_MutableStyle4Labels(String StyleKEY)
	{
		HashMap<String, E2_MutableStyle> oHashCache = (HashMap<String, E2_MutableStyle>)bibALL.getSessionValue("___LabelStyle_Cache___");
		
		if (oHashCache==null)
		{
			oHashCache = new HashMap<String, E2_MutableStyle>();
			bibALL.setSessionValue("___LabelStyle_Cache___", oHashCache);
		}

		return oHashCache.get(StyleKEY);
	}
	

	@SuppressWarnings("unchecked")
	public static void set_MutableStyle4Labels(String StyleKEY, E2_MutableStyle oStyle)
	{
		HashMap<String, E2_MutableStyle> oHashCache = (HashMap<String, E2_MutableStyle>)bibALL.getSessionValue("___LabelStyle_Cache___");
		
		if (oHashCache==null)
		{
			oHashCache = new HashMap<String, E2_MutableStyle>();
			bibALL.setSessionValue("___LabelStyle_Cache___", oHashCache);
		}

		oHashCache.put(StyleKEY,oStyle);
	}
	
	
	
	
//	//methoden, um styles fuer labels in der Session in einem Cash zu halten
//	@SuppressWarnings("unchecked")
//	public static  E2_MutableStyle get_MutableStyle4TextFields(String StyleKEY)
//	{
//		HashMap<String, E2_MutableStyle> oHashCache = (HashMap<String, E2_MutableStyle>)bibALL.getSessionValue("___TextfieldStyle_Cache___");
//		
//		if (oHashCache==null)
//		{
//			oHashCache = new HashMap<String, E2_MutableStyle>();
//			bibALL.setSessionValue("___TextfieldStyle_Cache___", oHashCache);
//		}
//
//		return oHashCache.get(StyleKEY);
//	}
//	
//
//	@SuppressWarnings("unchecked")
//	public static void set_MutableStyle4TextFields(String StyleKEY, E2_MutableStyle oStyle)
//	{
//		HashMap<String, E2_MutableStyle> oHashCache = (HashMap<String, E2_MutableStyle>)bibALL.getSessionValue("___TextfieldStyle_Cache___");
//		
//		if (oHashCache==null)
//		{
//			oHashCache = new HashMap<String, E2_MutableStyle>();
//			bibALL.setSessionValue("___TextfieldStyle_Cache___", oHashCache);
//		}
//
//		oHashCache.put(StyleKEY,oStyle);
//	}
//	
	
	
	
	
	
	public static int[] I(int i0,int i1)
	{
		int[] iRueck = new int[2];
		iRueck[0]=i0;
		iRueck[1]=i1;
		return iRueck;
	}
	
	public static int[] I(int i0,int i1, int i2)
	{
		int[] iRueck = new int[3];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		return iRueck;
	}
	
	public static int[] I(int i0,int i1, int i2, int i3)
	{
		int[] iRueck = new int[4];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		return iRueck;
	}
	
	public static int[] I(int i0,int i1, int i2, int i3, int i4)
	{
		int[] iRueck = new int[5];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		return iRueck;
	}

	
	
	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5)
	{
		int[] iRueck = new int[6];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		return iRueck;
	}

	
	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5, int i6)
	{
		int[] iRueck = new int[7];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		iRueck[6]=i6;
		return iRueck;
	}

	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5, int i6, int i7)
	{
		int[] iRueck = new int[8];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		iRueck[6]=i6;
		iRueck[7]=i7;
		return iRueck;
	}

	
	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8)
	{
		int[] iRueck = new int[9];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		iRueck[6]=i6;
		iRueck[7]=i7;
		iRueck[8]=i8;
		return iRueck;
	}

	
	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9)
	{
		int[] iRueck = new int[10];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		iRueck[6]=i6;
		iRueck[7]=i7;
		iRueck[8]=i8;
		iRueck[9]=i9;
		return iRueck;
	}

	
	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10)
	{
		int[] iRueck = new int[11];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		iRueck[6]=i6;
		iRueck[7]=i7;
		iRueck[8]=i8;
		iRueck[9]=i9;
		iRueck[10]=i10;
		return iRueck;
	}

	
	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11)
	{
		int[] iRueck = new int[12];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		iRueck[6]=i6;
		iRueck[7]=i7;
		iRueck[8]=i8;
		iRueck[9]=i9;
		iRueck[10]=i10;
		iRueck[11]=i11;
		return iRueck;
	}

	
	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12)
	{
		int[] iRueck = new int[13];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		iRueck[6]=i6;
		iRueck[7]=i7;
		iRueck[8]=i8;
		iRueck[9]=i9;
		iRueck[10]=i10;
		iRueck[11]=i11;
		iRueck[12]=i12;
		return iRueck;
	}

	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13)
	{
		int[] iRueck = new int[14];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		iRueck[6]=i6;
		iRueck[7]=i7;
		iRueck[8]=i8;
		iRueck[9]=i9;
		iRueck[10]=i10;
		iRueck[11]=i11;
		iRueck[12]=i12;
		iRueck[13]=i13;
		return iRueck;
	}
	
	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14)
	{
		int[] iRueck = new int[15];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		iRueck[6]=i6;
		iRueck[7]=i7;
		iRueck[8]=i8;
		iRueck[9]=i9;
		iRueck[10]=i10;
		iRueck[11]=i11;
		iRueck[12]=i12;
		iRueck[13]=i13;
		iRueck[14]=i14;
		return iRueck;
	}
	
	
	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15)
	{
		int[] iRueck = new int[16];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		iRueck[6]=i6;
		iRueck[7]=i7;
		iRueck[8]=i8;
		iRueck[9]=i9;
		iRueck[10]=i10;
		iRueck[11]=i11;
		iRueck[12]=i12;
		iRueck[13]=i13;
		iRueck[14]=i14;
		iRueck[15]=i15;
		return iRueck;
	}
	
	
	
	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16)
	{
		int[] iRueck = new int[17];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		iRueck[6]=i6;
		iRueck[7]=i7;
		iRueck[8]=i8;
		iRueck[9]=i9;
		iRueck[10]=i10;
		iRueck[11]=i11;
		iRueck[12]=i12;
		iRueck[13]=i13;
		iRueck[14]=i14;
		iRueck[15]=i15;
		iRueck[16]=i16;
		return iRueck;
	}
	
	
	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17)
	{
		int[] iRueck = new int[18];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		iRueck[6]=i6;
		iRueck[7]=i7;
		iRueck[8]=i8;
		iRueck[9]=i9;
		iRueck[10]=i10;
		iRueck[11]=i11;
		iRueck[12]=i12;
		iRueck[13]=i13;
		iRueck[14]=i14;
		iRueck[15]=i15;
		iRueck[16]=i16;
		iRueck[17]=i17;
		return iRueck;
	}

	

	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18)
	{
		int[] iRueck = new int[19];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		iRueck[6]=i6;
		iRueck[7]=i7;
		iRueck[8]=i8;
		iRueck[9]=i9;
		iRueck[10]=i10;
		iRueck[11]=i11;
		iRueck[12]=i12;
		iRueck[13]=i13;
		iRueck[14]=i14;
		iRueck[15]=i15;
		iRueck[16]=i16;
		iRueck[17]=i17;
		iRueck[18]=i18;
		return iRueck;
	}


	public static int[] I(int i0,int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19)
	{
		int[] iRueck = new int[20];
		iRueck[0]=i0;
		iRueck[1]=i1;
		iRueck[2]=i2;
		iRueck[3]=i3;
		iRueck[4]=i4;
		iRueck[5]=i5;
		iRueck[6]=i6;
		iRueck[7]=i7;
		iRueck[8]=i8;
		iRueck[9]=i9;
		iRueck[10]=i10;
		iRueck[11]=i11;
		iRueck[12]=i12;
		iRueck[13]=i13;
		iRueck[14]=i14;
		iRueck[15]=i15;
		iRueck[16]=i16;
		iRueck[17]=i17;
		iRueck[18]=i18;
		iRueck[19]=i19;
		return iRueck;
	}


	
	
	public static void set_Cookie(String cName, String cValue)
	{
		
		
	   Cookie Plaetzchen = new Cookie(cName, cValue);
	   Plaetzchen.setMaxAge(604800);                       //lebt eine woche
		
 	   ApplicationInstance.getActive().enqueueCommand(
			   new BrowserSetCookieCommand(Plaetzchen));

	}

	
	public static String get_Cookie(String cName)
	{
		String cRueck = null;
		
		Cookie[] arrayCookies  = bibE2.get_ContainerContext().getCookies();
		for (int i=0;i<arrayCookies.length;i++)
		{
			if(arrayCookies[i].getName().equals(cName))
			{
				cRueck = arrayCookies[i].getValue();
			}
		}
		
		return cRueck;

	}
	
	
	
	public static void DEBUG_AUSGABE_HASHKEYS(HashMap  hmOut)
	{
		Vector<String> vKeys= bibALL.get_vBuildKeyVectorFromHashmap(hmOut);
		
		for (String c: vKeys)
		{
			System.out.println(c);
		}
		
		
		
	}
	
	
	
	
	public static boolean get_bIsModuleAllowed4ThisUser(String cModuleName) 
	{
		if (S.isFull(cModuleName))
		{
			HashMap<String, String> hmMenues = bibSES.get_ALLOWED_MENUES_4_USER();
			if (hmMenues.containsKey("echo2_starter:"+cModuleName))
			{
				return true;
			}
		}
		return false;

	}
	
	public static String get_TabNameFromModulKenner(String cModuleName) 
	{
		if (S.isFull(cModuleName))
		{
			HashMap<String, String> hmMenues = bibSES.get_ALLOWED_MENUES_4_USER();
			if (hmMenues.containsKey("echo2_starter:"+cModuleName))
			{
				return hmMenues.get("echo2_starter:"+cModuleName);
			}
		}		
		return "<unbekanntes Module>";

	}
	

	
//	public static void removeAllMessageWindowsFromBasicContentPane()
//	{
//		E2_ContentPane  oFirstPane = bibE2.GET_FIRST_CONTENTPANE_IN_SESSION();
//		
//		Component[] comps = oFirstPane.getComponents();
//		
//		for (int i=0;i<comps.length;i++)
//		{
//			if (comps[i] instanceof E2_MessageWindow)
//			{
//				oFirstPane.remove(comps[i]);
//			}
//		}
//		
//	}
	
	
//	//neue methode zum holen einer session-gespeicherten window-objekt
//	public static E2_MessageWindow get_MessageWindow()
//	{
////		String SessionKey_4_MessageWindow = "PUBLIC_MESSAGE_WINDOW_POPUP";
////		
////		E2_MessageWindow oWindow = (E2_MessageWindow) bibALL.getSessionValue(SessionKey_4_MessageWindow);
//		
////		if (oWindow==null)
////		{
//			E2_MessageWindow oWindow = new E2_MessageWindow();
////			bibALL.setSessionValue(SessionKey_4_MessageWindow, oWindow);
////			bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().add(oWindow);
////		}
//		
//		return oWindow;
//	}
	
	
	public ServletContext getServletContext() {
		return bibE2.get_CurrSession().getServletContext();
	}
	
	
	
	
	public static E2_ResourceIcon getIcon(String name) {
		return E2_ResourceIcon.get_RI(name);
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 16.12.2019
	 *
	 * @return s sessionId
	 */
	public static String getSessionId() {
		ContainerContext containerContext = (ContainerContext) ApplicationInstance.getActive().getContextProperty(ContainerContext.CONTEXT_PROPERTY_NAME);
		HttpSession ses = containerContext.getSession();
		return ses.getId();
	}
	
	
}


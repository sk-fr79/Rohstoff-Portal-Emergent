/*
 * Created on 27.01.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.Echo2.AgentsAndValidators;


import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/*
 * validator-klasse, die nur einen Buttons-String benoetigt und sich den zugehoerigen
 * Container-kenner selbst sucht
 */
public class E2_ButtonAUTHValidator_AUTO extends XX_ActionValidator
{
	
	private String	    cAUTHBUTTON = "";
//	private Component   o_CompReference = null;

	/**
	 * 
	 * @param cButtonKenner
	 */
	public E2_ButtonAUTHValidator_AUTO(String	cButtonKenner)
	{
		this.cAUTHBUTTON = bibALL.null2leer(cButtonKenner);
	}


//	/**
//	 * 
//	 * @param cButtonKenner 
//	 * @param oCompReference (falls die validierung des buttons aus einem Popup einem anderen E2_BasicModulContainer zugewiesen werden soll)
//	 *                       (Beispiel: Validierung eines bestätigungsbuttons aus einem Popup-MessageContainer)
//	 */
//	public E2_ButtonAUTHValidator_AUTO(String	cButtonKenner, Component oCompReference) {
//		this.cAUTHBUTTON = bibALL.null2leer(cButtonKenner);
//		this.o_CompReference = oCompReference;
//	}
//	
	
	

	public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();

//		String[][] cAllowed     = bibALL.get_ALLOWEDBUTTONS();
//		boolean    bAllowed     = false;
//		boolean    bButtonFound = false;
//
//		// es gibt einen Eintrag, der einen benutzer für alle autentifizierungen dieser maske 
//		// zulässt, dieser muss, wenn nicht gefunden, auch einmal geschrieben werden
//		boolean bSuperEntryFound = false;
//
		
		
		
		
		String cAUTH_MODULE_KENNER = null;
		
		E2_RecursiveSearchParent_BasicModuleContainer oRCS  = null;
		
		oRCS = new E2_RecursiveSearchParent_BasicModuleContainer(oCompWhichIsValidated);
		
		E2_BasicModuleContainer oContainerParentWithMODULE_KENNER = oRCS.get_First_FoundContainer_With_MODUL_IDENTIFIER();
		E2_BasicModuleContainer oContainerParentWithMODULE_KENNER_OF_CALLING_BasicModuleContainer = oRCS.get_First_FoundContainer_With_MODUL_IDENTIFIER_FROM_CALLING_MODULE();    //beim mehrfachen popup unbenannter E2_BasicModulContainer
		if (oContainerParentWithMODULE_KENNER == null)
		{
			if (oContainerParentWithMODULE_KENNER_OF_CALLING_BasicModuleContainer !=null)    //dann nachsehen, ob es ueberhaupt eine BasicModuleContainer gibt, und ob der ein popup-container ist, d.h. einen rufenden Modulkenner in sich traegt
			{
				cAUTH_MODULE_KENNER = oContainerParentWithMODULE_KENNER_OF_CALLING_BasicModuleContainer.get_MODUL_IDENTIFIER_OF_CALLING_BasicModuleContainer();
			}
		}
		else
		{
			cAUTH_MODULE_KENNER = oContainerParentWithMODULE_KENNER.get_MODUL_IDENTIFIER();
		}
		
		
		if (cAUTH_MODULE_KENNER==null)
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String( "Diese Funktion ist hier nicht möglich !")));
		}
		else
		{
			//2015-05-20: auslagerung der datenbank-ops in eigene klasse
			oMV.add_MESSAGE(new E2_ButtonAUTHValidator_Helper(cAUTH_MODULE_KENNER,this.cAUTHBUTTON,true).get_oMV());
			
//			
//			
//			//2015-05-19: debug-info
//			try {
//				if (bibSES.get_RECORD_USER().is_DEVELOPER_YES()) {
//					bibMSG.add_MESSAGE(new MyE2_Info_Message("DEV.Info:Modul(Auto):"+cAUTH_MODULE_KENNER+":Button:"+this.cAUTHBUTTON));
//				}
//			} catch (myException e) {e.printStackTrace();
//			}
//			
//			
//			// falls der validator unsauber definiert wurde, KEIN zugriff
//			if (this.cAUTHBUTTON.trim().equals("") || cAUTH_MODULE_KENNER.trim().equals(""))
//			{
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String( "Sie haben keine Berechtigung für diese Funktion:")));
//			}
//			else
//			{
//				if (cAllowed != null)
//				{
//					for (int i = 0; i < cAllowed.length; i++)
//					{
//						if (cAUTH_MODULE_KENNER.equals(cAllowed [i] [0]))
//						{
//		
//							if (cAllowed [i] [1].equals("@@@ALL@@@")) // supereintrittskarte für das modul
//							{
//								bSuperEntryFound = true;
//		
//								if (cAllowed [i] [2].equals("Y"))
//									bAllowed = true;
//								
//							}
//		
//							if (this.cAUTHBUTTON.equals(cAllowed [i] [1]))
//							{
//								bButtonFound = true;
//		
//								if (cAllowed [i] [2].equals("Y"))
//									bAllowed = true; // dann darf er
//		
//							}
//						}
//					}
//				}
//		
//		
//				// jetzt, falls die button/modul-kombination noch nicht verwendet wurde, in die datenbank schreiben
//				if (!bButtonFound)
//				{
//					MyDBToolBox oDB     = bibALL.get_myDBToolBox();
//					
//					// die ersetzung table / view muss abgeschaltet werden, da 
//					// in dem where-bereich die werte JT_xxx enthalten, was fälschlicherweise ersetzt würde
//					oDB.set_bErsetzungTableView(false);
//					
//					String		 cInsert = "INSERT INTO " + bibALL.get_TABLEOWNER() + ".JD_BUTTON (ID_BUTTON,MODULKENNER,BUTTONKENNER) VALUES (SEQ_BUTTON.NEXTVAL," + bibALL.MakeSql(cAUTH_MODULE_KENNER) + "," + bibALL.MakeSql(this.cAUTHBUTTON) + ")";
//		
//					String cCount = oDB.EinzelAbfrage("SELECT COUNT(*) FROM " + bibALL.get_TABLEOWNER() + ".JD_BUTTON " + " WHERE MODULKENNER=" + bibALL.MakeSql(cAUTH_MODULE_KENNER) + " AND BUTTONKENNER =" + bibALL.MakeSql(this.cAUTHBUTTON));
//		
//					if (cCount.trim().equals("0")) // sonst war der knopf schon drin	
//					{
//						if (!oDB.ExecSQL(cInsert,true))
//						{
//							bibALL.WriteLogInfoLine(this.getClass().getName()+"Fehler beim Schreiben eine Button/Modul-Kenner-Kombination (button)");
//							bibALL.WriteLogInfoLine("      "+cInsert);
//						}
//						else
//						{
//							bibALL.WriteLogInfoLine("Zugriff in Datenbank geschrieben: " + cAUTH_MODULE_KENNER + " / " + this.cAUTHBUTTON);
//						}
//					}
//					oDB.set_bErsetzungTableView(true);
//					bibALL.destroy_myDBToolBox(oDB);
//				}
//		
//				if (!bSuperEntryFound)
//				{
//					MyDBToolBox oDB     = bibALL.get_myDBToolBox();
//					oDB.set_bErsetzungTableView(false);
//					String		 cInsert = "INSERT INTO " + bibALL.get_TABLEOWNER() + ".JD_BUTTON (ID_BUTTON,MODULKENNER,BUTTONKENNER) VALUES (SEQ_BUTTON.NEXTVAL," + bibALL.MakeSql(cAUTH_MODULE_KENNER) + ",'@@@ALL@@@')";
//		
//					String cSQL_anzahl = "SELECT COUNT(*) FROM " + bibALL.get_TABLEOWNER() + ".JD_BUTTON " + " WHERE MODULKENNER=" + bibALL.MakeSql(cAUTH_MODULE_KENNER) + " AND BUTTONKENNER ='@@@ALL@@@'";
//		
//					String cCount = oDB.EinzelAbfrage(cSQL_anzahl);
//		
//					if (cCount.trim().equals("0")) // sonst war der knopf schon drin	
//					{
//						if (!oDB.ExecSQL(cInsert,true))
//						{
//							bibALL.WriteLogInfoLine(this.getClass().getName()+"Fehler beim Schreiben eine Button/Modul-Kenner-Kombination (all)");
//							bibALL.WriteLogInfoLine("      "+cInsert);
//						}
//						else
//						{
//							bibALL.WriteLogInfoLine("Zugriff in Datenbank geschrieben: " + cAUTH_MODULE_KENNER + " / " + this.cAUTHBUTTON);
//						}
//					}
//					oDB.set_bErsetzungTableView(true);
//					bibALL.destroy_myDBToolBox(oDB);
//				}
//	
//			}
//			// ganz am schluss prüfen, ob der user supervisor ist
//			if (bibALL.get_bIST_SUPERVISOR())
//			{
//				return new MyE2_MessageVector();
//			}
//	
//			if (!bAllowed)
//			{
//				MyString cHelp = new MyString( "Sie haben keine Berechtigung für diese Funktion:");
//				cHelp.addUnTranslated(cAUTH_MODULE_KENNER+" / "+this.cAUTHBUTTON);
//	
//				oMV.add_MESSAGE(new MyE2_Alarm_Message(cHelp));
//			}
		}
		
		return oMV;
	}



	public MyE2_MessageVector isValid(String cID_Unformated)
	{
		return new MyE2_MessageVector();
	}

}

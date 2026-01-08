package panter.gmbh.indep;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class bibSES_readerUserRights {

	/*
	 * enthaelt alle buttons im system, hat der user zugriffsrecht, dann steht an pos 2 ein y 
	 */
	private String[][] cARRAY_KENNER = null;

	public bibSES_readerUserRights(RECORD_USER  recUser) throws myException {
		super();
		
		// jetzt noch die button-validierungen des users einlesen
		String cSQL3 = "SELECT MODULKENNER,BUTTONKENNER " + "FROM " + bibE2.cTO() + ".JD_BUTTON," + 
								bibE2.cTO() + ".JD_BUTTON_USER WHERE JD_BUTTON.ID_BUTTON = JD_BUTTON_USER.ID_BUTTON AND JD_BUTTON_USER.ID_USER=" + 
								recUser.get_ID_USER_cUF();
		String cSQL4 = "SELECT MODULKENNER,BUTTONKENNER " + "FROM " + bibE2.cTO() + ".JD_BUTTON";

		String[][] allowedButtons 	= 	bibDB.EinzelAbfrageInArray(cSQL3, "@@@");
		String[][] allButtons 		=	bibDB.EinzelAbfrageInArray(cSQL4, "@@@");

		if (allowedButtons != null && allButtons != null)
		{
			
			String[][] kennerArray = new String[allButtons.length][3];
			for (int i=0;i<allButtons.length;i++)
			{
				kennerArray[i][0]=allButtons[i][0];
				kennerArray[i][1]=allButtons[i][1];
				kennerArray[i][2]="N";                 // erstmal nicht erlaubt
				
				// jetzt schauen, ob der geprüfte button in allowedbuttons ist
				for (int k=0;k<allowedButtons.length;k++)
				{
					if (allowedButtons[k][0].equals(allButtons[i][0]) &&
						allowedButtons[k][1].equals(allButtons[i][1]))
					{
						kennerArray[i][2]="Y";
						break;
					}
				}
			}
			this.cARRAY_KENNER = kennerArray;
		} else {
			//wenn die querys nicht klappen, dann exception
			throw new myException("bibSES.createSession:fillSESSION_Values:Die erlaubten Buttons konnten nicht abgefragt werden !");
		}
	}

	public String[][] get_cARRAY_KENNER() {
		return cARRAY_KENNER;
	}
	
	
	
	
}

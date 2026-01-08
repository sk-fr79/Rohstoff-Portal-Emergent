package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;


import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK.MODUL_LINK_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK.MODUL_LINK_Connector;
import panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK.MODUL_LINK_Connector_Multiple_Targets;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.MASCHINENSTAMM.MS___AUFGABEN_LIST_BasicModule_Inlay;

public class MESSAGE_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		// Textfeld, höhe anpassen
		String sText = oUsedResultMAP.get_UnFormatedValue("NACHRICHT");
		String[] rows = sText.split("\n");
		int   rows_len = sText.length() / 50 ;
		
		Object o = oMAP.get__Comp("NACHRICHT");
		int r = (int) Math.round((rows.length + 2) * 1.1);

		//if (r > 10) r = 10;
		int rResult = (r > rows_len ? r: rows_len);
		
		
		if (o instanceof MyE2_DB_TextArea) {
			//((MyE2_DB_TextArea) o).set_iRows(r);
			((MyE2_DB_TextArea) o).setHeight(new Extent((int) (rResult ) ,Extent.PC));
		}
		
		
		if (S.NN(oUsedResultMAP.get_FormatedValue("GELOESCHT")).equals("Y")) {
			oMAP.set_AllComponentsAsDeleted();
		}

		
		
		// Link-Buttons generieren
		MyE2_Row_EveryTimeEnabled oRowButtons = (MyE2_Row_EveryTimeEnabled)oMAP.get__Comp(MESSAGE_CONST.MESSAGE_LIST_ROW_BUTTONS);
		oRowButtons.removeAll();

		String sIDNachricht = oUsedResultMAP.get_UnFormatedValue("ID_NACHRICHT");
		MODUL_LINK_Connector oModulLinks = new MODUL_LINK_Connector( true,true,false );
		oModulLinks.initConnector(E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_LISTE, sIDNachricht,(Vector<E2_BasicModuleContainer>)null);
		
		if (oModulLinks.isVisible()){
			
			oRowButtons.add(oModulLinks);
		}
		
//		oRowWorkflowButtons.add(oLink);
//		
		
		
	}

}

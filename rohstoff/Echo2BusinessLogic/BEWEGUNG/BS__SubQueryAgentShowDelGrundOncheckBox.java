package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Iterator;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;


//2011-01-10: erweitert bei relevanten ComponentMaps die markierung der Checkboxen auf den loeschgrund
public class BS__SubQueryAgentShowDelGrundOncheckBox extends XX_ComponentMAP_SubqueryAGENT {

	@Override
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) 	throws myException 
	{
	}

	@Override
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		
		//2018-05-30: ersetzt durch allgemeine beschriftungsblocke in der E2ComponentMap.__Check_If_CheckBoxForList_And_set_ToolTips
		
//		Iterator<MyE2IF__Component> oIter = oMAP.values().iterator();
//		
//		while (oIter.hasNext())
//		{
//			MyE2IF__Component oComp = oIter.next();
//			
//			if (oComp instanceof E2_CheckBoxForList)
//			{
//				String cTabelle = oMAP.get_oInternalSQLResultMAP().get_oSQLFieldMAP().get_cMAIN_TABLE();
//				String cID = oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
//				
//				MyE2_String cToolTips = new MyE2_String("Tabelle: ",true,cTabelle,false," ... ID: ",true,cID,false);
//				
//				
//				//wenn die interne resultmap ein feld Deleted und del_grund enthaelt und deleted = 'Y' dann auch anzeigeb
//				if (	oMAP.get_oInternalSQLResultMAP().containsKey("DELETED") && 
//						oMAP.get_oInternalSQLResultMAP().containsKey("DEL_GRUND") &&
//						oMAP.get_oInternalSQLResultMAP().get_FormatedValue("DELETED").equals("Y"))
//				{
//					
//					cToolTips.addTranslated(" // GELÖSCHT: //  ");
//					cToolTips.addTranslated(" // GRUND:   ");
//					cToolTips.addUnTranslated(S.NN(oMAP.get_oInternalSQLResultMAP().get_FormatedValue("DEL_GRUND")));
//					cToolTips.addTranslated(" // DATUM:   ");
//					cToolTips.addUnTranslated(S.NN(oMAP.get_oInternalSQLResultMAP().get_FormatedValue("DEL_DATE")));
//					cToolTips.addTranslated(" // VON:  ");
//					cToolTips.addUnTranslated(S.NN(oMAP.get_oInternalSQLResultMAP().get_FormatedValue("DEL_KUERZEL")));
//				}
//				
//				((E2_CheckBoxForList)oComp).setToolTipText(cToolTips.CTrans());
//			}
//		}
	}

}

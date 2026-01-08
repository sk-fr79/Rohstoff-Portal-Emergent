package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class FU__SubQueryAgent_SHOW_StornoGrund extends	XX_ComponentMAP_SubqueryAGENT 
{

	@Override
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
	{

	}

	@Override
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		//2018-05-30: ersetzt durch allgemeine infos in E2_ComponentMap.__Check_If_CheckBoxForList_And_set_ToolTips
		
		
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
//				if (	oMAP.get_oInternalSQLResultMAP().containsKey("STORNO_KUERZEL") && 
//						oMAP.get_oInternalSQLResultMAP().containsKey("STORNO_GRUND") &&
//						oMAP.get_oInternalSQLResultMAP().get_FormatedValue("IST_STORNIERT").equals("Y"))
//				{
//					
//					cToolTips.addTranslated(" // Storniert: //  ");
//					cToolTips.addTranslated(" // GRUND:   ");
//					cToolTips.addUnTranslated(S.NN(oMAP.get_oInternalSQLResultMAP().get_FormatedValue("STORNO_GRUND")));
//					cToolTips.addTranslated(" // VON:  ");
//					cToolTips.addUnTranslated(S.NN(oMAP.get_oInternalSQLResultMAP().get_FormatedValue("STORNO_KUERZEL")));
//				}
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
//				
//				((E2_CheckBoxForList)oComp).setToolTipText(cToolTips.CTrans());
//			}
//		}

		
	}

}

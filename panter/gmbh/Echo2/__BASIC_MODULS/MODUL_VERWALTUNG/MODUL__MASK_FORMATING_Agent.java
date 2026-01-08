package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_VERWALTUNG;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_TAG;
import panter.gmbh.Echo2.RecursiveSearch.E2_SEARCH_TAGS;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_SERVLETS;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class MODUL__MASK_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
		
		MyE2IF__Component  oCompSelectHauptmenue = new E2_RecursiveSearch_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.ST__MODUL_MODUL_MANAGER_LISTE__SELEKTOR_COMPONENT_HAUPT_MENUE).get_SingleFoundComponent();

		if (oCompSelectHauptmenue != null && oCompSelectHauptmenue instanceof MyE2_SelectField)
		{
			MyE2_SelectField  oSelFieldSelektor = (MyE2_SelectField)oCompSelectHauptmenue;
			MyE2_SelectField  oSelFieldMask = (MyE2_SelectField)oMAP.get__Comp(RECORD_SERVLETS.FIELD__ID_HAUPTMENUE);
			
			if (S.isFull(oSelFieldSelektor.get_ActualWert()))
			{
				MyLong oLong = new MyLong(oSelFieldSelektor.get_ActualWert());
				
				if (oLong.get_bOK())
				{
					oSelFieldMask.set_ActiveValue_OR_FirstValue(oLong.get_cF_LongString());
				}
			}
		}
		
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		
	}

}

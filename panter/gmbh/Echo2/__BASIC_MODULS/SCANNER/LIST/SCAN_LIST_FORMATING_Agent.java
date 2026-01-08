package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.LIST;


import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_MODULES;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.SCAN_CONST;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.SCANNER_SETTINGS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		String fType 		= oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue(SCANNER_SETTINGS.filetype.fieldLabel());
		if(S.isFull( fType)){
			((MyE2_DB_Label_INGRID)oMAP.get__Comp(SCANNER_SETTINGS.filetype)).setText(SCAN_CONST.SCAN_FILETYPE.valueOf(fType).user_text());
		}

		String mKenner 		= oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue(SCANNER_SETTINGS.module_kenner.fieldLabel());
		if(S.isFull( mKenner)){
			((MyE2_DB_Label_INGRID)oMAP.get__Comp(SCANNER_SETTINGS.module_kenner)).setText(VALID_ENUM_MODULES.RANGE_KEY.valueOf(mKenner).user_text());
		}

		String pKenner 		= oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue(SCANNER_SETTINGS.programm_kenner.fieldLabel());
		if(S.isFull( pKenner)){
			((MyE2_DB_Label_INGRID)oMAP.get__Comp(SCANNER_SETTINGS.programm_kenner)).setText(SCAN_CONST.SCAN_PROGRAMMKENNER.valueOf(pKenner).user_text());
		}

		String aufloesung 	= "DPI"+oMAP.get_oInternalSQLResultMAP().get_UnFormatedValue(SCANNER_SETTINGS.dpi.fieldLabel());
		if(S.isFull( aufloesung)){
			((MyE2_DB_Label_INGRID)oMAP.get__Comp(SCANNER_SETTINGS.dpi)).setText(SCAN_CONST.SCAN_DPI.valueOf(aufloesung).user_text());
		}

	}
}
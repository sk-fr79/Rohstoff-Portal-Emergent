package rohstoff.Echo2BusinessLogic.MAIL_INBOX;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.AM_BasicContainer;
import panter.gmbh.Echo2.components.MyE2_Column_IMMER_ENABLED;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_INBOX;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class MAIL_INBOX_MASK_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		MyE2_Column_IMMER_ENABLED oCol = (MyE2_Column_IMMER_ENABLED) oMAP.get__Comp(MAIL_INBOX_Const.DAUGHTER_PAGE_ARCHIVE_CONTENTS);
		oCol.removeAll();
		
		String sTablename = RECORD_EMAIL_INBOX.TABLENAME.substring(3);
		String sID = oUsedResultMAP.get_cUNFormatedROW_ID();
		AM_BasicContainer o = new AM_BasicContainer(sTablename,sID,oMAP.get_oModulContainerMASK_This_BelongsTo().get_MODUL_IDENTIFIER(),true);
		o.set_bVisible_Row_For_Messages(false);
		oCol.add(o);
	}

	
	
}

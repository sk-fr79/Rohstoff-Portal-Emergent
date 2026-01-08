package panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING;

import panter.gmbh.Echo2.__BASIC_MODULS.DRUCK_PIPELINE.DP__CONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REPORT_PP_POS_USER_EXCL;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REPORT_PP_POS_USER_INCL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_PIPELINE_POS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_REPORT_PIPELINE_POS_EXT extends RECORD_REPORT_PIPELINE_POS 
{

	private boolean suppressedByScript = false;
	
	
	public RECORD_REPORT_PIPELINE_POS_EXT() throws myException {
		super();
	}

	public RECORD_REPORT_PIPELINE_POS_EXT(long lID_Unformated, MyConnection Conn)
			throws myException {
		super(lID_Unformated, Conn);
	}

	public RECORD_REPORT_PIPELINE_POS_EXT(long lID_Unformated)
			throws myException {
		super(lID_Unformated);
	}

	public RECORD_REPORT_PIPELINE_POS_EXT(MyConnection Conn) throws myException {
		super(Conn);
	}

	public RECORD_REPORT_PIPELINE_POS_EXT(RECORD_REPORT_PIPELINE_POS recordOrig) {
		super(recordOrig);
	}

	public RECORD_REPORT_PIPELINE_POS_EXT(String c_ID_or_WHEREBLOCK_OR_SQL,
			MyConnection Conn) throws myException {
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public RECORD_REPORT_PIPELINE_POS_EXT(String c_ID_or_WHEREBLOCK_OR_SQL)
			throws myException {
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}

	
	public String  get_HashKey_4_FileList() throws myException
	{
		return 	this.get_REPORTFILEBASENAME_cUF_NN("--")+
					this.get_ZUSATZSTRING4JASPERHASH1_cUF_NN("")+
					this.get_ZUSATZSTRING4JASPERHASH2_cUF_NN("")+
					this.get_ZUSATZSTRING4JASPERHASH3_cUF_NN("");


	}
	
	
	public boolean get_bIsRelevant(E2_JasperHASH  oActualHash) throws myException
	{
		boolean bRueck = (oActualHash.get_IS_TYP_PRINT() && this.is_RELEVANT_4_PRINT_YES())
						|| (oActualHash.get_IS_TYP_MAIL() && this.is_RELEVANT_4_MAIL_YES())
						|| (oActualHash.get_IS_TYP_PREVIEW() && this.is_RELEVANT_4_PREVIEW_YES());
		
		
		if (!bRueck)
		{
			return bRueck;    //dann schon entschieden, nich relevant
		}
		
		if (this.get_bTYP_VERARBEITUNG_ARCHIV())
		{			
			//System.out.println("Archiv ---------------------");
		}
		
		// 2011-04-11: Benutzer bekommt pipelinepos oder nicht
		boolean bUserIsIncl = true;
		
		RECLIST_REPORT_PP_POS_USER_INCL  reclistINCL = this.get_DOWN_RECORD_LIST_REPORT_PP_POS_USER_INCL_id_report_pipeline_pos(null,null,true);
		if (reclistINCL.get_vKeyValues().size()>0)
		{
			bUserIsIncl = false;                  //falls es eine Positivliste gibt, dann muss der user rein
			
			for (int i=0;i<reclistINCL.get_vKeyValues().size();i++)
			{
				if (reclistINCL.get(i).get_UP_RECORD_USER_id_user().get_ID_USER_cUF_NN("-1").equals(bibALL.get_RECORD_USER().get_ID_USER_cUF_NN("-")))
				{
					bUserIsIncl = true;
				}
			}
		}
		
		
		
		// 2011-04-11: Benutzer bekommt pipelinepos oder nicht
		boolean bUserIsExcl = false;
		
		RECLIST_REPORT_PP_POS_USER_EXCL  reclistEXCL = this.get_DOWN_RECORD_LIST_REPORT_PP_POS_USER_EXCL_id_report_pipeline_pos(null,null,true);
		if (reclistEXCL.get_vKeyValues().size()>0)
		{
			for (int i=0;i<reclistEXCL.get_vKeyValues().size();i++)
			{
				if (reclistEXCL.get(i).get_UP_RECORD_USER_id_user().get_ID_USER_cUF_NN("-1").equals(bibALL.get_RECORD_USER().get_ID_USER_cUF_NN("-")))
				{
					bUserIsExcl = true;
				}
			}
		}
	

		bRueck = bUserIsIncl && (!bUserIsExcl);
		
		//
		
		
//		//jetzt die benutzer-relevanz pruefen
//		if (S.isFull(this.get_ID_USER_cUF_NN("")))
//		{
//			if (!this.get_ID_USER_cUF_NN("--").equals(bibALL.get_RECORD_USER().get_ID_MANDANT_cUF_NN("-")))
//			{
//				bRueck = false;
//			}
//		}		
//		else if (S.isFull(this.get_ID_USER_AUSSCHLUSS_cUF_NN("")))
//		{
//			if (this.get_ID_USER_AUSSCHLUSS_cUF_NN("--").equals(bibALL.get_RECORD_USER().get_ID_MANDANT_cUF_NN("-")))
//			{
//				bRueck = false;
//			}
//		}
		
		
		//jetzt das script evaluieren
		if (S.isFull(this.get_SQL_EXEC_TRUE_FALSE_cF()))
		{
			Interpret_VALID  oValid = new Interpret_VALID(this.get_SQL_EXEC_TRUE_FALSE_cF(), oActualHash);
			boolean bHelp = oValid.get_bIsValid();
			
			this.setSuppressedByScript(!bHelp);
			
			bRueck = bRueck && oValid.get_bIsValid();
		}
		
		return bRueck;
		
	}
	
	
	
	public boolean get_bTYP_VERARBEITUNG_DIREKTDRUCK() throws myException
	{
		return this.get_TYP_VERARBEITUNG_cUF().equals(DP__CONST.PP_VERARBEITUNG_TYP_DIREKTDRUCK);
	}
	
	public boolean get_bTYP_VERARBEITUNG_STANDARD() throws myException
	{
		return this.get_TYP_VERARBEITUNG_cUF().equals(DP__CONST.PP_VERARBEITUNG_TYP_STANDARD);
	}
	
	public boolean get_bTYP_VERARBEITUNG_CONTROLMAIL() throws myException
	{
		return this.get_TYP_VERARBEITUNG_cUF().equals(DP__CONST.PP_VERARBEITUNG_TYP_EMAIL);
	}
	
	public boolean get_bTYP_VERARBEITUNG_ARCHIV() throws myException
	{
		return this.get_TYP_VERARBEITUNG_cUF().equals(DP__CONST.PP_VERARBEITUNG_TYP_ARCHIV);
	}

	public boolean isSuppressedByScript() {
		return suppressedByScript;
	}

	public void setSuppressedByScript(boolean p_suppressedByScript) {
		this.suppressedByScript = p_suppressedByScript;
	}
	
	
	public boolean isForDownload() throws myException {
		return this.get_TYP_VERARBEITUNG_cUF().equals(DP__CONST.PP_VERARBEITUNG_TYP_STANDARD);
	}
	
	
	
	
	
}

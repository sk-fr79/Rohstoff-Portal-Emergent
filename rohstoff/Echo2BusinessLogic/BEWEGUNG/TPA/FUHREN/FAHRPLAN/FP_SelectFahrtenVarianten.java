package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FAHRTENVARIANTEN;
import panter.gmbh.indep.MyDropDownSettings;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.SCHNELLERFASSUNG.FPSE_Container_SCHNELL_Erfassung_Fahrt;

public class FP_SelectFahrtenVarianten extends MyE2_SelectField
{

	private FPSE_Container_SCHNELL_Erfassung_Fahrt oContainerSCHNELL_Erfassung = null;
	
	/*
	 * fuer die liste wird die uebergabe von cvarianten einmal mit null gemacht, geclonte 
	 * uebergeben den ausgangskonstructor 
	 */
	public FP_SelectFahrtenVarianten(FPSE_Container_SCHNELL_Erfassung_Fahrt ContainerSCHNELL_Erfassung) throws myException
	{
		super();

		this.oContainerSCHNELL_Erfassung = ContainerSCHNELL_Erfassung;
		
		MyDropDownSettings oDDVarianten = new MyDropDownSettings(bibE2.cTO(),
																"JT_FAHRTENVARIANTEN " +
																"LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE A1 ON (A1.ID_ADRESSE=JT_FAHRTENVARIANTEN.ID_ADRESSE_START) " +
																"LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE A2 ON (A1.ID_ADRESSE=JT_FAHRTENVARIANTEN.ID_ADRESSE_ZIEL) " +
																"LEFT OUTER JOIN "+bibE2.cTO()+".JT_ARTIKEL AR ON (AR.ID_ARTIKEL=JT_FAHRTENVARIANTEN.ID_ARTIKEL) ",
																" A1.NAME1||' - '||A1.ORT||' ---> '|| A2.NAME1||' - '||A2.ORT||'  ('|| NVL(AR.ANR1,'-')||')'",
																"JT_FAHRTENVARIANTEN.ID_FAHRTENVARIANTEN",
																null,
																null,
																true,
																" A1.NAME1||' - '||A1.ORT||' ---> '|| A2.NAME1||' - '||A2.ORT||'  ('|| NVL(AR.ANR1,'-')||')'");

		this.set_ListenInhalt(oDDVarianten.getDD(),false);
		this.add_oActionAgent(new ownActionAgent());
	}

	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{

			String cID_FAHRTENVARIANTEN = FP_SelectFahrtenVarianten.this.get_ActualWert();
			
			if (S.isEmpty(cID_FAHRTENVARIANTEN))
			{
				return;
			}
			
			FPSE_Container_SCHNELL_Erfassung_Fahrt oCont_Erfassung = FP_SelectFahrtenVarianten.this.oContainerSCHNELL_Erfassung;

			RECORD_FAHRTENVARIANTEN recFV = new RECORD_FAHRTENVARIANTEN(new MyLong(cID_FAHRTENVARIANTEN).get_cUF_LongString());   // Umwandlung ueber myLong, weil die werte im selector einen tausenderpunkt haben 
			
			oCont_Erfassung.get_oSearchStartAdresse().get_oTextFieldForSearchInput().setText(recFV.get_ID_ADRESSE_START_cUF_NN(""));
			oCont_Erfassung.get_oSearchZielAdresse().get_oTextFieldForSearchInput().setText(recFV.get_ID_ADRESSE_ZIEL_cUF_NN(""));

			oCont_Erfassung.get_oSearchStartAdresse().FillLabelWithDBQuery(recFV.get_ID_ADRESSE_START_cUF_NN(""));
			oCont_Erfassung.get_oSearchZielAdresse().FillLabelWithDBQuery(recFV.get_ID_ADRESSE_ZIEL_cUF_NN(""));
			
			oCont_Erfassung.get_oSearchStartAdresse().perform_XX_MaskActionAfterFound(recFV.get_ID_ADRESSE_START_cUF_NN(""), true);
			oCont_Erfassung.get_oSearchZielAdresse().perform_XX_MaskActionAfterFound(recFV.get_ID_ADRESSE_ZIEL_cUF_NN(""), true);

			for (int i=0;i<oCont_Erfassung.get_vUnterGridContainer().size();i++)
			{
				
				oCont_Erfassung.get_vUnterGridContainer().get(i).get_oSearchSorte().get_oTextFieldForSearchInput().setText(recFV.get_ID_ARTIKEL_cUF_NN(""));
				oCont_Erfassung.get_vUnterGridContainer().get(i).get_oSearchSorte().FillLabelWithDBQuery(recFV.get_ID_ARTIKEL_cUF_NN(""));
				if (S.isFull(recFV.get_ID_ARTIKEL_cUF_NN("")))    //sorte kann null sein
				{
					oCont_Erfassung.get_vUnterGridContainer().get(i).get_oSearchSorte().perform_XX_MaskActionAfterFound(recFV.get_ID_ARTIKEL_cUF_NN(""), true);
				}
			}
		}
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		throw new myExceptionCopy("Copy not defined !"); 
	}

	

	
	
	
}

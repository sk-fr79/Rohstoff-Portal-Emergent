package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.E2_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.BST_K_MASK__ModulContainer;

public class FU_MASK_BT_OpenTPA extends E2_BUTTON_OPEN_MASK_FromID
{

	public FU_MASK_BT_OpenTPA()	throws myException
	{
		super(new BST_K_MASK__ModulContainer(null,false), new MyE2_String("Transportauftrag"), null,"EDIT_TPA_AUS_FUHRE", "VIEW_TPA_AUS_FUHRE");
		
		this.setText("TPA");
		this.setFont(new E2_FontBold(-2));
		this.setToolTipText(new MyE2_String("Wechsel in die Maske des Transportauftrages").CTrans());
		
		this.set_cMeldungFuerNichtGefunden(new MyE2_String("Zu dieser Fuhre existiert kein Transportauftrag !"));
		
		this.add_oActionAgent(new actionAgentDefiniereTPA_ID(), true);   //muss als erster actionAgent durchlaufen werden
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery(	"JT_VKOPF_TPA",
				"  NVL(ABGESCHLOSSEN,'N'),  NVL(DELETED,'N')",
				bibALL.get_Array("N","N"),
				true, new MyE2_String("Bearbeiten nur erlaubt bei Transportaufträgen, die NICHT abgeschlossen und NICHT geloescht sind !")));

		
	}

	
	/*
	 * ist immer enabled, da im moment des klicks entschieden wird, ob 
	 * ein tpa vorhanden ist oder nicht
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(true);
	}


	

	/*
	 * actionAgent muss vor allen anderen ActionAgenten ausgefuehrt werden, da er die Fuhren-ID raussucht und dem Button in die EXT().Merkmal1 die ID_TPA_uebergibt 
	 */
	private class actionAgentDefiniereTPA_ID extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			String cID_VKOPF_TPA = null;
			
			FU_MASK_BT_OpenTPA oThis = FU_MASK_BT_OpenTPA.this;
			String cID_VPOS_TPA_FUHRE = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()==null?
																null:
																""+oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_LActualDBValue("ID_VPOS_TPA_FUHRE", true);
			
			if (cID_VPOS_TPA_FUHRE != null)
			{
				RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE);
				if (recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa() != null)
				{
					cID_VKOPF_TPA = recFuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_ID_VKOPF_TPA_cUF();
				}
			}
			oThis.EXT().set_C_MERKMAL(cID_VKOPF_TPA);
		}
	}
	
	@Override
	public void put_SpecialButtonsToRowForButtons_EDIT(MyE2_Row rowForButtons) throws myException
	{
	}

	@Override
	public void put_SpecialButtonsToRowForButtons_VIEW(MyE2_Row rowForButtons) throws myException
	{
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		FU_MASK_BT_OpenTPA oBT = null;;
		try
		{
			oBT = new FU_MASK_BT_OpenTPA();
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}

		return oBT;
	}
	
}

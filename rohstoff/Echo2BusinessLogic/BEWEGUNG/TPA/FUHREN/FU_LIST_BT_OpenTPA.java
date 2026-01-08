package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.E2_DB_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.BST_K_MASK__ModulContainer;

public class FU_LIST_BT_OpenTPA extends E2_DB_BUTTON_OPEN_MASK_FromID
{

	private  BST_K_MASK__ModulContainer   BasicModuleContainerTPA = null;
	
	public FU_LIST_BT_OpenTPA(SQLField field, BST_K_MASK__ModulContainer   oBasicModuleContainerTPA)  throws myException
	{
		super(field, oBasicModuleContainerTPA, new MyE2_String("Transportauftrag"), null, "EDIT_TPA_AUS_FUHRE", "VIEW_TPA_AUS_FUHRE");
		this.BasicModuleContainerTPA = oBasicModuleContainerTPA;
		
		this.set_cMeldungFuerNichtGefunden(new MyE2_String("Zu dieser Fuhre existiert kein Transportauftrag !"));
		
		this.add_IDValidator(new E2_Validator_ID_DBQuery(	"JT_VKOPF_TPA",
				"  NVL(ABGESCHLOSSEN,'N'),  NVL(DELETED,'N')",
				bibALL.get_Array("N","N"),
				true, new MyE2_String("Bearbeiten nur erlaubt bei Transportaufträgen, die NICHT abgeschlossen und NICHT geloescht sind !")));

	}
	
	
	/*
	 * wird nur enabled, wenn der letzte status view ist und das merkmal nicht null,
	 * in MERKMAL steht die fuer die Maske relevante ID 
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.setEnabled(true);
	}

	
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_Button:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");
								

		//die ID_VKOPF_TPA rauslesen
		String cID_VKOPF_TPA = oResultMAP.get_LActualDBValue("ID_VKOPF_TPA", true).toString().trim();
		if (cID_VKOPF_TPA.equals("0"))
		{
			cID_VKOPF_TPA = null;
		}
		
		if (cID_VKOPF_TPA == null)
		{
			this.setIcon(E2_ResourceIcon.get_RI("fuhre_frei.png"));
			this.setToolTipText(new MyE2_String("Die Transportposition ist frei, d.h. hat keinen Transportauftrag !").CTrans());
		}
		else
		{
			this.setIcon(E2_ResourceIcon.get_RI("fuhre_tpa.png"));
			this.setToolTipText(new MyE2_String("Öffnet den Transportauftrag, zu dem diese Warenposition gehört !").CTrans());
		}
		
		this.EXT().set_C_MERKMAL(cID_VKOPF_TPA);
		
		this.set_bEnabled_For_Edit(true);     // definierten zustand einschalten
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);
	}
	

	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		FU_LIST_BT_OpenTPA oButtCopy = null;
		
		try
		{
			oButtCopy = new FU_LIST_BT_OpenTPA(
											this.EXT_DB().get_oSQLField(),
											this.BasicModuleContainerTPA);
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("E2_BUTTON_OPEN_MASK_FromID:get_Copy:Copy-Error!");
		}
		return oButtCopy;
	}

	
	
	
}

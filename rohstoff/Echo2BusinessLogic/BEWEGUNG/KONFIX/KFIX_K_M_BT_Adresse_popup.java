package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_AuthValidatorEditAdress;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;

public class KFIX_K_M_BT_Adresse_popup extends MyE2_Button implements IF_RB_Component
{

	private RB_KF key = null;
	
	//button, der aus einer kontrakt-position !. die lieferanten-Adresse anzeigt und auch oeffnet
	public KFIX_K_M_BT_Adresse_popup() throws myException
	{
		super("---Neueingabe---");
		this.setFont(new E2_FontItalic(-2));
		this.add_oActionAgent(new ownActionAgent());
		this.add_IDValidator(new FS_AuthValidatorEditAdress());
		this.setToolTipText(new MyE2_String("In die Adressmaske des Kunden/Lieferanten wechseln !").CTrans());
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			try
			{
				/*
				 * der rufende button ist der button, der den event ausloest
				 */
				KFIX_K_M_BT_Adresse_popup oButton = (KFIX_K_M_BT_Adresse_popup)bibE2.get_LAST_ACTIONEVENT().getSource();
				
//				SQLResultMAP oResultMAP = oButton.EXT().get_oComponentMAP().get_oInternalSQLResultMAP();
				
//				if (oResultMAP == null)           // bei neueingabe ist der button ohne sinn
//					return;
				
//				String cID_VPOS_KON = oResultMAP.get_UnFormatedValue("ID_VPOS_KON");
				
//				My_VPos_KON oVposKon = new My_VPos_KON(cID_VPOS_KON);
				String cID_ADRESSE = oButton.EXT().get_C_MERKMAL();//oVposKon.get_oVKopfKON().get_UnFormatedValue("ID_ADRESSE");
				
				if (bibALL.isLong(cID_ADRESSE))
				{
				
					if (bibDB.EinzelAbfrage("SELECT COUNT(*) FROM " +
							bibE2.cTO()+".JT_ADRESSE WHERE ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO+
							" AND ID_ADRESSE="+cID_ADRESSE).equals("1"))
					{
						
						FS_ModulContainer_MASK oMask = new FS_ModulContainer_MASK();
				
						MyE2_MessageVector vRueck = oButton.valid_IDValidation(bibALL.get_Vector(cID_ADRESSE));
						String cSTATUS = null;
						String cInfo = null;
						
						oMask.get_oRowForButtons().removeAll();
						
						if (vRueck.size() != 0)
						{
							cSTATUS = E2_ComponentMAP.STATUS_VIEW;
							cInfo = "Adresse prüfen ";
						}
						else
						{
							cSTATUS = E2_ComponentMAP.STATUS_EDIT;
							cInfo = "Adresse bearbeiten ...";
							oMask.get_oRowForButtons().add(new maskButtonSaveMask(oMask,new E2_SaveMaskStandard(oMask, null),null, null));
						}
						oMask.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMask));
						E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMask.get_vCombinedComponentMAPs();
						vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(cSTATUS,cID_ADRESSE);
						oMask.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String(cInfo));
					}
				}
				
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("FU_MASK_DB_SEARCH_AdressePOPUP_BUTTON:ownActionAgent:Exception: "+ex.getLocalizedMessage(),false),false);
			}
		}
		
	}

	@Override
	public void mark_Neutral() throws myException {
		this.set_bEnabled_For_Edit(true);
	}

	@Override
	public void mark_MustField() throws myException {}

	@Override
	public void mark_Disabled() throws myException {
		this.set_bEnabled_For_Edit(false);
	}

	@Override
	public void mark_FalseInput() throws myException {}

	@Override
	public void set_Alignment(Alignment align) throws myException {
		
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD()==null) {
			this.setText("---Neueingabe---");
		} else {
			String id_vkopf_kon = new Rec20(_TAB.vpos_kon)._fill_id(((MyRECORD)dataObject.get_RecORD()).fs(this.rb_KF().FIELDNAME(),"")).get_fs_dbVal(VPOS_KON.id_vkopf_kon);
			String txt = new Rec20_VKOPF_KON(_TAB.vkopf_kon)._fill_id(id_vkopf_kon).get_adresse_for_info();
			this.EXT().set_C_MERKMAL(new Rec20_VKOPF_KON(_TAB.vkopf_kon)._fill_id(id_vkopf_kon).get_id_adresse());
			this.setText(txt);
		}
		
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {}

	@Override
	public RB_KF rb_KF() throws myException {
		return key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.key = key;
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return new Vector<RB_Validator_Component>();
	}
	
	
	
	
}

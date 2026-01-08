package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_ComponentMAP;

public class FU_DAUGHTER_ORTE extends XX_FULL_DAUGHTER
{
	private FUO_LIST_BasicModuleContainer oListContainer = null;
	private String  EK_VK = null; 
	
	public FU_DAUGHTER_ORTE(	SQLFieldForPrimaryKey  			osqlField, 
								String 				   			cEK_VK) throws myException
	{
		super(osqlField);
		if (!(cEK_VK.equals("EK") ||cEK_VK.equals("VK")))
		{
			throw new myException(this,"Only marker EK and VK are allowed !!");
		}
		
		this.EK_VK = cEK_VK;
		
		this.oListContainer = new FUO_LIST_BasicModuleContainer(cEK_VK,this);
		
	}
 
	@Override
	public Component build_content_for_Mask(String valueFormated,String cValueUnformated, String cMASK_STATUS) throws myException
	{
		this.set_buttons_IN_Status(cMASK_STATUS);   // die buttons passend einstellen
		
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) || cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY))
		{
			return new MyE2_Label(new MyE2_String("Zusatzorte sind nicht aktiv!"));
		}
		else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
		{
			
			this.setMaskTitle(new RECORD_VPOS_TPA_FUHRE(cValueUnformated));
			
			this.oListContainer.set_BASE_ID_VPOS_TPA_FUHRE(cValueUnformated);
			this.oListContainer.get_oNaviList().Fill_NavigationList("");
			return this.oListContainer;
		}
		else
		{
			return new MyE2_Label(new MyE2_String("Fehler !!!"));
		}
	}

	
	
	private class ownSaveButton extends MyE2_Button 
	{
		public ownSaveButton(MyE2_String text)
		{
			super(text);
			this.add_oActionAgent(new ownActionAgent());
		}
		
		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				E2_BasicModuleContainer_MASK  oFuhrenMask = FU_DAUGHTER_ORTE.this.EXT().get_ModuleContainer_MASK_this_BelongsTo();
				new E2_SaveMaskStandard(oFuhrenMask,null).doSaveMask(true);
			}
			
		}

	}
	
	
	
	
	private void setMaskTitle(RECORD_VPOS_TPA_FUHRE recFuhre) throws myException
	{
		/*
		 * jetzt eine info in die maske schreiben (falls diese benutzt wird
		 */
		MyE2_String oString = new MyE2_String("Eingabe Zusatz-Ladeposition zu Fuhre: ");
		if (this.EK_VK.equals("VK"))
		{
			oString = new MyE2_String("Eingabe Zusatz-Abladeposition zu Fuhre: ");
		}
		
		String cZusatz = recFuhre.get_ANR1_EK_cUF_NN("<anr1-ek>")+" - "+recFuhre.get_L_NAME1_cUF_NN("<Name1>")+" "+recFuhre.get_L_ORT_cUF_NN("<Ladeort>")+"   ->  "+ recFuhre.get_ANR1_VK_cUF_NN("<anr1-vk>")+" - "+recFuhre.get_A_NAME1_cUF_NN("<Name1>")+" "+recFuhre.get_A_ORT_cUF_NN("<Abladeort>");

		oString.addUnTranslated(cZusatz);
		
		MyE2_Label oLabInfo = new MyE2_Label(oString);
		oLabInfo.setFont(new E2_FontItalic(-2));
		this.oListContainer.get_oMaskContainer().add_Headline(oLabInfo);
	}

	
	
	
	@Override
	public Component build_non_active_placeholder() throws myException
	{
		return new MyE2_Label(new MyE2_String("INAKTIV !!!"));
	}

	@Override
	public void prepare_DaughterContentForNew() throws myException
	{
		this.removeAll();
		this.add(new ownSaveButton(new MyE2_String("Zusatzorte können erst erfasst werden, wenn die Fuhre gespeichert wurde !")));
	}

	@Override
	public void set_buttons_IN_Status(String cMASK_STATUS) throws myException
	{
		this.oListContainer.get_oPanel().get_oFUO_LIST_BT_DELETE().set_bEnabled_For_Edit(false);
		this.oListContainer.get_oPanel().get_oFUO_LIST_BT_EDIT().set_bEnabled_For_Edit(false);
		this.oListContainer.get_oPanel().get_oFUO_LIST_BT_VIEW().set_bEnabled_For_Edit(false);
		this.oListContainer.get_oPanel().get_oFUO_LIST_BT_NEW().set_bEnabled_For_Edit(false);
		
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			this.oListContainer.get_oPanel().get_oFUO_LIST_BT_DELETE().set_bEnabled_For_Edit(true);
			this.oListContainer.get_oPanel().get_oFUO_LIST_BT_EDIT().set_bEnabled_For_Edit(true);
			this.oListContainer.get_oPanel().get_oFUO_LIST_BT_VIEW().set_bEnabled_For_Edit(true);
			this.oListContainer.get_oPanel().get_oFUO_LIST_BT_NEW().set_bEnabled_For_Edit(true);
		}
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
		{
			this.oListContainer.get_oPanel().get_oFUO_LIST_BT_VIEW().set_bEnabled_For_Edit(true);
		}
	}

	
	//zusatzmethode
	public FU_MASK_ComponentMAP get_oFU_MASK_ComponentMAP() throws myException
	{
		return (FU_MASK_ComponentMAP)this.EXT().get_oComponentMAP();
	}

	public FUO_LIST_BasicModuleContainer  get_oFUO_LIST_BasicModuleContainer()
	{
		return oListContainer;
	}

	public boolean is_EK() {
		return S.NN(this.EK_VK).equals("EK");
	}
	
	
}

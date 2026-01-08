package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VectorMyString;


public class FU___ButtonStornoInfo extends MyE2_ButtonInLIST
{
	
	private String cID_VPOS_TPA_FUHRE = null;
	
	/**
	 * 
	 * @param ID_VPOS_TPA_FUHRE_UF
	 */
	public FU___ButtonStornoInfo(String ID_VPOS_TPA_FUHRE_UF)
	{
		super(E2_ResourceIcon.get_RI("buchung_fuhre_status_storno.png"), E2_ResourceIcon.get_RI("empty10.png"));
		this.cID_VPOS_TPA_FUHRE = ID_VPOS_TPA_FUHRE_UF;
		this.add_oActionAgent(new actionAgentShowStornoGrund());
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		FU___ButtonStornoInfo oButton = new FU___ButtonStornoInfo(this.cID_VPOS_TPA_FUHRE);

		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		
		return oButton;
	}
	
	
	
	/*
	 * mit dem zugehoerigen button in der liste kann der storno-grund angezeigt werden
	 */
	private class actionAgentShowStornoGrund extends XX_ActionAgent
	{
		public actionAgentShowStornoGrund()
		{
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(FU___ButtonStornoInfo.this.cID_VPOS_TPA_FUHRE);
			
			if (recFuhre.is_IST_STORNIERT_YES())
			{
				new PopUpContainerStornoInfo(recFuhre).show_POPUP_BOX();
			}
			else
			{
				new PopUpContainerNichtStorniert().show_POPUP_BOX();
			}
		}
		
	}

	
	private class PopUpContainerStornoInfo extends E2_ConfirmBasicModuleContainer
	{

		public PopUpContainerStornoInfo(RECORD_VPOS_TPA_FUHRE recFuhre) throws myException
		{
			super(new MyE2_String("Info "), 
					new MyE2_String("über die Stornierung:"),
						new VectorMyString(new MyE2_String("Storniert von:",true,recFuhre.get_STORNO_KUERZEL_cF_NN(""),false),
											new MyE2_String("Grund: "),
											new MyE2_String(recFuhre.get_STORNO_GRUND_cF_NN("")),
											null),
						new MyE2_String("OK"), null,new Extent(300), new Extent(330));
			
		}
		
	}

	private class PopUpContainerNichtStorniert extends E2_ConfirmBasicModuleContainer
	{

		public PopUpContainerNichtStorniert()  throws myException
		{
			super(new MyE2_String("Info:"), new MyE2_String("Fuhre ist NICHT Storniert !"),
					new MyE2_String(""), new MyE2_String("OK"), null,new Extent(300), new Extent(330));
		}
		
	}

	
	

}

package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

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

public class BST__ButtonStornoInfo extends MyE2_ButtonInLIST
{
	
	private RECORD_VPOS_TPA_FUHRE recVPOS_TPA_FUHRE = null;
	
	public BST__ButtonStornoInfo(String ID_VPOS_TPA_FUHRE) throws myException
	{
		super();
		this._INIT(ID_VPOS_TPA_FUHRE);
		this.add_oActionAgent(new actionAgentShowStornoGrund());
	}

	public BST__ButtonStornoInfo()
	{
		super();
		this.add_oActionAgent(new actionAgentShowStornoGrund());
	}
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		BST__ButtonStornoInfo oButton = new BST__ButtonStornoInfo();

		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		
		return oButton;
	}
	
	
	public void _INIT(String ID_VPOS_TPA_FUHRE) throws myException
	{
		this.recVPOS_TPA_FUHRE = new RECORD_VPOS_TPA_FUHRE(ID_VPOS_TPA_FUHRE);
		
		if (this.recVPOS_TPA_FUHRE.is_IST_STORNIERT_YES())
		{
			this.__setImages(E2_ResourceIcon.get_RI("storno_all.png"), E2_ResourceIcon.get_RI("empty10.png"));
		}
		else
		{
			this.__setImages(E2_ResourceIcon.get_RI("empty10.png"), E2_ResourceIcon.get_RI("empty10.png"));
		}
	}


	
	public void set_ID_VPOS_TPA_FUHRE(String cid_vpos_tpa_fuhre) throws myException
	{
		this._INIT(cid_vpos_tpa_fuhre);
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
			if (BST__ButtonStornoInfo.this.recVPOS_TPA_FUHRE == null)
				return;
			
			if (BST__ButtonStornoInfo.this.recVPOS_TPA_FUHRE.is_IST_STORNIERT_YES())
			{
				new PopUpContainerStornoInfo().show_POPUP_BOX();
			}
			else
			{
				new PopUpContainerNichtStorniert().show_POPUP_BOX();
			}
		}
		
	}

	
	private class PopUpContainerStornoInfo extends E2_ConfirmBasicModuleContainer
	{

		public PopUpContainerStornoInfo() throws myException
		{
			super(new MyE2_String("Info "), 
					new MyE2_String("über die Stornierung:"),
						new VectorMyString(new MyE2_String("Storniert von:",true,recVPOS_TPA_FUHRE.get_STORNO_KUERZEL_cF_NN(""),false),
											new MyE2_String("Grund: "),
											new MyE2_String(recVPOS_TPA_FUHRE.get_STORNO_GRUND_cF_NN("")),
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

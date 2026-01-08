package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.BSK_K_MASK__ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.BSK_P_MASK__ModulContainer;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;

public class BSKC_ModulContainerLIST extends Project_BasicModuleContainer
{
	private BSK_NavigationList 				oNavigationList = new BSK_NavigationList();
	private BSKC_ListSelector				oListSelektor = null;

	
	// popup-comtainer fuer die aktiven ID-Buttons
	private FS_ModulContainer_MASK 			oFirmenMASK = null;
	private BSK_K_MASK__ModulContainer      oKontraktKopfMASK_EK = null;
	private BSK_K_MASK__ModulContainer      oKontraktKopfMASK_VK = null;
	private BSK_P_MASK__ModulContainer  	oKontraktPosMASK_EK = null;
	private BSK_P_MASK__ModulContainer  	oKontraktPosMASK_VK = null;
	
	private String   						EK_VK = null;
	
	public BSKC_ModulContainerLIST(String cEK_VK) throws myException
	{
		super(cEK_VK.equals("EK")?E2_MODULNAMES.NAME_MODUL_VERTRAGSCLEARING_EK:E2_MODULNAMES.NAME_MODUL_VERTRAGSCLEARING_VK);
	
		this.EK_VK = cEK_VK;
		
		this.oFirmenMASK = 			new FS_ModulContainer_MASK();
		this.oKontraktKopfMASK_EK = new BSK_K_MASK__ModulContainer(new BS__SETTING(myCONST.VORGANGSART_EK_KONTRAKT),null);
		this.oKontraktKopfMASK_VK = new BSK_K_MASK__ModulContainer(new BS__SETTING(myCONST.VORGANGSART_VK_KONTRAKT),null);
		this.oKontraktPosMASK_EK = 	new BSK_P_MASK__ModulContainer(new BS__SETTING(myCONST.VORGANGSART_EK_KONTRAKT));
		this.oKontraktPosMASK_VK = 	new BSK_P_MASK__ModulContainer(new BS__SETTING(myCONST.VORGANGSART_VK_KONTRAKT));
		
		this.oNavigationList.INIT(this, cEK_VK, this.get_MODUL_IDENTIFIER(),null);
		
		this.oListSelektor = new BSKC_ListSelector(this.oNavigationList,cEK_VK, this.get_MODUL_IDENTIFIER());
		
		this.add(oListSelektor,E2_INSETS.I_5_5_5_5);
		this.add(new BSKC_ExpandBlockSummen(this.oNavigationList,this.EK_VK),E2_INSETS.I_5_5_5_5);
		
		this.add(new BSKC_ListBedienPanel(this,cEK_VK),E2_INSETS.I_5_5_5_5);
		
		this.add(this.oNavigationList,E2_INSETS.I_5_5_5_5);
		
		this.oListSelektor.get_oSelVector().doActionPassiv();
		
	}



	public E2_NavigationList get_oNavigationList()
	{
		return oNavigationList;
	}




	public FS_ModulContainer_MASK get_oFirmenMASK()
	{
		return oFirmenMASK;
	}



	public BSK_K_MASK__ModulContainer get_oKontraktKopfMASK_EK()
	{
		return oKontraktKopfMASK_EK;
	}



	public BSK_K_MASK__ModulContainer get_oKontraktKopfMASK_VK()
	{
		return oKontraktKopfMASK_VK;
	}



	public BSK_P_MASK__ModulContainer get_oKontraktPosMASK_EK()
	{
		return oKontraktPosMASK_EK;
	}



	public BSK_P_MASK__ModulContainer get_oKontraktPosMASK_VK()
	{
		return oKontraktPosMASK_VK;
	}



	public String get_EK_VK()
	{
		return EK_VK;
	}
	

}

package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSRG_K_LIST__ModulContainer extends Project_BasicModuleContainer 
{
	private BSRG_K_LIST__ComponentMAP 		oComponentMAPList = null;
	private BSRG_K_MASK__ModulContainer		oMaskContainer = null;
	private E2_NavigationList 				oNavigationList = null;

	
	// das settingobject, das abnahmeangebot von angeboten unterscheidet
	private BS__SETTING						SETTING = null;
	

	private BSRG_K_LIST_Selector 			oSelektor = null;
	
	
	public BSRG_K_LIST__ModulContainer(String cBELEGTYP) throws myException
	{
		super(new BS__SETTING(cBELEGTYP).get_cMODULCONTAINER_LIST_IDENTIFIER());
		
		this.SETTING = new BS__SETTING(cBELEGTYP);
		
		this.oNavigationList = new E2_NavigationList();

		this.oComponentMAPList = new BSRG_K_LIST__ComponentMAP(this,this.oNavigationList,this.SETTING);
		
		this.oMaskContainer = new BSRG_K_MASK__ModulContainer(this.SETTING, this);
		
		this.set_oNaviListFirstAdded(this.oNavigationList);
		
		// dem positions-Element die navigationsliste uebergeben:Damit wird die liste auch beim speichern aus der maske
		// aktualisiert
		E2_ComponentMAP oMapMask = this.oMaskContainer.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN();
		((BSRG_K_MASK_COMP_KP_FullDaughterPositions)oMapMask.get__Comp(BSRG__CONST.HASH_KEY_DAUGHTERTABLE_POSITIONS)).set_oNaviList_VKOPF(this.oNavigationList);
		
			
		// hilfs-columns
		MyE2_Column	oColBasic = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
		
		this.oSelektor = new BSRG_K_LIST_Selector(this.oNavigationList,cBELEGTYP, this.get_MODUL_IDENTIFIER());
		
		oColBasic.add(oSelektor, E2_INSETS.I_10_2_10_2);
		oColBasic.add(new BSRG_K_LIST_BedienPanel(this,this.oMaskContainer), E2_INSETS.I_10_2_10_2);
		
		/*
		 * die liste leer initialisieren
		 */
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( Grid.PROPERTY_BORDER, new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, new Insets(2,2,10,2)); 
		
		
		this.oNavigationList.INIT_WITH_ComponentMAP(oComponentMAPList,oStyle, this.get_MODUL_IDENTIFIER());
		//this.oNavigationList.get_vectorSegmentation().set_iSegmentGroesse(10);
		
		this.add(oColBasic);
		
		
		oColBasic.add(this.oNavigationList);
		
		// liste mit den selektorbedingungen aufbauen
		oSelektor.get_oSelVector().doActionPassiv();
		
		
	}

	public E2_NavigationList 		get_oNavigationList() 	{		return oNavigationList;		}
	public BS__SETTING 				get_SETTING()			{		return SETTING;				}
	public BSRG_K_LIST_Selector 		get_oSelektor() 		{		return oSelektor;			}
}

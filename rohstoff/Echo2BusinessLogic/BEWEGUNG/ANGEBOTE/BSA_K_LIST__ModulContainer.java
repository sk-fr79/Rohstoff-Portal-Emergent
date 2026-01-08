package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

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

public class BSA_K_LIST__ModulContainer extends Project_BasicModuleContainer 
{
	private BSA_K_LIST__ComponentMAP 		oComponentMAPList = null;
	private BSA_K_MASK__ModulContainer		oMaskContainer = null;
	private E2_NavigationList 				oNavigationList = null;

	
	// das settingobject, das abnahmeangebot von angeboten unterscheidet
	private BS__SETTING					SETTING = null;
	

	private BSA_K_LIST_Selector 				oSelektor = null;
	
	
	
	
	public BSA_K_LIST__ModulContainer( String cBELEGTYP) throws myException
	{
		super(new BS__SETTING(cBELEGTYP).get_cMODULCONTAINER_LIST_IDENTIFIER());
		this.SETTING = new BS__SETTING(cBELEGTYP);
		
		this.oNavigationList = new E2_NavigationList();

		this.oComponentMAPList = new BSA_K_LIST__ComponentMAP(this,this.oNavigationList,this.SETTING);
		
		this.oMaskContainer = new BSA_K_MASK__ModulContainer(this.SETTING, this);
		
		//2011-02-02: FirstNavilist 
		this.set_oNaviListFirstAdded(this.oNavigationList);
		
		// dem positions-Element die navigationsliste uebergeben:Damit wird die liste auch beim speichern aus der maske
		// aktualisiert
		E2_ComponentMAP oMapMask = this.oMaskContainer.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN();
		((BSA_K_MASK_COMP_KP_FullDaughterPositions)oMapMask.get__Comp(BSA__CONST.HASH_KEY_DAUGHTERTABLE_POSITIONS)).set_oNaviList_VKOPF(this.oNavigationList);
		
			
		// hilfs-columns
		MyE2_Column	oColBasic = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
		
		this.oSelektor = new BSA_K_LIST_Selector(this.oNavigationList, this.get_MODUL_IDENTIFIER());
		
		oColBasic.add(oSelektor, E2_INSETS.I_10_2_10_2);
		oColBasic.add(new BSA_K_LIST_BedienPanel(this,this.oMaskContainer), E2_INSETS.I_10_2_10_2);
		
		
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


	public BSA_K_LIST__ComponentMAP 	get_oComponentMAPList() 	{		return oComponentMAPList;	}
	public BSA_K_MASK__ModulContainer get_oMaskContainer()		{		return oMaskContainer;		}
	public E2_NavigationList 		get_oNavigationList() 		{		return oNavigationList;		}
	public BS__SETTING 				get_SETTING()				{		return SETTING;				}
	public BSA_K_LIST_Selector 		get_oSelektor() 			{		return oSelektor;			}

}

package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BST_K_LIST__ModulContainer extends Project_BasicModuleContainer 
{
	private BST_K_LIST__ComponentMAP 		oComponentMAPList = null;
	private BST_K_MASK__ModulContainer		oMaskContainer = null;
	private E2_NavigationList 				oNavigationList = null;
	
	// das settingobject, das abnahmeangebot von angeboten unterscheidet
	
	private BST_K_LIST_Selector 				oSelektor = null;
	
	private BS__SETTING   					oSetting = null;

	
	public BST_K_LIST__ModulContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_TPA_LIST);

		this.oSetting = new BS__SETTING(myCONST.VORGANGSART_TRANSPORT);

		this.oNavigationList = new E2_NavigationList();

		this.oComponentMAPList = new BST_K_LIST__ComponentMAP(this,this.oNavigationList);
		
		this.oMaskContainer = new BST_K_MASK__ModulContainer(this,false);
		
		
		// dem positions-Element die navigationsliste uebergeben:Damit wird die liste auch beim speichern aus der maske
		// aktualisiert
		E2_ComponentMAP oMapMask = this.oMaskContainer.get_vCombinedComponentMAPs().get_oE2_ComponentMAP_MAIN();
		((BST_K_MASK_COMP_KP_FullDaughterPositions)oMapMask.get__Comp(BST__CONST.HASH_KEY_DAUGHTERTABLE_POSITIONS)).set_oNaviList_VKOPF(this.oNavigationList);
		
			
		// hilfs-columns
		MyE2_Column	oColBasic = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
		
		this.oSelektor = new BST_K_LIST_Selector(this.oNavigationList, this.get_MODUL_IDENTIFIER());
		
		oColBasic.add(oSelektor, E2_INSETS.I_10_2_10_2);
		oColBasic.add(new BST_K_LIST_BedienPanel(this,this.oMaskContainer), E2_INSETS.I_10_2_10_2);
		
		
		/*
		 * die liste leer initialisieren
		 */
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( Grid.PROPERTY_BORDER, new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, new Insets(2,2,10,2)); 
		
		
		this.oNavigationList.INIT_WITH_ComponentMAP(oComponentMAPList,oStyle, this.get_MODUL_IDENTIFIER());
		//this.oNavigationList.get_vectorSegmentation().set_iSegmentGroesse(10);
		
		
		//2011-12-12: aenderung der reihenfolge des zufuegens
		//alt: oColBasic.add(this.oNavigationList);
		
		this.add(oColBasic);
		this.add(this.oNavigationList);                //2011-12-12: neu 
		
		
		
		// liste mit den selektorbedingungen aufbauen
		oSelektor.get_oSelVector().doActionPassiv();
		
		
	}

	public BS__SETTING get_oSetting()
	{
		return oSetting;
	}

	public E2_NavigationList 		get_oNavigationList() 	{		return oNavigationList;		}
	public BST_K_LIST_Selector 		get_oSelektor()				{		return oSelektor;			}

}

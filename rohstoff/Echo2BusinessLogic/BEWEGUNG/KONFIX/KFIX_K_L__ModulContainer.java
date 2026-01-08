package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_BasicModuleContaier_Refreshable;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class KFIX_K_L__ModulContainer extends Project_BasicModuleContainer  implements IF_BasicModuleContaier_Refreshable
{
	private KFIX_K_L__ComponentMAP 		oComponentMAPList = null;



	//	private BSK_K_MASK__ModulContainer_NG		oMaskContainer = null;
	private E2_NavigationList 				oNavigationList = null;
	private KFIX_K_L_BedienPanel 			oBedienPanel = null;


	// das settingobject, das abnahmeangebot von angeboten unterscheidet

	private VORGANGSART 					belegTyp = null;

	private KFIX_K_L_Selector 			oSelektor = null;

	public KFIX_K_L__ModulContainer(MODUL oModul) throws myException
	{
		super(oModul.get_callKey());

		if(oModul == MODUL.NAME_MODUL_EK_KONTRAKT_LIST_NG){
			this.belegTyp = VORGANGSART.EK_KONTRAKT;
		}else if(oModul == MODUL.NAME_MODUL_VK_KONTRAKT_LIST_NG){
			this.belegTyp = VORGANGSART.VK_KONTRAKT;
		}

		this.oNavigationList = new E2_NavigationList();

		this.oComponentMAPList = new KFIX_K_L__ComponentMAP(this,this.oNavigationList,this.belegTyp);

		this.set_oNaviListFirstAdded(this.oNavigationList);

		this.oNavigationList.set_bSaveSortStatus(true);
		
		// dem positions-Element die navigationsliste uebergeben:Damit wird die liste auch beim speichern aus der maske
		// aktualisiert


		// hilfs-columns
		MyE2_Column	oColBasic = new MyE2_Column();

		this.oSelektor = new KFIX_K_L_Selector(this.oNavigationList, this.belegTyp, this.get_MODUL_IDENTIFIER());
		this.oBedienPanel=new KFIX_K_L_BedienPanel(this);
		
		oColBasic.add(oSelektor, E2_INSETS.I_10_2_10_2);
		oColBasic.add(oBedienPanel, E2_INSETS.I_10_2_10_2);

		/*
		 * die liste leer initialisieren
		 */
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( Grid.PROPERTY_BORDER, new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
		oStyle.setProperty( Grid.PROPERTY_INSETS, new Insets(2,2,10,2)); 


		this.oNavigationList.INIT_WITH_ComponentMAP(oComponentMAPList,oStyle, this.get_MODUL_IDENTIFIER());

		oColBasic.add(this.oNavigationList);

		this.add(oColBasic);



		// liste mit den selektorbedingungen aufbauen
		oSelektor.get_oSelVector().doActionPassiv();
	}

	public E2_NavigationList get_oNavigationList() 	{
		return oNavigationList;		
	}

	public KFIX_K_L_Selector get_oSelektor()			
	{		
		return oSelektor;			
	}

	public VORGANGSART getBelegTyp() { 
		return belegTyp;
	}

	@Override
	public void Prepare_for_Refresh(E2_BasicModuleContainer calingContainer) throws myException
	{
	}


	@Override
	public void Refresh(Vector<String> zusatzVector) throws myException
	{
		//fuer alle globalen aktionen wird die navigationlist neu aufgebaut
		this.oNavigationList._REBUILD_ACTUAL_SITE(null);
	}


	public boolean get_bZeigeArtikelbez2()
	{
		return this.oBedienPanel.get_oCB_SchalteArtbez2Ein().isSelected();
	}


	public boolean get_bZeigeRechnungsPositionenAn()
	{
		return this.oBedienPanel.get_oCB_SchalteRechPosEin().isSelected();
	}

	public boolean get_bZeigeFuhrenListeAn()
	{
		return this.oBedienPanel.get_oCB_SchalteFuhrenListeEin().isSelected();
	}

	public boolean get_bSortListePOS_NR()
	{
		return this.oBedienPanel.get_oCB_SortierungPOS_NUMMER().isSelected();
	}

	public KFIX_K_L__ComponentMAP get_oComponentMAPList() 
	{
		return oComponentMAPList;
	}


	public String get_cActualSorteKurz() throws myException
	{
		return  this.get_oSelektor().get_oSelSorten().get_cActualSorteKurz();
	}

	public String get_cActualSorteGanz() throws myException
	{
		return  this.get_oSelektor().get_oSelSorten().get_cActualSorteGanz();
	}

	public BS__SETTING get_settings() throws myException{
		return new BS__SETTING(this.belegTyp.get_DBValue());
	}

}

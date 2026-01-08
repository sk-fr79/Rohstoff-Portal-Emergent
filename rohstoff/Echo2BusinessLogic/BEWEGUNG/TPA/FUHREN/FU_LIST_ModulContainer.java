package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_complex_object_resetter;
import panter.gmbh.Echo2.E2_complex_object_resetter_INTERFACE;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_BasicModuleContaier_Refreshable;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RecursiveSearch.E2_SEARCH_TAGS;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.BST_K_MASK__ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FPP_BedienPanel;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FPP_ListSelector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FPT_BedienPanel;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP__ALL;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;

public class FU_LIST_ModulContainer extends Project_BasicModuleContainer implements IF_BasicModuleContaier_Refreshable, E2_complex_object_resetter_INTERFACE
{	
	public static final String NAME_OF_EDITBUTTON_IN_LIST =		"NAME_OF_EDITBUTTON_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";
	public static final String NAME_OF_CHECKBOX_IN_LIST = 		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_TEILPOS_EXTENDER_IN_LIST = 		"NAME_OF_TEILPOS_EXTENDER_IN_LIST";
	
	public static final String NAME_OF_BLOCK_LIEF = 			"NAME_OF_NAMEBLOCK_LIEF";
	public static final String NAME_OF_BLOCK_ABN = 				"NAME_OF_NAMEBLOCK_ABN";
	public static final String NAME_OF_BLOCK_SPEDITEUR = 		"NAME_OF_BLOCK_SPEDITEUR";
	
	public static final String NAME_OF_MENGENBLOCK= 			"NAME_OF_MENGENBLOCK";
	public static final String NAME_OF_MENGENBLOCK_INPUT= 		"NAME_OF_MENGENBLOCK_INPUT";
	public static final String NAME_OF_MENGENBLOCK_OUTPUT= 		"NAME_OF_MENGENBLOCK_OUTPUT";
	
	public static final String NAME_OF_PREISBLOCK_EK= 			"NAME_OF_PREISBLOCK_EK";
	public static final String NAME_OF_PREISBLOCK_VK= 			"NAME_OF_PREISBLOCK_VK";
	
	
	public static final String NAME_OF_SORTENBLOCK= 			"NAME_OF_SORTENBLOCK";
	public static final String NAME_OF_POSTENBLOCK= 			"NAME_OF_POSTENBLOCK";
	public static final String NAME_OF_LIEFERBEDINGUNGSBLOCK= 	"NAME_OF_LIEFERBEDINGUNGSBLOCK";
	public static final String NAME_OF_KENNZEICHENBLOCK= 		"NAME_OF_KENNZEICHENBLOCK";
	
	public static final String NAME_OF_DATUMSBLOCK= 			"NAME_OF_DATUMSBLOCK";
	public static final String NAME_OF_BUCHUNGSNUMMERNBLOCK= 	"NAME_OF_BUCHUNGSNUMMERNBLOCK";
	public static final String NAME_OF_LIEFERABHOLDAT= 			"NAME_OF_LIEFERABHOLDAT";

	//fahrplan-bloecke
	public static final String NAME_OF_FP_DIVERSE_BLOCK= 		"NAME_OF_FP_DIVERSE_BLOCK";
	public static final String NAME_OF_FP_CONTAINERBLOCK= 		"NAME_OF_FP_CONTAINERBLOCK";
	public static final String NAME_OF_FP_LKWBLOCK= 			"NAME_OF_FP_LKWBLOCK";
	public static final String NAME_OF_FP_INFOBLOCK= 			"NAME_OF_FP_INFOBLOCK";
	public static final String NAME_OF_FP_CODEBLOCK= 			"NAME_OF_FP_CODEBLOCK";
	public static final String NAME_OF_FP_VORMERKBLOCK= 		"NAME_OF_FP_VORMERKBLOCK";
	public static final String NAME_OF_FP_AUSFUEHRBLOCK= 		"NAME_OF_FP_AUSFUEHRBLOCK";
	public static final String NAME_OF_FP_ERFASSUNGBLOCK= 		"NAME_OF_FP_ERFASSUNGBLOCK";
	
	public static final String NAME_OF_KOSTEN_BLOCK = 			"NAME_OF_KOSTEN_BLOCK";
	
	public static final String NAME_OF_INFO_ICONSBLOCK= 			"NAME_OF_INFO_ICONSBLOCK";
	public static final String NAME_OF_INFO_STATUSBLOCK= 			"NAME_OF_INFO_STATUSBLOCK";
	public static final String NAME_OF_EXTENDER_ORTE= 				"NAME_OF_EXTENDER_ORTE";
	public static final String NAME_OF_ICON_TPA_FIELD= 				"NAME_OF_ICON_TPA_FIELD";
	public static final String NAME_OF_ICON_RECHNUNGEINGANG= 		"NAME_OF_ICON_RECHNUNGEINGANG";
	public static final String NAME_OF_ICON_BUCHUNG_FERTIG= 		"NAME_OF_ICON_BUCHUNG_FERTIG";

	public static final String NAME_OF_ROW_4_BAM= 					"NAME_OF_ROW_4_BAM";
	
	public static final String NAME_OF_ICON_CHECK_HANDELSDEF= 		"NAME_OF_ICON_CHECK_HANDELSDEF";

	public static final String NAME_OF_ICON_GEFAHR_1= 				"NAME_OF_ICON_SPALTE2_1";
	public static final String NAME_OF_ICON_GEFAHR_2= 				"NAME_OF_ICON_SPALTE2_2";
	public static final String NAME_OF_ICON_Zusatzorte= 			"NAME_OF_ICON_SPALTE2_3";
	public static final String NAME_OF_ICON_GEFAHR_4= 				"NAME_OF_ICON_SPALTE2_4";
	public static final String NAME_OF_ICON_FAHRPLAN_YES_NO_GEPLANT="NAME_OF_ICON_FAHRPLAN_YES_NO_GEPLANT";
	public static final String NAME_OF_SORTBUTTON = 				"NAME_OF_SORTBUTTON";
	
	public static final String NAME_OF_ID_BLOCK= 					"NAME_OF_ID_BLOCK";

	public static final String NAME_OF_ICON_PLANMENGE= 				"NAME_OF_ICON_PLANMENGE";
	public static final String NAME_OF_ICON_LADEMENGE= 				"NAME_OF_ICON_LADEMENGE";
	public static final String NAME_OF_ICON_ABLADEMENGE= 			"NAME_OF_ICON_ABLADEMENGE";
	public static final String NAME_OF_ICON_FERTIGBUCHUNG= 			"NAME_OF_ICON_FERTIGBUCHUNG";

	//2011-12-05: sprungmethoden aus der fuhren
	public static final String NAME_OF_BUTTON_SPRINGE_EX_FUHRE= 	"NAME_OF_BUTTON_SPRINGE_EX_FUHRE";

	
	public static final String NAME_OF_ICON_FAHRPLAN_VERPLANT= 		"NAME_OF_ICON_FAHRPLAN_VERPLANT";
	public static final String NAME_OF_ICON_FAHRPLAN_OFFEN= 		"NAME_OF_ICON_FAHRPLAN_OFFEN";

	
	public static final String NAME_OF_BEFUNDUNGSBLOCK= 			"NAME_OF_BEFUNDUNGSBLOCK";
	
	/*
	 * 2018-05-07: ah7-button in fuhrenliste
	 */
	public static final String NAME_OF_AH7_COL= 					"NAME_OF_AH7_COL";
	
	//2018-06-08: waehrungsanzeige
	public static final String NAME_OF_WAEHRUNG_ANZEIGE=   			"NAME_OF_WAEHRUNG_ANZEIGE";

	
	private MyE2_Column 		oColumnBasic = null;
	private E2_NavigationList 	oNavList = 	null;
	
//	
//	/*
//	 * mask-container fuer die beanstandungsmeldung,
//	 * wird beim ersten aufruf intialisiert
//	 */
//	private BAMF_MASK_ModulContainer		oMaskBAM	= null;
//	
	
	/*
	 * mask-container fuer die fuhreneingabe
	 */
	private FU_MASK_ModulContainer		oMaskFuhre = null;
	private FU_LIST_ComponentMAP 		oListMap = null;
	
	/*
	 * TPA-Mask-Container fuer die openTPA-Buttons in der liste
	 */
	private BST_K_MASK__ModulContainer   oBasicModuleContainerTPA = null;
	
	
	//2011-03-01: adress-maske fuer sprungbuttons
	private FS_ModulContainer_MASK       oFirmenMask = null;
	



	private boolean bAusFahrplan = false;
	
	
	//2011-12-06: speed-test
	private E2_complex_object_resetter oObjectResetter = null;
	
	
	//2014-06-02: veroeffentlichen des bedienpanels in der fuhrenzentrale
	private FU_LIST_BedienPanel_FuhrenZentrale oPanel = null;
	
	
	public FU_LIST_ModulContainer(String BASIC_MODULE_NAME) throws myException
	{
		super(BASIC_MODULE_NAME);
		
		this.oColumnBasic = new MyE2_Column(new Style_Column_Normal(0, new Insets(2,2)));
		
		this.add(this.oColumnBasic);
		
		this.oBasicModuleContainerTPA = new BST_K_MASK__ModulContainer(null,false);
		
		this.oFirmenMask = new FS_ModulContainer_MASK();
		
		try
		{
			this.oNavList = new E2_NavigationList();
			
			//2012-01-11: navigationlist wird getagged, damit sie aus dem zuordnungsbutton fuer UMA-vertraege gefunden wird
			this.oNavList.EXT().add_SEARCH_TAG(E2_SEARCH_TAGS.SEARCH_TAGS.NAVIGATIONLIST_IN_FUHRENZENTRALE_FAHRPLAN_FAHRPLANPOOL);
	

			if (BASIC_MODULE_NAME.equals(E2_MODULNAMES.NAME_MODUL_FAHRTENPOOL) || BASIC_MODULE_NAME.equals(E2_MODULNAMES.NAME_MODUL_FAHRPLAN))
			{
				this.bAusFahrplan = true;			
			}
			
			
			this.oMaskFuhre = new FU_MASK_ModulContainer(null, this.oNavList,this.bAusFahrplan,true);
			this.oListMap = new FU_LIST_ComponentMAP(new FU_LIST_SQLFieldMAP(BASIC_MODULE_NAME),this,BASIC_MODULE_NAME,this.oBasicModuleContainerTPA);
			
			
			// segmentlaenge definieren
			//this.oNavList.get_vectorSegmentation().set_iSegmentGroesse(5);

			
			// liste fertig definieren
			this.oNavList.INIT_WITH_ComponentMAP(oListMap, null, this.get_MODUL_IDENTIFIER());

			
			//2011-01-25: manuelles zufuegen der navilistfirst added wegen sprungmethode in dieses Modul
			this.set_oNaviListFirstAdded(this.oNavList);
			
			/*
			 * maske zusammenstellen
			 */
			if (BASIC_MODULE_NAME.equals(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER))
			{
				
				
				FU_LIST_SELECTOR oSel = new FU_LIST_SELECTOR(this.oNavList, this.get_MODUL_IDENTIFIER());
				this.oColumnBasic.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Fuhren-Zentrale"),MyE2_Label.STYLE_TITEL_BIG()),E2_INSETS.I_2_2_2_2),new Insets(0,0,0,10));
				this.oColumnBasic.add(oSel,	new Insets(0,0,0,10));
				this.oColumnBasic.add(this.oPanel=new FU_LIST_BedienPanel_FuhrenZentrale(this),	new Insets(0,0,0,10));
				this.oColumnBasic.add(this.oNavList);
				oSel.get_oSelVector().doActionPassiv();
				
				this.oObjectResetter = new E2_complex_object_resetter(oSel, oPanel.get_oSearchObject(), this.oNavList);

			}
			else if (BASIC_MODULE_NAME.equals(E2_MODULNAMES.NAME_MODUL_FAHRTENPOOL))
			{
				FPP_BedienPanel oPanel = null;
				
				FPP_ListSelector  oSel = new FPP_ListSelector(this.oNavList);
				this.oColumnBasic.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Fahrtenpool"),MyE2_Label.STYLE_TITEL_BIG()),E2_INSETS.I_2_2_2_2),new Insets(0,0,0,10));
				this.oColumnBasic.add(oSel,	new Insets(0,0,0,10));
				this.oColumnBasic.add(oPanel = new FPP_BedienPanel(this),	new Insets(0,0,0,10));
				this.oColumnBasic.add(this.oNavList);
				oSel.get_oSelVector().doActionPassiv();
				
				
				this.oObjectResetter = new E2_complex_object_resetter(null, oPanel.get_oSearchObject(), this.oNavList);

			}
			else if (BASIC_MODULE_NAME.equals(E2_MODULNAMES.NAME_MODUL_FAHRPLAN))
			{
				
				this.oColumnBasic.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Fahrplan"),MyE2_Label.STYLE_TITEL_BIG()),E2_INSETS.I_2_2_2_2),new Insets(0,0,0,10));
				this.oColumnBasic.add(new FPT_BedienPanel(this),	new Insets(0,0,0,10));
				//hier wird die liste "geleert"
				FP__ALL.set_BedingungenForFahrplanSQLFieldMAP_CLOSED(this.oListMap.get_oSQLFieldMAP());   //immer false-bedingung im geschlossenen zustand
				this.oColumnBasic.add(this.oNavList);
				this.oNavList._REBUILD_COMPLETE_LIST("");
				
				this.oObjectResetter = new E2_complex_object_resetter(null, null, this.oNavList);
			}
			
			
		}
		catch (myException ex)
		{
			ex.printStackTrace();
			throw new myException(ex);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("Error :"+ex.getLocalizedMessage());
		}
		
		
	}

	

	public FS_ModulContainer_MASK get_oFirmenMask() 
	{
		return oFirmenMask;
	}



	public E2_NavigationList get_oNavList()
	{
		return oNavList;
	}

	public FU_MASK_ModulContainer get_oMaskFuhre() throws myException
	{
		return oMaskFuhre;
	}

	public FU_LIST_ComponentMAP get_oE2_ComponentMAPList()
	{
		return oListMap;
	}


	@Override
	public void Refresh(Vector<String> zusatzVector) throws myException
	{
		//fuer alle globalen aktionen wird die navigationlist neu aufgebaut
		this.oNavList._REBUILD_ACTUAL_SITE(null);
		
	}



	@Override
	public void Prepare_for_Refresh(E2_BasicModuleContainer calingContainer) throws myException
	{
		
	}



	@Override
	public E2_complex_object_resetter get_Object_resetter() throws myException
	{
		return this.oObjectResetter;
	}



	public FU_LIST_BedienPanel_FuhrenZentrale get_oFuhrenzentraleBedienPanel() {
		return this.oPanel;
	}

	
}

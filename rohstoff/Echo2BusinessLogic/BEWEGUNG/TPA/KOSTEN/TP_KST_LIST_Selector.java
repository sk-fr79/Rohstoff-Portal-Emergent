package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;


import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlanCourier;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class TP_KST_LIST_Selector extends E2_ListSelectorContainer
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 					oSelVector = null;
	private multiDropDown 									SelMultiStart = null;
	private multiDropDown 									SelMultiZiel = null;

	private TP_KST_LIST_Selector_MDD_TPSorten    	selTP_Sorten = 			new TP_KST_LIST_Selector_MDD_TPSorten();
	private TP_KST_LIST_Selector_MDD_Spedition   	sel_Spedition= 			new TP_KST_LIST_Selector_MDD_Spedition();
	private TP_KST_LIST_Selector_MDD_FUSorten    	selFU_Sorten= 			new TP_KST_LIST_Selector_MDD_FUSorten();
	private TP_KST_LIST_SelectorLadedatum_von_bis 	selLadedatumVonBis = 	new TP_KST_LIST_SelectorLadedatum_von_bis();
	
	public TP_KST_LIST_Selector(E2_NavigationList oNavigationList, String cMODULE_KENNER) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);

		oSelVector.add(selTP_Sorten);
		oSelVector.add(sel_Spedition);
		oSelVector.add(selFU_Sorten);
		oSelVector.add(selLadedatumVonBis);
		
		
		//2013-03-05: neuer db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODULE_KENNER);
		
		this.SelMultiStart = new multiDropDown(true,TP_KST__CONST.get_KernStringNormierterOrt("ADR_START")+"='#WERT#'");
		this.SelMultiZiel = new multiDropDown(false,TP_KST__CONST.get_KernStringNormierterOrt("ADR_ZIEL")+"='#WERT#'");
		
		this.oSelVector.add(oDB_BasedSelektor);
		this.oSelVector.add(SelMultiStart);
		this.oSelVector.add(SelMultiZiel);

		MyE2_CheckBox  chNullPreiseRaus = new MyE2_CheckBox();
		this.oSelVector.add(new E2_ListSelectorStandard(chNullPreiseRaus, VPOS_TPA.einzelpreis.t().s()+">0", ""));
		
		
		MyE2_Grid oGridInnen = new MyE2_Grid(6,0);
		this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
		
		oGridInnen.add(new ownTitel(""), E2_INSETS.I_2_2_2_2);
		oGridInnen.add(new ownTitel("Ort/Normalisiert"), E2_INSETS.I_2_2_2_2);
		oGridInnen.add(new ownTitel(""), E2_INSETS.I_2_2_2_2);
		oGridInnen.add(new ownTitel(""), E2_INSETS.I_2_2_2_2);
		oGridInnen.add(new ownTitel(""), E2_INSETS.I_2_2_2_2);
		oGridInnen.add(new ownTitel(""), E2_INSETS.I_2_2_2_2);
		
		
		oGridInnen.add(new ownTitel("von"), E2_INSETS.I_2_2_2_2);
		oGridInnen.add(SelMultiStart.get_oComponentWithoutText());
		oGridInnen.add(new ownTitel("Sorte (Spedition)"), E2_INSETS.I(20,2,2,2));
		oGridInnen.add(this.selTP_Sorten.get_oComponentForSelection(),E2_INSETS.I_2_2_2_2);
		oGridInnen.add(new ownTitel("Sorte (Fuhre)"), E2_INSETS.I(20,2,2,2));
		oGridInnen.add(this.selFU_Sorten.get_oComponentForSelection(),E2_INSETS.I_2_2_2_2);
		
		oGridInnen.add(new ownTitel("nach"), E2_INSETS.I_2_2_2_2);
		oGridInnen.add(SelMultiZiel.get_oComponentWithoutText());
		oGridInnen.add(new ownTitel("Spedition"), E2_INSETS.I(20,2,2,2));
		oGridInnen.add(this.sel_Spedition.get_oComponentForSelection(),E2_INSETS.I_2_2_2_2);
		oGridInnen.add(new ownTitel("0-Preise ausblenden"),  E2_INSETS.I(20,2,2,2));
		oGridInnen.add(chNullPreiseRaus, E2_INSETS.I_2_2_2_2);
		
		oGridInnen.add(new ownTitel("Ladedatum"), E2_INSETS.I_2_2_2_2);
		oGridInnen.add(selLadedatumVonBis.get_oComponentWithoutText());
		oGridInnen.add(new ownTitel(""), E2_INSETS.I_2_2_2_2);
		oGridInnen.add(new ownTitel(""), E2_INSETS.I_2_2_2_2);
	
		oGridInnen.add(oDB_BasedSelektor.get_oComponentForSelection(new MyE2_String("Diverse: "),100), E2_INSETS.I_2_2_2_2);
		
		

	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}
	
	
	
	
	private class multiDropDown extends E2_ListSelectorMultiDropDown_STD {

		public multiDropDown(boolean bIstStart,  String WhereStringSchablone) throws myException {
			super(new MyE2_SelectField(new TP_KST_SELECT_Normalisierte_Orte(bIstStart).toString()+" ORDER BY 1", false, true, false, false), WhereStringSchablone);
			this.get_oSelFieldBasis().setWidth(new Extent(300));
			this.get_oSelFieldBasis().setFont(new E2_FontPlanCourier());
			this.set_bCopyFontAndSizeOfOrigSelectField(true);
			if (bIstStart) {
				this.get_vAgents4ActiveComponentsBeforeSelection().add(new ownActionAgentRefreshZiel());
			}
		}

		private class ownActionAgentRefreshZiel extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				if (S.isEmpty(multiDropDown.this.get_WhereBlock())) {
					TP_KST_LIST_Selector.this.SelMultiZiel.get_oSelFieldBasis().set_Fuelle_Neu(new TP_KST_SELECT_Normalisierte_Orte(false).toString()+" ORDER BY 1");
				} else {
					DEBUG.System_println(multiDropDown.this.get_WhereBlock(),DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
					
					String cWhereReduzierend = multiDropDown.this.get_WhereBlock();
					//der where-block liefert eine durch or verbundene liste mit moeglichen start-orten, hier muss nur der ADRESS-TABLE - bezeichner ausgetauscht werden
					// (heiﬂt im Start  ADR_START, in der Selektor-Query aber ADR2
					
					cWhereReduzierend = cWhereReduzierend.replace("ADR_START.ORT", "ADR2.ORT");
					TP_KST_LIST_Selector.this.SelMultiZiel.LEER_MACHEN();
					DEBUG.System_println(new TP_KST_SELECT_Normalisierte_Orte(false).toString()+" AND "+cWhereReduzierend+" ORDER BY 1", DEBUG.DEBUG_FLAGS.MARTINS_EIGENER.name());
					TP_KST_LIST_Selector.this.SelMultiZiel.get_oSelFieldBasis().set_Fuelle_Neu(new TP_KST_SELECT_Normalisierte_Orte(false).toString()+" AND "+cWhereReduzierend+" ORDER BY 1");
				}
			}
		}
		
		
		@Override
		public E2_BasicModuleContainer get_PopupContainer() throws myException {
			return new ownBasicContainer();
		}
		
		private class ownBasicContainer extends E2_BasicModuleContainer {
		}
		
		
		
	}
	
	
	
	private class ownTitel extends MyE2_Label {
		public ownTitel(String cText) {
			super(new MyE2_String(cText),new E2_FontItalic(-2));
		}
	}
	
}

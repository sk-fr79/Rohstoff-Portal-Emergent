package panter.gmbh.Echo2.ListAndMask.List.calculation;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorMaskHighlight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_COLUMNS_TO_CALC;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


/**
 * button, der listenkomponenten mitgegeben werden kann um summen/berechnungen spalten zu machen
 * @author martin
 *
 */
public class LIST_TITEL_CalcButton extends CALC_Button implements CALC_ZUSATZKOMPONENTE_IF{
   
	
	private String 				c_LISTCONTAINER_KENNER	=			null;
	private MyE2_String     	c_TEXT_4_POPUPWINDOWTITLEBAR	=	null;
	private MyE2_String     	c_TEXT_4_HEADLINE_INNEN	=			null;
	private MyE2_String     	c_BeschriftungSumme	=				null;
	private int   				i_NachkommastellenSumme =			0;
	private boolean   			b_AbfrageViaQuery =					false;
	private CALC_Rule_ABSTRACT  o_RuleSumme = 						null; 
	private MyE2IF__Component 	o_Component_from_List = 			null;
	private String    			c_toolTips = 						null;
	private String    			c_ADDON_WINDOW_SIZE_STORE = 		"1";          //falls mehrere knoepfe in einer spalte, dann diese zusatzkennung
	 
	private boolean   			b_ShowAddonLineInListTitel = 		false;
	
	private MyE2_Grid  	        grid_DirektAnzeige = 				new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	
	public LIST_TITEL_CalcButton(	MyE2IF__Component 	oComponent_from_List, 
									String 				cLISTCONTAINER_KENNER,
									MyE2_String     	cTEXT_4_POPUPWINDOWTITLEBAR,
									MyE2_String     	cTEXT_4_HEADLINE_INNEN,
									MyE2_String     	cBeschriftungSumme,
									int   				iNachkommastellenSumme,
									boolean   			bAbfrageViaQuery) throws myException {
		
		super(E2_ResourceIcon.get_RI("calc_mini.png"));
		
		this.o_Component_from_List = oComponent_from_List;
		
		this.c_LISTCONTAINER_KENNER	=			cLISTCONTAINER_KENNER;
		this.c_TEXT_4_POPUPWINDOWTITLEBAR	=	cTEXT_4_POPUPWINDOWTITLEBAR;
		this.c_TEXT_4_HEADLINE_INNEN	=		cTEXT_4_HEADLINE_INNEN;
		this.c_BeschriftungSumme	=			cBeschriftungSumme;
		this.i_NachkommastellenSumme =			iNachkommastellenSumme;
		this.b_AbfrageViaQuery =				bAbfrageViaQuery;
		
		this.o_RuleSumme = null;
		
		this._define();
		
	}

	public LIST_TITEL_CalcButton(		MyE2IF__Component 		oComponent_from_List,
										RECORD_COLUMNS_TO_CALC 	oRecCols) throws myException {

		super(E2_ResourceIcon.get_RI("calc_mini.png"));
		
		this.o_Component_from_List = 			oComponent_from_List;
		
		this.c_LISTCONTAINER_KENNER	=			oRecCols.get_MODULNAME_LISTE_cUF();
		this.c_TEXT_4_POPUPWINDOWTITLEBAR	=	new MyE2_String(oRecCols.get_TEXT4WINDOWTITLE_cUF_NN(oRecCols.get_TEXT4SUMMATION_cUF_NN("Summe:")));
		this.c_TEXT_4_HEADLINE_INNEN	=		new MyE2_String(oRecCols.get_TEXT4TITLE_IN_WINDOW_cUF_NN(oRecCols.get_TEXT4SUMMATION_cUF_NN("Summe:")));
		this.c_BeschriftungSumme	=			new MyE2_String(oRecCols.get_TEXT4SUMMATION_cUF_NN("Summe:"));
		this.i_NachkommastellenSumme =			oRecCols.get_NUMBER_DECIMALS_lValue(0l).intValue();
		this.b_AbfrageViaQuery =				oRecCols.is_SUMMATION_VIA_QUERY_YES();
		this.o_RuleSumme = null;
		this.c_toolTips = 						oRecCols.get_TOOLTIPS_cUF_NN("");
		this.c_ADDON_WINDOW_SIZE_STORE = 		oRecCols.get_ID_COLUMNS_TO_CALC_cUF();
		
		this.b_ShowAddonLineInListTitel = 		oRecCols.is_SHOW_LINE_IN_LISTHEADER_YES();
		
		if (S.isFull(oRecCols.get_VALIDATION_TAG_cUF_NN(""))) {
			this.add_GlobalAUTHValidator_AUTO(oRecCols.get_VALIDATION_TAG_cUF_NN(""));
		}
		
		this._define();
}

	
	
	
	private void _define() throws myException {
		if (this.b_AbfrageViaQuery) {
			if (this.o_Component_from_List instanceof MyE2IF__DB_Component) {
				this.o_RuleSumme = new CALC_Rule_SUMMATION_LIST_COL((MyE2IF__DB_Component)this.o_Component_from_List);
			} else {
				throw new myException("No DB-Component Used for Calculation!");
			}
		} else {
			this.o_RuleSumme = new CALC_Rule_InterpretVisibleLabel(this.o_Component_from_List);
		}
		
		if (S.isEmpty(this.c_BeschriftungSumme)) {
			this.c_BeschriftungSumme = new MyE2_String("Summe: ");
			if (S.isFull(this.o_Component_from_List.EXT().get_cList_or_Mask_Titel())) {
				this.c_BeschriftungSumme.addString(this.o_Component_from_List.EXT().get_cList_or_Mask_Titel());
			}
		}
	
		this.o_RuleSumme.set_cINFOTEXT(this.c_BeschriftungSumme);
		this.o_RuleSumme.set_iDECIMALNUMBERS(this.i_NachkommastellenSumme);
		this.o_RuleSumme._SET_ShowOnlyInPopup(!this.b_ShowAddonLineInListTitel);

		this.grid_DirektAnzeige.setVisible(false);
		
		if (S.isFull(this.c_toolTips)) {
			this.setToolTipText(this.c_toolTips);
		} else {
			this.setToolTipText(new MyE2_String("Summation der Spalte: ",true,this.o_Component_from_List.EXT().get_cList_or_Mask_Titel().CTrans(),false).CTrans());
		}
	}
	
	
	
	@Override
	public CALC_Container_IF   _GENERATE_Container() throws myException {
		//den container fertigstellen
		CALC_POPUP_Window_4_NAVILIST  oCalcContainer = new CALC_POPUP_Window_4_NAVILIST();
		oCalcContainer._SET_CALC_CLASS_MARKER(this.c_LISTCONTAINER_KENNER+"@"+this.o_Component_from_List.EXT().get_C_HASHKEY()+"@"+this.c_ADDON_WINDOW_SIZE_STORE);
		oCalcContainer._SET_CONTAINER_TEXT4TITELBAR(this.c_TEXT_4_POPUPWINDOWTITLEBAR);
		oCalcContainer._SET_CONTAINER_HEADLINE(this.c_TEXT_4_HEADLINE_INNEN);
		
		oCalcContainer._GET_CALC_RULES().add(new CALC_Rule_AnzahlAuswahl());
		oCalcContainer._GET_CALC_RULES().add(this.o_RuleSumme);

		return oCalcContainer;
	}

	@Override
	public Component _GET_ZUSATZ_Komponente() {
		return this.grid_DirektAnzeige;
	}

	@Override
	public void _FILL_AND_SHOW_ZUSATZ_Komponente(String cErgebnisFromCalcRule, String cTooltips) throws myException {
		GridLayoutData oGL = MyE2_Grid.LAYOUT_RIGHT_CENTER(E2_INSETS.I(0,0,1,0), new E2_ColorMaskHighlight(), 1, 1);
		MyE2_Label oLabel = new MyE2_Label(cErgebnisFromCalcRule, new E2_FontItalic());
		oLabel.setToolTipText(cTooltips);
		this.grid_DirektAnzeige.add(oLabel,oGL);
		this.grid_DirektAnzeige.setVisible(true);
	}

	@Override
	public void _RESET_ZUSATZ_Komponente() {
		this.grid_DirektAnzeige.removeAll();
		this.grid_DirektAnzeige.setVisible(false);
	}

	

	
	
}

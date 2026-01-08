package panter.gmbh.Echo2.ListAndMask.List.calculation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorMaskHighlight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/*
 * moegliche implementierung eines CALC_Container_IF
 */
public class CALC_POPUP_Window_4_NAVILIST extends E2_BasicModuleContainer implements CALC_Container_IF {
	
	private E2_NavigationList     		o_NaviList = 				null;
	private Vector<CALC_Rule_ABSTRACT>	v_CalcRules = 				new Vector<CALC_Rule_ABSTRACT>();
	private MyE2_String  				c_CONTAINER_TEXT4TITELBAR = null;
	private MyE2_String  				c_CONTAINER_HEADLINE = 		null;
	private CALC_Button  				o_CALC_Button = 			null;
	
	private MyE2_CheckBox       		oCB_AlleInListe = 			null;
	private MyE2_CheckBox       		oCB_AlleAufSeite = 			null;
	private MyE2_CheckBox       		oCB_AlleSelektierten = 		null;
	
	private Vector<String> 				vID_AlleInListe = 			new Vector<String>();
	private Vector<String> 				vID_AlleAufSeite = 			new Vector<String>();
	private Vector<String> 				vID_AlleSelektierten = 		new Vector<String>();

	private HashMap<MyE2_CheckBox, Vector<String>>  		hmZuordnungButtonZuVectorIDs = 	new HashMap<MyE2_CheckBox, Vector<String>>();
	
	private LinkedHashMap<CALC_Rule_ABSTRACT, MyE2_Label>  	hmErgebnisLabels = 				new LinkedHashMap<CALC_Rule_ABSTRACT, MyE2_Label>();
	
	private MyE2_Grid  					oGRID_INNEN = 		new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	
	
	private boolean 		 			b_AlleInListe = 			true;
	private boolean 					b_AlleAufSeite = 			true;
	private boolean 					b_AlleSelektierten = 		true;
	
	
	private MyE2_Button  				bt_CLOSE_Window = new MyE2_Button("Fenster schlieﬂen",MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
	
	
	public CALC_POPUP_Window_4_NAVILIST() {
		super();
		this.add(this.oGRID_INNEN, E2_INSETS.I_0_0_0_0);
		this.add(new E2_ComponentGroupHorizontal(0, this.bt_CLOSE_Window , E2_INSETS.I(0,0,10,0)), E2_INSETS.I(5,15,2,0));
		
		this.bt_CLOSE_Window.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				CALC_POPUP_Window_4_NAVILIST.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		});
	}

	


	@Override
	public void _FILL_INTERNAL_Container() throws myException {
		
		this.vID_AlleInListe.removeAllElements();
		this.vID_AlleAufSeite.removeAllElements();
		this.vID_AlleSelektierten.removeAllElements();
		
		this.oGRID_INNEN.removeAll();
		
		this.vID_AlleInListe.addAll(this.o_NaviList.get_vectorSegmentation());
		this.vID_AlleAufSeite.addAll(this.o_NaviList.get_vActualID_Segment());
		this.vID_AlleSelektierten.addAll(this.o_NaviList.get_vSelectedIDs_Unformated());
		
		this.oCB_AlleInListe = 		new MyE2_CheckBox(new MyE2_String("Alle Zeilen in der Selektion",true,"  ("+vID_AlleInListe.size()+")",false));
		this.oCB_AlleAufSeite = 	new MyE2_CheckBox(new MyE2_String("Alle Zeilen auf der momentanen Seite",true,"  ("+vID_AlleAufSeite.size()+")",false));
		this.oCB_AlleSelektierten = new MyE2_CheckBox(new MyE2_String("Alle ausgew‰hlten Zeilen",true,"  ("+vID_AlleSelektierten.size()+")",false));

		this.oCB_AlleInListe.add_oActionAgent(new ownActionSelectAuswahl());
		this.oCB_AlleAufSeite.add_oActionAgent(new ownActionSelectAuswahl());
		this.oCB_AlleSelektierten.add_oActionAgent(new ownActionSelectAuswahl());
		
		this.hmZuordnungButtonZuVectorIDs.put(this.oCB_AlleInListe, 		this.vID_AlleInListe);
		this.hmZuordnungButtonZuVectorIDs.put(this.oCB_AlleAufSeite, 		this.vID_AlleAufSeite);
		this.hmZuordnungButtonZuVectorIDs.put(this.oCB_AlleSelektierten, 	this.vID_AlleSelektierten);
		
		ActionAgent_RadioFunction_CheckBoxList  oRadioBT_AuswahlRange = new ActionAgent_RadioFunction_CheckBoxList(false);
		if (this.b_AlleInListe) 		{oRadioBT_AuswahlRange.add_CheckBox(this.oCB_AlleInListe);}
		if (this.b_AlleAufSeite) 		{oRadioBT_AuswahlRange.add_CheckBox(this.oCB_AlleAufSeite);}
		if (this.b_AlleSelektierten) 	{oRadioBT_AuswahlRange.add_CheckBox(this.oCB_AlleSelektierten);}


		MyE2_Grid  oGridInnen = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		this.oGRID_INNEN.add(oGridInnen, E2_INSETS.I(2,2,2,2));
		
		if(S.isFull(this._GET_CONTAINER_HEADLINE())) {
			oGridInnen.add(new MyE2_Label(this._GET_CONTAINER_HEADLINE(),MyE2_Label.STYLE_TITEL_NORMAL()),2, E2_INSETS.I(2,2,2,2));
		}
		
		if (this.b_AlleSelektierten) 	{oGridInnen.add(this.oCB_AlleSelektierten,2, 	E2_INSETS.I(2,2,2,2));}
		if (this.b_AlleAufSeite) 		{oGridInnen.add(this.oCB_AlleAufSeite,2, 		E2_INSETS.I(2,2,2,2));}
		if (this.b_AlleInListe) 		{oGridInnen.add(this.oCB_AlleInListe,2, 		E2_INSETS.I(2,2,2,2));}
		
		oGridInnen.add_Separator(E2_INSETS.I(2,2,2,2));

		MyE2_Grid oGridErgebnisse = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
		oGridInnen.add(oGridErgebnisse,E2_INSETS.I(0,10,0,0));
		
		//fuer jede regel einen label bauen, der dann nach auswahl der datensaetze gefuellt wird
		for (CALC_Rule_ABSTRACT oRule: this.v_CalcRules) {
			this.hmErgebnisLabels.put(oRule, new MyE2_Label("",true));
		}
		
		for (CALC_Rule_ABSTRACT oRule: this.hmErgebnisLabels.keySet()) {
			oGridErgebnisse.add(new MyE2_Label(oRule.get_cINFOTEXT()),MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2)));
			oGridErgebnisse.add(this.hmErgebnisLabels.get(oRule),MyE2_Grid.LAYOUT_RIGHT_CENTER(E2_INSETS.I(2,2,2,2)));
		}

		//falls eins selektion vorliegt, dann starten
		if (this.vID_AlleSelektierten.size()>0 && this.b_AlleSelektierten) {
			this.oCB_AlleSelektierten.doAction();
			this.oCB_AlleSelektierten.setSelected(true);
		} else {
			if (this.vID_AlleAufSeite.size()>0 && this.b_AlleAufSeite) {
				this.oCB_AlleAufSeite.doAction();
				this.oCB_AlleAufSeite.setSelected(true);
			}
		}

		
		
	}


	@Override
	public void _SHOW_Container() throws myException {
		this.CREATE_AND_SHOW_POPUPWINDOW(	new Extent(480), 
											new Extent(330), 
												this._GET_CONTAINER_TEXT4TITELBAR()==null
												?	new MyE2_String("Ergebnisse"):
												  	this._GET_CONTAINER_TEXT4TITELBAR());
		
	}


	@Override
	public Vector<CALC_Rule_ABSTRACT> _GET_CALC_RULES() throws myException {
		return this.v_CalcRules;
	}



	@Override
	public String _GET_CALC_CLASS_MARKER() {
		return this.get_cADDON_TO_CLASSNAME();
	}


	@Override
	public void _SET_CALC_CLASS_MARKER(String cCALC_CLASS_MARKER) {
		this.set_cADDON_TO_CLASSNAME(cCALC_CLASS_MARKER);
	}


	@Override
	public void _SET_CALLING_CALC_BUTTON(CALC_Button oCALC_BUTTON) {
		this.o_CALC_Button = oCALC_BUTTON;
	}


	@Override
	public CALC_Button _GET_CALLING_CALC_BUTTON() {
		return this.o_CALC_Button;
	}
	

	private class ownActionSelectAuswahl extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			CALC_POPUP_Window_4_NAVILIST oThis = CALC_POPUP_Window_4_NAVILIST.this;
			MyE2_CheckBox  oCB = (MyE2_CheckBox)oExecInfo.get_MyActionEvent().getSource();

			//pruefen, ob der button das interface CALC_ZUSATZKOMPONENTE_IF erfuellt
			if (oThis._GET_CALLING_CALC_BUTTON() instanceof CALC_ZUSATZKOMPONENTE_IF) {
				if (((CALC_ZUSATZKOMPONENTE_IF)oThis._GET_CALLING_CALC_BUTTON())._GET_ZUSATZ_Komponente()!=null) {
					((CALC_ZUSATZKOMPONENTE_IF)oThis._GET_CALLING_CALC_BUTTON())._RESET_ZUSATZ_Komponente();
				}
			}
			
			Vector<String> vIDs = oThis.hmZuordnungButtonZuVectorIDs.get(oCB);
			
			GridLayoutData LayoutRightWarn = MyE2_Grid.LAYOUT_RIGHT_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorHelpBackground(), 1, 1);
			GridLayoutData LayoutRightNormal = MyE2_Grid.LAYOUT_RIGHT_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorMaskHighlight(), 1, 1);
			
			GridLayoutData  oLayout4Label = LayoutRightNormal;
			Font 			oFontLabel = 	new E2_FontBold(2);  			
			
			for (CALC_Rule_ABSTRACT oRule: oThis.v_CalcRules) {
				
				MyE2_Label oLabelErgebnis = oThis.hmErgebnisLabels.get(oRule);
				oLabelErgebnis.setFont(new E2_FontBold(2));
				oLabelErgebnis.setLayoutData(LayoutRightNormal);   //standard herstellen
				
				String c_Ergebnis = "<<Error>>";
				try {
					BigDecimal bdErgebnis = oRule.get_bdVALUE_ERGEBNISS(vIDs);
					if (bdErgebnis == null) {   //null heiﬂt normalerweise: fehlerzustand
						if (oRule.get_cFehlerInfo4User()!=null) {
							c_Ergebnis = oRule.get_cFehlerInfo4User().COrig();
						} else {
							c_Ergebnis = "<<Error>>";
						}
						oLayout4Label 	= LayoutRightWarn;
						oFontLabel 		= new E2_FontPlain();
						
					} else {
						c_Ergebnis = MyNumberFormater.formatDez(bdErgebnis, oRule.get_iDECIMALNUMBERS(), true);	
						String cToolTips = oRule.get_cINFOTEXT().CTrans()+" ("+vIDs.size()+" "+new MyE2_String("Datens‰tze").CTrans()+")";
						if (!oRule._GET_ShowOnlyInPopup() && oThis._GET_CALLING_CALC_BUTTON() instanceof CALC_ZUSATZKOMPONENTE_IF) {
							if (((CALC_ZUSATZKOMPONENTE_IF)oThis._GET_CALLING_CALC_BUTTON())._GET_ZUSATZ_Komponente()!=null) {
								((CALC_ZUSATZKOMPONENTE_IF)oThis._GET_CALLING_CALC_BUTTON())._FILL_AND_SHOW_ZUSATZ_Komponente(c_Ergebnis, cToolTips);
							}
						}
					}
				} catch (Exception e) {
					throw new myException("Unknown error");
				}
				oLabelErgebnis.setText(c_Ergebnis);
				oLabelErgebnis.setLayoutData(oLayout4Label);
				oLabelErgebnis.setFont(oFontLabel);
			}
		}
		
	}



	public boolean _get_AlleInListe() {
		return b_AlleInListe;
	}


	public void _set_bAlleInListe(boolean bAlleInListe) {
		this.b_AlleInListe = bAlleInListe;
	}


	public boolean _get_AlleAufSeite() {
		return b_AlleAufSeite;
	}


	public void _set_bAlleAufSeite(boolean bAlleAufSeite) {
		this.b_AlleAufSeite = bAlleAufSeite;
	}


	public boolean _get_AlleSelektierten() {
		return b_AlleSelektierten;
	}


	public void _set_bAlleSelektierten(boolean bAlleSelektierten) {
		this.b_AlleSelektierten = bAlleSelektierten;
	}


	@Override
	public MyE2_String _GET_CONTAINER_TEXT4TITELBAR() {
		return this.c_CONTAINER_TEXT4TITELBAR;
	}


	@Override
	public void _SET_CONTAINER_TEXT4TITELBAR(MyE2_String cCONTAINER_TEXT4TITELBAR) {
		this.c_CONTAINER_TEXT4TITELBAR = cCONTAINER_TEXT4TITELBAR;
		
	}


	@Override
	public MyE2_String _GET_CONTAINER_HEADLINE() {
		return this.c_CONTAINER_HEADLINE;
	}


	@Override
	public void _SET_CONTAINER_HEADLINE(MyE2_String cCONTAINER_HEADLINE) {
		this.c_CONTAINER_HEADLINE = cCONTAINER_HEADLINE;
	}


	@Override
	public void _REGISTER_NAVILIST_TO_CALC_RULES(E2_NavigationList oNAVIGATIONLIST) throws myException  {
		for (CALC_Rule_ABSTRACT oRULE: this._GET_CALC_RULES()) {
			oRULE._SET_NAVILIST_THIS_BELONGS_TO(oNAVIGATIONLIST);
		}
	}


	@Override
	public void _SET_NAVILIST_THIS_BELONGS_TO(E2_NavigationList oNAVI_LIST) throws myException {
		this.o_NaviList = oNAVI_LIST;
	}


	@Override
	public E2_NavigationList _GET_NAVILIST_THIS_BELONGS_TO() throws myException {
		return this.o_NaviList;
	}
	
	
}

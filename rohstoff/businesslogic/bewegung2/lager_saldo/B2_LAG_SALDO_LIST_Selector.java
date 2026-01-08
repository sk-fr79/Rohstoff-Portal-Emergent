package rohstoff.businesslogic.bewegung2.lager_saldo;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Factory_ForLager;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Hauptsorte;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Sorte;
import rohstoff.businesslogic.bewegung2.global.ENUM_SELEKTOR_Lagertyp;
import rohstoff.businesslogic.bewegung2.lager_saldo.B2_LAG_SALDO_CONST.TRANSLATOR;


public class B2_LAG_SALDO_LIST_Selector extends E2_ExpandableRow {

	/**
	 * 
	 */
	private UTIL_MultiSelectField_Factory_ForLager  		oSelMulti_Lager 			= null;

	private UTIL_MultiSelectField_Hauptsorte		 		oSelMulti_Hauptsorte 		= null;
	private UTIL_MultiSelectField_Sorte				 		oSelMulti_Sorte				= null;
	private E2_Button      									oPBRefreshMaterial 			= null;

	private B2_LAG_SALDO_LIST_Selector_EW_FW_ST 			oSelectorLagerTypen 		= null;

	private MyE2_CheckBox 				   					oCBCalculateVertragsDaten 	= null;
	private MyE2_SelectField 								oSelEinheit 				= null;
	private E2_SelectionComponentsVector 					oSelVector 					= null;

	private ownTF4Datum										oDateAdditional1 			= null;
	private ownTF4Datum										oDateAdditional2 			= null;

	private RB_TransportHashMap								m_tpHashMap					= null;
	//fuer gleichheit der selektor-unter-grids
	private int[] 											iSpaltenGridMain 	= {100,1000,100,100};


	public B2_LAG_SALDO_LIST_Selector(RB_TransportHashMap  p_tpHashMap) throws myException
	{
		super(S.ms("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));

		
		this.m_tpHashMap = p_tpHashMap;       

		this.oSelVector = new E2_SelectionComponentsVector(this.m_tpHashMap.getNavigationList());
		
		this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR,this);
		this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR,this.oSelVector );
	
		E2_MutableStyle oStyle = new E2_MutableStyle();
		oStyle.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));
		oStyle.setProperty(CheckBox.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-1), Border.STYLE_NONE));

		oSelMulti_Lager = new UTIL_MultiSelectField_Factory_ForLager(true,"",800, "ADR.ID_ADRESSE =  #WERT# ");
		oSelVector.add(oSelMulti_Lager);

		oSelectorLagerTypen = new B2_LAG_SALDO_LIST_Selector_EW_FW_ST(this.m_tpHashMap.getNavigationList());
		oSelectorLagerTypen.set_CheckboxIsExternal(2);
		oSelVector.add (oSelectorLagerTypen);


		oSelMulti_Hauptsorte = new UTIL_MultiSelectField_Hauptsorte(null, 50, "SUBSTR(ART.ANR1,0,2) = '#WERT#'");
		oSelMulti_Hauptsorte.get_vAgents4ActiveComponentsBeforeSelection().add(new actionAgentHauptartikel());
		oSelVector.add(oSelMulti_Hauptsorte);


		oSelMulti_Sorte = new UTIL_MultiSelectField_Sorte(null, 250, "ART.ID_ARTIKEL = #WERT#");
		oSelVector.add(oSelMulti_Sorte);

		// Einheiten
		oSelEinheit = new MyE2_SelectField("SELECT EINHEITLANG, ID_EINHEIT  from "
				+ bibE2.cTO()
				+ ".JT_EINHEIT "
				+ " WHERE ID_MANDANT = "
				+ bibALL.get_ID_MANDANT() + " ORDER BY IST_STANDARD DESC, EINHEITLANG", false, true,
				false, false);

		
		oSelVector.add(new E2_ListSelectorStandard(oSelEinheit, " EH.ID_EINHEIT = #WERT#", null, null));

		// Schalter, ob die Vertragsdaten geladen werden sollen
		oCBCalculateVertragsDaten = new MyE2_CheckBox(S.ms("Vertragsdaten anzeigen"));
		oCBCalculateVertragsDaten.setSelected(false);

		//
		// Refreshbutton der Materialien
		//
		this.oPBRefreshMaterial = new E2_Button()._image(E2_ResourceIcon.get_RI("reload.png"));
		this.oPBRefreshMaterial._ttt(S.ms("Liste der Materialien neu laden.").toString());
		this.oPBRefreshMaterial._aaa(new actionAgentHauptartikel());

		//2013-03-05: neuer db-gestuetzter listselektor
		String module_kenner = TRANSLATOR.LIST.get_modul().get_callKey();
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(module_kenner);
		this.oSelVector.add(oDB_BasedSelektor);

		// Datumsfelder, das zusätzlich als Spalte angezeigt werden muss.
		oDateAdditional1 = new ownTF4Datum(null, false);
		oDateAdditional2 = new ownTF4Datum(null, false);

		RB_gld gld_aussen = new RB_gld()._ins(10,2,10,2)._span(1)._left_top();

		E2_Grid  oGridAussen = new E2_Grid()._s(3)._w100()._bo_ddd()._insets(10, 0, 10, 0);
		oGridAussen.setOrientation(MyE2_Grid.ORIENTATION_HORIZONTAL);
		oGridAussen.setRowHeight(0,new Extent(23));


		E2_Grid oGridLagerauswahl = new E2_Grid()._s(3)._bo_no();//3,styleGridBorderInner);
		oGridLagerauswahl.setColumnWidth(0, new Extent(iSpaltenGridMain[0]));
		oGridAussen._a(oGridLagerauswahl, gld_aussen._c()._span(4));//4,1,E2_INSETS.I_10_2_10_2,Alignment.ALIGN_TOP);

		oGridLagerauswahl
		._a(new RB_lab()._t(S.ms("Lager:")),									new RB_gld()._left_top()._ins(0,2,0,0))//1,1,E2_INSETS.I_0_2_0_0,Alignment.ALIGN_TOP);	
		._a(oSelMulti_Lager.get_oComponentForSelection(),						new RB_gld()._left_top()._ins(0,2,0,0)._span(2))
		._a(new RB_lab()._t(S.ms("")),											new RB_gld()._left_top()._ins(0,2,0,0))	
		._a(oSelectorLagerTypen.getCheckboxOf(ENUM_SELEKTOR_Lagertyp.STRECKE),	new RB_gld()._left_top()._ins(0)._span(2));


		// 1. Spalte jetzt ein Grid für die Sorten
		E2_Grid oGridSorten = new E2_Grid()._bo_no()._s(4);
		oGridAussen._a(oGridSorten,gld_aussen);

		oGridSorten.setColumnWidth(0, new Extent(iSpaltenGridMain[0]));
		//Z1
		oGridSorten
		._a(new RB_lab()._t(S.ms("Hauptsorte:")),				new RB_gld()._ins(0,3,0,0)._left_top())
		._a(oSelMulti_Hauptsorte.get_oComponentForSelection(),	new RB_gld()._ins(0,0,5,0)._left_top()._span(3))
		// Z2
		._a(new RB_lab()._t(S.ms("Sorte:")),					new RB_gld()._ins(0)._left_top())
		._a(oSelMulti_Sorte.get_oComponentForSelection(),		new RB_gld()._ins(0)._left_top()._span(3));



		E2_Grid oGridDivers1 = new E2_Grid()._bo_no()._s(2);
		oGridAussen._a(oGridDivers1,gld_aussen);
		oGridDivers1.setSize(2);

		oGridDivers1._a(new RB_lab()._t(S.ms("Einheit:")),								new RB_gld()._ins(0,0,5,0)._left_top());
		oGridDivers1._a(oSelEinheit,													new RB_gld()._ins(0)._left_top());

		oGridDivers1._a(new RB_lab()._t(oDB_BasedSelektor.get_bIsFilled()?"Divers:":""),new RB_gld()._ins(0,5,5,0)._left_top());
		oGridDivers1._a(oDB_BasedSelektor.get_oComponentForSelection(100),				new RB_gld()._ins(0)._left_top());

		oGridDivers1._a(new RB_lab()._t(S.ms("")),										new RB_gld()._ins(0,0,5,0)._left_top());
		oGridDivers1._a(oCBCalculateVertragsDaten,										new RB_gld()._ins(0)._left_top());

		oGridDivers1._a(new RB_lab()._t(S.ms("")),										new RB_gld()._ins(0,5,5,0)._left_top());
		
		MyE2_Row oRowPassivschalter = new MyE2_Row();
		oRowPassivschalter.add(oSelVector.get_AktivPassivComponent());
		
		oGridDivers1._a(oRowPassivschalter,												new RB_gld()._ins(0,0,5,0)._left_top());

		E2_ExpandableRow oRowDivers2 = 	new E2_ExpandableRow(	S.ms("Zusätzliche Auswahlkriterien: Lagerorte"), 
				new Border(1,new E2_ColorDDDark(),Border.STYLE_NONE),
				new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));

		// Standardmäßig verstecken
		oRowDivers2.get_oButtonClose().doActionPassiv();
		oGridAussen._a(oRowDivers2,gld_aussen);

		E2_Grid oGridDivers2 = new E2_Grid()._bo_no()._s(2)._w100();
		oRowDivers2.add(oGridDivers2, E2_INSETS.I_0_0_0_0);
		
		oGridDivers2
		._a(new RB_lab()._t(S.ms("Lagerorte:")), 					new RB_gld()._ins(0,10,0,0)._left_top()) //1,1,E2_INSETS.I_0_10_0_0,Alignment.ALIGN_TOP)
		._a(oSelectorLagerTypen.get_oComponentForSelection(),		new RB_gld()._ins(10,10,0,0)._left_top())//._span(2))

		._a(new RB_lab()._t(S.ms("Zusätzliche Lagerbestände :")),	new RB_gld()._ins(0,5,0,0)._left_top())//._span(2))
		._a(oDateAdditional1,										new RB_gld()._ins(10,5,0,0)._left_top())//._span(2))

		._a(new RB_lab()._t(S.ms("")),								new RB_gld()._ins(0,5,0,0)._left_top())//._span(2))
		._a(oDateAdditional2,										new RB_gld()._ins(10,5,0,0)._left_top())//._span(2))
;
		get_oSelVector().add_oActionAgent(null);

		this.add(oGridAussen);

	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

	private class actionAgentHauptartikel extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			B2_LAG_SALDO_LIST_Selector oThis = B2_LAG_SALDO_LIST_Selector.this;

			Vector<String> vHauptartikel = oSelMulti_Hauptsorte.get_SelectedValues();
			Vector<String> vWhere = new Vector<String>();
			for (String s: vHauptartikel){
				vWhere.add("SUBSTR('00" + s.trim() + "',-2) " );
			}

			String sParamValues = "";
			if (vHauptartikel != null && vHauptartikel.size() > 0) {
				sParamValues = " AND SUBSTR(ANR1,0,2) in ( " + bibALL.Concatenate( vWhere, "," , "'-1'") + " ) ";
			}
			oThis.oSelMulti_Sorte.Refresh("#P1#", sParamValues);
		}
	}


	private class ownTF4Datum extends MyE2_TextField_DatePOPUP_OWN
	{
		public ownTF4Datum( String cStartWert, boolean bEnabled4Edit) throws myException
		{
			super(cStartWert, 100,true,true);
			this.set_bEnabled_For_Edit(bEnabled4Edit);
			this.get_oButtonCalendar().set_bEnabled_For_Edit(true); 

			this.get_oButtonEraser().add_oActionAgent(new XX_ActionAgent() {

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					oSelVector.makeSelektion();
				}
			});


			this.get_vActionAgentsZusatz().add(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					oSelVector.makeSelektion();
				}
			});
		}
	}
	
	public String get_AdditionalDateForSaldo1(boolean bISOFormat) throws myException{
		return get_AdditionalDateForSaldo(oDateAdditional1,bISOFormat);
	}
	
	
	public String get_AdditionalDateForSaldo2(boolean bISOFormat) throws myException{
		return get_AdditionalDateForSaldo(oDateAdditional2,bISOFormat);
	}
	
	private String get_AdditionalDateForSaldo(ownTF4Datum oTF, boolean bISOFormat) throws myException{
		String sDatum = bISOFormat ? oTF.get_oDBFormatedDateFromTextField() : oTF.get_oFormatedDateFromTextField();
		return sDatum;
	}
	
	public boolean get_VertragsdatenAnzeigen() throws myException{
		return this.oCBCalculateVertragsDaten.isSelected();
	}

}



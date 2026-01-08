package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.IF_Interpreter;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.IF_agentSimple;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;



/*
 * dropDownSelektion
 * wirkt wie ein Standard-DropDown-Selektor,  kann aber mehrere Auswahlen mit logischem oder zusammenfuehren 
 */
public class E2_ListSelectorMultiDropDownV3 extends XX_ListSelektor_EXT {

	
	public static String TRENNZEICHEN_FUER_SPEICHER="@";
	
	private HashMap<String, String>         hmValuePlusWhereblock =         new HashMap<String, String>();
	
	private VEK<RB_selField>           		selFields = 					new VEK<RB_selField>();
	private E2_Grid    		           	 	grid4Anzeige = new E2_Grid()._s(1);
	private XX_ActionAgent   				agent4Select = null;
	private RB_selField  					selFieldBase = null;
	private E2_BasicModuleContainer     	containerToSelectZusatzValues =	 	null;

	//erzeugt einen moeglichst eindeutigen namen zum speichern der popup-fenster-groesse
	private String 							containerAddOnForSaveWindowSize = null;
	
	private MyE2_String  					beschriftung = null;
	private int  							widthSelectComponentDropDown = 100;
	private int  							widthButton = 20;
	private int  							widthForBeschriftung = 100;
	
	
	public E2_ListSelectorMultiDropDownV3() throws myException	{
		super();
	}
	
	/**
	 * 
	 * @author martin
	 * @date 09.11.2020
	 *
	 * @param field
	 * @throws myException
	 * 
	 * erzeugt selector via distinct-query
	 */
	public E2_ListSelectorMultiDropDownV3(IF_Field field, IF_Interpreter<String, String> interpreter) throws myException	{
		super();
		_TAB tab = field._t();
		
		SEL sel = new SEL(field).FROM(tab).ADD_Distinct().WHERE(new vgl(_TAB.find_field(tab, "ID_MANDANT"), bibSES.get_ID_MANDANT_UF())).ORDERUP(field);
		
		VEK<Object[]> results = bibDB.getResultLines(sel.getSqlExt(), true);
		HMAP<String,String>   s_results = new HMAP<String,String>()._put("", "-");
		if (results!=null) {
			for (Object[] ar: results) {
				if (ar[0]!=null) {
					String readable = ar[0].toString();
					if (interpreter!=null) {
						readable = interpreter.getResult(readable);
					}
					s_results._put(ar[0].toString(),readable);
				}
			}
		}
		RB_selField referenz = new RB_selField()._populate(s_results);
		HMAP<String, String> bedingungen = new HMAP<String, String>();
		if (results!=null) {
			for (Object[] ar: results) {
				if (ar[0]!=null) {
					String s_val = ar[0].toString();
					if (ar[0] instanceof BigDecimal) {
						s_val =  MyNumberFormater.formatDez((BigDecimal)ar[0],field.generate_MetaFieldDef().get_FieldDecimals(),false);
					} else if (ar[0] instanceof Date) {
						s_val = new SimpleDateFormat("dd.MM.yyyy").format((ar[0]));
					}
					vgl v = new vgl(field, s_val) ;
					bedingungen._put(ar[0].toString(), v.s());
				}
			}
		}
		this._INIT(referenz, bedingungen);
		
		this.containerAddOnForSaveWindowSize = field.tnfn()+"@c1ba44ec-232e-11eb-adc1-0242ac120002";
	}
	
	
	
	public E2_ListSelectorMultiDropDownV3(RB_selField selFieldBasis, HMAP<String, String>    hmValuePlusWhereblock) throws myException	{
		super();
		this._INIT(selFieldBasis, hmValuePlusWhereblock);
	}

	
	public E2_ListSelectorMultiDropDownV3(@SuppressWarnings("rawtypes") IF_enum_4_db_specified enum4db, IF_Field field) throws myException	{
		super();
		RB_selField sel = new RB_selField()._populate(enum4db, true);
		String[][] vals= enum4db.get_dd_Array(false);
		
		HMAP<String, String> valuesAndBedinung = new HMAP<String, String>();
		
		for (String[] sub: vals) {
			valuesAndBedinung._put(sub[1], new vgl(field,sub[1]).s());
		}
		this._INIT(sel, valuesAndBedinung);
	}

	
	public E2_ListSelectorMultiDropDownV3  _INIT(RB_selField selFieldBasis, HMAP<String, String> hm_valuePlusWhereblock) throws myException	{
		selFieldBase = selFieldBasis;
		selFields._a(selFieldBasis);
		selFieldBasis._aaa(()->{executeCompleteSelectAction();});
		hmValuePlusWhereblock = hm_valuePlusWhereblock;
		
		closeAndDestroyPopupContainer();
		
		//es muss in der select-auswahl der erste punkt einen leeren wert geben
		if (S.isFull(selFieldBasis.getVCompleteDBVals().get(0))) {
			throw new myException("E2_ListSelectorMultiDropDown:Constuctor ! please put an empty value in front !!");
		}
		
		this.set_oNeutralisator(new OwnSelectFieldNeutralisator_1st_is_neutral());
		this.selFieldBase.setSelectedIndex(0);
		this._renderSelector();
		if (S.isEmpty(containerAddOnForSaveWindowSize)) {
			containerAddOnForSaveWindowSize=selFieldBasis.getClass().getCanonicalName()+"@c1ba44ec-232e-11eb-adc1-0242ac120002";
		}
		
		return this;
	}

	
	
	
	public RB_selField getSelFieldBasis() {
		return selFields.get(0);
	}


	
	
	public E2_Grid getGrid4Anzeige(){
		return grid4Anzeige;
	}


	//2012-02-14: neutralisatoren
	private class OwnSelectFieldNeutralisator_1st_is_neutral extends XX_ListSelektor_Neutralisator {
		@Override
		public void set_to_Neutral() throws myException	{
			selFields._clear()._a(selFieldBase);
			selFieldBase._setActiveDBVal("");
			_renderSelector();
		}
	}

	
	/**
	 * Gibt alle Selektierten Werte (IDs) zurück
	 * @return
	 * @throws myException
	 */
	public VEK<String> getSelectedValues() throws myException {
		VEK<String> vRet = new VEK<String>();
		for (RB_selField selField: selFields) {
			vRet._addValidated((s)-> {return S.isFull(s);},selField.getActualDbVal());
		}
		return vRet;
	}
	

	
	@Override
	public String get_WhereBlock() throws myException {
		String cWhere = "";
		VEK<String>  vSelectBloecke = new VEK<String>();
		for (String cWert:getSelectedValues()) {
			vSelectBloecke._addValidated((s)->{return S.isFull(s);}, "("+this.hmValuePlusWhereblock.get(cWert)+")");
		}
		if (vSelectBloecke.size()>0) {
			cWhere = "("+bibALL.Concatenate(vSelectBloecke, " OR ", " 1=1 ")+")";
		}
		DEBUG.println("Where-Statement: <"+S.NN(cWhere)+">");
		return cWhere;
	}

	
	
	@Override
	public Component get_oComponentWithoutText() {
		return this.grid4Anzeige;
	}
	
	@Override
	public Component get_oComponentForSelection() throws myException {
		return this.getGrid4Anzeige();
	}

	

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent agent) {
		this.agent4Select=agent;
	}

	
	@Override
	public void doInternalCheck()
	{
	}
	
	public  E2_ListSelectorMultiDropDownV3 _renderSelector() {
		if (beschriftung!=null && S.isFull(beschriftung)) {
			grid4Anzeige._clear()._setSize(widthForBeschriftung, widthSelectComponentDropDown,widthButton);
			grid4Anzeige._a(new RB_lab()._t(beschriftung), new RB_gld()._left_mid()._ins(0, 0, 4, 0));
		} else {
			grid4Anzeige._clear()._setSize(widthSelectComponentDropDown,widthButton);
		}
			
		if (containerToSelectZusatzValues==null && selFields.size()==1) {
			grid4Anzeige._a(selFields.get(0), new RB_gld()._ins(0,0,2,0)._left_mid())._a(new OwnButtonOpenMultiSelectPopup()._ttt("Die Auswahl auf weitere Bereiche erweitern"));
		} else {
			MyE2_String toolTips = S.ms("Erweiterte Auswahl: ").ut("\n\n");
			selFields.forEach((s)->{toolTips.ut(s.getActualVisibleVal()+"\n");});
			grid4Anzeige._a(new RB_lab()._t("<Mehrfach>"), new RB_gld()._ins(2,0,2,0)._left_mid()._col_back_d())._a(new OwnButtonOpenMultiSelectPopup()._ttt(toolTips));
		}
		return this;
	}





	

	
	
	private void executeCompleteSelectAction() throws myException{
		for (XX_ActionAgent a : E2_ListSelectorMultiDropDownV3.this.get_vAgents4ActiveComponentsBeforeSelection()){
			a.executeAgentCode(null);
			if (bibMSG.MV().hasAlarms()) {
				break;
			}
		}
		if (bibMSG.MV().isOK()) {
			agent4Select.executeAgentCode(null);
		}
		for (XX_ActionAgent a :E2_ListSelectorMultiDropDownV3.this.get_vAgents4ActiveComponentsAfterSelection()){
			a.executeAgentCode(null);
			if (bibMSG.MV().hasAlarms()) {
				break;
			}
		}
	}
	
	
	private void closeAndDestroyPopupContainer() throws myException {
		if (this.containerToSelectZusatzValues!=null) {
			this.containerToSelectZusatzValues.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			this.containerToSelectZusatzValues=null;
		}
	}
	
	//abstracte methode, damit eine eigene popupklasse mit groessen-speicherfunktion moeglich wird
	public E2_BasicModuleContainer createPopupContainer()  throws myException {
		return new OwnBasicModuleContainer();
	};     

	

	private class OwnBasicModuleContainer extends E2_BasicModuleContainer {
		public OwnBasicModuleContainer() {
			super();
			this.set_cADDON_TO_CLASSNAME(S.NN(containerAddOnForSaveWindowSize));
		}
	}


	/**
	 * @return the containerAddOnForSaveWindowSize
	 */
	public String getContainerAddOnForSaveWindowSize() {
		return containerAddOnForSaveWindowSize;
	}


	/**
	 * @param containerAddOnForSaveWindowSize the containerAddOnForSaveWindowSize to set
	 */
	public E2_ListSelectorMultiDropDownV3 _setContainerAddOnForSaveWindowSize(String containerAddOnForSaveWindowSize) {
		this.containerAddOnForSaveWindowSize = containerAddOnForSaveWindowSize;
		return this;
	}
	
	private void moveSels(VEK<RB_selField> source, VEK<RB_selField> target, IF_agentSimple agent4targets) throws myExceptionCopy {
		VEK<RB_selField>  cleanSels= source.getPartVEK((sf)->{return S.isAllFull(sf.getActualDbVal());});
		target._clear();
		for (RB_selField s: cleanSels) {
			target._a(s.get_Copy(null, s.getSelectedIndex()));
		}
		if (agent4targets!=null) {
			target.forEach((s)->{s._aaa(agent4targets);});
		}
			
	}

	
	public MyE2_String getBeschriftung() {
		return beschriftung;
	}

	public E2_ListSelectorMultiDropDownV3 _setBeschriftung(MyE2_String beschriftung) {
		this.beschriftung = beschriftung;
		this._renderSelector();
		return this;
	}

	/**
	 * 
	 * @author martin
	 * @date 10.11.2020
	 *
	 * @param beschriftung (untranlated)
	 * @return
	 */
	public E2_ListSelectorMultiDropDownV3 _setBeschriftung(String beschriftung) {
		this.beschriftung = S.msUt(beschriftung);
		this._renderSelector();
		return this;
	}

	
	public int getWidthSelectComponentDropDown() {
		return widthSelectComponentDropDown;
	}

	public E2_ListSelectorMultiDropDownV3 _setWidthSelectComponentDropDown(int widthSelectComponentDropDown) {
		this.widthSelectComponentDropDown = widthSelectComponentDropDown;
		this._renderSelector();
		return this;
	}

	public int getWidthButton() {
		return widthButton;
	}

	public E2_ListSelectorMultiDropDownV3 _setWidthButton(int widthButton) {
		this.widthButton = widthButton;
		this._renderSelector();
		return this;
	}

	public int getWidthForBeschriftung() {
		return widthForBeschriftung;
	}

	public E2_ListSelectorMultiDropDownV3 _setWidthForBeschriftung(int widthForBeschriftung) {
		this.widthForBeschriftung = widthForBeschriftung;
		this._renderSelector();
		return this;
	}

	
	
	
	private class OwnButtonOpenMultiSelectPopup extends E2_Button {
		private VEK<RB_selField> 	selFieldsPopup = new VEK<RB_selField>();

		public OwnButtonOpenMultiSelectPopup() 	{
			super();

			String label = "0";
			if (selFields.size()>=2) {
				label = ""+selFields.size();
			}
			this._t(label)._setShapeStandardTextButton()._b();
			this.setWidth(new Extent(20));
			this.add_oActionAgent(new ActionAgentCreatePopup());
		}
		
		private class ActionAgentCreatePopup extends XX_ActionAgent  {
			private E2_Grid gridInhaltFenster = new E2_Grid()._s(2)._w100();         
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 	{
				selFieldsPopup._clear();
				moveSels(selFields, selFieldsPopup,()->{refreshSelFieldVector();});
				gridInhaltFenster.setColumnWidth(0, new Extent(95,Extent.PERCENT));
				
				refreshSelFieldVector();
				baueGridNeu();
				
				containerToSelectZusatzValues=createPopupContainer();
				containerToSelectZusatzValues.add(this.gridInhaltFenster, E2_INSETS.I_5_5_5_5);
				containerToSelectZusatzValues.CREATE_AND_SHOW_POPUPWINDOW(new Extent(370), new Extent(300), new MyE2_String("Weitere Bereiche zufügen .."));
			}

			
			private void baueGridNeu() throws myException {
				
				E2_Button btSpeichern = 		new E2_Button()._t("OK")._ttt("Auswahl speichern und Mehrfachselektion ausführen")
															._aaa(()-> {
																moveSels(selFieldsPopup, selFields,()->{executeCompleteSelectAction();});
																closeAndDestroyPopupContainer();
																executeCompleteSelectAction();
																_renderSelector();
															})._setShapeStandardTextButton();
				
				E2_Button btAbbruch = 			new E2_Button()._t("Abbrechen")._ttt("Auswahl abbrechen, nichts verändern")
															._aaa(()->{
																closeAndDestroyPopupContainer();
																_renderSelector();
															})._setShapeStandardTextButton();
				
				E2_Button btClear = 			new E2_Button()._t("Selektion löschen")._ttt("Alle Felder löschen")
															._aaa(()-> {
																selFields._clear()._a(selFieldBase.get_Copy(null, 0));
																selFields.get(0)._aaa(()->{executeCompleteSelectAction();});																closeAndDestroyPopupContainer();
																executeCompleteSelectAction();
																_renderSelector();
															})._setShapeStandardTextButton();
				
				
				btClear.setIcon(E2_ResourceIcon.get_RI("multi_select_delete.png"));
				
				//der speicherbutton bekommt dann noch die zusatzaction des listenneubaus
				//2014-07-03: weitere agenten vor-und nach der Selektion (Ableitung von XX_ListSelektor_EXT
				btSpeichern.setFont(new E2_FontBold());
				
				this.gridInhaltFenster._clear();
				
				for (RB_selField sel: selFieldsPopup) {
					this.gridInhaltFenster._a(sel, new RB_gld()._ins(0, 0, 5, 5));
					this.gridInhaltFenster._a(new ownButtonDeleteSelectField(sel), new RB_gld()._ins(0,0,5,5));
				}
				
				int iBreiteButtons[] = {80,150,120};
				this.gridInhaltFenster._a(new E2_ComponentGroupHorizontal_NG(btSpeichern,btClear,btAbbruch,iBreiteButtons),new RB_gld()._ins(0,10,5,5));
			}
			

			
			private class ownButtonDeleteSelectField extends E2_Button {
				public ownButtonDeleteSelectField(RB_selField selField) {
					super();
					this._image(E2_ResourceIcon.get_RI("multi_select_delete.png"), true);
					this._ttt(S.ms("Diese Auswahlmöglichkeit entfernen"));
					
					this.add_oActionAgent(new XX_ActionAgent() 	{
						@Override
						public void executeAgentCode(ExecINFO oExecInfo) throws myException {
							selField._setActiveDBVal("");
							refreshSelFieldVector();
							baueGridNeu();
						}
					});
				}
			}
			
			private void refreshSelFieldVector() throws myException {
				//alle durchgehen, leere rausschmeisen und einen leeren anhaengen
				selFieldsPopup = selFieldsPopup.getPartVEK((sf)-> {
					return S.isFull(sf.getActualDbVal());
				});
				selFieldsPopup._a(selFieldBase.get_Copy(null,0)._aaa(()-> {
					refreshSelFieldVector();
				}));
				baueGridNeu();
			}
		}
	}



	

	


	
}

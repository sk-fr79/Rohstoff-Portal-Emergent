package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/**
 * abstrakte klasse fuer die generierung von list-selektoren, die mehrfache
 * merkmale eines datensatzes repraesentieren und bei denen definiert werden kann, ob
 * ein merkmal als ja, nein oder ohne beruecksichtigung in einer auswahl erfasst wird  
 * @author martin
 *
 */
public abstract class E2_ListSelector_MultiCheckboxArray extends XX_ListSelektor
{
	public static int STATUS_YES = 1;
	public static int STATUS_NO = 0;
	public static int STATUS_IGNORE = -1;

	
	private MyE2_String 				cTitle4User = 		null;
	private MyE2_String 				cText4CallButton = 	null;

	// grid fuer die anzeige im popup-menue
	private	E2_BasicModuleContainer  	oPopupContainer = null; 
	private MyE2_Grid  					oGridWithOptions = 	new MyE2_Grid(4, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private Vector<ownCheckboxTriple>   vCheckBoxTriple = 	new Vector<E2_ListSelector_MultiCheckboxArray.ownCheckboxTriple>();
	

	// componenten fuer den selector
	private MyE2_Button                 oButtonStartInSelector = 		null;

	private MyE2_Grid                   oGrid4Infos_Status = 			new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	

	//buttons im popup-fenster
	private MyE2_Button                 oStartSelectButton=	new MyE2_Button(new MyE2_String("OK"));
	private MyE2_Button                 oStartCancelButton=	new MyE2_Button(new MyE2_String("Abbruch"));
	private MyE2_Button                 oStartClearButton=	new MyE2_Button(new MyE2_String("Löschen"));

	
	public E2_ListSelector_MultiCheckboxArray(MyE2_String Title4User, MyE2_String Text4CallButton) throws myException
	{
		super();
		this.cTitle4User = 		Title4User;
		this.cText4CallButton = Text4CallButton;
		
		//elemente auf dem popup-fenster
		this.oStartSelectButton.add_oActionAgent(new ownActionCloseWindow());
		this.oStartSelectButton.add_oActionAgent(new ownActionSaveStatus());
		
		this.oStartCancelButton.add_oActionAgent(new ownActionCloseWindow());
		this.oStartCancelButton.add_oActionAgent(new ownActionRestoreStatus());
		
		this.oStartClearButton.add_oActionAgent(new ownActionClear());
		this.oStartClearButton.add_oActionAgent(new ownActionCheckZeilenStatus());
		
		this.oStartSelectButton.setToolTipText(new MyE2_String("Selektor ausführen").CTrans());
		this.oStartCancelButton.setToolTipText(new MyE2_String("Abbruch, Alles bleibt beim alten").CTrans());
		this.oStartClearButton.setToolTipText(new MyE2_String("Alle Selektionen auf <Ignorieren> stellen, Selektor ist neutral").CTrans());
		
		
		
		//der aufrufbutton im Selektor
		this.oButtonStartInSelector = new MyE2_Button(this.cText4CallButton);
		this.oButtonStartInSelector.add_oActionAgent(new ownActionStartPopupSelectorPart());
		this.oButtonStartInSelector.add_oActionAgent(new ownActionCheckZeilenStatus());
		this.oPopupContainer = this.generate_ModuleContainer();
		
		this.oPopupContainer.add(this.oGridWithOptions, new Insets(10, 10, 0, 0));
		
		Vector<String[]>  vCB = this.generate_Key_Value_Pairs();
		
		//nun das auswahl-grid festlegen
		oGridWithOptions.add(new MyE2_Label(this.cTitle4User,MyE2_Label.STYLE_TITEL_NORMAL()),MyE2_Grid.GRID_LAYOUTDATA(new Insets(0,10,0,20), 4, new Alignment(Alignment.CENTER, Alignment.CENTER)));
		
		Insets  oInsetGrid = new Insets(1,2,20,2);
		Insets  oInsetGridR = new Insets(1,2,1,2);
		for (String[]  oCB: vCB)
		{
			//hier die korrekte definition der checkbox pruefen:
			//die cb muessen im EXT().get_C_MERKMAL() die ID_ der Value-Tabelle enthalten (z.B. ID_ADRESSKLASSE_DEF
			if (S.isEmpty(oCB[0]) || S.isEmpty(oCB[1]) || (!bibALL.isLong(oCB[0]))  )
			{
				throw new myException("E2_ListSelector_MultiCheckboxArray: generate_Key_Value_Pairs(): All generated key-value-pairs must be not null and key must be the ID of value in Reference-Table");
			}
					
			GridLayoutData  oGL = MyE2_Grid.LAYOUT_LEFT_CENTER(oInsetGrid);
			GridLayoutData  oGLR = MyE2_Grid.LAYOUT_LEFT_CENTER(oInsetGridR);
			ownCheckboxTriple oTcB = new ownCheckboxTriple(oCB[0],oCB[1]);
			this.vCheckBoxTriple.add(oTcB);
			oGridWithOptions.add(oTcB.get_oLabelBeschriftung(),	oGL);
			oGridWithOptions.add(oTcB.get_cb_selectYes(),		oGL);
			oGridWithOptions.add(oTcB.get_cb_selectNo(),		oGL);
			oGridWithOptions.add(oTcB.get_cb_selectIgnore(),	oGLR);
		}
		
		Insets  oInsetLastLine = new Insets(0,10,10,0);
		this.oGridWithOptions.add(new MyE2_Label(""),1,oInsetLastLine);
		this.oGridWithOptions.add(this.oStartSelectButton,1,oInsetLastLine);
		this.oGridWithOptions.add(this.oStartCancelButton,1,oInsetLastLine);
		this.oGridWithOptions.add(this.oStartClearButton,1,oInsetLastLine);
		
		this.fill_InfoGrid_4_Selector(this.oGrid4Infos_Status);
		
		this.set_oNeutralisator(new ownSelectFieldNeutralisator());
	}

	
	
	
	

	@Override
	public Component get_oComponentForSelection() throws myException
	{
		MyE2_Grid oGridRueck = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridRueck.add(this.oButtonStartInSelector,E2_INSETS.I_0_0_5_0);
		oGridRueck.add(this.oGrid4Infos_Status,E2_INSETS.I_0_0_0_0);
		return oGridRueck;
	}

	@Override
	public Component get_oComponentWithoutText() throws myException
	{
		return this.get_oComponentForSelection();
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent)
	{
		this.oStartSelectButton.add_oActionAgent(oAgent);
	}

	@Override
	public void doInternalCheck()
	{
	}

	
	/**
	 * im konstruktor muss eine liste generiert werden mit checkboxen, die dem masken-kreuztabellen-feld entsprechen
	 * @return Vector with checkboxes
	 * @throws myException
	 */
	public abstract Vector<String[]> 			generate_Key_Value_Pairs() throws myException;
	public abstract E2_BasicModuleContainer 	generate_ModuleContainer() throws myException;
	public abstract String  					generate_ToolTipText4StartSelectButton() throws myException;
	public abstract void  					fill_InfoGrid_4_Selector(MyE2_Grid oGrid4Infos_Status) throws myException;
	
	
	protected class ownCheckboxTriple
	{
		private String         cValue4User = null;

		private String         cKey_UF = null;

		
		private MyE2_Label     oLabelBeschriftung = null;
		private MyE2_CheckBox  oCB_selectYes = 		new MyE2_CheckBox(new MyE2_String("Ja"),false, false);
		private MyE2_CheckBox  oCB_selectNo = 		new MyE2_CheckBox(new MyE2_String("Nein"), false, false);
		private MyE2_CheckBox  oCB_selectIgnore = 	new MyE2_CheckBox(new MyE2_String("Ignorieren"), true, false);    //ignorieren ist der standard
		
		private int 			oldStatus = 		E2_ListSelector_MultiCheckboxArray.STATUS_IGNORE;
		
		/**
		 * 
		 * @param KeyUF
		 * @param Value4User
		 */
		public ownCheckboxTriple(String KeyUF, String Value4User)
		{
			super();
			
			this.cValue4User = Value4User;
			this.cKey_UF = KeyUF;
			this.oLabelBeschriftung = new MyE2_Label(this.cValue4User);
			
			//die 3 auswahl-checkboxen muessen mit einem radioflag versehen sein
			ActionAgent_RadioFunction_CheckBoxList oRadioAction = new ActionAgent_RadioFunction_CheckBoxList(false);
			
			oRadioAction.add_CheckBox(this.oCB_selectYes);
			oRadioAction.add_CheckBox(this.oCB_selectNo);
			oRadioAction.add_CheckBox(this.oCB_selectIgnore);
			
			this.oCB_selectYes.add_oActionAgent(new ownActionZeilenStatus());
			this.oCB_selectNo.add_oActionAgent(new ownActionZeilenStatus());
			this.oCB_selectIgnore.add_oActionAgent(new ownActionZeilenStatus());
			
		}
		
		public MyE2_Label get_oLabelBeschriftung() {
			return oLabelBeschriftung;
		}

		public String get_cKey_UF() {
			return cKey_UF;
		}

		public String get_cValue4User() {
			return cValue4User;
		}
		
		public MyE2_CheckBox get_cb_selectYes() 	{
			return oCB_selectYes;
		}
		
		public MyE2_CheckBox get_cb_selectNo()	{
			return oCB_selectNo;
		}
		
		public MyE2_CheckBox get_cb_selectIgnore()	{
			return oCB_selectIgnore;
		}
		
		public void save_Status()	{
			if 			(this.oCB_selectYes.isSelected()) {
				this.oldStatus=E2_ListSelector_MultiCheckboxArray.STATUS_YES;
			} else if 	(this.oCB_selectNo.isSelected()) {
				this.oldStatus=E2_ListSelector_MultiCheckboxArray.STATUS_NO;
			} else if 	(this.oCB_selectIgnore.isSelected()) {
				this.oldStatus=E2_ListSelector_MultiCheckboxArray.STATUS_IGNORE;
			}
			this.checke_Zeilenstatus();
		}
		
		public void restore_Status()		{
			this.oCB_selectYes.setSelected(false);
			this.oCB_selectNo.setSelected(false);
			this.oCB_selectIgnore.setSelected(false);
			
			if 			(this.oldStatus==E2_ListSelector_MultiCheckboxArray.STATUS_YES) {
				this.oCB_selectYes.setSelected(true);
			} else if 	(this.oldStatus==E2_ListSelector_MultiCheckboxArray.STATUS_NO) {
				this.oCB_selectNo.setSelected(true);
			} else if 	(this.oldStatus==E2_ListSelector_MultiCheckboxArray.STATUS_IGNORE) {
				this.oCB_selectIgnore.setSelected(true);
			}
			this.checke_Zeilenstatus();
		}

		/*
		 * hervorheben der zeilen, wo ein wert auf ja oder nein steht
		 */
		public void checke_Zeilenstatus() {
			this.set_colorToGridLayoutData(this.oLabelBeschriftung,null);
			this.set_colorToGridLayoutData(this.oCB_selectYes,null);
			this.set_colorToGridLayoutData(this.oCB_selectNo,null);
			this.set_colorToGridLayoutData(this.oCB_selectIgnore,null);
			
			if (!this.oCB_selectIgnore.isSelected()) {
				this.set_colorToGridLayoutData(this.oLabelBeschriftung,new E2_ColorLLLight());
				this.set_colorToGridLayoutData(this.oCB_selectYes,new E2_ColorLLLight());
				this.set_colorToGridLayoutData(this.oCB_selectNo,new E2_ColorLLLight());
				this.set_colorToGridLayoutData(this.oCB_selectIgnore,new E2_ColorLLLight());
			}
			
		}
		
		private void set_colorToGridLayoutData(Component oComponent, Color oCol)
		{
			LayoutData  oLayout = oComponent.getLayoutData();
			if (oLayout!=null && oLayout instanceof GridLayoutData)
			{
				oComponent.setLayoutData(LayoutDataFactory.get_GL_Copy((GridLayoutData)oLayout,oCol));
			}
		}
		
		public boolean get_bIsYes() {
			return this.oCB_selectYes.isSelected();
		}
		
		public boolean get_bIsNo() {
			return this.oCB_selectNo.isSelected();
		}

		public boolean get_bIsIgnore() {
			return this.oCB_selectIgnore.isSelected();
		}
		
		private class ownActionZeilenStatus extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				ownCheckboxTriple.this.checke_Zeilenstatus();
			}
		}
	}
	
	
	public void save_Status()	{
		for (ownCheckboxTriple oCBTriple: this.vCheckBoxTriple)		{
			oCBTriple.save_Status();
		}
	}
	

	public void checke_Zeilenstatus()	{
		for (ownCheckboxTriple oCBTriple: this.vCheckBoxTriple)		{
			oCBTriple.checke_Zeilenstatus();
		}
	}

	public void restore_Status()	{
		for (ownCheckboxTriple oCBTriple: this.vCheckBoxTriple)		{
			oCBTriple.restore_Status();
		}
	}

	
	
	public Vector<ownCheckboxTriple> get_vCheckBoxTriple() 	{
		return vCheckBoxTriple;
	}

	
	public MyE2_Grid get_oGrid4Infos_Status() {
		return this.oGrid4Infos_Status;
	}
	public MyE2_Button get_oButtonStartInSelector() {
		return this.oButtonStartInSelector;
	}

	
	private class ownActionStartPopupSelectorPart extends XX_ActionAgent 	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ListSelector_MultiCheckboxArray.this.oPopupContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(500), new MyE2_String("Selector auf Mehrfachoption.."));
		}
		
	}
	
	
	
	private class ownActionClear extends XX_ActionAgent 	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			for (ownCheckboxTriple oTrip: E2_ListSelector_MultiCheckboxArray.this.vCheckBoxTriple) 	{
				oTrip.get_cb_selectYes().setSelected(false);
				oTrip.get_cb_selectNo().setSelected(false);
				oTrip.get_cb_selectIgnore().setSelected(true);
			}
		}
	}
	
	private class ownActionCloseWindow extends XX_ActionAgent 	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ListSelector_MultiCheckboxArray.this.oPopupContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}

	private class ownActionSaveStatus extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException{
			E2_ListSelector_MultiCheckboxArray.this.save_Status();
			E2_ListSelector_MultiCheckboxArray.this.fill_InfoGrid_4_Selector(E2_ListSelector_MultiCheckboxArray.this.oGrid4Infos_Status);
			E2_ListSelector_MultiCheckboxArray.this.oButtonStartInSelector.setToolTipText(E2_ListSelector_MultiCheckboxArray.this.generate_ToolTipText4StartSelectButton());
		}
	}

	
	private class ownActionRestoreStatus extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ListSelector_MultiCheckboxArray.this.restore_Status();
			E2_ListSelector_MultiCheckboxArray.this.fill_InfoGrid_4_Selector(E2_ListSelector_MultiCheckboxArray.this.oGrid4Infos_Status);
			E2_ListSelector_MultiCheckboxArray.this.oButtonStartInSelector.setToolTipText(E2_ListSelector_MultiCheckboxArray.this.generate_ToolTipText4StartSelectButton());
		}
	}
	
	private class ownActionCheckZeilenStatus extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ListSelector_MultiCheckboxArray.this.checke_Zeilenstatus();
		}
	}
	
	//2012-02-14: neutralisatoren
	private class ownSelectFieldNeutralisator extends XX_ListSelektor_Neutralisator
	{
		@Override
		public void set_to_Neutral() throws myException
		{
			for (ownCheckboxTriple oTrip: E2_ListSelector_MultiCheckboxArray.this.vCheckBoxTriple) 	{
				oTrip.get_cb_selectYes().setSelected(false);
				oTrip.get_cb_selectNo().setSelected(false);
				oTrip.get_cb_selectIgnore().setSelected(true);
			}

		}
	}


}

package rohstoff.Echo2BusinessLogic._4_ALL;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Decision.ActionAgent_4_decision;
import panter.gmbh.Echo2.ActionEventTools.Decision.IF_components_4_decision;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.Echo2.components.DB.E2_DB_BUTTON_OPEN_MASK_FromID;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.maggie.DotFormatterGermanFixed;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_ModulContainer_MASK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM.FS__Adress_Info_Zentrum;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.FS__Adress_Info_Zentrum_NG;




public class DB_BUTTON_FIRMA_WITH_INFO extends MyE2_Grid_InLIST  implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy,MyE2IF_IsMarkable
{
	private E2_DB_BUTTON_OPEN_MASK_FromID  oButtonOpenMask = null;
	private ownInternal_INFO_Button        oInfoButton = null;

	private FS_ModulContainer_MASK         oFirmenMask = null;

	private String                         cBUTTONKENNER_EDIT = "BEARBEITE_ADRESSE_AUS_LISTE";
	private String                         cBUTTONKENNER_VIEW = "ANZEIGE_ADRESSE_AUS_LISTE";

	private String                         cBUTTONKENNER_ADRESSUEBERSICHT = "ANZEIGE_ADRESSUEBERSICHT";

	private String  					   cFIELD_HASH_4_BESCHRIFTUNG = null;
	private String    					   cORDER_STRING_UP = null;
	private String    					   cORDER_STRING_DOWN = null;

	private int                            iWidthWith_4_BeginLineWrap = 0;

	private int                            iWidthAdressText = 0;

	private MyE2_Button                    oButtonToSelectRow = null;                     //button mit dem Textinhalt (Adresse), damit die markierung der zeile ganz normal geht


	/**
	 * 
	 * @param oFieldAdressID 
	 * @param FirmenMask
	 * @param FIELD_HASH_4_BESCHRIFTUNG
	 * @param WidthAdressText
	 * @param WidthWithLineWrap
	 * @throws myException
	 */
	public DB_BUTTON_FIRMA_WITH_INFO(SQLField oFieldAdressID,  FS_ModulContainer_MASK FirmenMask, String  FIELD_HASH_4_BESCHRIFTUNG,String ORDER_UP, String ORDER_DOWN, int WidthAdressText, int WidthWithLineWrap) throws myException 
	{
		super(5, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

		this.oFirmenMask = 					FirmenMask;
		this.oButtonOpenMask = 				new E2_DB_BUTTON_OPEN_MASK_FromID(oFieldAdressID, oFirmenMask, new MyE2_String("Adresse Maskenansicht"), E2_MODULNAMES.MODUL_KENNER_PROGRAMM_WIDE, this.cBUTTONKENNER_EDIT, this.cBUTTONKENNER_VIEW);
		this.oButtonToSelectRow=            new MyE2_Button("--");

		this.cFIELD_HASH_4_BESCHRIFTUNG = 	FIELD_HASH_4_BESCHRIFTUNG;
		this.iWidthWith_4_BeginLineWrap   = WidthWithLineWrap;
		this.iWidthAdressText   = 			WidthAdressText;

		this.cORDER_STRING_UP = 	ORDER_UP;
		this.cORDER_STRING_DOWN = 	ORDER_DOWN;

		if (S.isFull(this.cORDER_STRING_UP) && S.isFull(this.cORDER_STRING_DOWN))
		{
			this.EXT_DB().set_cSortAusdruckFuerSortbuttonUP(this.cORDER_STRING_UP);
			this.EXT_DB().set_cSortAusdruckFuerSortbuttonDOWN(this.cORDER_STRING_DOWN);
		}


		if (FirmenMask==null)
		{
			this.oFirmenMask = bibSES.get_FS_ModulContainer_MASK();
		}



		//		
		this.oInfoButton = new ownInternal_INFO_Button();
		//		}else {
		//			this.oInfoButton = new ownInternal_INFO_Button();
		//		}

		//hier ist der ButtonOpenMask ausnahmsweise kein Textbutton, sondern wird beim fuellen mit einem label bestueckt
		this.oButtonOpenMask.setStyle(MyE2_Button.StyleImageButton());
		this.oButtonOpenMask.set_bID_LIST_equals_ID_MASK(false);


		this.oButtonToSelectRow.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL());
		this.oButtonToSelectRow.add_oActionAgent(new actionAgentSelectRow());

		GridLayoutData GL1 = LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_0_0_5_0);
		GridLayoutData GL2 = LayoutDataFactory.get_GridLayoutGridCenter(E2_INSETS.I_0_0_0_0, null, new Alignment(Alignment.RIGHT, Alignment.CENTER));

		this.add(this.oButtonToSelectRow,GL1);
		this.add(this.oButtonOpenMask,GL2);
		this.add(this.oInfoButton,GL2);
		this.add(new ownButtonSprungIn_ARESSE(),GL2);



		this.oButtonOpenMask.get_vActionAgentsAfterSave().add(new ownActionAfterSave());

		if (this.iWidthAdressText>0)
		{
			this.setColumnWidth(0, new Extent(iWidthAdressText));
			this.EXT().set_oColExtent(new Extent(this.iWidthAdressText+30));
		}

	}


	private class actionAgentSelectRow extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_ComponentMAP  oMap = DB_BUTTON_FIRMA_WITH_INFO.this.EXT().get_oComponentMAP();

			if (oMap != null)
			{
				DB_BUTTON_FIRMA_WITH_INFO.this.EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();
			}
		}
	}


	private class ownActionAfterSave extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			//welche listen-id ist betroffen
			DB_BUTTON_FIRMA_WITH_INFO oThis = DB_BUTTON_FIRMA_WITH_INFO.this;

			E2_ComponentMAP  oMap = oThis.EXT().get_oComponentMAP();
			String cID_MAP = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();

			if (oThis.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to()!=null)
			{
				oThis.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to().Refresh_ComponentMAP(cID_MAP,E2_ComponentMAP.STATUS_VIEW);
				oThis.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to().Mark_ID_IF_IN_Page(cID_MAP);
			}
		}
	}



	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		DB_BUTTON_FIRMA_WITH_INFO oCopy = null;

		try
		{
			oCopy = new DB_BUTTON_FIRMA_WITH_INFO(	this.oButtonOpenMask.EXT_DB().get_oSQLField(),
					this.oFirmenMask,this.cFIELD_HASH_4_BESCHRIFTUNG,this.cORDER_STRING_UP,this.cORDER_STRING_DOWN, this.iWidthAdressText, this.iWidthWith_4_BeginLineWrap);

			//jetzt die einstellungen uebernehmen
			//EXT_DB haengt an der Komponente: oButtonOpenMask
			//EXT an FirmenListButton_WITH_INFO

			oCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oCopy));

			if (this.oButtonOpenMask.getIcon() != null)
				oCopy.get_oButtonOpenMask().setIcon(this.oButtonOpenMask.getIcon());

			if (this.oButtonOpenMask.get_oText() != null)
				oCopy.get_oButtonOpenMask().set_Text(this.oButtonOpenMask.get_oText());

			oCopy.get_oButtonOpenMask().set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oCopy));

			oCopy.get_oButtonOpenMask().setStyle(this.get_oButtonOpenMask().getStyle());
			oCopy.get_oButtonOpenMask().setInsets(this.get_oButtonOpenMask().getInsets());

			oCopy.get_oButtonOpenMask().setLineWrap(this.get_oButtonOpenMask().isLineWrap());

			return oCopy;

		}
		catch (myException ex)
		{
			throw new myExceptionCopy("FirmenListButton_WITH_INFO:get_Copy:Copy-Error!");
		}

	}


	public void set_EXT(MyE2EXT__Component oEXT)
	{
		this.oButtonOpenMask.set_EXT(oEXT);
	}


	public MyE2EXT__Component EXT()
	{
		return this.oButtonOpenMask.EXT();
	}




	public E2_DB_BUTTON_OPEN_MASK_FromID get_oButtonOpenMask() 
	{
		return oButtonOpenMask;
	}





	//##TODO@Sebastien: 25.07.2016 - neues Adresse info system
	public class ownInternal_INFO_Button extends MyE2_Button implements IF_components_4_decision 
	{
		private Vector<XX_ActionAgent> 		v_storage_vector_4_action_agents = new Vector<>();
		private Vector<XX_ActionAgent> 		v_storage_vector_4_status_at_start = new Vector<>();

		public ownInternal_INFO_Button() throws myException 
		{
			super();
			this.__setImages(E2_ResourceIcon.get_RI("inforound_mini.png"),E2_ResourceIcon.get_RI("inforound_mini.png"));
			this.setStyle(MyE2_Button.StyleImageButton());


			this.add_GlobalAUTHValidator_PROGRAMM_WIDE(DB_BUTTON_FIRMA_WITH_INFO.this.cBUTTONKENNER_ADRESSUEBERSICHT);

			this.setToolTipText(new MyE2_String("Adress-Informationen anzeigen ...").CTrans());
			if(bibALL.get_bDebugMode()){
				
				this.add_oActionAgent(new ownDecisionActionAgent(this));
			
			}
			else{
				this.add_oActionAgent(new XX_ActionAgent() 
				{
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException 
					{

						if(ENUM_MANDANT_DECISION.USE_NEW_INFO_BUTTON.is_YES()){
							new ownBasicContainer_ng();
						}else{
							new ownBasicContainer();
						}
					}
				});
			}
		}

		private class ownDecisionActionAgent extends ActionAgent_4_decision{

			public ownDecisionActionAgent(IF_components_4_decision p_actionComponent) {
				super(p_actionComponent);

			}

			@Override
			public Boolean make_decision_true_4_special__false_4_normal(
					Vector<XX_ActionAgent> v_addon_actions_when_false) throws myException {

				return true;
			}

			@Override
			public void generate_fill_and_show_popup(IF_components_4_decision activeComponent,
					Vector<XX_ActionAgent> v_standardAgents) throws myException {

				E2_BasicModuleContainer container = new E2_BasicModuleContainer();

				container.set_bVisible_Row_For_Messages(false);
				container.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_4_POPUP_MESSAGES());

				MyE2_Button alt_iz_bt = new MyE2_Button(new MyE2_String("Altes Info-Zentrum"));
				MyE2_Button neu_iz_bt = new MyE2_Button(new MyE2_String("Neue Info-Zentrum"));

				E2_Grid4MaskSimple grid = new E2_Grid4MaskSimple();
				grid.def_(E2_INSETS.I(5,10,5,10)).def_(2, 1).add_(new MyE2_Label(new MyE2_String("Im Debug-Modus wählen: Altes oder neues Info-Zentrum")))
				.def_(E2_INSETS.I(5,20,5,10)).def_(1, 1).add_(alt_iz_bt).add_(neu_iz_bt).setSize_(2);

				alt_iz_bt.add_oActionAgent(v_standardAgents, false);

				alt_iz_bt.add_oActionAgent(new XX_ActionAgent(){
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						new ownBasicContainer();						
					}});
				
				neu_iz_bt.add_oActionAgent(new XX_ActionAgent() {
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
						new ownBasicContainer_ng();						
					}});

				container.add(grid, E2_INSETS.I(2,2,2,2));
				container.CREATE_AND_SHOW_POPUPWINDOW(new Extent(380), new Extent(150), new MyE2_String("Ihre Auswahl:"));

			}

		}
		
		


		private class ownBasicContainer extends E2_BasicModuleContainer
		{

			public ownBasicContainer() throws myException 
			{
				super();
				FS__Adress_Info_Zentrum oAdressInfo = new FS__Adress_Info_Zentrum(this);

				String cID_ADRESSE = bibALL.ReplaceTeilString(S.NN(DB_BUTTON_FIRMA_WITH_INFO.this.oButtonOpenMask.EXT().get_C_MERKMAL()),".","");

				if (S.isEmpty(cID_ADRESSE) || !bibALL.isLong(cID_ADRESSE))
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Adresse konnte nicht gefunden werden !!")));
					return;
				}

				oAdressInfo.init_INFO(cID_ADRESSE);

				this.add(oAdressInfo, E2_INSETS.I_2_2_2_2);

				this.set_iVerticalOffsetForTabbedPane(130);
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(600), 
						new MyE2_String("Übersicht: ",true,oAdressInfo.get_recADRESSE().get___KETTE(bibALL.get_Vector("NAME1", "NAME2","ORT")),false));

				
				
			}
			
		}

		private class ownBasicContainer_ng extends E2_BasicModuleContainer{
			public ownBasicContainer_ng() throws myException {
				super();
				FS__Adress_Info_Zentrum_NG oAdressInfo = new FS__Adress_Info_Zentrum_NG(this);

				String cID_ADRESSE = bibALL.ReplaceTeilString(S.NN(DB_BUTTON_FIRMA_WITH_INFO.this.oButtonOpenMask.EXT().get_C_MERKMAL()),".","");

				if (S.isEmpty(cID_ADRESSE) || !bibALL.isLong(cID_ADRESSE))
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Adresse konnte nicht gefunden werden !!")));
					return;
				}

				oAdressInfo.init_INFO(cID_ADRESSE);

				this.add(oAdressInfo, E2_INSETS.I_2_2_2_2);

				this.set_oResizeHelper(new ownResizer());

				this.set_iVerticalOffsetForTabbedPane(130);
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(800), 
						new MyE2_String("Übersicht: ",true,oAdressInfo.get_recADRESSE().get___KETTE(bibALL.get_Vector("NAME1", "NAME2","ORT")),false," (*)", false));

			}
			
			private class ownResizer extends XX_BasicContainerResizeHelper {
				@Override
				public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {
					
					Extent  oWidth = ownContainer.get_oExtWidth();
					Extent  oHeight = ownContainer.get_oExtHeight();
					
					if (oWidth.getUnits()==Extent.PX && oHeight.getUnits()==Extent.PX)
					{
						E2_RecursiveSearch_Component oSearch = new E2_RecursiveSearch_Component(ownContainer, bibALL.get_Vector(MyE2_ContainerEx.class.getName()), null);
						
						if (oSearch.get_vAllComponents().size()==1)
						{
							MyE2_ContainerEx oContainerEx = (MyE2_ContainerEx)oSearch.get_vAllComponents().get(0);

							oContainerEx.setHeight(new Extent(oHeight.getValue()-200));
						}
					}
					
				}
			}

		}

		@Override
		public Vector<XX_ActionAgent> get_storage_vector_4_action_agents() throws myException {
			return v_storage_vector_4_action_agents;
		}

		@Override
		public Vector<XX_ActionAgent> get_storage_vector_4_status_at_start() throws myException {
			return v_storage_vector_4_status_at_start;
		}
	}





	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException 
	{
		this.oButtonOpenMask.prepare_ContentForNew(bSetDefault);
	}


	@Override
	public String get_cActualMaskValue() throws myException 
	{
		return this.oButtonOpenMask.get_cActualMaskValue();
	}


	@Override
	public String get_cActualDBValueFormated() throws myException 
	{
		return this.oButtonOpenMask.get_cActualDBValueFormated();
	}


	@Override
	public void set_cActualMaskValue(String cText) throws myException 
	{
		this.oButtonOpenMask.set_cActualMaskValue(cText);
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText,String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException 
	{
		this.oButtonOpenMask.set_cActual_Formated_DBContent_To_Mask(cText, cMASK_STATUS, oResultMAP);

		if (S.isFull(cText))
		{
			this.oButtonOpenMask.setToolTipText(new MyE2_String("Öffnet die Maske der Adresse ...").CTrans()+new MyE2_String("ID: "+S.NN(cText),false).toString());
		}
		else
		{
			this.oButtonOpenMask.setToolTipText(new MyE2_String("Keine Adresse vorhanden ...").CTrans());
		}

		String cButtonText = "";

		if (S.isFull(this.cFIELD_HASH_4_BESCHRIFTUNG))
		{
			cButtonText = oResultMAP.get_UnFormatedValue(this.cFIELD_HASH_4_BESCHRIFTUNG);
		}
		else
		{
			DotFormatterGermanFixed dfText = new DotFormatterGermanFixed(cText);
			if (dfText.doFormat())
			{
				RECORD_ADRESSE recAdress = new RECORD_ADRESSE(dfText.get_oLong());
				cButtonText = recAdress.get___KETTE(bibALL.get_Vector("NAME1", "ORT"));
			}
		}

		if (S.isEmpty(cButtonText))
		{
			cButtonText = "-";
		}

		//der eigentliche masken-button wird ohne test gezeigt und bekommt einen stift als button-icon
		this.oButtonOpenMask.setText("");
		this.oButtonOpenMask.setIcon(S.isEmpty(cButtonText)?E2_ResourceIcon.get_RI("empty10.png"):E2_ResourceIcon.get_RI("edit_list2.png"));


		this.oButtonToSelectRow.setText(cButtonText);

		//wenn der text die grenze ueberschreitet, dann kleinerer font
		if (this.iWidthWith_4_BeginLineWrap>0)
		{
			if (S.NN(this.oButtonToSelectRow.getText()).trim().length()>this.iWidthWith_4_BeginLineWrap)
			{
				//this.oButtonOpenMask.setFont(new E2_FontPlain(-2));
				//				this.oButtonOpenMask.setLineWrap(true);
				this.oButtonToSelectRow.setLineWrap(true);
			}
		}

	}


	@Override
	public void set_bIsComplexObject(boolean bisComplex) 
	{
		this.oButtonOpenMask.set_bIsComplexObject(bisComplex);
	}


	@Override
	public boolean get_bIsComplexObject() 
	{
		return this.oButtonOpenMask.get_bIsComplexObject();
	}

	@Override
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP,SQLMaskInputMAP oMaskInputMap) throws myException 
	{
		return this.oButtonOpenMask.get_vInsertStack(oE2_ComponentMAP, oMaskInputMap);
	}


	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP,SQLMaskInputMAP oMaskInputMap) throws myException 
	{
		return this.oButtonOpenMask.get_vUpdateStack(oE2_ComponentMAP, oMaskInputMap);
	}


	@Override
	public MyE2EXT__DB_Component EXT_DB() 
	{
		return this.oButtonOpenMask.EXT_DB();
	}


	@Override
	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB) 
	{
		this.oButtonOpenMask.set_EXT_DB(oEXT_DB);
	}


	@Override
	public void make_Look_Deleted(boolean bIsDeleted)
	{
		if (this.oButtonToSelectRow!=null)
		{
			this.oButtonToSelectRow.setFont(bIsDeleted?new E2_Font(Font.ITALIC+Font.LINE_THROUGH,-2):new E2_FontPlain());
		}
	}


	@Override
	public void setForeColorActive(Color ForeColor)
	{
		Color oForeColor = ForeColor==null?Color.BLACK:ForeColor;

		if (this.oButtonToSelectRow!=null)
		{
			this.oButtonToSelectRow.setForeground(oForeColor);
		}
	}


	@Override
	public void setFontActive(Font oFont)
	{
		Font oFont4Mark = oFont==null?new E2_FontPlain():oFont;

		if (this.oButtonToSelectRow!=null)
		{
			this.oButtonToSelectRow.setFont(oFont4Mark);
		}
	}



	//	@Override
	//	public Color get_ForeColor_of_markableComponent()
	//	{
	//		Color oColRueck = null;
	//		
	//		if (this.oButtonToSelectRow!=null)
	//		{
	//			oColRueck = this.oButtonToSelectRow.getForeground();
	//		}
	//		return oColRueck;
	//	}

	//private Color unmarked_Color = null;

	@Override
	public Color get_Unmarked_ForeColor() {
		return this.oButtonToSelectRow.getForeground();
	}

	@Override
	public Font get_Unmarked_Font() {
		return this.oButtonToSelectRow.getFont();
	}






	//2012-02-01: sprung in die adresse mit einbauen
	private class ownButtonSprungIn_ARESSE extends MyE2_Button
	{
		public ownButtonSprungIn_ARESSE() throws myException
		{
			super(E2_ResourceIcon.get_RI("kompass_mini.png"), E2_ResourceIcon.get_RI("kompass_mini.png"));

			this.setStyle(MyE2_Button.StyleImageButton());

			this.setToolTipText(new MyE2_String("Spring ins Adressmodul zu dieser Adresse").CTrans());

			this.add_oActionAgent(new ownActionJumpToFirma());
		}
	}


	private class ownActionJumpToFirma extends XX_ActionAgentJumpToTargetList
	{
		public ownActionJumpToFirma() throws myException 
		{
			super( E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST,"Firmenstamm");
		}


		@Override
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();

			if (vTargetList.size()==0)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Adresse !",true)));
			}
			return oMV;
		}


		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
		{
			Vector<String> vRueck = new Vector<String>();
			String cActualAdress = bibALL.ReplaceTeilString(S.NN(DB_BUTTON_FIRMA_WITH_INFO.this.oButtonOpenMask.EXT().get_C_MERKMAL()),".","");
			if (S.isFull(cActualAdress) && bibALL.isLong(cActualAdress))
			{
				vRueck.add(cActualAdress);
			}
			return vRueck;
		}
	}





}

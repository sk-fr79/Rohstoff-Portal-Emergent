package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import java.util.ArrayList;
import java.util.HashMap;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_vCombinedComponentMAPs;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMASK;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_PositionSorting;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_Validator_KOPF_UND_POSITION_OFFEN;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE.DATUMSKORREKTUR.Bt_korrigiereAngebot;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE.DATUMSKORREKTUR.Bt_korrigiereAngebotPosition;



public class BSA_K_LIST_EXPANDER_4_ComponentMAP extends XX_List_EXPANDER_4_ComponentMAP
{
	public static GridLayoutData GridLayoutDataLeft = null;
	public static GridLayoutData GridLayoutDataRight = null;
	static
	{
		GridLayoutData left = new GridLayoutData();
		GridLayoutData right = new GridLayoutData();

		left.setInsets(E2_INSETS.I_2_2_10_2);
		left.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));

		right.setInsets(E2_INSETS.I_2_2_10_2);
		right.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
	
		GridLayoutDataLeft=left;
		GridLayoutDataRight=right;
	}
	
	public static E2_ResourceIcon oIconLeer = E2_ResourceIcon.get_RI("leer.png");
	
	
	private MyE2_Row	  							oRow = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
	private String 		 							ID_ROW_Unformated_VKOPF = null;
	private BSA_K_LIST__ModulContainer				oKopfModulcontainerList = null;
	private BS__SETTING							oSetting = null;
	
	
	public BSA_K_LIST_EXPANDER_4_ComponentMAP(BSA_K_LIST__ModulContainer	 KopfModulcontainerList, E2_NavigationList NavigationList, BS__SETTING		Setting) throws myException
	{
		super(NavigationList);
		this.set_iLeftColumnOffset(5);
		this.oKopfModulcontainerList = KopfModulcontainerList;
		this.oSetting=Setting;
	}

	public Component get_ComponentDaughter(String cID_ROW_Unformated)
	{
		this.ID_ROW_Unformated_VKOPF = cID_ROW_Unformated;
		
		this.FillComponent();
		
		return this.oRow;
	}

	
	private void FillComponent()
	{
		this.oRow.removeAll();
		
		
		
		// sicherheitsabfrage
		if (bibALL.isEmpty(this.ID_ROW_Unformated_VKOPF))
			return;
		
		
		MyE2_Grid oGrid = new MyE2_Grid(8, MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		oGrid.setBackground(new E2_ColorLLLight());
		
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		
		/*
		 * unterscheiden, ob mit eingeschalteten DELETED - saetzen oder ohne
		 */
		String cZusatzWhere = "";
		if (! this.oKopfModulcontainerList.get_oSelektor().get_oCB_ShowDeletedRows().isSelected())
			cZusatzWhere = "   NVL(JT_VPOS_STD.DELETED,'N')='N' AND ";
			
		/*
		 * es kann auch sein, dass bei ausgeschalteten DELETED gerade einer geloescht wurde,
		 * der noch in der liste steht
		 */
		boolean bGeradeGeloescht = oDB.EinzelAbfrage("SELECT   NVL(DELETED,'N') FROM "+bibE2.cTO()+".JT_VKOPF_STD WHERE ID_VKOPF_STD="+this.ID_ROW_Unformated_VKOPF).equals("Y");
		if (bGeradeGeloescht)
			cZusatzWhere = "";
		
		
		String[][] cResult = oDB.EinzelAbfrageInArrayFormatiert(
									"SELECT " +
									" JT_VPOS_STD.ANZAHL," +
									" JT_VPOS_STD.ARTBEZ1," +
									"  NVL(JT_VPOS_STD.ANR1,'--')||'/'|| NVL(JT_VPOS_STD.ANR2,'--')," +
									" JT_VPOS_STD.EINZELPREIS," +
									" JT_VPOS_STD.GESAMTPREIS," +
									" JT_VPOS_STD.ID_VPOS_STD," +
									"   NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'DD.MM.YYYY'),'-------')||' - '||  NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_BIS,'DD.MM.YYYY'),'------'),"+
									"   NVL(JT_VPOS_STD.DELETED,'N')," +
									"   JT_VPOS_STD.ID_VPOS_STD" +
									" FROM " +
				           bibE2.cTO()+".JT_VPOS_STD," +
				           bibE2.cTO()+".JT_VPOS_STD_ANGEBOT " +
				           		" WHERE " +
				           		" JT_VPOS_STD.ID_VPOS_STD=JT_VPOS_STD_ANGEBOT.ID_VPOS_STD AND "+cZusatzWhere+
				           		" JT_VPOS_STD.ID_VKOPF_STD="+this.ID_ROW_Unformated_VKOPF+" ORDER BY POSITIONSNUMMER","###");
		
		String[][] cAbgeschlossenKopf = oDB.EinzelAbfrageInArray("SELECT  NVL(JT_VKOPF_STD.ABGESCHLOSSEN,'N'), NVL(JT_VKOPF_STD.DELETED,'N') FROM " +
											" "+bibE2.cTO()+".JT_VKOPF_STD " +
											" WHERE JT_VKOPF_STD.ID_VKOPF_STD = "+this.ID_ROW_Unformated_VKOPF);

		bibALL.destroy_myDBToolBox(oDB);
		
		if (cResult==null || cAbgeschlossenKopf == null)
		{
			oGrid.add(new MyE2_Label(new MyE2_String("Abfragefehler !!")),6,E2_INSETS.I_5_0_5_0);
		}
		else
		{
			try
			{
				boolean b_abgeschlossen_oder_geloescht = S.NN(cAbgeschlossenKopf[0][0]).equals("Y")||S.NN(cAbgeschlossenKopf[0][1]).equals("Y");
				// ueberschrift
				if (cAbgeschlossenKopf[0][0].equals("N") && cAbgeschlossenKopf[0][1].equals("N"))
					oGrid.add(new newButton());
				else
					oGrid.add(new MyE2_Label(BSA_K_LIST_EXPANDER_4_ComponentMAP.oIconLeer));
	
				// ueberschrift
				oGrid.add(new MyE2_Label("S"));
	
				oGrid.add(this.makeLabel("Anzahl",true, true, false));
				oGrid.add(this.makeLabel("Bezeichnung",false, true, false));
				oGrid.add(this.makeLabel("Sortencode",false, true, false));
				oGrid.add(this.makeLabel("Einzel",true, true, false));
				oGrid.add(this.makeLabel("Gesamt",true, true, false));
				if (!b_abgeschlossen_oder_geloescht) {
					oGrid.add(new Bt_korrigiereAngebot("Gültigkeit", this.ID_ROW_Unformated_VKOPF, this));
				} else {
					oGrid.add(this.makeLabel("Gültigkeit",true, true, false));
				}
				
				for (int i=0;i<cResult.length;i++)
				{
	
					boolean bDeleted = cResult[i][7].equals("Y");
	
	
					// bearbeiten-spalte
					if (cAbgeschlossenKopf[0][0].equals("N") && !bDeleted)
						oGrid.add(new editButton(bibALL.ReplaceTeilString(cResult[i][5],".","")));
					else
						oGrid.add(new viewButton(bibALL.ReplaceTeilString(cResult[i][5],".","")));
	
					// move-up-spalte
					if (cAbgeschlossenKopf[0][0].equals("N") && !bDeleted)
						oGrid.add(new moveUpButton(bibALL.ReplaceTeilString(cResult[i][5],".","")));
					else
						oGrid.add(new MyE2_Label(BSA_K_LIST_EXPANDER_4_ComponentMAP.oIconLeer));
	
					
					oGrid.add(this.makeLabel(cResult[i][0],true, false, bDeleted));
					oGrid.add(this.makeLabel(cResult[i][1],false, false, bDeleted));
					oGrid.add(this.makeLabel(cResult[i][2],false, false, bDeleted));
					oGrid.add(this.makeLabel(cResult[i][3],true, false, bDeleted));
					oGrid.add(this.makeLabel(cResult[i][4],true, false, bDeleted));
					if (!b_abgeschlossen_oder_geloescht) {
						oGrid.add(new Bt_korrigiereAngebotPosition(cResult[i][6], cResult[i][8], this));
					} else {
						oGrid.add(this.makeLabel(cResult[i][6],true, false, bDeleted));
					}
				}
				
				// spaltenbreiten auseinanderziehen
				oGrid.setColumnWidth(0, new Extent(20));
				oGrid.setColumnWidth(1, new Extent(20));
				oGrid.setColumnWidth(2, new Extent(100));
				oGrid.setColumnWidth(3, new Extent(300));
				oGrid.setColumnWidth(4, new Extent(100));
				oGrid.setColumnWidth(5, new Extent(100));
				oGrid.setColumnWidth(6, new Extent(100));
				oGrid.setColumnWidth(7, new Extent(100));
			}
			catch (myException ex)
			{
				oGrid.removeAll();
				oGrid.add(new MyE2_Label(ex.get_ErrorMessage(),MyE2_Label.STYLE_TITEL_BIG()),8,E2_INSETS.I_10_0_10_0);
			}
		}

		this.oRow.add(oGrid,new Insets(0));
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			BSA_K_LIST_EXPANDER_4_ComponentMAP oCopy = 
				new BSA_K_LIST_EXPANDER_4_ComponentMAP(this.oKopfModulcontainerList,this.get_oNavigationList(),this.oSetting);
			return oCopy;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
		
	}


	private MyE2_Label makeLabel(String cText,boolean bIstZahl, boolean bIsTitle, boolean bIsDeleted)
	{
		String ccText = "";
		
		if (cText.equals("###"))
			ccText = "-";
		else
			ccText = cText;
		
		MyE2_Label oLabRueck = null;

		E2_Font oDelFont = new E2_Font(Font.ITALIC+Font.LINE_THROUGH,-2);

		
		if (bIsTitle)
		{
			oLabRueck = new MyE2_Label(new MyE2_String(ccText));
			oLabRueck.setFont(new E2_FontItalic(-2));
		}
		else
		{
			oLabRueck = new MyE2_Label(ccText);
			if (bIsDeleted)
				oLabRueck.setFont(oDelFont);
			else
				oLabRueck.setFont(new E2_FontPlain(-2));
		}
		
		if (!bIstZahl)
			oLabRueck.setLayoutData(BSA_K_LIST_EXPANDER_4_ComponentMAP.GridLayoutDataLeft);
		else
			oLabRueck.setLayoutData(BSA_K_LIST_EXPANDER_4_ComponentMAP.GridLayoutDataRight);
			
		return oLabRueck;
	}

	
	
	
	public void refreshDaughterComponent()
	{
		this.FillComponent();
	}
	

	
	
	/*
	 * sub-klassen fuer editieren/anschauen der maske
	 */
	
	
	//// edit-button und actionagent
	private class editButton extends MyE2_Button
	{

		public editButton(String cID_POS_UNFORMATED) throws myException
		{
			super(E2_ResourceIcon.get_RI("edit.png"), E2_ResourceIcon.get_RI("leer.png"));
			
			this.add_oActionAgent(new ownActionAgentEditPosition(cID_POS_UNFORMATED));
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(BSA_K_LIST_EXPANDER_4_ComponentMAP.this.oKopfModulcontainerList.get_MODUL_IDENTIFIER(),
											"BEARBEITEN_POSITION_IN_MASKE"));
			
			this.add_IDValidator(new BS_Validator_KOPF_UND_POSITION_OFFEN("JT_VPOS_STD"));
			this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_STD","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Position wurde bereits gelöscht !")));

			this.setToolTipText(new MyE2_String("Positionssatz bearbeiten ").CTrans());
		}
		
	}

	
	private class ownActionAgentEditPosition extends XX_ActionAgent 
	{

		private String cID_POS_UNFORMATED = null;
		
		public ownActionAgentEditPosition(String ID_POS_UNFORMATED)
		{
			super();
			this.cID_POS_UNFORMATED = ID_POS_UNFORMATED;
		}

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			// zuerst die validierung
			MyE2_Button oBut = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			
			bibMSG.add_MESSAGE(oBut.valid_IDValidation(bibALL.get_Vector(this.cID_POS_UNFORMATED)));

			if (bibMSG.get_bIsOK())
			{
				BSA_K_LIST_EXPANDER_4_ComponentMAP oThis = BSA_K_LIST_EXPANDER_4_ComponentMAP.this;
	
				BSA_P_MASK__ModulContainer oMaskPositions = new BSA_P_MASK__ModulContainer(	oThis.oSetting);
				oMaskPositions.get_oRowForButtons().removeAll();
				oMaskPositions.get_oRowForButtons().add(new maskButtonSaveMask(oMaskPositions,new ownSaveMASK(oMaskPositions),null, null));
				oMaskPositions.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMaskPositions));
		
				oMaskPositions.make_SettingsFrom_Head_to_Position(oThis.ID_ROW_Unformated_VKOPF);
					
				E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMaskPositions.get_vCombinedComponentMAPs();
				vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,this.cID_POS_UNFORMATED);
				oMaskPositions.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Position bearbeiten ..."));
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Bearbeite Angebotsposition: "+this.cID_POS_UNFORMATED,true),true);
			}		
		}
		
	}

	
	
	/*
	 * eigener maskSaver, damit die tochterkomponente gleich refresh werden kann 
	 */
	private class ownSaveMASK extends E2_SaveMASK
	{

		public ownSaveMASK(E2_BasicModuleContainer_MASK maskContainer)
		{
			super(maskContainer, null);
		}

		public boolean checkMaskBeforeSave(E2_BasicModuleContainer_MASK oMaskContainer, E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps, String cActualMaskStatus)
		{
			return true;
		}

		public void actionAfterSaveMask() throws myException
		{
			BSA_K_LIST_EXPANDER_4_ComponentMAP oThis = BSA_K_LIST_EXPANDER_4_ComponentMAP.this;
			
			// NavigationList der Kopfsaetze
			E2_NavigationList oList = BSA_K_LIST_EXPANDER_4_ComponentMAP.this.get_oNavigationList();
			
			// E2_ComponentMap rausziehen
			E2_ComponentMAP oMapToRefresh = oList.get_ComponentMAP(oThis.ID_ROW_Unformated_VKOPF);
			
			// listeneintrag des kopfsatzes aktualisieren, dabei wird auch diese BSA_K_ComponentMAP_DaughterWithPositions aktualisiert
			oMapToRefresh._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW,false,null);
			
			
		}
		
	}
	
	
	/*
	 * sub-klassen fuer editieren/anschauen der maske
	 */
	//// view-button und actionagent
	private class viewButton extends MyE2_Button
	{

		public viewButton(String cID_POS_UNFORMATED)
		{
			super(E2_ResourceIcon.get_RI("view.png"), E2_ResourceIcon.get_RI("leer.png"));
			this.add_oActionAgent(new ownActionAgentViewPosition(cID_POS_UNFORMATED));
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(BSA_K_LIST_EXPANDER_4_ComponentMAP.this.oKopfModulcontainerList.get_MODUL_IDENTIFIER(),
										"ANZEIGE_POSITION_IN_MASKE"));
			
			this.setToolTipText(new MyE2_String("Positionssatz anzeigen ").CTrans());

		}
		
	}

	
	private class ownActionAgentViewPosition extends XX_ActionAgent 
	{

		private String cID_POS_UNFORMATED = null;
		
		public ownActionAgentViewPosition(String ID_POS_UNFORMATED)
		{
			super();
			this.cID_POS_UNFORMATED = ID_POS_UNFORMATED;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				BSA_K_LIST_EXPANDER_4_ComponentMAP oThis = BSA_K_LIST_EXPANDER_4_ComponentMAP.this;

				BSA_P_MASK__ModulContainer oMaskPositions = new BSA_P_MASK__ModulContainer(oThis.oSetting);

				
				oMaskPositions.get_oRowForButtons().removeAll();
				oMaskPositions.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMaskPositions));

				oMaskPositions.make_SettingsFrom_Head_to_Position(oThis.ID_ROW_Unformated_VKOPF);
				
				E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMaskPositions.get_vCombinedComponentMAPs();
				vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,this.cID_POS_UNFORMATED);
				oMaskPositions.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Position anzeigen ..."));
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Anzeige Angebotsposition: "+this.cID_POS_UNFORMATED));
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
		}
		
	}
	//// view-button und actionagent


	
	
	
	//// neu-button und action-agent
	private class newButton extends MyE2_Button
	{

		public newButton() throws myException
		{
			super(E2_ResourceIcon.get_RI("new.png"), E2_ResourceIcon.get_RI("leer.png"));

			this.add_oActionAgent(new ownActionAgentNewPosition());
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(BSA_K_LIST_EXPANDER_4_ComponentMAP.this.oKopfModulcontainerList.get_MODUL_IDENTIFIER(),
											"NEUEINGABEPOSITION_IN_MASKE"));
			
			this.setToolTipText(new MyE2_String("Neuen Positionssatz eingeben").CTrans());
			this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VKOPF_STD","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Der Vorgang wurde bereits gelöscht !")));
			
		}
		
	}

	
	private class ownActionAgentNewPosition extends XX_ActionAgent 
	{

		public ownActionAgentNewPosition()
		{
			super();
		}

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
				
			BSA_K_LIST_EXPANDER_4_ComponentMAP oThis = BSA_K_LIST_EXPANDER_4_ComponentMAP.this;
			
			// integritaet pruefen und fuellen und uebergeben an container
			bibE2.get_LAST_ACTIONEVENT().make_ID_Validation_ADD_TO_Global_MV(bibALL.get_Vector(oThis.ID_ROW_Unformated_VKOPF));

			if (bibMSG.get_bIsOK())
			{
			
				BSA_P_MASK__ModulContainer oMaskPositions = new BSA_P_MASK__ModulContainer(	oThis.oSetting);
	
				oMaskPositions.get_oRowForButtons().removeAll();
				oMaskPositions.get_oRowForButtons().add(new maskButtonSaveMask(oMaskPositions,new ownSaveMASK(oMaskPositions),null, null));
				oMaskPositions.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMaskPositions));
			
				oMaskPositions.make_SettingsFrom_Head_to_Position(oThis.ID_ROW_Unformated_VKOPF);
				
				E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMaskPositions.get_vCombinedComponentMAPs();
				vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_NEW_EMPTY,null);
				oMaskPositions.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Position neueingeben ..."));
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Neueingabe Angebotsposition ...",true),true);
			}		
		}
		
	}
	//// edit-button und actionagent

	
	
	
	
	
	
	private class moveUpButton extends MyE2_Button
	{
		public moveUpButton(String cID_VPOS)
		{
			super(E2_ResourceIcon.get_RI("moveup.png"));
			this.EXT().set_C_MERKMAL(cID_VPOS);
			this.add_oActionAgent(new ownActionAgentMoveUp());
		}
		
	}
	
	private class ownActionAgentMoveUp extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			MyE2_Button oButtonSource = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			String cID = oButtonSource.EXT().get_C_MERKMAL();
			
			if (bibALL.isEmpty(cID))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Sortieren !"));
			}
			else
			{
				try
				{
					new BS_PositionSorting(cID,BS_PositionSorting.DOWN,BSA_K_LIST_EXPANDER_4_ComponentMAP.this.oSetting.get_oVorgangTableNames());
					BSA_K_LIST_EXPANDER_4_ComponentMAP.this.refreshDaughterComponent();
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Reihenfolge wurde geändert"));
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
				
			
			
		}
		
	}

	@Override
	public ArrayList<HashMap<String, Component>>  get_ComponentListDaughter(String unformated) throws myException
	{
		return null;
	}

	
	
	
	
}

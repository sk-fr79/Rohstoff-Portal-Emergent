package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import echopointng.PopUp;

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
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.E2_Validator_ID_DBQuery;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
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
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_PositionSorting;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_Validator_KOPF_UND_POSITION_OFFEN;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.STORNO.DataRecordCopySQL_Kopf;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.STORNO.SQL_StatementBuilderForPositions;
import rohstoff.utils.My_VKopf_RG;




public class BSRG_K_LIST_EXPANDER_4_ComponentMAP extends XX_List_EXPANDER_4_ComponentMAP
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
	private BSRG_K_LIST__ModulContainer				oKopfModulcontainerList = null;
	private BS__SETTING								oSetting = null;
	
	private Vector<MyE2_CheckBox>  					vCB_Storno = new Vector<MyE2_CheckBox>();
	
	
	public BSRG_K_LIST_EXPANDER_4_ComponentMAP(BSRG_K_LIST__ModulContainer	 KopfModulcontainerList, E2_NavigationList NavigationList, BS__SETTING		Setting) throws myException
	{
		super(NavigationList);
		this.set_iLeftColumnOffset(5);
		this.oKopfModulcontainerList = KopfModulcontainerList;
		this.oSetting=Setting;
	}

	
	public Component get_ComponentDaughter(String cID_ROW_Unformated) throws myException
	{
		this.ID_ROW_Unformated_VKOPF = cID_ROW_Unformated;
		
		this.FillComponent();
		
		return this.oRow;
	}

	
	private void FillComponent() throws myException
	{
		this.oRow.removeAll();
		
		
		
		// sicherheitsabfrage
		if (bibALL.isEmpty(this.ID_ROW_Unformated_VKOPF))
			return;
		
		/*
		 * unterscheiden, ob mit eingeschalteten DELETED - saetzen oder ohne
		 */
		boolean bZeigeGeloeschte= this.oKopfModulcontainerList.get_oSelektor().get_oCB_ShowDeletedRows().isSelected();
			
		
		MyE2_Grid oGrid = new MyE2_Grid(15, MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		oGrid.setBackground(new E2_ColorLLLight());
		
		try
		{
			RECORD_VKOPF_RG  recVKOPF = new RECORD_VKOPF_RG(this.ID_ROW_Unformated_VKOPF);
			
			boolean bGeradeGeloescht = recVKOPF.is_DELETED_YES();
			
			// ueberschrift
			if (recVKOPF.is_ABGESCHLOSSEN_NO() && recVKOPF.is_DELETED_NO())
				oGrid.add(new newButton());
			else
				oGrid.add(new MyE2_Label(BSRG_K_LIST_EXPANDER_4_ComponentMAP.oIconLeer));

			// ueberschrift
			oGrid.add(new MyE2_Label("S"));
			oGrid.add(new MyE2_Label("-"));
			oGrid.add(new MyE2_Label("-"));
			oGrid.add(new buttonStorno(recVKOPF));
			
			GridLayoutData gLL = BSRG_K_LIST_EXPANDER_4_ComponentMAP.GridLayoutDataLeft;
			GridLayoutData gLR = BSRG_K_LIST_EXPANDER_4_ComponentMAP.GridLayoutDataRight;

			oGrid.add(this.makeLabel("Menge",gLR, true, false));
			oGrid.add(this.makeLabel("Bezeichnung",gLL, true, false));
			oGrid.add(this.makeLabel("Sortencode",gLL, true, false));
			oGrid.add(this.makeLabel("Einzel",gLR,true,  false));
			oGrid.add(this.makeLabel("Gesamt", gLR,true, false));
			
			if (recVKOPF.is_ABGESCHLOSSEN_NO() && recVKOPF.is_DELETED_NO())
			{
				MyE2_Grid oGridHelp = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
				oGridHelp.add(this.makeLabel("L-Dat.",MyE2_Grid.LAYOUT_RIGHT_TOP(E2_INSETS.I_0_0_5_0), true,  false));
				oGridHelp.add(new sorterVarianten(),MyE2_Grid.LAYOUT_RIGHT_TOP(E2_INSETS.I_0_0_5_0));
				oGrid.add(oGridHelp,MyE2_Grid.LAYOUT_RIGHT(E2_INSETS.I_0_0_0_0));
			}
			else
			{
				oGrid.add(this.makeLabel("L-Dat.",gLR, true,  false));
			}

			
			oGrid.add(this.makeLabel("Abz.Mge",gLR,true,  false));
			oGrid.add(this.makeLabel("Abz.Pr.",gLR,true,  false));
			
			oGrid.add(this.makeLabel("Fuhre",gLR, true,  false));
			oGrid.add(this.makeLabel("Fuhrenort",gLR, true, false));
			
			//for (int i=0;i<cResult.length;i++)
			for (int i=0;i<recVKOPF.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg(null,"POSITIONSNUMMER",false).get_vKeyValues().size();i++)	
			{
				RECORD_VPOS_RG recVPOS = recVKOPF.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg().get(i);
				
				boolean bDeleted = recVPOS.is_DELETED_YES();

				if (bDeleted && !(bZeigeGeloeschte || bGeradeGeloescht))
				{
					continue;
				}

				// bearbeiten-spalte
				if (	recVKOPF.is_ABGESCHLOSSEN_YES() || 
						bDeleted  || 
						S.isFull(recVPOS.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) || 
						S.isFull(recVPOS.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")))
				{
					oGrid.add(new viewButton(recVPOS.get_ID_VPOS_RG_cUF()));
				}
				else
				{
					oGrid.add(new editButton(recVPOS.get_ID_VPOS_RG_cUF()));
				}

				// move-up-spalte
				if (recVKOPF.is_ABGESCHLOSSEN_NO() && !bDeleted)
					oGrid.add(new moveUpButton(recVPOS.get_ID_VPOS_RG_cUF()));
				else
					oGrid.add(new MyE2_Label(BSRG_K_LIST_EXPANDER_4_ComponentMAP.oIconLeer));

				
				// zurueck-in-freiepositionen-spalte
				if (recVKOPF.is_ABGESCHLOSSEN_NO() && !bDeleted)
					oGrid.add(new MoveToPoolButton(recVPOS.get_ID_VPOS_RG_cUF()));
				else
					oGrid.add(new MyE2_Label(BSRG_K_LIST_EXPANDER_4_ComponentMAP.oIconLeer));

				
				
				// anzeige, ob abzuege da sind
				if (!recVPOS.get_EINZELPREIS_cUF_NN("").equals(recVPOS.get_EINZELPREIS_RESULT_cUF_NN("")))
					oGrid.add(new BSRG__allgemeinLabelAbzuege());
				else
					oGrid.add(new MyE2_Label(BSRG_K_LIST_EXPANDER_4_ComponentMAP.oIconLeer));

				// in dieser spalte werden entweder storno-checkbox oder storno-status angezeigt
				if (S.isFull(recVPOS.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")))
				{
					oGrid.add(new MyE2_Label(E2_ResourceIcon.get_RI("storno_beleg.png")));
				}
				else if (S.isFull(recVPOS.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")))
				{
					oGrid.add(new MyE2_Label(E2_ResourceIcon.get_RI("storno_gegenbeleg.png")));
				}
				else
				{
					MyE2_CheckBox oCB = new MyE2_CheckBox();
					oCB.EXT().set_C_MERKMAL(recVPOS.get_ID_VPOS_RG_cUF());
					oCB.setToolTipText(new MyE2_String("Bitte hier anhaken, wenn storniert werden soll !").CTrans());
					oGrid.add(oCB);
					this.vCB_Storno.add(oCB);
				}
				oGrid.add(this.makeLabel(recVPOS.get_ANZAHL_cF_NN(""),gLR, false, bDeleted));
				oGrid.add(this.makeLabel(recVPOS.get_ARTBEZ1_cF_NN(""),gLL, false, bDeleted));
				oGrid.add(this.makeLabel(recVPOS.get_ANR1_cF_NN("")+"/"+recVPOS.get_ANR2_cF_NN(""),gLL, false, bDeleted));
				oGrid.add(this.makeLabel(recVPOS.get_EINZELPREIS_cF_NN(""),gLR, false, bDeleted));
				oGrid.add(this.makeLabel(recVPOS.get_GESAMTPREIS_cF_NN(""),gLR, false, bDeleted));
				oGrid.add(this.makeLabel(recVPOS.get_AUSFUEHRUNGSDATUM_cF_NN("--"),gLR, false, bDeleted));
				
				oGrid.add(this.makeLabel(recVPOS.get_ANZAHL_ABZUG_cF_NN("--"),gLR, false, bDeleted));
				oGrid.add(this.makeLabel(recVPOS.get_GESAMTPREIS_ABZUG_cF_NN("--"),gLR, false, bDeleted));
				
				oGrid.add(new FuhrenSelectionsButton(recVPOS.get_ID_VPOS_TPA_FUHRE_ZUGEORD_cUF_NN("")),gLR);
				oGrid.add(new FuhrenOrtSelectionsButton(recVPOS.get_ID_VPOS_TPA_FUHRE_ORT_ZUGEORD_cUF_NN("")),gLR);
			}

			// spaltenbreiten auseinanderziehen
			oGrid.setColumnWidth(0, new Extent(20));
			oGrid.setColumnWidth(1, new Extent(20));
			oGrid.setColumnWidth(2, new Extent(20));
			oGrid.setColumnWidth(3, new Extent(20));
			oGrid.setColumnWidth(4, new Extent(20));
			oGrid.setColumnWidth(5, new Extent(100));
			oGrid.setColumnWidth(6, new Extent(200));
			oGrid.setColumnWidth(7, new Extent(100));
			oGrid.setColumnWidth(8, new Extent(100));
			oGrid.setColumnWidth(9, new Extent(100));
			oGrid.setColumnWidth(10, new Extent(100));
			oGrid.setColumnWidth(11, new Extent(100));
		}
		catch (myException ex)
		{
			oGrid.removeAll();
			oGrid.add(new MyE2_Label(ex.get_ErrorMessage(),MyE2_Label.STYLE_TITEL_BIG()),8,E2_INSETS.I_10_0_10_0);
		}

		this.oRow.add(oGrid,new Insets(0));
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			BSRG_K_LIST_EXPANDER_4_ComponentMAP oCopy = 
				new BSRG_K_LIST_EXPANDER_4_ComponentMAP(this.oKopfModulcontainerList,this.get_oNavigationList(),this.oSetting);
			return oCopy;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
		
	}


	private MyE2_Label makeLabel(String cText,GridLayoutData oGL, boolean bIsTitle, boolean bIsDeleted)
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
		
		oLabRueck.setLayoutData(oGL);
			
		return oLabRueck;
	}

	
	
	
	
	
	public void refreshDaughterComponent() throws myException
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
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.oKopfModulcontainerList.get_MODUL_IDENTIFIER(),
											"BEARBEITEN_POSITION_IN_MASKE"));
			
			this.add_IDValidator(new BS_Validator_KOPF_UND_POSITION_OFFEN("JT_VPOS_RG"));
			this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VPOS_RG","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Die Position wurde bereits gelöscht !")));

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

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				// zuerst die validierung
				MyE2_Button oBut = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
				
				bibMSG.add_MESSAGE(oBut.valid_IDValidation(bibALL.get_Vector(this.cID_POS_UNFORMATED)));
				if (bibMSG.get_bHasAlarms())
				{
					return;
				}
				
				BSRG_K_LIST_EXPANDER_4_ComponentMAP oThis = BSRG_K_LIST_EXPANDER_4_ComponentMAP.this;

				if (bibMSG.get_bIsOK())
				{
					BSRG_P_MASK__ModulContainer oMaskPositions = new BSRG_P_MASK__ModulContainer(
																		BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.oSetting);
	
					oMaskPositions.get_oRowForButtons().removeAll();
					oMaskPositions.get_oRowForButtons().add(new maskButtonSaveMask(oMaskPositions,new ownSaveMASK(oMaskPositions),null, null));
					oMaskPositions.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMaskPositions));
			
					oMaskPositions.make_SettingsFrom_Head_to_Position(oThis.ID_ROW_Unformated_VKOPF);
					
					E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMaskPositions.get_vCombinedComponentMAPs();
					vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,this.cID_POS_UNFORMATED);
					oMaskPositions.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Position bearbeiten ..."));
				}
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Bearbeite Vorgangsposition: "+this.cID_POS_UNFORMATED),true);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
		}
		
	}
	//// edit-button und actionagent

	
	
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
			BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.RefreshRow();
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
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.oKopfModulcontainerList.get_MODUL_IDENTIFIER(),
										"ANZEIGE_POSITION_IN_MASKE"));
			
			this.setToolTipText(new MyE2_String("Anzeige Vorgangsposition ").CTrans());
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
				BSRG_K_LIST_EXPANDER_4_ComponentMAP oThis = BSRG_K_LIST_EXPANDER_4_ComponentMAP.this;

				
				BSRG_P_MASK__ModulContainer oMaskPositions = new BSRG_P_MASK__ModulContainer(
																	BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.oSetting);

				
				oMaskPositions.get_oRowForButtons().removeAll();
				oMaskPositions.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMaskPositions));

				oMaskPositions.make_SettingsFrom_Head_to_Position(oThis.ID_ROW_Unformated_VKOPF);
				
				E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMaskPositions.get_vCombinedComponentMAPs();
				vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,this.cID_POS_UNFORMATED);
				oMaskPositions.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Position anzeigen ..."));
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Anzeige Vorgangsposition: "+this.cID_POS_UNFORMATED));
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
		}
		
	}
	//// view-button und actionagent


	
	/*
	 * storno-button fuer die titelzeile
	 */
	private class buttonStorno extends MyE2_Button
	{
		private RECORD_VKOPF_RG recVKOPF = null;
		
		public buttonStorno(RECORD_VKOPF_RG rec_VKOPF)
		{
			super(E2_ResourceIcon.get_RI("storno.png"));
			
			this.recVKOPF = rec_VKOPF;
			
			this.setToolTipText(new MyE2_String("Storniert die ausgewählten Positionen und und erzeugt einen neuen Beleg mit stornierten und neuen Positionen").CTrans());
			
			this.add_GlobalAUTHValidator_AUTO("STORNO_VORGANGSPOSITION");
			
			this.add_IDValidator(new XX_ActionValidator()
			{
				@Override
				public MyE2_MessageVector isValid(String cID_VPOS_RG) throws myException
				{
					MyE2_MessageVector  oMV = new MyE2_MessageVector();
					
					BSRG_K_LIST_EXPANDER_4_ComponentMAP oThis = BSRG_K_LIST_EXPANDER_4_ComponentMAP.this;
					
					RECORD_VKOPF_RG  recKopf = new RECORD_VKOPF_RG(oThis.ID_ROW_Unformated_VKOPF);
					
					if (recKopf.is_DELETED_YES())
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("Ein gelöschter Vorgang kann keine Stornierungen bekommen !"));
					}
					else
					{
						if (recKopf.is_ABGESCHLOSSEN_NO())
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message("Ein Vorgang kann erst storniert werden, wenn er abgeschlossen ist !"));
						}
						else if (S.isFull(recKopf.get_ID_VKOPF_RG_STORNO_NACHFOLGER_cF_NN("")) || S.isFull(recKopf.get_ID_VKOPF_RG_STORNO_VORGAENGER_cF_NN("")))
						{
							oMV.add_MESSAGE(new MyE2_Alarm_Message("Der Vorgang stammt bereits aus einem Stornierungszyklus und kann nicht nochmal storniert werden !"));
						}
						else
						{
							RECORD_VPOS_RG recVPOS = new RECORD_VPOS_RG(cID_VPOS_RG);
							if (recVPOS.is_DELETED_YES())
							{
								oMV.add_MESSAGE(new MyE2_Alarm_Message("Ein gelöschte Position kann keine Stornierung bekommen !"));
							}
							else if (S.isFull(recVPOS.get_ID_VPOS_RG_STORNO_NACHFOLGER_cF_NN("")) || S.isFull(recVPOS.get_ID_VPOS_RG_STORNO_VORGAENGER_cF_NN("")))
							{
								oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Position stammt bereits aus einem Stornierungszyklus und kann nicht nochmal storniert werden !"));
							}
						}
					}
					return oMV;
				}

				@Override
				public MyE2_MessageVector isValid(Component componentWhichIsValidated) throws myException
				{
					return null;
				}

			});

			this.add_oActionAgent(new ownActionStartPopup());
			
		}
		
		
		
		private class ownActionStartPopup extends XX_ActionAgent
		{

			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				BSRG_K_LIST_EXPANDER_4_ComponentMAP oThis = BSRG_K_LIST_EXPANDER_4_ComponentMAP.this;
				Vector<String> vIDs = new Vector<String>();
				
				for (int i=0;i<oThis.vCB_Storno.size();i++)
				{
					if (oThis.vCB_Storno.get(i).isSelected())
					{
						vIDs.add(oThis.vCB_Storno.get(i).EXT().get_C_MERKMAL());
					}
				}
				
				if (vIDs.size()>0)
				{
					bibMSG.add_MESSAGE(buttonStorno.this.valid_IDValidation(vIDs));
					if (bibMSG.get_bIsOK())
					{
						ownConfirmContainer oPopup = new ownConfirmContainer(new MyE2_String("Sind Sie sicher"),
								   new MyE2_String("STORNIEREN ?"),
								   new MyE2_String(""),
								   new MyE2_String("JA"),
								   new MyE2_String("NEIN"),
								   new Extent(400),
								   new Extent(200));
						oPopup.set_ActionAgentForOK(new ownActionAgentStorno());
						oPopup.show_POPUP_BOX();
					}
				}
			}
			
		}
		
		
		
		private class ownConfirmContainer extends E2_ConfirmBasicModuleContainer
		{

			public ownConfirmContainer(MyE2_String windowtitle,
					MyE2_String texttitle, MyE2_String innerTextblock,
					MyE2_String labelOKButton, MyE2_String labelCancelButton,
					Extent width, Extent height)  throws myException
			{
				super(windowtitle, texttitle, innerTextblock, labelOKButton, labelCancelButton,
						width, height);
			}
			
		}
		
		
		
		
		private class ownActionAgentStorno extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				BSRG_K_LIST_EXPANDER_4_ComponentMAP oThis = BSRG_K_LIST_EXPANDER_4_ComponentMAP.this;
				Vector<String> vIDs = new Vector<String>();
				
				for (int i=0;i<oThis.vCB_Storno.size();i++)
				{
					if (oThis.vCB_Storno.get(i).isSelected())
					{
						vIDs.add(oThis.vCB_Storno.get(i).EXT().get_C_MERKMAL());
					}
				}
				
				if (vIDs.size()>0)
				{
					bibMSG.add_MESSAGE(buttonStorno.this.valid_IDValidation(vIDs));
					if (bibMSG.get_bIsOK())
					{

						Vector<String> vSQL_Stack = new Vector<String>();
						String cID_VKOPF_NEU    = bibDB.EinzelAbfrage("SELECT SEQ_VKOPF_RG.NEXTVAL FROM DUAL");

						// der neue Kopfsatz
						vSQL_Stack.add(new DataRecordCopySQL_Kopf(buttonStorno.this.recVKOPF, cID_VKOPF_NEU, false).get_cINSERT_String());
							
						RECLIST_VPOS_RG  recListVposToStorno = new RECLIST_VPOS_RG("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VPOS_RG IN ("+bibALL.Concatenate(vIDs, ",", "-1")+")");
						
						vSQL_Stack.addAll(new SQL_StatementBuilderForPositions(	recListVposToStorno,
																				cID_VKOPF_NEU,
																				cID_VKOPF_NEU, 
																				true).get_vSQL_Statements());
							
						
						if (bibMSG.get_bIsOK())
						{
							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL_Stack, true));
							if (bibMSG.get_bIsOK())
							{
								bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Storno durchgeführt: ",true,""+vIDs.size(),false," Positionen wurden bearbeitet ",true)));
								BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.get_oNavigationList().ADD_NEW_ID_TO_ALL_VECTORS(cID_VKOPF_NEU);
								BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.get_oNavigationList()._REBUILD_ACTUAL_SITE("");
								BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.get_oNavigationList().Check_ID_IF_IN_Page(buttonStorno.this.recVKOPF.get_ID_VKOPF_RG_cUF());
								BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.get_oNavigationList().Check_ID_IF_IN_Page(cID_VKOPF_NEU);
							}
						}
											
					}
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte wählen Sie einen oder mehrere zu stornierende Positionen aus !"));
				}
			}
			
		}
		
		
		
	}
	
	
	
	//// neu-button und action-agent
	private class newButton extends MyE2_Button
	{

		public newButton() throws myException
		{
			super(E2_ResourceIcon.get_RI("new.png"), E2_ResourceIcon.get_RI("leer.png"));

			this.add_oActionAgent(new ownActionAgentNewPosition());
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.oKopfModulcontainerList.get_MODUL_IDENTIFIER(),
											"NEUEINGABEPOSITION_IN_MASKE"));
			
			this.setToolTipText(new MyE2_String("Neuen Vorgangsposition eingeben").CTrans());
			this.add_IDValidator(new E2_Validator_ID_DBQuery("JT_VKOPF_RG","  NVL(DELETED,'N')",bibALL.get_Array("N"),true, new MyE2_String("Der Vorgang wurde bereits gelöscht !")));
			
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
				
			BSRG_K_LIST_EXPANDER_4_ComponentMAP oThis = BSRG_K_LIST_EXPANDER_4_ComponentMAP.this;
			
			
			// integritaet pruefen und fuellen und uebergeben an container
			bibE2.get_LAST_ACTIONEVENT().make_ID_Validation_ADD_TO_Global_MV(bibALL.get_Vector(oThis.ID_ROW_Unformated_VKOPF));

			if (bibMSG.get_bIsOK())
			{
				BSRG_P_MASK__ModulContainer oMaskPositions = new BSRG_P_MASK__ModulContainer(
																	BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.oSetting);
	
				oMaskPositions.get_oRowForButtons().removeAll();
				oMaskPositions.get_oRowForButtons().add(new maskButtonSaveMask(oMaskPositions,new ownSaveMASK(oMaskPositions),null, null));
				oMaskPositions.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMaskPositions));
			
				oMaskPositions.make_SettingsFrom_Head_to_Position(oThis.ID_ROW_Unformated_VKOPF);
				
				E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMaskPositions.get_vCombinedComponentMAPs();
				vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_NEW_EMPTY,null);
				oMaskPositions.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Vorgangsposition neueingeben ..."));
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Neueingabe Vorgangsposition ...",true),true);
			}
			
		}
		
	}
	//// edit-button und actionagent

	
	
	
	// button zum entfernen einer position aus einer (ungebuchten) rechnung/gutschrift in den freiepositionen-pool
	private class MoveToPoolButton extends MyE2_Button
	{

		public MoveToPoolButton(String cID_VPOS)
		{
			super(E2_ResourceIcon.get_RI("cut.png"), E2_ResourceIcon.get_RI("leer.png"));
			
			this.setToolTipText(new MyE2_String("Position zurück in die freien Positionen ...").CTrans());
			
			this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("POSITION_ZURUECK_IN_FREIE"));
			this.add_IDValidator(new ownValidator());
			this.EXT().set_C_MERKMAL(cID_VPOS);
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					BSRG_K_LIST_EXPANDER_4_ComponentMAP oThis = BSRG_K_LIST_EXPANDER_4_ComponentMAP.this;
					
					String cID = ((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource()).EXT().get_C_MERKMAL();
					
					oExecInfo.get_MyActionEvent().make_ID_Validation_ADD_TO_Global_MV(bibALL.get_Vector(cID));
					
					Vector<String> vSQL = bibALL.get_Vector("UPDATE "+bibE2.cTO()+".JT_VPOS_RG SET ID_VKOPF_RG=NULL WHERE NVL(DELETED,'N')='N' AND ID_VPOS_RG="+cID,null,null,null);
					
					if (bibMSG.get_bIsOK())
					{
						try 
						{
							//dann muss die ID_ADRESSE aus dem kopf in die position geschrieben werden, falls es eine position ist, die im kopf-modul erzeugt wurde
							My_VKopf_RG oVKopf = new My_VKopf_RG(oThis.ID_ROW_Unformated_VKOPF);
							String cID_ADRESSE = oVKopf.get_UnFormatedValue("ID_ADRESSE");
							vSQL.add("UPDATE "+bibE2.cTO()+".JT_VPOS_RG SET ID_ADRESSE="+cID_ADRESSE+" WHERE ID_VPOS_RG="+cID);
							
							//wenn die anzahl der nicht geloeschten positionen 1 ist, dann wird der kopf geloescht
							if (oVKopf.get_NumberOfNotDeletedPositions()==1)
								vSQL.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_RG SET DELETED='Y',DEL_GRUND='NO POSITIONS',DEL_DATE=SYSDATE  WHERE ID_VKOPF_RG="+oThis.ID_ROW_Unformated_VKOPF);
							
							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL,true));
							if (bibMSG.get_bIsOK())
							{
								bibMSG.add_MESSAGE(new MyE2_Info_Message("OK! Position ist wieder im Pool !",true),false);
								BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.RefreshRow();
							}
							else
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler! Position konnte nicht verschoben werden",true),false);
							}
						} catch (myException e) 
						{
							e.printStackTrace();
							bibMSG.add_MESSAGE(e.get_ErrorMessage(),false);
						}
					}
					BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.refreshDaughterComponent();
				}
			});
			
		}
		
		
		private class ownValidator extends XX_ActionValidator
		{
			@Override
			public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
			{
				return null;
			}

			@Override
			protected MyE2_MessageVector isValid(String cID)	throws myException
			{
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				RECORD_VPOS_RG recVPOS_RG = new RECORD_VPOS_RG(cID);
				
				if (recVPOS_RG.is_DELETED_YES())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Rechnungs-/Gutschriftenposition wurde bereits gelöscht !"));
				}
				if (recVPOS_RG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_DELETED_YES())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Rechnung /Gutschrifte wurde bereits auf Belegebene gelöscht !"));
				}
				if (!recVPOS_RG.get_POSITION_TYP_cUF_NN("").equals(myCONST.VG_POSITION_TYP_ARTIKEL))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Es können nur Mengenpositionen in den Pool zurückgestellt werden !"));
				}
				if (recVPOS_RG.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_ABGESCHLOSSEN_YES())
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Rechnung /Gutschrifte wurde bereits abgeschlossen !"));
				}
				
				return oMV;
			}
			
		}
		
	}
	
	
	
	//button zum einblenden der fuhrenID und (falls eine Fuhrenid vorhanden ist) zum anzeigen aller rechnungen/gutschriften
	//die zu der fuhre gehoeren
	private class FuhrenSelectionsButton extends MyE2_Button
	{
		private RECORD_VPOS_TPA_FUHRE recFuhre = null;

		public FuhrenSelectionsButton(String id_Fuhre) throws myException
		{
			super("F:"+(S.isEmpty(id_Fuhre)?"--":id_Fuhre));
			
			this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL());
			
			if (!S.isEmpty(id_Fuhre))
			{
				this.recFuhre = new RECORD_VPOS_TPA_FUHRE(id_Fuhre);
				this.setToolTipText(recFuhre.get___KETTE(bibALL.get_Vector("#Lieferant","L_NAME1", "L_ORT","#Abnehmer", "A_NAME1", "A_ORT", "#Sorte","ARTBEZ1_EK"),
														"-","",""," - "));
			}
			
			this.add_oActionAgent(new ownActionSelectFuhrenBelege());
		}
		
		private class ownActionSelectFuhrenBelege extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if (FuhrenSelectionsButton.this.recFuhre!=null)
				{
					RECORD_VPOS_TPA_FUHRE  rec_Fuhre = FuhrenSelectionsButton.this.recFuhre;

					VectorSingle vID_VKOPF = new VectorSingle();
					
					//alle vorgangs-kopf-saetze suchen, die zu dieser fuhre gehoeren
					for (int i=0;i<rec_Fuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord().get_vKeyValues().size();i++)
					{
						
						if (rec_Fuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord().get(i).is_DELETED_NO())
						{
							if (rec_Fuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord().get(i).get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
							{
								if (rec_Fuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord().get(i).get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_DELETED_NO())
								{
									vID_VKOPF.add(rec_Fuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord().get(i).get_ID_VKOPF_RG_cUF_NN(""));
								}								
							}
						}
					}
					//navilist beschaffen
					BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.get_oNavigationList().get_vActualID_Segment().removeAllElements();
					BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.get_oNavigationList().get_vActualID_Segment().addAll(vID_VKOPF);
					BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.get_oNavigationList()._REBUILD_ACTUAL_SITE("");
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Es werden alle Belege angezeigt, die eine Position besitzen mit Bezug zur Fuhre: "+rec_Fuhre.get_ID_VPOS_TPA_FUHRE_cF()));
				}
			}
		}
	}
	

	
	
	//button zum einblenden der fuhrenID und (falls eine Fuhrenid vorhanden ist) zum anzeigen aller rechnungen/gutschriften
	//die zu der fuhre gehoeren
	private class FuhrenOrtSelectionsButton extends MyE2_Button
	{
		private RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt = null;

		public FuhrenOrtSelectionsButton(String id_Fuhre_ort) throws myException
		{
			super("FO:"+(S.isEmpty(id_Fuhre_ort)?"--":id_Fuhre_ort));
			
			this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL());

			if (!S.isEmpty(id_Fuhre_ort))
			{
				this.recFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT(id_Fuhre_ort);
				
				RECORD_VPOS_TPA_FUHRE  recFuhre = this.recFuhreOrt.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();

				this.setToolTipText(recFuhre.get___KETTE(bibALL.get_Vector("#Lieferant","L_NAME1", "L_ORT","#Abnehmer", "A_NAME1", "A_ORT", "#Sorte","ARTBEZ1_EK"),"-","",""," - ")+
									this.recFuhreOrt.get___KETTE(bibALL.get_Vector("# -----> ZUSATZORT","NAME1", "ORT",null),"-","",""," - ")			);
			}
			
			this.add_oActionAgent(new ownActionSelectFuhrenBelege());
		}
		
		private class ownActionSelectFuhrenBelege extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if (FuhrenOrtSelectionsButton.this.recFuhreOrt!=null)
				{
					RECORD_VPOS_TPA_FUHRE_ORT  rec_FuhreOrt = FuhrenOrtSelectionsButton.this.recFuhreOrt;

					VectorSingle vID_VKOPF = new VectorSingle();
					
					//alle vorgangs-kopf-saetze suchen, die zu dieser fuhre gehoeren
					for (int i=0;i<recFuhreOrt.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord().get_vKeyValues().size();i++)
					{
						if (rec_FuhreOrt.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord().get(i).is_DELETED_NO())
						{
							if (rec_FuhreOrt.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord().get(i).get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
							{
								if (rec_FuhreOrt.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord().get(i).get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_DELETED_NO())
								{
									vID_VKOPF.add(rec_FuhreOrt.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord().get(i).get_ID_VKOPF_RG_cUF_NN(""));
								}								
							}
						}
					}
					//navilist beschaffen
					BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.get_oNavigationList().get_vActualID_Segment().removeAllElements();
					BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.get_oNavigationList().get_vActualID_Segment().addAll(vID_VKOPF);
					BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.get_oNavigationList()._REBUILD_ACTUAL_SITE("");
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Es werden alle Belege angezeigt, die eine Position besitzen mit Bezug zum Fuhrenort: "+recFuhreOrt.get_ID_VPOS_TPA_FUHRE_ORT_cF()));
				}
			}
		}
	}

	
	
	
	
	private class moveUpButton extends MyE2_Button
	{
		public moveUpButton(String cID_VPOS)
		{
			super(E2_ResourceIcon.get_RI("moveup.png"));
			this.EXT().set_C_MERKMAL(cID_VPOS);
			this.add_oActionAgent(new ownActionAgentMoveUp());
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
						new BS_PositionSorting(cID,BS_PositionSorting.DOWN,BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.oSetting.get_oVorgangTableNames());
						BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.refreshDaughterComponent();
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Reihenfolge wurde geändert !"));
					}
					catch (myException ex)
					{
						bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
					}
				}
			}
		}

	}

	

	
	
	private void RefreshRow()
	{
		
		// NavigationList der Kopfsaetze
		E2_NavigationList oList = this.get_oNavigationList();
		
		// E2_ComponentMap rausziehen
		E2_ComponentMAP oMapToRefresh = oList.get_ComponentMAP(this.ID_ROW_Unformated_VKOPF);
		
		// listeneintrag des kopfsatzes aktualisieren, dabei wird auch diese BSRG_K_ComponentMAP_DaughterWithPositions aktualisiert
		try
		{
			oMapToRefresh._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW,false,null);
		}
		catch (myException ex)
		{
			
		}

	}


	@Override
	public ArrayList<HashMap<String, Component>>  get_ComponentListDaughter(String unformated) throws myException
	{
		return null;
	}
	
	
	private class sorterVarianten extends MyE2_PopUpMenue
	{

		public sorterVarianten()
		{
			super(E2_ResourceIcon.get_RI("popup_mini.png"), E2_ResourceIcon.get_RI("popup_mini.png"), false);
			this.addButton(new leistungsDatSorter(), true);
			this.addButton(new leistungsDatFuhrenIDSorter(), true);
			
			this.setToolTipText(new MyE2_String("Sortieren der Rechnungspositionen ").CTrans());
		}
		
	}
	
	
	
	private class leistungsDatSorter extends MyE2_Button
	{

		public leistungsDatSorter()
		{
			super(new MyE2_String("Sortiere: Leistungsdatum/Sortenbezeichnung"));
			this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
			//this.setLayoutData(BSRG_K_LIST_EXPANDER_4_ComponentMAP.GridLayoutDataRight);
			//this.setFont(new E2_FontBoldItalic(-2));
			this.add_oActionAgent(new ownActionSort());
			
			this.setToolTipText(new MyE2_String("Sortieren der Rechnungspositionen nach dem Leistungsdatum/Sortenbezeichnung").CTrans());
			
		}

		private class ownActionSort extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				BSRG_K_LIST_EXPANDER_4_ComponentMAP oThis = BSRG_K_LIST_EXPANDER_4_ComponentMAP.this;
				
				RECORD_VKOPF_RG  recVKOPF = new RECORD_VKOPF_RG(oThis.ID_ROW_Unformated_VKOPF);
				
				new __SORTER_POSITIONEN_DATUM_SORTE(recVKOPF,"AUSFUEHRUNGSDATUM,ARTBEZ1","Leistungsdatum, Artikelbezeichnung");
				
				BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.RefreshRow();
			}
		}
		
		
	}


	
	private class leistungsDatFuhrenIDSorter extends MyE2_Button
	{

		public leistungsDatFuhrenIDSorter()
		{
			super(new MyE2_String("Sortiere: Leistungsdatum/Fuhren-ID/RechnungspositionsID"));
			this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
			//this.setLayoutData(BSRG_K_LIST_EXPANDER_4_ComponentMAP.GridLayoutDataRight);
			//this.setFont(new E2_FontBoldItalic(-2));
			this.add_oActionAgent(new ownActionSort());
			
			this.setToolTipText(new MyE2_String("Sortieren der Rechnungspositionen nach dem Leistungsdatum/Fuhren-ID/RechnungspositionsID").CTrans());
			
		}

		private class ownActionSort extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				BSRG_K_LIST_EXPANDER_4_ComponentMAP oThis = BSRG_K_LIST_EXPANDER_4_ComponentMAP.this;
				
				RECORD_VKOPF_RG  recVKOPF = new RECORD_VKOPF_RG(oThis.ID_ROW_Unformated_VKOPF);
				
				String cSortString = RECORD_VPOS_RG.FIELD__AUSFUEHRUNGSDATUM+","+RECORD_VPOS_RG.FIELD__ID_VPOS_TPA_FUHRE_ZUGEORD+","+RECORD_VPOS_RG.FIELD__ID_VPOS_RG;
				
				new __SORTER_POSITIONEN_DATUM_SORTE(recVKOPF,cSortString, "Leistungsdatum/Fuhren-ID/RechnungspositionsID");
				
				BSRG_K_LIST_EXPANDER_4_ComponentMAP.this.RefreshRow();
			}
		}
		
		
	}

	
	
}

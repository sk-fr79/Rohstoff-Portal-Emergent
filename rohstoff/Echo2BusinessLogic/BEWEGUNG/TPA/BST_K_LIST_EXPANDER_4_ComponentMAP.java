package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

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
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
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
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_PositionSorting;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG_K_LIST_EXPANDER_4_ComponentMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.___BUTTON_LIST_BT_Mail_and_Print.___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_MASK;
import rohstoff.utils.VorgangTableNames;

public class BST_K_LIST_EXPANDER_4_ComponentMAP extends XX_List_EXPANDER_4_ComponentMAP
{

	public static E2_ResourceIcon oIconLeer = E2_ResourceIcon.get_RI("leer.png");
	
	
	private MyE2_Row	  							oRow = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
	private String 		 							ID_ROW_Unformated_VKOPF = null;
	private BST_K_LIST__ModulContainer				oKopfModulcontainerList = null;
	
	
	public BST_K_LIST_EXPANDER_4_ComponentMAP(	BST_K_LIST__ModulContainer	 	KopfModulcontainerList, 
												E2_NavigationList 				NavigationList) throws myException
	{
		super(NavigationList);
		this.set_iLeftColumnOffset(5);
		this.oKopfModulcontainerList = KopfModulcontainerList;

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
		
		
		RECORD_VKOPF_TPA  recKopf = new RECORD_VKOPF_TPA(this.ID_ROW_Unformated_VKOPF);
		
		MyE2_Grid oGrid = new MyE2_Grid(8, MyE2_Grid.STYLE_GRID_DDARK_BORDER());      //NEU_09 grid um eine spalte erweitert wegen anzeige storno und gefahr
		oGrid.setBackground(new E2_ColorLLight());
		
		
		/*
		 * unterscheiden, ob mit eingeschalteten DELETED - saetzen oder ohne
		 */
		boolean bShowDeleted = this.oKopfModulcontainerList.get_oSelektor().get_oCB_ShowDeletedRows().isSelected();

		RECLIST_VPOS_TPA  reclist_VPOS_TPA = new RECLIST_VPOS_TPA("SELECT * FROM "+
													bibE2.cTO()+".JT_VPOS_TPA WHERE ID_VKOPF_TPA="+this.ID_ROW_Unformated_VKOPF+
													" ORDER BY JT_VPOS_TPA.POSITIONSNUMMER");
		
		try
		{
			// ueberschrift
			if (recKopf.is_ABGESCHLOSSEN_NO() && recKopf.is_DELETED_NO())
				oGrid.add(new newButton(),new GridLayoutLight_LEFT());
			else
				oGrid.add(new MyE2_Label(BST_K_LIST_EXPANDER_4_ComponentMAP.oIconLeer),new GridLayoutLight_LEFT());

			// ueberschrift
			oGrid.add(new MyE2_Label("S"),new GridLayoutLight_LEFT());
			oGrid.add(this.makeLabel("  ",	     	true, 	new GridLayoutLight_RIGHT(),false));
//			oGrid.add(this.makeLabel("Dat.",		true, 	new GridLayoutLight_RIGHT(), false));
			if (recKopf.is_ABGESCHLOSSEN_NO() && recKopf.is_DELETED_NO())
			{
				oGrid.add(new liefDatSorter());
			}
			else
			{
				oGrid.add(this.makeLabel("L-Dat.",true,new GridLayoutLight_RIGHT(),   false));
			}
			oGrid.add(this.makeLabel("Anz.",		true, 	new GridLayoutLight_RIGHT(), false));
			oGrid.add(this.makeLabel("Bezeichnung", true,	new GridLayoutLight_LEFT(), false));
			oGrid.add(this.makeLabel("Einzel",		true, 	new GridLayoutLight_RIGHT(), false));
			oGrid.add(this.makeLabel("Gesamt",		true, 	new GridLayoutLight_RIGHT(), false));
			

			
			for (int i=0;i<reclist_VPOS_TPA.get_vKeyValues().size();i++)
			{
				RECORD_VPOS_TPA oREC = reclist_VPOS_TPA.get(i);
				RECORD_VPOS_TPA_FUHRE oRecFuhre = oREC.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0);
				boolean bDeleted = reclist_VPOS_TPA.get(i).is_DELETED_YES();
				
				
//				//nachsehen, ob buchungen vorliegen
//				long lAnzahlBuchungen = oREC.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord().get_ID_VPOS_RG_l_Count_NotNull(
//						new RECLIST_VPOS_RG.Validation()
//						{
//							@Override
//							public boolean isValid(RECORD_VPOS_RG orecord) throws myException
//							{
//								if (orecord.is_DELETED_NO())
//								{
//									return true;
//								}
//								return false;
//							}
//						});
				
				
				if (bDeleted && !bShowDeleted)
				{
					continue;
				}
				
				// zeile 1 ------------- FAHRAUFTRAG
				
				// bearbeiten-spalte
				//2011-03-25: tpa-positionen muessen editierbar sein, auch wenn die fuhre selbst buchungspositionen hat
				
//				if (oREC.is_DELETED_NO() && lAnzahlBuchungen==0 && oRecFuhre.is_IST_STORNIERT_NO())
				if (oREC.is_DELETED_NO() && oRecFuhre.is_IST_STORNIERT_NO() && (recKopf.is_ABGESCHLOSSEN_NO()))
				{
					oGrid.add(new editButton(bibALL.ReplaceTeilString(oREC.get_ID_VPOS_TPA_cUF(),".","")));
				}
				else
				{
					oGrid.add(new viewButton(bibALL.ReplaceTeilString(oREC.get_ID_VPOS_TPA_cUF(),".","")));
				}

				// move-up-spalte
				if (reclist_VPOS_TPA.get(0).get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().is_ABGESCHLOSSEN_NO())
					oGrid.add(new moveUpButton(oREC.get_ID_VPOS_TPA_cUF()));
				else
					oGrid.add(new MyE2_Label(BST_K_LIST_EXPANDER_4_ComponentMAP.oIconLeer));

				
				//NEU_09  --  anzeige gefahr / storno
				MyE2_Row  oRowHelp = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
				if (	oREC.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_UP_RECORD_EAK_CODE_id_eak_code()!=null && 
						oREC.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_UP_RECORD_EAK_CODE_id_eak_code().is_GEFAEHRLICH_YES())
				{
					oRowHelp.add(new MyE2_Label(E2_ResourceIcon.get_RI("warnschild_16.png")),E2_INSETS.I_1_1_1_1);
				}

				if (oREC.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).is_IST_STORNIERT_YES())
				{
					oRowHelp.add(new BST__ButtonStornoInfo(oREC.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_ID_VPOS_TPA_FUHRE_cUF()),E2_INSETS.I_1_1_1_1);
				}
				oGrid.add(oRowHelp);
				
				oGrid.add(this.makeLabel(oRecFuhre.get_DATUM_ABHOLUNG_cF_NN("------").substring(0,6),false,  new GridLayoutLLight_RIGHT(),bDeleted));
				oGrid.add(this.makeLabel(oREC.get_ANZAHL_cF_NN("-"),		false,  new GridLayoutLLight_RIGHT(), bDeleted));
				oGrid.add(this.makeLabel(oREC.get_ARTBEZ1_cUF_NN("-"),		false,  new GridLayoutLLight_LEFT(), bDeleted));
				oGrid.add(this.makeLabel(oREC.get_EINZELPREIS_cF_NN("-"),	false,  new GridLayoutLLight_RIGHT(), bDeleted));
				oGrid.add(this.makeLabel(oREC.get_GESAMTPREIS_cF_NN("-"),	false,  new GridLayoutLLight_RIGHT(), bDeleted));
				
				
				// zeile 2 -------------  GEFAHRENE SORTE4
				oGrid.add(new MyE2_Label(""),5,E2_INSETS.I_0_0_0_0);    //NEU_09  anpassen an groessere spaltenzahl
				oGrid.add(oInnerGrid(
						oRecFuhre.get_MENGE_VORGABE_KO_cF_NN(null, 0, true),
						oRecFuhre.get_EINHEIT_MENGEN_cUF_NN(""),
						oRecFuhre.get_ARTBEZ1_EK_cUF_NN(""),
						oRecFuhre.get_L_NAME1_cUF_NN("")+" "+oRecFuhre.get_L_ORT_cUF_NN(""),
						oRecFuhre.get_A_NAME1_cUF_NN("")+" "+oRecFuhre.get_A_ORT_cUF_NN(""), bDeleted));
			}
			
			// spaltenbreiten auseinanderziehen
			oGrid.setColumnWidth(0, new Extent(20));
			oGrid.setColumnWidth(1, new Extent(20));
			oGrid.setColumnWidth(2, new Extent(60));
			oGrid.setColumnWidth(3, new Extent(60));
			oGrid.setColumnWidth(4, new Extent(60));
			oGrid.setColumnWidth(5, new Extent(450));
			oGrid.setColumnWidth(6, new Extent(70));
			oGrid.setColumnWidth(7, new Extent(80));
			

		}
		catch (myException ex)
		{
			ex.printStackTrace();
			oGrid.removeAll();
			oGrid.add(new MyE2_Label(new MyE2_String("E R R O R !!")),6,E2_INSETS.I_5_0_5_0);
		}

		this.oRow.add(oGrid,new Insets(0));
	}
	
	
	
	
	
	private MyE2_Grid oInnerGrid(String cAnzahl,String cEinheit, String cSorteBez, String cStartort, String cZielOrt, boolean bDeleted)
	{
		
		
		MyE2_Grid oGrid = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_NO_BORDER());
		if (bDeleted)
		{
			oGrid.add(new MyE2_Label(cAnzahl+" "+cEinheit,	MyE2_Label.STYLE_SMALL_BOLD_ITALIC_LINETHROUGH()),E2_INSETS.I_2_0_2_0);
			oGrid.add(new MyE2_Label(cSorteBez,	MyE2_Label.STYLE_SMALL_BOLD_ITALIC_LINETHROUGH()),E2_INSETS.I_20_0_0_0);
			oGrid.add(new MyE2_Label(cStartort,	MyE2_Label.STYLE_SMALL_ITALIC_LINETHROUGH()),E2_INSETS.I_20_0_0_0);
			oGrid.add(new MyE2_Label(">>",		MyE2_Label.STYLE_SMALL_BOLD_ITALIC_LINETHROUGH()),E2_INSETS.I_20_0_0_0);
			oGrid.add(new MyE2_Label(cZielOrt,	MyE2_Label.STYLE_SMALL_ITALIC_LINETHROUGH()),E2_INSETS.I_20_0_0_0);
		}
		else
		{
			oGrid.add(new MyE2_Label(cAnzahl+" "+cEinheit,	MyE2_Label.STYLE_SMALL_BOLD()),E2_INSETS.I_2_0_2_0);
			oGrid.add(new MyE2_Label(cSorteBez,	MyE2_Label.STYLE_SMALL_BOLD()),E2_INSETS.I_20_0_0_0);
			oGrid.add(new MyE2_Label(cStartort,	MyE2_Label.STYLE_SMALL_PLAIN()),E2_INSETS.I_20_0_0_0);
			oGrid.add(new MyE2_Label(">>",		MyE2_Label.STYLE_SMALL_BOLD()),E2_INSETS.I_20_0_0_0);
			oGrid.add(new MyE2_Label(cZielOrt,	MyE2_Label.STYLE_SMALL_PLAIN()),E2_INSETS.I_20_0_0_0);
		}
		
		oGrid.setColumnWidth(0, new Extent(100));
		oGrid.setColumnWidth(1, new Extent(160));
		oGrid.setColumnWidth(2, new Extent(160));
		oGrid.setColumnWidth(3, new Extent(10));
		oGrid.setColumnWidth(4, new Extent(160));

		oGrid.setLayoutData(new GridLayoutLLLight_LEFT(3));
		return oGrid;
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			BST_K_LIST_EXPANDER_4_ComponentMAP oCopy = 
				new BST_K_LIST_EXPANDER_4_ComponentMAP(this.oKopfModulcontainerList,this.get_oNavigationList());
			return oCopy;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
	}


	private MyE2_Label makeLabel(String cText,boolean bIsTitle, GridLayoutData oLayout, boolean bDeleted)
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
			if (bDeleted)
				oLabRueck.setFont(oDelFont);
			else
				oLabRueck.setFont(new E2_FontPlain(-2));
		}
		
		oLabRueck.setLayoutData(oLayout);
			
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
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(BST_K_LIST_EXPANDER_4_ComponentMAP.this.oKopfModulcontainerList.get_MODUL_IDENTIFIER(),
											"BEARBEITEN_TPA_POS_IN_MASKE"));
			
			this.add_IDValidator(new ownValidator_Fuhre_ist_noch_nicht_gebucht_ODER_ist_gloescht_ODER_ist_storniert());
			this.setToolTipText(new MyE2_String("TPA-Positionssatz bearbeiten ").CTrans());
		}
		
	}

	
	private class ownValidator_Fuhre_ist_noch_nicht_gebucht_ODER_ist_gloescht_ODER_ist_storniert extends XX_ActionValidator
	{

		@Override
		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException
		{
			return null;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_VPOS_TPA_UF) throws myException
		{
			MyE2_MessageVector  oMV = new  MyE2_MessageVector();
			
			RECORD_VPOS_TPA recTPA = new RECORD_VPOS_TPA(cID_VPOS_TPA_UF);
			
			if (recTPA.get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().is_ABGESCHLOSSEN_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der Transportauftrag wurde bereits geschlossen !")));
				return oMV;
			}
			
			
			RECORD_VPOS_TPA_FUHRE  recFuhre = recTPA.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0);
			
			if (recFuhre.is_DELETED_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Fuhre wurde bereits gelöscht !")));
			}
			
			if (recFuhre.is_IST_STORNIERT_YES())
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Fuhre wurde storniert !")));
			}
			return oMV;
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
			MyE2_Button oButtonEdit = (MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource();
			
			
			BST_K_LIST_EXPANDER_4_ComponentMAP oThis = BST_K_LIST_EXPANDER_4_ComponentMAP.this;
			
			BST_P_MASK__ModulContainer oMaskPositions = new BST_P_MASK__ModulContainer(null,false);

			oMaskPositions.get_oRowForButtons().removeAll();

			maskButtonSaveMask oSaveMask = new maskButtonSaveMask(oMaskPositions,new ownSaveMASK(oMaskPositions),null, null);
//			oSaveMask.get_oButton().get_vSQL_STACK_DAEMON().add(new FUHREN_SQL_DAEMON());

			
			oMaskPositions.get_oRowForButtons().add(oSaveMask,E2_INSETS.I_0_2_10_2);
			oMaskPositions.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMaskPositions),E2_INSETS.I_0_2_10_2);

			//druckbutton einfuehren
			oMaskPositions.get_oRowForButtons().add(new ___BUTTON_LIST_BT_Mail_and_Print(new ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_MASK(oMaskPositions,false),"TRANSPORTAUFTRAGSMODUL"),E2_INSETS.I_0_2_10_2);

			oMaskPositions.make_SettingsFrom_Head_to_Position(oThis.ID_ROW_Unformated_VKOPF);
			
			E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMaskPositions.get_vCombinedComponentMAPs();
		
			bibMSG.add_MESSAGE(oButtonEdit.valid_IDValidation(bibALL.get_Vector(this.cID_POS_UNFORMATED)));

			if (bibMSG.get_bIsOK())
			{
				
				vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT,this.cID_POS_UNFORMATED);
				oMaskPositions.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("TPA-Position bearbeiten"));
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Bearbeite TPA-Position: "+this.cID_POS_UNFORMATED,true),true);
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
			BST_K_LIST_EXPANDER_4_ComponentMAP oThis = BST_K_LIST_EXPANDER_4_ComponentMAP.this;
			
			// NavigationList der Kopfsaetze
			E2_NavigationList oList = BST_K_LIST_EXPANDER_4_ComponentMAP.this.get_oNavigationList();
			
			// E2_ComponentMap rausziehen
			E2_ComponentMAP oMapToRefresh = oList.get_ComponentMAP(oThis.ID_ROW_Unformated_VKOPF);
			
			// listeneintrag des kopfsatzes aktualisieren, dabei wird auch diese BST_K_ComponentMAP_DaughterWithPositions aktualisiert
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
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(BST_K_LIST_EXPANDER_4_ComponentMAP.this.oKopfModulcontainerList.get_MODUL_IDENTIFIER(),
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
				
				BST_K_LIST_EXPANDER_4_ComponentMAP oThis = BST_K_LIST_EXPANDER_4_ComponentMAP.this;
				
				BST_P_MASK__ModulContainer oMaskPositions = new BST_P_MASK__ModulContainer(null,false);

				oMaskPositions.get_oRowForButtons().removeAll();
				oMaskPositions.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMaskPositions));

				//druckbutton einfuehren
				oMaskPositions.get_oRowForButtons().add(new ___BUTTON_LIST_BT_Mail_and_Print(new ___Sammler_ID_VPOS_TPA_FUHRE_EX_VPOS_TPA_MASK(oMaskPositions,true),"TRANSPORTAUFTRAGSMODUL"),E2_INSETS.I_0_2_10_2);

				oMaskPositions.make_SettingsFrom_Head_to_Position(oThis.ID_ROW_Unformated_VKOPF);
				
					
				MyE2_String cMask_Title = new MyE2_String("TPA-Position anzeigen");
				
				E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMaskPositions.get_vCombinedComponentMAPs();
				
				vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW,this.cID_POS_UNFORMATED);
				oMaskPositions.CREATE_AND_SHOW_POPUPWINDOW(null,null,cMask_Title);
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Anzeige TPAposition: "+this.cID_POS_UNFORMATED));
				
			}
			catch (myException ex)
			{
				ex.printStackTrace();
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
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(BST_K_LIST_EXPANDER_4_ComponentMAP.this.oKopfModulcontainerList.get_MODUL_IDENTIFIER(),
											"NEUEINGABEPOSITION_IN_MASKE"));
			
			
			this.add_IDValidator(new E2_Validator_ID_DBQuery(	"JT_VKOPF_TPA",
					"  NVL(ABGESCHLOSSEN,'N'),  NVL(DELETED,'N')",
					bibALL.get_Array("N","N"),
					true, new MyE2_String("Nur erlaubt bei Transportaufträgen, die NICHT abgeschlossen und NICHT geloescht sind !")));

			this.setToolTipText(new MyE2_String("Neuen Positionssatz eingeben").CTrans());
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
			
			BST_K_LIST_EXPANDER_4_ComponentMAP oThis = BST_K_LIST_EXPANDER_4_ComponentMAP.this;
			
			bibE2.get_LAST_ACTIONEVENT().make_ID_Validation_ADD_TO_Global_MV(bibALL.get_Vector(oThis.ID_ROW_Unformated_VKOPF));
			
			BST_P_MASK__ModulContainer oMaskPositions = new BST_P_MASK__ModulContainer(null,false);

			maskButtonSaveMask oSaveMask = new maskButtonSaveMask(oMaskPositions,new ownSaveMASK(oMaskPositions),null, null);
			//oSaveMask.get_oButton().get_vSQL_STACK_DAEMON().add(new FUHREN_SQL_DAEMON());


			
			oMaskPositions.get_oRowForButtons().removeAll();
			oMaskPositions.get_oRowForButtons().add(oSaveMask);
			oMaskPositions.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMaskPositions));
		
			oMaskPositions.make_SettingsFrom_Head_to_Position(oThis.ID_ROW_Unformated_VKOPF);
			

			if (bibMSG.get_bIsOK())
			{
			
				MyE2_String cMask_Title = new MyE2_String("TPA-Position NEUEINGABE");
				E2_vCombinedComponentMAPs vCombined_E2_ComponentMaps = oMaskPositions.get_vCombinedComponentMAPs();
				
				vCombined_E2_ComponentMaps.MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_NEW_EMPTY,null);
				oMaskPositions.CREATE_AND_SHOW_POPUPWINDOW(null,null,cMask_Title);
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Neueingabe TPA-Position ...",true),true);
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
					new BS_PositionSorting(cID,BS_PositionSorting.DOWN,new VorgangTableNames(myCONST.VORGANGSART_TRANSPORT));
					BST_K_LIST_EXPANDER_4_ComponentMAP.this.refreshDaughterComponent();
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Reihenfolge wurde geändert"));

				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
				
			
			
		}
		
	}
	

	
	

	private class GridLayoutLight_LEFT extends GridLayoutData
	{
		public GridLayoutLight_LEFT()
		{
			super();
			this.setInsets(E2_INSETS.I_2_2_10_2);
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
			this.setBackground(new E2_ColorLight());
		}
	}

	private class GridLayoutLight_RIGHT extends GridLayoutData
	{
		public GridLayoutLight_RIGHT()
		{
			super();
			this.setInsets(E2_INSETS.I_2_2_10_2);
			this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
			this.setBackground(new E2_ColorLight());
		}
	}
	
	private class GridLayoutLLight_LEFT extends GridLayoutData
	{
		public GridLayoutLLight_LEFT()
		{
			super();
			this.setInsets(E2_INSETS.I_2_2_10_2);
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
			this.setBackground(new E2_ColorLLight());
		}
	}

	private class GridLayoutLLight_RIGHT extends GridLayoutData
	{
		public GridLayoutLLight_RIGHT()
		{
			super();
			this.setInsets(E2_INSETS.I_2_2_10_2);
			this.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
			this.setBackground(new E2_ColorLLight());
		}
	}

	private class GridLayoutLLLight_LEFT extends GridLayoutData
	{
		public GridLayoutLLLight_LEFT(int iColSpan)
		{
			super();
			this.setInsets(E2_INSETS.I_2_2_10_2);
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
			this.setBackground(new E2_ColorLLLight());
			this.setColumnSpan(iColSpan);
		}
	}

	@Override
	public ArrayList<HashMap<String, Component>>  get_ComponentListDaughter(String unformated) throws myException
	{
		return null;
	}
	
	
	
	private class liefDatSorter extends MyE2_Button
	{

		public liefDatSorter()
		{
			super(new MyE2_String("L-Dat."));
			this.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
			this.setLayoutData(BSRG_K_LIST_EXPANDER_4_ComponentMAP.GridLayoutDataRight);
			this.setFont(new E2_FontBoldItalic(-2));
			this.add_oActionAgent(new ownActionSort());
			
			this.setToolTipText(new MyE2_String("Sortieren der Transportpositionen nach dem 1. Ladedatum").CTrans());
			
		}

		private class ownActionSort extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				BST_K_LIST_EXPANDER_4_ComponentMAP oThis = BST_K_LIST_EXPANDER_4_ComponentMAP.this;
				
				RECORD_VKOPF_TPA  recVKOPF = new RECORD_VKOPF_TPA(oThis.ID_ROW_Unformated_VKOPF);
				
				RECLIST_VPOS_TPA  recListVpos = recVKOPF.get_DOWN_RECORD_LIST_VPOS_TPA_id_vkopf_tpa();
				
				Vector<String> vSort = new Vector<String>();
				
				for (int i=0;i<recListVpos.get_vKeyValues().size();i++)
				{
					RECORD_VPOS_TPA recPos = recListVpos.get(i);
					
					vSort.add(myDateHelper.ChangeNormalString2DBFormatString(recPos.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_vpos_tpa().get(0).get_DATUM_ABHOLUNG_cF())+
											"@"+recPos.get_ID_VPOS_TPA_cUF());

				}

				Collections.sort(vSort);
				
				Vector<String> vSQL = new Vector<String>();
				
				int iPosNummer = 1;
				for (int i=0;i<vSort.size();i++)
				{
					RECORD_VPOS_TPA recPOS = recListVpos.get(vSort.get(i).substring(vSort.get(i).indexOf("@")+1));
					recPOS.set_NEW_VALUE_POSITIONSNUMMER(""+(iPosNummer++));
					vSQL.add(recPOS.get_SQL_UPDATE_STATEMENT(null, true));
				}
				
				bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
				
				if (bibMSG.get_bIsOK())
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Positionen wurden nach dem 1. Ladedatum neu sortiert !!"));
				}
				
				BST_K_LIST_EXPANDER_4_ComponentMAP.this.RefreshRow();
			}
		}
		
		
	}

	
	
	private void RefreshRow()
	{
		
		// NavigationList der Kopfsaetze
		E2_NavigationList oList = this.get_oNavigationList();
		
		// E2_ComponentMap rausziehen
		E2_ComponentMAP oMapToRefresh = oList.get_ComponentMAP(this.ID_ROW_Unformated_VKOPF);
		
		try
		{
			oMapToRefresh._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW,false,null);
		}
		catch (myException ex)
		{
			
		}

	}

	
	
	
}

package panter.gmbh.Echo2.RB.COMP.BETA;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer_ActionOnClose;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_ListBox;
import panter.gmbh.basics4project.DB_ENUMS.EAK_BRANCHE;
import panter.gmbh.basics4project.DB_ENUMS.EAK_CODE;
import panter.gmbh.basics4project.DB_ENUMS.EAK_GRUPPE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataIndexHashMAP;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.utils.EAK_DataRecordHashMap_CODE;

public class RB_searchAVVCode extends E2_Grid  implements MyE2IF__Component, IF_RB_Component_Savable, E2_IF_Copy, MyE2IF_IsMarkable {

	private E2_Button  	 			buttonSelect = 		new E2_Button()._image(E2_ResourceIcon.get_RI("suche_mini.png"),true);
	private E2_Button  	 			buttonErase = 		new E2_Button()._image(E2_ResourceIcon.get_RI("eraser.png"),true);
	private RB_TextField	 		tfDatenFeldWithID = new RB_TextField()._w(100)._bord_dd()._al_right();
	private RB_TextField		    tfForAnzeige = 		new RB_TextField()._w(400)._bord_dd()._al_left();	
	
	private Vector<XX_ActionAgent>  					vActionAfterFound = new Vector<>();
	private Vector<E2_BasicModuleContainer_ActionOnClose>		vActionsAfterSearchWindowClose = new Vector<>();
	
	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();

	
	
	public RB_searchAVVCode()  throws myException {
		super();
				
		this.tfDatenFeldWithID.set_bEnabled_For_Edit(false);
		this.tfForAnzeige.set_bEnabled_For_Edit(false);
		
		this.buttonSelect._aaa(new actionStartSearch());
		this.buttonSelect._ttt("AVV-Code auswählen ..");
		
		this.buttonErase._aaa(new ownActionErase());
		this.buttonErase._ttt("AVV-Code entfernen");
		
		this.arrangeComponents();
		
	}

	/**
	 * das komponenen-grid arrangieren (kann ueberschrieben werden)
	 */
	public void arrangeComponents() {
		this._clear()._setSize(300,100,20,20)._a(this.tfForAnzeige, new RB_gld()._left_mid()._ins(0, 2, 5, 2))
											._a(this.tfDatenFeldWithID, new RB_gld()._left_mid()._ins(0, 2, 5, 2))
											._a(this.buttonErase, new RB_gld()._left_mid()._ins(0, 2, 5, 2))
											._a(this.buttonSelect, new RB_gld()._left_mid()._ins(0, 2, 5, 2));
	}
	
	
	
	private class ownActionErase extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_searchAVVCode.this.tfDatenFeldWithID.setText("");
			RB_searchAVVCode.this.tfForAnzeige.setText("");
		}
	}

	
	

	
	public Object get_Copy(Object ob) throws myExceptionCopy {
		RB_searchAVVCode oRueck = null;
		
		try	{
			oRueck =  new RB_searchAVVCode();
			
			oRueck.tfDatenFeldWithID.setWidth(this.tfDatenFeldWithID.getWidth());
			oRueck.tfForAnzeige.setWidth(this.tfForAnzeige.getWidth());
			oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
			
			
			oRueck.getvActionsAfterFound().addAll(this.getvActionsAfterFound());
			oRueck.getvActionsAfterSearchWindowClose().addAll(this.getvActionsAfterSearchWindowClose());
			
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("FS_Component_LIST_SEARCH_EAK_CODES:get_Copy:copy-error!");
		}
		
		return oRueck;
	}
	

	public void set_bEnabled_For_Edit(boolean enabled) throws myException	{
		this.buttonSelect.set_bEnabled_For_Edit(enabled && this.EXT().is_ValidEnableAlowed() && this.EXT().get_bCanBeEnabled());
		this.buttonErase.set_bEnabled_For_Edit(enabled && this.EXT().is_ValidEnableAlowed()  && this.EXT().get_bCanBeEnabled());
	}

	

	public void FillComponent(String idAVVCode) throws myException {
		// zuerst die uebergabe eines leeren wertes abfangen
		if (S.isEmpty(idAVVCode)) 		{
			this.tfDatenFeldWithID.setText("");
			this.tfForAnzeige.setToolTipText("");
		} else {
		
			String cInfoText = "";
			
			SEL sel = new SEL()	.ADDFIELD(EAK_CODE.key_code)
								.ADDFIELD(EAK_CODE.code)
								.ADDFIELD(EAK_CODE.id_eak_code)
								.ADDFIELD(EAK_CODE.gefaehrlich)
								.ADDFIELD(EAK_GRUPPE.key_gruppe)
								.ADDFIELD(EAK_BRANCHE.key_branche)
								.FROM(_TAB.eak_code)
								.INNERJOIN(_TAB.eak_gruppe,EAK_CODE.id_eak_gruppe , EAK_GRUPPE.id_eak_gruppe)
								.INNERJOIN(_TAB.eak_branche,EAK_GRUPPE.id_eak_branche , EAK_BRANCHE.id_eak_branche)
								.WHERE(new vgl(EAK_CODE.id_eak_code, idAVVCode));
			
			Rec20  rec = new Rec20(_TAB.eak_code)._fill_sql(sel.s());

			String cIDSprache = bibALL.get_ID_SPRACHE_USER();
			String cUebersetzung = bibDB.EinzelAbfrage("SELECT CODE_UEBERSETZUNG FROM "+bibE2.cTO()+".JT_EAK_CODE_SP WHERE ID_EAK_CODE="+idAVVCode+" AND ID_SPRACHE="+cIDSprache,"","","");
				
			String cGefaehrlich = "   ";
			if (rec.get_ufs_dbVal(EAK_CODE.gefaehrlich,"N").equals("Y")) {
				cGefaehrlich = "(*)";
			}
				
			cInfoText = 	((String)rec.getRawVal(EAK_BRANCHE.key_branche.fn(),"")) +" "+
							((String)rec.getRawVal(EAK_GRUPPE.key_gruppe.fn(),"")) +" "+
							((String)rec.getRawVal(EAK_CODE.key_code.fn(),""))+" "+cGefaehrlich+" "+(S.isFull(cUebersetzung)?cUebersetzung:rec.get_ufs_dbVal(EAK_CODE.code));
			
			this.tfForAnzeige.setText(cInfoText);
			this.tfDatenFeldWithID.setToolTipText(cInfoText);
		}
	}
	


	
	
	/// ++++++++++++++++++++ die actionAgents fuer die buttons 
	private class actionStartSearch extends XX_ActionAgent	{

		public void executeAgentCode(ExecINFO oExecInfo) {
			RB_searchAVVCode oThis = RB_searchAVVCode.this;
			
			WindowToSearch oWindow = new WindowToSearch();
			oWindow.set_bVisible_Row_For_Messages(false);
			
			try
			{
				/*
				 * nachsehen, ob ein wert vorhanden war
				 */
				String cID_Code = bibALL.ReplaceTeilString(oThis.tfDatenFeldWithID.getText(),".","");
				if (bibALL.isLong(cID_Code)) 	{
					try {
						EAK_DataRecordHashMap_CODE  hmCode = new EAK_DataRecordHashMap_CODE(cID_Code);
						
						String cID_Gruppe = hmCode.get_hmGruppe().get_UnFormatedValue("ID_EAK_GRUPPE");
						String cID_Branche = hmCode.get_hmBranche().get_UnFormatedValue("ID_EAK_BRANCHE");
						
						// branche auswaehlen und gruppe anzeigen
						oWindow.fill_ColumnBranche();
						oWindow.get_oListBoxBranche().set_ActiveValue_OR_FirstValue(cID_Branche);

						oWindow.fill_ColumnGruppe(cID_Branche);
						oWindow.get_oListBoxGruppe().set_ActiveValue_OR_FirstValue(cID_Gruppe);
						
						oWindow.fill_ColumnCode(cID_Gruppe);
						oWindow.get_oListBoxCode().set_ActiveValue_OR_FirstValue(cID_Code);
						
						
					}
					catch (Exception ex)
					{}
					
				} 
				oWindow.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800),new Extent(610),new MyE2_String("Suche Abfall-Code ..."));
			} catch (myException ex) {
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
		}
	}
	
	
	
	
	private class WindowToSearch extends E2_BasicModuleContainer
	{
		// row mit den columns Branche/gruppe/code
		MyE2_Column	 	oColForSelect = 	new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
		
		MyE2_Column 		oColumnBranche = 	new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
		MyE2_Column 		oColumnGruppe = 		new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
		MyE2_Column 		oColumnCode  = 		new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
		
		MyE2_ListBox 	oListBoxBranche = 	null;
		MyE2_ListBox 	oListBoxGruppe = 	null;
		MyE2_ListBox 	oListBoxCode = 		null;
		
		public WindowToSearch()
		{
			super();
			
			this.add(this.oColForSelect);
			
			this.oColForSelect.add(oColumnBranche,E2_INSETS.I_1_1_1_1);
			this.oColForSelect.add(oColumnGruppe,E2_INSETS.I_1_1_1_1);
			this.oColForSelect.add(oColumnCode,E2_INSETS.I_1_1_1_1);
			
			this.fill_ColumnBranche();
			
			this.get_vActionsOnWindowClose().addAll(RB_searchAVVCode.this.vActionsAfterSearchWindowClose);
		}
		
		
		public void fill_ColumnBranche()
		{
			this.oColumnBranche.removeAll();
			this.oColumnGruppe.removeAll();
			this.oColumnCode.removeAll();
			
			String cSQL1 = "SELECT  JT_EAK_BRANCHE.KEY_BRANCHE||' - '|| NVL(JT_EAK_BRANCHE.BRANCHE,'-'), JT_EAK_BRANCHE.ID_EAK_BRANCHE "+
							" FROM " +
							bibE2.cTO()+".JT_EAK_BRANCHE ORDER BY KEY_BRANCHE ";
			

			String[][] cBranche = bibDB.EinzelAbfrageInArray(cSQL1,"");
			if 		(cBranche == null)
				this.oColumnBranche.add(new MyE2_Label("@@@ ERROR"));
			else if (cBranche.length == 0)
				this.oColumnBranche.add(new MyE2_Label("@@@ NO RESULT"));
			else
			{
				try
				{
					// jetzt uebersetzen
					// jetzt die sprache (gegebenenfalls manipulieren)
					String cIDSprache = bibALL.get_ID_SPRACHE_USER();
					
					String cSQL = "SELECT JT_EAK_BRANCHE.ID_EAK_BRANCHE,JT_EAK_BRANCHE.KEY_BRANCHE||' - '||JT_EAK_BRANCHE_SP.BRANCHE_UEBERSETZUNG " +
													" FROM "+
													bibE2.cTO()+".JT_EAK_BRANCHE_SP,"+
													bibE2.cTO()+".JT_EAK_BRANCHE " +
													" WHERE " +
													" JT_EAK_BRANCHE_SP.ID_EAK_BRANCHE=JT_EAK_BRANCHE.ID_EAK_BRANCHE AND " +
													" JT_EAK_BRANCHE_SP.BRANCHE_UEBERSETZUNG IS NOT NULL AND "+
													" JT_EAK_BRANCHE_SP.ID_SPRACHE="+cIDSprache;

					MyDataIndexHashMAP oDMI = new MyDataIndexHashMAP(cSQL,false);
					
					for (int i=0;i<cBranche.length;i++)
					{
						String cNeuerWert = oDMI.get_Result_without_Exception(cBranche[i][1], 1) ;
						if (!bibALL.isEmpty(cNeuerWert))
							cBranche[i][0]=cNeuerWert;
					}
					
					
					// vor das erste element einen "leer-wert" einfuegen
					String[][] ccBranche = new String[cBranche.length+1][2];
					ccBranche[0][0]="--";
					ccBranche[0][1]="--";
					for (int i=0;i<cBranche.length;i++)
					{
						ccBranche[i+1][0]=cBranche[i][0];
						ccBranche[i+1][1]=cBranche[i][1];
					}

					
					this.oListBoxBranche = new MyE2_ListBox(ccBranche,null,false);
					oListBoxBranche.setFont(new E2_FontItalic(-2));
					oListBoxBranche.add_oActionAgent(new actionAgentListBoxBranche());
					oListBoxBranche.setHeight(new Extent(170));
					this.oColumnBranche.add(oListBoxBranche);
				}
				catch (myException ex)
				{
					this.oColumnBranche.add(new MyE2_Label(ex.get_ErrorMessage()));
				}
				
			}
		}

		
		public void fill_ColumnGruppe(String cID_BRANCHE)
		{
			this.oColumnGruppe.removeAll();
			this.oColumnCode.removeAll();

			if (cID_BRANCHE.equals("--"))    // leereintrag
				return;
			
			String cSQL1 = "SELECT  JT_EAK_BRANCHE.KEY_BRANCHE||' - '||JT_EAK_GRUPPE.KEY_GRUPPE||' - '||  NVL(JT_EAK_GRUPPE.GRUPPE,'-'), JT_EAK_GRUPPE.ID_EAK_GRUPPE "+
						" FROM " +
						bibE2.cTO()+".JT_EAK_BRANCHE,"+
						bibE2.cTO()+".JT_EAK_GRUPPE "+
						" WHERE " +
						" JT_EAK_BRANCHE.ID_EAK_BRANCHE = JT_EAK_GRUPPE.ID_EAK_BRANCHE AND "+
						" JT_EAK_BRANCHE.ID_EAK_BRANCHE="+cID_BRANCHE;
			
			String[][] cGruppe = bibDB.EinzelAbfrageInArray(cSQL1,"");
			if 		(cGruppe == null)
				this.oColumnGruppe.add(new MyE2_Label("@@@ ERROR"));
			else if (cGruppe.length == 0)
				this.oColumnGruppe.add(new MyE2_Label("@@@ NO RESULT"));
			else
			{
				try
				{
					
					// jetzt uebersetzen
					// jetzt die sprache (gegebenenfalls manipulieren)
					String cIDSprache = bibALL.get_ID_SPRACHE_USER();
					
					String cSQL = "SELECT JT_EAK_GRUPPE.ID_EAK_GRUPPE,JT_EAK_BRANCHE.KEY_BRANCHE||' - '||JT_EAK_GRUPPE.KEY_GRUPPE||' - '||JT_EAK_GRUPPE_SP.GRUPPE_UEBERSETZUNG " +
													" FROM "+
													bibE2.cTO()+".JT_EAK_BRANCHE, " +
													bibE2.cTO()+".JT_EAK_GRUPPE, " +
													bibE2.cTO()+".JT_EAK_GRUPPE_SP "+
													" WHERE " +
													" JT_EAK_BRANCHE.ID_EAK_BRANCHE=JT_EAK_GRUPPE.ID_EAK_BRANCHE AND " +
													" JT_EAK_GRUPPE_SP.ID_EAK_GRUPPE=JT_EAK_GRUPPE.ID_EAK_GRUPPE AND " +
													" JT_EAK_GRUPPE_SP.GRUPPE_UEBERSETZUNG IS NOT NULL AND "+
													" JT_EAK_GRUPPE_SP.ID_SPRACHE="+cIDSprache + " AND  " +
													" JT_EAK_BRANCHE.ID_EAK_BRANCHE="+cID_BRANCHE;

					MyDataIndexHashMAP oDMI = new MyDataIndexHashMAP(cSQL,false);
					
					for (int i=0;i<cGruppe.length;i++)
					{
						String cNeuerWert = oDMI.get_Result_without_Exception(cGruppe[i][1], 1) ;
						if (!bibALL.isEmpty(cNeuerWert))
							cGruppe[i][0]=cNeuerWert;
					}
					
					
					
					// vor das erste element einen "leer-wert" einfuegen
					String[][] ccGruppe = new String[cGruppe.length+1][2];
					ccGruppe[0][0]="--";
					ccGruppe[0][1]="--";
					for (int i=0;i<cGruppe.length;i++)
					{
						ccGruppe[i+1][0]=cGruppe[i][0];
						ccGruppe[i+1][1]=cGruppe[i][1];
					}

					
					this.oListBoxGruppe = new MyE2_ListBox(ccGruppe,null,false);
					oListBoxGruppe.setFont(new E2_FontItalic(-2));
					oListBoxGruppe.add_oActionAgent(new actionAgentListBoxGruppe());
					oListBoxGruppe.setHeight(new Extent(170));
					this.oColumnGruppe.add(oListBoxGruppe);
				}
				catch (myException ex)
				{
					this.oColumnGruppe.add(new MyE2_Label(ex.get_ErrorMessage()));
				}
	
			}

			
		}
		
		public void fill_ColumnCode(String cID_GRUPPE)
		{
			this.oColumnCode.removeAll();

			if (cID_GRUPPE.equals("--"))    // leereintrag
				return;

			
			String cSQL1 = "SELECT  JT_EAK_BRANCHE.KEY_BRANCHE||' - '||" +
									"JT_EAK_GRUPPE.KEY_GRUPPE||' - '||" +
									"JT_EAK_CODE.KEY_CODE||' '||" +
									"TRANSLATE(NVL(JT_EAK_CODE.GEFAEHRLICH,'N'),'YN','* ')||' - '||" +
											"  NVL(JT_EAK_CODE.CODE,'-')," +
									" JT_EAK_CODE.ID_EAK_CODE "+
									" FROM " +
									bibE2.cTO()+".JT_EAK_BRANCHE,"+
									bibE2.cTO()+".JT_EAK_GRUPPE,"+
									bibE2.cTO()+".JT_EAK_CODE "+
									" WHERE " +
									" JT_EAK_BRANCHE.ID_EAK_BRANCHE = JT_EAK_GRUPPE.ID_EAK_BRANCHE AND "+
									" JT_EAK_GRUPPE.ID_EAK_GRUPPE = JT_EAK_CODE.ID_EAK_GRUPPE AND "+
									" JT_EAK_GRUPPE.ID_EAK_GRUPPE="+cID_GRUPPE;

			
			String[][] cCodes = bibDB.EinzelAbfrageInArray(cSQL1,"");
			if 		(cCodes == null)
				this.oColumnCode.add(new MyE2_Label("@@@ ERROR"));
			else if (cCodes.length == 0)
				this.oColumnCode.add(new MyE2_Label("@@@ NO RESULT"));
			else
			{
				try
				{
					
					// jetzt uebersetzen
					// jetzt die sprache (gegebenenfalls manipulieren)
					String cIDSprache = bibALL.get_ID_SPRACHE_USER();
					
					String cSQL = "SELECT JT_EAK_CODE.ID_EAK_CODE," +
										"JT_EAK_BRANCHE.KEY_BRANCHE||' - '||" +
										"JT_EAK_GRUPPE.KEY_GRUPPE||' - '||" +
										"JT_EAK_CODE.KEY_CODE||' '||" +
										"TRANSLATE(NVL(JT_EAK_CODE.GEFAEHRLICH,'N'),'YN','* ')||' - '||" +
										"  NVL(JT_EAK_CODE_SP.CODE_UEBERSETZUNG,'-')" +
													" FROM "+
													bibE2.cTO()+".JT_EAK_BRANCHE, " +
													bibE2.cTO()+".JT_EAK_GRUPPE, " +
													bibE2.cTO()+".JT_EAK_CODE, " +
													bibE2.cTO()+".JT_EAK_CODE_SP "+
													" WHERE " +
													" JT_EAK_BRANCHE.ID_EAK_BRANCHE=JT_EAK_GRUPPE.ID_EAK_BRANCHE AND " +
													" JT_EAK_GRUPPE.ID_EAK_GRUPPE=JT_EAK_CODE.ID_EAK_GRUPPE AND " +
													" JT_EAK_CODE_SP.ID_EAK_CODE=JT_EAK_CODE.ID_EAK_CODE AND " +
													" JT_EAK_CODE_SP.CODE_UEBERSETZUNG IS NOT NULL AND "+
													" JT_EAK_CODE_SP.ID_SPRACHE="+cIDSprache+" AND "+
													" JT_EAK_GRUPPE.ID_EAK_GRUPPE="+cID_GRUPPE;

					MyDataIndexHashMAP oDMI = new MyDataIndexHashMAP(cSQL,false);
					
					for (int i=0;i<cCodes.length;i++)
					{
						String cNeuerWert = oDMI.get_Result_without_Exception(cCodes[i][1], 1) ;
						if (!bibALL.isEmpty(cNeuerWert))
							cCodes[i][0]=cNeuerWert;
					}

					
					
					
					
					// vor das erste element einen "leer-wert" einfuegen
					String[][] ccCodes = new String[cCodes.length+1][2];
					ccCodes[0][0]="--";
					ccCodes[0][1]="--";
					for (int i=0;i<cCodes.length;i++)
					{
						ccCodes[i+1][0]=cCodes[i][0];
						ccCodes[i+1][1]=cCodes[i][1];
					}
					this.oListBoxCode = new MyE2_ListBox(ccCodes,null,false);
					oListBoxCode.setFont(new E2_FontItalic(-2));
					oListBoxCode.add_oActionAgent(new actionAgentListBoxCode());
					oListBoxCode.setHeight(new Extent(170));
					this.oColumnCode.add(oListBoxCode);
				}
				catch (myException ex)
				{
					this.oColumnCode.add(new MyE2_Label(ex.get_ErrorMessage()));
				}
			
			}
		}
		
		
		

		private class actionAgentListBoxBranche extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo)
			{
				MyE2_ListBox oListBranche = (MyE2_ListBox)bibE2.get_LAST_ACTIONEVENT().getSource();
				try
				{
					WindowToSearch.this.fill_ColumnGruppe(oListBranche.get_ActualWert());
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}

		
		private class actionAgentListBoxGruppe extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo)
			{
				MyE2_ListBox oListGruppe = (MyE2_ListBox)bibE2.get_LAST_ACTIONEVENT().getSource();
				try
				{
					WindowToSearch.this.fill_ColumnCode(oListGruppe.get_ActualWert());
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}


		private class actionAgentListBoxCode extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo)
			{
				MyE2_ListBox oListCode = (MyE2_ListBox)bibE2.get_LAST_ACTIONEVENT().getSource();
				try
				{
					RB_searchAVVCode oThis = RB_searchAVVCode.this;
					oThis.tfDatenFeldWithID.setText(oListCode.get_ActualWert());
					oThis.FillComponent(oListCode.get_ActualWert());
					
					WindowToSearch.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					
					if (oThis.getvActionsAfterFound().size()>0) {
						for (XX_ActionAgent a: oThis.getvActionsAfterFound()) {
							a.executeAgentCode(oExecInfo);
							if (bibMSG.get_bHasAlarms()) {
								break;
							}
						}
					}
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}

		public MyE2_ListBox get_oListBoxBranche() 		{			return oListBoxBranche;		}
		public MyE2_ListBox get_oListBoxCode() 			{			return oListBoxCode;		}
		public MyE2_ListBox get_oListBoxGruppe()		{			return oListBoxGruppe;		}

	}





	@Override
	public void make_Look_Deleted(boolean bIsDeleted) 	{
		Font oDelFontDeleted = bibE2.get_Font4DeletedLinesInLists();
		
		if (!bIsDeleted) {
			this.tfDatenFeldWithID.setFont(new E2_FontItalic(-2));
			this.tfForAnzeige.setFont(new E2_FontPlain(-2));
		} else {
			//kann bei der super - initialisierung zu laufzeitfehler fuehren, weil dort der ersatzbutton noch nicht initialisiert ist
			this.tfDatenFeldWithID.setFont(oDelFontDeleted);
			this.tfForAnzeige.setFont(oDelFontDeleted);
		}
		
	}



	@Override
	public void setForeColorActive(Color ForeColor) {		
		this.tfDatenFeldWithID.setForeground(ForeColor);
		this.tfForAnzeige.setForeground(ForeColor);
	}



	@Override
	public void setFontActive(Font oFont) {
		Font oFont4Mark = oFont==null?new E2_FontPlain():oFont;
		this.tfDatenFeldWithID.setFont(oFont4Mark);
		this.tfForAnzeige.setFont(oFont4Mark);
	}



	@Override
	public Color get_Unmarked_ForeColor() {
		return this.tfForAnzeige.getForeground();
	}

	@Override
	public Font get_Unmarked_Font() {
		return this.tfForAnzeige.getFont();
	}

	public E2_Button getButtonSelect() {
		return buttonSelect;
	}

	public E2_Button getButtonErase() {
		return buttonErase;
	}

	public RB_TextField getTfDatenFeldWithID() {
		return tfDatenFeldWithID;
	}

	public RB_TextField getTfForAnzeige() {
		return tfForAnzeige;
	}


 
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD()==null) {
			this.tfDatenFeldWithID.setText("");
		} else {
			this.tfDatenFeldWithID.setText(((MyRECORD)dataObject.get_RecORD()).fs(this.rb_KF().FIELDNAME(),""));
		}
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.tfDatenFeldWithID.setText(S.NN(valueFormated));
	}

	@Override
	public RB_KF rb_KF() throws myException {
		return this.EXT().get_RB_K();
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.EXT().set_RB_K(key);
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return this.vVALIDATORS_4_INPUT;	
	}

	
	@Override
	@Deprecated
	public void show_InputStatus(boolean bInputIsOK) {
	}

	@Override
	@Deprecated
	public void set_ME_First(boolean ME_InFront) {
	}

	@Override
	@Deprecated
	public void set_SpaceInPX(int iSpace) {
	}

	@Override
	public MyE2IF__Component ME() throws myException {
		return this;
	}

	@Override
	public Component C_ME() throws myException {
		return this;
	}

	@Override
	@Deprecated
	public Vector<MyE2IF__Component> get_vADDONS_IN_WRAP() throws myException {
		return null;
	}

	@Override
	public void mark_Neutral() throws myException {
		this.tfDatenFeldWithID.setBackground(new E2_ColorEditBackground());
	}

	@Override
	public void mark_MustField() throws myException {
		this.tfDatenFeldWithID.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
	}

	@Override
	public void mark_Disabled() throws myException {
		this.tfDatenFeldWithID.setBackground(new E2_ColorGray(230));
		this.tfForAnzeige.setBackground(new E2_ColorGray(230));
		this.buttonErase.mark_Disabled();
		this.buttonSelect.mark_Disabled();
	}

	@Override
	public void mark_FalseInput() throws myException {
		this.tfDatenFeldWithID.setBackground(new E2_ColorHelpBackground());
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return this.tfDatenFeldWithID.getText().trim();
	}

	public Vector<XX_ActionAgent> getvActionsAfterFound() {
		return vActionAfterFound;
	}

	public Vector<E2_BasicModuleContainer_ActionOnClose> getvActionsAfterSearchWindowClose() {
		return vActionsAfterSearchWindowClose;
	}

}

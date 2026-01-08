package rohstoff.utils.ECHO2;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_ListBox;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataIndexHashMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.utils.EAK_DataRecordHashMap_CODE;

public class Component_SEARCH_EAK_CODES extends MyE2_Row  implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy
{
	private MyE2_Button  	 		ButtonSelect = 		new MyE2_Button(E2_ResourceIcon.get_RI("suche.png"),true);
	private MyE2_TextField	 		TFDatenFeldWithID = null;
	private MyE2_TextField		    oTextForAnzeige = 	new MyE2_TextField("",150,100);	
	
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);

	
	
	public Component_SEARCH_EAK_CODES()  throws myException
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		
		this.TFDatenFeldWithID=new MyE2_TextField("",100,100);
		this.TFDatenFeldWithID.set_bEnabled_For_Edit(false);
		this.oTextForAnzeige.set_bEnabled_For_Edit(false);

		this.ButtonSelect.add_oActionAgent(new actionStartSearch());
		
		this.add(this.oTextForAnzeige,E2_INSETS.I_1_1_1_1);
		this.add(this.TFDatenFeldWithID,E2_INSETS.I_1_1_1_1);
		this.add(this.ButtonSelect,E2_INSETS.I_1_1_1_1);
	
		this.TFDatenFeldWithID.set_iWidthPixel(50);
		this.oTextForAnzeige.set_iWidthPixel(300);
		this.TFDatenFeldWithID.setFont(new E2_FontPlain(-2));
		this.oTextForAnzeige.setFont(new E2_FontItalic(-2));
	}


	public void prepare_ContentForNew(boolean bSetDefaults) throws myException
	{
		this.oTextForAnzeige.setText("");
		this.TFDatenFeldWithID.setText("");
		this.EXT_DB().set_cLASTActualDBValueFormated("");
		this.EXT_DB().set_cLASTActualMaskValue("");

	}


	public String get_cActualMaskValue() throws myException
	{
		return this.TFDatenFeldWithID.getText();
	}


	public String get_cActualDBValueFormated() throws myException
	{
		return this.TFDatenFeldWithID.getText();
	}


	public void set_cActualMaskValue(String cText) throws myException
	{
		this.TFDatenFeldWithID.setText(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);

		
	}


	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		this.set_cActualMaskValue(cText);
		this.FillLabelWithDBQuery(bibALL.ReplaceTeilString(cText,".",""));
	}
 


	public MyE2_TextField get_oTextForAnzeige()
	{
		return oTextForAnzeige;
	}
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		throw new myExceptionCopy("not implementet yet");
	}
	

	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		enabled = enabled && this.EXT().is_ValidEnableAlowed();
		enabled = enabled && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled();
		this.ButtonSelect.set_bEnabled_For_Edit(enabled);
	}


	public void set_bIsComplexObject(boolean bisComplex)
	{
	}


	public boolean get_bIsComplexObject()
	{
		return false;
	}


	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return null;
	}


	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return null;
	}


	public MyE2EXT__DB_Component EXT_DB()
	{
		return this.oEXTDB;
	}


	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)
	{
		this.oEXTDB=oEXT_DB;
	}
	
	

	public void FillLabelWithDBQuery(String ValueForDB) throws myException
	{
		
		// zuerst die uebergabe eines leeren wertes abfangen
		if (bibALL.isEmpty(ValueForDB))
		{
			this.oTextForAnzeige.setText("");
			this.oTextForAnzeige.setToolTipText("");
			this.TFDatenFeldWithID.setText("");
			return;
		}
		
		String cInfoText = "";
		
		String cSQL1 = "SELECT    JT_EAK_BRANCHE.KEY_BRANCHE, " +
								" JT_EAK_GRUPPE.KEY_GRUPPE," +
								" JT_EAK_CODE.KEY_CODE, " +
								" JT_EAK_CODE.CODE ," +
								" JT_EAK_CODE.ID_EAK_CODE, " +
								" NVL(JT_EAK_CODE.GEFAEHRLICH,'N') "+
						" FROM " +
						bibE2.cTO()+".JT_EAK_GRUPPE,"+
						bibE2.cTO()+".JT_EAK_BRANCHE,"+
						bibE2.cTO()+".JT_EAK_CODE "+
						" WHERE " +
						" JT_EAK_BRANCHE.ID_EAK_BRANCHE = JT_EAK_GRUPPE.ID_EAK_BRANCHE  AND"+ 
						" JT_EAK_GRUPPE.ID_EAK_GRUPPE = JT_EAK_CODE.ID_EAK_GRUPPE  AND "+
						" JT_EAK_CODE.ID_EAK_CODE="+ValueForDB;
		
		
		
		String[][] cErgebnis = bibDB.EinzelAbfrageInArray(cSQL1,"");

		if (cErgebnis == null)
		{
			cInfoText = "@@@ERROR ";
		}
		else if (cErgebnis.length == 0)
		{
			cInfoText = "";
		}
		else
		{
			String cIDSprache = bibALL.get_ID_SPRACHE_USER();
			String cUebersetzung = bibDB.EinzelAbfrage("SELECT CODE_UEBERSETZUNG FROM "+bibE2.cTO()+".JT_EAK_CODE_SP WHERE ID_EAK_CODE="+cErgebnis[0][4]+" AND ID_SPRACHE="+cIDSprache,"","","");
			
			String cGefaehrlich = "   ";
			if (cErgebnis[0][5].toUpperCase().equals("Y"))
				cGefaehrlich = "(*)";
			
			if (!bibALL.isEmpty(cUebersetzung))
				cErgebnis[0][3] = cUebersetzung;
			
			cInfoText = cErgebnis[0][0]+" "+cErgebnis[0][1]+" "+cErgebnis[0][2]+" "+cGefaehrlich+" "+cErgebnis[0][3];
		}
		
		this.oTextForAnzeige.setText(cInfoText);
		this.oTextForAnzeige.setToolTipText(cInfoText);

		
	}
	

	
	
	
	
	/// ++++++++++++++++++++ die actionAgents fuer die buttons 

	private class actionStartSearch extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Component_SEARCH_EAK_CODES oThis = Component_SEARCH_EAK_CODES.this;
			
			WindowToSearch oWindow = new WindowToSearch();
			oWindow.set_bVisible_Row_For_Messages(false);
			
			/*
			 * nachsehen, ob ein wert vorhanden war
			 */
			String cID_Code = bibALL.ReplaceTeilString(oThis.get_cActualMaskValue(),".","");
			if (bibALL.isLong(cID_Code))
			{
				try
				{
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
				{
					throw new myException("Component_SEARCH_EAK_CODES:actionStartSearch:"+ex.getLocalizedMessage());
				}
				
			}
			
			oWindow.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800),new Extent(610),new MyE2_String("Suche Abfall-Code ..."));
				
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
					Component_SEARCH_EAK_CODES oThis = Component_SEARCH_EAK_CODES.this;
					oThis.TFDatenFeldWithID.setText(oListCode.get_ActualWert());
					oThis.FillLabelWithDBQuery(oListCode.get_ActualWert());
					
					WindowToSearch.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					
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

}

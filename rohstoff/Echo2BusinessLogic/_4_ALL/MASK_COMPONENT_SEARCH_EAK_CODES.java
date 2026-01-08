package rohstoff.Echo2BusinessLogic._4_ALL;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import panter.gmbh.BasicInterfaces.IF_getOwnComponentMAP;
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
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF_IsMarkable;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_ListBox;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.staticStyles.Style_Row_Normal;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataIndexHashMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_MASK_ComponentMAP;
import rohstoff.utils.EAK_DataRecordHashMap_CODE;
import rohstoff.utils.ECHO2.DB_SEARCH_ArtikelBez;

public class MASK_COMPONENT_SEARCH_EAK_CODES extends MyE2_Row  implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy, MyE2IF_IsMarkable
{

	private MyE2_Button  	 		ButtonSelect = 		new MyE2_Button(E2_ResourceIcon.get_RI("suche_mini.png"),true);
	private MyE2_Button  	 		ButtonErase = 		new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"),true);
	private OwnDB_TextField	 		TFDatenFeldWithID = null;
	private MyE2_TextField		    oTextForAnzeige = 	new MyE2_TextField("",150,100);	
	
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);

	private FS_MASK_ComponentMAP	ComponentMAP_ADRESS = null;
	
	private DB_SEARCH_ArtikelBez    oSearchArtBez = null;                   //falls von oben das feld der korrellierenden artbez eingeblendet wird, dann wird ein button 
	 																		// eingeblendet, der den zur JT_ARTIKEL gehoerenden standard-AVV-Code definiert
	
	private XX_ActionAgent          oActionAfterFound = null;
	
	
	private MyE2_Button 			oButtonStdAVV = null;


	public MASK_COMPONENT_SEARCH_EAK_CODES(	SQLField 				osqlField, 
											FS_MASK_ComponentMAP	ocomponentMAP_ADRESS,
											DB_SEARCH_ArtikelBez    SearchArtBez)  throws myException
	{
		super(new Style_Row_Normal(0, new Insets(0,0,2,0)));
				
		this.ComponentMAP_ADRESS = ocomponentMAP_ADRESS;
		this.oSearchArtBez = SearchArtBez;
		
		this.TFDatenFeldWithID=new OwnDB_TextField(osqlField);
		this.TFDatenFeldWithID.set_bEnabled_For_Edit(false);
		this.oTextForAnzeige.set_bEnabled_For_Edit(false);
		
		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);

		this.ButtonSelect.add_oActionAgent(new actionStartSearch());
		this.ButtonSelect.add_GlobalAUTHValidator_AUTO("AVV_CODE_DEF");
		this.ButtonSelect.setToolTipText(new MyE2_String("AVV-Code auswählen ..").CTrans());
		
		this.ButtonErase.add_oActionAgent(new ownActionErase());
		this.ButtonErase.add_GlobalAUTHValidator_AUTO("AVV_CODE_DEF");
		this.ButtonErase.setToolTipText(new MyE2_String("AVV-Code entfernen").CTrans());
		
		this.add(this.oTextForAnzeige,E2_INSETS.I_0_0_2_0);
		this.add(this.TFDatenFeldWithID,E2_INSETS.I_0_0_2_0);
		this.add(this.ButtonErase,E2_INSETS.I_0_0_2_0);
		this.add(this.ButtonSelect,E2_INSETS.I_0_0_2_0);
		
		
		// DEN ZAUBERSTAB aktivieren, um die Standard-AVV-Code aus dem Sortenstamm zu laden
		if (this.oSearchArtBez!=null)
		{
			this.oButtonStdAVV = new MyE2_Button(E2_ResourceIcon.get_RI("wizard_mini.png"));
			oButtonStdAVV.add_GlobalAUTHValidator_AUTO("AVV_CODE_DEF");
			
			oButtonStdAVV.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					MASK_COMPONENT_SEARCH_EAK_CODES oThis = MASK_COMPONENT_SEARCH_EAK_CODES.this;
					E2_ComponentMAP   				oMap = oThis.EXT().get_oComponentMAP();
					
					//das suchfeld in der E2_ComponentMap-kopie dieser zeile suchen
					DB_SEARCH_ArtikelBez            oSearch = (DB_SEARCH_ArtikelBez)oMap.get_hmRealComponents().get(oThis.oSearchArtBez.EXT().get_C_HASHKEY());
					oThis.suche_StandardAVV_Code(oSearch);
				}
			});
			oButtonStdAVV.setToolTipText(new MyE2_String("Lade den Bar-Einkaufs-AVV-Code aus der Artikel-Stammdatei").CTrans());
			this.add(oButtonStdAVV,E2_INSETS.I_0_0_2_0);
		}
	
		this.TFDatenFeldWithID.set_iWidthPixel(50);
		this.oTextForAnzeige.set_iWidthPixel(150);
		this.TFDatenFeldWithID.setFont(new E2_FontPlain(-2));
		this.oTextForAnzeige.setFont(new E2_FontItalic(-2));
	}

	
	private class ownActionErase extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			MASK_COMPONENT_SEARCH_EAK_CODES.this.TFDatenFeldWithID.setText("");
			MASK_COMPONENT_SEARCH_EAK_CODES.this.oTextForAnzeige.setText("");
		}
	}

	
	
	//aenderung 2010-12-07: AVV-Code-Suche von extern ansteuerbar
	public void suche_StandardAVV_Code(DB_SEARCH_ArtikelBez    SearchArtBez) throws myException
	{

		String cID_ARTBEZ = bibALL.ReplaceTeilString(S.NN(SearchArtBez.get_cActualMaskValue()),".","");
		if (S.isEmpty(cID_ARTBEZ) || !bibALL.isLong(cID_ARTBEZ))
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst die Sortenbezeichung definieren !"));
			return;
		}
		RECORD_ARTIKEL  recArt = new RECORD_ARTIKEL_BEZ(cID_ARTBEZ).get_UP_RECORD_ARTIKEL_id_artikel();
		
		this.set_cActual_Formated_DBContent_To_Mask(recArt.get_ID_EAK_CODE_cUF_NN(""), E2_ComponentMAP.STATUS_EDIT, null);
		if (S.isEmpty(recArt.get_ID_EAK_CODE_cUF_NN("")))
		{
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Zu dieser Sorte ist kein Standard-AVV-Code erfasst worden !"));
		}
	}
	
	

	public void prepare_ContentForNew(boolean bSetDefault) throws myException
	{
		this.TFDatenFeldWithID.prepare_ContentForNew(bSetDefault);
		this.oTextForAnzeige.setText("");
		this.EXT_DB().set_cLASTActualDBValueFormated("");
		this.EXT_DB().set_cLASTActualMaskValue("");

	}


	public String get_cActualMaskValue() throws myException
	{
		return this.TFDatenFeldWithID.get_cActualMaskValue();
	}


	public String get_cActualDBValueFormated() throws myException
	{
		return this.TFDatenFeldWithID.get_cActualDBValueFormated();
	}


	public void set_cActualMaskValue(String cText) throws myException
	{
		this.TFDatenFeldWithID.set_cActualMaskValue(cText);
		
		this.EXT_DB().set_cLASTActualMaskValue(cText);
	}


	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		this.set_cActualMaskValue(cText);
		
		String cUnformatedValue = this.TFDatenFeldWithID.EXT_DB().get_oSQLField().get_cUnformated_ValueString_For_InternalUse(cText,false);
		this.FillLabelWithDBQuery(cUnformatedValue);
	}
 


	public MyE2_TextField get_oTextForAnzeige()
	{
		return oTextForAnzeige;
	}
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MASK_COMPONENT_SEARCH_EAK_CODES oRueck = null;
		
		try
		{
			oRueck =  new MASK_COMPONENT_SEARCH_EAK_CODES(	this.TFDatenFeldWithID.EXT_DB().get_oSQLField(),
																this.ComponentMAP_ADRESS,
																this.oSearchArtBez);
			
			oRueck.get_oTextForAnzeige().setWidth(this.oTextForAnzeige.getWidth());
			oRueck.get_oTFDatenFeldWithID().setWidth(this.TFDatenFeldWithID.getWidth());
		
			oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
			
			
			oRueck.set_oActionAfterFound(this.get_oActionAfterFound());
			
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("FS_Component_LIST_SEARCH_EAK_CODES:get_Copy:copy-error!");
		}
		
		return oRueck;
	}
	

	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		this.ButtonSelect.set_bEnabled_For_Edit(enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled());
		this.ButtonErase.set_bEnabled_For_Edit(enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled());
		if (this.oButtonStdAVV!=null)
		{
			this.oButtonStdAVV.set_bEnabled_For_Edit(enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled());
		}
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
	

	
	public XX_ActionAgent get_oActionAfterFound() 
	{
		return oActionAfterFound;
	}


	public void set_oActionAfterFound(XX_ActionAgent oActionAfterFound) 
	{
		this.oActionAfterFound = oActionAfterFound;
	}

	
	
	/// ++++++++++++++++++++ die actionAgents fuer die buttons 

	private class actionStartSearch extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			MASK_COMPONENT_SEARCH_EAK_CODES oThis = MASK_COMPONENT_SEARCH_EAK_CODES.this;
			
			
			WindowToSearch oWindow = new WindowToSearch();
			oWindow.set_bVisible_Row_For_Messages(false);
			
			try
			{
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
					{}
					
				}
				else
				{
					//  nachschauen, ob ein wert fuer eak-branche in der maske eingetragen war (falls in der maske benutzt)
					String cActualValue = null;
					if (oThis.ComponentMAP_ADRESS!=null)
					{
						cActualValue = ((MyE2IF__DB_Component)oThis.ComponentMAP_ADRESS.get_E2_ComponentMAP_Firmeninfo().get__Comp("ID_EAK_BRANCHE")).get_cActualDBValueFormated();
					}
					
					
					cActualValue = bibALL.ReplaceTeilString(bibALL.null2leer(cActualValue),".","");
					if (bibALL.isLong(cActualValue))
					{
						// branche auswaehlen und gruppe anzeigen
						oWindow.fill_ColumnBranche();
						oWindow.get_oListBoxBranche().set_ActiveValue_OR_FirstValue(cActualValue);

						oWindow.fill_ColumnGruppe(cActualValue);
					}
				}
				
				oWindow.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800),new Extent(610),new MyE2_String("Suche Abfall-Code ..."));
				
			}
			catch (myException ex)
			{
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
					MASK_COMPONENT_SEARCH_EAK_CODES oThis = MASK_COMPONENT_SEARCH_EAK_CODES.this;
					oThis.TFDatenFeldWithID.setText(oListCode.get_ActualWert());
					oThis.FillLabelWithDBQuery(oListCode.get_ActualWert());
					
					WindowToSearch.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					
					if (oThis.get_oActionAfterFound()!=null)
					{
						oThis.get_oActionAfterFound().executeAgentCode(oExecInfo);
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




	public MyE2_DB_TextField get_oTFDatenFeldWithID()
	{
		return TFDatenFeldWithID;
	}


	//2011-09-07: weitere getter-objekte, um das feld auch in der fuhre benutzbar zu machen
	public MyE2_Button get_oButtonSelect()
	{
		return ButtonSelect;
	}


	//2011-09-07: weitere getter-objekte, um das feld auch in der fuhre benutzbar zu machen
	public MyE2_Button get_oButtonErase()
	{
		return ButtonErase;
	}



	@Override
	public void make_Look_Deleted(boolean bIsDeleted)
	{
		Font oDelFontDeleted = bibE2.get_Font4DeletedLinesInLists();
		
		if (!bIsDeleted) {
			//kann bei der super - initialisierung zu laufzeitfehler fuehren, weil dort der ersatzbutton noch nicht initialisiert ist
			if (this.oTextForAnzeige !=null) {
				this.oTextForAnzeige.setFont(new E2_FontItalic(-2));
			}
			if (this.TFDatenFeldWithID !=null) {
				this.TFDatenFeldWithID.setFont(new E2_FontPlain(-2));
			}
		}
		else {
			//kann bei der super - initialisierung zu laufzeitfehler fuehren, weil dort der ersatzbutton noch nicht initialisiert ist
			if (this.oTextForAnzeige !=null){
				this.oTextForAnzeige.setFont(oDelFontDeleted);
			}
			if (this.TFDatenFeldWithID !=null){
				this.TFDatenFeldWithID.setFont(oDelFontDeleted);
			}
		}
		
	}



	@Override
	public void setForeColorActive(Color ForeColor)
	{		
		if (this.oTextForAnzeige !=null) {
			this.oTextForAnzeige.setForeground(ForeColor);
		}
		if (this.TFDatenFeldWithID !=null) {
			this.TFDatenFeldWithID.setForeground(ForeColor);
		}
	}



	@Override
	public void setFontActive(Font oFont)
	{
		Font oFont4Mark = oFont==null?new E2_FontPlain():oFont;
		if (this.oTextForAnzeige !=null) {
			this.oTextForAnzeige.setFont(oFont4Mark);
		}
		if (this.TFDatenFeldWithID !=null) {
			this.TFDatenFeldWithID.setFont(oFont4Mark);
		}
	}



//	@Override
//	public Color get_ForeColor_of_markableComponent()
//	{
//		Color oColRueck = new E2_ColorBase();
//		
//		if (this.oTextForAnzeige !=null) {
//			oColRueck = this.oTextForAnzeige.getForeground();
//		}
//
//		return oColRueck;
//	}

//	private Color unmarked_Color = null;
//	public void store_unmarked_ForeColor() {
//		this.unmarked_Color = this.oTextForAnzeige.getForeground();
//	}
//
//	public void restore_unmarked_ForeColor() {
//		if (this.unmarked_Color!=null) {
//			this.oTextForAnzeige.setForeground(this.unmarked_Color);
//		}
//	}


	@Override
	public Color get_Unmarked_ForeColor() {
		return this.oTextForAnzeige.getForeground();
	}

	@Override
	public Font get_Unmarked_Font() {
		return this.oTextForAnzeige.getFont();
	}


 
	/**
	 * 20190307: workaround, um eine infobutton in dem ID_AEK_CODE-feld unterzubringen,
	 * da sort nicht die komponente MASK_COMPONENT_SEARCH_EAK_CODES als DB_Componente gefunden wird, sondern das interne MyE2_DB_TextField mit der id
	 * Dieser internen komponente ist aber nicht die E2_ComponentMap zugeordnet, sondern der ausseren. deswegen gibt es einen nullpointer-Ex
	 * @author martin
	 * @date 07.03.2019
	 *
	 */
	private class OwnDB_TextField extends MyE2_DB_TextField implements IF_getOwnComponentMAP {

		public OwnDB_TextField(SQLField osqlField) throws myException {
			super(osqlField);
		}

		@Override
		public E2_ComponentMAP getOwnComponentMap() {
			return MASK_COMPONENT_SEARCH_EAK_CODES.this.EXT().get_oComponentMAP();
		}
		
	}
	
	

}

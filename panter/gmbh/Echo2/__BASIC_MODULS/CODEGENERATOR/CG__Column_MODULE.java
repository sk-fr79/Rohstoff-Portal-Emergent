package panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR;

import java.io.File;
import java.util.Vector;
import java.util.Map.Entry;

import nextapp.echo2.app.Component;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF_HashMap;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

/*
 * 
 * folgende: Tags in den Templates werden gegen die maskeneintraege ausgetauscht:
 * 
 * <PACKAGENAME>   	= Paketname
 * <BASISNAME> 		= Namesfragment vor jeder Klasse des moduls (kennzeichen fuer das modul)
 * <MODULKENNERMASK> = in E2_MODULNAMES hinterlegter NAME
 * <MODULKENNERLIST> = in E2_MODULNAMES hinterlegter NAME
 * <MAINTABLE> 	          = Haupttabelle
 * <MAININDEX> 	          = Haupttabelleprimaerschluessel
 * 
 * Listentags
 * </FIELDNAME/>           nimmt die folgezeile und macht einen Eintrag fuer jedes Feld, wobei <FIELDNAME> ausgetauscht wird 
 * 
 * 
 * SonderTags:
 * <ALLFIELDSINARRAY>     wird ersetzt durch eine Feldliste {"Feld1","Feld2","Feld3",...}
 * 
 * 
 */
public class CG__Column_MODULE extends MyE2_Column
{
	
	private MyE2_SelectField  	oSelTabelle = null;
	private MyE2_TextField  	oTF_TabIndex = null;
	
	private MyE2_TextField  	oTF_BasisName = null;
	private MyE2_TextField  	oTF_PacketName = null;
	private MyE2_TextField  	oTF_MODULEKENNER_LIST = null;
	private MyE2_TextField  	oTF_MODULEKENNER_MASK = null;
	
	private MyE2_TextField      oTF_FelderWegLassen = null;;
	
	private MyE2_Button   		oButtonStart = new MyE2_Button("Code erzeugen ");
	
	
	/*
	 * spalte bekommt die selektion fuer die auswahl, welche dateien generiert werden sollen
	 */
	private MyE2_Column 			oColSelectFiles = 	new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
	private Vector<MyE2_CheckBox>  	vSelectFiles = 		new Vector<MyE2_CheckBox>();
	

	private String[]   cTemplateFileNames = {
											"XXX_LIST_BasicModuleContainer",
											"XXX_LIST_BedienPanel",
											"XXX_LIST_BT_DELETE",
											"XXX_LIST_BT_EDIT",
											"XXX_LIST_BT_NEW",
											"XXX_LIST_BT_VIEW",
											"XXX_LIST_ComponentMap",
											"XXX_LIST_DATASEARCH",
											"XXX_LIST_FORMATING_Agent",
											"XXX_LIST_Selector",
											"XXX_LIST_SqlFieldMAP",
											"XXX_MASK",
											"XXX_MASK_BasicModuleContainer",
											"XXX_MASK_ComponentMAP",
											"XXX_MASK_FORMATING_Agent",
											"XXX_MASK_MapSettingAgent",
											"XXX_MASK_SQLFieldMAP",
											"XXX_MASK_TAB",
											"XXX_LIST_BasicModule_Inlay"
											};
	
	
	public CG__Column_MODULE() throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());

		this.oButtonStart.add_oActionAgent(new actionAgentStartCodeGen());
		this.oButtonStart.add_GlobalValidator(new validStart());
		
		this.oSelTabelle = new MyE2_SelectField(
										DB_META.get_TablesQuery_JT_and_JD(DB_META.DB_ORA, bibE2.cTO(), true, true),
										false,true,false,false);

		this.oSelTabelle.add_oActionAgent(new ownActionAgent());
		
		this.oTF_TabIndex = 			new MyE2_TextField("",600,100);
		this.oTF_BasisName = 			new MyE2_TextField("",600,100);
		this.oTF_PacketName = 			new MyE2_TextField("",600,100);
		this.oTF_MODULEKENNER_LIST = 	new MyE2_TextField("",600,100);
		this.oTF_MODULEKENNER_MASK = 	new MyE2_TextField("",600,100);
		
		this.oTF_FelderWegLassen =      new MyE2_TextField("",600,100);
		this.oTF_FelderWegLassen.setText("ID_MANDANT|LETZTE_AENDERUNG|GEAENDERT_VON|ERZEUGT_VON|ERZEUGT_AM");

		MyE2_Grid  oGrid = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
		
		this.add(oGrid, E2_INSETS.I_10_10_10_10);
		
		oGrid.add(new MyE2_Label("Programm-Module generieren ...."), 2,E2_INSETS.I_0_0_5_5);
		oGrid.add(new Separator(),2,E2_INSETS.I_0_0_5_5);
		oGrid.add(new MyE2_Label("Welche Tabelle ist die Haupttabelle"), 1,E2_INSETS.I_0_0_5_5); oGrid.add(this.oSelTabelle, 1,E2_INSETS.I_0_0_5_5);
		oGrid.add(new MyE2_Label("Primary-Key der Tabelle"), 1,E2_INSETS.I_0_0_5_5); oGrid.add(this.oTF_TabIndex, 1,E2_INSETS.I_0_0_5_5);
		oGrid.add(new MyE2_Label("Package-Namen der generierten Klassen"), 1,E2_INSETS.I_0_0_5_5); oGrid.add(this.oTF_PacketName, 1,E2_INSETS.I_0_0_5_5);
		oGrid.add(new MyE2_Label("Basis-Name (Pefix der Klassennamen)"), 1,E2_INSETS.I_0_0_5_5); oGrid.add(this.oTF_BasisName, 1,E2_INSETS.I_0_0_5_5);
		
		oGrid.add(new MyE2_Label("Listen-Module-Kenner"), 1,E2_INSETS.I_0_0_5_5); oGrid.add(this.oTF_MODULEKENNER_LIST, 1,E2_INSETS.I_0_0_5_5);
		oGrid.add(new MyE2_Label("Masken-Module-Kenner"), 1,E2_INSETS.I_0_0_5_5); oGrid.add(this.oTF_MODULEKENNER_MASK, 1,E2_INSETS.I_0_0_5_5);
		oGrid.add(new MyE2_Label("Bitte wählen Sie die gewünschten Module :"), 2,E2_INSETS.I_0_5_5_5);
		oGrid.add(new MyE2_Label("Felder weglassen (Trenner: |)"), 1,E2_INSETS.I_0_5_5_5);; oGrid.add(this.oTF_FelderWegLassen, 1,E2_INSETS.I_0_0_5_5);
		
		
		oGrid.add(new E2_ComponentGroupHorizontal(0,this.oButtonStart,E2_INSETS.I_0_0_0_0),  2,E2_INSETS.I_0_0_5_5);
		
		

		MyE2_CheckBox oCB_AlleAnAus = new MyE2_CheckBox("Alle Ein/Ausschalten");
		oCB_AlleAnAus.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				MyE2_CheckBox  ownCB = (MyE2_CheckBox)oExecInfo.get_MyActionEvent().getSource();
				CG__Column_MODULE oThis = CG__Column_MODULE.this;
			
				//if (ownCB.isSelected())
				for (int i=0;i<oThis.vSelectFiles.size();i++)
				{
					oThis.vSelectFiles.get(i).setSelected(ownCB.isSelected());
				}
			}
		});
				
		oGrid.add(oCB_AlleAnAus, 2,E2_INSETS.I_0_5_5_5);
		
		oGrid.add(this.oColSelectFiles, 2,E2_INSETS.I_0_5_5_5);
		
		for (int i=0;i<cTemplateFileNames.length;i++)
		{
			MyE2_CheckBox oCB = new MyE2_CheckBox(cTemplateFileNames[i]);
			oCB.EXT().set_C_MERKMAL(cTemplateFileNames[i]);
			oCB.setSelected(true);
			this.vSelectFiles.add(oCB);
			this.oColSelectFiles.add(oCB, E2_INSETS.I_2_2_2_0);
		}

		
		
	}
	
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			CG__Column_MODULE 	oThis = CG__Column_MODULE.this;
			
			String cTabname = oThis.oSelTabelle.get_ActualView();
			
			if (bibALL.isEmpty(cTabname))
			{
				oThis.oTF_TabIndex.setText("");
			}
			else
			{
				oThis.oTF_TabIndex.setText("ID_"+cTabname.substring(3));
			}
		}
		
	}
	
	
	
	
	
	private class actionAgentStartCodeGen extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			CG__Column_MODULE 	oThis = CG__Column_MODULE.this;
			
			String[][] cFields = bibDB.EinzelAbfrageInArray("SELECT COLUMN_NAME  FROM SYS.USER_TAB_COLUMNS WHERE TABLE_NAME="+
										bibALL.MakeSql(oThis.oSelTabelle.get_ActualWert())+" ORDER BY COLUMN_NAME");
			
			Vector<String> vFieldsWegLassen = bibALL.TrenneZeile(oThis.oTF_FelderWegLassen.getText(), "|") ;
			
			MyMetaFieldDEF_HashMap  oMetaDefMap = null;
			try
			{
				oMetaDefMap = new MyMetaFieldDEF_HashMap(oThis.oSelTabelle.get_ActualWert().trim());
			}
			catch (Exception ex)
			{
			}
			
			
			String cAllFields = "";
			for (int i=0;i<cFields.length;i++)
			{
				cAllFields += ",\""+cFields[i][0]+"\"";
			}
			cAllFields = "{"+cAllFields.substring(1)+"}";
			
			
			if (cFields==null || cFields.length==0)
				throw new myException("Error reading Fields of Table "+oThis.oSelTabelle.get_ActualWert());
			
			try
			{
				Vector<File> vErgebnisFiles = new Vector<File>();
				
				int iZahlErzeugt = 0;
				
				for (int i=0;i<oThis.vSelectFiles.size();i++)
				{
					MyE2_CheckBox oCB = (MyE2_CheckBox)oThis.vSelectFiles.get(i);
					if (!oCB.isSelected())
						continue;
					
					String cFileName = oCB.EXT().get_C_MERKMAL();
							
					// im outputname wird XXX ersetzt durch den Basisnamen
					String cOutName = bibALL.get_CompleteOutPutPath(true)+bibALL.ReplaceTeilString(cFileName, "XXX", oThis.oTF_BasisName.getText().trim());
					File oFileOut = new File(cOutName+".java");
					vErgebnisFiles.add(oFileOut);
					
					
					String cHelp = new MODULE_ResourceStringLoader(cFileName).get_cText();
					
					//jetzt den String auseinandernehmen
					String[] cLines = S.SeparateLinesFromString(cHelp);

					String cGanzesOutFile = "";
					for (int line=0;line<cLines.length;line++)
					{
						String cZeile = cLines[line];
						
						// separate benhandlung von listen
						if (cZeile.indexOf("</FIELDNAME/>")>0)    //dann steht in der naechsten zeile der eigentliche code
						{
							if (line<cLines.length-1)    //wenn es nicht die letzte zeile ist
							{
								cZeile = cLines[line+1];
								if (S.isFull(cZeile))
								{
									for (int m=0;m<cFields.length;m++)
									{
										if (!vFieldsWegLassen.contains(cFields[m][0]))
										{
											String cCodeZeile  = bibALL.ReplaceTeilString(cZeile, "<FIELDNAME>", 	cFields[m][0]);
											
											//jetzt nachsehen, ob ein Feldgenerator abgefragt wird
											if (cCodeZeile.indexOf("<FIELDMASKGENERATOR>")>0)
											{
												cCodeZeile = bibALL.ReplaceTeilString(cCodeZeile, "<FIELDMASKGENERATOR>", oThis.GenerateMaskField(cFields[m][0], oMetaDefMap, false));
											}
											//jetzt nachsehen, ob ein Feldgenerator abgefragt wird
											if (cCodeZeile.indexOf("<FIELDLISTGENERATOR>")>0)
											{
												cCodeZeile = bibALL.ReplaceTeilString(cCodeZeile, "<FIELDLISTGENERATOR>", oThis.GenerateMaskField(cFields[m][0], oMetaDefMap, true));
											}
											cGanzesOutFile = cGanzesOutFile+cCodeZeile+"\n";	
										}
									}
									line++;  //sonst wird eine zusaetzliche, ueberfluessige zeile erzeugt
								}
							}
							continue;
						}
						

						/*
						 * 
						 * folgende: Tags in den Templates werden gegen die maskeneintraege ausgetauscht:
						 * 
						 * <>   	= Paketname
						 * <> 		= Namesfragment vor jeder Klasse des moduls (kennzeichen fuer das modul)
						 * <> = in E2_MODULNAMES hinterlegter NAME
						 * <> = in E2_MODULNAMES hinterlegter NAME
						 * <> 	          = Haupttabelle
						 * <> 	          = Haupttabelleprimaerschluessel
						 */

						// jetzt die ersetzungen machen
						cZeile = bibALL.ReplaceTeilString(cZeile, "<PACKAGENAME>", 			oThis.oTF_PacketName.getText().trim());
						cZeile = bibALL.ReplaceTeilString(cZeile, "<BASISNAME>", 			oThis.oTF_BasisName.getText().trim());
						cZeile = bibALL.ReplaceTeilString(cZeile, "<MODULKENNERMASK>", 		oThis.oTF_MODULEKENNER_MASK.getText().trim());
						cZeile = bibALL.ReplaceTeilString(cZeile, "<MODULKENNERLIST>",		oThis.oTF_MODULEKENNER_LIST.getText().trim());
						cZeile = bibALL.ReplaceTeilString(cZeile, "<MAINTABLE>", 			oThis.oSelTabelle.get_ActualWert().trim());
						cZeile = bibALL.ReplaceTeilString(cZeile, "<MAININDEX>", 			oThis.oTF_TabIndex.getText().trim());
						cZeile = bibALL.ReplaceTeilString(cZeile, "<ALLFIELDSINARRAY>", 	cAllFields);
						
						cGanzesOutFile = cGanzesOutFile+cZeile+"\n";
					}
					
					FileUtils.writeStringToFile(oFileOut, cGanzesOutFile, "UTF-8");
					iZahlErzeugt++;
				}
						
				MyE2_String oMess = new MyE2_String("Dateien wurden in folgendem Ordner erzeugt: ");
				oMess.addUnTranslated(bibALL.get_CompleteOutPutPath(false));
				oMess.addUnTranslated("  ("+iZahlErzeugt+")");
				bibMSG.add_MESSAGE(new MyE2_Info_Message(oMess), false);		
				
			}
			catch (Exception ex)
			{
				throw new myException(ex.getLocalizedMessage());
			}
		}
		
	}
	

	
	private String GenerateMaskField(String cFieldName, MyMetaFieldDEF_HashMap oHashMapDefs, boolean bForceLabel) throws myException
	{
		
		//zuerst die MetaDefinition des Feldes suchen
		MyMetaFieldDEF oFieldDef = null;
		for (Entry<String,MyMetaFieldDEF> oEntry :oHashMapDefs.entrySet())
		{
			if (oEntry.getValue().get_FieldName().toUpperCase().equals(cFieldName.toUpperCase()))
			{
				oFieldDef = oEntry.getValue();
			}
		}

		if (oFieldDef == null)
		{
			return "new MyE2_DB_TextField(oFM.get_(\""+cFieldName+"\"),true,200)";
		}
		
		String cRueck = "";
		
		if (oFieldDef.get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_TEXT))
		{
			if (oFieldDef.get_FieldTextLENGTH()==1)
			{
				cRueck = "new MyE2_DB_CheckBox(oFM.get_(\""+cFieldName+"\"))";
			}
			else
			{
				if (!bForceLabel)
				{
					int iLang =200;
					if (oFieldDef.get_FieldTextLENGTH()>1 && oFieldDef.get_FieldTextLENGTH()<=10)
					{
						iLang = 100;
						cRueck = "new MyE2_DB_TextField(oFM.get_(\""+cFieldName+"\"),true,"+iLang+","+oFieldDef.get_FieldTextLENGTH()+",false)";
					}
					else if (oFieldDef.get_FieldTextLENGTH()>=11 && oFieldDef.get_FieldTextLENGTH()<=29)
					{
						iLang = 200;
						cRueck = "new MyE2_DB_TextField(oFM.get_(\""+cFieldName+"\"),true,"+iLang+","+oFieldDef.get_FieldTextLENGTH()+",false)";
					}
					else if (oFieldDef.get_FieldTextLENGTH()>=30 && oFieldDef.get_FieldTextLENGTH()<=79)
					{
						iLang = 350;
						cRueck = "new MyE2_DB_TextField(oFM.get_(\""+cFieldName+"\"),true,"+iLang+","+oFieldDef.get_FieldTextLENGTH()+",false)";
					}
					else if (oFieldDef.get_FieldTextLENGTH()>=80 && oFieldDef.get_FieldTextLENGTH()<=119)
					{
						iLang = 500;
						cRueck = "new MyE2_DB_TextField(oFM.get_(\""+cFieldName+"\"),true,"+iLang+","+oFieldDef.get_FieldTextLENGTH()+",false)";
					}
					else if (oFieldDef.get_FieldTextLENGTH()>=120  && oFieldDef.get_FieldTextLENGTH()<=300)
					{
						iLang = 500;
						cRueck = "new MyE2_DB_TextArea(oFM.get_(\""+cFieldName+"\"),"+iLang+",5)";					
					}
					else if (oFieldDef.get_FieldTextLENGTH()>300)
					{
						iLang = 500;
						cRueck = "new MyE2_DB_TextArea(oFM.get_(\""+cFieldName+"\"),"+iLang+",8)";					
					}
				}
				else
				{
					cRueck = "new MyE2_DB_Label(oFM.get_(\""+cFieldName+"\"))";					
				}
				
			}
		}
		else if (oFieldDef.get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_NUMMER))
		{
			if (!bForceLabel)
			{
				int iColumnLenght = (oFieldDef.get_FieldNumberLENGTH()*20);
				cRueck = "new MyE2_DB_TextField(oFM.get_(\""+cFieldName+"\"),true,"+iColumnLenght+","+oFieldDef.get_FieldNumberLENGTH()+",false)";
			}
			else
			{
				cRueck = "new MyE2_DB_Label(oFM.get_(\""+cFieldName+"\"))";								
			}
		}
		else if (oFieldDef.get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_DATUM))
		{
			if (!bForceLabel)
			{
				cRueck = "new MyE2_DB_TextField(oFM.get_(\""+cFieldName+"\"),true,120,10,false)";
			}
			else
			{
				cRueck = "new MyE2_DB_Label(oFM.get_(\""+cFieldName+"\"))";								
			}
			
		}
		
		/*
		 * fuer den notfall immer einen label uebergeben
		 */
		return cRueck;
		
	}
	
	
	
	private class validStart extends XX_ActionValidator
	{

		public MyE2_Message get_ErrorMessage() 
		{
			return new MyE2_Message(new  MyE2_String("Bitte alle Felder ausfüllen !!"),MyE2_Message.TYP_ALARM,false);
		}

		public MyE2_MessageVector isValid(Component oCompWhichIsValidated)
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			CG__Column_MODULE 	oThis = CG__Column_MODULE.this;
			boolean bHelp = true;
			try 
			{
				if (bibALL.isEmpty(oThis.oSelTabelle.get_ActualWert())) 		bHelp = false;
				if (bibALL.isEmpty(oThis.oTF_TabIndex.getText())) 				bHelp = false;
				if (bibALL.isEmpty(oThis.oTF_BasisName.getText())) 			bHelp = false;
				if (bibALL.isEmpty(oThis.oTF_PacketName.getText())) 			bHelp = false;
				if (bibALL.isEmpty(oThis.oTF_MODULEKENNER_LIST.getText())) 	bHelp = false;
				if (bibALL.isEmpty(oThis.oTF_MODULEKENNER_MASK.getText())) 	bHelp = false;

				if (!bHelp)
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new  MyE2_String("Bitte alle Felder ausfüllen !!")));
			} 
			catch (myException e) 
			{
				e.printStackTrace();
				oMV.add_MESSAGE(e.get_ErrorMessage());
			}
			return oMV;
		}

		
		public MyE2_MessageVector isValid(String cID_Unformated)
		{
			return new MyE2_MessageVector();
		}

	}
	
	
	
}

package panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR;

import java.io.File;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.xmlDefTools.ListDef_NG.FieldDef;
import panter.gmbh.Echo2.Container.xmlDefTools.ListDef_NG.ListColumn;
import panter.gmbh.Echo2.Container.xmlDefTools.ListDef_NG;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

public class CG__Column_XML_Editor_Definition extends MyE2_Column
{
	
	private MyE2_Button  but_StartGeneratingXMLFile =	new MyE2_Button("Baue XML Definitions-Datei");
	private MyE2_SelectField  oSelTable = null;
	
	private MyE2_Column  oColOutPut  = new MyE2_Column();
	
	private String[][]   cTables = null;
	
	
	public CG__Column_XML_Editor_Definition() throws myException 
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		this.cTables = bibDB.EinzelAbfrageInArray(DB_META.get_TablesQuerySort_A_to_Z_NUR_JD_JT_TABLES(bibE2.cTO(), true));
		oSelTable = new MyE2_SelectField(cTables, "", false);
		
		if (this.cTables==null || this.cTables.length==0)
			throw new myException(this,"Error: Could not read Tables ...");

		
		
		this.add(this.oSelTable, 	E2_INSETS.I_10_10_10_10);
		this.add(this.but_StartGeneratingXMLFile, 	E2_INSETS.I_10_10_10_10);
		this.add(this.oColOutPut,E2_INSETS.I_10_10_10_10);
		this.but_StartGeneratingXMLFile.add_oActionAgent(new CG_ActionAgent_Baue_XML_edit_file(this.oColOutPut));
		
	}


	
	private class CG_ActionAgent_Baue_XML_edit_file extends XX_ActionAgent
	{

		private String 			cTable = null;
		private MyE2_Column  	oColOutput = null;
		CG__Column_XML_Editor_Definition oThis = null;
		
		public CG_ActionAgent_Baue_XML_edit_file( MyE2_Column ColOutput)
		{
			super();
			oThis  = CG__Column_XML_Editor_Definition.this;
			oColOutput = ColOutput; 
		}


		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{

			this.cTable = oThis.oSelTable.get_ActualWert();
			
			if (bibALL.isEmpty(this.cTable)){
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Keine Tabelle ausgewählt!" ));
				return;
			}
			
			oColOutput.removeAll();
			String cXMLCode = build_Code_for_XML_file(cTable);
			String cOutName_XML = bibALL.get_CompleteOutPutPath(true) + this.cTable.substring(3).toLowerCase() + "_edit.xml";
			File oFileOut_XML = new File(cOutName_XML);

			String cStatus = "?";
			try{
				
				FileUtils.writeStringToFile(oFileOut_XML, cXMLCode, "UTF-8");
				
				File fTest = new File(cOutName_XML);
				bibMSG.add_MESSAGE(new MyE2_Info_Message("XML-Datei erzeugt: " + cOutName_XML));
				
				if (!fTest.exists())
				{
					DEBUG.System_println(cOutName_XML, "");	
				}
				
				cStatus = "XML-Datei erzeugt: " + cOutName_XML;
				
				
			} catch (Exception ex){
				ex.printStackTrace();
				cStatus+="  *** ERROR *** ";
			}
			
			this.oColOutput.add(new MyE2_Label(new MyE2_String(cStatus,false)), E2_INSETS.I_2_2_2_2);
		}

		
		
		
		
		
		/**
		 * xml-File definieren
		 * @param cTableName
		 * @return
		 * @throws myException
		 */
		private String build_Code_for_XML_file(String cTableName) throws myException
		{
			
			//zuerst eine Dummy-query machen
			String cQueryAnalyse = "SELECT * FROM "+bibE2.cTO()+"."+cTableName+" WHERE ID_"+cTableName.substring(3)+"=-1";
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			MyDBResultSet oRS = oDB.OpenResultSet(cQueryAnalyse);
			
			Vector<String>  vAutomaticFields = new Vector<String>();
			vAutomaticFields.add("ID_MANDANT");
			vAutomaticFields.add("LETZTE_AENDERUNG");
			vAutomaticFields.add("GEAENDERT_VON");
			vAutomaticFields.add("GLOBAL_SEQUENCE");
			
			String sAutomaticFields = ":" + bibALL.Concatenate(vAutomaticFields, ":", "") + ":";
			String sIDField = "ID_" + cTableName.substring(3);
			
			ListDef_NG oList = null;
			XStream oXS = new XStream(new Dom4JDriver());
			if (oRS.RS != null)
			{
				try
				{
					// 20101004:  die felder ueber sortierten vector iterieren
					Vector<String> 						vFields = new Vector<String>();
					HashMap<String, MyMetaFieldDEF>  	hmMetaDefs = new HashMap<String, MyMetaFieldDEF>();
					
					for (int i=0;i<oRS.RS.getMetaData().getColumnCount();i++)
					{
						String cFieldName = oRS.RS.getMetaData().getColumnLabel(i+1);
						vFields.add(cFieldName);
						MyMetaFieldDEF oMetaDef = new MyMetaFieldDEF(oRS.RS,i,cTableName);
						hmMetaDefs.put(cFieldName, oMetaDef);
					}
					Collections.sort(vFields);
					
					
					
					oXS.alias("listendefinition",ListDef_NG.class);
					oXS.alias("felddefinition",ListDef_NG.FieldDef.class);
					oXS.alias("spaltendefinition",ListDef_NG.ListColumn.class);
					oXS.alias("suchdefinition",ListDef_NG.SearchDef.class);
					oXS.alias("selektorcheckboxdefinition",ListDef_NG.SelectorDefCheckBox.class);
					oXS.alias("selektorcheckboxanausdefinition",ListDef_NG.SelectorDefCheckBoxOnOff.class);
					oXS.alias("selektordropdowndefinition",ListDef_NG.SelectorDefDropDown.class);
					

					
					String sTableStripped = cTableName.substring(3);
					oList = new ListDef_NG();
					oList.TABLENAME = cTableName;
					oList.AUTOMATICFIELDS = sAutomaticFields; //":ID_MANDANT:LETZTE_AENDERUNG:GEAENDERT_VON:GLOBAL_SEQUENCE:";
					oList.DELETEALLOWED = true;
					oList.EDITALLOWED = true;
					oList.NEWALLOWED = true;
					oList.HEADLINE = "Bearbeiten von " + sTableStripped;
					oList.HELPTEXT = "Mit dieser Maske werden Daten der Tabelle " + sTableStripped + " bearbeitet.";
					oList.MENUEGROUP = "Stammdaten";
					oList.MENUELINE = sTableStripped;
					
					oList.MODULKENNER ="MODUL_" + sTableStripped.toUpperCase();
					oList.PRIMARYKEYFIELD = "ID_" +sTableStripped.toUpperCase() ;
					oList.SEQUENCEQUERY = "SELECT SEQ_" +sTableStripped.toUpperCase() + ".NEXTVAL FROM DUAL";
					
					oList.NUMBERROWSINLIST = 10;
					oList.NUMBERCOLUMNSINSELECTORGRID = vFields.size() -1;

					
					
					
					
					
					for (int i=0;i<vFields.size();i++)
					{
						String cFieldName = vFields.get(i);
						
						// automatik-Felder werden nicht generiert.
						if (vAutomaticFields.contains(cFieldName.toUpperCase())){
							continue;
						}
						
						//MyMetaFieldDEF oMetaDef = new MyMetaFieldDEF(oRS.RS,i,cTableName);
						MyMetaFieldDEF oMetaDef = hmMetaDefs.get(cFieldName);
						
						// Columns
						ListColumn oCol = oList.new ListColumn(cFieldName);
						oCol.VISIBLEATSTART = true;
						
						

						// Standard-Fielddefs
						String sType = "";
						if (cFieldName.equalsIgnoreCase(sIDField)){ 
							sType = ListDef_NG.FIELDTYPE_LABEL;
						} else 	if (oMetaDef.get_bIsTextType() && oMetaDef.get_FieldTextLENGTH()==1)
						{
							sType = ListDef_NG.FIELDTYPE_CHECKBOX;
						} else {
							sType = ListDef_NG.FIELDTYPE_EDITFIELD;
						} 
						
						String 		sFieldname = oMetaDef.get_FieldName();
						String 		sTitle = oMetaDef.get_FieldName();
						String 		sDefSelectfield = "";

						boolean 	bEditable = true;
						boolean 	bSortable = true;
						int  		iPixel = 100;
						int  		iRows = 1;
						
						
						FieldDef oField = oList.new FieldDef(sFieldname, sTitle, sType,  bEditable,bSortable, iPixel, iRows, "");
						oField.MUSTFIELD = !oMetaDef.get_bFieldNullableBasic();
						oField.FIELDLABELUSER = sFieldname;
						oCol.add_Field(oField);
						
						oList.VECTORCOLUMNS.add(oCol);
					}
					
					
				}
				catch (SQLException ex)
				{
					oRS.Close();
					bibALL.destroy_myDBToolBox(oDB);
					throw new myException("SQLFieldMAP:initFields:SQL-Exception:"+ex.getMessage()+" -->  SQL-Query:"+cQueryAnalyse);
				}
				oRS.Close();
			}
			else
			{
				throw new myException("SQLFieldMAP:initFields:Error opening empty resultSet!   -->  SQL-Query:"+cQueryAnalyse);
			}
			
			String sXML = oXS.toXML(oList);
			return sXML;
		}

	
	}
}

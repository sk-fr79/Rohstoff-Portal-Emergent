package panter.gmbh.Echo2.Container.xmlDefTools;

import java.util.StringTokenizer;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SimpleStatus_an_aus;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PasswordField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField_INROW;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLConnectorInnerTables;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

/*
 * listendefinitionsklasse, strukturelle einfach gehalten
 * um mittels XStream geschrieben/gelesen werden zu koennen
 */
public class ListDef_NG
{
	public static final String		FIELDTYPE_LABEL = 			"FIELDTYPE_LABEL";
	public static final String		FIELDTYPE_CHECKBOX = 		"FIELDTYPE_CHECKBOX";
	public static final String		FIELDTYPE_DROPDOWN = 		"FIELDTYPE_DROPDOWN";
	public static final String		FIELDTYPE_EDITFIELD =		"FIELDTYPE_EDIT";
	public static final String		FIELDTYPE_EDITPASSWORD =	"FIELDTYPE_EDITPASSWORD";
	
	
	public boolean 	EDITALLOWED =		false;
	public boolean 	NEWALLOWED = 		false;
	public boolean 	DELETEALLOWED = 	false;
	public String 	TABLENAME	=		null;
	public String 	HEADLINE = 			null;
	public String   MENUELINE=          null;                   // wird fuer die menueuebersicht benutzt, falls nicht vorhanden wird HAEDLINE genommen
	public String   MENUEGROUP=         null;                   // wird fuer die menueuebersicht benutzt, falls nicht vorhanden wird HAEDLINE genommen
	
	public String 	MODULKENNER=		null;
	public int 		NUMBERROWSINLIST	= 	5;                 	// anzahl zeilen in der liste (am anfang)
	public int 		NUMBERCOLUMNSINSELECTORGRID = 3;       // anzahl spalten im selektionsblock
	
	public String 	AUTOMATICFIELDS = "";                	// liste in der form :NAME1:NAME2: usw, die die nicht benutzten felder (z.B. 
															// automatisch gesetzte felder, spezifizert
	
	public String 	PRIMARYKEYFIELD	= "";				// primaerschluessel
	public String   SEQUENCEQUERY	= "";				// sequence-abfrage
	
	
	public Vector<ListColumn> 				VECTORCOLUMNS= 					new Vector<ListColumn>();
	public Vector<SelectorDefDropDown> 		VECTORSELECTORSDROPDOWN = 		new Vector<SelectorDefDropDown>();
	public Vector<SelectorDefCheckBox> 		VECTORSELECTORSCHECKBOX = 		new Vector<SelectorDefCheckBox>();
	public Vector<SelectorDefCheckBoxOnOff>  VECTORSELECTORSCHECKBOXANAUS = 	new Vector<SelectorDefCheckBoxOnOff>();
	public Vector<SearchDef>				VECTORSEARCHFIELDS = 			new Vector<SearchDef>();
	
	/*
	 * in diesen vector koennen klassen-referenzen eingefuegt werden, die 
	 * aus der klasse XX_ModuleContainerListEditPopup abgeleitet sind,
	 * eingetragen werden. jeder Stringeintrag in den Vector muss 
	 * folgenden aufbau haben:
	 * <classe-name>:<buttonstring>:<authstring>
	 * z.b. rohstoff.Echo2xmlPopups.test.class:Testanwendung
	 */
	public Vector<String> 	VECTORPOPUPWINDOWCLASSES = new Vector<String>();
	
	
	/*
	 * vector mit zusatztabellen, die an die tabelle mit einer relation angehaengt werden
	 * Die zusatztabellen werden ueber einen 4-teiligen String definiert:
	 * <Vorsatzbuchstabe>:<Tabellenname>:<PrimaryKeyName>:<Relationsblock>
	 */
	public Vector<String> 	VECTORRELATIONTABLES = new Vector<String>();
	
	
	/*
	 * restrict-felder werden in folgender form angegeben:
	 * <TABLENAME>:<FIELDNAME>:<FIELALIAS>:<VORGABEWERT>
	 */
	public Vector<String>	VECTORRESTRICTFIELDS = new Vector<String>();
	
	
	/*
	 * Bedinungen, die der Abfrage in einer liste untergeschoben 
	 * werden koennen
	 * bsp: "JT_ADRESSE.ID_ADRESSE>10000"
	 */
	public Vector<String>	VECTORWHERESTATEMENTS = new Vector<String>();
	
	
	public String 	HELPTEXT	=				null;
	
	public Object	SELVECTOR	= null;						// eine objectreferenz, die 
															// mit dem evtl. vorhandenen Selectionsvector gefuellt wird.
															// noetig, damit ein event ausgeloest werden kann und 
															// damit voreingestellte selektionen beruecksichtigt werden koennen
	
	
	
	public boolean  NONSPECIALFIELDS = false;               //schalter, der dafuer sorgt, dass die drei felder id_mandant, letzte_aenderung, geaendert_von immer automatisch eingetragen werden
	
	
	public boolean  NEWINSINGLELINE   = false;              // schalter, der dafuer sorgt, dass neueingaben in eine einzelzeile angezeigt werden
	
	
	
	public ListDef_NG()
	{
		super();
	}
	
	


	/**
	 * @param ctablename
	 * @param cprimarykeyfield
	 * @param csequencequery
	 * @param title
	 * @param cmodulKenner
	 * @param cNotUsedFields
	 * @param editAllowed
	 * @param deleteAllowed
	 * @param newAllowed
	 */
	public ListDef_NG(		String ctablename, 
						String cprimarykeyfield,
						String csequencequery,
						String title, 
						String cmodulKenner,
						String cNotUsedFields,
						boolean editAllowed, 
						boolean deleteAllowed, 
						boolean newAllowed)
	{
		super();
		this.TABLENAME = 		ctablename;
		this.HEADLINE = 			title;
		this.EDITALLOWED = 	editAllowed;
		this.DELETEALLOWED = 	deleteAllowed;
		this.NEWALLOWED = 		newAllowed;
		this.MODULKENNER = 	cmodulKenner;
		this.AUTOMATICFIELDS = cNotUsedFields;
		this.PRIMARYKEYFIELD = cprimarykeyfield;
		this.SEQUENCEQUERY = csequencequery;
	}
	
	
	/**
	 * gibt einen button mit popup-funktion zurueck, wenn 
	 * der parameter HELPTEXT gesetzt ist, sonst null
	 */
	public MyE2_Button get_ButtonHelp()
	{
		MyE2_Button oButtonRueck = null;
		if (!bibALL.isEmpty(this.HELPTEXT))
		{
			oButtonRueck = new MyE2_Button(E2_ResourceIcon.get_RI("help.png"),true);
			oButtonRueck.add_oActionAgent(new XX_ActionAgent()
					{
						public void executeAgentCode(ExecINFO oExecInfo)  throws myException
						{
							E2_ConfirmBasicModuleContainer oBasicContainer = new E2_ConfirmBasicModuleContainer(
									 new MyE2_String("Hilfstext"),
									 null,
									 new MyE2_String(ListDef_NG.this.HELPTEXT,false),
									 new MyE2_String("OK"),
									 null,
									 new Extent(900),
									 new Extent(400));
							
							oBasicContainer.show_POPUP_BOX();		

						}
					}
					);
		}
		return oButtonRueck;
	}
	

	/**
	 * loest einen neuaufbau der liste aus, unter beruecksichtigung
	 * der evtl. vorhandenen selektionen 
	 */
	public void INIT_LIST() throws myException
	{
		if (this.SELVECTOR != null)
		{
			E2_SelectionComponentsVector oSelvector = (E2_SelectionComponentsVector)this.SELVECTOR;
			oSelvector.doActionPassiv();
		}
	}
	
	
	public SQLFieldMAP build_SQLFieldMAP() throws myException
	{
		SQLFieldMAP oSQLFieldMAP = new SQLFieldMAP(this.TABLENAME,bibE2.get_CurrSession());
		
		String cNotUsedList = ":"+this.PRIMARYKEYFIELD+":"+this.AUTOMATICFIELDS+":";
		/*
		 * alle tabellenfelder anfuegen, PrimaryKey separat
		 */
		oSQLFieldMAP.addCompleteTable_FIELDLIST(	this.TABLENAME,
													cNotUsedList,
													false,
													true,
													"");
		
		oSQLFieldMAP.add_SQLField(new SQLFieldForPrimaryKey(
				this.TABLENAME,
				this.PRIMARYKEYFIELD,
				this.PRIMARYKEYFIELD,
				new MyE2_String(this.PRIMARYKEYFIELD,false),
				bibE2.get_CurrSession(),
				this.SEQUENCEQUERY,
				true), false);
		
		
		// alle evtl. vorhandenen zusatztabellen in die fieldmap einfuegen
		if (this.VECTORRELATIONTABLES!= null && this.VECTORRELATIONTABLES.size()>0)
		{
			// aufbau: <Vorsatzbuchstabe>:<Tabellenname>:<PrimaryKeyName>:<Relationsblock>
			Vector<String> vCheckFieldAddons = new Vector<String>();
			Vector<String> vCheckTables = new Vector<String>();
			for (int i=0;i<this.VECTORRELATIONTABLES.size();i++)
			{
				StringSeparator vParts = new StringSeparator((String)this.VECTORRELATIONTABLES.get(i),":");
				if (vParts.size() != 4)
					throw new myException("ListDef:build_SQLFieldMAP:VECTORRELATIONTABLES-Def incorrect. MUST have 4 Parts: "+(String)this.VECTORRELATIONTABLES.get(i));
				
				String cVorsatz= 		((String)vParts.get(0)).toUpperCase();
				String cTable=	 		((String)vParts.get(1)).toUpperCase();
				String cPrimaryKey=		((String)vParts.get(2)).toUpperCase();
				String cRelation=		(String)vParts.get(3);
				
				if (vCheckFieldAddons.contains(cVorsatz))
					throw new myException("ListDef:build_SQLFieldMAP:VECTORRELATIONTABLES-Def Every Table must have different Pre-Sign: "+(String)this.VECTORRELATIONTABLES.get(i));

				if (vCheckTables.contains(cTable))
					throw new myException("ListDef:build_SQLFieldMAP:VECTORRELATIONTABLES-Def Every Table must have different Name: "+(String)this.VECTORRELATIONTABLES.get(i));

				vCheckFieldAddons.add(cVorsatz);
				vCheckTables.add(cTable);
				
				
				/*
				 * alle tabellenfelder anfuegen, PrimaryKey separat
				 */
				String cNotUsedList2 = ":"+cPrimaryKey+":"+this.AUTOMATICFIELDS+":";
				oSQLFieldMAP.addCompleteTable_FIELDLIST(	cTable,
															cNotUsedList2,
															false,
															false,
															cVorsatz);

				oSQLFieldMAP.add_SQLField(new SQLFieldForPrimaryKey(
						cTable,
						cPrimaryKey,
						cVorsatz+cPrimaryKey,
						new MyE2_String(cPrimaryKey,false),
						bibE2.get_CurrSession(),
						null,
						false), false);
				
				oSQLFieldMAP.add_InnerConnector(new SQLConnectorInnerTables(cRelation));
				
			}
		}
		
		// restrict-fields eintragen !!! wichtig! hier nur in der Haupttabelle erlaubt
		// aufbau der eintraege: <TABLENAME>:<FIELDNAME>:<FIELDALIAS>:<VORGABEWERT>
		// alle evtl. vorhandenen zusatztabellen in die fieldmap einfuegen
		if (this.VECTORRESTRICTFIELDS!= null && this.VECTORRESTRICTFIELDS.size()>0)
		{
			for (int i=0;i<this.VECTORRESTRICTFIELDS.size();i++)
			{
				StringSeparator vParts = new StringSeparator((String)this.VECTORRESTRICTFIELDS.get(i),":");
				if (vParts.size() != 4)
					throw new myException("ListDef:build_SQLFieldMAP:VECTORRESTRICTFIELDS-Def incorrect. MUST have 4 Parts: "+(String)this.VECTORRELATIONTABLES.get(i));

				String cTABLENAME = 	((String) vParts.get(0)).toUpperCase();
				String cFIELDNAME = 	((String) vParts.get(1)).toUpperCase();
				String cFIELDALIAS = 	((String) vParts.get(2)).toUpperCase();
				String cVORGABEWERT = 	(String) vParts.get(3);
				
				
				if (!cTABLENAME.equals(oSQLFieldMAP.get_cMAIN_TABLE()))
					throw new myException("ListDef:build_SQLFieldMAP:VECTORRESTRICTFIELDS-Def incorrect. Field MUST be a Main-Table-Field: "+(String)this.VECTORRELATIONTABLES.get(i));
					
				Vector<String> vFieldKennungsVector = oSQLFieldMAP.get_vFieldKennungsVector();
				
				if (vFieldKennungsVector.contains(cTABLENAME+":"+cFIELDNAME))
				{
					// das feld muss raus aus der bisherigen sqlfieldmap
					for (int k=0;k<oSQLFieldMAP.get_vFieldLabels().size();k++)
					{
						SQLField oField = oSQLFieldMAP.get_((String)oSQLFieldMAP.get_vFieldLabels().get(k)) ;
						if (oField.get_cFieldName().toUpperCase().equals(cFIELDNAME) && 
							oField.get_cTableName().toUpperCase().equals(cTABLENAME)	)
						{
							oSQLFieldMAP.remove(oSQLFieldMAP.get_vFieldLabels().get(k));
							oSQLFieldMAP.get_vFieldLabels().remove(k);
						}
					}
				}
				oSQLFieldMAP.add_SQLField(new SQLFieldForRestrictTableRange(
						cTABLENAME,
						cFIELDNAME,
						cFIELDALIAS,
						new MyE2_String(cFIELDALIAS,false),
						cVORGABEWERT,
						bibE2.get_CurrSession()
						), false);
			}
		}
		
		
		
		// restrict-fields eintragen !!! wichtig! hier nur in der Haupttabelle erlaubt
		// aufbau der eintraege: <TABLENAME>:<FIELDNAME>:<FIELDALIAS>:<VORGABEWERT>
		// alle evtl. vorhandenen zusatztabellen in die fieldmap einfuegen
		if (this.VECTORWHERESTATEMENTS!= null && this.VECTORWHERESTATEMENTS.size()>0)
		{
			for (int i=0;i<this.VECTORWHERESTATEMENTS.size();i++)
			{
				oSQLFieldMAP.add_BEDINGUNG_STATIC((String)this.VECTORWHERESTATEMENTS.get(i));
			}
		}
		
		
		
		oSQLFieldMAP.initFields();
		return oSQLFieldMAP;

	}

	
	
	public E2_ComponentMAP build_ComponentMAP(SQLFieldMAP oSQLFieldMAP) throws myException
	{

		if (this.VECTORCOLUMNS.size() == 0)
			throw new myException("ListDef:build_ComponentMAP: ListDef has no columns !");
		
		E2_ComponentMAP oComponentMAP = new E2_ComponentMAP(oSQLFieldMAP);
		oComponentMAP.add_Component(E2_ModuleContainerLIST_XML.HASHKEY_CHECKBOX,new E2_CheckBoxForList(),new MyE2_String("?"));
		oComponentMAP.add_Component(E2_ModuleContainerLIST_XML.HASHKEY_LISTMARKER,new E2_ButtonListMarker(),new MyE2_String("?"));

		
		GridLayoutData oLayoutDataForList = new GridLayoutData();
		oLayoutDataForList.setInsets(E2_INSETS.I_0_0_0_0);
		
		ColumnLayoutData oLayoutDataForComponentsInMultiCols = new ColumnLayoutData();
		oLayoutDataForComponentsInMultiCols.setInsets(E2_INSETS.I_0_0_0_0);

		GridLayoutData oLayoutDataForMultiCols = new GridLayoutData();
		oLayoutDataForMultiCols.setInsets(E2_INSETS.I_0_0_0_0);
		
		/*
		 * die hash-eintraege fuer die multicolumns werden generiert und durchgezaehlt
		 */
		int iHash = 0;
		
		for (int i=0;i<this.VECTORCOLUMNS.size();i++)
		{
			ListDef_NG.ListColumn oCol = (ListDef_NG.ListColumn)this.VECTORCOLUMNS.get(i);
			Vector<FieldDef> vFields = oCol.VECTORFIELDS;
			
			if (vFields.size()==0)
				throw new myException("ListDef:build_ComponentMAP: Columns has not fields !");
			
			if (vFields.size()==1)
			{
				/*
				 * einzelkomponenten in die map
				 */
				ListDef_NG.FieldDef oField = (ListDef_NG.FieldDef) vFields.get(0);
				MyE2IF__DB_Component oComp = oField.buildComponent(oSQLFieldMAP);
				oComp.EXT_DB().set_bIsSortable(oField.SORTABLE);
				((MyE2IF__Component)oComp).EXT().set_bDisabledFromBasic(!oField.EDITABLE);
				((MyE2IF__Component)oComp).EXT().set_bIsVisibleInList(oCol.VISIBLEATSTART);
				oComponentMAP.add_Component(oComp,new MyE2_String(oField.FIELDLABELUSER));
				((Component)oComp).setLayoutData(oLayoutDataForList);
				
			}
			else
			{
				MyE2_DB_MultiComponentColumn oMCol = new MyE2_DB_MultiComponentColumn();
				oComponentMAP.add_Component("HASH__"+iHash++,oMCol,new MyE2_String(oCol.COLUMNTITEL));
				oMCol.EXT().set_bIsVisibleInList(oCol.VISIBLEATSTART);
				for (int k=0;k<vFields.size();k++)
				{
					ListDef_NG.FieldDef oField = (ListDef_NG.FieldDef)vFields.get(k);
					MyE2IF__DB_Component oComp = oField.buildComponent(oSQLFieldMAP);
					oComp.EXT_DB().set_bIsSortable(oField.SORTABLE);
					((MyE2IF__Component)oComp).EXT().set_bDisabledFromBasic(!oField.EDITABLE);
					oMCol.add_Component(oComp, new MyE2_String(oField.FIELDLABELUSER),null);
				}
				
				//ersatz fuer leere labels
				oMCol.set_all_labels_EmtpyValue("-");
				((Component)oMCol).setLayoutData(oLayoutDataForMultiCols);
				
			}
		}
		return oComponentMAP;
	}
	
	
//	public E2_ComponentGroupHorizontal build_GridWithSelectors( E2_NavigationList oNaviList, String cMODUL_KENNER) throws myException
//	{
//
//		/*
//		 * selektor-definition
//		 */
//		E2_SelectionComponentsVector 	oSelVector = 		null;
//		E2_ComponentGroupHorizontal		oHorizontalGroup = 	null;
//		E2_ComponentGrid 				oCompGrid = 		null;	       
//		
//		
//		
//		if (  (this.VECTORSELECTORSDROPDOWN!=null && this.VECTORSELECTORSDROPDOWN.size()>0) || 
//			  (this.VECTORSELECTORSCHECKBOX!=null && this.VECTORSELECTORSCHECKBOX.size()>0) ||   
//			  (this.VECTORSELECTORSCHECKBOXANAUS!=null && this.VECTORSELECTORSCHECKBOXANAUS.size()>0)   
//			)
//		{
//			oCompGrid = 		new E2_ComponentGrid(ListDef_NG.this.NUMBERCOLUMNSINSELECTORGRID);	
//			oSelVector = 		new E2_SelectionComponentsVector(oNaviList);
//			oHorizontalGroup = 	new E2_ComponentGroupHorizontal(null);
//			oHorizontalGroup.add(new MyE2_Label(new MyE2_String("Datenselektion: ")));
//			oHorizontalGroup.add(oCompGrid);
//			
//			if (this.VECTORSELECTORSDROPDOWN!=null && this.VECTORSELECTORSDROPDOWN.size()>0)
//			{
//				for (int i=0;i<this.VECTORSELECTORSDROPDOWN.size();i++)
//				{
//					ListDef_NG.SelectorDefDropDown oSel = (ListDef_NG.SelectorDefDropDown)this.VECTORSELECTORSDROPDOWN.get(i);
//					E2_ListSelectorStandard oSelStd = oSel.makeSelector();
//					oSelVector.add(oSelStd);
//					MyE2_Row oRow = new MyE2_Row(); 
//					oRow.add(new MyE2_Label(new MyE2_String(oSel.DESCRIPTION)),new Insets(1,2,3,2));
//					oRow.add(oSelStd.get_oComponentForSelection(),new Insets(1,2,1,2));
//					oCompGrid.add(oRow);
//				}
//			}
//
//			if (this.VECTORSELECTORSCHECKBOX!=null && this.VECTORSELECTORSCHECKBOX.size()>0)
//			{
//				for (int i=0;i<this.VECTORSELECTORSCHECKBOX.size();i++)
//				{
//					ListDef_NG.SelectorDefCheckBox oSel = (ListDef_NG.SelectorDefCheckBox)this.VECTORSELECTORSCHECKBOX.get(i);
//					E2_ListSelectorStandard oSelStd = oSel.makeSelector();
//					oSelVector.add(oSelStd);
//					oCompGrid.add(oSelStd.get_oComponentForSelection(),new Insets(1,2,1,2));
//				}
//			}
//
//			if (this.VECTORSELECTORSCHECKBOXANAUS!=null && this.VECTORSELECTORSCHECKBOXANAUS.size()>0)
//			{
//				for (int i=0;i<this.VECTORSELECTORSCHECKBOXANAUS.size();i++)
//				{
//					ListDef_NG.SelectorDefCheckBoxOnOff oSel = (ListDef_NG.SelectorDefCheckBoxOnOff)this.VECTORSELECTORSCHECKBOXANAUS.get(i);
//					E2_SimpleStatus_an_aus oSelStd = oSel.makeSelector();
//					oSelVector.add(oSelStd);
//					oCompGrid.add(oSelStd.get_oComponentForSelection(),new Insets(1,2,1,2));
//				}
//			}
//
//			//2013-03-04: selector benutzerdefiniert
//			E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODUL_KENNER);
//			oSelVector.add(oDB_BasedSelektor);
//			oCompGrid.add(oDB_BasedSelektor.get_oComponentForSelection(100), new Insets(4,2,10,2));
//
//		} 
//
//		this.SELVECTOR = oSelVector;
//
//
//		
//		
//		return oHorizontalGroup;
//	
//	}
	

	
	public E2_Grid    build_GridWithSelectors( E2_NavigationList oNaviList, String cMODUL_KENNER) throws myException {

		/*
		 * selektor-definition
		 */
		E2_SelectionComponentsVector 	oSelVector = 		null;
		E2_Grid							gridAussen = 		null;
		E2_Grid 						gridInnen = 		null;	       
		
		
		if (  (this.VECTORSELECTORSDROPDOWN!=null && this.VECTORSELECTORSDROPDOWN.size()>0) || 
			  (this.VECTORSELECTORSCHECKBOX!=null && this.VECTORSELECTORSCHECKBOX.size()>0) ||   
			  (this.VECTORSELECTORSCHECKBOXANAUS!=null && this.VECTORSELECTORSCHECKBOXANAUS.size()>0)) {
			
			gridAussen = 	new E2_Grid()._s(2);         //einleitung und eigentliches grid
			gridInnen = 	new E2_Grid()._s(this.NUMBERCOLUMNSINSELECTORGRID)._bo_dd();	       
			
			oSelVector = 		new E2_SelectionComponentsVector(oNaviList);
			gridAussen._a(new MyE2_Label(new MyE2_String("Datenselektion: ")), new RB_gld()._ins(E2_INSETS.I(1,1,10,2)));
			gridAussen._a(gridInnen, new RB_gld()._ins(E2_INSETS.I(1,1,1,2)));
			
			if (this.VECTORSELECTORSDROPDOWN!=null && this.VECTORSELECTORSDROPDOWN.size()>0) {
				for (int i=0;i<this.VECTORSELECTORSDROPDOWN.size();i++) 	{
					ListDef_NG.SelectorDefDropDown oSel = (ListDef_NG.SelectorDefDropDown)this.VECTORSELECTORSDROPDOWN.get(i);
					E2_ListSelectorStandard oSelStd = oSel.makeSelector();
					oSelVector.add(oSelStd);
					E2_Grid g_help = new E2_Grid()._a(new RB_lab()._t(oSel.DESCRIPTION)._fs(-2)._i())._a(oSelStd.get_oComponentForSelection())._s(1);
					gridInnen._a(g_help,  new RB_gld()._ins(E2_INSETS.I(3,1,10,2)));
				}
			}

			if (this.VECTORSELECTORSCHECKBOX!=null && this.VECTORSELECTORSCHECKBOX.size()>0) {
				for (int i=0;i<this.VECTORSELECTORSCHECKBOX.size();i++) 	{
					ListDef_NG.SelectorDefCheckBox oSel = (ListDef_NG.SelectorDefCheckBox)this.VECTORSELECTORSCHECKBOX.get(i);
					E2_ListSelectorStandard oSelStd = oSel.makeSelector();
					oSelVector.add(oSelStd);
					E2_Grid g_help = new E2_Grid()._a(new RB_lab()._t("Bitte wählen")._fs(-2)._i())._a(oSelStd.get_oComponentForSelection())._s(1);
					gridInnen._a(g_help,  new RB_gld()._ins(E2_INSETS.I(3,1,10,2)));
				}
			}

			if (this.VECTORSELECTORSCHECKBOXANAUS!=null && this.VECTORSELECTORSCHECKBOXANAUS.size()>0) {
				for (int i=0;i<this.VECTORSELECTORSCHECKBOXANAUS.size();i++) {
					ListDef_NG.SelectorDefCheckBoxOnOff oSel = (ListDef_NG.SelectorDefCheckBoxOnOff)this.VECTORSELECTORSCHECKBOXANAUS.get(i);
					if (oSel.check_if_complete().get_bIsOK()) {
						E2_SimpleStatus_an_aus oSelStd = oSel.makeSelector();
						oSelVector.add(oSelStd);
						gridInnen._a(oSelStd.get_oComponentForSelection(),  new RB_gld()._ins(E2_INSETS.I(3,1,10,2)));
					} else {
						bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Ein Selektblock vom Typ:  selektorcheckboxanausdefinition ist unvollständig oder falsch definiert !")));
						bibMSG.add_MESSAGE(oSel.check_if_complete());
					}
				}
			}

			//2013-03-04: selector benutzerdefiniert
			E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(cMODUL_KENNER);
			oSelVector.add(oDB_BasedSelektor);
			E2_Grid g_help = new E2_Grid()._a(new RB_lab()._t("Diverse")._fs(-2)._i())._a(oDB_BasedSelektor.get_oComponentForSelection(100))._s(1);
			gridInnen._a(g_help);

		} 

		this.SELVECTOR = oSelVector;


		
		
		return gridAussen;
	
	}

	
	
	
	public E2_DataSearch build_SearchDef(	SQLFieldMAP oSQLFieldMAP,
											E2_NavigationList oNaviList,
											 String MODULE_KENNER) throws myException
	{
		/*
		 * suchdefinition
		 * 
		 */
		E2_DataSearch oSearch = null;
		if (this.VECTORSEARCHFIELDS!=null && this.VECTORSEARCHFIELDS.size()>0)
		{
			String cTable = oSQLFieldMAP.get_oSQLFieldPKMainTable().get_cTableName();
			String cPK    = oSQLFieldMAP.get_oSQLFieldPKMainTable().get_cFieldName();
			oSearch = new E2_DataSearch(cTable,cPK,MODULE_KENNER);
			E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
			oSearch.set_oSearchAgent(oSearchAgent);
			
			for (int i=0;i<this.VECTORSEARCHFIELDS.size();i++)
			{
				ListDef_NG.SearchDef oSearchDef = (ListDef_NG.SearchDef)this.VECTORSEARCHFIELDS.get(i);
				oSearch.add_SearchElement(oSearchDef.SEARCHQUERY,new MyE2_String(oSearchDef.DESCRIPTIONTEXT));
			}
		}
		return oSearch;
	}


	
	public void add_SelectorDef_DropDown(String cqueryListword_ValueForSelector, String cwhereblock, String description)
	{
		this.VECTORSELECTORSDROPDOWN.add(new SelectorDefDropDown(cqueryListword_ValueForSelector,cwhereblock,description));
	}
	
	public void add_SelectorDef_CheckBox(String whereBlock_Selected, String whereBlock_UnSelected, String description, boolean bSelectedAtStart)
	{
		this.VECTORSELECTORSCHECKBOX.add(new SelectorDefCheckBox(whereBlock_Selected,whereBlock_UnSelected,description,bSelectedAtStart));
	}
	
	
	public void add_SearchDef(String cdescription,String csearchQuery)
	{
		this.VECTORSEARCHFIELDS.add(new SearchDef(cdescription,csearchQuery));
	}
	

	
	public String get_MENUEEINTRAG()
	{
		if (S.isFull(this.MENUELINE))
		{			
			return this.MENUELINE;
		}
		else
		{
			if (S.isFull(this.HEADLINE))
			{
				return this.HEADLINE;
			}
			else
			{
				return this.TABLENAME;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * hier beginnen die inneren klassen, die zur definition der structur benoetigt werden
	 */
	public class ListColumn
	{
		public String 	COLUMNTITEL = 	null;
		public boolean 	VISIBLEATSTART = true;
		public Vector<FieldDef> 	VECTORFIELDS	= 	new Vector<FieldDef>();
		
		public ListColumn(String headline)
		{
			super();
			COLUMNTITEL = headline;
		}
		
		public void add_Field(FieldDef oFieldDef)
		{
			this.VECTORFIELDS.add(oFieldDef);
		}
		
	}
	

	public class FieldDef
	{
		public String 		FIELDNAME = null;
		public String 		FIELDLABELUSER = null;
		public String 		FIELDTYPE = null;
		public boolean 		SORTABLE = true;
		public int 			PIXELSIZE	= -1;
		public int 			NUMBERROWS	= -1;
		public boolean 		EDITABLE	= true;
		public String 		SELECTQUERYFORDROPDOWN = null;        // kann ein select-statement sein oder ein durch |-getrennte textliste fuer varianten
		public boolean		MUSTFIELD = false;
		public String 		DEFAULTVALUEFORMATED = null;
		
		/**
		 * @param fieldname
		 * @param title
		 * @param type
		 * @param beditable
		 * @param sortable
		 * @param iwidthInPixel
		 * @param irowNumbers
		 * @param cdefSelectField
		 */
		public FieldDef(	String fieldname, 
							String title, 
							String type,
							boolean beditable,
							boolean sortable, 
							int iwidthInPixel, 
							int irowNumbers,
							String cdefSelectField)
		{
			super();
			this.SORTABLE = sortable;
			this.EDITABLE = beditable;
			this.FIELDNAME = fieldname;
			this.FIELDLABELUSER = title;
			this.FIELDTYPE = type;
			this.PIXELSIZE = iwidthInPixel;
			this.NUMBERROWS =irowNumbers;
			this.SELECTQUERYFORDROPDOWN = cdefSelectField;
		}
		
		/**
		 * @param oSQLFieldMap
		 * @return s component wie definiert
		 * @throws myException
		 */
		private MyE2IF__DB_Component buildComponent(SQLFieldMAP oSQLFieldMap) throws myException
		{
			
			/*
			 * immer pruefen, ob das feld der primaer-key ist, wenn ja, dann label zurueck 
			 */
			SQLField oSQLFieldTest = (SQLField)oSQLFieldMap.get(this.FIELDNAME);
			
			
			if (oSQLFieldTest == null)
				throw new myException("ListDef:FieldDef:buildComponent: Field not found: "+this.FIELDNAME);

			if (DEFAULTVALUEFORMATED !=null)
				oSQLFieldTest.set_cDefaultValueFormated(this.DEFAULTVALUEFORMATED);
			
			if (oSQLFieldTest instanceof SQLFieldForPrimaryKey)
				return new MyE2_DB_Label_INROW(oSQLFieldTest);
			
			if (this.MUSTFIELD)
				oSQLFieldTest.get_oFieldMetaDef().set_bIsNullableBasic(false);
			
			
			if (this.FIELDTYPE.equals(ListDef_NG.FIELDTYPE_LABEL))
			{
				SQLField oSQLField = (SQLField)oSQLFieldMap.get(this.FIELDNAME);
				if (oSQLField == null)
					throw new myException("ListDef:FieldDef:buildComponent:FieldName not in SQLFieldMAP: "+this.FIELDNAME);
				
				return new MyE2_DB_Label_INROW(oSQLField);
			}
			
			if (this.FIELDTYPE.equals(ListDef_NG.FIELDTYPE_CHECKBOX))
			{
				SQLField oSQLField = (SQLField)oSQLFieldMap.get(this.FIELDNAME);
				if (oSQLField == null)
					throw new myException("ListDef:FieldDef:buildComponent:FieldName not in SQLFieldMAP: "+this.FIELDNAME);
				
				return new MyE2_DB_CheckBox(oSQLField);
			}
			
			/*
			 	listdef-varianten: 
			 		(1) SELECT bezeichnung,wert from ....
			 		(2) :wert1:wert2:wert3:                 einfache liste
			 		(3) |beschreibung1|wert1|beschreibung2|wert2|   doppelte liste aus wert und beschreibung
			 	
			*/
			
			if (this.FIELDTYPE.equals(ListDef_NG.FIELDTYPE_DROPDOWN))
			{
				SQLField oSQLField = (SQLField)oSQLFieldMap.get(this.FIELDNAME);
				if (oSQLField == null)
					throw new myException("ListDef:FieldDef:buildComponent:FieldName not in SQLFieldMAP: "+this.FIELDNAME);
				
				if (this.SELECTQUERYFORDROPDOWN == null || this.SELECTQUERYFORDROPDOWN.length()==0)
					throw new myException("ListDef:FieldDef:buildComponent:Selectfield must have a select-definition: "+this.FIELDNAME);

				if (this.SELECTQUERYFORDROPDOWN.toUpperCase().trim().startsWith("SELECT"))
				{
					return new MyE2_DB_SelectField(oSQLField,bibReplacer.ReplaceSysvariablesInStrings(SELECTQUERYFORDROPDOWN),false,false);
				}
				else if (this.SELECTQUERYFORDROPDOWN.trim().startsWith(":"))
				{
					if (!this.SELECTQUERYFORDROPDOWN.trim().startsWith(":") || !this.SELECTQUERYFORDROPDOWN.trim().endsWith(":"))
						throw new myException("ListDef:FieldDef:buildComponent:Selectfield must start and end with : !!!: "+this.FIELDNAME);

					StringTokenizer oStrTok = new StringTokenizer(this.SELECTQUERYFORDROPDOWN.trim(),":");
					
					/*
					 * drop-down-def muss im ersten feld ein leeren wert haben
					 */
					String[] cDefArray = new String[oStrTok.countTokens()+1];
					cDefArray[0]="";
					int i=1;
					
					while (oStrTok.hasMoreTokens())
						cDefArray[i++]=oStrTok.nextToken();
					
					return new MyE2_DB_SelectField(oSQLField,cDefArray,false);
					
				}
				else
				{
					if (!this.SELECTQUERYFORDROPDOWN.trim().startsWith("|") || !this.SELECTQUERYFORDROPDOWN.trim().endsWith("|"))
						throw new myException("ListDef:FieldDef:buildComponent:Selectfield must start and end with | !!!: "+this.FIELDNAME);

					StringTokenizer oStrTok = new StringTokenizer(this.SELECTQUERYFORDROPDOWN.trim(),"|");
					
					try
					{
						/*
						 * drop-down-def muss im ersten feld ein leeren wert haben
						 */
						int iAnzahl = (int)oStrTok.countTokens()/2;
						
						String[][] cDefArray = new String[iAnzahl+1][2];
						cDefArray[0][0]="-";
						cDefArray[0][1]="";
						int i=1;
						
						while (oStrTok.hasMoreTokens())
						{
							cDefArray[i][0]=oStrTok.nextToken();   // anzeige
							cDefArray[i][1]=oStrTok.nextToken();   // wert
							i++;
						}
						
						return new MyE2_DB_SelectField(oSQLField,cDefArray,false);
					}
					catch (Exception ex)
					{
						throw new myException("ListDef:Error defining View/Value - DropDown - Field!");
					}
				}
				
			}

			
			if (this.FIELDTYPE.equals(ListDef_NG.FIELDTYPE_EDITFIELD))
			{
				SQLField oSQLField = (SQLField)oSQLFieldMap.get(this.FIELDNAME);
				if (oSQLField == null)
					throw new myException("ListDef:FieldDef:buildComponent:FieldName not in SQLFieldMAP: "+this.FIELDNAME);
				
				if (this.NUMBERROWS<=0)
				{
					MyE2IF__DB_Component ocomp = MaskComponentsFAB.createStandardComponent(oSQLField,false, false, 0, false);
					if (this.PIXELSIZE > 0)
					{
						if (ocomp instanceof MyE2_DB_TextField)
						{
							((MyE2_DB_TextField)ocomp).set_iWidthPixel(this.PIXELSIZE);
							
							//die textkomponente ersetzen durch die variante in der Row, die im inaktiven zustand zum button wird
							ocomp = new MyE2_DB_TextField_INROW((MyE2_DB_TextField)ocomp);   
							
						}
						if (ocomp instanceof MyE2_DB_TextArea)
						{
							((MyE2_DB_TextArea)ocomp).set_iWidthPixel(this.PIXELSIZE);
						}
					}
					return ocomp;
				}
				else
				{
					if (this.NUMBERROWS==1)
					{
						MyE2_DB_TextField oTextField = new MyE2_DB_TextField(oSQLField);
						if (this.PIXELSIZE>0)
							oTextField.set_iWidthPixel(this.PIXELSIZE);
						else
							oTextField.set_iWidthPixel(MaskComponentsFAB.get_iStandardLabelLength_In_Pixel(oSQLField));

						//die textkomponente ersetzen durch die variante in der Row, die im inaktiven zustand zum button wird
						return new MyE2_DB_TextField_INROW((MyE2_DB_TextField)oTextField);  
					}
					else
					{
						MyE2_DB_TextArea oTextField = new MyE2_DB_TextArea(oSQLField);
						if (this.PIXELSIZE>0)
							oTextField.set_iWidthPixel(this.PIXELSIZE);
						else
							oTextField.set_iWidthPixel(MaskComponentsFAB.get_iStandardLabelLength_In_Pixel(oSQLField));
						
						oTextField.set_iRows(this.NUMBERROWS);
						
						return oTextField;
					}
				}
			}
			
			
			if (this.FIELDTYPE.equals(ListDef_NG.FIELDTYPE_EDITPASSWORD))
			{
				SQLField oSQLField = (SQLField)oSQLFieldMap.get(this.FIELDNAME);
				if (oSQLField == null)
					throw new myException("ListDef:FieldDef:buildComponent:FieldName not in SQLFieldMAP: "+this.FIELDNAME);
				
				MyE2_DB_PasswordField oTextField = new MyE2_DB_PasswordField(oSQLField);
				if (this.PIXELSIZE>0)
					oTextField.set_iWidthPixel(this.PIXELSIZE);
				else
					oTextField.set_iWidthPixel(MaskComponentsFAB.get_iStandardLabelLength_In_Pixel(oSQLField));
				
				return oTextField;
			}
			
			throw new myException("ListDef:FieldDef:buildComponent:FieldName Cannot build component: "+this.FIELDNAME);
		}
	}

	
	
	/*
	 * unterstuetzt werden datenbankgenerierte selektionsliste, die in einem dropdown
	 * hinterlegt werden
	 */
	public class SelectorDefDropDown
	{
		public String SQLQUERYDROPDOWN = null;
		public String DESCRIPTION = null;
		public String WHERESTATEMENT = null;
		
		public SelectorDefDropDown(String cqueryListword_ValueForSelector, String cwhereblock, String description)
		{
			super();
			SQLQUERYDROPDOWN = cqueryListword_ValueForSelector;
			WHERESTATEMENT = cwhereblock;
			DESCRIPTION = description;
		}

		
		/**
		 * @return s an instanceof E2_ListSelectorStandard with a drop-down selector
		 * @throws myException
		 */
		public E2_ListSelectorStandard makeSelector() throws myException
		{
			E2_ListSelectorStandard oListSelector = null;
			
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			String[][] cListAndValue = oDB.EinzelAbfrageInArray(bibReplacer.ReplaceSysvariablesInStrings(this.SQLQUERYDROPDOWN));
			bibALL.destroy_myDBToolBox(oDB);
			
			if (cListAndValue == null)
				throw new myException("ListDef:SelectorDef:makeSelector:False selector-Query "+bibReplacer.ReplaceSysvariablesInStrings(this.SQLQUERYDROPDOWN));
			
			/*
			 * listvalue bekommt immer einen leer-eintrag
			 */
			String[][] cListValue2 = new String[cListAndValue.length+1][2];
			cListValue2[0][0]="*";cListValue2[0][1]="";
			for (int i=0;i<cListAndValue.length;i++)
			{
				cListValue2[i+1][0]=cListAndValue[i][0];cListValue2[i+1][1]=cListAndValue[i][1];
			}
			MyE2_SelectField oSelField = new MyE2_SelectField(cListValue2,"",false); 
			oListSelector = new E2_ListSelectorStandard(oSelField,this.WHERESTATEMENT, null, null);
			return oListSelector;
		}
		
	}
	

	
	/*
	 * unterstuetzt werden datenbankgenerierte selektionsliste, die in einer checkbox
	 * hinterlegt werden, jeweils ein where-block fuer true und false
	 */
	public class SelectorDefCheckBox
	{
		public String DESCRIPTION = null;
		public String WHEREBLOCKSELECTED = null;
		public String WHEREBLOCKUNSELECTED = null;
		public boolean SELECTEDATSTART = false;
		
		public SelectorDefCheckBox(String whereBlock_Selected, String whereBlock_UnSelected, String description, boolean bSelectedAtStart)
		{
			super();
			this.WHEREBLOCKSELECTED = 	whereBlock_Selected;
			this.WHEREBLOCKUNSELECTED = 	whereBlock_UnSelected;
			this.DESCRIPTION = 		description;
			this.SELECTEDATSTART = bSelectedAtStart;
		}

		
		/**
		 * @return s an instanceof E2_ListSelectorStandard with a checkBox- selector
		 * @throws myException
		 */
		public E2_ListSelectorStandard makeSelector() throws myException
		{
			MyE2_CheckBox oCheck = new MyE2_CheckBox(new MyE2_String(this.DESCRIPTION));
			oCheck.setSelected(this.SELECTEDATSTART);
			
			return new E2_ListSelectorStandard(oCheck,	this.WHEREBLOCKSELECTED,this.WHEREBLOCKUNSELECTED);
		}
		
	}

	

	
	/*
	 * selektors, die eine Checkbox mit ihren beiden stati an und aus definiert
	 */
	public class SelectorDefCheckBoxOnOff
	{
		public String 	DESCRIPTION = null;
		public String 	TEXTAN = null;
		public String 	TEXTAUS = null;
		public String 	TOOLTIPAN = null;
		public String 	TOOLTIPAUS = null;
		public String 	TABLENAMEFIELDNAME = null;
		public boolean 	AN_SELECTEDATSTART = false;
		public boolean 	AUS_SELECTEDATSTART = false;
		public Integer 	SPALTENBREITE = 100;
		
		public SelectorDefCheckBoxOnOff(String dESCRIPTION, String tEXTAN, String tEXTAUS, String tOOLTIPAN, String tOOLTIPAUS, String tABLENAMEFIELDNAME, boolean aN_SELECTEDATSTART, boolean aUS_SELECTEDATSTART, Integer p_Spaltenbreite) {
			super();
			DESCRIPTION = dESCRIPTION;
			TEXTAN = tEXTAN;
			TEXTAUS = tEXTAUS;
			TOOLTIPAN = tOOLTIPAN;
			TOOLTIPAUS = tOOLTIPAUS;
			TABLENAMEFIELDNAME = tABLENAMEFIELDNAME;
			AN_SELECTEDATSTART = aN_SELECTEDATSTART;
			AUS_SELECTEDATSTART = aUS_SELECTEDATSTART;
			this.SPALTENBREITE = p_Spaltenbreite;
			if (this.SPALTENBREITE==null) {
				this.SPALTENBREITE = 100;
			}
		}

		
		public MyE2_MessageVector check_if_complete() {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			if (S.isEmpty(DESCRIPTION)) {
				mv.add_MESSAGE(new MyE2_Alarm_Message("SelectorDefCheckBoxOnOff: PARAMETER: DESCRIPTION (Blockdescription) IS emtpy !!!" ));
			}
			if (S.isEmpty(TEXTAN)) {
				mv.add_MESSAGE(new MyE2_Alarm_Message("SelectorDefCheckBoxOnOff: PARAMETER: TEXTAN (Text at on-Checkbox) IS emtpy !!!" ));
			}
			if (S.isEmpty(TEXTAUS)) {
				mv.add_MESSAGE(new MyE2_Alarm_Message("SelectorDefCheckBoxOnOff: PARAMETER: TEXTAUS (Text at off-Checkboc) IS emtpy !!!" ));
			}
			if (S.isEmpty(TOOLTIPAN)) {
				mv.add_MESSAGE(new MyE2_Alarm_Message("SelectorDefCheckBoxOnOff: PARAMETER: TOOLTIPAN (Tooltip at on-Checkbox) IS emtpy !!!" ));
			}
			if (S.isEmpty(TOOLTIPAUS)) {
				mv.add_MESSAGE(new MyE2_Alarm_Message("SelectorDefCheckBoxOnOff: PARAMETER: TOOLTIPAUS (Tooltip at off-Checkbox) IS emtpy !!!" ));
			}
			if (S.isEmpty(TABLENAMEFIELDNAME)) {
				mv.add_MESSAGE(new MyE2_Alarm_Message("SelectorDefCheckBoxOnOff: PARAMETER: TABLENAMEFIELDNAME (Fieldname which is selected) IS emtpy !!!" ));
			}
			
			return mv;
		}
		

		/**
		 * @return s an instanceof E2_ListSelectorStandard with a checkBox- selector
		 * @throws myException
		 */
		public E2_SimpleStatus_an_aus makeSelector() throws myException {
			return new E2_SimpleStatus_an_aus(this.TABLENAMEFIELDNAME, this.TEXTAN, this.TEXTAUS, this.TOOLTIPAN, this.TOOLTIPAUS, this.DESCRIPTION,this.AN_SELECTEDATSTART,this.AUS_SELECTEDATSTART, this.SPALTENBREITE);
		}
		
	}


	
	
	
	
	/*
	 * suchfeld, hier ausschliesslich ueber standard-feld-suche
	 */
	public class SearchDef
	{
		public String SEARCHQUERY = null;
		public String DESCRIPTIONTEXT = null;

		public SearchDef(String cdescription,String csearchQuery)
		{
			super();
			this.DESCRIPTIONTEXT = cdescription;
			this.SEARCHQUERY = csearchQuery;
		}
		
	}
	
	
	
}

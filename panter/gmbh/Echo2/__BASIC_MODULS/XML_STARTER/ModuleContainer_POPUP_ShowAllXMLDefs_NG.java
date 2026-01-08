package panter.gmbh.Echo2.__BASIC_MODULS.XML_STARTER;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.xmlDefTools.E2_ModuleContainerLIST_XML;
import panter.gmbh.Echo2.Container.xmlDefTools.ListDef_NG;
import panter.gmbh.Echo2.Container.xmlDefTools.XStreamLoaderListDefs;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_SaveOneString;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.Project_TableNamingAgent;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibServer;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;

public class ModuleContainer_POPUP_ShowAllXMLDefs_NG extends Project_BasicModuleContainer
{

	private Vector<XML_and_Name> vXML_Defs = new Vector<XML_and_Name>();

	private MyE2_TabbedPane  oTabGroups = new MyE2_TabbedPane(null);

	private MyE2_TextField 	 oSucheFeld = new MyE2_TextField("",200,2000);

	public ModuleContainer_POPUP_ShowAllXMLDefs_NG() throws myException
	{
		super(E2_CONSTANTS_AND_NAMES.NAME_SHOW_ALL_XML_DEFS_TO_CALL);

		this.add(buildSearchComponent(),E2_INSETS.I(10,10,10,0));

		File ofileDirAll = 		new File(bibALL.get_XML_PATH_ALL());
		File ofileDirMandant = 	new File(bibALL.get_XML_PATH_MANDANT());

		if (!ofileDirAll.isDirectory())
			throw new myExceptionForUser(new MyE2_String(bibALL.get_XML_PATH_ALL()+" is not a path !!!",false).CTrans());

		if (!ofileDirMandant.isDirectory())
			throw new myExceptionForUser(new MyE2_String(bibALL.get_XML_PATH_MANDANT()+" is not a path !!!",false).CTrans());

		String[] cXMLFilesALL = 	ofileDirAll.list();
		String[] cXMLFilesMANDANT = ofileDirMandant.list();

		for (int i=0;i<cXMLFilesALL.length;i++)
		{
			try
			{
				if (cXMLFilesALL[i].toUpperCase().endsWith(".XML"))
				{
					ListDef_NG oListDef = new XStreamLoaderListDefs(bibALL.get_XML_PATH_ALL()+cXMLFilesALL[i]).get_oList();
					vXML_Defs.add(new XML_and_Name(oListDef.get_MENUEEINTRAG(),oListDef,cXMLFilesALL[i]));
				}
			} 
			catch (myException e)
			{
				e.printStackTrace();
			}
		}

		for (int i=0;i<cXMLFilesMANDANT.length;i++)
		{
			try
			{
				if (cXMLFilesMANDANT[i].toUpperCase().endsWith(".XML"))
				{
					ListDef_NG oListDef = new XStreamLoaderListDefs(bibALL.get_XML_PATH_MANDANT()+cXMLFilesMANDANT[i]).get_oList();
					vXML_Defs.add(new XML_and_Name(oListDef.get_MENUEEINTRAG(),oListDef,cXMLFilesMANDANT[i]));
				}
			} 
			catch (myException e)
			{
				e.printStackTrace();
			}
		}

		Collections.sort(this.vXML_Defs);

		String searchresult = new E2_UserSetting_SaveOneString(ENUM_USER_SAVEKEY.KEY_SAVE_SEARCHFIELD_IN_XML_MODULES).LOAD();
		
		if(S.isFull(searchresult)){
			oSucheFeld.setText(searchresult);
			buildOrRefreshItemList(suche(searchresult));
		}else{
			buildOrRefreshItemList(null);
		}
	}

	private MyE2_Grid buildSearchComponent() throws myException{
		int [] ibreite = {200,20,20};
		
		
		
		E2_Grid4MaskSimple searchGrid = new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS())
				.def_(E2_INSETS.I(5,0,0,0))
				.add_(oSucheFeld)
				.add_(new MyE2_Button(E2_ResourceIcon.get_RI("suche.png"),  new MyE2_String("Suche das Stichwort in den XML-Listen"), new ownSucheActionAgent()))
				.add_(new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"), new MyE2_String("Suche löschen"), new ownEraseAgent()))
				.setSize_(ibreite);
		return searchGrid;
	}

	public Vector<XML_and_Name> suche(String strSucheKriterium) {
		String sucheKriterium = strSucheKriterium.toUpperCase();
		Vector<XML_and_Name> vErgebnis = new Vector<>();
		for(XML_and_Name item : ModuleContainer_POPUP_ShowAllXMLDefs_NG.this.vXML_Defs){
			if(item.oListDef.MENUEGROUP.toUpperCase().contains(sucheKriterium)){
				vErgebnis.addElement(item);
			}else if(item.cMenueEintrag.toUpperCase().contains(sucheKriterium)){
				vErgebnis.addElement(item);
			}else if(item.oListDef.MENUELINE.toUpperCase().equals(sucheKriterium)){
				vErgebnis.addElement(item);
			}
		}
		return vErgebnis;
	}

	
	private class ownStarterButton extends MyE2_Button
	{
		private XML_and_Name oDefPopup = null;

		public ownStarterButton(XML_and_Name defPopup)
		{
			super(defPopup.cMenueEintrag);
			oDefPopup = defPopup;
			this.add_oActionAgent(new actionStartModule());
			this.add_GlobalAUTHValidator(E2_CONSTANTS_AND_NAMES.NAME_SHOW_ALL_XML_DEFS_TO_CALL,defPopup.oListDef.MODULKENNER);

			//2014-01-03: in datenbank nach eine @USER: beschreibung einer Tabelle sehen und diese einblende
			String cToolTip = bibServer.get_Tooltips4TablesFromDB(this.oDefPopup.oListDef.TABLENAME);
			if (S.isEmpty(cToolTip)) {
				cToolTip = defPopup.XML_FileName;
			} else {
				cToolTip = new MyE2_String(cToolTip).CTrans()+"   ("+defPopup.XML_FileName+")";
			}
			this.setToolTipText(cToolTip);
		}

		private class actionStartModule extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				E2_ModuleContainerLIST_XML oPopupContainer = new E2_ModuleContainerLIST_XML(new Project_TableNamingAgent(),
						ownStarterButton.this.oDefPopup.oListDef);

				oPopupContainer.set_cADDON_TO_CLASSNAME(ownStarterButton.this.oDefPopup.oListDef.MODULKENNER);

				oPopupContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(800), new MyE2_String(ownStarterButton.this.oDefPopup.cMenueEintrag));
				oPopupContainer.set_bIsStartContainer_4_DBTimeStamps(true);
			}
		}

	}

	private class ownEraseAgent extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			oSucheFeld.setText("");
			buildOrRefreshItemList(null);
			new E2_UserSetting_SaveOneString(ENUM_USER_SAVEKEY.KEY_SAVE_SEARCHFIELD_IN_XML_MODULES).SAVE("");
		}
	}
	

	private class ownSucheActionAgent extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			String sucheKriterium = oSucheFeld.getText();
			Vector<XML_and_Name> vErgebnis = suche(sucheKriterium);

			buildOrRefreshItemList(vErgebnis);
			
			if(vErgebnis.size()>0){
				new E2_UserSetting_SaveOneString(ENUM_USER_SAVEKEY.KEY_SAVE_SEARCHFIELD_IN_XML_MODULES).SAVE(oSucheFeld.getText());
			}
		}


	}


	private class XML_and_Name implements Comparable<XML_and_Name>
	{
		public String   	cMenueEintrag = null;
		public ListDef_NG  	oListDef = null;
		public String   	XML_FileName = null;
		public XML_and_Name(String menueEintrag, ListDef_NG oListDEF, String cXMLFileName)
		{
			super();
			cMenueEintrag = menueEintrag;
			this.oListDef = oListDEF;
			this.XML_FileName = cXMLFileName;
		}
		@Override
		public int compareTo(XML_and_Name o)
		{
			XML_and_Name oComp = (XML_and_Name)o;

			return this.cMenueEintrag.compareTo(oComp.cMenueEintrag);
		}
	}

	
	public void buildOrRefreshItemList(Vector<XML_and_Name> oItemVector) throws myException{

		if(!(oItemVector == null)) {
			if( oItemVector.size()==0 && S.isFull(this.oSucheFeld.getText())){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Kein Suchergebnis für den Ausdruck <"+ this.oSucheFeld.getText() +"> !"));
			}
		}

		if(oItemVector == null || oItemVector.size()==0){
			oItemVector = vXML_Defs;
		}

		String cStandardGroup = "-";

		//sammler fuer die buttons der gruppen
		HashMap<String,Vector<ownStarterButton>> vStarterGroups = new HashMap<String, Vector<ownStarterButton>>();
		Vector<String>			 vGroups = new Vector<String>();

		vGroups.add(cStandardGroup);
		vStarterGroups.put(cStandardGroup, new Vector<ownStarterButton>());


		//jetzt die Buttons sammeln und die gruppen auslesen
		for (int i=0;i<oItemVector.size();i++)
		{
			ownStarterButton oStart = new ownStarterButton(oItemVector.get(i));

			if (!vGroups.contains(oStart.oDefPopup.oListDef.MENUEGROUP))
			{
				vGroups.add(oStart.oDefPopup.oListDef.MENUEGROUP);
				vStarterGroups.put(oStart.oDefPopup.oListDef.MENUEGROUP, new Vector<ownStarterButton>());
			}

			if (oStart.valid_GlobalValidation().get_bIsOK())    //nur die freigeschalteten anzeigen
			{
				if (S.isEmpty(oStart.oDefPopup.oListDef.MENUEGROUP))
				{
					//dann ins grosse toepfchen
					vStarterGroups.get(cStandardGroup).add(oStart);
				}
				else
				{
					vStarterGroups.get(oStart.oDefPopup.oListDef.MENUEGROUP).add(oStart);
				}
			}
		}

		this.remove(oTabGroups);
		this.oTabGroups = null;
		this.oTabGroups = new MyE2_TabbedPane(600);
		this.oTabGroups.set_bAutoHeight(true);
		this.add(oTabGroups, E2_INSETS.I_10_10_10_10);

		//jetzt nachsehen, welche gruppen fuer den Benutzer gefuellt wurden, diese dann in Tabs anordnen
		Collections.sort(vGroups);
		for (int i=0;i<vGroups.size();i++)
		{
			Vector<ownStarterButton> vStartersInThisGroup = vStarterGroups.get(vGroups.get(i));
			if (vStartersInThisGroup.size()>0)
			{
				MyE2_Column oColumnGroup = new MyE2_Column();
				oTabGroups.add_Tabb(new MyE2_String(vGroups.get(i),false),oColumnGroup);

				for (int k=0;k<vStartersInThisGroup.size();k++)
				{
					oColumnGroup.add(vStartersInThisGroup.get(k));
				}
			}
		}
	}
}

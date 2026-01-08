package panter.gmbh.Echo2.__BASIC_MODULS.XML_STARTER;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.xmlDefTools.E2_ModuleContainerLIST_XML;
import panter.gmbh.Echo2.Container.xmlDefTools.ListDef_NG;
import panter.gmbh.Echo2.Container.xmlDefTools.XStreamLoaderListDefs;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.Project_TableNamingAgent;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibServer;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;

public class ModuleContainer_POPUP_ShowAllXMLDefs extends Project_BasicModuleContainer
{

	private Vector<XML_and_Name> vXML_Defs = new Vector<XML_and_Name>();
	
	MyE2_TabbedPane  oTabGroups = new MyE2_TabbedPane(null);

	
	public ModuleContainer_POPUP_ShowAllXMLDefs() throws myException
	{
		super(E2_CONSTANTS_AND_NAMES.NAME_SHOW_ALL_XML_DEFS_TO_CALL);
		
		this.oTabGroups.set_bAutoHeight(true);
		
		File ofileDirAll = 		new File(bibALL.get_XML_PATH_ALL());
		File ofileDirMandant = 	new File(bibALL.get_XML_PATH_MANDANT());
		
		if (!ofileDirAll.isDirectory())
			throw new myExceptionForUser(new MyE2_String(bibALL.get_XML_PATH_ALL()+" is not a path !!!",false).CTrans());

		if (!ofileDirMandant.isDirectory())
			throw new myExceptionForUser(new MyE2_String(bibALL.get_XML_PATH_MANDANT()+" is not a path !!!",false).CTrans());
		
		String[] cXMLFilesALL = 	ofileDirAll.list();
		String[] cXMLFilesMANDANT = ofileDirMandant.list();

		this.add(this.oTabGroups, E2_INSETS.I_10_10_10_10);
		
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
		
		String cStandardGroup = "-";
		
		//sammler fuer die buttons der gruppen
		HashMap<String,Vector<ownStarterButton>> vStarterGroups = new HashMap<String, Vector<ownStarterButton>>();
		Vector<String>			 vGroups = new Vector<String>();
		
		vGroups.add(cStandardGroup);
		vStarterGroups.put(cStandardGroup, new Vector<ownStarterButton>());

		
		//jetzt die Buttons sammeln und die gruppen auslesen
		for (int i=0;i<this.vXML_Defs.size();i++)
		{
			ownStarterButton oStart = new ownStarterButton(this.vXML_Defs.get(i));
			
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

}

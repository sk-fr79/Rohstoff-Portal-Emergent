package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_VERWALTUNG;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.MyDropDownSettings;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


public class MODUL__MASK_ComponentMAP extends E2_ComponentMAP 
{

	public MODUL__MASK_ComponentMAP() throws myException
	{
		super(new MODUL__MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		

		MyDropDownSettings  oDDHauptmenue = new MyDropDownSettings(bibE2.cTO(),"JD_HAUPTMENUE","MENUETXT","ID_HAUPTMENUE",null,null,true,"MENUETXT");
		 
		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label(			oFM.get_("ID_SERVLETS")), new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_TextField(		oFM.get_("BESCHREIBUNG"),true,500,50,false), new MyE2_String("Beschreibung"));
		this.add_Component(new MyE2_DB_SelectField(		oFM.get_("ID_HAUPTMENUE"),oDDHauptmenue.getDD(),false), new MyE2_String("Hauptmenü"));
		this.add_Component(new MyE2_DB_TextField(		oFM.get_("MENUEEINTRAG"),true,200,100,false), new MyE2_String("Menüeintrag"));
		this.add_Component(new MyE2_DB_TextField(		oFM.get_("SERVLETAUFRUF"),true,500,100,false), new MyE2_String("Servletaufruf"));
		this.add_Component(new MyE2_DB_TextField(		oFM.get_("SORTNUMMER"),true,100,10,false), new MyE2_String("Reihenfolge"));
		this.add_Component(new MyE2_DB_TextField(		oFM.get_("TABTEXT"),true,200,50,false), new MyE2_String("Text auf Tab"));
		
		//zusatzbutton zum holen der moeglichen Module (Liste der im Programm registrierten module)
		HashMap<String, MyE2_String> hmModule = E2_MODULNAME_ENUM.get_hmModul_Kenner_And_Names();
		
//		E2_TabbedPaneForFirstContainer.get_TabSheet(null, null, null, null, hmModule);
		String[][]  tabWerte = new String[hmModule.size()][2];
		
		int i=0;
		for (Map.Entry<String, MyE2_String> oEntry: hmModule.entrySet())
		{
			tabWerte[i][0]=oEntry.getValue().COrig();
			tabWerte[i++][1]=oEntry.getKey();
		}
		

		this.add_Component("HOLE_MODUL",new ownButtonSearchModul(), new MyE2_String("Text auf Tab"));
		
		
		
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new MODUL__MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new MODUL__MASK_FORMATING_Agent());
	}

	
	
	private class ownButtonSearchModul extends MyE2_Button
	{

		public ownButtonSearchModul()
		{
			super(E2_ResourceIcon.get_RI("suche.png"),true);
			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO execInfo)	throws myException
				{
					new ownPopup();
				}
			});
					
		}
		
		
		
		
		
	}
	
	
	private class ownPopup extends E2_BasicModuleContainer
	{

		public ownPopup() throws myException
		{
			super();
			
			MyE2_Column  oColWithAuswahl = new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
			
			//zusatzbutton zum holen der moeglichen Module (Liste der im Programm registrierten module)
			HashMap<String, MyE2_String> hmModule = E2_MODULNAME_ENUM.get_hmModul_Kenner_And_Names_ONLY_LIST_MODULES();
			
			//E2_TabbedPaneForFirstContainer.get_TabSheet(null, null, null, null, hmModule);
			
			TreeMap<String,ownButtonSelectModulAndCloseWindows> sortMap = new TreeMap<String, ownButtonSelectModulAndCloseWindows>();
			
			for (Map.Entry<String, MyE2_String> oEntry: hmModule.entrySet())
			{
				sortMap.put(oEntry.getValue().COrig(),new ownButtonSelectModulAndCloseWindows(oEntry.getValue().COrig(),oEntry.getKey()));
			}
			
			Iterator<ownButtonSelectModulAndCloseWindows> oIter = sortMap.values().iterator();
			
			while (oIter.hasNext())
			{
				oColWithAuswahl.add(oIter.next(), E2_INSETS.I_5_2_5_2);
			}

			this.add(oColWithAuswahl, E2_INSETS.I_2_2_2_2);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(300), new Extent(700), new MyE2_String("Auswahl der möglichen Module"));
		}

		
		private class ownButtonSelectModulAndCloseWindows extends MyE2_Button
		{

			public ownButtonSelectModulAndCloseWindows(String cBeschreibung, String cKENNER)
			{
				super(cBeschreibung);
				this.EXT().set_C_MERKMAL(cKENNER);
				this.EXT().set_C_MERKMAL2(cBeschreibung);
				
				//dazuschreiben, wie oft ein modul schon in der Definition gelandet ist:
				String cAnzahl = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM JD_SERVLETS WHERE UPPER(SERVLETAUFRUF)='ECHO2_STARTER:"+cKENNER.toUpperCase()+"'");

				if (!cAnzahl.equals("0"))
				{
					this.setText(this.getText()+" ("+cAnzahl+")");
				}
				else
				{
					this.setFont(new E2_FontBold(2));
					this.setBorder(new Border(2, Color.BLACK, Border.STYLE_SOLID));
				}
				this.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO execInfo) throws myException
					{
						MyE2_Button  oSelect_Module  = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
						MyE2_DB_TextField oTFAufruf = (MyE2_DB_TextField)MODUL__MASK_ComponentMAP.this.get__Comp("SERVLETAUFRUF");
						oTFAufruf.setText("echo2_starter:"+oSelect_Module.EXT().get_C_MERKMAL());
						
						MyE2_DB_TextField oTFMenue = (MyE2_DB_TextField)MODUL__MASK_ComponentMAP.this.get__Comp("MENUEEINTRAG");
						oTFMenue.setText(oSelect_Module.EXT().get_C_MERKMAL2());
						
						ownPopup.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
				});
			}
			
		}
		

		
		
		
	}
	
	
	
	
}

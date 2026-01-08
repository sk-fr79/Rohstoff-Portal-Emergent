package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_MODULES;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ATOMBUILD.Komplett_Aufbau_Bewegungs_Struktur_aus_Fuhre;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._KORRE.LIEF_BED.KorrigiereLieferbedingungenNachRegeln;
import echopointng.Separator;


public class PluginCol_Korrekturen extends Basic_PluginColumn
{

	
	public PluginCol_Korrekturen(ContainerForVerwaltungsTOOLS oMothercontainer) throws myException
	{
		super(oMothercontainer);
		
		MyE2_Grid  oGrid4Buttons = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
		
		MyE2_Button 		oButtonBuildAll = new MyE2_Button(
								new MyE2_String("Starte Korrektur"),
								new MyE2_String(""),
								new ActionAgentKorrigiereLieferbedingungen());
		
		
		this.add(oGrid4Buttons);
		
		oGrid4Buttons.add(new MyE2_Label(new MyE2_String("Fuhren-Lieferbedingungen nach Regelsätzen korrigieren")), E2_INSETS.I(2,2,20,2));
		oGrid4Buttons.add(oButtonBuildAll, E2_INSETS.I(2,2,20,2));
		
		//2015-05-20: automatische generieren der Valid-kennungen fuer ENUM-Basierte Validierer
		oGrid4Buttons.add(new MyE2_Label(new MyE2_String("Validierkenner für die Buttons aus ENUM erzeugen")), E2_INSETS.I(2,2,20,2));
		oGrid4Buttons.add(new bt_Start_Generate_ButtonTable(), E2_INSETS.I(2,2,20,2));
		
		//2016-10-04: korrektur der fuhren-transformation in die atomare bewegungsstruktur
		oGrid4Buttons.add(new Komplett_Aufbau_Bewegungs_Struktur_aus_Fuhre(this), 2, E2_INSETS.I(2,2,2,2));
		
		
		this.add( new Separator());
		this.add(this.get_TextArea4Output(),ContainerForVerwaltungsTOOLS.INSETS_LIST);
		
	}
	
	
	private class bt_Start_Generate_ButtonTable extends MyE2_Button {

		public bt_Start_Generate_ButtonTable() {
			super(new MyE2_String("Erzeuge die Buttoneinträge"));
			this.add_oActionAgent(new ownAction());
		}
		
		private class ownAction extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				Vector<MyString>  vMeldungen = new Vector<MyString>();
				
				VALID_ENUM_MODULES.create_DB_Button_Entrys_4_all_ModuleRanges(vMeldungen);
				E2_MODULNAME_ENUM.create_DB_Button_Entrys_4_all_ModuleRanges(vMeldungen);
				
				if (vMeldungen.size()>0) {
					StringBuffer cText = new StringBuffer();
					for (MyString str: vMeldungen) {
						cText.append(str.CTrans());
						cText.append("\n");
					}
					
					PluginCol_Korrekturen.this.get_TextArea4Output().setText(cText.toString());
				}
			}
			
		}
		
	}
	
	
	private class ActionAgentKorrigiereLieferbedingungen extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new E2_ServerPushMessageContainer(new Extent(800),new Extent(250),new MyE2_String("Korrekturlauf der Lieferbedingungen ..."),true,false,true,4000) {
				@Override
				public void Run_Loop() throws myException {
					new KorrigiereLieferbedingungenNachRegeln(this);
				}

				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException {
					
				}
			};
		}
		
	}
	
	
	
	
	
	
	
}



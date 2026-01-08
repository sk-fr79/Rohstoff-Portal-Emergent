package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.Check;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_UserSetting_TextSaver;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.VERSION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class PROJECT_INFO_Container extends E2_BasicModuleContainer 
{

	public PROJECT_INFO_Container() 
	{
		super();
		
		MyE2_Grid  oGridInfo = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.add(oGridInfo, E2_INSETS.I_5_5_5_5);
		
		oGridInfo.add(new MyE2_Label(new MyE2_String("Rohstoffportal"),MyE2_Label.STYLE_TITEL_BIG()),2, E2_INSETS.I(5,2,5,4));
		
		oGridInfo.add(new MyE2_Label(new MyE2_String("Version:")),E2_INSETS.I(5,2,5,4));
		oGridInfo.add(new MyE2_Label(VERSION.Version),2,E2_INSETS.I(5,2,5,4));
		
		oGridInfo.add(new MyE2_Label(new MyE2_String("Build-Nr.:")),E2_INSETS.I(5,2,5,4));
		oGridInfo.add(new MyE2_Label(VERSION.BuildNumber),2,E2_INSETS.I(5,2,5,4));
		
		oGridInfo.add(new MyE2_Label(new MyE2_String("Datum:")),E2_INSETS.I(5,2,5,4));
		oGridInfo.add(new MyE2_Label(VERSION.Datum),2,E2_INSETS.I(5,2,5,4));
		
		try {
			if (bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES()) {
				// url: jdbc:oracle:thin:@192.168.0.216:1521:oraleb
				
				String 					cJdbcString = 	((String)bibALL.getSessionValue("JDBC_STRING"));
				
				//4.03.2019: new parser for jdbc string compatible with oracle18express edition
				//author: sebastien
				//empty string are rejected
				Check<String> entscheider = (teststr)-> {return S.isFull(teststr);};
				
				
				//regex: [^a-zA-Z\\d.] all non alphanumerics and dot are replace by @
				VEK<String> vParts = new VEK<String>()._addValidated(entscheider, cJdbcString.replaceAll("[^a-zA-Z\\d.]","@").split("@"));
				
//				Vector<String>			vParts = 		bibALL.TrenneZeile(cJdbcString, ":");
				
				oGridInfo.add(new MyE2_Label(new MyE2_String("Datenbank-IP:")),E2_INSETS.I(5,2,5,4));
				oGridInfo.add(new MyE2_Label(vParts.get(3)),E2_INSETS.I(5,2,5,4));
				
				oGridInfo.add(new MyE2_Label(new MyE2_String("Datenbank-SID:")),E2_INSETS.I(5,2,5,4));
				oGridInfo.add(new MyE2_Label(vParts.get(5)),E2_INSETS.I(5,2,5,4));
				
				oGridInfo.add(new MyE2_Label(new MyE2_String("Datenbank-Schema:")),E2_INSETS.I(5,2,5,4));
				oGridInfo.add(new MyE2_Label((String) bibALL.getSessionValue("JAVA_LOGIN")),E2_INSETS.I(5,2,5,4));
			}
			if (bibALL.get_RECORD_USER().is_DEVELOPER_YES() && bibALL.get_bDebugMode()) {
				oGridInfo.add(new MyE2_Label(new MyE2_String("Debug-Level einstellen:")),E2_INSETS.I(5,2,5,4));
				oGridInfo.add(new debug_settings_button(),E2_INSETS.I(5,2,5,4));
				oGridInfo.add(new MyE2_Label(""),E2_INSETS.I(5,2,5,4));
				oGridInfo.add(new disable_debug_mode_in_session(),E2_INSETS.I(5,2,5,4));
			}
		} catch (myException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 2015-05-13: einstellung des aktuellen debug-levels
	 * @author martin
	 *
	 */
	private class debug_settings_button extends MyE2_Button {

		public debug_settings_button() {
			super("Debug ?", MyE2_Button.StyleButtonCenteredWithDDarkBorder());
			this.add_GlobalValidator(new ownValidator());
			this.add_oActionAgent(new ownActionAgent());
		}
		
		private class ownValidator extends XX_ActionValidator {

			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
				if (bibALL.get_RECORD_USER().is_DEVELOPER_NO()) {
					return new MyE2_MessageVector(new MyE2_Alarm_Message(new MyE2_String("Nur für Entwickler !")));
				}
				return new MyE2_MessageVector();
			}

			@Override
			protected MyE2_MessageVector isValid(String cID_Unformated)	throws myException {
				return  new MyE2_MessageVector();
			}
		}
		
		
		private class ownActionAgent extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				new ownPopup();
			}
			
		}
		
		private class ownPopup extends E2_BasicModuleContainer {

			private Vector<ownCB> v_checkboxes = new Vector<ownCB>();
			public ownPopup() throws myException {
				super();
				
				MyE2_Grid grid = new MyE2_Grid(1);
				
				String actualDebugStatus = "|"+bibALL.get_DEBUG_FLAGS()+"|";
			
				for (DEBUG.DEBUG_FLAGS flag: DEBUG.DEBUG_FLAGS.values()) {
					ownCB cb= new ownCB(flag, actualDebugStatus.contains(flag.name()));
					this.v_checkboxes.add(cb);
					grid.add(cb,E2_INSETS.I(2,2,2,2));
				}
				
				MyE2_Button  save = new MyE2_Button("Speichern");
				save.add_oActionAgent(new ownActionSave());
				grid.add(save,E2_INSETS.I(2,20,2,2));
				
				this.add(grid,E2_INSETS.I(10,10,10,10));
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(300), new Extent(500), new MyE2_String("Debug-Level"));
			}
			
			private class ownCB extends MyE2_CheckBox {
				public ownCB(DEBUG.DEBUG_FLAGS  flag, boolean ein) {
					super(flag.name());
					this.setSelected(ein);
					this.EXT().set_C_MERKMAL(flag.name());
				}
			}
			
			private class ownActionSave extends XX_ActionAgent {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					String cDEBUGS = "";
					for (ownCB cb: ownPopup.this.v_checkboxes) {
						if (cb.isSelected()) {
							cDEBUGS=cDEBUGS+"|"+cb.EXT().get_C_MERKMAL();
						}
					}
					
					if (!cDEBUGS.startsWith("|")) {
						cDEBUGS = "|"+cDEBUGS;
					}
					if (!cDEBUGS.endsWith("|")) {
						cDEBUGS = cDEBUGS+"|";
					}
					
					//jetzt als usersetting ablegen
					new E2_UserSetting_TextSaver(ENUM_USER_SAVEKEY.SESSION_HASH_SAVE_DELEVELOPER_DEBUG_LEVEL).save_values(cDEBUGS);
					bibALL.set_DEBUG_FLAGS(cDEBUGS);
					
					ownPopup.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				}
				
			}
			
			
			
		}
		
	}

	
	
	private class disable_debug_mode_in_session extends MyE2_Button {

		public disable_debug_mode_in_session() {
			super(new MyE2_String("Debug-Modus AUS"));
			
			this.setToolTipText(new MyE2_String("In der aktuellen Sitzung den Debug-Modus auf false stellen").CTrans());
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					bibALL.set_bDebugMode(false);
				}
			});
		}
		
	}
}

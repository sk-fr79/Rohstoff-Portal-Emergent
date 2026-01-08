package panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR.TEMPLATES.RB_MODULES;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_HashMap;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR.TEMPLATES.RB_MODULES.Generator.PLACEHOLDER;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class CG__ModuleContainer_RB extends MyE2_Column {

	private interface components4here {
		public PLACEHOLDER get__PlaceHolder();
		public String      getText() throws myException;
	}

	
	private HashMap<PLACEHOLDER,components4here>  vFieldSammler = new HashMap<PLACEHOLDER,components4here>();
	
	public CG__ModuleContainer_RB() throws myException {
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		int[] breite = {300,200};
		MyE2_Grid  gridHelp = new MyE2_Grid(breite, MyE2_Grid.STYLE_GRID_GREY_BORDER_NO_INSETS_W100());
		
		
		
		for (Generator.PLACEHOLDER placeholder: Generator.PLACEHOLDER.values()) {
			if (placeholder.is_for_replacement_basic()) {
				gridHelp.add(new MyE2_Label(placeholder.get_c_erklaerung(),true), E2_INSETS.I(2,4,4,2));
				components4here tf = null;
				if (placeholder == Generator.PLACEHOLDER.TABLENAME) {
					tf = new ownSelectField(placeholder);
				} else {
					tf = new ownTextField(placeholder);
				}
				this.vFieldSammler.put(placeholder,tf);
				gridHelp.add((Component)tf, E2_INSETS.I(2,4,4,2));
			}
		}

		gridHelp.add(new MyE2_Button(	new MyE2_String("Erzeuge Klassen ..."),
										MyE2_Button.Style_Button_BIG_BLACK_ON_GREEN(), 
										null, 
										new ownActionAgent()), E2_INSETS.I(2,10,2,10));
		

		//20180813: usersetter, letzte werte laden
		try {
			HashMap<PLACEHOLDER, String> hmFuellWerte  = new ownUserSetting().read();
			for (PLACEHOLDER p: hmFuellWerte.keySet()) {
				if (S.isFull(hmFuellWerte.get(p)) && this.vFieldSammler.containsKey(p)) {
					if (p == PLACEHOLDER.TABLENAME) {
						((ownSelectField)this.vFieldSammler.get(p)).set_ActiveValue_OR_FirstValue(hmFuellWerte.get(p));
					} else {
						((ownTextField)this.vFieldSammler.get(p)).setText(hmFuellWerte.get(p));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

			
		this.add(gridHelp,E2_INSETS.I(10,10,10,10));
	}


	
	private class ownTextField extends MyE2_TextField implements components4here {
		private PLACEHOLDER  placeHolder = null;

		public ownTextField(PLACEHOLDER p_placeHolder) {
			super();
			this.placeHolder = p_placeHolder;
			this.set_iWidthPixel(400);
		}

		public PLACEHOLDER get__PlaceHolder() {
			return placeHolder;
		}
	}

	
	private class ownSelectField extends MyE2_SelectField implements components4here {
		private PLACEHOLDER  placeHolder = null;

		public ownSelectField(PLACEHOLDER p_placeHolder) throws myException {
			super();
			this.placeHolder = p_placeHolder;
			this.populateCombobox(DB_META.get_TablesQuery_NG(DB_META.DB_ORA, true, true), null, false, true, false, false);
			this.add_oActionAgent(new ownActionAgentSelectTable());
			this.setWidth(new Extent(400));
		}
		public PLACEHOLDER get__PlaceHolder() {
			return placeHolder;
		}
		public String getText() throws myException {
			return this.get_ActualWert();
		}
		
		private class ownActionAgentSelectTable extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				CG__ModuleContainer_RB oThis = CG__ModuleContainer_RB.this;
				String tabName= ownSelectField.this.get_ActualWert();
				if (S.isFull(tabName)) {
					((ownTextField)oThis.vFieldSammler.get(PLACEHOLDER.BASETABLENAME)).setText(
							tabName.substring(3));
					((ownTextField)oThis.vFieldSammler.get(PLACEHOLDER.BASETABLENAME_LOWERCASE)).setText(
							tabName.toLowerCase().substring(3));
					
					((ownTextField)oThis.vFieldSammler.get(PLACEHOLDER.MODULEKEYNAME)).setText(
							tabName.substring(3));
					
//					if (S.isEmpty(((ownTextField)oThis.vFieldSammler.get(PLACEHOLDER.MODULEKEYNAME)).getText()))  {
//						((ownTextField)oThis.vFieldSammler.get(PLACEHOLDER.MODULEKEYNAME)).setText(tabName.substring(3));
//					}
					if (S.isEmpty(((ownTextField)oThis.vFieldSammler.get(PLACEHOLDER.PACKAGE_PRAEFIX)).getText()))  {
						((ownTextField)oThis.vFieldSammler.get(PLACEHOLDER.PACKAGE_PRAEFIX)).setText((tabName.substring(3)+"  ").substring(0,3).trim());
					}
					if (S.isEmpty(((ownTextField)oThis.vFieldSammler.get(PLACEHOLDER.PACKAGE)).getText()))  {
						((ownTextField)oThis.vFieldSammler.get(PLACEHOLDER.PACKAGE)).setText("panter.gmbh.Echo2.");
					}
				}
			}
		}
	}
	
	
	
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			CG__ModuleContainer_RB oThis = CG__ModuleContainer_RB.this;
			HashMap<Generator.PLACEHOLDER, String> hmFuellWerte = new HashMap<Generator.PLACEHOLDER, String>();
			
			for (components4here tf: oThis.vFieldSammler.values()) {
				if (S.isEmpty(tf.getText())) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte füllen Sie das Feld: ",true,tf.get__PlaceHolder().get_c_erklaerung().CTrans(),false," aus",true)));
				} else {
					hmFuellWerte.put(tf.get__PlaceHolder(), tf.getText());
				}
			}
			
			if (bibMSG.get_bIsOK()) {
				new Generator(hmFuellWerte);
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die erzeigten Klassen stehen im OUTPUT-Ordner ...")));
				
			}
			
			try {
				//20180813: eintraege abspeichern fuer weitere aufrufe
				new ownUserSetting().save(hmFuellWerte);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	
	
	private class ownUserSetting extends E2_UserSetting_HashMap {

		public ownUserSetting() {
			super(ENUM_USER_SAVEKEY.KEY_SAVE_SETTINGS_PROGRAMMGENERATOR_RB_MODUL, PLACEHOLDER.genKeys());
		}
		
		public void save( HashMap<PLACEHOLDER,String>  hmFuellWerte) throws myException {
			HashMap<String, String> hm = new HashMap<>();
			
			for (PLACEHOLDER p: hmFuellWerte.keySet()) {
				hm.put(p.toString(), hmFuellWerte.get(p));
			}
			this.SAVE(hm, ENUM_USER_SAVEKEY.KEY_SAVE_SETTINGS_PROGRAMMGENERATOR_RB_MODUL.toString());
		}
	
		public HashMap<PLACEHOLDER, String> read() throws myException {
			HashMap<String, String> hm = new HashMap<>();
			HashMap<PLACEHOLDER, String> hmRet = new HashMap<>();
			
			hm = this.READ(ENUM_USER_SAVEKEY.KEY_SAVE_SETTINGS_PROGRAMMGENERATOR_RB_MODUL.toString());
			
			if (hm!=null) { 
				for (String key: hm.keySet()) {
					if (PLACEHOLDER.findPLACEHOLDER(key)!=null) {
						hmRet.put(PLACEHOLDER.findPLACEHOLDER(key), hm.get(key));
					}
				}
			}
			return hmRet;
		}
		
	}
}

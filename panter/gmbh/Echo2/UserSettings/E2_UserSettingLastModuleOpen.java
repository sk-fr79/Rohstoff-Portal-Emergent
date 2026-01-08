package panter.gmbh.Echo2.UserSettings;

import java.util.Vector;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.FillImage;
import nextapp.echo2.app.FillImageBorder;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.Messaging.MessageAgent_InPOPUP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.E2_TabbedPaneForFirstContainer;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class E2_UserSettingLastModuleOpen extends XXX_UserSetting
{

	private Vector<String> vAufrufStrings = new Vector<String>();
	
	//2012-05-24: anzeigegrid fuer fortschrittsanzeige bei laden des alten status
	private MyE2_Grid  oGridAussen = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	private MyE2_Grid  oGridFuerAnzeige = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	
	
	
	public void store_ModuleList() throws myException    //die methoden werden hier nur 
	{
		this.STORE("--", new String("--"));        
	}
	
	public void load_old_modules()  throws myException
	{
		String cOpenModules = (String)this.get_Settings("--");
		
		if (S.isFull(cOpenModules))
		{
			//2012-05-24: fortschrittsanzeige bei den aufruf-strings
			//Vector<String> vAufrufStrings = bibALL.TrenneZeile(cOpenModules, "|");
			this.vAufrufStrings.addAll(bibALL.TrenneZeile(cOpenModules, "|"));
			
			int iAnzahlOffen = this.vAufrufStrings.size();
			for (int i=0;i<iAnzahlOffen;i++) {
				if (S.isFull(vAufrufStrings.get(i))) {
					try	{
						bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().add_TabModule(vAufrufStrings.get(i),(i==(iAnzahlOffen-1)));
					} catch (myException ex) {
						ex.printStackTrace();
						bibMSG.add_MESSAGE(ex.get_ErrorMessage());
					} catch (Exception eex) {
						eex.printStackTrace();
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(eex.getLocalizedMessage(),false)));
					}
				}
			}
			//falls ladefehler auftreten, hier gesondert anzeigen
			if (bibMSG.get_bHasAlarms()) {
				new MessageAgent_InPOPUP(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION()).show_Messages();
			}
		}
	}

	//2012-05-24: fortschrittsanzeige bei den aufruf-strings
	public void load_old_modulesWithFortschrittsAnzeige(Button oButtonStart)  throws myException
	{
		String cOpenModules = (String)this.get_Settings("--");
		
		this.oGridAussen.add(new MyE2_Label(new MyE2_String("Ich lade die gespeicherten Module ...")),2,E2_INSETS.I_2_2_2_10);
		this.oGridAussen.add(new MyE2_Label(new MyE2_String("Modul: ")),1,E2_INSETS.I_2_2_2_2);
		this.oGridAussen.add(this.oGridFuerAnzeige,1,E2_INSETS.I_2_2_2_2);
		
		if (S.isFull(cOpenModules))
		{
			this.vAufrufStrings.addAll(bibALL.TrenneZeile(cOpenModules, "|"));
			
			//pro-forma einen last-action-event setzen, damit container geladen werden kann
			bibE2.set_LAST_ACTIONEVENT(new MyActionEvent(new ActionEvent(oButtonStart, "")));
			
			new ownFortschrittsanzeige();
		}
	}
	
	
	
	
	
	
	@Override
	protected String get_OBJECT_TO_STRING(Object setting) throws myException
	{
		E2_TabbedPaneForFirstContainer  oTabbedPane = bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION();
		
		String cRueck = "|";
		
		for (int i=0;i<oTabbedPane.get_vTabbs().size();i++)
		{
			cRueck+= oTabbedPane.get_vTabbs().get(i).get_cKompletterAufrufString()+"|";
		}
		
		return cRueck;
	}

	@Override
	protected Object get_STRING_TO_OBJECT(String databaseSetting)  throws myException
	{
		return databaseSetting;
	}

	@Override
	public String get_SessionHash()
	{
		return ENUM_USER_SAVEKEY.SESSION_HASH_USER_OPENMODULES.name();
	}

	
	//2012-05-24: ServerPushMessageContainer zur fortschrittsanzeige fuer die geladenen module
	private class ownFortschrittsanzeige extends E2_ServerPushMessageContainer
	{

		public ownFortschrittsanzeige()
		{
			super(new Extent(350), new Extent(80), null, false, true, false, 1000);
			this.set_bVisible_Row_For_Messages(false);
			
			this.add(E2_UserSettingLastModuleOpen.this.oGridAussen, E2_INSETS.I_2_2_2_2);
		}

		@Override
		public void Run_Loop() throws myException
		{
			
			Font  oFontBold = new E2_FontBoldItalic();
			
			int iAnzahlOffen = E2_UserSettingLastModuleOpen.this.vAufrufStrings.size();
			for (int i=0;i<iAnzahlOffen;i++)
			{
				if (S.isFull(E2_UserSettingLastModuleOpen.this.vAufrufStrings.get(i)))
				{
					try
					{
						String cBeschriftung =  new String(E2_UserSettingLastModuleOpen.this.vAufrufStrings.get(i));
						cBeschriftung = cBeschriftung.substring(cBeschriftung.indexOf("@@@@@")+5);

						
						E2_UserSettingLastModuleOpen.this.oGridFuerAnzeige.removeAll();
						E2_UserSettingLastModuleOpen.this.oGridFuerAnzeige.add(new MyE2_Label(cBeschriftung,oFontBold), E2_INSETS.I_0_0_0_0);
						
						bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().add_TabModule(E2_UserSettingLastModuleOpen.this.vAufrufStrings.get(i),(i==(iAnzahlOffen-1)));
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
		}

		@Override
		public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
		{
			
			
			
			oWindowPane.setTitle("");
			oWindowPane.setCloseIcon(null);
			oWindowPane.setTitleHeight(new Extent(0));
			oWindowPane.setMovable(false);
			oWindowPane.setResizable(false);
			
			FillImageBorder border = new FillImageBorder();
			
			border.setBorderInsets(new Insets(20));
			border.setContentInsets(new Insets(4,4,10,10));
			border.setFillImage(FillImageBorder.TOP_LEFT, new FillImage(E2_ResourceIcon.get_RI("bordertopleft.png")));
			border.setFillImage(FillImageBorder.TOP, new FillImage(E2_ResourceIcon.get_RI("bordertop.png")));
			border.setFillImage(FillImageBorder.TOP_RIGHT, new FillImage(E2_ResourceIcon.get_RI("bordertopright.png")));
			border.setFillImage(FillImageBorder.LEFT, new FillImage(E2_ResourceIcon.get_RI("borderleft.png")));
			border.setFillImage(FillImageBorder.RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderright.png")));
			border.setFillImage(FillImageBorder.BOTTOM_LEFT, new FillImage(E2_ResourceIcon.get_RI("borderbottomleft.png")));
			border.setFillImage(FillImageBorder.BOTTOM, new FillImage(E2_ResourceIcon.get_RI("borderbottom.png")));
			border.setFillImage(FillImageBorder.BOTTOM_RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderbottomright.png")));
			
			oWindowPane.setBorder(border);

			oWindowPane.setBackground(new E2_ColorBase());
			
			
			
			
			
		}
		
	}
	
}

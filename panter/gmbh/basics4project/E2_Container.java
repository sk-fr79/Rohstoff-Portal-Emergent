package panter.gmbh.basics4project;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import echopointng.KeyStrokeListener;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.ContentPane;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.PasswordField;
import nextapp.echo2.app.SplitPane;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.Window;
import nextapp.echo2.app.WindowPane;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.app.layout.RowLayoutData;
import nextapp.echo2.app.layout.SplitPaneLayoutData;
import nextapp.echo2.webcontainer.WebContainerServlet;
import nextapp.echo2.webcontainer.command.BrowserRedirectCommand;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.LoadLogo;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgentOpenURL;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.Container.E2_FillImageBorder;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.ServerPush.E2_ApplicationInstanceWithServerPush;
import panter.gmbh.Echo2.UserSettings.E2_UserSettingLastModuleOpen;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.TRIGGER_Operator;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Email_Handler;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MessagesDaemon;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.REMINDERS.REMINDER_Handler_Base;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.REMINDERS.REMINDER_Handler_Firmenstamm_Info;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.REMINDERS.REMINDER_Handler_Kreditversicherung_Ablauf;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.REMINDERS.REMINDER_Handler_Laufzettel_Eintrag;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.REMINDER_List_Executer;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR.ContainerAddon_TerminUndTodos_Workflow;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.HAD_Button;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2.HELP2__Button;
import panter.gmbh.Echo2.components.E2_CenteredImageLabel;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid_100_percent;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_MANDANT;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_INTERNET_BOOKMARK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_INTERNET_BOOKMARK;
import panter.gmbh.basics4project.menue.ProjectMenueBar;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/*
 * container-servlet, in das die anwendungen, die aus echo1 raus gestartet werden, eingeblendet werden
 */
public class E2_Container extends WebContainerServlet
{

	public static int PIXELWIDHT_LOGIN_WINDOW = 400;
	public static int PIXELHEIGHT_LOGIN_WINDOW = 230;
	
	
	public ApplicationInstance newApplicationInstance()
	{
		E2_ContainerApp oApp = new E2_ContainerApp();

		return oApp;
		
	}

	
	public class E2_ContainerApp extends E2_ApplicationInstanceWithServerPush      //NEU_09  alle application-instanzen fuer server-push vorbereitet
	{ 
		private 	Window 				oWindow = null;
		private 	TextField 			oTFUser = new TextField();
		private 	PasswordField 		oTFPass = new PasswordField();
		private     CheckBox            oCB_Restort_Stored_Modules = new CheckBox("Gespeicherte Sitzung laden / Load stored Session");

		private   	Button  			oButtStart = new Button("OK");

		public Window init() 
		{ 
			this.oWindow = new Window(); 
			this.oWindow.setTitle("Rohstoff-Portal");
			this.fillWindowForLogin(false,"");
			return oWindow; 
		}
		
		
		//neue version 2016-08-16
		@SuppressWarnings("unchecked")
		private void fillWindowForLogin(boolean bMitFehlerAnzeige, String cErrorString) {
			
			// fuer debuggen den benutzername und das passwort uebernehmen 
			File oFileDebug=new File(bibE2.get_ApplicationPath()+File.separator+"debug.conf");
			HashMap<String, String> hmInfos = new HashMap<String, String>();
			if (oFileDebug.exists())
			{
				try
				{
					List<String> Zeilen = FileUtils.readLines(oFileDebug,"UTF8");
					for (String zeile: Zeilen)
					{
						String cTest = zeile;
						Vector<String> vHelp = bibALL.TrenneZeile(cTest, ":");
						
						if (vHelp.size()==2)
						{
							hmInfos.put(vHelp.get(0), vHelp.get(1));
						}
					}
					
					if (hmInfos.containsKey("NAME"))
						this.oTFUser.setText(hmInfos.get("NAME"));

					if (hmInfos.containsKey("PASSWORD"))
						this.oTFPass.setText(hmInfos.get("PASSWORD"));
					
				}
				catch (Exception ex)
				{
				}
			}
			
		
			String cLastName = bibE2.get_Cookie("LAST_USER_LOGIN");
			
			this.oCB_Restort_Stored_Modules.setSelected(true);
			this.oCB_Restort_Stored_Modules.setFont(new E2_FontPlain()._fsa(-2));
			
			ContentPane oLIPane = new ContentPane();
			oLIPane.setBackground(Color.BLACK);
			this.oWindow.removeAll();
			this.oWindow.add(oLIPane);
			
			loginWindowPane oLoginPop = new loginWindowPane();
			
			oLoginPop.setBackground(Color.LIGHTGRAY);
			
			ContentPane oPopupPane = new ContentPane();
			
			oTFUser.setWidth(new Extent(200));
			oTFPass.setWidth(new Extent(200));

			oButtStart.setBorder(new Border(new Extent(1),Color.BLACK,1));
			oButtStart.setInsets(E2_INSETS.I(4,2,2,2));
			oButtStart.setPressedBorder(new Border(new Extent(1),Color.WHITE,1));
			oButtStart.setPressedEnabled(true);
			
			oButtStart.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent arg0)
												{
													E2_ContainerApp.this.fillWindow();
												}
											});
			
			KeyStrokeListener  oKL = new KeyStrokeListener();
			oKL.addKeyCombination(KeyStrokeListener.VK_RETURN);
			oKL.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent arg0)
												{
													E2_ContainerApp.this.fillWindow();
												}
											});
			
			
			E2_Grid oGrid = new E2_Grid()._setSize(200,200);
			
			
			oGrid
				._a(new RB_lab("Bitte melden Sie sich an ... / Please sign in ...")._fsa(-2)._b(), new RB_gld()._ins(10, 10, 10, 5)._left_mid()._span(2))
				
				._a(new RB_lab("Benutzer/User")._fsa(-2), new RB_gld()._ins(10, 5, 10, 5)._left_mid())
				._a(oTFUser, new RB_gld()._ins(10, 5, 10, 5)._left_mid())
				
				._a(new RB_lab("Passwort/Password")._fsa(-2), new RB_gld()._ins(10, 5, 10, 10)._left_mid())
				._a(oTFPass, new RB_gld()._ins(10, 5, 10, 10)._left_mid())
				
				._a(oCB_Restort_Stored_Modules, new RB_gld()._ins(10, 5, 10, 5)._span(2)._left_mid())
				
				._a(oButtStart, new RB_gld()._ins(10, 5, 10, 5)._span(1)._left_bottom())
				._a(new RB_lab()._t("Version "+VERSION.Version+" / "+VERSION.BuildNumber )._fsa(-4), new RB_gld()._ins(10, 5, 10, 5)._span(1)._right_bottom())
				
				._a(oKL, new RB_gld()._ins(1, 1, 1, 1)._left_mid()._span(2))
				  ;
			
			//falls fehlermeldung, diese einblenden
			if (! bibALL.isEmpty(cErrorString)) {
				E2_Grid g_error = new E2_Grid()._bc(Color.RED)._bo_col(Color.BLACK)._w100()._s(1);
				g_error._a(new RB_lab()._t("Anmeldung nicht möglich / Login refused"), new RB_gld()._ins(2)._left_mid());
				g_error._a(new RB_lab()._t(cErrorString), new RB_gld()._ins(2, 2, 2, 2)._left_mid());
				
				
				oGrid._a(g_error, new RB_gld()._ins(8)._span(2)._left_mid());
				int diff_height=60;
				if (S.NN(cErrorString).length()>100) {
					diff_height=150;
				}
				oLoginPop.setHeight(new Extent(E2_Container.PIXELHEIGHT_LOGIN_WINDOW+diff_height));
			}
			
			oPopupPane.add(oGrid);

			oLoginPop.add(oPopupPane);
		
			oLIPane.add(oLoginPop);
			
			ApplicationInstance.getActive().setFocusedComponent(oTFUser);

			if (cLastName!=null && S.isFull(cLastName))    //falls das cookie den zuletzt eingeloggten namen gefunden hat, diesen setzen und auf das passwort springen
			{
				//falls von der debug.conf ein wert gesetzt war, diesen vergleichen und ggf. das passwort leermachen
				if (!cLastName.equals(oTFUser.getText()))
				{
					oTFPass.setText("");
				}
				oTFUser.setText(cLastName);
				ApplicationInstance.getActive().setFocusedComponent(oTFPass);
			}
			
			
		}
		
		//vor neue version 2016-08-16
//		@SuppressWarnings("unchecked")
//		private void fillWindowForLogin(boolean bMitFehlerAnzeige, String cErrorString)
//		{
//			
//			// fuer debuggen den benutzername und das passwort uebernehmen 
//			File oFileDebug=new File(bibE2.get_ApplicationPath()+File.separator+"debug.conf");
//			HashMap<String, String> hmInfos = new HashMap<String, String>();
//			if (oFileDebug.exists())
//			{
//				try
//				{
//					List<String> Zeilen = FileUtils.readLines(oFileDebug,"UTF8");
//					for (String zeile: Zeilen)
//					{
//						String cTest = zeile;
//						Vector<String> vHelp = bibALL.TrenneZeile(cTest, ":");
//						
//						if (vHelp.size()==2)
//						{
//							hmInfos.put(vHelp.get(0), vHelp.get(1));
//						}
//					}
//					
//					if (hmInfos.containsKey("NAME"))
//						this.oTFUser.setText(hmInfos.get("NAME"));
//
//					if (hmInfos.containsKey("PASSWORD"))
//						this.oTFPass.setText(hmInfos.get("PASSWORD"));
//					
//				}
//				catch (Exception ex)
//				{
//				}
//			}
//			
//		
//			String cLastName = bibE2.get_Cookie("LAST_USER_LOGIN");
//			
//			this.oCB_Restort_Stored_Modules.setSelected(true);
//			
//			ContentPane oLIPane = new ContentPane();
//			oLIPane.setBackground(Color.BLACK);
//			this.oWindow.removeAll();
//			this.oWindow.add(oLIPane);
//			
//			loginWindowPane oLoginPop = new loginWindowPane();
//			
//			oLoginPop.setBackground(Color.LIGHTGRAY);
//			
//			ContentPane oPopupPane = new ContentPane();
//			
//			Label 				oLabUser = new Label("Benutzer/User");
//			Label 				oLabPass = new Label("Passwort/Password");
//			
//			oTFUser.setWidth(new Extent(200));
//			oTFPass.setWidth(new Extent(200));
//
//		//	Button  oButtStart = new Button("OK");
//			oButtStart.setBorder(new Border(new Extent(1),Color.BLACK,1));
//		
//			Label  oLabFehler = new Label("Anmeldung nicht möglich !");
//			oLabFehler.setBackground(Color.RED);
//			oLabFehler.setVisible(bMitFehlerAnzeige);
//			
//			
//			GridLayoutData oGLUser = new GridLayoutData();
//			oGLUser.setInsets(new Insets(10,20,10,0));
//			oTFUser.setLayoutData(oGLUser);
//			oLabUser.setLayoutData(oGLUser);
//			
//
//			GridLayoutData oGLPass = new GridLayoutData();
//			oGLPass.setInsets(new Insets(10,10,10,0));
//			oTFPass.setLayoutData(oGLPass);
//			oLabPass.setLayoutData(oGLPass);
//
//			GridLayoutData oGLButt = new GridLayoutData();
//			oGLButt.setInsets(new Insets(10,20,10,0));
//			oButtStart.setLayoutData(oGLButt);
//			oLabFehler.setLayoutData(oGLButt);
//			
//			oButtStart.addActionListener(new ActionListener()
//											{
//												public void actionPerformed(ActionEvent arg0)
//												{
//													E2_ContainerApp.this.fillWindow();
//												}
//											});
//			
//			KeyStrokeListener  oKL = new KeyStrokeListener();
//			oKL.addKeyCombination(KeyStrokeListener.VK_RETURN);
//			oKL.addActionListener(new ActionListener()
//											{
//												public void actionPerformed(ActionEvent arg0)
//												{
//													E2_ContainerApp.this.fillWindow();
//												}
//											});
//			
//			
//			Grid oGrid = new Grid(2);
//			
//			//oGrid.setInsets(new Insets(10,1,1,1));
//			
//			
//			oGrid.add(oLabUser);
//			oGrid.add(oTFUser);
//			oGrid.add(oLabPass);
//			oGrid.add(oTFPass);
//			
//			GridLayoutData   oLayout = new GridLayoutData();
//			oLayout.setColumnSpan(2);
//			oLayout.setInsets(new Insets(10,20,10,0));
//			oCB_Restort_Stored_Modules.setLayoutData(oLayout);
//			oGrid.add(oCB_Restort_Stored_Modules);
//			
//			oGrid.add(oButtStart);
//			oGrid.add(oLabFehler);
//			oGrid.add(oKL);
//			
//			//falls fehlermeldung, diese einblenden
//			if (! bibALL.isEmpty(cErrorString))
//			{
//				Label oLabFehlerMeldung = new Label(cErrorString);
//				oLabFehlerMeldung.setBackground(Color.RED);
//				oLabFehlerMeldung.setLineWrap(true);
//				oLabFehlerMeldung.setLayoutData(oGLPass);
//				oLoginPop.setHeight(new Extent(E2_Container.PIXELHEIGHT_LOGIN_WINDOW+140));
//				oGrid.add(new Label(" "));
//				oGrid.add(oLabFehlerMeldung);
//			}
//			
//			oPopupPane.add(oGrid);
//
//			oLoginPop.add(oPopupPane);
//		
//			oLIPane.add(oLoginPop);
//			
//			ApplicationInstance.getActive().setFocusedComponent(oTFUser);
//
//			if (cLastName!=null && S.isFull(cLastName))    //falls das cookie den zuletzt eingeloggten namen gefunden hat, diesen setzen und auf das passwort springen
//			{
//				//falls von der debug.conf ein wert gesetzt war, diesen vergleichen und ggf. das passwort leermachen
//				if (!cLastName.equals(oTFUser.getText()))
//				{
//					oTFPass.setText("");
//				}
//				oTFUser.setText(cLastName);
//				ApplicationInstance.getActive().setFocusedComponent(oTFPass);
//			}
//			
//			
//		}
		
		
		
		//hier wird der startbildschirm aufgebaut
		private void fillWindow()
		{
			
			//hier wird jetzt die session mit den noetigen infos ausgestattet
			createSession oSES_Cr = null;
			try
			{
				oSES_Cr = new createSession(
						   E2_ContainerApp.this.oTFUser.getText().trim(), 
						   E2_ContainerApp.this.oTFPass.getText().trim(), 
						   bibE2.get_CurrSession(), 
						   getServletContext(), 
						   getActiveConnection().getRequest());
				
			       if (! oSES_Cr.getSessionOK())
				   {
			    	   E2_ContainerApp.this.fillWindowForLogin(true,oSES_Cr.getErrorMessage());
				   }
			       else
			       {
//			    	   DEBUG.System_println("--sonderfelder--");
//			    	   DEBUG.System_print(DB_STATICS.get_vSonderFelder());
//			    	   DEBUG.System_println("--sonderfelder--");
			    	   
//			    	   new TP_KST_Selector_SELECT(true);
//			    	   new TP_KST_Selector_SELECT(true);
			    	   
			    	   
			    	   
			    	   	bibE2.set_Cookie("LAST_USER_LOGIN", E2_ContainerApp.this.oTFUser.getText());
			    	   
			    	    this.oWindow.removeAll();
			    	    
						SplitPane oSPHorizontal = new SplitPane(SplitPane.ORIENTATION_HORIZONTAL, new Extent(150));
						SplitPane oSPVertical   = new SplitPane(SplitPane.ORIENTATION_VERTICAL, new Extent(30));

						E2_ContentPane  oContentPaneBEGINNING = 	new E2_ContentPane(true);

						oSPVertical.setSeparatorColor(Color.DARKGRAY);
						oSPVertical.setSeparatorHeight(new Extent(1));
						
						E2_ContentPane  oBasePane = new E2_ContentPane(true);
						oBasePane.add(oSPVertical);
						oSPVertical.add(oSPHorizontal);
						oSPVertical.add(oContentPaneBEGINNING);
						
						oSPVertical.setResizable(true);
						
						oSPHorizontal.setBackground(new E2_ColorDDark());
			
						//20101008: Statt basecolumn ein grid mit 100% extent als basis
						GridLayoutData  BaseLayout = new GridLayoutData();
						BaseLayout.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
						BaseLayout.setInsets(new Insets(4,4,2,0));
						
						//MyE2_Column 	oBaseColumnBEGINNING =		new MyE2_Column();;
						MyE2_Grid_100_percent 	oBaseColumnBEGINNING =		new MyE2_Grid_100_percent(1,0);
						//MyE2_Row				oRowTitelLeft	=			new MyE2_Row();
			
						oContentPaneBEGINNING.add(oBaseColumnBEGINNING);
			
						E2_TabbedPaneForFirstContainer oE2_TabbedPane4Container = new E2_TabbedPaneForFirstContainer(oContentPaneBEGINNING);
						oE2_TabbedPane4Container.setLayoutData(BaseLayout);
						
						
						if (S.isFull(bibALL.get_RECORD_MANDANT().get_LOGONAME_cUF_NN("")))
						{
							if (new File(LoadLogo.get_CompleteLogoPath()).exists())
							{
								LoadLogo  ll = new LoadLogo();
								if (ll.get_ImageRef() != null)
								{
									oE2_TabbedPane4Container.set_oComponentToShowIfContainerIsHidden(new E2_CenteredImageLabel(ll.get_ImageRef()));
								}
							}
						}
						

						//oBaseColumnBEGINNING.add(oRowTitelLeft);
						oBaseColumnBEGINNING.add(oE2_TabbedPane4Container);
						if (oE2_TabbedPane4Container.get_oComponentToShowIfContainerIsHidden()!=null)
						{
							oBaseColumnBEGINNING.add(oE2_TabbedPane4Container.get_oComponentToShowIfContainerIsHidden());
						}
						
						oE2_TabbedPane4Container.setVisible(false);
			
						/*
						 * jetzt den globalen messageagent definieren
						 */
					    bibE2.SET_FIRST_CONTENTPANE_IN_SESSION(oBasePane);
					    bibE2.STORE_PROGRAMM_TABBEDPANE_IN_SESSION(oE2_TabbedPane4Container);
					    
					    this.oWindow.setContent(oBasePane);

					    ProjectMenueBar oMenueBar = new ProjectMenueBar();

					    SplitPaneLayoutData  oLayoutData = new SplitPaneLayoutData();
					    oLayoutData.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
					    oMenueBar.setLayoutData(oLayoutData);
						oSPHorizontal.add(oMenueBar);
						
						
						//2016-09-12: timestamp wird bereits vor dem aufbau der module gesetzt weil sonst anfangsspeicherungen von usersettings in modulselektoren nicht erfolgen können
						 this.write_Stamp();
						
						
						String cUserInfo = "";
						try
						{
							BASIC_RECORD_USER oUser = new BASIC_RECORD_USER(bibALL.get_ID_USER());
							cUserInfo = oUser.get_VORNAME_cF_NN("")+" "+oUser.get_NAME1_cF_NN("")+" ("+oUser.get_NAME_cF_NN("");
							
							BASIC_RECORD_MANDANT oMandant = new BASIC_RECORD_MANDANT(bibALL.get_ID_MANDANT());
							String cHelp = oMandant.get_KURZNAME_cF_NN("");
							if (S.isEmpty(cHelp)) cHelp=oMandant.get_NAME1_cUF_NN("")+" "+oMandant.get_NAME2_cUF_NN("");
							
							cUserInfo+="@"+cHelp+")";

							cUserInfo += "  [Version: "+VERSION.Version+"]"+(bibALL.get_bDebugMode()?"/D":"");
							
							//die letzten module wieder laden
							if (this.oCB_Restort_Stored_Modules.isSelected())
							{
								// 2012-05-24: module laden mit fortschrittsanzeige
								// new E2_UserSettingLastModuleOpen().load_old_modules();
								//new E2_UserSettingLastModuleOpen().load_old_modulesWithFortschrittsAnzeige(E2_ContainerApp.this.oButtStart);
								// 2015-04-20: module laden ohne fortschrittsanzeige, aber leer (lazyload)
								new E2_UserSettingLastModuleOpen().load_old_modules();
							}
							
							
							
						} 
						catch (myException e)
						{
							e.printStackTrace();
						}

						RowLayoutData oRowLayoutRight = new RowLayoutData();
						oRowLayoutRight.setWidth(new Extent(99,Extent.PERCENT));
						oRowLayoutRight.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));
						oRowLayoutRight.setInsets(E2_INSETS.I_5_0_5_0);

						RowLayoutData oRowLayoutLeft = new RowLayoutData();
						oRowLayoutLeft.setAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
						oRowLayoutLeft.setInsets(E2_INSETS.I_5_0_5_0);
						
						MyE2_Row oRowHelp = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
						
						//2011-12-02: globaler help-button
						//oRowHelp.add(new HELP_BUTTON_FOR_MODULES(),oRowLayoutLeft);
						//2015-10-08: neues hilfemodul
						//oRowHelp.add(new HAD_Button(),oRowLayoutLeft);
						//20180905: neuer hilfebutton
						oRowHelp.add(new HELP2__Button(null),oRowLayoutLeft);

						oRowHelp.add(new CloseButton(),oRowLayoutLeft);
						
						ContainerAddon_TerminUndTodos_Workflow  oContainer = new ContainerAddon_TerminUndTodos_Workflow();
						
						//jetzt die refreshables definieren
						bibE2.get_vRefreshables().add(oContainer);
						bibE2.get_vRefreshables().add(new MessagesDaemon());
						//2017-01-03: die logtrigger muessen ausgelesen werden
						bibE2.get_vRefreshables().add(new TRIGGER_Operator());
						
						oRowHelp.add(oContainer,oRowLayoutLeft);
						
					    if (S.isFull(bibALL.get_cTitelHinweis()))
					    {
					        oRowHelp.add(new MyE2_Label(new MyE2_String(bibALL.get_cTitelHinweis(),false), MyE2_Label.STYLE_ERROR_BIG()), new Insets(50,1,1,1));
					    }

					   //2012-01-17: pruefen, ob internet-buttons rausgelegt werden muessen
				       try
						{
				    	   RECLIST_INTERNET_BOOKMARK recList_WWW = new RECLIST_INTERNET_BOOKMARK("SELECT * FROM "+bibE2.cTO()+".JT_INTERNET_BOOKMARK ORDER BY "+RECORD_INTERNET_BOOKMARK.FIELD__TITEL_MENUE);

				        	//	this.recList_WWW = new RECLIST_INTERNET_BOOKMARK("SELECT * FROM JT_INTERNET_BOOKMARK");
							if (recList_WWW.get_vKeyValues().size()>0)
							{
								for (int i=0;i<recList_WWW.get_vKeyValues().size();i++)
								{
									RECORD_INTERNET_BOOKMARK  recIB = recList_WWW.get(i);
									if (recIB.is_ZEIGE_IN_TITELZEILE_YES())
									{
										oRowHelp.add(new ownInternetButton(recIB), E2_INSETS.I_5_0_5_0);
									}
								}
								
							}
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}

					    
					    

						MyE2_Label oLabName = new MyE2_Label(cUserInfo, MyE2_Label.STYLE_SMALL_PLAIN());
						oRowHelp.add(oLabName, oRowLayoutRight);
						
						oSPHorizontal.add(oRowHelp);
						
						
						// Timestamp schreiben, damit geschrieben werden kann.
						// 2016-10-12: wird vor den aufbau der alten module gesetzt wegen speichervorgaengen von usersettings in diversen selektoren:    this.write_Stamp();
						
						
						// Wiedervorlage prüfen und ggf. eintragen
						try {
							REMINDER_Handler_Base oReminder = null; 
							
							// Prüfung auf Meldung der Firmenstamm-Infos
							oReminder = new REMINDER_Handler_Firmenstamm_Info(bibALL.get_ID_USER());
							oReminder.updateReminders();

//							oReminder.updateReminders(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST);
//							oReminder.updateReminders(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK_INFOLISTE);
							
							// Prüfung auf den Kreditversicherungs-Ablauf
							oReminder = new REMINDER_Handler_Kreditversicherung_Ablauf();
							oReminder.updateReminders();
							
							// Prüfung auf Meldung der Laufzettel-Einträge
							oReminder = new REMINDER_Handler_Laufzettel_Eintrag();
							oReminder.updateReminders();
							
							// alle offenen Emails senden
							new MESSAGE_Email_Handler().sendAllOpenMails(); 
							
						} catch (Exception e) {
							// ??
						}
						
						// 2016-04-08 NEUE REMINDER-Struktur
						// REMINDER prüfen und ausstehende Nachrichten des Mandanten generieren / verschicken
						//
						try{
							REMINDER_List_Executer oReminderExecuter = new REMINDER_List_Executer();
							oReminderExecuter.doRemindAllOpenReminders();
						} catch(Exception e){
							// ??
						}

			       }	
			} 
			catch (myException e1)
			{
				e1.printStackTrace();
				E2_ContainerApp.this.fillWindowForLogin(true,e1.getMessage());
			}

		}
		
		
		private class ownInternetButton extends MyE2_Button
		{
			private RECORD_INTERNET_BOOKMARK  recIB = null;

			public ownInternetButton(RECORD_INTERNET_BOOKMARK rec_IB) throws myException
			{
				super(rec_IB.get_TITEL_BUTTON_cUF_NN("<WWW>"));
				this.setStyle(MyE2_Button.StyleTextButtonSTD(new E2_FontBold(0)));
				this.recIB = rec_IB;
				this.add_oActionAgent(new ActionAgentOpenURL(this.recIB.get_TITEL_MENUE_cUF_NN("<Menüaufruf>"), this.recIB.get_URL_cUF_NN(""), true));
				this.setToolTipText(new MyE2_String("Öffnet ein Browser-Fenster zur Adresse: ",true,this.recIB.get_URL_cUF_NN(""),false).CTrans());
				
			}
		}
		

		
		
		
		private void write_Stamp() throws myException
		{
			String cStamp = bibDB.EinzelAbfrage("SELECT "+DB_META.ORA_TIMESTAMP_MILLISECS+" FROM DUAL");
			if (cStamp.startsWith("2"))                 //sicherheitsabfrage, gilt bis ins Jahr 2999, dann beginnt der stamp mit 3
			{
				bibE2.set_LAST_DB_TIMESTAMP(cStamp);
				DEBUG.System_println("DB-Timestamp geschrieben:   ...: "+cStamp+"  <E2_Container>", DEBUG.DEBUG_FLAG_SQL_TIMESTAMP);
			}
			else
			{
				throw new myException(this,"Error with Timestamp !!");
			}
		}
		
		
	}
	
	
	
	
	private class CloseButton extends MyE2_Button
	{

		public CloseButton() 
		{
			super(E2_ResourceIcon.get_RI("windowclosebig.png"));
			this.setToolTipText(new MyE2_String("Programm beenden ...").CTrans());
			
			this.add_oActionAgent(new XX_ActionAgent()
			{

				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					//die tabs sichern
					try
					{
						new E2_UserSettingLastModuleOpen().store_ModuleList();
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
					
					
					ApplicationInstance.getActive().enqueueCommand(new BrowserRedirectCommand("start"));
					
				}
				
			});
		}
		
	}
	
	
	private class loginWindowPane extends WindowPane
	{
		
		public loginWindowPane()
		{
			super();
			
			MutableStyle oStyle = new MutableStyle();
			oStyle.setProperty( WindowPane.PROPERTY_BACKGROUND, Color.LIGHTGRAY);
			oStyle.setProperty( WindowPane.PROPERTY_TITLE_BACKGROUND, Color.DARKGRAY);
			oStyle.setProperty( WindowPane.PROPERTY_ICON_INSETS, new Insets(0,0,0,0));
			oStyle.setProperty( WindowPane.PROPERTY_BORDER, new E2_FillImageBorder(Color.BLACK,new Insets(1),new Insets(1)));
			oStyle.setProperty( WindowPane.PROPERTY_TITLE_HEIGHT,new Extent(25));
			oStyle.setProperty( WindowPane.PROPERTY_TITLE_INSETS,new Insets(7,3,0,0));
			oStyle.setProperty( WindowPane.PROPERTY_TITLE_FONT, new Font(Font.ARIAL,Font.ITALIC,new Extent(12,Extent.PT)));
			oStyle.setProperty( WindowPane.PROPERTY_TITLE_FOREGROUND, Color.WHITE);

			this.setClosable(false);
			this.setWidth(new Extent(E2_Container.PIXELWIDHT_LOGIN_WINDOW));
			this.setHeight(new Extent(E2_Container.PIXELHEIGHT_LOGIN_WINDOW));
			
			this.setDefaultCloseOperation(WindowPane.DISPOSE_ON_CLOSE);
			this.setStyle(oStyle);
			//this.setTitle("Anmeldung / Login");
			this.setTitle(VERSION.NamePortal);
			this.setTitleFont(new E2_Font(Font.PLAIN)._fsa(-2)._b());
			this.setModal(true);
		}
	}
	
	
	
	
	
	
}

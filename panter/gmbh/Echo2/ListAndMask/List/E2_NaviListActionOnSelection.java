/**
 * panter.gmbh.Echo2.ListAndMask.List
 * @author martin
 * @date 08.07.2019
 * 
 */
package panter.gmbh.Echo2.ListAndMask.List;

import java.util.stream.Stream;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.RadioButtonCheckboxAction;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_V2;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 08.07.2019
 * allgemeine aktion, die zur auswahl einer menge daten aus einer navilist auffordert und daran dann eine aktion ausfuehrt
 */
public abstract class E2_NaviListActionOnSelection extends XX_ActionAgent {

	
	private E2_NavigationList  			naviList = null;
	
	private MyE2_String  				titelInPopup = S.ms("Bitte wählen Sie");
	private MyE2_String  				titelOfPopupWindow = S.ms("Auswahl");
	private MyE2_String  				titelOfProgressWindow = S.ms("Fortschritt ...");
	
	private RB_cb  						cbAllInListFilter = 					new RB_cb()._t(S.ms("Alle aus dem aktuellen Filter"));
	private RB_cb						cbAllInActualPage = 				new RB_cb()._t(S.ms("Alle aus der aktuellen Seite"));
	private RB_cb						cbSelected = 						new RB_cb()._t(S.ms("Alle ausgewählten"));

	private RadioButtonCheckboxAction 	radioFunktion = 					null;
	private E2_BasicModuleContainer 	popup = 							null;
	
	private E2_Button           		btOK = 								new E2_Button()._t(S.ms("OK"))._aaa(new ActionAgentDoSomethingWithSelection())._setShapeStandardTextButton();
	private E2_Button        			btCancel = 							new E2_Button()._t(S.ms("Abbruch"))._aaa(()->{popup.CLOSE_AND_DESTROY_POPUPWINDOW(true);})._setShapeStandardTextButton();

	private int  						grenzeBisFortschrittsanzeige = 15;
	
	private int  						widthOfPopup = 480;
	private int  						heightOfPopup = 280;
	
	private int  						widthOfProgressPopup = 500;
	private int  						heightOfProgressPopup = 280;
	
	
	//kann leer bleiben
	public abstract void setShapeOfSelectPopup(E2_BasicModuleContainer 	popup );
	
	//hier wird dann das ausgefuehrt was eigentlich sinn der sache ist
	public abstract void doSomethingBeforeLoop() throws myException;
	public abstract void doSomethingWithId(String idUnformatedRowId) throws myException;
	public abstract void doSomethingAfterLoop() throws myException;
	
	//finale jobe werden immer ausgefuehrt, auch wenn eine exception alles unterbricht
	public abstract void doFinaleTasks();
	
	public abstract void setProgressWindowPaneLookAndFeel(MyE2_WindowPane pane);
	
	//hier wird definiert, was das grid in der fortschrittsanzeige anzeigen soll:
	public abstract void refreshFortschrittsAnzeige(E2_Grid g, int iCount, int iGesamt);
	/*
	  public void refreshFortschrittsAnzeige(E2_Grid g, int iCount, int iGesamt) {
	     g._clear()._setSize(200,20,200)._a(new RB_lab()._t("Adresse "))._a(new RB_lab()._t("von"), new RB_gld()._center_mid())._a(new RB_lab()._t(""+iCounter), new RB_gld()._center_mid()));
	  }
	 */


	
	//einige schalter
	private boolean   closePopupAfterDone = false;
	private boolean   autoCloseProgressWindowAfterDone = false;
	private int       loopRefreshTimeInMilliSeconds = 2000;
	
	
	private MyE2_MessageVector   mv_sammler = bibMSG.getNewMV();
	
	
	public E2_NaviListActionOnSelection(E2_NavigationList p_naviList) throws myException {
		super();
		this.naviList = p_naviList;
		
		radioFunktion = new RadioButtonCheckboxAction(false);
		radioFunktion._addCheckBox(cbAllInActualPage)._addCheckBox(cbAllInListFilter)._addCheckBox(cbSelected);
		
	}



	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		//zuerst pruefen, ob eine aktive auswahl bestht, wenn ja, dann den schalter auf 

		Stream.of(radioFunktion.getCheckBoxes().getArray()).forEach((cb)->{	cb.setSelected(false);});
		if (naviList.get_vSelectedIDs_Unformated().size()>0) {
			cbSelected.setSelected(true);
		} else {
			cbAllInActualPage.setSelected(true);
		}

		popup = new E2_BasicModuleContainer();
		
		E2_Grid grid = new E2_Grid()
				._setSize(300,150)
				._a(new RB_lab()._t(titelInPopup)._fsa(2), new RB_gld()._span(2)._ins(5, 15, 5, 15))
				
				._a(new RB_lab()._t("Auswahl"), new RB_gld()._ins(5,5,5,5)._col_back_d())
				._a(new RB_lab()._t("aktuell"), new RB_gld()._ins(5,5,5,5)._col_back_d()._center_mid())
				
				._a(cbAllInListFilter, new RB_gld()._ins(5, 2, 5, 2))
				._a(new RB_lab()._t(""+naviList.get_vectorSegmentation().size()), new RB_gld()._ins(5, 2, 5, 2)._center_mid())
				
				._a(cbAllInActualPage, new RB_gld()._ins(5, 2, 5, 2))
				._a(new RB_lab()._t(""+naviList.get_vActualID_Segment().size()), new RB_gld()._ins(5, 2, 5, 2)._center_mid())
				
				._a(cbSelected, new RB_gld()._ins(5, 2, 5, 2))
				._a(new RB_lab()._t(""+naviList.get_vSelectedIDs_Unformated().size()), new RB_gld()._ins(5, 2, 5, 2)._center_mid())
				
				._a(new E2_Grid()._setSize(100,100)._a(btOK, new RB_gld()._ins(0, 0, 10, 0))._a(btCancel, new RB_gld()._ins(0, 0, 0, 0))
					, new RB_gld()._span(2)._ins(5,20,5,5))
				
				;
			
		popup.add(grid, E2_INSETS.I(0,0,0,0));
		
		setShapeOfSelectPopup(E2_NaviListActionOnSelection.this.popup);
		
		popup.CREATE_AND_SHOW_POPUPWINDOW(new Extent(widthOfPopup), new Extent(heightOfPopup), titelOfPopupWindow);
	
	}

	public E2_NavigationList getNaviList() {
		return naviList;
	}

	public MyE2_String getTitelInPopup() {
		return titelInPopup;
	}

	public MyE2_String getTitelOfPopupWindow() {
		return titelOfPopupWindow;
	}


	public E2_NaviListActionOnSelection _setTitelInPopup(MyE2_String p_titelInPopup) {
		this.titelInPopup = p_titelInPopup;
		return this;
	}

	public E2_NaviListActionOnSelection _setTitelOfPopupWindow(MyE2_String p_titelOfPopupWindow) {
		this.titelOfPopupWindow = p_titelOfPopupWindow;
		return this;
	}

	public E2_NaviListActionOnSelection _setTitelOfProgressWindow(MyE2_String p_titelOfProgressWindow) {
		this.titelOfProgressWindow = p_titelOfProgressWindow;
		return this;
	}

	public int getGrenzeBisFortschrittsanzeige() {
		return grenzeBisFortschrittsanzeige;
	}

	public E2_NaviListActionOnSelection _setGrenzeBisFortschrittsanzeige(int p_grenzeBisFortschrittsanzeige) {
		this.grenzeBisFortschrittsanzeige = p_grenzeBisFortschrittsanzeige;
		return this;
	}

	
	public MyE2_String getTitelOfProgressWindow() {
		return titelOfProgressWindow;
	}

	public RB_cb getCbAllInListFilter() {
		return cbAllInListFilter;
	}

	public RB_cb getCbAllInActualPage() {
		return cbAllInActualPage;
	}

	public RB_cb getCbSelected() {
		return cbSelected;
	}

	public E2_Button getBtOK() {
		return btOK;
	}

	public E2_Button getBtCancel() {
		return btCancel;
	}

	public RadioButtonCheckboxAction getRadioFunktion() {
		return radioFunktion;
	}

	public E2_BasicModuleContainer getStartPopup() {
		return popup;
	}


	public boolean isClosePopupAfterDone() {
		return closePopupAfterDone;
	}

	public E2_NaviListActionOnSelection _setClosePopupAfterDone(boolean closePopupAfterDone) {
		this.closePopupAfterDone = closePopupAfterDone;
		return this;
	}

	public boolean isAutoCloseProgressWindowAfterDone() {
		return autoCloseProgressWindowAfterDone;
	}

	public E2_NaviListActionOnSelection _setAutoCloseProgressWindowAfterDone(boolean autoCloseProgressWindowAfterDone) {
		this.autoCloseProgressWindowAfterDone = autoCloseProgressWindowAfterDone;
		return this;
	}

	public int getLoopRefreshTimeInMilliSeconds() {
		return loopRefreshTimeInMilliSeconds;
	}

	public E2_NaviListActionOnSelection _setLoopRefreshTimeInMilliSeconds(int loopRefreshTimeInMilliSeconds) {
		this.loopRefreshTimeInMilliSeconds = loopRefreshTimeInMilliSeconds;
		return this;
	}


	public int getWidthOfPopup() {
		return widthOfPopup;
	}

	public E2_NaviListActionOnSelection _setWidthOfPopup(int widthOfPopup) {
		this.widthOfPopup = widthOfPopup;
		return this;
	}

	public int getHeightOfPopup() {
		return heightOfPopup;
	}

	public E2_NaviListActionOnSelection _setHeightOfPopup(int heightOfPopup) {
		this.heightOfPopup = heightOfPopup;
		return this;
	}



	public int getWidthOfProgressPopup() {
		return widthOfProgressPopup;
	}

	public E2_NaviListActionOnSelection _setWidthOfProgressPopup(int widthOfProgressPopup) {
		this.widthOfProgressPopup = widthOfProgressPopup;
		return this;
	}

	public int getHeightOfProgressPopup() {
		return heightOfProgressPopup;
	}

	public E2_NaviListActionOnSelection _setHeightOfProgressPopup(int heightOfProgressPopup) {
		this.heightOfProgressPopup = heightOfProgressPopup;
		return this;
	}


	
	private VEK<String> getIdForOperation() throws myException {
		VEK<String> v = new VEK<>();
		
		if (cbAllInListFilter.isSelected()) {
			v._a(naviList.get_vectorSegmentation());
		} else if (cbAllInActualPage.isSelected()) {
			v._a(naviList.get_vActualID_Segment());
		} else {
			v._a(naviList.get_vSelectedIDs_Unformated());
		}
		return v;
	}
	
	
	
	//executes function with serverpush
	private class ActionAgentDoSomethingWithSelection extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			VEK<String>  ids = getIdForOperation();
			
			E2_Grid g = new E2_Grid();
			
			try {
				doSomethingBeforeLoop();
				
				if (ids.size()<grenzeBisFortschrittsanzeige) {
					for (String id: ids) {
						doSomethingWithId(id);
					}
					doSomethingAfterLoop();
					doFinaleTasks();
					if (closePopupAfterDone) {
						popup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
				} else {
					
					new E2_ServerPushMessageContainer_V2(widthOfProgressPopup,heightOfProgressPopup,titelOfProgressWindow,autoCloseProgressWindowAfterDone,true,loopRefreshTimeInMilliSeconds,g) {
						@Override
						public void Run_Loop() throws myException {
							int iCount = ids.size();
							int iCounter = 0;
							for (String id: ids) {
								doSomethingWithId(id);
								iCounter++;
								if (this.isInterupted()) {
									break;
								}
								if (this.isAllowedToUpdatePopupInfos()) {
									refreshFortschrittsAnzeige(g, iCounter, iCount);
									this.setAllowedToUpdatePopupInfos(false);
								}
							}
							refreshFortschrittsAnzeige(g, iCounter, iCount);
							doSomethingAfterLoop();
							doFinaleTasks();

							if (closePopupAfterDone) {
								popup.CLOSE_AND_DESTROY_POPUPWINDOW(true);
							}

						}

						@Override
						public void setWindowPaneLookAndFeel(MyE2_WindowPane windowPane) throws myException {
							setProgressWindowPaneLookAndFeel(windowPane);
						}
					};

					
				}
			} catch (myException e) {
				mv_sammler._addAlarm(e.getOriginalMessage());
				doFinaleTasks();
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
				doFinaleTasks();
				mv_sammler._addAlarm(e.getLocalizedMessage());

				throw new myException("Error: Code <3fede044-a220-11e9-a2a3-2a2ae2dbcce4>: "+e.getLocalizedMessage());
			}
			

		}
	}



	public MyE2_MessageVector getMvSammler() {
		return mv_sammler;
	}







	
}

package panter.gmbh.Echo2.components.DB.MaskSearchField;


import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.IF_BasicModuleContainerUseInPopupAction;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


/**
 * neuer button, der in der maskensuche als addon-button eingefuegt werden kann und 
 * dann einen komplette E2_BasicModulContainer aufruft und dort in die navilist eine spalte einfuegt, die einen 
 * button enthaelt, der das modul-popup wieder schliesst und die zeilen-id in das suchfeld uebernimmt
 * @author martin
 *
 */



public abstract class ButtonToOpenListContainerForSearch extends E2_Button {

	private MyE2_DB_MaskSearchField  							m_ownSearchField = null;
	private E2_BasicModuleContainer   							m_E2_Container = null;

	/*
	 * generateUnpopulatedBasicModuleContainer() muss einen List- BasicmoduleContainer erzeugen, der
	 * das interface IF_BasicModuleContainerUseInPopupAction implementiert (Grund: er muss komplette erzeugbar sein ohne aber mit daten gefüllt zu werden)
	 * siehe {@link #FSK_MASK_SearchBank()}
	 */
	public abstract E2_BasicModuleContainer  					generateUnpopulatedBasicModuleContainer() throws myException;
	
	/**
	 * falls die id der liste nicht die id des gesuchten datensatzes ist, dann hier ein umsetzer
	 * @param idFromPopupList
	 * @return
	 * @throws myException
	 */
	public abstract Long  										getIDForSearchField(String idFromPopupList) throws myException;
	
	/**
	 * und umgekehrt
	 * @param idFromSearchField
	 * @return
	 * @throws myException
	 */
	public abstract Long  										getIDForPopupList(String idFromSearchField) throws myException;
	
	
	public abstract MyE2_String	 								getTooltipTextForModulOpenButton();
	
	
	public ButtonToOpenListContainerForSearch(MyE2_DB_MaskSearchField ownSearchField) {
		super();
		
		this.m_ownSearchField = ownSearchField;
		
		this._image("zeileneng.png", true);
		
		this._aaa(new OwnActionCreateAndOpenPopupWithListContainer())._ttt(this.getTooltipTextForModulOpenButton());
	}


	
	private class OwnActionCreateAndOpenPopupWithListContainer extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			ButtonToOpenListContainerForSearch oThis = ButtonToOpenListContainerForSearch.this;
			
			IF_BasicModuleContainerUseInPopupAction container4Popup = null;
			
			m_E2_Container = generateUnpopulatedBasicModuleContainer(); 
			
			if (m_E2_Container instanceof IF_BasicModuleContainerUseInPopupAction) {
				container4Popup = (IF_BasicModuleContainerUseInPopupAction)m_E2_Container;
			} else {
				throw new myException(this, "Container must implement IF_BasicModuleContainerUseInPopupAction-Interface !");
			}
			
			E2_NavigationList     naviList = container4Popup.getNavigationList();
			
			//dieser navigationList-ComponentMap eine neue komponente einfuegen
			String key = container4Popup.getMapKey4ReturnButton();
			E2_ComponentMAP  map = naviList.get_oComponentMAP__REF();
			map.add_Component(key, new OwnInjectedListbuttonInPopupListContainer(), S.ms("Übernehmen"));
			
			//und an die stelle nach dem marker einfuegen
			Vector<String> v = map.get_vComponentHashKeys();
			Vector<String> v_zwischen = new Vector<>();
			for (int i=0;i<v.size();i++) { 
				if (!v.get(i).equals(key)) {
					v_zwischen.add(v.get(i));
				}
			}
			v_zwischen.add(2, key);    //die neue spalte an position 2
			v.clear();
			v.addAll(v_zwischen);      //jetzt ist die neue spalte an position 2
			
			MutableStyle 	style = (MutableStyle)naviList.get_oDataGrid().getStyle();
			String 	kenner = naviList.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE();
			
			naviList.INIT_WITH_ComponentMAP(map, style, kenner);
			
			Long lValInSearchField = null;
			if (oThis.getOwnSearchField()!=null) {
				MyLong l = new MyLong(oThis.getOwnSearchField().get_cActualMaskValue());
				if (l.isOK()) {
					lValInSearchField = l.get_oLong();
				}
			}
			
			if (lValInSearchField==null) {
				container4Popup.fillList();
			} else {
				container4Popup.fillList(oThis.getIDForPopupList(lValInSearchField.toString()));
			}

			m_E2_Container.CREATE_AND_SHOW_POPUPWINDOW(	new Extent(container4Popup.getPopupWidth()),
														new Extent(container4Popup.getPopupHeight()), 
														container4Popup.getTitle4Popup());

		}
	}

	
	private class OwnInjectedListbuttonInPopupListContainer extends E2_Button {

		public OwnInjectedListbuttonInPopupListContainer() {
			super();
			
			this._image("ok_small.png")._style(E2_Button.StyleImageButton())
				._aaa(new ActionSetValues())
				._aaa(()->{m_E2_Container.CLOSE_AND_DESTROY_POPUPWINDOW(true);})
				;
			
			this._ttt(S.ms("Diesen Wert in die Suchmaske übernehmen "));
			
			//diesen button zentriert in die liste einfuegen
			this.EXT().set_oLayout_ListElement(new RB_gld()._center_mid());
			
		}
		
		@Override
		public OwnInjectedListbuttonInPopupListContainer get_Copy(Object objHelp) throws myExceptionCopy {
			return new OwnInjectedListbuttonInPopupListContainer();
		}

		@Override
		public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		}
		
		private class ActionSetValues extends XX_ActionAgent {
			public ActionSetValues() {
				super();
			}

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				OwnInjectedListbuttonInPopupListContainer bt = (OwnInjectedListbuttonInPopupListContainer)oExecInfo.get_MyActionEvent().getSource();
				String idRow = bt.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				Long   id4Search = getIDForSearchField(idRow);
				
				if (m_ownSearchField.get_oMaskActionAfterMaskValueIsFound() != null) {
					m_ownSearchField.set_cActualMaskValue(id4Search.toString());
					m_ownSearchField.get_oMaskActionAfterMaskValueIsFound().doMaskSettingsAfterValueWrittenInMaskField(id4Search.toString(),m_ownSearchField, true, true);
				}
			}
		}
	}


	public MyE2_DB_MaskSearchField getOwnSearchField() {
		return m_ownSearchField;
	}
	public ButtonToOpenListContainerForSearch _setOwnSearchField(MyE2_DB_MaskSearchField ownSearchField) {
		this.m_ownSearchField = ownSearchField;
		return this;
	}
	
	
}

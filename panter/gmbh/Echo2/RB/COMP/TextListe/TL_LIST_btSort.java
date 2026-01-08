/**
 * panter.gmbh.Echo2.RB.COMP.TextListe
 * @author martin
 * @date 20.02.2020
 * 
 */
package panter.gmbh.Echo2.RB.COMP.TextListe;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 20.02.2020
 *
 */
public class TL_LIST_btSort extends E2_Button {

	private VEK<SortLine> sortLines = new VEK<>();

	private RB_TransportHashMap m_tpHashMap = null;;

	/**
	 * @author martin
	 * @date 20.02.2020
	 *
	 */
	public TL_LIST_btSort(RB_TransportHashMap p_tpHashMap) {
		this.m_tpHashMap = p_tpHashMap;
        this.__setImages(E2_ResourceIcon.get_RI("sort_up_down.png"), E2_ResourceIcon.get_RI("sort_up_down__.png"));
        this._aaa(new StartSortPopup());
	}

	
	
	private class StartSortPopup extends XX_ActionAgent {

		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			Long idMotherTable = m_tpHashMap.getMotherKeyValue();
			String tabName = ((EnumTableTranslator)m_tpHashMap.getSB(TL_CONST.TabTrans)).getTableNameForTextListe();
					
			
			//jetzt die positions-id vordefinieren
			SEL selRecs = new SEL(_TAB.text_liste).FROM(_TAB.text_liste)
						.WHERE(new vgl(TEXT_LISTE.tablename,tabName))
						.AND(new vgl(TEXT_LISTE.id_table,idMotherTable.toString()))
						.ORDERUP(TEXT_LISTE.position);
			
			RecList21 rlTexte = new RecList21(_TAB.text_liste)._fill(new SqlStringExtended(selRecs.s()));
			
			sortLines._clear();
			
			for (Rec21 r: rlTexte) {
				sortLines._a(new SortLine(r));
			}

			SortPopup sortPop = new SortPopup();
			sortPop.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Sortieren der Textzeilen"));
			
		}
		
	}
	
	private class SortPopup extends E2_BasicModuleContainer {

		public SortPopup() {
			super();
			
			E2_Grid gSorter = new E2_Grid()._setSize(50,100,100,400,100)._bo_dd();
			
			gSorter._a(new RB_lab(S.ms("Legen Sie die Reihenfolge fest, indem Sie nacheinander auf die Nummern-Knöpfe klicken ...")), new RB_gld()._span(5)._ins(2, 2, 2, 10)._col_back_d());
			
			for (SortLine sl: sortLines) {
				gSorter	._a(sl.sortBt, new RB_gld()._ins(3))
						._a(sl.labTitel, new RB_gld()._ins(3))
						._a(sl.labAufzaehlText, new RB_gld()._ins(3))
						._a(sl.labLangtext, new RB_gld()._ins(3))
						._a(sl.labPosEnum, new RB_gld()._ins(3))
						;
				
			}
			
			E2_Grid buttonsContainer = new E2_Grid()._setSize(100,100)
					._a(new E2_Button()._t(S.ms("Neue Reihenfolge speichern"))._setShapeStandardTextButton()._aaa(()-> {
						VEK<Rec21> toSave = new VEK<>();
						MyE2_MessageVector mv = bibMSG.getNewMV();
						for (SortLine s: sortLines) {
							if (s.newValue>0) {
								s.recLine.nv(TEXT_LISTE.position, new Long(s.newValue), mv);
								toSave._a(s.recLine);
							}
						}
						
						if (bibDB.saveRecords(toSave, true, mv)) {
							mv._addInfo(S.ms("Reihenfolge wurde gespeichert !"));
						} else {
							mv._addAlarm(S.ms("Fehler beim Speichern!"));
						}
						
						if (mv.isOK()) {
							SortPopup.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
							m_tpHashMap.getNavigationList().get_oComponentMAP__REF().get_oSQLFieldMAP().clear_ORDER_SEGMENT();
							m_tpHashMap.getNavigationList().get_oComponentMAP__REF().get_oSQLFieldMAP().add_ORDER_SEGMENT(TEXT_LISTE.position.fn());

							m_tpHashMap.getNavigationList()._REBUILD_COMPLETE_LIST("");
						}
						bibMSG.MV()._add(mv);
						
					}), new RB_gld()._ins(0, 0, 20, 0))
					._a(new E2_Button()._t(S.ms("Abbruch"))._setShapeStandardTextButton()._aaa(()-> {
						SortPopup.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}));
					
			gSorter._a(buttonsContainer, new RB_gld()._span(5)._ins(2, 20, 2, 2));

			
			this.add(gSorter, E2_INSETS.I(5,5,5,5));
			
		}
		
	}
	
	
	private class SortLine {
		public E2_Button  	sortBt = null;
		public RB_lab  		labTitel = null;
		public RB_lab  		labAufzaehlText = null;
		public RB_lab  		labLangtext = null;
		public RB_lab  		labPosEnum = null;
		public Rec21       	recLine = null;
		
		public int 			newValue = 0;
		
		public SortLine(Rec21 p_recText) throws myException {
			super();
			this.recLine = p_recText;

			sortBt = new E2_Button()._setShapeStandardTextButtonCenterMidFontNormal(null, null)._t(recLine.getUfs(TEXT_LISTE.position,"-"))._aaa(new ActionSortButton(this));
			labTitel = new RB_lab()._t(recLine.getUfs(TEXT_LISTE.titel_text,"-"));
			labAufzaehlText = new RB_lab()._t(recLine.getUfs(TEXT_LISTE.aufzaehl_text,"-"));
			String text = recLine.getUfs(TEXT_LISTE.lang_text,"-");
			if (text.length()>100) {
				text = text.substring(0,100);
			}
			labLangtext = new RB_lab()._t(recLine.getUfs(TEXT_LISTE.lang_text,"-"));
			labPosEnum =  new RB_lab()._t(recLine.getUfs(TEXT_LISTE.position_enum,"-"));
		}
	}


	private class ActionSortButton extends XX_ActionAgent {

		private SortLine sortLine = null;
		
		public ActionSortButton(SortLine p_sortLine) {
			super();
			this.sortLine = p_sortLine;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			int nextId = 0;
			for (SortLine  s: sortLines) {
				if (s.newValue>nextId) {
					nextId = s.newValue;
				}
			}
			nextId = nextId+1;
			
			this.sortLine.newValue=nextId;
			
			this.sortLine.sortBt._t(""+nextId)._fsa(4)._b();
			this.sortLine.sortBt.set_bEnabled_For_Edit(false);
			
		}
		
	}
	
	
	
	
	
}

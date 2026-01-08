/**
 * panter.gmbh.Echo2.RB.COMP.TextListe
 * @author martin
 * @date 26.03.2020
 * 
 */
package panter.gmbh.Echo2.RB.COMP.TextListe;

import echopointng.ContainerEx;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.RadioButtonCheckboxAction;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE;
import panter.gmbh.basics4project.DB_ENUMS.TEXT_LISTE_VORLAGE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 26.03.2020
 *
 */
public class TL_LIST_btLoadFromVorlage extends E2_Button {

	private VEK<RB_cb>   cbListeVorlagen = new VEK<>();
	
	private RB_TransportHashMap tpHashMap = null;

	public TL_LIST_btLoadFromVorlage(RB_TransportHashMap	p_tpHashMap) throws myException {
		this.tpHashMap = p_tpHashMap;
		
		this._image("fileopen.png", true);
		
		this._addGlobalValidator(ENUM_VALIDATION.TEXT_LISTE_VORLAGE_NEW.getValidator());
		
		this._addValidator(()-> {
			MyE2_MessageVector mv = bibMSG.getNewMV();
			if (  S.isEmpty(  ((EnumTableTranslator)tpHashMap.getSB(TL_CONST.TabTrans)).getSaveKeyForVorlage())) {
				mv._addAlarm(S.ms("Fehler! Es wurde kein zugelassener Speichertemplate-Name übergeben !"));
			}
			return mv;
		});
		
		this._aaa(()-> {
			new OwnContainer();
		});
	}

	

	
	
	private class OwnContainer extends E2_BasicModuleContainer {
		
		RecList21 	rlTexte = 			null;
		E2_Grid 	gridMitVorlagen = 	new E2_Grid()._setSize(20,180,300)._bo_dd();
		
		public OwnContainer() throws myException {
			super();
			
			SEL sel = new SEL(_TAB.text_liste_vorlage,true).WHERE(new vglParam(TEXT_LISTE_VORLAGE.tablereference))
								.ORDERUP(TEXT_LISTE_VORLAGE.bezeichnung)._addParam(new Param_String("", getLoadKeyForVorlage()));
			
			rlTexte = new RecList21(_TAB.text_liste_vorlage)._fill(sel);
			RadioButtonCheckboxAction radioButtons = new RadioButtonCheckboxAction(true);
			
			if (rlTexte.size()==0) {
				bibMSG.MV()._addAlarm(S.ms("Es gibt keine Vorlagen für dieses Modul: ").ut(((EnumTableTranslator)tpHashMap.getSB(TL_CONST.TabTrans)).user_text()));
			} else {
			
				cbListeVorlagen.clear();
				for (Rec21 vorlage: rlTexte) {
					RB_cb cb = (RB_cb) new RB_cb()._setSomeThing(vorlage);
					radioButtons._addCheckBox(cb);
					
					cb._aaa(()->{
						renderGrid();
					});
					cbListeVorlagen._a(cb);
				}

				renderGrid();
				
				ContainerEx containerMitVorlagen = new ContainerEx();
				containerMitVorlagen.setWidth(new Extent(500));
				containerMitVorlagen.setHeight(new Extent(300));
				containerMitVorlagen.add(gridMitVorlagen);
				
				
				E2_Grid gridBase = new E2_Grid()._setSize(100,500);
				
				gridBase._a(new RB_lab()._tr("Bitte wählen Sie eine Vorlage aus: ")._b()._fsa(2), new RB_gld()._left_mid()._span(2)._ins(0,5,0,5));
				gridBase._a(containerMitVorlagen, new RB_gld()._left_mid()._ins(0, 5, 5, 5)._span(2));
				gridBase	._a(new E2_Grid()._a(new BtLoad(), new RB_gld()._ins(0, 10, 10, 10))._a(new BtClose(), new RB_gld()._ins(0,10,0,10)), new RB_gld()._span(2));
				
				this.add(gridBase, E2_INSETS.I(5));
				
				this.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Laden einer Vorlage ?"),600,500);
			}
			
		}
		
		private void renderGrid() throws myException {
			gridMitVorlagen._clear();
			for (RB_cb cb: cbListeVorlagen) {
				RB_gld layout =  new RB_gld()._left_top()._ins(3);

				Rec21 vorlage = (Rec21)cb.getSomeThing();
				if (cb.isSelected()) {
					layout._col_back_d();
				}
				gridMitVorlagen._a(cb, 	layout);
				gridMitVorlagen._a(new E2_Button()._t(S.NN(vorlage.getFs(TEXT_LISTE_VORLAGE.bezeichnung)))._lw()._aaa(	new OwnActionRefreshGrid(cb)),				layout);
				gridMitVorlagen._a(new RB_lab()._t(S.NN(vorlage.getFs(TEXT_LISTE_VORLAGE.beschreibung_lang)))._fsa(-2)._lw(),	layout);
			}
		}
		
		
		private class OwnActionRefreshGrid extends XX_ActionAgent {
			private RB_cb 		checkBox = null;
			public OwnActionRefreshGrid(RB_cb checkBox) {
				super();
				this.checkBox = checkBox;
			}

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				this.checkBox.doAction();
				renderGrid();
			}
		}

		
		
		private class BtLoad extends E2_Button {

			public BtLoad() {
				super();
				
				_t(S.ms("Laden"))._standard_text_button()._image("fileopen.png", true);
				
				_aaa(()-> {
					bibMSG.MV()._add(addTemplateListToModule());
					if (bibMSG.MV().isOK()) {
						CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
				});
			}
			
		}
		
		private class BtClose extends E2_Button {

			public BtClose() {
				super();
				
				_t(S.ms("Abbrechen"))._standard_text_button()._setShapeCancelAndClose();
				
				this._aaa(()-> {
					CLOSE_AND_DESTROY_POPUPWINDOW(true);
				});
				
			}
			
		}
		
	}

	

	private String getLoadKeyForVorlage() {
        return ((EnumTableTranslator)this.tpHashMap.getSB(TL_CONST.TabTrans)).getSaveKeyForVorlage();
	}
	
	private String getTableBaseNameForAddedTextList() {
        return ((EnumTableTranslator)this.tpHashMap.getSB(TL_CONST.TabTrans)).getTableNameForTextListe();
	}
	
	
	private String getTabNameForTextListe() {
        return EnumTableTranslator.TEXT_LISTE_VORLAGE.getTableNameForTextListe();
	}
	
	
	private MyE2_MessageVector addTemplateListToModule() {
		MyE2_MessageVector mv = bibMSG.getNewMV();
		
        Long  				idRefTable = tpHashMap.getMotherKeyValue();

        if (O.isOneNull(getLoadKeyForVorlage(),idRefTable)) {
        	mv._addAlarm(S.ms("Achtung! Speichern nicht möglich ! Referenz-Name oder id umbekannt! "));
        } else {
		
			try {
				
				Rec21 rVorlage = null;
				
				for (RB_cb cb: this.cbListeVorlagen) {
					if (cb.isSelected()) {
						rVorlage = (Rec21)cb.getSomeThing();
					}
				}
				
				if (rVorlage==null) {
					mv._addAlarm(S.ms("Bitte markieren Sie die gewünschte Vorlage!"));
				} else {
					
					RecList21 rlToLoad = new RecList21(_TAB.text_liste)._fill(
							new SEL(_TAB.text_liste,true).WHERE(new vglParam(TEXT_LISTE.tablename)).AND(new vglParam(TEXT_LISTE.id_table))
													._addParam(new Param_String("",getTabNameForTextListe()))
													._addParam(new Param_Long(rVorlage.getId()))
												);
					if (rlToLoad.size()==0) {
						mv._addAlarm(S.ms("Die Vorlage ist leer !"));												
					} else {
					
						VEK<Rec21> recsToSave = new VEK<>();
						for (Rec21 rt: rlToLoad) {
							Rec21 rc = rt.getRecForCreateCopyStdExclude(mv);
							rc	._setNewVal(TEXT_LISTE.tablename, getTableBaseNameForAddedTextList(), mv)
								._setNewVal(TEXT_LISTE.id_table, idRefTable, mv);
							recsToSave._a(rc);
						}
								
						if (mv.isOK()) {
							bibDB.saveRecords(recsToSave, true, mv);
							if (mv.isOK()) {
								mv._addInfo(S.ms("Die Texte der Vorlage ").ut(rVorlage.getUfs(TEXT_LISTE_VORLAGE.bezeichnung)).ut(" ").t(" wurde geladen !"));
								tpHashMap.getNavigationList().RefreshList();
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				mv._addAlarm("Error saving : <ed391850-6f74-11ea-bc55-0242ac130003>"+e.getLocalizedMessage());
			}
        }
        
		return mv;
	}
	

	
}

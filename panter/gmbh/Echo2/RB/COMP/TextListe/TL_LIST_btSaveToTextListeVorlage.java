/**
 * panter.gmbh.Echo2.RB.COMP.TextListe
 * @author martin
 * @date 26.03.2020
 * 
 */
package panter.gmbh.Echo2.RB.COMP.TextListe;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
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
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
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
public class TL_LIST_btSaveToTextListeVorlage extends E2_Button {

	private RB_TextField tfBezeichnung = null;
	private RB_TextArea  tfBeschreibung = null;

	
	private RB_TransportHashMap tpHashMap = null;

	public TL_LIST_btSaveToTextListeVorlage(RB_TransportHashMap	p_tpHashMap) throws myException {
		this.tpHashMap = p_tpHashMap;
		
		this._image("save.png", true);
		
		this._addGlobalValidator(ENUM_VALIDATION.TEXT_LISTE_VORLAGE_NEW.getValidator());
		
		this._addValidator(()-> {
			MyE2_MessageVector mv = bibMSG.getNewMV();
			if (  S.isEmpty( getSaveKeyForVorlage())) {
				mv._addAlarm(S.ms("Fehler! Es wurde kein zugelassener Speichertemplate-Name übergeben !"));
			}
			return mv;
		});
		
		this._aaa(()-> {
			new OwnContainer();
		});
		
		
	}

	
	


	private String getSaveKeyForVorlage() {
        return ((EnumTableTranslator)this.tpHashMap.getSB(TL_CONST.TabTrans)).getSaveKeyForVorlage();
	}
	
	
	private String getTabNameForTextListe() {
        return ((EnumTableTranslator)this.tpHashMap.getSB(TL_CONST.TabTrans)).getTableNameForTextListe();
	}
	
	
	
	private class OwnContainer extends E2_BasicModuleContainer {

		
		public OwnContainer() throws myException {
			super();
			
			tfBezeichnung = new RB_TextField()._width(400)._maxInputSize(50);
			tfBeschreibung = new RB_TextArea()._width(400)._rows(5);

			
			E2_Grid innen = new E2_Grid()._setSize(200,400);
			
			innen._a(new RB_lab()._tr("Vorhandene Textliste als Vorlage speichern ...")._b()._fsa(2), new RB_gld()._left_mid()._span(2)._ins(0,5,0,5));
			
			innen	._a(new RB_lab()._tr("Name der Vorlage")._b(), new RB_gld()._left_mid()._ins(0, 10, 5, 5))
					._a(tfBezeichnung, new RB_gld()._left_mid()._ins(0, 5, 5, 5));
			
			innen	._a(new RB_lab()._tr("Beschreibung")._b(), new RB_gld()._left_mid()._ins(0, 10, 5, 5))
					._a(tfBeschreibung, new RB_gld()._left_mid()._ins(0, 5, 5, 5));
			
			innen	._a(new E2_Grid()._a(new BtSave(), new RB_gld()._ins(0, 10, 10, 10))._a(new BtClose(), new RB_gld()._ins(0,10,0,10)), new RB_gld()._span(2));
			
			this.add(innen, E2_INSETS.I(5));
			
			this.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Speichern als Vorlage ?"),600,300);
			
		}
		
		
		private class BtSave extends E2_Button {

			public BtSave() {
				super();
				
				_t(S.ms("Speichern"))._standard_text_button()._setShapeSaveAndClose();
				
				_aaa(()-> {
					bibMSG.MV()._add(saveListToTemplate());
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

	
	private MyE2_MessageVector saveListToTemplate() {
		MyE2_MessageVector mv = bibMSG.getNewMV();
		
        Long  				  idRefTable = tpHashMap.getMotherKeyValue();

        if (O.isOneNull(getSaveKeyForVorlage(),idRefTable)) {
        	mv._addAlarm(S.ms("Achtung! Speichern nicht möglich ! Referenz-Name oder id umbekannt! "));
        } else {
		
			try {
				if (S.isEmpty(tfBezeichnung.getText())) {
					mv._addAlarm(S.ms("Bitte geben Sie eine Bezeichnung für die Textliste ein !"));
					tfBezeichnung.mark_FalseInput();
				} else {
					SEL selCheckIfBezeichungVorhanden = new SEL(_TAB.text_liste_vorlage).FROM(_TAB.text_liste_vorlage)
												.WHERE(new vglParam(TEXT_LISTE_VORLAGE.bezeichnung))
												.AND( new vglParam(TEXT_LISTE_VORLAGE.tablereference))
												;
					
					RecList21 rl = new RecList21(_TAB.text_liste_vorlage)._fill(
							new SqlStringExtended(selCheckIfBezeichungVorhanden.s())
										._addParameter(new Param_String("1", tfBezeichnung.getText().trim()))
										._addParameter(new Param_String("2", getSaveKeyForVorlage()))
										);
					
					if (rl.size()>0) {
						mv._addAlarm(S.ms("Es existiert bereits eine Vorlage mit diesem Namen !"));
					} else {
						
						VEK<Rec21> recsToSave = new VEK<>();
						
						Long idTextListeVorlage = Long.parseLong(_TAB.text_liste_vorlage.getNextVal());
						recsToSave._a(new Rec21(_TAB.text_liste_vorlage)	._setNewVal(TEXT_LISTE_VORLAGE.id_text_liste_vorlage, idTextListeVorlage, mv)
																			._setNewVal(TEXT_LISTE_VORLAGE.tablereference, getSaveKeyForVorlage(), mv)
																			._setNewVal(TEXT_LISTE_VORLAGE.bezeichnung, tfBezeichnung.getText().trim(), mv)
																			._setNewVal(TEXT_LISTE_VORLAGE.beschreibung_lang, tfBezeichnung.getText().trim(), mv));
						if (mv.isOK()) {
							RecList21 rlToSave = new RecList21(_TAB.text_liste)._fill(
										new SEL(_TAB.text_liste,true).WHERE(new vglParam(TEXT_LISTE.tablename)).AND(new vglParam(TEXT_LISTE.id_table))
																			._addParam(new Param_String("",getTabNameForTextListe()))
																			._addParam(new Param_Long(idRefTable))
																			);
							
							if (rlToSave.size()==0) {
								mv._addAlarm("Eine leere Liste kann nicht gespeichert werden !");
							} else {
								for (Rec21 rt: rlToSave) {
									Rec21 rc = rt.getRecForCreateCopyStdExclude(mv);
									rc	._setNewVal(TEXT_LISTE.tablename, EnumTableTranslator.TEXT_LISTE_VORLAGE.getTableNameForTextListe(), mv)
										._setNewVal(TEXT_LISTE.id_table, idTextListeVorlage, mv);
									recsToSave._a(rc);
								}
								
								if (mv.isOK()) {
									bibDB.saveRecords(recsToSave, true, mv);
									if (mv.isOK()) {
										mv._addInfo(S.ms("Die Vorlage wurde gespeichert unter dem Bezeichnung :"+ tfBezeichnung.getText().trim()));
									}
								}
								
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

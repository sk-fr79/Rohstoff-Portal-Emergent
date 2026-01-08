/**
 * rohstoff.businesslogic.bewegung2.list.listComponents
 * @author martin
 * @date 16.01.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.list.listComponents;

import java.math.BigDecimal;
import java.util.Date;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_Search_V2_Artbez;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_DEL_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_STORNO_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_bgVector;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_bgVector.EnVerteilStatus;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;

/** 
 * Erstellen einer offenen verteilung
 * @author martin
 * @date 16.01.2020
 * 
 */
public class B2_ListBtVerteilerOffen extends E2_Button {
	
	private RB_TransportHashMap m_tpHashMap;

	private Rec21_bgVector 		rec21BgVektorOriginal = 	null;
	private EnVerteilStatus 	verteilStatus = 	null; 
	
	private GridVerteiler 		gridVerteiler = 	new GridVerteiler();
	private VEK<Rec21Vektor>    verteilerVektoren = new VEK<>();
	
	private OwnBasicModuleContainer  ownBasicModuleContainer = null;
	
	/**
	 * @author martin
	 * @date 16.01.2020
	 *
	 */
	public B2_ListBtVerteilerOffen(RB_TransportHashMap tpHashMap, Rec21_bgVector rec21bgVektor) {
		this.m_tpHashMap = tpHashMap;
		this.rec21BgVektorOriginal = rec21bgVektor;
		
		this.verteilStatus = rec21BgVektorOriginal.getVerteilStatus();
		this.build();
	}

	
	public void build() {
		
		try {
			if (verteilStatus.isVerteilungOK()) {
				this.__setImages(E2_ResourceIcon.get_RI("verteil_fuhre.png"), E2_ResourceIcon.get_RI("verteil_fuhre__.png"));
				this._aaa(()->{
					verteilerVektoren._clear();
					ownBasicModuleContainer = new OwnBasicModuleContainer();
					
					//jetzt liste einstellen
					if (m_tpHashMap.getNavigationList().getStatusSaver() != null) {
						m_tpHashMap.getNavigationList().restoreStatus();
					}
					m_tpHashMap.getNavigationList().saveStatus(bibMSG.MV());
					m_tpHashMap.getNavigationList().get_vectorSegmentation().clear();
					
					VEK<String> idsSubList = new VEK<>();
					idsSubList._a(this.rec21BgVektorOriginal.getIdLong().toString());
					for (Rec21Vektor r: verteilerVektoren) {
						idsSubList._addIfNotIN(r.getIdLong().toString());
					}
					
					m_tpHashMap.getNavigationList().get_vectorSegmentation().addAll(idsSubList);
					m_tpHashMap.getNavigationList()._RebuildListWithActualIds();
					
					
					
				});
			} else {
				this.__setImages(E2_ResourceIcon.get_RI("verteil_fuhre__.png"), E2_ResourceIcon.get_RI("verteil_fuhre__.png"));
			}
			this._ttt(S.ms(verteilStatus.getMeldung()));
			
		} catch (Exception e) {
			e.printStackTrace();
			this.__setImages(E2_ResourceIcon.get_RI("verteil_fuhre__.png"), E2_ResourceIcon.get_RI("verteil_fuhre__.png"));
			this._t("@@ERR@@")._fc(Color.RED);
		}
	}


	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}
  

	
	
	
	private class OwnBasicModuleContainer extends E2_BasicModuleContainer {

		
		public OwnBasicModuleContainer() {
			super();
			
			try {
				//jetzt nachsehen, ob es bereits verteilmengen gibt
				RecList21 verteilerVorhanden = rec21BgVektorOriginal.getVerteilRecords();
				
				for (Rec21 r: verteilerVorhanden) {
					Rec21_bgVector vektor = new  Rec21_bgVector(r);
					verteilerVektoren._a(new Rec21Vektor(vektor));
				}
				this.add(gridVerteiler._refresh(verteilerVektoren), E2_INSETS.I(5));
				
				this.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Verteilung: "),700,500);

				
				
			} catch (myException e) {
				e.printStackTrace();
				bibMSG.MV()._add(e.get_ErrorMessage("c003beb6-3870-11ea-a137-2e728ce88125"));
			} catch (Exception e) {
				e.printStackTrace();
				bibMSG.MV()._addAlarm(e.getClass().getCanonicalName()+" / "+e.getLocalizedMessage()+" / c003beb6-3870-11ea-a137-2e728ce88126");
			}
		}
		
		
		
	}


	private class GridVerteiler extends E2_Grid {

		public GridVerteiler() {
			super();
		}

		
		public GridVerteiler _refresh(VEK<Rec21Vektor> verteilVektors) {

			try {
				this._clear()._setSize(400,200,40,40)._bo_d();
				
				Rec21 atomLeft = rec21BgVektorOriginal.getCompleteStackOfRecords().get(RecA1.key);
				if (atomLeft==null) {
					throw new myException("Error: cannot find left BG_ATOM-record !");
				}
				
				Rec21 artbez =  atomLeft.get_up_Rec21(ARTIKEL_BEZ.id_artikel_bez);
				
				if (atomLeft.getBigDecimalDbValue(BG_ATOM.menge)==null || atomLeft.getBigDecimalDbValue(BG_ATOM.menge).compareTo(BigDecimal.ZERO)==0) {
					bibMSG.MV()._addAlarm(S.ms("Die Lademenge ist leer. Es kann nichts verteilt werden!"));
				} else {
				
					if (artbez==null ) {
						bibMSG.MV()._addAlarm(S.ms("Die Ladesorte ist leer. Deshalb ist eine Verteilung nicht möglich !"));
					} else {
						Rec21_artikel_bez artBez = new Rec21_artikel_bez(artbez) ;
						
						this	._a(new RB_lab()._t(S.ms("Ladesorte ")), new RB_gld()._ins(2, 2, 2, 2)._left_mid()._col_back_dd())
								._a(new RB_lab()._t(S.ms("Lademenge")), new RB_gld()._ins(2, 2, 2, 2)._right_mid()._col_back_dd())
								._a(new RB_lab()._t(S.ms("")), new RB_gld()._ins(2, 2, 2, 2)._right_mid())
								._a(new RB_lab()._t(S.ms("")), new RB_gld()._ins(2, 2, 2, 2)._right_mid())
								
								._a(new RB_lab()._t(S.ms(artBez.__get_ANR1_ANR2_ARTBEZ1())), new RB_gld()._left_mid()._ins(2, 2, 2, 2))
								._a(new RB_lab()._t(S.ms(atomLeft.getFs(BG_ATOM.menge,"0"))), new RB_gld()._right_mid()._ins(2, 2, 2, 2))
								._a(new RB_lab()._t(S.ms("")), new RB_gld()._ins(2, 2, 2, 2)._right_mid())
								._a(new RB_lab()._t(S.ms("")), new RB_gld()._ins(2, 2, 2, 2)._right_mid())
								
								._addSeparator()
						
								._a(new RB_lab()._t(S.ms("Neue Sorte ")), new RB_gld()._ins(2, 2, 2, 2)._left_mid()._col_back_dd())
								._a(new RB_lab()._t(S.ms("Verteilmenge")), new RB_gld()._ins(2, 2, 2, 2)._right_mid()._col_back_dd())
//								._a(new RB_lab()._t(S.ms("")), new RB_gld()._ins(2, 2, 2, 2)._right_mid())
								;
						
						if (verteilVektors.size()>0) {
							//dann die letzte ueberschrift schliessen, sonst wird ein plus-button eingeblendet
							this._a(new RB_lab()._t(S.ms("")), new RB_gld()._ins(2, 2, 2, 2)._right_mid());
							this._a(new RB_lab()._t(S.ms("")), new RB_gld()._ins(2, 2, 2, 2)._right_mid());
						} else {
							this._a(new RB_lab()._t(S.ms("")), new RB_gld()._ins(2, 2, 2, 2)._right_mid());
							this._a(new E2_Button()._t("+")._ttt(S.ms("Weitere Verteilmenge dazufügen "))._fsa(4)._b()._center()._aaa(()-> 
																							{
																								verteilerVektoren._a(new Rec21Vektor());
																								gridVerteiler._refresh(verteilerVektoren);
																							})	, new RB_gld());
						}
						
						
						for (int i=0; i<verteilVektors.size();i++) {
							Rec21Vektor vektor = verteilVektors.get(i);
							this._a(vektor.searchArtBez, new RB_gld()._left_mid()._ins(2, 2, 2, 2))
								._a(vektor.tfMenge, new RB_gld()._right_mid()._ins(2, 2, 2, 2))
								;
							
							if (i<(verteilVektors.size()-1)) {
								this._a(new E2_Button()._t("-")._ttt(S.ms("Zeile entfernen"))._fsa(4)._b()._center()._aaa(new AA_DelLine(vektor)), new RB_gld());
								this._a(new RB_lab()._t(S.ms("")), new RB_gld()._ins(2, 2, 2, 2)._right_mid());
							} else {
								this._a(new E2_Button()._t("-")._ttt(S.ms("Zeile entfernen"))._fsa(4)._b()._center()._aaa(new AA_DelLine(vektor)), new RB_gld());
								this._a(new E2_Button()._t("+")._ttt(S.ms("Weitere Verteilmenge dazufügen "))._fsa(4)._b()._center()._aaa(new AA_AddLine())	, new RB_gld());
							}
							
						}

						this._a(new E2_Button()._t("Speichern")._setShapeBigHighLightText()
										._addGlobalValidator(new ValidatorInputValues())
										._addGlobalValidator(new ValidatorSummeAbzuege())
										._aaa(new AA_Save()), new RB_gld()._span(4)._ins(10));
						
						
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				this._clear();
				bibMSG.MV()._addAlarm(e.getLocalizedMessage());
			}
			
			return this;
		}
		
	}
	
	
	private class AA_AddLine extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			verteilerVektoren._a(new Rec21Vektor());
			gridVerteiler._refresh(verteilerVektoren);
		}
	}
	
	private class AA_DelLine extends XX_ActionAgent {
		private Rec21Vektor toRemove = null;
		
		public AA_DelLine(Rec21Vektor toRemove) {
			super();
			this.toRemove = toRemove;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (toRemove.is_newRecordSet()) {
				verteilerVektoren.remove(toRemove);
			} else {
				toRemove._setToDelete(true);
			}
			gridVerteiler._refresh(verteilerVektoren);
		}
	}
	
	
	
	private class ValidatorInputValues extends XX_ActionValidator_NG {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			String zeilenMitFehlern = "";
			int zeile=1;
			for (Rec21Vektor v: verteilerVektoren) {
				if (v.getArtikelBez()==null || v.getAnzahlAusVerteilung()==null ) {
					zeilenMitFehlern = zeilenMitFehlern+" / "+zeile;
				}
				zeile++;
			}

			MyE2_MessageVector mv = bibMSG.getNewMV();
					
			if (S.isFull(zeilenMitFehlern)) {
				mv._addAlarm(S.ms("Es befinden sich Erfassungsfehler in den folgende Zeilen: "+zeilenMitFehlern.substring(3)));
			}
			
			return mv;
		}
		
	}
	
	/**
	 * prueft, ob zu viel verteilt wurde
	 * @author martin
	 * @date 17.01.2020
	 *
	 */
	private class ValidatorSummeAbzuege extends XX_ActionValidator_NG {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			BigDecimal summe = new BigDecimal(0);
			for (Rec21Vektor v: verteilerVektoren) {
				if (!v.isToDelete()) {
					summe = summe.add(O.NN(v.getAnzahlAusVerteilung(),BigDecimal.ZERO));
				}
			}

			MyE2_MessageVector mv = bibMSG.getNewMV();
			
			Rec21 		atom1 = 		rec21BgVektorOriginal.getCompleteStackOfRecords().get(RecA1.key); 
			BigDecimal 	bdMengeRest = 	new BigDecimal(0).add(O.NN(atom1.getBigDecimalDbValue(BG_ATOM.menge), BigDecimal.ZERO)).subtract(O.NN(atom1.getBigDecimalDbValue(BG_ATOM.menge_abzug), BigDecimal.ZERO));
			
			if (summe.compareTo(bdMengeRest)>0) {
				mv._addAlarm(S.ms("Die Abzüge sind insgesamt zu gross !"));
			}
			return mv;
		}
		
	}
	
	
	
	private class AA_Save extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			VEK<Rec21>  recsToSave = new VEK<>();
						
			HMAP<RB_KM, Rec21> completeRec = rec21BgVektorOriginal.getCompleteStackOfRecords();
			
			MyE2_MessageVector mv = bibMSG.getNewMV();
			
			for (Rec21Vektor v: verteilerVektoren) {
				if (v.is_newRecordSet()) {
					Rec21 s1 = completeRec.get(RecS1.key);
					Rec21 s2 = completeRec.get(RecS2.key);
					Rec21 s3 = completeRec.get(RecS3.key);
					Rec21 a1 = completeRec.get(RecA1.key);
					Rec21 a2 = completeRec.get(RecA2.key);
					Rec21 vk  = completeRec.get(RecV.key);
	
					Rec21 s1C = 	s1.getRecForCreateCopyStdExclude(mv);
					Rec21 s2C = 	s2.getRecForCreateCopyStdExclude(mv);
					Rec21 s3C = 	s3.getRecForCreateCopyStdExclude(mv);
					Rec21 a1C = 	a1.getRecForCreateCopyStdExclude(mv);
					Rec21 a2C = 	a2.getRecForCreateCopyStdExclude(mv);
					Rec21 vkC  = 	vk.getRecForCreateCopyStdExclude(mv);
					
		        	//die id des neuen vektors
		        	String idSeqVektor = _TAB.bg_vektor.getNextVal();
	
		        	
		        	//dann muessen die seq-werte der 3 stationen ermittelt werden
		        	String idSeqStation1 = _TAB.bg_station.getNextVal();
		        	String idSeqStation2 = _TAB.bg_station.getNextVal();
		        	String idSeqStation3 = _TAB.bg_station.getNextVal();
		        	
		        	s1C._setNewVal(BG_STATION.id_bg_station, 		Long.parseLong(idSeqStation1), mv);
		        	s2C._setNewVal(BG_STATION.id_bg_station, 		Long.parseLong(idSeqStation2), mv);
		        	s3C._setNewVal(BG_STATION.id_bg_station, 		Long.parseLong(idSeqStation3), mv);
		        	
		        	s1C._setNewVal(BG_STATION.id_bg_vektor, 		Long.parseLong(idSeqVektor), mv);
		        	s2C._setNewVal(BG_STATION.id_bg_vektor, 		Long.parseLong(idSeqVektor), mv);
		        	s3C._setNewVal(BG_STATION.id_bg_vektor, 		Long.parseLong(idSeqVektor), mv);
		        	
		        	a1C._setNewVal(BG_ATOM.id_bg_station_quelle, 	Long.parseLong(idSeqStation1), mv);
		        	a1C._setNewVal(BG_ATOM.id_bg_station_ziel,   	Long.parseLong(idSeqStation2), mv);
		        	a2C._setNewVal(BG_ATOM.id_bg_station_quelle, 	Long.parseLong(idSeqStation2), mv);
		        	a2C._setNewVal(BG_ATOM.id_bg_station_ziel,   	Long.parseLong(idSeqStation3), mv);
	
		        	a1C._setNewVal(BG_ATOM.id_bg_vektor, 			Long.parseLong(idSeqVektor), mv);
		        	a2C._setNewVal(BG_ATOM.id_bg_vektor,  			Long.parseLong(idSeqVektor), mv);
	
		        	vkC._setNewVal(BG_VEKTOR.id_bg_vektor, 			Long.parseLong(idSeqVektor), mv);
		        	vkC._setNewVal(BG_VEKTOR.id_bg_vektor_base, rec21BgVektorOriginal.getIdLong(), mv);
		 

					a1C._setNewVal(BG_ATOM.menge, v.getAnzahlAusVerteilung(), mv);
					a1C._setNewVal(BG_ATOM.id_artikel_bez, 	v.getArtikelBez().getIdLong(), mv);
					a1C._setNewVal(BG_ATOM.id_artikel, 		v.getArtikelBez()._getRec21Artikel().getIdLong(), mv);
					
					a2C._setNewVal(BG_ATOM.menge, v.getAnzahlAusVerteilung(), mv);
					a2C._setNewVal(BG_ATOM.id_artikel_bez, 	v.getArtikelBez().getIdLong(), mv);
					a2C._setNewVal(BG_ATOM.id_artikel, 		v.getArtikelBez()._getRec21Artikel().getIdLong(), mv);
		        	vkC._setNewVal(BG_VEKTOR.planmenge, 		BigDecimal.ZERO, mv);
		        	
		        	
					recsToSave._a(vkC)._a(s1C)._a(s2C)._a(s3C)._a(a1C)._a(a2C);
				} else {
					Rec21 s1 = v.getCompleteStackOfRecords().get(RecS1.key);
					Rec21 s2 = v.getCompleteStackOfRecords().get(RecS2.key);
					Rec21 s3 = v.getCompleteStackOfRecords().get(RecS3.key);
					Rec21 a1 = v.getCompleteStackOfRecords().get(RecA1.key);
					Rec21 a2 = v.getCompleteStackOfRecords().get(RecA2.key);
					
					if (v.isToDelete()) {
						Rec21 recStorno = new Rec21(_TAB.bg_storno_info);
						Rec21 recDel =    new Rec21(_TAB.bg_del_info);
						Long idSeqStorno = Long.parseLong(_TAB.bg_storno_info.getNextVal());
						Long idSeqDelete = Long.parseLong(_TAB.bg_del_info.getNextVal());
					

						s1._setNewVal(BG_STATION.id_bg_storno_info, idSeqStorno, mv)._setNewVal(BG_STATION.id_bg_del_info, idSeqDelete, mv);
						s2._setNewVal(BG_STATION.id_bg_storno_info, idSeqStorno, mv)._setNewVal(BG_STATION.id_bg_del_info, idSeqDelete, mv);
						s3._setNewVal(BG_STATION.id_bg_storno_info, idSeqStorno, mv)._setNewVal(BG_STATION.id_bg_del_info, idSeqDelete, mv);
						a1._setNewVal(BG_ATOM.id_bg_storno_info, idSeqStorno, mv)._setNewVal(BG_ATOM.id_bg_del_info, idSeqDelete, mv);
						a2._setNewVal(BG_ATOM.id_bg_storno_info, idSeqStorno, mv)._setNewVal(BG_ATOM.id_bg_del_info, idSeqDelete, mv);
						v._setNewVal(BG_VEKTOR.planmenge, BigDecimal.ZERO, mv)._setNewVal(BG_VEKTOR.id_bg_storno_info, idSeqStorno, mv)._setNewVal(BG_VEKTOR.id_bg_del_info, idSeqDelete, mv);
						a1._setNewVal(BG_ATOM.menge, null, mv);
						a2._setNewVal(BG_ATOM.menge, null, mv);

						recStorno	._setNewVal(BG_STORNO_INFO.id_bg_storno_info, 	idSeqStorno, mv)
									._setNewVal(BG_STORNO_INFO.storno_datum, new Date(), mv)
									._setNewVal(BG_STORNO_INFO.storno_grund, "Automatisch bei Verteilung", mv)
									._setNewVal(BG_STORNO_INFO.id_user, Long.parseLong(bibALL.get_ID_USER()), mv)
									;
						recDel		._setNewVal(BG_DEL_INFO.id_bg_del_info, 			idSeqDelete, mv)
									._setNewVal(BG_DEL_INFO.delete_datum, new Date(), mv)
									._setNewVal(BG_DEL_INFO.delete_grund, "Automatisch bei Verteilung", mv)
									._setNewVal(BG_DEL_INFO.id_user, Long.parseLong(bibALL.get_ID_USER()), mv)
									;
						
						recsToSave._a(recStorno)._a(recDel)._a(v)._a(s1)._a(s2)._a(s3)._a(a1)._a(a2);
					} else {
						a1._setNewVal(BG_ATOM.menge, v.getAnzahlAusVerteilung(), mv);
						a1._setNewVal(BG_ATOM.id_artikel_bez, 	v.getArtikelBez().getIdLong(), mv);
						a1._setNewVal(BG_ATOM.id_artikel, 		v.getArtikelBez()._getRec21Artikel().getIdLong(), mv);
						
						a2._setNewVal(BG_ATOM.menge, v.getAnzahlAusVerteilung(), mv);
						a2._setNewVal(BG_ATOM.id_artikel_bez, 	v.getArtikelBez().getIdLong(), mv);
						a2._setNewVal(BG_ATOM.id_artikel, 		v.getArtikelBez()._getRec21Artikel().getIdLong(), mv);
						
						v._setNewVal(BG_VEKTOR.planmenge, BigDecimal.ZERO, mv);
						
						
						
						recsToSave._a(v)._a(s1)._a(s2)._a(s3)._a(a1)._a(a2);
					}
				}
			}

			BigDecimal summeAbzug = new BigDecimal(0);
			for (Rec21Vektor v: verteilerVektoren) {
				if (!v.isToDelete()) {
					summeAbzug = summeAbzug.add(O.NN(v.getAnzahlAusVerteilung(),BigDecimal.ZERO));
				}
			}
			
			Rec21 bgAtom1 = rec21BgVektorOriginal.getCompleteStackOfRecords().get(RecA1.key);
			Rec21 bgAtom2 = rec21BgVektorOriginal.getCompleteStackOfRecords().get(RecA2.key);
			
			bgAtom1._setNewVal(BG_ATOM.menge_verteilung, summeAbzug, mv);
			bgAtom2._setNewVal(BG_ATOM.menge_verteilung, summeAbzug, mv);
			
			recsToSave._a(bgAtom1)._a(bgAtom2);
			
			if (mv.isOK()) {
				mv._add(bibDB.execMultiRecSave(recsToSave, true));
				if (mv.isOK()) {
					ownBasicModuleContainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
//					RecList21 verteilerVorhandenNeu = rec21BgVektorOriginal.getVerteilRecords();
//					VEK<String> idsSubList = new VEK<>();
//					idsSubList._a(rec21BgVektorOriginal.getIdLong().toString());
//					for (Rec21 r: verteilerVorhandenNeu) {
//						idsSubList._addIfNotIN(r.getIdLong().toString());
//					}
//					m_tpHashMap.getNavigationList().get_vectorSegmentation().clear();
//					m_tpHashMap.getNavigationList().get_vectorSegmentation().addAll(idsSubList);
//					m_tpHashMap.getNavigationList()._RebuildListWithActualIds();

					
					m_tpHashMap.getNavigationList().RefreshList();
				} else {
					bibMSG.MV()._add(mv);
				}
			} else {
				bibMSG.MV()._add(mv);
			}
		}
	}
	
	
	
	
	
	
	private class Rec21Vektor extends Rec21_bgVector {

		public RB_HL_Search_V2_Artbez 	searchArtBez = 	new RB_HL_Search_V2_Artbez()._activatePopupPreSelectArtikel(true);
		public RB_TextField   			tfMenge  = 		(RB_TextField)  new RB_TextField()._al_right()._w(120);
		private boolean 				isToDelete = 	false;   //damit kann eine bestehende verteilerzeile als zu entfernen markiert werden
		
		
		public Rec21Vektor(Rec21_bgVector recVektor) throws myException {
			super(recVektor);
			
			searchArtBez.rb_set_db_value_manual(recVektor.getCompleteStackOfRecords().get(RecA1.key).getFs(BG_ATOM.id_artikel_bez));
			tfMenge.rb_set_db_value_manual(recVektor.getCompleteStackOfRecords().get(RecA1.key).getFs(BG_ATOM.menge));
			
		}
		
		public Rec21Vektor() throws myException {
			super();
			searchArtBez.rb_set_db_value_manual("");
			tfMenge.rb_set_db_value_manual("");
		}

		public boolean isToDelete() {
			return isToDelete;
		}

		public Rec21Vektor _setToDelete(boolean isToDelete) {
			this.isToDelete = isToDelete;
			if (isToDelete) {
				try {
					searchArtBez.set_bEnabled_For_Edit(false);
					tfMenge.set_bEnabled_For_Edit(false);
				} catch (myException e) {
					e.printStackTrace();
				}
			}
			return this;
		}
		
		
		public Rec21_artikel_bez getArtikelBez() {
			try {
				
				if (S.isFull(searchArtBez.rb_readValue_4_dataobject()) && searchArtBez.is_search_done_correct()) {
					MyLong artBez = new MyLong(searchArtBez.rb_readValue_4_dataobject());
					if (artBez.isOK()) {
						Rec21_artikel_bez rec = new Rec21_artikel_bez()._fill_id(artBez.get_lValue());
						if (rec.is_ExistingRecord()) {
							return rec;
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public BigDecimal getAnzahlAusVerteilung() {
			try {
				if (S.isFull(tfMenge.rb_readValue_4_dataobject())) {
					MyBigDecimal menge = new MyBigDecimal(tfMenge.rb_readValue_4_dataobject());
					if (menge.isOK()) {
						BigDecimal bd_menge = menge.get_bdWert();
						if (bd_menge.compareTo(BigDecimal.ZERO)>0) {
							return bd_menge;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
	}
	
	
}

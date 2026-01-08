package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_CONST.SEND_TYPE;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_MessageBoxGetValue;
import panter.gmbh.Echo2.components.E2_MessageBoxGetValue.TYPE_OF_INPUT;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController.WK_BasicHandling;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22Archivmedien;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22EmailSend;

/**
 * button, um eine neu maske zu oeffnen, in der die werte eines ausgewaehlten
 * satzes drinstehen
 * 
 * @author martin
 *
 */
public class WK_RB_bt_Storno extends E2_Button {
	private RB_TransportHashMap m_transportHashMap = null;

	private VEK<XX_ActionAgent> addOnActionsOkAfter = new VEK<>();
	private VEK<XX_ActionAgent> addOnActionsOkBefore = new VEK<>();

	private MyE2_String _msg_text_mindestens_eine_zeile_markieren = new MyE2_String(
			"Es muss mindestens 1 Zeile markiert sein!");
	private MyE2_String _msg_text_warnung_aenderung = new MyE2_String(
			"Soll die ausgwählte Wiegekarte storniert werden?");
	private MyE2_String _msg_text_warnung_aenderung_mit_param = new MyE2_String(
			"Sollen die #WERT# ausgewählten Wiegekarten storniert werden?");

	WK_BasicHandling baseHandler = null;
	
	public WK_RB_bt_Storno(RB_TransportHashMap p_tpHashMap) {
		super();
		this._image(E2_ResourceIcon.get_RI("storno.png"), E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new actionDoStorno());
		this._setTransportHashMap(p_tpHashMap);

		this.add_GlobalValidator(WK_RB_VALIDATORS.STORNO.getValidator());
	}

	public RB_TransportHashMap getTransportHashMap() {
		return m_transportHashMap;
	}

	public WK_RB_bt_Storno _setTransportHashMap(RB_TransportHashMap transportHashMap) {
		this.m_transportHashMap = transportHashMap;
		baseHandler = new WK_BasicHandling(m_transportHashMap);
		return this;
	}

	public WK_RB_bt_Storno set_message_text_mindestens_eine_irgendwas_markieren(String text) {
		_msg_text_mindestens_eine_zeile_markieren = new MyE2_String(text);
		return this;
	};

	public WK_RB_bt_Storno set_warnung_achtung_es_wird_ein_irgendwas_geloescht(String text) {
		_msg_text_warnung_aenderung = new MyE2_String(text);
		return this;
	};

	/**
	 * z.B."Sollen die #WERT# ausgewählten Wiegekarten storniert werden?" #WERT# ist
	 * der platzhalter
	 * 
	 * @author manfred
	 * @date 17.09.2020
	 *
	 * @param text
	 * @return
	 */
	public WK_RB_bt_Storno set_warnung_achtung_es_werden_n_irgendwas_geloescht_mit_platzhalter_fuer_zahl(String text) {
		// #WERT# ist der platzhalter
		_msg_text_warnung_aenderung_mit_param = new MyE2_String(text);
		return this;
	}

	
	
	private class actionDoStorno extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			WK_RB_bt_Storno oThis = WK_RB_bt_Storno.this;

			MyE2_MessageVector mv = bibMSG.MV();

			// ID holen und prüfen, ob auch nur einez Zeile ausgewählt ist
			Vector<Long> v_ids = getIdsToDelete(mv);

			if (bibMSG.get_bIsOK()) {
				if (v_ids.size() == 0) {
					bibMSG.MV()._addAlarm(oThis._msg_text_mindestens_eine_zeile_markieren);
				} else {

					// statements generieren
					Vector<String> v_sql = oThis.get_sql_statements(v_ids, bibMSG.MV());

					if (bibMSG.MV().isOK()) {
						// all in der liste markieren, die storniert werden sollen
						VEK<String> vid = new VEK<>();
						for (Long l : v_ids) {
							vid._a(l.toString());
						}
						oThis.getTransportHashMap().getNavigationList()._clearAllMarkers()._setMarkedIds(vid);

						// Ausführen der Stornierung
//						new PopUpSindSieSicher(v_ids ,v_sql);
						new PopUpStornoGrund(v_ids)
							._setText_Cancel("Abbrechen")
							._setText_OK("Stornieren")
							._setText_InputDescription("Stornogrund")
							._setSizeOfInputField(400)
							._setTypeOfInput(TYPE_OF_INPUT.STRING)
							._addChangeListener((o) -> {
								
									// changelistener wird nur durch OK aufgerufen
									String value = o.getValue();
									
									
									if (S.isFull(value)) {
										System.out.println("Stornogrund: " + value);
										Long idStorno = v_ids.get(0);
										
										_doStorno(idStorno, value, mv);

										Component comp = o._getComponentExtra();
										MyE2_CheckBox cb = (MyE2_CheckBox)comp;
										
										Long id_new = null;
										if(cb.isSelected()) {
											id_new = _doCreateKorrekturWK(idStorno, mv);
										}
										
										
										try {
											
											
											if (id_new != null) {
												WK_RB_bt_Storno.this.getTransportHashMap().getNavigationList().ADD_NEW_ID_TO_ALL_VECTORS(id_new.toString());
												
//												WK_RB_bt_Storno.this.getTransportHashMap().getNavigationList()._REBUILD_COMPLETE_LIST("");
//												WK_RB_bt_Storno.this.getTransportHashMap().getNavigationList().gotoSiteWithID_orFirstSite(id_new.toString());
												
												E2_ComponentMAP map = WK_RB_bt_Storno.this.getTransportHashMap().getNavigationList().get_ComponentMAP(id_new.toString());
												if (map != null) {
													WK_RB_LIST_bt_ListToMaskInListRowKENNZEICHEN btnOpen = (WK_RB_LIST_bt_ListToMaskInListRowKENNZEICHEN) map.get__Comp_From_RealComponents(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_KENNZEICHEN.db_val());
													btnOpen.doActionPassiv();
												} else {
													mv._addInfo("Der neue Datensatz konnte nicht automatisch ermittelt werden.");
												}
											}
										} catch (myException e) {
											e.printStackTrace();
										}
										
									} else {
										mv._addAlarm("Der Stornogrund darf nicht leer sein.");
									}

									return mv;

								})
							._show();

						;

					}
				}
			}
		}

	}

	
	
	/**
	 * Stornieren der Wiegekarte
	 * @author manfred
	 * @date 25.03.2021
	 *
	 * @param ID
	 * @param Stornogrund
	 * @param mv
	 * @return
	 */
	public WK_RB_bt_Storno _doStorno(Long ID, String Stornogrund, MyE2_MessageVector mv)  {

		baseHandler._storniereWiekgekarte(ID, Stornogrund, mv);
		
		return this;
	}


	/**
	 * Anlegen einer Korrekturwiegekarte
	 * @author manfred
	 * @date 25.03.2021
	 *
	 * @param ID
	 * @param mv
	 * @return
	 */
	private Long _doCreateKorrekturWK(Long ID , MyE2_MessageVector mv)  {

		Long idKorr = null;
		try {
			idKorr = baseHandler._createStornoKorrekturWiegekarte(ID.toString(), mv, true);
			
		} catch (myException e) {
			mv._addInfo("Korrekturwiegekarte konnte nicht angelegt werden.");
		}
		
		return idKorr;
	}
	
	
	
	
	
	private class PopUpStornoGrund extends E2_MessageBoxGetValue {
		/**
		 * @author manfred
		 * @date 09.02.2021
		 *
		 * @throws myException
		 */
		public PopUpStornoGrund(Vector<Long> vIDs) throws myException {
			super();
			this._setWidth(550);
			this._setText_TitelZeile("Wiegekarte stornieren...");
			
			MyE2_CheckBox cbKopie = new MyE2_CheckBox("Kopie der Wiegekarte erzeugen.");
			// Default: Kopie erzeugen
			cbKopie.setSelected(true);
			this._setComponentExtra(cbKopie);
			
		}
	}



	/**
	 * holt die id zum kopieren, kann ueberschrieben werden
	 * 
	 * @return
	 */
	public Vector<Long> getIdsToDelete(MyE2_MessageVector mv) throws myException {

		if (this.m_transportHashMap.getNavigationList() != null) {

			Vector<Long> v_l = new Vector<>();
			Vector<String> v = this.m_transportHashMap.getNavigationList().get_vSelectedIDs_Unformated();

			if (v.size() == 1) {
				MyLong l = new MyLong(v.get(0));
				if (l.isOK()) {
					v_l.add(l.get_oLong());
				} else {
					v_l = null;
					mv._addAlarm(S.ms("Fehler beim feststellen der IDs!"));
				}
			} else {
				mv._addAlarm("Es muß genau eine Zeile für den Storno-Vorgang ausgewählt sein!");
			}

			return v_l;
		}

		throw new myException(this, "NavigationList is not in the transport-hashmap");

	}


	
	public Vector<String> get_sql_statements(Vector<Long> vIDs, MyE2_MessageVector mv) throws myException {
		Vector<String> v_retSQL = new Vector<>();

		// alle IDs durchgehen und sqlStatements bauen...
		for (Long id_to_manipulate : vIDs) {

			MyLong lid = new MyLong(id_to_manipulate);

			if (lid.isOK()) {
				RecDOWiegekarte recWK = new RecDOWiegekarte(MASK_STATUS.EDIT)._fill_id(lid.get_oLong());
				if (recWK._is_Storno()) {
					mv._addAlarm(S.ms(String.format(
							"Die Wiegekarte mit der ID %S wurde schon storniert! Der Vorgang wurde abgebrochen.",
							recWK.get_fs_lastVal(WIEGEKARTE.id_wiegekarte))));
					// vorgang abbrechen
					break;
				} else {
					recWK._setNewVal(WIEGEKARTE.storno, "Y", mv);
					v_retSQL.add(recWK.get_SQL_UPDATE_STATEMENT(null, true));

				}
			} else {
				mv._addAlarm(S.ms("Fehler beim Erstellen der Update-Statements!"));
			}

		}

		return v_retSQL;
	};

	public VEK<XX_ActionAgent> getAddOnActionsOkAfterDeleteAction() {
		return addOnActionsOkAfter;
	}

	public VEK<XX_ActionAgent> getAddOnActionsOkBeforeDeleteAction() {
		return addOnActionsOkBefore;
	}

}

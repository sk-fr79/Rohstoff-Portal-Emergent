
package rohstoff.businesslogic.bewegung2.list;

import java.text.SimpleDateFormat;
import java.util.Date;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_IF_Rec21;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_MessageBox;
import panter.gmbh.Echo2.components.DB.E2_Button_DbSimple;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_DEL_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_bgVector;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;

public abstract class B2_ListBtDeleteAbstract extends E2_Button_DbSimple {

	private enum statusDel {
		deleted, undeleted
	}

	private RB_TransportHashMap m_tpHashMap;

	public abstract VEK<String> getIdVectorsToDel();

	public B2_ListBtDeleteAbstract(RB_TransportHashMap p_tpHashMap) {
		super();
		this.m_tpHashMap = p_tpHashMap;

		// Wenn mengen/preise erfasst sind, nicht erlaubt
		this._addValidator(()->{ 
			MyE2_MessageVector mv = bibMSG.newMV();
			for (String id: getIdVectorsToDel()) {
				Rec21_bgVector rec = (Rec21_bgVector)new Rec21_bgVector()._fill_id(id);
				mv._add(rec.checkDeletable());
			}
			return mv;
		});

		
		
		this._setShapeMuellLeerButton();

		this.setToolTipText(new MyE2_String("Fuhre in dieser Zeile loeschen").CTrans());

		this.add_GlobalValidator(ENUM_VALIDATION.BG_TRANSPORT_DELETE.getValidator());

		this._aaa(() -> {

			if (this.getIdVectorsToDel().size() == 0) {
				bibMSG.MV()._addAlarm(S.ms("Sie müssen Zeilen zum Löschen oder Wiederherstellen markieren !"));
			} else {

				statusDel status = checkIfAllSameStatus();
				if (status == null) {
					bibMSG.MV()._addAlarm(S.ms("Sie haben sowohl gelöschte als auch ungelöschte ausgewählt ! "));

				} else if (status == statusDel.undeleted) {
					new Popup4EditDeleteInfos();
				} else {
					new E2_MessageBox()._setYesText("Wiederherstellen")._setNoText("Abbruch")
							._setTitleOfPopup(S.ms("Gelöschte Fuhre(n) wiederherstellen?"))
							._addActionInFrontYesBt(() -> {

								MyE2_MessageVector mv = new MyE2_MessageVector();
								
								for (String s : this.getIdVectorsToDel()) {
									Rec21_bgVector rv = new Rec21_bgVector( new Rec21(_TAB.bg_vektor)._fill_id(s));
									mv._add(rv.unDelete());
								}
								bibMSG.MV()._add(mv);
								
							})._addActionYesBt(() -> {
								this.getTpHashMap().getNavigationList()._REBUILD_ACTUAL_SITE(null);
								this.getTpHashMap().getNavigationList().Mark_ID_IF_IN_Page(this.getIdVectorsToDel());
							})._addInfo("Bei " + this.getIdVectorsToDel().size() + " Fuhren Löschmarkierung")
							._addInfo("entfernen ?")._show(300, 180);
				}
			}
		});

	}

	private statusDel checkIfAllSameStatus() throws myException {
		VEK<statusDel> v = new VEK<>();

		VEK<String> ids = this.getIdVectorsToDel();

		for (String id : ids) {
			Rec21 rv = new Rec21(_TAB.bg_vektor)._fill_id(id);
			if (S.isEmpty(rv.getUfs(BG_VEKTOR.id_bg_del_info))) {
				v._addIfNotIN(statusDel.undeleted);
			} else {
				v._addIfNotIN(statusDel.deleted);
			}

		}

		if (v.size() == 1) {
			return v.get(0);
		} else {
			return null;
		}

	}

	private class Popup4EditDeleteInfos extends E2_BasicModuleContainer {

		RB_TextArea t = new RB_TextArea()._sizeWH(200, 80);
		E2_Button bSave = new E2_Button()._setShapeDeleteButton()._t(S.ms("Löschen"))._s_BorderText();
		E2_Button bCancel = new E2_Button()._setShapeCancelAndClose()._t(S.ms("Abbruch"))._s_BorderText();

		public Popup4EditDeleteInfos() throws myException {
			super();

			E2_Grid g = new E2_Grid();

			g._setSize(200, 200)
					._a(new RB_lab(S.ms("Bitte geben Sie Löschinformationen ein:")),
							new RB_gld()._span(2)._ins(2, 5, 2, 10))

					._a(new RB_lab(S.ms("Grund für den Löschvorgang:")), new RB_gld()._ins(2, 2, 2, 2))
					._a(t, new RB_gld()._ins(2, 2, 2, 2))._a(new E2_Grid()._setSize(90, 90)
							._a(bSave, new RB_gld()._ins(3))._a(bCancel, new RB_gld()._ins(3)));

			bCancel._aaa(() -> {
				CLOSE_AND_DESTROY_POPUPWINDOW(true);
			});
			bSave._aaa(new ActionExecuteDeletion());
			bSave._aaa(() -> {
				CLOSE_AND_DESTROY_POPUPWINDOW(true);
			});

			this.add(g, E2_INSETS.I(5, 5, 5, 5));
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(450), new Extent(250),
					S.ms("Bitte bestätigen Sie das Löschen der Warenbewegung(en) !"));
		}

		private class ActionExecuteDeletion extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {

				t.mark_Neutral();
				if (S.isEmpty(t.getText())) {
					bibMSG.MV()._addAlarm(S.ms("Bitte ausfüllen !"));
					t.mark_FalseInput();
				} else {

					VEK<String> ids = getIdVectorsToDel();
					VEK<Rec20> sqls = new VEK<>();

					for (String s : ids) {

						Rec21 recDel = new Rec21(_TAB.bg_del_info);
						recDel._setNewVal(BG_DEL_INFO.id_user, Long.parseLong(bibALL.get_ID_USER()), bibMSG.MV())
								._setNewVal(BG_DEL_INFO.delete_datum, new Date(), bibMSG.MV())
								._setNewVal(BG_DEL_INFO.delete_grund, t.getText(), bibMSG.MV());

						sqls._a(recDel);

						// E2_ComponentMAP_IF_Rec21 map = (E2_ComponentMAP_IF_Rec21)
						// B2_ListBtDeleteAbstract.this.EXT().get_oComponentMAP();

						Rec21_bgVector recV = (Rec21_bgVector) new Rec21_bgVector()._fill_id(s);
						sqls._a(recV);
						HMAP<RB_KM,Rec21> allRecordToVektor = recV.getCompleteStackOfRecords();

						if (allRecordToVektor != null) {
							recV._put_raw_value(BG_VEKTOR.id_bg_del_info, BG_DEL_INFO._tab().seq_currval(), false);
							allRecordToVektor.get(RecA1.key)._put_raw_value(BG_ATOM.id_bg_del_info,		BG_DEL_INFO._tab().seq_currval(), false);
							allRecordToVektor.get(RecA2.key)._put_raw_value(BG_ATOM.id_bg_del_info,		BG_DEL_INFO._tab().seq_currval(), false);
							allRecordToVektor.get(RecS1.key)._put_raw_value(BG_STATION.id_bg_del_info,	BG_DEL_INFO._tab().seq_currval(), false);
							allRecordToVektor.get(RecS2.key)._put_raw_value(BG_STATION.id_bg_del_info,	BG_DEL_INFO._tab().seq_currval(), false);
							allRecordToVektor.get(RecS3.key)._put_raw_value(BG_STATION.id_bg_del_info,	BG_DEL_INFO._tab().seq_currval(), false);
							sqls._a(allRecordToVektor.get(RecA1.key));
							sqls._a(allRecordToVektor.get(RecA2.key));
							sqls._a(allRecordToVektor.get(RecS1.key));
							sqls._a(allRecordToVektor.get(RecS2.key));
							sqls._a(allRecordToVektor.get(RecS3.key));
						}
					}
					bibMSG.MV()._add(bibDB.execMultiRecSave(sqls, true));
					if (bibMSG.get_bIsOK()) {
						// neubauen und markieren
						m_tpHashMap.getNavigationList()._REBUILD_ACTUAL_SITE("");
						m_tpHashMap.getNavigationList().Mark_ID_IF_IN_Page(ids);
						bibMSG.MV()._addInfo(S.ms("").ut("" + ids.size() + " ").t(" Fuhren gelöscht !"));
					}
				}
			}
		}

	}

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}

	/**
	 * spezielle methode zum ein permanent enables object doch noch disablen zu
	 * koennen
	 * 
	 * @param enabled
	 * @throws myException
	 */
	public void setEnabledForEditOfSuperClass(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(enabled);
	}

	public RB_TransportHashMap getTpHashMap() {
		return m_tpHashMap;
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		try {
			E2_ComponentMAP_IF_Rec21 map = ((E2_ComponentMAP_IF_Rec21) this.EXT().get_oComponentMAP());
			Rec21 rec = map.getRec21();
			if (S.isEmpty(rec.getUfs(BG_VEKTOR.id_bg_del_info))) {
				this._setShapeMuellLeerButton();
				this.setToolTipText(new MyE2_String("Fuhre in dieser Zeile löschen").CTrans());
			} else {
				this._setShapeMuellVollButton();
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
				Rec21 del = ((B2_ListComponentMap) this.EXT().get_oComponentMAP()).getRec21()
						.get_up_Rec21(BG_DEL_INFO.id_bg_del_info);
				Rec21 user = del.get_up_Rec21(USER.id_user);
				String tooltips = "Status: Gelöscht\n" + "von:  " + user.get_ufs_kette(" ", USER.vorname, USER.name1)
						+ "\nam:  " + sdf.format((Date) del.getRawVal(BG_DEL_INFO.delete_datum)) + "\nGrund:\n"
						+ del.getFs(BG_DEL_INFO.delete_grund) + "\n\n"
						+ S.ms("Fuhre in kann mit diesem Button wiederhergestellt werden").CTrans();
	
				this.setToolTipText(tooltips);
			}
		} catch (Exception e) {
			this._t("Error");
			e.printStackTrace();
		}

	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._setShapeMuellLeerButton();
	}

}

package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.SEP;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.WE_CM__Collector;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_BEWEGUNG_ATOM_ext;


public class SEP_OpenMASK extends E2_BasicModuleContainer {

	private E2_Grid  					internalGrid = new E2_Grid();
	
	private MyBigDecimal  				baseQuantity = null; 			//basis-menge
	private MyBigDecimal  				sumSubQuantity = null; 			//verteil-mengen-summe
	
	private SEP_OpenRowCollection    	rows = new SEP_OpenRowCollection();
	
	private MyE2_Button     			bt_save = (MyE2_Button)new MyE2_Button(new MyE2_String("Speichern"))._bordBlack()._backDDark()._center()._font(new E2_FontBold(2))._aaa(new ownActionSaveMask());
	private MyE2_Button     			bt_cancel = (MyE2_Button)new MyE2_Button(new MyE2_String("Abbruch"))._bordBlack()._backDDark()._center()._font(new E2_FontBold(2))._aaa(new ownActionCancelMask());
	
	private RECORD_BEWEGUNG_ATOM_ext 	recAtom = null;
	private WE_CM__Collector  			f_CM_Collector =null;
	
	public SEP_OpenMASK(RECORD_BEWEGUNG_ATOM  p_atom, WE_CM__Collector own_CM_Collector) throws myException {
		super();
		
		this.f_CM_Collector = own_CM_Collector;
		
		this.add(this.internalGrid, E2_INSETS.I(2,2,2,2));
		
		this.recAtom = new RECORD_BEWEGUNG_ATOM_ext(p_atom);
		this.baseQuantity = new MyBigDecimal(recAtom.bd(BEWEGUNG_ATOM.menge, BigDecimal.ZERO, 3),3);
		this.sumSubQuantity=new MyBigDecimal(BigDecimal.ZERO, 3);

		MyE2_MessageVector  mv_sammler = new MyE2_MessageVector();
		
		if (this.recAtom.is_atom_WE_MAIN()) {
			if (mv_sammler.get_bIsOK()) {
				this._render_list();
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(700), new Extent(500), new MyE2_String("Aufteilung eines Wareneingangs ..."));
			} else {
				bibMSG.add_MESSAGE(mv_sammler);
			}
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Prüfung des Atoms zeigt keine korrekte WE-Struktur!!!")));
		}
		
	}

	
	public SEP_OpenMASK _add_new_Row() throws myException {
		this.rows.add(new SEP_OpenRow(this));
		this._render_list();
		
		return this;
	}
	
	
	
	public void _render_list() throws myException {
		
		this.internalGrid.removeAll();
		this.internalGrid._setSize(370,200,25,25,25);
		
		Insets  i_std = E2_INSETS.I(2,2,2,2);
		
		this.internalGrid	._a(new MyE2_Label(new MyE2_String("Aufteilen der Warenbewegung (erzeugt neue Warenbewegungen)"),	MyE2_Label.STYLE_TITEL_BIG()),new RB_gld()._span(5)._ins(E2_INSETS.I(2,2,2,10)))
							
							._a(new MyE2_Label(new MyE2_String("Basismenge"),							MyE2_Label.STYLE_NORMAL_BOLD()),new RB_gld()._span(1)._ins(i_std))
							._a(new MyE2_Label(this.baseQuantity.get_FormatedRoundedNumber(3),			new E2_FontBold()), new RB_gld()._span(1)._right_top()._ins(i_std))
							._a(new MyE2_Label("",														new E2_FontBold()), new RB_gld()._span(3)._ins(i_std))
							._a(new MyE2_Label(new MyE2_String("Summe verteilt"),						MyE2_Label.STYLE_NORMAL_BOLD()),new RB_gld()._span(1)._ins(i_std))
							._a(new MyE2_Label(this.sumSubQuantity.get_FormatedRoundedNumber(3),		new E2_FontBold()), new RB_gld()._span(1)._right_top()._ins(i_std))
							._a(new MyE2_Label("",														new E2_FontBold()), new RB_gld()._span(3)._ins(i_std))
							
							._a(new MyE2_Label("",														new E2_FontBold()), new RB_gld()._span(5)._ins(E2_INSETS.I(2,2,2,10)))
							;
		
		
		RB_gld glst = new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._right_top();
		
		int i=0;
		for (SEP_OpenRow r: this.rows) {
			this.internalGrid	._a(r.get_searchArtBez(),	glst)
								._a(r.get_tf_menge(),		glst);
			if (r.isStatusOpen()) {
				this.internalGrid._a(r.get_bt_close(),	glst)._a(r.get_bt_delete(), glst);
			}else {
				this.internalGrid._a(r.get_bt_open(),	glst)._a(r.get_bt_delete(), glst);
			}
			if (i==this.rows.size()-1) {
				this.internalGrid._a(r.get_bt_add(),	glst);
			} else {
				this.internalGrid._a(new MyE2_Label(""), glst);
			}
			i++;
		}

		this.internalGrid	._a(new E2_Grid()._a(this.bt_save)._a(this.bt_cancel, new RB_gld()._ins(E2_INSETS.I(10,0,0,0))), new RB_gld()._span(5)._ins(E2_INSETS.I(2,10,2,2))._left_top());
	}
	
	
	public boolean calculate_liste() throws myException {
		boolean b_rueck = true;
		
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		BigDecimal bd_sum = this.rows.calculate_liste(mv);
		if (!mv.get_bIsOK()) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Summation fehlerhaft !")));
			bibMSG.add_MESSAGE(mv);
			b_rueck =false;
		}
		this.sumSubQuantity = new MyBigDecimal(bd_sum, 3);
		return b_rueck;
	}

	
	
	private class ownActionSaveMask extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SEP_OpenMASK oThis = SEP_OpenMASK.this;
			if (oThis.calculate_liste()) {
				if (oThis.baseQuantity.get_bdWert().compareTo(oThis.sumSubQuantity.get_bdWert())<0) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie können nicht mehr verteilen als die Basismenge !")));
				} else {
					Vector<String> v_sql = new Vector<>();

					MyBigDecimal ausgangsmenge = 	new MyBigDecimal(oThis.get_recAtom().bd(BEWEGUNG_ATOM.menge,BigDecimal.ZERO,3),3);

					//sammler fuer die zugefuegten ids
					Vector<String>  v_new_ids = new Vector<>();
					
					//vektor-pos-nummer hier fortlaufend vergeben (ab 2, da die erste immer die hauptpos ist)
					for (SEP_OpenRow r: rows) {
						if (!r.is_empty()) {
							String id_bewegung_vektor_new = bibDB.get_NextSequenceValueOfTable(_TAB.bewegung_vektor);
							v_new_ids.add(id_bewegung_vektor_new);
							v_sql.addAll(r.sql_save_stack(bibMSG.MV(), ausgangsmenge,id_bewegung_vektor_new));
						}
					}
					if (bibMSG.MV().get_bIsOK()) {

						//dann die neue menge einfuegen und das statement an den sql-stack anhaengen
						RECORD_BEWEGUNG_ATOM  ra = oThis.get_recAtom();
						ra.nv(BEWEGUNG_ATOM.menge, ausgangsmenge.get_bdWert(), bibMSG.MV());
						
						if (bibMSG.MV().get_bIsOK()) {
							v_sql.add(ra.get_SQL_UPDATE_STD());
							
							DEBUG.System_print(v_sql, true);
							
							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(v_sql, true));
							
							if (bibMSG.MV().get_bIsOK()) {
	
								//den alten satz auf der maske refreshen
								oThis.f_CM_Collector.re_open(MASK_STATUS.VIEW);
								
								//hier jetzt die neuen saetze auf der maske anzeigen
								for (String id : v_new_ids) {
									oThis.f_CM_Collector.get_my_maskContainer().add_edit_WE(id);
								}
								
								oThis.f_CM_Collector.get_my_maskContainer().rebuild_container_grid();
								oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
							}
						}
					}
				}
					
			}
		}
	}
	
	private class ownActionCancelMask extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SEP_OpenMASK oThis = SEP_OpenMASK.this;
			oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}

	
	public SEP_OpenRowCollection get_rows() {
		return rows;
	}


	
	public RECORD_BEWEGUNG_ATOM get_recAtom() {
		return recAtom;
	}




}

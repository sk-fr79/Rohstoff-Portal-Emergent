/**
 * rohstoff.businesslogic.bewegung2.list.listComponents
 * @author martin
 * @date 15.01.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.list.listComponents;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import panter.gmbh.BasicInterfaces.Service.PdServiceBewertungAbrechnungsStatus.ENUM_STATUS_UEBERGANG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.S;
import panter.gmbh.indep.Triple;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_bgVector;
import rohstoff.businesslogic.bewegung2.global.B2_InfoBlockFremdwaehrung;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;

/**
 * @author martin
 * @date 15.01.2020
 *
 */
public class B2_ListComponentDiverseButtons extends E2_UniversalListComponent {

	
	private RB_TransportHashMap m_tpHashMap;
	
	/**
	 * @author martin
	 * @date 15.01.2020
	 *
	 */
	public B2_ListComponentDiverseButtons(RB_TransportHashMap tpHashMap) {
		
		this.m_tpHashMap = tpHashMap;
		
		this.EXT().set_oCompTitleInList(new E2_Grid()._w(60)._setSize(60)
							._a(new RB_lab()._t("RE/GS"),new RB_gld()._center_top()._ins(0,0,0,0)._col_back_d())
							._a(new RB_lab()._t("Verteil."),new RB_gld()._center_top()._ins(0,4,0,0)._col_back_d()));
		
		this.EXT().set_oLayout_ListElement(new RB_gld()._center_top());
		this.EXT().set_oLayout_ListTitelElement(new RB_gld()._center_top()._col_back_d());
	}

	@Override
	public String key() throws myException {
		return this.getClass().getName()+"31b8cd1a-37a1-11ea-aec2-2e728ce88125";
	}

	@Override
	public String userText() throws myException {
		return "RE/GS";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear();

		try {
			
			B2_ListComponentMap map = (B2_ListComponentMap)this.EXT().get_oComponentMAP();
			
			Rec21_bgVector recVector = new Rec21_bgVector(map.getRec21());
			
			Pair<ENUM_STATUS_UEBERGANG> statusLeftRight = recVector.getStatusAbrechnungLeftRight();
			
			OwnButton btLeft = new OwnButton();
			OwnButton btRight = new OwnButton();
			
			switch (statusLeftRight.getVal1()) {
				case ALTE_FUHRE:  	btLeft._setTypeAlt();			break;
				case FREMD: 		btLeft._setTypeFremd();			break;
				case GUTSCHRIFT: 	btLeft._setTypeGutschrift();	break;
				case LAGER: 		btLeft._setTypeEigen();			break;
				case RECHNUNG: 		btLeft._setTypeRechnung();		break;
				case UNDEFINDED: 	btLeft._setTypeUndefined();		break;
				default:			btLeft._setTypeUndefined();		break;
			}

			switch (statusLeftRight.getVal2()) {
				case ALTE_FUHRE:  	btRight._setTypeAlt();			break;
				case FREMD: 		btRight._setTypeFremd();		break;
				case GUTSCHRIFT: 	btRight._setTypeGutschrift();	break;
				case LAGER: 		btRight._setTypeEigen();		break;
				case RECHNUNG: 		btRight._setTypeRechnung();		break;
				case UNDEFINDED: 	btRight._setTypeUndefined();	break;
				default:			btRight._setTypeUndefined();	break;
			}

			E2_Grid inhalt = new E2_Grid()._setSize(20,20)._bo_ddd();
				
			inhalt._a(btLeft)._a(btRight);
			inhalt._a(new InfoBlockFremdWaehrung(EnPositionStation.LEFT))._a(new InfoBlockFremdWaehrung(EnPositionStation.RIGHT));
			inhalt._a(new B2_ListBtVerteilerOffen(this.m_tpHashMap, new Rec21_bgVector(recVector)), new RB_gld()._span(2)._ins(0,5,2,5)._center_mid());
			
			this._a(inhalt);
			
		} catch (Exception e) {
			e.printStackTrace();
			this._setSize(40)._a(new RB_lab()._t("Error"), new RB_gld()._center_top()._ins(2,5,2,2)._col_back_alarm());
		}
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		B2_ListComponentDiverseButtons ret = new B2_ListComponentDiverseButtons(this.m_tpHashMap);
		ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
		return ret;
	}

	
	
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}

	
	private class OwnButton extends E2_Button {

		public OwnButton() {
			super();
			this._setWidth(20);
			this._setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
		}

		public OwnButton _setTypeAlt() {
			this._t("X")._fs(12)._b();
			this._fc(Color.DARKGRAY);
			this._ttt(S.ms("Alte Fuhre, Abrechnung wird in der Fuhrenzentrale gemacht!"));
			return this;
		}

		public OwnButton _setTypeGutschrift() {
			this._t("G")._fs(12)._b();
			this._ttt(S.ms("Gutschriftsposition"));
			return this;
		}

		public OwnButton _setTypeRechnung() {
			this._t("R")._fs(12)._b();
			this._ttt(S.ms("Rechnungsposition"));
			return this;
		}

		public OwnButton _setTypeEigen() {
			this._t("E")._fs(12)._b()._fc(Color.DARKGRAY);
			this._ttt(S.ms("Eigenware"));
			return this;
		}

		public OwnButton _setTypeFremd() {
			this._t("F")._fs(12)._b()._fc(Color.DARKGRAY);
			this._ttt(S.ms("Fremdware"));
			return this;
		}
		
		public OwnButton _setTypeUndefined() {
			this._t("?")._fs(12)._b()._fc(Color.DARKGRAY);
			this._ttt(S.ms("Undefinerter Besitzübergang !"));
			
			return this;
		}
		
		
		@Override
		public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		}
	}
	
	
	private class InfoBlockFremdWaehrung extends B2_InfoBlockFremdwaehrung {
		
		private Rec21_bgVector rec21_bgVector = null;
		
		/**
		 * @author martin
		 * @date 04.03.2020
		 *
		 * @param p_posStation
		 */
		public InfoBlockFremdWaehrung(EnPositionStation p_posStation) {
			super(p_posStation);
			
			try {
				B2_ListComponentMap map = (B2_ListComponentMap)B2_ListComponentDiverseButtons.this.EXT().get_oComponentMAP();
				rec21_bgVector = new Rec21_bgVector(map.getRec21());
				this._fill();
			} catch (myException e) {
				this._clear()._a(new RB_lab()._t("@Err")._ttt(e.getMessage()), new RB_gld()._center_mid()._col_back_alarm());
				e.printStackTrace();
			} catch (Exception e) {
				this._clear()._a(new RB_lab()._t("@Err")._ttt(e.getMessage()), new RB_gld()._center_mid()._col_back_alarm());
				e.printStackTrace();
			}
		}

		@Override
		public Triple<Long> getIdBesitzerLeftMidRight() throws Exception {
			
			Triple<Long> t = new Triple<Long>();
			
			t._setVal1(rec21_bgVector.getCompleteStackOfRecords().get(RecS1.key).getLongDbValue(BG_STATION.id_adresse_besitz_ldg));
			t._setVal2(rec21_bgVector.getCompleteStackOfRecords().get(RecS2.key).getLongDbValue(BG_STATION.id_adresse_besitz_ldg));
			t._setVal3(rec21_bgVector.getCompleteStackOfRecords().get(RecS3.key).getLongDbValue(BG_STATION.id_adresse_besitz_ldg));

			return t;
		}

		@Override
		public Pair<Long> getIdVposKonVposStdLeft() throws Exception {
			Pair<Long> p = new Pair<Long>();
			p._setVal1(rec21_bgVector.getCompleteStackOfRecords().get(RecA1.key).getLongDbValue(BG_ATOM.id_vpos_kon));
			p._setVal2(rec21_bgVector.getCompleteStackOfRecords().get(RecA1.key).getLongDbValue(BG_ATOM.id_vpos_std));
			
			return p;
		}

		@Override
		public Pair<Long> getIdVposKonVposStdRight() throws Exception {
			Pair<Long> p = new Pair<Long>();
			p._setVal1(rec21_bgVector.getCompleteStackOfRecords().get(RecA2.key).getLongDbValue(BG_ATOM.id_vpos_kon));
			p._setVal2(rec21_bgVector.getCompleteStackOfRecords().get(RecA2.key).getLongDbValue(BG_ATOM.id_vpos_std));
			
			return p;
		}
		
	}
	
}

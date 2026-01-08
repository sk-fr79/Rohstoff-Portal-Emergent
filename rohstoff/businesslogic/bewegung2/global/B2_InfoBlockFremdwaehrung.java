/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 02.03.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.BasicInterfaces.Service.PdServiceBewertungAbrechnungsStatus;
import panter.gmbh.BasicInterfaces.Service.PdServiceBewertungAbrechnungsStatus.ENUM_STATUS_UEBERGANG;
import panter.gmbh.BasicInterfaces.Service.PdServiceCreateFremdWaehrungLabel;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.RB.BASICS.IF_RbComponentWithOwnKey;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.O;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.S;
import panter.gmbh.indep.Triple;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 02.03.2020
 *
 */
public abstract class B2_InfoBlockFremdwaehrung extends E2_Grid implements IF_RB_Component, IF_RbComponentWithOwnKey {

	public abstract Triple<Long> 	getIdBesitzerLeftMidRight() throws Exception;   	//liefert die 3 Besitzer (idAdresse) links, mitte, rechts
	public abstract Pair<Long> 		getIdVposKonVposStdLeft() 	throws Exception;   	//liefert die 2 Ids VposKon und VposStd (oder null,null) links 
	public abstract Pair<Long> 		getIdVposKonVposStdRight() 	throws Exception;   	//liefert die 2 Ids VposKon und VposStd (oder null,null) links 
	
	private EnPositionStation  	posStation =  null;
	private boolean             useInList = false;
	
	
	/**
	 * @author martin
	 * @date 02.03.2020
	 *
	 */
	public  B2_InfoBlockFremdwaehrung(EnPositionStation p_posStation) {
		posStation = p_posStation;
	}

	
	public B2_InfoBlockFremdwaehrung _fill() throws Exception {
		
		this._clear()._setSize(50)._h(20);
		try {
			
			Triple<Long>  	idBesitzerLeftMidRight = this.getIdBesitzerLeftMidRight();

			if (idBesitzerLeftMidRight!=null && O.isNoOneNull(idBesitzerLeftMidRight.getVal1(),idBesitzerLeftMidRight.getVal2(),idBesitzerLeftMidRight.getVal3())) {

				Pair<Long>    	idVposKonStd = null;
				Long 			idBesitzerAbrech = null;
				if (posStation==EnPositionStation.LEFT) {
					idVposKonStd = 		this.getIdVposKonVposStdLeft();
					idBesitzerAbrech = 	idBesitzerLeftMidRight.getVal1();
				} else if (posStation==EnPositionStation.RIGHT) {
					idVposKonStd = 		this.getIdVposKonVposStdRight();
					idBesitzerAbrech = 	idBesitzerLeftMidRight.getVal3();
				} else {
					throw new myException("B2_InfoBlockFremdwaehrung: Only EnPositionStation.LEFT / RIGHT are allowed!");
				}

				PdServiceBewertungAbrechnungsStatus pdServiceBewertungAbrechnungsStatus = new PdServiceBewertungAbrechnungsStatus();
				PdServiceCreateFremdWaehrungLabel   pdServiceCreateFremdWaehrungLabel   = getServiceCreateFremdWaehrungLabel();
				
				ENUM_STATUS_UEBERGANG status = (posStation==EnPositionStation.LEFT) ? 
						pdServiceBewertungAbrechnungsStatus.getStatusLeft(idBesitzerLeftMidRight.getVal1(), idBesitzerLeftMidRight.getVal2(), idBesitzerLeftMidRight.getVal3()):
						pdServiceBewertungAbrechnungsStatus.getStatusRight(idBesitzerLeftMidRight.getVal1(), idBesitzerLeftMidRight.getVal2(), idBesitzerLeftMidRight.getVal3());
					
				switch (status) {
				case ALTE_FUHRE: 	this._a(new RB_lab()._t("Fu")._ttt(S.ms("Beleg aus alter Fuhre, keine Abrechnung hier !"))._f(new E2_FontItalic())._col_fore_dgrey(), new RB_gld()._center_mid());
					break;
				case FREMD:  		this._a(new RB_lab()._t("F")._ttt(S.ms("Fremdware, keine Abrechnung"))._f(new E2_FontItalic(4))._col_fore_dgrey(), new RB_gld()._center_mid());
					break;
				case GUTSCHRIFT: 	this._a(pdServiceCreateFremdWaehrungLabel.createLabel(idBesitzerAbrech, idVposKonStd.getVal1(), idVposKonStd.getVal2()), new RB_gld()._center_mid());
					break;
				case LAGER: 		this._a(new RB_lab()._icon(E2_ResourceIcon.get_RI("firma.png")), 			new RB_gld()._center_mid());
					break;
				case RECHNUNG:		this._a(pdServiceCreateFremdWaehrungLabel.createLabel(idBesitzerAbrech, idVposKonStd.getVal1(), idVposKonStd.getVal2()), new RB_gld()._center_mid());
					break;
				case UNDEFINDED:	this._a(new RB_lab()._t("?")._ttt(S.ms("Undefinierte Währung"))._f(new E2_FontItalic(4))._col_fore_dgrey(), new RB_gld()._center_mid());
					break;
				}
				
			} else {
				this._clear()._setSize(50)._a(new RB_lab()._t("?")._ttt("Währungrelation undefiniert"), new RB_gld());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			this._clear()._setSize(50)._a(new RB_lab()._t("@Error@")._ttt(e.getMessage()), new RB_gld()._col_back_alarm());
		}
		
		return this;
	}

	


	@Override
	public RB_KF getFieldKey() {
		return B2_InfoBlockFremdwaehrung.key();
	}

	public static RB_KF key() {
		return new RB_KF()._setHASHKEY(B2_InfoBlockFremdwaehrung.class.getName()+"<3e7f22ba-5c81-11ea-bc55-0242ac130003>")._setREALNAME(B2_InfoBlockFremdwaehrung.class.getName());
	}
	


	@Override
	public void mark_Neutral() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
	}

	private RB_KF key = null;
	
	@Override
	public RB_KF rb_KF() throws myException {
		return key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.key = key; 
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return null;
	}


	public boolean isUseInList() {
		return useInList;
	}


	public B2_InfoBlockFremdwaehrung _setUseInList(boolean useInList) {
		this.useInList = useInList;
		return this;
	}


	protected PdServiceCreateFremdWaehrungLabel getServiceCreateFremdWaehrungLabel() {
		return new PdServiceCreateFremdWaehrungLabel();
	}
	
	
}

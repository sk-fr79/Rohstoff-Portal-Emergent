/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 13.02.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_FieldForVisualizeValueGrid;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;

/**
 * @author martin
 * @date 13.02.2019
 *
 */
public class B2_AdressOwnerLabel extends RB_FieldForVisualizeValueGrid {

	private EnPositionStation m_posStation = null;
	
	public B2_AdressOwnerLabel(EnPositionStation posStation ) {
		super();
		this.m_posStation = posStation;
	}

	@Override
	public void convertDbValueToVisibleLabel(String dbValFormated) {
		
		
		if (m_posStation==EnPositionStation.MID) {
			this._clear()._setSize(250,30)._w100();
		} else {
			this._clear()._setSize(500,30)._w100();
		}
		//RB_gld layout = new RB_gld()._col_back_d();
		RB_gld layout = new RB_gld();
				
		if (m_posStation==EnPositionStation.LEFT) {
			layout._center_top()._ins(2, 2, 2, 2);
		} else if (m_posStation==EnPositionStation.MID) {
			layout._center_top()._ins(2, 2, 2, 2);
		} else {
			layout._center_top()._ins(2, 2, 2, 2);
		}
		
		this._bo_ddd();
		
		MyLong l = new MyLong(dbValFormated);
		
		RB_lab lb = new RB_lab()._f(new E2_FontPlain());
		
		this._a(lb, layout);
		
		if (l.isNotOK()) {
			if (S.isEmpty(dbValFormated)) {
				lb.setText("<"+S.ms("Undefiniert").CTrans()+">");
			} else {
				lb.setText("<"+S.ms("Fehler").CTrans()+">");
			}
		} else {
			try {
				Rec21_adresse ra = new Rec21_adresse()._fill_id(l.get_lValue());
				String namensfeld = ra.get__Name_flexible(" ");
				lb.setText(namensfeld);
				lb._fsa(-2);
				if (namensfeld.length()>40) {
					lb.setText(namensfeld.substring(0, 40)+" ...");
					lb._ttt(namensfeld);
				}
			} catch (myException e) {
				e.printStackTrace();
				lb.setText("");
				bibMSG.MV()._addAlarm(S.ms("Fehler beim Anzeigen der Besitzer !"));
			}
			
		}
		
		
	}

}

/**
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import panter.gmbh.Echo2.Messaging.MessagePopupImmediate;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.FUHREN_KOSTEN_TYP;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.StammDaten.FuhrenKostenTypen.EN_KOSTENTYP_VORSCHLAEGE;

/**
 * @author martin
 * Service, der prueft, ob es einen Eintrag im 
 * 
 */
public class PdServiceCheckAndAddRoutingKostenTyp {
		
	public boolean isKostenTypRoutingSingularAndPresent() throws myException {
		boolean bRet = false;
		if (this.getCountKostenTypRouting()==1) {
			bRet=true;
		}
		return bRet;
	}

	
	public int getCountKostenTypRouting() throws myException {
		RecList20 rlKosten = new RecList20(_TAB.fuhren_kosten_typ)._fill(new ownSel().s());
		return rlKosten.size();
	}


	/**
	 * 
	 * @return s singular Rec20(_TAB.fuhren_kosten_typ) or null, when not present or not singular
	 * @throws myException
	 */
	public Rec20 getRecTypRouting() throws myException {
		RecList20 rlKosten = new RecList20(_TAB.fuhren_kosten_typ)._fill(new ownSel().s());
		if (rlKosten.size()==1) {
			return rlKosten.get(0);
		}
		return null;
	}

	
	
	
	public void writeKostenTypRouting(MyE2_MessageVector mv) throws myException {
		
		MyE2_MessageVector mvLoc = new MyE2_MessageVector();
		
		Rec20 rec = new Rec20(_TAB.fuhren_kosten_typ)
				._setNewVal(FUHREN_KOSTEN_TYP.kurztext_uebersicht, 	EN_KOSTENTYP_VORSCHLAEGE.ROUTE.dbVal(), mvLoc)
				._setNewVal(FUHREN_KOSTEN_TYP.neutral, 				"N", mvLoc)
				._setNewVal(FUHREN_KOSTEN_TYP.speditionsrechnung, 	"N", mvLoc)
				._setNewVal(FUHREN_KOSTEN_TYP.betrifft_zoll, 		"N", mvLoc)
				._setNewVal(FUHREN_KOSTEN_TYP.text4benutzer, 		"Kosten aus Routenplaner", mvLoc)
				;
		
		if (mvLoc.get_bIsOK()) {
			try {
				rec._SAVE(true, mvLoc);
				
			} catch (Exception e) {
				e.printStackTrace();
				mvLoc.add_MESSAGE(new MyE2_Alarm_Message(e.getLocalizedMessage()));
			}
		}
		
		if (mv==null) {
			if (!mvLoc.isEmpty()) {
				new MessagePopupImmediate()._renderMessages(mvLoc)._showPopup();
			} 
		} else {
			mv.add_MESSAGE(mvLoc);
		}
		
	}
	
	
	private class ownSel extends SEL {

		/**
		 * @param tab
		 * @throws myException
		 */
		public ownSel() throws myException {
			super(_TAB.fuhren_kosten_typ);
			this.FROM(_TAB.fuhren_kosten_typ).WHERE(new And(new vgl(FUHREN_KOSTEN_TYP.kurztext_uebersicht,EN_KOSTENTYP_VORSCHLAEGE.ROUTE.dbVal()))
					.and(new vgl(FUHREN_KOSTEN_TYP.neutral, "N"))
					.and(new vgl(FUHREN_KOSTEN_TYP.speditionsrechnung, "N"))
					.and(new vgl(FUHREN_KOSTEN_TYP.betrifft_zoll, "N")));
		}
		
	}
	
}

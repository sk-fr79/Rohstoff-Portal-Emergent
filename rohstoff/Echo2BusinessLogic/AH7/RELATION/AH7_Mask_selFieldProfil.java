/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7_ENUM_SONDERPROFILE;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_AH7_Profil;

/**
 * @author martin
 *
 */
public class AH7_Mask_selFieldProfil extends RB_selField {

	/**
	 * @throws myException 
	 * 
	 */
	public AH7_Mask_selFieldProfil() throws myException {
		super();
		
		SEL selSonder = new SEL(_TAB.ah7_profil).FROM(_TAB.ah7_profil).ORDERUP(AH7_PROFIL.bezeichnung).WHERE(new vgl(AH7_PROFIL.profile4allothers,				AH7_ENUM_SONDERPROFILE.NOPROFILE.db_val()));
		SEL selSpec =  new SEL(_TAB.ah7_profil).FROM(_TAB.ah7_profil).ORDERUP(AH7_PROFIL.bezeichnung).WHERE(new vgl(AH7_PROFIL.profile4allothers,COMP.NOT_EQ,	AH7_ENUM_SONDERPROFILE.NOPROFILE.db_val()));
		
		
		this._populate(new RecList20(_TAB.ah7_profil)._fill(selSonder.s()),AH7_PROFIL.bezeichnung,AH7_PROFIL.id_ah7_profil,true);
		this._populate(new RecList20(_TAB.ah7_profil)._fill(selSpec.s()),AH7_PROFIL.bezeichnung,AH7_PROFIL.id_ah7_profil,true);
		
		this._aaa(new ownAction());
	}

	
	/**
	 * das selektierte profil anwenden
	 * @author martin
	 *
	 */
	private class ownAction extends XX_ActionAgent {

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			String val = AH7_Mask_selFieldProfil.this.getActualDbVal();
			
			MyLong l = new MyLong(val);
			
			MyE2_MessageVector mv = new MyE2_MessageVector();
			RB_MaskController c = new RB_MaskController(AH7_Mask_selFieldProfil.this);
			if (l.isOK()) {

				Rec21  rProfil = new Rec21_AH7_Profil(new Rec21(_TAB.ah7_profil)._fill_id(l.get_lValue()));
				
				MyLong idAdresseStartGeo = c.getMyLongMaskVal(AH7_STEUERDATEI.id_adresse_geo_start);
				MyLong idAdresseZielGeo = c.getMyLongMaskVal(AH7_STEUERDATEI.id_adresse_geo_ziel);
				
				if (idAdresseStartGeo.isOK() && idAdresseZielGeo.isOK()) {
					
					AH7__hmTranslateProfileToRealAdressIds mapp = new AH7__hmTranslateProfileToRealAdressIds(idAdresseStartGeo.get_cUF_LongString(), idAdresseZielGeo.get_cUF_LongString(), rProfil, mv);
					
					if (mapp.isBlock1GanzVoll() && mapp.isBlock2GanzVollOderLeer() && mapp.isBlock3GanzVollOderLeer()) {
						for (IF_Field f: mapp.keySet()) {
							c.set_maskVal(f, mapp.get(f), mv);
						}
					} else {
						AH7__hmFieldListEmptyValues emptyMap = new AH7__hmFieldListEmptyValues();
						for (IF_Field f: emptyMap.keySet()) {
							c.set_maskVal(f, emptyMap.get(f), mv);
						}
						bibMSG.MV()._addAlarm("Einer oder mehrere Blöcke sind unvollständig aufgeloest. Evtl. fordert das Profil eine nicht vorhandene Drittbesitzer-Adresse !");
					}
				}
			} else {
//				AH7__hmFieldListEmptyValues emptyMap = new AH7__hmFieldListEmptyValues();
//				for (IF_Field f: emptyMap.keySet()) {
//					c.set_maskVal(f, emptyMap.get(f), mv);
//				}
			}
			((RB_cb)c.get_comp(AH7_STEUERDATEI.drucke_blatt2, mv)).doActionPassiv();
			((RB_cb)c.get_comp(AH7_STEUERDATEI.drucke_blatt3, mv)).doActionPassiv();
			
			bibMSG.add_MESSAGE(mv);
		}
		
	}
	
}

/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7._services;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 *
 */
public class _PdServiceCheckAH7AdresseIsReady4AH7Print {
	
	private enum enFieldsMustBeFilled {
		name1(ADRESSE.name1, null)
		,strasse(ADRESSE.strasse, null)
		,ort(ADRESSE.ort, null)
		,eu_beiblatt_ansprech(ADRESSE.eu_beiblatt_ansprech, null)
		,eu_beiblatt_email(ADRESSE.eu_beiblatt_email, null)
		,eu_beiblatt_tel(ADRESSE.eu_beiblatt_tel, null)
		,eu_beiblatt_fax(ADRESSE.eu_beiblatt_fax, null)
		
		;
		private IF_Field m_field; 
		private String   m_lesbar;
		
		private enFieldsMustBeFilled(IF_Field field, String lesbar) {
			this.m_field	=	field;
			this.m_lesbar 	= 	lesbar;
		}

		public IF_Field getField() {
			return m_field;
		}
		
		public String getLesbar() {
			return S.NN(this.m_lesbar,this.name());
		}
		
	}
	
	
	public boolean isReadyForAH7Print(Long idAdress, MyE2_MessageVector mv) throws myException {
		boolean ret = true;
		if (idAdress==null || idAdress<=0) {
			throw new myException(this, "idAdress MUST not be null !");
		}
		
		Rec21_adresse adress = new Rec21_adresse()._fill_id(idAdress);
		if (adress.is_newRecordSet()) {
			throw new myException(this, "Adresse-ID "+idAdress+" cannot be found !");
		}
		
		for (enFieldsMustBeFilled f: enFieldsMustBeFilled.values()) {
			if (S.isEmpty(adress.getUfs(f.getField()))) {
				mv._addAlarm(S.ms("Fehlende Angabe in Adresse: ").ut("<"+f.getLesbar()+">: ").ut(adress.get__FullNameAndAdress_flexibleWithId(", ")));
			}
		}
		
		return ret;
	}
}
 
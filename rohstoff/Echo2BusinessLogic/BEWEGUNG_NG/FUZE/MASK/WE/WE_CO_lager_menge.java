package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_vektor;

/**
 * sondertextfeld fuer die behandlung der lagerseitigen menge, falls diese beim Wareneingang von der Lieferantenmenge abweicht (nachgezogene implizite mengenkorrekturbuchung
 * @author martin
 *
 */
public class WE_CO_lager_menge extends RB_TextAnzeige {



	/**
	 * @param i_width
	 * @throws myException 
	 */
	public WE_CO_lager_menge(int i_width) throws myException {
		super(i_width);
		
		this.rb_VALIDATORS_4_INPUT().add(new RB_Validator_Component() {
			
			@Override
			public MyE2_MessageVector do_Validate(IF_RB_Component rb_Component) throws myException {
				MyE2_MessageVector mv = new MyE2_MessageVector();
				
				WE_CO_lager_menge oThis = WE_CO_lager_menge.this; 
				
				
				if (!S.isEmpty(oThis.getText())) {
					MyBigDecimal  bd_menge = new MyBigDecimal(oThis.getText());
					if (bd_menge.isNotOK()) {
						mv.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Achtung! Keine korrekte Zahl im Feld: Lagermenge!")));
					}
				}
				
				return mv;
			}
		});

	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {

		//zuerst die menge des "hauptatoms" laden, damit der Anwender im normalfall das "richtige" sieht
		super.rb_Datacontent_to_Component(dataObject);
		
		
		MyBigDecimal bd = null; 
		
		//zuerst den dataobject-collector in den korrekten typ casten
		WE_DO_Atom do_atom = (WE_DO_Atom) dataObject;
		
		if (do_atom.get_rec20().is_newRecordSet()) {
			this.setText("");
		} else {
			Rec20 			r_vektor_pos = do_atom.get_rec20().get_up_rec20(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor_pos);
			Rec20_vektor 	r_vektor = new Rec20_vektor(r_vektor_pos.get_up_rec20(BEWEGUNG_VEKTOR.id_bewegung_vektor));
		
			bd = r_vektor._get_menge_WE_MENGENKORREKTUR();
			if (bd!=null && bd.isOK()) {
				this.setText(bd.get_FormatedRoundedNumber(3));
			}
		}
	}

	
	
	
}

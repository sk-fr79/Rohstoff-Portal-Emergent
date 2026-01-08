package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class AT_MASK_triggername extends RB_TextField {

	/**
	 * @param i_width
	 * @param i_max_input_size
	 * @throws myException 
	 */
	public AT_MASK_triggername(int i_width, int i_max_input_size) throws myException {
		super(i_width, i_max_input_size);
		
		this.rb_VALIDATORS_4_INPUT().add(new RB_Validator_Component() {
			
			@Override
			public MyE2_MessageVector do_Validate(IF_RB_Component rb_Component) throws myException {
				MyE2_MessageVector mv = new MyE2_MessageVector();
				
				String text = S.NN(AT_MASK_triggername.this.getText());
				String text_neu = text.toUpperCase();
				text_neu = bibALL.CleanString4DBName_NG(text_neu);
				
				if (!text.equals(text_neu)) {
					AT_MASK_triggername.this.setText(text_neu);
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte verwenden Sie im Triggernamen nur Grossbuchstaben oder Ziffern, keine Sonderzeichen, keine Umlaute!")));
				}
				
				
				return mv;
			}
		});
		
	}

}

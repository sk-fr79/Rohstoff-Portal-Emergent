package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_MANDANT_ext;

public class AW2_InfoLabel extends MyE2_Label {

	public AW2_InfoLabel() throws myException {
		super("");
	}
	
	public void set_number(int iCount) throws myException {
		GridLayoutData  gl = new GridLayoutData();
		gl.setAlignment(new Alignment(Alignment.CENTER, Alignment.TOP));
		gl.setInsets(E2_INSETS.I(0,0,0,0));
		if (iCount<=0){
			gl.setBackground(null);
		} else {
			gl.setBackground(new RECORD_MANDANT_ext(bibALL.get_RECORD_MANDANT()).get_COLOR_MASK_HIGHLIGHT());
		}
		this.setText(""+iCount);
		this.setLayoutData(gl);
	}
	
}

/**
 * 
 */
package panter.gmbh.Echo2.RB.COMP;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * Komponente, die bei rein passiv befuellten Datenbankfeldern benutzt werden kann, um z.B. eine ID_ADRESSE, die der 
 * Anwender nicht eingeben kann, in eine sprechende Adresse umzusetzen. Diese gibt aber die ID_ADRESSSE an die Datenbank zurueck
 */
public abstract class RB_TextLabelWithReplacement  extends RB_TextField {
	
	private String dbValueUnformated = null;
	
	public abstract String getReplaceDBValToView(String dbValFormated) throws myException;
	public abstract void   formatField(boolean enabled);
	
	
	/**
	 * 
	 */
	public RB_TextLabelWithReplacement() {
		super();
	}

	/**
	 * @param i_width
	 * @param i_max_input_size
	 */
	public RB_TextLabelWithReplacement(int i_width, int i_max_input_size) {
		super(i_width, i_max_input_size);
	}

	/**
	 * @param i_width
	 */
	public RB_TextLabelWithReplacement(int i_width) {
		super(i_width);
	}
	
	
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		
		if (dataObject.get_RecORD()==null) {
			this.dbValueUnformated = null;
		} else {
			this.dbValueUnformated = ((MyRECORD)dataObject.get_RecORD()).fs(this.rb_KF().FIELDNAME(),"");
		}
		this.setText(S.NN(this.getReplaceDBValToView(this.dbValueUnformated)));
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.dbValueUnformated = valueFormated;
		
		this.setText(S.NN(this.getReplaceDBValToView(this.dbValueUnformated)));
	}

	@Override
	public String rb_readValue_4_dataobject()  {
		return this.dbValueUnformated;
	}


	/**
	 * ist immer disabled
	 */
	@Override
	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException {
		super.set_bEnabled_For_Edit(false);
		this.formatField(bEnabled);
	}

	
	@Override
	public void mark_Neutral()  {
		this.formatField(true);
	}

	@Override
	public void mark_MustField() {
		this.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
	}

	@Override
	public void mark_Disabled()  {
		this.formatField(true);
	}

	@Override
	public void mark_FalseInput()  {
		this.formatField(true);
	}

	
}

package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.UI.BEWEG;

import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class B_LIST_SQL_FieldMAP extends Project_SQLFieldMAP {

	public B_LIST_SQL_FieldMAP() throws myException {
		super(_DB.BEWEGUNG,":"+_DB.BEWEGUNG$ID_BEWEGUNG+":",false);
		
		this.add_SQLField(new B_LIST_SQLFIELD_NAME_1ST_ATOM_1ST_VEKTOR(), true);
		this.add_SQLField(new B_LIST_SQLFIELD_NAME_LAST_ATOM_LAST_VEKTOR(), true);
		this.add_SQLField(new B_LIST_SQLFIELD_ARTIKEL_FIRT_ATOM_FIRST_VEKTOR(), true);
		
		this.initFields();
	}

}

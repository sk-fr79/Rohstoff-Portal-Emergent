package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.KOSTEN_LIEFERBED_ADR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FSK_SQLFieldMAP extends SQLFieldMAP {

	public FSK_SQLFieldMAP() throws myException {
		super("JT_KOSTEN_LIEFERBED_ADR");
		
		this.addCompleteTable_FIELDLIST( "JT_KOSTEN_LIEFERBED_ADR",
				":"+_DB.KOSTEN_LIEFERBED_ADR$ID_KOSTEN_LIEFERBED_ADR+
				":"+_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE_BASIS+
				":"+_DB.KOSTEN_LIEFERBED_ADR$ID_MANDANT+
				":"+_DB.KOSTEN_LIEFERBED_ADR$GEAENDERT_VON+
				":"+_DB.KOSTEN_LIEFERBED_ADR$LETZTE_AENDERUNG+":", false, true, "");

		this.add_SQLField(new SQLFieldForPrimaryKey( _DB.KOSTEN_LIEFERBED_ADR, _DB.KOSTEN_LIEFERBED_ADR$ID_KOSTEN_LIEFERBED_ADR, _DB.KOSTEN_LIEFERBED_ADR$ID_KOSTEN_LIEFERBED_ADR,
							new MyE2_String("ID-Transportkosten"), bibE2.get_CurrSession(),
							"SELECT " + bibALL.get_TABLEOWNER()	+ ".SEQ_KOSTEN_LIEFERBED_ADR.NEXTVAL FROM DUAL", true), false); 

		this.add_SQLField(new SQLFieldForRestrictTableRange( _DB.KOSTEN_LIEFERBED_ADR, _DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE_BASIS,  _DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE_BASIS,
							new MyE2_String("ID-ADRESSE"), "",bibE2.get_CurrSession()), false);


		//2016-03-23: sortierung "sprechend", nicht nach ID... 
		this.add_JOIN_Table(_TAB.adresse.n(), "STA", SQLFieldMAP.LEFT_OUTER, ADRESSE.name1.fn()+":"+ ADRESSE.name2.fn()+":"+ ADRESSE.ort.fn(), true, "STA.ID_ADRESSE="+KOSTEN_LIEFERBED_ADR.id_adresse.tnfn(), "START_", null);
		this.add_JOIN_Table(_TAB.adresse.n(), "ZIEL", SQLFieldMAP.LEFT_OUTER, ADRESSE.name1.fn()+":"+ ADRESSE.name2.fn()+":"+ ADRESSE.ort.fn(), true, "ZIEL.ID_ADRESSE="+KOSTEN_LIEFERBED_ADR.id_adresse_ziel.tnfn(), "ZIEL_", null);
		this.add_JOIN_Table(_TAB.artikel.n(), "ART", SQLFieldMAP.LEFT_OUTER, ARTIKEL.anr1.fn(), true, "ART.ID_ARTIKEL="+KOSTEN_LIEFERBED_ADR.id_artikel.tnfn(), "ART_", null);

		
		this.get_(_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_(_DB.KOSTEN_LIEFERBED_ADR$ID_ARTIKEL).set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);


		this.initFields();

	}

}

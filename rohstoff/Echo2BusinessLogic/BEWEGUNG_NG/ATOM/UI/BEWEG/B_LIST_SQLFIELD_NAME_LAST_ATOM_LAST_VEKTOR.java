package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.UI.BEWEG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class B_LIST_SQLFIELD_NAME_LAST_ATOM_LAST_VEKTOR extends SQLField {

	public B_LIST_SQLFIELD_NAME_LAST_ATOM_LAST_VEKTOR() throws myException {
		super("(SELECT NVL(S.ORT,AD.NAME1) FROM JT_BEWEGUNG_VEKTOR V "+ 
				" INNER JOIN JT_BEWEGUNG_ATOM A ON (V.ID_BEWEGUNG_VEKTOR=A.ID_BEWEGUNG_VEKTOR) "+
				" INNER JOIN JT_BEWEGUNG_STATION S ON (A.ID_BEWEGUNG_ATOM=S.ID_BEWEGUNG_ATOM) "+
				" INNER JOIN JT_ADRESSE AD ON (AD.ID_ADRESSE=S.ID_ADRESSE) "+
				" WHERE     V.ID_BEWEGUNG_VEKTOR = " +
				"               (SELECT MAX(VV.ID_BEWEGUNG_VEKTOR) FROM JT_BEWEGUNG_VEKTOR VV WHERE NVL(VV.DELETED,'N')='N' AND  VV.ID_BEWEGUNG=V.ID_BEWEGUNG)"+
				" AND A.ID_BEWEGUNG_ATOM =  " +
				"               (SELECT MAX(AA.ID_BEWEGUNG_ATOM)    FROM JT_BEWEGUNG_ATOM AA      WHERE NVL(AA.DELETED,'N')='N' AND AA.ID_BEWEGUNG_VEKTOR=V.ID_BEWEGUNG_VEKTOR)"+
                " AND S.MENGENVORZEICHEN= 1 "+
                " AND NVL(A.DELETED,'N')='N' "+
                " AND NVL(V.DELETED,'N')='N' "+
                " AND  V.ID_BEWEGUNG = JT_BEWEGUNG.ID_BEWEGUNG )", 
                B__CONST.COLKEY_ZIELORT, new MyE2_String("Letzer Abladeort") , bibE2.get_CurrSession());
	}

}

package panter.gmbh.Echo2.ListAndMask.List.calculation;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.vectorForSegmentation;


/**
 * calc-regel fuer summationen, basierend auf eine datenspalte, die einen numerischen wert zurueckgibt
 * @author martin
 *
 */
public class CALC_Rule_SUMMATION_LIST_COL extends CALC_Rule_ABSTRACT {

	private static 					String ERR_Code = 	"@ERROR@"; 
	private MyE2IF__DB_Component  	o_DB_Component = 	null;
	
	
	
	public CALC_Rule_SUMMATION_LIST_COL(MyE2IF__DB_Component oDB_Component) {
		super();
		this.o_DB_Component = oDB_Component;
		
		MyE2IF__Component  oComp = (MyE2IF__Component)this.o_DB_Component;         //jede MyE2IF__DB_Component ist auch eine MyE2IF__Component
		
		MyE2_String cInfoText = new MyE2_String("Summe: ");
		if (oComp.EXT().get_cList_or_Mask_Titel()!=null) {
			cInfoText.addString(oComp.EXT().get_cList_or_Mask_Titel());
		} else {
			cInfoText.addString(new MyE2_String(this.o_DB_Component.EXT_DB().get_oSQLField().get_cFieldLabel(),false));
		}
		
		this.set_cINFOTEXT(cInfoText);
		this.set_iDECIMALNUMBERS(this.o_DB_Component.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_FieldDecimals());
	}


	@Override
	public BigDecimal get_bdVALUE_ERGEBNISS(Vector<String> vID_COL) throws myException {

		this.set_cFehlerInfo4User(null);

		BigDecimal  bd_Rueck = new BigDecimal(0);
		
		//summation erfolgt ueber 50-er bloecke, um das in-statement nicht zu lang werden zu lassen
		vectorForSegmentation  vSeq = new vectorForSegmentation();
		vSeq.set_iSegmentGroesse(50);
		
		vSeq.addAll(vID_COL);
		
		int iAnzahlSegmente = vSeq.get_iZahlSegmente();
		
		
		for (int i=0;i<iAnzahlSegmente;i++) {
			Vector<String>  vID_Seq = vSeq.get_vSegment(i);
			
			String cWert = bibDB.EinzelAbfrage(this.get_ValueQuery(vID_Seq), "0", CALC_Rule_SUMMATION_LIST_COL.ERR_Code, CALC_Rule_SUMMATION_LIST_COL.ERR_Code);

			if (cWert.equals(CALC_Rule_SUMMATION_LIST_COL.ERR_Code)) {
				bd_Rueck = null;
				this.set_cFehlerInfo4User(new MyE2_String("Abfragefehler"));
				break;   //schleife abbrechen
			} else {
				MyBigDecimal oBD = new MyBigDecimal(cWert, false);
				if (oBD.get_bOK()) {
					bd_Rueck = bd_Rueck.add(oBD.get_bdWert());
				} else {
					bd_Rueck = null;
					this.set_cFehlerInfo4User(new MyE2_String("Der vorhandene Datenbankwert ist nicht als Zahl zu interpretieren: ",true,cWert,false));
					break;
				}
			}
		}

		return bd_Rueck;
	}


	private String get_ValueQuery(Vector<String> vID_COL) throws myException {

		SQLFieldMAP  	oFM = 			this.o_DB_Component.EXT_DB().get_oSQLFieldMAP();
		String 			FieldAusdruck = this.o_DB_Component.EXT_DB().get_oSQLField().get_cFieldAusdruck();
		
		String cSQLQuery = oFM.get_SQLQuery_OwnFieldBlock("SUM("+FieldAusdruck+")", vID_COL);
		
		return cSQLQuery;
	}



	public MyE2IF__DB_Component get_oDB_Component() {
		return o_DB_Component;
	}



	public void set_oDB_Component(MyE2IF__DB_Component oDB_Component) {
		this.o_DB_Component = oDB_Component;
	}



}

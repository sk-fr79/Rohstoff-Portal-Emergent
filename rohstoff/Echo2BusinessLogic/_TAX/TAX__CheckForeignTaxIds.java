package rohstoff.Echo2BusinessLogic._TAX;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_STD;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN.BL_Kostenberechnung;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.DBUtil;


/**
 * Check a set of tax IDs with the {@link ForeignTaxIDCheckerService} web service
 * and return the results back to the UI.
 */
public class TAX__CheckForeignTaxIds
{
	private Vector<String>  addressIdsWithInvalidUstId= new Vector<String>();
	private MyE2_Label      lbZaehler = new MyE2_Label("0");

	/**
	 * @param checkAddressIds  (wenn null, dann werden alle bearbeitet)
	 * @throws myException 
	 * @throws SQLException 
	 */
	public TAX__CheckForeignTaxIds(Vector<String> checkAddressIds) {
		super();
		addressIdsWithInvalidUstId.removeAllElements();
		
		ArrayList<HashMap<String, Object>> result = null;
		try {
			result = DBUtil.select(new SELECT(
					 _DB.FIRMENINFO$UMSATZSTEUERID, 
					 _DB.FIRMENINFO$UMSATZSTEUERLKZ, 
					 _DB.FIRMENINFO$ID_ADRESSE
			).from(_DB.FIRMENINFO).where(_DB.FIRMENINFO$ID_ADRESSE, "IN", checkAddressIds));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		TaxIdChecker tic = new TaxIdChecker();

		MyE2_Grid oGridAnzeige = new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		oGridAnzeige.add(new MyE2_Label(new MyE2_String("Ich baue die Kostensätze neu auf ...")),3, E2_INSETS.I(5,3,5,3));
		oGridAnzeige.add(this.lbZaehler,MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(5,3,5,3)));
		oGridAnzeige.add(new MyE2_Label("von"),MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(5,3,5,3)));
		oGridAnzeige.add(new MyE2_Label(""+this.addressIdsWithInvalidUstId.size()),MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(5,3,5,3)));
		
		for (HashMap<String, Object> addr : result) {
			String idAdresse = (((BigDecimal)addr.get("ID_ADRESSE")).toString());
			String lkz = (String)addr.get(_DB.FIRMENINFO$UMSATZSTEUERLKZ);
			String nr = (String)addr.get(_DB.FIRMENINFO$UMSATZSTEUERID);
			
			try {
				TaxId tid = new TaxId(lkz, nr);
				if (!tic.isValid(tid)) { 
				//System.out.println("OK="+tid.toString());
					//addressIdsWithInvalidUstId.add(((BigDecimal)addr.get("ID_ADRESSE")).toString());
					mv.add(new MyE2_Warning_Message("Ungülige UStId bei Kunde: "+idAdresse+"; Meldung: "+tic.getMessage()));
				}
			} catch (IllegalArgumentException e) {
				if (nr == null || lkz.equals("") || nr.equals("")) {
					mv.add(new MyE2_Info_Message("Fehlende UStId bei Kunde: "+idAdresse+". "));
				} else {
					mv.add(new MyE2_Info_Message("Ungülige (syntaktische) UStId bei Kunde: "+idAdresse+": "+lkz+nr));
				}
				
			}
		}
		bibMSG.add_MESSAGE(mv);
		
	}

	/** Returns the TaxId for the current Mandant */ 
	private TaxId getMandantTaxId() {
		TaxId tid;
		try {
			String idAdresseMandant = bibALL.get_RECORD_MANDANT()
					.get_EIGENE_ADRESS_ID_cUF();
			HashMap<String, Object> result = DBUtil.selectOne(new SELECT(
					 _DB.FIRMENINFO$UMSATZSTEUERID, 
					 _DB.FIRMENINFO$UMSATZSTEUERLKZ
			).from(_DB.FIRMENINFO).where(_DB.FIRMENINFO$ID_ADRESSE, idAdresseMandant));
			tid = new TaxId(
					(String)result.get(_DB.FIRMENINFO$UMSATZSTEUERLKZ), 
					(String)result.get(_DB.FIRMENINFO$UMSATZSTEUERID)
				);
		} catch (Exception e) {
			// Leber Offenburg DE
			tid = new TaxId("DE142532305");
		}
		return tid;
	}
	
	
	
	
}

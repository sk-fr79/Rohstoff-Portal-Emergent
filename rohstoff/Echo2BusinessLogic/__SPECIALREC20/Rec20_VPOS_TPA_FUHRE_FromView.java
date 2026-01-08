/**
 * 
 */
package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class Rec20_VPOS_TPA_FUHRE_FromView extends Rec20 {

	private String idVPOS_TPA_FUHRE = null;
	private String idVPOS_TPA_FUHRE_ORT = null;
	/**
	 * @param p_tab
	 * @param p_idVPOS_TPA_FUHRE   (wenn es einen fuhrenort gibt, dann kann die fuhre null sein
	 * @param p_idVPOS_TPA_FUHRE_ORT
	 * @throws myException
	 */
	public Rec20_VPOS_TPA_FUHRE_FromView( String p_idVPOS_TPA_FUHRE, String p_idVPOS_TPA_FUHRE_ORT) 	throws myException {
		super(_TAB.vpos_tpa_fuhre);
		this.idVPOS_TPA_FUHRE = p_idVPOS_TPA_FUHRE;
		this.idVPOS_TPA_FUHRE_ORT = p_idVPOS_TPA_FUHRE_ORT;
		
		if (S.isFull(p_idVPOS_TPA_FUHRE)) {
			MyLong l = new MyLong(p_idVPOS_TPA_FUHRE);
			if (l.isNotOK() || l.get_lValue()<=0) {
				throw new myException(this,"param p_idVPOS_TPA_FUHRE must be empty or number>0");
			}
		}
		
		if (S.isFull(p_idVPOS_TPA_FUHRE_ORT)) {
			MyLong l = new MyLong(p_idVPOS_TPA_FUHRE_ORT);
			if (l.isNotOK() || l.get_lValue()<=0) {
				throw new myException(this,"param p_idVPOS_TPA_FUHRE_ORT must be empty or number>0");
			}
		}
		
		if (S.isEmpty(this.idVPOS_TPA_FUHRE) && S.isEmpty(this.idVPOS_TPA_FUHRE_ORT)) {
			throw new myException(this,"Both ids empty is not allowed ");
		} else if (S.isFull(this.idVPOS_TPA_FUHRE_ORT)) {       
			//wenn der fuhrenort voll ist, dann ist die fuhre bestimmt
			SEL s= new SEL("*").FROM("V"+bibALL.get_ID_MANDANT()+"_FUHREN").WHERE(new TermSimple("NVL("+VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort.fn()+",0)="+this.idVPOS_TPA_FUHRE_ORT));
			this._fill_sql(s.s());
		} else if (S.isFull(this.idVPOS_TPA_FUHRE)) {
			//wenn die verzweigung hier landet, dann ist der fuhreort leer 
			SEL s= new SEL("*").FROM("V"+bibALL.get_ID_MANDANT()+"_FUHREN")	.WHERE(new TermSimple("NVL("+VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.fn()+",0)="+this.idVPOS_TPA_FUHRE))
																			.AND(new TermSimple("NVL("+VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort.fn()+",0)="+"0"));
			this._fill_sql(s.s());
		}
	}
	
	
	
	public String getIdVPOS_TPA_FUHRE() {
		return idVPOS_TPA_FUHRE;
	}
	public String getIdVPOS_TPA_FUHRE_ORT() {
		return idVPOS_TPA_FUHRE_ORT;
	}
	
	
	/**
	 * @return  Rec20(_TAB.vpos_tpa_fuhren_ort) or null
	 * @throws myException
	 */
	public Rec20 getRec20FuhrenOrt() throws myException {
		Rec20 r = null;

		String id_fo = this.getOverHeadValueUF(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort);
		
		if (S.isFull(id_fo)) {
			r = new Rec20(_TAB.vpos_tpa_fuhre_ort)._fill_id(id_fo);
		}
		return r;
	}
	
	
}

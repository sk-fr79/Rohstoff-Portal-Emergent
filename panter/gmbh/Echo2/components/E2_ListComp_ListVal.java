package panter.gmbh.Echo2.components;

import java.util.LinkedHashMap;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.DB.MyE2IF_DB_SimpleComponent;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * listenanzeiger, uebersetzt HashMap<String val !!UNFORMATED!! ,String anzeige> val in anzeige, mit listmarker-agent
 * @author martin
 *
 */
public class E2_ListComp_ListVal extends E2_Button implements MyE2IF_DB_SimpleComponent{

	private LinkedHashMap<String, String> 	hmDbvalAnzeige = new LinkedHashMap<String, String>();
	private String                      	fieldKeyToShow = null;
	private String  						anzeigeWhenEmpty = "-";

	
	public E2_ListComp_ListVal() {
		super();
		this._aaa(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				E2_ListComp_ListVal.this.EXT().get_oComponentMAP().set_CheckBoxForList_ToggleSelected();
			}
		});

	}

	public E2_ListComp_ListVal _add(LinkedHashMap<String, String> p_hmDbvalAnzeige) {
		this.hmDbvalAnzeige.putAll(p_hmDbvalAnzeige);
		return this;
	}

	public LinkedHashMap<String, String> gethmDbvalAnzeige() {
		return hmDbvalAnzeige;
	}
	
	// pair 1=dbVal, 2 = anzeige 
	public E2_ListComp_ListVal _add(@SuppressWarnings("unchecked") Pair<String> ... pairs) {
		for (Pair<String> p: pairs) {
			this.hmDbvalAnzeige.put(p.getVal1(), p.getVal2());
		}
		return this;
	}

	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) 	throws myException {
		this._t("<prepareing ...>");
		if (S.isAllFull(this.fieldKeyToShow)) {
			SQLResultMAP map = this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP();
			if (map.get(this.fieldKeyToShow)!=null) {
				String val = map.get(this.fieldKeyToShow).get_FieldValueUnformated();
				if (S.isEmpty(val)) {
					this._t(this.anzeigeWhenEmpty);
				} else {
					if (this.hmDbvalAnzeige.keySet().contains(val)) {
						this._t(this.hmDbvalAnzeige.get(val));
					} else {
						this._t(val);
					}
				}
			} else {
				throw new myException(this,"Error, Fieldkey is not in Resultset!!");
			}
		} else {
			throw new myException(this,"Error, Fieldkey is not set!!");
		}
	}

	
	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._t("-");
	}

	
	
	public String getFieldKeyToShow() {
		return this.fieldKeyToShow;
	}

	
	
	public E2_ListComp_ListVal _setFieldKeyToShow(String p_fieldKeyToShow) {
		this.fieldKeyToShow = p_fieldKeyToShow;
		return this;
	}

	
	
	public String getAnzeigeWhenEmpty() {
		return anzeigeWhenEmpty;
	}


	
	public E2_ListComp_ListVal _setAnzeigeWhenEmpty(String p_anzeigeWhenEmpty) {
		this.anzeigeWhenEmpty = p_anzeigeWhenEmpty;
		return this;
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		E2_ListComp_ListVal ret = new E2_ListComp_ListVal();
		ret.gethmDbvalAnzeige().putAll(this.hmDbvalAnzeige);
		ret._setAnzeigeWhenEmpty(this.getAnzeigeWhenEmpty())._setFieldKeyToShow(this.fieldKeyToShow);
		
		ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
		return ret;

	}

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}
}

/**
 * panter.gmbh.json.defClass
 * @author martin
 * @date 15.06.2020
 * 
 */
package panter.gmbh.json.defClass;

import java.util.List;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.RB.COMP.RB_labInGridNoDatabase;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_HelpButton;
import panter.gmbh.Echo2.components.E2_HelpPopUp;
import panter.gmbh.Echo2.components.E2_PopUp;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 15.06.2020
 *
 */
public class JsonMaskFieldDescription {

   private String  table = null;;
   private String  field = null;
   private String  text  = null;
   private Integer textdiff = 0;
   private Boolean bold = false;
   private Boolean italic = false;
   private Integer topoffset = null;
   private List<String>  infos = null;
   
   private _TAB    _tab = null;

	
	/**
	 * @author martin
	 * @date 15.06.2020
	 *
	 */
	public JsonMaskFieldDescription() {
	}

	
   

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getTextdiff() {
		return textdiff;
	}

	public void setTextdiff(Integer textdiff) {
		this.textdiff = textdiff;
	}

	public Boolean getBold() {
		return bold;
	}

	public void setBold(Boolean bold) {
		this.bold = bold;
	}

	public Boolean getItalic() {
		return italic;
	}

	public void setItalic(Boolean italic) {
		this.italic = italic;
	}

	public RB_labInGridNoDatabase getLabel() {
		RB_labInGridNoDatabase label = new RB_labInGridNoDatabase();
		
		label	._t(S.ms(S.NN(this.text,"<"+S.NN(this.table)+"/"+S.NN(this.field)+">")))
				._fsa(O.NN(this.textdiff,0));
		
		if (O.NN(this.bold, false)) {
			label._b();
		}
		
		if (O.NN(this.italic, false)) {
			label._i();
		}
		
		if (this.topoffset!=null) {
			label._setTopOfset(this.topoffset);
		}
		
		if (this.infos!=null && this.infos.size()>0) {
			VEK<String>  vInfos = new VEK<String>()._a(this.infos); 
			try {
				E2_HelpPopUp popup = new E2_HelpPopUp();
				popup._setIconActiv(E2_ResourceIcon.get_RI("maskhelp_mini.png"));
				int longestLine = 30;  //ergibt mindestbreite
				for (String s: vInfos) {
					if (S.NN(s).length()>longestLine) {longestLine=S.NN(s).length();}
					popup._addTextLine(s,-2);
				}
				label._addComponentAtRight(popup);
				popup._setWidth(new Double(longestLine*5.7).intValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return label;
	}

	public _TAB getTab() {
		return _tab;
	}

	public void setTab(_TAB tab) {
		this._tab = tab;
	}


	public Integer getTopoffset() {
		return topoffset;
	}

	public void setTopoffset(Integer topoffset) {
		this.topoffset = topoffset;
	}



	public List<String> getInfos() {
		return infos;
	}


	public void setInfo(List<String> infos) {
		this.infos = infos;
	}

	
}

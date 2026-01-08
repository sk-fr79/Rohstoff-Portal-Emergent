/**
 * rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE
 * @author manfred
 * @date 20.02.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2IF__Indirect;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Factory_ForUser;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.WF_HEAD_CONST;

/**
 * @author manfred
 * @date 20.02.2019
 *
 */
public class WF_SIMPLE__NaviList extends E2_NavigationList {

	private UTIL_MultiSelectField_Factory_ForUser    _sel_MainUser = null;

	/**
	 * @author manfred
	 * @date 20.02.2019
	 *
	 * @throws myException
	 */
	public WF_SIMPLE__NaviList() throws myException {
		super();

		
		this.add_actionAfterListCompleted(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				for (E2_ComponentMAP map: WF_SIMPLE__NaviList.this.get_vComponentMAPS()) {
					WF_SIMPLE__NaviList.this.mark_user_Components(map);
					WF_SIMPLE__NaviList.this.MarkDeleted(map);
				}
			}
		});

	}

	
	
	/** Highlight all UserFields for selected Main User
	 * 
	 * @author manfred
	 * @date 20.02.2019
	 *
	 * @param map
	 */
	protected void mark_user_Components(E2_ComponentMAP map) throws myException {
		HashMap<String, MyE2IF__Component> hm = map.get_hmRealComponents();
		
		
		Component  o_user_besitzer = (Component)hm.get(WF_SIMPLE_CONST.colname_besitzer_name);
		Component  o_user_bearbeiter = (Component)hm.get(WF_SIMPLE_CONST.colname_bearbeiter_name);
		Component  o_user_abgeschlossen_von = (Component)hm.get(WF_SIMPLE_CONST.colname_abgeschlossen_von_name);
		
		

		String id_user_besitzer = 			S.NN(map.get_oInternalSQLResultMAP().get_UnFormatedValue(LAUFZETTEL_EINTRAG.id_user_besitzer.fn()));
		String id_user_bearbeiter = 		S.NN(map.get_oInternalSQLResultMAP().get_UnFormatedValue(LAUFZETTEL_EINTRAG.id_user_bearbeiter.fn()));
		String id_user_abgeschlossen_von =	S.NN(map.get_oInternalSQLResultMAP().get_UnFormatedValue(LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von.fn()));
		
		Vector<String> id_users = this._sel_MainUser!=null ? this._sel_MainUser.get_SelectedValues() : new Vector<>();

		if (id_users.size() <= 0) {
			// falls nix ausgewählt ist, den Anwender nehmen
			id_users = new Vector<>();
			id_users.addElement(bibALL.get_ID_USER());
		}

		Color   	col_highlight = 		WF_HEAD_CONST.HIGHLIGHT_COLOR_4_USER_FIELDS;
			
		if (o_user_besitzer!=null && id_users.contains(id_user_besitzer)) {
			Component c_rendered = o_user_besitzer;
			if (o_user_besitzer instanceof MyE2IF__Indirect) {
				c_rendered = ((MyE2IF__Indirect)o_user_besitzer).get_RenderedComponent();
			} 
			LayoutDataFactory.change_gridLayoutData( c_rendered, col_highlight);
		}
		
		if (o_user_bearbeiter!=null && id_users.contains(id_user_bearbeiter)) {
			Component c_rendered = o_user_bearbeiter;
			if (o_user_bearbeiter instanceof MyE2IF__Indirect) {
				c_rendered = ((MyE2IF__Indirect)o_user_bearbeiter).get_RenderedComponent();
			} 
			LayoutDataFactory.change_gridLayoutData( c_rendered, col_highlight);
		}
		
		if (o_user_abgeschlossen_von!=null && id_users.contains(id_user_abgeschlossen_von)) {
			Component c_rendered = o_user_abgeschlossen_von;
			if (o_user_abgeschlossen_von instanceof MyE2IF__Indirect) {
				c_rendered = ((MyE2IF__Indirect)o_user_abgeschlossen_von).get_RenderedComponent();
			} 
			LayoutDataFactory.change_gridLayoutData( c_rendered, col_highlight);
		}

	}
	
	
	protected void MarkDeleted(E2_ComponentMAP map) throws myException{
		String isDeleted =	S.NN(map.get_oInternalSQLResultMAP().get_UnFormatedValue(LAUFZETTEL_EINTRAG.geloescht.fn()));
		String isLZDeleted = S.NN(map.get_oInternalSQLResultMAP().get_UnFormatedValue("LZ_GELOESCHT"));
		boolean bDeleted = "Y".equalsIgnoreCase(isDeleted) ||"Y".equalsIgnoreCase(isLZDeleted);  

		
//		MyE2_DB_TextArea txtAufgabe = (MyE2_DB_TextArea) map.get__Comp_From_RealComponents(LAUFZETTEL_EINTRAG.aufgabe.fn());
//    	MyE2_DB_TextArea txtBericht = (MyE2_DB_TextArea) map.get__Comp_From_RealComponents(LAUFZETTEL_EINTRAG.bericht.fn());
//    	boolean bIsDeleted = oMAP.get_bActualDBValue(LAUFZETTEL_EINTRAG.geloescht.fn());
		
		String id = S.NN(map.get_oInternalSQLResultMAP().get_UnFormatedValue(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag.fn()));
		
		if (bDeleted){
			E2_Font oDelFont = new E2_Font(Font.ITALIC + Font.LINE_THROUGH, 0);
			Iterator<Map.Entry<String, MyE2IF__Component>> it = map.get_hmRealComponents().entrySet().iterator();
			while (it.hasNext()){
				Entry<String, MyE2IF__Component> entry =it.next();
				MyE2IF__Component comp = (MyE2IF__Component) entry.getValue();
				if (comp instanceof MyE2_DB_Label_INGRID  ){
					((MyE2_DB_Label_INGRID)comp).setFont(oDelFont);
				} else if (comp instanceof MyE2_DB_TextArea ){
					((MyE2_DB_TextArea)comp).setFont(oDelFont);
				}
			}
		}
	}
	
	
	/**
	 * Set SelectField for MainUser
	 * @author manfred
	 * @date 20.02.2019
	 *
	 * @param selMainUser
	 */
	public void set_MainUser(UTIL_MultiSelectField_Factory_ForUser selMultMainUser){
		this._sel_MainUser = selMultMainUser;
	}
	

}

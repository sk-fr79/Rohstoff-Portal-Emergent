/**
 * rohstoff.businesslogic.bewegung.mask
 * @author martin
 * @date 20.11.2018
 * 
 */
package rohstoff.businesslogic.bewegung2.list;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.exceptions.myException;

public enum B2_ConstEnumNames implements IF_enum_4_db {
    
    CHECKBOX_LISTE( S.ms("Auswahl-Checkbox"))
    ,MARKER_LISTE( S.ms("Markierung Liste"))
    ,DIRECT_DEL( S.ms("Loeschbutton in Listenzeile"))
    ,DIRECT_STORNO( S.ms("Stornobutton in Listenzeile"))
    ,DIRECT_EDIT( S.ms("Editbutton in Listenzeile"))
    ,DIRECT_VIEW( S.ms("Anzeigebutton in Listenzeile"))
    ,DIRECT_UPLOAD( S.ms("Dateien hochladen"))
    ,SHOW_TYPE(S.ms("Typ der Fuhre"))
    ;
    
    private MyE2_String userText = null; 
    private String      m_dbVal = null;
    
    private B2_ConstEnumNames(MyE2_String p_userText) {
        this.userText = p_userText;
    }
    
    //konstuktor mit abweichenden werten
    private B2_ConstEnumNames(MyE2_String p_userText, String dbVal) {
        this.userText = p_userText;
        this.m_dbVal=dbVal;
    }
    
    @Override
    public String db_val() {
     	if (S.isFull(m_dbVal)) {
    		return m_dbVal;
    	}
       return this.name();
    }
    
    @Override
    public String user_text() {
        if (S.isEmpty(this.userText)) {
            return this.name();
        } else {
            return this.userText.CTrans();
        }
    }
    
    @Override
    public String user_text_lang() {
        return this.user_text();
    }
    
    @Override
    public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
        return null;
    }
    
}
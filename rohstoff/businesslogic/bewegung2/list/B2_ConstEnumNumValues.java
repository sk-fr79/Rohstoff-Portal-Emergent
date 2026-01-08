/**
 * rohstoff.businesslogic.bewegung.mask
 * @author martin
 * @date 20.11.2018
 * 
 */
package rohstoff.businesslogic.bewegung2.list;

/*
 * enum: hier koennen numerische (long) werte zentral gesteuert werden 
 */
public enum B2_ConstEnumNumValues {
     MASKPOPUP_WIDTH(new Integer(800))
    ,MASKPOPUP_HEIGHT(new Integer(800))
    ,MASKPOPUP_DESCRIPTION_COL_SIZE(new Integer(100))
    ,MASKPOPUP_FIELD_COL_SIZE(new Integer(700))
     
	;
	
	private Integer   m_value = null;
	
	private B2_ConstEnumNumValues(Integer p_value) {
		this.m_value=p_value;
	}
	
	public Integer getValue() {
		return this.m_value;
	}
}
/**
 * rohstoff.businesslogic.bewegung2.global
 * @author martin
 * @date 08.01.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.indep.enumtools.IF_enumForDb;

/**
 * @author martin
 * @date 08.01.2019
 * alle felder, die in der liste verfuegbar sind
 */
public enum EnBgFieldList  implements IF_enumForDb<EnBgFieldList>{
	ID_BG_TRANSPORT
	,VE_ID_BG_VEKTOR
	,S1_ID_BG_STATION
	,S2_ID_BG_STATION
	,S3_ID_BG_STATION

	,A1_ID_BG_ATOM
	,A2_ID_BG_ATOM
	
	//ab hier feld-definitionen fuer die sql-fieldmap
	,AD1_ID_ADRESSE("AD1.ID_ADRESSE","Startadresse-ID")
	,AD2_ID_ADRESSE("AD2.ID_ADRESSE","Zwischenlager-ID")
	,AD3_ID_ADRESSE("AD3.ID_ADRESSE","Zieladresse-ID")
	,AB1_ID_ARTIKEL_BEZ("AB1.ID_ARTIKEL_BEZ","Sorten-Bezeichner Start-Ladung")
	,AB2_ID_ARTIKEL_BEZ("AB2.ID_ARTIKEL_BEZ","Sorten-Bezeichner Ziel-Ladung")
	
	,AR1_ID_ARTIKEL("AR1.ID_ARTIKEL","Sorte Start-Ladung")
	,AR2_ID_ARTIKEL("AR2.ID_ARTIKEL","Sorte Ziel-Ladung")
	
	,VP1_ID_VPOS_KON("VP1.ID_VPOS_KON","ID-Kontrakt(Start)")
	,VP2_ID_VPOS_KON("VP2.ID_VPOS_KON","ID-Kontrakt(Ziel)")
	,VK1_ID_VKOPF_KON("VK1.ID_VKOPF_KON","ID-KonKopf(Start)")
	,VK2_ID_VKOPF_KON("VK2.ID_VKOPF_KON","ID-KonKopf(Ziel)")
	
	,ADB1_ID_ADRESSE_BASIS("ADB1.ID_ADRESSE","ID-Adresse-Basis Start")
	,ADB2_ID_ADRESSE_BASIS("ADB2.ID_ADRESSE","ID-Adresse-Basis Mittel")
	,ADB3_ID_ADRESSE_BASIS("ADB3.ID_ADRESSE","ID-Adresse-Basis Ziel")
	
	;
	
	private boolean m_forSqlFieldDef = false;
	private String m_fieldTerm = null;
	private String m_fieldName4User = null;
	
	private EnBgFieldList() {
		
	}
	
	private EnBgFieldList(String fieldTerm, String fieldname4user) {
		this.m_forSqlFieldDef=true;
		this.m_fieldTerm = fieldTerm;
		this.m_fieldName4User = fieldname4user;
	}
	
	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enumForDb#getValues()
	 */
	@Override
	public EnBgFieldList[] getValues() {
		return EnBgFieldList.values();
	}

	public boolean isForSqlFieldDef() {
		return m_forSqlFieldDef;
	}

	public String getFieldName4User() {
		return m_fieldName4User;
	}

	public String getFieldTerm() {
		return m_fieldTerm;
	}


	

	
}

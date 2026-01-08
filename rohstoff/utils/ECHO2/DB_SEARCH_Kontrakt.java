package rohstoff.utils.ECHO2;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class DB_SEARCH_Kontrakt extends MyE2_DB_MaskSearchField
{

	public DB_SEARCH_Kontrakt(	SQLField 			osqlField) throws myException
	{
		super(		osqlField, 
					"JT_VPOS_KON.ID_VPOS_KON,JT_VKOPF_KON.NAME1,JT_VKOPF_KON.NAME2,JT_VKOPF_KON.PLZ,JT_VKOPF_KON.ORT,JT_VPOS_KON.ANR1,JT_VPOS_KON.ARTBEZ1", 
					bibE2.cTO()+".JT_VKOPF_KON,"+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VPOS_KON_TRAKT", 
					"NAME1",
					"JT_VKOPF_KON.ID_VKOPF_KON = JT_VPOS_KON.ID_VKOPF_KON AND " +
					"JT_VPOS_KON.ID_VPOS_KON = JT_VPOS_KON_TRAKT.ID_VPOS_KON AND " +
					"NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N' AND " +
					"NVL(JT_VPOS_KON.DELETED,'N')='N' AND NVL(JT_VKOPF_KON.DELETED,'N')='N'", 
					"UPPER(JT_VKOPF_KON.NAME1) LIKE '%#WERT#%' OR " +
					"UPPER(JT_VKOPF_KON.NAME2) LIKE '%#WERT#%'  OR " +
					"UPPER(JT_VKOPF_KON.ORT) LIKE '%#WERT#%' OR " +
					"UPPER(JT_VPOS_KON.ANR1) LIKE '%#WERT#%' OR " +
					"UPPER(JT_VPOS_KON.ARTBEZ1) LIKE '%#WERT#%' OR " +
					"TO_CHAR(JT_VKOPF_KON.ID_ADRESSE)='#WERT#' OR "+ 
					"TO_CHAR(JT_VPOS_KON.ID_VPOS_KON)='#WERT#'", 
					null,
					null,
					"SELECT NVL(JT_VKOPF_KON.NAME1,'-')||' - '|| NVL(JT_VKOPF_KON.ORT,'-')||' - '||trim(to_char(floor( NVL(JT_VPOS_KON.ANZAHL,0))))||' - '|| NVL(JT_VPOS_KON.EINHEITKURZ,'-')||' - '|| NVL(JT_VPOS_KON.ANR1,'-')||' - '|| NVL(JT_VPOS_KON.ARTBEZ1,'-') " +
					" FROM "+bibE2.cTO()+".JT_VKOPF_KON, 	"+bibE2.cTO()+".JT_VPOS_KON, 	"+bibE2.cTO()+".JT_VPOS_KON_TRAKT " +
					" WHERE  " +
					" JT_VKOPF_KON.ID_VKOPF_KON = JT_VPOS_KON.ID_VKOPF_KON AND JT_VPOS_KON.ID_VPOS_KON = JT_VPOS_KON_TRAKT.ID_VPOS_KON AND JT_VPOS_KON_TRAKT.ID_VPOS_KON=#WERT#", null, false);
		
 		this.set_oPosX(null);
		this.set_oPosY(null);

       this.get_oTextForAnzeige().setWidth(new Extent(400));
	}

	
	@Override
	public E2_BasicModuleContainer get_ownContainer() throws myException
	{
		return new ownE2_Container();
	}

	private class ownE2_Container extends E2_BasicModuleContainer
	{
		public ownE2_Container()
		{
			super();
		}
	}
	

	
}

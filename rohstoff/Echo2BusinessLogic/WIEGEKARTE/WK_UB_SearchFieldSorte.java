package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.unboundDataFields.UB_MaskSearchField;
import panter.gmbh.indep.exceptions.myException;

public class WK_UB_SearchFieldSorte extends UB_MaskSearchField
{

	private static final long serialVersionUID = 1165110979296066791L;

	public WK_UB_SearchFieldSorte(String fieldName, boolean EmptyAllowed) throws myException
	{
		super(	fieldName, EmptyAllowed, 
				"B.ID_ARTIKEL_BEZ, NVL(AR.ANR1,'') || '-' || NVL(B.ANR2,'') || ' ' || NVL(B.ARTBEZ1,'')|| NVL2(B.ARTBEZ2,' ' || B.ARTBEZ2,'')  ", 
				bibE2.cTO()+".JT_ARTIKEL AR" +
				" INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL_BEZ B  		ON AR.ID_ARTIKEL = B.ID_ARTIKEL " 
				, "AR.ANR1, B.ANR2 " ,
				" NVL(AR.AKTIV,'N')='Y' " 
					+ " AND NVL(B.AKTIV,'N') = 'Y' "
				, 
				" UPPER(B.ARTBEZ1) LIKE UPPER('%#WERT#%') " +
				" OR UPPER(AR.ANR1) LIKE UPPER('%#WERT#%') " +
				" OR UPPER(AR.ANR1 || '-' || B.ANR2) = '#WERT#' " +
				" OR TO_CHAR(AR.ID_ARTIKEL)='#WERT#' " +
				" OR TO_CHAR(B.ID_ARTIKEL_BEZ)='#WERT#' ", 
				null,
				null, 
				 "SELECT trim( NVL(AR.ANR1,'') || '-' || NVL(B.ANR2,'') || ' ' || NVL(B.ARTBEZ1,'')|| NVL2(B.ARTBEZ2,' ' || B.ARTBEZ2,'')  ) "+
					" from " + 
					bibE2.cTO()+".JT_ARTIKEL AR" +
					" INNER JOIN " + bibE2.cTO() + ".JT_ARTIKEL_BEZ B  		ON AR.ID_ARTIKEL = B.ID_ARTIKEL "  +
					"WHERE " +
					"B.ID_ARTIKEL_BEZ = #WERT# ",
				E2_INSETS.I_0_0_2_0, true,0);
	
		
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

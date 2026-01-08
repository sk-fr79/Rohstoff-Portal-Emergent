package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.VorgangTableNames;

public class BS_PositionSorting
{
	public static final String UP = "UP";
	public static final String DOWN = "DOWN";
	public static final String POS1 = "POS1";
	public static final String END = "END";
	
	
	private String 				ID_VPOS_UNFORMATED = null;
	private VorgangTableNames 	oTN = null;
	private String 				cKOPF_ID = null;
	
	

	public BS_PositionSorting(String cID_VPOS_Unformated,String SORT_TYPE, VorgangTableNames TN) throws myException
	{
		super();
		this.ID_VPOS_UNFORMATED = cID_VPOS_Unformated;
		this.oTN = TN;
		
		String SORTTYPE = bibALL.null2leer(SORT_TYPE);
		
		if (!(SORTTYPE.equals(BS_PositionSorting.UP) || 
			SORTTYPE.equals(BS_PositionSorting.DOWN) ||				
			SORTTYPE.equals(BS_PositionSorting.POS1) ||
			SORTTYPE.equals(BS_PositionSorting.END)))
			throw new myException("BS_PositionSorting:Sortingtype "+SORTTYPE+" not allowed !");
			
		if (bibALL.isEmpty(ID_VPOS_UNFORMATED))
			throw new myException("BS_PositionSorting:Empty Position-ID not allowed !");

		MyDataRecordHashMap oRow = new MyDataRecordHashMap(oTN.get_cVPOS_TAB(),this.ID_VPOS_UNFORMATED);
		
		this.cKOPF_ID = oRow.get_UnFormatedValue(oTN.get_cVKOPF_PK());
		
		if (bibALL.isEmpty(this.cKOPF_ID))
			throw new myException("BS_PositionSorting:Constructor:Error Querying Pos-Row !");
		
		String[][] cPositions = this.get_PositionArray();
		
		// zuerst ab 1 aufwaerts bauen im Abstand von 2
		Vector<String> vSQL = new Vector<String>();
		for (int i=0;i<cPositions.length;i++)
		{
			vSQL.add("UPDATE "+bibE2.cTO()+"."+oTN.get_cVPOS_TAB()+" SET POSITIONSNUMMER="+(2*(i+1))+" WHERE "+oTN.get_cVPOS_PK()+"="+cPositions[i][0]);
		}
		
		if (bibDB.ExecMultiSQLVector(vSQL,true).get_bHasAlarms())
			throw new myException("BS_PositionSorting:Constructor:Error Norming Sort-Positions");
		
		String cChangeSQL = "";
		if (SORTTYPE.equals(BS_PositionSorting.DOWN))
			cChangeSQL = "UPDATE "+bibE2.cTO()+"."+oTN.get_cVPOS_TAB()+" SET POSITIONSNUMMER=POSITIONSNUMMER-3 WHERE "+oTN.get_cVPOS_PK()+"="+this.ID_VPOS_UNFORMATED;
		else if (SORTTYPE.equals(BS_PositionSorting.UP))
			cChangeSQL = "UPDATE "+bibE2.cTO()+"."+oTN.get_cVPOS_TAB()+" SET POSITIONSNUMMER=POSITIONSNUMMER+3 WHERE "+oTN.get_cVPOS_PK()+"="+this.ID_VPOS_UNFORMATED;
		else if (SORTTYPE.equals(BS_PositionSorting.POS1))
			cChangeSQL = "UPDATE "+bibE2.cTO()+"."+oTN.get_cVPOS_TAB()+" SET POSITIONSNUMMER=-1000000 WHERE "+oTN.get_cVPOS_PK()+"="+this.ID_VPOS_UNFORMATED;
		else if (SORTTYPE.equals(BS_PositionSorting.END))
			cChangeSQL = "UPDATE "+bibE2.cTO()+"."+oTN.get_cVPOS_TAB()+" SET POSITIONSNUMMER=1000000 WHERE "+oTN.get_cVPOS_PK()+"="+this.ID_VPOS_UNFORMATED;
		
		if (!bibDB.ExecSQL(cChangeSQL,true))
			throw new myException("BS_PositionSorting:Constructor:Error Changing position of Row");
		
		
		// neu einlesen und wieder sortieren
		cPositions = this.get_PositionArray();
		// zuerst ab 1 aufwaerts bauen im Abstand von 2
		vSQL = new Vector<String>();
		for (int i=0;i<cPositions.length;i++)
		{
			vSQL.add("UPDATE "+bibE2.cTO()+"."+oTN.get_cVPOS_TAB()+" SET POSITIONSNUMMER="+(i+1)+" WHERE "+oTN.get_cVPOS_PK()+"="+cPositions[i][0]);
		}
		
		if (bibDB.ExecMultiSQLVector(vSQL,true).get_bHasAlarms())
			throw new myException("BS_PositionSorting:Constructor:Error final sorting");
		
	}
	

	
	private String[][] get_PositionArray() throws myException
	{
		String[][] cPositions = bibDB.EinzelAbfrageInArray("SELECT "+oTN.get_cVPOS_PK()+",POSITIONSNUMMER  FROM "+
				bibE2.cTO()+"."+oTN.get_cVPOS_TAB()+
				" WHERE "+oTN.get_cVKOPF_PK()+"="+this.cKOPF_ID+ " ORDER BY POSITIONSNUMMER");
	
		if (cPositions == null)
			throw new myException("BS_PositionSorting:Constructor:Error Querying Pos-Row");
		
		
		
		return cPositions;
	}
	
	
	
}

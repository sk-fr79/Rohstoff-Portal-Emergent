package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.exceptions.myException;


/**
 * Loeschhilfe fuer belege. Bei bewegungsaetzen wird nicht mehr der beleg geloescht, sondern
 * nur noch das feld DELETED auf 'Y' gesetzt
 * 
 * @author martin
 *
 */
public class BS_DELETER
{
	private String cTABLE_NAME = null;
	private String cID_ToDelete = null;
	private String cDEL_GRUND = null;
	
	private Vector<String> vSQLUpdates = new Vector<String>();
	
	private String   cMAIN_DELETE_SQL = null;
	
	public BS_DELETER(String TABLE_NAME, String ID_ToDelete, String DEL_GRUND) throws myException
	{
		super();
		this.cTABLE_NAME = 		TABLE_NAME;
		this.cID_ToDelete = 	ID_ToDelete;
		this.cDEL_GRUND = 		DEL_GRUND;
		
		if (bibALL.isEmpty(this.cTABLE_NAME) || 
			bibALL.isEmpty(this.cID_ToDelete) ||
			bibALL.isEmpty(this.cDEL_GRUND))
			throw new myException("BS_DELETER: empty parameters are not allowed !!");
		
		this.cTABLE_NAME = this.cTABLE_NAME.toUpperCase();

		String cUpdateKern = " SET DELETED='Y',DEL_GRUND="+bibALL.MakeSql(this.cDEL_GRUND)+
											",DEL_KUERZEL="+bibALL.MakeSql(bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN("??"))+
											",DEL_DATE="+DB_META.get_tStampString(bibE2.get_DB_KENNUNG());
		
		// jetzt fallunterscheidungen
		if (this.cTABLE_NAME.equals("JT_VKOPF_KON"))
		{
			this.cMAIN_DELETE_SQL = "UPDATE "+bibE2.cTO()+".JT_VKOPF_KON "+cUpdateKern+" WHERE ID_VKOPF_KON="+this.cID_ToDelete;
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_KON "+cUpdateKern+" WHERE ID_VKOPF_KON="+this.cID_ToDelete);
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_KON "+cUpdateKern+" WHERE   NVL(DELETED,'N')='N' AND  ID_VKOPF_KON="+this.cID_ToDelete);
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_KON_TRAKT "+cUpdateKern+" WHERE   NVL(DELETED,'N')='N' AND ID_VPOS_KON IN (SELECT ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_VKOPF_KON="+this.cID_ToDelete+")");

			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET ID_VPOS_KON_EK=NULL WHERE ID_VPOS_KON_EK IN (SELECT ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_VKOPF_KON="+this.cID_ToDelete+")");
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET ID_VPOS_KON_VK=NULL WHERE ID_VPOS_KON_VK IN (SELECT ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_VKOPF_KON="+this.cID_ToDelete+")");
			
			/*
			 * die zuordnungen muessen geloescht werden (EK-VK
			 */
			this.vSQLUpdates.add("DELETE FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE  ID_VPOS_KON_EK IN (SELECT ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_VKOPF_KON="+this.cID_ToDelete+")");
			this.vSQLUpdates.add("DELETE FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE  ID_VPOS_KON_VK IN (SELECT ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_VKOPF_KON="+this.cID_ToDelete+")");
			
			//lager muessen geloescht werden
			this.vSQLUpdates.add("DELETE FROM "+bibE2.cTO()+".JT_VPOS_KON_LAGER WHERE ID_VPOS_KON IN (SELECT ID_VPOS_KON FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE ID_VKOPF_KON="+this.cID_ToDelete+")");

		}
		else if (this.cTABLE_NAME.equals("JT_VKOPF_STD"))
		{
			this.cMAIN_DELETE_SQL = "UPDATE "+bibE2.cTO()+".JT_VKOPF_STD "+cUpdateKern+" WHERE ID_VKOPF_STD="+this.cID_ToDelete;
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_STD "+cUpdateKern+" WHERE ID_VKOPF_STD="+this.cID_ToDelete);
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_STD "+cUpdateKern+" WHERE    NVL(DELETED,'N')='N' AND  ID_VKOPF_STD="+this.cID_ToDelete);
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT"+cUpdateKern+" WHERE   NVL(DELETED,'N')='N' AND  ID_VPOS_STD IN (SELECT ID_VPOS_STD FROM "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_VKOPF_STD="+this.cID_ToDelete+")");
		}
		else if (this.cTABLE_NAME.equals("JT_VKOPF_RG"))
		{
			this.cMAIN_DELETE_SQL = "UPDATE "+bibE2.cTO()+".JT_VKOPF_RG "+cUpdateKern+" WHERE ID_VKOPF_RG="+this.cID_ToDelete;
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_RG "+cUpdateKern+" WHERE ID_VKOPF_RG="+this.cID_ToDelete);
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_RG "+cUpdateKern+" WHERE    NVL(DELETED,'N')='N' AND  ID_VKOPF_RG="+this.cID_ToDelete);
			
//			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET @D_VPOS_RG_RECH=NULL WHERE @D_VPOS_RG_RECH IN (SELECT ID_VPOS_RG FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VKOPF_RG="+this.cID_ToDelete+")");
//			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET @D_VPOS_RG_GUT=NULL WHERE @D_VPOS_RG_GUT IN (SELECT ID_VPOS_RG FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VKOPF_RG="+this.cID_ToDelete+")");
		}
		else if (this.cTABLE_NAME.equals("JT_VKOPF_TPA"))
		{
			this.cMAIN_DELETE_SQL = "UPDATE "+bibE2.cTO()+".JT_VKOPF_TPA "+cUpdateKern+" WHERE ID_VKOPF_TPA="+this.cID_ToDelete;
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_TPA "+cUpdateKern+" WHERE ID_VKOPF_TPA="+this.cID_ToDelete);
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA "+cUpdateKern+" WHERE    NVL(DELETED,'N')='N' AND  ID_VKOPF_TPA="+this.cID_ToDelete);
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE"+cUpdateKern+" WHERE   NVL(DELETED,'N')='N' AND  ID_VPOS_TPA IN (SELECT ID_VPOS_TPA FROM "+bibE2.cTO()+".JT_VPOS_TPA WHERE ID_VKOPF_TPA="+this.cID_ToDelete+")");
			
			/*
			 * hier werden noch die fahrplanpositionen der geloeschten fuhren auf null gesetzt
			 */
			String cIN_BLOCK = "SELECT JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE "+
								"FROM "+bibE2.cTO()+".JT_VKOPF_TPA,"+bibE2.cTO()+".JT_VPOS_TPA,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE "+
								" WHERE " +
								" JT_VPOS_TPA_FUHRE.ID_VPOS_TPA = JT_VPOS_TPA.ID_VPOS_TPA "+ 
								" AND JT_VPOS_TPA.ID_VKOPF_TPA = JT_VKOPF_TPA.ID_VKOPF_TPA "+
								" AND JT_VKOPF_TPA.ID_VKOPF_TPA= "+this.cID_ToDelete;
			
			String cSQL = "UPDATE "+bibE2.cTO()+".JT_FAHRPLANPOS SET ID_VPOS_TPA_FUHRE=NULL WHERE ID_VPOS_TPA_FUHRE IN("+cIN_BLOCK+")";
			
			this.vSQLUpdates.add(cSQL);
			
		}
		else if (this.cTABLE_NAME.equals("JT_VPOS_KON"))
		{
			this.cMAIN_DELETE_SQL = "UPDATE "+bibE2.cTO()+".JT_VPOS_KON "+cUpdateKern+" WHERE ID_VPOS_KON="+this.cID_ToDelete;
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_KON "+cUpdateKern+" WHERE ID_VPOS_KON="+this.cID_ToDelete);
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_KON_TRAKT "+cUpdateKern+" WHERE ID_VPOS_KON ="+this.cID_ToDelete);
			
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET ID_VPOS_KON_EK=NULL WHERE ID_VPOS_KON_EK="+this.cID_ToDelete);
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET ID_VPOS_KON_VK=NULL WHERE ID_VPOS_KON_VK="+this.cID_ToDelete);
			
			/*
			 * die zuordnungen muessen geloescht werden (EK-VK
			 */
			this.vSQLUpdates.add("DELETE FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE  ID_VPOS_KON_EK="+this.cID_ToDelete);
			this.vSQLUpdates.add("DELETE FROM "+bibE2.cTO()+".JT_EK_VK_BEZUG WHERE  ID_VPOS_KON_VK="+this.cID_ToDelete);
			
			/*
			 * lagerzuordnungen muessen geloescht werden
			 */
			this.vSQLUpdates.add("DELETE FROM "+bibE2.cTO()+".JT_VPOS_KON_LAGER WHERE  ID_VPOS_KON="+this.cID_ToDelete);
			
			
		}
		else if (this.cTABLE_NAME.equals("JT_VPOS_STD"))
		{
			this.cMAIN_DELETE_SQL = "UPDATE "+bibE2.cTO()+".JT_VPOS_STD "+cUpdateKern+" WHERE ID_VPOS_STD="+this.cID_ToDelete;
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_STD "+cUpdateKern+" WHERE ID_VPOS_STD="+this.cID_ToDelete);
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT "+cUpdateKern+" WHERE ID_VPOS_STD ="+this.cID_ToDelete);
		}
		else if (this.cTABLE_NAME.equals("JT_VPOS_RG"))
		{
			this.cMAIN_DELETE_SQL = "UPDATE "+bibE2.cTO()+".JT_VPOS_RG "+cUpdateKern+" WHERE ID_VPOS_RG="+this.cID_ToDelete;
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_RG "+cUpdateKern+" WHERE ID_VPOS_RG="+this.cID_ToDelete);
		}
		else if (this.cTABLE_NAME.equals("JT_VPOS_TPA"))
		{
			this.cMAIN_DELETE_SQL = "UPDATE "+bibE2.cTO()+".JT_VPOS_TPA "+cUpdateKern+" WHERE ID_VPOS_TPA="+this.cID_ToDelete;
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA "+cUpdateKern+" WHERE ID_VPOS_TPA="+this.cID_ToDelete);
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE "+cUpdateKern+" WHERE ID_VPOS_TPA ="+this.cID_ToDelete);
			
			/*
			 * hier werden noch die fahrplanpositionen der geloeschten fuhren auf null gesetzt
			 */
			String cIN_BLOCK = "SELECT JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE "+
								"FROM "+bibE2.cTO()+".JT_VPOS_TPA,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE "+
								" WHERE " +
								" JT_VPOS_TPA_FUHRE.ID_VPOS_TPA = JT_VPOS_TPA.ID_VPOS_TPA "+ 
								" AND JT_VPOS_TPA.ID_VPOS_TPA= "+this.cID_ToDelete;
			
			String cSQL = "UPDATE "+bibE2.cTO()+".JT_FAHRPLANPOS SET ID_VPOS_TPA_FUHRE=NULL WHERE ID_VPOS_TPA_FUHRE IN("+cIN_BLOCK+")";
			
			this.vSQLUpdates.add(cSQL);

			
		}
		else if (this.cTABLE_NAME.equals("JT_VPOS_TPA_FUHRE"))
		{
			this.cMAIN_DELETE_SQL = "UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE "+cUpdateKern+" WHERE ID_VPOS_TPA_FUHRE ="+this.cID_ToDelete;
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE "+cUpdateKern+" WHERE ID_VPOS_TPA_FUHRE ="+this.cID_ToDelete);
			
			/*
			 * hier werden noch die fahrplanpositionen der geloeschten fuhren auf null gesetzt
			 */
			String cSQL = "UPDATE "+bibE2.cTO()+".JT_FAHRPLANPOS SET ID_VPOS_TPA_FUHRE=NULL WHERE ID_VPOS_TPA_FUHRE ="+this.cID_ToDelete;
			this.vSQLUpdates.add(cSQL);
		}
		else if (this.cTABLE_NAME.equals("JT_FAHRPLANPOS"))
		{
			this.cMAIN_DELETE_SQL = "UPDATE "+bibE2.cTO()+".JT_FAHRPLANPOS "+cUpdateKern+" WHERE ID_FAHRPLANPOS ="+this.cID_ToDelete;
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_FAHRPLANPOS "+cUpdateKern+" WHERE ID_FAHRPLANPOS ="+this.cID_ToDelete);
		}
		else if (this.cTABLE_NAME.equals("JT_VPOS_TPA_FUHRE_ORT"))
		{
			this.cMAIN_DELETE_SQL = "UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT "+cUpdateKern+" WHERE ID_VPOS_TPA_FUHRE_ORT ="+this.cID_ToDelete;
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT "+cUpdateKern+" WHERE ID_VPOS_TPA_FUHRE_ORT ="+this.cID_ToDelete);
		}
		//2011-06-01: fuhren-kosten
		else if (this.cTABLE_NAME.equals("JT_VPOS_TPA_FUHRE_KOSTEN"))
		{
			this.cMAIN_DELETE_SQL = "UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_KOSTEN "+cUpdateKern+" WHERE ID_VPOS_TPA_FUHRE_KOSTEN ="+this.cID_ToDelete;
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_KOSTEN "+cUpdateKern+" WHERE ID_VPOS_TPA_FUHRE_KOSTEN ="+this.cID_ToDelete);
		}
		//2011-12-30: uma-kontrakte
		else if (this.cTABLE_NAME.equals("JT_UMA_KONTRAKT"))
		{
			this.cMAIN_DELETE_SQL = "UPDATE "+bibE2.cTO()+".JT_UMA_KONTRAKT "+cUpdateKern+" WHERE ID_UMA_KONTRAKT ="+this.cID_ToDelete;
			this.vSQLUpdates.add("UPDATE "+bibE2.cTO()+".JT_UMA_KONTRAKT "+cUpdateKern+" WHERE ID_UMA_KONTRAKT ="+this.cID_ToDelete);
		}
		
		else
		{
			throw new myException("BS_DELETER: Table "+this.cTABLE_NAME+" IS NOT ALLOWED !!");
		}
		
	}
	
	
	public Vector<String> get_vDeleteStatements()
	{
		return this.vSQLUpdates;
	}


	/*
	 * einefaches, nicht kaskadierendes del-statement
	 */
	public String get_MAIN_DELETE_SQL()
	{
		return cMAIN_DELETE_SQL;
	}
	
}

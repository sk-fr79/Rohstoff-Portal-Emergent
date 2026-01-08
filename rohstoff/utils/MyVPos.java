/*
 * Created on 20.06.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package rohstoff.utils;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.myDataRecord;
import panter.gmbh.indep.exceptions.myException;

public class MyVPos extends myDataRecord
{

    private VorgangTableNames 	oTN = null;
    private MyVKopf				VKopf = null;
    private MyVPos_TPA_FUHRE	VPos_Fuhre = null;
    /**
     * @param cTableName
     * @param cRecordID
     * @param oSES
     * @throws myException
     */
    public MyVPos(VorgangTableNames otn, String cRecordID) throws myException
    {
        super(otn.get_cVPOS_TAB(), cRecordID);
        this.oTN = otn;
        
        if (!bibALL.null2leer(this.get_resultValue(otn.get_cVKOPF_PK())).equals(""))
        {
            /*
             * dann wird der kopf sofort erzeugt
             */
            this.VKopf = new MyVKopf(this.get_resultValue(otn.get_cVKOPF_PK()),this.oTN,bibE2.get_CurrSession());
        }
        
        /*
         * jetzt nachschauen, ob es ein transportauftrag ist, wenn ja auch das fuhrenobjekt bauen
         */
        if (this.oTN.get_cVORGANG_TYP().equals(myCONST.VORGANGSART_TRANSPORT))
        {
            /*
             * jetzt nachschauen, welche JT_VPOS_TPA_FUHRE gehoh
             */
            String cSQL = "SELECT ID_VPOS_TPA_FUHRE FROM " +bibE2.cTO()+ ".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA = "+cRecordID;
            String cID_Vpos_TPA_Fuhre = bibDB.EinzelAbfrage(cSQL,"","","");

            if (cID_Vpos_TPA_Fuhre.equals(""))
            {
                /*
                 * das darf nicht sein
                 */
                throw new myException("myVPos: Error retrieving dataset id of myVPos_TPA_FUHRE-object !");
            }
            else
            {
                VPos_Fuhre = new MyVPos_TPA_FUHRE(cID_Vpos_TPA_Fuhre);
            }
        }
    }

    
    public String get_cVORGANGSTYP()
    {
        return this.oTN.get_cVORGANG_TYP();
    }
    
    public String get_cPOSITIONSNUMMER()
    {
        return this.get_resultValue("POSITIONSNUMMER");
    }
    
    public String get_cID_ADRESSE()
    {
        if (VKopf==null)
            return null;
        else
            return VKopf.get_oAdress().get_cID_ADRESSE_orig();
    }
    
    
    public MyVKopf get_vKopf()
    {
        return VKopf;
    }
    public MyVPos_TPA_FUHRE get_vPos_TPA_Fuhre()
    {
        return VPos_Fuhre;
    }

}

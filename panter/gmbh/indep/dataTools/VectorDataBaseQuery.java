/*
 * Created on 12.08.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package panter.gmbh.indep.dataTools;

import java.util.Vector;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/**
 * vector, der als objekte eindimensionale arrays speichert, in diesem wiederum
 * ist immer eine zeile einer datenbankabfrage repräsentiert,
 * 
 * Ein zusätzliches array speichert die Feldnamen der query, damit kann
 * auf die einzelnen zeilen und feldnamen zurückgegriffen werden
 */
public class VectorDataBaseQuery extends Vector<MyResultValue[]>
{

    private MyDBToolBox oDB = null;
    private MyMetaFieldDEF_HashMap  oMetaColumnsOfResultSet = null;
    
    /*
     * noch ein vector, der die formatierten werte uebernimmt
     */
    protected void finalize()
	{
    	bibALL.destroy_myDBToolBox(this.oDB);
	}
    
    
    /**
     * @param cselectBlock
     * @param cfromBlock
     * @param cwhereBlockForRelations
     * @param cwhereBlock
     * @param corderBlock
     * @param oSES
     * @throws myException
     */
    public VectorDataBaseQuery(		String cSelectBlock, 
    								String cFromBlock ,
    								String cWhereBlockForRelations,
    								String cWhereBlock, 
    								String cOrderBlock) throws myException
    {
         /*
         * bei der structure-query müssen die relations-bedingungen mitgenommen werden, da sonst die ergebnistabellen temporär zu gross werden
         */
        String cWhereForQuery    = "";
        
        if (bibALL.null2leer(cWhereBlockForRelations).equals(""))
        {
            cWhereForQuery    = cWhereBlock;
        }
        else
        {
            cWhereForQuery    = cWhereBlockForRelations.trim()+" AND "+cWhereBlock;
        }
        
	   /*
		* jetzt die werte einlesen
		*/
	   String cQuery = "SELECT "+cSelectBlock+" FROM "+cFromBlock+" WHERE "+cWhereForQuery;
	   if (! bibALL.null2leer(cOrderBlock).equals("")) cQuery += " ORDER BY "+cOrderBlock;

	   initVectorDataBaseQuery(cQuery);
    }
    


    /**
     * @param cQuery
     * @throws myException
     */
    public VectorDataBaseQuery(String cQuery) throws myException
	{
		initVectorDataBaseQuery(cQuery);
	}


    
    
    private void initVectorDataBaseQuery(String cQuery) throws myException
    {
        
       this.oDB = bibALL.get_myDBToolBox();
 	  
	   MyResultValue[][] oResult = oDB.EinzelAbfrageInRSArray(cQuery);
	   
	   this.oMetaColumnsOfResultSet = oDB.get_oMetaColumnsOfResultSet();

	   if (oResult != null)
	   {
		   for (int i=0;i<oResult.length;i++)
		   {
		      this.add(oResult[i]);
		   }
	   }
	   bibALL.destroy_myDBToolBox(oDB);
     }
    
    
    
    
    /*
     * Methode, um ein Feld nach eine zeile und einem spaltenname zu finden
     */
    public String get_resultValue(String cName,int iRow)
    {
        String cRueck = null;
        
        try
        {
            if (this.size()>iRow)
            {
            	int iCol = this.oMetaColumnsOfResultSet.get_iNumberOfColumn(cName);
            	cRueck = S.NN(((MyResultValue[])this.get(iRow))[iCol].get_FieldValueUnformated());
            }
        }
        catch (myException ex)
        {
        	cRueck = null;
        }
        return cRueck;
    }
    
    
    /*
     * Methode, um ein Feld nach eine zeile und einem spaltenname zu finden
     */
    public String get_resultValueFormated(String cName,int iRow)
    {
        String cRueck = null;
        
        try
        {
           	int iCol = this.oMetaColumnsOfResultSet.get_iNumberOfColumn(cName);
            if (this.size()>iRow && this.oMetaColumnsOfResultSet.get_iNumberOfColumns()>iCol)
            {            
            	cRueck = S.NN(((MyResultValue[])this.get(iRow))[iCol].get_FieldValueFormated());
            }
        }
        catch (myException ex)
        {
        	cRueck = null;
        }
        return cRueck;
   }
    

    
    
    
    
    /*
     * methode, um die inhalte eines anderen vectors hier anzuhängen,
     * dabei muss die länge der internen strings gleich sein
     */
    public void add_Another_Vector(VectorDataBaseQuery vOther) throws myException
    {
        if (!this.oMetaColumnsOfResultSet.equalsInNames(vOther.get_oMetaColumnsOfResultSet()))
        {
            throw new myException("VectorDataBaseQuery :   "+"Error in add_Another_Vector() - not the same length !" );
        }
        else
        {
            this.addAll(vOther);
        }
    }




	public MyMetaFieldDEF_HashMap get_oMetaColumnsOfResultSet() 
	{
		return oMetaColumnsOfResultSet;
	}
    
    

}

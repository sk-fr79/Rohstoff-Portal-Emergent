/*
 * Created on 11.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package panter.gmbh.indep.myVectors;

import java.util.List;
import java.util.Vector;

/**
 * @Klasse zum bauen von vector-segmenten fuer id-abfragen in datenbanken
 *
 */
public class vectorForSegmentation extends Vector<String>
{
    
    public static int STANDARD_SEGMENTGROESSE = 20;
    
    
    /*
     * diese anzahl an elementen wird als block zurueckgegeben
     */
    private int iSegmentGroesse = vectorForSegmentation.STANDARD_SEGMENTGROESSE; 
    
	/*
	 * schalter, der sagt, ob eine liste immer voll dargestellt wird,
	 * d.h. es gibt immer nur ein segment
	 */
	private boolean 				bOnlyOneSegment = false;

    
    public vectorForSegmentation()
    {
        super();
    }
    
    
    /*
     * anzahl vorhandene segmente im vector
     */
    public int get_iZahlSegmente()
    {
        if (this.iSegmentGroesse == 0)  return 0;
        
        if (this.iSegmentGroesse > this.size() || this.bOnlyOneSegment)
        {
            return 1;
        }
        
        int iZahlSeg = this.size()/this.iSegmentGroesse;
        
        if (this.size() % this.iSegmentGroesse > 0) iZahlSeg ++;
        
        return iZahlSeg;
    }
    
    
    
    /**
     * Optimierung beim lesen der Segmente mit Vector.sublist(start,ende)
     * segmentnummer beginnt bei 0 !!!
     * @author manfred
     * @date   07.10.2015
     *
     * @param iSegmentNummer
     * @return
     */
    public Vector<String> get_vSegment(int iSegmentNummer)
    {
        Vector<String> vRueck = new Vector<String>();
        
        if (this.bOnlyOneSegment)   // bei einsegmentigen listen wird alles zurueckgegeben (immer)
        {
        	vRueck.addAll(this);
        	return vRueck;
        }
        
        int iFirst = iSegmentNummer*this.iSegmentGroesse;
        int iLast  = (iSegmentNummer+1)*this.iSegmentGroesse;
        
        if (iLast >= this.size()){
        	iLast = this.size();
        }
        
        List<String> lSegment = this.subList(iFirst, iLast);
        vRueck.addAll(lSegment);
        
        return vRueck;
    }
    
    
    /**
     * segmentnummer beginnt bei 0 !!!
     */
//    public Vector<String> get_vSegment_old(int iSegmentNummer)
//    {
//        Vector<String> vRueck = new Vector<String>();
//        
//        if (this.bOnlyOneSegment)   // bei einsegmentigen listen wird alles zurueckgegeben (immer)
//        {
//        	vRueck.addAll(this);
//        	return vRueck;
//        }
//        
//        
//        for (int i=(iSegmentNummer*this.iSegmentGroesse);i<((iSegmentNummer+1)*this.iSegmentGroesse);i++)
//        {
//            if (i<this.size())
//            {
//                vRueck.add(this.get(i));
//            }
//        }
//        
//        return vRueck;
//    }
    
    
    public void set_iSegmentGroesse(int segmentGroesse)
    {
    	
        if (segmentGroesse <=0 )
        {
        	this.iSegmentGroesse = 20;                 // segmentgroesse  muss groesser 0 sein
        }
        else
        {
            this.iSegmentGroesse = segmentGroesse;
        }
    }
    
    
    /*
     * suchen eines elementes innerhalb des ganzen segmentierungsvectors und
     * zurueckgeben des segments und der position in dem segment iRueck[0]=segment-nummer iRueck[1]=offset_in_segment
     */
    public int[] get_Segment_and_Position(String cID_ActiveUNFORMATED)
    {
        int[] iRueck = null;
        
        int iPos = -1;
        
        for (int i=0,iZahl=this.size();i<iZahl;i++)
        {
            if (this.get(i).equals(cID_ActiveUNFORMATED))
            {
                iPos = i;
                break;
            }
        }
            
        if (iPos>=0)
        {
            iRueck = new int[2];
            iRueck[0] = iPos/this.iSegmentGroesse;
            iRueck[1] = iPos % this.iSegmentGroesse;
        }
        
        return iRueck;
    }


    /*
     * neuen content uebergeben
     */
    public void do_Replace_content(Vector<String> vContent)
    {
        this.removeAllElements();
        this.addAll(vContent);
    }
    
    
    
    public int get_iSegmentGroesse()
    {
        return iSegmentGroesse;
    }
    
	public boolean 				get_bOnlyOneSegment()							{		return bOnlyOneSegment;	}
	public void 				set_bOnlyOneSegment(boolean onlyOneSegment)	{		bOnlyOneSegment = onlyOneSegment;	}

    
}

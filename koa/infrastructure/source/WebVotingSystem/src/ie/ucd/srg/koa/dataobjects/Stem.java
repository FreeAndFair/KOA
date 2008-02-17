/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.dataobjects.Stem.java
  *
  * -----------------------------------------------------------------------
  * 
  *  (c) 2003  Ministerie van Binnenlandse Zaken en Koninkrijkrelaties
  *
  *  Project		: Kiezen Op Afstand (KOA)
  *  Project Number	: ECF-2651
  *  
  *  History:
  *  Version	Date		Name		Reason
  * ---------------------------------------------------------
  *  0.1		17-04-2003	AGr			First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.dataobjects;
import java.math.BigDecimal;

import ie.ucd.srg.koa.dataobjects.Kandidaat;
/**
 *  This class contains data about a vote that has been send by a kiezer
 */
public class Stem implements java.io.Serializable
{
	public BigDecimal StemNummer;
	public Kandidaat kandidaat;
	public String modaliteit;
}

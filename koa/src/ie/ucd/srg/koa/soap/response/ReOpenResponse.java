/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.soap.response.ReOpenResponse.java
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
  *  1.0		29-04-2003	XUi 		First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.soap.response;
import ie.ucd.srg.koa.soap.response.Statistics;
/**
 * Class representing the response of the TSM
 * for state change reopening.
 * 
 * @author uiterlix
 */
public class ReOpenResponse
{
	private int returncode;
	private String errormessage = null;
	private Statistics statistics = null;
	/**
	 * Returns the error message
	 * 
	 * @return String the error message
	 */
	public String getErrormessage()
	{
		return errormessage;
	}
	/**
	 * Returns the return code
	 * 
	 * @return int the return code
	 */
	public int getReturncode()
	{
		return returncode;
	}
	/**
	 * Returns the statistics of the TSM
	 * 
	 * @return Statistics The statistics
	 */
	public Statistics getStatistics()
	{
		return statistics;
	}
	/**
	 * Sets the error message
	 * 
	 * @param string The error message
	 */
	public void setErrormessage(String string)
	{
		errormessage = string;
	}
	/**
	 * Sets the return code
	 * 
	 * @param i The return code
	 */
	public void setReturncode(int i)
	{
		returncode = i;
	}
	/**
	 * Sets the statistics
	 * 
	 * @param statistics The statistics to set
	 */
	public void setStatistics(Statistics statistics)
	{
		this.statistics = statistics;
	}
}
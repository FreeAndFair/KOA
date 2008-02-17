/*
 * Created on 26-Oct-2004
 */
package ie.ucd.srg.logica.eplatform.command.http;
import javax.servlet.http.*;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.logica.eplatform.ticket.*;

/**
 * @author Alan E. Morkan
 */
public interface HttpCommand {
	public void preExecution() throws EPlatformException;
	public void postExecution()  throws EPlatformException;
	public void updateSession(HttpSession h)throws EPlatformException;
	public String getResultJSP();
	public String getErrorJSP();
	public void init(HttpServletRequest request)throws EPlatformException;
	public void setCommandTarget(HttpServletRequest request);
	public Object performExecute(Ticket t);
}

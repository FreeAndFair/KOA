/*
 * Created on 26-Oct-2004
 */
package ie.ucd.srg.logica.eplatform.command.http;
import javax.servlet.http.*;
import ie.ucd.srg.logica.eplatform.command.*;
/**
 * @author Alan E. Morkan
 */
public interface HttpCommandFactory {
	public HttpCommand getCommand(HttpServletRequest h)throws NoSuchCommandException;
}

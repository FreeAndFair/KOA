/** -----------------------------------------------------------------------
  *
  *   ie.ucd.srg.koa.command.KOACommandFactory.java
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
  *  1.0		07-04-2003	KuijerM		First implementation
  * -----------------------------------------------------------------------
  */
package ie.ucd.srg.koa.votecommands;
import java.util.Hashtable;
import javax.servlet.http.*;

import ie.ucd.srg.logica.eplatform.command.NoSuchCommandException;
import ie.ucd.srg.logica.eplatform.command.http.HttpCommand;
import ie.ucd.srg.logica.eplatform.command.http.HttpCommandFactory;
import ie.ucd.srg.logica.eplatform.error.EPlatformException;
import ie.ucd.srg.logica.eplatform.util.LogHelper;
import ie.ucd.srg.koa.constants.ErrorConstants;
import ie.ucd.srg.koa.votecommands.CommandConstants;
/**
 * Factory to create the commands, based on the requested CommandClass
 * via the alias through the servlet.
 * 
 * @author: KuijerM
 */
public class KOACommandFactory
	implements ie.ucd.srg.logica.eplatform.command.http.HttpCommandFactory
{
	/* the singleton instance of the command factory */
	private /*@ non_null @*/ static ie.ucd.srg.logica.eplatform.command.http.HttpCommandFactory factory;
	/* hashtable used for the mapping of the servlet aliases to the commands */
	private /*@ non_null @*/ java.util.Hashtable commandMapping;
	/**
	 * Private constructor for the command factory.
	 */
	//@ signals (ClassNotFoundException) false;
	private KOACommandFactory() throws ClassNotFoundException
	{
		LogHelper.log(
			LogHelper.INFO,
			"[KOACommandFactory] new CommandFactory created");
		/* fill the mapping hashtable with all the servlet-aliases and command classes */
		commandMapping = new Hashtable();
		commandMapping.put(
			CommandConstants.LOGIN_ALIAS,
			Class.forName("ie.ucd.srg.koa.votecommands.LoginCommand"));
		commandMapping.put(
			CommandConstants.SELECT_CANDIDATE_ALIAS,
			Class.forName(
				"ie.ucd.srg.koa.votecommands.VerifyCandidateCommand"));
		commandMapping.put(
			CommandConstants.COMMIT_CANDIDATE_ALIAS,
			Class.forName("ie.ucd.srg.koa.votecommands.VoteCommand"));
		commandMapping.put(
			CommandConstants.ALREADY_VOTED_ALIAS,
			Class.forName(
				"ie.ucd.srg.koa.votecommands.AlreadyVotedCommand"));
	}
	/**
	 * Retrieve the command from the HTTP request
	 * 
	 * @param HttpServletRequest	Object that encapsulates the request to the servlet
	 * 
	 * @return HttpCommand 			The command that corresponds to the servlet alias
	 */
	//@ requires request != null;
	//@ ensures \result != null;
	//@ signals (NoSuchCommandException) false;
	public HttpCommand getCommand(HttpServletRequest request)
		throws NoSuchCommandException
	{
		HttpCommand command = null;
		/* get the servlet alias from the request */
		String alias = request.getServletPath();
		LogHelper.trace(
			LogHelper.TRACE,
			"[KOACommandFactory] getCommand with command alias ["
				+ alias
				+ "]");
		/* determine command from alias */
		Class commandClass = (Class) commandMapping.get(alias);
		if (commandClass == null)
		{
			/* if the alias is not present, log the warning and throw a noSuchCommandException */
			LogHelper.log(
				LogHelper.WARNING,
				"CommandClass was null for alias: " + alias);
			throw new NoSuchCommandException();
		}
		try
		{
			/* create a new instance of the command class */
			command = (HttpCommand) commandClass.newInstance();
			/* execute the init on the Http command with the current request as a parameter */
			command.init(request);
		}
		catch (EPlatformException ep)
		{
			/* if an eplatform exception occures, throw a new noSuchCommandException and log the error  */
			LogHelper.log(
				LogHelper.ERROR,
				"[KOACommandFactory] EPlatformException " + ep.getMessage());
			throw new NoSuchCommandException(ep.getErrorCode());
		}
		catch (Exception e)
		{
			/* if an exception occures, throw a new noSuchCommandException and log the error  */
			LogHelper.log(LogHelper.ERROR, "CommandFactory getCommand()");
			throw new NoSuchCommandException(ErrorConstants.DEFAULT);
		}
		/* return the command after it is initialized */
		return command;
	}
	/**
	 * Singleton getter for the command factory.
	 * 
	 * @return HttpCommandFactory The KOA command factory
	 */
	//@ ensures \result != null;
	public static HttpCommandFactory getHttpCommandFactory()
	{
		LogHelper.log(
			LogHelper.TRACE,
			"[KOACommandFactory] getHttpCommandFactory");
		/* Return the Command factory as a singleton */
		if (factory == null)
		{
			try
			{
				/* if we dont have a command factory yet, create one */
				factory = new KOACommandFactory();
			}
			catch (ClassNotFoundException cnfe)
			{
				/* if we cant find the command factory class, throw a runtime exception */
				throw new RuntimeException(
					"Error creating CommandFactory: " + cnfe.getMessage());
			}
		}
		/* return the singleton instance */
		return factory;
	}
}
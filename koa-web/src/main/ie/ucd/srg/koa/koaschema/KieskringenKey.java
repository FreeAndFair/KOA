package ie.ucd.srg.koa.koaschema;
/**
 * Key class for Entity Bean: Kieskringen
 */
public class KieskringenKey implements java.io.Serializable
{
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implemetation field for persistent attribute: kieskringnummer
	 */
	public java.lang.String kieskringnummer;
	/**
	 * Creates an empty key for Entity Bean: Kieskringen
	 */
	public KieskringenKey()
	{
	}
	/**
	 * Creates a key for Entity Bean: Kieskringen
	 */
	public KieskringenKey(java.lang.String kieskringnummer)
	{
		this.kieskringnummer = kieskringnummer;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey)
	{
		if (otherKey instanceof ie.ucd.srg.koa.koaschema.KieskringenKey)
		{
			ie.ucd.srg.koa.koaschema.KieskringenKey o =
				(ie.ucd.srg.koa.koaschema.KieskringenKey) otherKey;
			return ((this.kieskringnummer.equals(o.kieskringnummer)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode()
	{
		return (kieskringnummer.hashCode());
	}
}

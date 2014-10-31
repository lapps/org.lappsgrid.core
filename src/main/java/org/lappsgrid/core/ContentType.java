package org.lappsgrid.core;

//import com.fasterxml.jackson.annotation.JsonValue;
import org.lappsgrid.discriminator.Uri;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ContentType objects represent Discriminator values that represent a document's content
 * type, e.g. XML, GATE/XML, plain text, etc. Content types are broadly similar to MIME
 * types as defined by RFC2046 (http://tools.ietf.org/html/rfc2046).
 * <p>
 * LAPPS services typically require more information than can be expressed by MIME
 * Content-Types as defined by RFC2045 and RFC2046. For example, both GATE documents and
 * UIMA CAS files are application/xml, but services need to be able to distinguish
 * between them. To solve this problem LAPPS introduces several custom parameter
 * names:
 * <b>separator</b> can be used on text/plain for newline, tab separated, or
 * comma separated lists.  For example:
 *      text/plain; separator=tab
 *
 * <b>schema</b> can be used on application/xml files to specify the specific XML
 * vocabulary used.  For example:
 *      application/xml; schema=gate
 *
 * @author Keith Suderman
 */
public class ContentType {
	// Static definitions for the most commonly used types.
	public static final ContentType XML = expr(Uri.XML);
	public static final ContentType GATE = expr(Uri.GATE);
	public static final ContentType UIMA = expr(Uri.UIMA);
	public static final ContentType JSON = expr(Uri.JSON);
	public static final ContentType JSONLD = expr(Uri.JSON_LD);
	public static final ContentType LAPPS = expr(Uri.LAPPS);
	public static final ContentType TEXT = expr(Uri.TEXT);
	public static final ContentType OPL = expr(Uri.ONE_PER_LINE);
	public static final ContentType TSV = expr(Uri.TSV);
	public static final ContentType CSV = expr(Uri.CSV);

	// Class definition starts here.

	// The type/subtype are always combined into a single "type" as
	// far as LAPPS is concerned.  Parameters, if any are stored in a
	// hash map.
	protected String type;
	protected Map<String,String> parameters = new HashMap<String,String>();

	/** Cached value for the toString() method. */
	protected String string;

	public ContentType() { }
	public ContentType(String type) {
		this.setType(type);
	}
	public ContentType(ContentType type) {
		this.type = type.type;
		for (Map.Entry<String,String> entry : type.parameters.entrySet())
		{
			this.parameters.put(entry.getKey(), entry.getValue());
		}
	}
	public ContentType(String type, Map<String,String> params) {
		this.type = type;
		for (Map.Entry<String,String> entry : params.entrySet())
		{
			parameters.put(entry.getKey(), entry.getValue());
		}
	}

	public Map<String,String> getParameters()
	{
		return parameters;
	}

	public String getParameter(String name)
	{
		return parameters.get(name);
	}

	public void setParameter(String name, String value)
	{
		parameters.put(name, value);
	}

	public String getType()
	{
		return this.type;
	}

	public void setType(String type) {
		if (type.indexOf(";") < 0) {
			this.type = type;
		}
		else {
			parameters.clear();
			String[] parts = type.split(";");
			this.type = parts[0];
			for (int i=1; i < parts.length; ++i) {
				String part = parts[i];
				if (part.indexOf("=") < 0) {
					parameters.put(part.trim(), "true");
				}
				else {
					String[] s = part.split("=");
					String key = s[0].trim();
					String value = s[1].trim();
					parameters.put(key, value);
				}
			}
		}
	}

	public boolean isa(ContentType other) {
		return this.type.equals(other.type);
	}

	public boolean equals(Object object) {
		if (!(object instanceof ContentType)) {
			return false;
		}
		ContentType other = (ContentType) object;
		return this.type.equals(other.type) &&
				  subsumes(this.parameters, other.parameters) &&
				  subsumes(other.parameters, this.parameters);
	}

	public String toString() {
		if (string == null) {
			StringBuilder buffer = new StringBuilder();
			buffer.append(type);
			ArrayList<String> sortedKeys = new ArrayList<String>(parameters.keySet());
			Collections.sort(sortedKeys);
			for (String key : sortedKeys)
			{
				String value = parameters.get(key);
				buffer.append("; ");
				buffer.append(key);
				buffer.append("=");
				buffer.append(value);
			}
			string = buffer.toString();
		}
		return string;
	}

	/*
	  * map1 subsumes map2 if map1 contains everything in map2, that is, every key/value
	  * pair from map2 is also present in map1.
	  */
	private boolean subsumes(Map<String,String> map1, Map<String,String> map2) {
		boolean result = true;
		for (Map.Entry<String,String> entry : map2.entrySet())
		{
			String value1 = map1.get(entry.getKey());
			String value2 = entry.getValue();
			if (! value1.equals(value2))
			{
				return false;
			}
		}
		return true;
	}

	static ContentType expr(String type) {
		return new ContentType(type);
	}
	static ContentType expr(String type, Map parameters) {
		return new ContentType(type, parameters);
	}
}

package org.lappsgrid.core;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * @author Keith Suderman
 */
@Ignore
public class ContentTypeTests {

	@Test
	public void isaTest() {
		assertTrue(ContentType.GATE.isa(ContentType.XML));
		assertTrue(ContentType.UIMA.isa(ContentType.XML));
//        assertTrue(ContentType.JSONLD.isa(ContentType.JSON))
//        assertTrue(ContentType.LAPPS.isa(ContentType.JSONLD))
//        assertTrue(ContentType.LAPPS.isa(ContentType.JSON))
	}

	@Test
	public void equalsTest() {
		assertTrue(ContentType.GATE.equals(ContentType.GATE));
		ContentType gate = new ContentType("application/xml; profile=http://gate.ac.uk");
		System.out.println(ContentType.GATE.toString());
		System.out.println(gate.toString());
		assertTrue(gate.equals(ContentType.GATE));
		assertTrue(ContentType.GATE.equals(gate));
		assertTrue(ContentType.TEXT.equals(new ContentType("text/plain")));
		assertTrue(new ContentType("text/plain").equals(ContentType.TEXT));

	}

	@Test
	public void listTest() {
		List<ContentType> list = new ArrayList<ContentType>();
		list.add(ContentType.TEXT);
		list.add(new ContentType("application/xml"));
		assertTrue(list.contains(ContentType.TEXT));
		assertTrue(list.contains(new ContentType("text/plain")));
		assertTrue(list.contains(ContentType.XML));
		assertTrue(list.contains(new ContentType("application/xml")));
	}
	@Test
	public void constructor1Test() {
		ContentType type = new ContentType("text/plain");
		assertTrue(type.type == "text/plain");
		assertTrue(type.parameters.size() == 0);
	}

	@Test
	public void constructor2Test() {
		ContentType type = new ContentType("application/xml; schema=gate");
		assertTrue(type.type.equals("application/xml"));
		assertTrue(type.parameters.size() == 1);
		assertTrue(type.parameters.get("schema").equals("gate"));
	}

	@Test
	public void constructor21Test() {
		ContentType type = new ContentType("application/xml; schema=gate;");
		assertTrue(type.type.equals("application/xml"));
		assertTrue(type.parameters.size() == 1);
		assertTrue(type.parameters.get("schema").equals("gate"));
		assertTrue(type.toString().equals("application/xml; schema=gate"));
	}

	@Test
	public void constructorMapTest() {
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("charset", "UTF-8");
		parameters.put("schema", "gate");
		ContentType type = new ContentType("application/xml", parameters);
		assertTrue(type.type.equals("application/xml"));
		assertTrue(type.parameters.size() == 2);
		assertTrue(type.parameters.get("schema").equals("gate"));
		assertTrue(type.parameters.get("charset").equals("UTF-8"));
	}

	@Test
	public void constructorParametersTest() {
		ContentType type = new ContentType("application/xml; charset=UTF-8; schema=gate");
		assertTrue(type.type.equals("application/xml"));
		assertTrue(type.parameters.size() == 2);
		assertTrue(type.parameters.get("schema").equals("gate"));
		assertTrue(type.parameters.get("charset").equals("UTF-8"));
	}

	@Test
	public void parametersTests() {
		ContentType type = new ContentType("application/xml; charset=UTF-8; schema=gate");
		int size = type.getParameters().size();
		assertTrue("Wrong number of parameters: expected 2 found " + size, size == 2);
		assertTrue("UTF-8".equals(type.getParameter("charset")));
		assertTrue("gate".equals(type.getParameter("schema")));
		assertTrue("application/xml".equals(type.getType()));
		assertTrue(type.isa(ContentType.XML));

		ContentType appxml = new ContentType();
		appxml.setType("application/xml");
		appxml.setParameter("schema", "gate");
		appxml.setParameter("charset", "UTF-8");

		assertTrue(appxml.equals(type));
		assertTrue(type.equals(appxml));
		assertTrue(type.isa(appxml));
		assertTrue(appxml.isa(type));
	}
}

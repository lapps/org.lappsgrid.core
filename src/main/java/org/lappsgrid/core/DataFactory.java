/*-
 * Copyright 2014 The Language Application Grid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.lappsgrid.core;

import org.lappsgrid.discriminator.Discriminators;
import org.lappsgrid.serialization.Data;
import org.lappsgrid.serialization.datasource.GetRequest;
import org.lappsgrid.serialization.datasource.ListRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

/**
 * A factory class for creating {@code org.lappsgrid.serialization.Data} objects
 * for the most commonly used types.
 *
 * @author Keith Suderman
 */
public class DataFactory
{

   // Prevent instances of the DataFactory class from being created.
   protected DataFactory()
   {
   }

	/** An empty Data object with the discriminator set to http://vocab.lappsgrid.org/ns/ok */
   public static String ok()
   {
      return ok(false);
   }

	/** Returns an pretty-printed empty Data object with the discriminator set to {@code }http://vocab.lappsgrid.org/ns/ok}. */
   public static String ok(boolean pretty)
   {
      Data<String> data = new Data<String>(Discriminators.Uri.OK);

      if (pretty) {
         return data.asPrettyJson();
      }

      return data.asJson();
   }

	/**
	 * Returns a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/ns/error}.
	 * The {@code errorMessage} is stored in the payload unchanged.
	 * @param errorMessage
	 * @return
	 */
   public static String error(String errorMessage)
   {
      Data<String> data = new Data<>(Discriminators.Uri.ERROR, errorMessage);
      return data.asJson();
   }

	/**
	 * Returns a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/ns/error}.
	 * The stack-trace from the exception is included in the payload as a string.
	 *
	 * @param error The cause of the error
	 */
   public static String error(Throwable error)
   {
      StringWriter swriter = new StringWriter();
      PrintWriter writer = new PrintWriter(swriter);
      error.printStackTrace(writer);
      return new Data(Discriminators.Uri.ERROR, swriter.toString()).asJson();
   }

	/**
	 * Returns a data object with the disscriminator set to {@code http://vocab.lappsgrid.org/ns/error}.
	 * The error message and stack trace from the exception are included in the payload.
	 *
	 * @param message an error message
	 * @param error the cause of the error
	 */
   public static String error(String message, Throwable error)
   {
      StringWriter swriter = new StringWriter();
      PrintWriter writer = new PrintWriter(swriter);
      writer.println(message);
      error.printStackTrace(writer);
      return new Data(Discriminators.Uri.ERROR, swriter.toString()).asJson();
   }

	/** Not used at this time. */
   public static String query(String queryString)
   {
      return new Data(Discriminators.Uri.QUERY, queryString).asJson();
   }

	/**
	 * Creates a Data object with the discriminator set to {@code }http://vocab.lappsgrid.org/ns/action/get}.
	 * Get objects are sent to Datasource services to retrieve a single document.
	 *
	 * @param id the document id to be retrieved from the datasource.
	 * @return
	 */
   public static String get(String id)
   {
      return new GetRequest(id).asJson();
   }

	/**
	 * Creates a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/ns/action/list}.
	 */
   public static String list()
   {
      return new ListRequest().asJson();
   }

	/**
	 * Creates a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/ns/action/list}.
	 */
	public static String list(int start, int end)
	{
		return new ListRequest(start, end).asJson();
	}

	/**
	 * Creates a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/ns/string-list}.
	 * This method will likely be deprecated for the more aptly named {@code stringList} methods.
	 */
   public static String index(String[] items)
   {
      if (items.length == 0)
      {
         return new Data(Discriminators.Uri.STRING_LIST, "").asJson();
      }
      List<String> list = Arrays.asList(items);
      return new Data<>(Discriminators.Uri.STRING_LIST, list).asJson();
   }

	/**
	 * Creates a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/ns/list}.
	 */
   public static String stringList(String[] items)
   {
      List<String> list = Arrays.asList(items);
      return new Data<List<String>>(Discriminators.Uri.STRING_LIST, list).asJson();
   }


	/**
	 * Creates a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/ns/list}.
	 */
   public static String stringList(List<String> list)
   {
      return new Data(Discriminators.Uri.STRING_LIST, list).asJson();
   }

	/**
	 * Creates a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/ns/media/text}.
	 * The {@code payload} with contain a String object with the {@code text}.
	 */
   public static String text(String text)
   {
      return new Data(Discriminators.Uri.TEXT, text).asJson();
   }

	/**
	 * Creates a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/Document}.
	 * Not used at this time.
	 */
   public static String document(String document)
   {
      return new Data(Discriminators.Uri.DOCUMENT, document).asJson();
   }

	/**
	 * Creates a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/media/xml}.
	 * The {@code payload} will contain a String object with the XML.
	 */
   public static String xml(String xml)
   {
      return new Data(Discriminators.Uri.XML, xml).asJson();
   }

	/**
	 * Creates a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/ns/media/xml#gate}.
	 * The {@code payload} will contain the GATE/XML.
	 */
   public static String gateDocument(String document)
   {
      return new Data(Discriminators.Uri.GATE, document).asJson();
   }

	/**
	 * Creates a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/ns/media/xml#gate}.
	 * The {@code payload} will contain the GATE/XML.
	 */
   public static String gate(String document)
   {
      return new Data(Discriminators.Uri.GATE, document).asJson();
   }

   public static String oneperline(String text)
   {
      return new Data(Discriminators.Uri.ONE_PER_LINE, text).asJson();
   }

   public static String opl(String text)
   {
      return new Data(Discriminators.Uri.ONE_PER_LINE, text).asJson();
   }

	/**
	 * Creates a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/ns/media/json}.
	 * The {@code payload} will contain the the text, which is expected to be a JSON object.
	 */
   public static String json(String text)
   {
      return new Data(Discriminators.Uri.JSON, text).asJson();
   }

	/**
	 * Creates a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/ns/media/jsonld}.
	 * The {@code payload} will contain the the text, which is expected to be a JSON-LD document.
	 */
   public static String jsonLD(String text)
   {
      return new Data(Discriminators.Uri.JSON_LD, text).asJson();
   }

	/**
	 * Creates a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/ns/media/jsonld#lif}.
	 * The {@code payload} will contain the the text, which is expected to be a JSON-LD document that conforms
	 * to the <a href="http://vocab.lappsgrid.org/schema/lif.schema">LIF Schema</a>.
	 */
   public static String lapps(String json) { return new Data(Discriminators.Uri.LAPPS, json).asJson(); }

	/**
	 * Creates a Data object with the discriminator set to {@code http://vocab.lappsgrid.org/ns/meta}.
	 * The {@code payload} will contain the the text, which is expected to be a JSON-LD document that conforms
	 * to either the schema for <a href="http://vocab.lappsgrid.org/schema/service.schema">services</a> or
	 * the schema or <a href="http://vocab.lappsgrid.org/schema/datasource.schema">datasources</a>
	 */
   public static String meta(String json) { return new Data(Discriminators.Uri.META, json).asJson(); }
}


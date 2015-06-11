/*
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
import org.lappsgrid.serialization.datasource.ListRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

/**
 * A factory class for creating {@link org.lappsgrid.serialization.Data Data} objects
 * with the most commonly used types.
 *
 * @author Keith Suderman
 */
public class DataFactory
{

   // Prevent instances of the DataFactory class from being created.
   protected DataFactory()
   {
   }

   public static String ok()
   {
      return ok(false);
   }

   public static String ok(boolean pretty)
   {
      Data<String> data = new Data<String>(Discriminators.Uri.OK);
      if (pretty) {
         return data.asPrettyJson();
      }
      return data.asJson();
   }

   public static String error(String errorMessage)
   {
      Data<String> data = new Data<>(Discriminators.Uri.ERROR, errorMessage);
      return data.asJson();
   }

   public static String error(Throwable error)
   {
      StringWriter swriter = new StringWriter();
      PrintWriter writer = new PrintWriter(swriter);
      error.printStackTrace(writer);
      return new Data(Discriminators.Uri.ERROR, swriter.toString()).asJson();
   }

   public static String error(String message, Throwable error)
   {
      StringWriter swriter = new StringWriter();
      PrintWriter writer = new PrintWriter(swriter);
      writer.println(message);
      error.printStackTrace(writer);
      return new Data(Discriminators.Uri.ERROR, swriter.toString()).asJson();
   }

   public static String query(String queryString)
   {
      return new Data(Discriminators.Uri.QUERY, queryString).asJson();
   }

//   public static Data jquery(String queryString) {
//      return new Data(URI.QUERY_JQUERY, queryString);
//   }

//   public static Data lucene(String queryString) {
//      return new Data(URI.LUCENE, queryString);
//   }
//
//   public static Data regex(String regex) {
//      return new Data(URI.QUERY_REGEX, regex);
//   }

   public static String get(String id)
   {
      return new Data(Discriminators.Uri.GET, id).asJson();
   }

   public static String list()
   {
      return new Data(Discriminators.Uri.LIST).asJson();
   }

	public static String list(int start, int end)
	{
		return new ListRequest(start, end).asJson();
	}

   public static String index(String[] items)
   {
      if (items.length == 0)
      {
         return new Data(Discriminators.Uri.STRING_LIST, "").asJson();
      }
      List<String> list = Arrays.asList(items);
      return new Data<>(Discriminators.Uri.STRING_LIST, list).asJson();
//      StringBuilder buffer = new StringBuilder();
//      buffer.append(items[0]);
//      for (int i = 1; i < items.length; ++i)
//      {
//         buffer.append(" ");
//         buffer.append(items[i]);
//      }
//      return new Data(Discriminators.Uri.INDEX, buffer.toString());
   }

//   public static Data index(String items)
//   {
//      return new Data(Discriminators.Uri.INDEX, items);
//   }

   public static String stringList(String[] items)
   {
      List<String> list = Arrays.asList(items);
      return new Data<List<String>>(Discriminators.Uri.STRING_LIST, list).asJson();
//      StringBuilder buffer = new StringBuilder(4096);
//      for (String item : items)
//      {
//         buffer.append(item);
//         buffer.append('\n');
//      }
//      return new Data(Discriminators.Uri.STRING_LIST, buffer.toString());
   }

   public static Data stringList(List<String> list)
   {
//      StringBuilder buffer = new StringBuilder(4096);
//      for (String item : list)
//      {
//         buffer.append(item);
//         buffer.append('\n');
//      }
//      return new Data(Discriminators.Uri.STRING_LIST, buffer.toString());
      return new Data(Discriminators.Uri.STRING_LIST, list);
   }

   public static String text(String text)
   {
      return new Data(Discriminators.Uri.TEXT, text).asJson();
   }

   public static String document(String document)
   {
      return new Data(Discriminators.Uri.DOCUMENT, document).asJson();
   }

   public static String xml(String xml)
   {
      return new Data(Discriminators.Uri.XML, xml).asJson();
   }

   public static String gateDocument(String document)
   {
      return new Data(Discriminators.Uri.GATE, document).asJson();
   }

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

   public static String json(String text)
   {
      return new Data(Discriminators.Uri.JSON, text).asJson();
   }

   public static String jsonLD(String text)
   {
      return new Data(Discriminators.Uri.JSON_LD, text).asJson();
   }

   public static String lapps(String json) { return new Data(Discriminators.Uri.LAPPS, json).asJson(); }

   public static String meta(String json) { return new Data(Discriminators.Uri.META, json).asJson(); }
}


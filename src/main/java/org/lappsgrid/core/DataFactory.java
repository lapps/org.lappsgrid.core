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

import org.lappsgrid.api.Data;
import org.lappsgrid.discriminator.Uri;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * A factory class for creating {@link org.lappsgrid.api.Data Data} objects
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

   public static Data ok()
   {
      return new Data(Uri.OK);
   }

   public static Data error(String errorMessage)
   {
      return new Data(Uri.ERROR, errorMessage);
   }

   public static Data error(Throwable error)
   {
      StringWriter swriter = new StringWriter();
      PrintWriter writer = new PrintWriter(swriter);
      error.printStackTrace(writer);
      return new Data(Uri.ERROR, swriter.toString());
   }

   public static Data error(String message, Throwable error)
   {
      StringWriter swriter = new StringWriter();
      PrintWriter writer = new PrintWriter(swriter);
      writer.println(message);
      error.printStackTrace(writer);
      return new Data(Uri.ERROR, swriter.toString());
   }

   public static Data query(String queryString)
   {
      return new Data(Uri.QUERY, queryString);
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

   public static Data get(String id)
   {
      return new Data(Uri.GET, id);
   }

   public static Data list()
   {
      return new Data(Uri.LIST);
   }

   public static Data index(String[] items)
   {
      if (items.length == 0)
      {
         return new Data(Uri.INDEX, "");
      }
      StringBuilder buffer = new StringBuilder();
      buffer.append(items[0]);
      for (int i = 1; i < items.length; ++i)
      {
         buffer.append(" ");
         buffer.append(items[i]);
      }
      return new Data(Uri.INDEX, buffer.toString());
   }

   public static Data index(String items)
   {
      return new Data(Uri.INDEX, items);
   }

   public static Data stringList(String[] items)
   {
      StringBuilder buffer = new StringBuilder(4096);
      for (String item : items)
      {
         buffer.append(item);
         buffer.append('\n');
      }
      return new Data(Uri.STRING_LIST, buffer.toString());
   }

   public static Data stringList(List<String> list)
   {
      StringBuilder buffer = new StringBuilder(4096);
      for (String item : list)
      {
         buffer.append(item);
         buffer.append('\n');
      }
      return new Data(Uri.STRING_LIST, buffer.toString());
   }

   public static Data text(String text)
   {
      return new Data(Uri.TEXT, text);
   }

   public static Data document(String document)
   {
      return new Data(Uri.DOCUMENT, document);
   }

   public static Data xml(String xml)
   {
      return new Data(Uri.XML, xml);
   }

   public static Data gateDocument(String document)
   {
      return new Data(Uri.GATE, document);
   }

   public static Data gate(String document)
   {
      return new Data(Uri.GATE, document);
   }

   public static Data oneperline(String text)
   {
      return new Data(Uri.ONE_PER_LINE, text);
   }

   public static Data opl(String text)
   {
      return new Data(Uri.ONE_PER_LINE, text);
   }

   public static Data json(String text)
   {
      return new Data(Uri.JSON, text);
   }

   public static Data jsonLD(String text)
   {
      return new Data(Uri.JSON_LD, text);
   }

   public static Data lapps(String json) { return new Data(Uri.LAPPS, json); }

   public static Data meta(String json) { return new Data(Uri.META, json); }
}


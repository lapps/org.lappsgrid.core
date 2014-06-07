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
import org.lappsgrid.discriminator.Types;

/**
 * Queries over {@link org.lappsgrid.api.DataSource DataSource} to select matching documents.
 * Possible queries include:
 * 1) IR query for lucene/indri e.g. "(president AND Obama)";
 * 2) regular expression matching query e.g. "(president .* Obama)" over text field and/or annotation field
 * 3) composite query built from several queries e.g. "(query1 OR !query2)"
 *
 * @author Di Wang
 */
public class DataQuery extends Data {
  
  private static final long serialVersionUID = 8009560790223408090L;

  public DataQuery(){
    super(Types.QUERY);
  }
  
  public DataQuery(String queryStr){
    this();
    setPayload(queryStr);
  }
  
}

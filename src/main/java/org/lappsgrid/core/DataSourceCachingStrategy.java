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
import org.lappsgrid.api.DataSource;

/**
 * Defines how to cache data source based on {@link DataQuery} and {@link ViewOptions}.
 *  
 * @author Di Wang
 */
public interface DataSourceCachingStrategy {

  /**
   * Adds a corpus to the cache.
   *
   * @param query the query
   * @param option the option
   * @param corpus the corpus
   */
  public void addToCache(Data query, ViewOptions option, DataSource corpus);

  /**
   * Gets a corpus the from cache.
   *
   * @param query the query
   * @param option the option
   * @return the from cache
   */
  public DataSource getFromCache(Data query, ViewOptions option);

}

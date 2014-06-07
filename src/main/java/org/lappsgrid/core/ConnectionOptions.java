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

/**
 * Specify how to establish a connection to remote corpus. 
 * 
 * @author Di Wang
 */
public abstract class ConnectionOptions {

  /** The corpus url. */
  String corpusUrl;
  
  /** The username. */
  String username;
  
  /** The password. */
  String password;

  /** The caching strategy. */
  DataSourceCachingStrategy cachingStrategy;

  /**
   * Gets the corpus url.
   *
   * @return the corpus url
   */
  public String getCorpusUrl() {
    return corpusUrl;
  }

  /**
   * Sets the corpus url.
   *
   * @param corpusUrl the new corpus url
   */
  public void setCorpusUrl(String corpusUrl) {
    this.corpusUrl = corpusUrl;
  }

  /**
   * Gets the username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username.
   *
   * @param username the new username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password.
   *
   * @param password the new password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the caching strategy.
   *
   * @return the caching strategy
   */
  public DataSourceCachingStrategy getCachingStrategy() {
    return cachingStrategy;
  }

  /**
   * Sets the caching strategy.
   *
   * @param cachingStrategy the new caching strategy
   */
  public void setCachingStrategy(DataSourceCachingStrategy cachingStrategy) {
    this.cachingStrategy = cachingStrategy;
  }

}

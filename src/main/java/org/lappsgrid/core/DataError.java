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
import org.lappsgrid.discriminator.Uri;

/**
 * A {@link org.lappsgrid.api.Data Data} object for returning ERROR conditions
 * from {@link org.lappsgrid.api.DataSource DataSource} objects.
 * 
 * @author Keith Suderman
 *
 */
public class DataError extends Data
{
   private static final long serialVersionUID = 1L;
   
   public DataError(String message)
   {
      super(Uri.ERROR, message);
   }

}

/*! ******************************************************************************
*
* Pentaho Data Integration
*
* Copyright (C) 2002-2013 by Pentaho : http://www.pentaho.com
*
*******************************************************************************
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
******************************************************************************/

package org.pentaho.di.core.database;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * User: RFellows
 * Date: 3/4/13
 */
public class BaseDatabaseMetaTest {

  @Test
  public void testGetSafeFieldname_space() throws Exception {
    BaseDatabaseMeta meta = mock(BaseDatabaseMeta.class, Mockito.CALLS_REAL_METHODS);
    String expected = "hello_world";
    String fieldname = "hello world";
    String result = meta.getSafeFieldname(fieldname);

    assertEquals(expected, result);
  }
  @Test
  public void testGetSafeFieldname_beginingWithNumber() throws Exception {
    BaseDatabaseMeta meta = mock(BaseDatabaseMeta.class, Mockito.CALLS_REAL_METHODS);
    String expected = "_2B";
    String fieldname = "2B";
    String result = meta.getSafeFieldname(fieldname);

    assertEquals(expected, result);
  }

  @Test
  public void testGetSafeFieldname_reservedWord() throws Exception {
    BaseDatabaseMeta meta = mock(BaseDatabaseMeta.class, Mockito.CALLS_REAL_METHODS);
    when(meta.getReservedWords()).thenReturn(new String[] { "CASE", "JOIN" } );
    String expected = "case_";
    String fieldname = "case";
    String result = meta.getSafeFieldname(fieldname);

    assertEquals(expected, result);
  }

  @Test
  public void testGetSafeFieldname_nonAlphaNumericChars() throws Exception {
    BaseDatabaseMeta meta = mock(BaseDatabaseMeta.class, Mockito.CALLS_REAL_METHODS);
    String expected = "what_the_";
    String fieldname = "what the *#&@(@!?";
    String result = meta.getSafeFieldname(fieldname);

    assertEquals(expected, result);
  }
}

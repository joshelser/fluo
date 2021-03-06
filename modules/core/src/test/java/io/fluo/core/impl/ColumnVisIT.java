/*
 * Copyright 2014 Fluo authors (see AUTHORS)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.fluo.core.impl;

import java.util.Arrays;

import io.fluo.api.data.Column;
import io.fluo.core.TestBaseImpl;
import io.fluo.core.TestTransaction;
import org.junit.Test;

public class ColumnVisIT extends TestBaseImpl {

  @Test(expected = Exception.class)
  public void testFailFastSet() {
    TestTransaction tx1 = new TestTransaction(env);

    // expect set w/ bad col vis to fail fast
    tx1.mutate().row("r").fam("f").qual("q").vis("A&").set("v");
  }

  @Test(expected = Exception.class)
  public void testFailFastDelete() {
    TestTransaction tx1 = new TestTransaction(env);

    // expect delete w/ bad col vis to fail fast
    tx1.mutate().row("r").fam("f").qual("q").vis("A&").delete();
  }

  @Test(expected = Exception.class)
  public void testFailFastWeakNotify() {
    TestTransaction tx1 = new TestTransaction(env);

    // expect weaknotify w/ bad col vis to fail fast
    tx1.mutate().row("r").fam("f").qual("q").vis("A&").weaklyNotify();
  }

  @Test(expected = Exception.class)
  public void testFailFastGet() {
    TestTransaction tx1 = new TestTransaction(env);

    // expect get w/ bad col vis to fail fast
    tx1.get().row("r").fam("f").qual("q").vis("A&").toString();
  }

  @Test(expected = Exception.class)
  public void testFailFastGetCols() {
    TestTransaction tx1 = new TestTransaction(env);

    Column col1 = new Column("f", "q").setVisibility("A&");
    Column col2 = new Column("f", "q").setVisibility("C|");

    // expect get cols w/ bad col vis to fail fast
    tx1.get().row("r").columns(col1, col2).size();
  }

  @Test(expected = Exception.class)
  public void testFailFastGetRowsCols() {
    TestTransaction tx1 = new TestTransaction(env);

    Column col1 = new Column("f", "q").setVisibility("A&");
    Column col2 = new Column("f", "q").setVisibility("C|");

    // expect get rows cols w/ bad col vis to fail fast
    tx1.get().rowsString(Arrays.asList("r1", "r2")).columns(col1, col2).toStringMap().size();
  }
}

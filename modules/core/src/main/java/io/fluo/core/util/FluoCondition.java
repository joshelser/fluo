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

package io.fluo.core.util;

import io.fluo.api.data.Column;
import io.fluo.core.impl.Environment;
import org.apache.accumulo.core.data.Condition;

public class FluoCondition extends Condition {

  public FluoCondition(Environment env, Column col) {
    super(ByteUtil.toByteSequence(col.getFamily()), ByteUtil.toByteSequence(col.getQualifier()));
    if (col.getVisibility().length() > 0) {
      setVisibility(env.getSharedResources().getVisCache().getCV(col));
    }
  }

}

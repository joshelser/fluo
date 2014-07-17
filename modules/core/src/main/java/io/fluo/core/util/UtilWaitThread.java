/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fluo.core.util;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilWaitThread {
  private static Logger log = LoggerFactory.getLogger(UtilWaitThread.class);
  
  public static void sleep(long millis, AtomicBoolean skipLog) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      if (!skipLog.get()) {
        log.error(e.getMessage(), e);
      }
      Thread.currentThread().interrupt();
    }
  }
  
  public static void sleep(long millis) {
    sleep(millis, new AtomicBoolean(false));
  }
}
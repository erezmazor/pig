/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.pig.builtin;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.DataType;
import org.apache.pig.data.Tuple;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * This method should never be used directly, use {@link ToDate}.
 */
public class ToDateISO extends EvalFunc<DateTime> {

    public DateTime exec(Tuple input) throws IOException {
        String dtStr = DataType.toString(input.get(0));
        DateTimeZone dtz = ToDate.extractDateTimeZone(dtStr);
        if (dtz == null) {
            return new DateTime(dtStr);
        } else {
            return new DateTime(dtStr, dtz);
        }
    }

}

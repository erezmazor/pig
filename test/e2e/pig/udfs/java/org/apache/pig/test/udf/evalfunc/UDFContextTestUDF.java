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

package org.apache.pig.test.udf.evalfunc;

import java.io.IOException;
import java.util.Properties;

import org.apache.pig.data.DataType;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.logicalLayer.schema.Schema;
import org.apache.pig.impl.util.UDFContext;

public class UDFContextTestUDF extends EvalFunc<String> {
    String signature;
    
    @Override
    public String exec(Tuple input) throws IOException {
        Schema sch = (Schema)UDFContext.getUDFContext()
                .getUDFProperties(this.getClass()).get("pig.evalfunc.signature."+signature);
         return sch.toString();
    }

    @Override
    public void setUDFContextSignature(String signature) {
        this.signature = signature;
    }
        
    @Override
    public Schema outputSchema(Schema input) {
        Properties props = UDFContext.getUDFContext().getUDFProperties(this.getClass());
        props.put("pig.evalfunc.signature."+signature, input);
        return new Schema(new Schema.FieldSchema(null, DataType.CHARARRAY));
    }
}

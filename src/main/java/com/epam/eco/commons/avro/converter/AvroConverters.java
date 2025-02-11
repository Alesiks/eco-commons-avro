/*
 * Copyright 2019 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.epam.eco.commons.avro.converter;

import org.apache.avro.Schema;
import org.apache.commons.lang3.Validate;

/**
 * @author Ihar_Karoza
 */
public interface AvroConverters extends AvroConverter {

    void register(Schema.Type type, AvroConverter converter);

    AvroConverter getForType(Schema.Type type);

    default AvroConverter getForSchema(Schema schema) {
        Validate.notNull(schema, "Schema is null");

        return getForType(schema.getType());
    }

    default Object toAvro(Object value, Schema schema) {
        return getForSchema(schema).toAvro(value, schema, this);
    }

    @Override
    default Object toAvro(Object value, Schema schema, AvroConverters provider) {
        return getForSchema(schema).toAvro(value, schema, provider);
    }

    static AvroConverters createDefault() {
        return new DefaultAvroConverters();
    }

}

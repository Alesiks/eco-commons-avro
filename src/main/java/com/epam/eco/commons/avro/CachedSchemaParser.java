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
package com.epam.eco.commons.avro;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.avro.Schema;
import org.apache.commons.lang3.Validate;

/**
 * @author Andrei_Tytsik
 */
public abstract class CachedSchemaParser {

    private static final ConcurrentMap<String, Schema> CACHE = new ConcurrentHashMap<>();

    private CachedSchemaParser() {
    }

    public static Schema parse(String schemaJson) {
        Validate.notBlank(schemaJson, "Schema JSON is blank");

        return CACHE.computeIfAbsent(
                schemaJson,
                key -> AvroUtils.schemaFromJson(schemaJson));
    }

}

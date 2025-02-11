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
package com.epam.eco.commons.avro.avpath;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.epam.eco.commons.avro.AvroUtils;

/**
 * @author Andrei_Tytsik
 */
public class SelectMapValueByKey implements Expression<Map<?, ?>> {

    private final Object key;
    private final Object keyAvro;

    public SelectMapValueByKey(Object key) {
        this.key = key;
        this.keyAvro = AvroUtils.javaPrimitiveToAvro(key);
    }

    @Override
    public boolean accepts(Object object) {
        return object instanceof Map;
    }

    @Override
    public List<EvaluationResult> eval(Map<?, ?> map) {
        if (map == null || !map.containsKey(keyAvro)) {
            return Collections.emptyList();
        }

        return Collections.singletonList(
                SelectMapValueResult.with(
                        map,
                        key,
                        map.get(keyAvro)));
    }

    public Object getKey() {
        return key;
    }

    @Override
    public String toString() {
        return String.format("Map[%s]", key);
    }

}

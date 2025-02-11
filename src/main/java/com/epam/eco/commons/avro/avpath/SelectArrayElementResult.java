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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * @author Andrei_Tytsik
 */
public class SelectArrayElementResult extends AbstractEvaluationResult {

    private final int index;

    public SelectArrayElementResult(
            List<?> containerArray,
            int index,
            Object value) {
        super(containerArray, value);

        Validate.isTrue(index >= 0, "Index is invalid");

        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public List<?> getContainerArray() {
        return (List<?>)getContainer();
    }

    @Override
    public String toString() {
        return String.format(
                "{containerArray: [%s], index: %d, value: %s}",
                StringUtils.join(getContainerArray(), ", "), getIndex(), getValue());
    }

    public static SelectArrayElementResult with(
            List<?> containerArray,
            int index,
            Object value) {
        return new SelectArrayElementResult(containerArray, index, value);
    }

}

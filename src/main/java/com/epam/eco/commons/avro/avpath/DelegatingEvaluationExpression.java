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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Andrei_Tytsik
 */
public abstract class DelegatingEvaluationExpression implements Expression<Object> {

    private final List<Expression<?>> delegates = new ArrayList<>();

    @Override
    public boolean accepts(Object object) {
        for (Expression<?> exp : delegates) {
            if (exp.accepts(object)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<EvaluationResult> eval(Object object) {
        if (object == null) {
            return Collections.emptyList();
        }

        Expression<?> delegate = determineDelegate(object);
        return ((Expression<Object>)delegate).eval(object);
    }

    private Expression<?> determineDelegate(Object object) {
        for (Expression<?> delegate : delegates) {
            if (delegate.accepts(object)) {
                return delegate;
            }
        }

        throw new RuntimeException(
                String.format("Can't determine delegate to evaluate %s", object));
    }

    protected void addDelegate(Expression<?> delegate) {
        delegates.add(delegate);
    }

    @Override
    public String toString() {
        return StringUtils.join(delegates, "|");
    }

}

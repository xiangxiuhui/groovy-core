/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.groovy.transform

class GlobalTestTransformClassLoader extends GroovyClassLoader {
    private final String transformDescriptor

    GlobalTestTransformClassLoader(ClassLoader parent, Class<?>... transformClasses) {
        super(parent)
        transformDescriptor = transformClasses*.name.join("\n")
    }

    Enumeration getResources(String name) {
        if (name == "META-INF/services/org.codehaus.groovy.transform.ASTTransformation") {
            return Collections.enumeration(Collections.singleton(new FakeURLFactory().createURL(transformDescriptor)))
        }

        super.getResources(name)
    }
}
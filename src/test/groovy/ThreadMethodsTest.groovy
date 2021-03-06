/*
 * Copyright 2003-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package groovy

class ThreadMethodsTest extends GroovyTestCase {
    void testThreadNaming() {
        def t = Thread.start("MyNamedThread") {
            sleep 3000 // give ourselves time to find the thread
        }
        def threadFoundByName = false
        30.times {
            if (!threadFoundByName) {
                sleep 100 // a little bit of time for t to start
                threadFoundByName = Thread.allStackTraces.keySet().any { thread -> thread.name == 'MyNamedThread' }
            }
        }
        assert threadFoundByName
    }
}
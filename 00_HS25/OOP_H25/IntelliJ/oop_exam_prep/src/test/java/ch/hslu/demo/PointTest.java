/*
 * Copyright 2026 Roland Gisler, HSLU Informatik, Switzerland
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
package ch.hslu.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Testfälle für Point.
 */
final class PointTest {

    /**
     * Test of getX method, of class Point.
     */
    @Test
    void testGetX() {
        assertEquals(10, new Point(10, 0).getX());
    }

    /**
     * Test of getY method, of class Point.
     */
    @Test
    void testGetY() {
        assertEquals(20, new Point(0, 20).getY());
    }
}

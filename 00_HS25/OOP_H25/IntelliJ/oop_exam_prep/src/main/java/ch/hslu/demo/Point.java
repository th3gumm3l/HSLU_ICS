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
/**
 * Modell eines Punktes im 2D-Raum.
 * Klasse ist Immutable implementiert.
 */
public final class Point {

    private final int x;
    private final int y;

    /**
     * Konstruktor für Punkt mit Koordinaten.
     * @param x x-Koordinate.
     * @param y y-Koordinate.
     */
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Liefert x-Koordinate zurück.
     * @return x-Koordinate des Punktes.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Liefert y-Koordinate zurück.
     * @return y-Koordinate des Punktes.
     */
    public int getY() {
        return y;
    }
}

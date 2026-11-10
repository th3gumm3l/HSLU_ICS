USE RecommenDB;

-- ---------------------------------------------------------------------
-- 5.2 Interessen der Nutzer bestimmen
-- ---------------------------------------------------------------------

-- Code 5.1: Fünf-Sterne-Bewertungen
SELECT UserID, MovieID
FROM Ratings
WHERE Rating = 5;

-- Code 5.3: Join in Komma-Notation, mit Filmtitel
SELECT UserID, Movies.MovieID, Title
FROM Ratings, Movies
WHERE Rating = 5
  AND Ratings.MovieID = Movies.MovieID;

-- Code 5.4: dieselbe Abfrage mit JOIN ... ON
SELECT UserID, Movies.MovieID, Title
FROM Ratings
JOIN Movies
  ON Ratings.MovieID = Movies.MovieID
WHERE Rating = 5;

-- Code 5.5: View Ratings5 erstellen
CREATE OR REPLACE VIEW Ratings5 AS
SELECT UserID, Movies.MovieID, Title
FROM Ratings, Movies
WHERE Rating = 5
  AND Ratings.MovieID = Movies.MovieID;

-- Code 5.6: View wie eine Tabelle abfragen
SELECT * FROM Ratings5;

-- ---------------------------------------------------------------------
-- 5.3 Zielgruppe auswählen
-- ---------------------------------------------------------------------

-- Code 5.7: Film-ID per Mustervergleich finden (erwartet: 1371 = Star Trek, 1979)
SELECT *
FROM Movies
WHERE Title LIKE '%Star Trek%';

-- Code 5.8: Zielgruppe = Nutzer, die Film 1371 mit 5 Sternen bewertet haben
SELECT UserID AS TargetGroup
FROM Ratings5
WHERE MovieID = 1371;

-- ---------------------------------------------------------------------
-- 5.4 Interesse aggregieren
-- ---------------------------------------------------------------------

-- Code 5.9: Grösse der Zielgruppe (erwartet: 1256)
-- Hinweis: Mit ONLY_FULL_GROUP_BY kann "Title" ohne Aggregation Fehler 1140
-- auslösen. Falls ja, ANY_VALUE(Title) verwenden (siehe unten).
SELECT MovieID, ANY_VALUE(Title) AS Title, COUNT(*) AS N5R
FROM Ratings5
WHERE MovieID = 1371;

-- Code 5.10: Anzahl Fünf-Sterne-Bewertungen für alle Filme (dauert etwas)
SELECT MovieID, Title, COUNT(*) AS N5R
FROM Ratings5
GROUP BY MovieID, Title;

-- ---------------------------------------------------------------------
-- 5.5 Filme nach Interesse der Zielgruppe sortieren
-- ---------------------------------------------------------------------

-- Code 5.11: Rangfolge aller Filme nach Anzahl Fünf-Sterne-Bewertungen
SELECT MovieID, Title, COUNT(*) AS N5R
FROM Ratings5
GROUP BY MovieID, Title
ORDER BY COUNT(*) DESC;

-- Code 5.12: nur die Top 5
SELECT MovieID, Title, COUNT(*) AS N5R
FROM Ratings5
GROUP BY MovieID, Title
ORDER BY COUNT(*) DESC
LIMIT 5;

-- Code 5.13: Empfehlungen für Star Trek-Fans (Subquery mit WHERE ... IN)
-- Laufzeit laut Buch ca. 6 Sekunden, im Action Output notieren (Vergleich mit Kapitel 6)
SELECT MovieID, Title, COUNT(*) AS N5R
FROM Ratings5
WHERE UserID IN (
    SELECT UserID AS TargetGroup
    FROM Ratings5
    WHERE MovieID = 1371
)
GROUP BY MovieID, Title
ORDER BY COUNT(*) DESC
LIMIT 10;

-- ---------------------------------------------------------------------
-- 5.8 Herausforderungen (optional)
-- ---------------------------------------------------------------------

-- Herausforderung 1: Empfehlungen für den Lieblingsfilm
-- Titel anpassen, MovieID ablesen und unten einsetzen.
-- SELECT MovieID, Title FROM Movies WHERE Title LIKE '%MEIN FILM%';
--
-- SELECT MovieID, Title, COUNT(*) AS N5R
-- FROM Ratings5
-- WHERE UserID IN (
--     SELECT UserID FROM Ratings5 WHERE MovieID = <MOVIEID>
-- )
-- GROUP BY MovieID, Title
-- ORDER BY COUNT(*) DESC
-- LIMIT 10;

-- Herausforderung 2: Attraktivität über die durchschnittliche Bewertung der
-- Zielgruppe (statt Anzahl Fünf-Sterne-Bewertungen).
-- Wichtig: Ratings5 enthält nur 5-Sterne-Bewertungen, der Durchschnitt wäre
-- dort immer 5. Deshalb auf Ratings (alle Bewertungen) mit Movies joinen.
-- Ohne Mindestanzahl dominieren Filme mit nur wenigen Bewertungen.
SELECT m.MovieID, m.Title,
       AVG(r.Rating) AS AvgRating,
       COUNT(*)      AS N_Ratings
FROM Ratings r
JOIN Movies m ON m.MovieID = r.MovieID
WHERE r.UserID IN (
    SELECT UserID
    FROM Ratings5
    WHERE MovieID = 1371
)
GROUP BY m.MovieID, m.Title
HAVING COUNT(*) >= 50
ORDER BY AvgRating DESC
LIMIT 10;
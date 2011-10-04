(ns project-euler-solutions.problem-0017
  (:use [clojure.string :only (blank? join)])
  (:use [project-euler-solutions.common]))

(def words-1-to-19  {1 "one", 2 "two", 3 "three", 4 "four", 5 "five", 6 "six", 7 "seven", 8 "eight", 9 "nine", 10 "ten",
                     11 "eleven", 12 "twelve", 13 "thirteen", 14 "fourteen", 15 "fifteen", 16 "sixteen", 17 "seventeen",
                     18 "eighteen", 19 "nineteen"})

(def words-20-to-90 {2 "twenty", 3 "thirty", 4 "forty", 5 "fifty", 6 "sixty", 7 "seventy", 8 "eighty", 9 "ninety"})

(defn- join-with-space-and-skip-nil [& args]
  (join " " (filter (complement nil?) args)))

(defn- number-to-words [n]
  (assert (> n 0))
  (assert (<= n 1000))
  (let [[units tens hundreds thousands] (reverse (number-to-digits n))
        word-for-thousands      (if thousands "one thousand")
        word-for-hundreds       (if (and hundreds (> hundreds 0)) (str (words-1-to-19 hundreds) " hundred"))
        word-for-tens-and-units (if tens
                                  (if (< tens 2)
                                    (words-1-to-19 (+ (* tens 10) units))
                                    (str (words-20-to-90 tens)
                                         (if (> units 0) (str "-" (words-1-to-19 units)))))
                                  (if (> units 0) (words-1-to-19 units)))]
    (join-with-space-and-skip-nil
      word-for-thousands
      word-for-hundreds
      (if (and word-for-hundreds word-for-tens-and-units) "and")
      word-for-tens-and-units)))

(defn- count-letters [words]
  (count (filter #(not (#{\space, \-} %)) words)))

(defn- count-letters-of-words-for-number [n]
  (count-letters (number-to-words n)))

(assert (= 23 (count-letters-of-words-for-number 342)))
(assert (= 20 (count-letters-of-words-for-number 115)))

(defn- sum-of-count-of-letters-of-words-for-range [start end]
  (apply + (map count-letters-of-words-for-number (range start end))))

(assert (= 19 (sum-of-count-of-letters-of-words-for-range 1 6)))

(defn answer []
  (sum-of-count-of-letters-of-words-for-range 1 1001))

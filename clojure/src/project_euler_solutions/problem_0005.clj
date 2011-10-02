(ns project-euler-solutions.problem-0005
  (:use [project-euler-solutions.common]))

(defn- max-occurrences [maps]
  (apply merge-with max maps))

(defn- max-occurrences-prime-factors [coll]
  (max-occurrences (map count-occurrences (map prime-factors coll))))

(defn- raise-key-by-value [m]
  (map #(pow (first %) (second %)) m))

(assert (= 232792560 (apply * (raise-key-by-value (max-occurrences-prime-factors (range 2 21))))))

(defn answer []
  (apply * (raise-key-by-value (max-occurrences-prime-factors (range 2 21)))))

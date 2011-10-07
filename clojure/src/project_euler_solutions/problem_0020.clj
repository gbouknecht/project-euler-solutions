(ns project-euler-solutions.problem-0020
  (:use [project-euler-solutions.common]))

(defn- fac []
  (map first (iterate (fn [[result n]] (let [n (inc n)] [(* result n) n])) [1N 0])))

(assert (= 3628800 (nth (fac) 10)))

(defn answer []
  (sum-of-digits (nth (fac) 100)))

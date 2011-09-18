(ns project-euler-solutions.problem-0002
  (:use [project-euler-solutions.common]))

(defn- fibo []
  (map first (iterate (fn [[n1 n2]] [n2 (+ n1 n2)]) [1 2])))

(defn- even-fibo-not-exceeding [max]
   (take-while #(<= % max) (filter even? (fibo))))

(defn- sum-of-even-fibo-not-exceeding [max]
  (reduce + (even-fibo-not-exceeding max)))

(defn answer []
  (sum-of-even-fibo-not-exceeding 4000000))

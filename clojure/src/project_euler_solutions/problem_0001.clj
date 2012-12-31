(ns project-euler-solutions.problem-0001
  (:use [project-euler-solutions.common]))

(defn- multiples [start end & divisors]
  (for [n (range start end) :when (apply some-divisor n divisors)] n))

(defn- sum-of-multiples [start end & divisors]
  (reduce + (apply multiples start end divisors)))

(defn answer []
  (sum-of-multiples 1 1000 3 5))

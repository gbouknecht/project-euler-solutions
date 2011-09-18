(ns project-euler-solutions.problem-0007
  (:use [project-euler-solutions.common]))

(defn- nth-prime [n]
  (nth (primes) (dec n)))

(defn answer []
  (nth-prime 10001))

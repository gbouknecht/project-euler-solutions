(ns project-euler-solutions.problem-0010
  (:use [project-euler-solutions.common]))

(defn- sum-of-primes [upper-limit]
  (apply + (take-while #(< % upper-limit) (primes))))

(defn answer []
  (sum-of-primes 2000000))

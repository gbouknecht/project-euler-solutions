(ns project-euler-solutions.problem-0003
  (:use [project-euler-solutions.common]))

(defn- max-prime-factor [n]
  (reduce max (prime-factors n)))

(defn answer []
  (max-prime-factor 600851475143))

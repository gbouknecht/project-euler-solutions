(ns project-euler-solutions.problem-0016
  (:use [project-euler-solutions.common]))

(defn- sum-of-digits [n]
  (apply + (number-to-digits n)))

(defn- sum-of-digits-of-pow [n x]
  (sum-of-digits (pow n x)))

(assert (= 26 (sum-of-digits-of-pow 2 15)))

(defn answer []
  (sum-of-digits-of-pow 2 1000))

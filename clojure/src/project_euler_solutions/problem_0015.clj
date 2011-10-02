(ns project-euler-solutions.problem-0015
  (:use [project-euler-solutions.common]))

(defn- next-binomial-coefficients [coefficients]
  (first (reduce (fn [[result prev] curr] [(conj result (+ prev curr)) curr]) [[] 0] (conj coefficients 0))))

(defn- binomial-coefficients []
  (iterate next-binomial-coefficients [1]))

(defn- count-routes [grid-size]
  (apply max (nth (binomial-coefficients) (* 2 grid-size))))

(assert (= 6 (count-routes 2)))

(defn answer []
  (count-routes 20))

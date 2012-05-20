(ns project-euler-solutions.problem-0021
  (:use [project-euler-solutions.common]))

(def sum-of-proper-divisors-memoized (memoize sum-of-proper-divisors))

(defn amicable-number? [a]
  (let [sum-of-proper-divisors sum-of-proper-divisors-memoized]
    (let [b (sum-of-proper-divisors a)]
      (and (not= a b) (= (sum-of-proper-divisors b) a)))))

(defn amicable-numbers-between [start end]
  (filter amicable-number? (range start end)))

(defn sum-of-amicable-numbers-between [start end]
  (apply + (amicable-numbers-between start end)))

(defn answer []
  (sum-of-amicable-numbers-between 2 10000))

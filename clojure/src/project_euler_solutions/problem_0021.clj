(ns project-euler-solutions.problem-0021
  (:use [project-euler-solutions.common]))

(defn proper-divisors [n]
  (filter #(divisor? n %) (range 1 (inc (quot n 2)))))

(assert (= '(1 2 4 5 10 11 20 22 44 55 110) (proper-divisors 220)))
(assert (= '(1 2 4 71 142)                  (proper-divisors 284)))

(defn sum-of-proper-divisors [n]
  (apply + (proper-divisors n)))

(assert (= 284 (sum-of-proper-divisors 220)))
(assert (= 220 (sum-of-proper-divisors 284)))

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

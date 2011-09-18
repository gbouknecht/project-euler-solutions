(ns project-euler-solutions.problem-0012
  (:use [project-euler-solutions.common]))

(def primes-cached (primes))

(defn- divisor-count [n]
  (apply * (map #(inc (second %)) (count-occurrences (prime-factors n primes-cached)))))

(def divisor-count-memoized (memoize divisor-count))

(defn- divisor-count-for-triangle-number-with-n [n]
  (let [divisor-count divisor-count-memoized]
    (if (even? n)
      (* (divisor-count (quot n 2)) (divisor-count (inc n)))
      (* (divisor-count n)          (divisor-count (quot (inc n) 2))))))

(defn- triangle-number-with-n [n]
  (quot (* n (inc n)) 2))

(defn- first-triangle-number-where-divisor-count-is-greater-than [count]
  (first (for [n (iterate inc 2) :when (> (divisor-count-for-triangle-number-with-n n) count)] (triangle-number-with-n n))))
  
(assert (= 28       (first-triangle-number-where-divisor-count-is-greater-than   5)))

(defn answer []
  (first-triangle-number-where-divisor-count-is-greater-than 500))

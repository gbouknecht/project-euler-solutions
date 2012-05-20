(ns project-euler-solutions.problem-0023
  (:use [project-euler-solutions.common]))

(defn- abundant-number? [n]
  (> (sum-of-proper-divisors n) n))

(assert (not (some abundant-number? (range 0 12))))
(assert (abundant-number? 12))
(assert (not (some abundant-number? (range 13 18))))
(assert (abundant-number? 18))

(def abundant-number?-memoized (memoize abundant-number?))

(defn- abundant-numbers []
  (filter abundant-number? (range)))

(assert (= '(12 18 20 24 30) (take 5 (abundant-numbers))))

(def abundant-numbers-cached (abundant-numbers))

(defn- not-sum-of-two-abundant-numbers? [n]
  (let [abundant-number?                 abundant-number?-memoized
        abundant-numbers-up-to-half-of-n (take-while #(<= % (inc (quot n 2))) abundant-numbers-cached)]
    (not (some #(abundant-number? (- n %)) abundant-numbers-up-to-half-of-n))))

(defn- positive-integers-not-sum-of-two-abundant-numbers []
  (filter not-sum-of-two-abundant-numbers? (range 1 28124)))

(defn answer []
  (reduce + (positive-integers-not-sum-of-two-abundant-numbers)))

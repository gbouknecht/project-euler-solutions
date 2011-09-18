(ns project-euler-solutions.common)

(defn divisor? [n & divisors]
  (not (nil? (some #(= (rem n %) 0) divisors))))

(assert (true?  (divisor? 1 1)))
(assert (true?  (divisor? 15 3)))
(assert (true?  (divisor? 15 4 5)))
(assert (true?  (divisor? 15 10 2 5 9)))
(assert (false? (divisor? 1)))
(assert (false? (divisor? 1 2)))
(assert (false? (divisor? 15 4)))
(assert (false? (divisor? 15 10 2 9)))

(defn prime? [n]
  (cond
    (= n 1)        false
    (< n 4)        true
    (even? n)      false
    (< n 9)        true
    (divisor? n 3) false
    :else
      (let [r (int (Math/floor (Math/sqrt n)))]
        (loop [f 5]
          (if (<= f r)
            (cond
              (divisor? n f)       false
              (divisor? n (+ f 2)) false
              :else                (recur (+ f 6)))
            true)))))

(assert (false? (prime?  1)))
(assert (true?  (prime?  2)))
(assert (true?  (prime?  3)))
(assert (false? (prime?  4)))
(assert (true?  (prime?  5)))
(assert (false? (prime?  6)))
(assert (true?  (prime?  7)))
(assert (false? (prime?  8)))
(assert (false? (prime?  9)))
(assert (false? (prime? 10)))
(assert (true?  (prime? 11)))
(assert (true?  (prime? 23)))
(assert (false? (prime? 24)))

(defn primes []
  (filter prime? (iterate inc 2)))

(assert (= [2 3 5 7 11 13 17 19 23] (take 9 (primes))))

(defn prime-factors
  ([n]
    (prime-factors n (primes)))
  ([n primes]
    (loop [result [], n n, primes primes]
      (let [prime (first primes)]
        (cond
          (= n 1)            (reverse result)
          (divisor? n prime) (recur (cons prime result) (quot n prime) primes)
          :else              (recur result              n              (rest primes)))))))

(assert (= [2]             (prime-factors     2)))
(assert (= [3]             (prime-factors     3)))
(assert (= [2 2]           (prime-factors     4)))
(assert (= [3 3]           (prime-factors     9)))
(assert (= [3 5 5 7 11 11] (prime-factors 63525)))

(defn count-occurrences [coll]
  (reduce (fn [result x] (assoc result x (inc (get result x 0)))) {} coll))

(assert (= {}                                     (count-occurrences [])))
(assert (= {13 1}                                 (count-occurrences [13])))
(assert (= {1 1, 2 4, 3 4, 8 1, 10 2, 11 3, 45 1} (count-occurrences [2 11 8 3 45 1 11 2 3 2 3 10 3 11 2 10])))

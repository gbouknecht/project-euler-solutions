(ns project-euler-solutions.common)

(defn divisor?
  "Returns true if and only if n is divisible by one of the divisors."
  [n & divisors]
  (not (nil? (some #(= (rem n %) 0) divisors))))

(defn pow 
  "Returns n raised to power of x, both integers.
  
  Precondition: x >= 0"
  [n x]
  (assert (>= x 0))
  (nth (iterate #(* %1 n) 1N) x))

(defn prime?
  "Returns true if and only if n is prime."
  [n]
  (cond
    (< n 2)        false
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

(defn primes
  "Returns a lazy sequence of primes."
  []
  (filter prime? (iterate inc 2)))

(defn prime-factors
  "Returns the prime factors of n in ascending order.
  
  If primes is given as second parameter, that collection is used as the
  sequence of primes. You may consider to use a cached collection of primes
  when you are going to factorize a lot of numbers.

  Precondition: n > 0
  
  Example: (prime-factors 63525) returns (3 5 5 7 11 11)"
  ([n]
   (prime-factors n (primes)))
  ([n primes]
   (assert (> n 0))
   (loop [result [], n n, primes primes]
     (let [prime (first primes)]
       (cond
         (= n 1)            (reverse result)
         (divisor? n prime) (recur (cons prime result) (quot n prime) primes)
         :else              (recur result              n              (rest primes)))))))

(defn count-occurrences
  "Returns a map of the items of coll to the number of occurrences.
  
  Example: (count-occurrences [2 5 2 2 3 8 8 5]) returns {8 2, 3 1, 5 2, 2 3}"
  [coll]
  (reduce (fn [result x] (assoc result x (inc (get result x 0)))) {} coll))

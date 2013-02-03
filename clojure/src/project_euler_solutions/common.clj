(ns project-euler-solutions.common)

(defn divisor?
  "Returns true if and only if n is divisible by d."
  [n d]
  (= (rem n d) 0))

(defn some-divisor
  "Returns the first d in ds where d is a divisor of n, else nil."
  [n & ds]
  (first (filter #(divisor? n %) ds)))

(defn divisors
  "Returns the divisors of n in ascending order.

  Example: (divisors 28) returns (1 2 4 7 14 28)"
  [n]
  (let [upper-bound     (inc (Math/floor (Math/sqrt n)))
        lower-divisors  (filter #(divisor? n %) (range 1 upper-bound))
        higher-divisors (map #(quot n %) lower-divisors)]
    (sort (set (concat lower-divisors higher-divisors)))))

(defn proper-divisors
  "Returns the proper divisors of n in ascending order.

  Example: (proper-divisors 28) returns (1 2 4 7 14)"
  [n]
  (drop-last (divisors n)))

(defn sum-of-proper-divisors [n]
  (reduce + (proper-divisors n)))

(defn pow 
  "Returns n raised to power of x, both integers.
  
  Precondition: x >= 0"
  [n x]
  (assert (>= x 0))
  (nth (iterate #(* % n) 1N) x))

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

(defn number-to-digits
  "Returns n as a list of digits.

  Precondition: n >= 0

  Example: (number-to-digits 52893) returns (5 2 8 9 3)"
  [n]
  (assert (>= n 0))
  (if (< n 10)
    (list n)
    (reverse (map #(rem % 10) (take-while #(not (zero? %)) (iterate #(quot % 10) n))))))

(defn sum-of-digits
  "Returns the sum of the digits of n.

  Precondition: n >= 0"
  [n]
  (assert (>= n 0))
  (reduce + (number-to-digits n)))

(defn count-occurrences
  "Returns a map of the items of coll to the number of occurrences.
  
  Example: (count-occurrences [2 5 2 2 3 8 8 5]) returns {8 2, 3 1, 5 2, 2 3}"
  [coll]
  (reduce (fn [result x] (assoc result x (inc (get result x 0)))) {} coll))

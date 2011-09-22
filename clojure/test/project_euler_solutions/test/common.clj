(ns project-euler-solutions.test.common
  (:use clojure.test)
  (:use project-euler-solutions.common))

(deftest test-divisor?
  (are [result] (true? result)
       (divisor? 1 1)
       (divisor? 15 3)
       (divisor? 15 4 5)
       (divisor? 15 10 2 5 9))
  (are [result] (false? result)
       (divisor? 1)
       (divisor? 1 2)
       (divisor? 15 4)
       (divisor? 15 10 2 9)))

(deftest test-prime?
  (are [pred result] (pred result)
       false? (prime? -2)
       false? (prime? -1)
       false? (prime?  0)
       false? (prime?  1)
       true?  (prime?  2)
       true?  (prime?  3)
       false? (prime?  4)
       true?  (prime?  5)
       false? (prime?  6)
       true?  (prime?  7)
       false? (prime?  8)
       false? (prime?  9)
       false? (prime? 10)
       true?  (prime? 11)
       true?  (prime? 23)
       false? (prime? 24)))

(deftest test-primes
  (is (= [2 3 5 7 11 13 17 19 23] (take 9 (primes)))))

(deftest test-prime-factors
  (are [expected actual] (= expected actual)
       [2]             (prime-factors     2)
       [3]             (prime-factors     3)
       [2 2]           (prime-factors     4)
       [3 3]           (prime-factors     9)
       [3 5 5 7 11 11] (prime-factors 63525)))

(deftest test-count-occurrences
  (are [expected actual] (= expected actual)
       {}                                     (count-occurrences nil)
       {}                                     (count-occurrences [])
       {13 1}                                 (count-occurrences [13])
       {1 1, 2 4, 3 4, 8 1, 10 2, 11 3, 45 1} (count-occurrences [2 11 8 3 45 1 11 2 3 2 3 10 3 11 2 10])))

(ns project-euler-solutions.test.common
  (:use clojure.test)
  (:use project-euler-solutions.common))

(deftest test-divisor?
  (are [result] (true? result)
       (divisor? 1 1)
       (divisor? 15 3)
       (divisor? 15 5))
  (are [result] (false? result)
       (divisor? 1 2)
       (divisor? 15 4)
       (divisor? 15 10)))

(deftest test-some-divisor
  (are [expected actual] (= expected actual)
       1 (some-divisor 1 1)
       3 (some-divisor 15 3)
       5 (some-divisor 15 4 5)
       5 (some-divisor 15 10 2 5 9)
       nil (some-divisor 1)
       nil (some-divisor 1 2)
       nil (some-divisor 15 4)
       nil (some-divisor 15 10 2 9)))

(deftest test-proper-divisors
  (are [expected actual] (= expected actual)
       '(1 2 4 7 14)                    (proper-divisors  28)
       '(1 2 4 5 10 11 20 22 44 55 110) (proper-divisors 220)
       '(1 2 4 71 142)                  (proper-divisors 284)))

(deftest test-sum-of-proper-divisors
  (are [expected actual] (= expected actual)
        28 (sum-of-proper-divisors  28)
       284 (sum-of-proper-divisors 220)
       220 (sum-of-proper-divisors 284)))

(deftest test-pow
  (are [expected actual] (= expected actual)
                             1 (pow 1  0)
                             1 (pow 2  0)
                             1 (pow 3  0)
                             2 (pow 2  1)
                             4 (pow 2  2)
                         32768 (pow 2 15)
                33232930569601 (pow 7 16)
       1180591620717411303424N (pow 2 70)))

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

(deftest test-number-to-digits
  (are [expected actual] (= expected actual)
       '(0)         (number-to-digits 0)
       '(1)         (number-to-digits 1)
       '(9)         (number-to-digits 9)
       '(1 0)       (number-to-digits 10)
       '(5 2 8 9 3) (number-to-digits 52893)))

(deftest test-sum-of-digits
  (are [expected actual] (= expected actual)
        0 (sum-of-digits       0)
        5 (sum-of-digits       5)
       31 (sum-of-digits 4765234)))

(deftest test-count-occurrences
  (are [expected actual] (= expected actual)
       {}                                     (count-occurrences nil)
       {}                                     (count-occurrences [])
       {13 1}                                 (count-occurrences [13])
       {1 1, 2 4, 3 4, 8 1, 10 2, 11 3, 45 1} (count-occurrences [2 11 8 3 45 1 11 2 3 2 3 10 3 11 2 10])))

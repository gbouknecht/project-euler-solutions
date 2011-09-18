(ns project-euler-solutions.problem-0004
  (:use [project-euler-solutions.common]))

(defn- products [num-of-digits]
  (let [start (int (Math/pow 10 (dec num-of-digits)))
        end   (int (Math/pow 10 num-of-digits))]
    (for [x (range start end) y (range x end)] (* x y))))

(defn- palindrome? [s]
  (let [s (str s)]
    (= s (apply str (reverse s)))))

(defn- max-palindrome-product [num-of-digits]
  (reduce max (filter palindrome? (products num-of-digits))))

(defn answer []
  (max-palindrome-product 3))

(ns project-euler-solutions.problem-0009
  (:use [project-euler-solutions.common]))

(defn- pyth-triplet? [a b c]
  (and (< 0 a b c)
       (= (+ (* a a) (* b b)) (* c c))))

(defn- find-pyth-triplet [sum]
  (first (for [a (range 1 sum) b (range (inc a) sum) :when (pyth-triplet? a b (- sum a b))] [a b (- sum a b)])))

(defn- product-pyth-triplet [sum]
  (apply * (find-pyth-triplet sum)))

(defn answer []
  (product-pyth-triplet 1000))

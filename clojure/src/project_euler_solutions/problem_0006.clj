(ns project-euler-solutions.problem-0006
  (:use [project-euler-solutions.common]))

(defn- square [n]
  (* n n))

(defn- sum-of-squares [n]
  (reduce + (map square (range 1 (inc n)))))

(defn- square-of-sum [n]
  (square (reduce + (range 1 (inc n)))))

(defn- difference-between-sum-of-squares-and-square-of-sum [n]
  (Math/abs (- (sum-of-squares n) (square-of-sum n))))

(defn answer []
  (difference-between-sum-of-squares-and-square-of-sum 100))

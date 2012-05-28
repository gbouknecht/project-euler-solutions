(ns project-euler-solutions.problem-0023
  (:use [project-euler-solutions.common])
  (:import (java.util.concurrent Executors)))

(def available-processors (.. Runtime (getRuntime) (availableProcessors)))

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

(defn- sum-between [start end]
  (reduce + (filter not-sum-of-two-abundant-numbers? (range start end))))

(defn- create-task [upper-limit number-of-tasks task-size task-number]
  (let [last-task (= (dec number-of-tasks) task-number)
        start     (* task-size task-number)
        end       (if last-task upper-limit (+ start task-size))]
    (fn [] (sum-between start end))))

(defn- create-tasks [upper-limit number-of-tasks]
  (let [task-size (quot upper-limit number-of-tasks)]
    (map #(create-task upper-limit number-of-tasks task-size %) (range number-of-tasks))))

(defn- sum
  ([upper-limit]
   (sum upper-limit 1))
  ([upper-limit number-of-tasks]
   (let [pool  (Executors/newFixedThreadPool available-processors)
         tasks (create-tasks upper-limit number-of-tasks)]
     (try
       (reduce + (map #(.get %) (.invokeAll pool tasks)))
       (finally (.shutdown pool))))))

(defn answer []
  (sum 28124 100))

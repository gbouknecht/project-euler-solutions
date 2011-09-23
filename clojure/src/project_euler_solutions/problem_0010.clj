(ns project-euler-solutions.problem-0010
  (:use [project-euler-solutions.common])
  (:import (java.util.concurrent Executors)))

(def available-processors (.. Runtime (getRuntime) (availableProcessors)))

(defn- sum-of-primes-between [start end]
  (apply + (filter prime? (range start end))))

(defn- create-task [upper-limit number-of-tasks task-size task-number]
  (let [last-task (= (dec number-of-tasks) task-number)
        start     (* task-size task-number)
        end       (if last-task upper-limit (+ start task-size))]
    (fn [] (sum-of-primes-between start end))))

(defn- create-tasks [upper-limit number-of-tasks]
  (let [task-size (quot upper-limit number-of-tasks)]
    (map #(create-task upper-limit number-of-tasks task-size %) (range number-of-tasks))))

(defn- sum-of-primes-below
  ([upper-limit]
   (sum-of-primes-below upper-limit 1))
  ([upper-limit number-of-tasks]
   (let [pool  (Executors/newFixedThreadPool available-processors)
         tasks (create-tasks upper-limit number-of-tasks)]
     (try
       (apply + (map #(.get %) (.invokeAll pool tasks)))
       (finally (.shutdown pool))))))

(defn answer []
  (sum-of-primes-below 2000000 100))

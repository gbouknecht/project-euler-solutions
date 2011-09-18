(ns project-euler-solutions.problem-0014
  (:use [project-euler-solutions.common]))

(defn- chain-length [chain-lengths n]
  (loop [length 0, n n]
    (if (contains? chain-lengths n)
      (+ length (chain-lengths n))
      (let [length (inc length)]
        (cond
          (= n 1)   length
          (even? n) (recur length (quot n 2))
          :else     (recur length (+ (* n 3) 1)))))))

(defn- chain-lengths-under [n]
  (reduce (fn [chain-lengths n] (assoc chain-lengths n (chain-length chain-lengths n))) {} (range 1 n)))

(defn- longest-chain-under [n]
  (first (reduce #(if (>= (second %1) (second %2)) %1 %2) (chain-lengths-under n))))

(defn answer []
  (longest-chain-under 1000000))

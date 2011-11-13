(ns project-euler-solutions.problem-0022
  (:require [clojure.string :as string])
  (:use [project-euler-solutions.common]))

(defn- read-names []
  (map #(string/replace % "\"" "") (string/split (slurp "src/project_euler_solutions/problem_0022/names.txt") #",")))

(defn- alphabetical-value [name]
  (apply + (map #(- (int %) 64) name)))

(assert (= 53 (alphabetical-value "COLIN")))

(defn- name-score [name position]
  (* (alphabetical-value name) position))

(assert (= 49714 (name-score "COLIN" 938)))

(defn- names-scores [names]
  (map name-score names (iterate inc 1)))

(defn answer []
  (apply + (names-scores (sort (read-names)))))

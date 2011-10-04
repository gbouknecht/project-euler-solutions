(ns project-euler-solutions.problem-0019
  (:use [project-euler-solutions.common]))

(defstruct date :year :month :day :day-of-week)

(def days-of-week [:sun :mon :tue :wed :thu :fri :sat])

(defn- leap-year? [year]
  (or (and (divisor? year 4) (not (divisor? year 100))) (divisor? year 400)))

(defn- number-of-days [year month]
  (cond
    (#{1 3 5 7 8 10 12} month) 31
    (#{4 6 9 11}        month) 30
    (#{2}               month) (if (leap-year? year) 29 28)))

(defn- next-date [{year :year, month :month, day :day, day-of-week :day-of-week}]
  (assert (<= 1 month 12))
  (assert (<= 1 day 31))
  (assert (or (nil? day-of-week) ((set days-of-week) day-of-week)))
  (let [next-day         (inc (rem day (number-of-days year month)))
        next-month       (if (= next-day 1) (inc (rem month 12)) month)
        next-year        (if (and (= next-day 1) (= next-month 1)) (inc year) year)
        next-day-of-week (if day-of-week (second (drop-while #(not= % day-of-week) (cycle days-of-week))))]
    (struct date next-year next-month next-day next-day-of-week)))

(defn- same-dates [{year1 :year, month1 :month, day1 :day} {year2 :year, month2 :month, day2 :day}]
  (and (= year1 year2) (= month1 month2) (= day1 day2)))

(defn- dates [start-date end-date]
  (take-while #(not (same-dates % end-date)) (iterate next-date start-date)))

(defn- count-sundays-on-first-of-month [start-date end-date]
  (count (filter (fn [{day :day, day-of-week :day-of-week}] (and (= day 1) (= day-of-week :sun))) (dates start-date end-date))))

(defn answer []
  (let [start-date (last (dates (struct date 1900 1 1 :mon) (struct date 1901 1 2)))
        end-date   (struct date 2001 1 1)]
    (count-sundays-on-first-of-month start-date end-date)))

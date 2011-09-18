(ns project-euler-solutions.test.core
  (:use [clojure.test])
  (:require [project-euler-solutions problem-0001
                                     problem-0002
                                     problem-0003
                                     problem-0004
                                     problem-0005
                                     problem-0006
                                     problem-0007
                                     problem-0008
                                     problem-0009
                                     problem-0010
                                     problem-0011
                                     problem-0012
                                     problem-0013
                                     problem-0014]))

(deftest problem-0001 (is (=       233168 (project-euler-solutions.problem-0001/answer))))
(deftest problem-0002 (is (=      4613732 (project-euler-solutions.problem-0002/answer))))
(deftest problem-0003 (is (=         6857 (project-euler-solutions.problem-0003/answer))))
(deftest problem-0004 (is (=       906609 (project-euler-solutions.problem-0004/answer))))
(deftest problem-0005 (is (=    232792560 (project-euler-solutions.problem-0005/answer))))
(deftest problem-0006 (is (=     25164150 (project-euler-solutions.problem-0006/answer))))
(deftest problem-0007 (is (=       104743 (project-euler-solutions.problem-0007/answer))))
(deftest problem-0008 (is (=        40824 (project-euler-solutions.problem-0008/answer))))
(deftest problem-0009 (is (=     31875000 (project-euler-solutions.problem-0009/answer))))
(deftest problem-0010 (is (= 142913828922 (project-euler-solutions.problem-0010/answer))))
(deftest problem-0011 (is (=     70600674 (project-euler-solutions.problem-0011/answer))))
(deftest problem-0012 (is (=     76576500 (project-euler-solutions.problem-0012/answer))))
(deftest problem-0013 (is (=   5537376230 (project-euler-solutions.problem-0013/answer))))
(deftest problem-0014 (is (=       837799 (project-euler-solutions.problem-0014/answer))))

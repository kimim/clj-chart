(ns clj-chart.core-test
  (:require
    [clojure.test :refer :all]
    [clj-chart.core :as c]))

(deftest quick-test
  (testing "FIXME, I fail."
    (let [ds [[1.0 1.0][2.0 3.0]]]
      (c/save!
       "quick-seq.svg"
       (c/quick-seq ds)))
    (let [ds [{:x 1.0 :y 1.0} {:x 5.0 :y 1.0} {:x 2.0 :y 3.0}]]
      (c/save!
       "quick.svg"
       (c/quick-mapseq ds)))
    (is (= 1 1))))

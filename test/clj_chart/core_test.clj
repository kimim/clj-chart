(ns clj-chart.core-test
  (:require
    [clojure.test :refer :all]
    [clj-chart.core :as c]))

(defn save-check-delete [chart]
  (let [img "chart.svg"]
    (c/save! img chart)
    (is (clojure.string/includes? (slurp img) "viewBox"))
    (clojure.java.io/delete-file img)))

(deftest quick-test
  (testing "create quick chart."
    (let [x [1.0 2.0 3.0]
          y [1.0 4.0 9.0]]
      (save-check-delete (c/quick-xy x y)))

    (let [ds [[1.0 1.0][2.0 3.0]]]
      (save-check-delete (c/quick-seq ds)))

    (let [ds [{:x 1.0 :y 1.0} {:x 5.0 :y 1.0} {:x 2.0 :y 3.0}]]
      (save-check-delete (c/quick-mapseq ds)))))

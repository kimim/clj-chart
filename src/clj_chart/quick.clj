(ns clj-chart.quick
  (:import
   (org.knowm.xchart QuickChart)))

(defn quick
  "Create a quick x-y chart"
  [{:keys [title x-axis y-axis legend xs ys]
    :or {title "Quick Chart" x-axis "X" y-axis "Y" legend "Y(X)"}}]
  (let [chart (-> (QuickChart/getChart title x-axis y-axis legend xs ys))]
    chart))

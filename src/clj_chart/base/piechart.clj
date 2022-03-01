(ns clj-chart.base.piechart
  (:import
   (org.knowm.xchart PieChartBuilder)))

(defn piechart
  "Create pie chart"
  [{:keys [title width height series]
    :or {title "Pie Chart" width 600 height 400}}]
  (let [chart (-> (PieChartBuilder.)
                  (.title title)
                  (.width width)
                  (.height height)
                  (.build))]
    (doseq [serie series]
      (.addSeries chart
                  (:name serie)
                  (:value serie)))
    chart))

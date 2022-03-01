(ns clj-chart.bar
  (:import [org.knowm.xchart CategoryChartBuilder]))

(defn bar
  "Create Bar chart"
  [{:keys [title width height x-axis y-axis series]
    :or {title "Bubble Chart" width 600 height 400}}]
  (let [chart (-> (CategoryChartBuilder.)
                  (.width width)
                  (.height height)
                  (.xAxisTitle x-axis)
                  (.yAxisTitle y-axis)
                  (.build))]
    (doseq [serie series]
      (.addSeries chart
                  (:name serie)
                  (:xs serie)
                  (:ys serie)))
    chart))

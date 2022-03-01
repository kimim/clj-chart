(ns clj-chart.bubble
  (:import [org.knowm.xchart BubbleChartBuilder]))

(defn bubble
  "Create Bubble chart"
  [{:keys [title width height x-axis y-axis series]
    :or {title "Bubble Chart" width 600 height 400}}]
  (let [chart (-> (BubbleChartBuilder.)
                  (.width width)
                  (.height height)
                  (.xAxisTitle x-axis)
                  (.yAxisTitle y-axis)
                  (.build))]
    (doseq [serie series]
      (.addSeries chart
                  (:name serie)
                  (:xs serie)
                  (:ys serie)
                  (:size serie)))
    chart))

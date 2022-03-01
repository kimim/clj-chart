(ns clj-chart.donut
  (:import
   (org.knowm.xchart PieChartBuilder
                     PieSeries$PieSeriesRenderStyle)))

(defn donut
  "Create donut chart"
  [{:keys [title width height series]
    :or {title "Pie Chart" width 600 height 400}}]
  (let [chart (-> (PieChartBuilder.)
                  (.title title)
                  (.width width)
                  (.height height)
                  (.build))]
;; chart.getStyler().setDefaultSeriesRenderStyle(PieSeriesRenderStyle.Donut);
    (.setDefaultSeriesRenderStyle (.getStyler chart)
                                  PieSeries$PieSeriesRenderStyle/Donut)
    (doseq [serie series]
      (.addSeries chart
                  (:name serie)
                  (:value serie)))
    chart))

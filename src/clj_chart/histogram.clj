(ns clj-chart.histogram
  (:import
   (org.knowm.xchart CategoryChartBuilder
                     Histogram)))

(defn histogram
  "Create a histogram chart"
  [{:keys [title width height x-axis y-axis series min max bins]
    :or {title "histogram" width 600 height 400}}]
  (let [chart (-> (CategoryChartBuilder.)
                  (.width width)
                  (.height height)
                  (.title title)
                  (.xAxisTitle x-axis)
                  (.yAxisTitle y-axis)
                  (.build))]
    (doseq [serie series]
      (let [histogram (if (and min max)
                        (Histogram. (second serie) bins min max)
                        (Histogram. (second serie) bins))]
        (.addSeries chart
                    (first serie)
                    (.getxAxisData histogram)
                    (.getyAxisData histogram))))
    chart))

(ns clj-chart.base.xychart
  (:import
   (org.knowm.xchart XYChartBuilder)
   (org.knowm.xchart.style Styler$LegendPosition)
   (org.knowm.xchart XYSeries$XYSeriesRenderStyle)))

(defn xychart
  "Base XY Chart"
  [{:keys [title width height series-render-style series
           title-visible legend-position marker-size y-axis-logarithmic]
    :or {title "XY Chart" width 600 height 400
         series-render-style XYSeries$XYSeriesRenderStyle/Line
         title-visible true
         legend-position Styler$LegendPosition/OutsideE
         marker-size 5
         y-axis-logarithmic false}}]
  (let [chart (-> (XYChartBuilder.)
                  (.width width)
                  (.height height)
                  (.build))]
    (-> chart
        (.getStyler)
        (.setDefaultSeriesRenderStyle series-render-style)
        (.setChartTitleVisible title-visible)
        (.setLegendPosition legend-position)
        (.setMarkerSize marker-size)
        (.setYAxisLogarithmic y-axis-logarithmic))
    (doseq [serie series]
      (.addSeries chart
                  (:name serie)
                  (:xs serie)
                  (:ys serie)))
    chart))

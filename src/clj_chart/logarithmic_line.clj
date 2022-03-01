(ns clj-chart.logarithmic-line
  (:require [clj-chart.base.xychart :as xychart])
  (:import [org.knowm.xchart XYSeries$XYSeriesRenderStyle]))

(defn logarithmic-line
  "Create Logarithmic Line chart"
  [{:keys [title width height series]
    :or {title "Logarithmic Chart" width 600 height 400}}]
  (xychart/xychart {:title title
                    :width width
                    :series-render-style XYSeries$XYSeriesRenderStyle/Line
                    :y-axis-logarithmic true
                    :marker-size 10
                    :height height
                    :series series}))

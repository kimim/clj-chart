(ns clj-chart.line
  (:require [clj-chart.base.xychart :as xychart])
  (:import [org.knowm.xchart XYSeries$XYSeriesRenderStyle]))

(defn line
  "Create Line chart"
  [{:keys [title width height series]
    :or {title "Line Chart" width 600 height 400}}]
  (xychart/xychart {:title title
                    :width width
                    :series-render-style XYSeries$XYSeriesRenderStyle/Line
                    :marker-size 10
                    :height height
                    :series series}))

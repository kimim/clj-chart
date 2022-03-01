(ns clj-chart.scatter
  (:require [clj-chart.base.xychart :as xychart])
  (:import [org.knowm.xchart XYSeries$XYSeriesRenderStyle]))

(defn scatter
  "Create Scatter chart"
  [{:keys [title width height series]
    :or {title "Scatter Chart" width 600 height 400}}]
  (xychart/xychart {:title title
                    :width width
                    :series-render-style XYSeries$XYSeriesRenderStyle/Scatter
                    :marker-size 10
                    :height height
                    :series series}))

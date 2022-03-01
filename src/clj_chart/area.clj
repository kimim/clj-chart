(ns clj-chart.area
  (:require [clj-chart.base.xychart :as xychart])
  (:import [org.knowm.xchart XYSeries$XYSeriesRenderStyle]))

(defn area
  "Create area chart"
  [{:keys [title x-axis y-axis width height series]
    :or {title "Area Chart" x-axis "X" y-axis "Y" width 600 height 400}}]
  (xychart/xychart {:title title
                    :width width
                    :series-render-style XYSeries$XYSeriesRenderStyle/Area
                    :marker-size 10
                    :height height
                    :series series}))

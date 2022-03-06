(ns clj-chart.chart
  (:require
   [clj-chart.base :as base])
  (:import [java.awt Color]
           [org.knowm.xchart
            CategoryChartBuilder
            BubbleChartBuilder
            PieChartBuilder
            Histogram
            PieSeries$PieSeriesRenderStyle
            XYSeries$XYSeriesRenderStyle]))

(defn bar
  "Create Bar chart"
  [{:keys [title width height x-axis y-axis series]
    :or {title "Bar Chart" width 600 height 400 x-axis "X" y-axis "Y"}}]
  (base/base-chart-category {:title title :width width :height height
                             :x-axis x-axis :y-axis y-axis
                             :style {:series-style :bar}
                             :series series}))

(defn histogram
  "Create a histogram chart"
  [{:keys [title width height x-axis y-axis series min max bins]
    :or {title "histogram" width 600 height 400}}]
  (let [get-serie (fn [serie]
                    (let [histogram (if (and min max)
                                      (Histogram. (second serie) bins min max)
                                      (Histogram. (second serie) bins))]
                      {:name (first serie)
                       :xs (.getxAxisData histogram)
                       :ys (.getyAxisData histogram)}))
        new-series (map get-serie series)]
    (base/base-chart-category {:title title :width width :height height
                               :x-axis x-axis :y-axis y-axis
                               :style {:series-style :bar}
                               :series new-series})))

(defn line
  "Create Line chart"
  [{:keys [title width height series]
    :or {title "Line Chart" width 600 height 400}}]
  (base/base-chart-xy {:title title
                       :width width
                       :series-render-style XYSeries$XYSeriesRenderStyle/Line
                       :marker-size 10
                       :height height
                       :series series}))

(defn step
  "Create Step chart"
  [{:keys [title width height series]
    :or {title "Line Chart" width 600 height 400}}]
  (base/base-chart-xy {:title title
                       :width width
                       :series-render-style XYSeries$XYSeriesRenderStyle/Step
                       :marker-size 0
                       :height height
                       :series series}))

(defn area
  "Create area chart"
  [{:keys [title x-axis y-axis width height series]
    :or {title "Area Chart" x-axis "X" y-axis "Y" width 600 height 400}}]
  (base/base-chart-xy {:title title
                       :width width
                       :series-render-style XYSeries$XYSeriesRenderStyle/Area
                       :marker-size 10
                       :height height
                       :series series}))

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

(defn pie
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

(defn scatter
  "Create Scatter chart"
  [{:keys [title width height series]
    :or {title "Scatter Chart" width 600 height 400}}]
  (base/base-chart-xy {:title title
                       :width width
                       :series-render-style XYSeries$XYSeriesRenderStyle/Scatter
                       :marker-size 10
                       :height height
                       :series series}))

(defn logarithmic-line
  "Create Logarithmic Line chart"
  [{:keys [title width height series]
    :or {title "Logarithmic Chart" width 600 height 400}}]
  (base/base-chart-xy {:title title
                       :width width
                       :series-render-style XYSeries$XYSeriesRenderStyle/Line
                       :y-axis-logarithmic true
                       :marker-size 10
                       :height height
                       :series series}))

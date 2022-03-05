(ns clj-chart.chart
  (:require [clj-chart.base.xychart :as xychart])
  (:import [org.knowm.xchart
            CategoryChartBuilder
            BubbleChartBuilder
            PieChartBuilder
            Histogram
            PieSeries$PieSeriesRenderStyle
            XYSeries$XYSeriesRenderStyle]))

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
  (xychart/xychart {:title title
                    :width width
                    :series-render-style XYSeries$XYSeriesRenderStyle/Scatter
                    :marker-size 10
                    :height height
                    :series series}))

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

(ns clj-chart.base
  (:import [org.knowm.xchart
            XYChartBuilder
            CategoryChartBuilder
            PieChartBuilder
            CategorySeries$CategorySeriesRenderStyle]
           [org.knowm.xchart.style Styler$LegendPosition]
           [org.knowm.xchart XYSeries$XYSeriesRenderStyle]))

(def render-style-map
  {:bar CategorySeries$CategorySeriesRenderStyle/Bar
   :line CategorySeries$CategorySeriesRenderStyle/Line
   :area CategorySeries$CategorySeriesRenderStyle/Area
   :scatter CategorySeries$CategorySeriesRenderStyle/Scatter
   :steppedbar CategorySeries$CategorySeriesRenderStyle/SteppedBar
   :step CategorySeries$CategorySeriesRenderStyle/SteppedBar
   :stick CategorySeries$CategorySeriesRenderStyle/Stick})

(defn set-category-chart-style!
  [chart {:keys [x-axis-max-label-count series-style overlap]
            :or {x-axis-max-label-count 20
                 series-style :bar
                 overlap false}}]
  (let [styler (-> chart
                   (.getStyler))]
    (.setXAxisMaxLabelCount styler x-axis-max-label-count)
    (.setOverlapped styler overlap)
    (.setDefaultSeriesRenderStyle styler (render-style-map series-style))))

(defn set-serie-style!
  [serie {:keys [fill-color render-style]
          :or {fill-color nil
              render-style nil}}]
  (when fill-color
    (.setFillColor serie fill-color))
  (when render-style
    (.setChartCategorySeriesRenderStyle (render-style-map render-style))))

(defn base-chart-category
  "Base Category Chart"
  [{:keys [title width height x-axis y-axis style series ]
    :or {title "Category Chart" width 600 height 400 }}]
  (let [chart (-> (CategoryChartBuilder.)
                  (.width width)
                  (.height height)
                  (.xAxisTitle x-axis)
                  (.yAxisTitle y-axis)
                  (.build))]
    (set-category-chart-style! chart style)
    (doseq [serie series]
      (let [the-serie (.addSeries chart
                                  (:name serie)
                                  (:xs serie)
                                  (:ys serie))
            the-style (:style serie)]
        (if the-style
          (set-serie-style! the-serie the-style))))
    chart))


(defn base-chart-pie
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


(defn base-chart-xy
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

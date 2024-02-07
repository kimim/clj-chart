(ns clj-chart.core
  (:import
   (org.knowm.xchart SwingWrapper
                     VectorGraphicsEncoder
                     VectorGraphicsEncoder$VectorGraphicsFormat
                     QuickChart)))

(defn save!
  "Save chart as image file."
  [file-name chart]
  (VectorGraphicsEncoder/saveVectorGraphic
   chart file-name VectorGraphicsEncoder$VectorGraphicsFormat/SVG))

(defn view!
  "View chart in a Swing window."
  [chart]
  (.displayChart (SwingWrapper. chart)))

(defn quick-xy
  "Create a quick chart."
  [x-data y-data & {:keys [title x-label y-label legend]
                    :or {title " " x-label " " y-label " " legend " "}}]
  (let [chart (QuickChart/getChart title x-label y-label legend x-data y-data)
        _ (when (= legend " ")
            (.setLegendVisible (.getStyler chart) false))]
    chart))

(defn quick-seq
  "Create quick chart with a mapseq."
  [data-seq & {:keys [title x-label y-label legend]
               :or {title " " x-label " " y-label " " legend " "}}]
  (let [x-data (map first data-seq)
        y-data (map second data-seq)]
    (quick-xy x-data y-data
              :title title :x-label x-label :y-label y-label :legend legend)))

(defn quick-mapseq
  "Create quick chart with a mapseq."
  [data-mapseq & {:keys [title x-label y-label legend]
                  :or {title " " x-label " " y-label " " legend " "}}]
  (let [[x y] (keys (first data-mapseq))
        data-seq (map (juxt #(get % x) #(get % y)) data-mapseq)]
    (quick-seq data-seq
               :title title :x-label x-label :y-label y-label :legend legend)))

(ns clj-chart.plot
  (:import
   (org.knowm.xchart SwingWrapper
                     VectorGraphicsEncoder
                     VectorGraphicsEncoder$VectorGraphicsFormat)))

(defn view!
  "View chart in a Swing window"
  [chart]
  (.displayChart (SwingWrapper. chart)))

(defn store!
  "Store chart in file"
  [chart format filepath]
  (VectorGraphicsEncoder/saveVectorGraphic
      chart filepath VectorGraphicsEncoder$VectorGraphicsFormat/SVG))

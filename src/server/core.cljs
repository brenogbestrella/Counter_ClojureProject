(ns server.core
  (:require
   [reagent.dom :as rdom]
   [views.counter :refer [counter-panel fetch-counter!]]
   [events.core]  
   [subs.core])) 

(defn ^:dev/after-load mount-root []
  (rdom/render [counter-panel] (.getElementById js/document "app"))
  (fetch-counter!))

(defn init []
  (mount-root))





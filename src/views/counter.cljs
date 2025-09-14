(ns views.counter)

(defn ^:export -main []
  (let [app (.getElementById js/document "app")]
    (set! (.-innerHTML app) "Olá")))

(set! js/window.onload -main)

;; (defn -main []
;;   (println "Olá"))

;; (defn main-panel []
;;   (let [counter (re-frame/subscribe [::subs/counter])]
;;     [:div
;;      [:h1 "Counter"]
;;      [:p "Current value: " @counter]
;;      [:button {:on-click #(re-frame/dispatch [::events/increment-counter])}
;;       "Increment"]]))  

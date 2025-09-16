(ns views.counter
  (:require
   [re-frame.core :as rf])) 

(defn fetch-counter! []
  (-> (js/fetch "http://localhost:3000/counter/count")
      (.then (fn [response] (.json response)))
      (.then (fn [data] (rf/dispatch [:initialize-with-value (.-value data)])))
      (.catch (fn [err] (js/console.error "Error fetching counter value:" err)))))

(defn increment-counter! []
  (-> (js/fetch "http://localhost:3000/counter/increment" #js {:method "PUT"})
      (.finally (fn [_] (fetch-counter!)))  
      (.catch (fn [err] (js/console.error "Error incrementing counter:" err)))))

(defn reset-counter! []
  (-> (js/fetch "http://localhost:3000/counter/reset" #js {:method "DELETE"})
      (.finally (fn [] (fetch-counter!)))
      (.catch (fn [err] (js/console.error "Error reseting counter:" err)))))
      


(defn counter-panel []
  (let [counter @(rf/subscribe [:counter])]
    [:div
     [:h1 "COUNTER"]
     [:p {:id "counter"} (str counter)]
     [:div.buttons
      [:button {:on-click increment-counter!} "Increment"]
      [:button {:on-click reset-counter!} "Reset"]]]))




;; (defn -main []
;;   (println "Olá"))

;; (defn main-panel []
;;   (let [counter (re-frame/subscribe [::subs/counter])]
;;     [:div
;;      [:h1 "Counter"]
;;      [:p "Current value: " @counter]
;;      [:button {:on-click #(re-frame/dispatch [::events/increment-counter])}
;;       "Increment"]]))  

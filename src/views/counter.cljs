(ns views.counter)

(defn get-counter-value! [value]
  (when-let [el (.getElementById js/document "counter")]
    (set! (.-textContent el) (str value))))

(defn fetch-counter []
  (-> (js/fetch "http://localhost:3000/counter/count")
      (.then (fn [resp]
               (js/console.log "Resposta raw do fetch:" resp)
               (.json resp)))
      (.then (fn [data]
               (js/console.log "Dados convertidos:" data)
               (get-counter-value! (.-value data))))
      (.catch (fn [err]
                (js/console.error "Erro ao buscar contador:" err)
                (get-counter-value! "error")))))

(defn ^:export -main []
  (fetch-counter))

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

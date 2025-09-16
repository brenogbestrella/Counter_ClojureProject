(ns routes.router
  (:require
   [cheshire.core :as json]
   [datomic.controller :as controller]
   [datomic.config :as init]
   [reitit.ring :as ring]
   [ring.adapter.jetty :refer [run-jetty]]
   [ring.middleware.cors :as cors])) 

(defn response [data]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

(def api-routes
  [["/favicon.ico" {:get (fn [_] {:status 204})}]
   ["/counter"
    ["/count" {:get (fn [_] (response {:value (controller/get-counter-value)}))}]
    ["/increment" {:put (fn [_] (response {:value (controller/add-counter-value)}))}]
    ["/reset" {:delete (fn [_] (response {:value (controller/reset-counter-value)}))}]]])


(def app-handler
  (ring/ring-handler
   (ring/router api-routes)
   (ring/create-default-handler)))

(def app
  (-> app-handler
      (cors/wrap-cors :access-control-allow-origin [#"http://localhost:9000"]
                      :access-control-allow-methods [:get :put :post :delete])))

(defn -main []
  (init/init-db!)
  (println "Servidor de Backend rodando em http://localhost:3000")
  (run-jetty app {:port 3000 :join? false}))
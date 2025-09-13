(ns routes.router
  (:require
   [cheshire.core :as json]
   [datomic.controller :as controller]
   [datomic.config :as init]
   [reitit.ring :as ring]
   [ring.adapter.jetty :refer [run-jetty]]))

(defn response [data]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})

(def app
  (ring/ring-handler
   (ring/router
    [["/favicon.ico" {:get (fn [_] {:status 204})}]
     ["/counter"
      ["/get" {:get (fn [_] (response {:value (controller/get-counter-value)}))}]
      ["/increment" {:put (fn [_] (response {:value (controller/add-counter-value)}))}]
      ["/reset" {:delete (fn[_] (response {:value (controller/reset-counter-value)}))}]]])))

(defn -main []
  (init/init-db!)
  (println "Server running at http://localhost:3000")
  (run-jetty app {:port 3000 :join? false}))
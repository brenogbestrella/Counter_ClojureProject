(ns server.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.response :as resp]))

(defn app [request]
  (or
   (resp/resource-response (:uri request) {:root "public"})
   (resp/resource-response "index.html" {:root "public"})
   (-> (resp/response "Not Found")
       (resp/status 404))))

(defn -main []
  (println "Servidor de Frontend rodando em http://localhost:9000")
  (run-jetty app {:port 9000 :join? true}))


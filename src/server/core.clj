(ns server.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.util.response :refer [resource-response]]))

(defn app [request]
  (let [uri (:uri request)]
    (or (resource-response (subs uri 1) {:root "public"})
        (resource-response "index.html" {:root "public"})
        {:status 404 :body "Not Found"})))

(defn -main []
  (println "Servidor rodando em http://localhost:9000")
  (run-jetty app {:port 9000 :join? true}))


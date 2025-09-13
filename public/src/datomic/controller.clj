(ns datomic.controller
  (:require [datomic.client.api :as d]
            [datomic.counter :as counter]
            [datomic.config :as config]))

(def conn config/conn)

(defn get-counter-value []
  (counter/get-counter-number! (d/db conn)))

(defn reset-counter-value []
  (counter/reset-counter! (d/db conn)))

(defn add-counter-value []
  (let [current (get-counter-value)
        new-val (inc current)]
    (counter/insert-value! conn {:counters/value new-val})))
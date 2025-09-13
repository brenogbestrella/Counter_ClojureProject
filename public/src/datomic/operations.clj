(ns datomic.operations
  (:require [datomic.client.api :as d]))

(defn get-counter-number!
  "Gets the value of the counter"
  [db]
  (:counter/value
   (d/pull db [:counter/value] [:counter/id "main"])))

(defn add-one!
  "Add one unit to the counter"
  [conn]
  (let [db (d/db conn)
        current (or (get-counter-number! db) 0)
        new-val (inc current)]
    (d/transact conn {:tx-data [{:counter/id "main" :counter/value new-val}]})))

(defn reset-counter!
  "Reset the value of the counter"
  [conn]
  (d/transact conn {:tx-data [[:db/add [:counter/id "main"] :counter/value 0]]}))
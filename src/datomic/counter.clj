(ns datomic.counter
  (:require [datomic.client.api :as d]))

(defn get-counter-number!
  "Gets the value of the counter"
  [db]
  (:counters/value
   (d/pull db [:counters/value] [:counters/id "main"])))

(defn insert-value!
  "Add a value to the counter"
  [conn m]
  (let [val (:counters/value m)]
    (d/transact conn {:tx-data [{:counters/id "main" :counters/value val}]})))

(defn reset-counter!
  "Reset the value of the counter"
  [conn]
  (d/transact conn {:tx-data [[:db/add [:counters/id "main"] :counters/value 0]]}))
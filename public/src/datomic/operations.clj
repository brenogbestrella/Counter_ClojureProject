(ns datomic.operations
  (:require [datomic.client.api :as d]))

(defn add-one!
  "Add one unit to the counter"
  [conn {:keys [counter]}]
  (d/transact conn {:tx-data [{:counter/value counter}]}))

(defn get-counter-number!
  "Gets the value of the counter"
  [db]
  (:counter/value
   (d/pull db [:counter/value] [:counter/id "main"])))
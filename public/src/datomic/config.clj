(ns datomic.config
  (:require [datomic.client.api :as d]))

(def client (d/client {:server-type :datomic-local
                       :system "dev"}))

(d/create-database client {:db-name "counter"})

(def conn (d/connect client {:db-name "counter"}))

(def counter-schema
  [{:db/ident :counter/id
    :db/unique :db.unique/identity
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "The id of the counter"}
   {:db/ident :counter/value
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one
    :db/doc "The value of the counter"}])

(d/transact conn {:tx-data counter-schema})

(defn delete-database []
  (d/delete-database client {:db-name "counter"}))
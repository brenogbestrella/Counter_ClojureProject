(ns datomic.config
  (:require [datomic.client.api :as d]))

(def client (d/client {:server-type :datomic-local
                       :system "dev"
                       :storage-dir "/mnt/c/datomic"}))

(def conn (d/connect client {:db-name "counters"}))

(def counters-schema
  [{:db/ident :counters/id
    :db/unique :db.unique/identity
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "The id of the counter"}
   {:db/ident :counters/value
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one
    :db/doc "The value of the counter"}])

(defn init-db! []
  (try
    (d/create-database client {:db-name "counters"})
    (println "Counters Db created.")
    
    (d/transact conn {:tx-data counters-schema})
    (println "Created Schema.")

    (when-not (d/pull (d/db conn) [:counters/value] [:counters/id "main"])
      (d/transact conn {:tx-data [{:counters/id "main"
                                   :counters/value 0}]}))
    (println "Initial value defined to '0'.")

    (catch Exception e
      (println "Db already exists or error while creating it:" (.getMessage e)))))

(defn delete-database []
  (d/delete-database client {:db-name "counters"}))
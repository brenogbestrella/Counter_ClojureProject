(ns events.core
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 :initialize-with-value
 (fn [db [_ value]]
   (assoc db :counter value))) 

(rf/reg-event-db
 :set-counter
 (fn [db [_ value]]
   (assoc db :counter value)))
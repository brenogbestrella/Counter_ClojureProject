(ns subs.core
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :counter
 (fn [db _]
   (:counter db))) 
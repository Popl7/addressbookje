(ns addressbookje.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::contacts
 (fn [db]
   (:contacts db)))

(re-frame/reg-sub
 ::loading
 (fn [db]
   (:loading db)))

(re-frame/reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))

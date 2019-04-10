(ns addressbookje.events
  (:require
   [re-frame.core :as re-frame]
   [addressbookje.db :as db]
   [ajax.core :as ajax]
   [day8.re-frame.http-fx]
   [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::set-active-panel
 (fn-traced [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/reg-event-db
 :good-http-result
 (fn [db [_ contacts]]
   (-> db
       (assoc :loading false)
       (assoc :contacts contacts))))

(re-frame/reg-event-fx
 ::load-contacts
 (fn [{:keys [db]} _]
   {:db (assoc db :loading true)
    :http-xhrio {:method          :get
                  :uri             "/api/contacts"
                  :timeout         8000                                           ;; optional see API docs
                  :response-format (ajax/json-response-format {:keywords? true})  ;; IMPORTANT!: You must provide this.
                  :on-success      [:good-http-result]
                  :on-failure      [:bad-http-result]}
    }))

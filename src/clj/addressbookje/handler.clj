(ns addressbookje.handler
  (:require [compojure.core :refer [GET POST defroutes routes]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.json :refer [wrap-json-response]]))

(defonce contacts (atom [{:name "Carlo Sciolla"
                          :email "carlo.sciolla@gmail.com"}]))

(defn add-contact
  "Adds a new contact to the in-memory db"
  [contacts name email]
  (swap! contacts conj {:name name
                        :email email}))

(defroutes api-routes
  (GET "/api/contacts"  []
    (response @contacts))
  (POST "/api/contacts" [name email]
    (add-contact contacts name email)))

(defroutes static-routes
  (GET "/" [] (resource-response "index.html" {:root "public"}))
  (resources "/"))

(def app (routes static-routes
                 (wrap-json-response api-routes)))

(def dev-handler (-> #'app wrap-reload))

(def handler app)

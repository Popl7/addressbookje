(ns addressbookje.handler
  (:require [compojure.core :refer [GET POST defroutes routes]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response response status]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]))

(defonce contacts (atom [{:name "Carlo Sciolla"
                          :email "carlo.sciolla@gmail.com"}]))

(defn add-contact
  "Adds a new contact to the in-memory db"
  [contacts {:strs [name email]}]
  (swap! contacts conj {:name name
                        :email email})
  (-> (response "Created")
      (status 201)))

(defroutes api-routes
  (GET "/api/contacts"  []
    (response @contacts))
  (POST "/api/contacts" {body :body}
    (add-contact contacts body)))

(defroutes static-routes
  (GET "/" [] (resource-response "index.html" {:root "public"}))
  (resources "/"))

(def app (routes static-routes
                 (-> api-routes
                     wrap-json-response
                     wrap-json-body)))

(def dev-handler (-> #'app wrap-reload))

(def handler app)

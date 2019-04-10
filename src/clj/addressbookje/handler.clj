(ns addressbookje.handler
  (:require [compojure.core :refer [GET defroutes routes]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.json :refer [wrap-json-response]]))

(defroutes api-routes
  (GET "/api/contacts" [] (response "test")))

(defroutes static-routes
  (GET "/" [] (resource-response "index.html" {:root "public"}))
  (resources "/"))

(def app (routes static-routes
                 (wrap-json-response api-routes)))

(def dev-handler (-> #'app wrap-reload))

(def handler app)

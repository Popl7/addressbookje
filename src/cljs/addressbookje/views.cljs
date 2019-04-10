(ns addressbookje.views
  (:require
   [re-frame.core :as re-frame]
   [addressbookje.subs :as subs]
   [addressbookje.events :as events]
   ))


;; home

(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])
        contacts (re-frame/subscribe [::subs/contacts])
        loading (re-frame/subscribe [::subs/loading])]
    (fn []
      [:div
       [:h2 "contacts"]
       [:button.btn.btn-primary {:on-click #(re-frame/dispatch [::events/load-contacts])} "Load contacts"]
       (if @loading
         [:div "Loading..."]
         [:table.table
          [:thead
           [:tr
            [:th "Name"]
            [:th "Email"]]]
          [:tbody
           (for [contact @contacts]
             ^{:key contact}
             [:tr
              [:td (:name contact)]
              [:td (:email contact)]])]])]
       )))


;; about

(defn about-panel []
  [:div
   [:h1 "This is the About Page."]

   [:div
    [:a {:href "#/"}
     "go to Home Page"]]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))

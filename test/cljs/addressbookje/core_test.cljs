(ns addressbookje.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [addressbookje.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))

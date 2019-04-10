(ns addressbookje.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [addressbookje.core-test]))

(doo-tests 'addressbookje.core-test)

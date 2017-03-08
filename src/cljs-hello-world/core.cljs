(ns cljs-hello-world.core)
;; (:require [clojure.browser.repl :as repl]))
;; (:require [cljs.nodejs :as nodejs]))
;; (:require [cljsjs.react]))

;; (defonce conn
;; (repl/connect "http://localhost:9000/repl"))

(enable-console-print!)
;; (nodejs/enable-util-print!)

(println "Hello world!")
;; (defn -main [& args]
;;   (println "Hello world!"))

;; (set! *main-cli-fn* -main)

;; ADDED
(defn foo [a b]
  (* a b))  ;; CHANGED

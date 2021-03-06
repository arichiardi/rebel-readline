(ns rebel-dev.main
  (:require
   [rebel-readline.core
    :refer [line-reader clj-repl-read with-readline-input-stream help-message
            syntax-highlight-prn]]
   [rebel-readline.jline-api :as api]
   [rebel-readline.service.core :as srv]
   [rebel-readline.service.impl.local-clojure-service :as local-clj-service]
   [rebel-readline.service.impl.simple :as simple-service]
   [rebel-readline.utils :refer [*debug-log*]]
   [clojure.main]))

(defn -main [& args]
  (println "This is the DEVELOPMENT REPL in rebel-dev.main")
  (let [reader (line-reader (local-clj-service/create #_{:key-map :viins}))]
    (println (help-message))
    (binding [api/*line-reader* (:line-reader reader)
              srv/*service* (:service reader)
              *debug-log* true]
      (clojure.main/repl
       :prompt (fn [])
       :print syntax-highlight-prn
       :read (clj-repl-read reader)))))

#_(defn -main [& args]
  (let [repl-env (nash/repl-env)]
    (with-readline-input-stream (cljs-service/create {:repl-env repl-env})
      (cljs-repl/repl repl-env :prompt (fn [])))))

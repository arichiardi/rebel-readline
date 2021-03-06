# rebel-readline-cljs

A library that supplies a rebel readline service for the default
clojurescript repl and a `cljs.repl/repl-read` replacement.

## Quick try

`lein trampoline run` will get you into a Nashorn backed CLJS repl with the readline working.

## Usage

A simple usage example:

```clojure
(let [repl-env (cljs.repl.nashorn/repl-env)
      line-reader (rebel-readline.core/line-reader 
                   (rebel-readline-cljs.service/create {:repl-env repl-env}))]
  (cljs.repl/repl repl-env
   ;; the prompt is supplied by the readline program
   :prompt (fn [])
   :read (rebel-readline-cljs.core/cljs-repl-read line-reader)))
```

Or:

```clojure
(let [repl-env (cljs.repl.nashorn/repl-env)]
  (rebel-readline.core/with-readline-input-stream (rebel-readline-cljs.service/create 
                                                   {:repl-env repl-env})
    (cljs.repl/repl repl-env :prompt (fn [])))
```

## License

Copyright © 2018 Bruce Hauman

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

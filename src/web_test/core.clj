(ns web-test.core
  (:require [etaoin.api :as web])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  (let [driver (web/chrome)]
    (try (doseq [i (range 9)]
           (web/go driver "http://store.3vyd.com/")
           (web/wait-visible driver [{:id "username"}])
           (web/fill driver {:id "username"} "test")
           (web/fill driver {:id "password"} "111111")
           (web/click driver {:class "ant-btn submit ant-btn-primary ant-btn-lg"})
           (web/wait-visible driver {:class "toggle"})
           (web/click driver {:class "toggle"})
           (web/query driver {:tag :span :fn/has-text "退出"})
           (web/click-el driver (web/query driver {:tag :span :fn/has-text "退出"}))
           )
         (catch Exception e
           (web/screenshot driver "page.png")))))

(ns fate-dice.core
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]))

;; -------------------------
;; Views

(def state (r/atom []))

(defn home-page []
  [:div {:align "center"} [:h2 "Fate Dice"]
   [:div {:style {:width "100%"
                  :height "100%"
                  :min-height 200
                  :border "1px solid grey"
                  :align "center"
                  :padding 2}}
    [:p (get @state 0)]
    (if (> (count @state) 0) (for [[s d] @state] [:p (str d "=" s)]) nil)]
   [:input {:id "input" :type "text" :style {:width "100%"}}]])

;; -------------------------
;; Functions

(enable-console-print!)

(defn parse-dice
  "Takes the raw input from the JS event and returns a sequence of value lists representing dice configurations"
  [dice-string]
  (re-seq #"(-?)\s?([0-9]*)[dD]([0-9f]+)|(-?)\s?([0-9]+)|([a-zA-z]+)" dice-string))

(defn rand-sided-die
  "Returns a rand-int 1-n inclusive"
  [n]
  (inc (rand-int n)))

(defn rand-fate-die
  "Returns a rand-int -1-1 inclusive"
  [n]
  (dec (rand-int 3)))

(defn roll-sided-dice
  "Actual math of rolling dice"
  [dice]
  (let [die-expr (if (= (str (get dice 3)) "f")
                   rand-fate-die
                   rand-sided-die)]
    (reduce (fn [%1 %2] (vec [(+ (get %1 0) %2) (conj (get %1 1) %2)])) [0 []] (take (int (get dice 2)) (repeatedly #(* (die-expr (int (get dice 3))) (if (= (get dice 1) "-") -1 1)))))))

(defn roll-dice
  "Takes the parsed dice and actually rolls them"
  [dice-seq]
  (reset! state (map #(if (some? (get %1 3)) (roll-sided-dice %1)) dice-seq)))

(defn handle-input-event
  "Takes a dice roll event and processes it"
  [event]
  (if (= (.-keyCode event) 13)
    (do (.preventDefault event)
        (js/console.log (roll-dice (parse-dice (.-value (js/document.getElementById "input"))))))))

(defn main []
  (.addEventListener (js/document.getElementById "input") "keyup" handle-input-event))

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app"))
  (main))

(defn ^:export init! []
  (mount-root))

